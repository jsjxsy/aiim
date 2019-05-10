package com.orifound.aiim.web.struts;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.orifound.aiim.bll.service.IReportPrintSettingManageService;
import com.orifound.aiim.bll.service.IStatReportManageService;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.ReportPrintSetting;
import com.orifound.aiim.entity.ReportResultArchivesCollection;
import com.orifound.aiim.entity.ReportResultArchivesDestroy;
import com.orifound.aiim.entity.ReportResultArchivesPublic;
import com.orifound.aiim.entity.ReportResultArchivesSaved;
import com.orifound.aiim.entity.ReportResultArchivesTypeUse;
import com.orifound.aiim.entity.ReportResultArchivesUsePerson;
import com.orifound.aiim.entity.ReportResultArchivesUsePurpose;
import com.orifound.aiim.entity.ReportResultCertificateCharge;
import com.orifound.aiim.entity.ReportResultDepartmentSaved;
import com.orifound.aiim.entity.ReportResultOfficialArchivesInput;
import com.orifound.aiim.entity.ReportResultPersionalArchivesMoveOut;
import com.orifound.aiim.entity.ReportResultStoreroomUse;
import com.orifound.aiim.entity.ReportResultSystemVisit;
import com.orifound.aiim.entity.ReportResultTempratureHumidityForMonth;
import com.orifound.aiim.entity.ReportResultTempratureHumidityForYear;
import com.orifound.aiim.entity.ReportResultWorkProcedure;
import com.orifound.aiim.entity.StatReport;
import com.orifound.aiim.web.util.CreateTreeUtil;

