DROP TABLE IF EXISTS roles CASCADE;
CREATE TABLE roles(
	id BIGSERIAL PRIMARY KEY,
	name VARCHAR(180) NOT NULL UNIQUE,
	image varchar(255) NULL,
	route varchar(255) NULL,
	created_at TIMESTAMP(0) NOT NULL,
	updated_at TIMESTAMP(0) NOT NULL	
);

DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users(
 	id BIGSERIAL PRIMARY KEY,
	email VARCHAR(255)NOT NULL UNIQUE,
	name VARCHAR(255) NOT NULL,
	lastname VARCHAR(255) NOT NULL,
	phone VARCHAR(80) NOT NULL UNIQUE,
	image VARCHAR(255) NULL,
	password VARCHAR(255) NOT NULL,
	is_available BOOLEAN NULL,
	session_token VARCHAR(255) NULL,
	created_at TIMESTAMP(0) NOT NULL,
	updated_at TIMESTAMP(0) NOT NULL	
);
DROP TABLE IF EXISTS user_has_roles CASCADE;
CREATE TABLE user_has_roles(
	id_user BIGSERIAL NOT NULL,
	id_rol BIGSERIAL NOT NULL,
	created_at TIMESTAMP(0) NOT NULL,
	updated_at TIMESTAMP(0) NOT NULL,
	FOREIGN KEY(id_user) REFERENCES users(id) ON UPDATE CASCADE ON DELETE CASCADE,	
	FOREIGN KEY(id_rol) REFERENCES roles(id) ON UPDATE CASCADE ON DELETE CASCADE,
	PRIMARY KEY(id_user, id_rol)

);
/*inserta los datos a la tabla roles*/  
INSERT INTO roles(
	name,
	route,
	image,
	created_at,
	updated_at
)
VALUES(
 	'CLIENTE',
	'client/home',
	'https://upload.wikimedia.org/wikipedia/commons/thumb/0/04/User_icon_1.svg/800px-User_icon_1.svg.png',
	'2022-10-19',
	'2022-10-19'
);

INSERT INTO roles(
	name,
	route,
	image,
	created_at,
	updated_at
)
VALUES(
 	'RESTAURANTE',
	'restaurant/home',
	'https://png.pngtree.com/element_origin_min_pic/17/06/22/a394802ed2d0bdb3bc72de3b75d358e4.jpg',
	'2022-10-19',
	'2022-10-19'
);
INSERT INTO roles(
	name,
	route,
	image,
	created_at,
	updated_at
)
VALUES(
 	'REPARTIDOR',
	'delivery/home',
	'https://png.pngtree.com/png-vector/20210618/ourlarge/pngtree-cute-food-delivery-workers-dressed-in-uniforms-png-image_3495096.jpg',
	'2022-10-19',
	'2022-10-19'
);