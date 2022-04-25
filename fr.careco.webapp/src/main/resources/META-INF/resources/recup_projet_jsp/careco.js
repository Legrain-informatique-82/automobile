var AjaxUrl = ("Ajax");

var popupWindow = null;

function child_open(url, title)
{

    popupWindow = window.open(url, title, 'height=780, width=1200, top=10, left=10, toolbar=no, menubar=non, location=no, resizable=yes, scrollbars=yes, status=no');

}
function parent_disable() {
    if (popupWindow && !popupWindow.closed)
        popupWindow.focus();
}

function ajax(url, params, callback) {
    $.ajax({
        url: url,
        data: params,
        success: callback,
        contentType: "application/x-www-form-urlencoded;charset=UTF-8"
    });
}

function updateModels(data) {
    var response = eval("(" + data + ")");
    var selectedModel = $("#model").val();

    $("#model").find('option:not(:first)').remove();

    if (response["models"] != null) {
        for (var i = 0; i < response["models"].length; i++) {
            $("#model").append($("<option value='" + response["models"][i]["id"] + "'>"
                    + response["models"][i]["name"] + "</option>"));
        }
    }

    if (selectedModel != null)
        $("#model").val(selectedModel).attr('selected', true);

}


function submitForm(name) {
    document.forms[name].submit();
}

function submitFormBuy() {
    var input = $("<input>").attr("type", "hidden").attr("name", "buy").val("buy");
    $('#reservePart').append($(input));
    document.forms["reservePart"].submit();
}


function viewMoreAAA() {
    $("#carAAA").hide();
    $("#carAAADetail").show('slow');
}

function viewLessAAA() {
    $("#carAAA").show('slow');
    $("#carAAADetail").hide('slow');
}

function showObservations(codeMotor, codeBoite, cnit, year, month, day) {
    var params = "op=observations&codeMotor=" + codeMotor + "&codeBoite=" + codeBoite + "&cnit=" + cnit;
    if (year !== undefined && month !== undefined && day !== undefined) {
        params += "&year=" + year;
        params += "&month=" + month;
        params += "&day=" + day;
    }
    //var params = "op=observations&codeMotor=" + "SUNNY-CD17-5V" + "&cnit=" + cnit;
    //console.log(params);
    ajax(AjaxUrl, params, function(data) {
        fillObservations(data, year, codeMotor);
    });
    /* $('html, body').animate({
     scrollTop: $("#observation").offset().top
     }, 200);*/
}

function fillObservations(data, year, codeMotor) {



}

function reloadParts(selector, mode) {
    var limit = $("#" + selector.id).val();
    var last = 0;
    if (selector.id == "limitMenuMonStock") {
        $("#qsMonLimit").val(limit);
        $("#textMonLimit").val(limit);
        $("#csMonLimit").val(limit);
        last = 0;
    } else if (selector.id == "limitMenuGlobalStock") {
        $("#qsGlobalLimit").val(limit);
        $("#textGlobalLimit").val(limit);
        $("#csGlobalLimit").val(limit);
        last = 1;
    }

    var wl_Store = new $.wl_Store();
    var toStore = {
        lastClicked: last
    }
    wl_Store.save('lastClickedStore', toStore);

    if (mode == "qs") {
        submitForm("searchParts");
    } else if (mode == "ts") {
        //$("#textLimit").val(limit);
        submitForm("searchPartsText");
    } else if (mode == "cs") {
        submitForm("searchPartsCode");
    }


}

function changePage(page, mode) {
    $("input[id=page]").val(page);
    if (mode == "qs") {
        document.forms["searchParts"].submit();
    } else if (mode == "ts") {
        document.forms["searchPartsText"].submit();
    }
}


function changePageInput() {
    $("input[id=page]").val(page);
    if (mode == "qs") {
        document.forms["searchParts"].submit();
    } else if (mode == "ts") {
        document.forms["searchPartsText"].submit();
    }
}

// navigation part details

function showGeneral() {
    $("#general").attr("style", "display:block");
    $("#admin").attr("style", "display:none");
    $("#voiture").attr("style", "display:none");
    $("#piece-details").attr("style", "display:none");
    $("#prixGarantie").attr("style", "display:none");
    $("#observations").attr("style", "display:none");
    $("#image").attr("style", "display:none");
    $("#reserveForm").attr("style", "display:none");
    $("#updatePart").attr("style", "display:none");
}

