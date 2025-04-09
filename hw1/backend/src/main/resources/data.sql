-- Restaurantes
INSERT INTO restaurant (id, name) VALUES
    (1, 'Cantina dos Barcos'),
    (2, 'Cantina dos Moliceiros'),
    (3, 'Cantina Beira-Mar');

-- Restaurante 1 - Cantina dos Barcos
INSERT INTO meal (date, description, restaurant_id, type) VALUES
    ('2025-04-10', 'Sopa de feijão + Frango assado com arroz', 1, 'ALMOCO'),
    ('2025-04-10', 'Omelete de queijo e fiambre com salada', 1, 'JANTAR'),
    ('2025-04-11', 'Sopa de cenoura + Carne estufada com massa', 1, 'ALMOCO'),
    ('2025-04-11', 'Pescada frita com arroz de tomate', 1, 'JANTAR'),
    ('2025-04-12', 'Sopa de couve + Lasanha de carne', 1, 'ALMOCO'),
    ('2025-04-12', 'Bifanas no pão com batata cozida', 1, 'JANTAR'),
    ('2025-04-13', 'Sopa de ervilhas + Arroz de frango', 1, 'ALMOCO'),
    ('2025-04-13', 'Hambúrguer de carne com puré de batata', 1, 'JANTAR'),
    ('2025-04-14', 'Sopa de abóbora + Peixe grelhado com legumes', 1, 'ALMOCO'),
    ('2025-04-14', 'Rissóis de carne com salada', 1, 'JANTAR'),
    ('2025-04-15', 'Sopa de espinafres + Almôndegas em molho de tomate', 1, 'ALMOCO'),
    ('2025-04-15', 'Torta de atum com batata doce', 1, 'JANTAR'),
    ('2025-04-16', 'Sopa de legumes + Bife de vaca com batata frita', 1, 'ALMOCO'),
    ('2025-04-16', 'Bacalhau cozido com couve e grão', 1, 'JANTAR');

-- Restaurante 2 - Cantina dos Moliceiros
INSERT INTO meal (date, description, restaurant_id, type) VALUES
    ('2025-04-10', 'Sopa de legumes + Frango grelhado com massa', 2, 'ALMOCO'),
    ('2025-04-10', 'Tosta mista + salada mista', 2, 'JANTAR'),
    ('2025-04-11', 'Sopa de couve lombarda + Alheira com arroz', 2, 'ALMOCO'),
    ('2025-04-11', 'Panados de peru com puré de batata', 2, 'JANTAR'),
    ('2025-04-12', 'Sopa de tomate + Lulas grelhadas com salada', 2, 'ALMOCO'),
    ('2025-04-12', 'Prego no prato com batata cozida', 2, 'JANTAR'),
    ('2025-04-13', 'Sopa de lentilhas + Feijoada de carne', 2, 'ALMOCO'),
    ('2025-04-13', 'Omelete de espinafres com queijo', 2, 'JANTAR'),
    ('2025-04-14', 'Sopa de cenoura + Pataniscas de bacalhau', 2, 'ALMOCO'),
    ('2025-04-14', 'Frango estufado com batata à murro', 2, 'JANTAR'),
    ('2025-04-15', 'Sopa juliana + Páginas grelhadas com legumes', 2, 'ALMOCO'),
    ('2025-04-15', 'Jardineira de carne com arroz branco', 2, 'JANTAR'),
    ('2025-04-16', 'Sopa de feijão + Carne de porco à portuguesa', 2, 'ALMOCO'),
    ('2025-04-16', 'Bacalhau à Brás', 2, 'JANTAR');

-- Restaurante 3 - Cantina Beira-Mar
INSERT INTO meal (date, description, restaurant_id, type) VALUES
    ('2025-04-10', 'Sopa de beterraba + Lombinhos de porco com batata salteada', 3, 'ALMOCO'),
    ('2025-04-10', 'Quiche de legumes (vegetariana) com salada', 3, 'JANTAR'),
    ('2025-04-11', 'Sopa de couve-flor + Empadão de atum', 3, 'ALMOCO'),
    ('2025-04-11', 'Frango de churrasco com arroz de feijão', 3, 'JANTAR'),
    ('2025-04-12', 'Sopa de espinafres + Feijoada de javali', 3, 'ALMOCO'),
    ('2025-04-12', 'Pizza caseira (marguerita) com batata doce assada', 3, 'JANTAR'),
    ('2025-04-13', 'Sopa de legumes + Carne guisada com massa', 3, 'ALMOCO'),
    ('2025-04-13', 'Bolinho de bacalhau com arroz de cenoura', 3, 'JANTAR'),
    ('2025-04-14', 'Sopa de lentilhas + Arroz de pato', 3, 'ALMOCO'),
    ('2025-04-14', 'Tortilha de batata com salada', 3, 'JANTAR'),
    ('2025-04-15', 'Sopa de ervilhas + Bifes de peru com puré', 3, 'ALMOCO'),
    ('2025-04-15', 'Macarrão à pescador (com mix de marisco)', 3, 'JANTAR'),
    ('2025-04-09', 'Sopa de legumes + Cozido de grão com chouriço', 3, 'ALMOCO'),
    ('2025-04-09', 'Bacalhau espiritual (com gratinado)', 3, 'JANTAR');
