/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl.test;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.orifound.aiim.dal.dao.sqlserver.impl.UserChargeDepartmentInfoDaoImpl;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserChargeDepartmentInfo;

/**
 * 业务专业负责部门信息DAO实现的测试类
 *
 */
public class UserChargeDepartmentInfoDaoImplTest
{

	//DAO实现类
	private static UserChargeDepartmentInfoDaoImpl DaoImpl = new UserChargeDepartmentInfoDaoImpl();
	//错误信息对象
	private static ErrInfo pErrInfo = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		//数据源注入
		DaoImpl.setDataSource(AIIMDataSource.getInstance().getDataSource());
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
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.UserChargeDepartmentInfoDaoImpl#save(com.orifound.aiim.entity.UserChargeDepartmentInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testSave()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.UserChargeDepartmentInfoDaoImpl#delete(com.orifound.aiim.entity.UserChargeDepartmentInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testDelete()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.UserChargeDepartmentInfoDaoImpl#update(com.orifound.aiim.entity.UserChargeDepartmentInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testUpdate()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.UserChargeDepartmentInfoDaoImpl#findByUserID(int, java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindByUserID()
	{
		int pUserID=6;
		LinkedHashMap<Integer, UserChargeDepartmentInfo> userChargeDepartmentInfos=new LinkedHashMap<Integer, UserChargeDepartmentInfo>();
		if (DaoImpl.findByUserID(pUserID, userChargeDepartmentInfos, pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else
		{
			System.out.println("testFindByUserID 查询结果集数量: "+userChargeDepartmentInfos.size());
		}
	}

}
