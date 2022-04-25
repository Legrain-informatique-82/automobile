/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
// Store variables
$(document).ready(function() {

    // Store variables
			
    var accordion_head = $('.nav > li > a'),
    accordion_body = $('.nav li > .sub-menu');

    // Open the first tab on load

    //accordion_head.first().addClass('active').next().slideDown('normal');
    var url = window.location.toString();
    $('.nav > li > ul > li a').each(function(){
        var myHref = $(this).attr('href');
       // alert(myHref);
        if( url.match( myHref)) {
            $(this).addClass('active')
            $(this).closest('ul').show();
        }
    });

    // Click function

    accordion_head.on('click', function(event) {

        // Disable header links
				
        event.preventDefault();

        // Show and hide the tabs on click

        if ($(this).attr('class') != 'active'){
            accordion_body.slideUp('normal');
            $(this).next().stop(true,true).slideToggle('normal');
            accordion_head.removeClass('active');
            $(this).addClass('active');
        }

    });

});