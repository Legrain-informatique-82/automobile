#!/bin/bash

#cron, redémarrage des serveurs jboss et mysql tous les jours à 3h
#0 3 * * * /root/reboot_jboss.sh

echo "Début redémarrage de jboss et mysql  ====== $(date)" >> /var/log/log_reboot_jboss.txt
ps -ae | grep java;ps -ae | grep sh

PID_JAVA=$(pidof java)
PID_JBOSS=$(pidof -x standalone.sh)

echo "PID_JAVA = $PID_JAVA  ==== PID_JBOSS = $PID_JBOSS"

kill -9 $PID_JAVA $PID_JBOSS

ps -ae | grep java;ps -ae | grep sh

#service n'est pas dans le PATH par defaut si le script est lancé par un cron, il faut donc mettre le chemin complet
/usr/sbin/service mysql restart
/usr/sbin/service jboss start

ps -ae | grep java;ps -ae | grep sh
echo "Fin redémarrage de jboss et mysql  ====== $(date)" >> /var/log/log_reboot_jboss.txt
