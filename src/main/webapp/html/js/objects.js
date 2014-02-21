/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function init()
{
    $("#header").load('/ghome/html/header.html'); 
        $('#leftMenu').load("/ghome/html/menu.html");

    var liste = retrieveCapteurs();
    affichage(liste);
    $(".glyphicon-remove").click(function(event)
{
    confirm("Le capteur va être supprimé, êtes vous sur ? ");
    //TODO ENVOYER UNE REQUETE AU BACK END POUR VIRER LE CAPTEUR CHOISI 
    }
    
);
}
$(document).ready( init);


function retrieveCapteurs()
{
    var  xmlHttp = new XMLHttpRequest();
    xmlHttp.responseType="JSON";
    xmlHttp.open( "GET", "http://localhost:8087/getdata?name=Capteur", false );
    xmlHttp.send();
    var data = xmlHttp.responseText;
    var listeCapteurs=JSON.parse(data);
    return listeCapteurs;
}

function affichage(listeCapteurs)
{  
    for(var i=0;i< listeCapteurs.data.length;i++)
    {
        var strToAdd = "<tr>";
        strToAdd += "<td>" + listeCapteurs.data[i].idCapteur + "</td>";
        strToAdd += "<td>" + listeCapteurs.data[i].nomCapteur + "</td>";
              strToAdd += "<td>" + "<span class=\"glyphicon glyphicon-edit\"></span>" + "</td>";
                    strToAdd += "<td>" +"<span class=\"glyphicon glyphicon-remove\"></span>"+ "</td>";
     //   var objectToAdd = new Node(strToAdd);
        $("#listeCapteursContenu")[0].innerHTML+=strToAdd;
    }
  

}

