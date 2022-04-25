#!/bin/bash

# le fichier doit être charger au démarrage, donc une des possibiliés est de restautrer les règles juste avant l'activation des interfaces réseaux.
# Sur Debian, on peut placer le script dans le répertoire /etc/network/if-pre-up.d/

iptables-restore < /etc/iptables.regles