function showAdmin() {
    $("#general").attr("style", "display:none");
    $("#admin").attr("style", "display:block");
    $("#voiture").attr("style", "display:none");
    $("#piece-details").attr("style", "display:none");
    $("#prixGarantie").attr("style", "display:none");
    $("#observations").attr("style", "display:none");
    $("#image").attr("style", "display:none");
    $("#reserveForm").attr("style", "display:none");
    $("#updatePart").attr("style", "display:none");
}

function showVoiture() {
    $("#general").attr("style", "display:none");
    $("#admin").attr("style", "display:none");
    $("#voiture").attr("style", "display:block");
    $("#piece-details").attr("style", "display:none");
    $("#prixGarantie").attr("style", "display:none");
    $("#observations").attr("style", "display:none");
    $("#image").attr("style", "display:none");
    $("#reserveForm").attr("style", "display:none");
    $("#updatePart").attr("style", "display:none");
}

function showDetailsPiece() {
    $("#general").attr("style", "display:none");
    $("#admin").attr("style", "display:none");
    $("#voiture").attr("style", "display:none");
    $("#piece-details").attr("style", "display:block");
    $("#prixGarantie").attr("style", "display:none");
    $("#observations").attr("style", "display:none");
    $("#image").attr("style", "display:none");
    $("#reserveForm").attr("style", "display:none");
    $("#updatePart").attr("style", "display:none");
}

function showPrix() {
    $("#general").attr("style", "display:none");
    $("#admin").attr("style", "display:none");
    $("#voiture").attr("style", "display:none");
    $("#piece-details").attr("style", "display:none");
    $("#prixGarantie").attr("style", "display:block");
    $("#observations").attr("style", "display:none");
    $("#image").attr("style", "display:none");
    $("#reserveForm").attr("style", "display:none");
    $("#updatePart").attr("style", "display:none");
}


function showImage() {
    $("#general").attr("style", "display:none");
    $("#admin").attr("style", "display:none");
    $("#voiture").attr("style", "display:none");
    $("#piece-details").attr("style", "display:none");
    $("#prixGarantie").attr("style", "display:none");
    $("#observations").attr("style", "display:none");
    $("#image").attr("style", "display:block");
    $("#reserveForm").attr("style", "display:none");
    $("#updatePart").attr("style", "display:none");
}

function showReserve() {
    $("#general").attr("style", "display:none");
    $("#admin").attr("style", "display:none");
    $("#voiture").attr("style", "display:none");
    $("#piece-details").attr("style", "display:none");
    $("#prixGarantie").attr("style", "display:none");
    $("#observations").attr("style", "display:none");
    $("#image").attr("style", "display:none");
    $("#updatePart").attr("style", "display:none");
    $("#reserveForm").attr("style", "display:block");
}

function showUpdate() {
    $("#general").attr("style", "display:none");
    $("#admin").attr("style", "display:none");
    $("#voiture").attr("style", "display:none");
    $("#piece-details").attr("style", "display:none");
    $("#prixGarantie").attr("style", "display:none");
    $("#observations").attr("style", "display:none");
    $("#image").attr("style", "display:none");
    $("#reserveForm").attr("style", "display:none");
    $("#updatePart").attr("style", "display:block");
}

function createClientCl() {
    $("#clientCreate").attr("style", "display:block");
    $('html, body').animate({
        scrollTop: $("#clientCreate").offset().top
    }, 1000);
// $("#clientCreate").scroll();
}

// end navigation


function calculateFinalPrice() {
    var reg = /^\d*\.?\d*$/;

    var sellPrice = $("#sellPrice").val();
    var warrantyCost = $("#warrantyCost").val();
    var vat = $("#vat").val();
    var transportPrice = $("#transportPrice").val();
    var params = "op=calculatePrice&sellPrice=" + sellPrice + "&warrantyCost=" + warrantyCost + "&vat=" + vat + "&transportPrice=" + transportPrice;
    ajax(AjaxUrl, params, writePrice);



}

function writePrice(data) {
    var response = eval("(" + data + ")");
    $("#finalPrice").val(response["price"]);
}

function showAdc() {
    var params = "";
    ajax("AdcServlet", params, fillAdc);
}

