function update_cart(){
	$.get(base_url + (language == "en" ? (language + "/") : "" ) + "cart/show_cart/" + language, function(cart){
		$("#cart").html(cart);
	});	
}

function update_viewcart(){
	//$.get(base_url + (language == "en" ? (language + "/") : "" ) + "cart/show_viewcart/" + language, function(cart){
	//	$("#cartview").fadeOut(100);
	//	$("#cartview").html('');
	//	$("#cartview").html(cart);
	//	$("#cartview").fadeIn(200);
	//	
	//});
$("#led-aaa").removeClass('led-green');
		$("#led-aaa").addClass('led-red');

}









function hide_cart(){
	var cart = $('#cart');    
  //cart.addClass("TEST");
  $("#led-aaa").removeClass('led-off');
$("#led-aaa").addClass('led-green');

  cart.animate({
    right: -300
  },100);
  $("#led-aaa").removeClass('led-red');
$("#led-aaa").addClass('led-off');

}

$(document).ready(function() { 
	
	$(".add-to-cart").click(function() {
		
		// Get the product ID and the quantity 
		//var id = $(this).parent().find('.id').val();
		var id = $(this).parent().find('.code').val();
		var qty = $(this).parent().find('.qty').val();
		var img = $(this).parent().find('.image').val();
		
		//alert(base_url +  (language == "en" ? (language + "/") : "" ) + "cart/add_cart_item/" + language);
		
		if(qty != '' && id != '')
		{						
			$.ajax({
				type: "POST",
				dataType: 'html',
				url: base_url +  (language == "en" ? (language + "/") : "" ) + "cart/add_cart_item/" + language,
				data: { product_id: id, quantity: qty, image: img, ajax: '1' },
				async: true,
				success: function(output){
					if(output == 'true')
					{    			
	    			update_cart();	
	    		}
	    		else
	    		{
	    			alert("Product does not exist");
	    		}	
				},
				error: function(output){				
					alert('error');							
				} 				
			});			
		}		
	});
	
	
	/*	DELETE ITEM	*/
	$(".delete-item").live('click',function() {
		
		// Get the product ID and the quantity 
		var rowid = $(this).parent().find('.prod_id').attr('id');
		var qty = '0';
				
		if(rowid != '' && qty != '')
		{				
			$.ajax({
				type: "POST",
				dataType: 'html',
				url: base_url + "cart/update_cart",
				data: { rowid: rowid, qty: qty, ajax: '1' },
				async: true,
				success: function(output){					 			
	    			update_cart();	    		
	    			update_viewcart();	    		
				},
				error: function(output){				
					alert('error');							
				} 				
			});			
		}	
			
	});
	
	/*	DELETE ITEM	*/
	
	/*	ROW HIDE ON DELETE	*/
	$(".button.delete-item").live('click',function() {
		$(this).parent().parent().fadeOut(1000);
	});
	/*	ROW HIDE ON DELETE	*/
	
	
	/*	CLEAR CART	*/
	$(".empty-cart").live('click',function() {
		$('#empty-gif').show();
		$.ajax({
			type: "POST",
			dataType: 'html',
			url: base_url + "cart/empty_cart",
			data: { },
			async: true,
			success: function(output){
				hide_cart();
				update_cart();
				update_viewcart();
			},
			error: function(output){				
				alert('error');							
			} 	
					
		});		
		//$('#empty-gif').hide();	
	});
	/*	CLEAR CART	*/
	
	
	$('.cart-info').live('click',function() {
    var cart = $(this).parent().parent();
    
    cart.animate({
      //right: parseInt(cart.css('right')) == 0 ? -300 : 0
      right: 0
    },100);
    
    $(this).removeClass('cart-info');
    $(this).addClass('cart-info-active');
    //alert(parseInt(cart.css('right')));
    
  });

  $('.cart-info-active').live('click',function() {
    var cart = $(this).parent().parent();
    
    cart.animate({
      right: -300
    },100);
    
    $(this).removeClass('cart-info-active');
    $(this).addClass('cart-info');
    //alert(parseInt(cart.css('right')));
    
  });		
	
});