package com.orifound.aiim.web.util;
import java.util.Map;

import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesInfoQueryCondition;
import com.orifound.aiim.entity.ArchivesTypeDataItem;
import com.orifound.aiim.entity.DataSourceItem;
import com.orifound.aiim.entity.EnumEditStyle;
import com.orifound.aiim.entity.EnumSystemDataItem;
import com.orifound.aiim.entity.FieldValue;
import com.orifound.aiim.entity.OfficialArchivesInfo;
import com.orifound.aiim.entity.OfficialArchivesInfoQueryCondition;
import com.orifound.aiim.entity.SystemInitializer;

/**
 * ��̬����html����
 * @author Administrator
 *
 */
public class GenerateHtmlCodeUtil {

	/**
	 * ���ݸ����Ĳ���dataItems���������ڲ�ѯ��HTML����
	 * @param dataItems
	 * @return
	 * @throws Exception 
	 */
	public  static String GenerateHtmlCode(Map<String, ArchivesTypeDataItem> dataItems,Map<String,ArchivesInfoQueryCondition> archivesInfoQueryConditions){
		String html = "";
		for (ArchivesTypeDataItem dataItem : dataItems.values()) { 

			int	width = dataItem.getQueryEditBoxWidth();
			int	height = dataItem.getQueryEditBoxHeight();
			String columnName = dataItem.getColumnName();
			String labelText = dataItem.getDisplayText();
			String minValue = "";
			String maxValue = "";
			String value = "";
			if(archivesInfoQueryConditions != null && archivesInfoQueryConditions.get(dataItem.getColumnName()) != null){
				minValue = archivesInfoQueryConditions.get(columnName).getMinValue();
				maxValue = archivesInfoQueryConditions.get(columnName).getMaxValue();
				value = archivesInfoQueryConditions.get(columnName).getValue();
			}

			
			html += "<tr>" +
					  "<td align=\"right\">"+labelText+"</td>" +
					  "<td align=\"left\">";
			
		    if(dataItem.getEditStyle()==EnumEditStyle.�ı���){//�ı���
		    	String input = "<input type=\"text\" name=\""+columnName+"\"";
				
				//�ж��Ƿ��г�������
		    	if(width>0){
		    		input+=" style=\"width:"+width+"px\"";
		    	}
		    	
		    	//�ж��Ƿ�������
		    	if(dataItem.getDataTypeID().length()>3 && "date".equals(dataItem.getDataTypeID().toLowerCase().substring(0, 4))){
		    		String oddInput = input;
	    			input += " id=\""+columnName+"1\" value=\""+minValue+"\"/>";
		    		input += "<img style=\"margin-right:20px;\" src=\"images/dropdownTime.gif\" onclick=\"PopUpCalendar('"+columnName+"1',true)\""; 
	    			input += " /> �� "+ oddInput +" id=\""+columnName+"2\"  value=\""+maxValue+"\"/>";
		    		input += "<img style=\"margin-right:20px;\" src=\"images/dropdownTime.gif\" onclick=\"PopUpCalendar('"+columnName+"2',true)\"";
		    	}else{
		    		input += " id=\""+columnName+"\" value=\""+value+"\"";
		    	}
		    	input += "/>";
		    	html += input;
		    }
		    
			if(dataItem.getEditStyle() == EnumEditStyle.�����б��){//�����б��
				String select = "<select name=\""+columnName+"\"";
				
				//�ж��Ƿ��г�������
		    	if(width>0){
		    		select+=" style=\"width:"+width+"px;\">";
		    	}
		    	//�õ�����Դ
		    	select += "<option value=\"\">��ѡ��</option>";
		    	Map<Integer, DataSourceItem> dataSourceItems = null;
		    	try{

		    		dataSourceItems = SystemInitializer.getInstance().getDataSources().get(dataItem.getDataSourceID()).getDataSourceItems();

		    	}catch(Exception e){
		    		System.out.println("�õ�����Դʧ��");
		    	}
		    	//Map<Integer, DataSourceItem> dataSourceItems = getDataSourceItems();
		    	if(dataSourceItems != null){
		    		for (DataSourceItem dataSourceItem : dataSourceItems.values()) {
			    		if(!"".equals(value) && dataSourceItem.getID() == Integer.parseInt(value)){
			    			select += "<option value=\""+ dataSourceItem.getID() +"\" selected=\"selected\">"+ dataSourceItem.getName() +"</option>";
			    		}else{
			    			select += "<option value=\""+ dataSourceItem.getID() +"\">"+ dataSourceItem.getName() +"</option>";
			    		}
					}
		    	}
		    	select += "</select>";
		    	html += select;
		    }
			
			if(dataItem.getEditStyle() == EnumEditStyle.�ı���){//�ı���
				html+="<textarea name=\""+columnName+"\" style=\"height: "+ height +"px; width: "+ width +"px;\">"+value+"</textarea>";
			}
	       html+= "</td></tr>\n";
		}
		return html;
	}

