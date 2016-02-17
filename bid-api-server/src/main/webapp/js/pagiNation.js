	//分页
			function pagination(id, config) {
				//总的   当前页   
				this.id = id;
				this.total = config.total; //总的数据量
				//当前页  默认0
			   if(config.curIndex){
			   	this.curIndex=config.curIndex;
			   }else{
			   	this.curIndex = 0;
			   }
				//首页
				this.firstIndex = 0;
				//下一页
				this.nextIndex = 1;
				//末页
				this.endIndex = config.total - 1;				
				//显示页数范围  默认6页
			    this.pageRange=6;
			    //默认显示条数
			    if(config.size){this.size=config.size;}
			    else{this.size=20;}			    
			    //总的页数
				this.pageTotal = Math.ceil(this.total/this.size);
				this.callBack=config.callBack;
				if(this.total<=this.size){
					document.getElementById(id).innerHTML="";
				}else{
					this.init();					
				}
			}
			pagination.prototype = {					
					setPagition:function(t){			
					//	var _d=document,_ele=_d.getElementById(id),_index=t.curIndex;
						var _d=document,id=t.id,_ele=_d.getElementById(id),_index=t.curIndex,pageTotal=t.pageTotal,pageRange=t.pageRange,halfPageRange=pageRange/2;
						_ele.innerHTML='';		
						
						var _startIndex=0,_endIndex=0,_rL=pageTotal-_startIndex;
						//如果小于分页栏要显示的数目，按照索引显示  total  10  pageRange 6  pageTotal-curIndex<6    pageRange*1.5>total>pageRange
						if(pageTotal<pageRange){
							_startIndex=0;
							_endIndex=pageTotal-1;
						}
						else {
							if(_index<halfPageRange){
								_startIndex=0;
								_endIndex=pageRange-1;
							}
							else if(pageTotal-_index<=pageRange){
								_startIndex=pageTotal-pageRange;
								_endIndex=pageTotal-1;
							}else{
								_startIndex=_index-halfPageRange+1;
								_endIndex=_startIndex+pageRange-1;
							}							
						}			
						var _spTotal=document.createElement("span");
						_spTotal.innerHTML="共有条"+t.total+"数据"
						_ele.appendChild(_spTotal);
						
						var a=_d.createElement("a");
						a.href="javascript:void(0)";
						a.id=id+"-first";
						a.innerHTML="首页";
						_ele.appendChild(a);
						
						var aPre=a.cloneNode();
						aPre.id=id+"-prev";
						aPre.innerHTML="上一页";						
						_ele.appendChild(aPre);
						
						for(var i=_startIndex;i<=_endIndex;i++){				
							var aNum=a.cloneNode(),_i=i+1;
							aNum.id=id+"-num-"+_i;
							aNum.innerHTML=_i;
							if(_index==i){
								aNum.className="On NavNow";//On  NavNow
							}
							_ele.appendChild(aNum);
						}	
						
						var aNext=a.cloneNode();
						aNext.id=id+"-next";
						aNext.innerHTML="下一页";
						_ele.appendChild(aNext);
						
						var aEnd=a.cloneNode();
						aEnd.id=id+"-end";
						aEnd.innerHTML="尾页";
						_ele.appendChild(aEnd);
						//回到顶端
						document.body.scrollTop=0;
					},
					init: function() {
						var d = document,_t= this,_boxId=_t.id;
						_t.setPagition(_t);							 
						var _m = d.getElementById(_t.id);
						_m.onclick = function(e) {						
							//_evt 事件源,_tar 源节点,_tp 点击类型,_curIndex 点击的当前页,_curEle 当前节点,_bCurIndex 点击之前的页面索引,_total=_t.total 数据总数
							var _evt,_tar,_tp,_curIndex,_curEle,_bCurIndex,_total=_t.total,_size=_t.size,_pageTotal=Math.ceil(_total/_size),_end=_pageTotal-1;
							
							//如果只有一页
							if(_pageTotal==1){return;}
							    _evt = e || window.event;
							    _tar = _evt.target||_evt.srcElement;
							    
							    _tp = _tar.id.split('-')[1];						 
							    _bCurIndex=_t.curIndex;
							if (_tp == "prev") {
								//_curIndex  >0    不是第一页，切换页码数字pgSeria的显示 ,  
								if(_bCurIndex>0){									
								 	_curIndex=_bCurIndex-1;
									_t.curIndex=_curIndex;
								}else{
									return false;
								}
							} else if (_tp == "next") {
								//不是最后一页
								if(_bCurIndex<_end){									
									_curIndex=_bCurIndex+1;
									_t.curIndex=_curIndex;
								}else{
									return false;
								}
							} else if (_tp == "first") {
								//当前页不是第一页								
								if(_bCurIndex!=0){									
									_curIndex=0;
									_t.curIndex=_curIndex;
								}else{
									return false;
								}
							} else if (_tp == "end") {
								//当前页不是最后一页
								if(_bCurIndex!=_end){									
									_curIndex=_end;
									_t.curIndex=_curIndex;
								}else{
									return false; 
								}
								
							} else if (_tp == "num") {
								//与点击之前不是同一页
								var _eleTxt=parseFloat(_tar.innerHTML);
								  if((_bCurIndex+1)==_eleTxt){return false;}
								  else{
								  	_curIndex=_eleTxt-1;
								  	_t.curIndex=_curIndex;
								  }
								  
							}
							if(typeof(_t.callBack)=='function'){
								//alert(_t.curIndex);
								_t.callBack(_t);
							}
							
						}
					}
				}
				function getfundnotice(){
					//t.setPagition(t.pageTotal,t.pageRange,t);
				}
			
				/*获取属性值*/
			function fnGetAttr(obj, attrName) {
				var attrs = obj.attributes;
				for (var v in attrs) {
					if (typeof attrs[v] != 'object') {
						continue;
					}
					if (attrName.toLowerCase() == attrs[v].name.toLowerCase()) {
						return attrs[v].value;
					}
				}
				return null;
			}