/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.*;

import com.orifound.aiim.bll.service.IRoleRightArchivesSecrecyManageService;
import com.orifound.aiim.bll.service.IRoleRightArchivesTypeManageService;
import com.orifound.aiim.bll.service.IRoleRightSystemFeatureManageService;
import com.orifound.aiim.bll.service.ISystemUserRightInitializeService;
import com.orifound.aiim.bll.service.IUserRightArchivesSecrecyManageService;
import com.orifound.aiim.bll.service.IUserRightArchivesTypeManageService;
import com.orifound.aiim.bll.service.IUserRightSystemFeatureManageService;
import com.orifound.aiim.bll.util.CommonUtil;
import com.orifound.aiim.entity.ArchivesSecrecy;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.SystemFeature;

/**
 * 系统用户权限初始化服务实现类
 * 
 */
public class SystemUserRightInitializeServiceImpl implements ISystemUserRightInitializeService
{

	/**
	 * 用户系统功能授权管理服务对象
	 */
	private IUserRightSystemFeatureManageService userRightSystemFeatureManageService = null;

	/**
	 * 设置属性值：用户系统功能授权管理服务对象
	 * 
	 * @param userRightSystemFeatureManageService
	 *            用户系统功能授权管理服务对象
	 */
	public void setUserRightSystemFeatureManageService(IUserRightSystemFeatureManageService userRightSystemFeatureManageService)
	{
		this.userRightSystemFeatureManageService = userRightSystemFeatureManageService;
	}

	/**
	 * 获取属性值：用户系统功能授权管理服务对象
	 * 
	 * @return 用户系统功能授权管理服务对象
	 */
	public IUserRightSystemFeatureManageService getUserRightSystemFeatureManageService()
	{
		return userRightSystemFeatureManageService;
	}

	/**
	 * 角色系统功能授权管理服务对象
	 */
	private IRoleRightSystemFeatureManageService roleRightSystemFeatureManageService = null;

	/**
	 * 设置属性值：角色系统功能授权管理服务对象
	 * 
	 * @param roleRightSystemFeatureManageService
	 *            角色系统功能授权管理服务对象
	 */
	public void setRoleRightSystemFeatureManageService(IRoleRightSystemFeatureManageService roleRightSystemFeatureManageService)
	{
		this.roleRightSystemFeatureManageService = roleRightSystemFeatureManageService;
	}

	/**
	 * 获取属性值：角色系统功能授权管理服务对象
	 * 
	 * @return 角色系统功能授权管理服务对象
	 */
	public IRoleRightSystemFeatureManageService getRoleRightSystemFeatureManageService()
	{
		return roleRightSystemFeatureManageService;
	}

	/**
	 * 用户档案资源授权管理服务对象
	 */
	private IUserRightArchivesTypeManageService userRightArchivesTypeManageService = null;

	/**
	 * 设置属性值：用户档案资源授权管理服务对象
	 * 
	 * @param userRightArchivesTypeManageService
	 *            用户档案资源授权管理服务对象
	 */
	public void setUserRightArchivesTypeManageService(IUserRightArchivesTypeManageService userRightArchivesTypeManageService)
	{
		this.userRightArchivesTypeManageService = userRightArchivesTypeManageService;
	}

	/**
	 * 获取属性值：用户档案资源授权管理服务对象
	 * 
	 * @return 用户档案资源授权管理服务对象
	 */
	public IUserRightArchivesTypeManageService getUserRightArchivesTypeManageService()
	{
		return userRightArchivesTypeManageService;
	}

	/**
	 * 角色档案资源授权管理服务对象
	 */
	private IRoleRightArchivesTypeManageService roleRightArchivesTypeManageService = null;

	/**
	 * 设置属性值：角色档案资源授权管理服务对象
	 * 
	 * @param roleRightArchivesTypeManageService
	 *            角色档案资源授权管理服务对象
	 */
	public void setRoleRightArchivesTypeManageService(IRoleRightArchivesTypeManageService roleRightArchivesTypeManageService)
	{
		this.roleRightArchivesTypeManageService = roleRightArchivesTypeManageService;
	}