	//��¼��̬����
	public  static String GenerateHtmlCode(Map<String, ArchivesTypeDataItem> dataItems,ArchivesInfo archivesInfo,Object obj){
		Map<String, FieldValue> rowFieldsValues =null;
		String html = "";
		if(archivesInfo != null){
			rowFieldsValues = archivesInfo.getRowFieldsValues();
			html += "<tr style=\"display:none;\"><td></td><td height=\"25px\">" +
			         "<input type=\"hidden\" title=\"clear\" name=\""+ EnumSystemDataItem.�ڲ����.getEnumValue() +"\" value=\""+archivesInfo.getNBXH()+"\" id=\"NBXH\">" +
			         "<input type=\"hidden\" title=\"clear\" name=\""+ EnumSystemDataItem.����������ڲ����.getEnumValue()+ "\" value=\""+archivesInfo.getParentNBXH()+"\" id=\"parentNBXH\">"+
			         "</td></tr>";
		}else{
			html += "<tr style=\"display:none;\"><td></td><td height=\"25px\">" +
			         "<input type=\"hidden\" title=\"clear\" name=\""+ EnumSystemDataItem.�ڲ����.getEnumValue() +"\" value=\"\" id=\"NBXH\">" +
			         "<input type=\"hidden\" title=\"clear\" name=\""+ EnumSystemDataItem.����������ڲ����.getEnumValue() + "\" value=\"\" id=\"parentNBXH\">"+
			         "</td></tr>";
		}
		
		int length = 0;
		
		int i = 0;
		for (ArchivesTypeDataItem dataItem : dataItems.values()) { 
			int height = dataItem.getInputEditBoxHeight();//����ؼ��ĸ�
			int	width = dataItem.getInputEditBoxWidth();//����ؼ��Ŀ�

			String columnName = dataItem.getColumnName();//����ؼ�������
			String labelText = " "+dataItem.getDisplayText();//����ؼ�ǰ���ı�
			int labelTextLength = labelText.length()*12;//����ؼ��ı��ĳ���
			int fullLength = labelTextLength + width;//����ؼ��ı��Ϳؼ����ܳ���
			boolean inputHoldFlag = dataItem.getInputHoldFlag();//��¼������־
			String value = "";//����ؼ���ֵ
			
			if(rowFieldsValues != null && rowFieldsValues.get(columnName) != null){
				value = rowFieldsValues.get(columnName).getValue();
			}else if(dataItem.getDefaultValue() != null){
				value = dataItem.getDefaultValue();
			}
			
			//����ǵ�һ��
			if (i == 0) {
				html += "<tr>" +
				  "<td align=\"right\" height=\"25px\">"+labelText+"</td>" +
				  "<td align=\"left\">";
			}else if(length+fullLength > 700){//���ܳ��ȼ��Ͽؼ��ĳ��ȴ���ҳ��涨�Ŀ��ʱ����һ��
				html+= "</td></tr>\n";

				html += "<tr>" +
						  "<td align=\"right\" height=\"25px\">"+labelText+"</td>" +
						  "<td align=\"left\">";
				length = 0;//ֵ����
			}else{
				html += labelText;
			}

		    if(dataItem.getEditStyle()==EnumEditStyle.�ı���){//�ı���
		    	String input = "<input type=\"text\" name=\""+columnName+"\"";
				
				//�ж��Ƿ��г�������
		    	if(width>0){
		    		input+=" style=\"width:"+width+"px\"";
		    	}
		    	
		    	//���������¼������
		    	if(!inputHoldFlag){
		    		input+=" title=\"clear\"";
		    	}
		    	
		    	//�ж��Ƿ�������
		    	if(dataItem.getDataTypeID().length()>3 && "date".equals(dataItem.getDataTypeID().toLowerCase().substring(0, 4))){
	    			input += " id=\""+columnName+"1\" value=\""+value+"\"/>";
		    		input += "<img style=\"margin-right:20px;\" src=\"images/dropdownTime.gif\" onclick=\"PopUpCalendar('"+columnName+"1',true)\""; 
		    	}else{
		    		input += " id=\""+columnName+"\" value=\""+value+"\"";
		    	}
		    	input += "/>";
		    	html += input;
		    }
		    
			if(dataItem.getEditStyle() == EnumEditStyle.�����б��){//�����б��
				String select = "<select name=\""+columnName+"\"";
				
				//�ж��Ƿ��г�������
		    	if(width>0){
		    		select+=" style=\"width:"+width+"px;\">";
		    	}
		    	
		    	//�õ�����Դ
		    	Map<Integer, DataSourceItem> dataSourceItems = SystemInitializer.getInstance().getDataSources().get(dataItem.getDataSourceID()).getDataSourceItems();
		    	//Map<Integer, DataSourceItem> dataSourceItems = getDataSourceItems();
		    	select += "<option value=\"\">��ѡ��</option>";
		    	for (DataSourceItem dataSourceItem : dataSourceItems.values()) {
		    		if(!"".equals(value) && value != null && dataSourceItem.getID() == Integer.parseInt(value)){
		    			select += "<option value=\""+ dataSourceItem.getID() +"\" selected=\"selected\">"+ dataSourceItem.getName() +"</option>";
		    		}else{
		    			select += "<option value=\""+ dataSourceItem.getID() +"\">"+ dataSourceItem.getName() +"</option>";
		    		}	
				}
		    	select += "</select>";
		    	html += select;
		    }
			
			if(dataItem.getEditStyle() == EnumEditStyle.�ı���){//�ı���
				html+="<textarea";
				//���������¼������
		    	if(!inputHoldFlag){
		    		html+=" title=\"clear\"";
		    	}
		    	html+=" name=\""+columnName+"\" style=\"height: "+ height +"px; width: "+ width +"px;\">"+value+"</textarea>";
			}
			length += fullLength;
			
			//��������һ��
			if(i == dataItems.values().size() - 1){
				html+= "</td></tr>\n";
			}
			i++;
		}
		return html;
	}
	
