/**
 * 
 */
package com.orifound.aiim.bll.service.impl.test;

import static org.junit.Assert.*;

import java.util.EnumMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.orifound.aiim.bll.service.impl.OfficialArchivesInfoTableManageServiceImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.OfficialArchivesInfoTableDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.test.AIIMDataSource;
import com.orifound.aiim.entity.EnumOfficialArchivesInfoTableType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.OfficialArchivesInfoTable;

/**
 * 公文档案信息相关表的管理服务测试类
 *
 */
public class OfficialArchivesInfoTableManageServiceImplTest
{
//业务逻辑实现类
private static OfficialArchivesInfoTableManageServiceImpl ServiceImpl = new OfficialArchivesInfoTableManageServiceImpl();
//错误信息对象
private static ErrInfo pErrInfo = null;

/**
 * @throws java.lang.Exception
 */
@BeforeClass
public static void setUpBeforeClass() throws Exception
{
	//DAO注入
	ServiceImpl.setOfficialArchivesInfoTableDao(new OfficialArchivesInfoTableDaoImpl(AIIMDataSource.getInstance().getDataSource()));
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
	 * Test method for {@link com.orifound.aiim.bll.service.impl.OfficialArchivesInfoTableManageServiceImpl#saveOfficialArchivesInfoTable(com.orifound.aiim.entity.OfficialArchivesInfoTable, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testSaveOfficialArchivesInfoTable()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.OfficialArchivesInfoTableManageServiceImpl#deleteOfficialArchivesInfoTable(com.orifound.aiim.entity.OfficialArchivesInfoTable, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testDeleteOfficialArchivesInfoTable()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.OfficialArchivesInfoTableManageServiceImpl#updateOfficialArchivesInfoTable(com.orifound.aiim.entity.OfficialArchivesInfoTable, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testUpdateOfficialArchivesInfoTable()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.OfficialArchivesInfoTableManageServiceImpl#findOfficialArchivesInfoTables(int, java.util.EnumMap, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindOfficialArchivesInfoTables()
	{
		int pArchivesTypeID=1;
		EnumMap<EnumOfficialArchivesInfoTableType, OfficialArchivesInfoTable> officialArchivesInfoTables=new EnumMap<EnumOfficialArchivesInfoTableType, OfficialArchivesInfoTable>(EnumOfficialArchivesInfoTableType.class);
		if (ServiceImpl.findOfficialArchivesInfoTables(pArchivesTypeID, officialArchivesInfoTables, pErrInfo)==false)
		{
			fail(pErrInfo.toString());
		}
		else 
		{
			for (OfficialArchivesInfoTable item : officialArchivesInfoTables.values())
			{
				System.out.println(item.getTableName());
			}
		}
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.OfficialArchivesInfoTableManageServiceImpl#findOfficialArchivesInfoTableByID(int, com.orifound.aiim.entity.OfficialArchivesInfoTable, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindOfficialArchivesInfoTableByID()
	{
		fail("Not yet implemented");
	}

}
