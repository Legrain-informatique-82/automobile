 $(document).ready(function() {
						   
	
    //for Caching
    var $content = $('#content');
	
    /*----------------------------------------------------------------------*/
    /* preload images
		/*----------------------------------------------------------------------*/
		
    //$.preload();
		
    /*----------------------------------------------------------------------*/
    /* Widgets
		/*----------------------------------------------------------------------*/
    //$content.find('accord.div').wl_Widget('set', 'collapsed:false');
    
    $content.find('div.widgets').wl_Widget();
    // open aaa widget
    
    var hasAAA = $("#hasAAA").val();
    if(hasAAA == 0){
        $("#widget_TABSINFOS").addClass("collapsed");
        $(".tab").attr("style", "display:none");
    }else{
        $("#widget_TABSINFOS").removeClass("collapsed");
        $(".tab").attr("style", "display:block");
    }
    var hasMonParts = $("#hasMonParts").val();
    if(hasMonParts === undefined || hasMonParts == 0){
        $("#widget_accordionResultat").addClass("collapsed");
        $("#resultsParts").attr("style", "display:none");
        var hasGlobalParts = $("#hasGlobalParts").val();
        if(hasGlobalParts === undefined || hasGlobalParts == 0){
            $("#widget_accordionResultat").addClass("collapsed");
            $("#resultsParts").attr("style", "display:none");
        }else{
            $("#widget_accordionResultat").removeClass("collapsed");
            $("#resultsParts").attr("style", "display:block");
            $('#resultsParts').accordion({
                //collapsible:true,
                autoHeight:false,
                active: 1
            });
        
        }
    }else{
        $("#widget_accordionResultat").removeClass("collapsed");
        $("#resultsParts").attr("style", "display:block");
        $('#resultsParts').accordion({
            //collapsible:true,
            autoHeight:false,
            active: 0
        });
        
        
    }
    
    var wl_Store = new $.wl_Store();
    var toStore = wl_Store.get('lastClickedStore');
   
    if(toStore != null && toStore['lastClicked'] == 0){
        $('#resultsParts').accordion({
            //collapsible:true,
            autoHeight:false,
            active: toStore['lastClicked']
        });
    }else if(toStore != null && toStore['lastClicked'] == 1){
        $('#resultsParts').accordion({
            //collapsible:true,
            autoHeight:false,
            active: toStore['lastClicked']
        });
    }
 
 /*----------------------------------------------------------------------*/
    /* Navigation Stuff
		/*----------------------------------------------------------------------*/
		
		
    //Top Pageoptions
    $('#wl_config').click(function(){
        var $pageoptions = $('#pageoptions');
        if($pageoptions.height() < 200){
            $pageoptions.animate({
                'height':200
            });
            $(this).addClass('active');
        }else{
            $pageoptions.animate({
                'height':20
            });
            $(this).removeClass('active');
        }
        return false;
    });
		
		
    //Header navigation for smaller screens
    var $headernav = $('ul#headernav');
		
    $headernav.bind('click',function(){
        //if(window.innerWidth > 800) return false;
        var ul = $headernav.find('ul').eq(0);
        (ul.is(':hidden')) ? ul.addClass('shown') : ul.removeClass('shown');
    });
		
    $headernav.find('ul > li').bind('click',function(event){
        event.stopPropagation();
        var children = $(this).children('ul');
			
        if(children.length){
            (children.is(':hidden')) ? children.addClass('shown') : children.removeClass('shown');
            return false;
        }
    });
	    //Main Navigation		
    var $nav = $('#van');
			
    $nav.delegate('li','click.wl', function(event){
        var _this = $(this),
        _parent = _this.parent(),
        a = _parent.find('a');
        _parent.find('ul').slideUp('fast');
        a.removeClass('active');
        _this.find('ul:hidden').slideDown('fast');
        _this.find('a').eq(0).addClass('active');
        event.stopPropagation();
    });
	
	    /*----------------------------------------------------------------------*/
	
    /*----------------------------------------------------------------------*/
    /* Helps to make current section active in the Mainbar
		/*----------------------------------------------------------------------*/
			
    var loc = location.pathname.replace(/\/([^.]+)\//g,'');
    var current = $nav.find('a[href="'+loc+'"]');
			
    if(current.parent().parent().is('#van')){
        current.addClass('active');
    }else{
        current.parent().parent().parent().find('a').eq(0).addClass('active').next().show();
        current.addClass('active');
	
    }
});
 
 $(document).ready(function() {
	  $("#cadre-menu").hover(function() {
	    $("#cadre-menu").animate({ marginRight: "497px"  }, 400 );
	  },function(){
	    $("#cadre-menu").animate({ marginRight: "0"  }, 300 );
	  });
	});