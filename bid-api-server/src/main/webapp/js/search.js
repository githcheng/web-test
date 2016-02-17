//初始化搜索框
function fnSeaTxtInit(){
    //搜索框添加事件
    var txtSearch = fnGetEleById("txtSearch");
    //搜索框初始化值    
    if (txtSearch) {
    	var _ctn=GetQueryStringPar("ctn");
    	if(_ctn){txtSearch.value=_ctn;}        
        addEvent(txtSearch,"focus", function(){
        	if (txtSearch.value == "请输入您要查找的内容") {
		        txtSearch.value = "";
		    }
        });
        
        addEvent(txtSearch,"blur", function(){
        	if (txtSearch.value == "") {
		        txtSearch.value = "请输入您要查找的内容";
		    }
        });
    }   	
}
//初始化搜索按钮
function fnSeaBtnInit(){   
	var d=document;
	var st=d.getElementById("txtSearch");	
	var tp=GetQueryStringPar("type");
	if(st.value=="请输入您要查找的内容"||st.value==""){
		//alert("没有输入内容，查找所有");
	}
	if(tp=="search"){
    	GetWord(st.value);
    }
    else{
    	var vUrl = "searchLst.html?place="+parTagName+"&type=search&ctn="+st.value; 
    	window.open(vUrl);
    }	
}
window.onkeydown=function(e){
				var d=document;
				if(e.keyCode==13){
				   	var osearch=d.getElementById("txtSearch");
				   	if(osearch==d.activeElement){
				   		fnSeaBtnInit();
				   	}
				}				
            }
   
//搜索按钮点击事件
function fnSearchE(){
	//获取搜索的内容
	var d=document;
	var st=d.getElementById("txtSearch");
	if(st.innerText=="请输入您要查找的内容"){
		alert("没有输入内容，查找所有");
	}
}
//获取分词
function GetWord(ctn){
	if (ctn == null || ctn == 'undefined') {
		ctn = GetQueryStringPar("ctn");
	}
	var url="http://101.200.81.203:35412/daxiaobiao/_analyze?analyzer=ik&pretty=true&text="+ctn;
     GetJsonData(url,function(words){
     	fnGetData(0,ctn,words);
     });
}
function fnGetData(pageIndex,ctn,words) {
	var tpCode = GetQueryStringPar("type");
	//var ctn = GetQueryStringPar("ctn");

	var fr = document.getElementById("indexFrame") || window.top.document.getElementById("indexFrame");
	var pageSize = 20;
	if (tpCode == null || tpCode == "") {
		tpCode = 1
	}
	if (ctn == null || ctn == undefined || ctn == "") {
		ctn = " ";
	}
	//var ctn="招标";
	var data = [{
			"query": {
				"bool": {
					"should": [{
						"match": {
							"content": ctn //此处为搜索内容
						}
					}],
					"must": [{
						"term": {
							"type": 1 // 此处为搜索的类型，招标，中标，变更等，字段含义如常量wiki
						}
					}]
				}
			},
			"_source": [
				"id",
				"time",
				"title",
				"type",
				"content"
			],
			"from": 20*pageIndex, //此处为分页设置，如果size=20,那么 from 第一页：0；第二页：20；第三页：40
			"size": 20, //每页显示多少个
			"min_score": 0.1
		}];
	
		var vUrl = "http://101.200.81.203:35412/daxiaobiao/bid/_search";
		
		data = JSON.stringify(data);
		//data=null;
		GetJsonDataPost(vUrl, data, function(sJ) {
			console.log(sJ);
			var d=document;
			var total=sJ.hits.total;
		    if(total==0){
		      d.getElementById("NoResult").style["display"]="block";
		    }else{
		      d.getElementById("NoResult").style["display"]="none";
		    }
			
			var hits = sJ.hits.hits;
			var len = 170; //显示字符串的长度
			var dvCtn = window.top.document.getElementById("SearchCtn");
			dvCtn.innerHTML="";
			for (var i = 0; i < hits.length; i++) {
				var _source = hits[i]._source,
					_highlight = {};
				if (hits[i].highlight == null || typeof hits[i].highlight == "undefined") {
					_highlight = {
						content: [],
						title: []
					};
				} else {
					_highlight = hits[i].highlight;
				}
				var _div = "div",
					_sp = "span",
					_a = "a";
				var blc = fnCteEle(_div, null, "lstBlock", null);
	
				var tlePre = fnCteEle(_sp, null, "lstTitlePre", fnGetTpName("[" + _source.type + "]"));
				var tleTle = fnCteEle(_a, null, "lstTitle", _source.title);
				tleTle.href = "article.html?artID=" + hits[i]._id + "&artType=" + _source.type + "&type=" + _source.type + "";
				tleTle.title = _source.title;
				tleTle.target = "_blank";
				tleTle.innerHTML = fnSplitChars(_source.title, 20);
				var _tm = formatDate(_source.time,"yyyy-MM-dd") + "";
				var pbDate = fnCteEle(_a, null, "lstPbDate", _tm);
	
				var lstDetail = tleTle.cloneNode(); //= fnCteEle(_div, null, "lstDetail", combineArrCtn(_highlight.content));
				lstDetail.className = "lstDetail";
				var tCtn =_source.content ;//_highlight.content[0];
	
				if (getBytesCount(tCtn) > len) {
					tCtn = fnSplitStr(tCtn, len) + "...";
				}
				lstDetail.innerHTML = fnLightTxt(tCtn, words.tokens);
	
				//  var spCount = fnCteEle(_sp, null, "lstClick", 3);
	
				blc.appendChild(tlePre);
				blc.appendChild(tleTle);
				blc.appendChild(pbDate);
				blc.appendChild(lstDetail);
				dvCtn.appendChild(blc);;
			}			
			dvCtn.innerHTML = dvCtn.innerHTML;
			//
			var config = {
						total: total,
						size:20,
						curIndex:pageIndex,
						callBack:function(t){							
						//	getfundnotice();						
							fnGetData(t.curIndex,ctn, words);
						}
					}
			var pa1 = new pagination("pa1", config);
		});
}

