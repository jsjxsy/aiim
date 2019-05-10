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

import com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesInfoTableDaoImpl;
import com.orifound.aiim.entity.ArchivesInfoTable;
import com.orifound.aiim.entity.EnumArchivesInfoTableType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 档案信息表DAO实现的测试类
 *
 */
public class ArchivesInfoTableDaoImplTest
{

	//DAO实现类
	private static ArchivesInfoTableDaoImpl DaoImpl=new ArchivesInfoTableDaoImpl();
	//错误信息对象
	private static ErrInfo pErrInfo=null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		//数据源注入
		DaoImpl.setDataSource(AIIMDataSource.getInstance().getDataSource());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
		DaoImpl=null;
		pErrInfo=null;
	}

	@Before
	public void setUp() throws Exception
	{
		pErrInfo=new ErrInfo();
	}

	@After
	public void tearDown() throws Exception
	{
		pErrInfo=null;
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesInfoTableDaoImpl#save(com.orifound.aiim.entity.ArchivesInfoTable, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testSave()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesInfoTableDaoImpl#deleteByArchivesTypeID(int, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testDeleteByArchivesTypeID()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesInfoTableDaoImpl#setCreatedFlag(com.orifound.aiim.entity.ArchivesInfoTable, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testSetCreatedFlag()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesInfoTableDaoImpl#findByArchivesTypeID(int, java.util.EnumMap, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindByArchivesTypeID()
	{
		EnumMap<EnumArchivesInfoTableType, ArchivesInfoTable> archivesInfoTables=new EnumMap<EnumArchivesInfoTableType, ArchivesInfoTable>(EnumArchivesInfoTableType.class);
		int pArchivesTypeID=1;
		if (DaoImpl.findByArchivesTypeID(pArchivesTypeID, archivesInfoTables, pErrInfo)==false)
		{
			fail(pErrInfo.toString());
		}
		else
		{
			System.out.println("testFindByArchivesTypeID 结果集数量: "+archivesInfoTables.size());
			System.out.println("档案归档过程表： "+archivesInfoTables.get(EnumArchivesInfoTableType.档案归档过程表).getTableName());
		}
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesInfoTableDaoImpl#findByID(int, com.orifound.aiim.entity.ArchivesInfoTable, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindByID()
	{
		fail("Not yet implemented");
	}

}
