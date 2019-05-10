/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.IAppraisalUseScopesDetailManageService;
import com.orifound.aiim.bll.util.CommonUtil;
import com.orifound.aiim.bll.util.StringTool;
import com.orifound.aiim.dal.dao.IAppraisalUseScopesDetailDao;
import com.orifound.aiim.entity.AppraisalUseScopesDetail;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 档案划分控制鉴定明细情况表的实体类管理服务类
 *
 */
public class AppraisalUseScopesDetailManageServiceImpl implements IAppraisalUseScopesDetailManageService {

	/**
	 * 注入划控鉴定的档案明细信息DAO
	 */
	@Autowired
	private IAppraisalUseScopesDetailDao appraisalUseScopesDetailDao;
	
	/**
	 * 构造函数
	 */
	public AppraisalUseScopesDetailManageServiceImpl() {

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public AppraisalUseScopesDetailManageServiceImpl(IAppraisalUseScopesDetailDao appraisalUseScopesDetailDao) {
		this.appraisalUseScopesDetailDao = appraisalUseScopesDetailDao;
	}
	
	/**
	 * 检查档案划分控制鉴定明细情况表的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForAppraisalUseScopesDetail(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (appraisalUseScopesDetailDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("档案划分控制鉴定明细情况表的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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
	
	
	
	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IAppraisalUseScopesDetailManageService#deleteAppraisalUseScopesDetail(com.orifound.aiim.entity.AppraisalUseScopesDetail, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteAppraisalUseScopesDetail(
			AppraisalUseScopesDetail appraisalUseScopesDetail, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IAppraisalUseScopesDetailManageService#findAppraisalUseScopesDetailByID(int, com.orifound.aiim.entity.AppraisalUseScopesDetail, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findAppraisalUseScopesDetailByID(int pID,
			AppraisalUseScopesDetail appraisalUseScopesDetail, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IAppraisalUseScopesDetailManageService#findAppraisalUseScopesDetails(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findAppraisalUseScopesDetails(
			List<AppraisalUseScopesDetail> appraisalUseScopesDetails,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IAppraisalUseScopesDetailManageService#saveAppraisalUseScopesDetail(com.orifound.aiim.entity.AppraisalUseScopesDetail, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveOrUpdateAppraisalUseScopesDetail(AppraisalUseScopesDetail appraisalUseScopesDetail, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForAppraisalUseScopesDetail(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检查档案划分控制鉴定明细情况表的实体是否为空
			if (pFlag) {
				if (appraisalUseScopesDetail == null) {
					pFlag =false;
					pErrInfo.getContent().append("档案划分控制鉴定明细情况表的实体非法为空。");
				}
			}
			
			//检测属性鉴定人是否为空
			if (pFlag) {
				if (StringTool.checkNull(appraisalUseScopesDetail.getAppraisalPersion()) == false) {
					pFlag =false;
					pErrInfo.getContent().append("属性:鉴定人非法为空。");
				}
			}
			
			//检测属性鉴定日期是否为空
			if (pFlag) {
				if (appraisalUseScopesDetail.getAppraisalDate() == null) {
					pFlag =false;
					pErrInfo.getContent().append("属性:鉴定日期非法为空。");
				}
			}
			
			//检测属性经办人编号 是否为空
			if (pFlag) {
				if (appraisalUseScopesDetail.getManagerUserID() <= 0) {
					pFlag =false;
					pErrInfo.getContent().append("属性:经办人编号非法为空。");
				}
			}
			
			
			//查询已经存在的划控信息
			AppraisalUseScopesDetail oldAppraisalUseScopesDetail = new AppraisalUseScopesDetail();
			if (pFlag) {
				pErrPos = 2;
				//根据档案内部序号查找档案划分控制鉴定明细情况表的实体类信息
				if (appraisalUseScopesDetailDao.findByByNBXH(appraisalUseScopesDetail.getArchivesTypeID() ,appraisalUseScopesDetail.getNBXH(), oldAppraisalUseScopesDetail, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "根据档案内部序号查找档案划分控制鉴定明细情况表的实体类信息 失败：");
				}
			}
			
			//判断是否已经存在划控信息
			if (pFlag) {
				pErrPos = 3;
				if (oldAppraisalUseScopesDetail.getID() >= 1) {	//存在划控信息 进行更新操作
					//设置id值
					appraisalUseScopesDetail.setID(oldAppraisalUseScopesDetail.getID());
					
					//判断是否划控鉴定 取消所有角色
					if(appraisalUseScopesDetail.getRoleIds()==null || appraisalUseScopesDetail.getRoleIds().size()<=0) {
						
						//删除 档案划分控制鉴定明细情况信息
						if (appraisalUseScopesDetailDao.delete(appraisalUseScopesDetail, pErrInfo) == false) {
							pFlag = false;
							pErrInfo.getContent().insert(0, "更新 档案划分控制鉴定明细情况信息 失败：");
						}
						
					} else {
						//更新 档案划分控制鉴定明细情况信息
						if (appraisalUseScopesDetailDao.update(appraisalUseScopesDetail, pErrInfo) == false) {
							pFlag = false;
							pErrInfo.getContent().insert(0, "更新 档案划分控制鉴定明细情况信息 失败：");
						}
						
						pErrPos = 4;
						//判断档案是否已经存在划控角色
						if(oldAppraisalUseScopesDetail.getRoleIds()!=null || oldAppraisalUseScopesDetail.getRoleIds().size()>=1) {
							//删除划控角色id集合
							List<Integer> deleteRoleIds = oldAppraisalUseScopesDetail.getRoleIds();
							//新增划控角色id集合
							List<Integer> saveRoleIds = new ArrayList<Integer>();
							
							//循环 页面划控鉴定登记生成的角色id集合
							for(Integer newRoleId : appraisalUseScopesDetail.getRoleIds()) {
								if(oldAppraisalUseScopesDetail.getRoleIds().contains(newRoleId)) {
									//如果划控角色已经存在  从删除列表除去
									deleteRoleIds.remove(newRoleId);
								} else {
									//如果不存在 进行插入操作
									saveRoleIds.add(newRoleId);
								}
							}
							
							pErrPos = 5;
							//判断是否存在删除的角色
							if(pFlag && deleteRoleIds.size() >= 1) {
								if (appraisalUseScopesDetailDao.deleteRoles(oldAppraisalUseScopesDetail.getID(), deleteRoleIds, pErrInfo) == false) {
									pFlag = false;
									pErrInfo.getContent().insert(0, "删除 档案划控鉴定角色 失败：");
								}
							}
							
							pErrPos = 6;
							//判断是否存在新增的角色
							if (pFlag && saveRoleIds.size()>=1) {
								if (appraisalUseScopesDetailDao.saveRoles(oldAppraisalUseScopesDetail.getID(), saveRoleIds, pErrInfo) == false) {
									pFlag = false;
									pErrInfo.getContent().insert(0, "新增 档案划控鉴定角色 失败：");
								}
							}
						} else {	
							pErrPos = 7;
							//档案不存在划控角色	直接插入角色
							if (appraisalUseScopesDetailDao.saveRoles(oldAppraisalUseScopesDetail.getID(), appraisalUseScopesDetail.getRoleIds(), pErrInfo) == false) {
								pFlag = false;
								pErrInfo.getContent().insert(0, "新增 档案划控鉴定角色 失败：");
							}
						}
					}
					
				} else {	//不存在划控信息 进行新增操作
					
					//保存档案划分控制鉴定明细情况信息
					if (appraisalUseScopesDetailDao.save(appraisalUseScopesDetail, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "保存档案划分控制鉴定明细情况信息 失败：");
					}
					
					
					//插入划控角色信息
					if (pFlag) {
						if (appraisalUseScopesDetailDao.saveRoles(appraisalUseScopesDetail.getID(), appraisalUseScopesDetail.getRoleIds(), pErrInfo) == false) {
							pFlag = false;
							pErrInfo.getContent().insert(0, "新增 档案划控鉴定角色 失败：");
						}
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

		return pFlag;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IAppraisalUseScopesDetailManageService#updateAppraisalUseScopesDetail(com.orifound.aiim.entity.AppraisalUseScopesDetail, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateAppraisalUseScopesDetail(
			AppraisalUseScopesDetail appraisalUseScopesDetail, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean findAppraisalUseScopesDetailByByNBXH(int archivesTypeID, int NBXH, AppraisalUseScopesDetail appraisalUseScopesDetail, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForAppraisalUseScopesDetail(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检测内部序号是否为空
			if (pFlag) {
				if (NBXH <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->内部序号非法为空。");
				}
			}

			// 调用DAO
			if (pFlag) {
				pErrPos = 2;
				if (appraisalUseScopesDetailDao.findByByNBXH(archivesTypeID, NBXH, appraisalUseScopesDetail, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Service 根据档案内部序号查找档案划分控制鉴定明细情况表的实体类信息 失败：");
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
	public boolean findWithPage(Map<String, String> params,
			DataPageInfo dataPageInfo,
			List<AppraisalUseScopesDetail> appraisalUseScopesDetails, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForAppraisalUseScopesDetail(pErrInfo) == false) {
				pFlag = false;
			}
			
			//判断分页参数是否为空
			if (pFlag) {
				if(dataPageInfo == null || dataPageInfo.getPageSize()<=0) {
					pFlag = false;
					pErrInfo.getContent().append("参数：分页类非法为空。");
				}
			}
			
			//判断存毁鉴定记录集合参数
			if (pFlag) {
				if (appraisalUseScopesDetails == null) {
					pFlag = false;
					pErrInfo.getContent().append("参数：划控鉴定记录集合为空。");
				}
			}
			
			//检测页面查询参数是否为空
			if (pFlag) {
				if (params == null) {
					pFlag = false;
					pErrInfo.getContent().append("参数：页面查询参数为空。");
				}
			}
			
			if (pFlag) {
				pErrPos = 2;
				//分页查询开放鉴定登记信息
				
				//判断是否有档案类型条件	查询该一级档案分类下的所有档案分类id
				String archivesTypeId = params.get("archivesTypeId");
				List<Integer> archivesTypeIds = null;
				if(StringTool.checkNull(archivesTypeId)) {
					archivesTypeIds = new ArrayList<Integer>();
					CommonUtil.getAllChildArchivesTypeId(archivesTypeIds, Integer.valueOf(""+archivesTypeId));
				}
				
				if (appraisalUseScopesDetailDao.findWithPage(archivesTypeIds, params, dataPageInfo, appraisalUseScopesDetails, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "分页查询开放鉴定登记信息 失败：");
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
	public boolean findRoleNamesById(int pId, List<String> roleNames,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForAppraisalUseScopesDetail(pErrInfo) == false) {
				pFlag = false;
			}

			//鉴定管理：划控鉴定信息 根据id获取授权的所有角色名称
			if (pFlag) {
				pErrPos = 2;
				if (appraisalUseScopesDetailDao.findRoleNamesById(pId, roleNames, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Service 鉴定管理：划控鉴定信息 根据id获取授权的所有角色名称 失败：");
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
