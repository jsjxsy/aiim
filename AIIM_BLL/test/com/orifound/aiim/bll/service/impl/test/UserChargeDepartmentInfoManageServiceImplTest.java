/**
 * 
 */
package com.orifound.aiim.bll.service.impl.test;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.orifound.aiim.bll.service.impl.UserChargeDepartmentInfoManageServiceImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.UserChargeDepartmentInfoDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.test.AIIMDataSource;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserChargeDepartmentInfo;

/**
 * 业务专员负责部门信息管理服务测试类
 *
 */
public class UserChargeDepartmentInfoManageServiceImplTest
{

	//业务逻辑实现类
	private static UserChargeDepartmentInfoManageServiceImpl ServiceImpl = new UserChargeDepartmentInfoManageServiceImpl();
	//错误信息对象
	private static ErrInfo pErrInfo = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		//DAO注入
		ServiceImpl.setUserChargeDepartmentInfoDao(new UserChargeDepartmentInfoDaoImpl(AIIMDataSource.getInstance().getDataSource()));
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
	 * Test method for {@link com.orifound.aiim.bll.service.impl.UserChargeDepartmentInfoManageServiceImpl#getUserChargeDepartmentInfoDao()}.
	 */
	@Test
	public void testGetUserChargeDepartmentInfoDao()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.UserChargeDepartmentInfoManageServiceImpl#setUserChargeDepartmentInfoDao(com.orifound.aiim.dal.dao.IUserChargeDepartmentInfoDao)}.
	 */
	@Test
	public void testSetUserChargeDepartmentInfoDao()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.UserChargeDepartmentInfoManageServiceImpl#saveUserChargeDepartmentInfo(com.orifound.aiim.entity.UserChargeDepartmentInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testSaveUserChargeDepartmentInfo()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.UserChargeDepartmentInfoManageServiceImpl#deleteUserChargeDepartmentInfo(com.orifound.aiim.entity.UserChargeDepartmentInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testDeleteUserChargeDepartmentInfo()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.UserChargeDepartmentInfoManageServiceImpl#updateUserChargeDepartmentInfo(com.orifound.aiim.entity.UserChargeDepartmentInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testUpdateUserChargeDepartmentInfo()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.UserChargeDepartmentInfoManageServiceImpl#findUserChargeDepartmentInfosByUserID(int, java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindUserChargeDepartmentInfosByUserID()
	{
		int pUserID=1;
		LinkedHashMap<Integer, UserChargeDepartmentInfo> userChargeDepartmentInfos=new LinkedHashMap<Integer, UserChargeDepartmentInfo>();
		if (ServiceImpl.findUserChargeDepartmentInfosByUserID(pUserID, userChargeDepartmentInfos, pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else
		{
			System.out.println("testFindUserChargeDepartmentInfosByUserID 查询结果集数量: "+userChargeDepartmentInfos.size());
		}
	}

}
