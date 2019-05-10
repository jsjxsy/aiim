/**
 * 
 */
package com.orifound.aiim.bll.service.impl.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.orifound.aiim.bll.service.impl.RoleRightArchivesSecrecyManageServiceImpl;
import com.orifound.aiim.bll.service.impl.RoleRightArchivesTypeManageServiceImpl;
import com.orifound.aiim.bll.service.impl.RoleRightSystemFeatureManageServiceImpl;
import com.orifound.aiim.bll.service.impl.SystemUserRightInitializeServiceImpl;
import com.orifound.aiim.bll.service.impl.UserChargeDepartmentInfoManageServiceImpl;
import com.orifound.aiim.bll.service.impl.UserChargeUserInfoManageServiceImpl;
import com.orifound.aiim.bll.service.impl.UserInfoManageServiceImpl;
import com.orifound.aiim.bll.service.impl.UserRightArchivesSecrecyManageServiceImpl;
import com.orifound.aiim.bll.service.impl.UserRightArchivesTypeManageServiceImpl;
import com.orifound.aiim.bll.service.impl.UserRightSystemFeatureManageServiceImpl;
import com.orifound.aiim.bll.service.impl.UserRolesInfoManageServiceImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.RoleRightArchivesSecrecyDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.RoleRightArchivesTypeDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.RoleRightSystemFeatureDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.UserChargeDepartmentInfoDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.UserChargeUserInfoDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.UserInfoDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.UserRightArchivesSecrecyDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.UserRightArchivesTypeDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.UserRightSystemFeatureDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.UserRolesInfoDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.test.AIIMDataSource;
import com.orifound.aiim.entity.ArchivesSecrecy;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.SystemFeature;
import com.orifound.aiim.entity.UserChargeDepartmentInfo;
import com.orifound.aiim.entity.UserChargeUserInfo;
import com.orifound.aiim.entity.UserInfo;

/**
 * 用户信息管理服务测试类
 *
 */
public class UserInfoManageServiceImplTest
{

