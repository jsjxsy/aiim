/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ReportResultArchivesUsePerson;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 报表统计结果档案利用人情况表的DAO接口定义
 *
 */
public interface IReportResultArchivesUsePersonDao {

	/**
	 * Dao接口定义：添加ReportResultArchivesUsePerson
	 * @param pReportResultArchivesUsePerson 要添加的ReportResultArchivesUsePerson
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(ReportResultArchivesUsePerson pReportResultArchivesUsePerson, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的ReportResultArchivesUsePerson
	 * @param pReportResultArchivesUsePerson 要删除的ReportResultArchivesUsePerson
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(ReportResultArchivesUsePerson pReportResultArchivesUsePerson, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的ReportResultArchivesUsePerson
	 * @param pReportResultArchivesUsePerson 要更新的ReportResultArchivesUsePerson
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(ReportResultArchivesUsePerson pReportResultArchivesUsePerson, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的ReportResultArchivesUsePerson
	 * @param pReportResultArchivesUsePersons 返回查找成功的ReportResultArchivesUsePerson集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<ReportResultArchivesUsePerson> pReportResultArchivesUsePersons, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找ReportResultArchivesUsePerson
	 * @param pID 指定的唯一标识
	 * @param pReportResultArchivesUsePerson 返回查找成功的ReportResultArchivesUsePerson
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, ReportResultArchivesUsePerson pReportResultArchivesUsePerson, ErrInfo pErrInfo);

}
