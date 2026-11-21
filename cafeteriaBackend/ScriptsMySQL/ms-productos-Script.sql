create database producto;
use producto;
truncate table producto;
truncate table categoriaproducto;

SELECT * FROM producto;
SELECT * FROM categoriaproducto;

USE `producto`;

INSERT INTO `producto`.`categoriaproducto`
(`idCategoriaProducto`,
`nombreCategoriaProducto`)
VALUES
(1, 'Grano Entero'),
(2, 'Café molido'),
(3, 'Descafeinado');

INSERT INTO `producto`.`producto`
(`idProducto`,
`descripcionProducto`,
`nombreProducto`,
`precioProducto`,
`stockProducto`,
`idCategoriaProducto`)
VALUES
(1, 'Disfruta del Café Crema e Aroma de O\'CCAFFÈ, una exquisita mezcla que captura la esencia del verdadero café italiano. Elaborado con 100% granos de Arábica, combina tres variedades creando un café intenso con dulzor natural, acidez equilibrada y un sutil regusto a regaliz. Intensidad: Media (3/7).', 'Occaffe Grano Espresso Classico Professional 1kg', 30990, 25, 1),
(2, 'El Café Kimbo Filtro 226,8 Grs 100% Arabica Grano Molido es una joya de la tradición italiana que combina dulzor e intensidad en cada sorbo. Formato molido ideal para café largo al estilo italiano. Tostado: Medio. Intensidad: Media. Perfil Aromático: Chocolate negro y avellanas.', 'Kimbo Filtro 100% Arábico 226,8gr Grano Molido', 7990, 30, 2),
(3, 'Café en Granos Borbone 100% Arabica, opción perfecta tanto para máquinas expendedoras como para uso doméstico. Aroma lleno con notas de chocolate negro, malta y castaños. Intensidad 6 / 10.', 'Borbone 100% Arabica 1kg Grano Entero', 34990, 20, 1),
(4, 'Café Exquisit, mezcla extraordinaria de 90% Arábica y 10% Robusta que aporta un perfil de sabor dulce y envolvente con cuerpo medio y aroma embriagador. Presentación: Bolsa de 1 Kg en grano entero. Tostado: Medio. Intensidad: Media - Suave.', 'Lucaffe Exquisit 1 kg Grano Entero', 46990, 15, 1),
(5, 'Café Italiano Bontadi Cuba en Grano Entero 1kg, 100% Arabica. Libera un sabor de gran cuerpo con notas de grano de cacao y aromas de tabaco, crema dorada/bronce y sabor de larga duración.', 'Bontadi Cuba 100% Arabica 1kg Grano Entero', 43990, 18, 1),
(6, 'Café Decaffeinato O\'CCAFFÈ, opción ideal para disfrutar del sabor del café sin cafeína. Intensidad de 2/7, granos 100% Arábica con perfil de sabor equilibrado, rico aroma y sutiles matices.', 'Occaffe Grano Descafeinado Professional 1kg', 35990, 22, 3),
(7, 'Café Italiano Bontadi Papua en Grano Entero 1kg, 100% Arabica. Blend compuesto principalmente por granos de Papua Nueva Guinea cultivados a 2000 metros de altitud. Sabor pleno e intenso con regusto dulce y agradable, tostado a fuego lento.', 'Bontadi Papua 100% Arabica 1kg Grano Entero', 34990, 20, 1),
(8, 'Mezcla exquisita de café 100% Arábica proveniente de América del Sur, Asia y África Central. Bolsa de 1 Kg en grano entero, tostado medio e intensidad media-baja, suave y accesible.', 'Lucaffe Mr. Exclusive 1 kg Grano Entero', 49990, 12, 1),
(9, 'Exclusiva selección de café 100% Arábica de las Pequeñas islas de Jamaica. Aroma inmejorable con notas que recuerdan al chocolate y levemente a plátano. Presentación: Bolsa de 700grs. Tostado: Medio. Intensidad: Media - Baja. Acidez: Baja.', 'Lucaffe Blucaffe 100% Arabica Jamaica 700grs', 52990, 10, 1),
(10, 'Mezcla gourmet de café 100% Arábica obtenido mediante la selección de las mejores variedades de Centro-Sudamérica y Asia. En taza desarrolla un gusto redondo y persistente con ligera acidez agradable y aroma envolvente. Bolsa de 1kg grano entero.', 'Bontadi 100% Arabico 1kg Grano Entero', 31990, 25, 1),
(11, 'Lavazza café Crema e Aroma Grano Entero 1 kg. Selección de granos premium con mezcla de 40% Arábica y 60% Robusta. Aroma intenso con notas de chocolate y frutos secos, alta intensidad y gran cuerpo de larga duración.', 'Lavazza Crema e Aroma 1 kg Grano Entero', 34990, 28, 1),
(12, 'Lucaffe Classic 1 kg Grano Entero, espresso italiano que combina 80% de Arábica con 20% de Robusta. Cuerpo suave y generoso, crema de densidad media y aroma excepcional. Tostado: Medio. Intensidad: Media.', 'Lucaffe Classic 1 kg Grano Entero', 44990, 16, 1);

