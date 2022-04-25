--AAA
ALTER TABLE `Immatriculation` ADD `siren` VARCHAR( 50 ) NULL ,
ADD `pneu` VARCHAR( 512 ) NULL;
ALTER TABLE `Immatriculation` CHANGE `Depollution` `Depollution` VARCHAR( 120 ) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL ;

--careco
ALTER TABLE `Stock` ADD `pieceLourde` BOOLEAN NOT NULL;

ALTER TABLE `Stock` CHANGE `disponibilite` `disponibilite` BOOLEAN NULL DEFAULT NULL;

ALTER TABLE `Panier` CHANGE `prixHT` `prixHT` DECIMAL( 10, 2 ) NOT NULL ,
CHANGE `tva` `tva` DECIMAL( 10, 2 ) NOT NULL ,
CHANGE `totalTTC` `totalTTC` DECIMAL( 10, 2 ) NOT NULL ,
CHANGE `montantTVA` `montantTVA` DECIMAL( 10, 2 ) NULL DEFAULT NULL ,
CHANGE `montantLivraison` `montantLivraison` DECIMAL( 10, 2 ) NULL DEFAULT NULL ;

ALTER TABLE `LignePanier` CHANGE `prixVenteTTCFinal` `prixVenteTTCFinal` DECIMAL( 10, 2 ) NULL DEFAULT NULL ,
CHANGE `prixVenteHTFinal` `prixVenteHTFinal` DECIMAL( 10, 2 ) NULL DEFAULT NULL ,
CHANGE `tauxTVA` `tauxTVA` DECIMAL( 10, 2 ) NULL DEFAULT NULL ,
CHANGE `montantTVA` `montantTVA` DECIMAL( 10, 2 ) NULL DEFAULT NULL ,
CHANGE `montantPE` `montantPE` DECIMAL( 10, 2 ) NULL DEFAULT NULL ,
CHANGE `supplementLivraison` `supplementLivraison` DECIMAL( 10, 2 ) NULL DEFAULT NULL ;

ALTER TABLE `Client` CHANGE `tva` `tva` DECIMAL( 10, 2 ) NOT NULL;

ALTER TABLE `GarantieCareco` CHANGE `prixVentePieceDebut` `prixVentePieceDebut` DECIMAL( 10, 2 ) NOT NULL ,
CHANGE `prixVentePiecefin` `prixVentePiecefin` DECIMAL( 10, 2 ) NOT NULL ,
CHANGE `prixPE12HT` `prixPE12HT` DECIMAL( 11, 2 ) NOT NULL ,
CHANGE `prixPE12TTC` `prixPE12TTC` DECIMAL( 11, 2 ) NOT NULL ,
CHANGE `prixPE24HT` `prixPE24HT` DECIMAL( 11, 2 ) NOT NULL ,
CHANGE `prixPE24TTC` `prixPE24TTC` DECIMAL( 11, 2 ) NOT NULL;

ALTER TABLE `Stock` CHANGE `prixCareco` `prixCareco` DECIMAL( 10, 2 ) NULL DEFAULT NULL ,
CHANGE `prixVente` `prixVente` DECIMAL( 10, 2 ) NULL DEFAULT NULL ,
CHANGE `prixAchat` `prixAchat` DECIMAL( 10, 2 ) NULL DEFAULT NULL ,
CHANGE `prixMinimum` `prixMinimum` DECIMAL( 10, 2 ) NULL DEFAULT NULL ,
CHANGE `prixVenteConseille` `prixVenteConseille` DECIMAL( 10, 2 ) NULL DEFAULT NULL ;

ALTER TABLE `Panier` CHANGE `dateFin` `dateFin` DATETIME NOT NULL ;
ALTER TABLE `Panier` CHANGE `datePanier` `datePanier` DATETIME NOT NULL ;
ALTER TABLE `TransactionAchatVente` CHANGE `derierChangementEtat` `derierChangementEtat` DATETIME NULL DEFAULT NULL ;
ALTER TABLE `LignePanier` CHANGE `dateDateDernierChangementEtat` `dateDateDernierChangementEtat` DATETIME NOT NULL ;

ALTER TABLE `TransactionAchatVente` ADD `idEtatVendeur` INT NULL ,
ADD `idEtatAcheteur` INT NULL ;

ALTER TABLE `Users` ADD `quanCree` DATETIME NULL ,
ADD `quandModif` DATETIME NULL ,
ADD `quiCree` INT NULL ,
ADD `quiModif` INT NULL ,
ADD `tableOrigine` VARCHAR( 512 ) NULL ,
ADD `idOrigine` INT NULL ,
ADD `ip` VARCHAR( 512 ) NULL ;

