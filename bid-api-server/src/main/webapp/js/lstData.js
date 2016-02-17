/**
 * Created by Administrator on 2015/10/17.
 */

/* 各个类型列表请求
 * typeCode 各个类型编码
 * size  显示条数
 * conID 容器ID
 * */
function GetKindsData(typeCode, size, conID, len, IsPag, PageIndex) {
	var d=document;
    len = len == null || len == undefined ? 20*2 : len*2; //截取字符串的长度
    if (PageIndex == null || PageIndex == undefined) {
        PageIndex =0;
    }
    //dvZBBlock
    var vUrl = "/digest/getPageList?page=" + PageIndex + "&page_size=" + size + "&type=" + typeCode + "&min_id=-1&time=1991-05-07_12:00";
    GetJsonData(vUrl, function (data) {
        var dataArr=data.digest_list;   
        var total=data.total;
        if(total>0){
	        var vTempHtml = "  <ul>";
	        // li�
	        var tempLi = "";
	        $.each(dataArr, function (i, item) {
	            tempLi = tempLi + "  <li><span class=\"pre\">.</span>"              
	                + "  <a href=\"article.html?artID=" + item.id + "&artType=" + item.type + "&type=" + item.type  + "\" title=\"" + item.title + "\"  typeCode=\"" + typeCode + "\"target=\"_blank\">" + fnSplitStr(item.title, len) + "</a>"
	                +"<span class='spTime'>"
	                + (item.time+"").substr(5,5)               
	                +"</span>"
	                + "   </li>"
	        });
	        vTempHtml += tempLi + "</ul>";
	        if (IsPag) {
				var config = {
							total: total,
							size:20,
							curIndex:PageIndex-1,
							callBack:function(t){							
							//	getfundnotice();t.curIndex	typeCode, size, conID, len, IsPag, PageIndex					
								GetKindsData(typeCode, size, conID, len/2, IsPag, t.curIndex+1);
							}
						}
				var pa1 = new pagination("pa1", config);	
				
				if(total>0){
			      d.getElementById("NoResult").style["display"]="none";
			    }else{
			      d.getElementById("NoResult").style["display"]="block";
			    }
	        }	         
	        fnGetEleById(conID).innerHTML = vTempHtml;
	      }	     
    });
}
/*新闻资讯*/
function GetNewsHomeInit(typeCode, size, conID, len, IsPag, PageIndex) {
    len = len == null || len == undefined ? 20*2 : len*2; //截取字符串的长度
    if (PageIndex == null || PageIndex == undefined) {
        PageIndex = 1;
    }
    //dvZBBlock
    var vUrl = "/digest/getPageList?page=" + PageIndex + "&page_size=" + size + "&type=" + typeCode + "&min_id=-1&time=1991-05-07_12:00";
    GetJsonData(vUrl, function (data) {
        eval("dataArr=data.digest_list");       
        var vTempHtml = "  <ul class=\"word2\" >";
        // li�
        var tempLi = "";
        $.each(dataArr, function (i, item) {
            tempLi = tempLi + "  <li class=\"mt0\" style=\"opacity: 1;\">"
                + "  <a href=\"javascript:void(0)\" class='move' onclick=\"goPage(this)\" typeCode=\"" + typeCode + "\" tar=\"article.html?artID=" + item.id + "&artType=" + item.type + "\" title=\"" + item.title + "\" >" + fnSplitStr(item.title, len) + "</a>"
                + "   </li>"
        });
        vTempHtml += tempLi + "</ul>";       
        fnGetEleById(conID).innerHTML = vTempHtml;
    });
}
/*文章页请求*/
function GetArticle() {
    var id = GetQueryString("artID");
    var type = GetQueryString("artType");
    var url = "/detail/get?id=" + id + "&type=" + type;
    GetJsonData(url, function (data) {
    	var artHtml = "";
    	if(data.code==10001){artHtml=data.msg;}
    	else{
	         var json=data.info;
	         artHtml = ""
	            + "<div class=\"artTle\">"
	            + "  <h2 class=\"artTitle\">" + json.title + "</h2>"
	            + "  </div>"
	            + "  <div class=\"artBlock\">"
	            + "<div class=\"dvPubTime\"><span class=\"spPutTime\" >" + json.time + "</span></div>"
	            //+ "<div class=\"artBlockHead\">项目信息</div>"
	            + "<div class=\"artBlockBody artCont\" id=\"artCont\">"
	            + json.content
	            + "</div>"
	            + "      </div>";
      }
        fnGetEleById("artContent").innerHTML = artHtml;
    });
}