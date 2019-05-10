package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.EvaluateDetails;
import com.orifound.aiim.entity.EvaluateRegister;
import com.orifound.aiim.entity.EvaluateRegisterVO;

/**
 * 考核管理服务的接口定义
 * @author tyb
 *
 */
public interface IEvaluateManageService {
	/**
	 * 查询已经通过考核的最大年度的考核记录
	 * @paramn dataPageInfo 数据页信息对象类
	 * @param evaluateRegisterVOs 返回查找成功的考核登记信息显示类集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByMaxYear(DataPageInfo dataPageInfo, List<EvaluateRegisterVO> evaluateRegisterVOs, ErrInfo pErrInfo);
	
	/**
	 * 分页查询特定年度的考核记录显示类
	 * @param year 查询年度
	 * @paramn dataPageInfo 数据页信息对象类
	 * @param evaluateRegisterVOs 返回查找成功的考核登记信息显示类集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByYear(String year, DataPageInfo dataPageInfo, List<EvaluateRegisterVO> evaluateRegisterVOs, ErrInfo pErrInfo);
	
	/**
	 * 查询特定年度的考核记录数
	 * @param year 查询年度
	 * @param evaluateRegisterVO 返回查找成功的考核登记信息显示类
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findCountByYear(String year, EvaluateRegisterVO evaluateRegisterVO, ErrInfo pErrInfo);
	
	/**
	 * 根据考核记录编号查询考核登记明细信息
	 * @param evaluateRegID 考核记录编号
	 * @param evaluateRegisterVO 返回查找成功的考核信息显示类
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findEvaluateDetailsByRegID(int evaluateRegID, EvaluateRegisterVO evaluateRegisterVO, ErrInfo pErrInfo);
	
	/**
	 * 更新指定的考核明细信息
	 * @param EvaluateDetails 要更新的考核明细信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateDetail(EvaluateDetails evaluateDetails, ErrInfo pErrInfo);
	
	/**
	 * 更新指定的考核登记信息
	 * @param EvaluateRegister 要更新的考核登记信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateEvaluate(EvaluateRegister evaluateRegister, ErrInfo pErrInfo);
	
	/**
	 * 追加特定年度的考核记录
	 * @paran year 追加记录年度
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean insertAppendByYear(String year, ErrInfo pErrInfo);
	
	/**
	 * 插入特定年度的考核记录以及明细
	 * @paran year 追加记录年度
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean insertByYear(String year, ErrInfo pErrInfo);
	
	/**
	 * 检索考核登记信息
	 * @param evaluateName 	考核人姓名
	 * @param dutyId 		职务id
	 * @param registerDate 	考核日期
	 * @param minScore		最小分数
	 * @param maxScore		最大分数
	 * @param dataPageInfo	数据页信息对象类
	 * @param evaluateRegisterVOs 返回查找成功的考核信息显示类
	 * @param pErrInfo 		返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean search(String evaluateName, int dutyId, String registerDate, int minScore, int maxScore, DataPageInfo dataPageInfo, List<EvaluateRegisterVO> evaluateRegisterVOs, ErrInfo pErrInfo);
	
	/**
	 * 批量删除指定的考核登记信息
	 * @param evaluateIds 要删除的考核登记id集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteBatch(List<Integer> evaluateIds, ErrInfo pErrInfo);
	
	/**
	 * 获取数据库中存在的考核记录年度
	 * @param evaluatedYears 返回考核年度集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findEvaluatedYears(List<String> evaluatedYears, ErrInfo pErrInfo);
	
	/**
	 * 根据考核年度检索考核登记信息
	 * @param year 			考核年度
	 * @param evaluateRegisterVOs 返回查找成功的考核信息显示类
	 * @param pErrInfo 		返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean search(String year, List<EvaluateRegisterVO> evaluateRegisterVOs, ErrInfo pErrInfo);
	
	/**
	 * 查询当前年度是否需要追加新进的人员,控制以前年度不需要追加
	 * @param currentYear 	当前年度
	 * @param count			count[0] 保存不在考核表的人员数
	 * @param pErrInfo 		返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findNeedAppend(String currentYear, Integer[] count, ErrInfo pErrInfo);
	
	/**
	 * 查询已经通过考核的最大年度
	 * @param evaluateRegister 返回考核记录的最大年度
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findMaxYear(EvaluateRegister evaluateRegister, ErrInfo pErrInfo);
}
