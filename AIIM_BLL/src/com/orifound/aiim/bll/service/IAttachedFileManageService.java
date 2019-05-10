/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.*;
import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesInfoAttachedFile;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ԭ�Ĺ������Ľӿڶ���
 *
 */
public interface IAttachedFileManageService {

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
	 * ��ָ���ļ�����ԭ�ĵ����ļ���Ϣ�����ӳɹ���ǰָ���ļ���ArchivesInfoAttachedFiles���Ի�ͬ������
	 * @param archivesType ָ���ļ������ĵ�������
	 * @param archivesInfo ָ�����ļ�
	 * @param archivesInfoAttachedFile Ҫ���ӵ�ԭ�ĵ����ļ���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean addArchivesInfoAttachedFile(ArchivesType archivesType,ArchivesInfo archivesInfo,ArchivesInfoAttachedFile archivesInfoAttachedFile, ErrInfo pErrInfo);
	
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
	 * ɾ��ָ����ԭ�ĵ����ļ���Ϣ
	 * @param archivesType ������������
	 * @param archivesInfoAttachedFile Ҫɾ����ԭ�ĵ����ļ�����ID���Ա��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteArchivesInfoAttachedFile(ArchivesType archivesType,ArchivesInfoAttachedFile archivesInfoAttachedFile, ErrInfo pErrInfo);
	
	/**
	 * ����ָ����ԭ�ĵ����ļ���Ϣ
	 * @param archivesType ������������
	 * @param archivesInfoAttachedFile Ҫ���µ�ԭ�ĵ����ļ���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateArchivesInfoAttachedFile(ArchivesType archivesType,ArchivesInfoAttachedFile archivesInfoAttachedFile, ErrInfo pErrInfo);
	
	/**
	 * ���ļ���Ų���ԭ�ĵ����ļ���Ϣ
	 * @param archivesType ������������
	 * @param archivesInfoAttachedFile Ҫ���ҵ�ԭ�ĵ����ļ�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	 boolean findArchivesInfoAttachedFileById(ArchivesType archivesType,ArchivesInfoAttachedFile archivesInfoAttachedFile, ErrInfo pErrInfo);
	
	/**
	 * ����ָ��ԭ�ĵ����ļ��������ļ��еĴ���
	 * @param archivesType ָ���ļ������ĵ�������
	 * @param archivesInfoAttachedFiles Ҫ���������ԭ�ĵ����ļ����ϣ����Ա�����OrderID���Ա��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean sortArchivesInfoAttachedFiles(ArchivesType archivesType,List<ArchivesInfoAttachedFile> archivesInfoAttachedFiles, ErrInfo pErrInfo);
	
	/**
	 * ��ȡ�鵵���ԭ�ĵ����ļ��Ĵ��·���������·������ڱ�������tag��
	 * @param archivesType ��������
	 * @param archivesInfoAttachedFiles Ҫ��ȡ�洢·����ԭ�ĵ����ļ�����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean getSavedArchivesInfoAttachedFilesSavePath(ArchivesType archivesType,List<ArchivesInfoAttachedFile> archivesInfoAttachedFiles, ErrInfo pErrInfo);
	
}
