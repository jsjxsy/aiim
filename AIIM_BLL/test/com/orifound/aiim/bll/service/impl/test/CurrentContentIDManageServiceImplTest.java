/**
 * 
 */
package com.orifound.aiim.bll.service.impl.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.orifound.aiim.bll.service.impl.CurrentContentIDManageServiceImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.CurrentContentIDDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.test.AIIMDataSource;
import com.orifound.aiim.entity.CurrentContentID;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 当前案卷号管理服务测试类
 *
 */
public class CurrentContentIDManageServiceImplTest
{

	//业务逻辑实现类
	private static CurrentContentIDManageServiceImpl ServiceImpl = new CurrentContentIDManageServiceImpl();
	//错误信息对象
	private static ErrInfo pErrInfo = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		//DAO注入
		ServiceImpl.setCurrentContentIDDao(new CurrentContentIDDaoImpl(AIIMDataSource.getInstance().getDataSource()));
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
	 * Test method for {@link com.orifound.aiim.bll.service.impl.CurrentContentIDManageServiceImpl#saveCurrentContentID(com.orifound.aiim.entity.CurrentContentID, com.orifound.aiim.entity.ErrInfo)}.
	 */
	/*@Test
	public void testSaveCurrentContentID()
	{
		CurrentContentID currentContentID=new CurrentContentID("G01", 2, 0);
		if (ServiceImpl.saveCurrentContentID(currentContentID, pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else 
		{
			System.out.println("testSaveCurrentContentID OK.");
		}
	}

	*//**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.CurrentContentIDManageServiceImpl#updateCurrentContentID(com.orifound.aiim.entity.CurrentContentID, com.orifound.aiim.entity.ErrInfo)}.
	 *//*
	@Test
	public void testUpdateCurrentContentID()
	{
		CurrentContentID currentContentID=new CurrentContentID("G01", 1, 0);
		if (ServiceImpl.updateCurrentContentID(currentContentID, pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else 
		{
			System.out.println("testUpdateCurrentContentID OK.");
		}
	}

	*//**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.CurrentContentIDManageServiceImpl#findCurrentContentIDByID(java.lang.String, int, com.orifound.aiim.entity.CurrentContentID, com.orifound.aiim.entity.ErrInfo)}.
	 *//*
	@Test
	public void testFindCurrentContentIDByID()
	{
		CurrentContentID currentContentID=new CurrentContentID();
		if (ServiceImpl.findCurrentContentIDByID("G01", 1, currentContentID, pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else 
		{
			System.out.println("testFindCurrentContentIDByID OK. getContentID: "+currentContentID.getContentID());
		}
	}
*/
}
