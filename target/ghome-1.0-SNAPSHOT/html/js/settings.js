/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function init()
{
    $("#header").load("/ghome/html/header.html"); 
    // TO DO : requete sur getData pour chopper la liste des pieces dans la base
        $('#leftMenu').load("/ghome/html/menu.html");
    
    var liste = retrievePieces();
    affichage(liste);
    
$("#btnAjouter").click((function(event)
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
    var liste = retrievePieces();
    affichage(liste);
    $("#nomPiece").val("") ;

   }
   else 
   {
       $("#MessageErreur")[0].textContent="Erreur : L'un des champs est vide";
       $("#MessageErreur")[0].style.display="block";    
   }
    }
));

}

function retrievePieces()
{
    var  xmlHttp = new XMLHttpRequest();
    xmlHttp.responseType="JSON";
        xmlHttp.open( "GET", "http://localhost:8087/getdata?name=Piece", false );
    xmlHttp.send();
    var data = xmlHttp.responseText;
    var listePieces=JSON.parse(data);
    return listePieces;
}

function affichage(listePieces)
{  
    $("#listePiecesContenu")[0].innerHTML="";
    for(var i=0;i< listePieces.data.length;i++)
    {
        var strToAdd = "<tr>";
        strToAdd += "<td>" + listePieces.data[i].nom + "</td>";
              strToAdd += "<td>" +  "</td>";
                    strToAdd += "<td>" +"<span class=\"glyphicon glyphicon-remove\"></span>"+ "</td>";
     //   var objectToAdd = new Node(strToAdd);
        $("#listePiecesContenu")[0].innerHTML+=strToAdd;
    }
    }
$(document).ready( init);
