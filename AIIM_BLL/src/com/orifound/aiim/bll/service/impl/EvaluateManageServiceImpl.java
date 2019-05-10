/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.IEvaluateManageService;
import com.orifound.aiim.dal.dao.IEvaluateDetailsDao;
import com.orifound.aiim.dal.dao.IEvaluateRegisterDao;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.EvaluateDetails;
import com.orifound.aiim.entity.EvaluateRegister;
import com.orifound.aiim.entity.EvaluateRegisterVO;

/**
 * 考核管理服务实现类
 * @author tyb
 *
 */
public class EvaluateManageServiceImpl implements IEvaluateManageService {
	/**
	 * 考核登记表的数据访问对象
	 */
	@Autowired
	private IEvaluateRegisterDao evaluateRegisterDao ;
	
	/**
	 * 考核登记明细表的数据访问对象
	 */
	@Autowired
	private IEvaluateDetailsDao evaluateDetailsDao;
	
	/**
	 * 检查考核登记表的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForEvaluateRegister(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (evaluateRegisterDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("考核登记表的DAO非法为空，请检查是否有进行依赖注入或赋值。");
			}
			
			if (pFlag) {
				if (evaluateDetailsDao == null) {
					pFlag = false;
					pErrInfo.getContent().append("考核登记详细表的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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
		}

		return pFlag;
	}


	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IEvaluateManageService#findByMaxYear(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByMaxYear(DataPageInfo dataPageInfo, List<EvaluateRegisterVO> evaluateRegisterVOs, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForEvaluateRegister(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检查 考核登记信息显示类集合 是否为空
			if (pFlag) {
				if (evaluateRegisterVOs == null) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->考核登记信息显示类集合非法为空。");
				}
			}

			if (pFlag) {
				pErrPos = 2;
				//查询已经通过考核的最大年度的考核记录
				if (evaluateRegisterDao.findByMaxYear(dataPageInfo, evaluateRegisterVOs, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询已经通过考核的最大年度的考核记录 失败：");
				}
				
				
				if(evaluateRegisterVOs.size() <= 0) {	//没有考核记录则显示当前年度需要考核的所有考核人员
					String maxYear = new SimpleDateFormat("yyyy").format(new Date());
					
					//查询当前年度考核记录
					if (findByYear(maxYear, dataPageInfo, evaluateRegisterVOs, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "查询当前年度考核记录 失败：");
					}
					
					if(evaluateRegisterVOs.size() <= 0) {	//不存在当前年度的考核记录
						//插入当前年度的考核记录
						if (pFlag) {
							if(evaluateRegisterDao.insertByYear(maxYear, pErrInfo) == false ) {
								pFlag = false;
								pErrInfo.getContent().insert(0, "插入当前年度的考核记录 失败：");
							}
						}
						
						//插入当前年度的考核记录明细
						if (pFlag) {
							if (evaluateDetailsDao.insertByYear(maxYear, pErrInfo) == false) {
								pFlag = false;
								pErrInfo.getContent().insert(0, "插入当前年度的考核记录明细 失败：");
							}
						}
						
						//查询当前年度考核记录
						if (pFlag) {
							if (findByYear(maxYear, dataPageInfo, evaluateRegisterVOs, pErrInfo) == false) {
								pFlag = false;
								pErrInfo.getContent().insert(0, "查询当前年度考核记录 失败：");
							}
						}
					}
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


	@Override
	public boolean findByYear(String year, DataPageInfo dataPageInfo, List<EvaluateRegisterVO> evaluateRegisterVOs, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForEvaluateRegister(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检查 年度是否为空
			if (pFlag) {
				if (year==null || "".equals(year.trim())) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->考核年度非法为零。");
				}
			}
			
			//检查 年度是否为纯数字
			if (pFlag) {
				if (year.matches("[0-9]*") == false) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->考核年度不是纯数字非法。");
				}
			}
			
			//检查 考核登记信息显示类集合 是否为空
			if (pFlag) {
				if (evaluateRegisterVOs == null) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->考核登记信息显示类集合非法为零。");
				}
			}

			if (pFlag) {
				pErrPos = 2;
				//调用DAO 查特定年度的考核记录
				if (evaluateRegisterDao.findByYear(year, dataPageInfo, evaluateRegisterVOs, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查特定年度的考核记录 失败：");
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


	@Override
	public boolean findEvaluateDetailsByRegID(int evaluateRegID, EvaluateRegisterVO evaluateRegisterVO, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForEvaluateRegister(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检查考核记录编号
			if (pFlag) {
				if (evaluateRegID <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->考核记录编号非法为零。");
				}
			}
			
			//检查 考核信息显示类
			if (pFlag) {
				if (evaluateRegisterVO == null) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->核信息显示类非法为空。");
				}
			}

			if (pFlag) {
				pErrPos = 2;
				//调用DAO 查询考核登记明细信息
				if (evaluateRegisterDao.findByEvaluateRegID(evaluateRegID, evaluateRegisterVO, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查特定年度的考核记录 失败：");
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


	@Override
	public boolean updateDetail(EvaluateDetails evaluateDetails,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForEvaluateRegister(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检查 考核登记编号
			if (pFlag) {
				if (evaluateDetails.getEvaluateRegID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->考核登记编号非法。");
				}
			}
			
			//检查 考核明细编号
			if (pFlag) {
				if (evaluateDetails.getEvaluateItemID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->考核明细编号非法。");
				}
			}
			
			//判断 评分
			if (pFlag) {
				if (evaluateDetails.getScore() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->评分分数非法。");
				}
			}
			
			//调用DAO 更新考核明细
			if (pFlag) {
				pErrPos = 2;
				if (evaluateDetailsDao.update(evaluateDetails, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "更新指定的考核明细信息 失败。");
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


	@Override
	public boolean updateEvaluate(EvaluateRegister evaluateRegister, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForEvaluateRegister(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检查总分数
			if (pFlag) {
				if (evaluateRegister.getScore() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->总分数非法。");
				}
			}
			
			//检查 考核人id
			if (pFlag) {
				if (evaluateRegister.getCheckerUserID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->考核人id非法。");
				}
			}
			
			//检查 考核人职务id
			if (pFlag) {
				if (evaluateRegister.getCheckerDutyID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->考核人职务id非法。");
				}
			}
			
			//检查 考核等级id
			if (pFlag) {
				if (evaluateRegister.getLevelID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->考核等级id非法。");
				}
			}
			if (pFlag) {
				pErrPos = 2;
				//调用DAO 更新考核登记信息
				if (evaluateRegisterDao.update(evaluateRegister, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "更新考核登记信息 失败：");
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


	@Override
	public boolean insertAppendByYear(String year, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForEvaluateRegister(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				//调用DAO 追加特定年度的考核记录
				if (evaluateRegisterDao.insertAppendByYear(year, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "追加特定年度的考核记录 失败：");
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


	@Override
	public boolean insertByYear(String year, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForEvaluateRegister(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检查 年度是否为空
			if (pFlag) {
				if (year==null || "".equals(year.trim())) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->考核年度非法为零。");
				}
			}
			
			//检查 年度是否为纯数字
			if (pFlag) {
				if (year.matches("[0-9]*") == false) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->考核年度不是纯数字非法。");
				}
			}
			
			//检查考核年度是否为当前年度
			if (pFlag) {
				int intYear = Integer.valueOf(year);
				if(intYear != Calendar.getInstance().get(Calendar.YEAR)) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->考核年度不是现在年度，不能插入考核记录。");
				}
			}
			
			if (pFlag) {
				pErrPos = 2;
				//插入特定年度的考核记录
				if (evaluateRegisterDao.insertByYear(year, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "插入特定年度的考核记录 失败：");
				}
			}
			
			if (pFlag) {
				//插入当前年度的考核明细记录
				if (evaluateDetailsDao.insertByYear(year, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "插入特定年度的考核明细记录 失败：");
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


	@Override
	public boolean search(String evaluateName, int dutyId, String registerDate,
			int minScore, int maxScore, DataPageInfo dataPageInfo,
			List<EvaluateRegisterVO> evaluateRegisterVOs, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForEvaluateRegister(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检索考核登记信息
			if (pFlag) {
				pErrPos = 2;
				if (evaluateRegisterDao.search(evaluateName, dutyId, registerDate, minScore, maxScore, dataPageInfo, evaluateRegisterVOs, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "检索考核登记信息 失败：");
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


	public IEvaluateRegisterDao getEvaluateRegisterDao() {
		return evaluateRegisterDao;
	}


	public void setEvaluateRegisterDao(IEvaluateRegisterDao evaluateRegisterDao) {
		this.evaluateRegisterDao = evaluateRegisterDao;
	}


	public IEvaluateDetailsDao getEvaluateDetailsDao() {
		return evaluateDetailsDao;
	}


	public void setEvaluateDetailsDao(IEvaluateDetailsDao evaluateDetailsDao) {
		this.evaluateDetailsDao = evaluateDetailsDao;
	}


	@Override
	public boolean deleteBatch(List<Integer> evaluateIds, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForEvaluateRegister(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检查 考核登记信息id集合是否为空
			if (pFlag) {
				if (evaluateIds==null || evaluateIds.size()<=0) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->考核登记信息id集合非法为空。");
				}
			}

			if (pFlag) {
				pErrPos = 2;
				//批量删除指定的考核登记信息
				if (pFlag) {
					if (evaluateRegisterDao.deleteBatch(evaluateIds, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "批量删除指定的考核登记信息 失败：");
					}
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


	@Override
	public boolean findCountByYear(String year, EvaluateRegisterVO evaluateRegisterVO, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForEvaluateRegister(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检查 年度是否为空
			if (pFlag) {
				if (year==null || "".equals(year.trim())) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->考核年度非法为零。");
				}
			}
			
			//检查 年度是否为纯数字
			if (pFlag) {
				if (year.matches("[0-9]*") == false) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->考核年度不是纯数字非法。");
				}
			}

			if (pFlag) {
				pErrPos = 2;
				//查询特定年度的考核记录数
				if (evaluateRegisterDao.findCountByYear(year, evaluateRegisterVO, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Service查询特定年度的考核记录数 失败：");
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


	@Override
	public boolean findEvaluatedYears(List<String> evaluatedYears, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForEvaluateRegister(pErrInfo) == false) {
				pFlag = false;
			}
			
			if (pFlag) {
				if (evaluatedYears == null) {
					pFlag = false;
					pErrInfo.getContent().append("考核年度集合非法为空。");
				}
			}

			if (pFlag) {
				pErrPos = 2;
				//获取数据库中存在的考核记录年度
				if (evaluateRegisterDao.findEvaluatedYears(evaluatedYears, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Service 获取数据库中存在的考核记录年度 失败：");
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


	@Override
	public boolean search(String year, List<EvaluateRegisterVO> evaluateRegisterVOs, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForEvaluateRegister(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检查 年度是否为空
			if (pFlag) {
				if (year==null || "".equals(year.trim())) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->考核年度非法为零。");
				}
			}
			
			//检查 年度是否为纯数字
			if (pFlag) {
				if (year.matches("[0-9]*") == false) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->考核年度不是纯数字非法。");
				}
			}
			
			//检查 考核登记信息显示类集合 是否为空
			if (pFlag) {
				if (evaluateRegisterVOs == null) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->考核登记信息显示类集合非法为零。");
				}
			}

			if (pFlag) {
				pErrPos = 2;
				if (evaluateRegisterDao.search(year, evaluateRegisterVOs, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "检索考核登记信息 ");
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


	@Override
	public boolean findNeedAppend(String currentYear, Integer[] count, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForEvaluateRegister(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检测人员数量数组是否为空
			if (pFlag) {
				if (count==null || count.length<=0) {
					pFlag = false;
					pErrInfo.getContent().append("人员数量数组非法为空。");
				}
			}

			//查询当前年度是否需要追加新进的人员
			if (pFlag) {
				pErrPos = 2;
				if (evaluateRegisterDao.findNeedAppend(currentYear, count, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询当前年度是否需要追加新进的人员 失败：");
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
		
System.out.println(pErrInfo.toString());

		return pFlag;
	}


	@Override
	public boolean findMaxYear(EvaluateRegister evaluateRegister, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForEvaluateRegister(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检测考核记录是否为空
			if (pFlag) {
				if (evaluateRegister == null) {
					pFlag = false;
					pErrInfo.getContent().append("考核记录非法为空。");
				}
			}

			//查询已经通过考核的最大年度
			if (pFlag) {
				pErrPos = 2;
				if (evaluateRegisterDao.findMaxYear(evaluateRegister, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询已经通过考核的最大年度 失败");
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
}