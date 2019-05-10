/**
 * 
 */
package com.orifound.aiim.entity;

/**
 * ������ԴȨ�޻���
 *
 */
public class BaseRightArchivesResource
{
	/**
	 * ���캯��
	 */
	public BaseRightArchivesResource()
	{
	}

	/**
	 * �ɲ鿴����������Ϣ��Ȩ�ޱ�־
	 */
	private boolean rightFlag_ArchivesInfo=false;

	/**
	 * ��ȡ����ֵ���ɲ鿴����������Ϣ��Ȩ�ޱ�־
	 * @return �ɲ鿴����������Ϣ��Ȩ�ޱ�־
	 */
	public boolean getRightFlag_ArchivesInfo()
	{
		return rightFlag_ArchivesInfo;
	}

	/**
	 * ��������ֵ���ɲ鿴����������Ϣ��Ȩ�ޱ�־
	 * @param rightFlag_ArchivesInfo �ɲ鿴����������Ϣ��Ȩ�ޱ�־
	 */
	public void setRightFlag_ArchivesInfo(boolean rightFlag_ArchivesInfo)
	{
		this.rightFlag_ArchivesInfo = rightFlag_ArchivesInfo;
	}

	/**
	 * �ɲ���ԭ�ĵ����ļ���Ȩ�ޱ�־
	 */
	private boolean rightFlag_AttachedFile=false;

	/**
	 * ��ȡ����ֵ���ɲ���ԭ�ĵ����ļ���Ȩ�ޱ�־
	 * @return �ɲ���ԭ�ĵ����ļ���Ȩ�ޱ�־
	 */
	public boolean getRightFlag_AttachedFile()
	{
		return rightFlag_AttachedFile;
	}

	/**
	 * ��������ֵ���ɲ���ԭ�ĵ����ļ���Ȩ�ޱ�־
	 * @param rightFlag_AttachedFile �ɲ���ԭ�ĵ����ļ���Ȩ�ޱ�־
	 */
	public void setRightFlag_AttachedFile(boolean rightFlag_AttachedFile)
	{
		this.rightFlag_AttachedFile = rightFlag_AttachedFile;
	}

	/**
	 * ������ʵ�ﵵ����Ȩ�ޱ�־
	 */
	private boolean rightFlag_PaperArchives=false;

	/**
	 * ��ȡ����ֵ��������ʵ�ﵵ����Ȩ�ޱ�־
	 * @return ������ʵ�ﵵ����Ȩ�ޱ�־
	 */
	public boolean getRightFlag_PaperArchives()
	{
		return rightFlag_PaperArchives;
	}

	/**
	 * ��������ֵ��������ʵ�ﵵ����Ȩ�ޱ�־
	 * @param rightFlag_PaperArchives ������ʵ�ﵵ����Ȩ�ޱ�־
	 */
	public void setRightFlag_PaperArchives(boolean rightFlag_PaperArchives)
	{
		this.rightFlag_PaperArchives = rightFlag_PaperArchives;
	}
	
	/**
	* ��ָ�������¡��������������ֵ
	* @param archivesSecrecy ָ���Ķ���Դ
	*/
	public void cloneFrom(BaseRightArchivesResource baseRightArchivesResource)
	{
		this.rightFlag_ArchivesInfo = baseRightArchivesResource.getRightFlag_ArchivesInfo();
		this.rightFlag_AttachedFile = baseRightArchivesResource.getRightFlag_AttachedFile();
		this.rightFlag_PaperArchives = baseRightArchivesResource.getRightFlag_PaperArchives();
	}
}
