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
    var captTemp = false;
    var captPres = false;
    var captContact = false;
    var captBouton = false;
    for (var i=0;i<listeCapteur.data.length;i++)
    {
        if (listeCapteur.data[i].type == "TEMPERATURE")
        {
            captTemp = true;
        }
        if (listeCapteur.data[i].type == "PRESENCE")
        {
            captPres = true;
        }
        if (listeCapteur.data[i].type == "CONTACTEUR")
        {
            captContact = true;
        }
        if (listeCapteur.data[i].type == "BOUTON")
        {
            captBouton = true;
        }
    }
    
   
    
    if (captTemp)
    {
      var opt = document.createElement('option');
        opt.value = "TEMPERATURE";
        opt.innerHTML = "TEMPERATURE";
        select = document.getElementById('typeRegle');
        select.appendChild(opt);
    }
        if (captPres)
    {
         var opt = document.createElement('option');
        opt.value = "PRESENCE";
        opt.innerHTML = "PRESENCE";
        select = document.getElementById('typeRegle');
        select.appendChild(opt);
    }
        if (captContact)
    {
         var opt = document.createElement('option');
        opt.value = "CONTACTEUR";
        opt.innerHTML = "CONTACTEUR";
        select = document.getElementById('typeRegle');
        select.appendChild(opt);
    }
        if (captBouton)
    {
         var opt = document.createElement('option');
        opt.value = "BOUTON";
        opt.innerHTML = "BOUTON";
        select = document.getElementById('typeRegle');
        select.appendChild(opt);
    }
    }          


$(document).ready( init);