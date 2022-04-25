-- phpMyAdmin SQL Dump
-- version 3.4.10.1deb1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le : Mer 11 Décembre 2013 à 12:52
-- Version du serveur: 5.5.34
-- Version de PHP: 5.3.10-1ubuntu3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `careco`
--

-- --------------------------------------------------------

--
-- Structure de la table `Annuaire`
--

CREATE TABLE IF NOT EXISTS `Annuaire` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `centre` varchar(255) NOT NULL,
  `adherent` varchar(255) NOT NULL,
  `adresse` varchar(255) NOT NULL,
  `cp` varchar(255) NOT NULL,
  `ville` varchar(255) NOT NULL,
  `telephone` varchar(255) NOT NULL,
  `telecopie` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=78 ;

--
-- Contenu de la table `Annuaire`
--

--INSERT INTO `Annuaire` (`id`, `centre`, `adherent`, `adresse`, `cp`, `ville`, `telephone`, `telecopie`) VALUES


-- --------------------------------------------------------

--
-- Structure de la table `auth_url`
--

CREATE TABLE IF NOT EXISTS `auth_url` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `url` varchar(512) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `auth_url`
--

INSERT INTO `auth_url` (`id`, `role_id`, `url`) VALUES
(1, 2, '/b/');

-- --------------------------------------------------------

--
-- Structure de la table `auth_view`
--

