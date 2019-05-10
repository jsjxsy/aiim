/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.ErrInfo;
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

/**
 * ͳ�Ʊ���������Ľӿڶ���
 *
 */
public interface IStatReportManageService {

	/**
	 * ���һ���µ�ͳ�Ʊ���
	 * @param pStatReport ����ӵ�ͳ�Ʊ�����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveStatReport(StatReport pStatReport, ErrInfo pErrInfo);

	/**
	 * ɾ��ָ����ͳ�Ʊ���
	 * @param pStatReport Ҫɾ����ͳ�Ʊ�����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteStatReport(StatReport pStatReport, ErrInfo pErrInfo);

	/**
	 * �޸�ָ����ͳ�Ʊ���
	 * @param pStatReport �޸ĺ��ͳ�Ʊ�����Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateStatReport(StatReport pStatReport, ErrInfo pErrInfo);

	/**
	 * �������е�ͳ�Ʊ�����Ϣ
	 * @param pStatReports ���ز��ҳɹ���ͳ�Ʊ�����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findStatReports(List<StatReport> pStatReports, ErrInfo pErrInfo);

	/**
	 * ����Ψһ��ʶ����ͳ�Ʊ�����Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param pStatReport ���ز��ҳɹ���ͳ�Ʊ�����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findStatReportByID(int pID, StatReport pStatReport, ErrInfo pErrInfo);

	
	/**
	 * ����ͳ�ƽ�����������ݲ����
	 * @param pReportResultArchivesCollections ���ز��ҳɹ���ͳ�Ʊ�����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAllReportResultArchivesCollection(List<ReportResultArchivesCollection>  pReportResultArchivesCollections, ErrInfo pErrInfo);
	
	/**
	 * ��ѯ���еı���ͳ�ƽ�����������������
	 * @param pReportResultArchivesUsePersons ���ر���ͳ�ƽ���������������������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAllReportResultArchivesUsePerson(List<ReportResultArchivesUsePerson>  pReportResultArchivesUsePersons, ErrInfo pErrInfo);
	
	/**
	 * ��ѯ����ͳ�ƽ�������������������
	 * @param pReportResultArchivesTypeUses
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAllReportResultArchivesTypeUse(List<ReportResultArchivesTypeUse>  pReportResultArchivesTypeUses, ErrInfo pErrInfo);
	
	/**
	 * ��ѯ����ͳ�ƽ������������Ŀ����� 
	 * @param pReportResultArchivesUsePurposes
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAllReportResultArchivesUsePurpose(List<ReportResultArchivesUsePurpose>  pReportResultArchivesUsePurposes, ErrInfo pErrInfo);
	
	/**
	 * ��ѯ����ͳ�ƽ�����������鵵��� 
	 * @param pReportResultArchivesSaveds
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAllReportResultArchivesSaved(List<ReportResultArchivesSaved>  pReportResultArchivesSaveds, ErrInfo pErrInfo);
	
	/**
	 * ��ѯ����ͳ�ƽ�������Ź鵵��� 
	 * @param pReportResultDepartmentSaveds
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAllReportResultDepartmentSaved(List<ReportResultDepartmentSaved>  pReportResultDepartmentSaveds, ErrInfo pErrInfo);
	
	/**
	 * ��ѯ����ͳ�ƽ�������µ���ת����� 
	 * @param pReportResultPersionalArchivesMoveOuts
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAllReportResultPersionalArchivesMoveOut(List<ReportResultPersionalArchivesMoveOut>  pReportResultPersionalArchivesMoveOuts, ErrInfo pErrInfo);
	
	
	/**
	 * ��ѯ����ͳ�ƽ��������������� 
	 * @param pReportResultArchivesDestroys
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAllReportResultArchivesDestroy(List<ReportResultArchivesDestroy>  pReportResultArchivesDestroys, ErrInfo pErrInfo);
	
	/**
	 * ��ѯ����ͳ�ƽ��������������� 
	 * @param pReportResultArchivesPublics
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAllReportResultArchivesPublic(List<ReportResultArchivesPublic>  pReportResultArchivesPublics, ErrInfo pErrInfo);
	
	/**
	 * ��ѯ����ͳ�ƽ�����ⷿ��ʩ������� 
	 * @param pReportResultStoreroomUses
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAllReportResultStoreroomUse(List<ReportResultStoreroomUse>  pReportResultStoreroomUses, ErrInfo pErrInfo);
	
	/**
	 * ��ѯ����ͳ�ƽ�����ⷿ��ʪ����ȱ仯��� 
	 * @param pReportResultStoreroomUses
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAllReportResultTempratureHumidityForYear(List<ReportResultTempratureHumidityForYear>  pReportResultTempratureHumidityForYears, ErrInfo pErrInfo);
	
	
	/**
	 * ��ѯ����ͳ�ƽ�����ⷿ��ʪ���¶ȱ仯��� 
	 * @param pReportResultTempratureHumidityForMonths
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAllReportResultTempratureHumidityForMonth(List<ReportResultTempratureHumidityForMonth>  pReportResultTempratureHumidityForMonths, ErrInfo pErrInfo);
	
	/**
	 * ��ѯ����ͳ�ƽ������֤�շ���� 
	 * @param pReportResultCertificateCharges
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAllReportResultCertificateCharge(List<ReportResultCertificateCharge>  pReportResultCertificateCharges, ErrInfo pErrInfo);
	
	/**
	 * ��ѯ����ͳ�ƽ�������ĵǼ���� 
	 * @param pReportResultOfficialArchivesInputs
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAllReportResultOfficialArchivesInput(List<ReportResultOfficialArchivesInput>  pReportResultOfficialArchivesInputs, ErrInfo pErrInfo);
	
	/**
	 * ��ѯ����ͳ�ƽ����ϵͳ������� 
	 * @param pReportResultOfficialArchivesInputs
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAllReportResultSystemVisit(List<ReportResultSystemVisit>  pReportResultSystemVisits, ErrInfo pErrInfo);
	
	/**
	 * ��ѯ����ͳ�ƽ����������� 
	 * @param pReportResultWorkProcedures
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAllReportResultWorkProcedure(List<ReportResultWorkProcedure>  pReportResultWorkProcedures, ErrInfo pErrInfo);
	
	
}
