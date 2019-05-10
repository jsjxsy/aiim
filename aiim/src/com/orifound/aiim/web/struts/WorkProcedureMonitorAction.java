package com.orifound.aiim.web.struts;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.servlet.ServletUtilities;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.orifound.aiim.bll.service.IPerformanceManageService;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserInfo;
import com.orifound.aiim.web.util.CreateTreeUtil;
import com.orifound.aiim.web.util.JfreeChartFactory;
import com.orifound.aiim.web.util.StringTool;
import com.orifound.aiim.web.util.TimeTool;

/**
 * ��Ч����->�������̼�������
 * @author tyb
 *
 */
public class WorkProcedureMonitorAction extends ActionSupport {

	private static final long serialVersionUID = -8100262328816068351L;
	
	static Log logger = LogFactory.getLog(WorkProcedureMonitorAction.class);
	
	/**
	 * ���صĽ��URL
	 */
	private String resultURL;
	
	/**
	 * ��� ��ʼʱ��
	 */
	private String beginTime;
	
	/**
	 * ��� ����ʱ��
	 */
	private String endTime;
	
	/**
	 * �˵��ڵ�ֵ
	 */
	private String nodeId;

	/**
	 * ѡ��������ɫid
	 * Ĭ��Ϊҵ��ָ����
	 */
	private int roleId = 1;
	
	/**
	 * ҵ��ָ������Աid����
	 */
	private int[] businessGuidId;
	
	/**
	 * ע�뼨Ч���������
	 */
	@Autowired
	private IPerformanceManageService performanceManageService;
	
	private int chartWidth = 0;
	private int chartHeight = 380;
	
