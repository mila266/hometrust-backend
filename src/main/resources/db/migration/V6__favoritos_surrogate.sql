ALTER TABLE cliente_favorito DROP CONSTRAINT cliente_favorito_pkey;
ALTER TABLE cliente_favorito ADD COLUMN id_favorito BIGSERIAL PRIMARY KEY;
ALTER TABLE cliente_favorito ADD CONSTRAINT uk_cliente_favorito UNIQUE (id_cliente, id_profesional);
