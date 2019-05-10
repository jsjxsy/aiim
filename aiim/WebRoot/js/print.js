//��ӡ���ø�ʽ����
var pageNumberContent = ' ҳ��:&p/&P';

/**
 * ҳü��ҳ��
 */
function HeaderFooter(viewPosition, viewContent) {
	//��ʾλ��
	this.viewPosition = viewPosition;
	//��ʾ����
	this.viewContent = viewContent;
	
	//ҳü����
	this.setHeader = function(printPageSet) {
		var header_left = '';
		var header_center = '';
		var header_right = '';
		//����ҳü����
		if(this.viewPosition == 'left') {
			header_left += this.viewContent;
		} else if(this.viewPosition == 'center') {
			header_center += this.viewContent;
		} else if(this.viewPosition == 'right') {
			header_right += this.viewContent;
		}
		//����ҳ������
		if(printPageSet.pageNumber == 'header_left') {
			header_left += pageNumberContent;
		} else if(printPageSet.pageNumber == 'header_center') {
			header_center += pageNumberContent;
		} else if(printPageSet.pageNumber == 'header_right') {
			header_right += pageNumberContent;
		}
		
		this.viewContent = '';
		var count = 0;
		//��󹹽�ҳü����
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
			//���ֻ��һ������ ��Ҫ&b&b
			this.viewContent = this.viewContent.replaceAll('(&b)+', '&b&b');
		}
	}
	
	//ҳ������
	this.setFooter = function(printPageSet) {
		var footer_left = '';
		var footer_center = '';
		var footer_right = '';
		//����ҳü����
		if(this.viewPosition == 'left') {
			footer_left = this.viewContent;
		} else if(this.viewPosition == 'center') {
			footer_center = this.viewContent;
		} else if(this.viewPosition == 'right') {
			footer_right = this.viewContent;
		}
		
		//����ҳ������
		if(printPageSet.pageNumber == 'footer_left') {
			footer_left += pageNumberContent;
		} else if(printPageSet.pageNumber == 'footer_center') {
			footer_center += pageNumberContent;
		} else if(printPageSet.pageNumber == 'footer_right') {
			footer_right += pageNumberContent;
		}
		
		var count = 0;
		this.viewContent = '';
		//��󹹽�ҳü����
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
 * ��ӡҳ�����ö���
 */
function PrintPageSet(header, footer, margin_left, margin_top, margin_right, margin_bottom, 
			rowHeight,titleHeight, pageNumber, pageNumberContent, outlineBorder, tableName, titleId) {
	//ҳü
	this.header = header;
	//ҳ��
	this.footer = footer;
	//��߾�
	this.margin_left = margin_left;
	//�ϱ߾�
	this.margin_top = margin_top;
	//�ұ߾�
	this.margin_right = margin_right;
	//�±߾�
	this.margin_bottom = margin_bottom;
	//�и�
	this.rowHeight = rowHeight;
	//����
	this.titleHeight = titleHeight;
	//ҳ������
	this.pageNumber = pageNumber;
	//ҳ��ֵ
	this.pageNumberContent = pageNumberContent;
	//���ñ�����
	this.outlineBorder = outlineBorder;
	//��ӡ�������
	this.tableName = tableName;
	//Ŀ¼����id
	this.titleId = titleId
	
	this.setOutlineBorder = function() {
		$('#'+tableName).addClass('tableBorder_'+this.outlineBorder);
	}
	
	this.init = function() {
		//ҳü����
		header.setHeader(this);
		//ҳ������
		footer.setFooter(this);
		//���ƴ�ӡ ����
		configPrintPage(this);
	}
}

//���� �и�������
function setTableHeight(tableName, rowHeight, titleId, titleHeight) {
	//�����и�
	$('#'+tableName+' tr').each(function(){
		$(this).attr('height', rowHeight);
	});
	//���ñ�������
	$('#'+titleId).css('height', titleHeight+'px');
}

/**
 * ������ҳ��ӡ��ҳüҳ��Ϊ��
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
 * �Զ���������ҳ��ӡ��ҳüҳ���Ѿ��߾�
 * ҳ�߾�1��Ӧ25.4mm����margin_left=1��ʾʵ��ֵ��25.4mm
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
 * Ĭ�� ��ӡ
 */
function printPage() {
	removeDefaultHeader();
	window.print();
}

//��ҳ��ӡ����
function paginationPrint(pageHeight) {
	pageHeight = pageHeight - 50;
	//��¼���Ŀǰ�ĸ߶�
	var tableHeight = new Number(0);
	//ѭ��tbody ��ȡ�����ܿ��
	$('#printPage > tr').each(function(index){
		tableHeight = tableHeight + $(this).attr('offsetHeight');
		//����ÿ�����ÿҳ�߶ȡ����е�һҳ�߶�С50(ȥ������߶�)
		if((tableHeight >= pageHeight && index>=1) || (index==0 && tableHeight>=(pageHeight-$('#catalogTitle').attr('offsetHeight')))) {
			//���õ������»�ҳ
			$(this).addClass('pageNext');
			//������һ�м�top�߿�
			$(this).addClass('trBorder');

			//��ձ��Ŀǰ�ĸ߶�
			tableHeight = 0;
		}
	});
}

/**
 * �����滻����
 */
String.prototype.replaceAll = function(reg, replaceContent) {
	return this.replace(new RegExp(reg,'gm'),replaceContent);
}