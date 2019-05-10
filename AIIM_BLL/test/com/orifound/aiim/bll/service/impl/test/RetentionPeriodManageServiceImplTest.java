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

import com.orifound.aiim.bll.service.impl.RetentionPeriodManageServiceImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.RetentionPeriodDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.test.AIIMDataSource;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.RetentionPeriod;

/**
 * 保管期限管理服务测试类
 *
 */
public class RetentionPeriodManageServiceImplTest
{

	//业务逻辑实现类
	private static RetentionPeriodManageServiceImpl ServiceImpl = new RetentionPeriodManageServiceImpl();
	//错误信息对象
	private static ErrInfo pErrInfo = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		//DAO注入
		ServiceImpl.setRetentionPeriodDao(new RetentionPeriodDaoImpl(AIIMDataSource.getInstance().getDataSource()));
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
	 * Test method for {@link com.orifound.aiim.bll.service.impl.RetentionPeriodManageServiceImpl#saveRetentionPeriod(com.orifound.aiim.entity.RetentionPeriod, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testSaveRetentionPeriod()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.RetentionPeriodManageServiceImpl#deleteRetentionPeriod(com.orifound.aiim.entity.RetentionPeriod, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testDeleteRetentionPeriod()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.RetentionPeriodManageServiceImpl#updateRetentionPeriod(com.orifound.aiim.entity.RetentionPeriod, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testUpdateRetentionPeriod()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.RetentionPeriodManageServiceImpl#findRetentionPeriods(java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindRetentionPeriods()
	{
		LinkedHashMap<Integer, RetentionPeriod> retentionPeriods=new LinkedHashMap<Integer, RetentionPeriod>();
		if (ServiceImpl.findRetentionPeriods(retentionPeriods, pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else
		{
			System.out.println("testFindRetentionPeriods 查询结果集数量："+retentionPeriods.size());
			for (RetentionPeriod item : retentionPeriods.values())
			{
				System.out.println(item.getName());
			}
		}
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.RetentionPeriodManageServiceImpl#findRetentionPeriodByID(int, com.orifound.aiim.entity.RetentionPeriod, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindRetentionPeriodByID()
	{
		fail("Not yet implemented");
	}

}
