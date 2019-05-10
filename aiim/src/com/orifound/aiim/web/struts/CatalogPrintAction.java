package com.orifound.aiim.web.struts;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.ICatalogPrintManageService;
import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ArchivesTypeDataItem;
import com.orifound.aiim.entity.CatalogDataItem;
import com.orifound.aiim.entity.EnumCatalogType;
import com.orifound.aiim.entity.EnumCheckResult;
import com.orifound.aiim.entity.EnumSystemDataItem;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.OfficialArchivesInfo;
import com.orifound.aiim.entity.OfficialArchivesType;
import com.orifound.aiim.entity.PaperTransferBatchesDetail;
import com.orifound.aiim.entity.PrintPageSet;
import com.orifound.aiim.entity.SystemInitializer;

/**
 * Ŀ¼��ӡAction
 *
 */
public class CatalogPrintAction extends BaseAction {

	private static final long serialVersionUID = 4101833970440922557L;
	
	/**
	 * Ŀ¼��ӡ���������
	 */
	@Autowired
	private ICatalogPrintManageService catalogPrintManageService;
	
	/**
	 * ����Action��תURL��ַ
	 */
	private String resultURL;
	
	/**
	 * ��������id
	 */
	private int archivesTypeID;
	
	/**
	 * ��ӡ��ѯ ������Ϣ����
	 */
	private List<ArchivesInfo> archivesInfos = new ArrayList<ArchivesInfo>();
	
	/**
	 * ��ӡĿ¼����id
	 */
	private int catalogTypeID;
	
	/**
	 * Ŀ¼��ӡģ������
	 */
	private EnumCatalogType catalogType;
	
	/**
	 * ��ʾ��ӡ�������
	 */
	private LinkedHashMap<String, CatalogDataItem> catalogDataItems = new LinkedHashMap<String, CatalogDataItem>();
	
	/**
	 * ��ӡҳ�����ò���ʵ��
	 */
	private PrintPageSet printPageSet = new PrintPageSet();
	
	/**
	 * ��������id
	 */
	private int officialArchivesTypeID;
	
	/**
	 * ��ӡ��ʾ�ֶ���������
	 */
	private String[] columnName;
	
	/**
	 * ҳ����ʾ�������
	 */
	private List<ArchivesTypeDataItem> viewDataItems = new ArrayList<ArchivesTypeDataItem>();
	
	/**
	 * �ƽ����κ�
	 */
	private String batNo;
	
	/**
	 * ������
	 */
	private String boxBarcode;
	
	/**
	 * �Ƿ�鵵
	 * y:�鵵,n:δ�鵵
	 */
	private String isArchived;
	
	/**
	 * ���� �ڲ����
	 */
	private int NBXH;
	
	/**
	 * ��ӡ����Ŀ¼ �������� 
	 */
	private String depaermentName;

	/**
	 * Ŀ¼��ӡ����
	 * @return String
	 */
	public String printConfig() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForBll(pErrInfo ) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				//��ʼ��Ŀ¼��ӡģ������
				catalogType = EnumCatalogType.getEnumElement(catalogTypeID);
				
				//����Ŀ¼��ӡ
				if(catalogTypeID == EnumCatalogType.����Ŀ¼.getEnumValue()) {
					System.out.println("����Ŀ¼��ӡ����");
					if (officialArchivesTypeID <= 0) {
						pFlag = false;
						pErrInfo.getContent().append("����Ŀ¼��ӡ->��������id�Ƿ�Ϊ�ա�");
					}
					if (pFlag) {
						OfficialArchivesType officialArchivesType = SystemInitializer.getInstance().getOfficialArchivesTypes().get(officialArchivesTypeID);
						catalogDataItems = officialArchivesType.getCatalogPrintTemplates().get(catalogType);
					}
					
					//�ǹ���Ŀ¼��ӡ
				} else if(archivesTypeID>=1) {		
					ArchivesType archivesType = new ArchivesType(archivesTypeID);
					//����Ŀ¼��ӡģ�����Ͳ�ѯ��ӡ��ʾ���������
					if (catalogPrintManageService.findDataItemByCatalogType(archivesType , catalogType , catalogDataItems, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "����Ŀ¼��ӡģ�����Ͳ�ѯ��ӡ��ʾ��������� ʧ�ܣ�");
					}
				}
				
