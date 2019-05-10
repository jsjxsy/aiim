/**
 * 
 */
package com.orifound.aiim.web.struts;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.IEvaluateManageService;
import com.orifound.aiim.bll.service.IPerformanceManageService;
import com.orifound.aiim.bll.service.ITaskInfoManageService;
import com.orifound.aiim.bll.service.ITaskResponseManageService;
import com.orifound.aiim.bll.service.IUserInfoManageService;
import com.orifound.aiim.web.util.StringTool;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.EvaluateRegister;
import com.orifound.aiim.entity.EvaluateRegisterVO;
import com.orifound.aiim.entity.TaskPerson;
import com.orifound.aiim.entity.TaskResponse;
import com.orifound.aiim.entity.UserInfo;

/**
 * 绩效管理DWR
 * @author tyb
 *
 */
public class PerformanceManageDWR {
	
	/**
	 * 注入绩效信息服务类
	 */
	@Autowired
	private IPerformanceManageService performanceManageService;
	
	/**
	 * 注入考核管理服务类
	 */
	@Autowired
	private IEvaluateManageService evaluateManageService;
	
	/**
	 * 注入用户管理服务类
	 */
	@Autowired
	private IUserInfoManageService userInfoManageService;
	
	/**
	 * 注入任务信息管理服务类
	 */
	@Autowired
	private ITaskInfoManageService taskInfoManageService;
	
	@Autowired
	private ITaskResponseManageService taskResponseManageService;
	
