package com.orifound.aiim.web.struts;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.orifound.aiim.bll.service.IEvaluateManageService;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.Duty;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.EvaluateDetails;
import com.orifound.aiim.entity.EvaluateItem;
import com.orifound.aiim.entity.EvaluateLevel;
import com.orifound.aiim.entity.EvaluateRegister;
import com.orifound.aiim.entity.EvaluateRegisterVO;
import com.orifound.aiim.entity.SystemInitializer;
import com.orifound.aiim.entity.UserInfo;
import com.orifound.aiim.web.util.StringTool;

/**
 * ���˵Ǽǹ���Action
 * @author tyb
 *
 */
public class EvaluateManageAction extends ActionSupport {

	private static final long serialVersionUID = -3955385655229093562L;
	
	/**
	 * ע�� ���˹��������
	 */
	@Autowired
	private IEvaluateManageService evaluateManageService;
	
	/**
	 * ���صĽ��URL
	 */
	private String resultURL;
	
	/**
	 * ���˼�¼��� 
	 */
	private int evaluateRegID;
	
	/**
	 * ְ��id
	 */
	private int dutyId;
	
	/**
	 * ����������
	 */
	private String evaluateName;
	
	/**
	 * ��������
	 */
	private String registerDate;
	
	/**
	 * ��С����
	 */
	private int minScore;
	
	/**
	 * ������
	 */
	private int maxScore;
	
	/**
	 * ��ǰ�������
	 */
	private String currentYear;
	
	/**
	 * �������ֵȼ��ֵ���Ϣ����
	 */
	private List<EvaluateLevel> evaluateLevels = null;
	
	/**
	 * ְ����Ϣ�����ֵ���Ϣ����
	 */
	private List<Duty> duties = SystemInitializer.getInstance().getDuties();
	
	private DataPageInfo dataPageInfo = new DataPageInfo();// ��ҳʵ��BEAN
	
	private List<String> evaluatedYears = null;