ALTER TABLE `Adherent` ADD `quanCree` DATETIME NULL ,
ADD `quandModif` DATETIME NULL ,
ADD `quiCree` INT NULL ,
ADD `quiModif` INT NULL ,
ADD `tableOrigine` VARCHAR( 512 ) NULL ,
ADD `idOrigine` INT NULL ,
ADD `ip` VARCHAR( 512 ) NULL ;

ALTER TABLE `Annuaire` ADD `quanCree` DATETIME NULL ,
ADD `quandModif` DATETIME NULL ,
ADD `quiCree` INT NULL ,
ADD `quiModif` INT NULL ,
ADD `tableOrigine` VARCHAR( 512 ) NULL ,
ADD `idOrigine` INT NULL ,
ADD `ip` VARCHAR( 512 ) NULL ;

ALTER TABLE `FicheCareco` ADD `quanCree` DATETIME NULL ,
ADD `quandModif` DATETIME NULL ,
ADD `quiCree` INT NULL ,
ADD `quiModif` INT NULL ,
ADD `tableOrigine` VARCHAR( 512 ) NULL ,
ADD `idOrigine` INT NULL ,
ADD `ip` VARCHAR( 512 ) NULL ;

ALTER TABLE `GarantieCareco` ADD `quanCree` DATETIME NULL ,
ADD `quandModif` DATETIME NULL ,
ADD `quiCree` INT NULL ,
ADD `quiModif` INT NULL ,
ADD `tableOrigine` VARCHAR( 512 ) NULL ,
ADD `idOrigine` INT NULL ,
ADD `ip` VARCHAR( 512 ) NULL ;

ALTER TABLE `GroupeEntreprise` ADD `quanCree` DATETIME NULL ,
ADD `quandModif` DATETIME NULL ,
ADD `quiCree` INT NULL ,
ADD `quiModif` INT NULL ,
ADD `tableOrigine` VARCHAR( 512 ) NULL ,
ADD `idOrigine` INT NULL ,
ADD `ip` VARCHAR( 512 ) NULL ;

ALTER TABLE `ImagePiece` ADD `quanCree` DATETIME NULL ,
ADD `quandModif` DATETIME NULL ,
ADD `quiCree` INT NULL ,
ADD `quiModif` INT NULL ,
ADD `tableOrigine` VARCHAR( 512 ) NULL ,
ADD `idOrigine` INT NULL ,
ADD `ip` VARCHAR( 512 ) NULL ;

ALTER TABLE `ImportationV1` ADD `quanCree` DATETIME NULL ,
ADD `quandModif` DATETIME NULL ,
ADD `quiCree` INT NULL ,
ADD `quiModif` INT NULL ,
ADD `ip` VARCHAR( 512 ) NULL ;

ALTER TABLE `LignePanier` ADD `quanCree` DATETIME NULL ,
ADD `quandModif` DATETIME NULL ,
ADD `quiCree` INT NULL ,
ADD `quiModif` INT NULL ,
ADD `tableOrigine` VARCHAR( 512 ) NULL ,
ADD `idOrigine` INT NULL ,
ADD `ip` VARCHAR( 512 ) NULL ;

ALTER TABLE `Message` ADD `quanCree` DATETIME NULL ,
ADD `quandModif` DATETIME NULL ,
ADD `quiCree` INT NULL ,
ADD `quiModif` INT NULL ,
ADD `tableOrigine` VARCHAR( 512 ) NULL ,
ADD `idOrigine` INT NULL ,
ADD `ip` VARCHAR( 512 ) NULL ;

ALTER TABLE `Panier` ADD `quanCree` DATETIME NULL ,
ADD `quandModif` DATETIME NULL ,
ADD `quiCree` INT NULL ,
ADD `quiModif` INT NULL ,
ADD `tableOrigine` VARCHAR( 512 ) NULL ,
ADD `idOrigine` INT NULL ,
ADD `ip` VARCHAR( 512 ) NULL ;

ALTER TABLE `Roles` ADD `quanCree` DATETIME NULL ,
ADD `quandModif` DATETIME NULL ,
ADD `quiCree` INT NULL ,
ADD `quiModif` INT NULL ,
ADD `tableOrigine` VARCHAR( 512 ) NULL ,
ADD `idOrigine` INT NULL ,
ADD `ip` VARCHAR( 512 ) NULL ;

