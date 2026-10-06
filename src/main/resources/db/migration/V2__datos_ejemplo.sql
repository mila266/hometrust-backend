-- Datos de ejemplo para pruebas iniciales
INSERT INTO cliente (nombre, ubicacion) VALUES
 ('Familia Quispe', 'San Miguel, Lima'),
 ('Sr. Herrera', 'Pueblo Libre, Lima');

INSERT INTO profesional (nombre, estado_verificacion, ubicacion_actual) VALUES
 ('Carlos Ramirez', 'ACTIVO', 'San Miguel'),
 ('Lucia Fernandez', 'ACTIVO', 'Magdalena'),
 ('Rosa Delgado', 'EN_REVISION', 'Brena');

INSERT INTO reputacion (id_profesional, calificacion_promedio, total_servicios) VALUES
 (1, 4.80, 52),
 (2, 4.60, 38),
 (3, 0.00, 0);

INSERT INTO categoria_servicio (nombre, oficio, tarifa_base_referencial) VALUES
 ('Fuga de agua', 'Gasfiteria', 60.00),
 ('Instalacion electrica', 'Electricidad', 75.00);
