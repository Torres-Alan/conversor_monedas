# Conversor de Monedas en Java

Este proyecto consiste en una aplicación de consola desarrollada en Java que permite realizar conversiones de monedas en tiempo real utilizando datos actualizados desde la API pública de ExchangeRate-API. Fue realizado como parte del programa ONE (Oracle Next Education) de Alura Latam.

## Funcionalidades

- Menú interactivo para seleccionar el tipo de conversión de moneda.
- Envío de solicitudes HTTP mediante la clase `HttpClient`.
- Procesamiento de respuestas en formato JSON usando la biblioteca `Gson`.
- Conversión dinámica entre monedas, como:
  - USD a ARS
  - USD a BRL
  - USD a COP
  - USD a CLP
  - USD a MXN

## Requisitos

- JDK 21
- Biblioteca Gson (versión 2.13.1)
- Conexión a Internet para acceder a la API de tasas de cambio

## Estructura del proyecto

- conversor_monedas/
  - .idea/  
    - Configuración del proyecto de IntelliJ
  - lib/
    - gson-2.13.1.jar — Biblioteca Gson agregada manualmente
  - out/
    - production/
      - conversor_monedas/
        - *.class — Archivos compilados automáticamente
  - src/
    - Main.java — Archivo base de prueba
    - ClienteHttp.java — Primera solicitud HTTP básica
    - ClienteHttpPersonalizado.java — Solicitud con moneda base configurable
    - ClienteHttpResponse.java — Manejo completo de la respuesta HTTP
    - AnalizadorJson.java — Análisis del JSON con Gson
    - FiltroDeMonedas.java — Filtrado de monedas específicas del JSON
    - ConversorMonedas.java — Conversión con entrada del usuario
    - Conversor.java — Versión final con menú interactivo
  - .gitignore — Exclusión de archivos para Git
  - conversor_monedas.iml — Archivo de configuración de módulo
  - README.md — Documento de descripción del proyecto

## API Key

Para utilizar esta aplicación, necesitas una clave de API válida de [ExchangeRate-API](https://www.exchangerate-api.com/). Puedes obtenerla creando una cuenta gratuita y copiando tu API key personal.

> Recuerda **no subir tu API key pública al repositorio**. Usa archivos de configuración o variables locales para mantenerla segura.

## Licencia

Este proyecto es de uso educativo y fue desarrollado por Alan Daniel Torres Fuentes como parte de los desafíos del programa ONE de Alura.
