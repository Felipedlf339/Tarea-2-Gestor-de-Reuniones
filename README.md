# Tarea-2-Gestor-de-Reuniones

## Integrantes
- Felipe de la Fuente
- Javiera Aravena
- Martin García

## Descripcion del proyecto
Sistema de gestión de reuniones desarrollado en Java con Maven.
Permite gestionar reuniones virtuales y presenciales, invitar empleados,
departamentos e invitados externos, registrar asistencia y generar informes.

## UML

## Decisiones de diseño

### 1.Interfaz `invitable`
Se definió la interfaz `Invitable` con el método `invitar()` para representar
cualquier entidad que pueda ser invitada a una reunión. Esto permite tratar
a `Empleado`, `Departamento` e `InvitadoExterno` de forma uniforme, sin
necesidad de crear métodos separados para cada tipo.

### 2.Clase  `InvitadoExterno`
Se agregó la clase `InvitadoExterno` que implementa `Invitable`. Esta clase
no estaba en el UML original pero fue necesaria para cumplir el requisito
de permitir invitar personas externas a la empresa. Tiene dos atributos:
`nombreCompleto` y `correo`. Al implementar `Invitable`, se integra
naturalmente al sistema sin modificar ninguna clase.

### 3.Cambios en clase `Nota`
Se implementaron el constructor, los métodos (`getContenido()` y `setContenido()`), el
método `toString()` requerido. El diagrama UML original contenía solamente su atributo privado (`contenido` de tipo `String`), por lo que se agrega el getter para poder leer el contenido (recordemos que es privado por lo que no se puede acceder de forma directa), el setter para modificarlo y el constructor para inicializarlo.

### 4.Cambios en clase `Reunion`:
Se implementaron varios cambios respecto a lo presentado originalmente en el UML, El UML sugería utilizar el tipo `Date` para la fecha y la hora prevista, pero en su lugar se opto por usar la API de (`java.time`), utilizando `LocalDate` para la fecha y `LocalTime` para la hora prevista debido a que esta misma API se introdujo para reemplazar `Date` quedando esta obsoleta, el UML mostraba líneas de asociación hacia `Invitacion`, `Asistencia`, `Nota` y `Retraso` (Por medio de asistencia), pero no se definían los atributos ni los metodos para contenerlas por ende se crearon listas (`List<>`) para cada una de estas clases y se añadieron métodos especificos para sus usos (`agregarInvitacion`, `agregarAsistencia`, `agregarRetraso`, `nuevaNota`, `invitarDepartamento`). Se agregan getters y setters omitiendo algunos setters por falta de coherencia (Como modificar externamente asistencias, hora de inicio cuando la reunión ya comenzó entre otras). Se implementa el `toString()` para retornar los datos importantes de la reunión.

### 5.Cambios en clase `ReunionPresencial`
El diagrama UML define a `ReunionPresencial` como una subclase cde `Reunion`, apareciendo únicamente su atributo privado (`- sala: String`). Se agregó el constructor utilizando la palabra `super()` para que la inicialización de los datos se haga en la clase abstracta `Reunion`, localmente instancia el atributo `sala`. Nuevamente por el encapsulamiento se agrega su getter y setter. Se extiende el comportamiento del método heredado agregando un salto de línea y la ubicación física, permitiendo que apareza la sala o lugar elegido para la reunión.

### 6. Cambios en clase `ReunionVirtual`
El diagrama UML define a `ReunionVirtual` como una subclase de `Reunion`, apareciendo únicamente su atributo privado (`- enlace: String`). Se agregó el constructor utilizando la palabra `super()` para que la inicialización de los datos base se haga en la clase abstracta `Reunion`, localmente instancia el atributo `enlace`. Nuevamente por el de encapsulamiento se agregan su getter y setter. Se extiende el comportamiento del método heredado agregando un salto de línea y el enlace, permitiendo que aparezca el enlace para conectarse de forma online a la reunión.


### 7.Excepciones Propias
Se crearon tres excepciones personalizadas para validar datos incorrectos
o faltantes en la creación de objetos:

- `EmpleadoSinNombreException` — lanzada cuando se intenta crear un
  `Empleado` con nombre null o vacío.
- `EmpleadoSinCorreoException` — lanzada cuando se intenta crear un
  `Empleado` con correo null o vacío.
- `DepartamentoSinNombreException` — lanzada cuando se intenta crear un
  `Departamento` con nombre null o vacío.
- `NotaInvalidaException` — lanzada cuando se intenta crear o modificar una `Nota` con un texto nulo, vacío o con solo espacios en blanco.
- `SalaInvalidaException` — lanzada cuando se intenta asignar una sala nula, vacío en blanco a una `ReunionPresencial`.
- `EnlaceInvalidoException` — lanzada cuando se intenta asignar un enlace nulo, vacío o en blanco a una `ReunionVirtual`.
- `ReunionException` — lanzada de forma general para diversos errores que se pueden producir en `Reunion`, se aplica a casos como iniciar una reunión que ya comenzó, finalizarla sin haber empezado, registrar asistencia cuando la reunión ya finalizó entre otros.
