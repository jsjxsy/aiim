/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.orifound.aiim.dal.dao.sqlserver.impl.CurrentContentIDDaoImpl;
import com.orifound.aiim.entity.CurrentContentID;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 当前案卷号信息DAO测试类
 *
 */
public class CurrentContentIDDaoImplTest
{

	//DAO实现类
	private static CurrentContentIDDaoImpl DaoImpl = new CurrentContentIDDaoImpl();
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
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.CurrentContentIDDaoImpl#save(com.orifound.aiim.entity.CurrentContentID, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testSave()
	{
		CurrentContentID currentContentID=new CurrentContentID();
		if (DaoImpl.save(currentContentID, pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else 
		{
			System.out.println("testSave OK.");
		}
	}

	/**
<<<<<<< .mine
=======
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.CurrentContentIDDaoImpl#update(com.orifound.aiim.entity.CurrentContentID, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testUpdate()
	{
		CurrentContentID currentContentID=new CurrentContentID();
		if (DaoImpl.update(currentContentID, pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else 
		{
			System.out.println("testUpdate OK.");
		}
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.CurrentContentIDDaoImpl#findByID(java.lang.String, int, com.orifound.aiim.entity.CurrentContentID, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindByID()
	{
		CurrentContentID currentContentID=new CurrentContentID();
		if (DaoImpl.findByPrefix(currentContentID.getArchivesIDPrefix(), currentContentID, pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else 
		{
			System.out.println("testFindByID OK. getContentID: "+currentContentID.getContentID());
		}
	}

}
