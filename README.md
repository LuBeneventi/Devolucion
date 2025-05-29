##DEVOLUCION
INSERT INTO `devolucion`(`id_dev`, `comentario`, `estado`, `fecha_emision`, `id_venta`, `motivo`) 
VALUES (1, 'Producto llegó roto', 'SOLICITADA', '2025-05-28', 101, 'MAL_ESTADO');

INSERT INTO `devolucion`(`id_dev`, `comentario`, `estado`, `fecha_emision`, `id_venta`, `motivo`) 
VALUES (2, 'Recibí otro producto distinto', 'ACEPTADA', '2025-05-27', 102, 'PRODUCTO_EQUIVOCADO');

INSERT INTO `devolucion`(`id_dev`, `comentario`, `estado`, `fecha_emision`, `id_venta`, `motivo`) 
VALUES (3, 'Faltan accesorios en el paquete', 'CANCELADA', '2025-05-26', 103, 'PRODUCTO_INCOMPLETO');

INSERT INTO `devolucion`(`id_dev`, `comentario`, `estado`, `fecha_emision`, `id_venta`, `motivo`) 
VALUES (4, 'No estoy satisfecho con el producto', 'TERMINADA', '2025-05-25', 104, 'INSATISFACTORIO');


-- Producto devuelto asociado a devolución con id 1
INSERT INTO `producto_devuelto`(`id`, `cantidad`, `id_producto`, `dev_id`) VALUES (1, 2, 1001, 1);
INSERT INTO `producto_devuelto`(`id`, `cantidad`, `id_producto`, `dev_id`) VALUES (2, 1, 1005, 2);
INSERT INTO `producto_devuelto`(`id`, `cantidad`, `id_producto`, `dev_id`) VALUES (3, 3, 1003, 3);
INSERT INTO `producto_devuelto`(`id`, `cantidad`, `id_producto`, `dev_id`) VALUES (4, 1, 1007, 4);
