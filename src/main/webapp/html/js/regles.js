/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function init() {


    $('#leftMenu').load("/ghome/html/menu.html");

    $("#header").load("/ghome/html/header.html");
    var list = retrievePieces();
    affichage(list);
    $("input[type=checkbox]").on("click", function(event) {
        event.stopPropagation();
    });
    

}

function quelquonque()
{
    newRegle = new Object();
    var tableauCheckbox = $("input[type='checkbox']") ;
    
}

function retrievePieces()
{
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.responseType = "JSON";
    xmlHttp.open("GET", "http://localhost:8087/getdata?name=Piece", false);
    xmlHttp.send();
    var data = xmlHttp.responseText;
    var listePieces = JSON.parse(data);
    return listePieces;
}

function affichage(listePieces)
{
    $("#pieceGauche")[0].innerHTML = "";
    $("#pieceDroite")[0].innerHTML = "";

    for (var i = 0; i < listePieces.data.length; i++)
    {
        if (i % 2 == 0)
        {
            var strToAdd = "<label class=\"checkbox\">";
            strToAdd += " <input type=\"checkbox\">"+ listePieces.data[i].nom +"</label>" ;
            //   var objectToAdd = new Node(strToAdd);
            $("#pieceGauche")[0].innerHTML += strToAdd;
        }
        else
        {
            var strToAdd = "<label class=\"checkbox\">";
            strToAdd += " <input type=\"checkbox\" value=\""+ listePieces.data[i].nom + "\">"+ listePieces.data[i].nom +"</label>" ;;
            //   var objectToAdd = new Node(strToAdd);
            $("#pieceDroite")[0].innerHTML += strToAdd;
        }
    }
}
$(document).ready(init);


$(document).ready(init);

/*    <label class="checkbox">
 <input type="checkbox"> Coche moi
 </label>
 <label class="checkbox">
 <input type="checkbox"> Moi aussi
 </label>
 <label class="checkbox">
 <input type="checkbox"> Et moi lol
 </label>/*/