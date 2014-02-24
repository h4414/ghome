/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function init() {

    $("#validation").click(quelquonque);
    $('#leftMenu').load("/ghome/html/menu.html");
    
    $("#header").load("/ghome/html/header.html");
    var list = retrievePieces();
    affichage(list);
    $("input[type=checkbox]").on("click", function(event) {
        event.stopPropagation();
    });
    $("#ComboboxType").load("/ghome/html/reglesCombobox.html");
$("#btnAddCondition").click(function(event)
{
   var index= $("#typeRegle")[0].selectedIndex;
  var optionSelected = $("#typeRegle")[0].options[index];
  if(optionSelected.value == "TEMPERATURE")
  {
      $("#temperature").load("reglesTemp.html");
      $("#temperature")[0].style.display = "block";
      
  }
  if(optionSelected.value == "CONTACTEUR")
  {
      $("#Contacteur").load("reglesContacteur.html");
      $("#Contacteur")[0].style.display = "block";
  }
  if(optionSelected.value == "BOUTON")
  {
       $("#bouton").load("reglesBouton.html");
      $("#bouton")[0].style.display = "block";
  }
    if(optionSelected.value == "PRESENCE")
  {
      $("#presence").load("reglesPresence.html");
      $("#presence")[0].style.display = "block";
  }
      if(optionSelected.value == "PRESENCE")
  {
      $("#presence").load("reglesPresence.html");
      $("#presence")[0].style.display = "block";
  }   
        if(optionSelected.value == "TEMPS")
  {
      $("#temps").load("reglesTemps.html");
      $("#temps")[0].style.display = "block";
  }   
});
}

function quelquonque()
{
    newRegle = new Object();
    var tableauCheckbox = new Array($("input[type='checkbox']")) ;
    var tableauPiece = new Array();
    for (var i = 0 ; i < tableauCheckbox.length ; i++ )
    {
        if (tableauCheckbox[i].parentnode.id == "pieceGauche" || tableauCheckbox[i].parentnode.id == "pieceDroite")
        {
            tableauPiece.append(tableauCheckbox[i]);
        }
    }
    newRegle.pieces = tableauPiece;
    newRegle.conditions = new Array();
    condition = new Object();
    var heureDebut = $("heureDebut");
    if (heureDebut.value != "")
    {
        condition.type = "presence";
        condition.dateDebut = $("heureDebut");
        condition.dateFin = $("heureFin");
        newRegle.conditions.append(condition);
    }
    condition = new Object();
    var contacteur = $("porteOuverte").value;
    if(contacteur != "")
    {
        condition.type = "contacteur";
        if ($("porteFermee").value != "")
        {
            condition.fermee = "1";
        }
        else
        {
            condition.fermee = "0";
        }
        newRegle.conditions.append(condition);
    }
    //TODO temps.
    condition = new Object();
    var tempMin = $("tempMin");
    if(tempMin.value != "")
    {
        conditon.type = "temperature";
        condition.tempMin = $("tempMin").value;
        condition.tempMax = $("tempMax").value;
        newRegle.conditions.append(condition);
    }
    condition = new Object();
    var idBouton0 = $("btA0").value;
    var idBouton1 = $("btA1").value;
    var idBouton2 = $("btB0").value;
    var idBouton3 = $("btB1").value;
    condition.type = "bouton";
    if (idBouton0 != "")
    {
        condition.bouton = "0";
        newRegle.conditions.append(condition);
    }
    if (idBouton1 != "")
    {
        condition.bouton = "1";
        newRegle.conditions.append(condition);
    }
    if (idBouton2 != "")
    {
        condition.bouton = "2";
        newRegle.conditions.append(condition);
    }
    if (idBouton3 != "")
    {
        condition.bouton = "3";
        newRegle.conditions.append(condition);
    }
    var jText = JSON.stringify(newRegle);
    
    var xmlHttp = null;

    xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "POST", "http://localhost:8087/addrule", false );
    xmlHttp.send( jText );
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



/*    <label class="checkbox">
 <input type="checkbox"> Coche moi
 </label>
 <label class="checkbox">
 <input type="checkbox"> Moi aussi
 </label>
 <label class="checkbox">
 <input type="checkbox"> Et moi lol
 </label>/*/