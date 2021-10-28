CREATE TABLE CATEGORIA (id BIGINT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(100));
INSERT INTO CATEGORIA(id,nombre) VALUES(1,'Alimentos');
INSERT INTO CATEGORIA(id,nombre) VALUES(2,'Aseo');
INSERT INTO CATEGORIA(id,nombre) VALUES(3,'Electrodomésticos');

CREATE TABLE PRODUCTO (id BIGINT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(100), descripcion VARCHAR(100), stock DOUBLE, precio DOUBLE, estado VARCHAR(20), FECHADECREACION DATE, idcategoria INT);

INSERT INTO PRODUCTO(id,nombre, descripcion, stock,precio,estado,FECHADECREACION,idcategoria)
VALUES(111,'Carne de res', 'Carne de res', 100, 14000, 'DISPONIBLE',  '2018-09-05', 1);

INSERT INTO PRODUCTO(id,nombre, descripcion, stock,precio,estado,FECHADECREACION,idcategoria)
VALUES(222,'Escoba', 'Escoba de barrer', 50, 7000, 'DISPONIBLE',  '2018-09-05', 2);

INSERT INTO PRODUCTO(id,nombre, descripcion, stock,precio,estado,FECHADECREACION,idcategoria)
VALUES(333,'Carne de cerdo', 'Carne de cerdo', 70, 9000, 'DISPONIBLE',  '2018-09-05', 1);

INSERT INTO PRODUCTO(id,nombre, descripcion, stock,precio,estado,FECHADECREACION,idcategoria)
VALUES(444,'Televisor 55', 'OLED de 55', 10, 3000000, 'DISPONIBLE',  '2018-09-05', 3);

INSERT INTO PRODUCTO(id,nombre, descripcion, stock,precio,estado,FECHADECREACION,idcategoria)
VALUES(555,'Carne de pollo', 'Carne de pollo', 40, 4000, 'DISPONIBLE',  '2018-09-05', 1);

