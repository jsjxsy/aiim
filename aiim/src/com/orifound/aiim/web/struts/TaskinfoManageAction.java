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
 * �������Action
 * @author tyb
 *
 */
public class TaskinfoManageAction extends ActionSupport {

	private static final long serialVersionUID = -3875151448620857894L;
	
	/**
	 * ע��������Ϣ����������
	 */
	@Autowired
	private ITaskInfoManageService taskInfoManageService;
	
	private DataPageInfo dataPageInfo = new DataPageInfo();// ��ҳʵ��BEAN
	
	/**
	 * ���صĽ��URL
	 */
	private String resultURL;
	
	/**
	 * ���񷢲���
	 */
	private String fromUserName;
	
	/**
	 * ������
	 */
	private String receiveName;
	
	/**
	 * �������ڿ�ʼ
	 */
	private String beginTime;
	
	/**
	 * �������ڽ���
	 */
	private String endTime;
	
	/**
	 * ��������
	 */
	private String title;
	
	/**
	 * �Ƿ񷢲������־:y ������n ������
	 */
	private String publishFlag;
	
	/**
	 * ������id����(ʹ��";"�ָ�)
	 */
	private String receiveIds;
	
	/**
	 * �޸�ǰ�Ľ�����id����(ʹ��";"�ָ�)
	 */
	private String oldReceiveIds;
	
	/**
	 * ��������(����Ϊ��)
	 */
	private String content;
	
	/**
	 * ��Ҫ�༭������id
	 */
	private int editTaskInfoId;
	
	/**
	 * �����ؼ���
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
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForTaskInfo(pErrInfo ) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				HttpServletRequest request = ServletActionContext.getRequest();
				
				/*��װ ҳ���ѯ����*/
				Map<String, String> params = new LinkedHashMap<String, String>();
				//���񷢲��� �ж��Ƿ����
				if (StringTool.checkNull(fromUserName)) {
					params.put("fromUserName", fromUserName);
				}
				//������
				if (StringTool.checkNull(receiveName)) {
					params.put("receiveName", receiveName);
				}
				
				//�Ƿ񷢲� Ĭ����ʾ�ѷ�����Ϣ
				if (StringTool.checkNull(publishFlag) == false) {
					publishFlag = "y";
				}
				params.put("publishFlag", publishFlag);
				
				titleKeyMap.put("title", "����");
				titleKeyMap.put("content", "����");
				
				request.setAttribute("keySize", titleKeyMap.keySet().size());
				
				if(StringTool.checkNull(content)) {
					for(String titleKey : titleKeys) {
						params.put(titleKey, content);
					}
					//Ĭ�ϸ�����������
					if(titleKeys.size() <=0) {
						params.put("title", content);
					}
				}
				
//				dataPageInfo.setPageSize(SystemInitializer.getInstance().getPageViewRecordsCount());
				dataPageInfo.setPageSize(2);
				List<TaskInfo> taskInfos = new ArrayList<TaskInfo>();
				
				//����Service ʵ�ַ�ҳ��ѯ
				if (taskInfoManageService.findWithPage(params, dataPageInfo, taskInfos , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Actionִ�з�ҳ��ѯ������Ϣ ʧ�ܣ�");
				}
				
				//������Ϣ��request����
				if (taskInfos.size() >= 1) {
					request.setAttribute("taskInfos", taskInfos);
					request.setAttribute("publishOwn", "y");
					request.setAttribute("receiveOwn", "y");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			e.printStackTrace();
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}
		
		System.out.println(pErrInfo.toString());
		
		resultURL = "/JXGL/taskInfo_list.jsp";
		return SUCCESS;
	}
	
