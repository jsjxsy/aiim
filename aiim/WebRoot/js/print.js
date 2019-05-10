//打印设置格式设置
var pageNumberContent = ' 页码:&p/&P';

/**
 * 页眉、页脚
 */
function HeaderFooter(viewPosition, viewContent) {
	//显示位置
	this.viewPosition = viewPosition;
	//显示类容
	this.viewContent = viewContent;
	
	//页眉设置
	this.setHeader = function(printPageSet) {
		var header_left = '';
		var header_center = '';
		var header_right = '';
		//设置页眉内容
		if(this.viewPosition == 'left') {
			header_left += this.viewContent;
		} else if(this.viewPosition == 'center') {
			header_center += this.viewContent;
		} else if(this.viewPosition == 'right') {
			header_right += this.viewContent;
		}
		//增加页码设置
		if(printPageSet.pageNumber == 'header_left') {
			header_left += pageNumberContent;
		} else if(printPageSet.pageNumber == 'header_center') {
			header_center += pageNumberContent;
		} else if(printPageSet.pageNumber == 'header_right') {
			header_right += pageNumberContent;
		}
		
		this.viewContent = '';
		var count = 0;
		//最后构建页眉代码
		if(header_left.length >= 1) {
			count++;
			this.viewContent = header_left+'&b';
		}
		if(header_center.length >= 1) {
			count++;
			this.viewContent += '&b'+header_center+'&b';
		}
		if(header_right.length >= 1) {
			count++;
			this.viewContent += '&b'+header_right;
		}
		
		if(count >= 2) {
			this.viewContent = this.viewContent.replaceAll('(&b)+', '&b');	
		} else {
			//如果只有一项内容 需要&b&b
			this.viewContent = this.viewContent.replaceAll('(&b)+', '&b&b');
		}
	}
	
	//页脚设置
	this.setFooter = function(printPageSet) {
		var footer_left = '';
		var footer_center = '';
		var footer_right = '';
		//设置页眉内容
		if(this.viewPosition == 'left') {
			footer_left = this.viewContent;
		} else if(this.viewPosition == 'center') {
			footer_center = this.viewContent;
		} else if(this.viewPosition == 'right') {
			footer_right = this.viewContent;
		}
		
		//增加页码设置
		if(printPageSet.pageNumber == 'footer_left') {
			footer_left += pageNumberContent;
		} else if(printPageSet.pageNumber == 'footer_center') {
			footer_center += pageNumberContent;
		} else if(printPageSet.pageNumber == 'footer_right') {
			footer_right += pageNumberContent;
		}
		
		var count = 0;
		this.viewContent = '';
		//最后构建页眉代码
		if(footer_left.length >= 1) {
			count++;
			this.viewContent = footer_left+'&b';
		}
		if(footer_center.length >= 1) {
			count++;
			this.viewContent += '&b'+footer_center+'&b';
		}
		if(footer_right.length >= 1) {
			count++;
			this.viewContent += '&b'+footer_right+'\t\t...';
		}
		if(count >= 2) {
			this.viewContent = this.viewContent.replaceAll('(&b)+', '&b');	
		} else {
			this.viewContent = this.viewContent.replaceAll('(&b)+', '&b&b');
		}
	}
}

/**
 * 打印页面设置对象
 */
function PrintPageSet(header, footer, margin_left, margin_top, margin_right, margin_bottom, 
			rowHeight,titleHeight, pageNumber, pageNumberContent, outlineBorder, tableName, titleId) {
	//页眉
	this.header = header;
	//页脚
	this.footer = footer;
	//左边距
	this.margin_left = margin_left;
	//上边距
	this.margin_top = margin_top;
	//右边距
	this.margin_right = margin_right;
	//下边距
	this.margin_bottom = margin_bottom;
	//行高
	this.rowHeight = rowHeight;
	//栏高
	this.titleHeight = titleHeight;
	//页码设置
	this.pageNumber = pageNumber;
	//页码值
	this.pageNumberContent = pageNumberContent;
	//设置表格外框
	this.outlineBorder = outlineBorder;
	//打印表格名称
	this.tableName = tableName;
	//目录标题id
	this.titleId = titleId
	
	this.setOutlineBorder = function() {
		$('#'+tableName).addClass('tableBorder_'+this.outlineBorder);
	}
	
	this.init = function() {
		//页眉设置
		header.setHeader(this);
		//页脚设置
		footer.setFooter(this);
		//控制打印 设置
		configPrintPage(this);
	}
}

