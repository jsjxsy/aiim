/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.IEvaluateLevelManageService;
import com.orifound.aiim.dal.dao.IEvaluateLevelDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.EvaluateLevel;

/**
 * 考核评分项管理服务类
 * @author tyb
 *
 */
public class EvaluateLevelManageServiceImpl implements IEvaluateLevelManageService {
	/**
	 * 考核评分等级表的数据访问对象
	 */
	@Autowired
	private IEvaluateLevelDao evaluateLevelDao = null;

	/**
	 * 构造函数
	 */
	public EvaluateLevelManageServiceImpl() {

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public EvaluateLevelManageServiceImpl(IEvaluateLevelDao evaluateLevelDao) {
		this.evaluateLevelDao = evaluateLevelDao;
	}
	
	
	/**
	 * 检查考核评分等级的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForEvaluateLevel(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (evaluateLevelDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("考核评分等级的DAO非法为空，请检查是否有进行依赖注入或赋值。");
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
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
	 * @see com.orifound.aiim.bll.service.IEvaluateLevelManageService#deleteEvaluateLevel(com.orifound.aiim.entity.EvaluateLevel, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteEvaluateLevel(EvaluateLevel evaluateLevel,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IEvaluateLevelManageService#findEvaluateLevelByID(int, com.orifound.aiim.entity.EvaluateLevel, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findEvaluateLevelByID(int pID, EvaluateLevel evaluateLevel,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IEvaluateLevelManageService#findEvaluateLevels(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findEvaluateLevels(List<EvaluateLevel> evaluateLevels, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForEvaluateLevel(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检查 考核评分等级集合 是否为空
			if (pFlag) {
				if (evaluateLevels == null) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->考核评分等级集合非法为空。");
				}
			}
			
			if (pFlag) {
				pErrPos = 2;
				//调用DAO 查询考核评分等级集合
				if (evaluateLevelDao.findAll(evaluateLevels, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询考核评分等级集合 失败：");
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
			//销毁局部变量
			throwable = null;
		}
		return pFlag;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IEvaluateLevelManageService#saveEvaluateLevel(com.orifound.aiim.entity.EvaluateLevel, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveEvaluateLevel(EvaluateLevel evaluateLevel,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IEvaluateLevelManageService#updateEvaluateLevel(com.orifound.aiim.entity.EvaluateLevel, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateEvaluateLevel(EvaluateLevel evaluateLevel,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

}