	/**
	 * ����������Ϣ
	 * @return
	 */
	public String save() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForTaskInfo(pErrInfo ) == false) {
				pFlag = false;
			}

			if (pFlag) {
				HttpServletRequest request = ServletActionContext.getRequest();
				pErrPos = 2;
				TaskInfo taskinfo = new TaskInfo();
				UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
				
				//���Ự���Ƿ�����û���Ϣ
				if (pFlag) {
					if(userInfo == null) {
						pFlag = false;
						pErrInfo.getContent().append("�Ự���û���Ϣ�Ƿ�Ϊ�ա�");
					}
				}
				
				if (pFlag) {
					if (StringTool.checkNull(publishFlag) == false) {
						pFlag = false;
						pErrInfo.getContent().append("�Ƿ񷢲���־�Ƿ�Ϊ�ա�");
					} else {
						if (publishFlag.equals("y") && StringTool.checkNull(receiveIds) == false) {
							pFlag = false;
							pErrInfo.getContent().append("��������˷Ƿ�Ϊ�ա�");
						}
					}
				}
				
				//���һ���µ�������Ϣ
				if (pFlag) {
					taskinfo.setFromUserID(userInfo.getUserID());
					taskinfo.setFromDutyID(userInfo.getDutyID());
					taskinfo.setContent(content);
					taskinfo.setTitle(title);
					taskinfo.setLastModifyTime(new Date());
					taskinfo.setPublishFlag(false);
					if("y".equals(publishFlag)) {
						taskinfo.setPublishTime(new Date());
						//�����Ƿ񷢲�����
						taskinfo.setPublishFlag(true);
						//�������������id���� 
						String[] receivers = receiveIds.split(";");
						List<Integer> taskPersonIds = new ArrayList<Integer>();
						for(String id : receivers) {
							taskPersonIds.add(Integer.valueOf(id));
						}
						taskinfo.setTaskPersonIds(taskPersonIds);
					}
					
					if (taskInfoManageService.saveTaskInfo(taskinfo, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "Action���һ���µ�������Ϣ ʧ�ܣ�");
					}
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}
		resultURL = "/JXGL/taskInfo_list.jsp";
		return SUCCESS;
	}
	
	/**
	 * ������½���
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
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForTaskInfo(pErrInfo ) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				TaskInfo taskinfo = new TaskInfo();
				//����Ψһ��ʶ����������Ϣ
				if (taskInfoManageService.findTaskInfoByID(editTaskInfoId, taskinfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action����Ψһ��ʶ����������Ϣ ʧ��");
				}
				
				if (pFlag) {
					if (taskinfo != null) {
						//����ɵĽ�����id����
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
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//���پֲ�����
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
	 * ����������Ϣ
	 * @return
	 */
	public String update() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		try {
			//��ʼ���� 1...
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForTaskInfo(pErrInfo ) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				
				//��Ҫɾ�������������id
				List<Integer> delTaskPersonIds = new ArrayList<Integer>();
				//��Ҫ���������������id
				List<Integer> insertTaskPersonIds = new ArrayList<Integer>();
				
				//�ж��Ƿ��Ѿ��оɵĽ�����
				String[] oldReceivers = new String[0];
				if (StringTool.checkNull(oldReceiveIds)) {
					oldReceivers = oldReceiveIds.split(";");
					//Ĭ������ɾ�����оɵĽ�����
					for(String oldReceiver : oldReceivers) {
						if(StringTool.checkNull(oldReceiver)) {
							delTaskPersonIds.add(Integer.valueOf(oldReceiver));
						}
					}
				}
				
				//�ж��Ƿ��Ѿ����µĽ�����
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
							//�µĽ����˲����ھɵĽ������� �������ݿ�
							insertTaskPersonIds.add(Integer.valueOf(newReceiver));
						} else {
							//�µĽ����˴��ھɵĽ������� ������ɾ��
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
				
				//���Ự���Ƿ�����û���Ϣ
				if (pFlag) {
					if(userInfo == null) {
						pFlag = false;
						pErrInfo.getContent().append("�Ự���û���Ϣ�Ƿ�Ϊ�ա�");
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
						//�����Ƿ񷢲�����
						taskinfo.setPublishFlag(true);
					} else {
						taskinfo.setPublishTime(null);
						taskinfo.setPublishFlag(false);
					}
					taskinfo.setTaskPersonIds(insertTaskPersonIds);
					taskinfo.setDelTaskPersonIds(delTaskPersonIds);
					
					if (taskInfoManageService.updateTaskInfo(taskinfo, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "Action�޸�ָ����������Ϣ ʧ�ܣ�");
					}
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}

		resultURL = "/JXGL/taskInfo_list.jsp";
		return SUCCESS;
	}
	
	/**
	 * ������ϸ��Ϣ
	 * @return String
	 */
	public String detail() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForTaskInfo(pErrInfo ) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				TaskInfo taskInfo = new TaskInfo();
				//����Ψһ��ʶ���ҹ���������ϸ��Ϣ
				if (taskInfoManageService.findDetailByID(editTaskInfoId, taskInfo , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Service����Ψһ��ʶ���ҹ���������ϸ��Ϣ ʧ�ܣ�");
				}
				HttpServletRequest request = ServletActionContext.getRequest();
				request.setAttribute("taskInfo", taskInfo);
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}
		resultURL = "/JXGL/taskInfo_detail.jsp";
		return SUCCESS;
	}
	
	/**
	 * �ҷ���������
	 * @return
	 */
	public String publishTask() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForTaskInfo(pErrInfo ) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				HttpServletRequest request = ServletActionContext.getRequest();
				
				/*��װ ҳ���ѯ����*/
				Map<String, String> params = new LinkedHashMap<String, String>();
				
				UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
				if(userInfo == null) {
					pFlag = false;
					pErrInfo.getContent().append("�Ự���û���ϢΪ�ա�");
				} else {
					params.put("fromUserName", userInfo.getRealName());
				}
				
				titleKeyMap.put("title", "����");
				titleKeyMap.put("content", "����");
				
				request.setAttribute("keySize", titleKeyMap.keySet().size());
				
				if(StringTool.checkNull(content)) {
					for(String titleKey : titleKeys) {
						params.put(titleKey, content);
					}
					//Ĭ�ϸ�����������
					if(titleKeys.size() <=0) {
						params.put("title", content);
					}
				}
				
				//������
				if (StringTool.checkNull(receiveName)) {
					params.put("receiveName", receiveName);
				}
				
