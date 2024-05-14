-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Erstellungszeit: 10. Mai 2024 um 08:17
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
-- Tabellenstruktur für Tabelle `jd_logger`
--

CREATE TABLE `jd_logger` (
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `power` int(11) NOT NULL,
  `today_yield` char(15) COLLATE latin1_german1_ci NOT NULL,
  `total_yield` char(15) COLLATE latin1_german1_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci;

--
-- Daten für Tabelle `jd_logger`
--

INSERT INTO `jd_logger` (`timestamp`, `power`, `today_yield`, `total_yield`) VALUES
('2024-05-09 17:15:48', 900, '', ''),
('2024-05-10 06:08:17', 900, '', '');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `jd_logger`
--
ALTER TABLE `jd_logger`
  ADD PRIMARY KEY (`timestamp`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
