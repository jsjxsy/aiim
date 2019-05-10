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

import com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesSecrecyDaoImpl;
import com.orifound.aiim.entity.ArchivesSecrecy;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 档案密级信息DAO实现的测试类
 *
 */
public class ArchivesSecrecyDaoImplTest
{

	//DAO实现类
	private static ArchivesSecrecyDaoImpl DaoImpl = new ArchivesSecrecyDaoImpl();
	//错误信息对象
	private static ErrInfo pErrInfo = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		//数据源注入
		DaoImpl.setDataSource(AIIMDataSourceTYB.getInstance().getDataSource());
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
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesSecrecyDaoImpl#save(com.orifound.aiim.entity.ArchivesSecrecy, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testSave()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesSecrecyDaoImpl#delete(com.orifound.aiim.entity.ArchivesSecrecy, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testDelete()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesSecrecyDaoImpl#update(com.orifound.aiim.entity.ArchivesSecrecy, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testUpdate()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesSecrecyDaoImpl#findAll(java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindAll()
	{
		LinkedHashMap<Integer, ArchivesSecrecy> archivesSecrecys=new LinkedHashMap<Integer, ArchivesSecrecy>();
		if (DaoImpl.findAll(archivesSecrecys, pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else
		{
			System.out.println("testFindAll 查询结果集数量: "+archivesSecrecys.size());
			for (ArchivesSecrecy item : archivesSecrecys.values())
			{
				System.out.println(item.getName());
			}
		}
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesSecrecyDaoImpl#findByID(int, com.orifound.aiim.entity.ArchivesSecrecy, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindByID()
	{
		fail("Not yet implemented");
	}

	@Test
	public void findByOpenSecrecyTest() {
		ArchivesSecrecy archivesSecrecy = new ArchivesSecrecy();
		if (DaoImpl.findByOpenSecrecy(archivesSecrecy , pErrInfo) == false) {
			fail(pErrInfo.toShortString());
		}
		
		System.out.println(archivesSecrecy.getName());
	}
}
