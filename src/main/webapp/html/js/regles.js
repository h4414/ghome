/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function init(){
    alert('yolo');
}

$( "#ruleList" ).accordion();
$('#heure1').timepicker();
$('#heure2').timepicker();

$(document).on('load', init);

