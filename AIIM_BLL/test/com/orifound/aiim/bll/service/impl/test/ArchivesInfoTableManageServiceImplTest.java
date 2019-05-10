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

import com.orifound.aiim.bll.service.impl.ArchivesInfoTableManageServiceImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesInfoTableDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.test.AIIMDataSource;
import com.orifound.aiim.entity.ArchivesInfoTable;
import com.orifound.aiim.entity.EnumArchivesInfoTableType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 档案信息表管理服务测试类
 *
 */
public class ArchivesInfoTableManageServiceImplTest
{

	//业务逻辑实现类
	private static ArchivesInfoTableManageServiceImpl ServiceImpl = new ArchivesInfoTableManageServiceImpl();
	//错误信息对象
	private static ErrInfo pErrInfo = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		//DAO注入
		ServiceImpl.setArchivesInfoTableDao(new ArchivesInfoTableDaoImpl(AIIMDataSource.getInstance().getDataSource()));
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
	 * Test method for {@link com.orifound.aiim.bll.service.impl.ArchivesInfoTableManageServiceImpl#findArchivesInfoTableByID(int, com.orifound.aiim.entity.ArchivesInfoTable, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindArchivesInfoTableByID()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.ArchivesInfoTableManageServiceImpl#findArchivesTypeTables(int, java.util.EnumMap, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindArchivesTypeTables()
	{
		int pID=1;
		EnumMap<EnumArchivesInfoTableType, ArchivesInfoTable> archivesInfoTables=new EnumMap<EnumArchivesInfoTableType, ArchivesInfoTable>(EnumArchivesInfoTableType.class);
		if (ServiceImpl.findArchivesTypeTables(pID, archivesInfoTables, pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else
		{
			for (ArchivesInfoTable item : archivesInfoTables.values())
			{
				System.out.println(item.getTableType().toString()+": "+item.getTableName());
			}
		}
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.ArchivesInfoTableManageServiceImpl#saveArchivesInfoTable(com.orifound.aiim.entity.ArchivesInfoTable, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testSaveArchivesInfoTable()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.ArchivesInfoTableManageServiceImpl#setCreatedFlag(com.orifound.aiim.entity.ArchivesInfoTable, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testSetCreatedFlag()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.ArchivesInfoTableManageServiceImpl#deleteArchivesTypeTables(int, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testDeleteArchivesTypeTables()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.ArchivesInfoTableManageServiceImpl#updateArchivesInfoTable(com.orifound.aiim.entity.ArchivesInfoTable, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testUpdateArchivesInfoTable()
	{
		fail("Not yet implemented");
	}

}
