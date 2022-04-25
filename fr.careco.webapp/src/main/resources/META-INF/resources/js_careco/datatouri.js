/**
 
    * http://stackoverflow.com/questions/4998908/convert-data-uri-to-file-then-append-to-formdata/5100158
 
    *
 
    *
 
    */
 
    function dataURItoBlob(dataURI, callback) {
 
        // convert base64 to raw binary data held in a string
 
        // doesn't handle URLEncoded DataURIs
 

 
        var byteString;
 
        if (dataURI.split(',')[0].indexOf('base64') >= 0) {
 
            byteString = atob(dataURI.split(',')[1]);
 
        } else {
 
            byteString = unescape(dataURI.split(',')[1]);
 
        }
 

 
        // separate out the mime component
 
        var mimeString = dataURI.split(',')[0].split(':')[1].split(';')[0];
 

 
        // write the bytes of the string to an ArrayBuffer
 
        var ab = new ArrayBuffer(byteString.length);
 
        var ia = new Uint8Array(ab);
 
        for (var i = 0; i < byteString.length; i++) {
 
            ia[i] = byteString.charCodeAt(i);
 
        }
 

 
        // write the ArrayBuffer to a blob, and you're done
 
        var BlobBuilder = window.WebKitBlobBuilder || window.MozBlobBuilder;
 
        var bb = new BlobBuilder();
 
        bb.append(ab);
 
        return bb.getBlob(mimeString);
 
    }
 

 
   function getAsJPEGBlob(canvas) {
 
        if(canvas.mozGetAsFile) {
 
            return canvas.mozGetAsFile("foo.jpg", "image/jpeg");
 
        } else {
 
            var data = canvas.toDataURL('image/jpeg', 0.7);
 
            var blob = dataURItoBlob(data);
 
            return blob;
 
        }
 
    }
 