//设置 行高与栏高
function setTableHeight(tableName, rowHeight, titleId, titleHeight) {
	//设置行高
	$('#'+tableName+' tr').each(function(){
		$(this).attr('height', rowHeight);
	});
	//设置标题栏高
	$('#'+titleId).css('height', titleHeight+'px');
}

/**
 * 设置网页打印的页眉页脚为空
 */
function removeDefaultHeader() { 
	var hkey_root,hkey_path,hkey_key;
	hkey_root="HKEY_CURRENT_USER";
	hkey_path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";
	try{
		var RegWsh = new ActiveXObject("WScript.Shell");
		hkey_key="header" ;
		RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"");
		hkey_key="footer";
		RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"");
		hkey_key="margin_left"; 
        RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"1");
        hkey_key="margin_top"; 
        RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"1");
        hkey_key="margin_right";
        RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"1");
        hkey_key="margin_bottom";
        RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"1");
	}
	catch(e){}
}

/**
 * 自定义设置网页打印的页眉页脚已经边距
 * 页边距1对应25.4mm，即margin_left=1表示实际值的25.4mm
 */
function configPrintPage(printPageSet) {
	var hkey_root,hkey_path,hkey_key;
	hkey_root="HKEY_CURRENT_USER";
	hkey_path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";
	try{
		var RegWsh = new ActiveXObject("WScript.Shell");
		hkey_key="header" ;
		RegWsh.RegWrite(hkey_root+hkey_path+hkey_key, printPageSet.header.viewContent);
		hkey_key="footer";
		RegWsh.RegWrite(hkey_root+hkey_path+hkey_key, printPageSet.footer.viewContent);
		hkey_key="margin_left"; 
        RegWsh.RegWrite(hkey_root+hkey_path+hkey_key, printPageSet.margin_left);
        hkey_key="margin_top"; 
        RegWsh.RegWrite(hkey_root+hkey_path+hkey_key, printPageSet.margin_top);
        hkey_key="margin_right";
        RegWsh.RegWrite(hkey_root+hkey_path+hkey_key, printPageSet.margin_right);
        hkey_key="margin_bottom";
        RegWsh.RegWrite(hkey_root+hkey_path+hkey_key, printPageSet.margin_bottom);
	}
	catch(e){alert('configPrintPage ERROR!');}
}

/**
 * 默认 打印
 */
function printPage() {
	removeDefaultHeader();
	window.print();
}

//分页打印控制
function paginationPrint(pageHeight) {
	pageHeight = pageHeight - 50;
	//记录表格目前的高度
	var tableHeight = new Number(0);
	//循环tbody 获取表格的总宽度
	$('#printPage > tr').each(function(index){
		tableHeight = tableHeight + $(this).attr('offsetHeight');
		//控制每个表格每页高度、其中第一页高度小50(去除标题高度)
		if((tableHeight >= pageHeight && index>=1) || (index==0 && tableHeight>=(pageHeight-$('#catalogTitle').attr('offsetHeight')))) {
			//设置当列以下换页
			$(this).addClass('pageNext');
			//设置下一列加top边框
			$(this).addClass('trBorder');

			//清空表格目前的高度
			tableHeight = 0;
		}
	});
}

/**
 * 正则替换所有
 */
String.prototype.replaceAll = function(reg, replaceContent) {
	return this.replace(new RegExp(reg,'gm'),replaceContent);
}