	/**
	 * 获取属性值：角色档案资源授权管理服务对象
	 * 
	 * @return 角色档案资源授权管理服务对象
	 */
	public IRoleRightArchivesTypeManageService getRoleRightArchivesTypeManageService()
	{
		return roleRightArchivesTypeManageService;
	}

	/**
	 * 用户档案密级授权管理服务对象
	 */
	private IUserRightArchivesSecrecyManageService userRightArchivesSecrecyManageService = null;

	/**
	 * 设置属性值：用户档案密级授权管理服务对象
	 * 
	 * @param userRightArchivesSecrecyManageService
	 *            用户档案密级授权管理服务对象
	 */
	public void setUserRightArchivesSecrecyManageService(IUserRightArchivesSecrecyManageService userRightArchivesSecrecyManageService)
	{
		this.userRightArchivesSecrecyManageService = userRightArchivesSecrecyManageService;
	}

	/**
	 * 获取属性值：用户档案密级授权管理服务对象
	 * 
	 * @return 用户档案密级授权管理服务对象
	 */
	public IUserRightArchivesSecrecyManageService getUserRightArchivesSecrecyManageService()
	{
		return userRightArchivesSecrecyManageService;
	}

	/**
	 * 角色档案密级授权管理服务对象
	 */
	private IRoleRightArchivesSecrecyManageService roleRightArchivesSecrecyManageService = null;

	/**
	 * 设置属性值：角色档案密级授权管理服务对象
	 * 
	 * @param roleRightArchivesSecrecyManageService
	 *            角色档案密级授权管理服务对象
	 */
	public void setRoleRightArchivesSecrecyManageService(IRoleRightArchivesSecrecyManageService roleRightArchivesSecrecyManageService)
	{
		this.roleRightArchivesSecrecyManageService = roleRightArchivesSecrecyManageService;
	}

	/**
	 * 获取属性值：角色档案密级授权管理服务对象
	 * 
	 * @return 角色档案密级授权管理服务对象
	 */
	public IRoleRightArchivesSecrecyManageService getRoleRightArchivesSecrecyManageService()
	{
		return roleRightArchivesSecrecyManageService;
	}

