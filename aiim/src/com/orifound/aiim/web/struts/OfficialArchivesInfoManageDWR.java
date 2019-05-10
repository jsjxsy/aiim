package com.orifound.aiim.web.struts;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

import com.opensymphony.xwork2.ActionSupport;
import com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService;
import com.orifound.aiim.bll.service.IOfficialArchivesInfoManageService;
import com.orifound.aiim.bll.util.ArchivesInfoFactory;
import com.orifound.aiim.bll.util.OfficialArchivesInfoFactory;
import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ArchivesTypeDataItem;
import com.orifound.aiim.entity.EnumArchivesInfoType;
import com.orifound.aiim.entity.EnumOfficialArchivesInfoTableType;
import com.orifound.aiim.entity.EnumSystemDataItem;
import com.orifound.aiim.entity.EnumWorkFlowStatus;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.FieldValue;
import com.orifound.aiim.entity.OfficialArchivesInfo;
import com.orifound.aiim.entity.OfficialArchivesType;
import com.orifound.aiim.entity.OfficialArchivesTypeSavedMapping;
import com.orifound.aiim.entity.SystemInitializer;
import com.orifound.aiim.entity.UserInfo;
import com.orifound.aiim.web.util.GenerateHtmlCodeUtil;

public class OfficialArchivesInfoManageDWR extends ActionSupport {
	private static final long serialVersionUID = 1L;
	static Log logger = LogFactory.getLog(IntegratedQueryAction.class);
	@Autowired
	private IOfficialArchivesInfoManageService officialArchivesInfoManageService = null;
	@Autowired
	private IArchivesInfoWorkingManageService archivesInfoWorkingManageService = null;
	
