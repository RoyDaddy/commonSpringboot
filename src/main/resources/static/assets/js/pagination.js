//페이징처리
function pagination(searchVO, loadFunction){
	var html = '';
	
	if(searchVO.totPage > 1){
		if(searchVO.page > 1){
			html += '<a href="javascript:movePage(1,\'' + loadFunction + '\')"><<</a>' +
					'<a href="javascript:movePage(' + (searchVO.page-1) + ',\'' + loadFunction + '\')"><</a>';
		}
		
		for(var i=searchVO.pageStart; i<searchVO.pageEnd+1; i++){
			if(i == searchVO.page){
				html += '<a href="javascript:void(0);" class="on">'+i+'</a>';
			}else{
				html += '<a href="javascript:movePage(' + i + ',\'' + loadFunction + '\')">'+i+'</a>';
			}
		}
		
		if(searchVO.totPage > searchVO.page){
			html += '<a href="javascript:movePage(' + (searchVO.page+1) + ',\'' + loadFunction + '\')">></a>'+
					'<a href="javascript:movePage(' + searchVO.totPage + ',\'' + loadFunction + '\')">>></a>';
		}
	}
	$("div.paginate").html(html);
}

//페이지클릭
function movePage(page, loadFunction){
	searchVO['page'] = page;
	eval(loadFunction);
}