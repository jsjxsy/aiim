package com.orifound.aiim.web.struts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ActionSupport;
import com.orifound.aiim.bll.service.ICurrentBarcodeManageService;
import com.orifound.aiim.bll.service.IStocktakingInfoManageService;
import com.orifound.aiim.bll.service.IStocktakingManageService;
import com.orifound.aiim.bll.service.IStoreroomManageService;
import com.orifound.aiim.bll.service.ITempratureHumidityInfoManageService;
import com.orifound.aiim.dal.dao.IStocktakingInfoDao;
import com.orifound.aiim.entity.CurrentBarcode;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EnumBarcodeType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.StockReportAddressNotMatch;
import com.orifound.aiim.entity.StockReportArchivesBoxNotMatch;
import com.orifound.aiim.entity.StockReportArchivesCount;
import com.orifound.aiim.entity.StockReportPaperNotExist;
import com.orifound.aiim.entity.StockReportSystemNotExist;
import com.orifound.aiim.entity.StocktakingAddressBoxDetail;
import com.orifound.aiim.entity.StocktakingArchivesDetail;
import com.orifound.aiim.entity.StocktakingInfo;
import com.orifound.aiim.entity.StoreroomArchivesInfo;
import com.orifound.aiim.entity.StoreroomStructure;
import com.orifound.aiim.entity.TempratureHumidityInfo;
/**
 * 
 * @author Administrator
 *
 */
