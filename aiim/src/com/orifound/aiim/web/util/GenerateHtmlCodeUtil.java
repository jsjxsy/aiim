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
 * 动态生成html代码
 * @author Administrator
 *
 */
public class GenerateHtmlCodeUtil {

	/**
	 * 根据给定的参数dataItems，生成用于查询的HTML代码
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
			
		    if(dataItem.getEditStyle()==EnumEditStyle.文本框){//文本框
		    	String input = "<input type=\"text\" name=\""+columnName+"\"";
				
				//判断是否有长度限制
		    	if(width>0){
		    		input+=" style=\"width:"+width+"px\"";
		    	}
		    	
		    	//判断是否是日期
		    	if(dataItem.getDataTypeID().length()>3 && "date".equals(dataItem.getDataTypeID().toLowerCase().substring(0, 4))){
		    		String oddInput = input;
	    			input += " id=\""+columnName+"1\" value=\""+minValue+"\"/>";
		    		input += "<img style=\"margin-right:20px;\" src=\"images/dropdownTime.gif\" onclick=\"PopUpCalendar('"+columnName+"1',true)\""; 
	    			input += " /> 到 "+ oddInput +" id=\""+columnName+"2\"  value=\""+maxValue+"\"/>";
		    		input += "<img style=\"margin-right:20px;\" src=\"images/dropdownTime.gif\" onclick=\"PopUpCalendar('"+columnName+"2',true)\"";
		    	}else{
		    		input += " id=\""+columnName+"\" value=\""+value+"\"";
		    	}
		    	input += "/>";
		    	html += input;
		    }
		    
			if(dataItem.getEditStyle() == EnumEditStyle.下拉列表框){//下拉列表框
				String select = "<select name=\""+columnName+"\"";
				
				//判断是否有长度限制
		    	if(width>0){
		    		select+=" style=\"width:"+width+"px;\">";
		    	}
		    	//得到数据源
		    	select += "<option value=\"\">请选择</option>";
		    	Map<Integer, DataSourceItem> dataSourceItems = null;
		    	try{

		    		dataSourceItems = SystemInitializer.getInstance().getDataSources().get(dataItem.getDataSourceID()).getDataSourceItems();

		    	}catch(Exception e){
		    		System.out.println("得到数据源失败");
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
			
			if(dataItem.getEditStyle() == EnumEditStyle.文本域){//文本域
				html+="<textarea name=\""+columnName+"\" style=\"height: "+ height +"px; width: "+ width +"px;\">"+value+"</textarea>";
			}
	       html+= "</td></tr>\n";
		}
		return html;
	}

	//著录动态生成
	public  static String GenerateHtmlCode(Map<String, ArchivesTypeDataItem> dataItems,ArchivesInfo archivesInfo,Object obj){
		Map<String, FieldValue> rowFieldsValues =null;
		String html = "";
		if(archivesInfo != null){
			rowFieldsValues = archivesInfo.getRowFieldsValues();
			html += "<tr style=\"display:none;\"><td></td><td height=\"25px\">" +
			         "<input type=\"hidden\" title=\"clear\" name=\""+ EnumSystemDataItem.内部序号.getEnumValue() +"\" value=\""+archivesInfo.getNBXH()+"\" id=\"NBXH\">" +
			         "<input type=\"hidden\" title=\"clear\" name=\""+ EnumSystemDataItem.所属案卷的内部序号.getEnumValue()+ "\" value=\""+archivesInfo.getParentNBXH()+"\" id=\"parentNBXH\">"+
			         "</td></tr>";
		}else{
			html += "<tr style=\"display:none;\"><td></td><td height=\"25px\">" +
			         "<input type=\"hidden\" title=\"clear\" name=\""+ EnumSystemDataItem.内部序号.getEnumValue() +"\" value=\"\" id=\"NBXH\">" +
			         "<input type=\"hidden\" title=\"clear\" name=\""+ EnumSystemDataItem.所属案卷的内部序号.getEnumValue() + "\" value=\"\" id=\"parentNBXH\">"+
			         "</td></tr>";
		}
		
		int length = 0;
		
		int i = 0;
		for (ArchivesTypeDataItem dataItem : dataItems.values()) { 
			int height = dataItem.getInputEditBoxHeight();//输入控件的高
			int	width = dataItem.getInputEditBoxWidth();//输入控件的宽

			String columnName = dataItem.getColumnName();//输入控件的名称
			String labelText = " "+dataItem.getDisplayText();//输入控件前的文本
			int labelTextLength = labelText.length()*12;//输入控件文本的长度
			int fullLength = labelTextLength + width;//输入控件文本和控件的总长度
			boolean inputHoldFlag = dataItem.getInputHoldFlag();//著录保留标志
			String value = "";//输入控件的值
			
			if(rowFieldsValues != null && rowFieldsValues.get(columnName) != null){
				value = rowFieldsValues.get(columnName).getValue();
			}else if(dataItem.getDefaultValue() != null){
				value = dataItem.getDefaultValue();
			}
			
			//如果是第一条
			if (i == 0) {
				html += "<tr>" +
				  "<td align=\"right\" height=\"25px\">"+labelText+"</td>" +
				  "<td align=\"left\">";
			}else if(length+fullLength > 700){//当总长度加上控件的长度大于页面规定的宽度时另起一行
				html+= "</td></tr>\n";

				html += "<tr>" +
						  "<td align=\"right\" height=\"25px\">"+labelText+"</td>" +
						  "<td align=\"left\">";
				length = 0;//值清零
			}else{
				html += labelText;
			}

		    if(dataItem.getEditStyle()==EnumEditStyle.文本框){//文本框
		    	String input = "<input type=\"text\" name=\""+columnName+"\"";
				
				//判断是否有长度限制
		    	if(width>0){
		    		input+=" style=\"width:"+width+"px\"";
		    	}
		    	
		    	//如果不是著录保留项
		    	if(!inputHoldFlag){
		    		input+=" title=\"clear\"";
		    	}
		    	
		    	//判断是否是日期
		    	if(dataItem.getDataTypeID().length()>3 && "date".equals(dataItem.getDataTypeID().toLowerCase().substring(0, 4))){
	    			input += " id=\""+columnName+"1\" value=\""+value+"\"/>";
		    		input += "<img style=\"margin-right:20px;\" src=\"images/dropdownTime.gif\" onclick=\"PopUpCalendar('"+columnName+"1',true)\""; 
		    	}else{
		    		input += " id=\""+columnName+"\" value=\""+value+"\"";
		    	}
		    	input += "/>";
		    	html += input;
		    }
		    
			if(dataItem.getEditStyle() == EnumEditStyle.下拉列表框){//下拉列表框
				String select = "<select name=\""+columnName+"\"";
				
				//判断是否有长度限制
		    	if(width>0){
		    		select+=" style=\"width:"+width+"px;\">";
		    	}
		    	
		    	//得到数据源
		    	Map<Integer, DataSourceItem> dataSourceItems = SystemInitializer.getInstance().getDataSources().get(dataItem.getDataSourceID()).getDataSourceItems();
		    	//Map<Integer, DataSourceItem> dataSourceItems = getDataSourceItems();
		    	select += "<option value=\"\">请选择</option>";
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
			
			if(dataItem.getEditStyle() == EnumEditStyle.文本域){//文本域
				html+="<textarea";
				//如果不是著录保留项
		    	if(!inputHoldFlag){
		    		html+=" title=\"clear\"";
		    	}
		    	html+=" name=\""+columnName+"\" style=\"height: "+ height +"px; width: "+ width +"px;\">"+value+"</textarea>";
			}
			length += fullLength;
			
			//如果是最后一条
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
			         "<td><input type=\"hidden\" name=\""+ EnumSystemDataItem.内部序号.getEnumValue() +"\" value=\""+archivesInfo.getNBXH()+"\" id=\"NBXH\">" +
			         "<input type=\"hidden\" name=\""+ EnumSystemDataItem.所属案卷的内部序号.getEnumValue()+ "\" value=\""+archivesInfo.getParentNBXH()+"\" id=\"parentNBXH\">"+
			         "</td></tr>";
		}else{
			html += "<tr style=\"display:none;\">" +
			         "<td height=\"25px\"></td>" +
			         "<td><input type=\"hidden\" name=\""+ EnumSystemDataItem.内部序号.getEnumValue() +"\" value=\"\" id=\"NBXH\">" +
			         "<input type=\"hidden\" name=\""+ EnumSystemDataItem.所属案卷的内部序号.getEnumValue() + "\" value=\"\" id=\"parentNBXH\">"+
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
			
		    if(dataItem.getEditStyle()==EnumEditStyle.文本框){//文本框
		    	String input = "<input type=\"text\" name=\""+columnName+"\"";
				
				//判断是否有长度限制
		    	if(width>0){
		    		input+=" style=\"width:"+width+"px\"";
		    	}
		    	
		    	//判断是否是日期
		    	if(dataItem.getDataTypeID().length()>3 && "date".equals(dataItem.getDataTypeID().toLowerCase().substring(0, 4))){
	    			input += " id=\""+columnName+"1\" value=\""+value+"\"/>";
		    		input += "<img style=\"margin-right:20px;\" src=\"images/dropdownTime.gif\" onclick=\"PopUpCalendar('"+columnName+"1',true)\""; 
		    	}else{
		    		input += " id=\""+columnName+"1\" value=\""+value+"\"";
		    	}
		    	input += "/>";
		    	html += input;
		    }
		    
			if(dataItem.getEditStyle() == EnumEditStyle.下拉列表框){//下拉列表框
				String select = "<select name=\""+columnName+"\"";
				
				//判断是否有长度限制
		    	if(width>0){
		    		select+=" style=\"width:"+width+"px;\">";
		    	}
		    	//得到数据源
		    	Map<Integer, DataSourceItem> dataSourceItems = SystemInitializer.getInstance().getDataSources().get(dataItem.getDataSourceID()).getDataSourceItems();
		    	//Map<Integer, DataSourceItem> dataSourceItems = getDataSourceItems();
		    	select += "<option value=\"\">请选择</option>";
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
			
			if(dataItem.getEditStyle() == EnumEditStyle.文本域){//文本域
				html+="<textarea name=\""+columnName+"\" style=\"height: "+ height +"px; width: "+ width +"px;\">"+value+"</textarea>";
			}
	       html+= "</td></tr>\n";
		}
		return html;
	}*/
	
