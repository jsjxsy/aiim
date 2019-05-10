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
 * 考核登记管理Action
 * @author tyb
 *
 */
public class EvaluateManageAction extends ActionSupport {

	private static final long serialVersionUID = -3955385655229093562L;
	
	/**
	 * 注入 考核管理服务类
	 */
	@Autowired
	private IEvaluateManageService evaluateManageService;
	
	/**
	 * 返回的结果URL
	 */
	private String resultURL;
	
	/**
	 * 考核记录编号 
	 */
	private int evaluateRegID;
	
	/**
	 * 职务id
	 */
	private int dutyId;
	
	/**
	 * 考核人姓名
	 */
	private String evaluateName;
	
	/**
	 * 考核日期
	 */
	private String registerDate;
	
	/**
	 * 最小分数
	 */
	private int minScore;
	
	/**
	 * 最大分数
	 */
	private int maxScore;
	
	/**
	 * 当前考核年度
	 */
	private String currentYear;
	
	/**
	 * 考核评分等级字典信息集合
	 */
	private List<EvaluateLevel> evaluateLevels = null;
	
	/**
	 * 职务信息数据字典信息集合
	 */
	private List<Duty> duties = SystemInitializer.getInstance().getDuties();
	
	private DataPageInfo dataPageInfo = new DataPageInfo();// 分页实体BEAN
	
	private List<String> evaluatedYears = null;

	/**
	 * 列表显示考核登记信息
	 * 显示已经通过考核的最大年度的考核记录，如果没有考核记录则显示当前年度需要考核的所有考核人员
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
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForEvaluateManage(pErrInfo ) == false) {
				pFlag = false;
			}
			
			//获取数据库中存在的考核记录年度集合
			if (pFlag) {
				evaluatedYears = new ArrayList<String>();
				if (evaluateManageService.findEvaluatedYears(evaluatedYears, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action 获取数据库中存在的考核记录年度 失败：");
				}
			}

			if (pFlag) {
				pErrPos = 2;
				List<EvaluateRegisterVO> evaluateRegisterVOs = new ArrayList<EvaluateRegisterVO>();
				
				System.out.println("开始查询前："+currentYear);
				
				/**
				 * 判断是否通过菜单项 第一次进入
				 */
				if (StringTool.checkNull(currentYear) == false) { //默认显示当前最大年度的考核记录
					if (evaluateManageService.findByMaxYear(null, evaluateRegisterVOs , pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "查询已经通过考核的最大年度的考核记录 失败：");
					}
					
					if (pFlag) {
						//检测页面显示年度的考核记录是否已经完成考核
						if (evaluateRegisterVOs.size() >= 1) {
							currentYear = evaluateRegisterVOs.get(0).getYears();
						}
					}
					System.out.println("currentYear="+currentYear);
					
				} else { //分页查询特定年度的考核记录
					if (evaluateManageService.findByYear(currentYear, null, evaluateRegisterVOs, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "分页查询特定年度的考核记录 失败：");
					}
				}
				
				//传递数据至页面
				if (pFlag) {
					duties = SystemInitializer.getInstance().getDuties();
					request.setAttribute("evaluateRegisterVOs", evaluateRegisterVOs);
					request.setAttribute("currentYear", currentYear);
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
		resultURL = "/JXGL/evaluateRegister_list.jsp";
		return SUCCESS;
	}
	
