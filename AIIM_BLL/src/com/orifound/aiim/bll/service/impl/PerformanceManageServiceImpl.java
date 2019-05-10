/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.IPerformanceManageService;
import com.orifound.aiim.dal.dao.IArchivesInfoWorkProcedureDao;
import com.orifound.aiim.dal.dao.IPaperTransferBatchesDao;
import com.orifound.aiim.dal.dao.IUserInfoDao;
import com.orifound.aiim.dal.util.StringTool;
import com.orifound.aiim.dal.util.TimeTool;
import com.orifound.aiim.entity.EnumSystemRole;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserInfo;

/**
 * 绩效管理服务的接口定义
 * @author tyb
 *
 */
public class PerformanceManageServiceImpl implements IPerformanceManageService {
	
	/**
	 * 档案归档过程表DAO
	 */
	@Autowired
	private IArchivesInfoWorkProcedureDao archivesInfoWorkProcedureDao;
	
	/**
	 * 移交批次信息表的DAO
	 */
	@Autowired
	private IPaperTransferBatchesDao paperTransferBatchesDao;
	
	/**
	 * 注入用户信息表的DAO
	 */
	@Autowired
	private IUserInfoDao userInfoDao;
	
	/**
	 * 检查DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForDAO(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (archivesInfoWorkProcedureDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("的DAO非法为空，请检查是否有进行依赖注入或赋值。");
			}
			
			if (pFlag) {
				if (paperTransferBatchesDao == null) {
					pFlag = false;
					pErrInfo.getContent().append("移交批次信息表的DAO非法为空，请检查是否有进行依赖注入或赋值。");
				}
			}
			
			if (pFlag) {
				if (userInfoDao == null) {
					pFlag = false;
					pErrInfo.getContent().append("用户信息表的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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

	/**
	 * 绩效管理->入库情况查询
	 * @param counts  counts[0]总移交数、counts[1]入库数
	 * @param beginTime 监测开始时间
	 * @param endTime 监测结束时间
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return boolean 处理成功返回true，否则返回false
	 */
	public boolean findInStorageCondition(Map<String, Integer> inStorageCondition, String beginTime, String endTime, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForDAO(pErrInfo) == false) {
				pFlag = false;
			}
			
			List<Integer> userIds = null;
			if (pFlag) {
				//查询所有具有档案管理室角色的人员信息集合
				List<UserInfo> userInfos = new ArrayList<UserInfo>();
				if (findArchivesinfoManagers(userInfos , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询所有具有档案管理室角色的人员信息集合 失败：");
				}
				
				if (userInfos.size() >= 1) {
					userIds = new ArrayList<Integer>();
					for(UserInfo info : userInfos) {
						userIds.add(info.getUserID());
					}
				}
			}

			if (pFlag) {
				pErrPos = 2;
				//查找档案管理室入库情况
				if (archivesInfoWorkProcedureDao.findInStorageCondition(inStorageCondition, userIds, beginTime, endTime, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"查找档案管理室入库情况失败");
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
	
	/**
	 * 监测档案形成部门、业务指导室的人员特定时间段内著录档案的数量<br>
	 * @param recordCondition map集合保存:人员真实姓名-著录数量 键值对
	 * @param userIds 人员id集合
	 * @param beginTime 检测开始时间	
	 * @param endTime	检测结束时间
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	 public boolean findRecordCondition(Map<String, Integer> recordCondition, List<Integer> userIds, String beginTime, String endTime, ErrInfo pErrInfo) {
			boolean pFlag = true;
			int pErrPos = 0;
			Throwable throwable = new Throwable();

			try {
				pErrPos = 1;
				//检查是否有进行DAO依赖注入
				if (checkDaoInjectionForDAO(pErrInfo) == false) {
					pFlag = false;
				}
				
				//检查著录结果map集合是否为空
				if (pFlag) {
					if(recordCondition == null) {
						pFlag = false;
						pErrInfo.getContent().append("输入参数:著录结果map集合非法为空。");
					}
				}
				
				//检查业务指导室人员id集合是否为空
				if (pFlag) {
					if(userIds==null || userIds.size()<=0) {
						pFlag = false;
						pErrInfo.getContent().append("输入参数:人员id集合非法为空。");
					}
				}

				//检查开始时间、结束时间不为空时，开始时间是否小于结束时间
				if (pFlag) {
					if(StringTool.checkNull(beginTime) && StringTool.checkNull(endTime)) {
						Date beginDate = TimeTool.getDateFromString(beginTime);
						Date endDate = TimeTool.getDateFromString(endTime);
						if(beginDate.getTime() >= endDate.getTime()) {
							pErrInfo.getContent().append("输入参数:开始时间大于结束时间非法。");
						}
					}
				}
				
				if (pFlag) {
					pErrPos = 2;
					if(archivesInfoWorkProcedureDao.recordCondition(recordCondition, userIds, beginTime, endTime, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "监测档案形成部门、业务指导室的人员特定时间段内著录情况 DAO执行失败。");
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
	 
	 /**
	 * 监测业务指导室人员特定时间段内审核、待审核档案的数量<br>
	 * @param recordAudit 封装成Map<String, Integer[]>保存人员审核档案数量,String保存人员真实姓名,Integer[0]保存已审核、Integer[1]待审核
	 * @param userIds 人员id集合
	 * @param beginTime 检测开始时间
	 * @param endTime	检测结束时间
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	 public boolean findRecordAuditCondition(Map<String, Integer[]> recordAudit, List<Integer> userIds, String beginTime, String endTime, ErrInfo pErrInfo) {
		 boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForDAO(pErrInfo) == false) {
				pFlag = false;
			}
			
			if (pFlag) {
				pErrPos = 2;
				if (archivesInfoWorkProcedureDao.recordAuditCondition(recordAudit, userIds, beginTime, endTime, pErrInfo) == false ) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "监测业务指导室人员特定时间段内审核、待审核档案的数量->DAO执行失败。");
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
	 
	 /**
	 * 查询所有具有业务指导室角色的人员信息集合
	 * @param userInfos 查找成功后返回的用户信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean findBusinessGuids(List<UserInfo> userInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			if (checkDaoInjectionForDAO(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				//DAO调用
				if (userInfoDao.findBusinessGuids(userInfos, EnumSystemRole.业务指导专员角色, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询所有具有业务指导室角色的人员信息集合 DAO执行失败。");
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
		
	/**
	 * 查询业务指导室指导的所有档案兼职人员
	 * @param businessGuidIds 业务指导室人员id集合
	 * @param userIds 档案兼职人员Id集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean findPartTimePersons(List<Integer> businessGuidIds, List<Integer> userIds, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForDAO(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				//DAO调用
				if (userInfoDao.findPartTimePersons(businessGuidIds, userIds, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询业务指导室指导的所有档案兼职人员 DAO执行失败。");
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
	public boolean findArchivesinfoManagers(List<UserInfo> userInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForDAO(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				//查询所有具有档案管理室角色的人员信息集合
				if (userInfoDao.findArchivesinfoManagers(userInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Service 查询所有具有档案管理室角色的人员信息集合 失败：");
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
	public boolean receiverCondition(List<Integer> counts, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForDAO(pErrInfo) == false) {
				pFlag = false;
			}

			//查询绩效管理->入库情况查询 档案管理室接收情况
			if (pFlag) {
				pErrPos = 2;
				if (paperTransferBatchesDao.receiverCondition(counts, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询绩效管理->入库情况查询 档案管理室接收情况 失败：");
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