	//公文著录动态生成
	public  static String GenerateOfficialArchivesHtmlCode(Map<String, ArchivesTypeDataItem> dataItems,OfficialArchivesInfo officialArchivesInfo,Object obj){
		Map<String, FieldValue> rowFieldsValues =null;
		String html = "";
		if(officialArchivesInfo != null){
			rowFieldsValues = officialArchivesInfo.getRowFieldsValues();
			html += "<tr style=\"display:none;\">" +
			         "<td height=\"25px\"></td>" +
			         "<td><input type=\"hidden\" name=\""+ EnumSystemDataItem.内部序号.getEnumValue() +"\" value=\""+officialArchivesInfo.getNBXH()+"\" id=\"NBXH\">" +
			         "</td></tr>";
		}else{
			html += "<tr style=\"display:none;\">" +
			         "<td height=\"25px\"></td>" +
			         "<td><input type=\"hidden\" name=\""+ EnumSystemDataItem.内部序号.getEnumValue() +"\" value=\"\" id=\"NBXH\">" +
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
			
		    if(dataItem.getEditStyle()==EnumEditStyle.文本框){//文本框
		    	String input = "<input type=\"text\" name=\""+columnName+"\"";
				
				//判断是否有长度限制
		    	if(width>0){
		    		input+=" style=\"width:"+width+"px\"";
		    	}
		    	
		    	//判断是否是日期
		    	if(dataItem.getDataTypeID().length()>3 && "date".equals(dataItem.getDataTypeID().toLowerCase().substring(0, 4))){
	    			input += " id=\""+columnName+"1\" value=\""+value+"\"/>";
		    		input += "<img style=\"margin-right:20px;\" src=\"images/dropdownTime.gif\" onclick=\"PopUpCalendar('"+columnName+"1',true)\""; 
		    	}else{
		    		input += " id=\""+columnName+"1\" value=\""+value+"\"";
		    	}
		    	input += "/>";
		    	html += input;
		    }
		    
			if(dataItem.getEditStyle() == EnumEditStyle.下拉列表框){//下拉列表框
				String select = "<select name=\""+columnName+"\"";
				
				//判断是否有长度限制
		    	if(width>0){
		    		select+=" style=\"width:"+width+"px;\">";
		    	}
		    	//得到数据源
		    	Map<Integer, DataSourceItem> dataSourceItems = SystemInitializer.getInstance().getDataSources().get(dataItem.getDataSourceID()).getDataSourceItems();
		    	//Map<Integer, DataSourceItem> dataSourceItems = getDataSourceItems();
		    	select += "<option value=\"\">请选择</option>";
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
			
			if(dataItem.getEditStyle() == EnumEditStyle.文本域){//文本域
				html+="<textarea name=\""+columnName+"\" style=\"height: "+ height +"px; width: "+ width +"px;\">"+value+"</textarea>";
			}
	       html+= "</td></tr>";
		}
		return html;
	}
	
	/**公文档案查询
	 * 根据给定的参数dataItems，生成用于查询的HTML代码
	 * @param dataItems
	 * @return 查询的html代码
	 * @throws Exception 异常
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

			if(dataItem.getSystemDataItemType() == EnumSystemDataItem.档案形成部门编号){
				html += "<tr>" +
				  "<td align=\"right\" style=\"display:none\" />"+labelText+"</td>" +
				  "<td align=\"left\">";
			}else{
				html += "<tr>" +
				  "<td align=\"right\" />"+labelText+"</td>" +
				  "<td align=\"left\">";
			}
			
			
		    if(dataItem.getEditStyle()==EnumEditStyle.文本框){//文本框
		    	String input ="";
				if(dataItem.getSystemDataItemType()== EnumSystemDataItem.档案形成部门编号){
					input= "<input type=\"text\" name=\""+columnName+"\" type=\" hidden\"";
				}else{
					input = "<input type=\"text\" name=\""+columnName+"\"";
				}
		    	//String input = "<input type=\"text\" name=\""+columnName+"\"";
				
				//判断是否有长度限制
		    	if(width>0){
		    		input+=" style=\"width:"+width+"px\"";
		    	}
		    	
		    	//判断是否是日期
		    	if(dataItem.getDataTypeID().length()>3 && "date".equals(dataItem.getDataTypeID().toLowerCase().substring(0, 4))){
		    		String oddInput = input;
	    			input += " id=\""+columnName+"1\" value=\""+minValue+"\"/>";
		    		input += "<img style=\"margin-right:20px;\" src=\"images/dropdownTime.gif\" onclick=\"PopUpCalendar('"+columnName+"1',true)\""; 
	    			input += " /> 到 "+ oddInput +" id=\""+columnName+"2\"  value=\""+maxValue+"\"/>";
		    		input += "<img style=\"margin-right:20px;\" src=\"images/dropdownTime.gif\" onclick=\"PopUpCalendar('"+columnName+"2',true)\"";
		    	}else{
		    		input += " id=\""+columnName+"\" value=\""+value+"\"";
		    	}
		    	input += "/>";
		    	html += input;
		    }
		    
			if(dataItem.getEditStyle() == EnumEditStyle.下拉列表框){//下拉列表框
				String select = "<select name=\""+columnName+"\"";
				
				//判断是否有长度限制
		    	if(width>0){
		    		select+=" style=\"width:"+width+"px;\">";
		    	}
		    	//得到数据源
		    	select += "<option value=\"\">请选择</option>";
		    	Map<Integer, DataSourceItem> dataSourceItems = null;
		    	try{
		    		dataSourceItems = SystemInitializer.getInstance().getDataSources().get(dataItem.getDataSourceID()).getDataSourceItems();
		    	}catch(Exception e){
		    		System.out.println("得到数据源失败");
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
			
			if(dataItem.getEditStyle() == EnumEditStyle.文本域){//文本域
				html+="<textarea name=\""+columnName+"\" style=\"height: "+ height +"px; width: "+ width +"px;\">"+value+"</textarea>";
			}
	       html+= "</td></tr>";
		}
		return html;
	}
	
	/**
	 * 根据给定的参数dataItems，生成用于查询的HTML代码
	 * @param dataItems
	 * @return
	 * @throws Exception 
	 */
	public  static String GenerateOfficialHtmlCode(Map<String, ArchivesTypeDataItem> dataItems,Map<String,ArchivesInfoQueryCondition> archivesInfoQueryConditions){
		String html = "";
		for (ArchivesTypeDataItem dataItem : dataItems.values()) { 

			int	width = dataItem.getQueryEditBoxWidth();//输入控件的
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

			
			if(dataItem.getSystemDataItemType() == EnumSystemDataItem.档案形成部门编号){
				html += "<tr>" +
				  "<td align=\"right\" style=\"display:none\" />"+labelText+"</td>" +
				  "<td align=\"left\">";
			}else{
				html += "<tr>" +
				  "<td align=\"right\" />"+labelText+"</td>" +
				  "<td align=\"left\">";
			}
			
			  if(dataItem.getEditStyle()==EnumEditStyle.文本框){//文本框
			    	String input ="";
					if(dataItem.getSystemDataItemType()== EnumSystemDataItem.档案形成部门编号){
						input= "<input type=\"text\" name=\""+columnName+"\" type=\" hidden\"";
					}else{
						input = "<input type=\"text\" name=\""+columnName+"\"";
					}
		  
				
				//判断是否有长度限制
		    	if(width>0){
		    		input+=" style=\"width:"+width+"px\"";
		    	}
		    	
		    	//判断是否是日期
		    	if(dataItem.getDataTypeID().length()>3 && "date".equals(dataItem.getDataTypeID().toLowerCase().substring(0, 4))){
		    		String oddInput = input;
	    			input += " id=\""+columnName+"1\" value=\""+minValue+"\"/>";
		    		input += "<img style=\"margin-right:20px;\" src=\"images/dropdownTime.gif\" onclick=\"PopUpCalendar('"+columnName+"1',true)\""; 
	    			input += " /> 到 "+ oddInput +" id=\""+columnName+"2\"  value=\""+maxValue+"\"/>";
		    		input += "<img style=\"margin-right:20px;\" src=\"images/dropdownTime.gif\" onclick=\"PopUpCalendar('"+columnName+"2',true)\"";
		    	}else{
		    		input += " id=\""+columnName+"\" value=\""+value+"\"";
		    	}
		    	input += "/>";
		    	html += input;
		    }
		    
			if(dataItem.getEditStyle() == EnumEditStyle.下拉列表框){//下拉列表框
				String select = "<select name=\""+columnName+"\"";
				
				//判断是否有长度限制
		    	if(width>0){
		    		select+=" style=\"width:"+width+"px;\">";
		    	}
		    	//得到数据源
		    	select += "<option value=\"\">请选择</option>";
		    	Map<Integer, DataSourceItem> dataSourceItems = null;
		    	try{

		    		dataSourceItems = SystemInitializer.getInstance().getDataSources().get(dataItem.getDataSourceID()).getDataSourceItems();

		    	}catch(Exception e){
		    		System.out.println("得到数据源失败");
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
			
			if(dataItem.getEditStyle() == EnumEditStyle.文本域){//文本域
				html+="<textarea name=\""+columnName+"\" style=\"height: "+ height +"px; width: "+ width +"px;\">"+value+"</textarea>";
			}
	       html+= "</td></tr>\n";
		}
		return html;
	}
}
