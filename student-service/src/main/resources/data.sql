-- Carreras
INSERT INTO career (name) VALUES 
('Diseño'),
('Informática'),
('Actuación'),
('Administración'),
('Psicología');

-- Estudiantes
INSERT INTO student (name, surname, career_id) VALUES
-- Diseño
('Alicia', 'Pérez', 1),
('Rául', 'Muñoz', 1),
-- Informática
('Carlos', 'López', 2),
('María', 'González', 2),
-- Actuación
('Pedro', 'Sánchez', 3),
('Lucía', 'Torres', 3),
-- Administración
('Sofía', 'Ramírez', 4),
('Javier', 'Hernández', 4),
-- Psicología
('Camila', 'Díaz', 5),
('Andrés', 'Ortiz', 5);