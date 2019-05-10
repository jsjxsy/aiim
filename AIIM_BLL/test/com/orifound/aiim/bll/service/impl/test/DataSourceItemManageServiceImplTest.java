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

import com.orifound.aiim.bll.service.impl.DataSourceItemManageServiceImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.DataSourceItemDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.test.AIIMDataSource;
import com.orifound.aiim.entity.DataSourceItem;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 数据源的数据项管理服务测试类
 *
 */
public class DataSourceItemManageServiceImplTest
{

	//业务逻辑实现类
	private static DataSourceItemManageServiceImpl ServiceImpl = new DataSourceItemManageServiceImpl();
	//错误信息对象
	private static ErrInfo pErrInfo = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		//DAO注入
		ServiceImpl.setDataSourceItemDao(new DataSourceItemDaoImpl(AIIMDataSource.getInstance().getDataSource()));
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
	 * Test method for {@link com.orifound.aiim.bll.service.impl.DataSourceItemManageServiceImpl#saveDataSourceItem(com.orifound.aiim.entity.DataSourceItem, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testSaveDataSourceItem()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.DataSourceItemManageServiceImpl#deleteDataSourceItem(com.orifound.aiim.entity.DataSourceItem, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testDeleteDataSourceItem()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.DataSourceItemManageServiceImpl#updateDataSourceItem(com.orifound.aiim.entity.DataSourceItem, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testUpdateDataSourceItem()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.DataSourceItemManageServiceImpl#findDataSourceItemsByDataSourceID(java.lang.Integer, java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindDataSourceItemsByDataSourceID()
	{
		int pID=1;
		LinkedHashMap<Integer, DataSourceItem> dataSourceItems=new LinkedHashMap<Integer, DataSourceItem>();
		if (ServiceImpl.findDataSourceItemsByDataSourceID(pID, dataSourceItems, pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else
		{
			System.out.println("testFindDataSourceItemsByDataSourceID 查询结果集数量: "+dataSourceItems.size());
			for (DataSourceItem item : dataSourceItems.values())
			{
				System.out.println(item.getName());
			}
		}
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.DataSourceItemManageServiceImpl#findDataSourceItemByID(int, com.orifound.aiim.entity.DataSourceItem, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindDataSourceItemByID()
	{
		fail("Not yet implemented");
	}

}
