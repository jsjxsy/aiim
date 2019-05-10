/**
 * 
 */
package com.orifound.aiim.bll.util;

import com.orifound.aiim.entity.OfficialArchivesInfo;
import com.orifound.aiim.entity.OfficialArchivesType;

/**
 * 公文档案信息对象工厂
 *
 */
public class OfficialArchivesInfoFactory
{
	/**
	 * 创建一个指定档案分类下的档案信息对象<br>
	 * 自动构建好档案信息对象下的数据项集合，各数据项都是空值或其本身的缺省值
	 * @param archivesTypeID 档案分类编号
	 * @return 具备数据项集合的档案信息对象
	 * @throws Exception 
	 */
	public static OfficialArchivesInfo newOfficialArchivsInfo(int officialArchivesTypeID) throws Exception
	{
		boolean pFlag = true;

		//错误信息
		StringBuilder pErrInfo=new StringBuilder();
		OfficialArchivesType officialArchivesType=null; //所属档案分类
		OfficialArchivesInfo officialArchivesInfo=null; //生产出来的档案信息对象
		
		try
		{
			//检查公文档案分类编号是否为0
			if (officialArchivesTypeID<=0)
			{
				pFlag = false;
				pErrInfo.append("档案分类编号非法为0");
			}
			
			//检查系统初始化器下面的公文档案分类集合是否有值
			if (pFlag)
			{
				if (CommonUtil.getSystemInitializer().getOfficialArchivesTypes() == null)
				{
					pFlag = false;
					pErrInfo.append("系统初始化对象的树状档案分类集合非法为空，请先进行系统初始化操作。");
				}
				else
				{
					if (CommonUtil.getSystemInitializer().getOfficialArchivesTypes().size() == 0)
					{
						pFlag = false;
						pErrInfo.append("系统初始化对象的树状档案分类集合非法为空，请确定系统数据库中存在档案分类字典信息。");
					}
				}
			}
			
			//计算公文档案分类
			if (pFlag)
			{
				if (CommonUtil.getSystemInitializer().getOfficialArchivesTypes().containsKey(officialArchivesTypeID)==false)
				{
					pFlag = false;
					pErrInfo.append("系统中不存在编号为 "+officialArchivesTypeID+" 的档案分类");
				}
				else
				{
					//保存获取的公文档案分类信息
					officialArchivesType=CommonUtil.getSystemInitializer().getOfficialArchivesTypes().get(officialArchivesTypeID);
					
					if (officialArchivesType.getDataItemsForAll()==null)
					{
						pFlag = false;
						pErrInfo.append("档案分类对应的数据项集合非法为空。");
					}
				}
			}
			
			//生产出带数据项集合的档案信息对象
			if (pFlag)
			{
				officialArchivesInfo=new OfficialArchivesInfo(officialArchivesType);
			}
		}
		catch (Exception e)
		{
			//异常错误
			pFlag = false;
			pErrInfo.append(e.toString());
		}
		finally
		{
			//销毁局部变量
			officialArchivesType=null;
			
			//抛出异常信息
			if (pFlag == false && pErrInfo.length() > 0)
			{
				throw new Exception(pErrInfo.toString());
			}
		}

		return officialArchivesInfo;
	}
	
	/**
	 * 创建一个指定公文档案分类下的档案信息对象，并赋予内部序号属性值<br>
	 * 自动构建好公文档案信息对象下的数据项集合，各数据项都是空值或其本身的缺省值
	 * @param officialArchivesTypeID 公文档案分类编号
	 * @param pNBXH 公文档案内部序号
	 * @return 具备数据项集合的公文档案信息对象
	 * @throws Exception 
	 */
	public static OfficialArchivesInfo newOfficialArchivsInfo(int officialArchivesTypeID,int pNBXH) throws Exception
	{
		boolean pFlag = true;

		//错误信息
		StringBuilder pErrInfo=new StringBuilder();
		//返回的档案信息对象
		OfficialArchivesInfo officialArchivesInfo=null;
		
		try
		{
			if (pNBXH<=0)
			{
				pFlag = false;
				pErrInfo.append("档案内部序号非法为0");
			}
			
			if (pFlag)
			{
				officialArchivesInfo=newOfficialArchivsInfo(officialArchivesTypeID);
				if (officialArchivesInfo!=null)
				{
					officialArchivesInfo.setNBXH(pNBXH);
				}
				else 
				{
					pFlag = false;
					pErrInfo.append("新创建的档案信息对象非法为空。");
				}
			}
			
		}
		catch (Exception e)
		{
			//异常错误
			pFlag = false;
			pErrInfo.append(e.toString());
		}
		finally
		{
			//抛出异常信息
			if (pFlag == false && pErrInfo.length() > 0)
			{
				throw new Exception(pErrInfo.toString());
			}
		}

		return officialArchivesInfo;
	}
}
