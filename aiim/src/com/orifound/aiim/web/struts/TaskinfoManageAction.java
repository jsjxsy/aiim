package com.orifound.aiim.web.struts;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.orifound.aiim.bll.service.ITaskInfoManageService;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.TaskInfo;
import com.orifound.aiim.entity.UserInfo;
import com.orifound.aiim.web.util.StringTool;

/**
 * 任务管理Action
 * @author tyb
 *
 */
public class TaskinfoManageAction extends ActionSupport {

	private static final long serialVersionUID = -3875151448620857894L;
	
	/**
	 * 注入任务信息管理服务对象
	 */
	@Autowired
	private ITaskInfoManageService taskInfoManageService;
	
	private DataPageInfo dataPageInfo = new DataPageInfo();// 分页实体BEAN
	
	/**
	 * 返回的结果URL
	 */
	private String resultURL;
	
	/**
	 * 任务发布人
	 */
	private String fromUserName;
	
	/**
	 * 接收人
	 */
	private String receiveName;
	
	/**
	 * 发布日期开始
	 */
	private String beginTime;
	
	/**
	 * 发布日期结束
	 */
	private String endTime;
	
	/**
	 * 任务主题
	 */
	private String title;
	
	/**
	 * 是否发布任务标志:y 发布、n 不发布
	 */
	private String publishFlag;
	
	/**
	 * 接收人id集合(使用";"分隔)
	 */
	private String receiveIds;
	
	/**
	 * 修改前的接收人id集合(使用";"分隔)
	 */
	private String oldReceiveIds;
	
	/**
	 * 任务内容(可以为空)
	 */
	private String content;
	
	/**
	 * 需要编辑的任务id
	 */
	private int editTaskInfoId;
	
	/**
	 * 搜索关键词
	 */
	private List<String> titleKeys = new ArrayList<String>();
	
	private Map<String, String> titleKeyMap = new LinkedHashMap<String, String>();

	public String show() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForTaskInfo(pErrInfo ) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				HttpServletRequest request = ServletActionContext.getRequest();
				
				/*包装 页面查询参数*/
				Map<String, String> params = new LinkedHashMap<String, String>();
				//任务发布人 判断是否存在
				if (StringTool.checkNull(fromUserName)) {
					params.put("fromUserName", fromUserName);
				}
				//接收人
				if (StringTool.checkNull(receiveName)) {
					params.put("receiveName", receiveName);
				}
				
				//是否发布 默认显示已发布信息
				if (StringTool.checkNull(publishFlag) == false) {
					publishFlag = "y";
				}
				params.put("publishFlag", publishFlag);
				
				titleKeyMap.put("title", "主题");
				titleKeyMap.put("content", "内容");
				
				request.setAttribute("keySize", titleKeyMap.keySet().size());
				
				if(StringTool.checkNull(content)) {
					for(String titleKey : titleKeys) {
						params.put(titleKey, content);
					}
					//默认根据主题搜索
					if(titleKeys.size() <=0) {
						params.put("title", content);
					}
				}
				
//				dataPageInfo.setPageSize(SystemInitializer.getInstance().getPageViewRecordsCount());
				dataPageInfo.setPageSize(2);
				List<TaskInfo> taskInfos = new ArrayList<TaskInfo>();
				
				//调用Service 实现分页查询
				if (taskInfoManageService.findWithPage(params, dataPageInfo, taskInfos , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action执行分页查询任务信息 失败：");
				}
				
				//保存信息到request对象
				if (taskInfos.size() >= 1) {
					request.setAttribute("taskInfos", taskInfos);
					request.setAttribute("publishOwn", "y");
					request.setAttribute("receiveOwn", "y");
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
		
		System.out.println(pErrInfo.toString());
		
		resultURL = "/JXGL/taskInfo_list.jsp";
		return SUCCESS;
	}
	
	/**
	 * 保存任务信息
	 * @return
	 */
	public String save() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForTaskInfo(pErrInfo ) == false) {
				pFlag = false;
			}

