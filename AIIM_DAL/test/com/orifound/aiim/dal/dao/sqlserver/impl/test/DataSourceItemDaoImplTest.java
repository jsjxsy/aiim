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

import com.orifound.aiim.dal.dao.sqlserver.impl.DataSourceItemDaoImpl;
import com.orifound.aiim.entity.DataSourceItem;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 数据源的数据项信息DAO实现的测试类
 *
 */
public class DataSourceItemDaoImplTest
{

	//DAO实现类
	private static DataSourceItemDaoImpl DaoImpl = new DataSourceItemDaoImpl();
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
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.DataSourceItemDaoImpl#save(com.orifound.aiim.entity.DataSourceItem, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testSave()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.DataSourceItemDaoImpl#delete(com.orifound.aiim.entity.DataSourceItem, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testDelete()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.DataSourceItemDaoImpl#update(com.orifound.aiim.entity.DataSourceItem, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testUpdate()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.DataSourceItemDaoImpl#findByDataSourceID(int, java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindByDataSourceID()
	{
		int pID=1;
		LinkedHashMap<Integer, DataSourceItem> dataSourceItems=new LinkedHashMap<Integer, DataSourceItem>();
		if (DaoImpl.findByDataSourceID(pID, dataSourceItems, pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else
		{
			System.out.println("testFindByDataSourceID 查询结果集数量: "+dataSourceItems.size());
			for (DataSourceItem item : dataSourceItems.values())
			{
				System.out.println(item.getName());
			}
		}
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.DataSourceItemDaoImpl#findByID(int, com.orifound.aiim.entity.DataSourceItem, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindByID()
	{
		fail("Not yet implemented");
	}

}
