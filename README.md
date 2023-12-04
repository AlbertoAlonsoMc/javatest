# Prueba Técnica - Alberto Alonso
## Descripción
Servicio desarrollado en SprinBoot enfocado a la llamada a un endpoint vía API REST, y sus respectivos tests en JUnit.

## Librerías utilizadas
Spring Web  
Spring Data JPA  
Lombok  
H2 Database  
Model Mapper

## Estructura del proyecto
Nos encontramos con las siguiente estructura de carpetas y archivos importantes tras src/main/:
### java/com.generalsw.javatest
* Config: Configuración del Bean del ModelMapper
* Controller: Alberga el RestController de la aplicación.
* ExceptionHandler: Contiene la clase para un pequeño manejo de excepciones.
* Model: Alberga tanto la entidad como su DTO y su mapeador.
* Repository: Contiene la interfaz del repositorio.
* Service: Consta de la interfaz del servicio y su correspondiente implementación.
### resources
* data.sql: Archivo de inicialización de la base de datos H2 en memoria en cada ejecución.

## Instrucciones de ejecución
Para arrancar el servicio, basta con ejecutarlo desde la clase PruebaTecnicaApplication.java

Una vez arrancado, habría que realizar una llamada al endpoint ("prices/findPrice") con sus respectivos parámetros de entrada. Los parámetros son los siguientes:
- applicationDate: Fecha de búsqueda (formato "YYYY-MM-DD-hh.mm.ss).
- productId: ID del producto.
- brandId: ID de la cadena.

Un ejemplo de una llamada al endpoint sería este:  
localhost:8080/prices/findPrice?applicationDate=2020-06-14-16.31.00&productId=35455&brandId=1

## Notas importantes
Cabe destacar tres aspectos importantes:
* Inyección de dependencias: Haciendo uso de buenas prácticas, se evita el uso de **@Autowired** y se opta por la inyección por constructor. Esto se hace mediante el uso de la etiqueta **@RequiredArgsConstructor** y el modificador **final**.


* Mapeado de DTO: Se hace uso de ModelMapper para transformar los objetos de tipo entidad a objetos de tipo Output Dto, para así facilitar la salida de datos.

 
* Base de datos en memoria: Se utiliza una base de datos H2 inicializada con los datos proporcionados en el enunciado de la prueba.




