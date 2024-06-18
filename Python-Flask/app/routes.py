# app/routes.py
from flask import Blueprint, jsonify
from flask import Flask, jsonify, render_template,request
from flask_socketio import SocketIO, emit
import openai
import requests
from config import Config
from datetime import datetime
from collections import defaultdict

main_bp = Blueprint('main', __name__)

# Inicializar la aplicación Flask y el socketio
app = Flask(__name__)
socketio = SocketIO(app)

# Clave de la API de OpenAI
openai.api_key = Config.OPENAI_API_KEY

# Función para obtener los gastos desde la API externa
def obtener_gastos():
    try:
        url = f"{Config.API_URL}/getall"
        response = requests.get(url)
        response.raise_for_status()
        return response.json()
    except requests.RequestException as e:
        return {'error': f'Failed to fetch data: {str(e)}'}

# Función para calcular los gastos
def calcular_gastos(gastos):
    # Inicializar variables
    categorias = {}
    total_gastos = 0
    # Calcular la distribución de gastos y el total de gastos
    for gasto in gastos:
        concepto = gasto["concepto"]
        monto = gasto["monto"]
        categorias[concepto] = categorias.get(concepto, 0) + monto
        total_gastos += monto
    
    # Obtener los tres primeros conceptos con datos
    top_conceptos = sorted(categorias.items(), key=lambda x: x[1], reverse=True)[:3]
    return categorias, total_gastos, top_conceptos

# Ruta para mostrar el gráfico de gastos
@main_bp.route('/api/gastos', methods=['GET'])
def get_gastos_kpi():
    gastos = obtener_gastos()
    categorias, total_gastos, top_conceptos = calcular_gastos(gastos)
    # Generar una descripción usando GPT-3.5-turbo
    descripcion_gastos = obtener_descripcion_gpt3_turbo(top_conceptos, total_gastos)
    # Agregar más detalles sobre los gastos
    detalles_gastos = {
        "total_gastos": total_gastos,
        "categorias": categorias,
        "descripcion": descripcion_gastos
    }
    return jsonify(detalles_gastos)

def obtener_descripcion_gpt3_turbo(categorias, total_gastos):
    prompt = (
        "He analizado los gastos de un usuario y quiero proporcionar una descripción clara y útil de sus hábitos de gasto. Aquí están los datos:\n\n"
        f"Categorías de gastos y sus respectivos montos:\n{categorias}\n\n"
        f"Total de gastos: {total_gastos}\n\n"
        "Por favor, proporciona una breve descripción de estos resultados. Además, sugiere formas prácticas y efectivas para que el usuario pueda mejorar sus hábitos de gasto y ahorrar más dinero en el futuro."
    )

    try:
        response = openai.ChatCompletion.create(
            model="gpt-3.5-turbo",
            messages=[
                {"role": "system", "content": "Eres un asistente financiero experto."},
                {"role": "user", "content": prompt}
            ],
            max_tokens=150,
            n=1,
            stop=None,
            temperature=0.7,
        )
        return response['choices'][0]['message']['content'].strip()
    except Exception as e:
        return f'Error generating GPT-3 response: {str(e)}'


@main_bp.route('/gasto')
def gasto():
    return render_template('gasto.html')

@main_bp.route('/presupuesto')
def presupuesto():
    return render_template('presupuesto.html')

@main_bp.route('/distribucion')
def distribucion():
    # Obtener datos de la API de distribución de gastos por categoría
    api_url = 'http://127.0.0.1:5000/api/distribucion-gastos-por-categoria'
    try:
        response = requests.get(api_url)
        response.raise_for_status()
        data = response.json()
        distribucion_gastos = data['distribucion_gastos']
        descripcion = data['descripcion']
    except requests.RequestException as e:
        # Manejar errores de solicitud a la API
        error_message = f'Error al obtener datos de la API: {str(e)}'
        return render_template('error.html', error_message=error_message)
    return render_template('distribucion.html', distribucion_gastos=distribucion_gastos, descripcion=descripcion)

# Función para obtener los gastos desde la API externa
def calcular_distribucion_gastos(gastos):
    distribucion_gastos = defaultdict(float)
    for gasto in gastos:
        categoria = gasto.get("concepto", "Otra")
        monto = gasto.get("monto", 0)
        distribucion_gastos[categoria] += monto
    return dict(distribucion_gastos)

