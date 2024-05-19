
# Flask Dashboard API

Este proyecto es una aplicación API en Flask que genera gráficos y proporciona datos de KPI. La aplicación sirve un dashboard que muestra diferentes KPI visualizados mediante gráficos generados dinámicamente.

## Requisitos

- Python 3.6+
- Virtualenv (opcional, pero recomendado)

## Instalación

### Paso 1: Clonar el repositorio

```
bash
git clone 
cd Python_Flask
```

### Paso 2: Crear y activar un entorno virtual

#### En Windows

```bash
python -m venv venv
venv\Scripts\activate
```

#### En macOS/Linux

```bash
python3 -m venv venv
source venv/bin/activate
```

### Paso 3: Instalar dependencias

Con el entorno virtual activado, instala las dependencias necesarias utilizando `pip`:

```
bash
pip install -r requirements.txt
```

### Paso 4: Ejecutar la aplicación

Una vez instaladas las dependencias, puedes ejecutar la aplicación Flask:

```
bash
python app.py
```

Esto iniciará el servidor Flask en `http://127.0.0.1:5000`.

## Uso

### Dashboard

Para ver el dashboard con los gráficos de KPI, abre un navegador web y navega a `http://127.0.0.1:5000`.

### API Endpoints

#### Obtener Datos KPI

Puedes obtener los datos KPI en formato JSON utilizando la siguiente ruta:

```http
GET /kpi
```

Ejemplo de respuesta:

```json
{
    "kpi1": {"value": 123, "description": "KPI 1 descripción"},
    "kpi2": {"value": 456, "description": "KPI 2 descripción"}
}
```

#### Generar Gráficos

Puedes generar gráficos accediendo a las siguientes rutas:

##### Gráfico de Línea

```http
GET /plot?type=line
```

##### Gráfico de Barras

```http
GET /plot?type=bar
```

## Estructura del Proyecto

```
flask_project/
├── app.py
├── templates/
│   └── index.html
├── static/
├── requirements.txt
└── README.md
```

- **app.py**: El archivo principal de la aplicación Flask que define las rutas y la lógica para generar gráficos y datos KPI.
- **templates/**: Directorio que contiene la plantilla HTML para el dashboard.
- **static/**: Directorio para archivos estáticos (CSS, JS, imágenes).
- **requirements.txt**: Archivo que lista todas las dependencias del proyecto.
- **README.md**: Este archivo.

## Contribuir

Si deseas contribuir a este proyecto, por favor, abre un issue o envía un pull request.

## Licencia

Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo LICENSE para más detalles.

### Archivo `requirements.txt`

Para crear el archivo `requirements.txt`, ejecuta el siguiente comando mientras tu entorno virtual está activado:

```
bash
pip freeze > requirements.txt
```

Esto generará una lista de todas las dependencias instaladas en tu entorno virtual.

Este README proporciona una guía clara sobre cómo instalar, ejecutar y usar la aplicación Flask. Puedes personalizarlo según las necesidades específicas de tu proyecto.