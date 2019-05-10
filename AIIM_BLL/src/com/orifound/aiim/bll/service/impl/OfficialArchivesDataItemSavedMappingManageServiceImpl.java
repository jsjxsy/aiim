package com.orifound.aiim.bll.service.impl;

import java.util.Map;


import com.orifound.aiim.bll.service.IOfficialArchivesDataItemSavedMappingManageService;
import com.orifound.aiim.dal.dao.IOfficialArchivesDataItemSavedMappingDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.OfficialArchivesDataItemSavedMapping;

public class OfficialArchivesDataItemSavedMappingManageServiceImpl  implements
		IOfficialArchivesDataItemSavedMappingManageService {
	
	/**
	 * 构造函数
	 */
	public OfficialArchivesDataItemSavedMappingManageServiceImpl() {

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public OfficialArchivesDataItemSavedMappingManageServiceImpl(IOfficialArchivesDataItemSavedMappingDao officialArchivesDataItemSavedMappingDao) {
		this.officialArchivesDataItemSavedMappingDao = officialArchivesDataItemSavedMappingDao;
	}
	
	/**
	 * 检查OfficialArchivesDataItemSavedMapping的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForOfficialArchivesDataItemSavedMapping(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (officialArchivesDataItemSavedMappingDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("OfficialArchivesDataItemSavedMapping的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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
	
	/**
	 * 公文档案数据项的信息映射表的数据访问对象
	 */
	private IOfficialArchivesDataItemSavedMappingDao officialArchivesDataItemSavedMappingDao = null;

	/**
	 * 获取属性值：公文档案数据项的信息映射表的数据访问对象
	 * @return 公文档案数据项的信息映射表的数据访问对象
	 */
	public IOfficialArchivesDataItemSavedMappingDao getOfficialArchivesDataItemSavedMappingDao() {
		return officialArchivesDataItemSavedMappingDao;
	}

	/**
	 * 设置属性值：公文档案数据项的信息映射表的数据访问对象
	 * @param officialArchivesDataItemSavedMappingDao 公文档案数据项的信息映射表的数据访问对象
	 */
	public void setOfficialArchivesDataItemSavedMappingDao(IOfficialArchivesDataItemSavedMappingDao officialArchivesDataItemSavedMappingDao) {
		this.officialArchivesDataItemSavedMappingDao = officialArchivesDataItemSavedMappingDao;
	}
	
	@Override
	public boolean deleteOfficialArchivesDataItemSavedMapping(
			OfficialArchivesDataItemSavedMapping officialArchivesDataItemSavedMapping,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findOfficialArchivesDataItemSavedMappingByID(
			int pID,
			OfficialArchivesDataItemSavedMapping officialArchivesDataItemSavedMapping,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 查询所有的公文档案数据项保存映射
	 */
	public boolean findByArchivesTypeSavedMappingID(Integer archivesTypeSavedMappingID , Map<Integer,OfficialArchivesDataItemSavedMapping> officialArchivesDataItemSavedMappings, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
		    //检查是否有进行DAO依赖注入
			if (checkDaoInjectionForOfficialArchivesDataItemSavedMapping(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0,"OfficialArchivesDataItemSavedMappingManageService的DAO依赖注入失败!");
			}

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if(officialArchivesDataItemSavedMappingDao.findByArchivesTypeSavedMappingID(archivesTypeSavedMappingID, officialArchivesDataItemSavedMappings, pErrInfo)==false){
					pFlag=false;
					pErrInfo.getContent().insert(0,"获取指定公文档案分类映射关系对应的所有数据项的归档关系映射信息失败：");
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

	@Override
	public boolean saveOfficialArchivesDataItemSavedMapping(
			OfficialArchivesDataItemSavedMapping officialArchivesDataItemSavedMapping,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateOfficialArchivesDataItemSavedMapping(
			OfficialArchivesDataItemSavedMapping officialArchivesDataItemSavedMapping,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
	
		return false;
	}

}
