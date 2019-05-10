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
 * ��Ч����DWR
 * @author tyb
 *
 */
public class PerformanceManageDWR {
	
	/**
	 * ע�뼨Ч��Ϣ������
	 */
	@Autowired
	private IPerformanceManageService performanceManageService;
	
	/**
	 * ע�뿼�˹��������
	 */
	@Autowired
	private IEvaluateManageService evaluateManageService;
	
	/**
	 * ע���û����������
	 */
	@Autowired
	private IUserInfoManageService userInfoManageService;
	
	/**
	 * ע��������Ϣ���������
	 */
	@Autowired
	private ITaskInfoManageService taskInfoManageService;
	
	@Autowired
	private ITaskResponseManageService taskResponseManageService;
	
	/**
	 * ����û���Ϣ��ҵ���߼���������ע�루BLL Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkBllInjectionForUserinfo(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//����Ƿ��н���BLLҵ���߼����������ע��
			pErrPos = 1;
			if (performanceManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append(
						"��Ч�����BLLҵ���߼�����Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
			
			if (evaluateManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append(
						"������Ϣ��BLLҵ���߼�����Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
			
			if (userInfoManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append(
						"�û���Ϣ��BLLҵ���߼�����Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
			
			if (taskInfoManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append(
						"������Ϣ��BLLҵ���߼�����Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
			
			if (taskResponseManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append(
						"����ظ���Ϣ��BLLҵ���߼�����Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
	
	/**
	 * ��ѯ���о���ҵ��ָ���ҽ�ɫ����Ա��Ϣ����
	 * @param userInfos ���ҳɹ��󷵻ص��û���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public List<UserInfo> findbusinessGuids(HttpServletRequest request) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		List<UserInfo> businessGuids = null;
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForUserinfo(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				//��ѯ���о���ҵ��ָ���ҽ�ɫ����Ա��Ϣ����
				businessGuids = new ArrayList<UserInfo>();
				if (performanceManageService.findBusinessGuids(businessGuids, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ���о���ҵ��ָ���ҽ�ɫ����Ա��Ϣ����  DWRִ��ʧ�ܡ�");
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

		return businessGuids;
	}
	
	/**
	 * ��ѯ���˼�¼�е�������
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
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForUserinfo(pErrInfo ) == false) {
				pFlag = false;
			}

			//��ѯ�Ѿ�ͨ�����˵�������
			if (pFlag) {
				pErrPos = 2;
				if (evaluateManageService.findMaxYear(evaluateRegister , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ�Ѿ�ͨ�����˵������� ʧ�ܣ�");
				}
			}
			
			//������˼�¼�Ѿ�����������ȵļ�¼ ���á��¿���ȡ���ť
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

		return String.valueOf(maxYear);
	}
	
	/**
	 * �ж�ָ������Ƿ���Ҫ׷����Ա
	 * @param currentYear ָ�����
	 * @return ���ؿ��˼�¼ȱ�ٵ�����,����Ҫ׷�ӷ���0
	 */
	public int isAppendEvaluate(int currentYear,HttpServletRequest request) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		Integer[] count = new Integer[] {0};
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForUserinfo(pErrInfo ) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				//�ж�ָ������Ƿ�����������
				if(currentYear >= Calendar.getInstance().get(Calendar.YEAR)) {
					//��ѯ��ǰ����Ƿ���Ҫ׷���½�����Ա
					if (evaluateManageService.findNeedAppend(""+currentYear , count , pErrInfo) == false) {
						pFlag  = false;
						pErrInfo.getContent().insert(0, "DWR ��ѯ��ǰ����Ƿ���Ҫ׷���½�����Ա ʧ�ܣ�");
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
		
		System.out.println(pErrInfo.toString());
		return count[0];
	}
	
	/**
	 * ׷���ض������Ա���˼�¼
	 * @param year ׷�����
	 */
	public void appendEvaluate(String year,HttpServletRequest request) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForUserinfo(pErrInfo ) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				//׷���ض���ȵĿ��˼�¼
				if (evaluateManageService.insertAppendByYear(year, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "׷���ض���ȵĿ��˼�¼ ʧ�ܣ�");
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
	}
	
	/**
	 * �����ض���ȵĿ��˼�¼�Լ���ϸ
	 * @param year ���
	 */
	public void insertEvaluateByYear(String year,HttpServletRequest request) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForUserinfo(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				//�����ض���ȵĿ��˼�¼�Լ���ϸ
				if (evaluateManageService.insertByYear(year, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "DWR �����ض���ȵĿ��˼�¼�Լ���ϸ ʧ�ܣ�");
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
			
			System.out.println(pErrInfo.toString());
		}
	}
	
	/**
	 * ��ȡ�ض���ȵĿ��˼�¼��
	 * @param year �������
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
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForUserinfo(pErrInfo ) == false) {
				pFlag = false;
			}
			
			if (pFlag) {
				pErrPos = 2;
				//��ѯ�ض���ȵĿ��˼�¼��
				if (evaluateManageService.findCountByYear(year, evaluateRegisterVO, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "DWR��ѯ�ض���ȵĿ��˼�¼�� ʧ�ܣ�");
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
		System.out.println(pErrInfo.toString());
		return evaluateRegisterVO.getEvaluateCount();
	}
	
	/**
	 * ����ɾ��ѡ�п��˼�¼
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
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForUserinfo(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				if(evaluateIds!=null && evaluateIds.size()>=1) {
					//����ɾ��ָ���Ŀ��˵Ǽ���Ϣ
					if (evaluateManageService.deleteBatch(evaluateIds, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "Action ����ɾ��ָ���Ŀ��˵Ǽ���Ϣ ʧ�ܣ�");
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
	}
	
	/**
	 * ��ѯ��������˼���
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
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForUserinfo(pErrInfo ) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				userInfos = new ArrayList<UserInfo>();
				if (userInfoManageService.findTaskPersons(userInfos , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "DWR��ѯ��������˼��� ʧ�ܣ�");
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
		System.out.println(pErrInfo.toString());
		return userInfos;
	}
	
	/**
	 * ����ɾ��������Ϣ
	 * @param taskInfoIds ������ϢID����
	 */
	public void deleteBatchTaskInfo(List<Integer> taskInfoIds,HttpServletRequest request) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForUserinfo(pErrInfo ) == false) {
				pFlag = false;
			}

