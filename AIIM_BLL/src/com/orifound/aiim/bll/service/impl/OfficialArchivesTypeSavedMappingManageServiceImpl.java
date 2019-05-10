package com.orifound.aiim.bll.service.impl;

import java.util.List;
import java.util.Map;

import com.orifound.aiim.bll.service.IOfficialArchivesTypeSavedMappingManageService;
import com.orifound.aiim.dal.dao.IOfficialArchivesTypeSavedMappingDao;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.OfficialArchivesTypeSavedMapping;

public class OfficialArchivesTypeSavedMappingManageServiceImpl implements IOfficialArchivesTypeSavedMappingManageService{
	
	/**
	 * 构造函数
	 */
	public OfficialArchivesTypeSavedMappingManageServiceImpl() {

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public OfficialArchivesTypeSavedMappingManageServiceImpl(IOfficialArchivesTypeSavedMappingDao officialArchivesTypeSavedMappingDao) {
		this.officialArchivesTypeSavedMappingDao = officialArchivesTypeSavedMappingDao;
	}
	
	/**
	 * 公文档案分类的信息映射表的数据访问对象
	 */
	private IOfficialArchivesTypeSavedMappingDao officialArchivesTypeSavedMappingDao = null;

	/**
	 * 获取属性值：公文档案分类的信息映射表的数据访问对象
	 * @return 公文档案分类的信息映射表的数据访问对象
	 */
	public IOfficialArchivesTypeSavedMappingDao getOfficialArchivesTypeSavedMappingDao() {
		return officialArchivesTypeSavedMappingDao;
	}
	
	
	/**
	 * 设置属性值：公文档案分类的信息映射表的数据访问对象
	 * @param officialArchivesTypeSavedMappingDao 公文档案分类的信息映射表的数据访问对象
	 */
	public void setOfficialArchivesTypeSavedMappingDao(IOfficialArchivesTypeSavedMappingDao officialArchivesTypeSavedMappingDao) {
		this.officialArchivesTypeSavedMappingDao = officialArchivesTypeSavedMappingDao;
	}
	
	/**
	 * 检查officialArchivesTypeSavedMappingDao的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForOfficialArchivesTypeSaveMapping(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (officialArchivesTypeSavedMappingDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("officialArchivesTypeSavedMappingDao的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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
	
	@Override
	public boolean deleteOfficialArchivesTypeSavedMapping(
			OfficialArchivesTypeSavedMapping officialArchivesTypeSavedMapping,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findOfficialArchivesTypeSavedMappingByID(int pID,
			OfficialArchivesTypeSavedMapping officialArchivesTypeSavedMapping,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public 	boolean findOfficialArchivesTypeSavedMappings(Map<Integer,OfficialArchivesTypeSavedMapping> officialArchivesTypeSavedMappings,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveOfficialArchivesTypeSavedMapping(
			OfficialArchivesTypeSavedMapping officialArchivesTypeSavedMapping,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateOfficialArchivesTypeSavedMapping(
			OfficialArchivesTypeSavedMapping officialArchivesTypeSavedMapping,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 根据公文档案类型编号查询出档案类型集合
	 */
	public boolean findArchivesTypesByOfficialArchivesTypeID(int officialArchivesTypeID, Map<Integer,OfficialArchivesTypeSavedMapping> officialArchivesTypeSavedMappings, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (this.checkDaoInjectionForOfficialArchivesTypeSaveMapping(pErrInfo)==false) {
				pFlag = false;
				pErrInfo.getContent().append("officialArchivesTypeSavedMappingDao的DAO非法为空，请检查是否有进行依赖注入或赋值。");
			}
			
			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if(officialArchivesTypeID == 0){
					pFlag = false;
					pErrInfo.getContent().append("档案类型编号非法为空.");
				}
			}
			
			//获取公文档案类型归档映射关系
			if (pFlag) {
				pErrPos = 3;
				if(officialArchivesTypeSavedMappingDao.findByOfficialArchivesTypeID(officialArchivesTypeID, officialArchivesTypeSavedMappings, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().append("获取公文档案类型归档映射关系失败： ");
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
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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