function fillAdc(data) {
    if (data != null && data.length > 0) {
        var response = eval("(" + data + ")");
        $('#tecdoc').text(response["tecdoc"]);
        $('#makeAdc').text(response["make"]);
        $('#modelAdc').text(response["model"]);
        $('#trimAdc').text(response["trim"]);
        $('#powerAdc').text(response["power"] + " kW");
        $('#volumeAdc').text(response["volume"] + " cm3");
        $('#essence').text(response["essence"]);

        $('#yearFromAdc').text(response["yearFrom"]);
        $('#yearToAdc').text(response["yearTo"]);
        var m = "";
        for (var i = 0; i < response["moteur"].length; i++) {
            m += " " + response["moteur"][i];
            if (i < response["moteur"].length - 1) {
                m += " ,";
            }
        }
        $('#moteurAdc').text(m);
        var b = "";
        for (var j = 0; j < response["boites"].length; j++) {
            b += " " + response["boites"][j];
            if (j < response["boites"].length - 1) {
                b += " ,";
            }
        }
        $('#boiteAdc').text(b);
        $("#adcError").hide();
        $("#info").show();
    } else {
        $("#info").hide();
        $("#adcError").show();
    }
}

function openMoteurForm(motorCode, color) {
    if (color === 'ywllow') {
        $("#partCode").val(motorCode);
        $("#moteurType").val(motorCode);
        $("#moteurType").prop("disabled", true);
        $("#partType").val('');
    } else if (color === "green") {
        $("#partType").val(motorCode);
        $("#moteurType").val(motorCode);
        $("#moteurType").prop("disabled", true);
        $("#partCode").val('');
    }
    $("#expertise").attr("style", "display:block");
    $("#expertise").accordion({
        //collapsible:true,
        autoHeight: false,
        active: 1
    });
    ;
}

function openBoiteForm(boiteCode, color) {
    if (color === 'yellow') {
        $("#partCodeBoite").val(boiteCode);
        $("#partTypeBoiteF").val(boiteCode);
        $("#partTypeBoiteF").prop("disabled", true);
        $("#partTypeBoite").val('');
    } else if (color === "green") {
        $("#partTypeBoite").val(boiteCode);
        $("#partTypeBoiteF").val(boiteCode);
        $("#partTypeBoiteF").prop("disabled", true);
        $("#partCodeBoite").val('');
    }
    $("#expertise").attr("style", "display:block");
    $("#expertise").accordion({
        //collapsible:true,
        autoHeight: false,
        active: 3
    });
    ;
}
function insertMoteur() {
    var moteurType = $("#moteurType").val();
    var partCode = $("#partCode").val();
    var partType = $("#partType").val();
    var type = $("#type").val();
    var buyPrice = $("#buyPrice").val();
    var buyDate = $("#buyDate").val();
    var file = $("#file").val();
    var kilometrage = $("#kilometrage").val();
    var locationCode = $("#locationCode").val();
    var sellPrice = $("#sellPrice").val();
    var sellPriceCareco = $("#sellPriceCareco").val();
    var note = $("#note").val();
    var information = $("#information").val();
    var params = "moteurType=" + moteurType + "&partCode=" + partCode + "&partType=" + partType + "&type=" + type +
            "&buyPrice=" + buyPrice + "&buyDate=" + buyDate + "&file=" + file + "&kilometrage=" + kilometrage +
            "&locationCode=" + locationCode + "&sellPrice=" + sellPrice + "&sellPriceCareco=" + sellPriceCareco +
            "&note=" + note + "&information=" + information;
    ajax("AddPart", params, showMessageMoteur);
}

function showMessageMoteur(data) {

    var response = eval("(" + data + ")");
    $("#messageMoteur").html("<div class=\"alert success\">" + response["message"] + "</div>");
    $("#messageMoteur").show();
}

function insertBoite() {
    var moteurType = $("#moteurType").val();
    var partCode = $("#partCodeBoite").val();
    var partType = $("#partTypeBoite").val();
    var type = $("#typeBoite").val();
    var buyPrice = $("#buyPriceBoite").val();
    var buyDate = $("#buyDateBoite").val();
    var file = $("#fileBoite").val();
    var kilometrage = $("#kilometrageBoite").val();
    var locationCode = $("#locationCodeBoite").val();
    var sellPrice = $("#sellPriceBoite").val();
    var sellPriceCareco = $("#sellPriceCarecoBoite").val();
    var note = $("#noteBoite").val();
    var information = $("#informationBoite").val();
    var params = "moteurType=" + moteurType + "&partCode=" + partCode + "&partType=" + partType + "&type=" + type +
            "&buyPrice=" + buyPrice + "&buyDate=" + buyDate + "&file=" + file + "&kilometrage=" + kilometrage +
            "&locationCode=" + locationCode + "&sellPrice=" + sellPrice + "&sellPriceCareco=" + sellPriceCareco +
            "&note=" + note + "&information=" + information;
    ajax("AddPart", params, showMessageBoite);
}

