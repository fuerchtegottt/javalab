-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Erstellungszeit: 13. Mai 2026 um 16:43
-- Server-Version: 10.5.19-MariaDB-0+deb11u2
-- PHP-Version: 8.1.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `g3ll3rt_prod`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `webfeed`
--

CREATE TABLE `webfeed` (
  `feed_key` timestamp NOT NULL DEFAULT current_timestamp(),
  `feed_type` char(10) NOT NULL,
  `feed_text` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci COMMENT='web feed store';

--
-- Daten für Tabelle `webfeed`
--

INSERT INTO `webfeed` (`feed_key`, `feed_type`, `feed_text`) VALUES
('2026-05-13 14:41:52', 'NOTE', 'dies ist ein Test');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `webfeed`
--
ALTER TABLE `webfeed`
  ADD PRIMARY KEY (`feed_key`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