	/*public  static String GenerateHtmlCode(Map<String, ArchivesTypeDataItem> dataItems,ArchivesInfo archivesInfo,Object obj){
		Map<String, FieldValue> rowFieldsValues =null;
		String html = "";
		if(archivesInfo != null){
			rowFieldsValues = archivesInfo.getRowFieldsValues();
			html += "<tr style=\"display:none;\">" +
			         "<td height=\"25px\"></td>" +
			         "<td><input type=\"hidden\" name=\""+ EnumSystemDataItem.�ڲ����.getEnumValue() +"\" value=\""+archivesInfo.getNBXH()+"\" id=\"NBXH\">" +
			         "<input type=\"hidden\" name=\""+ EnumSystemDataItem.����������ڲ����.getEnumValue()+ "\" value=\""+archivesInfo.getParentNBXH()+"\" id=\"parentNBXH\">"+
			         "</td></tr>";
		}else{
			html += "<tr style=\"display:none;\">" +
			         "<td height=\"25px\"></td>" +
			         "<td><input type=\"hidden\" name=\""+ EnumSystemDataItem.�ڲ����.getEnumValue() +"\" value=\"\" id=\"NBXH\">" +
			         "<input type=\"hidden\" name=\""+ EnumSystemDataItem.����������ڲ����.getEnumValue() + "\" value=\"\" id=\"parentNBXH\">"+
			         "</td></tr>";
		}
		
		for (ArchivesTypeDataItem dataItem : dataItems.values()) { 
			int height = dataItem.getInputEditBoxHeight();
			int	width = dataItem.getInputEditBoxWidth();

			String columnName = dataItem.getColumnName();
			String labelText = dataItem.getDisplayText();
			String value = "";
			if(rowFieldsValues != null && rowFieldsValues.get(columnName) != null){
				value = rowFieldsValues.get(columnName).getValue();
			}else if(dataItem.getDefaultValue() != null){
				value = dataItem.getDefaultValue();
			}
			
			
			html += "<tr>" +
					  "<td align=\"right\" height=\"25px\">"+labelText+"</td>" +
					  "<td align=\"left\">";
			
		    if(dataItem.getEditStyle()==EnumEditStyle.�ı���){//�ı���
		    	String input = "<input type=\"text\" name=\""+columnName+"\"";
				
				//�ж��Ƿ��г�������
		    	if(width>0){
		    		input+=" style=\"width:"+width+"px\"";
		    	}
		    	
		    	//�ж��Ƿ�������
		    	if(dataItem.getDataTypeID().length()>3 && "date".equals(dataItem.getDataTypeID().toLowerCase().substring(0, 4))){
	    			input += " id=\""+columnName+"1\" value=\""+value+"\"/>";
		    		input += "<img style=\"margin-right:20px;\" src=\"images/dropdownTime.gif\" onclick=\"PopUpCalendar('"+columnName+"1',true)\""; 
		    	}else{
		    		input += " id=\""+columnName+"1\" value=\""+value+"\"";
		    	}
		    	input += "/>";
		    	html += input;
		    }
		    
			if(dataItem.getEditStyle() == EnumEditStyle.�����б��){//�����б��
				String select = "<select name=\""+columnName+"\"";
				
				//�ж��Ƿ��г�������
		    	if(width>0){
		    		select+=" style=\"width:"+width+"px;\">";
		    	}
		    	//�õ�����Դ
		    	Map<Integer, DataSourceItem> dataSourceItems = SystemInitializer.getInstance().getDataSources().get(dataItem.getDataSourceID()).getDataSourceItems();
		    	//Map<Integer, DataSourceItem> dataSourceItems = getDataSourceItems();
		    	select += "<option value=\"\">��ѡ��</option>";
		    	for (DataSourceItem dataSourceItem : dataSourceItems.values()) {
		    		if(!"".equals(value) && value != null && dataSourceItem.getID() == Integer.parseInt(value)){
		    			select += "<option value=\""+ dataSourceItem.getID() +"\" selected=\"selected\">"+ dataSourceItem.getName() +"</option>";
		    		}else{
		    			select += "<option value=\""+ dataSourceItem.getID() +"\">"+ dataSourceItem.getName() +"</option>";
		    		}	
				}
		    	select += "</select>";
		    	html += select;
		    }
			
			if(dataItem.getEditStyle() == EnumEditStyle.�ı���){//�ı���
				html+="<textarea name=\""+columnName+"\" style=\"height: "+ height +"px; width: "+ width +"px;\">"+value+"</textarea>";
			}
	       html+= "</td></tr>\n";
		}
		return html;
	}*/
	
