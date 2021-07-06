-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Erstellungszeit: 06. Jul 2021 um 14:54
-- Server-Version: 5.7.25
-- PHP-Version: 5.6.40

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `usr_web286_1`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `web_prop`
--

CREATE TABLE `web_prop` (
  `key` varchar(4) COLLATE latin1_german1_ci NOT NULL,
  `value` varchar(30) COLLATE latin1_german1_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci;

--
-- Daten für Tabelle `web_prop`
--

INSERT INTO `web_prop` (`key`, `value`) VALUES
('IP', '');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `web_prop`
--
ALTER TABLE `web_prop`
  ADD PRIMARY KEY (`key`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
