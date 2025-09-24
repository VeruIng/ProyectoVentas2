Proyecto Ventas – Segunda Entrega

Integrantes
- veruska aguas castelar
- diego mauricio martinez veja
- lina marcela perez murcia
- mayerly agudelo monje

Descripción
Este proyecto corresponde a la segunda entrega de la asignatura Conceptos Fundamentales de Programación.  
En esta fase trabajamos en la generación de reportes a partir de los archivos de productos, vendedores y ventas creados en la primera entrega.

 Objetivos
- Leer y procesar archivos de texto con información de vendedores, productos y ventas.
- Generar reportes ordenados de manera automática.
- Practicar estructuras de datos como `Map`, ciclos y manejo de archivos en Java.

Archivos principales
- GenerateInfoFiles.java → Genera los archivos de vendedores, productos y ventas (ya coherentes entre sí).  
- Main.java → Procesa los archivos generados y crea reportes.  
- productos.txt → Lista de productos con su precio.  
- vendedores.txt → Lista de vendedores (tipo doc, número, nombre, apellido).  
- ventas_XXXXX.txt → Archivo de ventas de cada vendedor.  
- reporte_vendedores.txt → Reporte de vendedores con el total recaudado (ordenado de mayor a menor).  
- reporte_productos.txt → Reporte de productos con cantidad y precio.

Resultados de la segunda entrega
1. Reporte de Vendedores:  
   Muestra el nombre completo del vendedor junto con el total de dinero recaudado, ordenado de mayor a menor.  
2. Reporte de Productos  
Muestra el ID del producto, la cantidad total vendida y su precio. 
 
Cómo ejecutar
1. Ejecutar `GenerateInfoFiles.java` para generar los archivos de prueba.  
2. Ejecutar `Main.java` para procesar los archivos y generar los reportes.  
3. Revisar los resultados en:
- `reporte_vendedores.txt`
- `reporte_productos.txt`

Aprendizaje
En esta entrega reforzamos:
- La importancia de mantener coherencia entre los datos generados y los procesados.  
- El uso de `HashMap` para relacionar documentos de vendedores y productos.  
- La práctica de ordenar resultados antes de generar reportes.  


