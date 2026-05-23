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

### 3.Excepciones Propias
Se crearon tres excepciones personalizadas para validar datos incorrectos
o faltantes en la creación de objetos:

- `EmpleadoSinNombreException` — lanzada cuando se intenta crear un
  `Empleado` con nombre null o vacío.
- `EmpleadoSinCorreoException` — lanzada cuando se intenta crear un
  `Empleado` con correo null o vacío.
- `DepartamentoSinNombreException` — lanzada cuando se intenta crear un
  `Departamento` con nombre null o vacío.