//				dataPageInfo.setPageSize(SystemInitializer.getInstance().getPageViewRecordsCount());
				dataPageInfo.setPageSize(2);
				
				List<TaskInfo> taskInfos = new ArrayList<TaskInfo>();
				
				//����Service ʵ�ַ�ҳ��ѯ
				if (taskInfoManageService.findWithPage(params, dataPageInfo, taskInfos , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Actionִ�з�ҳ��ѯ������Ϣ ʧ�ܣ�");
				}
				
				//������Ϣ��request����
				if (taskInfos.size() >= 1) {
					request.setAttribute("taskInfos", taskInfos);
					request.setAttribute("publishOwn", "y");
					request.setAttribute("receiveOwn", "n");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}
		resultURL = "/JXGL/taskinfoPublish_list.jsp";
		return SUCCESS;
	}
	
	/**
	 * �Ҹ��������
	 * @return
	 */
	public String receiveTask() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForTaskInfo(pErrInfo ) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				HttpServletRequest request = ServletActionContext.getRequest();
				
				/*��װ ҳ���ѯ����*/
				Map<String, String> params = new LinkedHashMap<String, String>();
				
				//���񷢲��� �ж��Ƿ����
				if (StringTool.checkNull(fromUserName)) {
					params.put("fromUserName", fromUserName);
				}
				
				UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
				if(userInfo == null) {
					pFlag = false;
					pErrInfo.getContent().append("�Ự���û���ϢΪ�ա�");
				} else {
					params.put("receiveName", userInfo.getRealName());
				}
				
				titleKeyMap.put("title", "����");
				titleKeyMap.put("content", "����");
				
				request.setAttribute("keySize", titleKeyMap.keySet().size());
				
				if(StringTool.checkNull(content)) {
					for(String titleKey : titleKeys) {
						params.put(titleKey, content);
					}
					//Ĭ�ϸ�����������
					if(titleKeys.size() <=0) {
						params.put("title", content);
					}
				}
				
//				dataPageInfo.setPageSize(SystemInitializer.getInstance().getPageViewRecordsCount());
				dataPageInfo.setPageSize(2);
				
				List<TaskInfo> taskInfos = new ArrayList<TaskInfo>();
				
				//����Service ʵ�ַ�ҳ��ѯ
				if (taskInfoManageService.findWithPage(params, dataPageInfo, taskInfos , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Actionִ�з�ҳ��ѯ������Ϣ ʧ�ܣ�");
				}
				
				//������Ϣ��request����
				if (taskInfos.size() >= 1) {
					request.setAttribute("taskInfos", taskInfos);
					request.setAttribute("publishOwn", "n");
					request.setAttribute("receiveOwn", "y");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}
		resultURL = "/JXGL/taskinfoReceive_list.jsp";
		return SUCCESS;
	}
	
	/**
	 * ���������Ϣ��ҵ���߼���������ע�루BLL Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkBllInjectionForTaskInfo(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//����Ƿ��н���BLLҵ���߼����������ע��
			pErrPos = 1;
			if (taskInfoManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append("������Ϣ��BLLҵ���߼�����Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
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