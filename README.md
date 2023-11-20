# Explicación de la Arquitectura Hexagonal y DDD en el Proyecto

## Arquitectura Hexagonal

### Capas Concéntricas:

- **Centro (Dominio):** El paquete `domain` contiene las clases que representan el núcleo del dominio (`Price` y `PriceRepository`). Este es el corazón de la aplicación y contiene la lógica empresarial.

- **Adaptadores (Adapters):** Los paquetes `application`, `adapter`, y `infrastructure` contienen adaptadores que conectan el núcleo con el mundo exterior. `PriceApplicationService` actúa como un adaptador de aplicación, `PriceController` es un adaptador de interfaz de usuario, y `JpaPriceRepository` y `SpringDataJpaPriceRepository` son adaptadores de infraestructura.

### Independencia de la Infraestructura:

- Las interfaces como `PriceRepository` en `domain.repository` son independientes de la tecnología de persistencia concreta. Están diseñadas para definir un contrato que la infraestructura debe cumplir.

- Los adaptadores de infraestructura (`JpaPriceRepository` y `SpringDataJpaPriceRepository`) implementan estas interfaces, proporcionando la implementación concreta y tratando los detalles de la tecnología de persistencia.

### Inversión de Dependencias (DI):

- Las dependencias están invertidas: el núcleo no depende directamente de los detalles de la implementación de la infraestructura. En cambio, la infraestructura implementa interfaces definidas en el dominio.

## Diseño Dirigido por el Dominio (DDD)

### Modelo de Dominio Rico:

- La clase `Price` en `domain.model` es un ejemplo de un modelo de dominio rico que encapsula la lógica de negocio relacionada con el precio.

### Ubicación de la Lógica de Dominio:

- La lógica de dominio se encuentra en la capa central (`domain`). En este caso, el cálculo del precio y la definición de la interfaz del repositorio se encuentran en este paquete.

### Ubicación de la Persistencia:

- La implementación concreta de la persistencia (JPA en este caso) se encuentra en `infrastructure.repository`, manteniendo separados los detalles de implementación de la lógica de dominio.

### Bounded Context:

- El diseño sigue la idea de bounded contexts de DDD. Los paquetes `application`, `adapter`, y `infrastructure` representan diferentes contextos que interactúan con el núcleo del dominio (`domain`).

### Interfaces Explícitas:

- Las interfaces (`PriceRepository`) son explícitas y definidas en el dominio, proporcionando contratos claros para la infraestructura.

En resumen, el proyecto sigue la arquitectura hexagonal al organizar las capas concéntricas y siguiendo los principios de DDD al centrarse en el modelo de dominio y mantener una separación clara entre la lógica de negocio y los detalles de implementación. Esto facilita la comprensión, el mantenimiento y la evolución de la aplicación a medida que cambian los requisitos del negocio.




# Zara E-Commerce

En la base de datos del comercio electrónico de la compañía, existe la tabla **PRICES**, que refleja el precio final (PVP) y la tarifa que se aplica a un producto de una cadena en un rango de fechas determinadas. A continuación, se muestra un ejemplo de la tabla con los campos relevantes:

### PRICES

| BRAND_ID | START_DATE               | END_DATE                 | PRICE_LIST | PRODUCT_ID | PRIORITY | PRICE | CURR |
|----------|--------------------------|--------------------------|------------|------------|----------|-------|------|
| 1        | 2020-06-14 00:00:00     | 2020-12-31 23:59:59     | 1          | 35455      | 0        | 35.50 | EUR  |
| 1        | 2020-06-14 15:00:00     | 2020-06-14 18:30:00     | 2          | 35455      | 1        | 25.45 | EUR  |
| 1        | 2020-06-15 00:00:00     | 2020-06-15 11:00:00     | 3          | 35455      | 1        | 30.50 | EUR  |
| 1        | 2020-06-15 16:00:00     | 2020-12-31 23:59:59     | 4          | 35455      | 1        | 38.95 | EUR  |

**Campos:**

- **BRAND_ID:** Clave foránea de la cadena del grupo (1 = ZARA).
- **START_DATE , END_DATE:** Rango de fechas en el que aplica el precio tarifa indicado.
- **PRICE_LIST:** Identificador de la tarifa de precios aplicable.
- **PRODUCT_ID:** Identificador código de producto.
- **PRIORITY:** Desambiguador de aplicación de precios. Si dos tarifas coinciden en un rango de fechas se aplica la de mayor prioridad (mayor valor numérico).
- **PRICE:** Precio final de venta.
- **CURR:** ISO de la moneda.

### Se Pide:

Construir una aplicación/servicio en Spring Boot que provea un endpoint REST de consulta tal que:

- Acepte como parámetros de entrada: fecha de aplicación, identificador de producto, identificador de cadena.
- Devuelva como datos de salida: identificador de producto, identificador de cadena, tarifa a aplicar, fechas de aplicación y precio final a aplicar.

Se debe utilizar una base de datos en memoria (tipo H2) e inicializarla con los datos del ejemplo. Puedes cambiar el nombre de los campos y añadir otros nuevos si lo deseas, eligiendo el tipo de dato que consideres adecuado.

### Desarrollar Tests:

Desarrollar unos tests al endpoint REST que validen las siguientes peticiones al servicio con los datos del ejemplo:

- **Test 1:** Petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)
- **Test 2:** Petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)
- **Test 3:** Petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)
- **Test 4:** Petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)
- **Test 5:** Petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)
