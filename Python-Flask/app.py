from flask import Flask, render_template, jsonify, send_file, request
import matplotlib.pyplot as plt
import io

app = Flask(__name__)

# Datos de ejemplo para los KPI
kpi_data = {
    "kpi1": {"value": 123, "description": "KPI 1 descripción"},
    "kpi2": {"value": 456, "description": "KPI 2 descripción"}
}

@app.route('/')
def index():
    # Datos para el gráfico en el index
    x = [1, 2, 3, 4, 5]
    y = [10, 20, 25, 30, 40]

    # Crear el gráfico de línea
    img = io.BytesIO()
    plt.figure()
    plt.plot(x, y, marker='o')
    plt.title('Gráfico de Línea')
    plt.xlabel('Eje X')
    plt.ylabel('Eje Y')
    plt.savefig(img, format='png')
    img.seek(0)
    plt.close()

    # Convertir el gráfico a base64
    import base64
    img_base64 = base64.b64encode(img.getvalue()).decode()

    return render_template('index.html', img_base64=img_base64)

@app.route('/api/kpi', methods=['GET'])
def get_kpi():
    return jsonify(kpi_data)

@app.route('/api/plot', methods=['GET'])
def plot():
    # Parámetros opcionales para personalizar el gráfico
    kpi_type = request.args.get('type', 'line')

    # Datos de ejemplo para el gráfico
    x = [1, 2, 3, 4, 5]
    y = [10, 20, 25, 30, 40]

    # Crear el gráfico según el tipo solicitado
    img = io.BytesIO()
    plt.figure()
    
    if kpi_type == 'bar':
        plt.bar(x, y)
        plt.title('Gráfico de Barras')
    else:
        plt.plot(x, y, marker='o')
        plt.title('Gráfico de Línea')
    
    plt.xlabel('Eje X')
    plt.ylabel('Eje Y')

    # Guardar el gráfico en un objeto BytesIO
    plt.savefig(img, format='png')
    img.seek(0)
    plt.close()

    # Enviar el archivo como una respuesta HTTP
    return send_file(img, mimetype='image/png')

if __name__ == '__main__':
    app.run(debug=True)
