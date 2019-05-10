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
 * ��������-�ļ����� ������(DWR)
 * 
 * @author ������
 * 
 */
public class ArchivesInfoManageDWR extends ActionSupport {
	
	private static final long serialVersionUID = 1L;

	static Log logger = LogFactory.getLog(ArchivesInfoManageDWR.class);
	
	@Autowired
	private IArchivesInfoWorkingManageService archivesInfoWorkingManageService; 


	/**
	 * ���浵����Ϣ
	 * @param fileType  ������Ϣ�Ĺ鵵����
	 * @param archivesTypeId �������ͱ��
	 * @param formMap ��Map����
	 * @param session session����
	 * @param application application����
	 * @return NBXH �ڲ���� (����Ҫɾ������ӵĵ�����Ϣ��Ҫ�õ�NBXH�������һ��������Ϣʱ��û��NBXH���ʷ���NBXH)
	 * @throws Exception
	 */
	public int saveArchivesInfo(int fileType,int archivesTypeId, boolean parentFlag,Map<String,String> formMap,HttpSession session) throws Exception{
		System.out.println("-----���浵����Ϣ-----------------------------------");
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
				pErrInfo.getContent().append("�õ�������ʧ�ܣ�");
			}
			
			if(pFlag){
			    pErrPos = 2;
				if(archivesTypeId == 0){
					pFlag = false;
					pErrInfo.getContent().append("�õ���������ʧ�ܣ�");
				}
			}
			
			if(pFlag){
				pErrPos = 3;
				if(archivesTypeId == 0){
					pFlag = false;
					pErrInfo.getContent().append("�õ�����������Ϣʧ�ܣ�");
				}else{
					archivesType = new ArchivesType();
					archivesType.setID(archivesTypeId);
				}
			}
			
			if(pFlag){
				pErrPos = 4;
				if((userId = ((UserInfo)session.getAttribute("userInfo")).getUserID()) ==0){
					pFlag = false;
					pErrInfo.getContent().append("�õ��û�IDʧ�ܣ�");
				}
			}
			
			if (pFlag) {
				pErrPos = 5;
				archivesInfo = ArchivesInfoFactory.newArchivsInfo(archivesTypeId);
				
				archivesInfo.setParentFlag(parentFlag);
				
				if (fileType == 3) {
					String parentNBXH = formMap.get(EnumSystemDataItem.����������ڲ����.getEnumValue());
			    	if(parentNBXH != null && !"".equals(parentNBXH)){
			    		archivesInfo.setParentNBXH(Integer.parseInt(parentNBXH.toString()));
			    	}else{
				    	pFlag = false;
				    	pErrInfo.getContent().append("��ȡ�����ڲ����ʧ�ܣ�");
				    }
				}	
			}
			
			//�õ������ݹ���dataItem���ϲ�����archivesInfo
			if (pFlag) {
				if (this.getArchivesInfo(formMap, archivesInfo, pErrInfo) == false) {
					pFlag = false;
				}
			}
			
			//����ҵ���߼����浵��
			if(pFlag){
				pErrPos = 7;
				
				archivesInfo.setWorkFlowStatus(EnumWorkFlowStatus.��¼���);
				archivesInfo.setUserID1(userId);
				
				if(archivesInfoWorkingManageService.saveArchivesInfo(userId, archivesType, EnumArchivesInfoType.getEnumElement(fileType), archivesInfo, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"���浵����Ϣʧ��");
				}
			}