			//�ж�����id�����Ƿ�Ϊ��
			if (pFlag) {
				if (taskInfoIds==null || taskInfoIds.size()<=0) {
					pFlag = false;
					pErrInfo.getContent().append("����id���ϷǷ�Ϊ�ա�");
				}
			}
			
			System.out.println("deleteBatchTaskInfo");
			for(Integer i : taskInfoIds) {
				System.out.println(i);
			}
			
			//����ɾ��ָ����������Ϣ
			if (pFlag) {
				pErrPos = 2;
				if (taskInfoManageService.deleteBatch(taskInfoIds, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "DWR����ɾ��ָ����������Ϣ ʧ�ܣ�");
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
	}
	
	/**
	 * ��������id���������������Ϣ
	 * @param taskInfoId ����id
	 */
	public List<TaskPerson> findTaskPersonByTaskInfoId(int taskInfoId,HttpServletRequest request) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		List<TaskPerson> taskPersons = null;
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForUserinfo(pErrInfo ) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				taskPersons = new ArrayList<TaskPerson>();
				if (taskInfoManageService.findTaskPersonByID(taskInfoId, taskPersons, pErrInfo)) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "DWR��������id���������������Ϣ ʧ�ܣ�");
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
		return taskPersons;
	}
	
	/**
	 *  ��������ظ���Ϣ
	 * @param taskID			����id
	 * @param responseContent	����ظ�����
	 * @param responseUserID	����ظ���id
	 */
	public boolean saveTaskResponse(int taskID, String responseContent, int responseUserID,HttpServletRequest request) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForUserinfo(pErrInfo )== false) {
				pFlag = false;
			}
			
			//�������ظ���Ϣ������id
			if (pFlag) {
				if (taskID<=0) {
					pFlag = false;
					pErrInfo.getContent().append("����id�Ƿ���");
				}
			}
			
			//�������ظ���id
			if (pFlag) {
				if (responseUserID<=0) {
					pFlag = false;
					pErrInfo.getContent().append("����ظ���id�Ƿ���");
				}
			}
			
			//��� ����ظ���Ϣ�Ļظ�����
			if (pFlag) {
				if (StringTool.checkNull(responseContent) == false) {
					pFlag = false;
					pErrInfo.getContent().append("����ظ����ݷǷ�Ϊ�ա�");
				}
			}

			if (pFlag) {
				pErrPos = 2;
				//���һ���µ�����ظ���Ϣ
				TaskResponse taskResponse = new TaskResponse(taskID, new Date(), responseUserID, responseContent);
				
				if (taskResponseManageService.saveTaskResponse(taskResponse, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "DWR���һ���µ�����ظ���Ϣ ʧ�ܣ�");
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
		return pFlag;
	}
}