/**
 * Created by Administrator on 2015/11/13.
 */
//登录框
function fnLoginBindOn(){
	var d=document,o_uname,o_pwd;
	    o_uname=d.getElementById("logForm-username");
	    o_pwd=d.getElementById("logForm-pwd");
	    o_uname.onfocus=function(){
	    	var intxt=o_uname.value;
	    	var tip=fnGetAttr(o_uname,"default-val");
	    	if(intxt==tip){
	    		o_uname.value="";
	    	}
	    }
	     o_uname.onblur=function(){
	    	var intxt=o_uname.value;
	    	var tip=fnGetAttr(o_uname,"default-val");
	    	if(intxt==""){
	    		o_uname.value=tip;
	    	}
	    }
	     o_pwd.onfocus=function(){
	    	var intxt=o_pwd.value;
	    	var tip=fnGetAttr(o_pwd,"default-val");
	    	if(intxt==tip){
	    		o_pwd.value="";
	    		o_pwd.type="password";
	    	}
	    	
	    }
	     o_pwd.onblur=function(){
	    	var intxt=o_pwd.value;
	    	var tip=fnGetAttr(o_pwd,"default-val");
	    	if(intxt==""){
	    		o_pwd.value=tip;
	    		o_pwd.type="text";
	    	}
	    }	
}
