/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).on("submit","form",(function(event)
{
   newCapteur = new Object();
   var indexSelected = $("#selectPieces")[0].selectedIndex;
   newCapteur.type=$("#typeCapteur")[0].options[indexSelected].text; // A retravailler ^^ 
   newCapteur.id = $("#idCapteur").val().toUpperCase();
   newCapteur.nomCapteur = $("#nomCapteur").val();
   indexSelected = $("#selectPieces")[0].selectedIndex;
   newCapteur.piece = $("#selectPieces")[0].options[indexSelected].text;
   if( newCapteur.type !== "" &&  newCapteur.id !== "" && newCapteur.nomCapteur !== "" && newCapteur.piece !== "")
   {
   var jText = JSON.stringify(newCapteur);
    

    var xmlHttp = null;

    xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "POST", "http://localhost:8087/addobject", false );
    xmlHttp.send( jText );
   }
   else 
   {
       $("#MessageErreur")[0].textContent="Erreur : L'un des champs est vide";
       $("#MessageErreur")[0].style.display="block"; 
       event.preventDefault();
   }
}));

$("#idCapteur").blur(function()
{
    
    var valueID = $("#idCapteur").val();
    valueID = valueID.toUpperCase();
    if (valueID != "")
    {
    var regexp = new RegExp("^[0-9A-F]+$");
    if(!regexp.test(valueID))
    {
       $("#idCapteur").val(""); 
       $("#MessageErreur")[0].textContent="Erreur : l'ID du capteur est un nombre hexadÃ©cimal";
       $("#MessageErreur")[0].style.display="block";  
    }
    }
});

function init()
{
    $("#header").load("/ghome/html/header.html"); 
    // TO DO : requete sur getData pour chopper la liste des pieces dans la base
       var xmlHttp = null;

    xmlHttp = new XMLHttpRequest();
    xmlHttp.responseType="JSON";
    xmlHttp.open( "GET", "http://localhost:8087/getdata?name=Piece", false );
    xmlHttp.send();
    var data = xmlHttp.responseText;
    var listePiece=JSON.parse(data);
    var i;
    for(i=0;i< listePiece.length;i++ )
    {
    $("#selectPieces")[0].options[i].textContent=(listePiece[i]["nom"] );
    }
    
}

$(document).ready( init);