/**
 * Created by Administrator on 2015/10/31.
 */
/*根据ID获取元素*/
function fnGetEleById(eleId) {
	return document.getElementById(eleId);
}
/*获取属性ֵ*/
function fnGetAttr(obj, attrName) {
	var attrs = obj.attributes;
	for (var v in attrs) {
		if (attrName.toLowerCase() == attrs[v].name) {
			return attrs[v].value;
		}
	}
}
//绑定事件
function addEvent(elm, evType, fn, useCapture) {
	if (elm.addEventListener) {
		elm.addEventListener(evType, fn, useCapture);//DOM2.0
		return true;
	}
	else if (elm.attachEvent) {
		var r = elm.attachEvent('on' + evType, fn);//IE5+
		return r;
	}
	else {
		elm['on' + evType] = fn;//DOM 0
	}
}


//构建数组字符串
function combineArrCtn(arr) {
	var tempTxt = "";
	for (var i = 0; i < arr.length; i++) {
		tempTxt += arr[i];
	}
	return tempTxt;
}
//创建元素
function fnCteEle(eleName, id, clsName, val) {
	var ele = document.createElement(eleName);
	if (id != null) ele.id = id;
	if (clsName != null) ele.className = clsName;
	if (val != null) ele.innerHTML = val;
	return ele;
}
//获取字符的长度
function getBytesCount(str) {
	if (str == null) {
		return 0;
	} else {
		return (str.length + str.replace(/[\u0000-\u00ff]/g, "").length);
	}
}


var GetLength = function(str) {
	///<summary>获得字符串实际长度，中文2，英文1</summary>
	///<param name="str">要获得长度的字符串</param>
	var realLength = 0,
		len = str.length,
		charCode = -1;
	for (var i = 0; i < len; i++) {
		charCode = str.charCodeAt(i);
		if (charCode >= 0 && charCode <= 128) realLength += 1;
		else realLength += 2;
	}
	return realLength;
};

//js截取字符串，中英文都能用
//如果给定的字符串大于指定长度，截取指定长度返回，否者返回源字符串。
//字符串，长度

/**
 * js截取字符串，中英文都能用
 * @param str：需要截取的字符串
 * @param len: 需要截取的长度
 */
function fnSplitStr(str, len) {
	var str_length = 0;
	var str_len = 0;
	str_cut = new String();
	str_len = str.length;
	for (var i = 0; i < str_len; i++) {
		a = str.charAt(i);
		str_length++;
		if (escape(a).length > 4) {
			//中文字符的长度经编码之后大于4
			str_length++;
		}
		str_cut = str_cut.concat(a);
		if (str_length >= len) {
			str_cut = str_cut.concat("...");
			return str_cut;
		}
	}
	//如果给定字符串小于指定长度，则返回源字符串；
	if (str_length < len) {
		return str;
	}
}

//高亮显示
function fnLightTxt(str, keywords) {
	var reg,t=str;	
	for(var i=0;i<keywords.length;i++){
	 reg = new RegExp(keywords[i].token, "g");
	 t = t.replace(reg, '\<em\>' + keywords[i].token + '\</em\>');
	}
	//document.getElementById("tt").innerHTML = t;
	return t;
}

/*获取数据 json*/
function GetJsonDataPost(url, data, func) {
	$.ajax({
		type: "post",
		url: url,
		data: data,
		dataType: "application/json;charset=utf-8",
		dataType: "json",
		success: function(iJson) {
			var dataArr = eval(iJson);
			var tempHtml = "";
			if ((typeof func).toLocaleLowerCase() == "function") {
				func(iJson);
			}
		},
		error: function(e) {
			//alert("error");
		}
	});
}
/*获取数据 json*/
function GetJsonData(url, func) {
    $.ajax({
        type: "get",
        url: url,
        // url:"/DaXiaoBiao/lst.json?code=0",
        dataType: "json",
        success: function (iJson) {
            var dataArr = eval(iJson);
            var tempHtml = "";
            if ((typeof func).toLocaleLowerCase() == "function") {
                func(iJson);
            }
        },
        error: function (e) {
           // alert("error");
        }
    });
}
var formatDate = function(time, format) {
	var t = new Date(time);
	var tf = function(i) {
		return (i < 10 ? '0' : '') + i
	};
	return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function(a) {
		switch (a) {
			case 'yyyy':
				return tf(t.getFullYear());
				break;
			case 'MM':
				return tf(t.getMonth() + 1);
				break;
			case 'mm':
				return tf(t.getMinutes());
				break;
			case 'dd':
				return tf(t.getDate());
				break;
			case 'HH':
				return tf(t.getHours());
				break;
			case 'ss':
				return tf(t.getSeconds());
				break;
		};
	});
};
//   获取url参数
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) return decodeURI(r[2]);
	return null;
}
//   获取url参数
function GetQueryStringPar(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.top.location.search.substr(1).match(reg);
	if (r != null) return decodeURI(r[2]);
	return null;
}
/*截取字符*/
function fnSplitChars(str, len) {
    if (str.length < len) {
        return str;
    }
    return str.substr(0, len) + "";
}
//获取类型信息
function fnGetTpName(_index) {
	var arr = ["招标信息", "招标公告", "中标公告", "招标变更", "新闻资讯", "政策法规", "联系我们"];
	return _index < arr.length ? arr[_index] : "";
	/*
	 招标信息   1
	 招标公告   1
	 中标公告   2
	 招标变更   3
	 新闻资讯   4
	 政策法规   5
	 联系我们   6
	 */
}