ALTER TABLE `Stock` ADD `quanCree` DATETIME NULL ,
ADD `quandModif` DATETIME NULL ,
ADD `quiCree` INT NULL ,
ADD `quiModif` INT NULL ,
ADD `tableOrigine` VARCHAR( 512 ) NULL ,
ADD `idOrigine` INT NULL ,
ADD `ip` VARCHAR( 512 ) NULL ;

ALTER TABLE `TransactionAchatVente` ADD `quanCree` DATETIME NULL ,
ADD `quandModif` DATETIME NULL ,
ADD `quiCree` INT NULL ,
ADD `quiModif` INT NULL ,
ADD `tableOrigine` VARCHAR( 512 ) NULL ,
ADD `idOrigine` INT NULL ,
ADD `ip` VARCHAR( 512 ) NULL ;

ALTER TABLE `UserCompany` ADD `quanCree` DATETIME NULL ,
ADD `quandModif` DATETIME NULL ,
ADD `quiCree` INT NULL ,
ADD `quiModif` INT NULL ,
ADD `tableOrigine` VARCHAR( 512 ) NULL ,
ADD `idOrigine` INT NULL ,
ADD `ip` VARCHAR( 512 ) NULL ;

ALTER TABLE `UserRoles` ADD `quanCree` DATETIME NULL ,
ADD `quandModif` DATETIME NULL ,
ADD `quiCree` INT NULL ,
ADD `quiModif` INT NULL ,
ADD `tableOrigine` VARCHAR( 512 ) NULL ,
ADD `idOrigine` INT NULL ,
ADD `ip` VARCHAR( 512 ) NULL ;

ALTER TABLE `auth_url` ADD `quanCree` DATETIME NULL ,
ADD `quandModif` DATETIME NULL ,
ADD `quiCree` INT NULL ,
ADD `quiModif` INT NULL ,
ADD `tableOrigine` VARCHAR( 512 ) NULL ,
ADD `idOrigine` INT NULL ,
ADD `ip` VARCHAR( 512 ) NULL ;

ALTER TABLE `auth_view` ADD `quanCree` DATETIME NULL ,
ADD `quandModif` DATETIME NULL ,
ADD `quiCree` INT NULL ,
ADD `quiModif` INT NULL ,
ADD `tableOrigine` VARCHAR( 512 ) NULL ,
ADD `idOrigine` INT NULL ,
ADD `ip` VARCHAR( 512 ) NULL ;

ALTER TABLE `Client` ADD `quanCree` DATETIME NULL ,
ADD `quandModif` DATETIME NULL ,
ADD `quiCree` INT NULL ,
ADD `quiModif` INT NULL ,
ADD `tableOrigine` VARCHAR( 512 ) NULL ,
ADD `idOrigine` INT NULL ,
ADD `ip` VARCHAR( 512 ) NULL ;

ALTER TABLE `Adherent` ADD `versionObj` INT NULL;

ALTER TABLE `Users` ADD `versionObj` INT NULL;
ALTER TABLE `Annuaire` ADD `versionObj` INT NULL;
ALTER TABLE `FicheCareco` ADD `versionObj` INT NULL;
ALTER TABLE `Client` ADD `versionObj` INT NULL;
ALTER TABLE `auth_url` ADD `versionObj` INT NULL;
ALTER TABLE `auth_view` ADD `versionObj` INT NULL;
ALTER TABLE `GarantieCareco` ADD `versionObj` INT NULL;
ALTER TABLE `GroupeEntreprise` ADD `versionObj` INT NULL;
ALTER TABLE `ImagePiece` ADD `versionObj` INT NULL;
ALTER TABLE `LignePanier` ADD `versionObj` INT NULL;
ALTER TABLE `Message` ADD `versionObj` INT NULL;
ALTER TABLE `Panier` ADD `versionObj` INT NULL;
ALTER TABLE `Roles` ADD `versionObj` INT NULL;
ALTER TABLE `Stock` ADD `versionObj` INT NULL;
ALTER TABLE `TransactionAchatVente` ADD `versionObj` INT NULL;
ALTER TABLE `UserCompany` ADD `versionObj` INT NULL;
ALTER TABLE `UserRoles` ADD `versionObj` INT NULL;

