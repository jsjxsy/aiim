/**
 * 
 */
package com.orifound.aiim.bll.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.orifound.aiim.entity.ArchivesInfoQueryCondition;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.EnumColumnDataType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.ArchivesTypeEx;
import com.orifound.aiim.entity.OfficialArchivesInfoQueryCondition;
import com.orifound.aiim.entity.PaperTransferBatchesArchvTypeDetail;
import com.orifound.aiim.entity.SystemInitializer;

/**
 * 公共工具类
 * 
 */
public class CommonUtil {


	/**
	 * 获取属性值：全局的系统初始化器对象实例
	 * @return 全局的系统初始化器对象实例
	 */
	public static SystemInitializer getSystemInitializer()
	{
		return SystemInitializer.getInstance();
	}
	
	/**
	 * 获取系统的树状档案分类的完整克隆
	 * @param clonedArchivesTypes 返回克隆的树状档案分类
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	public static boolean cloneSystemArchivesTypes(LinkedHashMap<Integer, ArchivesType> clonedArchivesTypes,ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		try
		{
			//检查系统初始化器下面的档案分类集合是否有值
			pErrPos = 1;
			if (getSystemInitializer().getArchivesTypes() == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("系统初始化对象的树状档案分类集合非法为空，请先进行系统初始化操作。");
			}
			else
			{
				if (getSystemInitializer().getArchivesTypes().size() == 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("系统初始化对象的树状档案分类集合非法为空，请确定系统数据库中存在档案分类字典信息。");
				}
			}
			
			if (pFlag)
			{
				pErrPos = 2;
				if (getSystemInitializer().getArchivesTypes()!=null)
				{
					for (ArchivesType item : getSystemInitializer().getArchivesTypes().values())
					{
						clonedArchivesTypes.put(item.getID(), item.clone());
					}
				}
			}
		}
		catch (Exception e)
		{
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
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
	
	/**
	 * 将系统的树状档案分类的完整克隆为树状扩展档案分类集合
	 * @param clonedArchivesTypeExs 返回克隆的树状扩展档案分类
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	public static boolean cloneSystemArchivesTypesToEx(LinkedHashMap<Integer, ArchivesTypeEx> clonedArchivesTypeExs,ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		try
		{
			//检查系统初始化器下面的档案分类集合是否有值
			pErrPos = 1;
			if (getSystemInitializer().getArchivesTypes() == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("系统初始化对象的树状档案分类集合非法为空，请先进行系统初始化操作。");
			}
			else
			{
				if (getSystemInitializer().getArchivesTypes().size() == 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("系统初始化对象的树状档案分类集合非法为空，请确定系统数据库中存在档案分类字典信息。");
				}
			}
			
			if (pFlag)
			{
				pErrPos = 2;
				if (getSystemInitializer().getArchivesTypes()!=null)
				{
					for (ArchivesType item : getSystemInitializer().getArchivesTypes().values())
					{
						clonedArchivesTypeExs.put(item.getID(),new ArchivesTypeEx(item.clone()));
					}
				}
			}
		}
		catch (Exception e)
		{
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
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
	
	/**
	 * 将树状结构的档案分类集合转换为平面结构
	 * @param treeArchivesTypes 要转换的树状档案分类集合
	 * @param planeArchivesTypes 返回平面结构的档案分类集合
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	public static boolean convertTreeArchivesTypesToPlane(LinkedHashMap<Integer, ArchivesType> treeArchivesTypes, Map<Integer, ArchivesType> planeArchivesTypes, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			// 检查树状结构的档案分类是否为空
			pErrPos = 1;
			if (treeArchivesTypes == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("树状结构的档案分类信息非法为空。");
			}
			else
			{
				if (treeArchivesTypes.size() == 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("树状结构的档案分类信息非法为空。");
				}
			}

			// 开始转换
			if (pFlag)
			{
				for (ArchivesType item : treeArchivesTypes.values())
				{
					// 将当前分类加入返回结果集中
					planeArchivesTypes.put(item.getID(), item);

					// 如果当前分类下还有下级分类，则递归处理
					if (item.getChildArchivesTypes() != null)
					{
						if (item.getChildArchivesTypes().size() > 0)
						{
							if (convertTreeArchivesTypesToPlane(item.getChildArchivesTypes(), planeArchivesTypes, pErrInfo) == false)
							{
								pFlag = false;
								pErrInfo.getContent().insert(0, "树状结构的档案分类转换为平面结构失败: ");
							}
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			// 异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			// 拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// 如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// 销毁局部变量
			throwable = null;
		}

		return pFlag;
	}
	
	
	/**
	 * 从指定的档案分类树中获取最末节点的档案分类
	 * @param treeArchivesTypes 要转换的树状档案分类集合
	 * @param planeArchivesTypes 返回平面结构的档案分类集合
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */ 
	public static boolean getEndArchivesTypesByTree(LinkedHashMap<Integer, ArchivesType> treeArchivesTypes, Map<Integer, ArchivesType> endArchivesTypes, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			// 检查树状结构的档案分类是否为空
			pErrPos = 1;
			if (treeArchivesTypes == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("树状结构的档案分类信息非法为空。");
			}
			else
			{
				if (treeArchivesTypes.size() == 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("树状结构的档案分类信息非法为空。");
				}
			}

			// 开始转换
			if (pFlag)
			{
				for (ArchivesType item : treeArchivesTypes.values())
				{
					if(item.getEndFlag()==true){
						// 将当前是最末节点的分类加入返回结果集中
						endArchivesTypes.put(item.getID(), item);
					}					

					// 如果当前分类下还有下级分类，则递归处理
					if (item.getChildArchivesTypes() != null)
					{
						if (item.getChildArchivesTypes().size() > 0)
						{
							if (getEndArchivesTypesByTree(item.getChildArchivesTypes(), endArchivesTypes, pErrInfo) == false)
							{
								pFlag = false;
								pErrInfo.getContent().insert(0, "从指定的档案分类树中获取最末节点的档案分类失败: ");
							}
						}
					}
				}
			}
			
			
		}catch (Exception e){
			// 异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}finally{
			// 拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// 如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// 销毁局部变量
			throwable = null;
		}

