INSERT INTO horarios_disponibles (fecha, hora_inicio, hora_fin, cupos_disponibles)
VALUES
  ('2025-02-01', '09:00:00', '10:00:00', 5),
  ('2025-02-01', '10:00:00', '11:00:00', 4),
  ('2025-02-01', '11:00:00', '12:00:00', 3),
  ('2025-02-01', '12:00:00', '13:00:00', 2),
  ('2025-02-01', '15:00:00', '16:00:00', 5),
  
  ('2025-02-02', '09:00:00', '10:00:00', 5),
  ('2025-02-02', '10:00:00', '11:00:00', 5),
  ('2025-02-02', '11:00:00', '12:00:00', 5),
  ('2025-02-02', '12:00:00', '13:00:00', 3),
  ('2025-02-02', '19:00:00', '20:00:00', 6);
  
INSERT INTO clientes (nombre, email, telefono)
VALUES
	('Juan Caicedo', 'juan.caicedo@mail.com', '3186251254'),
    ('Miriam Hernandez', 'miriam.hernandez@mail.com', '3186256587'),
    ('Isabel Pantoja', 'isabel.pantoja@mail.com', '3156875421');	