	/**
	 * 检查相关的业务逻辑对象依赖注入（BLL Depandency Injection）
	 * 
	 * @param pErrInfo
	 *            处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkBllInjection(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			// 检查是否有进行用户系统功能授权管理的BLL业务逻辑对象的依赖注入
			pErrPos = 1;
			if (userRightSystemFeatureManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("用户系统功能授权管理的BLL业务逻辑对象非法为空，请检查是否有进行依赖注入或赋值。");
			}

			// 检查是否有进行角色系统功能授权管理的BLL业务逻辑对象的依赖注入
			if (roleRightSystemFeatureManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("角色系统功能授权管理的BLL业务逻辑对象非法为空，请检查是否有进行依赖注入或赋值。");
			}

			// 检查是否有进行用户档案资源授权管理的BLL业务逻辑对象的依赖注入
			if (userRightArchivesTypeManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("用户档案资源授权管理的BLL业务逻辑对象非法为空，请检查是否有进行依赖注入或赋值。");
			}

			// 检查是否有进行角色档案资源授权管理的BLL业务逻辑对象的依赖注入
			if (roleRightArchivesTypeManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("角色档案资源授权管理的BLL业务逻辑对象非法为空，请检查是否有进行依赖注入或赋值。");
			}

			// 检查是否有进行用户档案密级授权管理的BLL业务逻辑对象的依赖注入
			if (userRightArchivesSecrecyManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("用户档案密级授权管理的BLL业务逻辑对象非法为空，请检查是否有进行依赖注入或赋值。");
			}

			// 检查是否有进行角色档案密级授权管理的BLL业务逻辑对象的依赖注入
			if (roleRightArchivesSecrecyManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("角色档案密级授权管理的BLL业务逻辑对象非法为空，请检查是否有进行依赖注入或赋值。");
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
		}

		return pFlag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.orifound.aiim.bll.service.ISystemUserRightInitializeService#
	 * findRightSystemFeatureMenusByUserID(int, int[], java.util.LinkedHashMap,
	 * com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findRightSystemFeatureMenusByUserID(int pUserID, int[] pRolesID, LinkedHashMap<String, SystemFeature> systemFeatureMenus, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		LinkedHashMap<String, SystemFeature> userRightMenus = null;
		LinkedHashMap<String, SystemFeature> roleRightMenus = null;

		try
		{
			pErrPos = 1;
			// 检查是否有进行相关BLL业务逻辑对象的依赖注入
			if (checkBllInjection(pErrInfo) == false)
			{
				pFlag = false;
			}

			// 调用用户系统功能授权管理服务接口获取直接对当前用户授权的系统菜单集合
			if (pFlag)
			{
				pErrPos = 2;
				userRightMenus = new LinkedHashMap<String, SystemFeature>();
				if (userRightSystemFeatureManageService.findRightSystemFeatureMenusByUserID(pUserID, userRightMenus, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "获取用户的系统功能菜单授权信息失败: ");
				}
			}

			// 调用角色系统功能授权管理服务接口获取直接对当前用户所属角色授权的系统菜单集合
			if (pFlag)
			{
				if (pRolesID.length > 0)
				{
					pErrPos = 4;
					roleRightMenus = new LinkedHashMap<String, SystemFeature>();
					if (roleRightSystemFeatureManageService.findRightSystemFeatureMenusByRolesID(pRolesID, roleRightMenus, pErrInfo) == false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0, "获取角色的系统功能菜单授权信息失败: ");
					}
				}
			}

			// 合并用户和角色的系统菜单功能授权集合
			if (pFlag)
			{
				// 如果有角色信息，则合并用户和角色授权的系统功能菜单集合
				if (pRolesID.length > 0)
				{
					pErrPos = 5;
					if (combineSystemFeatureMenus(userRightMenus, roleRightMenus, systemFeatureMenus, pErrInfo) == false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0, "合并用户与角色的系统功能菜单授权信息失败: ");
					}
				}
				else
				{
					pErrPos = 6;
					// 如果没有角色信息，则直接把对用户授权的系统功能菜单集合作为结果返回
					for (SystemFeature item : userRightMenus.values())
					{
						systemFeatureMenus.put(item.getUclKey(), item);
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
			userRightMenus = null;
			roleRightMenus = null;
		}

		return pFlag;
	}

	/**
	 * 合并两个系统功能菜单集合
	 * 
	 * @param menus1
	 *            要合并的系统菜单集合1
	 * @param menus2
	 *            要合并的系统菜单集合2
	 * @param combinedMenus
	 *            合并后的系统菜单集合，以UclKey作为集合键，返回的是树状结构的集合，可访问下级功能菜单属性
	 * @param pErrInfo
	 *            处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean combineSystemFeatureMenus(LinkedHashMap<String, SystemFeature> menus1, LinkedHashMap<String, SystemFeature> menus2,
			LinkedHashMap<String, SystemFeature> combinedMenus, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{

			pErrPos = 1;
			if (menus1 == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("menus1参数非法为空。");
			}
			else
			{
				if (menus2 == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("menus2参数非法为空。");
				}
			}

			// 先把menu1作为返回结果
			if (pFlag)
			{
				if (menus1.size() > 0)
				{
					pErrPos = 2;
					for (SystemFeature item : menus1.values())
					{
						combinedMenus.put(item.getUclKey(), item);
					}
				}
			}

			// 开始合并menu2
			if (pFlag)
			{
				if (menus2.size() > 0)
				{
					pErrPos = 3;
					for (SystemFeature item : menus2.values())
					{
						// 当前返回结果中不存在的就合并加入作为结果返回
						if (combinedMenus.containsKey(item.getUclKey()) == false)
						{
							combinedMenus.put(item.getUclKey(), item);
						}
						// 存在的话则继续合并其下级菜单项
						else
						{
							String pKey = item.getUclKey();
							LinkedHashMap<String, SystemFeature> childMenus1 = menus1.get(pKey).getChildSystemFeatures();
							LinkedHashMap<String, SystemFeature> childMenus2 = menus2.get(pKey).getChildSystemFeatures();

							// 如果childMenus1不为null，而childMenus2为空，则无需合并了，因为childMenus1已经在返回结果中了

							// 如果childMenus1为null，childMenus2也为空，则无需合并

							// 如果childMenus1为null，而childMenus2不为空，则将childMenus2直接合并
							if (childMenus1 == null && childMenus2 != null)
							{
								pErrPos = 4;
								if (childMenus2.size() > 0)
								{
									combinedMenus.get(pKey).setChildSystemFeatures(childMenus2);
								}
							}

							// 如果childMenus1不为null，childMenus2也不为空，则需要递归合并
							if (childMenus1 != null && childMenus2 != null)
							{
								pErrPos = 5;
								LinkedHashMap<String, SystemFeature> childCombinedMenus = new LinkedHashMap<String, SystemFeature>();
								if (combineSystemFeatureMenus(childMenus1, childMenus2, childCombinedMenus, pErrInfo) == false)
								{
									pFlag = false;
									pErrInfo.getContent().insert(0, "合并系统功能菜单集合失败: ");
								}
								else
								{
									// 返回合并结果
									combinedMenus.get(pKey).setChildSystemFeatures(childCombinedMenus);
								}

								childCombinedMenus = null;
							}

							childMenus1 = null;
							childMenus2 = null;
						}

						// 合并出错就跳出循环
						if (pFlag == false)
						{
							break;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.orifound.aiim.bll.service.ISystemUserRightInitializeService#
	 * findRightSystemFeaturesByUserID(int, int[], java.util.Map,
	 * com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findRightSystemFeaturesByUserID(int pUserID, int[] pRolesID, Map<String, SystemFeature> systemFeatures, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		Map<String, SystemFeature> userRightSystemFeatures = null;
		Map<String, SystemFeature> roleRightSystemFeatures = null;

		try
		{
			pErrPos = 1;
			// 检查是否有进行相关BLL业务逻辑对象的依赖注入
			if (checkBllInjection(pErrInfo) == false)
			{
				pFlag = false;
			}

			// 调用用户系统功能授权管理服务接口获取直接对当前用户授权的系统功能集合
			if (pFlag)
			{
				pErrPos = 2;
				userRightSystemFeatures = new HashMap<String, SystemFeature>();
				if (userRightSystemFeatureManageService.findRightSystemFeaturesByUserID(pUserID, userRightSystemFeatures, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "获取用户的系统功能授权信息失败: ");
				}
			}

			// 调用角色系统功能授权管理服务接口获取直接对当前用户所属角色授权的系统菜单集合
			if (pFlag)
			{
				if (pRolesID.length > 0)
				{
					pErrPos = 4;
					roleRightSystemFeatures = new HashMap<String, SystemFeature>();
					if (roleRightSystemFeatureManageService.findRightSystemFeaturesByRolesID(pRolesID, roleRightSystemFeatures, pErrInfo) == false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0, "获取角色的系统功能授权信息失败: ");
					}
				}
			}

			// 合并用户和角色的系统功能授权集合（UCL--用户访问控制列表）
			if (pFlag)
			{
				// 先把对用户授权的系统功能集合作为结果返回
				pErrPos = 5;
				for (SystemFeature item : userRightSystemFeatures.values())
				{
					systemFeatures.put(item.getUclKey(), item);
				}

				// 如果有角色信息，则合并用户和角色的系统功能授权集合（UCL--用户访问控制列表）
				if (pRolesID.length > 0)
				{
					pErrPos = 6;
					for (SystemFeature item : roleRightSystemFeatures.values())
					{
						if (systemFeatures.containsKey(item.getUclKey()) == false)
						{
							systemFeatures.put(item.getUclKey(), item);
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
			userRightSystemFeatures = null;
			roleRightSystemFeatures = null;
		}

		return pFlag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.orifound.aiim.bll.service.ISystemUserRightInitializeService#
	 * findRightArchivesTypesByUserID(int, int[], java.util.LinkedHashMap,
	 * com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findRightArchivesTypesByUserID(int pUserID, int[] pRolesID, LinkedHashMap<Integer, ArchivesType> archivesTypes, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		LinkedHashMap<Integer, ArchivesType> userRightArchivesTypes = null;
		LinkedHashMap<Integer, ArchivesType> roleRightArchivesTypes = null;
		LinkedHashMap<Integer, ArchivesType> combinedArchivesTypes = null; // 保存合并后的档案资源授权集合，是平面结构的集合

		try
		{
			pErrPos = 1;
			// 检查是否有进行相关BLL业务逻辑对象的依赖注入
			if (checkBllInjection(pErrInfo) == false)
			{
				pFlag = false;
			}

			// 检查系统初始化器下面的档案分类集合是否有值
			if (pFlag)
			{
				if (CommonUtil.getSystemInitializer().getArchivesTypes() == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("系统初始化对象的树状档案分类集合非法为空，请先进行系统初始化操作。");
				}
				else
				{
					if (CommonUtil.getSystemInitializer().getArchivesTypes().size() == 0)
					{
						pFlag = false;
						pErrInfo.getContent().append("系统初始化对象的树状档案分类集合非法为空，请确定系统数据库中存在档案分类字典信息。");
					}
				}
			}

			// 调用用户档案资源授权管理服务接口获取直接对当前用户授权的档案资源集合
			if (pFlag)
			{
				pErrPos = 2;
				userRightArchivesTypes = new LinkedHashMap<Integer, ArchivesType>();
				if (userRightArchivesTypeManageService.findRightArchivesTypeByUserID(pUserID, userRightArchivesTypes, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "获取用户的档案资源授权信息失败: ");
				}
			}

			// 调用角色档案资源授权管理服务接口获取直接对当前用户所属角色授权的档案资源集合
			if (pFlag)
			{
				if (pRolesID.length > 0)
				{
					pErrPos = 4;
					roleRightArchivesTypes = new LinkedHashMap<Integer, ArchivesType>();
					if (roleRightArchivesTypeManageService.findRightArchivesTypeByRolesID(pRolesID, roleRightArchivesTypes, pErrInfo) == false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0, "获取角色的档案资源授权信息失败: ");
					}
				}
			}

			// 合并用户和角色的档案资源授权集合（平面结构的集合）
			if (pFlag)
			{
				// 先把对用户授权的档案资源集合作为合并结果
				combinedArchivesTypes = new LinkedHashMap<Integer, ArchivesType>();
				pErrPos = 5;
				for (ArchivesType item : userRightArchivesTypes.values())
				{
					combinedArchivesTypes.put(item.getID(), item);
				}

				// 如果有角色信息，则合并用户和角色的档案资源授权集合
				if (pRolesID.length > 0)
				{
					//将角色的授权信息合并至结果集中
					pErrPos = 6;
					for (ArchivesType item : roleRightArchivesTypes.values())
					{
						if (combinedArchivesTypes.containsKey(item.getID()) == false)
						{
							combinedArchivesTypes.put(item.getID(), item);
						}
						//如果结果集中已经存在该角色的授权信息，则合并其档案信息、原文电子文件、实物档案的授权信息
						else 
						{
							//用户授权和角色授权中只要有一个具备，就表示拥有访问权限
							boolean pRightFlag_ArchivesInfo=combinedArchivesTypes.get(item.getID()).getRightFlag_ArchivesInfo() || item.getRightFlag_ArchivesInfo();
							boolean pRightFlag_AttachedFile=combinedArchivesTypes.get(item.getID()).getRightFlag_AttachedFile() || item.getRightFlag_AttachedFile();
							boolean pRightFlag_PaperArchives=combinedArchivesTypes.get(item.getID()).getRightFlag_PaperArchives() || item.getRightFlag_PaperArchives();
							combinedArchivesTypes.get(item.getID()).setRightFlag_ArchivesInfo(pRightFlag_ArchivesInfo);
							combinedArchivesTypes.get(item.getID()).setRightFlag_AttachedFile(pRightFlag_AttachedFile);
							combinedArchivesTypes.get(item.getID()).setRightFlag_PaperArchives(pRightFlag_PaperArchives);
						}
					}
				}
			}

			// 将平面结构的档案资源授权集合转换为树状结构的档案资源授权集合
			if (pFlag)
			{
				if (CommonUtil.convertPlaneArchivesTypesToTree(combinedArchivesTypes, archivesTypes, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "将平面型档案分类集合转换为树状结构失败: ");
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
			userRightArchivesTypes = null;
			roleRightArchivesTypes = null;
			combinedArchivesTypes = null;
		}

		return pFlag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.orifound.aiim.bll.service.ISystemUserRightInitializeService#
	 * findRightArchivesSecrecysByUserID(int, int[], java.util.Map,
	 * com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findRightArchivesSecrecysByUserID(int pUserID, int[] pRolesID, Map<Integer, ArchivesSecrecy> archivesSecrecys, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		Map<Integer, ArchivesSecrecy> userRightArchivesSecrecys = null;
		Map<Integer, ArchivesSecrecy> roleRightArchivesSecrecys = null;

		try
		{
			pErrPos = 1;
			// 检查是否有进行相关BLL业务逻辑对象的依赖注入
			if (checkBllInjection(pErrInfo) == false)
			{
				pFlag = false;
			}

			// 调用用户档案密级授权管理服务接口获取直接对当前用户授权的档案密级集合
			if (pFlag)
			{
				pErrPos = 2;
				userRightArchivesSecrecys = new HashMap<Integer, ArchivesSecrecy>();
				if (userRightArchivesSecrecyManageService.findRightArchivesSecrecysByUserID(pUserID, userRightArchivesSecrecys, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "获取用户的档案密级授权信息失败: ");
				}
			}

			// 调用角色档案密级授权管理服务接口获取直接对当前用户所属角色授权的档案密级集合
			if (pFlag)
			{
				if (pRolesID.length > 0)
				{
					pErrPos = 4;
					roleRightArchivesSecrecys = new HashMap<Integer, ArchivesSecrecy>();
					if (roleRightArchivesSecrecyManageService.findRightArchivesSecrecysByRolesID(pRolesID, roleRightArchivesSecrecys, pErrInfo) == false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0, "获取角色的档案密级授权信息失败: ");
					}
				}
			}

			// 合并用户和角色的档案密级授权集合
			if (pFlag)
			{
				// 先把对用户授权的档案密级集合作为结果返回
				pErrPos = 5;
				for (ArchivesSecrecy item : userRightArchivesSecrecys.values())
				{
					archivesSecrecys.put(item.getID(), item);
				}

				// 如果有角色信息，则合并用户和角色的档案密级授权集合
				if (pRolesID.length > 0)
				{
					//将角色的授权信息合并至结果集中
					pErrPos = 6;
					for (ArchivesSecrecy item : roleRightArchivesSecrecys.values())
					{
						if (archivesSecrecys.containsKey(item.getID()) == false)
						{
							archivesSecrecys.put(item.getID(), item);
						}
						//如果结果集中已经存在该角色的授权信息，则合并其档案信息、原文电子文件、实物档案的授权信息
						else 
						{
							//用户授权和角色授权中只要有一个具备，就表示拥有访问权限
							boolean pRightFlag_ArchivesInfo=archivesSecrecys.get(item.getID()).getRightFlag_ArchivesInfo() || item.getRightFlag_ArchivesInfo();
							boolean pRightFlag_AttachedFile=archivesSecrecys.get(item.getID()).getRightFlag_AttachedFile() || item.getRightFlag_AttachedFile();
							boolean pRightFlag_PaperArchives=archivesSecrecys.get(item.getID()).getRightFlag_PaperArchives() || item.getRightFlag_PaperArchives();
							archivesSecrecys.get(item.getID()).setRightFlag_ArchivesInfo(pRightFlag_ArchivesInfo);
							archivesSecrecys.get(item.getID()).setRightFlag_AttachedFile(pRightFlag_AttachedFile);
							archivesSecrecys.get(item.getID()).setRightFlag_PaperArchives(pRightFlag_PaperArchives);
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
			userRightArchivesSecrecys = null;
			roleRightArchivesSecrecys = null;
		}

		return pFlag;
	}

}