function showMessageBoite(data) {

    var response = eval("(" + data + ")");
    $("#messageBoite").html("<div class=\"alert success\">" + response["message"] + "</div>");
    $("#messageBoite").show();
}

function showModal(panierId, partId, price, type) {
    $.prompt("Mettre Ã  jour les prix", price,
            function(price) {
                var params = "partId=" + partId + "&panierId=" + panierId + "&price=" + price + "&type=" + type;
                ajax("UpdatePrice", params, reloadPage);
                window.location.href = $("#url").val();
            },
            function() {

            });
}

function showModalDeletePart(partId) {
    $.confirm("Voulez-vous vraiment supprimer partie?",
            function(price) {
                var params = "partId=" + partId;
                ajax("DeletePart", params, reloadPageRechercez);
            },
            function() {

            });
}

function reloadPage() {
    window.location.href = 'panier.jsp?noImmatr=0';
}

function reloadPageRechercez() {
    window.location.href = 'rechercez.jsp';
}

function changeEmplacement(cb) {
    if (!cb.checked) {
        $("#locationCode").prop('disabled', true);
    } else {
        $("#locationCode").prop('disabled', false);
    }
}

function changeExport(cb) {
    if (!cb.checked) {
        $("#garantie").prop('disabled', true);
    } else {
        $("#garantie").prop('disabled', false);
    }
}

function changeEmplacementBoite(cb) {
    if (!cb.checked) {
        $("#locationCodeBoite").prop('disabled', true);
    } else {
        $("#locationCodeBoite").prop('disabled', false);
    }
}

function changeExportBoite(cb) {
    if (!cb.checked) {
        $("#garantieBoite").prop('disabled', true);
    } else {
        $("#garantieBoite").prop('disabled', false);
    }
}

function exportStock()
{
    var win = window.open('ExportStock', '_blank');
    win.focus();
}

function calculateCarecoPrix() {
    var price = $("#sellPrice").val();
    $("#carecoPrice").val(price - 0.3 * price);
}

function calculateCarecoPrixBoite() {
    var price = $("#sellPriceBoite").val();
    $("#carecoPriceBoite").val(price - 0.3 * price);
}


