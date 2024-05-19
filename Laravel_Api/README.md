# Instalación de Laravel

Este es un breve tutorial sobre cómo instalar Laravel en tu sistema local. Laravel es un marco de trabajo de PHP que facilita el desarrollo de aplicaciones web modernas y escalables.

## Requisitos del Sistema

Antes de comenzar, asegúrate de que tu sistema cumple con los siguientes requisitos:

- PHP >= 8.1.25
- Composer
- Node.js & NPM
- Git
- Servidor web (Apache, Nginx, etc.)
- xammp
## Paso 1: Instalar Composer

Composer es una herramienta de gestión de dependencias para PHP que se utiliza ampliamente en el desarrollo de aplicaciones Laravel. Puedes descargar e instalar Composer desde [getcomposer.org](https://getcomposer.org/).

## Paso 2: Instalar Laravel

Una vez que hayas instalado Composer, puedes utilizarlo para crear un nuevo proyecto Laravel. Abre una terminal y ejecuta el siguiente comando:

```
bash
composer create-project --prefer-dist laravel/laravel nombre-del-proyecto
```

Esto creará un nuevo directorio llamado `nombre-del-proyecto` y descargará todas las dependencias necesarias para un proyecto Laravel.

## Paso 3: Configurar el Entorno

Después de instalar Laravel, necesitarás configurar algunas variables de entorno. Copia el archivo `.env.example` y cámbiale el nombre a `.env`. Este archivo contiene la configuración básica para tu aplicación, como la conexión a la base de datos y la clave de cifrado.

```
bash
cp .env.example .env
```

Genera una nueva clave de cifrado para tu aplicación ejecutando el siguiente comando:

```
bash
php artisan key:generate
```

## Paso 4: Ejecutar el Servidor de Desarrollo

Una vez que hayas configurado el archivo `.env`, puedes iniciar el servidor de desarrollo de Laravel ejecutando el siguiente comando en la terminal:

```
bash
php artisan serve
```


Esto iniciará un servidor de desarrollo en `http://localhost:8000`, donde podrás ver tu aplicación Laravel en acción.

## Paso 5: ¡Listo para Codificar!

¡Ahora estás listo para comenzar a codificar tu aplicación Laravel! Puedes encontrar la documentación oficial de Laravel en [laravel.com/docs](https://laravel.com/docs) para obtener más información sobre cómo desarrollar con Laravel.

## Problemas Comunes

Si encuentras algún problema durante la instalación o configuración de Laravel, consulta la [documentación oficial](https://laravel.com/docs) o busca soluciones en la [comunidad de Laravel](https://laracasts.com/discuss).
