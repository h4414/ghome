/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function init()
{
        $("input[type=checkbox]").on("click", function(event) {
        event.stopPropagation();
    });
}
    
$(document).ready( init);

