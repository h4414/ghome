/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(init);

function init()
{
    
    $("#header").load("/ghome/html/header.html");
        $('#leftMenu').load("/ghome/html/menu.html");

//Get context with jQuery - using jQuery's .get() method.
var ctx = $("#myChart").get(0).getContext("2d");
var ctx2 = $("#myChart2").get(0).getContext("2d");
//This will get the first returned node in the jQuery collection.
var myNewChart = new Chart(ctx); 
    var listHisto  = recupHisto();
var listHeure = calculDataPresence(listHisto);
    /*
    var date = new Date(listHisto.data[i].debutPresence);
    listHeure.push(date.getHours());*/
    traceGraphPresence(ctx,listHeure);
    
    var temperatureChart = new Chart(ctx2);
    var listHeureTemp = calculDataTemp(listHisto);
    traceGraphTemp(ctx2,listHeureTemp);
    
    //affichage liste de presence
    affichageListePresence(listHisto);
    
}

function traceGraphPresence(ctx,listHeure)
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

}

function traceGraphTemp(ctx,listHeure)
{
var data = {
        labels : [0,1,2,3,4,5,6,7,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23],
	datasets : [
		{
                    
			fillColor : "rgba(220,220,220,0.5)",
			strokeColor : "rgba(220,220,220,1)",
                        pointColor : "rgba(220,220,220,1)",
			pointStrokeColor : "#fff",
			data : listHeure
		}

	]
};
//data.labels = [0,1,2,3,4,5,6,7,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23];
//data.datasets[0].data = listHeure;
 var chart = new Chart(ctx).Line(data);

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
function calculDataPresence(listHisto)
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
function calculDataTemp(listHisto)
{
    var listHeure = new Array();
    for(var i=0;i< 24;i++)
    {
        listHeure[i]=0;
        var temp = new Array();
        var t = 0
        for(var j=0;j<listHisto.data.length;j++)
        {
            var date = new Date(listHisto.data[j].debutPresence);
            if(date.getHours() == i)
            {
                temp[t]=(listHisto.data[j].donnee);
                t++;
            }
        }
        var moy = 0;
        for (var k=0;k<temp.length;k++)
        {
            moy = moy + temp[k];
        }
        listHeure[i]=moy/(temp.length);
        
    }
    return listHeure;
}

function affichageListePresence(listHisto)
{
    var listLastHist = listHisto.data.slice(-10);
    var options = {hour: "numeric", minute: "numeric", second: "numeric",
           hour12: false};
    for (var i=listLastHist.length-1;i>0;i--)
    {
        var str ="<tr>";
        var beginTime = new Date(listLastHist[i].debutPresence);
        var endTime = new Date(listLastHist[i].finPresence);
        str += "<td>"+ listLastHist[i].idCapteur + "</td>";
        str += "<td>"+beginTime.toLocaleDateString("fr-FR") +"</td>";
        str += "<td>"+ beginTime.toLocaleString("fr-FR",options)+ "</td>";
        str += "<td>"+endTime.toLocaleDateString("fr-FR")+"</td>";
        str += "<td>"+ endTime.toLocaleString("fr-FR",options) + "</td>";
        str += "</tr>";
        $("#listePresenceContenu")[0].innerHTML+=str;
    }
}
