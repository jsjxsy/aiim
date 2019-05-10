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
 * 绩效管理->工作过程监测控制类
 * @author tyb
 *
 */
public class WorkProcedureMonitorAction extends ActionSupport {

	private static final long serialVersionUID = -8100262328816068351L;
	
	static Log logger = LogFactory.getLog(WorkProcedureMonitorAction.class);
	
	/**
	 * 返回的结果URL
	 */
	private String resultURL;
	
	/**
	 * 检测 开始时间
	 */
	private String beginTime;
	
	/**
	 * 检测 结束时间
	 */
	private String endTime;
	
	/**
	 * 菜单节点值
	 */
	private String nodeId;

	/**
	 * 选择监测对象角色id
	 * 默认为业务指导室
	 */
	private int roleId = 1;
	
	/**
	 * 业务指导室人员id数组
	 */
	private int[] businessGuidId;
	
	/**
	 * 注入绩效管理服务类
	 */
	@Autowired
	private IPerformanceManageService performanceManageService;
	
	private int chartWidth = 0;
	private int chartHeight = 380;
	
	/**
	 * 获取绩效管理树菜单
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
	 * 根据nodeId值
	 * 判断获取哪个情况监测 
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
			//工作过程监测
			recordCondition();
			break;
		case 11:
			//著录情况
			recordCondition();
			break;
		case 12:
			//著录审核情况
			recordAuditCondition();
			break;
		case 13:
			//入库情况
			inStorageCondition();
			break;
		default:
			break;
		}
		return SUCCESS;
	}

	/**
	 * 监测业务指导室的人员\档案兼职人员特定时间段内著录档案的数量
	 */
	void recordCondition() {
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		// 得到request
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForPerformanceManageService(pErrInfo) == false) {
				pFlag = false;
			}
			
			if (pFlag) {
				pErrPos = 2;
				//Map集合 键值对保存:人员姓名：档案数量
				Map<String, Integer> recordCondition = new LinkedHashMap<String, Integer>();
				List<Integer> businessGuidIds = null;
				List<Integer> userIds = null;
				
				//查询所有具有业务指导室角色的人员信息集合
				List<UserInfo> businessGuids = new ArrayList<UserInfo>();
				if (performanceManageService.findBusinessGuids(businessGuids, pErrInfo)  == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询所有具有业务指导室角色的人员信息集合 Action执行失败。");
				}
				
				if(businessGuids.size() >= 1) {
					businessGuidIds = new ArrayList<Integer>();
					for(UserInfo businessGuid : businessGuids) {
						businessGuidIds.add(businessGuid.getUserID());
					}
				}
				
				//如果时间为空 设置默认时间
				if(StringTool.checkNull(beginTime) == false || StringTool.checkNull(beginTime) == false) {
					beginTime = TimeTool.getFirstDay();
					endTime = TimeTool.getDefalutDate();
				}
				
				if(pFlag) {
					
					//查询业务指导室著录情况
					if(roleId == 1) {
						if(performanceManageService.findRecordCondition(recordCondition , businessGuidIds, beginTime, endTime, pErrInfo) == false) {
							pFlag = false;
							pErrInfo.getContent().insert(0, "查询业务指导室的人员特定时间段内著录档案的数量 ->Action执行失败。");
						}
						
					} else  if(roleId == 2) {			//查询档案兼职人员著录情况
						
						//构建业务指导室人员id集合字符串(以逗号分隔)传值到JSP页面
						StringBuffer businessGuidIdList = new StringBuffer();
						//构建查询的业务指导人员
						businessGuidIds = new ArrayList<Integer>();
						if(businessGuidId!=null && businessGuidId.length>=1) {
							for(int id : businessGuidId) {
								businessGuidIds.add(id);
								businessGuidIdList.append(id).append(",");
							}
							businessGuidIdList.deleteCharAt(businessGuidIdList.length()-1);
							request.setAttribute("businessGuidIdList", businessGuidIdList.toString());
							
							userIds = new ArrayList<Integer>();
							//查询业务指导室指导的所有档案兼职人员
							if (performanceManageService.findPartTimePersons(businessGuidIds, userIds, pErrInfo) == false) {
								pFlag = false;
								pErrInfo.getContent().insert(0, "查询业务指导室指导的所有档案兼职人员 ->Action执行失败。");
							}
							
							//查询档案兼职人员特定时间段内著录档案的数量
							if (pFlag) {
								//判断是否存在档案兼职人员
								if (userIds.size()>=1) {
									if(performanceManageService.findRecordCondition(recordCondition, userIds, beginTime, endTime, pErrInfo) == false) {
										pFlag = false;
										pErrInfo.getContent().insert(0, "查询兼职档案员特定时间段内著录档案的数量-> Action执行失败。");
									}
								}
							}
						} else {
							request.setAttribute("businessGuidIdList", "none");
						}
					}
				}
				
				//构建柱状图
				if (pFlag) {
					//设置显示的数值含义
					String[] rowKeys = { "著录"};
					//设置标题
					String[] titles = null;
					if(roleId == 1) {
						titles = new String[] {"业务指导室著录情况一览表", beginTime+"到"+endTime,"数量"};
					}
					if(roleId == 2) {
						titles = new String[] {"兼职档案员著录情况一览表", beginTime+"到"+endTime,"数量"};
					}
					
					JFreeChart chart = createChart(recordCondition, rowKeys, titles);
					
					//设置柱状图的宽高
					setChartSzie(recordCondition.keySet().size());
					String fileName = ServletUtilities.saveChartAsPNG(chart, chartWidth, chartHeight, request.getSession());

					String url = request.getContextPath() + "/servlet/DisplayChart?filename=" + fileName;
					request.setAttribute("url", url);
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
		resultURL = "/JXGL/recordCondition.jsp";
	}
	
	/**
	 * 监测业务指导室著录审核情况
	 */
	void recordAuditCondition() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForPerformanceManageService(pErrInfo) == false) {
				pFlag = false;
			}
			
