#cd /donnees/j2ee/nexus-2.5.1-01-bundle/nexus-2.5.1-01/bin
#./nexus start
#echo "lancement nexus admin/admin123"

JAVA_HOME=/donnees/java/jdk1.7.0_25_x64
export JAVA_HOME

cd /donnees/Projet/Java/Eclipse/Careco/fr.careco.ejb
mvn clean install

cd /donnees/Projet/Java/Eclipse/Careco/fr.careco.webapp
mvn clean install

cd /donnees/Projet/Java/Eclipse/Careco/fr.careco.ear
mvn clean install

echo "XSwTRduU"
scp /donnees/Projet/Java/Eclipse/Careco/fr.careco.ear/target/fr.careco.ear-0.0.1-SNAPSHOT.ear root@188.165.243.53:/var/careco/

echo "==========================="
echo "ps -ae | grep standalone.sh"
echo "ps -ae | grep java"
echo "ssh root@188.165.243.53"
echo "cd /var/careco/"
echo "cd /var/careco/jboss-as-7.1.3.Final_careco/bin"
echo "/var/careco/jboss-as-7.1.3.Final_careco/bin/standalone.sh"
echo "cd /var/careco/jboss-as-7.1.3.Final_careco/standalone/deployments/"
echo "rm -rf /var/careco/jboss-as-7.1.3.Final_careco/standalone/deployments/fr.careco.*"
echo "mv /var/careco/jboss-as-7.1.3.Final_careco/standalone/deployments/fr.careco.ear-0.0.1-SNAPSHOT.ear /var/careco/old_ear/"
echo "mv /var/careco/fr.careco.ear-0.0.1-SNAPSHOT.ear /var/careco/jboss-as-7.1.3.Final_careco/standalone/deployments/"
echo "scp -r /donnees/j2ee/jboss-as-7.1.3.Final_careco/standalone/deployments/fr.careco.ear.ear root@188.165.243.53:/var/careco/"
echo "/usr/bin/rsync -arvz --delete-after /donnees/j2ee/jboss-as-7.1.3.Final_careco/standalone/deployments/fr.careco.ear.ear root@188.165.243.53:/var/careco/"
echo "mv jboss-as-7.1.3.Final_careco/standalone/deployments/fr.careco.ear.ear ./fr.careco.ear.ear_05-12-2013"
echo "cp -rap fr.careco.ear.ear jboss-as-7.1.3.Final_careco/standalone/deployments/"
echo "==========================="

echo "==========================="
echo "Uvo6KWoeNgXr"
echo "/usr/bin/rsync -arvz --delete-after /donnees/j2ee/jboss-as-7.1.3.Final_careco/standalone/deployments/fr.careco.ear.ear root@37.59.53.196:/var/careco/"
echo "mv jboss-as-7.1.3.Final_careco/standalone/deployments/fr.careco.ear.ear ./fr.careco.ear.ear_09-01-2014"
echo "cp -rap fr.careco.ear.ear jboss-as-7.1.3.Final_careco/standalone/deployments/"
echo "tail -f /var/careco/jboss-as-7.1.3.Final_careco/standalone/log/server.log"
echo "service jboss start"
echo "service jboss stop"
echo "ps -ae | grep  sh"
echo "ps -ae | grep  java"
echo "ps -ae | grep java;ps -ae | grep sh"
echo "==========================="

echo "========ITCAR.PRO============="
echo "/usr/bin/rsync -arvz --delete-after /donnees/j2ee/jboss-as-7.1.3.Final_itcar_pro/standalone/deployments/fr.careco.ear.ear root@94.23.246.43:/var/careco/";
echo "mv jboss-as-7.1.3.Final_careco/standalone/deployments/fr.careco.ear.ear ./fr.careco.ear.ear_03-12-2014"
echo "cp -rap fr.careco.ear.ear jboss-as-7.1.3.Final_careco/standalone/deployments/"


#19:02:49,884 ERROR [fr.legrain.data.GestionModif] (http-/0.0.0.0:8888-1) Erreur : setListeGestionModif: java.lang.Exception: Le fichier .properties /home/nicolas/public/lgrdoss/BureauDeGestion/demo/Bd/Modif.properties est inexistant

