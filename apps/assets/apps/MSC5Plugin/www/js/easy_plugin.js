(function(w){
    w.easyPlugin = {
        "getString":function(){
            return w.plus.bridge.execSync( "HelloPlugin", "getString" );
        },
        "show":function( tip, okCB ) {
            var okID = w.plus.bridge.callbackId( okCB );
            w.plus.bridge.exec( "HelloPlugin", "show", [tip,okID] );
        }
    };
})(window);