$(document).ready(function() {

    $('#make').change(function() {
        var params = "op=models&make=" + $('#make').val();
        ajax(AjaxUrl, params, updateModels);
    });

    /*if ($("#callAdc").val() == 1) {
     showAdc();
     
     }
     /* $( "#adcInfo" ).click(function() {
     showAdc();
     } );*/
    $('#loadingDiv').hide().ajaxStart(function() {
        $(this).show();  // show Loading Div
    }).ajaxStop(function() {
        $(this).hide(); // hide loading div
    });

    $('#info').hide().ajaxStart(function() {
        $(this).hide();  // show Loading Div
    }).ajaxStop(function() {
        $(this).show(); // hide loading div
    });
    $('#queryImmatriculation').on('keyup keypress blur change', function() {
        $("#lastTyped").val("0");
    });

    $('#queryVin').on('keyup keypress blur change', function() {
        $("#lastTyped").val("1");
    });

    $('#queryCnit').on('keyup keypress blur change', function() {
        $("#lastTyped").val("2");
    });

    $('#queryPolice').on('keyup keypress blur change', function() {
        $("#lastTyped").val("3");
    });

    $("#queryCnit").autocomplete({
        source: function(request, response) {
            $.ajax({
                url: "Ajax?op=cnitSuggestions",
                dataType: "json",
                data: {
                    cnit: request.term
                },
                success: function(data) {
                    var array = [];
                    array = data.suggestions;
                    response(array);
                }
            });
        },
        minLength: 1
    });

    $("#queryVin").autocomplete({
        source: function(request, response) {
            $.ajax({
                url: "Ajax?op=vinSuggestions",
                dataType: "json",
                data: {
                    vin: request.term
                },
                success: function(data) {
                    var array = [];
                    array = data.suggestions;
                    response(array);
                }
            });
        },
        minLength: 1
    });

    $("#typeMotor").autocomplete({
        source: function(request, response) {
            $.ajax({
                url: "Ajax?op=ajaxSugestions",
                dataType: "json",
                data: {
                    engineType: request.term
                },
                success: function(data) {
                    var array = [];
                    array = data.suggestions;
                    response(array);
                }
            });
        },
        minLength: 1
    });

    $("#typeBoite").autocomplete({
        source: function(request, response) {
            $.ajax({
                url: "Ajax?op=ajaxSugestions",
                dataType: "json",
                data: {
                    gearBoxType: request.term
                },
                success: function(data) {
                    var array = [];
                    array = data.suggestions;
                    response(array);
                }
            });
        },
        minLength: 1
    });

    $("#userTo").autocomplete({
        source: function(request, response) {
            $.ajax({
                url: "Ajax?op=mailSuggestions",
                dataType: "json",
                data: {
                    mailQuery: request.term
                },
                success: function(data) {
                    var array = [];
                    array = data.suggestions;
                    response(array);
                }
            });
        },
        minLength: 1
    });

    $("#queryPolice").autocomplete({
        source: function(request, response) {
            $.ajax({
                url: "Ajax?op=policeSuggestions",
                dataType: "json",
                data: {
                    queryPolice: request.term
                },
                success: function(data) {
                    var array = [];
                    array = data.suggestions;
                    response(array);
                }
            });
        },
        minLength: 1
    });

    $("#location").autocomplete({
        source: function(request, response) {
            $.ajax({
                url: "Ajax?op=locationSuggestions",
                dataType: "json",
                data: {
                    location: request.term
                },
                success: function(data) {
                    var array = [];
                    array = data.suggestions;
                    response(array);
                }
            });
        },
        minLength: 1
    });

    $("#locationCode").autocomplete({
        source: function(request, response) {
            $.ajax({
                url: "Ajax?op=locationSuggestions",
                dataType: "json",
                data: {
                    location: request.term
                },
                success: function(data) {
                    var array = [];
                    array = data.suggestions;
                    response(array);
                }
            });
        },
        minLength: 1
    });



    $("#clientDrop").change(function() {

        if ($("#clientDrop").val() === "-1") {
            $("#btoc").prop('disabled', false);
            $("#btob").prop('disabled', false);
            $("#lastName").prop('disabled', false);
            $("#firstName").prop('disabled', false);
            $("#companyName").prop('disabled', false);
            $("#company").prop('disabled', false);
            $("#phone").prop('disabled', false);
            $("#phoneComment").prop('disabled', false);
            $("#mobile").prop('disabled', false);
            $("#mobileComment").prop('disabled', false);
            $("#fax").prop('disabled', false);
            $("#carte").prop('disabled', false);

            $("#compte").prop('disabled', false);
            $("#email").prop('disabled', false);
            $("#addressF").prop('disabled', false);
            $("#addressL").prop('disabled', false);
        } else {
            $("#btoc").prop('disabled', true);
            $("#btob").prop('disabled', true);
            $("#lastName").prop('disabled', true);
            $("#firstName").prop('disabled', true);
            $("#companyName").prop('disabled', true);
            $("#company").prop('disabled', true);
            $("#phone").prop('disabled', true);
            $("#phoneComment").prop('disabled', true);
            $("#mobile").prop('disabled', true);
            $("#mobileComment").prop('disabled', true);
            $("#fax").prop('disabled', true);
            $("#carte").prop('disabled', true);

            $("#compte").prop('disabled', true);
            $("#email").prop('disabled', true);
            $("#addressF").prop('disabled', true);
            $("#addressL").prop('disabled', true);
        }
    });

    $("#companyName").keyup(function() {
        $("#company").prop("disabled", true);
    });

    $("#company").change(function() {
        if ($("#company").val() === "-1") {
            $("#companyName").prop("disabled", false);
        } else {
            $("#companyName").prop("disabled", true);
        }
    });

    $("#btoc").click(function() {
        $("#company").prop("disabled", true);
        $("#companyName").prop("disabled", true);
    });
    $("#btob").click(function() {
        $("#company").prop("disabled", false);
        $("#companyName").prop("disabled", false);
    });

    //$("#createClient").wl_Form();
    $("#updatePart").wl_Form();

    $("#updatePart").submit(function() {
        var url = "UpdatePart";
        $.ajax({
            type: "POST",
            url: url,
            data: $("#updatePart").serialize(), // serializes the form's elements.
            success: function(data)
            {
                $("#updateMessage").html(data);
            }
        });

        return false;
    });
    calculateCarecoPrix();

});