		return pFlag;
	}
	
	
	
	/**
	 * 移除指定扩展档案分类树中不存在于指定平面扩展档案分类集合中的最末节点<br>
	 * 存在其中的从平面结构中的对应元素进行克隆处理
	 * @param treeArchivesTypeExs 指定的树状扩展档案分类集合
	 * @param planeArchivesTypeExs 指定的平面结构扩展档案分类集合
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private static boolean removeEndArchivesTypeExNotExitsPlane(LinkedHashMap<Integer, ArchivesTypeEx> treeArchivesTypeExs,Map<Integer, ArchivesTypeEx> planeArchivesTypeExs, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//检查扩展树状档案分类是否为空
			if (treeArchivesTypeExs==null)
			{
				pFlag = false;
				pErrInfo.getContent().append("树状扩展档案分类集合非法为空。");
			}
			
			//检查平面扩展档案分类集合是否为空
			if (pFlag)
			{
				if (planeArchivesTypeExs==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("平面扩展档案分类集合非法为空。");
				}
			}
			
			//开始处理
			if (pFlag)
			{
				//循环遍历集合中的元素，判定是否存在子分类
				if (treeArchivesTypeExs.size()>0)
				{
					//需要移除的关键字集合
					List<Integer> needRemoveKeys=new ArrayList<Integer>();
					
					for (ArchivesTypeEx item : treeArchivesTypeExs.values())
					{
						//如果还有子分类，则递归处理
						if (item.getChildArchivesTypeExs()!=null)
						{
							if (removeEndArchivesTypeExNotExitsPlane(item.getChildArchivesTypeExs(),planeArchivesTypeExs, pErrInfo)==false)
							{
								pFlag = false;
								pErrInfo.getContent().insert(0, "移除指定树状扩展档案分类集合中的底层扩展档案分类节点失败: ");
							}
							//子分类处理完毕了后，有可能子分类都移除了，如果没有子分类了，则当前节点也需要移除
							//先将关键字保存在需要移除的关键字集合中，后面再遍历移除之
							else
							{
								if (item.getChildArchivesTypeExs().size()==0)
								{
									needRemoveKeys.add(item.getID());
								}
							}
						}
						//如果没有子分类则进一步判断
						else 
						{
							//如果是最底层档案分类节点，则判断是否存在于平面档案分类集合中
							if (item.getEndFlag()==true)
							{
								//如果不存在，则将其关键字保存在需要移除的关键字集合中，后面再遍历移除之
								if (planeArchivesTypeExs.containsKey(item.getID())==false)
								{
									needRemoveKeys.add(item.getID());
								}
								//如果存在，则从其克隆，以复制相关扩展属性值至树状扩展档案分类集合中
								else 
								{
									item.cloneExtendProperty(planeArchivesTypeExs.get(item.getID()));
								}
							}
							//如果不是最底层分类，则直接移除，先将其关键字保存在需要移除的关键字集合中，后面再遍历移除之
							else
							{
								needRemoveKeys.add(item.getID());
							}
						}
						
						//出错跳出循环
						if (pFlag==false)
						{
							break;
						}
					}
					
					//遍历要移除的关键字集合，从当前树状档案分类集合中逐个移除之
					if (needRemoveKeys.size()>0)
					{
						for (Integer key : needRemoveKeys)
						{
							if (treeArchivesTypeExs.containsKey(key))
							{
								treeArchivesTypeExs.remove(key);
							}
						}
					}
					
					needRemoveKeys=null;
				}
			}
		}
		catch (Exception e)
		{
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
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
	
	/**
	 * 移除指定档案分类树中不存在于指定平面档案分类集合中的最末节点
	 * @param treeArchivesTypes 指定的树状档案分类集合
	 * @param planeArchivesTypes 指定的平面结构档案分类集合
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private static boolean removeEndArchivesTypeNotExitsPlane(LinkedHashMap<Integer, ArchivesType> treeArchivesTypes,Map<Integer, ArchivesType> planeArchivesTypes, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//检查树状档案分类是否为空
			if (treeArchivesTypes==null)
			{
				pFlag = false;
				pErrInfo.getContent().append("树状档案分类集合非法为空。");
			}
			
			//检查平面档案分类集合是否为空
			if (pFlag)
			{
				if (planeArchivesTypes==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("平面档案分类集合非法为空。");
				}
			}
			
			//开始处理
			if (pFlag)
			{
				//循环遍历集合中的元素，判定是否存在子分类
				if (treeArchivesTypes.size()>0)
				{
					//需要移除的关键字集合
					List<Integer> needRemoveKeys=new ArrayList<Integer>();
					
					for (ArchivesType item : treeArchivesTypes.values())
					{
						//如果还有子分类，则递归处理
						if (item.getChildArchivesTypes()!=null)
						{
							if (removeEndArchivesTypeNotExitsPlane(item.getChildArchivesTypes(),planeArchivesTypes, pErrInfo)==false)
							{
								pFlag = false;
								pErrInfo.getContent().insert(0, "移除指定树状档案分类集合中的底层档案分类节点失败: ");
							}
							else
							{
								//子分类处理完毕了后，有可能子分类都移除了，如果没有子分类了，则当前节点也需要移除
								//先将关键字保存在需要移除的关键字集合中，后面再遍历移除之
								if (item.getChildArchivesTypes().size()==0)
								{
									needRemoveKeys.add(item.getID());
								}
								//如果还存在子类，则进一步判断是否存在于平面档案分类集合中，如果存在则克隆相关属性值（基本档案资源权限信息）
								else 
								{
									if (planeArchivesTypes.containsKey(item.getID()))
									{
										item.cloneBaseRightArchivesResourceFrom(planeArchivesTypes.get(item.getID()));
									}
								}
							}
						}
						//如果没有子分类则进一步判断
						else 
						{
							//如果是最底层档案分类节点，则判断是否存在于平面档案分类集合中
							if (item.getEndFlag()==true)
							{
								//如果不存在，则将其关键字保存在需要移除的关键字集合中，后面再遍历移除之
								if (planeArchivesTypes.containsKey(item.getID())==false)
								{
									needRemoveKeys.add(item.getID());
								}
								//如果存在则克隆相关属性值（基本档案资源权限信息）
								else 
								{
									item.cloneBaseRightArchivesResourceFrom(planeArchivesTypes.get(item.getID()));
								}
							}
							//如果不是最底层分类又没有子类，则直接移除，先将其关键字保存在需要移除的关键字集合中，后面再遍历移除之
							else
							{
								needRemoveKeys.add(item.getID());
							}
						}
						
						//出错跳出循环
						if (pFlag==false)
						{
							break;
						}
					}
					
					//遍历要移除的关键字集合，从当前树状档案分类集合中逐个移除之
					if (needRemoveKeys.size()>0)
					{
						for (Integer key : needRemoveKeys)
						{
							if (treeArchivesTypes.containsKey(key))
							{
								treeArchivesTypes.remove(key);
							}
						}
					}
					
					needRemoveKeys=null;
				}
			}
		}
		catch (Exception e)
		{
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
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
	
	/**
	 * 参考系统档案分类树将平面型的最底层档案分类节点集合转换为树状结构的档案分类集合<br>
	 * 构建好其上级档案分类的路径层次关系
	 * @param planeArchivesTypes 要转换的平面型档案分类集合
	 * @param treeArchivesTypes 返回转换后的树状结构的档案分类集合
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	public static boolean convertPlaneArchivesTypesToTree(Map<Integer, ArchivesType> planeArchivesTypes, LinkedHashMap<Integer, ArchivesType> treeArchivesTypes, ErrInfo pErrInfo) 
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		try 
		{
			//检查平面型的档案分类集合是否为空
			pErrPos = 1;
			if (pFlag) 
			{
				if (planeArchivesTypes==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("平面型的档案分类集合非法为空。");
				}
			}
			
			//先将系统档案分类树的克隆版本保存至返回的档案分类树中
			if (pFlag)
			{
				pErrPos = 2;
				if (cloneSystemArchivesTypes(treeArchivesTypes, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "克隆系统档案分类树失败: ");
				}
			}
			
			//然后从要返回的档案分类树中移除掉那些不存在于平面档案分类集合中的最底层档案分类节点
			if (pFlag)
			{
				if (removeEndArchivesTypeNotExitsPlane(treeArchivesTypes, planeArchivesTypes, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "从指定树状档案分类集合中移除不存在于指定平面档案分类集合中的最底层档案分类节点失败: ");
				}
			}
		} 
		catch (Exception e) 
		{
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} 
		finally 
		{
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				
				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException()!=null)
				{
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
	 * 参考系统档案分类树将平面型扩展的最底层档案分类节点集合转换为树状结构的扩展档案分类集合<br>
	 * 构建好其上级扩展档案分类的路径层次关系
	 * @param planeArchivesTypeExs 要转换的平面型扩展档案分类集合
	 * @param treeArchivesTypeExs 返回转换后的树状结构的扩展档案分类集合
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	public static boolean convertPlaneArchivesTypeExsToTree(Map<Integer, ArchivesTypeEx> planeArchivesTypeExs, LinkedHashMap<Integer, ArchivesTypeEx> treeArchivesTypeExs, ErrInfo pErrInfo) 
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		try 
		{
			//检查平面型的扩展档案分类集合是否为空
			pErrPos = 1;
			if (pFlag) 
			{
				if (planeArchivesTypeExs==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("平面型的扩展档案分类集合非法为空。");
				}
			}
			
			//先根据系统档案分类树的克隆出一个扩展档案分类树保存至返回的扩展档案分类树中
			if (pFlag)
			{
				pErrPos = 2;
				if (cloneSystemArchivesTypesToEx(treeArchivesTypeExs, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "克隆系统档案分类树至扩展档案分类树失败: ");
				}
			}
			
			//然后从要返回的档案分类树中移除掉那些不存在于平面档案分类集合中的最底层档案分类节点，存在的从平面结构元素中克隆扩展属性值保存至返回树中
			if (pFlag)
			{
				if (removeEndArchivesTypeExNotExitsPlane(treeArchivesTypeExs, planeArchivesTypeExs, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "从指定树状扩展档案分类集合中移除不存在于指定平面扩展档案分类集合中的最底层档案分类节点失败: ");
				}
			}
		} 
		catch (Exception e) 
		{
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} 
		finally 
		{
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				
				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException()!=null)
				{
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
	 * 将平面型的纸质档案移交批次档案分类明细情况集合转换为页面所需的树状结构的档案分类节点集合
	 * @param archivesTypes 档案分类集合（一级分类）
	 * @param archivesTypeExs 纸质档案移交批次档案分类明细情况节点集合
	 * @param treeArchivesTypeExs 返回转换后的树状档案移交的档案类型节点集合
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	public static boolean getArchivesTypeTreeFromArchvTypeDetails(Map<Integer, ArchivesType> archivesTypes,Map<Integer, PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetails,Map<Integer, ArchivesTypeEx> treeArchivesTypeExs, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			

			//开始处理2...
			if (pFlag) {

			}
		} catch (Exception e) {
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				
				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException()!=null)
				{
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
	 * 检查指定编号的档案分类编号是否合法
	 * @param archivesTypeID 档案分类编号
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	public static boolean checkArchivesType(int archivesTypeID,ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			if (archivesTypeID<=0)
			{
				pFlag = false;
				pErrInfo.getContent().append("档案分类编号非法为0");
			}
			
			//检查系统初始化器下面的档案分类集合是否有值
			if (pFlag)
			{
				pErrPos = 1;
				if (getSystemInitializer().getArchivesTypes() == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("系统初始化对象的树状档案分类集合非法为空，请先进行系统初始化操作。");
				}
				else
				{
					if (getSystemInitializer().getArchivesTypes().size() == 0)
					{
						pFlag = false;
						pErrInfo.getContent().append("系统初始化对象的树状档案分类集合非法为空，请确定系统数据库中存在档案分类字典信息。");
					}
				}
			}
			
			//计算所属的档案分类
			if (pFlag)
			{
				pErrPos = 2;
				if (getSystemInitializer().getPlaneArchivesTypes().containsKey(archivesTypeID)==false)
				{
					pFlag = false;
					pErrInfo.getContent().append("系统中不存在编号为 "+archivesTypeID+" 的档案分类");
				}
			}
		}
		catch (Exception e)
		{
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
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
	
	/**
	 * 检查指定编号的档案分类编号是否合法
	 * @param archivesTypeID 档案分类编号
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	public static boolean checkOfficialArchivesType(int officialArchivesTypeID,ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			if (officialArchivesTypeID<=0)
			{
				pFlag = false;
				pErrInfo.getContent().append("档案分类编号非法为0");
			}
			
			//检查系统初始化器下面的档案分类集合是否有值
			if (pFlag)
			{
				pErrPos = 1;
				if (getSystemInitializer().getArchivesTypes() == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("系统初始化对象的树状档案分类集合非法为空，请先进行系统初始化操作。");
				}
				else
				{
					if (getSystemInitializer().getArchivesTypes().size() == 0)
					{
						pFlag = false;
						pErrInfo.getContent().append("系统初始化对象的树状档案分类集合非法为空，请确定系统数据库中存在档案分类字典信息。");
					}
				}
			}
			
			//计算所属的档案分类
			if (pFlag)
			{
				pErrPos = 2;
				if (getSystemInitializer().getOfficialArchivesTypes().containsKey(officialArchivesTypeID)==false)
				{
					pFlag = false;
					pErrInfo.getContent().append("系统中不存在编号为 "+officialArchivesTypeID+" 的档案分类");
				}
			}
		}
		catch (Exception e)
		{
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
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
	
	/**
	 * 检查档案著录查询条件并进行相关的条件值修复处理<br>
	 * 例如将空的范围查询条件值补全，最小值与最大值调换，将日期条件值格式化为标准年月日时分秒字符串
	 * @param archivesInfoQueryConditions 档案著录查询条件集合
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	public static boolean checkArchivesInfoInputQueryConditions(List<ArchivesInfoQueryCondition> archivesInfoQueryConditions, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			if (archivesInfoQueryConditions!=null)
			{
				if (archivesInfoQueryConditions.size()>0)
				{
					for (ArchivesInfoQueryCondition item : archivesInfoQueryConditions)
					{
						//检查是否可以作为著录查询条件
						if (item.getDataItem().getInputQueryFlag()==false)
						{
							pFlag = false;
							pErrInfo.getContent().append("数据项（"+item.getDataItem().getDisplayText()+"）不能够作为著录查询条件。");
						}
						
						//如果是范围查询，则检查最小值和最大值
						if (pFlag)
						{
							if (item.getDataItem().getRangeQueryFlag())
							{
								if (item.getMinValue().length()==0 && item.getMaxValue().length()==0)
								{
									pFlag = false;
									pErrInfo.getContent().append("数据项（"+item.getDataItem().getDisplayText()+"）的范围查询条件非法为空。");
								}
								else 
								{
									//有最小值，没有最大值，则将最大值赋值为最小值
									if (item.getMinValue().length()>0 && item.getMaxValue().length()==0)
									{
										item.setMaxValue(item.getMinValue());
									}
									//有最大值，没有最小值，则将最小值赋值为最大值
									else if (item.getMinValue().length()==0 && item.getMaxValue().length()>0)
									{
										item.setMinValue(item.getMaxValue());
									}
									else 
									{
										//如果最小值比最大值还大，那么需要调换一下。
										if (item.getDataItem().getColumnDataType()==EnumColumnDataType.实数) 
										{
											if (Float.valueOf(item.getMinValue())>Float.valueOf(item.getMaxValue()))
											{
												String tempValue=item.getMinValue();
												item.setMinValue(item.getMaxValue());
												item.setMaxValue(tempValue);
											}
										}
										else if (item.getDataItem().getColumnDataType()==EnumColumnDataType.整数 
												|| item.getDataItem().getColumnDataType()==EnumColumnDataType.布尔值)
										{
											if (Integer.valueOf(item.getMinValue())>Integer.valueOf(item.getMaxValue()))
											{
												String tempValue=item.getMinValue();
												item.setMinValue(item.getMaxValue());
												item.setMaxValue(tempValue);
											}
										}
										else if (item.getDataItem().getColumnDataType()==EnumColumnDataType.日期时间)
										{
											//先把日期值格式化为标准字符串格式
											SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
											Date tempDateMin=simpleDateFormat.parse(item.getMinValue());
											item.setMinValue(simpleDateFormat.format(tempDateMin));
											Date tempDateMax=simpleDateFormat.parse(item.getMaxValue());
											item.setMaxValue(simpleDateFormat.format(tempDateMax));
											
											if (tempDateMin.getTime()>tempDateMax.getTime())
											{
												String tempValue=item.getMinValue();
												item.setMinValue(item.getMaxValue());
												item.setMaxValue(tempValue);
											}
										}
										else if (item.getDataItem().getColumnDataType()==EnumColumnDataType.字符串 
												|| item.getDataItem().getColumnDataType()==EnumColumnDataType.文本)
										{
											if (item.getMinValue().compareTo(item.getMaxValue())>0)
											{
												String tempValue=item.getMinValue();
												item.setMinValue(item.getMaxValue());
												item.setMaxValue(tempValue);
											}
										}
									}
								}
							}
							//如果不是范围查询，则检查条件值
							else 
							{
								if (item.getValue().length()==0)
								{
									pFlag = false;
									pErrInfo.getContent().append("数据项（"+item.getDataItem().getDisplayText()+"）的查询条件非法为空。");
								}
								else 
								{
									//把日期值格式化为标准字符串格式
									if (item.getDataItem().getColumnDataType()==EnumColumnDataType.日期时间)
									{
										SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
										Date tempDateMin=simpleDateFormat.parse(item.getMinValue());
										item.setValue(simpleDateFormat.format(tempDateMin));
									}
								}
							}
						}
						
						//如果检查未通过，则跳出循环
						if (pFlag==false)
						{
							break;
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
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
	
	/**
	 * 检查档案著录查询条件并进行相关的条件值修复处理<br>
	 * 例如将空的范围查询条件值补全，最小值与最大值调换，将日期条件值格式化为标准年月日时分秒字符串
	 * @param archivesInfoQueryConditions 档案著录查询条件集合
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	public static boolean checkOfficialArchivesInfoInputQueryConditions(List<OfficialArchivesInfoQueryCondition> officialArchivesInfoQueryConditions, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			if (officialArchivesInfoQueryConditions!=null)
			{
				if (officialArchivesInfoQueryConditions.size()>0)
				{
					for (OfficialArchivesInfoQueryCondition item : officialArchivesInfoQueryConditions)
					{
						//检查是否可以作为著录查询条件
//						if (item.getDataItem().getInputQueryFlag()==false)
//						{
//							pFlag = false;
//							pErrInfo.getContent().append("数据项（"+item.getDataItem().getDisplayText()+"）不能够作为著录查询条件。");
//						}
						
						//如果是范围查询，则检查最小值和最大值
						if (pFlag)
						{
							if (item.getDataItem().getRangeQueryFlag())
							{
								if (item.getMinValue().length()==0 && item.getMaxValue().length()==0)
								{
									pFlag = false;
									pErrInfo.getContent().append("数据项（"+item.getDataItem().getDisplayText()+"）的范围查询条件非法为空。");
								}
								else 
								{
									//有最小值，没有最大值，则将最大值赋值为最小值
									if (item.getMinValue().length()>0 && item.getMaxValue().length()==0)
									{
										item.setMaxValue(item.getMinValue());
									}
									//有最大值，没有最小值，则将最小值赋值为最大值
									else if (item.getMinValue().length()==0 && item.getMaxValue().length()>0)
									{
										item.setMinValue(item.getMaxValue());
									}
									else 
									{
										//如果最小值比最大值还大，那么需要调换一下。
										if (item.getDataItem().getColumnDataType()==EnumColumnDataType.实数) 
										{
											if (Float.valueOf(item.getMinValue())>Float.valueOf(item.getMaxValue()))
											{
												String tempValue=item.getMinValue();
												item.setMinValue(item.getMaxValue());
												item.setMaxValue(tempValue);
											}
										}
										else if (item.getDataItem().getColumnDataType()==EnumColumnDataType.整数 
												|| item.getDataItem().getColumnDataType()==EnumColumnDataType.布尔值)
										{
											if (Integer.valueOf(item.getMinValue())>Integer.valueOf(item.getMaxValue()))
											{
												String tempValue=item.getMinValue();
												item.setMinValue(item.getMaxValue());
												item.setMaxValue(tempValue);
											}
										}
										else if (item.getDataItem().getColumnDataType()==EnumColumnDataType.日期时间)
										{
											//先把日期值格式化为标准字符串格式
											SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
											Date tempDateMin=simpleDateFormat.parse(item.getMinValue());
											item.setMinValue(simpleDateFormat.format(tempDateMin));
											Date tempDateMax=simpleDateFormat.parse(item.getMaxValue());
											item.setMaxValue(simpleDateFormat.format(tempDateMax));
											
											if (tempDateMin.getTime()>tempDateMax.getTime())
											{
												String tempValue=item.getMinValue();
												item.setMinValue(item.getMaxValue());
												item.setMaxValue(tempValue);
											}
										}
										else if (item.getDataItem().getColumnDataType()==EnumColumnDataType.字符串 
												|| item.getDataItem().getColumnDataType()==EnumColumnDataType.文本)
										{
											if (item.getMinValue().compareTo(item.getMaxValue())>0)
											{
												String tempValue=item.getMinValue();
												item.setMinValue(item.getMaxValue());
												item.setMaxValue(tempValue);
											}
										}
									}
								}
							}
							//如果不是范围查询，则检查条件值
							else 
							{
								if (item.getValue().length()==0)
								{
									pFlag = false;
									pErrInfo.getContent().append("数据项（"+item.getDataItem().getDisplayText()+"）的查询条件非法为空。");
								}
								else 
								{
									//把日期值格式化为标准字符串格式
									if (item.getDataItem().getColumnDataType()==EnumColumnDataType.日期时间)
									{
										SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
										Date tempDateMin=simpleDateFormat.parse(item.getMinValue());
										item.setValue(simpleDateFormat.format(tempDateMin));
									}
								}
							}
						}
						
						//如果检查未通过，则跳出循环
						if (pFlag==false)
						{
							break;
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
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
	
	/**
	 * 检查档案利用查询条件并进行相关的条件值修复处理<br>
	 * 例如将空的范围查询条件值补全，最小值与最大值调换，将日期条件值格式化为标准年月日时分秒字符串
	 * @param archivesInfoQueryConditions 档案著录查询条件集合
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	public static boolean checkArchivesInfoUseQueryConditions(List<ArchivesInfoQueryCondition> archivesInfoQueryConditions, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			if (archivesInfoQueryConditions!=null)
			{
				if (archivesInfoQueryConditions.size()>0)
				{
					for (ArchivesInfoQueryCondition item : archivesInfoQueryConditions)
					{
						//检查是否可以作为利用查询条件
						if (item.getDataItem().getUseQueryFlag()==false)
						{
							pFlag = false;
							pErrInfo.getContent().append("数据项（"+item.getDataItem().getDisplayText()+"）不能够作为利用查询条件。");
						}
						
						//如果是范围查询，则检查最小值和最大值
						if (pFlag)
						{
							if (item.getDataItem().getRangeQueryFlag())
							{
								if (item.getMinValue().length()==0 && item.getMaxValue().length()==0)
								{
									pFlag = false;
									pErrInfo.getContent().append("数据项（"+item.getDataItem().getDisplayText()+"）的范围查询条件非法为空。");
								}
								else 
								{
									//有最小值，没有最大值，则将最大值赋值为最小值
									if (item.getMinValue().length()>0 && item.getMaxValue().length()==0)
									{
										item.setMaxValue(item.getMinValue());
									}
									//有最大值，没有最小值，则将最小值赋值为最大值
									else if (item.getMinValue().length()==0 && item.getMaxValue().length()>0)
									{
										item.setMinValue(item.getMaxValue());
									}
									else 
									{
										//如果最小值比最大值还大，那么需要调换一下。
										if (item.getDataItem().getColumnDataType()==EnumColumnDataType.实数) 
										{
											if (Float.valueOf(item.getMinValue())>Float.valueOf(item.getMaxValue()))
											{
												String tempValue=item.getMinValue();
												item.setMinValue(item.getMaxValue());
												item.setMaxValue(tempValue);
											}
										}
										else if (item.getDataItem().getColumnDataType()==EnumColumnDataType.整数 
												|| item.getDataItem().getColumnDataType()==EnumColumnDataType.布尔值)
										{
											if (Integer.valueOf(item.getMinValue())>Integer.valueOf(item.getMaxValue()))
											{
												String tempValue=item.getMinValue();
												item.setMinValue(item.getMaxValue());
												item.setMaxValue(tempValue);
											}
										}
										else if (item.getDataItem().getColumnDataType()==EnumColumnDataType.日期时间)
										{
											//先把日期值格式化为标准字符串格式
											SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
											Date tempDateMin=simpleDateFormat.parse(item.getMinValue());
											item.setMinValue(simpleDateFormat.format(tempDateMin));
											Date tempDateMax=simpleDateFormat.parse(item.getMaxValue());
											item.setMaxValue(simpleDateFormat.format(tempDateMax));
											
											if (tempDateMin.getTime()>tempDateMax.getTime())
											{
												String tempValue=item.getMinValue();
												item.setMinValue(item.getMaxValue());
												item.setMaxValue(tempValue);
											}
										}
										else if (item.getDataItem().getColumnDataType()==EnumColumnDataType.字符串 
												|| item.getDataItem().getColumnDataType()==EnumColumnDataType.文本)
										{
											if (item.getMinValue().compareTo(item.getMaxValue())>0)
											{
												String tempValue=item.getMinValue();
												item.setMinValue(item.getMaxValue());
												item.setMaxValue(tempValue);
											}
										}
									}
								}
							}
							//如果不是范围查询，则检查条件值
							else 
							{
								if (item.getValue().length()==0)
								{
									pFlag = false;
									pErrInfo.getContent().append("数据项（"+item.getDataItem().getDisplayText()+"）的查询条件非法为空。");
								}
								else 
								{
									//把日期值格式化为标准字符串格式
									if (item.getDataItem().getColumnDataType()==EnumColumnDataType.日期时间)
									{
										SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
										Date tempDateMin=simpleDateFormat.parse(item.getMinValue());
										item.setValue(simpleDateFormat.format(tempDateMin));
									}
								}
							}
						}
						
						//如果检查未通过，则跳出循环
						if (pFlag==false)
						{
							break;
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
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
	
	
	
	
	
	/**
	 * 判断指定字符串数据是否为日期数据
	 * @param strData 字符串数据
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	public static boolean isDate(String strData)
	{
		boolean pIsDate = true;

		try
		{
			//尝试转换为日期
			SimpleDateFormat dateFormater=new SimpleDateFormat("yyyy-MM-dd");
			dateFormater.parse(strData);
		}
		catch (Exception e)
		{
			//出现异常表示不是日期
			pIsDate = false;
		}

		return pIsDate;
	}
	
	/**
	 * 判断指定字符串数据是否为布尔值数据
	 * @param strData 字符串数据
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	public static boolean isBoolean(String strData)
	{
		boolean pIsBoolean = true;

		try
		{
			if (strData.equals("0")==false && strData.equals("1")==false)
			{
				pIsBoolean=false;
			}
		}
		catch (Exception e)
		{
			//出现异常表示不是布尔值
			pIsBoolean=false;
		}

		return pIsBoolean;
	}
	
	/**
	 * 判断指定字符串数据是否为整数数据
	 * @param strData 字符串数据
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	public static boolean isInteger(String strData)
	{
		boolean pIsInteger = true;

		try
		{
			Long.parseLong(strData);
		}
		catch (Exception e)
		{
			//出现异常表示不是整数
			pIsInteger=false;
		}

		return pIsInteger;
	}
	
	/**
	 * 判断指定字符串数据是否为实数数据
	 * @param strData 字符串数据
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	public static boolean isDouble(String strData)
	{
		boolean pIsDouble = true;

		try
		{
			Double.parseDouble(strData);
		}
		catch (Exception e)
		{
			//出现异常表示不是实数
			pIsDouble=false;
		}

		return pIsDouble;
	}
	
	/**
	 * 将树状结构的档案分类集合转换为平面结构
	 * 
	 * @param planeArchivesTypes 返回平面树
	 * @param treeArchivesTypes 参考树
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	public static boolean getChildPlaneArchivesTypes(LinkedHashMap<Integer, ArchivesType> treeArchivesTypes, Map<Integer, ArchivesType> planeArchivesTypes, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			// 检查树状结构的档案分类是否为空
			pErrPos = 1;
			if (treeArchivesTypes == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("树状结构的档案分类信息非法为空。");
			}
			else
			{
				if (treeArchivesTypes.size() == 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("树状结构的档案分类信息非法为空。");
				}
			}

			// 开始转换
			if (pFlag)
			{
				for (ArchivesType item : treeArchivesTypes.values())
				{
					// 如果当前分类下还有下级分类，则递归处理
					if (item.getChildArchivesTypes() != null)
					{
						if (item.getChildArchivesTypes().size() > 0)
						{
							if (getChildPlaneArchivesTypes(item.getChildArchivesTypes(), planeArchivesTypes, pErrInfo) == false)
							{
								pFlag = false;
								pErrInfo.getContent().insert(0, "树状结构的档案分类转换为平面结构失败: ");
							}
						}
					}else{
						
						// 将当前分类加入返回结果集中
						planeArchivesTypes.put(item.getID(), item);
					}
				}
			}
		}
		catch (Exception e)
		{
			// 异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			// 拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// 如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// 销毁局部变量
			throwable = null;
		}

		return pFlag;
	}

	/**
	 * 将树状结构的档案分类集合转换为平面结构
	 * 
	 * @param archivesTypeExs 返回平面树
	 * @param archivesTypes 参考树
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	public static boolean getChildPlaneArchivesTypeExs(LinkedHashMap<Integer, ArchivesType> archivesTypes, Map<Integer, ArchivesTypeEx> childPlaneArchivesTypeExs, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			// 检查树状结构的档案分类是否为空
			pErrPos = 1;
			if (archivesTypes == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("树状结构的档案分类信息非法为空。");
			}
			else
			{
				if (archivesTypes.size() == 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("树状结构的档案分类信息非法为空。");
				}
			}

			// 开始转换
			if (pFlag)
			{
				ArchivesTypeEx archivesTypeEx = null;
				for (ArchivesType item : archivesTypes.values())
				{
					// 如果当前分类下还有下级分类，则递归处理
					if (item.getChildArchivesTypes() != null)
					{
						if (item.getChildArchivesTypes().size() > 0)
						{
							if (getChildPlaneArchivesTypeExs(item.getChildArchivesTypes(), childPlaneArchivesTypeExs, pErrInfo) == false)
							{
								pFlag = false;
								pErrInfo.getContent().insert(0, "树状结构的档案分类转换为平面结构失败: ");
							}
						}
					}else{
						
						archivesTypeEx = new ArchivesTypeEx();
						archivesTypeEx.cloneFrom(item);
						
						// 将当前分类加入返回结果集中
						childPlaneArchivesTypeExs.put(archivesTypeEx.getID(), archivesTypeEx);
					}
				}
			}
		}
		catch (Exception e)
		{
			// 异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			// 拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// 如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// 销毁局部变量
			throwable = null;
		}

		return pFlag;
		
	}
	
	/**
	 * 获取指定档案类型的所有子类型id集合
	 * @param archiveTypeId 指定档案类型id
	 * @return
	 */
	public static boolean getAllChildArchivesTypeId(List<Integer> archivesTypeIds, int archivesTypeId) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//获取档案实体
			ArchivesType archivesType = getSystemInitializer().getPlaneArchivesTypes().get(archivesTypeId);
			//递归获取子档案分类id
			archivesTypeIds.addAll(getChild(archivesType));
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo .getContent().append(e.toString());
			pErrInfo.setException(e);
			e.printStackTrace();
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

			//销毁局部变量
			throwable = null;
		}
		return pFlag;
	}
	
	//递归查询知道档案分类下的所有子分类
	private static List<Integer> getChild(ArchivesType archivesType) {
		List<Integer> archivesTypeIds = null;
		if(archivesType!=null && archivesType.getChildArchivesTypes()!=null && archivesType.getChildArchivesTypes().keySet()!=null && archivesType.getChildArchivesTypes().keySet().size()>=1) {
			archivesTypeIds = new ArrayList<Integer>();
			for(Integer archivesTypeId : archivesType.getChildArchivesTypes().keySet()) {
				archivesTypeIds.add(archivesTypeId);
				List<Integer> integers = getChild(archivesType.getChildArchivesTypes().get(archivesTypeId));
				if(integers!=null && integers.size()>=1) {
					archivesTypeIds.addAll(integers);
				}
			}
		}
		return archivesTypeIds;
	}
	
	/**
	 * 获取档案类型的一级档案类型 
	 * @return
	 */
	public static ArchivesType getTopArchivesType(ArchivesType archivesType) {
		if(archivesType!=null && archivesType.getParentArchivesType()!=null) {
			while(archivesType.getParentArchivesType() != null) {
				archivesType = archivesType.getParentArchivesType();
			}
		}
		return archivesType;
	}
}