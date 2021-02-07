# Simulador de ejecución de procesos.

Proyecto Java que crea la simulación de procesos a traves del uso de una cola anidada en la cual los nodos forman una lista teniendo como atributo el siguiente nodo en la lista. La aplicación pretende mostrar la simulación la ejecución de varios procesos en maquina a traves de un **Diagrama de Gantt**. Estos procesos requieren un tiempo de ejecución pero deben esperar hasta que el proceso anterior sea ejecutado.

## Descripción

En el ejemplo se presenta: 
* Interfaz de usuario desde código Java (sin utilizar asistentes de GUI).
* Enfoque de **ComponentesGráficos** para modularización de responsabilidades.
* **Modularización de código** separando la creación de objetos gráficos.
* Optimizacion de recursos para aplicaciones a traves de **servicios**.
* Optimización de código a traves de **servicios**.
* Personalización avanzada a traves de **servicio**.
* Representación única de objetos de una misma clase (Singleton) para control de **Servicios**.
* Uso de eventos a traves de **ActionListener, MouseListener, FocusListener**.
* Discriminación por clases para control de eventos.
* Uso de **Tablas** y control de información a traves de ellas.
* Uso de ScrollPane para navegación de interfaz.
* Uso de **Graphics y Graphics2D** para pintar en pantalla.
* Animaciones de movimiento con uso de **Timer**.
* Animaciones Gráficas a traves de **Hilos** para representación de simulación en Diagrama de Gantt en tiempo real.

## Demostración

<div align='center'>
    <img  src='./Demostracion/Demostracion.gif'>
    <p>Demostración simulación de procesos.</p>
</div>