				//����ӡ��������Ƿ�Ϊ��
				if (pFlag) {
					if (catalogDataItems == null) {
						pFlag = false;
						pErrInfo.getContent().append("Ŀ¼��ӡ->��ӡ������ϷǷ�Ϊ�ա�");
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
		resultURL = "/PRINT/print_config.jsp";
		return SUCCESS;
	}
	
	/**
	 * ��ӡĿ¼ѡ��
	 * @return
	 */
	public String switchCatalog() {
		System.out.println("----��ӡĿ¼ѡ��--------");
		System.out.println("catalogTypeID="+catalogTypeID);
		switch (catalogTypeID) {
		case 0:
			resultURL = "/PRINT/print_select.jsp";
			break;
			
			//����Ŀ¼	��ӡ��ѯ
		case 1:
			archivesCatalog();
			resultURL = "/PRINT/archivesCatalog.jsp";
			break;
			
			//����Ŀ¼	��ӡ��ѯ
		case 2:
			boxCatalog();
			resultURL = "/PRINT/boxCatalog.jsp";
			break;
			
			//����Ŀ¼	��ӡ��ѯ
		case 3:
			fileCatalog();
			resultURL = "/PRINT/fileCatalog.jsp";
			break;
			
			//����Ŀ¼	��ӡ��ѯ	
		case 4:
			officialArchivesInfoCatalogPrint();
			resultURL = "/PRINT/officialArchivesInfoCatalog.jsp";
			break;
		
			//������		��ӡ��ѯ	
		case 5:
			appendixTablePrint();
			resultURL = "/PRINT/appendixTable.jsp";
			break;
			
			//��Ƥ	��ӡ��ѯ	
		case 6:
			envelopePrint();
			resultURL = "/PRINT/envelope.jsp";
			break;
			
			//����	��ӡ��ѯ	
		case 7:
			fileRetrieval();
			resultURL = "/PRINT/fileRetrieval.jsp";
			break;
		
		//�ƽ��嵥	��ӡ��ѯ
		case 11:
			transferList();
			resultURL = "/PRINT/transferList.jsp";
			
		default:
			break;
		}
		return SUCCESS;
	}
	
	/**
	 * ����Ŀ¼ ��ӡ��ѯ
	 * ҳ�洫�ݲ�����boxBarcode ������
	 */
	private void boxCatalog() {
		System.out.println("----����Ŀ¼ ��ӡ��ѯ--------");
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForBll(pErrInfo) == false) {
				pFlag = false;
			}
			
			if (pFlag) {
				pErrPos = 2;
				//������ӡҳ����ʾ�������
				this.buildDataItem();
				
				pErrPos = 3;
				//����Ŀ¼ ��ӡ��ѯ
				if (catalogPrintManageService.findArchivesinfoForBoxCatalog(boxBarcode, archivesInfos , pErrInfo) == false) {
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
	}

	/**
	 * �ƽ��嵥 ��ӡ��ѯ
	 * ҳ�洫�ݲ���: archivesTypeID	��������id
	 * 				batNo	���κ�
	 * 				stateType ����״̬
	 */
	public void transferList() {
		System.out.println("----�ƽ��嵥 ��ӡ��ѯ--------");
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		HttpServletRequest request = getRequest();
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForBll(pErrInfo ) == false) {
				pFlag = false;
			}

			ArchivesType archivesType = new ArchivesType(archivesTypeID);
			List<EnumCheckResult> enumCheckResults = new ArrayList<EnumCheckResult>();
			if("1".equals(request.getParameter("stateType"))){
				enumCheckResults.add(EnumCheckResult.��δ���);
			}else if("2".equals(request.getParameter("stateType"))){
				enumCheckResults.add(EnumCheckResult.��δ���);
				enumCheckResults.add(EnumCheckResult.ҵ��ָ�������ͨ��);
				enumCheckResults.add(EnumCheckResult.�������������ͨ��);
			}else if("3".equals(request.getParameter("stateType"))){//ҵ��ָ���Ҳ鿴��ǰ�嵥
				enumCheckResults.add(EnumCheckResult.��δ���);
				enumCheckResults.add(EnumCheckResult.ҵ��ָ�������ͨ��);
			}else if("4".equals(request.getParameter("stateType"))){
				enumCheckResults.add(EnumCheckResult.ҵ��ָ�������ͨ��);
				enumCheckResults.add(EnumCheckResult.�������������ͨ��);
			}else if("5".equals(request.getParameter("stateType"))){
				enumCheckResults.add(EnumCheckResult.ҵ��ָ�������ͨ��);
				enumCheckResults.add(EnumCheckResult.�������������ͨ��);
			}else if("6".equals(request.getParameter("stateType"))){
				enumCheckResults.add(EnumCheckResult.�������������ͨ��);
			}
			
			//���շ��ص�����Ϣ�б�
			List<PaperTransferBatchesDetail> paperTransferBatchesDetails = new ArrayList<PaperTransferBatchesDetail>();
			
			//����ҵ���߼�
			if (pFlag) {
				if(catalogPrintManageService.findArchivesinfoForTransferList(batNo, archivesType, paperTransferBatchesDetails, enumCheckResults, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "�ƽ��嵥 ��ӡ��ѯ ʧ�ܣ�");
				}
     		}
			request.setAttribute("paperTransferBatchesDetails", paperTransferBatchesDetails);
			
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
	}
	
	/**
	 * ����Ŀ¼	��ӡ��ѯ
	 * ҳ�洫�ݲ���:	archivesTypeID ��������id
	 * 				batNo	���κ�
	 * depaermentName ��������:DXBM(�����γɲ���)��YWZD(ҵ��ָ����)��DAGL(����������)
	 */
	public void archivesCatalog() {
		System.out.println("----����Ŀ¼	��ӡ��ѯ--------");
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForBll(pErrInfo ) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				
				//������ӡҳ����ʾ�������
				this.buildDataItem();
				
				//����Ŀ¼��ӡ ��ѯ������Ϣ
				ArchivesType archivesType = new ArchivesType(archivesTypeID);
				System.out.println();
				if (catalogPrintManageService.findArchivesinfoForTransferCatalog(depaermentName, batNo, archivesType, archivesInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "����Ŀ¼��ӡ ��ѯ������Ϣ ʧ�ܣ�");
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
		System.out.println(pErrInfo.toString());
	}
	
	/**
	 * ����Ŀ¼	��ӡ��ѯ
	 * ҳ�洫�ݲ���: isArchived	�Ƿ�鵵
	 * 				NBXH	�ڲ����
	 * 				archivesTypeID ��������id
	 */
	private void fileCatalog() {
		System.out.println("----����Ŀ¼	��ӡ��ѯ--------");
		System.out.println("NBXH="+NBXH);
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForBll(pErrInfo ) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				//������ӡҳ����ʾ�������
				this.buildDataItem();
				
				//����Ŀ¼��ӡ ��ѯ������Ϣ
				ArchivesType archivesType = new ArchivesType(archivesTypeID);
				ArchivesInfo archivesInfo = new ArchivesInfo();
				archivesInfo.setKeyInCol(NBXH);
				if("y".equals(isArchived)) {
					//��ѯ�ѹ鵵��Ϣ 
					archivesInfo.setTag("y");
				}
				//����Ŀ¼ ��ӡ��ѯ
				if (catalogPrintManageService.findArchivesinfoForFileCatalog(archivesType, archivesInfo, archivesInfos, pErrInfo) == false) {
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
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
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
	}
	
	/**
	 * ������ ��ӡ��ѯ
	 */
	private void appendixTablePrint() {
		
	}
	
	/**
	 * ��Ƥ��ӡ��ѯ
	 */
	private void envelopePrint() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForBll(pErrInfo ) == false) {
				pFlag = false;
			}
			
			ArchivesType archivesType = new ArchivesType(archivesTypeID);
			ArchivesInfo archivesInfo = new ArchivesInfo();
			if (pFlag) {
				pErrPos = 2;
				if (catalogPrintManageService.findArchivesinfoForEnelope(NBXH, archivesType, archivesInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action��Ƥ��ӡ��ѯ �����ڲ���Ų��ҵ�����Ϣ ʧ�ܣ�");
				}
				getRequest().setAttribute("archivesInfo", archivesInfo);
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
	}
	
	/**
	 * ���� ��ӡ��ѯ
	 */
	private void fileRetrieval() {
		System.out.println("----����	��ӡ��ѯ--------");
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForBll(pErrInfo ) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				//����Ŀ¼��ӡ ��ѯ������Ϣ
				ArchivesType archivesType = new ArchivesType(archivesTypeID);
				
				if (catalogPrintManageService.findArchivesinfoForTransferCatalog(depaermentName, batNo, archivesType, archivesInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "����Ŀ¼��ӡ ��ѯ������Ϣ ʧ�ܣ�");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			e.printStackTrace();
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
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
	}

	/**
	 * ������ӡҳ����ʾ�������
	 * ������ҳ���������
	 */
	private void buildDataItem() {
		//������ʾ�������
		LinkedHashMap<String, ArchivesTypeDataItem> dataItems = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID).getDataItemsForAll();
		
		if(dataItems.containsKey(EnumSystemDataItem.���������ı�.getEnumValue())) {
			dataItems.get(EnumSystemDataItem.���������ı�.getEnumValue()).setDisplayText("��������");
		}
		
		if(dataItems.containsKey(EnumSystemDataItem.�����ܼ��ı�.getEnumValue())) {
			dataItems.get(EnumSystemDataItem.�����ܼ��ı�.getEnumValue()).setDisplayText("�����ܼ�");
		}
		
		if(dataItems.containsKey(EnumSystemDataItem.�����γɲ�������.getEnumValue())) {
			dataItems.get(EnumSystemDataItem.�����γɲ�������.getEnumValue()).setDisplayText("�����γɲ���");
		}
		
		if(dataItems.containsKey(EnumSystemDataItem.����.getEnumValue())) {
			dataItems.get(EnumSystemDataItem.����.getEnumValue()).setKeyInCol("class='archivesinfoTitle'");
		}
		
		for(String viewName : columnName) {
			if(dataItems.containsKey(viewName)) {
				viewDataItems.add(dataItems.get(viewName));
			}
		}
		getRequest().setAttribute("viewDataItems", viewDataItems);
	}
	
	/**
	 * ����Ŀ¼��ӡ
	 * @return
	 */
	public void officialArchivesInfoCatalogPrint() {
		System.out.println("-------����Ŀ¼��ӡ--------");
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForBll(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				
				//������ӡҳ����ʾ�������
				this.buildDataItem();
				List<OfficialArchivesInfo> officialArchivesInfos = new ArrayList<OfficialArchivesInfo>();
				OfficialArchivesType officialArchivesType = SystemInitializer.getInstance().getOfficialArchivesTypes().get(officialArchivesTypeID);
				if (catalogPrintManageService.findArchivesinfoForofficialArchivesInfoCatalog(officialArchivesType, officialArchivesInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "����Ŀ¼ ��ӡ��ѯ ʧ�ܣ�");
				} 
				getRequest().setAttribute("officialArchivesInfos", officialArchivesInfos);
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
	}

	public String getResultURL() {
		return resultURL;
	}

	public void setResultURL(String resultURL) {
		this.resultURL = resultURL;
	}
	
	/**
	 * ���Ŀ¼��ӡ��ҵ���߼���������ע�루BLL Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkBllInjectionForBll(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//����Ƿ��н���BLLҵ���߼����������ע��
			pErrPos = 1;
			if (catalogPrintManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append(
						"Ŀ¼��ӡ��BLLҵ���߼�����Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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

	public LinkedHashMap<String, CatalogDataItem> getCatalogDataItems() {
		return catalogDataItems;
	}

	public void setCatalogDataItems(
			LinkedHashMap<String, CatalogDataItem> catalogDataItems) {
		this.catalogDataItems = catalogDataItems;
	}

	public int getArchivesTypeID() {
		return archivesTypeID;
	}

	public void setArchivesTypeID(int archivesTypeID) {
		this.archivesTypeID = archivesTypeID;
	}

	public int getCatalogTypeID() {
		return catalogTypeID;
	}

	public void setCatalogTypeID(int catalogTypeID) {
		this.catalogTypeID = catalogTypeID;
	}

	public EnumCatalogType getCatalogType() {
		return catalogType;
	}

	public void setCatalogType(EnumCatalogType catalogType) {
		this.catalogType = catalogType;
	}

	public PrintPageSet getPrintPageSet() {
		return printPageSet;
	}

	public List<ArchivesInfo> getArchivesInfos() {
		return archivesInfos;
	}

	public void setArchivesInfos(List<ArchivesInfo> archivesInfos) {
		this.archivesInfos = archivesInfos;
	}

	public void setPrintPageSet(PrintPageSet printPageSet) {
		this.printPageSet = printPageSet;
	}

	public String getDepaermentName() {
		return depaermentName;
	}

	public void setDepaermentName(String depaermentName) {
		this.depaermentName = depaermentName;
	}

	public int getOfficialArchivesTypeID() {
		return officialArchivesTypeID;
	}

	public String getBatNo() {
		return batNo;
	}

	public void setBatNo(String batNo) {
		this.batNo = batNo;
	}

	public String getBoxBarcode() {
		return boxBarcode;
	}

	public void setBoxBarcode(String boxBarcode) {
		this.boxBarcode = boxBarcode;
	}

	public void setOfficialArchivesTypeID(int officialArchivesTypeID) {
		this.officialArchivesTypeID = officialArchivesTypeID;
	}

	public String[] getColumnName() {
		return columnName;
	}

	public void setColumnName(String[] columnName) {
		this.columnName = columnName;
	}

	public List<ArchivesTypeDataItem> getViewDataItems() {
		return viewDataItems;
	}

	public String getIsArchived() {
		return isArchived;
	}

	public void setIsArchived(String isArchived) {
		this.isArchived = isArchived;
	}

	public int getNBXH() {
		return NBXH;
	}

	public void setNBXH(int nBXH) {
		NBXH = nBXH;
	}

	public void setViewDataItems(List<ArchivesTypeDataItem> viewDataItems) {
		this.viewDataItems = viewDataItems;
	}
}