	/**
	 * ��ȡ��Ч�������˵�
	 * @return String
	 */
	public String getTree() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String tree = CreateTreeUtil.getPerformanceManageTree();
		request.setAttribute("tree",tree);
		request.setAttribute("proceseAction","workProcedureMonitorAction_manage.action");
		resultURL = "/left.jsp";
		return SUCCESS;
	}

	/**
	 * ����nodeIdֵ
	 * �жϻ�ȡ�ĸ������� 
	 * @return
	 */
	public String manage() {
		if(StringTool.checkNull(nodeId) == false) {
		}
		Integer node = 0;
		try {
			node = Integer.valueOf(nodeId);
		} catch (NumberFormatException e) {
			node = 1;
		}
		
		getTree();
		
		switch (node) {
		case 1:
			//�������̼��
			recordCondition();
			break;
		case 11:
			//��¼���
			recordCondition();
			break;
		case 12:
			//��¼������
			recordAuditCondition();
			break;
		case 13:
			//������
			inStorageCondition();
			break;
		default:
			break;
		}
		return SUCCESS;
	}

	/**
	 * ���ҵ��ָ���ҵ���Ա\������ְ��Ա�ض�ʱ�������¼����������
	 */
	void recordCondition() {
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		// �õ�request
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForPerformanceManageService(pErrInfo) == false) {
				pFlag = false;
			}
			
			if (pFlag) {
				pErrPos = 2;
				//Map���� ��ֵ�Ա���:��Ա��������������
				Map<String, Integer> recordCondition = new LinkedHashMap<String, Integer>();
				List<Integer> businessGuidIds = null;
				List<Integer> userIds = null;
				
				//��ѯ���о���ҵ��ָ���ҽ�ɫ����Ա��Ϣ����
				List<UserInfo> businessGuids = new ArrayList<UserInfo>();
				if (performanceManageService.findBusinessGuids(businessGuids, pErrInfo)  == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ���о���ҵ��ָ���ҽ�ɫ����Ա��Ϣ���� Actionִ��ʧ�ܡ�");
				}
				
				if(businessGuids.size() >= 1) {
					businessGuidIds = new ArrayList<Integer>();
					for(UserInfo businessGuid : businessGuids) {
						businessGuidIds.add(businessGuid.getUserID());
					}
				}
				
				//���ʱ��Ϊ�� ����Ĭ��ʱ��
				if(StringTool.checkNull(beginTime) == false || StringTool.checkNull(beginTime) == false) {
					beginTime = TimeTool.getFirstDay();
					endTime = TimeTool.getDefalutDate();
				}
				
				if(pFlag) {
					
					//��ѯҵ��ָ������¼���
					if(roleId == 1) {
						if(performanceManageService.findRecordCondition(recordCondition , businessGuidIds, beginTime, endTime, pErrInfo) == false) {
							pFlag = false;
							pErrInfo.getContent().insert(0, "��ѯҵ��ָ���ҵ���Ա�ض�ʱ�������¼���������� ->Actionִ��ʧ�ܡ�");
						}
						
					} else  if(roleId == 2) {			//��ѯ������ְ��Ա��¼���
						
						//����ҵ��ָ������Աid�����ַ���(�Զ��ŷָ�)��ֵ��JSPҳ��
						StringBuffer businessGuidIdList = new StringBuffer();
						//������ѯ��ҵ��ָ����Ա
						businessGuidIds = new ArrayList<Integer>();
						if(businessGuidId!=null && businessGuidId.length>=1) {
							for(int id : businessGuidId) {
								businessGuidIds.add(id);
								businessGuidIdList.append(id).append(",");
							}
							businessGuidIdList.deleteCharAt(businessGuidIdList.length()-1);
							request.setAttribute("businessGuidIdList", businessGuidIdList.toString());
							
							userIds = new ArrayList<Integer>();
							//��ѯҵ��ָ����ָ�������е�����ְ��Ա
							if (performanceManageService.findPartTimePersons(businessGuidIds, userIds, pErrInfo) == false) {
								pFlag = false;
								pErrInfo.getContent().insert(0, "��ѯҵ��ָ����ָ�������е�����ְ��Ա ->Actionִ��ʧ�ܡ�");
							}
							
							//��ѯ������ְ��Ա�ض�ʱ�������¼����������
							if (pFlag) {
								//�ж��Ƿ���ڵ�����ְ��Ա
								if (userIds.size()>=1) {
									if(performanceManageService.findRecordCondition(recordCondition, userIds, beginTime, endTime, pErrInfo) == false) {
										pFlag = false;
										pErrInfo.getContent().insert(0, "��ѯ��ְ����Ա�ض�ʱ�������¼����������-> Actionִ��ʧ�ܡ�");
									}
								}
							}
						} else {
							request.setAttribute("businessGuidIdList", "none");
						}
					}
				}
				
				//������״ͼ
				if (pFlag) {
					//������ʾ����ֵ����
					String[] rowKeys = { "��¼"};
					//���ñ���
					String[] titles = null;
					if(roleId == 1) {
						titles = new String[] {"ҵ��ָ������¼���һ����", beginTime+"��"+endTime,"����"};
					}
					if(roleId == 2) {
						titles = new String[] {"��ְ����Ա��¼���һ����", beginTime+"��"+endTime,"����"};
					}
					
					JFreeChart chart = createChart(recordCondition, rowKeys, titles);
					
					//������״ͼ�Ŀ��
					setChartSzie(recordCondition.keySet().size());
					String fileName = ServletUtilities.saveChartAsPNG(chart, chartWidth, chartHeight, request.getSession());

					String url = request.getContextPath() + "/servlet/DisplayChart?filename=" + fileName;
					request.setAttribute("url", url);
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
		resultURL = "/JXGL/recordCondition.jsp";
	}
	
	/**
	 * ���ҵ��ָ������¼������
	 */
	void recordAuditCondition() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForPerformanceManageService(pErrInfo) == false) {
				pFlag = false;
			}
			
			if (pFlag) {
				pErrPos = 2;
				Map<String, Integer[]> recordAudit = new LinkedHashMap<String, Integer[]>();
				//��ѯ���о���ҵ��ָ���ҽ�ɫ����Ա��Ϣ����
				List<UserInfo> businessGuids = new ArrayList<UserInfo>();
				if (performanceManageService.findBusinessGuids(businessGuids, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ���о���ҵ��ָ���ҽ�ɫ����Ա��Ϣ����->userInfoManageServiceִ��ʧ�ܡ�");
				}
				
				List<Integer> userIds = null;
				if(businessGuids.size() >= 1) {
					userIds = new ArrayList<Integer>();
					for(UserInfo user : businessGuids) {
						userIds.add(user.getUserID());
					}
					
					//���ʱ��Ϊ�� ����Ĭ��ʱ��
					if(StringTool.checkNull(beginTime) == false || StringTool.checkNull(beginTime) == false) {
						beginTime = TimeTool.getFirstDay();
						endTime = TimeTool.getDefalutDate();
					}
					
					//��ѯ����������
					if (pFlag) {
						if (performanceManageService.findRecordAuditCondition(recordAudit, userIds, beginTime, endTime, pErrInfo) == false) {
							pFlag = false;
							pErrInfo.getContent().insert(0, "��ѯ����������->archivesInfoWorkProcedureManageServiceִ��ʧ�ܡ�");
						}
						
						//���û�������¼����ȫ��������
						if(recordAudit.keySet().size() <= 0) {
							for(UserInfo user : businessGuids) {
								recordAudit.put(user.getRealName(), new Integer[]{0,0});
							}
						}
						//������״ͼ
						if(recordAudit.keySet().size() >= 1) {
							//������ʾ����ֵ����
							String[] rowKeys = {"�����","�����"};
							//���ñ���
							String[] titles= new String[] {"ҵ��ָ������¼������һ����", beginTime+"��"+endTime,"����"};
							
							JFreeChart chart = createCharts(recordAudit, rowKeys, titles);
							setChartSzie(recordAudit.keySet().size());
							String fileName = ServletUtilities.saveChartAsPNG(chart, chartWidth, chartHeight, request.getSession());
							String url = request.getContextPath()
									+ "/servlet/DisplayChart?filename=" + fileName;
							request.setAttribute("url", url);
						}
					}
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
		
		resultURL = "/JXGL/recordAudit.jsp";
	}
	
	/**
	 * ��⹤����
	 */
	void inStorageCondition() {
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForPerformanceManageService(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				
				//���ʱ��Ϊ�� ����Ĭ��ʱ��
				if(StringTool.checkNull(beginTime) == false) {
					endTime = TimeTool.getDefalutDate();
				}
				
				Map<String, Integer> inStorageCondition = new LinkedHashMap<String, Integer>();
				//��ѯ������
				if (performanceManageService.findInStorageCondition(inStorageCondition,null,endTime, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"Action���ҵ���������������ʧ��");
				}

				//������״ͼ
				if (pFlag) {
					HttpServletRequest request = ServletActionContext.getRequest();
					JfreeChartFactory jfreeChart = JfreeChartFactory.getInstance();
					double[][] data = new double[1][inStorageCondition.keySet().size()];
					String[] columnKeys = new String[inStorageCondition.keySet().size()];
					int i = 0;
					for(String key : inStorageCondition.keySet()) {
						//������Ա����
						columnKeys[i] = key;
						//������¼����
						data[0][i] = inStorageCondition.get(key);
						i++;
					}
					
					jfreeChart.setColumnKeys(columnKeys);
					jfreeChart.setData(data);
					jfreeChart.setRowKeys(new String[]{"�����"});
					jfreeChart.setTitles(new String[] {"������������⹤����һ����", "��ֹ��"+endTime,"����"});
					setChartSzie(inStorageCondition.keySet().size());
					String fileName = ServletUtilities.saveChartAsPNG(jfreeChart.createChart(null), chartWidth, chartHeight, request.getSession());
					String url = request.getContextPath() + "/servlet/DisplayChart?filename=" + fileName;
					request.setAttribute("url", url);
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
		resultURL = "/JXGL/inStorageCondition.jsp";
	}
	
	/**
	 * ���������ҽ������
	 * @return
	 */
	public String archivesinfoManagerRevceiverCondition() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForPerformanceManageService(pErrInfo ) == false) {
				pFlag = false;
			}

			List<Integer> counts = new ArrayList<Integer>();
			//��Ч����->��������ѯ ���������ҽ������
			if (pFlag) {
				pErrPos = 2;
				if (performanceManageService.receiverCondition(counts , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action ��Ч����->��������ѯ ���������ҽ������ ʧ�ܣ�");
				}
			}
			
			//������״ͼ
			if (pFlag) {
				HttpServletRequest request = ServletActionContext.getRequest();
				JfreeChartFactory jfreeChart = JfreeChartFactory.getInstance();
				double[][] data = new double[counts.size()][1];
				for(int i=0; i<counts.size(); i++) {
					data[i][0] = counts.get(i);
				}
				jfreeChart.setColumnKeys(new String[]{""});
				jfreeChart.setData(data);
				jfreeChart.setRowKeys(new String[]{"δ������","�ܽ�����"});
				jfreeChart.setTitles(new String[] {"���������ҵ����������һ����"});
				setChartSzie(counts.size());
				String fileName = ServletUtilities.saveChartAsPNG(jfreeChart.createChart(null), chartWidth, chartHeight, request.getSession());
				String url = request.getContextPath() + "/servlet/DisplayChart?filename=" + fileName;
				request.setAttribute("url", url);
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
		resultURL = "/JXGL/revceiverCondition.jsp";
		return SUCCESS;
	}
	
	/**
	 * ������״ͼ�Ŀ��
	 * @param columnCount X������
	 */
	private void setChartSzie(int columnCount) {
		if(columnCount <= 20) {
			chartWidth = 500 + columnCount*10;
		} else {
			chartWidth=700;
		}
	}
	
	/**
	 * ����JFreeChart�������ͼƬ(��¼���ʹ��)
	 * @param recordCondition 
	 * @param rowKeys
	 * @param titles
	 * @return
	 */
	private JFreeChart createChart(Map<String, Integer> recordCondition, String[] rowKeys, String[] titles) {
		double[][] data = new double[1][recordCondition.keySet().size()];
		String[] columnKeys = new String[recordCondition.keySet().size()];
		
		int i = 0;
		for(String key : recordCondition.keySet()) {
			//������Ա����
			columnKeys[i] = key;
			//������¼����
			data[0][i] = recordCondition.get(key);
			i++;
		}
		JfreeChartFactory jfreeChart = JfreeChartFactory.getInstance();
		jfreeChart.setColumnKeys(columnKeys);
		jfreeChart.setData(data);
		jfreeChart.setRowKeys(rowKeys);
		jfreeChart.setTitles(titles);
		
		return jfreeChart.createChart(null);
		 
	}
	
	/**
	 * ����JFreeChart�������ͼƬ(������ʹ��)
	 * @param recordCondition 
	 * @param rowKeys
	 * @param titles
	 * @return
	 */
	private JFreeChart createCharts(Map<String, Integer[]> recordCondition, String[] rowKeys, String[] titles) {
		double[][] data = new double[2][recordCondition.keySet().size()];
		String[] columnKeys = new String[recordCondition.keySet().size()];
		
		int i = 0;
		for(String key : recordCondition.keySet()) {
			//������Ա����
			columnKeys[i] = key;
			//���ô��������
			data[0][i] = recordCondition.get(key)[1];
			//�������������
			data[1][i] = recordCondition.get(key)[0];
			i++;
		}
		JfreeChartFactory jfreeChart = JfreeChartFactory.getInstance();
		jfreeChart.setColumnKeys(columnKeys);
		jfreeChart.setData(data);
		jfreeChart.setRowKeys(rowKeys);
		jfreeChart.setTitles(titles);
		
		return jfreeChart.createChart(null);
	}
	
	/**
	 * ��鼨Ч����������ҵ���߼���������ע�루BLL Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkBllInjectionForPerformanceManageService(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//����Ƿ��н���BLLҵ���߼����������ע��
			pErrPos = 1;
			if (performanceManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append("��Ч����������BLLҵ���߼�����Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
	
	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	
	public String getResultURL() {
		return resultURL;
	}

	public void setResultURL(String resultURL) {
		this.resultURL = resultURL;
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
	
	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int[] getBusinessGuidId() {
		return businessGuidId;
	}

	public void setBusinessGuidId(int[] businessGuidId) {
		this.businessGuidId = businessGuidId;
	}
}