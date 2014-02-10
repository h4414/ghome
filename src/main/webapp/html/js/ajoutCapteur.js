/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$("#btnValider").click(function()
{
   newCapteur = new Object();
   newCapteur.type="capteur";
   newCapteur.id = $("#idCapteur").val();
   newCapteur.nomCapteur = $("#nomCapteur").val(); 
   newCapteur.piece = "A REMPLIR";
   var jText = JSON.stringify(newCapteur);
    

    var xmlHttp = null;

    xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "POST", "http://localhost:8087/addobject", false );
    xmlHttp.send( jText );
});

$("#idCapteur").blur(function()
{
    var valueID = $("#idCapteur").val();
    valueID = valueID.toUpperCase();
    var regexp = new RegExp("^[0-9A-F]+$");
    if(!regexp.test(valueID))
    {
       $("#idCapteur").val(""); 
       $("#MessageErreur")[0].textContent="Erreur : l'ID du capteur est un nombre hexadécimal";
       $("#MessageErreur")[0].style.display="block";  //style.display="block";
    }
});