# Función para generar la descripción utilizando ChatGPT
def generar_descripcion_chatgpt(distribucion_gastos):
    prompt = (
        "He analizado los gastos del usuario y he calculado la distribución de gastos por categoría. "
        "Aquí está el resumen:\n\n"
    )
    for categoria, monto in distribucion_gastos.items():
        prompt += f"- {categoria}: ${monto:.2f}\n"
    prompt += "\n¿Puedes proporcionar una breve descripción de estos resultados?"
    
    try:
        response = openai.ChatCompletion.create(
            model="gpt-3.5-turbo",
            messages=[
                {"role": "system", "content": "Eres un asistente financiero experto."},
                {"role": "user", "content": prompt}
            ],
            max_tokens=150,
            n=1,
            stop=None,
            temperature=0.7,
        )
        return response['choices'][0]['message']['content'].strip()
    except Exception as e:
        return f'Error al generar la respuesta de ChatGPT: {str(e)}'


# Endpoint para obtener la distribución de gastos por categoría
@main_bp.route('/api/distribucion-gastos-por-categoria', methods=['GET'])
def obtener_distribucion_gastos_por_categoria():
    gastos = obtener_gastos()
    if 'error' in gastos:
        return jsonify({'error': 'Error al obtener los datos de gastos'})
    distribucion_gastos = calcular_distribucion_gastos(gastos)
    descripcion = generar_descripcion_chatgpt(distribucion_gastos)
    return jsonify({
        'distribucion_gastos': distribucion_gastos,
        'descripcion': descripcion
    })
###


@main_bp.route('/api/gastos-categoria-top5', methods=['GET'])
def calcular_top_5_gastos():
    # Obtener la URL de la API de la solicitud
    url = f"{Config.API_URL}/getall"
    # Manejar el caso de error si la URL no está presente en la solicitud
    if not url:
        return {'error': 'API URL missing in request'}, 400
    # Obtener los datos de gastos
    gastos = obtener_gastos_top5(url)
    # Manejar el caso de error si no se pueden obtener los datos de gastos
    if 'error' in gastos:
        return gastos, 500
    # Procesar los datos de gastos
    top_5_categorias = procesar_gastos(gastos)
    # Obtener respuesta de GPT-3 para mejorar la descripción
    gpt3_response = obtener_respuesta_gpt3(top_5_categorias)
    return {'top_5_categorias': top_5_categorias, 'IA': gpt3_response}

def obtener_gastos_top5(url):
    try:
        response = requests.get(url)
        response.raise_for_status()
        return response.json()
    except requests.RequestException as e:
        return {'error': f'Failed to fetch data from {url}: {str(e)}'}

def procesar_gastos(gastos):
    # Inicializar un diccionario para almacenar los gastos por categoría y fecha
    gastos_por_categoria_fecha = defaultdict(lambda: defaultdict(float))
    # Calcular los gastos totales por categoría y fecha
    for gasto in gastos:
        categoria = gasto['concepto']
        monto = gasto['monto']
        fecha = datetime.strptime(gasto['fecha'], '%Y-%m-%d').date()
        gastos_por_categoria_fecha[categoria][fecha] += monto
    # Calcular el total de gastos por categoría para cada fecha
    total_gastos_por_categoria_fecha = {}
    for categoria, gastos_por_fecha in gastos_por_categoria_fecha.items():
        total_gastos_por_categoria_fecha[categoria] = sum(gastos_por_fecha.values())
    # Obtener las 5 categorías con los gastos más grandes
    top_5_categorias = sorted(total_gastos_por_categoria_fecha.items(), key=lambda x: x[1], reverse=True)[:5]
    return top_5_categorias


def obtener_respuesta_gpt3(top_5_categorias):
    prompt = (
        "Dado el siguiente top 5 de categorías de gastos, por favor proporciona una breve descripción de los resultados:\n\n"
        f"{top_5_categorias}\n\n"
        "Descripción:"
    )

    try:
        response = openai.ChatCompletion.create(
            model="gpt-3.5-turbo",
            messages=[
                {"role": "system", "content": "Eres un asistente útil."},
                {"role": "user", "content": prompt}
            ],
            max_tokens=100,
            n=1,
            stop=None,
            temperature=0.5,
        )
        return response['choices'][0]['message']['content'].strip()
    except Exception as e:
        return f'Error generating GPT-3 response: {str(e)}'

