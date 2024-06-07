from flask import Flask, jsonify, render_template, request
from flask_socketio import SocketIO, emit
import openai

app = Flask(__name__)
socketio = SocketIO(app)

# Clave de la API de OpenAI
openai.api_key = 'key'

# Datos simulados de gastos
gastos = [
    {"id": 1, "concepto": "Alquiler", "monto": 700.00, "fecha": "2024-06-05"},
    {"id": 2, "concepto": "Supermercado", "monto": 150.00, "fecha": "2024-06-06"},
    {"id": 3, "concepto": "Transporte", "monto": 200.00, "fecha": "2024-06-07"},
    {"id": 4, "concepto": "Ocio", "monto": 300.00, "fecha": "2024-06-08"},
    {"id": 5, "concepto": "Educación", "monto": 100.00, "fecha": "2024-06-09"},
    {"id": 6, "concepto": "Ocio", "monto": 150.00, "fecha": "2024-06-10"}
]

def calcular_gastos():
    # Inicializar variables
    categorias = {}
    total_gastos = 0

    # Eliminar conceptos duplicados y calcular la distribución de gastos
    for gasto in gastos:
        concepto = gasto["concepto"]
        monto = gasto["monto"]
        if concepto not in categorias:
            categorias[concepto] = monto
        else:
            categorias[concepto] += monto
        total_gastos += monto

    # Obtener los tres primeros conceptos con datos
    sorted_conceptos = sorted(categorias.items(), key=lambda x: x[1], reverse=True)
    top_conceptos = sorted_conceptos[:3]

    # Preparar los datos para el gráfico de torta
    labels = [concepto for concepto, _ in top_conceptos]
    data = [(monto / total_gastos) * 100 for _, monto in top_conceptos]
    backgroundColor = ["#5C28B1", "#01D492", "#E87309", "#E53F52", "#FFC107"][:len(labels)]

    return {"labels": labels, "backgroundColor": backgroundColor, "data": data}

# Ruta para mostrar el gráfico en una página HTML y como una API JSON
@app.route('/kpi/gastos', methods=['GET'])
def get_gastos_kpi():
    data = calcular_gastos()
    return jsonify(data)

@app.route('/')
def index():
    return render_template('index.html')

# Evento de conexión de SocketIO
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

def obtener_respuesta(solicitud):
    if solicitud.lower().startswith("analizar mis gastos"):
        total_gastos = sum(gasto["monto"] for gasto in gastos)
        promedio_gastos = total_gastos / len(gastos)
        mensaje = f"El análisis de gastos indica que el promedio de gastos es de {promedio_gastos:.2f} unidades monetarias."
        return mensaje
    else:
        try:
            respuesta = openai.Completion.create(
                engine="chat-gtp3-turbo",
                prompt=solicitud,
                temperature=0.7,
                max_tokens=150
            )
            if respuesta.choices:
                return respuesta.choices[0].text.strip()
            else:
                return "No se pudo obtener una respuesta de OpenAI."
        except Exception as e:
            return f"Error al procesar la solicitud: {str(e)}"



if __name__ == '__main__':
    socketio.run(app, debug=True)
