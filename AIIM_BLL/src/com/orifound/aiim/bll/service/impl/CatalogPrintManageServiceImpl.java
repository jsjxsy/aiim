/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.IArchivesInfoSavedManageService;
import com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService;
import com.orifound.aiim.bll.service.ICatalogPrintManageService;
import com.orifound.aiim.bll.service.IOfficialArchivesInfoManageService;
import com.orifound.aiim.bll.service.IPaperTransferManageService;
import com.orifound.aiim.bll.service.IStoreroomArchivesInfoManageService;
import com.orifound.aiim.bll.util.CommonUtil;
import com.orifound.aiim.bll.util.StringTool;
import com.orifound.aiim.dal.dao.ICatalogPrintDao;
import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.CatalogDataItem;
import com.orifound.aiim.entity.EnumCatalogType;
import com.orifound.aiim.entity.EnumCheckResult;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.OfficialArchivesInfo;
import com.orifound.aiim.entity.OfficialArchivesType;
import com.orifound.aiim.entity.PaperTransferBatchesDetail;
import com.orifound.aiim.entity.StoreroomArchivesInfo;

/**
 * Ŀ¼��ӡ�������ʵ����
 *
 */
public class CatalogPrintManageServiceImpl implements ICatalogPrintManageService {
	
	/**
	 * ע��Ŀ¼��ӡdao
	 */
	@Autowired
	private ICatalogPrintDao catalogPrintDao;
	
	/**
	 * ע�빫�ĵ����ǼǱ� ����������
	 */
	@Autowired
	private IOfficialArchivesInfoManageService officialArchivesInfoManageService;
	
	/**
	 * ע�뵵���ƽ�����������
	 */
	@Autowired
	private IPaperTransferManageService paperTransferManageService;
	
	/**
	 * ע��ⷿ������Ϣ�Ĺ���������
	 */
	@Autowired
	private IStoreroomArchivesInfoManageService storeroomArchivesInfoManageService;
	
	/**
	 * ע�뵵���鵵��Ϣ�Ĺ���������
	 */
	@Autowired
	private IArchivesInfoSavedManageService archivesInfoSavedManageService;
	
	/**
	 * ע�뵵���鵵�������еĵ�����Ϣ����������
	 */
	@Autowired
	private IArchivesInfoWorkingManageService archivesInfoWorkingManageService;
	