	//������¼��̬����
	public  static String GenerateOfficialArchivesHtmlCode(Map<String, ArchivesTypeDataItem> dataItems,OfficialArchivesInfo officialArchivesInfo,Object obj){
		Map<String, FieldValue> rowFieldsValues =null;
		String html = "";
		if(officialArchivesInfo != null){
			rowFieldsValues = officialArchivesInfo.getRowFieldsValues();
			html += "<tr style=\"display:none;\">" +
			         "<td height=\"25px\"></td>" +
			         "<td><input type=\"hidden\" name=\""+ EnumSystemDataItem.�ڲ����.getEnumValue() +"\" value=\""+officialArchivesInfo.getNBXH()+"\" id=\"NBXH\">" +
			         "</td></tr>";
		}else{
			html += "<tr style=\"display:none;\">" +
			         "<td height=\"25px\"></td>" +
			         "<td><input type=\"hidden\" name=\""+ EnumSystemDataItem.�ڲ����.getEnumValue() +"\" value=\"\" id=\"NBXH\">" +
			         "</td></tr>";
		}
		
		for (ArchivesTypeDataItem dataItem : dataItems.values()) { 
			int height = dataItem.getInputEditBoxHeight();
			int	width = dataItem.getInputEditBoxWidth();

			String columnName = dataItem.getColumnName();
			String labelText = dataItem.getDisplayText();
			String value = "";
			if(rowFieldsValues != null && rowFieldsValues.get(columnName) != null){
				value = rowFieldsValues.get(columnName).getValue();
			}else if(dataItem.getDefaultValue() != null){
				value = dataItem.getDefaultValue();
			}
			
			
			html += "<tr>" +
					  "<td align=\"right\" height=\"25px\">"+labelText+"</td>" +
					  "<td align=\"left\">";
			
		    if(dataItem.getEditStyle()==EnumEditStyle.�ı���){//�ı���
		    	String input = "<input type=\"text\" name=\""+columnName+"\"";
				
				//�ж��Ƿ��г�������
		    	if(width>0){
		    		input+=" style=\"width:"+width+"px\"";
		    	}
		    	
		    	//�ж��Ƿ�������
		    	if(dataItem.getDataTypeID().length()>3 && "date".equals(dataItem.getDataTypeID().toLowerCase().substring(0, 4))){
	    			input += " id=\""+columnName+"1\" value=\""+value+"\"/>";
		    		input += "<img style=\"margin-right:20px;\" src=\"images/dropdownTime.gif\" onclick=\"PopUpCalendar('"+columnName+"1',true)\""; 
		    	}else{
		    		input += " id=\""+columnName+"1\" value=\""+value+"\"";
		    	}
		    	input += "/>";
		    	html += input;
		    }
		    
			if(dataItem.getEditStyle() == EnumEditStyle.�����б��){//�����б��
				String select = "<select name=\""+columnName+"\"";
				
				//�ж��Ƿ��г�������
		    	if(width>0){
		    		select+=" style=\"width:"+width+"px;\">";
		    	}
		    	//�õ�����Դ
		    	Map<Integer, DataSourceItem> dataSourceItems = SystemInitializer.getInstance().getDataSources().get(dataItem.getDataSourceID()).getDataSourceItems();
		    	//Map<Integer, DataSourceItem> dataSourceItems = getDataSourceItems();
		    	select += "<option value=\"\">��ѡ��</option>";
		    	for (DataSourceItem dataSourceItem : dataSourceItems.values()) {
		    		if(!"".equals(value) && value != null && dataSourceItem.getID() == Integer.parseInt(value)){
		    			select += "<option value=\""+ dataSourceItem.getID() +"\" selected=\"selected\">"+ dataSourceItem.getName() +"</option>";
		    		}else{
		    			select += "<option value=\""+ dataSourceItem.getID() +"\">"+ dataSourceItem.getName() +"</option>";
		    		}	
				}
		    	select += "</select>";
		    	html += select;
		    }
			
			if(dataItem.getEditStyle() == EnumEditStyle.�ı���){//�ı���
				html+="<textarea name=\""+columnName+"\" style=\"height: "+ height +"px; width: "+ width +"px;\">"+value+"</textarea>";
			}
	       html+= "</td></tr>";
		}
		return html;
	}
	
