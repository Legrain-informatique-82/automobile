var scrollSpeed = 70;
    
// set the default position
var current = 0;

// set the direction
var direction = 'h';

function bgscroll(){

    // 1 pixel row at a time
    current -= 1;

    // move the background with backgrond-position css properties
    $('#header-home').css("backgroundPosition", (direction == 'w') ? current+"px 0" : "0 " + current+"px");
    $('#header').css("backgroundPosition", (direction == 'w') ? current+"px 0" : "0 " + current+"px");

}

$(document).ready(function(){

  //Calls the scrolling function repeatedly
     setInterval(bgscroll, scrollSpeed);    
	
	/** Homepage lvl2 menu remove **/
  $("#products li .submenu").css('display', 'none');
	
	$('#spinner')
  .hide()  // hide
  .ajaxStart(function() {
      $(this).show();
  })
  .ajaxStop(function() {
      $(this).hide();
  });
  
  
  /*	LogIn div toggle	*/
  $('.login-btn, .close-btn').click(function(){
  	$('#login-div').slideToggle(200);
  });
  /*	LogIn div toggle	*/
	
	/* Sponsors color hover */	
	$("#sponsors-list li").hover(function(){
		$(this).find(".hover-img").stop().fadeIn(200);

	}, function(){							
		$(this).find(".hover-img").stop().fadeOut(200);
	});	
	/* Sponsors color hover */
	
	
	/* Input fields focus/blur */
	$('.input-clear').focus(function(){
		
		def = $(this).attr('default');		
		if($(this).val() == def)
		{
			$(this).val('');
		}
		
	});
	
	$('.input-clear').blur(function(){
		var val = $(this).val();
		
		if(val == "")
		{
			def = $(this).attr('default');
			$(this).val(def);
		}
	});
	/* Input fields focus/blur */
	
	
	/*	Items per page change	*/
	$('.perpage').change(function(){
		$(this).parent().submit();
	});
	/*	Items per page change	*/	
	
	
	/*	Apply for newsletter	*/
	$("#newsletter-btn").click(function(){
		var email = $("#newsletter").val();
		var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
		if(email != '' && emailReg.test(email))
		{
			$.ajax({
				type: "POST",
				dataType: 'json',
				url: base_url + "newsletter/apply_for_newsletter",
				data: {email:email},
				async: true,
				success: function(output){
					
						alert('Uspešno ste se prijavili');
										
				},
				error: function(output){				
																			
				} 				
			});
		}
		else
		{
			alert('Error!!!');
		}
		def = $("#newsletter").attr('default');			
		$("#newsletter").val(def);
	});	
	
	/*	Apply for newsletter	*/
	
	
	/*	Search form submit	*/
	
	$("#search-btn").click(function(){
		var term = $("#searchterm").val();
		var def = $("#searchterm").attr("default");
		
		if(def != term)
		{
			$("#searchform").submit();
		}
		
	});
	
	/*	Search form submit	*/
	
	
	/*	Submit your order	*/
	$("#orderbtn").live('click',function(){	
			$.ajax({
				type: "POST",
				dataType: 'json',
				url: base_url + "cart/order",
				data: {order:true},
				async: true,
				success: function(output){
					//alert("OUTPUT JE: " + output);
						if(output == '1')
						{
							$('#sent-div').fadeIn(200);
							$('#not-sent-div').fadeOut(200);
							update_cart();
							update_viewcart();
							
						}
						else
						{
							$('#not-sent-div').fadeIn(200);
							$('#sent-div').fadeOut(200);
						}										
				},
				error: function(output){				
							$('#not-sent-div').fadeIn(200);
							$('#sent-div').fadeOut(200);												
				} 				
			});	
			
			
		
	});	
	
	/*	Submit your order	*/
	
	
	/*	Submit your order	*/
	$(".responsediv").click(function(){
		$(this).fadeOut(100);
	});
	
	
	/*	Request new pass	*/	
	$("#request-pass-btn").live('click',function(){
	
	
		var email = $("#email").val();
		
				
		if(email != '')
		{						
			$.ajax({
				type: "POST",
				dataType: 'json',
				url: base_url + "members/send_reset_pass",
				data: {email:email},
				async: true,
				success: function(output){
					
					if(output == "1")
					{
						$('#error').fadeOut(200);
						$('#success').fadeIn(200);
						
					}
					else
					{
						$('#success').fadeOut(200);
						$('#error').fadeIn(200);
					}			
										
				},
				error: function(output){				
						alert('error');	
						$('#success').fadeOut(200);
						$('#error').fadeIn(200);						
				} 				
			});
			
		}
		
		$("#email").val('');
		/**/
		
	});
	
	/*	Request new pass	*/
	
	$("#change-password").click(function(){
		var newpass = $("#newpass").val();
		var newpass2 = $("#repeat-newpass").val();
		if(newpass == newpass2 && newpass != '')
			$("#change-pass-form").submit();
		else
			alert("The passwords dont match");
	});
	
	$("ul#products li a.active").parent().find('.submenu').css("display", "block");
	$("ul#products li .submenu li a.active").parent().parent().parent().css("display", "block");
	
});