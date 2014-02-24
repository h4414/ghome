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
    $("#action").load("/ghome/html/reglesActions.html");
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
    var tableauCheckbox = $("input[type='checkbox']") ;
    var tableauPiece = new Array();
    for (var i = 0 ; i < tableauCheckbox.length ; i++ )
    {
        if (tableauCheckbox[i].parentNode.parentNode.id == "pieceGauche" || tableauCheckbox[i].parentNode.parentNode.id == "pieceDroite")
        {
            tableauPiece.push(tableauCheckbox[i].value);
        }
    }
    newRegle.pieces = tableauPiece;
    newRegle.conditions = new Array();
    condition = new Object();
    var heureDebut = $("#dateDebut");
    if (heureDebut.val() != "")
    {
        condition.type = "presence";
        condition.dateDebut = $("#dateDebut").val();
        condition.dateFin = $("#dateFin").val();
        newRegle.conditions.push(condition);
    }
    condition = new Object();
    var contacteur = $("#porteOuverte").val();
    if(contacteur != "")
    {
        condition.type = "contacteur";
        if ($("#porteFermee").val() != "")
        {
            condition.fermee = "1";
        }
        else
        {
            condition.fermee = "0";
        }
        newRegle.conditions.push(condition);
    }
    //TODO temps.
    condition = new Object();
    var tempMin = $("#tempMin");
    if(tempMin.val() != "")
    {
        condition.type = "temperature";
        condition.tempMin = $("#tempMin").val();
        condition.tempMax = $("#tempMax").val();
        newRegle.conditions.push(condition);
    }
    condition = new Object();
    var idBouton0 = $("#btA0").val();
    var idBouton1 = $("#btA1").val();
    var idBouton2 = $("#btB0").val();
    var idBouton3 = $("#btB1").val();
    condition.type = "bouton";
    if (idBouton0 != "")
    {
        condition.bouton = "0";
        newRegle.conditions.push(condition);
    }
    if (idBouton1 != "")
    {
        condition.bouton = "1";
        newRegle.conditions.push(condition);
    }
    if (idBouton2 != "")
    {
        condition.bouton = "2";
        newRegle.conditions.push(condition);
    }
    if (idBouton3 != "")
    {
        condition.bouton = "3";
        newRegle.conditions.push(condition);
    }
    
    newRegle.actions = new Array();
    var envoiMail = $("#envoiMail").is(':checked');
    var activerPrise = $("#activerPrise").is(':checked');
    var desactiverPrise = $("#desactiverPrise").is(':checked');
    
    if (envoiMail)
    {
        action = new Object();
        action.type = "envoyerMail";
        newRegle.actions.push(action);
        
    }
    if (activerPrise)
    {
        action = new Object();
        action.type = "activerPrise";
        var index= $("#prise")[0].selectedIndex;
        var optionSelected = $("#prise")[0].options[index];
        action.id = optionSelected.value;
        newRegle.actions.push(action);
        
    }
    else if (desactiverPrise)
    {
        action = new Object();
        action.type = "desactiverPrise";
        var index= $("#prise")[0].selectedIndex;
        var optionSelected = $("#prise")[0].options[index];
        action.id = optionSelected.value;
        newRegle.actions.push(action);
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
