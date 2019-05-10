package com.orifound.aiim.bll.service.impl;

import java.util.List;


import com.orifound.aiim.bll.service.IOfficialArchivesInfoAttachedFileManageService;
import com.orifound.aiim.dal.dao.IOfficialArchivesInfoAttachedFileDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.OfficialArchivesInfoAttachedFile;
import com.orifound.aiim.entity.OfficialArchivesType;

public class OfficialArchivesInfoAttachedFileManageServiceImpl 
		 implements
		IOfficialArchivesInfoAttachedFileManageService {
	/**
	 * 公文档案原文信息表的数据访问对象
	 */
	private IOfficialArchivesInfoAttachedFileDao officialArchivesInfoAttachedFileDao = null;

	/**
	 * 获取属性值：公文档案原文信息表的数据访问对象
	 * @return 公文档案原文信息表的数据访问对象
	 */
	public IOfficialArchivesInfoAttachedFileDao getOfficialArchivesInfoAttachedFileDao() {
		return officialArchivesInfoAttachedFileDao;
	}

	/**
	 * 设置属性值：公文档案原文信息表的数据访问对象
	 * @param officialArchivesInfoAttachedFileDao 公文档案原文信息表的数据访问对象
	 */
	public void setOfficialArchivesInfoAttachedFileDao(IOfficialArchivesInfoAttachedFileDao officialArchivesInfoAttachedFileDao) {
		this.officialArchivesInfoAttachedFileDao = officialArchivesInfoAttachedFileDao;
	}
	/**
	 * 构造函数
	 */
	public OfficialArchivesInfoAttachedFileManageServiceImpl() {	

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public OfficialArchivesInfoAttachedFileManageServiceImpl(IOfficialArchivesInfoAttachedFileDao officialArchivesInfoAttachedFileDao) {
		this.officialArchivesInfoAttachedFileDao = officialArchivesInfoAttachedFileDao;
	}
	@Override
	public boolean deleteOfficialArchivesInfoAttachedFile(OfficialArchivesType officialArchivesType,
			int officialArchivesInfoAttachedFileID,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查档案分类信息
			pErrPos = 1;
			if (officialArchivesType == null ) {
				pFlag = false;
				pErrInfo.getContent().append("档案分类信息对象为空");
			}else if(officialArchivesType.getID() == 0){
				pFlag = false;
				pErrInfo.getContent().append("档案分类信息对象编号非法为0");
			}

			if (pFlag) {
				if (officialArchivesInfoAttachedFileID == 0) {
					pFlag = false;
					pErrInfo.getContent().append("原文信息编号非法为0");
				}
			}
			
			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (officialArchivesInfoAttachedFileDao.delete(officialArchivesType, officialArchivesInfoAttachedFileID, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"删除原文电子档案信息失败");
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
	public boolean findOfficialArchivesInfoAttachedFileByID(OfficialArchivesType officialArchivesType,
			OfficialArchivesInfoAttachedFile officialArchivesInfoAttachedFile,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查档案分类信息
			pErrPos = 1;
			if (officialArchivesType == null ) {
				pFlag = false;
				pErrInfo.getContent().append("档案分类信息对象为空");
			}else if(officialArchivesType.getID() == 0){
				pFlag = false;
				pErrInfo.getContent().append("档案分类信息对象编号非法为0");
			}
			
			//检查原文电子文件信息
			if (officialArchivesInfoAttachedFile == null ) {
				pFlag = false;
				pErrInfo.getContent().append("要查找的原文电子文件信息对象为空");
			}else if(officialArchivesInfoAttachedFile.getID() == 0){
				pFlag = false;
				pErrInfo.getContent().append("要查找的原文电子文件信息对编号非法为0");
			}
			
			//调用dao查找原文电子文件信息
			if (pFlag) {
				pErrPos = 2;
				if (officialArchivesInfoAttachedFileDao.findByID(officialArchivesType, officialArchivesInfoAttachedFile, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"按ID原文电子档案信息失败");
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
	public boolean findOfficialArchivesInfoAttachedFiles(OfficialArchivesType officialArchivesType,
			int pNBXH,
			List<OfficialArchivesInfoAttachedFile> officialArchivesInfoAttachedFiles,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查档案分类信息
			if (officialArchivesType == null ) {
				pFlag = false;
				pErrInfo.getContent().append("档案分类信息对象为空");
			}else if(officialArchivesType.getID() == 0){
				pFlag = false;
				pErrInfo.getContent().append("档案分类信息对象编号非法为0");
			}
			
			if (pFlag) {
				if (pNBXH == 0) {
					pFlag = false;
					pErrInfo.getContent().append("原文所属档案的内部序号非法为0");
				}
			}
			
			if (pFlag) {
				if (officialArchivesInfoAttachedFiles == null) {
					pFlag = false;
					pErrInfo.getContent().append("要返回的原文电子档案信息集合对象没有赋值");
				}
			}
			
			//调用DAO查找原文电子档案信息
			if (pFlag) {
				pErrPos = 2;
				if (officialArchivesInfoAttachedFileDao.findAll(officialArchivesType, pNBXH, officialArchivesInfoAttachedFiles, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"查找原文电子档案信息失败");
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
	public boolean saveOfficialArchivesInfoAttachedFile(OfficialArchivesType officialArchivesType,
			OfficialArchivesInfoAttachedFile officialArchivesInfoAttachedFile,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查档案分类信息
			pErrPos = 1;
			if (officialArchivesType == null ) {
				pFlag = false;
				pErrInfo.getContent().append("档案分类信息对象为空");
			}else if(officialArchivesType.getID() == 0){
				pFlag = false;
				pErrInfo.getContent().append("档案分类信息对象编号非法为0");
			}
			
			//检查原文电子文件信息
			if (officialArchivesInfoAttachedFile == null ) {
				pFlag = false;
				pErrInfo.getContent().append("要新增的原文电子文件信息对象为空");
			}else if(officialArchivesInfoAttachedFile.getNBXH() == 0){
				pFlag = false;
				pErrInfo.getContent().append("要新增的原文电子文件信息所属的内部序号非法为0");
			}
			
			//调用dao查找原文信息是否存在
			if (pFlag) {
				pErrPos = 2;
				if (officialArchivesInfoAttachedFileDao.findOfficialArchivesInfoAttachedFileByName(officialArchivesType, officialArchivesInfoAttachedFile, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"按文件名原文电子档案信息失败");
				}
			}

			//调用dao新增原文
			if (pFlag) {
				pErrPos = 3;
				if(officialArchivesInfoAttachedFile.getID() == 0){
					if (officialArchivesInfoAttachedFileDao.save(officialArchivesType, officialArchivesInfoAttachedFile, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0,"添加原文电子档案信息失败");
					}
				}else{
					pFlag = false;
					pErrInfo.getContent().append("此文件已存在，重新上传请先删除原有文件");
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
	public boolean updateOfficialArchivesInfoAttachedFile(
			OfficialArchivesInfoAttachedFile officialArchivesInfoAttachedFile,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

}
