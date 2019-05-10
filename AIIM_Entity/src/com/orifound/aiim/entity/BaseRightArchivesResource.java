/**
 * 
 */
package com.orifound.aiim.entity;

/**
 * 档案资源权限基类
 *
 */
public class BaseRightArchivesResource
{
	/**
	 * 构造函数
	 */
	public BaseRightArchivesResource()
	{
	}

	/**
	 * 可查看档案基本信息的权限标志
	 */
	private boolean rightFlag_ArchivesInfo=false;

	/**
	 * 获取属性值：可查看档案基本信息的权限标志
	 * @return 可查看档案基本信息的权限标志
	 */
	public boolean getRightFlag_ArchivesInfo()
	{
		return rightFlag_ArchivesInfo;
	}

	/**
	 * 设置属性值：可查看档案基本信息的权限标志
	 * @param rightFlag_ArchivesInfo 可查看档案基本信息的权限标志
	 */
	public void setRightFlag_ArchivesInfo(boolean rightFlag_ArchivesInfo)
	{
		this.rightFlag_ArchivesInfo = rightFlag_ArchivesInfo;
	}

	/**
	 * 可查阅原文电子文件的权限标志
	 */
	private boolean rightFlag_AttachedFile=false;

	/**
	 * 获取属性值：可查阅原文电子文件的权限标志
	 * @return 可查阅原文电子文件的权限标志
	 */
	public boolean getRightFlag_AttachedFile()
	{
		return rightFlag_AttachedFile;
	}

	/**
	 * 设置属性值：可查阅原文电子文件的权限标志
	 * @param rightFlag_AttachedFile 可查阅原文电子文件的权限标志
	 */
	public void setRightFlag_AttachedFile(boolean rightFlag_AttachedFile)
	{
		this.rightFlag_AttachedFile = rightFlag_AttachedFile;
	}

	/**
	 * 可利用实物档案的权限标志
	 */
	private boolean rightFlag_PaperArchives=false;

	/**
	 * 获取属性值：可利用实物档案的权限标志
	 * @return 可利用实物档案的权限标志
	 */
	public boolean getRightFlag_PaperArchives()
	{
		return rightFlag_PaperArchives;
	}

	/**
	 * 设置属性值：可利用实物档案的权限标志
	 * @param rightFlag_PaperArchives 可利用实物档案的权限标志
	 */
	public void setRightFlag_PaperArchives(boolean rightFlag_PaperArchives)
	{
		this.rightFlag_PaperArchives = rightFlag_PaperArchives;
	}
	
	/**
	* 从指定对象克隆，复制所有属性值
	* @param archivesSecrecy 指定的对象源
	*/
	public void cloneFrom(BaseRightArchivesResource baseRightArchivesResource)
	{
		this.rightFlag_ArchivesInfo = baseRightArchivesResource.getRightFlag_ArchivesInfo();
		this.rightFlag_AttachedFile = baseRightArchivesResource.getRightFlag_AttachedFile();
		this.rightFlag_PaperArchives = baseRightArchivesResource.getRightFlag_PaperArchives();
	}
}