	/**
	 * 检查用户信息的业务逻辑对象依赖注入（BLL Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkBllInjectionForUserinfo(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查是否有进行BLL业务逻辑对象的依赖注入
			pErrPos = 1;
			if (performanceManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append(
						"绩效管理的BLL业务逻辑对象非法为空，请检查是否有进行依赖注入或赋值。");
			}
			
			if (evaluateManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append(
						"考核信息的BLL业务逻辑对象非法为空，请检查是否有进行依赖注入或赋值。");
			}
			
			if (userInfoManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append(
						"用户信息的BLL业务逻辑对象非法为空，请检查是否有进行依赖注入或赋值。");
			}
			
			if (taskInfoManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append(
						"任务信息的BLL业务逻辑对象非法为空，请检查是否有进行依赖注入或赋值。");
			}
			
			if (taskResponseManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append(
						"任务回复信息的BLL业务逻辑对象非法为空，请检查是否有进行依赖注入或赋值。");
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
	 * 查询所有具有业务指导室角色的人员信息集合
	 * @param userInfos 查找成功后返回的用户信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	public List<UserInfo> findbusinessGuids(HttpServletRequest request) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		List<UserInfo> businessGuids = null;
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForUserinfo(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				//查询所有具有业务指导室角色的人员信息集合
				businessGuids = new ArrayList<UserInfo>();
				if (performanceManageService.findBusinessGuids(businessGuids, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询所有具有业务指导室角色的人员信息集合  DWR执行失败。");
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

		return businessGuids;
	}
	
	/**
	 * 查询考核记录中的最大年度
	 * @return
	 */
	public String findMaxYear(HttpServletRequest request) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		int maxYear = 0;
		EvaluateRegister evaluateRegister = new EvaluateRegister();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForUserinfo(pErrInfo ) == false) {
				pFlag = false;
			}

			//查询已经通过考核的最大年度
			if (pFlag) {
				pErrPos = 2;
				if (evaluateManageService.findMaxYear(evaluateRegister , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询已经通过考核的最大年度 失败：");
				}
			}
			
			//如果考核记录已经存在现在年度的记录 禁用“新开年度”按钮
			if(pFlag) {
				if (StringTool.checkNull(evaluateRegister.getYears())) {
					maxYear = Integer.valueOf(evaluateRegister.getYears());
					
					System.out.println("DWR maxYear="+maxYear);
					
					if(maxYear >= Calendar.getInstance().get(Calendar.YEAR)) {
						maxYear = 0;
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

		return String.valueOf(maxYear);
	}
	
	/**
	 * 判断指定年度是否需要追加人员
	 * @param currentYear 指定年度
	 * @return 返回考核记录缺少的人数,不需要追加返回0
	 */
	public int isAppendEvaluate(int currentYear,HttpServletRequest request) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		Integer[] count = new Integer[] {0};
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForUserinfo(pErrInfo ) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				//判断指定年度是否大于现在年度
				if(currentYear >= Calendar.getInstance().get(Calendar.YEAR)) {
					//查询当前年度是否需要追加新进的人员
					if (evaluateManageService.findNeedAppend(""+currentYear , count , pErrInfo) == false) {
						pFlag  = false;
						pErrInfo.getContent().insert(0, "DWR 查询当前年度是否需要追加新进的人员 失败：");
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
		
		System.out.println(pErrInfo.toString());
		return count[0];
	}
	
	/**
	 * 追加特定年度人员考核记录
	 * @param year 追加年度
	 */
	public void appendEvaluate(String year,HttpServletRequest request) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForUserinfo(pErrInfo ) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				//追加特定年度的考核记录
				if (evaluateManageService.insertAppendByYear(year, pErrInfo) == false) {
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
	}
	
	/**
	 * 插入特定年度的考核记录以及明细
	 * @param year 年度
	 */
	public void insertEvaluateByYear(String year,HttpServletRequest request) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForUserinfo(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				//插入特定年度的考核记录以及明细
				if (evaluateManageService.insertByYear(year, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "DWR 插入特定年度的考核记录以及明细 失败：");
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
			
			System.out.println(pErrInfo.toString());
		}
	}
	
	/**
	 * 获取特定年度的考核记录数
	 * @param year 考核年度
	 * @return
	 */
	public int findCountByYear(String year,HttpServletRequest request) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		EvaluateRegisterVO evaluateRegisterVO = new EvaluateRegisterVO();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForUserinfo(pErrInfo ) == false) {
				pFlag = false;
			}
			
			if (pFlag) {
				pErrPos = 2;
				//查询特定年度的考核记录数
				if (evaluateManageService.findCountByYear(year, evaluateRegisterVO, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "DWR查询特定年度的考核记录数 失败：");
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
		return evaluateRegisterVO.getEvaluateCount();
	}
	
	/**
	 * 批量删除选中考核记录
	 * @return
	 */
	public void deleteBatchEvaluate(List<Integer> evaluateIds,HttpServletRequest request) {
		System.out.println("PerformanceManageDWR	deleteBatchEvaluate");
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForUserinfo(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				if(evaluateIds!=null && evaluateIds.size()>=1) {
					//批量删除指定的考核登记信息
					if (evaluateManageService.deleteBatch(evaluateIds, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "Action 批量删除指定的考核登记信息 失败：");
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
	}
	
	/**
	 * 查询任务接收人集合
	 * @return List<UserInfo>
	 */
	public List<UserInfo> findTaskPersons(HttpServletRequest request) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		List<UserInfo> userInfos = null;
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForUserinfo(pErrInfo ) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				userInfos = new ArrayList<UserInfo>();
				if (userInfoManageService.findTaskPersons(userInfos , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "DWR查询任务接收人集合 失败：");
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
		return userInfos;
	}
	
	/**
	 * 批量删除任务信息
	 * @param taskInfoIds 任务信息ID集合
	 */
	public void deleteBatchTaskInfo(List<Integer> taskInfoIds,HttpServletRequest request) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForUserinfo(pErrInfo ) == false) {
				pFlag = false;
			}

			//判断任务id集合是否为空
			if (pFlag) {
				if (taskInfoIds==null || taskInfoIds.size()<=0) {
					pFlag = false;
					pErrInfo.getContent().append("任务id集合非法为空。");
				}
			}
			
			System.out.println("deleteBatchTaskInfo");
			for(Integer i : taskInfoIds) {
				System.out.println(i);
			}
			
			//批量删除指定的任务信息
			if (pFlag) {
				pErrPos = 2;
				if (taskInfoManageService.deleteBatch(taskInfoIds, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "DWR批量删除指定的任务信息 失败：");
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
	}
	
	/**
	 * 根据任务id查找任务接收人信息
	 * @param taskInfoId 任务id
	 */
	public List<TaskPerson> findTaskPersonByTaskInfoId(int taskInfoId,HttpServletRequest request) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		List<TaskPerson> taskPersons = null;
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForUserinfo(pErrInfo ) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				taskPersons = new ArrayList<TaskPerson>();
				if (taskInfoManageService.findTaskPersonByID(taskInfoId, taskPersons, pErrInfo)) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "DWR根据任务id查找任务接收人信息 失败：");
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
		return taskPersons;
	}
	
	/**
	 *  保存任务回复信息
	 * @param taskID			任务id
	 * @param responseContent	任务回复内容
	 * @param responseUserID	任务回复人id
	 */
	public boolean saveTaskResponse(int taskID, String responseContent, int responseUserID,HttpServletRequest request) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForUserinfo(pErrInfo )== false) {
				pFlag = false;
			}
			
			//检查任务回复信息的任务id
			if (pFlag) {
				if (taskID<=0) {
					pFlag = false;
					pErrInfo.getContent().append("任务id非法。");
				}
			}
			
			//检查任务回复人id
			if (pFlag) {
				if (responseUserID<=0) {
					pFlag = false;
					pErrInfo.getContent().append("任务回复人id非法。");
				}
			}
			
			//检查 任务回复信息的回复内容
			if (pFlag) {
				if (StringTool.checkNull(responseContent) == false) {
					pFlag = false;
					pErrInfo.getContent().append("任务回复内容非法为空。");
				}
			}

			if (pFlag) {
				pErrPos = 2;
				//添加一个新的任务回复信息
				TaskResponse taskResponse = new TaskResponse(taskID, new Date(), responseUserID, responseContent);
				
				if (taskResponseManageService.saveTaskResponse(taskResponse, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "DWR添加一个新的任务回复信息 失败：");
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			e.printStackTrace();
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