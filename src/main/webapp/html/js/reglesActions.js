/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function init()
{
    var xmlHttp = null;
    xmlHttp = new XMLHttpRequest();
    xmlHttp.responseType="JSON";
    xmlHttp.open( "GET", "http://localhost:8087/getdata?name=Capteur", false );
    xmlHttp.send();
    var data = xmlHttp.responseText;
    var listeCapteur=JSON.parse(data);
    
    var listePrise = new Array();
    var j=0;
    for (var i=0;i<listeCapteur.data.length;i++)
    {
        if (listeCapteur.data[i].type == "PRISE")
        {
            listePrise[j]=listeCapteur.data[i];
            j++;
        }
    }
    
    for (var i=0;i<listePrise.length;i++)
    {
        var opt = document.createElement('option');
        opt.value = listePrise[i].idCapteur;
        opt.innerHTML = listePrise[i].nomCapteur;
        select = document.getElementById('prise');
        select.appendChild(opt);
    }
    
    
}

$(document).ready( init);