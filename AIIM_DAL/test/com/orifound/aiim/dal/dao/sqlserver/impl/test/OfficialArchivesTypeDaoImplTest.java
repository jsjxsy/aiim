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

import com.orifound.aiim.dal.dao.sqlserver.impl.OfficialArchivesTypeDaoImpl;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.OfficialArchivesType;

/**
 * 公文档案分类字典表DAO测试类
 *
 */
public class OfficialArchivesTypeDaoImplTest
{

	//DAO实现类
	private static OfficialArchivesTypeDaoImpl DaoImpl = new OfficialArchivesTypeDaoImpl();
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
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.OfficialArchivesTypeDaoImpl#save(com.orifound.aiim.entity.OfficialArchivesType, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testSave()
	{
		OfficialArchivesType officialArchivesType=new OfficialArchivesType("收文test", "SWtest", "\\SWtest", null);
		if (DaoImpl.save(officialArchivesType, pErrInfo)==false)
		{
			fail(pErrInfo.toString());
		}
		else 
		{
			System.out.println("testSave OK.");
			System.out.println("ID: "+officialArchivesType.getID());
		}
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.OfficialArchivesTypeDaoImpl#delete(com.orifound.aiim.entity.OfficialArchivesType, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testDelete()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.OfficialArchivesTypeDaoImpl#update(com.orifound.aiim.entity.OfficialArchivesType, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testUpdate()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.OfficialArchivesTypeDaoImpl#findAll(java.util.List, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindAll()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.OfficialArchivesTypeDaoImpl#findByID(int, com.orifound.aiim.entity.OfficialArchivesType, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindByID()
	{
		int pOfficialArchivesTypeID=1;
		OfficialArchivesType officialArchivesType=new OfficialArchivesType();
		if (DaoImpl.findByID(pOfficialArchivesTypeID, officialArchivesType, pErrInfo)==false)
		{
			fail(pErrInfo.toString());
		}
		else
		{
			System.out.println(officialArchivesType.getName());
		}
	}

}