# Rutas para manejar eventos de SocketIO
@socketio.on('connect')
def handle_connect():
    print('Cliente conectado')
    emit('response', {'message': 'Conexión establecida'})

# Evento de desconexión de SocketIO
@socketio.on('disconnect')
def handle_disconnect():
    print('Cliente desconectado')

# Evento personalizado para recibir datos desde el cliente
@socketio.on('analizar_gastos')
def handle_analizar_gastos(data):
    texto_analisis = obtener_respuesta(data['solicitud'])
    emit('respuesta_analisis', {'analisis': texto_analisis})

# Función para obtener respuesta de OpenAI
def obtener_respuesta(solicitud):
    gastos = obtener_gastos()
    if solicitud.lower().startswith("analizar mis gastos"):
        total_gastos = sum(gasto["monto"] for gasto in gastos)
        promedio_gastos = total_gastos / len(gastos)
        mensaje = f"El análisis de gastos indica que el promedio de gastos es de {promedio_gastos:.2f} unidades monetarias."
        return mensaje
    else:
        try:
            respuesta = openai.Completion.create(
                engine="gpt-3.5-turbo",
                prompt=solicitud,
                temperature=0.7,
                max_tokens=150
            )
            if respuesta.choices:
                return respuesta.choices[0].text.strip()
            else:
                return "No se pudo obtener una respuesta de OpenAI."
        except requests.RequestException as e:
            return f"Error al procesar la solicitud: {str(e)}"

# Función para calcular el promedio de gastos por día, mes y año
def calcular_promedio_gastos(gastos):
    gastos_por_dia = defaultdict(float)
    gastos_por_mes = defaultdict(float)
    gastos_por_anio = defaultdict(float)
    num_gastos_por_dia = defaultdict(int)
    num_gastos_por_mes = defaultdict(int)
    num_gastos_por_anio = defaultdict(int)

    for gasto in gastos:
        fecha = datetime.strptime(gasto['fecha'], '%Y-%m-%d')
        monto = gasto['monto']

        # Promedio de gastos por día
        fecha_str = fecha.strftime('%Y-%m-%d')
        gastos_por_dia[fecha_str] += monto
        num_gastos_por_dia[fecha_str] += 1

        # Promedio de gastos por mes
        fecha_mes = fecha.strftime('%Y-%m')
        gastos_por_mes[fecha_mes] += monto
        num_gastos_por_mes[fecha_mes] += 1

        # Promedio de gastos por año
        fecha_anio = fecha.strftime('%Y')
        gastos_por_anio[fecha_anio] += monto
        num_gastos_por_anio[fecha_anio] += 1
    promedio_gastos_por_dia = {fecha: gastos_por_dia[fecha] / num_gastos_por_dia[fecha] for fecha in gastos_por_dia}
    promedio_gastos_por_mes = {fecha: gastos_por_mes[fecha] / num_gastos_por_mes[fecha] for fecha in gastos_por_mes}
    promedio_gastos_por_anio = {fecha: gastos_por_anio[fecha] / num_gastos_por_anio[fecha] for fecha in gastos_por_anio}

    # Generar descripción usando OpenAI
    descripcion = generar_descripcion_promedio(promedio_gastos_por_dia, promedio_gastos_por_mes, promedio_gastos_por_anio)

    return {
        'promedio_gastos_por_dia': promedio_gastos_por_dia,
        'promedio_gastos_por_mes': promedio_gastos_por_mes,
        'promedio_gastos_por_anio': promedio_gastos_por_anio,
        'descripcion': descripcion
    }

