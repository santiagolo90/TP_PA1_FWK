-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 19-06-2019 a las 01:08:39
-- Versión del servidor: 10.1.19-MariaDB
-- Versión de PHP: 5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `javatest`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `auto`
--

CREATE TABLE `auto` (
  `id` int(50) NOT NULL,
  `marca` varchar(100) NOT NULL,
  `color` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `auto`
--

INSERT INTO `auto` (`id`, `marca`, `color`) VALUES
(1, 'chery', 'amarillo'),
(2, 'chery', 'amarillo'),
(3, 'chery', 'amarillo'),
(4, 'chery', 'amarillo'),
(5, 'chery', 'amarillo'),
(6, 'chery', 'amarillo'),
(7, 'chery', 'amarillo'),
(8, 'chery', 'amarillo'),
(9, 'chery', 'amarillo'),
(10, 'chery', 'amarillo'),
(11, 'chery', 'amarillo'),
(12, 'chery', 'amarillo'),
(13, 'chery', 'amarillo'),
(14, 'chery', 'amarillo'),
(15, 'chery', 'amarillo'),
(16, 'chery', 'amarillo'),
(17, 'ford', 'rojo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE `persona` (
  `id` int(32) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `edad` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `persona`
--

INSERT INTO `persona` (`id`, `nombre`, `apellido`, `edad`) VALUES
(1, 'Pepe', 'Argento', 50),
(2, 'Pepe', 'Argento', 50),
(3, 'Pepe', 'Argento', 50),
(4, 'Pepe', 'Argento', 50),
(5, 'Pepe', 'Argento', 50),
(6, 'Pepe', 'Argento', 50),
(7, 'Mario', 'Argento', 50);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `auto`
--
ALTER TABLE `auto`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `auto`
--
ALTER TABLE `auto`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
--
-- AUTO_INCREMENT de la tabla `persona`
--
ALTER TABLE `persona`
  MODIFY `id` int(32) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
