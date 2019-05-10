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
 * 统计报表管理服务的接口定义
 *
 */
public interface IStatReportManageService {

	/**
	 * 添加一个新的统计报表
	 * @param pStatReport 新添加的统计报表信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveStatReport(StatReport pStatReport, ErrInfo pErrInfo);

	/**
	 * 删除指定的统计报表
	 * @param pStatReport 要删除的统计报表，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteStatReport(StatReport pStatReport, ErrInfo pErrInfo);

	/**
	 * 修改指定的统计报表
	 * @param pStatReport 修改后的统计报表信息，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateStatReport(StatReport pStatReport, ErrInfo pErrInfo);

	/**
	 * 查找所有的统计报表信息
	 * @param pStatReports 返回查找成功的统计报表集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findStatReports(List<StatReport> pStatReports, ErrInfo pErrInfo);

	/**
	 * 根据唯一标识查找统计报表信息
	 * @param pID 指定的唯一标识
	 * @param pStatReport 返回查找成功的统计报表信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findStatReportByID(int pID, StatReport pStatReport, ErrInfo pErrInfo);

	
	/**
	 * 报表统计结果表－案卷档案馆藏情况
	 * @param pReportResultArchivesCollections 返回查找成功的统计报表信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAllReportResultArchivesCollection(List<ReportResultArchivesCollection>  pReportResultArchivesCollections, ErrInfo pErrInfo);
	
	/**
	 * 查询所有的报表统计结果表－档案利用人情况
	 * @param pReportResultArchivesUsePersons 返回报表统计结果表－档案利用人情况集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAllReportResultArchivesUsePerson(List<ReportResultArchivesUsePerson>  pReportResultArchivesUsePersons, ErrInfo pErrInfo);
	
	/**
	 * 查询报表统计结果表－档案分类利用情况
	 * @param pReportResultArchivesTypeUses
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAllReportResultArchivesTypeUse(List<ReportResultArchivesTypeUse>  pReportResultArchivesTypeUses, ErrInfo pErrInfo);
	
	/**
	 * 查询报表统计结果表－档案利用目的情况 
	 * @param pReportResultArchivesUsePurposes
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAllReportResultArchivesUsePurpose(List<ReportResultArchivesUsePurpose>  pReportResultArchivesUsePurposes, ErrInfo pErrInfo);
	
	/**
	 * 查询报表统计结果表－案卷档案归档情况 
	 * @param pReportResultArchivesSaveds
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAllReportResultArchivesSaved(List<ReportResultArchivesSaved>  pReportResultArchivesSaveds, ErrInfo pErrInfo);
	
	/**
	 * 查询报表统计结果表－部门归档情况 
	 * @param pReportResultDepartmentSaveds
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAllReportResultDepartmentSaved(List<ReportResultDepartmentSaved>  pReportResultDepartmentSaveds, ErrInfo pErrInfo);
	
	/**
	 * 查询报表统计结果表－人事档案转出情况 
	 * @param pReportResultPersionalArchivesMoveOuts
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAllReportResultPersionalArchivesMoveOut(List<ReportResultPersionalArchivesMoveOut>  pReportResultPersionalArchivesMoveOuts, ErrInfo pErrInfo);
	
	
	/**
	 * 查询报表统计结果表－档案销毁情况 
	 * @param pReportResultArchivesDestroys
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAllReportResultArchivesDestroy(List<ReportResultArchivesDestroy>  pReportResultArchivesDestroys, ErrInfo pErrInfo);
	
	/**
	 * 查询报表统计结果表－档案开放情况 
	 * @param pReportResultArchivesPublics
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAllReportResultArchivesPublic(List<ReportResultArchivesPublic>  pReportResultArchivesPublics, ErrInfo pErrInfo);
	
	/**
	 * 查询报表统计结果表－库房设施利用情况 
	 * @param pReportResultStoreroomUses
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAllReportResultStoreroomUse(List<ReportResultStoreroomUse>  pReportResultStoreroomUses, ErrInfo pErrInfo);
	
	/**
	 * 查询报表统计结果表－库房温湿度年度变化情况 
	 * @param pReportResultStoreroomUses
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAllReportResultTempratureHumidityForYear(List<ReportResultTempratureHumidityForYear>  pReportResultTempratureHumidityForYears, ErrInfo pErrInfo);
	
	
	/**
	 * 查询报表统计结果表－库房温湿度月度变化情况 
	 * @param pReportResultTempratureHumidityForMonths
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAllReportResultTempratureHumidityForMonth(List<ReportResultTempratureHumidityForMonth>  pReportResultTempratureHumidityForMonths, ErrInfo pErrInfo);
	
	/**
	 * 查询报表统计结果表－出证收费情况 
	 * @param pReportResultCertificateCharges
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAllReportResultCertificateCharge(List<ReportResultCertificateCharge>  pReportResultCertificateCharges, ErrInfo pErrInfo);
	
	/**
	 * 查询报表统计结果表－公文登记情况 
	 * @param pReportResultOfficialArchivesInputs
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAllReportResultOfficialArchivesInput(List<ReportResultOfficialArchivesInput>  pReportResultOfficialArchivesInputs, ErrInfo pErrInfo);
	
	/**
	 * 查询报表统计结果表－系统访问情况 
	 * @param pReportResultOfficialArchivesInputs
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAllReportResultSystemVisit(List<ReportResultSystemVisit>  pReportResultSystemVisits, ErrInfo pErrInfo);
	
	/**
	 * 查询报表统计结果表－工作情况 
	 * @param pReportResultWorkProcedures
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAllReportResultWorkProcedure(List<ReportResultWorkProcedure>  pReportResultWorkProcedures, ErrInfo pErrInfo);
	
	
}
