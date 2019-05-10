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

import com.orifound.aiim.dal.dao.sqlserver.impl.RetentionPeriodDaoImpl;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.RetentionPeriod;

/**
 * 保管期限信息DAO实现的测试类
 *
 */
public class RetentionPeriodDaoImplTest
{

	//DAO实现类
	private static RetentionPeriodDaoImpl DaoImpl = new RetentionPeriodDaoImpl();
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
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.RetentionPeriodDaoImpl#getRetentionPeriodDao()}.
	 */
	@Test
	public void testGetRetentionPeriodDao()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.RetentionPeriodDaoImpl#setRetentionPeriodDao(com.orifound.aiim.dal.dao.IRetentionPeriodDao)}.
	 */
	@Test
	public void testSetRetentionPeriodDao()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.RetentionPeriodDaoImpl#save(com.orifound.aiim.entity.RetentionPeriod, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testSave()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.RetentionPeriodDaoImpl#delete(com.orifound.aiim.entity.RetentionPeriod, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testDelete()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.RetentionPeriodDaoImpl#update(com.orifound.aiim.entity.RetentionPeriod, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testUpdate()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.RetentionPeriodDaoImpl#findAll(java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindAll()
	{
		LinkedHashMap<Integer, RetentionPeriod> retentionPeriods=new LinkedHashMap<Integer, RetentionPeriod>();
		if (DaoImpl.findAll(retentionPeriods, pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else
		{
			System.out.println("testFindAll 查询结果集数量："+retentionPeriods.size());
			for (RetentionPeriod item : retentionPeriods.values())
			{
				System.out.println(item.getName());
			}
		}
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.RetentionPeriodDaoImpl#findByID(int, com.orifound.aiim.entity.RetentionPeriod, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindByID()
	{
		fail("Not yet implemented");
	}

}
