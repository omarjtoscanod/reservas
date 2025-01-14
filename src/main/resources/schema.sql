-- Tabla CLIENTES
DROP TABLE IF EXISTS clientes;
CREATE TABLE clientes (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  telefono VARCHAR(50)
);

-- ------------------------------------------------------------------
-- Tabla HORARIOS_DISPONIBLES
DROP TABLE IF EXISTS horarios_disponibles;
CREATE TABLE horarios_disponibles (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  fecha DATE NOT NULL,
  hora_inicio TIME NOT NULL,
  hora_fin TIME NOT NULL,
  cupos_disponibles INT NOT NULL DEFAULT 1
);

-- ------------------------------------------------------------------
-- Tabla RESERVAS
DROP TABLE IF EXISTS reservas;
CREATE TABLE reservas (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  client_id BIGINT NOT NULL,
  horario_disponible_id BIGINT NOT NULL,
  fecha_creacion TIMESTAMP NOT NULL,
  estado VARCHAR(50) NOT NULL,

  CONSTRAINT fk_reserva_cliente 
    FOREIGN KEY (client_id) 
    REFERENCES clientes(id) 
    ON DELETE CASCADE,

  CONSTRAINT fk_reserva_horario 
    FOREIGN KEY (horario_disponible_id)
    REFERENCES horarios_disponibles(id)
    ON DELETE CASCADE
);