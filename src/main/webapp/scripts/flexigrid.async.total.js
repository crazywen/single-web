function doAsyncTotal(g, p, url, params) {
	if(p.page > 1) {
		p.newp = 1;
		return;
	}
	
	if(p.total<p.rp){//只有一页，无需重计总数
		return;
	}
		
	$('.pPageStat', g.pDiv).html("统计总记录数中, 请稍候 ...");
	$('.pReload', g.pDiv).addClass('loading');
	g.loading = true;

	$.post(url, params, function(json){
		if(json.ret!=0){
			alert(json.msg);
		} else {
			p.total = json.data;
			p.pages = Math.ceil(p.total / p.rp);
			$('.pReload', this.pDiv).removeClass('loading');
			p.page = p.newp;
			p.newp = 1;
			g.loading = false;
			g.buildpager(); 
		}
	});
}