	/**���ĵ�����ѯ
	 * ���ݸ����Ĳ���dataItems���������ڲ�ѯ��HTML����
	 * @param dataItems
	 * @return ��ѯ��html����
	 * @throws Exception �쳣
	 */
	public  static String GenerateOfficialArchivesHtmlCode(Map<String, ArchivesTypeDataItem> dataItems,Map<String,OfficialArchivesInfoQueryCondition> officialArchivesInfoQueryConditions){
		String html = "";
		for (ArchivesTypeDataItem dataItem : dataItems.values()) { 
		
			int	width = dataItem.getQueryEditBoxWidth();
			int	height = dataItem.getQueryEditBoxHeight();
			String columnName = dataItem.getColumnName();
			String labelText = dataItem.getDisplayText();
			String minValue = "";
			String maxValue = "";
			String value = "";
			if(officialArchivesInfoQueryConditions != null && officialArchivesInfoQueryConditions.get(dataItem.getColumnName()) != null){
				minValue = officialArchivesInfoQueryConditions.get(columnName).getMinValue();
				maxValue = officialArchivesInfoQueryConditions.get(columnName).getMaxValue();
				value = officialArchivesInfoQueryConditions.get(columnName).getValue();
			}

			if(dataItem.getSystemDataItemType() == EnumSystemDataItem.�����γɲ��ű��){
				html += "<tr>" +
				  "<td align=\"right\" style=\"display:none\" />"+labelText+"</td>" +
				  "<td align=\"left\">";
			}else{
				html += "<tr>" +
				  "<td align=\"right\" />"+labelText+"</td>" +
				  "<td align=\"left\">";
			}
			
			
		    if(dataItem.getEditStyle()==EnumEditStyle.�ı���){//�ı���
		    	String input ="";
				if(dataItem.getSystemDataItemType()== EnumSystemDataItem.�����γɲ��ű��){
					input= "<input type=\"text\" name=\""+columnName+"\" type=\" hidden\"";
				}else{
					input = "<input type=\"text\" name=\""+columnName+"\"";
				}
		    	//String input = "<input type=\"text\" name=\""+columnName+"\"";
				
				//�ж��Ƿ��г�������
		    	if(width>0){
		    		input+=" style=\"width:"+width+"px\"";
		    	}
		    	
		    	//�ж��Ƿ�������
		    	if(dataItem.getDataTypeID().length()>3 && "date".equals(dataItem.getDataTypeID().toLowerCase().substring(0, 4))){
		    		String oddInput = input;
	    			input += " id=\""+columnName+"1\" value=\""+minValue+"\"/>";
		    		input += "<img style=\"margin-right:20px;\" src=\"images/dropdownTime.gif\" onclick=\"PopUpCalendar('"+columnName+"1',true)\""; 
	    			input += " /> �� "+ oddInput +" id=\""+columnName+"2\"  value=\""+maxValue+"\"/>";
		    		input += "<img style=\"margin-right:20px;\" src=\"images/dropdownTime.gif\" onclick=\"PopUpCalendar('"+columnName+"2',true)\"";
		    	}else{
		    		input += " id=\""+columnName+"\" value=\""+value+"\"";
		    	}
		    	input += "/>";
		    	html += input;
		    }
		    
			if(dataItem.getEditStyle() == EnumEditStyle.�����б��){//�����б��
				String select = "<select name=\""+columnName+"\"";
				
				//�ж��Ƿ��г�������
		    	if(width>0){
		    		select+=" style=\"width:"+width+"px;\">";
		    	}
		    	//�õ�����Դ
		    	select += "<option value=\"\">��ѡ��</option>";
		    	Map<Integer, DataSourceItem> dataSourceItems = null;
		    	try{
		    		dataSourceItems = SystemInitializer.getInstance().getDataSources().get(dataItem.getDataSourceID()).getDataSourceItems();
		    	}catch(Exception e){
		    		System.out.println("�õ�����Դʧ��");
		    	}
		    	//Map<Integer, DataSourceItem> dataSourceItems = getDataSourceItems();
		    	if(dataSourceItems != null){
		    		for (DataSourceItem dataSourceItem : dataSourceItems.values()) {
			    		if(!"".equals(value) && dataSourceItem.getID() == Integer.parseInt(value)){
			    			select += "<option value=\""+ dataSourceItem.getID() +"\" selected=\"selected\">"+ dataSourceItem.getName() +"</option>";
			    		}else{
			    			select += "<option value=\""+ dataSourceItem.getID() +"\">"+ dataSourceItem.getName() +"</option>";
			    		}
					}
		    	}
		    	select += "</select>";
		    	html += select;
		    }
			
			if(dataItem.getEditStyle() == EnumEditStyle.�ı���){//�ı���
				html+="<textarea name=\""+columnName+"\" style=\"height: "+ height +"px; width: "+ width +"px;\">"+value+"</textarea>";
			}
	       html+= "</td></tr>";
		}
		return html;
	}
	
