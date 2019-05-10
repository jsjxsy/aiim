/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.IAppraisalPublicDetailManageService;
import com.orifound.aiim.bll.util.CommonUtil;
import com.orifound.aiim.dal.dao.IAppraisalPublicDetailDao;
import com.orifound.aiim.bll.util.StringTool;
import com.orifound.aiim.entity.AppraisalPublicDetail;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserInfo;

/**
 * 档案公开/开放鉴定明细情况表的实体类管理服务类
 *
 */
public class AppraisalPublicDetailManageServiceImpl implements IAppraisalPublicDetailManageService {
	
	/**
	 * 注入档案开放/公开鉴定表DAO
	 */
	@Autowired
	private IAppraisalPublicDetailDao appraisalPublicDetailDao;
	
	/**
	 * 构造函数
	 */
	public AppraisalPublicDetailManageServiceImpl() {

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public AppraisalPublicDetailManageServiceImpl(IAppraisalPublicDetailDao appraisalPublicDetailDao) {
		this.appraisalPublicDetailDao = appraisalPublicDetailDao;
	}
	
	/**
	 * 检查档案开放/公开鉴定表的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForAppraisalPublicDetail(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (appraisalPublicDetailDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("档案开放/公开鉴定表的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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
	 * @see com.orifound.aiim.bll.service.IAppraisalPublicDetailManageService#deleteAppraisalPublicDetail(com.orifound.aiim.entity.AppraisalPublicDetail, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteAppraisalPublicDetail(
			AppraisalPublicDetail appraisalPublicDetail, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IAppraisalPublicDetailManageService#findAppraisalPublicDetailByID(int, com.orifound.aiim.entity.AppraisalPublicDetail, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findAppraisalPublicDetailByID(int pID, int publicFlag, AppraisalPublicDetail appraisalPublicDetail, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForAppraisalPublicDetail(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检测id是否为空
			if (pFlag) {
				if (pID<=0) {
					pFlag = false;
					pErrInfo.getContent().append("id主键非法为空。");
				}
			}
			
			//检测档案公开/开放鉴定明细情况表的实体对象是否为空
			if (pFlag) {
				if (appraisalPublicDetail == null) {
					pFlag = false;
					pErrInfo.getContent().append("档案公开/开放鉴定明细情况表的实体对象非法为空。");
				}
			}
			

			if (pFlag) {
				pErrPos = 2;
				//根据唯一标识查找档案开放/公开鉴定信息
				if (appraisalPublicDetailDao.findByID(pID, publicFlag, appraisalPublicDetail, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Service 根据唯一标识查找档案开放/公开鉴定信息 失败：");
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
	 * @see com.orifound.aiim.bll.service.IAppraisalPublicDetailManageService#findAppraisalPublicDetails(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findAppraisalPublicDetails(
			List<AppraisalPublicDetail> appraisalPublicDetails, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IAppraisalPublicDetailManageService#saveAppraisalPublicDetail(com.orifound.aiim.entity.AppraisalPublicDetail, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveAppraisalPublicDetail(
			AppraisalPublicDetail appraisalPublicDetail, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IAppraisalPublicDetailManageService#saveBatchForPublicAppraisal(com.orifound.aiim.entity.UserInfo, com.orifound.aiim.entity.ArchivesType, java.util.List, java.util.Map, java.lang.String, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveBatchForPublicAppraisal(UserInfo userInfo,
			ArchivesType archivesType, List<Integer> archivesNBXHs,
			Map<String, String> opinion, String isPublic, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForAppraisalPublicDetail(pErrInfo) == false) {
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
			
			//检测批量档案内部序号信息是否为空
			if (pFlag) {
				if (archivesNBXHs==null || archivesNBXHs.size()<=0) {
					pFlag = false;
					pErrInfo.getContent().append("批量档案内部序号信息非法为空。");
				}
			}
			
			if (pFlag) {
				pErrPos = 4;
				if ("0".equals(isPublic)) {
					//鉴定管理->开放鉴定 批量添加档案开放鉴定信息
					if (appraisalPublicDetailDao.saveBatchForPublicAppraisal(userInfo, archivesType, archivesNBXHs, opinion,  pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "Service 鉴定管理->开放鉴定 批量添加档案开放鉴定信息 失败：");
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
	 * @see com.orifound.aiim.bll.service.IAppraisalPublicDetailManageService#updateAppraisalPublicDetail(com.orifound.aiim.entity.AppraisalPublicDetail, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateAppraisalPublicDetail( AppraisalPublicDetail appraisalPublicDetail, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveBatchForOpenAppraisal(UserInfo userInfo,
			ArchivesType archivesType, List<Integer> archivesNBXHs,
			Map<String, String> opinion, String isOpen, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForAppraisalPublicDetail(pErrInfo) == false) {
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
			
			//检测批量档案内部序号信息是否为空
			if (pFlag) {
				if (archivesNBXHs==null || archivesNBXHs.size()<=0) {
					pFlag = false;
					pErrInfo.getContent().append("批量档案内部序号信息非法为空。");
				}
			}
			
			if (pFlag) {
				pErrPos = 4;
				
				if("0".equals(isOpen)) {
					//鉴定管理->公开鉴定 批量添加档案开放鉴定信息
					if (appraisalPublicDetailDao.saveBatchForOpenAppraisal(userInfo, archivesType, archivesNBXHs, opinion, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "Service 鉴定管理->公开鉴定 批量添加档案开放鉴定信息 失败：");
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

	@Override
	public boolean findWithPage(Map<String, String> params, DataPageInfo dataPageInfo,
			List<AppraisalPublicDetail> appraisalPublicDetails, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForAppraisalPublicDetail(pErrInfo) == false) {
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
				if (appraisalPublicDetails == null) {
					pFlag = false;
					pErrInfo.getContent().append("参数：开放鉴定记录集合为空。");
				}
			}
			
			//检测页面查询参数是否为空
			if (pFlag) {
				if (params == null) {
					pFlag = false;
					pErrInfo.getContent().append("参数：页面查询参数为空。");
				}
			}
			
			//检查开放标志是否为空
			if (pFlag) {
				String PublicFlag = params.get("PublicFlag");
				if (StringTool.checkNull(PublicFlag) == false || PublicFlag.matches("[0-1]{1}")==false) {
					pFlag = false;
					pErrInfo.getContent().append("页面查询参数：开放标志非法为空或者是0、1以外的值。");
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
				
				if (appraisalPublicDetailDao.findWithPage(archivesTypeIds, params, dataPageInfo, appraisalPublicDetails, pErrInfo) == false) {
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
	public boolean deleteAppraisalPublicDetail(ArchivesType archivesType, int NBXH, int publicFlag, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForAppraisalPublicDetail(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检查档案类型id
			if (pFlag) {
				if (archivesType==null || archivesType.getID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("档案类型非法为空！");
				}
			}
			
			//检查档案内部序号
			if (pFlag) {
				if (NBXH <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("档案内部序号非法为空！");
				}
			}
			
			//获取档案分类信息
			if (pFlag)
			{
				pErrPos = 2;
				
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

			if (pFlag) {
				pErrPos = 2;
				if (appraisalPublicDetailDao.delete(archivesType, NBXH, publicFlag, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "鉴定管理->取消公开鉴定 删除指定档案类型和内部序号的档案公开鉴定信息 失败：");
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
