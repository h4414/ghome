/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(init);

function init()
{
    
    $("#header").load("/ghome/html/header.html"); 
//Get context with jQuery - using jQuery's .get() method.
var ctx = $("#myChart").get(0).getContext("2d");
//This will get the first returned node in the jQuery collection.
var myNewChart = new Chart(ctx); 
    var listHisto  = recupHisto();
var listHeure = calculData(listHisto);
    /*
    var date = new Date(listHisto.data[i].debutPresence);
    listHeure.push(date.getHours());*/
    traceGraph(ctx,listHeure);
}

function traceGraph(ctx,listHeure)
{
var data = {
	datasets : [
		{
                    
			fillColor : "rgba(220,220,220,0.5)",
			strokeColor : "rgba(220,220,220,1)",
			
		}

	]
};
data.labels = [0,1,2,3,4,5,6,7,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23];
data.datasets[0].data = listHeure;
 var chart = new Chart(ctx).Bar(data);
 char
}

function recupHisto()
{
//Partie recup des données. 
     var xmlHttp = new XMLHttpRequest();
    xmlHttp.responseType="JSON";
    xmlHttp.open( "GET", "http://localhost:8087/getdata?name=Historique", false );
    xmlHttp.send();
    var data = xmlHttp.responseText;
    var listHisto=JSON.parse(data);
    return listHisto;
}
function calculData(listHisto)
{

var listHeure = new Array() ;
for(var i=0;i< 24;i++)
{
    listHeure[i]=0;
    for(var j=0;j<listHisto.data.length;j++)
    {        
        var date = new Date(listHisto.data[j].debutPresence);
       if(date.getHours() == i) 
       {
           listHeure[i]++;
       }
    }
   
}
return listHeure;
}