CREATE TABLE IF NOT EXISTS `auth_view` (
  `id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `url` varchar(512) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `Client`
--

CREATE TABLE IF NOT EXISTS `Client` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `societe` varchar(255) DEFAULT NULL,
  `telephoneFixe` varchar(255) DEFAULT NULL,
  `commentaireTelFixe` varchar(255) DEFAULT NULL,
  `telephonePortable` varchar(255) DEFAULT NULL,
  `commentairePortable` varchar(255) DEFAULT NULL,
  `fax` varchar(255) DEFAULT NULL,
  `numCarteClient` varchar(255) DEFAULT NULL,
  `numCompteClient` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `adresseFacturation` varchar(255) DEFAULT NULL,
  `adresseLivraison` varchar(255) DEFAULT NULL,
  `tva` decimal(10,0) NOT NULL,
  `immatriculation` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=46 ;

--
-- Contenu de la table `Client`
--

INSERT INTO `Client` (`id`, `type`, `nom`, `prenom`, `societe`, `telephoneFixe`, `commentaireTelFixe`, `telephonePortable`, `commentairePortable`, `fax`, `numCarteClient`, `numCompteClient`, `email`, `adresseFacturation`, `adresseLivraison`, `tva`, `immatriculation`) VALUES
(38, 'btoc', 'Comptoir', 'Client', '', '05', '', '', '', '', '', '', '', '', '', 0, ''),
(39, 'btoc', 'Comptoir', 'Client', '', '', '', '', '', '', '', '', '', '', '', 0, '82LB0170'),
(40, 'btoc', 'Comptoir', 'Client', '', '0555555555', '', '0666666666', '', '', '', '', 'aaa@aaa.fr', 'adresse de facturation', '', 0, '82KM9654'),
(41, 'btoc', 'Comptoir', 'Client', '', '', '', '', '', '', '', '', '', '', '', 20, '56TS1968'),
(42, 'btoc', 'Comptoir', 'Client', '', '', '', '', '', '', '', '', '', '', '', 20, '32LM5770'),
(43, 'btoc', 'Comptoir', 'Client', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 20, '88TZ5286'),
(44, 'btoc', 'Comptoir', 'Client', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 20, '31CKP0337'),
(45, 'btoc', 'Comptoir', 'Client', '', '', '', '', '', '', '', '', '', '', '', 0, '44YS6070');

-- --------------------------------------------------------

--
-- Structure de la table `GroupeEntreprise`
--

CREATE TABLE IF NOT EXISTS `GroupeEntreprise` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `GroupeEntreprise`
--

INSERT INTO `GroupeEntreprise` (`id`, `nom`) VALUES
(1, 'CARECO');

-- --------------------------------------------------------

--
-- Structure de la table `LignePanier`
--

CREATE TABLE IF NOT EXISTS `LignePanier` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idPiece` int(11) NOT NULL,
  `idPanier` int(11) NOT NULL,
  `prixVenteTTCFinal` decimal(10,0) DEFAULT NULL,
  `prixVenteHTFinal` decimal(10,0) DEFAULT NULL,
  `transactionAchatVente` int(11) DEFAULT NULL,
  `dateDateDernierChangementEtat` date NOT NULL,
  `statut` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idPiece` (`idPiece`),
  KEY `idPanier` (`idPanier`),
  KEY `transactionAchatVente` (`transactionAchatVente`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

-- --------------------------------------------------------

--
-- Structure de la table `Message`
--

CREATE TABLE IF NOT EXISTS `Message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `de` int(11) NOT NULL,
  `a` int(11) NOT NULL,
  `sujet` varchar(1024) DEFAULT NULL,
  `message` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `de` (`de`),
  KEY `a` (`a`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `Panier`
--

CREATE TABLE IF NOT EXISTS `Panier` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idClient` int(11) DEFAULT NULL,
  `idEntreprise` int(11) NOT NULL,
  `validite` varchar(255) DEFAULT NULL,
  `datePanier` date NOT NULL,
  `dateFin` date NOT NULL,
  `vendeur` int(11) NOT NULL,
  `nbPiece` int(11) NOT NULL,
  `devis` tinyint(1) NOT NULL,
  `prixHT` decimal(10,0) NOT NULL,
  `tva` decimal(10,0) NOT NULL,
  `totalTTC` decimal(10,0) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idClient` (`idClient`),
  KEY `idEntreprise` (`idEntreprise`),
  KEY `vendeur` (`vendeur`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=34 ;

-- --------------------------------------------------------

--
-- Structure de la table `Roles`
--

CREATE TABLE IF NOT EXISTS `Roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(25) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `Roles`
--

INSERT INTO `Roles` (`id`, `role`, `description`) VALUES
(1, 'admin', NULL),
(2, 'utilisateur', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `Stock`
--

CREATE TABLE IF NOT EXISTS `Stock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idStock` int(11) NOT NULL,
  `idStockOrigine` int(11) NOT NULL,
  `disponibilite` varchar(255) DEFAULT NULL,
  `typeDePiece` varchar(255) DEFAULT NULL,
  `refConstructeur` varchar(255) DEFAULT NULL,
  `CNITTypeMine` varchar(255) DEFAULT NULL,
  `vin` varchar(255) DEFAULT NULL,
  `marque` varchar(255) DEFAULT NULL,
  `modele` varchar(255) DEFAULT NULL,
  `version` varchar(255) DEFAULT NULL,
  `kms` int(11) DEFAULT NULL,
  `empl` varchar(255) DEFAULT NULL,
  `vendeur` varchar(255) DEFAULT NULL,
  `garantie` int(11) DEFAULT NULL,
  `prixCareco` decimal(10,0) DEFAULT NULL,
  `prixVente` decimal(10,0) DEFAULT NULL,
  `numLivrePolice` varchar(255) DEFAULT NULL,
  `immatriculation` varchar(255) DEFAULT NULL,
  `export` tinyint(1) DEFAULT NULL,
  `demonte` tinyint(1) DEFAULT NULL,
  `date1erMiseEnCirculation` date DEFAULT NULL,
  `prixAchat` decimal(10,0) DEFAULT NULL,
  `emplacementCasier` varchar(255) DEFAULT NULL,
  `numeroDeSeriePiece` varchar(255) DEFAULT NULL,
  `prixMinimum` decimal(10,0) DEFAULT NULL,
  `prixVenteConseille` decimal(10,0) DEFAULT NULL,
  `commentaireInterne` varchar(2048) DEFAULT NULL,
  `commentaireCommercial` varchar(2048) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idStock` (`idStock`),
  KEY `idStockOrigine` (`idStockOrigine`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

-- --------------------------------------------------------

--
-- Structure de la table `TransactionAchatVente`
--

CREATE TABLE IF NOT EXISTS `TransactionAchatVente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `etatVendeur` varchar(255) NOT NULL,
  `etatAcheteur` varchar(255) NOT NULL,
  `derierChangementEtat` date DEFAULT NULL,
  `termine` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

-- --------------------------------------------------------

--
-- Structure de la table `UserCompany`
--

CREATE TABLE IF NOT EXISTS `UserCompany` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idGroupeEntreprise` int(11) DEFAULT NULL,
  `nom` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idGroupeEntreprise` (`idGroupeEntreprise`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Contenu de la table `UserCompany`
--

INSERT INTO `UserCompany` (`id`, `idGroupeEntreprise`, `nom`) VALUES
(1, 1, 'Entreprise AAA'),
(2, 1, 'Entreprise BBB');

-- --------------------------------------------------------

--
-- Structure de la table `UserRoles`
--

CREATE TABLE IF NOT EXISTS `UserRoles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `userRoles` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `userRoles` (`userRoles`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Contenu de la table `UserRoles`
--

INSERT INTO `UserRoles` (`id`, `user_id`, `userRoles`) VALUES
(1, 1, 2),
(2, 2, 1),
(3, 3, 1);

-- --------------------------------------------------------

--
-- Structure de la table `Users`
--

CREATE TABLE IF NOT EXISTS `Users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) NOT NULL,
  `passwd` varchar(64) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `userCompany` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userCompany` (`userCompany`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=18 ;

--
-- Contenu de la table `Users`
--

INSERT INTO `Users` (`id`, `username`, `passwd`, `nom`, `prenom`, `email`, `userCompany`) VALUES
(1, 'legrain', 'CPb1ngVAlEYiyUF2zQ0SnyKLNRvBQWnBB1MLc+FcC70=', NULL, NULL, NULL, 2),
(2, 'nicolas', 'VLICnfhhmeKqsofW4g8gD18GuPR1U6PBjf/1BgoeYW4=', NULL, NULL, NULL, 1);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `LignePanier`
--
ALTER TABLE `LignePanier`
  ADD CONSTRAINT `LignePanier_ibfk_1` FOREIGN KEY (`idPiece`) REFERENCES `Stock` (`id`),
  ADD CONSTRAINT `LignePanier_ibfk_6` FOREIGN KEY (`idPanier`) REFERENCES `Panier` (`id`),
  ADD CONSTRAINT `LignePanier_ibfk_7` FOREIGN KEY (`transactionAchatVente`) REFERENCES `TransactionAchatVente` (`id`);

--
-- Contraintes pour la table `Message`
--
ALTER TABLE `Message`
  ADD CONSTRAINT `Message_ibfk_2` FOREIGN KEY (`a`) REFERENCES `Users` (`id`),
  ADD CONSTRAINT `Message_ibfk_1` FOREIGN KEY (`de`) REFERENCES `Users` (`id`);

--
-- Contraintes pour la table `Panier`
--
ALTER TABLE `Panier`
  ADD CONSTRAINT `Panier_ibfk_4` FOREIGN KEY (`idEntreprise`) REFERENCES `UserCompany` (`id`),
  ADD CONSTRAINT `Panier_ibfk_1` FOREIGN KEY (`idClient`) REFERENCES `Client` (`id`),
  ADD CONSTRAINT `Panier_ibfk_3` FOREIGN KEY (`vendeur`) REFERENCES `Users` (`id`);

--
-- Contraintes pour la table `Stock`
--
ALTER TABLE `Stock`
  ADD CONSTRAINT `Stock_ibfk_4` FOREIGN KEY (`idStockOrigine`) REFERENCES `UserCompany` (`id`),
  ADD CONSTRAINT `Stock_ibfk_3` FOREIGN KEY (`idStock`) REFERENCES `UserCompany` (`id`);

--
-- Contraintes pour la table `UserCompany`
--
ALTER TABLE `UserCompany`
  ADD CONSTRAINT `UserCompany_ibfk_1` FOREIGN KEY (`idGroupeEntreprise`) REFERENCES `GroupeEntreprise` (`id`);

--
-- Contraintes pour la table `UserRoles`
--
ALTER TABLE `UserRoles`
  ADD CONSTRAINT `UserRoles_ibfk_2` FOREIGN KEY (`userRoles`) REFERENCES `Roles` (`id`),
  ADD CONSTRAINT `UserRoles_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `Users` (`id`);

--
-- Contraintes pour la table `Users`
--
ALTER TABLE `Users`
  ADD CONSTRAINT `Users_ibfk_5` FOREIGN KEY (`userCompany`) REFERENCES `UserCompany` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
