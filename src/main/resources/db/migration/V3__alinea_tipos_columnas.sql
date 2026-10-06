-- CalificacionEntity.puntaje es int -> la columna debe ser integer (no smallint)
ALTER TABLE calificacion ALTER COLUMN puntaje TYPE integer;

-- SolicitudEntity.tarifaAcordada es double -> double precision (no numeric)
ALTER TABLE solicitud ALTER COLUMN tarifa_acordada TYPE double precision;

-- ReputacionEntity.calificacionPromedio es double -> double precision (no numeric)
ALTER TABLE reputacion ALTER COLUMN calificacion_promedio TYPE double precision;
