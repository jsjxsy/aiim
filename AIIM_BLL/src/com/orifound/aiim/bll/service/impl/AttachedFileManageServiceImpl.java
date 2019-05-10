/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.text.SimpleDateFormat;
import java.util.*;

import com.orifound.aiim.bll.service.IAttachedFileManageService;
import com.orifound.aiim.bll.service.IConfigManageService;
import com.orifound.aiim.dal.dao.IArchivesInfoAttachedFileTypeCodeDao;
import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesInfoAttachedFile;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.Config;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 原文管理服务实现类
 *
 */
public class AttachedFileManageServiceImpl implements
		IAttachedFileManageService {

	
	/**
	 * 系统配置管理服务
	 */
	private IConfigManageService configManageService = null;
	
	public IConfigManageService getConfigManageService() {
		return configManageService;
	}
	
	public void setConfigManageService(IConfigManageService configManageService) {
		this.configManageService = configManageService;
	}

	/**
	 * 原文电子文件信息表的数据访问对象
	 */
	private IArchivesInfoAttachedFileTypeCodeDao archivesInfoAttachedFileTypeCodeDao = null;

	
	/**
	 * 获取属性值：原文电子文件信息表的数据访问对象
	 * @return 原文电子文件信息表的数据访问对象
	 */
	public IArchivesInfoAttachedFileTypeCodeDao getArchivesInfoAttachedFileTypeCodeDao() {
		return archivesInfoAttachedFileTypeCodeDao;
	}
	
	/**
	 * 设置属性值：原文电子文件信息表的数据访问对象
	 * @param attachedFileDao 原文电子文件信息表的数据访问对象
	 */
	public void setArchivesInfoAttachedFileTypeCodeDao(IArchivesInfoAttachedFileTypeCodeDao archivesInfoAttachedFileTypeCodeDao) {
		this.archivesInfoAttachedFileTypeCodeDao = archivesInfoAttachedFileTypeCodeDao;
	}
	
	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IAttachedFileManageService#deleteArchivesInfoAttachedFile(com.orifound.aiim.entity.ArchivesType, int, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteArchivesInfoAttachedFile(ArchivesType archivesType, int archivesInfoAttachedFileID, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查档案分类信息
			pErrPos = 1;
			if (archivesType == null ) {
				pFlag = false;
				pErrInfo.getContent().append("档案分类信息对象为空");
			}else if(archivesType.getID() == 0){
				pFlag = false;
				pErrInfo.getContent().append("档案分类信息对象编号非法为0");
			}

			if (pFlag) {
				if (archivesInfoAttachedFileID == 0) {
					pFlag = false;
					pErrInfo.getContent().append("原文信息编号非法为0");
				}
			}
			
			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (archivesInfoAttachedFileTypeCodeDao.deleteArchivesInfoAttachedFile(archivesType, archivesInfoAttachedFileID, pErrInfo) == false) {
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

	

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IAttachedFileManageService#deleteArchivesInfoAttachedFile(com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.ArchivesInfoAttachedFile, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteArchivesInfoAttachedFile(ArchivesType archivesType,ArchivesInfoAttachedFile archivesInfoAttachedFile, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return true;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IAttachedFileManageService#findArchivesInfoAttachedFiles(com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.ArchivesInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesInfoAttachedFiles(ArchivesType archivesType,ArchivesInfo archivesInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return true;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IAttachedFileManageService#findArchivesInfoAttachedFiles(com.orifound.aiim.entity.ArchivesType, int, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesInfoAttachedFiles(ArchivesType archivesType,int pNBXH,List<ArchivesInfoAttachedFile> archivesInfoAttachedFiles,ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查档案分类信息
			if (archivesType == null ) {
				pFlag = false;
				pErrInfo.getContent().append("档案分类信息对象为空");
			}else if(archivesType.getID() == 0){
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
				if (archivesInfoAttachedFiles == null) {
					pFlag = false;
					pErrInfo.getContent().append("要返回的原文电子档案信息集合对象没有赋值");
				}
			}
			
			//调用DAO查找原文电子档案信息
			if (pFlag) {
				pErrPos = 2;
				if (archivesInfoAttachedFileTypeCodeDao.findArchivesInfoAttachedFiles(archivesType, pNBXH, archivesInfoAttachedFiles, pErrInfo) == false) {
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IAttachedFileManageService#saveArchivesInfoAttachedFile(com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.ArchivesInfo, com.orifound.aiim.entity.ArchivesInfoAttachedFile, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean addArchivesInfoAttachedFile(ArchivesType archivesType,ArchivesInfo archivesInfo,ArchivesInfoAttachedFile archivesInfoAttachedFile, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return true;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IAttachedFileManageService#saveArchivesInfoAttachedFile(com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.ArchivesInfoAttachedFile, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean addArchivesInfoAttachedFile(ArchivesType archivesType,ArchivesInfoAttachedFile archivesInfoAttachedFile, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查档案分类信息
			pErrPos = 1;
			if (archivesType == null ) {
				pFlag = false;
				pErrInfo.getContent().append("档案分类信息对象为空");
			}else if(archivesType.getID() == 0){
				pFlag = false;
				pErrInfo.getContent().append("档案分类信息对象编号非法为0");
			}
			
			//检查原文电子文件信息
			if (archivesInfoAttachedFile == null ) {
				pFlag = false;
				pErrInfo.getContent().append("要新增的原文电子文件信息对象为空");
			}else if(archivesInfoAttachedFile.getNBXH() == 0){
				pFlag = false;
				pErrInfo.getContent().append("要新增的原文电子文件信息所属的内部序号非法为0");
			}
			
			//调用dao查找原文信息是否存在
			if (pFlag) {
				pErrPos = 2;
				if (archivesInfoAttachedFileTypeCodeDao.findArchivesInfoAttachedFileByName(archivesType, archivesInfoAttachedFile, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"按文件名原文电子档案信息失败");
				}
			}

			//调用dao新增原文
			if (pFlag) {
				pErrPos = 3;
				if(archivesInfoAttachedFile.getID() == 0){
					if (archivesInfoAttachedFileTypeCodeDao.addArchivesInfoAttachedFile(archivesType, archivesInfoAttachedFile, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0,"添加原文电子档案信息失败");
					}
				}else{
					pFlag = false;
					pErrInfo.getContent().append("此文件已存在，重新上传请先删除原有文件");
				}
				/*else{
					if (archivesInfoAttachedFileTypeCodeDao.updateArchivesInfoAttachedFile(archivesType, archivesInfoAttachedFile, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().append("更新原文电子档案信息失败");
					}
				}*/
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IAttachedFileManageService#sortArchivesInfoAttachedFiles(com.orifound.aiim.entity.ArchivesType, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean sortArchivesInfoAttachedFiles(ArchivesType archivesType,List<ArchivesInfoAttachedFile> archivesInfoAttachedFiles,ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return true;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IAttachedFileManageService#updateArchivesInfoAttachedFile(com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.ArchivesInfoAttachedFile, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateArchivesInfoAttachedFile(ArchivesType archivesType,ArchivesInfoAttachedFile archivesInfoAttachedFile, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean findArchivesInfoAttachedFileById(ArchivesType archivesType, ArchivesInfoAttachedFile archivesInfoAttachedFile, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查档案分类信息
			pErrPos = 1;
			if (archivesType == null ) {
				pFlag = false;
				pErrInfo.getContent().append("档案分类信息对象为空");
			}else if(archivesType.getID() == 0){
				pFlag = false;
				pErrInfo.getContent().append("档案分类信息对象编号非法为0");
			}
			
			//检查原文电子文件信息
			if (archivesInfoAttachedFile == null ) {
				pFlag = false;
				pErrInfo.getContent().append("要查找的原文电子文件信息对象为空");
			}else if(archivesInfoAttachedFile.getID() == 0){
				pFlag = false;
				pErrInfo.getContent().append("要查找的原文电子文件信息对编号非法为0");
			}
			
			//调用dao查找原文电子文件信息
			if (pFlag) {
				pErrPos = 2;
				if (archivesInfoAttachedFileTypeCodeDao.findArchivesInfoAttachedFileById(archivesType, archivesInfoAttachedFile, pErrInfo) == false) {
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
	public boolean getSavedArchivesInfoAttachedFilesSavePath(ArchivesType archivesType, List<ArchivesInfoAttachedFile> archivesInfoAttachedFiles, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		List<Config> pConfigs = new ArrayList<Config>();		
		
		try {
			pErrPos = 1;
			if(archivesInfoAttachedFiles==null){
				pFlag = false;
				pErrInfo.getContent().append("电子集合非法为空！");
			}
			
			if(archivesType==null){
				pFlag = false;
				pErrInfo.getContent().append("档案分类信息非法为空！");
			}
			
			//从配置文件中获取原文电子文件的根目录
			if (pFlag) {
				pErrPos = 2;
				if(configManageService.findConfigByConfigType("AttachedFileReSavedForUsePath", pConfigs, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "从配置文件中获取原文电子文件的根目录失败：");
				}else{
					if(pConfigs.size()<1){
						pFlag = false;
						pErrInfo.getContent().append("配置文件没有配置原文电子文件的根目录！");
					}
				}
			}
			
			//设置原文电子文件的存储路径
			if (pFlag) {
				pErrPos = 3;				
				for (ArchivesInfoAttachedFile archivesInfoAttachedFile : archivesInfoAttachedFiles) {
					String attachedFileReSavedForUsePath = "";//原文电子文件的路径
					//1：取根目录
					for (Config config : pConfigs) {
						Date maxDate = new SimpleDateFormat("yyyyMMdd").parse(config.getMaxValue());
						Date minDate =  new SimpleDateFormat("yyyyMMdd").parse(config.getMinValue());
						if(archivesInfoAttachedFile.getResaveTime().after(minDate) && archivesInfoAttachedFile.getResaveTime().before(maxDate)){
							attachedFileReSavedForUsePath = config.getConfigValue();
						}
					}	
					//取文件的相对目录
					if ("".equals(attachedFileReSavedForUsePath)) {
						pFlag = false;
						pErrInfo.getContent().append("没有找到原文电子文件的根目录！");
					}else{
						//2：二级目录 :分类/时间/1_78_G01-2010-DQ11-27.pdf
						attachedFileReSavedForUsePath += archivesType.getAttachedFileSavePath() ;
						attachedFileReSavedForUsePath += "\\" + new SimpleDateFormat("yyyyMMdd").format(archivesInfoAttachedFile.getResaveTime());
						attachedFileReSavedForUsePath += "\\" + archivesInfoAttachedFile.getResaveFileName();
						//将文件的路经设置到原文信息对象的tag属性
						archivesInfoAttachedFile.setTag(attachedFileReSavedForUsePath);
System.out.println(archivesInfoAttachedFile.getTag());
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
