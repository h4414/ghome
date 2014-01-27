/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function init(){
   
    $( "#ruleList" ).accordion();
    $('#heure1').timepicker();
    $('#heure2').timepicker();
    
}

$(".presenceRuleSubmit").click(function(){
   ruleSettings = new Object();
   ruleSettings.type="intrusion";
   ruleSettings.time = new Object();
   ruleSettings.time.begin = ($("#heure1").val());
   ruleSettings.time.end = ($("#heure2").val()); 
   var jText = JSON.stringify(ruleSettings);
    
   /* $.ajax({
        url: 'http://localhost:8087/addrule',
        type: 'POST',
        contentType: "text/json;charset=UTF-8",
        data: jText,
        complete: function(xhr, status){
            alert("finished");
        },
        beforeSend: function(){
            alert("attention ...");
        }
        
    });*/
    var xmlHttp = null;

    xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "POST", "http://localhost:8087/addrule", false );
    xmlHttp.send( jText );
  
  
   
});


$(document).ready( init);

