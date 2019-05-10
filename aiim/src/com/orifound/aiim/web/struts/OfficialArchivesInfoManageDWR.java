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
	 * @param officialArchivesTypeId ���ĵ�������
	 * @param NBXH �ڲ�ѡ��
	 * @param session httpsession
	 * @return ����HTML����
	 * @throws Exception �쳣
	 */
	public String getHtmlCode(int officialArchivesTypeId,int NBXH,HttpSession session) throws Exception {
		System.out.println("----�õ�������¼html����---------------------------");
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
				pErrInfo.getContent().append("�˷����¿���¼������Ϊ�գ�");
			}
			if(pFlag){
				pErrPos = 3;
				//������
				if(NBXH==0){
					htmlCode = GenerateHtmlCodeUtil.GenerateOfficialArchivesHtmlCode(dataItems, null,null);
				}else{//˫���༭
					OfficialArchivesType officialArchiveType= SystemInitializer.getInstance().getOfficialArchivesTypes().get(officialArchivesTypeId);
					OfficialArchivesInfo officialArchivesInfo = new OfficialArchivesInfo(officialArchiveType);
					pFlag = officialArchivesInfoManageService.findOfficialArchivesInfoByNBXH(NBXH, officialArchiveType, officialArchivesInfo, pErrInfo);
					htmlCode = GenerateHtmlCodeUtil.GenerateOfficialArchivesHtmlCode(dataItems, officialArchivesInfo,null);
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
	 * ���湫�ĵ�����Ϣ
	 * @param officialArchivesTypeId ���浵�����ͱ��
	 * @param formMap ��Map����
	 * @param session session����
	 * @param application application����
	 * @return NBXH �ڲ���� (����Ҫɾ������ӵĵ�����Ϣ��Ҫ�õ�NBXH�������һ�����ĵ�����Ϣʱ��û��NBXH���ʷ���NBXH)
	 * @throws Exception
	 */
	public int saveOfficialArchivesInfo(int officialArchivesTypeId,Map<String,String> formMap,HttpSession session) throws Exception{
		System.out.println("-----���湫�ĵ�����Ϣ-----------------------------------");
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
				pErrInfo.getContent().append("�õ�������ʧ�ܣ�");
			}
			
			if(pFlag){
				pErrPos = 2;
				if(officialArchivesTypeId == 0){
					pFlag = false;
					pErrInfo.getContent().append("�õ����ĵ���������Ϣʧ�ܣ�");
				}else{
					officialArchivesType = new OfficialArchivesType();
					officialArchivesType.setID(officialArchivesTypeId);
				}
			}
		
			if(pFlag){
				pErrPos = 3;
				if((userId = ((UserInfo)session.getAttribute("userInfo")).getUserID()) ==0){
					pFlag = false;
					pErrInfo.getContent().append("�õ��û�IDʧ�ܣ�");
				}
			}
			//��ȡ������Ϣ
			if (pFlag) {
				pErrPos = 4;
				if((officialArchivesInfo = OfficialArchivesInfoFactory.newOfficialArchivsInfo(officialArchivesTypeId))==null){
					pFlag = false;
					pErrInfo.getContent().append("��ȡ������Ϣʧ�ܣ�");
				}
			}
			//���õ�ǰ����
			if (pFlag) {
				pErrPos = 5;
				if((formationDepartmentID = ((UserInfo)session.getAttribute("userInfo")).getDepartmentID()) ==0){
					pFlag = false;
					pErrInfo.getContent().append("formationDepartmentID�ܣ�");
				}else{
					officialArchivesInfo.setFormationDepartmentID(formationDepartmentID);
				}
			}
			
			//�õ������ݹ���dataItem���ϲ�����officialArchivesInfo
			if (pFlag) {
				pErrPos = 6;
				if (this.getOfficialArchivesInfo(formMap, officialArchivesInfo, pErrInfo) == false) {
					pFlag = false;
				}
			}
			
			//����ҵ���߼����浵��
			if(pFlag){
				pErrPos = 7;
				if(officialArchivesInfoManageService.saveOfficialArchivesInfo(userId, officialArchivesType, EnumOfficialArchivesInfoTableType.���ĵ����ǼǱ�, officialArchivesInfo, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"���湫�ĵ�����Ϣʧ��");
				}
			}

			if (pFlag) {
				NBXH = officialArchivesInfo.getNBXH();
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
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");

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
	
	//formMap�����ݴ�ŵ�officialArchivesInfo��
	private boolean getOfficialArchivesInfo(Map<String, String> formMap, OfficialArchivesInfo officialArchivesInfo, ErrInfo pErrInfo){
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			for (Iterator<String> iterator = formMap.keySet().iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				
				//����
				if(officialArchivesInfo.getRowFieldsValues().get(key) != null ){
					
					String value = formMap.get(key).toString();
					FieldValue fieldValue = officialArchivesInfo.getRowFieldsValues().get(key);
					
					if(fieldValue.getMandatory() == true && "".equals(value) 
							&& !(EnumSystemDataItem.�ڲ����.getEnumValue()).equals(key) 
							&& !(EnumSystemDataItem.����ҳ��.getEnumValue()).equals(key)){
						
						pFlag = false;
						pErrInfo.getContent().append(fieldValue.getDisplayText()+"�Ƿ�Ϊ��");
						break;
					}else{
						officialArchivesInfo.getRowFieldsValues().get(key).setValue(value);
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
			}

			//���پֲ�����
			throwable = null;
		}
		return pFlag;
	}
	
	/**
	 * ���¹��ĵ�����Ϣ
	 * 
	 * @param userID �û���
	 * @param officialArchivesInfo ���ĵ�����Ϣ
	 * @return
	 */
	public int updateOfficialArchivesInfo(int officialArchivesTypeId, Map<String, String> formMap,HttpSession session) throws Exception{
		System.out.println("---���µ�����Ϣ--------------------------------------");
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
				pErrInfo.getContent().append("�õ�������ʧ�ܣ�");
			}
			
			if(pFlag){
				pErrPos = 2;
				if(officialArchivesTypeId == 0){
					pFlag = false;
					pErrInfo.getContent().append("�õ����ĵ���������Ϣʧ�ܣ�");
				}else{
					officialArchivesType = new OfficialArchivesType();
					officialArchivesType.setID(officialArchivesTypeId);
				}
			}
			
			
			
			
			//�õ������ݹ���dataItem���ϲ�����offiicalArchivesInfo
			if(pFlag){
				pErrPos = 5;
				officialArchivesInfo = OfficialArchivesInfoFactory.newOfficialArchivsInfo(officialArchivesTypeId);
				officialArchivesInfo.setNBXH(Integer.parseInt(formMap.get(EnumSystemDataItem.�ڲ����.getEnumValue())));
				
				if (pFlag) {
					pErrPos = 6;
					if((formationDepartmentID = ((UserInfo)session.getAttribute("userInfo")).getDepartmentID()) ==0){
						pFlag = false;
						pErrInfo.getContent().append("formationDepartmentID�ܣ�");
					}else{
						officialArchivesInfo.setFormationDepartmentID(formationDepartmentID);
					}
				}
				if(pFlag){
					pErrPos = 7;
					int userId=0;
					if((userId = ((UserInfo)session.getAttribute("userInfo")).getUserID()) ==0){
						pFlag = false;
						pErrInfo.getContent().append("�õ��û�IDʧ�ܣ�");
					}else{
						officialArchivesInfo.setRegUserID(userId);
					}
				}
				
				//�õ������ݹ���dataItem���ϲ�����archivesInfo
				if (pFlag) {
					if (this.getOfficialArchivesInfo(formMap, officialArchivesInfo, pErrInfo) == false) {
						pFlag = false;
					}
				}
			}

			//����ҵ���߼����µ�����Ϣ
			if(pFlag){
				pErrPos = 6;
				if(officialArchivesInfoManageService.updateOfficialArchivesInfo(officialArchivesType, EnumOfficialArchivesInfoTableType.getEnumElement(officialArchivesTypeId), officialArchivesInfo, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "���µ�����Ϣʧ��");
				}
			}

			if (pFlag) {
				NBXH = officialArchivesInfo.getNBXH();
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
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
				System.out.println(pErrInfo.getContent().toString());
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
	 * @param officalArchivesInfo ���ĵ�����Ϣ
	 * @return nbxh �ڲ����
	 * @throws Exception �쳣
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
				pErrInfo.getContent().append("�õ����ĵ�������IDʧ��");
			}else{
				officialArchivesType = SystemInitializer.getInstance().getOfficialArchivesTypes().get(officialArchivesTypeId);
			}
			
			if (pFlag) {
				if (NBXH ==0) {
					pFlag = false;
					pErrInfo.getContent().append("�õ����ĵ����ڲ����ʧ��");
				}else{
					officialArchivesInfo = new OfficialArchivesInfo(officialArchivesType);
					officialArchivesInfo.setNBXH(NBXH);
				}
			}
			
			//����ҵ��i�߼�ɾ��������Ϣ
			if (pFlag) {
				pErrPos = 2;
				if(officialArchivesInfoManageService.deleteOfficialArchivesInfo(officialArchivesType, EnumOfficialArchivesInfoTableType.getEnumElement(officialArchivesTypeId), officialArchivesInfo, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"ɾ�����ĵ�����Ϣʧ��");
				}else{
					result = officialArchivesInfo.getNBXH();
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
	 * ���й��ĵ�����Ϣ�鵵
	 * @return �Ƿ�ɹ�
	 * @throws Exception �쳣
	 */
	public boolean archiviedOfficialArchivesInfos(int NBXH,int officialArchivesTypeID,int archivesTypeID,int childArchivesTypeID,String archivesFondID,HttpSession session) throws Exception {
		System.out.println("archiviedOfficialArchivesInfos");
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo=new ErrInfo();
		
		try {
			//��ʼ���� 1...
			pErrPos = 1;
			//�ж�NBXH�Ƿ�Ϊ��
			if (NBXH == 0) {
				pFlag=false;
				pErrInfo.getContent().append("NBXH�Ƿ�Ϊ��!");
			}
			//�ж�officialArchivesTypeID�Ƿ�Ϊ��
			if (officialArchivesTypeID == 0) {
				pFlag=false;
				pErrInfo.getContent().append("officialArchivesTypeID�Ƿ�Ϊ��!");
			}
			
			if (archivesTypeID == 0) {
				pFlag=false;
				pErrInfo.getContent().append("archivesTypeID�Ƿ�Ϊ��!");
			}
			
			if (archivesFondID == null || archivesFondID.equals("")) {
				pFlag=false;
				pErrInfo.getContent().append("archivesFondID�Ƿ�Ϊ��!");
			}
			//��������---->һ������
			ArchivesType archivesType=SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID);
			//���ĵ�������Ĺ鵵ӳ���ϵ��ʵ����
			OfficialArchivesTypeSavedMapping officialArchivesTypeSavedMapping=SystemInitializer.getInstance().getOfficialArchivesTypes().get(officialArchivesTypeID).getOfficialArchivesTypeSavedMappings().get(archivesType.getID());
			//��ѯ���ĵ�����Ϣ
			OfficialArchivesInfo officialArchivesInfo=OfficialArchivesInfoFactory.newOfficialArchivsInfo(officialArchivesTypeID);
			OfficialArchivesType officialArchivesType=SystemInitializer.getInstance().getOfficialArchivesTypes().get(officialArchivesTypeID);
			if(officialArchivesInfoManageService.findOfficialArchivesInfoByNBXH(NBXH, officialArchivesType, officialArchivesInfo, pErrInfo)==false){
				pErrPos = 2;
				pErrInfo.getContent().append("officialArchivesInfo���ĵ�����Ϣ����ʧ��!");
			}
		
		    ArchivesInfo archivesInfo=ArchivesInfoFactory.newArchivsInfo(archivesTypeID);
		    
			Map<String, ArchivesTypeDataItem> archivesTypeDataItems=archivesType.getDataItemsForAll();
			//����ȫ�ڱ��
			archivesInfo.setArchivesFondsID(archivesFondID);
			//���õ������ͱ��
			archivesInfo.setArchivesTypeID(childArchivesTypeID);
			//ȫ�ڱ��
			archivesInfo.setArchivesFondsID(archivesFondID);
			
			for (ArchivesTypeDataItem archivesTypeDataItem : archivesTypeDataItems.values()) {
				//�鵵ʱ��û��ӳ����ֶ�����ΪĬ��ֵ
				if(archivesTypeDataItem.getColumnName().equals(EnumSystemDataItem.������������״̬.getEnumValue()) ){
					archivesInfo.setWorkFlowStatus(EnumWorkFlowStatus.��¼���);
				}
				
				if(officialArchivesTypeSavedMapping.getOfficialArchivesDataItemSavedMapping().containsKey(archivesTypeDataItem.getDataItemID())){
					String colName=officialArchivesTypeSavedMapping.getOfficialArchivesDataItemSavedMapping().get(archivesTypeDataItem.getDataItemID()).getColumnName();
					if (officialArchivesInfo.getRowFieldsValues().containsKey(colName))
					{
						archivesInfo.getRowFieldsValues().get(archivesTypeDataItem.getColumnName()).setValue(officialArchivesInfo.getRowFieldsValues().get(colName).getValue());
					}
					else {
						pFlag = false;
						pErrInfo.getContent().append("����������鵵ӳ���ϵ�е�������("+colName+")�ڶ�Ӧ���ķ������������в����ڡ�");
					}
				}
					
			}
			if(archivesInfoWorkingManageService.saveArchivesInfo(officialArchivesInfo.getRegUserID(), archivesType, EnumArchivesInfoType.�ļ�������, archivesInfo, pErrInfo)==false){
				pFlag=false;
				pErrInfo.getContent().insert(0, "���Ĺ鵵��Ϣ����ʧ��: ");
			}else{
				System.out.println("�鵵�ɹ�!");
				System.out.println(EnumSystemDataItem.�鵵��־.getEnumValue());
				officialArchivesInfo.setSavedFlag(true);
				if(officialArchivesInfoManageService.updateOfficialArchivesInfo(officialArchivesType, EnumOfficialArchivesInfoTableType.���ĵ����ǼǱ�, officialArchivesInfo, pErrInfo)==false){
					pFlag=false;
					pErrInfo.getContent().insert(0, "���¹��ĵ�����Ϣ�鵵��ʾʧ��:");
				}else{
					System.out.println("<->"+officialArchivesInfo.getSavedFlag());
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
			}
			//���پֲ�����
			throwable = null;
		}

		return pFlag;
	}
	
	/**
	 * ���й��ĵ�����Ϣ�鵵
	 * @return �Ƿ�ɹ�
	 * @throws Exception �쳣
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
						pErrInfo.getContent().insert(0, "�������Ĺ鵵��Ϣ����ʧ��: ");
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
			}
			//���پֲ�����
			throwable = null;
		}

		return pFlag;
	}
	
	//��ѯ���������µ��ӷ��� ,ArchivesTypeIDΪ�ؼ��֣�archivesNameΪֵ
	public Map<Integer,String> findchildArchivesTypeByArchivesTypeID(int archivesTypeID){
		Map<Integer, ArchivesType> archivesTypes=SystemInitializer.getInstance().getArchivesTypes().get(archivesTypeID).getChildArchivesTypes();
		Map<Integer,String> result=new HashMap<Integer, String>();
		for (ArchivesType archivesType : archivesTypes.values()) {
			result.put(archivesType.getID(), archivesType.getName());
		}
		
		return result;
	}
}