	/**
	 * 
	 * @param officialArchivesTypeId 公文档案类型
	 * @param NBXH 内部选后
	 * @param session httpsession
	 * @return 生成HTML代码
	 * @throws Exception 异常
	 */
	public String getHtmlCode(int officialArchivesTypeId,int NBXH,HttpSession session) throws Exception {
		System.out.println("----得到公文著录html代码---------------------------");
		System.out.println("officialArchivesTypeId: "+officialArchivesTypeId);
		System.out.println("NBXH: "+NBXH);
		
		boolean pFlag = true;
		ErrInfo pErrInfo = new ErrInfo();
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		String htmlCode = "";
		
		try {
			pErrPos = 1;
			Map<String, ArchivesTypeDataItem> dataItems = SystemInitializer.getInstance().getOfficialArchivesTypes().get(officialArchivesTypeId).getDataItemsForInput();
			if(dataItems == null){
				pErrPos = 2;
				pFlag = false;
				pErrInfo.getContent().append("此分类下可著录数据项为空！");
			}
			if(pFlag){
				pErrPos = 3;
				//点击添加
				if(NBXH==0){
					htmlCode = GenerateHtmlCodeUtil.GenerateOfficialArchivesHtmlCode(dataItems, null,null);
				}else{//双击编辑
					OfficialArchivesType officialArchiveType= SystemInitializer.getInstance().getOfficialArchivesTypes().get(officialArchivesTypeId);
					OfficialArchivesInfo officialArchivesInfo = new OfficialArchivesInfo(officialArchiveType);
					pFlag = officialArchivesInfoManageService.findOfficialArchivesInfoByNBXH(NBXH, officialArchiveType, officialArchivesInfo, pErrInfo);
					htmlCode = GenerateHtmlCodeUtil.GenerateOfficialArchivesHtmlCode(dataItems, officialArchivesInfo,null);
					}
				}
			if(!pFlag){
				pErrInfo.getContent().insert(0,"根据内部序号查找档案信息失败");
			}
		} catch (Exception e) {
			// 异常错误
			e.printStackTrace();
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		} finally {
			// 拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
				
				if(pErrInfo.getException() != null){
					htmlCode = pErrInfo.getContent().toString();
				}else{
					htmlCode = pErrInfo.toShortString();
				}
			}
		}
		return htmlCode;
	}
	
	/**
	 * 保存公文档案信息
	 * @param officialArchivesTypeId 保存档案类型编号
	 * @param formMap 表单Map集合
	 * @param session session对象
	 * @param application application对象
	 * @return NBXH 内部序号 (如需要删除刚添加的档案信息，要用到NBXH，而添加一个公文档案信息时并没有NBXH，故返回NBXH)
	 * @throws Exception
	 */
	public int saveOfficialArchivesInfo(int officialArchivesTypeId,Map<String,String> formMap,HttpSession session) throws Exception{
		System.out.println("-----保存公文档案信息-----------------------------------");
		System.out.println("officialArchivesTypeId: "+officialArchivesTypeId);
		System.out.println("formMap: "+formMap);
		
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		int NBXH = 0;
		int userId = 0;
		int formationDepartmentID=0;
		OfficialArchivesType officialArchivesType = null;
		OfficialArchivesInfo officialArchivesInfo = null;
		try {
			pErrPos = 1;
			if(formMap == null){
				pFlag = false;
				pErrInfo.getContent().append("得到表单数据失败！");
			}
			
			if(pFlag){
				pErrPos = 2;
				if(officialArchivesTypeId == 0){
					pFlag = false;
					pErrInfo.getContent().append("得到公文档案分类信息失败！");
				}else{
					officialArchivesType = new OfficialArchivesType();
					officialArchivesType.setID(officialArchivesTypeId);
				}
			}
		
			if(pFlag){
				pErrPos = 3;
				if((userId = ((UserInfo)session.getAttribute("userInfo")).getUserID()) ==0){
					pFlag = false;
					pErrInfo.getContent().append("得到用户ID失败！");
				}
			}
			//获取公文信息
			if (pFlag) {
				pErrPos = 4;
				if((officialArchivesInfo = OfficialArchivesInfoFactory.newOfficialArchivsInfo(officialArchivesTypeId))==null){
					pFlag = false;
					pErrInfo.getContent().append("获取公文信息失败！");
				}
			}
			//设置当前部门
			if (pFlag) {
				pErrPos = 5;
				if((formationDepartmentID = ((UserInfo)session.getAttribute("userInfo")).getDepartmentID()) ==0){
					pFlag = false;
					pErrInfo.getContent().append("formationDepartmentID败！");
				}else{
					officialArchivesInfo.setFormationDepartmentID(formationDepartmentID);
				}
			}
			
			//得到表单数据构造dataItem集合并构造officialArchivesInfo
			if (pFlag) {
				pErrPos = 6;
				if (this.getOfficialArchivesInfo(formMap, officialArchivesInfo, pErrInfo) == false) {
					pFlag = false;
				}
			}
			
			//调用业务逻辑保存档案
			if(pFlag){
				pErrPos = 7;
				if(officialArchivesInfoManageService.saveOfficialArchivesInfo(userId, officialArchivesType, EnumOfficialArchivesInfoTableType.公文档案登记表, officialArchivesInfo, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"保存公文档案信息失败");
				}
			}

			if (pFlag) {
				NBXH = officialArchivesInfo.getNBXH();
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
			
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
				
				//异常和错误处理
				if(pErrInfo.getException() != null){
					logger.error(pErrInfo.getContent().toString());
					throw new Exception(pErrInfo.getContent().toString());
				}else{
					throw new Exception(pErrInfo.toShortString());
				}
			}
		}
		return NBXH;
	}
	
	//formMap表单数据存放到officialArchivesInfo中
	private boolean getOfficialArchivesInfo(Map<String, String> formMap, OfficialArchivesInfo officialArchivesInfo, ErrInfo pErrInfo){
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			for (Iterator<String> iterator = formMap.keySet().iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				
				//过滤
				if(officialArchivesInfo.getRowFieldsValues().get(key) != null ){
					
					String value = formMap.get(key).toString();
					FieldValue fieldValue = officialArchivesInfo.getRowFieldsValues().get(key);
					
					if(fieldValue.getMandatory() == true && "".equals(value) 
							&& !(EnumSystemDataItem.内部序号.getEnumValue()).equals(key) 
							&& !(EnumSystemDataItem.档案页数.getEnumValue()).equals(key)){
						
						pFlag = false;
						pErrInfo.getContent().append(fieldValue.getDisplayText()+"非法为空");
						break;
					}else{
						officialArchivesInfo.getRowFieldsValues().get(key).setValue(value);
					}
				}		
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}
		return pFlag;
	}
	
	/**
	 * 更新公文档案信息
	 * 
	 * @param userID 用户名
	 * @param officialArchivesInfo 公文档案信息
	 * @return
	 */
	public int updateOfficialArchivesInfo(int officialArchivesTypeId, Map<String, String> formMap,HttpSession session) throws Exception{
		System.out.println("---更新档案信息--------------------------------------");
		System.out.println("officialArchivesTypeId: "+officialArchivesTypeId);
		System.out.println("formMap: "+formMap);
		
		boolean pFlag = true;
		int pErrPos = 0;
		int formationDepartmentID=0;
		
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		int NBXH = 0;
		OfficialArchivesType officialArchivesType = null;
		OfficialArchivesInfo officialArchivesInfo = null;
		try {
			pErrPos = 1;
			if(formMap == null || formMap.size()<=0){
				pFlag = false;
				pErrInfo.getContent().append("得到表单数据失败！");
			}
			
			if(pFlag){
				pErrPos = 2;
				if(officialArchivesTypeId == 0){
					pFlag = false;
					pErrInfo.getContent().append("得到公文档案分类信息失败！");
				}else{
					officialArchivesType = new OfficialArchivesType();
					officialArchivesType.setID(officialArchivesTypeId);
				}
			}
			
			
			
			
			//得到表单数据构造dataItem集合并构造offiicalArchivesInfo
			if(pFlag){
				pErrPos = 5;
				officialArchivesInfo = OfficialArchivesInfoFactory.newOfficialArchivsInfo(officialArchivesTypeId);
				officialArchivesInfo.setNBXH(Integer.parseInt(formMap.get(EnumSystemDataItem.内部序号.getEnumValue())));
				
				if (pFlag) {
					pErrPos = 6;
					if((formationDepartmentID = ((UserInfo)session.getAttribute("userInfo")).getDepartmentID()) ==0){
						pFlag = false;
						pErrInfo.getContent().append("formationDepartmentID败！");
					}else{
						officialArchivesInfo.setFormationDepartmentID(formationDepartmentID);
					}
				}
				if(pFlag){
					pErrPos = 7;
					int userId=0;
					if((userId = ((UserInfo)session.getAttribute("userInfo")).getUserID()) ==0){
						pFlag = false;
						pErrInfo.getContent().append("得到用户ID失败！");
					}else{
						officialArchivesInfo.setRegUserID(userId);
					}
				}
				
				//得到表单数据构造dataItem集合并构造archivesInfo
				if (pFlag) {
					if (this.getOfficialArchivesInfo(formMap, officialArchivesInfo, pErrInfo) == false) {
						pFlag = false;
					}
				}
			}

			//调用业务逻辑更新档案信息
			if(pFlag){
				pErrPos = 6;
				if(officialArchivesInfoManageService.updateOfficialArchivesInfo(officialArchivesType, EnumOfficialArchivesInfoTableType.getEnumElement(officialArchivesTypeId), officialArchivesInfo, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "更新档案信息失败");
				}
			}

			if (pFlag) {
				NBXH = officialArchivesInfo.getNBXH();
			}
		} catch (Exception e) {
			e.printStackTrace();
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
				System.out.println(pErrInfo.getContent().toString());
				//异常和错误处理
				if(pErrInfo.getException() != null){
					logger.error(pErrInfo.getContent().toString());
					throw new Exception(pErrInfo.getContent().toString());
				}else{
					throw new Exception(pErrInfo.toShortString());
				}
			}
		}
		return NBXH;
	}
	
	/**
	 * 删除档案著录信息
	 * 
	 * @param officalArchivesInfo 公文档案信息
	 * @return nbxh 内部序号
	 * @throws Exception 异常
	 */
	public int deleteOfficialArchivesInfo(int NBXH,int officialArchivesTypeId,HttpSession session) throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		int result = 0;
		OfficialArchivesType officialArchivesType = null;
		OfficialArchivesInfo officialArchivesInfo = null;
		
		try {
			pErrPos = 1;
			if(officialArchivesTypeId == 0){
				pFlag = false;
				pErrInfo.getContent().append("得到公文档案分类ID失败");
			}else{
				officialArchivesType = SystemInitializer.getInstance().getOfficialArchivesTypes().get(officialArchivesTypeId);
			}
			
			if (pFlag) {
				if (NBXH ==0) {
					pFlag = false;
					pErrInfo.getContent().append("得到公文档案内部序号失败");
				}else{
					officialArchivesInfo = new OfficialArchivesInfo(officialArchivesType);
					officialArchivesInfo.setNBXH(NBXH);
				}
			}
			
			//调用业务i逻辑删除档案信息
			if (pFlag) {
				pErrPos = 2;
				if(officialArchivesInfoManageService.deleteOfficialArchivesInfo(officialArchivesType, EnumOfficialArchivesInfoTableType.getEnumElement(officialArchivesTypeId), officialArchivesInfo, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"删除公文档案信息失败");
				}else{
					result = officialArchivesInfo.getNBXH();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
				
				//异常和错误处理
				if(pErrInfo.getException() != null){
					logger.error(pErrInfo.getContent().toString());
					throw new Exception(pErrInfo.getContent().toString());
				}else{
					throw new Exception(pErrInfo.toShortString());
				}
			}
			//销毁局部变量
			throwable = null;
		}
		return result;
	}

	/**
	 * 进行公文档案信息归档
	 * @return 是否成功
	 * @throws Exception 异常
	 */
	public boolean archiviedOfficialArchivesInfos(int NBXH,int officialArchivesTypeID,int archivesTypeID,int childArchivesTypeID,String archivesFondID,HttpSession session) throws Exception {
		System.out.println("archiviedOfficialArchivesInfos");
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo=new ErrInfo();
		
		try {
			//开始处理 1...
			pErrPos = 1;
			//判断NBXH是否为空
			if (NBXH == 0) {
				pFlag=false;
				pErrInfo.getContent().append("NBXH非法为空!");
			}
			//判断officialArchivesTypeID是否为空
			if (officialArchivesTypeID == 0) {
				pFlag=false;
				pErrInfo.getContent().append("officialArchivesTypeID非法为空!");
			}
			
			if (archivesTypeID == 0) {
				pFlag=false;
				pErrInfo.getContent().append("archivesTypeID非法为空!");
			}
			
			if (archivesFondID == null || archivesFondID.equals("")) {
				pFlag=false;
				pErrInfo.getContent().append("archivesFondID非法为空!");
			}
			//档案分类---->一级分类
			ArchivesType archivesType=SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID);
			//公文档案分类的归档映射关系表实体类
			OfficialArchivesTypeSavedMapping officialArchivesTypeSavedMapping=SystemInitializer.getInstance().getOfficialArchivesTypes().get(officialArchivesTypeID).getOfficialArchivesTypeSavedMappings().get(archivesType.getID());
			//查询公文档案信息
			OfficialArchivesInfo officialArchivesInfo=OfficialArchivesInfoFactory.newOfficialArchivsInfo(officialArchivesTypeID);
			OfficialArchivesType officialArchivesType=SystemInitializer.getInstance().getOfficialArchivesTypes().get(officialArchivesTypeID);
			if(officialArchivesInfoManageService.findOfficialArchivesInfoByNBXH(NBXH, officialArchivesType, officialArchivesInfo, pErrInfo)==false){
				pErrPos = 2;
				pErrInfo.getContent().append("officialArchivesInfo公文档案信息查找失败!");
			}
		
		    ArchivesInfo archivesInfo=ArchivesInfoFactory.newArchivsInfo(archivesTypeID);
		    
			Map<String, ArchivesTypeDataItem> archivesTypeDataItems=archivesType.getDataItemsForAll();
			//设置全宗编号
			archivesInfo.setArchivesFondsID(archivesFondID);
			//设置档案类型编号
			archivesInfo.setArchivesTypeID(childArchivesTypeID);
			//全宗编号
			archivesInfo.setArchivesFondsID(archivesFondID);
			
			for (ArchivesTypeDataItem archivesTypeDataItem : archivesTypeDataItems.values()) {
				//归档时候将没有映射的字段设置为默认值
				if(archivesTypeDataItem.getColumnName().equals(EnumSystemDataItem.档案管理工作流状态.getEnumValue()) ){
					archivesInfo.setWorkFlowStatus(EnumWorkFlowStatus.著录完成);
				}
				
				if(officialArchivesTypeSavedMapping.getOfficialArchivesDataItemSavedMapping().containsKey(archivesTypeDataItem.getDataItemID())){
					String colName=officialArchivesTypeSavedMapping.getOfficialArchivesDataItemSavedMapping().get(archivesTypeDataItem.getDataItemID()).getColumnName();
					if (officialArchivesInfo.getRowFieldsValues().containsKey(colName))
					{
						archivesInfo.getRowFieldsValues().get(archivesTypeDataItem.getColumnName()).setValue(officialArchivesInfo.getRowFieldsValues().get(colName).getValue());
					}
					else {
						pFlag = false;
						pErrInfo.getContent().append("公文数据项归档映射关系中的数据项("+colName+")在对应公文分类的数据项定义中不存在。");
					}
				}
					
			}
			if(archivesInfoWorkingManageService.saveArchivesInfo(officialArchivesInfo.getRegUserID(), archivesType, EnumArchivesInfoType.文件级档案, archivesInfo, pErrInfo)==false){
				pFlag=false;
				pErrInfo.getContent().insert(0, "公文归档信息保存失败: ");
			}else{
				System.out.println("归档成功!");
				System.out.println(EnumSystemDataItem.归档标志.getEnumValue());
				officialArchivesInfo.setSavedFlag(true);
				if(officialArchivesInfoManageService.updateOfficialArchivesInfo(officialArchivesType, EnumOfficialArchivesInfoTableType.公文档案登记表, officialArchivesInfo, pErrInfo)==false){
					pFlag=false;
					pErrInfo.getContent().insert(0, "更新公文档案信息归档标示失败:");
				}else{
					System.out.println("<->"+officialArchivesInfo.getSavedFlag());
				}
			}
		
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}
	
	/**
	 * 进行公文档案信息归档
	 * @return 是否成功
	 * @throws Exception 异常
	 */
	public boolean archiviedBatchOfficialArchivesInfos(Integer[] NBXHS,int officialArchivesTypeID,int archivesTypeID,int childArchivesTypeID,String archivesFondID,HttpSession session) throws Exception {
		System.out.println("archiviedOfficialArchivesInfos");
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo=new ErrInfo();
		
		try {
				for (int i = 0; i < NBXHS.length; i++) {
					int NBXH=NBXHS[i];
					if(archiviedOfficialArchivesInfos(NBXH,officialArchivesTypeID, archivesTypeID,childArchivesTypeID, archivesFondID, session)==false){
						pFlag=false;
						pErrInfo.getContent().insert(0, "批量公文归档信息保存失败: ");
					}
				}
		
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}
	
	//查询档案分类下的子分类 ,ArchivesTypeID为关键字，archivesName为值
	public Map<Integer,String> findchildArchivesTypeByArchivesTypeID(int archivesTypeID){
		Map<Integer, ArchivesType> archivesTypes=SystemInitializer.getInstance().getArchivesTypes().get(archivesTypeID).getChildArchivesTypes();
		Map<Integer,String> result=new HashMap<Integer, String>();
		for (ArchivesType archivesType : archivesTypes.values()) {
			result.put(archivesType.getID(), archivesType.getName());
		}
		
		return result;
	}
}
