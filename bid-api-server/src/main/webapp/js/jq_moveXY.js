/**
 * /*基于JQ的上下左右无间隙移动插件
 *作者 林吉
 *QQ 317365887
 *邮箱 317365887@qq.com 
*/    
(function($){
	$.fn.moveXY=function(options)
	{
		var opts=$.extend({}, $.fn.moveXY.defaults, options);
		return this.each(function()
		{
      	    var $this=$(this);
			var $li=$this.find(opts.targetObj),slideSize;
			if(opts.effect=="yToBottom" || opts.effect=="yToTop")
			{
				slideSize=$li.height();	
			}else{
					slideSize=$li.width();
					var _num=$li.length;
					$this.css("width",_num*slideSize);
				 }	 
			$.fn.moveXY.effects[opts.effect]($this,opts,$li,slideSize);		
		});
	};
	
	$.fn.moveXY.defaults=
	{
		targetObj:"li",//被控制对象
		preButtonID:"preButton",
		nextButtonID:"nextButton",
		autoMove:true,
		effect:"yToBottom",
		speed:1000,
		timer:3000
	};
	
	$.fn.moveXY.effects=
	{
		yToBottom:function($this,opts,$li,slideH){
			var doPlay=function()
			{
				if(!$this.is(":animated"))
				{
					$this.animate({"top":"+"+slideH},opts.speed,function()
					{
				   		var $cl =$this.find(opts.targetObj+":last").clone(true);
				   		$cl.css({opacity:0,filter:'alpha(opacity=0)'});
				   		$this.prepend($cl);	
				   		$this.css("top",0);
				   		$cl.animate({"opacity":1},opts.speed,function(){
				      		$this.find(opts.targetObj+":last").remove();
				   		});
					});			
				}				
			}//自动播放函数
			var autoPlay=setInterval(doPlay,opts.timer);
			$li.mouseenter(function(){
				if(autoPlay)
				{
					clearInterval(autoPlay);
				}
			}).mouseleave(function(){
				   if(autoPlay)
					{
						clearInterval(autoPlay);
					}
					autoPlay=setInterval(doPlay,opts.timer);
				});
		},
		yToTop:function($this,opts,$li,slideH){
			var doPlay=function()
			{
				if(!$this.is(":animated"))
				{
				  $this.animate({top: '-'+slideH},opts.speed,function(){
				  	 var $cl =$this.find(opts.targetObj+":first");
				   	 $cl.css({opacity:0,filter:'alpha(opacity=0)'});
				   	 $this.append($cl);
				     $this.css("top",0);
				     $cl.animate({"opacity":1},opts.speed);				   
				   }); 	
				}					
			}//自动播放函数
			var autoPlay=setInterval(doPlay,opts.timer);
			$li.mouseenter(function(){
				if(autoPlay)
				{
					clearInterval(autoPlay);
				}
			}).mouseleave(function(){
				   if(autoPlay)
					{
						clearInterval(autoPlay);
					}
					autoPlay=setInterval(doPlay,opts.timer);
				});
		},				
		xToLeft:function($this,opts,$li,slideW){
			var doPlay=function()
			{
				if(!$this.is(":animated"))
				{
					$this.animate({left:"-"+slideW},function(){
					$this.find(opts.targetObj+":first").appendTo($this);
						$this.css({"left":"0"});
					});
				}		
			}
			var autoPlay=setInterval(doPlay,opts.timer);
				$li.mouseenter(function(){
					if(autoPlay)
					{
						clearInterval(autoPlay);
					}
				}).mouseleave(function(){
				   if(autoPlay)
				   {
						clearInterval(autoPlay);
				   }
					autoPlay=setInterval(doPlay,opts.timer);
				});	
		},
		xToRight:function($this,opts,$li,slideW){
			var doPlay=function()
			{
				if(!$this.is(":animated"))
				{
				    $this.find(opts.targetObj+":last").prependTo($this);
					$this.css({"left":-slideW});
					$this.stop().animate({"left":0});
				}	
			}
			var autoPlay=setInterval(doPlay,opts.timer);
				$li.mouseenter(function(){
					if(autoPlay)
					{
						clearInterval(autoPlay);
					}
				}).mouseleave(function(){
				   if(autoPlay)
				   {
						clearInterval(autoPlay);
				   }
					autoPlay=setInterval(doPlay,opts.timer);
				});	
		},
		buttonClick:function($this,opts,$li,slideW){
			var $preButton=$('#'+opts.preButtonID);
			var $nextButton=$('#'+opts.nextButtonID);
			var isAuto=opts.autoMove;
			var prePlay=function()
			{
				if(!$this.is(":animated"))
				{
				    $this.find(opts.targetObj+":last").prependTo($this);
					$this.css({"left":-slideW});
					$this.stop().animate({"left":0});
				}	
			}
			var nextPlay=function()
			{
				if(!$this.is(":animated"))
				{
					$this.animate({left:"-"+slideW},function(){
					$this.find(opts.targetObj+":first").appendTo($this);
						$this.css({"left":"0"});
					});
				}		
			}
			if(isAuto){
				var doPlay=function()
				{
					if(!$this.is(":animated"))
					{
						$this.animate({left:"-"+slideW},function(){
						$this.find(opts.targetObj+":first").appendTo($this);
							$this.css({"left":"0"});
						});
					}		
				}
			
				var autoPlay=setInterval(doPlay,opts.timer);
				$li.mouseenter(function(){
					if(autoPlay)
					{
						clearInterval(autoPlay);
					}
				}).mouseleave(function(){
				   if(autoPlay)
				   {
						clearInterval(autoPlay);
				   }
					autoPlay=setInterval(doPlay,opts.timer);
				});
			}	
			$preButton.click(function(){
				prePlay();
				if(autoPlay)
				{
					clearInterval(autoPlay);
				}
				setTimeout(function(){
					autoPlay=setInterval(doPlay,opts.timer);
				},1000);
			})
			$nextButton.click(function(){
				nextPlay();
				if(autoPlay)
				{
					clearInterval(autoPlay);
				}
				setTimeout(function(){
					autoPlay=setInterval(doPlay,opts.timer);
				},1000);
			})
		}	
	};
})(jQuery); 