ALTER TABLE `Stock` ADD `codeCompatibilite` VARCHAR( 256 ) NULL ;
ALTER TABLE `Stock` ADD `pieceOrpheline` BOOLEAN NULL ;
ALTER TABLE `Stock` ADD `dmsRef` VARCHAR( 256 ) NULL ;
ALTER TABLE `Stock` ADD `visibleAdherent` BOOLEAN NULL ,
ADD `visibleStock` BOOLEAN NULL ;
ALTER TABLE `Stock` ADD `energie` VARCHAR( 256 ) NULL ;

ALTER TABLE `Stock` ADD `cacheAAA` INT NULL ,
ADD `dumpMoteurClub` INT NULL ,
ADD `importFichier` VARCHAR( 512 ) NULL ,
ADD `idImportFichier` INT NULL ,
ADD `dateAchat` DATE NULL ,
ADD `status` VARCHAR( 512 ) NULL ;

ALTER TABLE `UserCompany` ADD `idMoteurClub` INT NULL ,
ADD `idCarecoFr` INT NULL ;

ALTER TABLE `Stock` ADD `importTypeFichier` VARCHAR( 256 ) NULL ;

CREATE TABLE IF NOT EXISTS `ImportStockTemp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idStock` int(11) NOT NULL,
  `idStockOrigine` int(11) NOT NULL,
  `disponibilite` tinyint(1) DEFAULT NULL,
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
  `prixCareco` decimal(10,2) DEFAULT NULL,
  `prixVente` decimal(10,2) DEFAULT NULL,
  `numLivrePolice` varchar(255) DEFAULT NULL,
  `immatriculation` varchar(255) DEFAULT NULL,
  `export` tinyint(1) DEFAULT NULL,
  `demonte` tinyint(1) DEFAULT NULL,
  `date1erMiseEnCirculation` date DEFAULT NULL,
  `prixAchat` decimal(10,2) DEFAULT NULL,
  `emplacementCasier` varchar(255) DEFAULT NULL,
  `numeroDeSeriePiece` varchar(255) DEFAULT NULL,
  `prixMinimum` decimal(10,2) DEFAULT NULL,
  `prixVenteConseille` decimal(10,2) DEFAULT NULL,
  `commentaireInterne` varchar(2048) DEFAULT NULL,
  `commentaireCommercial` varchar(2048) DEFAULT NULL,
  `dossier` varchar(255) DEFAULT NULL,
  `nogo` varchar(255) DEFAULT NULL,
  `pieceLourde` tinyint(1) NOT NULL,
  `quanCree` datetime DEFAULT NULL,
  `quandModif` datetime DEFAULT NULL,
  `quiCree` int(11) DEFAULT NULL,
  `quiModif` int(11) DEFAULT NULL,
  `tableOrigine` varchar(512) DEFAULT NULL,
  `idOrigine` int(11) DEFAULT NULL,
  `ip` varchar(512) DEFAULT NULL,
  `versionObj` int(11) DEFAULT NULL,
  `codeCompatibilite` varchar(256) DEFAULT NULL,
  `pieceOrpheline` tinyint(1) DEFAULT NULL,
  `dmsRef` varchar(256) DEFAULT NULL,
  `visibleAdherent` tinyint(1) DEFAULT NULL,
  `visibleStock` tinyint(1) DEFAULT NULL,
  `energie` varchar(256) DEFAULT NULL,
  `cacheAAA` int(11) DEFAULT NULL,
  `dumpMoteurClub` int(11) DEFAULT NULL,
  `importFichier` varchar(512) DEFAULT NULL,
  `idImportFichier` int(11) DEFAULT NULL,
  `dateAchat` date DEFAULT NULL,
  `status` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idStock` (`idStock`),
  KEY `idStockOrigine` (`idStockOrigine`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=500 ;

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `Stock`
--
ALTER TABLE `ImportStockTemp`
  ADD CONSTRAINT `ImportStockTemp_ibfk_3` FOREIGN KEY (`idStock`) REFERENCES `UserCompany` (`id`),
  ADD CONSTRAINT `ImportStockTemp_ibfk_4` FOREIGN KEY (`idStockOrigine`) REFERENCES `UserCompany` (`id`);


ALTER TABLE `ImportStockTemp` ADD `importTypeFichier` VARCHAR( 256 ) NULL ;

CREATE TABLE IF NOT EXISTS `VehiculeVHU` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company` int(11) DEFAULT NULL,
  `numeroLivrePolice` varchar(255) DEFAULT NULL,
  `immatriculation` varchar(255) DEFAULT NULL,
  `vin` varchar(255) DEFAULT NULL,
  `cacheAAA` int(11) DEFAULT NULL,
  `quanCree` datetime DEFAULT NULL,
  `quandModif` datetime DEFAULT NULL,
  `quiCree` int(11) DEFAULT NULL,
  `quiModif` int(11) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `versionObj` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `company` (`company`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;


ALTER TABLE `VehiculeVHU`
  ADD CONSTRAINT `VehiculeVHU_ibfk_1` FOREIGN KEY (`company`) REFERENCES `UserCompany` (`id`);
  
  ALTER TABLE `Panier` ADD `montantLivraisonHT` DECIMAL( 10, 2 ) NULL AFTER `montantLivraison` ,
ADD `montantLivraisonTVA` DECIMAL( 10, 2 ) NULL AFTER `montantLivraisonHT` ,
ADD `totalPEHT` DECIMAL( 10, 2 ) NULL AFTER `montantLivraisonTVA` ,
ADD `totalPETVA` DECIMAL( 10, 2 ) NULL AFTER `totalPEHT` ,
ADD `totalPETTC` DECIMAL( 10, 2 ) NULL AFTER `totalPETVA` ,
ADD `commentaire` TEXT NULL AFTER `totalPETTC`;

ALTER TABLE `LignePanier` ADD `montantPEHT` DECIMAL( 10, 2 ) NULL AFTER `montantPE` ,
ADD `montantPETVA` DECIMAL( 10, 2 ) NULL AFTER `montantPEHT` ,
ADD `tauxTVAPE` DECIMAL( 10, 2 ) NULL AFTER `montantPETVA` ;

ALTER TABLE `LignePanier` ADD `supplementLivraisonHT` DECIMAL( 10, 2 ) NULL AFTER `supplementLivraison` ,
ADD `supplementLivraisonTVA` DECIMAL( 10, 2 ) NULL AFTER `supplementLivraisonHT` ,
ADD `tauxTVALivraison` DECIMAL( 10, 2 ) NULL AFTER `supplementLivraisonTVA` ;

ALTER TABLE `LignePanier` ADD `PEDesactivee` BOOLEAN NULL AFTER `tauxTVALivraison` ;

UPDATE `careco`.`GarantieCareco` SET `prixPE12HT` = '33.33',
`prixPE24HT` = '50.00' WHERE `GarantieCareco`.`id` =1;

UPDATE `careco`.`GarantieCareco` SET `prixPE12HT` = '58.33',
`prixPE24HT` = '87.5' WHERE `GarantieCareco`.`id` =2;

UPDATE `careco`.`GarantieCareco` SET `prixPE12HT` = '87.5',
`prixPE24HT` = '133.33' WHERE `GarantieCareco`.`id` =3;

UPDATE `careco`.`GarantieCareco` SET `prixPE12HT` = '133.33',
`prixPE24HT` = '200.00' WHERE `GarantieCareco`.`id` =4;

UPDATE `careco`.`GarantieCareco` SET `prixPE12HT` = '158.33',
`prixPE24HT` = '237.5' WHERE `GarantieCareco`.`id` =5;

ALTER TABLE `Panier` CHANGE `tva` `tva` DECIMAL( 10, 3 ) NOT NULL ;
ALTER TABLE `Client` CHANGE `tva` `tva` DECIMAL( 10, 3 ) NOT NULL ;

ALTER TABLE `Client` ADD `adresse2Facturation` VARCHAR( 255 ) NOT NULL AFTER `adresseFacturation` ,
ADD `codePostalFacturation` VARCHAR( 255 ) NOT NULL AFTER `adresse2Facturation` ,
ADD `villeFacturation` VARCHAR( 255 ) NOT NULL AFTER `codePostalFacturation` ,
ADD `paysFacturation` VARCHAR( 255 ) NOT NULL AFTER `villeFacturation` ;

ALTER TABLE `Client` CHANGE `adresse2Facturation` `adresse2Facturation` VARCHAR( 255 ) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL ,
CHANGE `codePostalFacturation` `codePostalFacturation` VARCHAR( 255 ) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL ,
CHANGE `villeFacturation` `villeFacturation` VARCHAR( 255 ) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL ,
CHANGE `paysFacturation` `paysFacturation` VARCHAR( 255 ) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL ;

ALTER TABLE `Client` ADD `adresse2Livraison` VARCHAR( 255 ) NULL AFTER `adresseLivraison` ,
ADD `codePostalLivraison` VARCHAR( 255 ) NULL AFTER `adresse2Livraison` ,
ADD `villeLivraison` VARCHAR( 255 ) NULL AFTER `codePostalLivraison` ,
ADD `paysLivraison` VARCHAR( 255 ) NULL AFTER `villeLivraison` ;

ALTER TABLE `LignePanier` ADD `dureePeVendu` INT NULL AFTER `tauxTVALivraison` ,
ADD `typeVente` VARCHAR( 255 ) NULL AFTER `dureePeVendu` ,
ADD `commentaireCommande` TEXT NULL AFTER `typeVente` ;

update Stock s set s.vendeur = (select nom from UserCompany u where s.idStock = u.id) where s.vendeur is null;

CREATE TABLE IF NOT EXISTS `SocietesAmies` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `typeRelation` varchar(255) DEFAULT NULL,
  `societeA` int(11) NOT NULL,
  `societeB` int(11) NOT NULL,
  `pourcentageReduction` decimal(10,2) DEFAULT NULL,
  `quanCree` datetime DEFAULT NULL,
  `quandModif` datetime DEFAULT NULL,
  `quiCree` int(11) DEFAULT NULL,
  `quiModif` int(11) DEFAULT NULL,
  `ip` varchar(512) DEFAULT NULL,
  `versionObj` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `societeB` (`societeB`),
  KEY `societeA` (`societeA`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

--
-- Contraintes pour la table `SocietesAmies`
--
ALTER TABLE `SocietesAmies`
  ADD CONSTRAINT `SocietesAmies_ibfk_1` FOREIGN KEY (`societeA`) REFERENCES `UserCompany` (`id`),
  ADD CONSTRAINT `SocietesAmies_ibfk_2` FOREIGN KEY (`societeB`) REFERENCES `UserCompany` (`id`);
  

ALTER TABLE `Message` ADD `nouveau` BOOLEAN NULL AFTER `message` ,
ADD `important` BOOLEAN NULL AFTER `nouveau` ,
ADD `type` VARCHAR( 255 ) NULL AFTER `important` ;

--A faire
---------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------

ALTER TABLE `Immatriculation` ADD `ktype` VARCHAR( 50 ) NULL ;

CREATE TABLE IF NOT EXISTS `Log_Appel_B2B` (
  `Code_Log` int(11) NOT NULL AUTO_INCREMENT,
  `Login_Log` varchar(50) DEFAULT NULL,
  `Mdp_Log` varchar(50) DEFAULT NULL,
  `Immat_Log` varchar(20) DEFAULT NULL,
  `TyperReq_Log` int(5) DEFAULT NULL,
  `Date_Log` date DEFAULT NULL,
  `Type_Log` varchar(50) DEFAULT NULL,
  `Nom_Log` varchar(3) DEFAULT NULL,
  `Heure_Log` time DEFAULT NULL,
  `CodeType_Log` int(2) DEFAULT NULL,
  `TypeConnex` varchar(7) DEFAULT NULL,
  PRIMARY KEY (`Code_Log`),
  UNIQUE KEY `Code_Log` (`Code_Log`)
) ENGINE=InnoDB

ALTER TABLE `AutorisationB2B` CHANGE `Time` `Time` DATETIME NULL DEFAULT NULL;
UPDATE AutorisationB2B SET `Time` = NULL 

ALTER TABLE `AutorisationB2C` CHANGE `Time` `Time` DATETIME NULL DEFAULT NULL;
UPDATE AutorisationB2C SET `Time` = NULL 

ALTER TABLE `Log_Appel_B2B` ADD `Message` text,
ADD  `IP` varchar(256) DEFAULT NULL,
ADD `Logiciel_Client` varchar(256) DEFAULT NULL;

CREATE TABLE IF NOT EXISTS `Log_Appel_B2C` (
  `Code_Log` int(11) NOT NULL AUTO_INCREMENT,
  `Login_Log` varchar(50) DEFAULT NULL,
  `Mdp_Log` varchar(50) DEFAULT NULL,
  `Immat_Log` varchar(20) DEFAULT NULL,
  `TyperReq_Log` int(5) DEFAULT NULL,
  `Date_Log` date DEFAULT NULL,
  `Type_Log` varchar(50) DEFAULT NULL,
  `Nom_Log` varchar(3) DEFAULT NULL,
  `Heure_Log` time DEFAULT NULL,
  `CodeType_Log` int(2) DEFAULT NULL,
  `TypeConnex` varchar(7) DEFAULT NULL,
   `Message` text,
  `IP` varchar(256) DEFAULT NULL,
  `Logiciel_Client` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`Code_Log`),
  UNIQUE KEY `Code_Log` (`Code_Log`)
) ENGINE=InnoDB









