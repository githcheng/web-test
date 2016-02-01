/**
 * Created by Administrator on 2015/11/12.
 */
/*分站信息标签点击*/
var parTagName = "全国";//分区
var type=1;//类型
function fnClickParTab(e) {
    var evt=e||window.event;
    var tarA=evt.srcElement;
    if((tarA.className&&tarA.className=="more")||tarA.tagName.toLocaleLowerCase()!="a"){return;}
    else{
        parTagName = fnGetAttr(tarA,"title");
        //alert(parTagName);
    }   
}
/*搜索 tab标签类型*/
var tabNow = null;
function TabChange(self) {
    if (tabNow == null) {
        tabNow = document.getElementById("tab4");
    }
    tabNow.className = "";
    self.className = "tabLinkNow";
    //搜索框提示值
    var searIn = fnGetEleById("txtSearch");
    searIn.value = fnGetAttr(self, "tipInfo");
    //当前tab卡
    tabNow = self;
}
/*menu菜单点击效果*/
function menuClck(e) {
    var nav = fnGetEleById("navUl");
    var srcEle = e.srcElement;
    var as = nav.getElementsByTagName("a");
    for (var i in as) {
        var a = as[i];
        //添加，修改样式效果
        if (a == srcEle) {
         //   a.className = "menNow";
            //页面跳转
            var href = fnGetAttr(a, "tar");
           // setFrameSrc(href);
            window.top.location.href=href;
            //window.open(href);
        } else {
          //  a.className = "";
        }
    }
}
//初始化菜单选择
function menuTabSelInit(){
    var tpCode=GetQueryStringPar("type");

    var d=document;
    var id="nav"+tpCode;   
    var nav=d.getElementById(id);  
    if(nav){
       nav.className = "menNow";
    }
}
window.onload = function () {
	var tp=GetQueryStringPar("type");
  //  iFrameHeight();
    menuTabSelInit();
    //分站点击事件
    var parTabsCtn = fnGetEleById("sddm");
    if (parTabsCtn) {
    	addEvent(parTabsCtn,"click",function(e){
    		fnClickParTab(e);
    	})
        //parTabsCtn.addEventListener("click", fnClickParTab);
    }
    //菜单添加点击事件
    var navUl = fnGetEleById("navUl");
    if (navUl) {
      //  navUl.addEventListener("click", menuClck);
    }
    fnSeaTxtInit();
    //搜索按钮添加点击事件
   fnGetEleById("dvSearchIco").onclick=function(){fnSeaBtnInit();};    
}
/**/
var timeout = 500;
var closetimer = 0;
var ddmenuitem = 0;
function mopen(id) { // cancel close timer
	mcancelclosetime();
	// close old layer
	if (ddmenuitem) ddmenuitem.style.visibility = 'hidden';
	// get new layer and show it
	ddmenuitem = document.getElementById(id);
	ddmenuitem.style.visibility = 'visible';
}
// close showed layer
function mclose() {
	if (ddmenuitem) ddmenuitem.style.visibility = 'hidden';
}
// go close timer
function mclosetime() {
	closetimer = window.setTimeout(mclose, timeout);
}
// cancel close timer
function mcancelclosetime() {
	if (closetimer) {
		window.clearTimeout(closetimer);
		closetimer = null;
	}
}
// close layer when click-out
document.onclick = mclose;
