$(function(){
	$("#version").focusout(function(){

		var version = $("#version").val();
		var id = $("#id").val();

		var urlMagic = "https://api.magicthegathering.io/" + version + "/cards" + id;

		$.ajax({
			url : urlMagic,
			type : "get",
			dataType : 	"json",
			sucess : function(data){
				console.log(data);
			
				$("#Name")data.name;

			}
		});

	});

});