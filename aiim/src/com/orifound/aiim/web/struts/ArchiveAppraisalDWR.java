package com.orifound.aiim.web.struts;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.IAppraisalPublicDetailManageService;
import com.orifound.aiim.bll.service.IAppraisalUseScopesDetailManageService;
import com.orifound.aiim.bll.service.IArchivesInfoSavedManageService;
import com.orifound.aiim.entity.AppraisalUseScopesDetail;
import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 鉴定管理 DWR
 *
 */
public class ArchiveAppraisalDWR {
	
	/**
	 * 注入档案划分控制鉴定明细情况表的实体类管理服务对象
	 */
	@Autowired
	private IAppraisalUseScopesDetailManageService appraisalUseScopesDetailManageService;
	
	/**
	 * 注入档案归档信息的管理服务对象
	 */
	@Autowired
	private IArchivesInfoSavedManageService archivesInfoSavedManageService;
	
	/**
	 * 注入档案公开/开放鉴定明细情况表的实体类管理服务对象
	 */
	@Autowired
	private IAppraisalPublicDetailManageService appraisalPublicDetailManageService;
	
	/**
	 * 根据档案内部序号查找档案划控明细信息
	 * @param NBXH 内部序号
	 * @return AppraisalUseScopesDetail  档案划控明细信息
	 */ 
	public AppraisalUseScopesDetail findAppraisalUseScopesDetailByByNBXH(int archivesTypeID, int NBXH ,HttpServletRequest request) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		AppraisalUseScopesDetail appraisalUseScopesDetail = null;
		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForAppraisalUseScopesDetail(pErrInfo ) == false) {
				pFlag = false;
			}

			//根据档案内部序号查找档案划分控制鉴定明细情况表的实体类信息
			if (pFlag) {
				pErrPos = 2;
				appraisalUseScopesDetail = new AppraisalUseScopesDetail();
				if (appraisalUseScopesDetailManageService.findAppraisalUseScopesDetailByByNBXH(archivesTypeID, NBXH, appraisalUseScopesDetail , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "DWR 根据档案内部序号查找档案划分控制鉴定明细情况表的实体类信息 失败：");
				}
			}
			
			//如果不存在划控记录 返回空
			if(appraisalUseScopesDetail.getID() <=0) {
				appraisalUseScopesDetail = null;
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
		return appraisalUseScopesDetail;
	}
	
	/**
	 * 鉴定管理->划控鉴定登记
	 * @param NBXH 内部序号
	 * @param archivesTypeID 档案类型id
	 * @param appraisalReason 鉴定原因
	 * @param appraisalDate 鉴定日期
	 * @param appraisalPersion 鉴定人
	 * @param managerUserID 经办人id
	 * @param roleIds 划控角色id数组
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean controlAreaRegister(int NBXH,int archivesTypeID,String appraisalReason,String appraisalDate,String appraisalPersion,int managerUserID,Integer[] roleIds,HttpServletRequest request) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForAppraisalUseScopesDetail(pErrInfo) == false) {
				pFlag = false;
			}

			ArchivesInfo archivesInfo = new ArchivesInfo();
			
			//查询档案信息
			if (pFlag) {
				pErrPos = 2;
				ArchivesType archivesType = new ArchivesType();
				archivesType.setID(archivesTypeID);
				
				//根据内部序号查找档案信息
				if (archivesInfoSavedManageService.findByNBXH(NBXH, archivesType, archivesInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "根据内部序号查找档案信息	失败：");
				}
			}
			
			//保存划控鉴定明细信息
			if (pFlag) {
				Date date = new SimpleDateFormat("yyyy-MM-dd").parse(appraisalDate);
				AppraisalUseScopesDetail appraisalUseScopesDetail = new AppraisalUseScopesDetail(archivesTypeID, NBXH, archivesInfo.getArchivesID(),
						archivesInfo.getTitle(), appraisalReason, date, null, appraisalPersion, managerUserID, null);
				//设置档案形成部门
				appraisalUseScopesDetail.setFormationDepartmentID(archivesInfo.getFormationDepartmentID());
				//设置属性：划控角色id集合 
				appraisalUseScopesDetail.setRoleIds(Arrays.asList(roleIds));
				
				if (appraisalUseScopesDetailManageService.saveOrUpdateAppraisalUseScopesDetail(appraisalUseScopesDetail , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "添加一个新的档案划分控制鉴定明细情况表的实体类 失败：");
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
	 * 根据划控鉴定获取授权的角色名称列表
	 * @param AppraisalUseScopesDetailsId
	 * @return List<String> 返回角色名称集合
	 */
	public List<String> findRoleNamesByControlArea(int AppraisalUseScopesDetailsId,HttpServletRequest request) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		List<String> roleNames = null;
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForAppraisalUseScopesDetail(pErrInfo) == false) {
				pFlag = false;
			}
			
			//鉴定管理：划控鉴定信息 根据id获取授权的所有角色名称
			if (pFlag) {
				pErrPos = 2;
				roleNames = new ArrayList<String>();
				if (appraisalUseScopesDetailManageService.findRoleNamesById(AppraisalUseScopesDetailsId, roleNames , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "DWR 鉴定管理：划控鉴定信息 根据id获取授权的所有角色名称 失败：");
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
		if(roleNames!=null && roleNames.size()>=1) {
			return roleNames;
		} else {
			return null;
		}
	}
	
	/**
	 * 取消开放/公开鉴定
	 * @param archivesTypeID 档案类型id
	 * @param NBXH	档案内部序号
	 * @param publicFlag 开放标志
	 * @return boolean 返回是否操作成功
	 */
	public boolean cancelPublicOpenRegister(int archivesTypeID, int NBXH, int publicFlag,HttpServletRequest request) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForAppraisalUseScopesDetail(pErrInfo ) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				ArchivesType archivesType = new ArchivesType();
				archivesType.setID(archivesTypeID);
				if (appraisalPublicDetailManageService.deleteAppraisalPublicDetail(archivesType, NBXH, publicFlag, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "DWR 鉴定管理->取消开放/公开鉴定 删除指定档案类型和内部序号的档案开放鉴定信息 失败：");
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
	
	/**
	 * 检查档案划分控制鉴定明细情况表的业务逻辑对象依赖注入（BLL Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkBllInjectionForAppraisalUseScopesDetail(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查是否有进行BLL业务逻辑对象的依赖注入
			pErrPos = 1;
			if (appraisalUseScopesDetailManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append(
						"档案划分控制鉴定明细情况表的BLL业务逻辑对象非法为空，请检查是否有进行依赖注入或赋值。");
			}
			
			pErrPos = 2;
			if (archivesInfoSavedManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append(
						"档案归档信息表的BLL业务逻辑对象非法为空，请检查是否有进行依赖注入或赋值。");
			}
			
			pErrPos = 3;
			if (appraisalPublicDetailManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append(
						"档案公开/开放鉴定明细情况表的BLL业务逻辑对象非法为空，请检查是否有进行依赖注入或赋值。");
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
}