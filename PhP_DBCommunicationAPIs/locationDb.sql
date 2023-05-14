-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : sam. 24 déc. 2022 à 01:06
-- Version du serveur :  10.4.17-MariaDB
-- Version de PHP : 8.0.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `locationDb`
--

-- --------------------------------------------------------

--
-- Structure de la table `Locations`
--

CREATE TABLE `Locations` (
  `id` int(11) NOT NULL,
  `nom` varchar(240) DEFAULT 'default',
  `prenom` varchar(240) DEFAULT 'default',
  `photo` int(11) DEFAULT 0,
  `latitude` double DEFAULT 0,
  `longitude` double DEFAULT 0,
  `altitude` double DEFAULT 0,
  `pays` varchar(240) DEFAULT 'default',
  `ville` varchar(240) DEFAULT 'default',
  `province` varchar(240) DEFAULT 'default',
  `postalCode` varchar(240) DEFAULT 'default',
  `statut` varchar(240) NOT NULL DEFAULT 'hide',
  `userName` varchar(240) NOT NULL DEFAULT 'default',
  `pass` varchar(240) NOT NULL DEFAULT 'default'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `Locations`
--

INSERT INTO `Locations` (`id`, `nom`, `prenom`, `photo`, `latitude`, `longitude`, `altitude`, `pays`, `ville`, `province`, `postalCode`, `statut`, `userName`, `pass`) VALUES
(7, 'elmehdi', 'arkhis', 869, 37.4219983333, -122.084, 5, 'United States', 'Mountain View', 'California', '94043', 'show', 'default', 'default'),
(17, 'sofia', 'hakimi', 479, 33.57311, -7.5898433333333, 0, 'Morocco', 'Casablanca', 'Casablanca-Settat', '20250', 'hide', 'sofia', 'sofia'),
(18, 'malak', 'minani', 679, 33.57311, -7.58984333333, 0, 'Morocco', 'Casablanca', 'Casablanca-Settat', '20250', 'show', 'malak', 'malak'),
(22, 'amin', 'amin', 947, 0, 0, 0, 'default', 'default', 'default', 'default', 'hide', 'amina', 'amina'),
(23, 'papoN', 'papoP', 217, 0, 0, 0, 'default', 'default', 'default', 'default', 'hide', 'papouU', 'papouP'),
(24, 'llo', 'llo', 216, 0, 0, 0, 'default', 'default', 'default', 'default', 'hide', 'llo', 'llo'),
(25, 'fernand', 'fernand', 849, 33.57311, -7.5898433333333, 0, 'Morocco', 'Casablanca', 'Casablanca-Settat', '20250', 'hide', 'fernand', 'fernand'),
(26, 'papou', 'papou', 631, 0, 0, 0, 'default', 'default', 'default', 'default', 'hide', 'papou', 'papou');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `Locations`
--
ALTER TABLE `Locations`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `Locations`
--
ALTER TABLE `Locations`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
