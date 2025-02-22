CREATE DATABASE LaboratorioClinico;
USE LaboratorioClinico;

CREATE TABLE Clientes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombres VARCHAR(50) NOT NULL,
    apellidoPaterno VARCHAR(50) NOT NULL,
    apellidoMaterno VARCHAR(50) NOT NULL,
    fechaNacimiento DATETIME NOT NULL,
    estaBorrado BIT DEFAULT 0
);

CREATE TABLE Analisis (
    id INT PRIMARY KEY AUTO_INCREMENT,
    fechaHora DATETIME NOT NULL,
    estaBorrado BIT DEFAULT 0,
    idCliente INT NOT NULL,
    FOREIGN KEY (idCliente) REFERENCES Clientes(id)
);

CREATE TABLE Categorias (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE Pruebas (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    idCategoria INT NOT NULL,
    estaBorrado BIT DEFAULT 0,
    FOREIGN KEY (idCategoria) REFERENCES Categorias(id)
);

CREATE TABLE Registros (
    id INT PRIMARY KEY AUTO_INCREMENT,
    idAnalisis INT NOT NULL,
    idPrueba INT NOT NULL,
    FOREIGN KEY (idAnalisis) REFERENCES Analisis(id),
    FOREIGN KEY (idPrueba) REFERENCES Pruebas(id)
);

CREATE TABLE Parametros (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE Mediciones (
    id INT PRIMARY KEY AUTO_INCREMENT,
    idPrueba INT NOT NULL,
    idParametro INT NOT NULL,
    FOREIGN KEY (idPrueba) REFERENCES Pruebas(id),
    FOREIGN KEY (idParametro) REFERENCES Parametros(id)
);

CREATE TABLE Resultados (
    id INT PRIMARY KEY AUTO_INCREMENT,
    idAnalisis INT NOT NULL,
    idParametro INT NOT NULL,
    valor VARCHAR(50) NOT NULL,
    FOREIGN KEY (idAnalisis) REFERENCES Analisis(id),
    FOREIGN KEY (idParametro) REFERENCES Parametros(id)
);


INSERT INTO Clientes (nombres, apellidoPaterno, apellidoMaterno, fechaNacimiento, estaBorrado) 
VALUES ('Juan', 'Pérez', 'Rodríguez', '1990-03-15 00:00:00', 0);

INSERT INTO Categorias (nombre) 
VALUES ('Hematología');

INSERT INTO Pruebas (nombre, idCategoria, estaBorrado) 
VALUES ('Biometría Hemática', 1, 0);

INSERT INTO Parametros (nombre) 
VALUES ('Hemoglobina'), ('Hematocrito'), ('Glóbulos Rojos'), ('Glóbulos Blancos'), ('Plaquetas');

INSERT INTO Mediciones (idPrueba, idParametro) 
VALUES (1, 1),  -- Biometría Hemática - Hemoglobina
       (1, 2),  -- Biometría Hemática - Hematocrito
       (1, 3),  -- Biometría Hemática - Glóbulos Rojos
       (1, 4),  -- Biometría Hemática - Glóbulos Blancos
       (1, 5);  -- Biometría Hemática - Plaquetas

INSERT INTO Analisis (fechaHora, estaBorrado, idCliente) 
VALUES ('2025-02-17 08:30:00', 0, 1);

INSERT INTO Registros (idAnalisis, idPrueba) 
VALUES (1, 1);  -- Análisis 1 incluye la prueba Biometría Hemática

INSERT INTO Resultados (idAnalisis, idParametro, valor) 
VALUES (1, 1, '14.2 g/dL'),   -- Hemoglobina
       (1, 2, '42.1 %'),      -- Hematocrito
       (1, 3, '5.1 mill/µL'), -- Glóbulos Rojos
       (1, 4, '6.8 mill/µL'), -- Glóbulos Blancos
       (1, 5, '250 mill/µL'); -- Plaquetas

