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

import com.orifound.aiim.bll.service.impl.DataSourceManageServiceImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.DataSourceDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.test.AIIMDataSource;
import com.orifound.aiim.entity.DataSource;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 数据源管理服务测试类
 *
 */
public class DataSourceManageServiceImplTest
{
	
	//业务逻辑实现类
	private static DataSourceManageServiceImpl ServiceImpl = new DataSourceManageServiceImpl();
	//错误信息对象
	private static ErrInfo pErrInfo = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		//DAO注入
		ServiceImpl.setDataSourceDao(new DataSourceDaoImpl(AIIMDataSource.getInstance().getDataSource()));
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
	 * Test method for {@link com.orifound.aiim.bll.service.impl.DataSourceManageServiceImpl#saveDataSource(com.orifound.aiim.entity.DataSource, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testSaveDataSource()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.DataSourceManageServiceImpl#deleteDataSource(com.orifound.aiim.entity.DataSource, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testDeleteDataSource()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.DataSourceManageServiceImpl#updateDataSource(com.orifound.aiim.entity.DataSource, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testUpdateDataSource()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.DataSourceManageServiceImpl#findDataSource(java.util.Map, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindDataSource()
	{
		Map<Integer, DataSource> dataSources=new HashMap<Integer, DataSource>();
		if (ServiceImpl.findDataSources(dataSources, pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else
		{
			System.out.println("testFindDataSource 查询结果集数量："+dataSources.size());
			for (DataSource item : dataSources.values())
			{
				System.out.println(item.getName());
			}
		}
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.DataSourceManageServiceImpl#findDataSourceByID(java.lang.String, com.orifound.aiim.entity.DataSource, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindDataSourceByID()
	{
		fail("Not yet implemented");
	}

}