# Función para generar la descripción usando OpenAI
def generar_descripcion_promedio(promedio_gastos_por_dia, promedio_gastos_por_mes, promedio_gastos_por_anio):
    # Formatear los promedios de gastos para una mejor presentación
    formatted_promedio_gastos_por_dia = format_promedio_gastos(promedio_gastos_por_dia)
    formatted_promedio_gastos_por_mes = format_promedio_gastos(promedio_gastos_por_mes)
    formatted_promedio_gastos_por_anio = format_promedio_gastos(promedio_gastos_por_anio)

    prompt = (
        "He calculado los promedios de gastos por día, mes y año basados en los datos proporcionados. Aquí están los resultados:\n\n"
        f"Promedio de gastos por día:\n{formatted_promedio_gastos_por_dia}\n\n"
        f"Promedio de gastos por mes:\n{formatted_promedio_gastos_por_mes}\n\n"
        f"Promedio de gastos por año:\n{formatted_promedio_gastos_por_anio}\n\n"
        "Por favor, proporciona una descripción de estos promedios de gastos."
    )

    try:
        response = openai.Completion.create(
            engine="gpt-3.5-turbo",
            prompt=prompt,
            temperature=0.7,
            max_tokens=150
        )
        return response.choices[0].text.strip()
    except Exception as e:
        return f'Error generando descripción: {str(e)}'

def format_promedio_gastos(promedio_gastos_dict):
    formatted_data = ""
    for key, value in promedio_gastos_dict.items():
        formatted_data += f"{key}: {value:.2f}\n"
    return formatted_data


# Endpoint para obtener el promedio de gastos por día, mes y año
@main_bp.route('/api/promedio-gastos', methods=['GET'])
def obtener_promedio_gastos():
    gastos = obtener_gastos()
    if 'error' in gastos:
        return jsonify({'error': 'Error al obtener los datos de gastos'})
    promedio_gastos = calcular_promedio_gastos(gastos)
    return jsonify(promedio_gastos)


############################################
# Cargar datos desde el endpoint
response = requests.get('http://167.99.63.51:27016/api/presupuestosUsuarios')
data = response.json()

def calculate_kpis(presupuestos):
    try:
        # Convertir fechas a objetos datetime
        for presupuesto in presupuestos:
            if "fechaInicio" in presupuesto and isinstance(presupuesto["fechaInicio"], str):
                presupuesto["fechaInicio"] = datetime.strptime(presupuesto["fechaInicio"], "%d-%m-%Y")
            if "fechaFin" in presupuesto and isinstance(presupuesto["fechaFin"], str):
                presupuesto["fechaFin"] = datetime.strptime(presupuesto["fechaFin"], "%d-%m-%Y")

        # Si no hay al menos dos presupuestos, no se puede calcular crecimiento ni cambio mensual promedio
        if len(presupuestos) < 2:
            return None

        # KPI 1: Crecimiento del Presupuesto
        crecimiento_presupuesto = ((presupuestos[-1]["montoTotal"] - presupuestos[-2]["montoTotal"]) / presupuestos[-2]["montoTotal"]) * 100

        # KPI 2: Duración del Presupuesto
        duraciones = [(p["fechaFin"] - p["fechaInicio"]).days for p in presupuestos]

        # KPI 3: Promedio Mensual del Presupuesto
        promedios_mensuales = []
        for p, duracion in zip(presupuestos, duraciones):
            if duracion != 0:
                promedio_mensual = p["montoTotal"] / (duracion / 30)
            else:
                promedio_mensual = 0  # Evitar división por cero
            promedios_mensuales.append(promedio_mensual)

        # KPI 4: Cambio Mensual Promedio del Presupuesto
        cambio_mensual_promedio = ((promedios_mensuales[-1] - promedios_mensuales[-2]) / promedios_mensuales[-2]) * 100

        # Resultados
        kpi_results = {
            "crecimiento_presupuesto": crecimiento_presupuesto,
            "duraciones": duraciones,
            "promedios_mensuales": promedios_mensuales,
            "cambio_mensual_promedio": cambio_mensual_promedio
        }

        return kpi_results
    except Exception as e:
        print(f"Error calculating presupuesto total: {e}")
        return None

@main_bp.route('/api/presupuesto-total', methods=['GET'])
def get_kpis():
    kpi_results = []
    for user_data in data:
        if user_data["presupuestos"]:
            kpis = calculate_kpis(user_data["presupuestos"])
            if kpis:
                kpi_results.append({
                    "usuario": user_data["usuario"]["nombre"],
                    "presupuesto total": kpis
                })
    return jsonify(kpi_results)


######################################
  
@main_bp.route('/api/example', methods=['GET'])
def get_example():
    example_data = {
        "field1": "example1",
        "field2": "example2"
    }
    return jsonify(example_data)
