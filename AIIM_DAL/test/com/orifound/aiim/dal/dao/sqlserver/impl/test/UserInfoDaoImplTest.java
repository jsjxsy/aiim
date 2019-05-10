/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.orifound.aiim.dal.dao.sqlserver.impl.UserInfoDaoImpl;
import com.orifound.aiim.entity.EnumSystemRole;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserInfo;

/**
 * 用户信息DAO实现的测试类
 *
 */
public class UserInfoDaoImplTest
{

	//DAO实现类
	private static UserInfoDaoImpl DaoImpl = new UserInfoDaoImpl();
	//错误信息对象
	private static ErrInfo pErrInfo = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		//数据源注入
		DaoImpl.setDataSource(AIIMDataSourceTYB.getInstance().getDataSource());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
		DaoImpl = null;
		pErrInfo = null;
	}

	@Before
	public void setUp() throws Exception
	{
		pErrInfo = new ErrInfo();
	}

	@After
	public void tearDown() throws Exception
	{
		pErrInfo = null;
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.UserInfoDaoImpl#delete(com.orifound.aiim.entity.UserInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testDelete()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.UserInfoDaoImpl#findAll(java.util.List, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindAll()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.UserInfoDaoImpl#findAnonymous(com.orifound.aiim.entity.UserInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindAnonymous()
	{
		UserInfo userInfo=new UserInfo();
		if (DaoImpl.findAnonymous(userInfo, pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else
		{
			System.out.println("testFindByUserName 用户名称: "+userInfo.getUserName());
		}
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.UserInfoDaoImpl#findByDepartmentID(int, java.util.List, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindByDepartmentID()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.UserInfoDaoImpl#findByIDCard(int, java.lang.String, com.orifound.aiim.entity.UserInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindByIDCard()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.UserInfoDaoImpl#findByRealName(java.lang.String, java.util.List, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindByRealName()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.UserInfoDaoImpl#findByUserName(java.lang.String, com.orifound.aiim.entity.UserInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindByUserName()
	{
		String pUserName="aiim2";
		UserInfo userInfo=new UserInfo();
		if (DaoImpl.findByUserName(pUserName, userInfo, pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else
		{
			System.out.println("testFindByUserName 用户密码: "+userInfo.getUserPWD());
		}
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.UserInfoDaoImpl#save(com.orifound.aiim.entity.UserInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testSave()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.UserInfoDaoImpl#update(com.orifound.aiim.entity.UserInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testUpdate()
	{
		fail("Not yet implemented");
	}
	
	/**
	 * 查询所有具有业务指导室角色的人员信息集合
	 */
	@Test
	public void findbusinessGuidsTest() {
		List<UserInfo> userInfos = new ArrayList<UserInfo>();
		if (DaoImpl.findBusinessGuids(userInfos,EnumSystemRole.业务指导专员角色, pErrInfo) == false) {
			fail(pErrInfo.toShortString());
		}
		//业务指导室人员集合
		for(UserInfo userInfo : userInfos) {
			System.out.println(userInfo.getUserID()+":"+userInfo.getRealName());
		}
	
	}
	
	/**
	 * 查询业务指导室指导的所有档案兼职人员
	 */
	@Test
	public void findpartTimePersonsTest() {
		List<UserInfo> businessGuids = new ArrayList<UserInfo>();
		if (DaoImpl.findBusinessGuids(businessGuids,EnumSystemRole.业务指导专员角色, pErrInfo) == false) {
			fail(pErrInfo.toShortString());
		}
		
	}

}
