-- CategoriaServicioEntity.tarifaBaseReferencial es double -> double precision
ALTER TABLE categoria_servicio ALTER COLUMN tarifa_base_referencial TYPE double precision;

-- Mas categorías para el catálogo
INSERT INTO categoria_servicio (nombre, oficio, tarifa_base_referencial) VALUES
 ('Cambio de cerradura', 'Cerrajeria', 55.00),
 ('Mantenimiento de gas', 'Gasfiteria', 70.00);

-- Perfiles de ejemplo para los técnicos activos sembrados en V2
INSERT INTO perfil_profesional (id_profesional, descripcion, destacado) VALUES
 (1, 'Gasfitero con 10 anios de experiencia en fugas y redes de agua.', TRUE),
 (2, 'Electricista certificada: instalaciones y emergencias electricas.', FALSE);

-- Categorías que atiende cada perfil (id_perfil 1 y 2 recién creados)
INSERT INTO perfil_categoria (id_perfil, id_categoria) VALUES
 (1, 1),  -- Gasfiteria: Fuga de agua
 (1, 4),  -- Gasfiteria: Mantenimiento de gas
 (2, 2);  -- Electricidad: Instalacion electrica
