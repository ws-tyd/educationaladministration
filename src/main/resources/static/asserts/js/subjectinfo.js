$(function(){
		$('button').click(function(){
		var a = $("button").index(this);
		var b = $("button").eq(a);
		if($(b).html()==="发布"){
			$(b).text("取消发布");
		}else {
			$(b).text("发布");
		}
		var c = $("table").find("tr").eq(a).find("th").eq(1).html();
		alert(c);
	});
})


