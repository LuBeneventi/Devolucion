##MOTIVO
INSERT INTO `motivo_devolucion` (`id_motivo`, `descripcion`)
VALUES (1, 'Producto dañado al recibirlo');

INSERT INTO `motivo_devolucion` (`id_motivo`, `descripcion`)
VALUES (2, 'No corresponde con la descripción');

INSERT INTO `motivo_devolucion` (`id_motivo`, `descripcion`)
VALUES (3, 'Talla o medida incorrecta');

INSERT INTO `motivo_devolucion` (`id_motivo`, `descripcion`)
VALUES (4, 'Ya no lo necesito');

INSERT INTO `motivo_devolucion` (`id_motivo`, `descripcion`)
VALUES (5, 'Recibí un producto diferente al solicitado');

{
"descripcion": "PRODUCTO ROTO"
}

##DEVOLUCION
INSERT INTO `devolucion` (`fecha_emision`, `id_dev`, `id_motivo`, `id_venta`, `comentario`, `estado`)
VALUES ('2025-05-20', 301, 1, 501, 'El producto llegó roto y con la caja abierta.', 'SOLICITADA');

INSERT INTO `devolucion` (`fecha_emision`, `id_dev`, `id_motivo`, `id_venta`, `comentario`, `estado`)
VALUES ('2025-05-21', 302, 2, 502, 'El artículo no es como se describe en la tienda.', 'ACEPTADA');

INSERT INTO `devolucion` (`fecha_emision`, `id_dev`, `id_motivo`, `id_venta`, `comentario`, `estado`)
VALUES ('2025-05-22', 303, 3, 503, 'La talla del producto no coincide con la solicitada.', 'CANCELADA');

INSERT INTO `devolucion` (`fecha_emision`, `id_dev`, `id_motivo`, `id_venta`, `comentario`, `estado`)
VALUES ('2025-05-23', 304, 4, 504, 'Decidí que ya no lo necesito.', 'SOLICITADA');

INSERT INTO `devolucion` (`fecha_emision`, `id_dev`, `id_motivo`, `id_venta`, `comentario`, `estado`)
VALUES ('2025-05-24', 305, 5, 505, 'Me llegó un producto completamente distinto al que pedí.', 'ACEPTADA');


INSERT INTO `producto_devuelto` (`id`, `cantidad`, `id_producto`, `devolucion_id`) 
VALUES (1, 2, 101, 301);

INSERT INTO `producto_devuelto` (`id`, `cantidad`, `id_producto`, `devolucion_id`) 
VALUES (2, 1, 104, 302);

INSERT INTO `producto_devuelto` (`id`, `cantidad`, `id_producto`, `devolucion_id`) 
VALUES (3, 3, 110, 303);

INSERT INTO `producto_devuelto` (`id`, `cantidad`, `id_producto`, `devolucion_id`) 
VALUES (4, 5, 115, 304);

INSERT INTO `producto_devuelto` (`id`, `cantidad`, `id_producto`, `devolucion_id`) 
VALUES (5, 2, 120, 305);


{
"idVenta": 1,
"productos":[{
"idProducto": 45,
"cantidad": 2
},{
"idProducto": 78,
"cantidad": 1
}],
"razon":{
"idMotivo": 1
},
"comentario": "El producto llego hecho trizas!"
}