	//业务逻辑实现类
	private static UserInfoManageServiceImpl ServiceImpl = new UserInfoManageServiceImpl();
	//错误信息对象
	private static ErrInfo pErrInfo = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		//DAO注入
		ServiceImpl.setUserInfoDao(new UserInfoDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		
		//BLL注入
		
		//用户代工信息管理服务对象
		ServiceImpl.setUserChargeUserInfoManageService(new UserChargeUserInfoManageServiceImpl(new UserChargeUserInfoDaoImpl(AIIMDataSource.getInstance().getDataSource())));
		
		//业务专员负责部门信息管理服务对象
		ServiceImpl.setUserChargeDepartmentInfoManageService(new UserChargeDepartmentInfoManageServiceImpl(new UserChargeDepartmentInfoDaoImpl(AIIMDataSource.getInstance().getDataSource())));
		
		//用户角色信息管理服务对象
		ServiceImpl.setUserRolesInfoManageService(new UserRolesInfoManageServiceImpl(new UserRolesInfoDaoImpl(AIIMDataSource.getInstance().getDataSource())));
		
		//系统用户权限初始化服务对象
		SystemUserRightInitializeServiceImpl systemUserRightInitializeServiceImpl=new SystemUserRightInitializeServiceImpl();
		systemUserRightInitializeServiceImpl.setUserRightSystemFeatureManageService(new UserRightSystemFeatureManageServiceImpl(new UserRightSystemFeatureDaoImpl(AIIMDataSource.getInstance().getDataSource())));
		systemUserRightInitializeServiceImpl.setRoleRightSystemFeatureManageService(new RoleRightSystemFeatureManageServiceImpl(new RoleRightSystemFeatureDaoImpl(AIIMDataSource.getInstance().getDataSource())));
		systemUserRightInitializeServiceImpl.setUserRightArchivesTypeManageService(new UserRightArchivesTypeManageServiceImpl(new UserRightArchivesTypeDaoImpl(AIIMDataSource.getInstance().getDataSource())));
		systemUserRightInitializeServiceImpl.setRoleRightArchivesTypeManageService(new RoleRightArchivesTypeManageServiceImpl(new RoleRightArchivesTypeDaoImpl(AIIMDataSource.getInstance().getDataSource())));
		systemUserRightInitializeServiceImpl.setUserRightArchivesSecrecyManageService(new UserRightArchivesSecrecyManageServiceImpl(new UserRightArchivesSecrecyDaoImpl(AIIMDataSource.getInstance().getDataSource())));
		systemUserRightInitializeServiceImpl.setRoleRightArchivesSecrecyManageService(new RoleRightArchivesSecrecyManageServiceImpl(new RoleRightArchivesSecrecyDaoImpl(AIIMDataSource.getInstance().getDataSource())));
		ServiceImpl.setSystemUserRightInitializeService(systemUserRightInitializeServiceImpl);
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
		ServiceImpl = null;
		pErrInfo = null;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		pErrInfo = new ErrInfo();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{
		pErrInfo = null;
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.UserInfoManageServiceImpl#addUserToDeparment(com.orifound.aiim.entity.UserInfo, com.orifound.aiim.entity.DepartmentInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testAddUserToDeparment()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.UserInfoManageServiceImpl#addUsersToDeparment(java.util.List, com.orifound.aiim.entity.DepartmentInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testAddUsersToDeparment()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.UserInfoManageServiceImpl#deleteUserChargeDepartment(com.orifound.aiim.entity.UserInfo, com.orifound.aiim.entity.DepartmentInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testDeleteUserChargeDepartment()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.UserInfoManageServiceImpl#deleteUserChargeDepartments(com.orifound.aiim.entity.UserInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testDeleteUserChargeDepartments()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.UserInfoManageServiceImpl#deleteUserInfo(com.orifound.aiim.entity.UserInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testDeleteUserInfo()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.UserInfoManageServiceImpl#findUsers(java.util.List, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindUsers()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.UserInfoManageServiceImpl#findAnonymousUser(com.orifound.aiim.entity.UserInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindAnonymousUser()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.UserInfoManageServiceImpl#findUserByIDCardNo(java.lang.String, com.orifound.aiim.entity.UserInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindUserByIDCardNo()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.UserInfoManageServiceImpl#findUserByRealName(java.lang.String, java.util.List, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindUserByRealName()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.UserInfoManageServiceImpl#findUserByUserID(java.lang.Integer, com.orifound.aiim.entity.UserInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindUserByUserID()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.UserInfoManageServiceImpl#findUsersByDepartmentID(int, java.util.List, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindUsersByDepartmentID()
	{
		fail("Not yet implemented");
	}
	
	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.UserInfoManageServiceImpl#LoginWithAnonymous(com.orifound.aiim.entity.UserInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testLoginWithAnonymous()
	{
		//系统初始化
		
		if (SystemInitializePrepare.initialize(pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else
		{
			UserInfo userInfo=new UserInfo();
			if (ServiceImpl.loginWithAnonymous(userInfo, pErrInfo)==false)
			{
				fail(pErrInfo.toShortString());
			}
			else
			{
				System.out.println("LoginWithAnonymous successfully");
				System.out.println("用户名: "+userInfo.getUserName());
				System.out.println("用户密码: "+userInfo.getUserPWD());
				
				//显示用户代工信息
				if (userInfo.getChargeUserInfos()!=null)
				{
					if (userInfo.getChargeUserInfos().size()>0)
					{
						System.out.println("用户代工信息: "+userInfo.getChargeUserInfos().size());
						for (UserChargeUserInfo item : userInfo.getChargeUserInfos())
						{
							System.out.println("--"+item.getChargedUserID());
						}
						
						System.out.println("");
					}
				}
				
				//业务专员负责的部门信息
				if (userInfo.getChargeDepartmentInfos()!=null)
				{
					if (userInfo.getChargeDepartmentInfos().size()>0)
					{
						System.out.println("负责的部门信息: "+userInfo.getChargeDepartmentInfos().size());
						for (UserChargeDepartmentInfo item : userInfo.getChargeDepartmentInfos().values())
						{
							System.out.println("--"+item.getName());
						}
						
						System.out.println("");
					}
				}
				
				//显示授权的系统功能菜单
				if (userInfo.getSystemMenus()!=null)
				{
					if (userInfo.getSystemMenus().size()>0)
					{
						System.out.println("授权的系统功能菜单: "+userInfo.getSystemMenus().size());
						for (SystemFeature item : userInfo.getSystemMenus().values())
						{
							System.out.println("--"+item.getName());
							
							if (item.getChildSystemFeatures()!=null)
							{
								for (SystemFeature childMenu : item.getChildSystemFeatures().values())
								{
									System.out.println("----"+childMenu.getName());
									
									if (childMenu.getChildSystemFeatures()!=null)
									{
										for (SystemFeature endMenu : childMenu.getChildSystemFeatures().values())
										{
											System.out.println("------"+endMenu.getName());
										}
									}
								}
							}
						}
						
						System.out.println("");
					}
				}
				
				//显示系统功能授权信息：UCL
				if (userInfo.getUCL()!=null)
				{
					if (userInfo.getUCL().size()>0)
					{
						System.out.println("授权的系统功能（UCL）: "+userInfo.getUCL().size());
						for (SystemFeature item : userInfo.getUCL().values())
						{
							System.out.println("--"+item.getName());
						}
						
						System.out.println("");
					}
				}
				
				//显示档案资源授权信息
				if (userInfo.getArchivesTypes()!=null)
				{
					System.out.println("授权的档案资源: " +userInfo.getArchivesTypes().size()+" 个");
					for (ArchivesType item : userInfo.getArchivesTypes().values())
					{
						System.out.println("--"+item.getFullCode()+","+item.getFullName());
						
						if (item.getChildArchivesTypes()!=null)
						{
							for (ArchivesType item1 : item.getChildArchivesTypes().values())
							{
								System.out.println("----"+item1.getFullCode()+","+item1.getFullName());
								
								if (item1.getChildArchivesTypes()!=null)
								{
									for (ArchivesType item2 : item1.getChildArchivesTypes().values())
									{
										System.out.println("------"+item2.getFullCode()+","+item2.getFullName());
										
									}
								}
							}
						}
					}
					
					System.out.println("");
				}
				
				//显示档案密级授权
				if (userInfo.getArchivesSecrecys()!=null)
				{
					if (userInfo.getArchivesSecrecys().size()>0)
					{
						System.out.println("授权的档案密级: " +userInfo.getArchivesSecrecys().size()+" 个");
						for (ArchivesSecrecy item : userInfo.getArchivesSecrecys().values())
						{
							System.out.println("--"+item.getName());
						}
					}
				}
			}
			
		}
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.UserInfoManageServiceImpl#login(com.orifound.aiim.entity.UserInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testLogin()
	{
		//系统初始化
		if (SystemInitializePrepare.initialize(pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else
		{
			UserInfo userInfo=new UserInfo();
			userInfo.setUserName("aiim1");
			userInfo.setUserPWD("aiim1");
			if (ServiceImpl.login(userInfo, pErrInfo)==false)
			{
				fail(pErrInfo.toShortString());
			}
			else
			{
				System.out.println("login successfully");
				
				//显示用户代工信息
				if (userInfo.getChargeUserInfos()!=null)
				{
					if (userInfo.getChargeUserInfos().size()>0)
					{
						System.out.println("用户代工信息: "+userInfo.getChargeUserInfos().size());
						for (UserChargeUserInfo item : userInfo.getChargeUserInfos())
						{
							System.out.println("--"+item.getChargedUserID());
						}
						
						System.out.println("");
					}
				}
				
				//业务专员负责的部门信息
				if (userInfo.getChargeDepartmentInfos()!=null)
				{
					if (userInfo.getChargeDepartmentInfos().size()>0)
					{
						System.out.println("负责的部门信息: "+userInfo.getChargeDepartmentInfos().size());
						for (UserChargeDepartmentInfo item : userInfo.getChargeDepartmentInfos().values())
						{
							System.out.println("--"+item.getName());
						}
						
						System.out.println("");
					}
				}
				
				//显示授权的系统功能菜单
				if (userInfo.getSystemMenus()!=null)
				{
					if (userInfo.getSystemMenus().size()>0)
					{
						System.out.println("授权的系统功能菜单: "+userInfo.getSystemMenus().size());
						for (SystemFeature item : userInfo.getSystemMenus().values())
						{
							System.out.println("--"+item.getID()+","+item.getName());
							
							if (item.getChildSystemFeatures()!=null)
							{
								for (SystemFeature childMenu : item.getChildSystemFeatures().values())
								{
									System.out.println("----"+childMenu.getID()+","+childMenu.getName());
									
									if (childMenu.getChildSystemFeatures()!=null)
									{
										for (SystemFeature endMenu : childMenu.getChildSystemFeatures().values())
										{
											System.out.println("------"+endMenu.getID()+","+endMenu.getName());
										}
									}
								}
							}
						}
						
						System.out.println("");
					}
				}
				
				//显示系统功能授权信息：UCL
				if (userInfo.getUCL()!=null)
				{
					if (userInfo.getUCL().size()>0)
					{
						System.out.println("授权的系统功能（UCL）: "+userInfo.getUCL().size());
						for (SystemFeature item : userInfo.getUCL().values())
						{
							System.out.println("--"+item.getID()+"," +item.getName());
						}
						
						System.out.println("");
					}
				}
				
				//显示档案资源授权信息
				if (userInfo.getArchivesTypes()!=null)
				{
					System.out.println("授权的档案资源: " +userInfo.getArchivesTypes().size()+" 个");
					for (ArchivesType item : userInfo.getArchivesTypes().values())
					{
						System.out.println("--"+item.getFullCode()+","+item.getFullName());
						
						if (item.getChildArchivesTypes()!=null)
						{
							for (ArchivesType item1 : item.getChildArchivesTypes().values())
							{
								System.out.println("----"+item1.getFullCode()+","+item1.getFullName()+",原文权限："+item1.getRightFlag_AttachedFile());
								
								if (item1.getChildArchivesTypes()!=null)
								{
									for (ArchivesType item2 : item1.getChildArchivesTypes().values())
									{
										System.out.println("------"+item2.getFullCode()+","+item2.getFullName()+",原文权限："+item2.getRightFlag_AttachedFile());
										
									}
								}
							}
						}
					}
					
					System.out.println("");
				}
				
				//显示档案密级授权
				if (userInfo.getArchivesSecrecys()!=null)
				{
					if (userInfo.getArchivesSecrecys().size()>0)
					{
						System.out.println("授权的档案密级: " +userInfo.getArchivesSecrecys().size()+" 个");
						for (ArchivesSecrecy item : userInfo.getArchivesSecrecys().values())
						{
							System.out.println("--"+item.getID()+","+item.getName()+",原文权限："+item.getRightFlag_AttachedFile());
						}
					}
				}
			}
			
		}
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.UserInfoManageServiceImpl#logout(com.orifound.aiim.entity.UserInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testLogout()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.UserInfoManageServiceImpl#saveUserChargeDepartment(com.orifound.aiim.entity.UserInfo, com.orifound.aiim.entity.DepartmentInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testSaveUserChargeDepartment()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.UserInfoManageServiceImpl#saveUserChargeDepartments(com.orifound.aiim.entity.UserInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testSaveUserChargeDepartments()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.UserInfoManageServiceImpl#saveUserInfo(com.orifound.aiim.entity.UserInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testSaveUserInfo()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.UserInfoManageServiceImpl#updateUserInfo(com.orifound.aiim.entity.UserInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testUpdateUserInfo()
	{
		fail("Not yet implemented");
	}
	
	

}
