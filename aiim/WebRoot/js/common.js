//显示隐藏查询窗口
function showfind(ob){
	var obj=document.getElementById("find");
	if (obj==null) {
		return false;
	}
	if (ob.alt=="显示查询(Q)")  {
		obj.style.display="";
		ob.alt="隐藏查询(Q)";
	}
	else {
		obj.style.display="none";
		ob.alt="显示查询(Q)";
	}
	return false;
}

//改变图片背景颜色
function changeImage(obj,img) {
	if (obj) {
		obj.src="images/"+img;
	}
}

//设置单击表格中某一行时改变该行的颜色
function selectRow(obj)
{	
	var preSelectObj = document.getElementById('preSelectRow');//获取存ID为preSelectRow对象（此对象存储上一个被选中行的ID）
	if(preSelectObj.value!=null&&preSelectObj.value!="")
	{
		//alert('!=1');
		var preSelectId = preSelectObj.value;//取preSelectRow对象中的VALUE值
		document.getElementById(preSelectId).className=obj.className;//将当前被选中的行的样式赋给上一被选中的行的样式
	}	
	//preSelectObj.className= obj.className;
	obj.className='selectRowColor';//改变被选中行的样式
	preSelectObj.value=obj.id;//保存当前被选中行的ID
}

/*翻页函数
  url 请求的url地址
  formName 查询条件的form
  currentPage 当前页
*/ 
function pageTurning(formName,currentPage){
    document.getElementById("currentPage").value=currentPage;
    document.forms[formName].submit();
}
/*
  跳到某一页
*/
function gotoPage(formName){
    var gotoPage = document.getElementById("gotoPage").value;
    pageTurning(formName,gotoPage);
}

//全选
function selectAll(obj){
   var inputs = document.getElementsByTagName("input")
   if(obj.checked==true){
   //alert(inputs.length);
      for(var i=0;i<inputs.length;i++){
        //alert('aa');
        if(inputs[i].type=="checkbox"){
           inputs[i].checked=true;
        }
      }
   }else{
      for(var i=0;i<inputs.length;i++){
        if(inputs[i].type=="checkbox"){
           inputs[i].checked=false;
        }
      }
   }
}

//验证时间正确性
function isDate(date) {
    var iaMonthDays = [31,28,31,30,31,30,31,31,30,31,30,31];
    var iaDate = new Array(3);
    var year, month, day;
    
       if (date=="") {
           alert("请输入日期，格式为（yyyy－mm-dd）！");
           return false;
       }
       iaDate = date.toString().split("-");
       if (iaDate.length != 3) {
           alert("输入格式不正确，格式为（yyyy－mm-dd）！");
           return false
       }
       if (iaDate[0].length > 4 || iaDate[1].length > 2 || iaDate[2].length > 2) {
           alert("输入格式不正确，格式为（yyyy－mm-dd）！");
           return false;
       }
       year = parseFloat(iaDate[0]);
       month = parseFloat(iaDate[1]);
       day=parseFloat(iaDate[2]);
       if (year < 1900 || year > 2100) {
           alert("年份超出范围（1900-2100）！");
           return false;
       }
       if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) iaMonthDays[1]=29;
       if (month < 1 || month > 12) {
           alert("月份输入错误（1-12）！");
           return false;
       }
       if (day < 1 || day > iaMonthDays[month - 1]) {
           alert("天数超出范围！");
           return false;
       }
       return true;
}
//打开弹出DIV
function opendiv()
{
	//var div11 = document.getElementById("div1");
	
	var div11=document.createElement("div11");
	div11.style.position="absolute";
	div11.style.visibility="visible";
	div11.style.top="0";
	div11.style.left="0";
	div11.style.filter="alpha(opacity=70)";
	div11.style.width="100%";
	div11.style.height="100%";
	div11.style.zIndex="10001";
	div11.style.background="#CCCCCC";
	div11.name="div11";
	div11.id="div11";
	div11.style.textAlign = "center";
	div11.style.verticalAlign = "middle";

	var div2 =  document.getElementById("divAddKC");
	div2.style.position="absolute";
	div2.style.visibility="visible";
	//div2.style.display="block";
	div2.style.top=30;
	//div2.style.left=30;
	div2.style.filter="alpha(opacity=100)";
	//div2.style.width="570px";
	//div2.style.height="289px";
	//div2.style.top=(document.body.clientHeight/2 -150);
	//div2.style.left=(document.body.clientWidth /2-320);
	div2.style.zIndex="10002";
	div2.style.background="#FFFFFF";
	div11.appendChild(div2);
	document.body.appendChild(div11);
	//document.body.appendChild(div2);
}
//关闭弹出div
function colseDiv()
{	
	var div11=document.getElementById("div11");
	document.body.removeChild(div11);
	var div2 =  document.getElementById("divAddKC");
	div2.style.visibility="hidden";
}
function addKC()
{
	opendiv();
}

//跟换提交按钮的背景图片
//改变图片背景颜色
function changeSubmitBgImage(obj,img) {
	if (obj) {
		obj.style.backgroundImage="url(images/"+img+")";
	}
}

//日期格式化,将js中的Date格式转化成yyyy-MM-dd格式
function getFormateDate(dat){
    return dat.getFullYear()+'-'+(1+dat.getMonth())+'-'+dat.getDate();
}

//去除字符串前后空格，用法：var aa = "space   ";aa = aa.trim();结果为:aa="space";去左/右空格同理
String.prototype.trim = function()    
{    
return this.replace(/(^\s*)|(\s*$)/g, "");    
}    

//去除字符串左空格
String.prototype.lTrim = function()    
{    
return this.replace(/(^\s*)/g, "");    
}    
    
//去除字符串右空格
String.prototype.rTrim = function()    
{    
return this.replace(/(\s*$)/g, "");    
} 


/**
 * 显示模态窗口
 */
function showWinModalDialog(url, width, height){
	window.showModalDialog(url,"newwindow","dialogWidth="+width+";dialogHeight="+height+";center=yes;scroll=no;status=no;resizable=no;help=no;location=no");
}

function showWinModalDialogScroll(url, width, height){
	window.showModalDialog(url,"newwindow","dialogWidth="+width+";dialogHeight="+height+";center=yes;scroll=yes;status=no;resizable=no;help=no;location=no");
}

/**
 * 比较数字大小
 * a>b 返回true 否则返回false
 */
function compareNumberSize(a, b) {
	if(isNaN(a)) a = 0;
    if(isNaN(b)) b = 0;
	return Number(a) > Number(b) ? true : false;
}