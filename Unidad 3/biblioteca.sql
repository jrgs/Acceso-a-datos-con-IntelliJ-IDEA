DROP DATABASE "Biblioteca";

CREATE DATABASE "Biblioteca" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Spanish_Spain.1252';


ALTER DATABASE "Biblioteca" OWNER TO postgres;

DROP TABLE IF EXISTS prestamos;
DROP TABLE IF EXISTS libros;
DROP TABLE IF EXISTS usuarios;

CREATE TABLE libros (
  isbn varchar(13) PRIMARY KEY,
  titulo varchar(90) NOT NULL,
  copias INTEGER default '1',
  editorial varchar(60)
);

INSERT INTO libros (isbn, titulo, copias, editorial) VALUES
('0141189207445', 'El amor en tiempos del colera', 1, 'Penguin libros'),
('0192834177425', 'Macbeth', 5, 'Oxford University Press'),
('0521316928985', 'Cien años de soledad', 6, 'Cambridge University Press'),
('9780060722302', 'Nixon y Kissinger', 1, 'HarperCollins editorials Inc'),
('9780199535897', 'Romeo y Julieta', 3, 'Oxford University Press'),
('9780415141444', 'Diccionario Español-Inglés', 2, 'Santillana');

CREATE TABLE usuarios (
  codigo varchar(8) PRIMARY KEY,
  nombre varchar(25) NOT NULL,
  apellidos varchar(25) NOT NULL,
  fechanacimiento date 
);


INSERT INTO usuarios (codigo, nombre, apellidos, fechanacimiento) VALUES
('A786543', 'Jose', 'García', '1983-11-14'),
('B329087', 'Juan', 'López', '1984-04-10'),
('E433982', 'Ana', 'Ramírez', '1965-08-28'),
('E722654', 'Pedro', 'Sánchez', '1994-09-05'),
('K893417', 'Antonia', 'Font', '1984-09-18');

CREATE TABLE prestamos (
  id SERIAL PRIMARY KEY,
  fechaprestamo date NOT NULL,
  fechadevolucion date default NULL,
  libro varchar(13) NOT NULL,
  usuario varchar(13) NOT NULL
);

INSERT INTO prestamos (fechaprestamo, fechadevolucion, libro, usuario) VALUES
('2008-11-28', '2008-12-17', '0521316928985', 'A786543'),
('2008-11-04', '2008-11-30', '9780415141444', 'A786543'),
('2008-11-27', '2009-01-13', '0192834177425', 'A786543'),
('2008-11-24', '2008-11-30', '9780199535897', 'A786543'),
('2008-11-29', '2008-12-10', '9780415141444', 'E433982'),
('2008-11-11', '2008-11-30', '0521316928985', 'E433982'),
('2008-11-30', '2009-01-15', '0192834177425', 'B329087'),
('2008-11-28', '2009-01-06', '0192834177425', 'E722654'),
('2008-12-11', '2009-02-27', '0141189207445', 'E722654'),
('2008-11-28', '2009-01-13', '9780060722302', 'K893417'),
('2008-11-29', '2009-01-07', '9780199535897', 'K893417'),
('2008-11-07', '2008-11-29', '0141189207445', 'K893417');

ALTER TABLE prestamos
  ADD CONSTRAINT prestamos_ibfk_1 FOREIGN KEY (libro) REFERENCES libros(isbn) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT prestamos_ibfk_2 FOREIGN KEY (usuario) REFERENCES usuarios(codigo) ON DELETE CASCADE ON UPDATE CASCADE;
