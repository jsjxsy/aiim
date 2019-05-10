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
 * ԭ�Ĺ������ʵ����
 *
 */
public class AttachedFileManageServiceImpl implements
		IAttachedFileManageService {

	
	/**
	 * ϵͳ���ù������
	 */
	private IConfigManageService configManageService = null;
	
	public IConfigManageService getConfigManageService() {
		return configManageService;
	}
	
	public void setConfigManageService(IConfigManageService configManageService) {
		this.configManageService = configManageService;
	}

	/**
	 * ԭ�ĵ����ļ���Ϣ������ݷ��ʶ���
	 */
	private IArchivesInfoAttachedFileTypeCodeDao archivesInfoAttachedFileTypeCodeDao = null;

	
	/**
	 * ��ȡ����ֵ��ԭ�ĵ����ļ���Ϣ������ݷ��ʶ���
	 * @return ԭ�ĵ����ļ���Ϣ������ݷ��ʶ���
	 */
	public IArchivesInfoAttachedFileTypeCodeDao getArchivesInfoAttachedFileTypeCodeDao() {
		return archivesInfoAttachedFileTypeCodeDao;
	}
	
	/**
	 * ��������ֵ��ԭ�ĵ����ļ���Ϣ������ݷ��ʶ���
	 * @param attachedFileDao ԭ�ĵ����ļ���Ϣ������ݷ��ʶ���
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
			//��鵵��������Ϣ
			pErrPos = 1;
			if (archivesType == null ) {
				pFlag = false;
				pErrInfo.getContent().append("����������Ϣ����Ϊ��");
			}else if(archivesType.getID() == 0){
				pFlag = false;
				pErrInfo.getContent().append("����������Ϣ�����ŷǷ�Ϊ0");
			}

			if (pFlag) {
				if (archivesInfoAttachedFileID == 0) {
					pFlag = false;
					pErrInfo.getContent().append("ԭ����Ϣ��ŷǷ�Ϊ0");
				}
			}
			
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (archivesInfoAttachedFileTypeCodeDao.deleteArchivesInfoAttachedFile(archivesType, archivesInfoAttachedFileID, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"ɾ��ԭ�ĵ��ӵ�����Ϣʧ��");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//���پֲ�����
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
			//��鵵��������Ϣ
			if (archivesType == null ) {
				pFlag = false;
				pErrInfo.getContent().append("����������Ϣ����Ϊ��");
			}else if(archivesType.getID() == 0){
				pFlag = false;
				pErrInfo.getContent().append("����������Ϣ�����ŷǷ�Ϊ0");
			}
			
			if (pFlag) {
				if (pNBXH == 0) {
					pFlag = false;
					pErrInfo.getContent().append("ԭ�������������ڲ���ŷǷ�Ϊ0");
				}
			}
			
			if (pFlag) {
				if (archivesInfoAttachedFiles == null) {
					pFlag = false;
					pErrInfo.getContent().append("Ҫ���ص�ԭ�ĵ��ӵ�����Ϣ���϶���û�и�ֵ");
				}
			}
			
			//����DAO����ԭ�ĵ��ӵ�����Ϣ
			if (pFlag) {
				pErrPos = 2;
				if (archivesInfoAttachedFileTypeCodeDao.findArchivesInfoAttachedFiles(archivesType, pNBXH, archivesInfoAttachedFiles, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"����ԭ�ĵ��ӵ�����Ϣʧ��");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//���پֲ�����
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
			//��鵵��������Ϣ
			pErrPos = 1;
			if (archivesType == null ) {
				pFlag = false;
				pErrInfo.getContent().append("����������Ϣ����Ϊ��");
			}else if(archivesType.getID() == 0){
				pFlag = false;
				pErrInfo.getContent().append("����������Ϣ�����ŷǷ�Ϊ0");
			}
			
			//���ԭ�ĵ����ļ���Ϣ
			if (archivesInfoAttachedFile == null ) {
				pFlag = false;
				pErrInfo.getContent().append("Ҫ������ԭ�ĵ����ļ���Ϣ����Ϊ��");
			}else if(archivesInfoAttachedFile.getNBXH() == 0){
				pFlag = false;
				pErrInfo.getContent().append("Ҫ������ԭ�ĵ����ļ���Ϣ�������ڲ���ŷǷ�Ϊ0");
			}
			
			//����dao����ԭ����Ϣ�Ƿ����
			if (pFlag) {
				pErrPos = 2;
				if (archivesInfoAttachedFileTypeCodeDao.findArchivesInfoAttachedFileByName(archivesType, archivesInfoAttachedFile, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"���ļ���ԭ�ĵ��ӵ�����Ϣʧ��");
				}
			}

			//����dao����ԭ��
			if (pFlag) {
				pErrPos = 3;
				if(archivesInfoAttachedFile.getID() == 0){
					if (archivesInfoAttachedFileTypeCodeDao.addArchivesInfoAttachedFile(archivesType, archivesInfoAttachedFile, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0,"���ԭ�ĵ��ӵ�����Ϣʧ��");
					}
				}else{
					pFlag = false;
					pErrInfo.getContent().append("���ļ��Ѵ��ڣ������ϴ�����ɾ��ԭ���ļ�");
				}
				/*else{
					if (archivesInfoAttachedFileTypeCodeDao.updateArchivesInfoAttachedFile(archivesType, archivesInfoAttachedFile, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().append("����ԭ�ĵ��ӵ�����Ϣʧ��");
					}
				}*/
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//���پֲ�����
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
			//��鵵��������Ϣ
			pErrPos = 1;
			if (archivesType == null ) {
				pFlag = false;
				pErrInfo.getContent().append("����������Ϣ����Ϊ��");
			}else if(archivesType.getID() == 0){
				pFlag = false;
				pErrInfo.getContent().append("����������Ϣ�����ŷǷ�Ϊ0");
			}
			
			//���ԭ�ĵ����ļ���Ϣ
			if (archivesInfoAttachedFile == null ) {
				pFlag = false;
				pErrInfo.getContent().append("Ҫ���ҵ�ԭ�ĵ����ļ���Ϣ����Ϊ��");
			}else if(archivesInfoAttachedFile.getID() == 0){
				pFlag = false;
				pErrInfo.getContent().append("Ҫ���ҵ�ԭ�ĵ����ļ���Ϣ�Ա�ŷǷ�Ϊ0");
			}
			
			//����dao����ԭ�ĵ����ļ���Ϣ
			if (pFlag) {
				pErrPos = 2;
				if (archivesInfoAttachedFileTypeCodeDao.findArchivesInfoAttachedFileById(archivesType, archivesInfoAttachedFile, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"��IDԭ�ĵ��ӵ�����Ϣʧ��");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
			//���پֲ�����
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
				pErrInfo.getContent().append("���Ӽ��ϷǷ�Ϊ�գ�");
			}
			
			if(archivesType==null){
				pFlag = false;
				pErrInfo.getContent().append("����������Ϣ�Ƿ�Ϊ�գ�");
			}
			
			//�������ļ��л�ȡԭ�ĵ����ļ��ĸ�Ŀ¼
			if (pFlag) {
				pErrPos = 2;
				if(configManageService.findConfigByConfigType("AttachedFileReSavedForUsePath", pConfigs, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "�������ļ��л�ȡԭ�ĵ����ļ��ĸ�Ŀ¼ʧ�ܣ�");
				}else{
					if(pConfigs.size()<1){
						pFlag = false;
						pErrInfo.getContent().append("�����ļ�û������ԭ�ĵ����ļ��ĸ�Ŀ¼��");
					}
				}
			}
			
			//����ԭ�ĵ����ļ��Ĵ洢·��
			if (pFlag) {
				pErrPos = 3;				
				for (ArchivesInfoAttachedFile archivesInfoAttachedFile : archivesInfoAttachedFiles) {
					String attachedFileReSavedForUsePath = "";//ԭ�ĵ����ļ���·��
					//1��ȡ��Ŀ¼
					for (Config config : pConfigs) {
						Date maxDate = new SimpleDateFormat("yyyyMMdd").parse(config.getMaxValue());
						Date minDate =  new SimpleDateFormat("yyyyMMdd").parse(config.getMinValue());
						if(archivesInfoAttachedFile.getResaveTime().after(minDate) && archivesInfoAttachedFile.getResaveTime().before(maxDate)){
							attachedFileReSavedForUsePath = config.getConfigValue();
						}
					}	
					//ȡ�ļ������Ŀ¼
					if ("".equals(attachedFileReSavedForUsePath)) {
						pFlag = false;
						pErrInfo.getContent().append("û���ҵ�ԭ�ĵ����ļ��ĸ�Ŀ¼��");
					}else{
						//2������Ŀ¼ :����/ʱ��/1_78_G01-2010-DQ11-27.pdf
						attachedFileReSavedForUsePath += archivesType.getAttachedFileSavePath() ;
						attachedFileReSavedForUsePath += "\\" + new SimpleDateFormat("yyyyMMdd").format(archivesInfoAttachedFile.getResaveTime());
						attachedFileReSavedForUsePath += "\\" + archivesInfoAttachedFile.getResaveFileName();
						//���ļ���·�����õ�ԭ����Ϣ�����tag����
						archivesInfoAttachedFile.setTag(attachedFileReSavedForUsePath);
System.out.println(archivesInfoAttachedFile.getTag());
					}					
				}
			}
						
			
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
			//���پֲ�����
			throwable = null;
		}

		return pFlag;		
	}

	
	
}
