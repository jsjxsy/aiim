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

import com.orifound.aiim.dal.dao.sqlserver.impl.DataSourceDaoImpl;
import com.orifound.aiim.entity.DataSource;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 数据源信息DAO实现的测试类
 *
 */
public class DataSourceDaoImplTest
{

	//DAO实现类
	private static DataSourceDaoImpl DaoImpl = new DataSourceDaoImpl();
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
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.DataSourceDaoImpl#save(com.orifound.aiim.entity.DataSource, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testSave()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.DataSourceDaoImpl#delete(com.orifound.aiim.entity.DataSource, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testDelete()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.DataSourceDaoImpl#update(com.orifound.aiim.entity.DataSource, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testUpdate()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.DataSourceDaoImpl#findAll(java.util.Map, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindAll()
	{
		Map<Integer, DataSource> dataSources=new HashMap<Integer, DataSource>();
		if (DaoImpl.findAll(dataSources, pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else
		{
			System.out.println("testFindAll 查询结果集数量："+dataSources.size());
			for (DataSource item : dataSources.values())
			{
				System.out.println(item.getName());
			}
		}
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.DataSourceDaoImpl#findByID(int, com.orifound.aiim.entity.DataSource, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindByID()
	{
		fail("Not yet implemented");
	}

}
