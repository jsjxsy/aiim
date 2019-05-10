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

import com.orifound.aiim.dal.dao.sqlserver.impl.UserRightArchivesSecrecyDaoImpl;
import com.orifound.aiim.entity.ArchivesSecrecy;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 用户档案密级授权信息DAO实现类的测试类
 *
 */
public class UserRightArchivesSecrecyDaoImplTest
{

	//DAO实现类
	private static UserRightArchivesSecrecyDaoImpl DaoImpl = new UserRightArchivesSecrecyDaoImpl();
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
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.UserRightArchivesSecrecyDaoImpl#save(com.orifound.aiim.entity.UserRightArchivesSecrecy, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testSave()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.UserRightArchivesSecrecyDaoImpl#delete(com.orifound.aiim.entity.UserRightArchivesSecrecy, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testDelete()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.UserRightArchivesSecrecyDaoImpl#update(com.orifound.aiim.entity.UserRightArchivesSecrecy, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testUpdate()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.UserRightArchivesSecrecyDaoImpl#findByUserID(int, java.util.Map, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindByUserID()
	{
		int pUserID=2;
		Map<Integer, ArchivesSecrecy> userRightArchivesSecrecys=new HashMap<Integer, ArchivesSecrecy>();
		if (DaoImpl.findByUserID(pUserID, userRightArchivesSecrecys, pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else
		{
			System.out.println("testFindByUserID 查询结果集数量: "+userRightArchivesSecrecys.size());
			for (ArchivesSecrecy item : userRightArchivesSecrecys.values())
			{
				System.out.println(item.getName());
			}
		}
	}

}
