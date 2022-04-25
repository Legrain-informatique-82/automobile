function nitcars() {
 var nbpic=50;
 var icar=document.getElementById("p1").innerHTML;
 //var tcar=document.getElementById("car-up").innerHTML;
 //document.getElementById("p2").innerHTML=tcar;
 if (icar < nbpic) {
icar = icar * 1 ;
icar++;
return icar;}
else {
 icar =1 ;
 return icar;
}
};
function picD(i) {

  document.getElementById("e_cars").src="css/images/cars/ds3/Citroen_DS3_2011_360_"+ i +".jpg";
  document.getElementById("l_cars").href="css/images/cars/ds3/Citroen_DS3_2011_360_"+ i +".jpg";
  document.getElementById("p1").innerHTML=i;
  
 return;
 }
function nitcar() {
 var nbpic=1;
var icar=document.getElementById("p1").innerHTML;
if (icar > nbpic) {
icar = icar * 1 ;
icar--;
return icar;}
else {
 icar = 50 ;
 return icar;
}
};

function cplay(){
var icar=document.getElementById("p1").innerHTML;
setTimeout( function () {
if (icar < 50) {
icar = icar * 1 ;
icar++;
document.getElementById("e_cars").src="css/images/cars/ds3/Citroen_DS3_2011_360_"+ icar +".jpg";
document.getElementById("l_cars").href="css/images/cars/ds3/Citroen_DS3_2011_360_"+ icar +".jpg";
document.getElementById("p1").innerHTML=icar;
}
else {
 icar = 1 ;
 document.getElementById("e_cars").src="css/images/cars/ds3/Citroen_DS3_2011_360_"+ icar +".jpg";
 document.getElementById("l_cars").href="css/images/cars/ds3/Citroen_DS3_2011_360_"+ icar +".jpg";
document.getElementById("p1").innerHTML=icar;
}
document.getElementById("e_cars").src="css/images/cars/ds3/Citroen_DS3_2011_360_"+ icar +".jpg";
document.getElementById("l_cars").href="css/images/cars/ds3/Citroen_DS3_2011_360_"+ icar +".jpg";
document.getElementById("p1").innerHTML=icar;
cplay();
},130);

}