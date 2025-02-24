CREATE DATABASE LaboratorioClinico;
USE LaboratorioClinico;

CREATE TABLE Clientes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombres VARCHAR(50) NOT NULL,
    apellidoPaterno VARCHAR(50) NOT NULL,
    apellidoMaterno VARCHAR(50) NOT NULL,
    fechaNacimiento DATE NOT NULL,
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

CREATE TABLE Empleados(
	id int PRIMARY KEY AUTO_INCREMENT,
	usuario VARCHAR(10) NOT NULL,
    pass VARCHAR(10) NOT NULL,
    tipo ENUM('administrativo', 'capturista') NOT NULL
);
