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
    
    
}

$(document).ready( init);