	/**
	 * 考核明细信息显示
	 * @return String
	 */
	public String evaluateDetails() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForEvaluateManage(pErrInfo ) == false) {
				pFlag = false;
			}
			
			//获取数据库中存在的考核记录年度集合
			if (pFlag) {
				evaluatedYears = new ArrayList<String>();
				if (evaluateManageService.findEvaluatedYears(evaluatedYears, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action 获取数据库中存在的考核记录年度 失败：");
				}
			}

			if (pFlag) {
				pErrPos = 2;
				EvaluateRegisterVO evaluateRegisterVO = new EvaluateRegisterVO();
				//调用Service 查询考核明细信息
				if (evaluateManageService.findEvaluateDetailsByRegID(evaluateRegID, evaluateRegisterVO, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询考核明细信息失败：");
				}
				HttpServletRequest request = ServletActionContext.getRequest();
				
				evaluateLevels = SystemInitializer.getInstance().getEvaluateLevels();
				request.setAttribute("evaluateItems", SystemInitializer.getInstance().getEvaluateItems());
				request.setAttribute("evaluateRegisterVO", evaluateRegisterVO);
				
				//保存所有考核项id 逗号分隔
				StringBuffer detailIds = new StringBuffer();
				for(EvaluateDetails details : evaluateRegisterVO.getDetails()) {
					detailIds.append(details.getEvaluateItemID()).append(",");
				}
				detailIds.deleteCharAt(detailIds.length()-1);
				System.out.println("detailIds.toString()="+detailIds.toString());
				request.setAttribute("detailIds", detailIds);
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

		resultURL = "/JXGL/evaluateDetails_show.jsp";
		return SUCCESS;
	}
	
	/**
	 * 更新考核登记信息
	 */
	public String update() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForEvaluateManage(pErrInfo ) == false) {
				pFlag = false;
			}
			
			//获取数据库中存在的考核记录年度集合
			if (pFlag) {
				evaluatedYears = new ArrayList<String>();
				if (evaluateManageService.findEvaluatedYears(evaluatedYears, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action 获取数据库中存在的考核记录年度 失败：");
				}
			}
			
			UserInfo user = (UserInfo) request.getSession().getAttribute("userInfo");
			
			//判断 用户信息
			if (user == null) {
				pFlag = false;
				pErrInfo.getContent().append("无法获取用户信息。");
			}
			
			
			//判断 评分等级id
			String evaluateLevelId = request.getParameter("evaluateLevelId");
			if (pFlag) {
				if (StringTool.checkNull(evaluateLevelId) == false) {
					pFlag = false;
					pErrInfo.getContent().append("无法获取考核评分等级id。");
				}
			}
			
			//检查 考核记录编号
			if (evaluateRegID<=0) {
				pFlag = false;
				pErrInfo.getContent().append("考核记录编号非法为零。");
			}
			
			//计算总分数
			int score = 0;
			
			if (pFlag) {
				pErrPos = 2;
				List<EvaluateItem> evaluateItems = SystemInitializer.getInstance().getEvaluateItems();
				EvaluateDetails evaluateDetails = null;
				String DD_EvaluateItemId = null;
				String DD_EvaluateItemScore = null;
				
				//循环更新考核明细
				for(EvaluateItem item : evaluateItems) {
					DD_EvaluateItemId = request.getParameter("DD_EvaluateItemId"+item.getID());
					DD_EvaluateItemScore = request.getParameter("DD_EvaluateItemScore"+item.getID());
					if (StringTool.checkNull(DD_EvaluateItemId) && StringTool.checkNull(DD_EvaluateItemScore)) {
						score += Integer.valueOf(DD_EvaluateItemScore);
						evaluateDetails = new EvaluateDetails(evaluateRegID, Integer.valueOf(DD_EvaluateItemId), Integer.valueOf(DD_EvaluateItemScore));
						if (evaluateManageService.updateDetail(evaluateDetails, pErrInfo) == false) {
							pFlag = false;
							pErrInfo.getContent().insert(0, "更新考核明细失败");
						}
					}
				}
			}
			
			//更新考核登记信息
			if (pFlag) {
				pErrPos = 3;
				EvaluateRegister evaluateRegister = new EvaluateRegister(evaluateRegID, score, Integer.valueOf(evaluateLevelId), request.getParameter("content"), new Date(), user.getUserID(), user.getDutyID());
				if (evaluateManageService.updateEvaluate(evaluateRegister, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "更新考核登记信息失败");
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
				
				System.out.println(pErrInfo.toString());
			}

			//销毁局部变量
			throwable = null;
		}
		resultURL = "/JXGL/evaluateRegister_list.jsp";
		return SUCCESS;
	}

	/**
	 * 搜索考核登记信息
	 */
	public String search() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForEvaluateManage(pErrInfo) == false) {
				pFlag = false;
			}
			
			//获取数据库中存在的考核记录年度集合
			if (pFlag) {
				evaluatedYears = new ArrayList<String>();
				if (evaluateManageService.findEvaluatedYears(evaluatedYears, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action 获取数据库中存在的考核记录年度 失败：");
				}
			}

			if (pFlag) {
				pErrPos = 2;
				duties = SystemInitializer.getInstance().getDuties();
				if (pFlag) {
					List<EvaluateRegisterVO> evaluateRegisterVOs = new ArrayList<EvaluateRegisterVO>();
					
					System.out.println("currentYear="+currentYear);
					
					//检索考核登记信息
					if (evaluateManageService.search(currentYear, evaluateRegisterVOs , pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "检索考核登记信息 失败：");
					}
					
					if (pFlag) {
						HttpServletRequest request = ServletActionContext.getRequest();
						request.setAttribute("evaluateRegisterVOs", evaluateRegisterVOs);
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
		
		resultURL = "/JXGL/evaluateRegister_list.jsp";
		return SUCCESS;
	}
	
	/**
	 * 接受人选择
	 * @return
	 */
	public String receiverChioce() {
		resultURL = "/JXGL/receiver_choice.jsp";
		return SUCCESS;
	}
	
	/**
	 * 检查考核管理的业务逻辑对象依赖注入（BLL Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkBllInjectionForEvaluateManage(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查是否有进行BLL业务逻辑对象的依赖注入
			pErrPos = 1;
			if (evaluateManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append("考核管理业务逻辑对象非法为空，请检查是否有进行依赖注入或赋值。");
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