CREATE TABLE cliente (
    id_cliente      BIGSERIAL PRIMARY KEY,
    nombre          VARCHAR(150) NOT NULL,
    ubicacion       VARCHAR(255) NOT NULL
);

CREATE TABLE profesional (
    id_profesional      BIGSERIAL PRIMARY KEY,
    nombre              VARCHAR(150) NOT NULL,
    estado_verificacion VARCHAR(30)  NOT NULL DEFAULT 'REGISTRADO',
    ubicacion_actual    VARCHAR(255)
);

CREATE TABLE perfil_profesional (
    id_perfil       BIGSERIAL PRIMARY KEY,
    id_profesional  BIGINT NOT NULL REFERENCES profesional(id_profesional),
    descripcion     VARCHAR(500),
    destacado       BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE categoria_servicio (
    id_categoria            BIGSERIAL PRIMARY KEY,
    nombre                  VARCHAR(120) NOT NULL,
    oficio                  VARCHAR(120) NOT NULL,
    tarifa_base_referencial NUMERIC(10,2) NOT NULL
);

-- relacion muchos-a-muchos: un perfil atiende varias categorias
CREATE TABLE perfil_categoria (
    id_perfil     BIGINT NOT NULL REFERENCES perfil_profesional(id_perfil),
    id_categoria  BIGINT NOT NULL REFERENCES categoria_servicio(id_categoria),
    PRIMARY KEY (id_perfil, id_categoria)
);

CREATE TABLE documento (
    id_documento    BIGSERIAL PRIMARY KEY,
    id_profesional  BIGINT NOT NULL REFERENCES profesional(id_profesional),
    tipo            VARCHAR(80) NOT NULL,
    url             VARCHAR(255) NOT NULL,
    estado          VARCHAR(30) NOT NULL DEFAULT 'PENDIENTE'
);

CREATE TABLE solicitud (
    id_solicitud    BIGSERIAL PRIMARY KEY,
    id_cliente      BIGINT NOT NULL REFERENCES cliente(id_cliente),
    id_profesional  BIGINT REFERENCES profesional(id_profesional),
    descripcion     VARCHAR(500) NOT NULL,
    ubicacion       VARCHAR(255) NOT NULL,
    tipo            VARCHAR(15) NOT NULL DEFAULT 'NORMAL',
    estado          VARCHAR(20) NOT NULL DEFAULT 'CREADA',
    tarifa_acordada NUMERIC(10,2) NOT NULL DEFAULT 0,
    fecha_creacion  TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE oferta (
    id_oferta               BIGSERIAL PRIMARY KEY,
    id_solicitud            BIGINT NOT NULL REFERENCES solicitud(id_solicitud),
    id_profesional          BIGINT NOT NULL REFERENCES profesional(id_profesional),
    tarifa_propuesta        NUMERIC(10,2) NOT NULL,
    tiempo_estimado_llegada INTEGER NOT NULL,
    ubicacion_actual        VARCHAR(255),
    estado                  VARCHAR(20) NOT NULL DEFAULT 'ENVIADA'
);

CREATE TABLE calificacion (
    id_calificacion BIGSERIAL PRIMARY KEY,
    id_solicitud    BIGINT NOT NULL UNIQUE REFERENCES solicitud(id_solicitud),
    puntaje         SMALLINT NOT NULL CHECK (puntaje BETWEEN 1 AND 5),
    comentario      VARCHAR(500),
    fecha           TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE reputacion (
    id_reputacion           BIGSERIAL PRIMARY KEY,
    id_profesional          BIGINT NOT NULL UNIQUE REFERENCES profesional(id_profesional),
    calificacion_promedio   NUMERIC(3,2) NOT NULL DEFAULT 0,
    total_servicios         INTEGER NOT NULL DEFAULT 0
);

-- favoritos: relacion muchos-a-muchos entre cliente y profesional
CREATE TABLE cliente_favorito (
    id_cliente      BIGINT NOT NULL REFERENCES cliente(id_cliente),
    id_profesional  BIGINT NOT NULL REFERENCES profesional(id_profesional),
    PRIMARY KEY (id_cliente, id_profesional)
);