public class StoreroomManageAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 123343L;
	
	/**
	 * �ⷿ����ҵ���߼���
	 */
	@Autowired
	private IStoreroomManageService storeroomManageService ;
	
	/**
	 * �������ӡҵ���߼�����ӿ�
	 */
	@Autowired
	private ICurrentBarcodeManageService currentBarcodeManageService;
	
	/**
	 * �ⷿ�¶�ʪ����Ϣ�������ӿ�
	 */
	@Autowired
	private ITempratureHumidityInfoManageService tempratureHumidityInfoManageService;
	
	/**
	 * �ⷿ�̵㹤����Ϣ�������ӿ�
	 */
	@Autowired
	private IStocktakingInfoManageService stocktakingInfoManageService;
	
	/**
	 * �ⷿ�̵�������ӿ�
	 */
	@Autowired
	private IStocktakingManageService stocktakingManageService;
	/**
	 * ʵ���ϴ��ļ�
	 */
	private File upload;

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}
	
	/**
	 * ����ҳ��Ϣ������
	 */
	private DataPageInfo dataPageInfo = new DataPageInfo();
	
	public DataPageInfo getDataPageInfo() {
		return dataPageInfo;
	}

	public void setDataPageInfo(DataPageInfo dataPageInfo) {
		this.dataPageInfo = dataPageInfo;
	}

	/**
	 * �ⷿ��ʪ�ȵǼ���Ϣ
	 */
	private TempratureHumidityInfo tempratureHumidityInfo;
	
	public TempratureHumidityInfo getTempratureHumidityInfo() {
		return tempratureHumidityInfo;
	}

	public void setTempratureHumidityInfo(TempratureHumidityInfo tempratureHumidityInfo) {
		this.tempratureHumidityInfo = tempratureHumidityInfo;
	}

	/**
	 * ��ѯ���еĿⷿ��Ϣ
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public String findStorerooms() throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		String forWard = "";
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		String pageResult = "";
		List<StoreroomStructure> storeRooms = new ArrayList<StoreroomStructure>();

		try {
			//�������еĿⷿ��Ϣ
			if (pFlag) {
				pErrPos = 1;
				if(storeroomManageService.findStorerooms(storeRooms, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"��ѯ�ⷿ��Ϣʧ�ܣ�");
				}
			}
			
			//��ȡ���Ľ����ӵ�request�����ص�ҳ��
			if(true){//�˴�����ִ��
				pErrPos = 2;
				request.setAttribute("pageResult", pageResult);
				request.setAttribute("storeRooms", storeRooms);
				forWard ="toStoreroomStructure";
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
				forWard = "error";
				request.setAttribute("pErrInfo", pErrInfo);
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}

		return forWard;
	}
	
	/**
	 * �Ǽǿⷿ������Ϣ
	 * @return
	 * @throws Exception
	 */
	public String registerTempratureHumidityInfo() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		String forWard = "";
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		List<StoreroomStructure> storeRooms = new ArrayList<StoreroomStructure>();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			if (tempratureHumidityInfo==null) {				
				pFlag = false;
				pErrInfo.getContent().append("�ⷿ������Ϣ�Ƿ�Ϊ�գ�");
			}
			
			//�������еĿⷿ��Ϣ
			if (pFlag) {
				pErrPos = 1;
				if(storeroomManageService.findStorerooms(storeRooms, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"��ѯ�ⷿ��Ϣʧ�ܣ�");
				}
			}

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if(tempratureHumidityInfoManageService.addTempratureHumidityInfo(tempratureHumidityInfo, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"���һ���µĿⷿ�¶�ʪ����Ϣʧ�ܣ�");
				}
			}
			
			if (pFlag) {
				pErrPos = 3;
				request.setAttribute("successFlg",true);
				request.setAttribute("storeRooms",storeRooms);
				forWard = "toAddKFHJ";
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
				forWard = "error";
				request.setAttribute("pErrInfo", pErrInfo);
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}

		return forWard;	
	}
	
	/**
	 * ͨ���ⷿ������Ϣ��Ż�ȡ�ⷿ��Ϣ
	 * @return
	 * @throws Exception
	 */
	public String getTempratureHumidityInfoByID() throws Exception{
		System.out.println("getTempratureHumidityInfoByID");
		boolean pFlag = true;
		int pErrPos = 0;
		String forWard = "";
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		List<StoreroomStructure> storeRooms = new ArrayList<StoreroomStructure>();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			if (tempratureHumidityInfo==null) {				
				tempratureHumidityInfo = new TempratureHumidityInfo();
			}
			System.out.println("tempratureHumidityInfo.getID():"+tempratureHumidityInfo.getID());
			
			if(request.getParameter("tempratureId")!=null && !"".equals(request.getParameter("tempratureId"))){
				tempratureHumidityInfo.setID(Integer.parseInt(request.getParameter("tempratureId")));
			}
			
			//�������еĿⷿ��Ϣ
			if (pFlag) {
				pErrPos = 1;
				if(storeroomManageService.findStorerooms(storeRooms, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"��ѯ�ⷿ��Ϣʧ�ܣ�");
				}
			}

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if(tempratureHumidityInfoManageService.findTempratureHumidityInfoByID(tempratureHumidityInfo, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"����Ψһ��ʶ���ҿⷿ�¶�ʪ����Ϣ��Ϣʧ�ܣ�");
				}
			}
			
			//�����ݷ��ص�ҳ��
			if (pFlag) {
				pErrPos = 3;
				//request.setAttribute("successFlg",true);
				request.setAttribute("storeRooms",storeRooms);
				forWard = "toAlterKFHJ";
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
				forWard = "error";
				request.setAttribute("pErrInfo", pErrInfo);
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}
		return forWard;	
	}
	
	
	/**
	 * ���¿ⷿ������Ϣ
	 * @return
	 * @throws Exception
	 */
	public String updateTempratureHumidityInfo() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		String forWard = "";
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		List<StoreroomStructure> storeRooms = new ArrayList<StoreroomStructure>();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			if (tempratureHumidityInfo==null) {				
				pFlag = false;
				pErrInfo.getContent().append("�ⷿ������Ϣ�Ƿ�Ϊ�գ�");
			}
			
			//�������еĿⷿ��Ϣ
			if (pFlag) {
				pErrPos = 1;
				if(storeroomManageService.findStorerooms(storeRooms, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"��ѯ�ⷿ��Ϣʧ�ܣ�");
				}
			}

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if(tempratureHumidityInfoManageService.updateTempratureHumidityInfo(tempratureHumidityInfo, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"���¿ⷿ�¶�ʪ����Ϣʧ�ܣ�");
				}
			}
			
			if (pFlag) {
				pErrPos = 3;
				request.setAttribute("successFlg",true);
				request.setAttribute("storeRooms",storeRooms);
				forWard = "toAlterKFHJ";
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
				forWard = "error";
				request.setAttribute("pErrInfo", pErrInfo);
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}

		return forWard;	
	}
	
	/**
	 * ����ɾ���ⷿ�̵���Ϣ
	 * @return
	 * @throws Exception
	 */
	public String deleteStocktakings() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		String forWard = "";
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		String whereSQL = "";
		List<StocktakingInfo> stocktakingInfos = new ArrayList<StocktakingInfo>();
		String[] stocktakings = {};	
		try {
			//��ʼ���� 1...
			pErrPos = 1;
			

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if(request.getParameterValues("stocktakings")!=null ){
					stocktakings = request.getParameterValues("stocktakings");
				}
			}
			
			//����ҵ���߼���ɾ���ⷿ�̵㹤����Ϣ
			if(pFlag){
				pErrPos = 3;
				StocktakingInfo stocktakingInfo =null;
				if (stocktakings.length>0) {
					for (String str_stocktakings : stocktakings) {
						stocktakingInfo = new StocktakingInfo();
						stocktakingInfo.setID(Integer.parseInt(str_stocktakings));
						System.out.println(stocktakingInfo.getID());
						if(stocktakingInfoManageService.deleteStocktakingInfo(stocktakingInfo, pErrInfo)==false){
							pFlag = false;
							pErrInfo.getContent().append("ɾ��ָ���Ŀⷿ�̵㹤����Ϣʧ�ܣ�");
						}else{
							System.out.println("���Ϊ"+stocktakingInfo.getID()+"�Ŀⷿ�̵㹤����Ϣ�ɹ���");	
						}
					}
				}
			}
			
			//��ѯ���пⷿ�̵���Ϣ(��ҳ)
			if (pFlag) {
				pErrPos = 2;
				dataPageInfo.setPageSize(15);
				if (stocktakingInfoManageService.findStocktakingInfos(stocktakingInfos, dataPageInfo, pErrInfo)==false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"A�������еĿⷿ�̵㹤����Ϣ��Ϣʧ��:");
				}
			}
			
			//��������ص�ҳ��
			if (pFlag) {
				pErrPos = 4;
				request.setAttribute("stocktakingInfos", stocktakingInfos);				
				forWard = "toStocktakingInfoManage";
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
				forWard = "error";
				request.setAttribute("pErrInfo", pErrInfo);
				tempBuilder = null;
				System.out.println("errorInfo:"+pErrInfo.toString());
			}

			//���پֲ�����
			throwable = null;
		}

		return forWard;
	}
	
	/**
	 * ����ɾ���ⷿ�����Ǽ���Ϣ
	 * @return
	 * @throws Exception
	 */
	public String deleteTempratureHumidityInfos() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		String forWard = "";
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		String whereSQL = "";
		List<TempratureHumidityInfo> tempratureHumidityInfos  = new ArrayList<TempratureHumidityInfo>();
		String[] tempratures = {};	
		try {
			//��ʼ���� 1...
			pErrPos = 1;
			

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if(request.getParameterValues("tempratures")!=null ){
					tempratures = request.getParameterValues("tempratures");
				}
			}
			
			//����ҵ���߼���ɾ���ⷿ������Ϣ
			if(pFlag){
				pErrPos = 3;
				TempratureHumidityInfo temprature =null;
				if (tempratures.length>0) {
					for (String str_temprature : tempratures) {
						temprature = new TempratureHumidityInfo();
						temprature.setID(Integer.parseInt(str_temprature));
						System.out.println(temprature.getID());
						if(tempratureHumidityInfoManageService.deleteTempratureHumidityInfo(temprature, pErrInfo)==false){
							pFlag = false;
							pErrInfo.getContent().append("ɾ��ָ���Ŀⷿ�¶�ʪ����Ϣʧ�ܣ�");
						}else{
							System.out.println("���Ϊ"+temprature.getID()+"�Ļ�����Ϣ�ɹ���");	
						}
					}
				}
			}
			
			//����ҵ���߼�����ѯ�����еĵ�����Ϣ(��ҳ��ʽ)
			if (pFlag) {
				pErrPos = 3;
				dataPageInfo.setPageSize(15);
				if(tempratureHumidityInfoManageService.findTempratureHumidityInfosByCondition(whereSQL, tempratureHumidityInfos, dataPageInfo, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"�����������ҿⷿ�¶�ʪ����Ϣ��Ϣ����ʧ�ܣ�");
				}
			}
			
			//��������ص�ҳ��
			if (pFlag) {
				pErrPos = 4;
				request.setAttribute("tempratureHumidityInfos", tempratureHumidityInfos);				
				forWard = "toEnvironment";
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
				forWard = "error";
				request.setAttribute("pErrInfo", pErrInfo);
				tempBuilder = null;
				System.out.println("errorInfo:"+pErrInfo.toString());
			}

			//���پֲ�����
			throwable = null;
		}

		return forWard;
	}
	
	/**
	 * �ϴ��ⷿ�̵㹤�����ļ�
	 * @return
	 * @throws Exception
	 */
	public String uploadStocktakingData() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		String forWard = "";
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		String archivesBoxBarcode = "";//��������
		String storeAddressBarcode = "";//�豸������
		String archivesBarcode = "";//����������
		List<String> barcodes = new ArrayList<String>();//�洢������
		int boxNum = 0;
		int archivesNum = 0;
		boolean archivesBarcodeFlag = false;
		int stocktakingId = 0;
		
		try {
			//��ʼ���� 1...
			if (pFlag) {
				pErrPos = 1;
				if (this.getUpload() == null) {
					pFlag = false;
					pErrInfo.getContent().append("��ѡ��Ҫ�ϴ����ļ�");
				}
			}		
			
			//��ȡ�̵���
			if (request.getParameter("stocktakingId")!=null && !"".equals(request.getParameter("stocktakingId"))) {
				stocktakingId = Integer.parseInt(request.getParameter("stocktakingId"));
			} else{
				pFlag = false;
				pErrInfo.getContent().append("��ȡ�̵㹤�����ʧ�ܣ�");
			}
			
			//���ж�ȡ������ֵ��ѭ��ִ��ҵ���ϼ��߼�����
			if (pFlag) {
				pErrPos = 2;
				FileInputStream fis = new FileInputStream(upload);
				BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
				String line  ="";				
				
				//������ת�浽������
				while((line=reader.readLine())!=null){
					if(!"".equals(line)){
						barcodes.add(line);
					}
				}
			}
			
			//�ж��ϴ����ļ�����
			if (pFlag) {
				pErrPos = 4;
				//�ж��ϴ��ļ������ͣ���λ��/����λ��
				if (barcodes.get(0).length()==5) {//��λ��
					archivesBarcodeFlag = false;
				}else if (barcodes.get(0).length()==7) {//����λ��
					archivesBarcodeFlag = true;
				}
			
				//����ҵ���߼�
				if (archivesBarcodeFlag) {//�ϴ�����Ϊ����λ��
					StocktakingArchivesDetail stocktakingArchivesDetail = null;
					for(int i= 0;i<barcodes.size();i++){
						if (barcodes.get(i).length()==7) {
							archivesBoxBarcode = barcodes.get(i);
							System.out.println("�豸���룺" + barcodes.get(i));
						}
						if (barcodes.get(i).length()==9) {							
							archivesBarcode = barcodes.get(i);
							System.out.println("�������룺" + barcodes.get(i));
							archivesNum++;
							
							//����������Ϳⷿ�豸���붼��Ϊ��
							if(!"".equals(archivesBoxBarcode) && !"".equals(archivesBarcode) ){
								stocktakingArchivesDetail = new StocktakingArchivesDetail();
								stocktakingArchivesDetail.setArchivesBarcode(archivesBarcode);
								stocktakingArchivesDetail.setArchivesBoxBarcode(archivesBoxBarcode);
								stocktakingArchivesDetail.setStocktakingID(stocktakingId);
								//�Ǽ��ϼ�λ����Ϣ
								if(stocktakingManageService.addStocktakingArchivesDetail(stocktakingArchivesDetail, pErrInfo)==false){
									pFlag = false;
									pErrInfo.getContent().insert(0,"��ӿⷿ�̵�ĵ������뵵������ϸʧ�ܣ�");
								}
							}
						}
					}
				}else{//�ϴ�����Ϊ��λ��
					StocktakingAddressBoxDetail stocktakingAddressBoxDetail  = null;
					for(int i= 0;i<barcodes.size();i++){
						if (barcodes.get(i).length()==5) {//�豸������
							storeAddressBarcode = barcodes.get(i);
							System.out.println("�豸���룺" + barcodes.get(i));
						}else if (barcodes.get(i).length()==7) {//��������
							archivesBoxBarcode = barcodes.get(i);
							System.out.println("�������룺" + barcodes.get(i));
							boxNum++;
							
							//����������Ϳⷿ�豸���붼��Ϊ��
							if(!"".equals(archivesBoxBarcode) && !"".equals(storeAddressBarcode) ){
								stocktakingAddressBoxDetail = new StocktakingAddressBoxDetail();
								stocktakingAddressBoxDetail.setArchivesBoxBarcode(archivesBoxBarcode);
								stocktakingAddressBoxDetail.setStoreAddressBarcode(storeAddressBarcode);
								stocktakingAddressBoxDetail.setStocktakingID(stocktakingId);
								if(stocktakingManageService.addStocktakingAddressBoxDetail(stocktakingAddressBoxDetail, pErrInfo)==false){
									pFlag = false;
									pErrInfo.getContent().insert(0,"��ӿⷿ�̵���豸λ���뵵������ϸʧ�ܣ�");
								}
							}
						}
					}
				}
			}
			
			//��ȡ�̵㹤����Ϣ
			StocktakingInfo stocktakingInfo = new StocktakingInfo();
			stocktakingInfo.setID(stocktakingId);
			if (pFlag) {
				pErrPos = 2;
				if(stocktakingInfoManageService.findStocktakingInfoByID(stocktakingInfo, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"����Ψһ��ʶ���ҿⷿ�̵㹤����Ϣ��Ϣ");
				}
			}
			
			//��������ص�ҳ��
			if (pFlag) {
				pErrPos = 3;
				request.setAttribute("uploadFlag",true);
				request.setAttribute("stocktakingInfo",stocktakingInfo);
				request.setAttribute("stocktakedFlag",stocktakingInfo.getStocktakedFlag());
				request.setAttribute("stocktakingId",request.getParameter("stocktakingId"));
				request.setAttribute("boxNum",boxNum);
				request.setAttribute("fileSize",upload.length());
				forWard = "toStocktaking";
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
				forWard = "error";
				request.setAttribute("pErrInfo", pErrInfo);
				tempBuilder = null;
				System.out.println(pErrInfo.toString());
			}
			//���پֲ�����
			throwable = null;
		}
		return forWard;
	}
	
	/**
	 * DWR:ִ���̵����
	 * @param stocktakingId �̵㹤�����
	 * @param session �Ự
	 * @return
	 * @throws Exception
	 */
	public String executeStocktaking(int stocktakingId,HttpSession session) throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		String result = "";
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			System.out.println("stocktakingId:"+stocktakingId);

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (stocktakingManageService.executeStocktaking(stocktakingId, pErrInfo)==false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"ִ�пⷿ�̵㹤��ʧ�ܣ�");
				}else{
					System.out.println("ִ�пⷿ�̵㹤���ɹ�!");
					StocktakingInfo stocktakingInfo = new StocktakingInfo();
					stocktakingInfo.setID(stocktakingId);
					if(stocktakingInfoManageService.updateStocktakingInfo(stocktakingInfo, pErrInfo)==false){
						pFlag = false;
						pErrInfo.getContent().insert(0,"�޸�ָ���Ŀⷿ�̵㹤�����̵�״̬ʧ�ܣ�");
					}
					
					result = "�̵�ɹ�!";
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
			}

			//���پֲ�����
			throwable = null;
		}
		return result;
	}
	
	/**
	 * ����򿪿ⷿ�̵�ҳ������
	 * @return
	 * @throws Exception
	 */
	public String stocktakingBefore() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		String forWard = "";
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		StocktakingInfo stocktakingInfo = new StocktakingInfo();
		
		try {
			pErrPos = 1;
			if (request.getParameter("stocktakingId")!=null && !"".equals(request.getParameter("stocktakingId")) ) {
				stocktakingInfo.setID(Integer.parseInt(request.getParameter("stocktakingId")));
			}else{
				pFlag = false;
				pErrInfo.getContent().append("��ȡ�ⷿ�̵㹤�����ʧ�ܣ�");
			}
			
			if (pFlag) {
				pErrPos = 2;
				if(stocktakingInfoManageService.findStocktakingInfoByID(stocktakingInfo, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"����Ψһ��ʶ���ҿⷿ�̵㹤����Ϣ��Ϣ");
				}
			}

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 3;
				request.setAttribute("stocktakingInfo",stocktakingInfo);
				request.setAttribute("stocktakedFlag",stocktakingInfo.getStocktakedFlag());
				request.setAttribute("stocktakingId",request.getParameter("stocktakingId"));
				forWard = "toStocktaking";
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
				forWard = "error";
				request.setAttribute("pErrInfo", pErrInfo);
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}

		return forWard;
	}
	
	/**
	 * �������ӿⷿ�̵㹤��ҳ���¼�
	 * @return
	 * @throws Exception
	 */
	public String addStocktakingBefore() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		String forWard = "";
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		List<StoreroomStructure> storeRooms = new ArrayList<StoreroomStructure>();
		
		try {
			//��ʼ���� 1...
			pErrPos = 1;
			

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (storeroomManageService.findStorerooms(storeRooms, pErrInfo)==false) {
					pFlag =false;
					pErrInfo.getContent().insert(0,"�������еĿⷿ��Ϣ");
				}
			}
			
			//�����ݷ��ص�ҳ��
			if (pFlag) {
				pErrPos = 3;
				request.setAttribute("storeRooms", storeRooms);
				forWard = "toAddStocktaking";
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
				forWard = "error";
				request.setAttribute("pErrInfo", pErrInfo);
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}
		return forWard;
	} 
	
	
	/**
	 * ��ӿⷿ�̵㹤����Ϣ
	 * @return
	 * @throws Exception
	 */
	public String addStocktaking() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		String forWard = "";
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		StocktakingInfo stocktakingInfo = new StocktakingInfo();
		List<StoreroomStructure> storeRooms = new ArrayList<StoreroomStructure>();
		
		try {
			//��ʼ���� 1...
			pErrPos = 1;
			if (request.getParameter("storeroomID")!=null && !"".equals(request.getParameter("storeroomID"))) {
				stocktakingInfo.setStoreroomID(Integer.parseInt(request.getParameter("storeroomID")));
				stocktakingInfo.setStocktakingDate(new Date());
				stocktakingInfo.setStocktakedFlag(false);
				System.out.println("success!");
			}

			//����ҵ���߼�������µĿⷿ�̵���Ϣ
			if (pFlag) {
				pErrPos = 2;
				if (stocktakingInfoManageService.addStocktakingInfo(stocktakingInfo, pErrInfo)==false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"���һ���µĿⷿ�̵㹤����Ϣʧ�ܣ�");
				}
			}

			//��ȡ���пⷿ��Ϣ
			if (pFlag) {
				pErrPos = 2;
				if (storeroomManageService.findStorerooms(storeRooms, pErrInfo)==false) {
					pFlag =false;
					pErrInfo.getContent().insert(0,"�������еĿⷿ��Ϣ");
				}
			}
			
			//�����ݷ��ص�ҳ��
			if (pFlag) {
				pErrPos = 3;
				request.setAttribute("successFlg", true);
				request.setAttribute("storeRooms", storeRooms);
				request.setAttribute("storeroomID", request.getParameter("storeroomID"));
				forWard = "toAddStocktaking";
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
				forWard = "error";
				request.setAttribute("pErrInfo", pErrInfo);
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}
		return forWard;
	} 
	
	
	
	/**
	 * ��ȡ���пⷿ�̵㹤����Ϣ
	 * @return
	 * @throws Exception
	 */
	public String findStocktakingInfos() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		String forWard = "";
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		List<StocktakingInfo> stocktakingInfos = new ArrayList<StocktakingInfo>();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				dataPageInfo.setPageSize(15);
				if (stocktakingInfoManageService.findStocktakingInfos(stocktakingInfos, dataPageInfo, pErrInfo)==false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"A�������еĿⷿ�̵㹤����Ϣ��Ϣʧ��:");
				}
			}
			
			//�����ݷ��ص�ҳ��
			if (pFlag) {
				pErrPos = 3;
				request.setAttribute("stocktakingInfos", stocktakingInfos);
				forWard = "toStocktakingInfoManage";
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
				forWard = "error";
				request.setAttribute("pErrInfo", pErrInfo);
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}
		return forWard;
	}
	
	
	
	/**
	 * ����ʱ�䷶Χ��ѯ�ⷿ�¶ȡ�ʪ����Ϣ
	 * @return
	 * @throws Exception
	 */
	public String findTempratureHumidityInfosByDate() throws Exception{
		System.out.println("findTempratureHumidityInfosByDate --> successful!!!");
		boolean pFlag = true;
		int pErrPos = 0;
		String forWard = "";
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		List<TempratureHumidityInfo> tempratureHumidityInfos = new ArrayList<TempratureHumidityInfo>();
		String whereSQL = "";

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			if(request.getParameter("beginDate")!=null && !"".equals(request.getParameter("beginDate").trim())){
				whereSQL += (" AND MeasureDate >= '"+request.getParameter("beginDate").trim()+"'");
			}
			
			if(request.getParameter("endDate")!=null && !"".equals(request.getParameter("endDate").trim())){
				whereSQL += (" AND MeasureDate <= '"+request.getParameter("endDate").trim()+"'");
			}
			
			
			System.out.println("whereSQL:"+whereSQL);
			
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				dataPageInfo.setPageSize(15);
				if(tempratureHumidityInfoManageService.findTempratureHumidityInfosByCondition(whereSQL, tempratureHumidityInfos, dataPageInfo, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"�����������ҿⷿ�¶�ʪ����Ϣ��Ϣ����ʧ�ܣ�");
				}
			}
			
			//��������ص�ҳ��
			if (pFlag) {
				pErrPos = 3;
				request.setAttribute("tempratureHumidityInfos", tempratureHumidityInfos);
				request.setAttribute("beginDate", request.getParameter("beginDate"));
				request.setAttribute("endDate", request.getParameter("endDate"));
				forWard = "toEnvironment";
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
				forWard = "error";
				request.setAttribute("pErrInfo", pErrInfo);
				tempBuilder = null;
				System.out.println(pErrInfo.toString());
			}

			//���پֲ�����
			throwable = null;
			
		}

		return forWard;
	}
	
	/**
	 * �򿪻����Ǽ�ҳ��<br/>��ȡ��ҳ������Ҫ�����ݣ��ⷿ����
	 * @return
	 * @throws Exception
	 */
	public String getAddHJDefaultInputItem() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		String forWard = "";
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		List<StoreroomStructure> storeRooms = new ArrayList<StoreroomStructure>();

		try {
			//�������еĿⷿ��Ϣ
			if (pFlag) {
				pErrPos = 1;
				if(storeroomManageService.findStorerooms(storeRooms, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"��ѯ�ⷿ��Ϣʧ�ܣ�");
				}
			}

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				request.setAttribute("storeRooms", storeRooms);
				forWard = "toAddKFHJ";//��ת�������ⷿ�����Ǽ�ҳ��

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
				forWard = "error";
				request.setAttribute("pErrInfo", pErrInfo);
				tempBuilder = null;
				System.out.println(pErrInfo.toString());
			}

			//���پֲ�����
			throwable = null;
		}

		return forWard;
		
	}

	/**
	 * ���ݿⷿ��ʩ��Ų�ѯ���¼��豸�ļ���
	 * @return
	 * @throws Exception
	 */
	public String showSubStructureByID() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		String forWard = "";
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		List<StoreroomStructure> storeroomStructures = new ArrayList<StoreroomStructure>();
		int storeroomStructureID = 0;

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			
			if(request.getParameter("storeroomStructureID")!= null && !"".equals(request.getParameter("storeroomStructureID").trim())){
				storeroomStructureID = Integer.parseInt(request.getParameter("storeroomStructureID").trim());
			}else{
				pFlag = false;
				pErrInfo.getContent().append("��ȡ�ⷿ�ṹ���ʧ�ܣ�");
			}

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if(storeroomManageService.findStoreroomStructureChildrenByID(storeroomStructureID, storeroomStructures, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"���ݿⷿ�ṹ��Ų������¼��ⷿ�ṹʧ�ܣ�");
				}
			}
			
			if (pFlag) {
				pErrPos = 3;
				request.setAttribute("storeroomStructures", storeroomStructures);
				forWard = "toVeiwSubStructure";
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
				forWard = "error";
				request.setAttribute("pErrInfo", pErrInfo);
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}
		return forWard;
	}

	
	/**
	 * �Ǽ�ʵ�ﵵ���ڿⷿ�е��ϼ�λ��<br>
	 * �豸������5λ����������7λ
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public String registerStoreAddress() {
		boolean pFlag = true;
		int pErrPos = 0;
		String forWard = "";
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		String archivesBoxBarcode = "";//��������
		String storeAddressBarcode = "";//�豸������
		List<String> storeAddressBarcodes = new ArrayList<String>();
		int boxNum = 0;
		try {
			//��ʼ���� 1...
			if (pFlag) {
				pErrPos = 1;
				if (this.getUpload() == null) {
					pFlag = false;
					pErrInfo.getContent().append("��ѡ��Ҫ�ϴ����ļ�");
				}
			}		
			
			//���ж�ȡ������ֵ��ѭ��ִ��ҵ���ϼ��߼�����
			if (pFlag) {
				pErrPos = 2;
				FileInputStream fis = new FileInputStream(upload);
				BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
				String line  ="";
				while((line=reader.readLine())!=null){
					if (line.length()==5) {
						storeAddressBarcode = line;
						storeAddressBarcodes.add(storeAddressBarcode);
						System.out.println("�豸�����룺"+line);
					}
					if (line.length()==7) {
						archivesBoxBarcode = line;
						System.out.println("�������룺"+line);
						boxNum++;
						//����������Ϳⷿ�豸���붼��Ϊ��
						if(!"".equals(archivesBoxBarcode) && !"".equals(storeAddressBarcode) ){
							//�Ǽ��ϼ�λ����Ϣ
							if(storeroomManageService.registerStoreAddress(archivesBoxBarcode, storeAddressBarcode, pErrInfo)==false){
								pFlag = false;
								pErrInfo.getContent().insert(0,"�Ǽ�ʵ�ﵵ���ڿⷿ�е��ϼ�λ��ʧ�ܣ�");
							}
						}
					}
				}
			}
			
			//���¿ⷿ�豸����
			if (pFlag) {
				pErrPos = 3;
				for (String addressBarcode : storeAddressBarcodes) {
					if(storeroomManageService.updateStoreroomStructureUsedSizeByBarcode(addressBarcode, pErrInfo)==false){
						pFlag = false;
						pErrInfo.getContent().insert(0,"���¿ⷿ�豸�����ÿռ�ʧ�ܣ�");
					}
				}
			}
			
			
			//��������ص�ҳ��
			if (pFlag) {
				pErrPos = 3;
				request.setAttribute("uploadFlag",true);
				request.setAttribute("boxNum",boxNum);
				System.out.println(boxNum);
				request.setAttribute("fileSize",upload.length());
				forWard = "toLocationRegister";
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
				forWard = "error";
				request.setAttribute("pErrInfo", pErrInfo);
				System.out.println(pErrInfo.toString());
				tempBuilder = null;
			}
			//���پֲ�����
			throwable = null;
		}
		return forWard;
	}	
	
	
	
	/**
	 * λ����״̬��ѯ<br>
	 * @return 
	 */
	public String findLocAndStatus()  throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		String forWard = "";
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		List<StoreroomArchivesInfo> storeroomArchivesInfos = new ArrayList<StoreroomArchivesInfo>();
		String whereSQL = "";
		
		try {
			//��ȡ����
			pErrPos = 1;
			if(!"".equals(request.getParameter("archivesBarcode").trim())){
				whereSQL +=( " AND ArchivesBarcode ='" + request.getParameter("archivesBarcode").trim())+"'";
				System.out.println("archivesBarcode---");
			}
			if(!"".equals(request.getParameter("archivesID").trim())){
				whereSQL += (" AND ArchivesID LIKE '" +request.getParameter("archivesID").trim())+"%'";
				System.out.println("archivesID---");
			}
			if(!"".equals(request.getParameter("title").trim())){
				whereSQL += (" AND Title LIKE '%" +request.getParameter("title").trim())+"%'";
				System.out.println("title---");
			}
		
			System.out.println("whereSQL:"+whereSQL);
			
			//����ҵ���߼�����ѯ���������Ŀⷿ������Ϣ
			if (pFlag) {	
				pErrPos = 2;
				if(storeroomManageService.findStoreroomArchivesInfosByCondition(whereSQL, storeroomArchivesInfos, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().append("��ѯ���������Ŀⷿ������Ϣʧ�ܣ�");
				}
			}
			
			//�����ݷ��ص�ҳ��
			if (pFlag) {
				pErrPos = 3;
				request.setAttribute("recordSize",storeroomArchivesInfos.size());
				request.setAttribute("storeroomArchivesInfos",storeroomArchivesInfos);
				request.setAttribute("archivesBarcode", request.getParameter("archivesBarcode"));
				request.setAttribute("archivesID", request.getParameter("archivesID"));
				request.setAttribute("title", request.getParameter("title"));
				
				forWard = "toQueryLocAndStatus";
				System.out.println("storeroomManageService.findStoreroomArchivesInfosByCondition ---success!");
			
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
				forWard = "error";
				request.setAttribute("pErrInfo", pErrInfo);
				tempBuilder = null;
System.out.println(pErrInfo.toString());
			}

			//���پֲ�����
			throwable = null;
		}

		return forWard;
	}
	
	
	
	/**
	 * ��ӡ������
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public String printBarcode(int barcodeType,int barcodeCount,HttpSession session,ServletContext application) throws Exception{
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;	
			if(barcodeType != 2 && barcodeType != 3){
				pFlag = false;
				pErrInfo.getContent().append("����������ѡ�����");
				result = "����������ѡ�����";
			}
			
			//��֤��ӡ����
			if(barcodeCount <= 0){
				pFlag = false;
				pErrInfo.getContent().append("��ӡ������������㡣");
				result = "��ӡ�������������";
			}
			
			//�������벢�����������ֹ��Χ
			if (pFlag) {
				CurrentBarcode currentBarcode = new CurrentBarcode();
				currentBarcode.setBarcodeType(EnumBarcodeType.getEnumElement(barcodeType));
				if(currentBarcodeManageService.printBarcode(barcodeCount, currentBarcode, pErrInfo)){
					int begin = currentBarcode.getCurrentBarcodeNo() - barcodeCount + 1;
					int end = currentBarcode.getCurrentBarcodeNo();
					result = EnumBarcodeType.getEnumElement(barcodeType)+":��ֹ��Χ�ǣ�" + begin +"--" + end;
				}else{
					pFlag = false;
					pErrInfo.getContent().insert(0,"��ӡ" + currentBarcode.getBarcodeType() + "ʧ�ܡ�");
					result = pErrInfo.toShortString();
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
		return result;
	}
	
	
	
//////////////8.24 10.49ADD//////////////////////////
	public String findStocktakingReports() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		String forWard = "";
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		StocktakingInfo stocktakingInfo = new StocktakingInfo();
		try {
			//��ʼ���� 1...
			pErrPos = 1;
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if(request.getParameter("stocktakingId")!=null && !"".equals(request.getParameter("stocktakingId"))){
					stocktakingInfo.setID(Integer.parseInt(request.getParameter("stocktakingId")));
				}else{
					pFlag = false;
					pErrInfo.getContent().append("��ȡ�̵㹤�����ʧ�ܣ�");
				}
			}
		
			if(stocktakingInfoManageService.findStocktakingInfoByID(stocktakingInfo, pErrInfo)==false){
				pFlag = false;
				pErrInfo.getContent().insert(0,"����Ψһ��ʶ���ҿⷿ�̵㹤����Ϣ��Ϣʧ�ܣ�");
			}
			
			//��������ص�ҳ��
			if (pFlag) {
				pErrPos = 4;
				request.setAttribute("stocktakingInfo",stocktakingInfo);
				request.setAttribute("stocktakedFlag",stocktakingInfo.getStocktakedFlag());
System.out.println("stocktakedFlag:"+stocktakingInfo.getStocktakedFlag());
				request.setAttribute("stocktakingId",request.getParameter("stocktakingId"));
				forWard = "toViewStocktakingReports";
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
				forWard = "error";
				request.setAttribute("pErrInfo", pErrInfo);
				tempBuilder = null;
				System.out.println("errorInfo:"+pErrInfo.toString());
			}

			//���پֲ�����
			throwable = null;
		}

		return forWard;
	}
	
	//��ѯ���пⷿ�����������
	public String findStockReportArchivesCount() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		String forWard = "";
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		int stocktakingId = 0;
		List<StockReportArchivesCount> stockReportArchivesCounts = new ArrayList<StockReportArchivesCount>();
		try {
			//��ʼ���� 1...
			pErrPos = 1;
			
			if (pFlag) {
				pErrPos = 2;
				if(request.getParameter("stocktakingId")!=null && !"".equals(request.getParameter("stocktakingId"))){
					stocktakingId = Integer.parseInt(request.getParameter("stocktakingId"));
				}else{
					pFlag = false;
					pErrInfo.getContent().append("��ȡ�̵㹤�����ʧ�ܣ�");
				}
			}
			
			if (pFlag) {
				pErrPos = 3;
				if (stocktakingManageService.findStockReportArchivesCount(stocktakingId, stockReportArchivesCounts, pErrInfo)==false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"��ѯ���пⷿ�����������");
				}
				
			}

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 4;
				request.setAttribute("stockReportArchivesCounts", stockReportArchivesCounts);
				forWard = "toStockReportArchivesCount";
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
				forWard = "error";
				request.setAttribute("pErrInfo", pErrInfo);
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}

		return forWard;
	}


	//��ѯ����ϵͳ�в��ڼܵ������
	public String findStockReportSystemNotExist() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		String forWard = "";
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		int stocktakingId = 0;
		List<StockReportSystemNotExist> stockReportSystemNotExists = new ArrayList<StockReportSystemNotExist>();
		try {
			//��ʼ���� 1...
			pErrPos = 1;
			
			if (pFlag) {
				pErrPos = 2;
				if(request.getParameter("stocktakingId")!=null && !"".equals(request.getParameter("stocktakingId"))){
					stocktakingId = Integer.parseInt(request.getParameter("stocktakingId"));
				}else{
					pFlag = false;
					pErrInfo.getContent().append("��ȡ�̵㹤�����ʧ�ܣ�");
				}
			}

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 3;
				if (stocktakingManageService.findStockReportSystemNotExist(stocktakingId, stockReportSystemNotExists, pErrInfo)==false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"��ѯ����ϵͳ�в��ڼܵ������ʧ�ܣ�");
				}
			}
			
			if (pFlag) {
				pErrPos = 4;
				request.setAttribute("stockReportSystemNotExists", stockReportSystemNotExists);
				forWard = "toStockReportSystemNotExist";
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
				forWard = "error";
				request.setAttribute("pErrInfo", pErrInfo);
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}

		return forWard;
	}
	

	//��ѯ����ʵ�ﵵ�����ڼܵ������
	public String findStockReportPaperNotExist() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		String forWard = "";
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		int stocktakingId = 0;
		List<StockReportPaperNotExist> stockReportPaperNotExists = new ArrayList<StockReportPaperNotExist>();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			
			if (pFlag) {
				pErrPos = 2;
				if(request.getParameter("stocktakingId")!=null && !"".equals(request.getParameter("stocktakingId"))){
					stocktakingId = Integer.parseInt(request.getParameter("stocktakingId"));
				}else{
					pFlag = false;
					pErrInfo.getContent().append("��ȡ�̵㹤�����ʧ�ܣ�");
				}
			}

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 3;
				if (stocktakingManageService.findStockReportPaperNotExist(stocktakingId, stockReportPaperNotExists, pErrInfo)==false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"��ѯ����ʵ�ﵵ�����ڼܵ������");
				}
			}
			
			if (pFlag) {
				pErrPos = 4;
				request.setAttribute("stockReportPaperNotExists",stockReportPaperNotExists);
				forWard = "toStockReportPaperNotExist";
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
				forWard = "error";
				request.setAttribute("pErrInfo", pErrInfo);
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}

		return forWard;
	}
	
	//��ѯ����λ�ò�ƥ�����
	public String findStockReportAddressNotMatch() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		String forWard = "";
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		int stocktakingId = 0;
		List<StockReportAddressNotMatch> stockReportAddressNotMatchs = new ArrayList<StockReportAddressNotMatch>();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			
			if (pFlag) {
				pErrPos = 2;
				if(request.getParameter("stocktakingId")!=null && !"".equals(request.getParameter("stocktakingId"))){
					stocktakingId = Integer.parseInt(request.getParameter("stocktakingId"));
				}else{
					pFlag = false;
					pErrInfo.getContent().append("��ȡ�̵㹤�����ʧ�ܣ�");
				}
			}

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 3;
				if (stocktakingManageService.findStockReportAddressNotMatch(stocktakingId, stockReportAddressNotMatchs, pErrInfo)==false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"��ѯ����λ�ò�ƥ�����ʧ�ܣ�");
				}
			}
			
			if (pFlag) {
				pErrPos = 4;
				request.setAttribute("stockReportAddressNotMatchs",stockReportAddressNotMatchs);
				forWard = "toStockReportAddressNotMatch";
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
				forWard = "error";
				request.setAttribute("pErrInfo", pErrInfo);
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}

		return forWard;
	}
	
	//��ѯ����װ�в�ƥ�䵵�����
	public String findStockReportArchivesBoxNotMatch() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		String forWard = "";
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		int stocktakingId = 0;
		List<StockReportArchivesBoxNotMatch> stockReportArchivesBoxNotMatchs = new ArrayList<StockReportArchivesBoxNotMatch>();
		
		try {
			//��ʼ���� 1...
			pErrPos = 1;
			
			if (pFlag) {
				pErrPos = 2;
				if(request.getParameter("stocktakingId")!=null && !"".equals(request.getParameter("stocktakingId"))){
					stocktakingId = Integer.parseInt(request.getParameter("stocktakingId"));
				}else{
					pFlag = false;
					pErrInfo.getContent().append("��ȡ�̵㹤�����ʧ�ܣ�");
				}
			}

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 3;
				if (stocktakingManageService.findStockReportArchivesBoxNotMatch(stocktakingId, stockReportArchivesBoxNotMatchs, pErrInfo)==false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"��ѯ����װ�в�ƥ�䵵�����ʧ�ܣ�");
				}
			}
			
			if (pFlag) {
				pErrPos = 4;
				request.setAttribute("stockReportArchivesBoxNotMatchs", stockReportArchivesBoxNotMatchs);
				forWard = "toStockReportArchivesBoxNotMatch";
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
				forWard = "error";
				request.setAttribute("pErrInfo", pErrInfo);
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}

		return forWard;
	}
	

}