	/**
	 * ���ݸ����Ĳ���dataItems���������ڲ�ѯ��HTML����
	 * @param dataItems
	 * @return
	 * @throws Exception 
	 */
	public  static String GenerateOfficialHtmlCode(Map<String, ArchivesTypeDataItem> dataItems,Map<String,ArchivesInfoQueryCondition> archivesInfoQueryConditions){
		String html = "";
		for (ArchivesTypeDataItem dataItem : dataItems.values()) { 

			int	width = dataItem.getQueryEditBoxWidth();//����ؼ���
			int	height = dataItem.getQueryEditBoxHeight();
			String columnName = dataItem.getColumnName();
			String labelText = dataItem.getDisplayText();
			String minValue = "";
			String maxValue = "";
			String value = "";
			if(archivesInfoQueryConditions != null && archivesInfoQueryConditions.get(dataItem.getColumnName()) != null){
				minValue = archivesInfoQueryConditions.get(columnName).getMinValue();
				maxValue = archivesInfoQueryConditions.get(columnName).getMaxValue();
				value = archivesInfoQueryConditions.get(columnName).getValue();
			}

			
			if(dataItem.getSystemDataItemType() == EnumSystemDataItem.�����γɲ��ű��){
				html += "<tr>" +
				  "<td align=\"right\" style=\"display:none\" />"+labelText+"</td>" +
				  "<td align=\"left\">";
			}else{
				html += "<tr>" +
				  "<td align=\"right\" />"+labelText+"</td>" +
				  "<td align=\"left\">";
			}
			
			  if(dataItem.getEditStyle()==EnumEditStyle.�ı���){//�ı���
			    	String input ="";
					if(dataItem.getSystemDataItemType()== EnumSystemDataItem.�����γɲ��ű��){
						input= "<input type=\"text\" name=\""+columnName+"\" type=\" hidden\"";
					}else{
						input = "<input type=\"text\" name=\""+columnName+"\"";
					}
		  
				
				//�ж��Ƿ��г�������
		    	if(width>0){
		    		input+=" style=\"width:"+width+"px\"";
		    	}
		    	
		    	//�ж��Ƿ�������
		    	if(dataItem.getDataTypeID().length()>3 && "date".equals(dataItem.getDataTypeID().toLowerCase().substring(0, 4))){
		    		String oddInput = input;
	    			input += " id=\""+columnName+"1\" value=\""+minValue+"\"/>";
		    		input += "<img style=\"margin-right:20px;\" src=\"images/dropdownTime.gif\" onclick=\"PopUpCalendar('"+columnName+"1',true)\""; 
	    			input += " /> �� "+ oddInput +" id=\""+columnName+"2\"  value=\""+maxValue+"\"/>";
		    		input += "<img style=\"margin-right:20px;\" src=\"images/dropdownTime.gif\" onclick=\"PopUpCalendar('"+columnName+"2',true)\"";
		    	}else{
		    		input += " id=\""+columnName+"\" value=\""+value+"\"";
		    	}
		    	input += "/>";
		    	html += input;
		    }
		    
			if(dataItem.getEditStyle() == EnumEditStyle.�����б��){//�����б��
				String select = "<select name=\""+columnName+"\"";
				
				//�ж��Ƿ��г�������
		    	if(width>0){
		    		select+=" style=\"width:"+width+"px;\">";
		    	}
		    	//�õ�����Դ
		    	select += "<option value=\"\">��ѡ��</option>";
		    	Map<Integer, DataSourceItem> dataSourceItems = null;
		    	try{

		    		dataSourceItems = SystemInitializer.getInstance().getDataSources().get(dataItem.getDataSourceID()).getDataSourceItems();

		    	}catch(Exception e){
		    		System.out.println("�õ�����Դʧ��");
		    	}
		    	//Map<Integer, DataSourceItem> dataSourceItems = getDataSourceItems();
		    	if(dataSourceItems != null){
		    		for (DataSourceItem dataSourceItem : dataSourceItems.values()) {
			    		if(!"".equals(value) && dataSourceItem.getID() == Integer.parseInt(value)){
			    			select += "<option value=\""+ dataSourceItem.getID() +"\" selected=\"selected\">"+ dataSourceItem.getName() +"</option>";
			    		}else{
			    			select += "<option value=\""+ dataSourceItem.getID() +"\">"+ dataSourceItem.getName() +"</option>";
			    		}
					}
		    	}
		    	select += "</select>";
		    	html += select;
		    }
			
			if(dataItem.getEditStyle() == EnumEditStyle.�ı���){//�ı���
				html+="<textarea name=\""+columnName+"\" style=\"height: "+ height +"px; width: "+ width +"px;\">"+value+"</textarea>";
			}
	       html+= "</td></tr>\n";
		}
		return html;
	}
}
