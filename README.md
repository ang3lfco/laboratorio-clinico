# Laboratorio Clínico

Sistema de gestión para un **Laboratorio Clínico**, desarrollado en **Java** con arquitectura en capas.  
Permite la administración de clientes, empleados, análisis clínicos, parámetros, mediciones y resultados de pruebas.  

Incluye interfaz gráfica en **Java Swing**, lógica de negocio separada y conexión a base de datos mediante **MySQL**.

---

## Características principales

- Gestión de **clientes** (registro, edición, consulta).
- Gestión de **empleados**.
- Administración de **análisis clínicos** y **pruebas**.
- Manejo de **parámetros** y **mediciones**.
- Registro de resultados de análisis.
- Generación de **reportes**.
- Interfaz gráfica intuitiva con **Swing**.
- Script SQL incluido para crear la base de datos.

---

## Estructura del proyecto

El proyecto está organizado en las siguientes capas:

- **DTOs/** → Objetos de transferencia de datos.
- **Entidades/** → Representación de las tablas en la base de datos.
- **Negocio/** → Lógica de negocio e interfaces.
- **Persistencia/** (si aplica) → Acceso a datos y queries.
- **Presentación/** → Ventanas y controladores en Swing.
- **laboratorio-clinico-sql.sql** → Script de creación de base de datos.
- **pom.xml** → Configuración Maven y dependencias.
- **libs/** → Librerías externas (ej. `LGoodDatePicker`).

---

## Capturas de la aplicación
<img width="1366" height="727" alt="Screenshot (9937)" src="https://github.com/user-attachments/assets/0c2c983c-c3c9-48db-9a7e-c52f61d07ae9" />
<img width="1366" height="725" alt="Screenshot (9938)" src="https://github.com/user-attachments/assets/7decc352-585e-40a9-a298-63267ded1bf7" />
<img width="1366" height="723" alt="Screenshot (9939)" src="https://github.com/user-attachments/assets/b0f758f4-623f-4e4f-8fcf-40b6a553742c" />
<img width="1366" height="725" alt="Screenshot (9940)" src="https://github.com/user-attachments/assets/6a2fe26c-1a23-4ca4-a747-3a7ea12290b0" />
<img width="1366" height="725" alt="Screenshot (9941)" src="https://github.com/user-attachments/assets/d65f63a2-6969-4e0d-9ea1-eb89f8519fe0" />
<img width="1366" height="725" alt="Screenshot (9942)" src="https://github.com/user-attachments/assets/6ac7c666-fc35-491d-973c-c0cc345afa20" />
<img width="1366" height="725" alt="Screenshot (9943)" src="https://github.com/user-attachments/assets/15fd4037-1546-4463-80dd-38d32664470b" />
<img width="1366" height="725" alt="Screenshot (9944)" src="https://github.com/user-attachments/assets/505a4eb5-1628-4676-a9f0-947c00f0331b" />
<img width="1366" height="723" alt="Screenshot (9945)" src="https://github.com/user-attachments/assets/1f32d998-f25d-4970-8fae-7af6df861271" />
<img width="1366" height="725" alt="Screenshot (9946)" src="https://github.com/user-attachments/assets/73c75c1a-6929-4cef-92db-1c91fec3db95" />
<img width="1366" height="725" alt="Screenshot (9947)" src="https://github.com/user-attachments/assets/33fc79e9-06fd-4963-98c4-124668f7f3eb" />
<img width="1366" height="725" alt="Screenshot (9948)" src="https://github.com/user-attachments/assets/079f2021-0f13-43d6-89d0-3095b0fbfd8e" />
<img width="1366" height="725" alt="Screenshot (9949)" src="https://github.com/user-attachments/assets/73ef002d-50ad-4d73-98d3-d3a58f8fb962" />

---

## Tecnologías utilizadas

- **Java 11+**
- **Java Swing** (interfaz gráfica)
- **MySQL** (base de datos)
- **Maven** (gestión de dependencias)
- **LGoodDatePicker** (selector de fechas en Swing)
