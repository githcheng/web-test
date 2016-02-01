/**
 * Created by Administrator on 2015/11/11.
 */
/*js�������*/
var newObj=function(config){
    this.id=config.id;
    this.class=config.class;
    this.name=config.name;
    this.pp=function(){
        alert(this.id);
    }
    //�ڲ��������ⲿ���ܵ���
    var pp2=function(){
        alert("pp2");
    }
   // pp2();
   this.dialog(this.id);
}
//
newObj.prototype={
    dialog:function(){
        alert(this.id);
    }
}
var config={id:1,class:'cls',name:'name'}
var config2={id:2,class:'cls',name:'name'}
var obj=new newObj(config);
//obj.dialog();
//newObj.pp();

var obj2=new newObj(config2);
//obj2.dialog();

/*
Object.prototype={
    alertMsg:function(){
        alert("MSG");
    }
}
var p=[];
p.alertMsg();
*/
Object.alertMsg=function(){
        alert("MSG");
    }

var p=[];
p.alertMsg();