			if (pFlag) {
				pErrPos = 2;
				Map<String, Integer[]> recordAudit = new LinkedHashMap<String, Integer[]>();
				//查询所有具有业务指导室角色的人员信息集合
				List<UserInfo> businessGuids = new ArrayList<UserInfo>();
				if (performanceManageService.findBusinessGuids(businessGuids, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询所有具有业务指导室角色的人员信息集合->userInfoManageService执行失败。");
				}
				
				List<Integer> userIds = null;
				if(businessGuids.size() >= 1) {
					userIds = new ArrayList<Integer>();
					for(UserInfo user : businessGuids) {
						userIds.add(user.getUserID());
					}
					
					//如果时间为空 设置默认时间
					if(StringTool.checkNull(beginTime) == false || StringTool.checkNull(beginTime) == false) {
						beginTime = TimeTool.getFirstDay();
						endTime = TimeTool.getDefalutDate();
					}
					
					//查询审核情况数量
					if (pFlag) {
						if (performanceManageService.findRecordAuditCondition(recordAudit, userIds, beginTime, endTime, pErrInfo) == false) {
							pFlag = false;
							pErrInfo.getContent().insert(0, "查询审核情况数量->archivesInfoWorkProcedureManageService执行失败。");
						}
						
						//如果没有审核著录数据全部设置零
						if(recordAudit.keySet().size() <= 0) {
							for(UserInfo user : businessGuids) {
								recordAudit.put(user.getRealName(), new Integer[]{0,0});
							}
						}
						//生成柱状图
						if(recordAudit.keySet().size() >= 1) {
							//设置显示的数值含义
							String[] rowKeys = {"待审核","已审核"};
							//设置标题
							String[] titles= new String[] {"业务指导室著录审核情况一览表", beginTime+"到"+endTime,"数量"};
							
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
		
		resultURL = "/JXGL/recordAudit.jsp";
	}
	
	/**
	 * 入库工作量
	 */
	void inStorageCondition() {
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForPerformanceManageService(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				
				//如果时间为空 设置默认时间
				if(StringTool.checkNull(beginTime) == false) {
					endTime = TimeTool.getDefalutDate();
				}
				
				Map<String, Integer> inStorageCondition = new LinkedHashMap<String, Integer>();
				//查询入库情况
				if (performanceManageService.findInStorageCondition(inStorageCondition,null,endTime, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"Action查找档案管理室入库情况失败");
				}

				//生成柱状图
				if (pFlag) {
					HttpServletRequest request = ServletActionContext.getRequest();
					JfreeChartFactory jfreeChart = JfreeChartFactory.getInstance();
					double[][] data = new double[1][inStorageCondition.keySet().size()];
					String[] columnKeys = new String[inStorageCondition.keySet().size()];
					int i = 0;
					for(String key : inStorageCondition.keySet()) {
						//设置人员姓名
						columnKeys[i] = key;
						//设置著录数量
						data[0][i] = inStorageCondition.get(key);
						i++;
					}
					
					jfreeChart.setColumnKeys(columnKeys);
					jfreeChart.setData(data);
					jfreeChart.setRowKeys(new String[]{"入库数"});
					jfreeChart.setTitles(new String[] {"档案管理室入库工作量一览表", "截止到"+endTime,"数量"});
					setChartSzie(inStorageCondition.keySet().size());
					String fileName = ServletUtilities.saveChartAsPNG(jfreeChart.createChart(null), chartWidth, chartHeight, request.getSession());
					String url = request.getContextPath() + "/servlet/DisplayChart?filename=" + fileName;
					request.setAttribute("url", url);
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
		resultURL = "/JXGL/inStorageCondition.jsp";
	}
	
	/**
	 * 档案管理室接收情况
	 * @return
	 */
	public String archivesinfoManagerRevceiverCondition() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForPerformanceManageService(pErrInfo ) == false) {
				pFlag = false;
			}

			List<Integer> counts = new ArrayList<Integer>();
			//绩效管理->入库情况查询 档案管理室接收情况
			if (pFlag) {
				pErrPos = 2;
				if (performanceManageService.receiverCondition(counts , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action 绩效管理->入库情况查询 档案管理室接收情况 失败：");
				}
			}
			
			//生成柱状图
			if (pFlag) {
				HttpServletRequest request = ServletActionContext.getRequest();
				JfreeChartFactory jfreeChart = JfreeChartFactory.getInstance();
				double[][] data = new double[counts.size()][1];
				for(int i=0; i<counts.size(); i++) {
					data[i][0] = counts.get(i);
				}
				jfreeChart.setColumnKeys(new String[]{""});
				jfreeChart.setData(data);
				jfreeChart.setRowKeys(new String[]{"未接收数","总接收数"});
				jfreeChart.setTitles(new String[] {"档案管理室档案接收情况一览表"});
				setChartSzie(counts.size());
				String fileName = ServletUtilities.saveChartAsPNG(jfreeChart.createChart(null), chartWidth, chartHeight, request.getSession());
				String url = request.getContextPath() + "/servlet/DisplayChart?filename=" + fileName;
				request.setAttribute("url", url);
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
		resultURL = "/JXGL/revceiverCondition.jsp";
		return SUCCESS;
	}
	
	/**
	 * 设置柱状图的宽高
	 * @param columnCount X轴列数
	 */
	private void setChartSzie(int columnCount) {
		if(columnCount <= 20) {
			chartWidth = 500 + columnCount*10;
		} else {
			chartWidth=700;
		}
	}
	
	/**
	 * 构建JFreeChart对象产生图片(著录情况使用)
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
			//设置人员姓名
			columnKeys[i] = key;
			//设置著录数量
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
	 * 构建JFreeChart对象产生图片(审核情况使用)
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
			//设置人员姓名
			columnKeys[i] = key;
			//设置待审核数量
			data[0][i] = recordCondition.get(key)[1];
			//设置已审核数量
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
	 * 检查绩效管理服务类的业务逻辑对象依赖注入（BLL Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkBllInjectionForPerformanceManageService(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查是否有进行BLL业务逻辑对象的依赖注入
			pErrPos = 1;
			if (performanceManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append("绩效管理服务类的BLL业务逻辑对象非法为空，请检查是否有进行依赖注入或赋值。");
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