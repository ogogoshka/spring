--
-- Структура таблицы `bonus`
--

CREATE TABLE `bonus` (
  `id` int(10) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `instock` int(11) DEFAULT NULL,
  `promoid` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `bonus`
--

INSERT INTO `bonus` (`id`, `name`, `instock`, `promoid`) VALUES
(1, 'кружка', 1, 1),
(2, 'надувной круг', 2, 1),
(3, 'надувной матрас', 3, 1),
(4, 'ручка', 2, 2),
(5, 'дверная ручка', 3, 2);

-- --------------------------------------------------------

--
-- Структура таблицы `promo`
--

CREATE TABLE `promo` (
  `id` int(10) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `promo`
--

INSERT INTO `promo` (`id`, `name`) VALUES
(1, 'PromoTest1'),
(2, 'PromoTest2'),
(3, 'Бешеный Июнь');

--
-- Индексы таблицы `bonus`
--
ALTER TABLE `bonus`
  ADD PRIMARY KEY (`id`),
  ADD KEY `promo_id` (`promoid`);

--
-- Индексы таблицы `promo`
--
ALTER TABLE `promo`
  ADD PRIMARY KEY (`id`);

--
-- Ограничения внешнего ключа таблицы `bonus`
--
ALTER TABLE `bonus`
  ADD CONSTRAINT `bonus_ibfk_1` FOREIGN KEY (`promoid`) REFERENCES `promo` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