	/**
	 * ���캯��
	 */
	public CatalogPrintManageServiceImpl() {

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public CatalogPrintManageServiceImpl(ICatalogPrintDao catalogPrintDao) {
		this.catalogPrintDao = catalogPrintDao;
	}
	
	/**
	 * ����DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForCatalogPrint(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (catalogPrintDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
		}

		return pFlag;
	}
	
	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.ICatalogPrintManageService#findDataItemByCatalogType(com.orifound.aiim.entity.EnumCatalogType, java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findDataItemByCatalogType(ArchivesType archivesType, EnumCatalogType catalogType, LinkedHashMap<String, CatalogDataItem> catalogDataItems, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��鵵�������Ƿ��и�ֵ
			if (pFlag)
			{
				pErrPos = 2;
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����������Ϣδ��ʼ����");
				}else {
					if (archivesType.getID()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("���������ŷǷ�Ϊ0");
					}
				}
			}
			
			//��ȡ����������Ϣ
			if (pFlag)
			{
				pErrPos = 3;
				if (CommonUtil.getSystemInitializer().getPlaneArchivesTypes().containsKey(archivesType.getID())==false)
				{
					pFlag = false;
					pErrInfo.getContent().append("ϵͳ�в����ڱ��Ϊ "+archivesType.getID()+" �ĵ�������");
				}
				else
				{
					//���µ������������
					archivesType=CommonUtil.getSystemInitializer().getPlaneArchivesTypes().get(archivesType.getID());
					archivesType = CommonUtil.getTopArchivesType(archivesType);
				}
			}

			if (pFlag) {
				pErrPos = 2;
				catalogDataItems.putAll(archivesType.getCatalogPrintTemplates().get(catalogType));
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.ICatalogPrintManageService#findArchivesinfoForTransferCatalog(com.orifound.aiim.entity.ArchivesType, int, boolean, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesinfoForTransferCatalog(String depaermentName, String paperTransferBatNo,ArchivesType archivesType, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForCatalogPrint(pErrInfo) == false) {
				pFlag = false;
			}
			
			//��ⲿ�������Ƿ�Ϊ��
			if (pFlag) {
				if (StringTool.checkNull(depaermentName) == false) {
					pFlag = false;
					pErrInfo.getContent().append("�������ƷǷ�Ϊ�ա�");
				}
			}
			
			//������κ��Ƿ�Ϊ��
			if (pFlag) {
				if (StringTool.checkNull(paperTransferBatNo) == false) {
					pFlag = false;
					pErrInfo.getContent().append("���κŷǷ�Ϊ�ա�");
				}
			}
			
			//��鵵�������Ƿ��и�ֵ
			if (pFlag)
			{
				pErrPos = 2;
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����������Ϣδ��ʼ����");
				}else {
					if (archivesType.getID()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("���������ŷǷ�Ϊ0");
					}
				}
			}
			
			//��ȡ����������Ϣ
			if (pFlag)
			{
				pErrPos = 3;
				if (CommonUtil.getSystemInitializer().getPlaneArchivesTypes().containsKey(archivesType.getID())==false)
				{
					pFlag = false;
					pErrInfo.getContent().append("ϵͳ�в����ڱ��Ϊ "+archivesType.getID()+" �ĵ�������");
				}
				else
				{
					//���µ������������
					archivesType=CommonUtil.getSystemInitializer().getPlaneArchivesTypes().get(archivesType.getID());
				}
			}

			//�ƽ�Ŀ¼��ӡ ��ѯ������Ϣ
			if (pFlag) {
				pErrPos = 2;
				//�����Ե�������id
				if (catalogPrintDao.findArchivesinfoForTransferCatalog(depaermentName ,paperTransferBatNo, archivesType, archivesInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�ƽ�Ŀ¼��ӡ ��ѯ������Ϣ ʧ�ܣ�");
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
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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

	@Override
	public boolean findArchivesinfoForBoxCatalog(String boxBarcode, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForCatalogPrint(pErrInfo) == false) {
				pFlag = false;
			}
			
			//�����������Ƿ�Ϊ��
			if (pFlag) {
				if (StringTool.checkNull(boxBarcode) == false) {
					pFlag = false;
					pErrInfo.getContent().append("������Ƿ�Ϊ�ա�");
				}
			}
			
			//��⵵����Ϣ�����Ƿ�Ϊ��
			if (pFlag) {
				if (archivesInfos == null) {
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ���ϷǷ�Ϊ�ա�");
				}
			}
			
			ArchivesType archivesType = null;
			if (pFlag) {
				//���ݺ�������ҵ�����Ϣ
				List<StoreroomArchivesInfo> storeroomArchivesInfos = new ArrayList<StoreroomArchivesInfo>();
				if (storeroomArchivesInfoManageService.findByBoxBarcode(boxBarcode, storeroomArchivesInfos , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ݺ�������ҵ�����Ϣ ʧ�ܣ�");
				}
				//������ڿⷿ������Ϣ
				if(storeroomArchivesInfos.size() >= 1) {
					archivesType = new ArchivesType(storeroomArchivesInfos.get(0).getArchivesTypeID());
				}
			}
						
			//��ȡ����������Ϣ
			if (pFlag && archivesType!=null)
			{
				pErrPos = 3;
				if (CommonUtil.getSystemInitializer().getPlaneArchivesTypes().containsKey(archivesType.getID())==false)
				{
					pFlag = false;
					pErrInfo.getContent().append("ϵͳ�в����ڱ��Ϊ "+archivesType.getID()+" �ĵ�������");
				}
				else
				{
					//���µ������������
					archivesType=CommonUtil.getSystemInitializer().getPlaneArchivesTypes().get(archivesType.getID());
				}
			}

			if (pFlag) {
				pErrPos = 2;
				//����Ŀ¼ ��ӡ��ѯ
				if (catalogPrintDao.findArchivesinfoForBoxCatalog(boxBarcode, 4, archivesType, archivesInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "����Ŀ¼ ��ӡ��ѯ ʧ�ܣ�");
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
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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

	@Override
	public boolean findArchivesinfoForEnelope(int pNBXH, ArchivesType archivesType, ArchivesInfo archivesInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForCatalogPrint(pErrInfo) == false) {
				pFlag = false;
			}
			
			//����ڲ�����Ƿ�Ϊ��
			if (pFlag) {
				if (pNBXH <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("�ڲ���ŷǷ�Ϊ�ա�");
				}
			}
			
			//��鵵�������Ƿ��и�ֵ
			if (pFlag)
			{
				pErrPos = 2;
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����������Ϣδ��ʼ����");
				}else {
					if (archivesType.getID()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("���������ŷǷ�Ϊ0");
					}
				}
			}
			
			//��ȡ����������Ϣ
			if (pFlag)
			{
				pErrPos = 3;
				if (CommonUtil.getSystemInitializer().getPlaneArchivesTypes().containsKey(archivesType.getID())==false)
				{
					pFlag = false;
					pErrInfo.getContent().append("ϵͳ�в����ڱ��Ϊ "+archivesType.getID()+" �ĵ�������");
				}
				else
				{
					//���µ������������
					archivesType=CommonUtil.getSystemInitializer().getPlaneArchivesTypes().get(archivesType.getID());
				}
			}

			//��Ƥ��ӡ��ѯ �����ڲ���Ų��ҵ�����Ϣ
			if (pFlag) {
				pErrPos = 2;
				if (catalogPrintDao.findArchivesinfoForEnelope(pNBXH, archivesType, archivesInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��Ƥ��ӡ��ѯ �����ڲ���Ų��ҵ�����Ϣ ʧ�ܣ�");
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
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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

	@Override
	public boolean findArchivesinfoForFileCatalog(ArchivesType archivesType, ArchivesInfo archivesInfo, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForEntityClassName(pErrInfo) == false) {
				pFlag = false;
			}
			
			//��⵵����Ϣ�����Ƿ�Ϊ��
			if (pFlag) {
				if (archivesInfos == null) {
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ���ϷǷ�Ϊ�ա�");
				}
			}
			
			//��⵵����Ϣ�Ƿ�Ϊ��
			if (pFlag) {
				if (archivesInfo == null) {
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ�Ƿ�Ϊ�ա�");
				}
			}
			
			//�ж��ڲ�����Ƿ�Ϊ��
			int NBXH = Integer.valueOf(archivesInfo.getKeyInCol().toString());
			if (pFlag) {
				if (NBXH <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("�����ڲ���ŷǷ�Ϊ�ա�");
				}
			}
			if (pFlag) {
				pErrPos = 4;
				//���ݵ����ĵ����Ƿ�Ϊ�� �жϲ�ѯ����
				if (archivesInfo.getTag()!=null && "y".equals(archivesInfo.getTag().toString())) {	//�����ڲ���Ų��ҵ����鵵��Ϣ
					
					if (archivesInfoSavedManageService.findChildrenByNBXH(NBXH, archivesType, archivesInfos, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "�����ڲ���Ų��ҵ�����Ϣ ʧ�ܣ�");
					}
					
				} else {	//�����ڲ���Ų��ҵ����鵵�������еĵ�����Ϣ
					if (archivesInfoWorkingManageService.findChildArchivesInfosByNBXH(NBXH, archivesType, archivesInfos, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "�����ڲ���Ų��ҵ����鵵�������еĵ�����Ϣ ʧ�ܣ�");
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
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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

	@Override
	public boolean findArchivesinfoForFileRetrieval() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findArchivesinfoForTransferList(String paperTransferBatNo,ArchivesType archivesType,List<PaperTransferBatchesDetail> paperTransferBatchesDetails,List<EnumCheckResult> enumCheckResults, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForEntityClassName(pErrInfo) == false) {
				pFlag = false;
			}

			//��鵵�������Ƿ��и�ֵ
			if (pFlag)
			{
				pErrPos = 2;
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����������Ϣδ��ʼ����");
				}else {
					if (archivesType.getID()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("���������ŷǷ�Ϊ0");
					}
				}
			}
			
			//��ȡ����������Ϣ
			if (pFlag)
			{
				pErrPos = 3;
				if (CommonUtil.getSystemInitializer().getPlaneArchivesTypes().containsKey(archivesType.getID())==false)
				{
					pFlag = false;
					pErrInfo.getContent().append("ϵͳ�в����ڱ��Ϊ "+archivesType.getID()+" �ĵ�������");
				}
				else
				{
					//���µ������������
					archivesType=CommonUtil.getSystemInitializer().getPlaneArchivesTypes().get(archivesType.getID());
				}
			}
			
			if (pFlag) {
				if(paperTransferManageService.findPaperTransferBatchesDetails(paperTransferBatNo, archivesType, paperTransferBatchesDetails,enumCheckResults, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().append("��ѯ���ε�����ϸ��Ϣʧ��");
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
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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

	@Override
	public boolean findArchivesinfoForofficialArchivesInfoCatalog(OfficialArchivesType officialArchivesType, List<OfficialArchivesInfo> officialArchivesInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForEntityClassName(pErrInfo) == false) {
				pFlag = false;
			}
			
			//��鹫�������Ƿ�Ϊ��
			if (pFlag) {
				if(officialArchivesType==null || officialArchivesType.getID() <=0 ) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�������ͷǷ�Ϊ�ա�");
				}
			}
			
			if (pFlag) {
				pErrPos = 2;
				//���ݹ������Ͳ�ѯ������Ϣ
				if (officialArchivesInfoManageService.findAll(officialArchivesType, officialArchivesInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ݹ������Ͳ�ѯ������Ϣ ʧ�ܣ�");
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
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
	 * ���Entity��ҵ���߼���������ע�루BLL Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkBllInjectionForEntityClassName(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//����Ƿ��н���BLLҵ���߼����������ע��
			pErrPos = 1;
			if (officialArchivesInfoManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append(
						"���ĵ����ǼǱ��BLLҵ���߼�����Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
			
			if (paperTransferManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append(
						"�����ƽ���BLLҵ���߼�����Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
			
			if (storeroomArchivesInfoManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append(
						"�ⷿ������Ϣ��BLLҵ���߼�����Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
			
			if (archivesInfoSavedManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append(
						"�����鵵��Ϣ��BLLҵ���߼�����Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
			
			if (archivesInfoWorkingManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append(
						"�����鵵�������BLLҵ���߼�����Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
		}

		return pFlag;
	}

}