public class StatReportManageAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private IStatReportManageService statReportManageService;
	
	public IStatReportManageService getStatReportManageService() {
		return statReportManageService;
	}
	public void setStatReportManageService(IStatReportManageService statReportManageService) {
		this.statReportManageService = statReportManageService;
	}
	
	private IReportPrintSettingManageService reportPrintSettingManageService; 
	
	public IReportPrintSettingManageService getReportPrintSettingManageService() {
		return reportPrintSettingManageService;
	}
	public void setReportPrintSettingManageService(IReportPrintSettingManageService reportPrintSettingManageService) {
		this.reportPrintSettingManageService = reportPrintSettingManageService;
	}
	
	/**
	 * ��ȡͳ�Ʊ�����
	 */
	public String getStatReportTree() throws Exception {
		// TODO Auto-generated method stub
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		List<StatReport> pStatReports = new ArrayList<StatReport>(); 
		try {
			// ��ȡ�û�ID
			pErrPos = 1;
			HttpServletRequest request = ServletActionContext.getRequest();
			if (pFlag) {
				if (statReportManageService.findStatReports(pStatReports, pErrInfo) == false) {
					pErrInfo.getContent().insert(0, "��ѯͳ�Ʊ�����Ϣʧ�ܣ�");
					pFlag = false;
					result="error";
				}
			}
			String tree = CreateTreeUtil.getStatReportTree(pStatReports);
			request.setAttribute("tree", tree);
			request.setAttribute("reportResultArchivesCollection", "findAllReportResultArchivesCollection.action");
			request.setAttribute("reportResultArchivesUsePerson", "findAllReportResultArchivesUsePerson.action");
			request.setAttribute("reportResultArchivesTypeUse", "findAllReportResultArchivesTypeUse.action");
			request.setAttribute("reportResultArchivesUsePurpose", "findAllReportResultArchivesUsePurpose.action");
			request.setAttribute("reportResultArchivesSaved", "findAllReportResultArchivesSaved.action");
			request.setAttribute("reportResultDepartmentSaved", "findAllReportResultDepartmentSaved.action");
			request.setAttribute("reportResultPersionalArchivesMoveOut", "findAllReportResultPersionalArchivesMoveOut.action");
			request.setAttribute("reportResultArchivesDestroy", "findAllReportResultArchivesDestroy.action");
			request.setAttribute("reportResultArchivesPublic", "findAllReportResultArchivesPublic.action");
			request.setAttribute("reportResultStoreroomUse", "findAllReportResultStoreroomUse.action");
			request.setAttribute("reportResultTempratureHumidityForYear", "findAllReportResultTempratureHumidityForYear.action");
			request.setAttribute("reportResultTempratureHumidityForMonth", "findAllReportResultTempratureHumidityForMonth.action");
			request.setAttribute("reportResultCertificateCharge", "findAllReportResultCertificateCharge.action");
			request.setAttribute("reportResultOfficialArchivesInput", "findAllReportResultOfficialArchivesInput.action");
			request.setAttribute("reportResultSystemVisit", "findAllReportResultSystemVisit.action");
			request.setAttribute("reportResultWorkProcedure", "findAllReportResultWorkProcedure.action");
			result = "success";

		} catch (Exception e) {
			result = "error";
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
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
			}
		}
		return result;
	}
	
	/**
	 * ��ѯ����ͳ�Ʊ�����--�������ݲ����
	 * @return 
	 * @throws Exception
	 */
	public String findAllReportResultArchivesCollection() throws Exception {
		// TODO Auto-generated method stub
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		List<ReportResultArchivesCollection> pReportResultArchivesCollections = new ArrayList<ReportResultArchivesCollection>(); 
		List<ReportPrintSetting> pReportPrintSettings = new ArrayList<ReportPrintSetting>(); 
		try {
			// ��ȡ�û�ID
			pErrPos = 1;
			HttpServletRequest request = ServletActionContext.getRequest();
			if (pFlag) {
				if (statReportManageService.findAllReportResultArchivesCollection(pReportResultArchivesCollections, pErrInfo) == false) {
					result = "error";
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ����ͳ�ƽ�����������ݲ����ʧ�ܣ�");
				}
			}
			
			if (pFlag) {
				if (reportPrintSettingManageService.findReportPrintSettings(pReportPrintSettings, pErrInfo) == false) {
					result = "error";
					pErrInfo.getContent().insert(0, "��ѯ�����ӡ���ñ�ʧ�ܣ�");
				}
			}
			request.setAttribute("reportResultArchivesCollections", pReportResultArchivesCollections);
			request.setAttribute("reportPrintSettings", pReportPrintSettings);
			result = "success";

		} catch (Exception e) {
			result = "error";
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
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
			}
		}
		return result;
	}
	
	/**
	 * ��ѯ���еı���ͳ�ƽ�������������������
	 * @return 
	 * @throws Exception
	 */
	public String findAllReportResultArchivesTypeUse() throws Exception {
		// TODO Auto-generated method stub
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		List<ReportResultArchivesTypeUse>  pReportResultArchivesTypeUses = new ArrayList<ReportResultArchivesTypeUse>(); 
		List<ReportPrintSetting> pReportPrintSettings = new ArrayList<ReportPrintSetting>(); 
		try {
			// ��ȡ�û�ID
			pErrPos = 1;
			HttpServletRequest request = ServletActionContext.getRequest();
			if (pFlag) {
				if (statReportManageService.findAllReportResultArchivesTypeUse(pReportResultArchivesTypeUses, pErrInfo) == false) {
					result = "error";
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ����ͳ�ƽ�������������������ʧ�ܣ�");
				}
			}
			
			if (pFlag) {
				if (reportPrintSettingManageService.findReportPrintSettings(pReportPrintSettings, pErrInfo) == false) {
					result = "error";
					pErrInfo.getContent().insert(0, "��ѯ�����ӡ���ñ�ʧ�ܣ�");
				}
			}
			request.setAttribute("reportResultArchivesTypeUses", pReportResultArchivesTypeUses);
			request.setAttribute("reportPrintSettings", pReportPrintSettings);
			result = "success";

		} catch (Exception e) {
			result = "error";
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
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
			}
		}
		return result;
	}
	
	/**
	 * ��ѯ���еı���ͳ�ƽ�����������������
	 * @return 
	 * @throws Exception
	 */
	public String findAllReportResultArchivesUsePerson() throws Exception {
		// TODO Auto-generated method stub
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		List<ReportResultArchivesUsePerson>  pReportResultArchivesUsePersons = new ArrayList<ReportResultArchivesUsePerson>(); 
		List<ReportPrintSetting> pReportPrintSettings = new ArrayList<ReportPrintSetting>(); 
		try {
			// ��ȡ�û�ID
			pErrPos = 1;
			HttpServletRequest request = ServletActionContext.getRequest();
			if (pFlag) {
				if (statReportManageService.findAllReportResultArchivesUsePerson(pReportResultArchivesUsePersons, pErrInfo) == false) {
					result = "error";
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ����ͳ�ƽ�����������������ʧ�ܣ�");
				}
			}
			
			if (pFlag) {
				if (reportPrintSettingManageService.findReportPrintSettings(pReportPrintSettings, pErrInfo) == false) {
					result = "error";
					pErrInfo.getContent().insert(0, "��ѯ�����ӡ���ñ�ʧ�ܣ�");
				}
			}
			request.setAttribute("reportResultArchivesUsePersons", pReportResultArchivesUsePersons);
			request.setAttribute("reportPrintSettings", pReportPrintSettings);
			result = "success";

		} catch (Exception e) {
			result = "error";
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
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
			}
		}
		return result;
	}
	
	/**
	 * ��ѯ���еı���ͳ�ƽ������������Ŀ�����
	 * @return 
	 * @throws Exception
	 */
	public String findAllReportResultArchivesUsePurpose() throws Exception {
		// TODO Auto-generated method stub
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		List<ReportResultArchivesUsePurpose>  pReportResultArchivesUsePurposes = new ArrayList<ReportResultArchivesUsePurpose>(); 
		List<ReportPrintSetting> pReportPrintSettings = new ArrayList<ReportPrintSetting>(); 
		try {
			// ��ȡ�û�ID
			pErrPos = 1;
			HttpServletRequest request = ServletActionContext.getRequest();
			if (pFlag) {
				if (statReportManageService.findAllReportResultArchivesUsePurpose(pReportResultArchivesUsePurposes, pErrInfo) == false) {
					result = "error";
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ����ͳ�ƽ������������Ŀ�����ʧ�ܣ�");
				}
			}
			
			if (pFlag) {
				if (reportPrintSettingManageService.findReportPrintSettings(pReportPrintSettings, pErrInfo) == false) {
					result = "error";
					pErrInfo.getContent().insert(0, "��ѯ�����ӡ���ñ�ʧ�ܣ�");
				}
			}
			request.setAttribute("reportResultArchivesUsePurposes", pReportResultArchivesUsePurposes);
			request.setAttribute("reportPrintSettings", pReportPrintSettings);
			result = "success";

		} catch (Exception e) {
			result = "error";
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
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
			}
		}
		return result;
	}
	
	/**
	 * ��ѯ���еı���ͳ�ƽ�����������鵵���
	 * @return 
	 * @throws Exception
	 */
	public String findAllReportResultArchivesSaved() throws Exception {
		// TODO Auto-generated method stub
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		List<ReportResultArchivesSaved>  pReportResultArchivesSaveds = new ArrayList<ReportResultArchivesSaved>(); 
		List<ReportPrintSetting> pReportPrintSettings = new ArrayList<ReportPrintSetting>(); 
		try {
			// ��ȡ�û�ID
			pErrPos = 1;
			HttpServletRequest request = ServletActionContext.getRequest();
			if (pFlag) {
				if (statReportManageService.findAllReportResultArchivesSaved(pReportResultArchivesSaveds, pErrInfo) == false) {
					result = "error";
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ����ͳ�ƽ�����������鵵���ʧ�ܣ�");
				}
			}
			
			if (pFlag) {
				if (reportPrintSettingManageService.findReportPrintSettings(pReportPrintSettings, pErrInfo) == false) {
					result = "error";
					pErrInfo.getContent().insert(0, "��ѯ�����ӡ���ñ�ʧ�ܣ�");
				}
			}
			request.setAttribute("reportResultArchivesSaveds", pReportResultArchivesSaveds);
			request.setAttribute("reportPrintSettings", pReportPrintSettings);
			result = "success";

		} catch (Exception e) {
			result = "error";
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
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
			}
		}
		return result;
	}
	
	
	/**
	 * ��ѯ���еı���ͳ�ƽ�������Ź鵵���
	 * @return 
	 * @throws Exception
	 */
	public String findAllReportResultDepartmentSaved() throws Exception {
		// TODO Auto-generated method stub
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		List<ReportResultDepartmentSaved>  pReportResultDepartmentSaveds = new ArrayList<ReportResultDepartmentSaved>(); 
		List<ReportPrintSetting> pReportPrintSettings = new ArrayList<ReportPrintSetting>(); 
		try {
			// ��ȡ�û�ID
			pErrPos = 1;
			HttpServletRequest request = ServletActionContext.getRequest();
			if (pFlag) {
				if (statReportManageService.findAllReportResultDepartmentSaved(pReportResultDepartmentSaveds, pErrInfo) == false) {
					result = "error";
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ����ͳ�ƽ�������Ź鵵���ʧ�ܣ�");
				}
			}
			
			if (pFlag) {
				if (reportPrintSettingManageService.findReportPrintSettings(pReportPrintSettings, pErrInfo) == false) {
					result = "error";
					pErrInfo.getContent().insert(0, "��ѯ�����ӡ���ñ�ʧ�ܣ�");
				}
			}
			request.setAttribute("reportResultDepartmentSaveds", pReportResultDepartmentSaveds);
			request.setAttribute("reportPrintSettings", pReportPrintSettings);
			result = "success";

		} catch (Exception e) {
			result = "error";
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
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
			}
		}
		return result;
	}
	
	
	/**
	 * ��ѯ���еı���ͳ�ƽ�������µ���ת����� 
	 * @return 
	 * @throws Exception
	 */
	public String findAllReportResultPersionalArchivesMoveOut() throws Exception {
		// TODO Auto-generated method stub
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		List<ReportResultPersionalArchivesMoveOut>  pReportResultPersionalArchivesMoveOuts = new ArrayList<ReportResultPersionalArchivesMoveOut>(); 
		List<ReportPrintSetting> pReportPrintSettings = new ArrayList<ReportPrintSetting>(); 
		try {
			// ��ȡ�û�ID
			pErrPos = 1;
			HttpServletRequest request = ServletActionContext.getRequest();
			if (pFlag) {
				if (statReportManageService.findAllReportResultPersionalArchivesMoveOut(pReportResultPersionalArchivesMoveOuts, pErrInfo) == false) {
					result = "error";
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ����ͳ�ƽ�������������������ʧ�ܣ�");
				}
			}
			
			if (pFlag) {
				if (reportPrintSettingManageService.findReportPrintSettings(pReportPrintSettings, pErrInfo) == false) {
					result = "error";
					pErrInfo.getContent().insert(0, "��ѯ�����ӡ���ñ�ʧ�ܣ�");
				}
			}
			request.setAttribute("reportResultPersionalArchivesMoveOuts", pReportResultPersionalArchivesMoveOuts);
			request.setAttribute("reportPrintSettings", pReportPrintSettings);
			result = "success";

		} catch (Exception e) {
			result = "error";
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
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
			}
		}
		return result;
	}
	
	
	/**
	 * ��ѯ���еı���ͳ�ƽ��������������� 
	 * @return 
	 * @throws Exception
	 */
	public String findAllReportResultArchivesDestroy() throws Exception {
		// TODO Auto-generated method stub
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		List<ReportResultArchivesDestroy>  pReportResultArchivesDestroys = new ArrayList<ReportResultArchivesDestroy>(); 
		List<ReportPrintSetting> pReportPrintSettings = new ArrayList<ReportPrintSetting>(); 
		try {
			// ��ȡ�û�ID
			pErrPos = 1;
			HttpServletRequest request = ServletActionContext.getRequest();
			if (pFlag) {
				if (statReportManageService.findAllReportResultArchivesDestroy(pReportResultArchivesDestroys, pErrInfo) == false) {
					result = "error";
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ����ͳ�ƽ���������������ʧ�ܣ�");
				}
			}
			
			if (pFlag) {
				if (reportPrintSettingManageService.findReportPrintSettings(pReportPrintSettings, pErrInfo) == false) {
					result = "error";
					pErrInfo.getContent().insert(0, "��ѯ�����ӡ���ñ�ʧ�ܣ�");
				}
			}
			request.setAttribute("reportResultArchivesDestroys", pReportResultArchivesDestroys);
			request.setAttribute("reportPrintSettings", pReportPrintSettings);
			result = "success";

		} catch (Exception e) {
			result = "error";
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
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
			}
		}
		return result;
	}
	
	/**
	 * ��ѯ���еı���ͳ�ƽ��������������� 
	 * @return 
	 * @throws Exception
	 */
	public String findAllReportResultArchivesPublic() throws Exception {
		// TODO Auto-generated method stub
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		List<ReportResultArchivesPublic>  pReportResultArchivesPublics = new ArrayList<ReportResultArchivesPublic>(); 
		List<ReportPrintSetting> pReportPrintSettings = new ArrayList<ReportPrintSetting>(); 
		try {
			// ��ȡ�û�ID
			pErrPos = 1;
			HttpServletRequest request = ServletActionContext.getRequest();
			if (pFlag) {
				if (statReportManageService.findAllReportResultArchivesPublic(pReportResultArchivesPublics, pErrInfo) == false) {
					result = "error";
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ����ͳ�ƽ���������������ʧ�ܣ�");
				}
			}
			
			if (pFlag) {
				if (reportPrintSettingManageService.findReportPrintSettings(pReportPrintSettings, pErrInfo) == false) {
					result = "error";
					pErrInfo.getContent().insert(0, "��ѯ�����ӡ���ñ�ʧ�ܣ�");
				}
			}
			request.setAttribute("reportResultArchivesPublics", pReportResultArchivesPublics);
			request.setAttribute("reportPrintSettings", pReportPrintSettings);
			result = "success";

		} catch (Exception e) {
			result = "error";
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
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
			}
		}
		return result;
	}
	
	/**
	 * ��ѯ���еı�ͳ�ƽ�����ⷿ��ʩ������� 
	 * @return 
	 * @throws Exception
	 */
	public String findAllReportResultStoreroomUse() throws Exception {
		// TODO Auto-generated method stub
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		List<ReportResultStoreroomUse>  pReportResultStoreroomUses = new ArrayList<ReportResultStoreroomUse>(); 
		List<ReportPrintSetting> pReportPrintSettings = new ArrayList<ReportPrintSetting>(); 
		try {
			// ��ȡ�û�ID
			pErrPos = 1;
			HttpServletRequest request = ServletActionContext.getRequest();
			if (pFlag) {
				if (statReportManageService.findAllReportResultStoreroomUse(pReportResultStoreroomUses, pErrInfo) == false) {
					result = "error";
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ����ͳ�ƽ�����ⷿ��ʩ�������ʧ�ܣ�");
				}
			}
			
			if (pFlag) {
				if (reportPrintSettingManageService.findReportPrintSettings(pReportPrintSettings, pErrInfo) == false) {
					result = "error";
					pErrInfo.getContent().insert(0, "��ѯ�����ӡ���ñ�ʧ�ܣ�");
				}
			}
			request.setAttribute("reportResultStoreroomUses", pReportResultStoreroomUses);
			request.setAttribute("reportPrintSettings", pReportPrintSettings);
			result = "success";

		} catch (Exception e) {
			result = "error";
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
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
			}
		}
		return result;
	}
	
	/**
	 * ��ѯ���еı���ͳ�ƽ�����ⷿ��ʪ����ȱ仯���
	 * @return 
	 * @throws Exception
	 */
	public String findAllReportResultTempratureHumidityForYear() throws Exception {
		// TODO Auto-generated method stub
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		List<ReportResultTempratureHumidityForYear>  pReportResultTempratureHumidityForYears = new ArrayList<ReportResultTempratureHumidityForYear>(); 
		List<ReportPrintSetting> pReportPrintSettings = new ArrayList<ReportPrintSetting>(); 
		try {
			// ��ȡ�û�ID
			pErrPos = 1;
			HttpServletRequest request = ServletActionContext.getRequest();
			if (pFlag) {
				if (statReportManageService.findAllReportResultTempratureHumidityForYear(pReportResultTempratureHumidityForYears, pErrInfo) == false) {
					result = "error";
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ����ͳ�ƽ�����ⷿ��ʪ����ȱ仯���ʧ�ܣ�");
				}
			}
			
			if (pFlag) {
				if (reportPrintSettingManageService.findReportPrintSettings(pReportPrintSettings, pErrInfo) == false) {
					result = "error";
					pErrInfo.getContent().insert(0, "��ѯ�����ӡ���ñ�ʧ�ܣ�");
				}
			}
			request.setAttribute("reportResultTempratureHumidityForYears", pReportResultTempratureHumidityForYears);
			request.setAttribute("reportPrintSettings", pReportPrintSettings);
			result = "success";

		} catch (Exception e) {
			result = "error";
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
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
			}
		}
		return result;
	}
	
	/**
	 * ��ѯ���еı���ͳ�ƽ�����ⷿ��ʪ���¶ȱ仯��� 
	 * @return 
	 * @throws Exception
	 */
	public String findAllReportResultTempratureHumidityForMonth() throws Exception {
		// TODO Auto-generated method stub
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		List<ReportResultTempratureHumidityForMonth>  pReportResultTempratureHumidityForMonths = new ArrayList<ReportResultTempratureHumidityForMonth>(); 
		List<ReportPrintSetting> pReportPrintSettings = new ArrayList<ReportPrintSetting>(); 
		try {
			// ��ȡ�û�ID
			pErrPos = 1;
			HttpServletRequest request = ServletActionContext.getRequest();
			if (pFlag) {
				if (statReportManageService.findAllReportResultTempratureHumidityForMonth(pReportResultTempratureHumidityForMonths, pErrInfo) == false) {
					result = "error";
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ����ͳ�ƽ�����ⷿ��ʪ���¶ȱ仯���ʧ�ܣ�");
				}
			}
			
			if (pFlag) {
				if (reportPrintSettingManageService.findReportPrintSettings(pReportPrintSettings, pErrInfo) == false) {
					result = "error";
					pErrInfo.getContent().insert(0, "��ѯ�����ӡ���ñ�ʧ�ܣ�");
				}
			}
			request.setAttribute("reportResultTempratureHumidityForMonths", pReportResultTempratureHumidityForMonths);
			request.setAttribute("reportPrintSettings", pReportPrintSettings);
			result = "success";

		} catch (Exception e) {
			result = "error";
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
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
			}
		}
		return result;
	}
	
	/**
	 * ��ѯ���еı�ͳ�ƽ������֤�շ���� 
	 * @return 
	 * @throws Exception
	 */
	public String findAllReportResultCertificateCharge() throws Exception {
		// TODO Auto-generated method stub
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		List<ReportResultCertificateCharge>  pReportResultCertificateCharges = new ArrayList<ReportResultCertificateCharge>(); 
		List<ReportPrintSetting> pReportPrintSettings = new ArrayList<ReportPrintSetting>(); 
		try {
			// ��ȡ�û�ID
			pErrPos = 1;
			HttpServletRequest request = ServletActionContext.getRequest();
			if (pFlag) {
				if (statReportManageService.findAllReportResultCertificateCharge(pReportResultCertificateCharges, pErrInfo) == false) {
					result = "error";
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ����ͳ�ƽ������֤�շ����ʧ�ܣ�");
				}
			}
			
			if (pFlag) {
				if (reportPrintSettingManageService.findReportPrintSettings(pReportPrintSettings, pErrInfo) == false) {
					result = "error";
					pErrInfo.getContent().insert(0, "��ѯ�����ӡ���ñ�ʧ�ܣ�");
				}
			}
			request.setAttribute("reportResultCertificateCharges", pReportResultCertificateCharges);
			request.setAttribute("reportPrintSettings", pReportPrintSettings);
			result = "success";

		} catch (Exception e) {
			result = "error";
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
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
			}
		}
		return result;
	}
	
	
	/**
	 * ��ѯ���еı���ͳ�ƽ�������ĵǼ���� 
	 * @return 
	 * @throws Exception
	 */
	public String findAllReportResultOfficialArchivesInput() throws Exception {
		// TODO Auto-generated method stub
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		List<ReportResultOfficialArchivesInput>  pReportResultOfficialArchivesInputs = new ArrayList<ReportResultOfficialArchivesInput>(); 
		List<ReportPrintSetting> pReportPrintSettings = new ArrayList<ReportPrintSetting>(); 
		try {
			// ��ȡ�û�ID
			pErrPos = 1;
			HttpServletRequest request = ServletActionContext.getRequest();
			if (pFlag) {
				if (statReportManageService.findAllReportResultOfficialArchivesInput(pReportResultOfficialArchivesInputs, pErrInfo) == false) {
					result = "error";
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ����ͳ�ƽ�������ĵǼ����ʧ�ܣ�");
				}
			}
			
			if (pFlag) {
				if (reportPrintSettingManageService.findReportPrintSettings(pReportPrintSettings, pErrInfo) == false) {
					result = "error";
					pErrInfo.getContent().insert(0, "��ѯ�����ӡ���ñ�ʧ�ܣ�");
				}
			}
			request.setAttribute("reportResultOfficialArchivesInputs", pReportResultOfficialArchivesInputs);
			request.setAttribute("reportPrintSettings", pReportPrintSettings);
			result = "success";

		} catch (Exception e) {
			result = "error";
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
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
			}
		}
		return result;
	}
	
	/**
	 * ��ѯ���еı���ͳ�ƽ����ϵͳ������� 
	 * @return 
	 * @throws Exception
	 */
	public String findAllReportResultSystemVisit() throws Exception {
		// TODO Auto-generated method stub
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		List<ReportResultSystemVisit>  pReportResultSystemVisits = new ArrayList<ReportResultSystemVisit>(); 
		List<ReportPrintSetting> pReportPrintSettings = new ArrayList<ReportPrintSetting>(); 
		try {
			// ��ȡ�û�ID
			pErrPos = 1;
			HttpServletRequest request = ServletActionContext.getRequest();
			if (pFlag) {
				if (statReportManageService.findAllReportResultSystemVisit(pReportResultSystemVisits, pErrInfo) == false) {
					result = "error";
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ����ͳ�ƽ����ϵͳ�������ʧ�ܣ�");
				}
			}
			
			if (pFlag) {
				if (reportPrintSettingManageService.findReportPrintSettings(pReportPrintSettings, pErrInfo) == false) {
					result = "error";
					pErrInfo.getContent().insert(0, "��ѯ�����ӡ���ñ�ʧ�ܣ�");
				}
			}
			request.setAttribute("reportResultSystemVisits", pReportResultSystemVisits);
			request.setAttribute("reportPrintSettings", pReportPrintSettings);
			result = "success";

		} catch (Exception e) {
			result = "error";
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
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
			}
		}
		return result;
	}
	
	/**
	 * ��ѯ���еı���ͳ�ƽ����������� 
	 * @return 
	 * @throws Exception
	 */
	public String findAllReportResultWorkProcedure() throws Exception {
		// TODO Auto-generated method stub
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		List<ReportResultWorkProcedure>  pReportResultWorkProcedures = new ArrayList<ReportResultWorkProcedure>(); 
		List<ReportPrintSetting> pReportPrintSettings = new ArrayList<ReportPrintSetting>(); 
		try {
			// ��ȡ�û�ID
			pErrPos = 1;
			HttpServletRequest request = ServletActionContext.getRequest();
			if (pFlag) {
				if (statReportManageService.findAllReportResultWorkProcedure(pReportResultWorkProcedures, pErrInfo) == false) {
					result = "error";
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ����ͳ�ƽ�����������ʧ�ܣ�");
				}
			}
			
			if (pFlag) {
				if (reportPrintSettingManageService.findReportPrintSettings(pReportPrintSettings, pErrInfo) == false) {
					result = "error";
					pErrInfo.getContent().insert(0, "��ѯ�����ӡ���ñ�ʧ�ܣ�");
				}
			}
			request.setAttribute("reportResultWorkProcedures", pReportResultWorkProcedures);
			request.setAttribute("reportPrintSettings", pReportPrintSettings);
			result = "success";

		} catch (Exception e) {
			result = "error";
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
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
			}
		}
		return result;
	}
}
