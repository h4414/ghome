/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function init()
{
    $("#header").load("/ghome/html/header.html"); 
    // TO DO : requete sur getData pour chopper la liste des pieces dans la base
    

$("#btnAjouter").click((function()
{
    newPiece = new Object();
   newPiece.nom = $("#nomPiece").val() ;
   if( newPiece.nom !== ""  )//RAJOUTER CONDITION SUR UNICITE
   {
   var jText = JSON.stringify(newPiece);
    

    var xmlHttp = null;

    xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "POST", "http://localhost:8087/addpiece", false );
    xmlHttp.send( jText );
    $("#MessageSucces")[0].textContent="La pièce a bien été ajoutée";
    $("#MessageSucces")[0].style.display="block"; 
    $("#nomPiece").val("") ;
   }
   else 
   {
       $("#MessageErreur")[0].textContent="Erreur : L'un des champs est vide";
       $("#MessageErreur")[0].style.display="block"; 
       
   }
   
}));}
$(document).ready( init);