	/**
	 * �б���ʾ���˵Ǽ���Ϣ
	 * ��ʾ�Ѿ�ͨ�����˵������ȵĿ��˼�¼�����û�п��˼�¼����ʾ��ǰ�����Ҫ���˵����п�����Ա
	 * @return
	 */
	public String show() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForEvaluateManage(pErrInfo ) == false) {
				pFlag = false;
			}
			
			//��ȡ���ݿ��д��ڵĿ��˼�¼��ȼ���
			if (pFlag) {
				evaluatedYears = new ArrayList<String>();
				if (evaluateManageService.findEvaluatedYears(evaluatedYears, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action ��ȡ���ݿ��д��ڵĿ��˼�¼��� ʧ�ܣ�");
				}
			}

			if (pFlag) {
				pErrPos = 2;
				List<EvaluateRegisterVO> evaluateRegisterVOs = new ArrayList<EvaluateRegisterVO>();
				
				System.out.println("��ʼ��ѯǰ��"+currentYear);
				
				/**
				 * �ж��Ƿ�ͨ���˵��� ��һ�ν���
				 */
				if (StringTool.checkNull(currentYear) == false) { //Ĭ����ʾ��ǰ�����ȵĿ��˼�¼
					if (evaluateManageService.findByMaxYear(null, evaluateRegisterVOs , pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "��ѯ�Ѿ�ͨ�����˵������ȵĿ��˼�¼ ʧ�ܣ�");
					}
					
					if (pFlag) {
						//���ҳ����ʾ��ȵĿ��˼�¼�Ƿ��Ѿ���ɿ���
						if (evaluateRegisterVOs.size() >= 1) {
							currentYear = evaluateRegisterVOs.get(0).getYears();
						}
					}
					System.out.println("currentYear="+currentYear);
					
				} else { //��ҳ��ѯ�ض���ȵĿ��˼�¼
					if (evaluateManageService.findByYear(currentYear, null, evaluateRegisterVOs, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "��ҳ��ѯ�ض���ȵĿ��˼�¼ ʧ�ܣ�");
					}
				}
				
				//����������ҳ��
				if (pFlag) {
					duties = SystemInitializer.getInstance().getDuties();
					request.setAttribute("evaluateRegisterVOs", evaluateRegisterVOs);
					request.setAttribute("currentYear", currentYear);
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
		resultURL = "/JXGL/evaluateRegister_list.jsp";
		return SUCCESS;
	}
	
	/**
	 * ������ϸ��Ϣ��ʾ
	 * @return String
	 */
	public String evaluateDetails() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForEvaluateManage(pErrInfo ) == false) {
				pFlag = false;
			}
			
			//��ȡ���ݿ��д��ڵĿ��˼�¼��ȼ���
			if (pFlag) {
				evaluatedYears = new ArrayList<String>();
				if (evaluateManageService.findEvaluatedYears(evaluatedYears, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action ��ȡ���ݿ��д��ڵĿ��˼�¼��� ʧ�ܣ�");
				}
			}

			if (pFlag) {
				pErrPos = 2;
				EvaluateRegisterVO evaluateRegisterVO = new EvaluateRegisterVO();
				//����Service ��ѯ������ϸ��Ϣ
				if (evaluateManageService.findEvaluateDetailsByRegID(evaluateRegID, evaluateRegisterVO, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ������ϸ��Ϣʧ�ܣ�");
				}
				HttpServletRequest request = ServletActionContext.getRequest();
				
				evaluateLevels = SystemInitializer.getInstance().getEvaluateLevels();
				request.setAttribute("evaluateItems", SystemInitializer.getInstance().getEvaluateItems());
				request.setAttribute("evaluateRegisterVO", evaluateRegisterVO);
				
				//�������п�����id ���ŷָ�
				StringBuffer detailIds = new StringBuffer();
				for(EvaluateDetails details : evaluateRegisterVO.getDetails()) {
					detailIds.append(details.getEvaluateItemID()).append(",");
				}
				detailIds.deleteCharAt(detailIds.length()-1);
				System.out.println("detailIds.toString()="+detailIds.toString());
				request.setAttribute("detailIds", detailIds);
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

		resultURL = "/JXGL/evaluateDetails_show.jsp";
		return SUCCESS;
	}
	
	/**
	 * ���¿��˵Ǽ���Ϣ
	 */
	public String update() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForEvaluateManage(pErrInfo ) == false) {
				pFlag = false;
			}
			
			//��ȡ���ݿ��д��ڵĿ��˼�¼��ȼ���
			if (pFlag) {
				evaluatedYears = new ArrayList<String>();
				if (evaluateManageService.findEvaluatedYears(evaluatedYears, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action ��ȡ���ݿ��д��ڵĿ��˼�¼��� ʧ�ܣ�");
				}
			}
			
			UserInfo user = (UserInfo) request.getSession().getAttribute("userInfo");
			
			//�ж� �û���Ϣ
			if (user == null) {
				pFlag = false;
				pErrInfo.getContent().append("�޷���ȡ�û���Ϣ��");
			}
			
			
			//�ж� ���ֵȼ�id
			String evaluateLevelId = request.getParameter("evaluateLevelId");
			if (pFlag) {
				if (StringTool.checkNull(evaluateLevelId) == false) {
					pFlag = false;
					pErrInfo.getContent().append("�޷���ȡ�������ֵȼ�id��");
				}
			}
			
			//��� ���˼�¼���
			if (evaluateRegID<=0) {
				pFlag = false;
				pErrInfo.getContent().append("���˼�¼��ŷǷ�Ϊ�㡣");
			}
			
			//�����ܷ���
			int score = 0;
			
			if (pFlag) {
				pErrPos = 2;
				List<EvaluateItem> evaluateItems = SystemInitializer.getInstance().getEvaluateItems();
				EvaluateDetails evaluateDetails = null;
				String DD_EvaluateItemId = null;
				String DD_EvaluateItemScore = null;
				
				//ѭ�����¿�����ϸ
				for(EvaluateItem item : evaluateItems) {
					DD_EvaluateItemId = request.getParameter("DD_EvaluateItemId"+item.getID());
					DD_EvaluateItemScore = request.getParameter("DD_EvaluateItemScore"+item.getID());
					if (StringTool.checkNull(DD_EvaluateItemId) && StringTool.checkNull(DD_EvaluateItemScore)) {
						score += Integer.valueOf(DD_EvaluateItemScore);
						evaluateDetails = new EvaluateDetails(evaluateRegID, Integer.valueOf(DD_EvaluateItemId), Integer.valueOf(DD_EvaluateItemScore));
						if (evaluateManageService.updateDetail(evaluateDetails, pErrInfo) == false) {
							pFlag = false;
							pErrInfo.getContent().insert(0, "���¿�����ϸʧ��");
						}
					}
				}
			}
			
			//���¿��˵Ǽ���Ϣ
			if (pFlag) {
				pErrPos = 3;
				EvaluateRegister evaluateRegister = new EvaluateRegister(evaluateRegID, score, Integer.valueOf(evaluateLevelId), request.getParameter("content"), new Date(), user.getUserID(), user.getDutyID());
				if (evaluateManageService.updateEvaluate(evaluateRegister, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���¿��˵Ǽ���Ϣʧ��");
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
				
				System.out.println(pErrInfo.toString());
			}

			//���پֲ�����
			throwable = null;
		}
		resultURL = "/JXGL/evaluateRegister_list.jsp";
		return SUCCESS;
	}

	/**
	 * �������˵Ǽ���Ϣ
	 */
	public String search() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForEvaluateManage(pErrInfo) == false) {
				pFlag = false;
			}
			
			//��ȡ���ݿ��д��ڵĿ��˼�¼��ȼ���
			if (pFlag) {
				evaluatedYears = new ArrayList<String>();
				if (evaluateManageService.findEvaluatedYears(evaluatedYears, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action ��ȡ���ݿ��д��ڵĿ��˼�¼��� ʧ�ܣ�");
				}
			}

			if (pFlag) {
				pErrPos = 2;
				duties = SystemInitializer.getInstance().getDuties();
				if (pFlag) {
					List<EvaluateRegisterVO> evaluateRegisterVOs = new ArrayList<EvaluateRegisterVO>();
					
					System.out.println("currentYear="+currentYear);
					
					//�������˵Ǽ���Ϣ
					if (evaluateManageService.search(currentYear, evaluateRegisterVOs , pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "�������˵Ǽ���Ϣ ʧ�ܣ�");
					}
					
					if (pFlag) {
						HttpServletRequest request = ServletActionContext.getRequest();
						request.setAttribute("evaluateRegisterVOs", evaluateRegisterVOs);
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
		
		resultURL = "/JXGL/evaluateRegister_list.jsp";
		return SUCCESS;
	}
	
	/**
	 * ������ѡ��
	 * @return
	 */
	public String receiverChioce() {
		resultURL = "/JXGL/receiver_choice.jsp";
		return SUCCESS;
	}
	
	/**
	 * ��鿼�˹����ҵ���߼���������ע�루BLL Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkBllInjectionForEvaluateManage(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//����Ƿ��н���BLLҵ���߼����������ע��
			pErrPos = 1;
			if (evaluateManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append("���˹���ҵ���߼�����Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
	
	public int getEvaluateRegID() {
		return evaluateRegID;
	}

	public void setEvaluateRegID(int evaluateRegID) {
		this.evaluateRegID = evaluateRegID;
	}

	public List<EvaluateLevel> getEvaluateLevels() {
		return evaluateLevels;
	}

	public void setEvaluateLevels(List<EvaluateLevel> evaluateLevels) {
		this.evaluateLevels = evaluateLevels;
	}
	
	public DataPageInfo getDataPageInfo() {
		return dataPageInfo;
	}

	public void setDataPageInfo(DataPageInfo dataPageInfo) {
		this.dataPageInfo = dataPageInfo;
	}

	public String getCurrentYear() {
		return currentYear;
	}

	public void setCurrentYear(String currentYear) {
		this.currentYear = currentYear;
	}
	
	public int getDutyId() {
		return dutyId;
	}

	public void setDutyId(int dutyId) {
		this.dutyId = dutyId;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public int getMinScore() {
		return minScore;
	}

	public void setMinScore(int minScore) {
		this.minScore = minScore;
	}

	public int getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}

	public String getEvaluateName() {
		return evaluateName;
	}

	public void setEvaluateName(String evaluateName) {
		this.evaluateName = evaluateName;
	}

	public List<Duty> getDuties() {
		return duties;
	}

	public void setDuties(List<Duty> duties) {
		this.duties = duties;
	}

	public List<String> getEvaluatedYears() {
		return evaluatedYears;
	}

	public void setEvaluatedYears(List<String> evaluatedYears) {
		this.evaluatedYears = evaluatedYears;
	}
}