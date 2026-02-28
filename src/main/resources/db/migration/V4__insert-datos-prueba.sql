-- La contraseña es '123456' encriptada con BCrypt
INSERT INTO usuarios (nombre, correo_electronico, contrasena)
VALUES ('Usuario Test', 'test@foro.com', '$2a$10$Y566DpY6G8S.m9clpL7Sze63v/mKsvN.71S8n4vK3e2V57/Fp7v66');

INSERT INTO cursos (nombre, categoria)
VALUES ('Spring Boot 3', 'Programación');