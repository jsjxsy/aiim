package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesInfoAttachedFile;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ������Ϣԭ�ı��Dao�ӿ�
 * @author Administrator
 *
 */
public interface IArchivesInfoAttachedFileTypeCodeDao {

	/**
	 * ��ȡָ���ļ���ԭ�ĵ����ļ���Ϣ����ȡ�ɹ���ֱ����Ϊ���Թ���ָ�����ļ��������档
	 * @param archivesType ָ���ļ������ĵ�������
	 * @param archivesInfo ָ�����ļ����ö����µ�ArchivesInfoAttachedFiles���Ա����˻�ȡ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean findArchivesInfoAttachedFiles(ArchivesType archivesType,ArchivesInfo archivesInfo, ErrInfo pErrInfo);
	
	/**
	 * ��ȡָ���ļ���ԭ�ĵ����ļ���Ϣ
	 * @param archivesType ָ���ļ������ĵ�������
	 * @param pNBXH ָ���ļ����ڲ����
	 * @param archivesInfoAttachedFiles ��ȡ�ɹ��󷵻ص�ԭ�ĵ����ļ�����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean findArchivesInfoAttachedFiles(ArchivesType archivesType,int pNBXH, List<ArchivesInfoAttachedFile> archivesInfoAttachedFiles, ErrInfo pErrInfo);
	
	/**
	 * ���ԭ�ĵ����ļ���Ϣ
	 * @param archivesType ������������
	 * @param archivesInfoAttachedFile Ҫ��ӵ�ԭ�ĵ����ļ���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean addArchivesInfoAttachedFile(ArchivesType archivesType,ArchivesInfoAttachedFile archivesInfoAttachedFile, ErrInfo pErrInfo);
	
	/**
	 * ɾ��ָ����ԭ�ĵ����ļ���Ϣ
	 * @param archivesType ������������
	 * @param archivesInfoAttachedFileID Ҫɾ����ԭ�ĵ����ļ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean deleteArchivesInfoAttachedFile(ArchivesType archivesType,int archivesInfoAttachedFileID, ErrInfo pErrInfo);
	
	/**
	 * ���ļ�������ԭ�ĵ����ļ���Ϣ
	 * @param archivesType ������������
	 * @param archivesInfoAttachedFile Ҫ���ҵ�ԭ�ĵ����ļ�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean findArchivesInfoAttachedFileByName(ArchivesType archivesType,ArchivesInfoAttachedFile archivesInfoAttachedFile, ErrInfo pErrInfo);
	
	/**
	 * ���ļ���Ų���ԭ�ĵ����ļ���Ϣ
	 * @param archivesType ������������
	 * @param archivesInfoAttachedFile Ҫ���ҵ�ԭ�ĵ����ļ�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean findArchivesInfoAttachedFileById(ArchivesType archivesType,ArchivesInfoAttachedFile archivesInfoAttachedFile, ErrInfo pErrInfo);
	
	/**
	 * ����ԭ�ĵ����ļ���Ϣ
	 * @param archivesType ������������
	 * @param archivesInfoAttachedFile Ҫ���µ�ԭ�ĵ����ļ���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean updateArchivesInfoAttachedFile(ArchivesType archivesType,ArchivesInfoAttachedFile archivesInfoAttachedFile, ErrInfo pErrInfo);
}