			if (pFlag) {
				HttpServletRequest request = ServletActionContext.getRequest();
				pErrPos = 2;
				TaskInfo taskinfo = new TaskInfo();
				UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
				
				//检查会话中是否存在用户信息
				if (pFlag) {
					if(userInfo == null) {
						pFlag = false;
						pErrInfo.getContent().append("会话中用户信息非法为空。");
					}
				}
				
				if (pFlag) {
					if (StringTool.checkNull(publishFlag) == false) {
						pFlag = false;
						pErrInfo.getContent().append("是否发布标志非法为空。");
					} else {
						if (publishFlag.equals("y") && StringTool.checkNull(receiveIds) == false) {
							pFlag = false;
							pErrInfo.getContent().append("任务接收人非法为空。");
						}
					}
				}
				
				//添加一个新的任务信息
				if (pFlag) {
					taskinfo.setFromUserID(userInfo.getUserID());
					taskinfo.setFromDutyID(userInfo.getDutyID());
					taskinfo.setContent(content);
					taskinfo.setTitle(title);
					taskinfo.setLastModifyTime(new Date());
					taskinfo.setPublishFlag(false);
					if("y".equals(publishFlag)) {
						taskinfo.setPublishTime(new Date());
						//设置是否发布任务
						taskinfo.setPublishFlag(true);
						//设置任务接收人id集合 
						String[] receivers = receiveIds.split(";");
						List<Integer> taskPersonIds = new ArrayList<Integer>();
						for(String id : receivers) {
							taskPersonIds.add(Integer.valueOf(id));
						}
						taskinfo.setTaskPersonIds(taskPersonIds);
					}
					
					if (taskInfoManageService.saveTaskInfo(taskinfo, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "Action添加一个新的任务信息 失败：");
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
		resultURL = "/JXGL/taskInfo_list.jsp";
		return SUCCESS;
	}
	
	/**
	 * 任务更新界面
	 * @return
	 */
	public String updateUI() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForTaskInfo(pErrInfo ) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				TaskInfo taskinfo = new TaskInfo();
				//根据唯一标识查找任务信息
				if (taskInfoManageService.findTaskInfoByID(editTaskInfoId, taskinfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action根据唯一标识查找任务信息 失败");
				}
				
				if (pFlag) {
					if (taskinfo != null) {
						//保存旧的接受人id集合
						oldReceiveIds = taskinfo.getReceiveIds();
						if(taskinfo.getPublishFlag()) {
							publishFlag = "y";
						} else {
							publishFlag = "n";
						}
						ServletActionContext.getRequest().setAttribute("taskinfo", taskinfo);
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

		String show = request.getParameter("show");
		
		System.out.println("show="+show);
		
		if(StringTool.checkNull(show) && "show".equals(show)) {
			resultURL = "/JXGL/taskInfo_show.jsp";
		} else {
			resultURL = "/JXGL/taskInfo_update.jsp";
		}
		
		return SUCCESS;
	}
	/**
	 * 更新任务信息
	 * @return
	 */
	public String update() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		try {
			//开始处理 1...
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForTaskInfo(pErrInfo ) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				
				//需要删除的任务接收人id
				List<Integer> delTaskPersonIds = new ArrayList<Integer>();
				//需要新增的任务接收人id
				List<Integer> insertTaskPersonIds = new ArrayList<Integer>();
				
				//判断是否已经有旧的接收人
				String[] oldReceivers = new String[0];
				if (StringTool.checkNull(oldReceiveIds)) {
					oldReceivers = oldReceiveIds.split(";");
					//默认设置删除所有旧的接收人
					for(String oldReceiver : oldReceivers) {
						if(StringTool.checkNull(oldReceiver)) {
							delTaskPersonIds.add(Integer.valueOf(oldReceiver));
						}
					}
				}
				
				//判断是否已经有新的接收人
				String[] newReceivers = new String[0];
				if (StringTool.checkNull(receiveIds)) {
					newReceivers = receiveIds.split(";");
					boolean exist = false;
					for(String newReceiver : newReceivers) {
						exist = false;
						for(String oldReceiver : oldReceivers) {
							if(newReceiver.equals(oldReceiver)) {
								exist = true;
								break;
							}
						}
						if (!exist) {
							//新的接收人不存在旧的接受人中 插入数据库
							insertTaskPersonIds.add(Integer.valueOf(newReceiver));
						} else {
							//新的接收人存在旧的接受人中 不进行删除
							delTaskPersonIds.remove(Integer.valueOf(newReceiver));
						}
					}
				}
				
				System.out.println("insertTaskPersonIds");
				for(Integer i: insertTaskPersonIds) {
					System.out.println(i);
				}
				
				System.out.println("delTaskPersonIds");
				for(Integer i: delTaskPersonIds) {
					System.out.println(i);
				}
				
				HttpServletRequest request = ServletActionContext.getRequest();
				UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
				
				//检查会话中是否存在用户信息
				if (pFlag) {
					if(userInfo == null) {
						pFlag = false;
						pErrInfo.getContent().append("会话中用户信息非法为空。");
					}
				}
				
				if (pFlag) {
					TaskInfo taskinfo = new TaskInfo();
					taskinfo.setID(editTaskInfoId);
					taskinfo.setFromUserID(userInfo.getUserID());
					taskinfo.setFromDutyID(userInfo.getDutyID());
					taskinfo.setContent(content);
					taskinfo.setTitle(title);
					taskinfo.setLastModifyTime(new Date());
					
					System.out.println("publishFlag="+publishFlag);
					
					if(publishFlag.equals("y")) {
						taskinfo.setPublishTime(new Date());
						//设置是否发布任务
						taskinfo.setPublishFlag(true);
					} else {
						taskinfo.setPublishTime(null);
						taskinfo.setPublishFlag(false);
					}
					taskinfo.setTaskPersonIds(insertTaskPersonIds);
					taskinfo.setDelTaskPersonIds(delTaskPersonIds);
					
					if (taskInfoManageService.updateTaskInfo(taskinfo, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "Action修改指定的任务信息 失败：");
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

		resultURL = "/JXGL/taskInfo_list.jsp";
		return SUCCESS;
	}
	
	/**
	 * 任务详细信息
	 * @return String
	 */
	public String detail() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForTaskInfo(pErrInfo ) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				TaskInfo taskInfo = new TaskInfo();
				//根据唯一标识查找工作任务详细信息
				if (taskInfoManageService.findDetailByID(editTaskInfoId, taskInfo , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Service根据唯一标识查找工作任务详细信息 失败：");
				}
				HttpServletRequest request = ServletActionContext.getRequest();
				request.setAttribute("taskInfo", taskInfo);
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
		resultURL = "/JXGL/taskInfo_detail.jsp";
		return SUCCESS;
	}
	
	/**
	 * 我发布的任务
	 * @return
	 */
	public String publishTask() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForTaskInfo(pErrInfo ) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				HttpServletRequest request = ServletActionContext.getRequest();
				
				/*包装 页面查询参数*/
				Map<String, String> params = new LinkedHashMap<String, String>();
				
				UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
				if(userInfo == null) {
					pFlag = false;
					pErrInfo.getContent().append("会话中用户信息为空。");
				} else {
					params.put("fromUserName", userInfo.getRealName());
				}
				
				titleKeyMap.put("title", "主题");
				titleKeyMap.put("content", "内容");
				
				request.setAttribute("keySize", titleKeyMap.keySet().size());
				
				if(StringTool.checkNull(content)) {
					for(String titleKey : titleKeys) {
						params.put(titleKey, content);
					}
					//默认根据主题搜索
					if(titleKeys.size() <=0) {
						params.put("title", content);
					}
				}
				
				//接收人
				if (StringTool.checkNull(receiveName)) {
					params.put("receiveName", receiveName);
				}
				
//				dataPageInfo.setPageSize(SystemInitializer.getInstance().getPageViewRecordsCount());
				dataPageInfo.setPageSize(2);
				
				List<TaskInfo> taskInfos = new ArrayList<TaskInfo>();
				
				//调用Service 实现分页查询
				if (taskInfoManageService.findWithPage(params, dataPageInfo, taskInfos , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action执行分页查询任务信息 失败：");
				}
				
				//保存信息到request对象
				if (taskInfos.size() >= 1) {
					request.setAttribute("taskInfos", taskInfos);
					request.setAttribute("publishOwn", "y");
					request.setAttribute("receiveOwn", "n");
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
		resultURL = "/JXGL/taskinfoPublish_list.jsp";
		return SUCCESS;
	}
	
	/**
	 * 我负责的任务
	 * @return
	 */
	public String receiveTask() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForTaskInfo(pErrInfo ) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				HttpServletRequest request = ServletActionContext.getRequest();
				
				/*包装 页面查询参数*/
				Map<String, String> params = new LinkedHashMap<String, String>();
				
				//任务发布人 判断是否存在
				if (StringTool.checkNull(fromUserName)) {
					params.put("fromUserName", fromUserName);
				}
				
				UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
				if(userInfo == null) {
					pFlag = false;
					pErrInfo.getContent().append("会话中用户信息为空。");
				} else {
					params.put("receiveName", userInfo.getRealName());
				}
				
				titleKeyMap.put("title", "主题");
				titleKeyMap.put("content", "内容");
				
				request.setAttribute("keySize", titleKeyMap.keySet().size());
				
				if(StringTool.checkNull(content)) {
					for(String titleKey : titleKeys) {
						params.put(titleKey, content);
					}
					//默认根据主题搜索
					if(titleKeys.size() <=0) {
						params.put("title", content);
					}
				}
				
//				dataPageInfo.setPageSize(SystemInitializer.getInstance().getPageViewRecordsCount());
				dataPageInfo.setPageSize(2);
				
				List<TaskInfo> taskInfos = new ArrayList<TaskInfo>();
				
				//调用Service 实现分页查询
				if (taskInfoManageService.findWithPage(params, dataPageInfo, taskInfos , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action执行分页查询任务信息 失败：");
				}
				
				//保存信息到request对象
				if (taskInfos.size() >= 1) {
					request.setAttribute("taskInfos", taskInfos);
					request.setAttribute("publishOwn", "n");
					request.setAttribute("receiveOwn", "y");
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
		resultURL = "/JXGL/taskinfoReceive_list.jsp";
		return SUCCESS;
	}
	
	/**
	 * 检查任务信息的业务逻辑对象依赖注入（BLL Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkBllInjectionForTaskInfo(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查是否有进行BLL业务逻辑对象的依赖注入
			pErrPos = 1;
			if (taskInfoManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append("任务信息的BLL业务逻辑对象非法为空，请检查是否有进行依赖注入或赋值。");
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

	public String getResultURL() {
		return resultURL;
	}

	public void setResultURL(String resultURL) {
		this.resultURL = resultURL;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getReceiveName() {
		return receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public DataPageInfo getDataPageInfo() {
		return dataPageInfo;
	}

	public void setDataPageInfo(DataPageInfo dataPageInfo) {
		this.dataPageInfo = dataPageInfo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getReceiveIds() {
		return receiveIds;
	}

	public void setReceiveIds(String receiveIds) {
		this.receiveIds = receiveIds;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPublishFlag() {
		return publishFlag;
	}

	public void setPublishFlag(String publishFlag) {
		this.publishFlag = publishFlag;
	}

	public int getEditTaskInfoId() {
		return editTaskInfoId;
	}

	public void setEditTaskInfoId(int editTaskInfoId) {
		this.editTaskInfoId = editTaskInfoId;
	}

	public String getOldReceiveIds() {
		return oldReceiveIds;
	}

	public void setOldReceiveIds(String oldReceiveIds) {
		this.oldReceiveIds = oldReceiveIds;
	}

	public List<String> getTitleKeys() {
		return titleKeys;
	}

	public void setTitleKeys(List<String> titleKeys) {
		this.titleKeys = titleKeys;
	}

	public Map<String, String> getTitleKeyMap() {
		return titleKeyMap;
	}

	public void setTitleKeyMap(Map<String, String> titleKeyMap) {
		this.titleKeyMap = titleKeyMap;
	}

}