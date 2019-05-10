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

import com.orifound.aiim.bll.service.impl.ArchivesSecrecyManageServiceImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesSecrecyDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.test.AIIMDataSource;
import com.orifound.aiim.entity.ArchivesSecrecy;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 档案密级管理服务测试类
 *
 */
public class ArchivesSecrecyManageServiceImplTest
{

	//业务逻辑实现类
	private static ArchivesSecrecyManageServiceImpl ServiceImpl = new ArchivesSecrecyManageServiceImpl();
	//错误信息对象
	private static ErrInfo pErrInfo = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		//DAO注入
		ServiceImpl.setArchivesSecrecyDao(new ArchivesSecrecyDaoImpl(AIIMDataSource.getInstance().getDataSource()));
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
	 * Test method for {@link com.orifound.aiim.bll.service.impl.ArchivesSecrecyManageServiceImpl#saveArchivesSecrecy(com.orifound.aiim.entity.ArchivesSecrecy, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testSaveArchivesSecrecy()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.ArchivesSecrecyManageServiceImpl#deleteArchivesSecrecy(com.orifound.aiim.entity.ArchivesSecrecy, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testDeleteArchivesSecrecy()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.ArchivesSecrecyManageServiceImpl#updateArchivesSecrecy(com.orifound.aiim.entity.ArchivesSecrecy, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testUpdateArchivesSecrecy()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.ArchivesSecrecyManageServiceImpl#findArchivesSecrecy(java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindArchivesSecrecy()
	{
		LinkedHashMap<Integer, ArchivesSecrecy> archivesSecrecys=new LinkedHashMap<Integer, ArchivesSecrecy>();
		if (ServiceImpl.findArchivesSecrecys(archivesSecrecys, pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else
		{
			System.out.println("testFindArchivesSecrecy 查询结果集数量: "+archivesSecrecys.size());
			for (ArchivesSecrecy item : archivesSecrecys.values())
			{
				System.out.println(item.getName());
			}
		}
		
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.ArchivesSecrecyManageServiceImpl#findArchivesSecrecyByID(int, com.orifound.aiim.entity.ArchivesSecrecy, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindArchivesSecrecyByID()
	{
		fail("Not yet implemented");
	}

}
