(function(w){
	w.easyPlugin = {
		"getString":function(){
			return w.plus.bridge.execSync( "HelloPlugin", "getString" );
		}
	};
})(window);