package com.orifound.aiim.web.struts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService;

import com.orifound.aiim.bll.util.ArchivesInfoFactory;
import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ArchivesTypeDataItem;
import com.orifound.aiim.entity.EnumArchivesInfoType;
import com.orifound.aiim.entity.EnumSystemDataItem;
import com.orifound.aiim.entity.EnumWorkFlowStatus;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.FieldValue;
import com.orifound.aiim.entity.SystemInitializer;
import com.orifound.aiim.entity.UserInfo;
import com.orifound.aiim.web.interceptors.FunctionalDescription;
import com.orifound.aiim.web.util.GenerateHtmlCodeUtil;

/**
 * 档案管理-文件管理 控制类(DWR)
 * 
 * @author 代向涛
 * 
 */
public class ArchivesInfoManageDWR extends ActionSupport {
	
	private static final long serialVersionUID = 1L;

	static Log logger = LogFactory.getLog(ArchivesInfoManageDWR.class);
	
	@Autowired
	private IArchivesInfoWorkingManageService archivesInfoWorkingManageService; 


	/**
	 * 保存档案信息
	 * @param fileType  档案信息的归档类型
	 * @param archivesTypeId 档案类型编号
	 * @param formMap 表单Map集合
	 * @param session session对象
	 * @param application application对象
	 * @return NBXH 内部序号 (如需要删除刚添加的档案信息，要用到NBXH，而添加一个档案信息时并没有NBXH，故返回NBXH)
	 * @throws Exception
	 */
	public int saveArchivesInfo(int fileType,int archivesTypeId, boolean parentFlag,Map<String,String> formMap,HttpSession session) throws Exception{
		System.out.println("-----保存档案信息-----------------------------------");
		System.out.println("fileType: "+fileType);
		System.out.println("archivesTypeId: "+archivesTypeId);
		System.out.println("formMap: "+formMap);
		System.out.println("parentFlag: "+parentFlag);
		System.out.println("parentNBXH: "+formMap.get("ParentNBXH").trim());
		
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		int NBXH = 0;
		int userId = 0;
		ArchivesType archivesType = null;
		ArchivesInfo archivesInfo = null;
		try {
			pErrPos = 1;
			if(formMap == null){
				pFlag = false;
				pErrInfo.getContent().append("得到表单数据失败！");
			}
			
			if(pFlag){
			    pErrPos = 2;
				if(archivesTypeId == 0){
					pFlag = false;
					pErrInfo.getContent().append("得到档案类型失败！");
				}
			}
			
			if(pFlag){
				pErrPos = 3;
				if(archivesTypeId == 0){
					pFlag = false;
					pErrInfo.getContent().append("得到档案分类信息失败！");
				}else{
					archivesType = new ArchivesType();
					archivesType.setID(archivesTypeId);
				}
			}
			
			if(pFlag){
				pErrPos = 4;
				if((userId = ((UserInfo)session.getAttribute("userInfo")).getUserID()) ==0){
					pFlag = false;
					pErrInfo.getContent().append("得到用户ID失败！");
				}
			}
			
			if (pFlag) {
				pErrPos = 5;
				archivesInfo = ArchivesInfoFactory.newArchivsInfo(archivesTypeId);
				
				archivesInfo.setParentFlag(parentFlag);
				
				if (fileType == 3) {
					String parentNBXH = formMap.get(EnumSystemDataItem.所属案卷的内部序号.getEnumValue());
			    	if(parentNBXH != null && !"".equals(parentNBXH)){
			    		archivesInfo.setParentNBXH(Integer.parseInt(parentNBXH.toString()));
			    	}else{
				    	pFlag = false;
				    	pErrInfo.getContent().append("获取案卷内部序号失败！");
				    }
				}	
			}
			
			//得到表单数据构造dataItem集合并构造archivesInfo
			if (pFlag) {
				if (this.getArchivesInfo(formMap, archivesInfo, pErrInfo) == false) {
					pFlag = false;
				}
			}
			
			//调用业务逻辑保存档案
			if(pFlag){
				pErrPos = 7;
				
				archivesInfo.setWorkFlowStatus(EnumWorkFlowStatus.著录完成);
				archivesInfo.setUserID1(userId);
				
				if(archivesInfoWorkingManageService.saveArchivesInfo(userId, archivesType, EnumArchivesInfoType.getEnumElement(fileType), archivesInfo, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"保存档案信息失败");
				}
			}

			if (pFlag) {
				NBXH = archivesInfo.getNBXH();
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
		}
		return NBXH;
	}
	
	/**
	 * 更新档案信息
	 * 
	 * @param userID
	 * @param archivesInfo
	 * @return
	 */
	public int updateArchivesInfo(int archivesTypeId,int fileType, Map<String, String> formMap,HttpSession session) throws Exception{
		System.out.println("---更新档案信息--------------------------------------");
		System.out.println("archivesTypeId: "+archivesTypeId);
		System.out.println("formMap: "+formMap);
		
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		int NBXH = 0;
		ArchivesType archivesType = null;
		ArchivesInfo archivesInfo = null;
		try {
			pErrPos = 1;
			if(formMap == null || formMap.size()<=0){
				pFlag = false;
				pErrInfo.getContent().append("得到表单数据失败！");
			}
			
			if(pFlag){
			    pErrPos = 2;
				if(archivesTypeId == 0){
					pFlag = false;
					pErrInfo.getContent().append("得到档案类型失败！");
				}
			}
			
			//得到表单数据构造dataItem集合并构造archivesInfo
			if(pFlag){
				pErrPos = 5;
				archivesInfo = ArchivesInfoFactory.newArchivsInfo(archivesTypeId);
				archivesInfo.setNBXH(Integer.parseInt(formMap.get(EnumSystemDataItem.内部序号.getEnumValue())));

				//得到表单数据构造dataItem集合并构造archivesInfo
				if (pFlag) {
					if (this.getArchivesInfo(formMap, archivesInfo, pErrInfo) == false) {
						pFlag = false;
					}
				}
			}

			//调用业务逻辑更新档案信息
			if(pFlag){
				pErrPos = 6;
				if(archivesInfoWorkingManageService.updateArchivesInfo(archivesType, EnumArchivesInfoType.getEnumElement(fileType), archivesInfo, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "更新档案信息失败");
				}
			}

			if (pFlag) {
				NBXH = archivesInfo.getNBXH();
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
		}
		return NBXH;
	}
	
	/**
	 * 删除档案著录信息
	 * 
	 * @param archivesInfo
	 * @return
	 * @throws Exception 
	 */
	public int deleteArchivesInfo(int NBXH,int archivesTypeId, int fileType,HttpSession session) throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		int result = 0;
		ArchivesType archivesType = null;
		ArchivesInfo archivesInfo = null;
		
		try {
			pErrPos = 1;
			if(archivesTypeId == 0){
				pFlag = false;
				pErrInfo.getContent().append("得到档案分类ID失败");
			}else{
				archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeId);
			}
			
			if (pFlag) {
				if (NBXH ==0) {
					pFlag = false;
					pErrInfo.getContent().append("得到档案内部序号失败");
				}else{
					archivesInfo = new ArchivesInfo(archivesType);
					archivesInfo.setNBXH(NBXH);
				}
			}
			
			//调用业务i逻辑删除档案信息
			if (pFlag) {
				pErrPos = 2;
				if(archivesInfoWorkingManageService.deleteArchivesInfo(archivesType, EnumArchivesInfoType.getEnumElement(fileType), archivesInfo, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"删除档案信息失败");
				}else{
					result = archivesInfo.getNBXH();
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
	 * 根据内部序号查询指定案卷的卷内文件信息集合
	 * 
	 * @param archivesInfo
	 *            待查的案卷信息
	 * @return archivesInfos 查询到的卷内文件信息集合
	 */
	public List<ArchivesInfo> findChildArchivesInfosByNBXH(int archivesTypeID,int NBXH,HttpSession session) throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		List<ArchivesInfo> archivesInfos = null;
		ArchivesType archivesType = null;
		
		try {
			pErrPos = 1;
			if (archivesTypeID == 0) {
				pFlag = false;
				pErrInfo.getContent().append("获得档案类型失败");
			}else{
				archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID);
			}

			if (pFlag) {
				pErrPos = 2;
				if (NBXH == 0) {
					pFlag = false;
					pErrInfo.getContent().append("获得档案内部序号失败");
				}
			}
			
			//调用业务逻辑
			if (pFlag) {
				pErrPos = 3;
				archivesInfos = new ArrayList<ArchivesInfo>();
				if(archivesInfoWorkingManageService.findChildArchivesInfosByNBXH(NBXH, archivesType, archivesInfos, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"获得档案卷内文件失败");
				}
			}
		} catch (Exception e) {
			//异常错误
			e.printStackTrace();
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
		return archivesInfos;
	}

	/**
	 * 得到当前案卷卷内文件的内部序号
	 * @param archivesInfo
	 * @return
	 * @throws Exception
	 */
	public List<Integer> findChildArchivesInfosNBXHS(int NBXH, int archivesTypeID ,HttpSession session)throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		
		ArchivesType archivesType = null;
		List<ArchivesInfo> archivesInfos = null;
		List<Integer> NBXHS = null;
		try {
			pErrPos = 1;
			// 检查档案内部序号是否有值
			if (NBXH == 0) {
				pFlag = false;
				pErrInfo.getContent().append("档案内部序号未赋值");
			}

			// 检查档案分类编号是否有值
			if (pFlag) {
				pErrPos = 2;
				if(archivesTypeID == 0){
					pFlag = false;
					pErrInfo.getContent().append("案类型编号未赋值");
				}else{
					archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID);
				}
			}
			
			// 查询出该案卷下的卷内文件列表
			if (pFlag) {
				pErrPos = 3;
				archivesInfos = new ArrayList<ArchivesInfo>();
				if (archivesInfoWorkingManageService.findChildArchivesInfosByNBXH(NBXH, archivesType, archivesInfos, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "查找档案卷内文件内部序号失败");
				} else {
					//保存档案的内部序号
					NBXHS = new ArrayList<Integer>();
					for (ArchivesInfo archivesInfo : archivesInfos) {
						NBXHS.add(archivesInfo.getNBXH());
					}
				}
			}
		} catch (Exception e) {
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
		return NBXHS;
	}
	
	
	/**
	 * 得到html代码
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 * @throws Exception 
	 */
	@FunctionalDescription(FeatureName = "新增档案信息",OperateContent = "点击按钮得到新增著录代码")
	public String getHtmlCode(int archivesTypeId,int NBXH,HttpSession session,HttpServletRequest request){
		System.out.println("----得到著录html代码---------------------------");
		System.out.println("archivesTypeId: "+archivesTypeId);
		System.out.println("NBXH: "+NBXH);
		
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		String htmlCode = "";
		ArchivesType archivesType = null;
		
		try {
			pErrPos = 1;
			Map<String, ArchivesTypeDataItem> dataItems = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeId).getDataItemsForInput();
			
			if(dataItems == null){
				pErrPos = 2;
				pFlag = false;
				pErrInfo.getContent().append("此分类下可著录数据项为空！");
			}
			
			if(pFlag){
				pErrPos = 3;
				
				if(NBXH==0){
					htmlCode = GenerateHtmlCodeUtil.GenerateHtmlCode(dataItems, null,null);
				}else{
					//调用业务逻辑得到ArchivesInfo 对象
					archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeId);
					ArchivesInfo archivesInfo = new ArchivesInfo(archivesType);
					
					pFlag = archivesInfoWorkingManageService.findArchivesInfoByNBXH(NBXH, archivesType, archivesInfo, pErrInfo);
					
					htmlCode = GenerateHtmlCodeUtil.GenerateHtmlCode(dataItems, archivesInfo,null);
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
	 * 文件管理：组卷
	 * @param NBXHS 待组卷的文件内部序号数组
	 * @param formMap 表单数据
	 * @return 组卷后的案卷内部序号
	 * @throws Exception 
	 */
	public int combineArchivesInfos(int archivesTypeId , int[] NBXHS , Map<String,String> formMap , HttpSession session) throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		int parentNBXH = 0;
		ArchivesType archivesType = null;
		ArchivesInfo archivesInfo = null;
		ArchivesInfo archivesInfoParent = null;
		List<ArchivesInfo> archivesInfos = null;
		try {
			pErrPos = 1;
			if(formMap == null || formMap.size()<=0){
				pFlag = false;
				pErrInfo.getContent().append("得到表单数据失败！");
			}
			
			if(pFlag){
			    pErrPos = 2;
				if(archivesTypeId == 0){
					pFlag = false;
					pErrInfo.getContent().append("得到档案类型失败！");
				}else{
					archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeId);
				}
			}
			
			//得到内部序号的集合构造业务逻辑所需档案信息对象集合
			if(pFlag){
				pErrPos = 5;
				if(NBXHS == null){
					pFlag = false;
					pErrInfo.getContent().append("没有得到组卷的文件内部序号的集合");
				}else{
					archivesInfos = new ArrayList<ArchivesInfo>();
					for (int NBXH : NBXHS) {
						archivesInfo = new ArchivesInfo(archivesType);
						archivesInfo.setNBXH(NBXH);
						archivesInfos.add(archivesInfo);
					}
				}				
			}

			//得到表单数据构造dataItem集合并构造archivesInfo
			if (pFlag) {
				archivesInfoParent = new ArchivesInfo(archivesType);

				archivesInfoParent.setParentFlag(true);		
				
				if (this.getArchivesInfo(formMap, archivesInfoParent, pErrInfo) == false) {
					pFlag = false;
				}
			
			}

			//调用业务逻辑更新档案信息
			if(pFlag){
				pErrPos = 6;
				int userId = ((UserInfo)session.getAttribute("userInfo")).getUserID();
				archivesInfoParent.setWorkFlowStatus(EnumWorkFlowStatus.著录完成);
				archivesInfoParent.setUserID1(userId);
				if(archivesInfoWorkingManageService.combineArchivesInfos(userId , archivesType, archivesInfos, archivesInfoParent, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "组卷失败: ");
				}else{
					parentNBXH = archivesInfoParent.getNBXH();
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
		return parentNBXH;
	}


	private boolean getArchivesInfo(Map<String, String> formMap, ArchivesInfo archivesInfo, ErrInfo pErrInfo) throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			for (Iterator<String> iterator = formMap.keySet().iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				
				//过滤
				if(archivesInfo.getRowFieldsValues().get(key) != null ){
					
					String value = formMap.get(key).toString();
					FieldValue fieldValue = archivesInfo.getRowFieldsValues().get(key);
					
					if(fieldValue.getMandatory() == true && "".equals(value) 
							&& !(EnumSystemDataItem.内部序号.getEnumValue()).equals(key) 
							&& !(EnumSystemDataItem.所属案卷的内部序号.getEnumValue()).equals(key) 
							&& !(EnumSystemDataItem.档案页数.getEnumValue()).equals(key)){
						
						pFlag = false;
						pErrInfo.getContent().append(fieldValue.getDisplayText()+"非法为空");
						break;
					}else{
						archivesInfo.getRowFieldsValues().get(key).setValue(value);
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
		return pFlag;
	}
	
	
	/**
	 * 著录审核通过
	 * @param NBXH 审核的档案内部序号
	 * @return 组卷后的案卷内部序号
	 * @throws Exception 
	 */
	public int checkPass(int archivesTypeID , int NBXH , HttpSession session) throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		int success = 0;
		UserInfo userInfo = null;
		
		ArchivesType archivesType = null;
		ArchivesInfo archivesInfo = null;
		try {
			pErrPos = 1;
			userInfo = (UserInfo)session.getAttribute("userInfo");
			
			if(pFlag){
			    pErrPos = 2;
				if(archivesTypeID == 0){
					pFlag = false;
					pErrInfo.getContent().append("得到档案类型失败！");
				}else{
					archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID);
				}
			}
			
			//得到内部序号的集合构造业务逻辑所需档案信息对象集合
			if(pFlag){
				pErrPos = 5;
				if(NBXH ==0){
					pFlag = false;
					pErrInfo.getContent().append("没有得到组卷的文件内部序号的集合");
				}else{
					archivesInfo = ArchivesInfoFactory.newArchivsInfo(archivesTypeID, NBXH);
				}			
			}

			//设置档案信息工作流为著录审核
			if (pFlag) {	
				if (archivesInfoWorkingManageService.inputCheckPass(userInfo.getUserID(), archivesType, archivesInfo,  pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"设置档案工作流为著录审核完成失败");
				}
			}
			
			if (pFlag) {
				success = 1;
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
		return success;
	}
	
	/**
	 * 著录审核打回
	 * @param NBXH 审核的档案内部序号
	 * @return 组卷后的案卷内部序号
	 * @throws Exception 
	 */
	public int checkBack(int archivesTypeID , int NBXH , String backReason,HttpSession session) throws Exception {
		System.out.println("------著录审核打回---------------------------");
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		int success = 0;
		UserInfo userInfo = null;
		
		ArchivesType archivesType = null;
		ArchivesInfo archivesInfo = null;
		try {
			pErrPos = 1;
			userInfo = (UserInfo)session.getAttribute("userInfo");
			
			if(pFlag){
			    pErrPos = 2;
				if(archivesTypeID == 0){
					pFlag = false;
					pErrInfo.getContent().append("得到档案类型失败！");
				}else{
					archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID);
				}
			}
			
			//得到内部序号的集合构造业务逻辑所需档案信息对象集合
			if(pFlag){
				pErrPos = 5;
				if(NBXH ==0){
					pFlag = false;
					pErrInfo.getContent().append("得到案卷内部序号失败");
				}else{
					archivesInfo = ArchivesInfoFactory.newArchivsInfo(archivesTypeID, NBXH);
				}			
			}

			//设置档案信息工作流为著录审核
			if (pFlag) {	
				if (archivesInfoWorkingManageService.inputCheckBack(userInfo.getUserID(), archivesType, archivesInfo, backReason, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"设置档案工作流为著录审核打回失败");
				}
			}
			
			if (pFlag) {
				success = 1;
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
		return success;
	}
	
	
	/**
	 * 设置档案信息修复标志
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	public int updateArchivesInfoFixedFlag(int archivesTypeID,int NBXH, HttpSession session) throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		
		ArchivesType archivesType = null;
		try {
			//开始处理 1...
			pErrPos = 1;
			archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID);
			if (pFlag) {
				pErrPos = 2;
				if (archivesInfoWorkingManageService.updateArchivesInfoFixedFlag(archivesType, NBXH, pErrInfo)== false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"设置档案信息修复标志失败");
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
		return NBXH;
	}
	
	/**
	 * 得到档案类型对象
	 */
	public ArchivesType getArchivesTypeByID(int archivesTypeID,HttpSession session) throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
        ErrInfo pErrInfo = new ErrInfo();
		
		ArchivesType archivesType = null;
		try {
			archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID);
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
		return archivesType;
	}
	
	/**
	 * 得到档案打回原因
	 */
	public String findBackReason(int archivesTypeID,int NBXH,HttpSession session) throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
        ErrInfo pErrInfo = new ErrInfo();
		
		ArchivesType archivesType = null;
		ArchivesInfo archivesInfo = new ArchivesInfo(archivesType);
		String backReason = "";
		try {
			archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID);
			if (archivesInfoWorkingManageService.findArchivesInfoByNBXH(NBXH, archivesType, archivesInfo, pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "查找档案信息失败");
				System.out.println(pErrInfo.toString());
			}
			
			if (pFlag) {
				backReason = archivesInfo.getSendBackReason();
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
		return backReason;
	}
}
