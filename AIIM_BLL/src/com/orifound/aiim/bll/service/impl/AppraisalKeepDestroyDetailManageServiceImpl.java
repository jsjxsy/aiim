/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.IAppraisalKeepDestroyDetailManageService;
import com.orifound.aiim.bll.util.CommonUtil;
import com.orifound.aiim.dal.dao.IAppraisalKeepDestroyDetailDao;
import com.orifound.aiim.dal.util.StringTool;
import com.orifound.aiim.entity.AppraisalKeepDestroyDetail;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserInfo;

/**
 * 存毁鉴定明细情况管理服务实现类
 * @author tyb
 *
 */
public class AppraisalKeepDestroyDetailManageServiceImpl implements IAppraisalKeepDestroyDetailManageService {
	
	/**
	 * 注入存毁鉴定明细情况表的DAO
	 */
	@Autowired
	private IAppraisalKeepDestroyDetailDao appraisalKeepDestroyDetailDao;
	
	/**
	 * 构造函数
	 */
	public AppraisalKeepDestroyDetailManageServiceImpl() {

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public AppraisalKeepDestroyDetailManageServiceImpl(IAppraisalKeepDestroyDetailDao appraisalKeepDestroyDetailDao) {
		this.appraisalKeepDestroyDetailDao = appraisalKeepDestroyDetailDao;
	}
	
	/**
	 * 检查存毁鉴定明细情况表的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForAppraisalKeepDestroyDetail(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (appraisalKeepDestroyDetailDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("存毁鉴定明细情况表的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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
	 * @see com.orifound.aiim.bll.service.IAppraisalKeepDestroyDetailManageService#deleteAppraisalKeepDestroyDetail(com.orifound.aiim.entity.AppraisalKeepDestroyDetail, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteAppraisalKeepDestroyDetail(
			AppraisalKeepDestroyDetail appraisalKeepDestroyDetail,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IAppraisalKeepDestroyDetailManageService#findAppraisalKeepDestroyDetailByID(int, com.orifound.aiim.entity.AppraisalKeepDestroyDetail, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findAppraisalKeepDestroyDetailByID(int pID, AppraisalKeepDestroyDetail appraisalKeepDestroyDetail, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForAppraisalKeepDestroyDetail(pErrInfo) == false) {
				pFlag = false;
			}
			
			//DoSomething
			if (pFlag) {
				if (appraisalKeepDestroyDetail == null) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "id非法为空。");
				}
			}
			
			//检查id是否为空
			if (pFlag) {
				if (pID <=0 ) {
					pFlag = false;
					pErrInfo.getContent().append("id非法为空。");
				}
			}

			if (pFlag) {
				pErrPos = 2;
				//据唯一标识查找存毁鉴定明细情况
				if (appraisalKeepDestroyDetailDao.findByID(pID, appraisalKeepDestroyDetail, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "据唯一标识查找存毁鉴定明细情况 失败：");
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
	 * @see com.orifound.aiim.bll.service.IAppraisalKeepDestroyDetailManageService#findAppraisalKeepDestroyDetails(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findAppraisalKeepDestroyDetails(
			List<AppraisalKeepDestroyDetail> appraisalKeepDestroyDetails,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IAppraisalKeepDestroyDetailManageService#saveAppraisalKeepDestroyDetail(com.orifound.aiim.entity.AppraisalKeepDestroyDetail, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveAppraisalKeepDestroyDetail(
			AppraisalKeepDestroyDetail appraisalKeepDestroyDetail,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IAppraisalKeepDestroyDetailManageService#updateAppraisalKeepDestroyDetail(com.orifound.aiim.entity.AppraisalKeepDestroyDetail, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateAppraisalKeepDestroyDetail(
			AppraisalKeepDestroyDetail appraisalKeepDestroyDetail,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveBatch(UserInfo userInfo, ArchivesType archivesType, Map<Integer, Map<String, String>> batchArchives, Map<String, String> opinion, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForAppraisalKeepDestroyDetail(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检测用户信息是否为空
			if (pFlag) {
				if (userInfo == null || userInfo.getUserID()<=0) {
					pFlag = false;
					pErrInfo.getContent().append("用户信息非法为空。");
				}
			}
			
			//检查档案类型是否有赋值
			if (pFlag)
			{
				pErrPos = 2;
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息未初始化。");
				}else {
					if (archivesType.getID()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案分类编号非法为0");
					}
				}
			}
			
			//获取档案分类信息
			if (pFlag)
			{
				pErrPos = 3;
				if (CommonUtil.getSystemInitializer().getPlaneArchivesTypes().containsKey(archivesType.getID())==false)
				{
					pFlag = false;
					pErrInfo.getContent().append("系统中不存在编号为 "+archivesType.getID()+" 的档案分类");
				}
				else
				{
					//更新档案分类的引用
					archivesType=CommonUtil.getSystemInitializer().getPlaneArchivesTypes().get(archivesType.getID());
				}
			}
			
			//检测批量档案信息是否为空
			if (pFlag) {
				if (batchArchives==null || batchArchives.size()<=0) {
					pFlag = false;
					pErrInfo.getContent().append("批量档案信息非法为空。");
				}
			}

			if (pFlag) {
				pErrPos = 4;
				if (appraisalKeepDestroyDetailDao.saveBatch(userInfo, archivesType, batchArchives, opinion, pErrInfo) == false ) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "批量添加存毁鉴定明细情况 失败：");
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
			List<AppraisalKeepDestroyDetail> appraisalKeepDestroyDetails,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForAppraisalKeepDestroyDetail(pErrInfo) == false) {
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
				if (appraisalKeepDestroyDetails == null) {
					pFlag = false;
					pErrInfo.getContent().append("参数：存毁鉴定记录集合为空。");
				}
			}

			if (pFlag) {
				pErrPos = 2;
				//分页查询存毁鉴定登记信息
				
				//判断是否有档案类型条件	查询该一级档案分类下的所有档案分类id
				String archivesTypeId = params.get("archivesTypeId");
				List<Integer> archivesTypeIds = null;
				if(StringTool.checkNull(archivesTypeId)) {
					archivesTypeIds = new ArrayList<Integer>();
					CommonUtil.getAllChildArchivesTypeId(archivesTypeIds, Integer.valueOf(""+archivesTypeId));
				}
				
				if (appraisalKeepDestroyDetailDao.findWithPage(archivesTypeIds, params, dataPageInfo, appraisalKeepDestroyDetails, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "分页查询存毁鉴定登记信息 失败：");
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