			if (pFlag) {
				NBXH = archivesInfo.getNBXH();
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
			
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
				
				//�쳣�ʹ�����
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
	 * ���µ�����Ϣ
	 * 
	 * @param userID
	 * @param archivesInfo
	 * @return
	 */
	public int updateArchivesInfo(int archivesTypeId,int fileType, Map<String, String> formMap,HttpSession session) throws Exception{
		System.out.println("---���µ�����Ϣ--------------------------------------");
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
				pErrInfo.getContent().append("�õ�������ʧ�ܣ�");
			}
			
			if(pFlag){
			    pErrPos = 2;
				if(archivesTypeId == 0){
					pFlag = false;
					pErrInfo.getContent().append("�õ���������ʧ�ܣ�");
				}
			}
			
			//�õ������ݹ���dataItem���ϲ�����archivesInfo
			if(pFlag){
				pErrPos = 5;
				archivesInfo = ArchivesInfoFactory.newArchivsInfo(archivesTypeId);
				archivesInfo.setNBXH(Integer.parseInt(formMap.get(EnumSystemDataItem.�ڲ����.getEnumValue())));

				//�õ������ݹ���dataItem���ϲ�����archivesInfo
				if (pFlag) {
					if (this.getArchivesInfo(formMap, archivesInfo, pErrInfo) == false) {
						pFlag = false;
					}
				}
			}

			//����ҵ���߼����µ�����Ϣ
			if(pFlag){
				pErrPos = 6;
				if(archivesInfoWorkingManageService.updateArchivesInfo(archivesType, EnumArchivesInfoType.getEnumElement(fileType), archivesInfo, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "���µ�����Ϣʧ��");
				}
			}

			if (pFlag) {
				NBXH = archivesInfo.getNBXH();
			}
		} catch (Exception e) {
			e.printStackTrace();
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;

				//�쳣�ʹ�����
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
	 * ɾ��������¼��Ϣ
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
				pErrInfo.getContent().append("�õ���������IDʧ��");
			}else{
				archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeId);
			}
			
			if (pFlag) {
				if (NBXH ==0) {
					pFlag = false;
					pErrInfo.getContent().append("�õ������ڲ����ʧ��");
				}else{
					archivesInfo = new ArchivesInfo(archivesType);
					archivesInfo.setNBXH(NBXH);
				}
			}
			
			//����ҵ��i�߼�ɾ��������Ϣ
			if (pFlag) {
				pErrPos = 2;
				if(archivesInfoWorkingManageService.deleteArchivesInfo(archivesType, EnumArchivesInfoType.getEnumElement(fileType), archivesInfo, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"ɾ��������Ϣʧ��");
				}else{
					result = archivesInfo.getNBXH();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
				
				//�쳣�ʹ�����
				if(pErrInfo.getException() != null){
					logger.error(pErrInfo.getContent().toString());
					throw new Exception(pErrInfo.getContent().toString());
				}else{
					throw new Exception(pErrInfo.toShortString());
				}
			}
			//���پֲ�����
			throwable = null;
		}
		return result;
	}

	
	/**
	 * �����ڲ���Ų�ѯָ������ľ����ļ���Ϣ����
	 * 
	 * @param archivesInfo
	 *            ����İ�����Ϣ
	 * @return archivesInfos ��ѯ���ľ����ļ���Ϣ����
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
				pErrInfo.getContent().append("��õ�������ʧ��");
			}else{
				archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID);
			}

			if (pFlag) {
				pErrPos = 2;
				if (NBXH == 0) {
					pFlag = false;
					pErrInfo.getContent().append("��õ����ڲ����ʧ��");
				}
			}
			
			//����ҵ���߼�
			if (pFlag) {
				pErrPos = 3;
				archivesInfos = new ArrayList<ArchivesInfo>();
				if(archivesInfoWorkingManageService.findChildArchivesInfosByNBXH(NBXH, archivesType, archivesInfos, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"��õ��������ļ�ʧ��");
				}
			}
		} catch (Exception e) {
			//�쳣����
			e.printStackTrace();
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
				
				//�쳣�ʹ�����
				if(pErrInfo.getException() != null){
					logger.error(pErrInfo.getContent().toString());
					throw new Exception(pErrInfo.getContent().toString());
				}else{
					throw new Exception(pErrInfo.toShortString());
				}
			}
			//���پֲ�����
			throwable = null;
		}
		return archivesInfos;
	}

	/**
	 * �õ���ǰ��������ļ����ڲ����
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
			// ��鵵���ڲ�����Ƿ���ֵ
			if (NBXH == 0) {
				pFlag = false;
				pErrInfo.getContent().append("�����ڲ����δ��ֵ");
			}

			// ��鵵���������Ƿ���ֵ
			if (pFlag) {
				pErrPos = 2;
				if(archivesTypeID == 0){
					pFlag = false;
					pErrInfo.getContent().append("�����ͱ��δ��ֵ");
				}else{
					archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID);
				}
			}
			
			// ��ѯ���ð����µľ����ļ��б�
			if (pFlag) {
				pErrPos = 3;
				archivesInfos = new ArrayList<ArchivesInfo>();
				if (archivesInfoWorkingManageService.findChildArchivesInfosByNBXH(NBXH, archivesType, archivesInfos, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ҵ��������ļ��ڲ����ʧ��");
				} else {
					//���浵�����ڲ����
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
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
				
				//�쳣�ʹ�����
				if(pErrInfo.getException() != null){
					logger.error(pErrInfo.getContent().toString());
					throw new Exception(pErrInfo.getContent().toString());
				}else{
					throw new Exception(pErrInfo.toShortString());
				}
			}

			//���پֲ�����
			throwable = null;
		}
		return NBXHS;
	}
	
	
	/**
	 * �õ�html����
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 * @throws Exception 
	 */
	@FunctionalDescription(FeatureName = "����������Ϣ",OperateContent = "�����ť�õ�������¼����")
	public String getHtmlCode(int archivesTypeId,int NBXH,HttpSession session,HttpServletRequest request){
		System.out.println("----�õ���¼html����---------------------------");
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
				pErrInfo.getContent().append("�˷����¿���¼������Ϊ�գ�");
			}
			
			if(pFlag){
				pErrPos = 3;
				
				if(NBXH==0){
					htmlCode = GenerateHtmlCodeUtil.GenerateHtmlCode(dataItems, null,null);
				}else{
					//����ҵ���߼��õ�ArchivesInfo ����
					archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeId);
					ArchivesInfo archivesInfo = new ArchivesInfo(archivesType);
					
					pFlag = archivesInfoWorkingManageService.findArchivesInfoByNBXH(NBXH, archivesType, archivesInfo, pErrInfo);
					
					htmlCode = GenerateHtmlCodeUtil.GenerateHtmlCode(dataItems, archivesInfo,null);
				}
			}
			
			if(!pFlag){
				pErrInfo.getContent().insert(0,"�����ڲ���Ų��ҵ�����Ϣʧ��");
			}
		} catch (Exception e) {
			// �쳣����
			e.printStackTrace();
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		} finally {
			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
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
	 * �ļ��������
	 * @param NBXHS �������ļ��ڲ��������
	 * @param formMap ������
	 * @return ����İ����ڲ����
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
				pErrInfo.getContent().append("�õ�������ʧ�ܣ�");
			}
			
			if(pFlag){
			    pErrPos = 2;
				if(archivesTypeId == 0){
					pFlag = false;
					pErrInfo.getContent().append("�õ���������ʧ�ܣ�");
				}else{
					archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeId);
				}
			}
			
			//�õ��ڲ���ŵļ��Ϲ���ҵ���߼����赵����Ϣ���󼯺�
			if(pFlag){
				pErrPos = 5;
				if(NBXHS == null){
					pFlag = false;
					pErrInfo.getContent().append("û�еõ������ļ��ڲ���ŵļ���");
				}else{
					archivesInfos = new ArrayList<ArchivesInfo>();
					for (int NBXH : NBXHS) {
						archivesInfo = new ArchivesInfo(archivesType);
						archivesInfo.setNBXH(NBXH);
						archivesInfos.add(archivesInfo);
					}
				}				
			}

			//�õ������ݹ���dataItem���ϲ�����archivesInfo
			if (pFlag) {
				archivesInfoParent = new ArchivesInfo(archivesType);

				archivesInfoParent.setParentFlag(true);		
				
				if (this.getArchivesInfo(formMap, archivesInfoParent, pErrInfo) == false) {
					pFlag = false;
				}
			
			}

			//����ҵ���߼����µ�����Ϣ
			if(pFlag){
				pErrPos = 6;
				int userId = ((UserInfo)session.getAttribute("userInfo")).getUserID();
				archivesInfoParent.setWorkFlowStatus(EnumWorkFlowStatus.��¼���);
				archivesInfoParent.setUserID1(userId);
				if(archivesInfoWorkingManageService.combineArchivesInfos(userId , archivesType, archivesInfos, archivesInfoParent, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ʧ��: ");
				}else{
					parentNBXH = archivesInfoParent.getNBXH();
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
				
				//�쳣�ʹ�����
				if(pErrInfo.getException() != null){
					logger.error(pErrInfo.getContent().toString());
					throw new Exception(pErrInfo.getContent().toString());
				}else{
					throw new Exception(pErrInfo.toShortString());
				}
			}

			//���پֲ�����
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
				
				//����
				if(archivesInfo.getRowFieldsValues().get(key) != null ){
					
					String value = formMap.get(key).toString();
					FieldValue fieldValue = archivesInfo.getRowFieldsValues().get(key);
					
					if(fieldValue.getMandatory() == true && "".equals(value) 
							&& !(EnumSystemDataItem.�ڲ����.getEnumValue()).equals(key) 
							&& !(EnumSystemDataItem.����������ڲ����.getEnumValue()).equals(key) 
							&& !(EnumSystemDataItem.����ҳ��.getEnumValue()).equals(key)){
						
						pFlag = false;
						pErrInfo.getContent().append(fieldValue.getDisplayText()+"�Ƿ�Ϊ��");
						break;
					}else{
						archivesInfo.getRowFieldsValues().get(key).setValue(value);
					}
				}		
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
				
				//�쳣�ʹ�����
				if(pErrInfo.getException() != null){
					logger.error(pErrInfo.getContent().toString());
					throw new Exception(pErrInfo.getContent().toString());
				}else{
					throw new Exception(pErrInfo.toShortString());
				}
			}

			//���پֲ�����
			throwable = null;
		}
		return pFlag;
	}
	
	
	/**
	 * ��¼���ͨ��
	 * @param NBXH ��˵ĵ����ڲ����
	 * @return ����İ����ڲ����
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
					pErrInfo.getContent().append("�õ���������ʧ�ܣ�");
				}else{
					archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID);
				}
			}
			
			//�õ��ڲ���ŵļ��Ϲ���ҵ���߼����赵����Ϣ���󼯺�
			if(pFlag){
				pErrPos = 5;
				if(NBXH ==0){
					pFlag = false;
					pErrInfo.getContent().append("û�еõ������ļ��ڲ���ŵļ���");
				}else{
					archivesInfo = ArchivesInfoFactory.newArchivsInfo(archivesTypeID, NBXH);
				}			
			}

			//���õ�����Ϣ������Ϊ��¼���
			if (pFlag) {	
				if (archivesInfoWorkingManageService.inputCheckPass(userInfo.getUserID(), archivesType, archivesInfo,  pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"���õ���������Ϊ��¼������ʧ��");
				}
			}
			
			if (pFlag) {
				success = 1;
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
				
				//�쳣�ʹ�����
				if(pErrInfo.getException() != null){
					logger.error(pErrInfo.getContent().toString());
					throw new Exception(pErrInfo.getContent().toString());
				}else{
					throw new Exception(pErrInfo.toShortString());
				}
			}
			//���پֲ�����
			throwable = null;
		}
		return success;
	}
	
	/**
	 * ��¼��˴��
	 * @param NBXH ��˵ĵ����ڲ����
	 * @return ����İ����ڲ����
	 * @throws Exception 
	 */
	public int checkBack(int archivesTypeID , int NBXH , String backReason,HttpSession session) throws Exception {
		System.out.println("------��¼��˴��---------------------------");
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
					pErrInfo.getContent().append("�õ���������ʧ�ܣ�");
				}else{
					archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID);
				}
			}
			
			//�õ��ڲ���ŵļ��Ϲ���ҵ���߼����赵����Ϣ���󼯺�
			if(pFlag){
				pErrPos = 5;
				if(NBXH ==0){
					pFlag = false;
					pErrInfo.getContent().append("�õ������ڲ����ʧ��");
				}else{
					archivesInfo = ArchivesInfoFactory.newArchivsInfo(archivesTypeID, NBXH);
				}			
			}

			//���õ�����Ϣ������Ϊ��¼���
			if (pFlag) {	
				if (archivesInfoWorkingManageService.inputCheckBack(userInfo.getUserID(), archivesType, archivesInfo, backReason, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"���õ���������Ϊ��¼��˴��ʧ��");
				}
			}
			
			if (pFlag) {
				success = 1;
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
				
				//�쳣�ʹ�����
				if(pErrInfo.getException() != null){
					logger.error(pErrInfo.getContent().toString());
					throw new Exception(pErrInfo.getContent().toString());
				}else{
					throw new Exception(pErrInfo.toShortString());
				}
			}
			//���پֲ�����
			throwable = null;
		}
		return success;
	}
	
	
	/**
	 * ���õ�����Ϣ�޸���־
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public int updateArchivesInfoFixedFlag(int archivesTypeID,int NBXH, HttpSession session) throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		
		ArchivesType archivesType = null;
		try {
			//��ʼ���� 1...
			pErrPos = 1;
			archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID);
			if (pFlag) {
				pErrPos = 2;
				if (archivesInfoWorkingManageService.updateArchivesInfoFixedFlag(archivesType, NBXH, pErrInfo)== false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"���õ�����Ϣ�޸���־ʧ��");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
				
				//�쳣�ʹ�����
				if(pErrInfo.getException() != null){
					logger.error(pErrInfo.getContent().toString());
					throw new Exception(pErrInfo.getContent().toString());
				}else{
					throw new Exception(pErrInfo.toShortString());
				}
			}

			//���پֲ�����
			throwable = null;
		}
		return NBXH;
	}
	
	/**
	 * �õ��������Ͷ���
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
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
				
				//�쳣�ʹ�����
				if(pErrInfo.getException() != null){
					logger.error(pErrInfo.getContent().toString());
					throw new Exception(pErrInfo.getContent().toString());
				}else{
					throw new Exception(pErrInfo.toShortString());
				}
			}
			//���پֲ�����
			throwable = null;
		}
		return archivesType;
	}
	
	/**
	 * �õ��������ԭ��
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
				pErrInfo.getContent().insert(0, "���ҵ�����Ϣʧ��");
				System.out.println(pErrInfo.toString());
			}
			
			if (pFlag) {
				backReason = archivesInfo.getSendBackReason();
			}
			
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
			//���پֲ�����
			throwable = null;
		}
		return backReason;
	}
}
