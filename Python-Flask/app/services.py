import requests
from flask import current_app

def get_external_data():
    try:
        response = requests.get(current_app.config['API_URL'])
        response.raise_for_status()  # Asegura que se lance una excepción en caso de error HTTP
        return response.json()
    except requests.RequestException as e:
        # En caso de error al hacer la solicitud
        return {'error': f'Failed to fetch data: {str(e)}'}
    except ValueError as e:
        # En caso de error al decodificar JSON
        return {'error': f'Failed to parse JSON: {str(e)}'}
