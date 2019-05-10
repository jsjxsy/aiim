/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.IEvaluateItemManageService;
import com.orifound.aiim.dal.dao.IEvaluateItemDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.EvaluateItem;

/**
 * 绩效考评项目字典表 服务类
 * @author tyb
 *
 */
public class EvaluateItemManageServiceImpl implements IEvaluateItemManageService {
	/**
	 * 注入 考核项表的数据访问对象
	 */
	@Autowired
	private IEvaluateItemDao evaluateItemDao = null;
	
	/**
	 * 构造函数
	 */
	public EvaluateItemManageServiceImpl() {

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public EvaluateItemManageServiceImpl(IEvaluateItemDao evaluateItemDao) {
		this.evaluateItemDao = evaluateItemDao;
	}
	
	/**
	 * 检查考核项的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForEvaluateItem(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (evaluateItemDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("考核项的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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
	 * @see com.orifound.aiim.bll.service.IEvaluateItemManageService#deleteEvaluateItem(com.orifound.aiim.entity.EvaluateItem, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteEvaluateItem(EvaluateItem evaluateItem,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IEvaluateItemManageService#findEvaluateItemByID(int, com.orifound.aiim.entity.EvaluateItem, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findEvaluateItemByID(int pID, EvaluateItem evaluateItem,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IEvaluateItemManageService#findEvaluateItems(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findEvaluateItems(List<EvaluateItem> evaluateItems, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForEvaluateItem(pErrInfo) == false) {
				pFlag = false;
			}

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				//检查传入参数 考核项集合
				if (evaluateItems == null) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->考核项集合非法为空。");
				}
			}
			
			//调用DAO
			if (pFlag) {
				pErrPos = 3;
				//DoSomething
				if (evaluateItemDao.findAll(evaluateItems, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查找所有的考核项目字典信息 失败：");
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
	 * @see com.orifound.aiim.bll.service.IEvaluateItemManageService#saveEvaluateItem(com.orifound.aiim.entity.EvaluateItem, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveEvaluateItem(EvaluateItem evaluateItem, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IEvaluateItemManageService#updateEvaluateItem(com.orifound.aiim.entity.EvaluateItem, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateEvaluateItem(EvaluateItem evaluateItem,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

}
