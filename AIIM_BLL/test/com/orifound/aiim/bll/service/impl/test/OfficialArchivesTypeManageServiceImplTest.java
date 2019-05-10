/**
 * 
 */
package com.orifound.aiim.bll.service.impl.test;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.orifound.aiim.bll.service.impl.OfficialArchivesTypeManageServiceImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.OfficialArchivesTypeDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.test.AIIMDataSource;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.OfficialArchivesType;

/**
 * 公文档案分类信息管理服务测试类
 *
 */
public class OfficialArchivesTypeManageServiceImplTest
{

	//业务逻辑实现类
	private static OfficialArchivesTypeManageServiceImpl ServiceImpl = new OfficialArchivesTypeManageServiceImpl();
	//错误信息对象
	private static ErrInfo pErrInfo = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		//DAO注入
		ServiceImpl.setOfficialArchivesTypeDao(new OfficialArchivesTypeDaoImpl(AIIMDataSource.getInstance().getDataSource()));
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
	 * Test method for {@link com.orifound.aiim.bll.service.impl.OfficialArchivesTypeManageServiceImpl#saveOfficialArchivesType(com.orifound.aiim.entity.OfficialArchivesType, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testSaveOfficialArchivesType()
	{
		OfficialArchivesType officialArchivesType=new OfficialArchivesType("收文", "SW", "\\SW", null);
		if (ServiceImpl.saveOfficialArchivesType(officialArchivesType, pErrInfo)==false)
		{
			fail(pErrInfo.toString());
		}
		else 
		{
			System.out.println("testSaveOfficialArchivesType OK.");
		}
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.OfficialArchivesTypeManageServiceImpl#deleteOfficialArchivesType(com.orifound.aiim.entity.OfficialArchivesType, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testDeleteOfficialArchivesType()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.OfficialArchivesTypeManageServiceImpl#updateOfficialArchivesType(com.orifound.aiim.entity.OfficialArchivesType, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testUpdateOfficialArchivesType()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.OfficialArchivesTypeManageServiceImpl#findOfficialArchivesTypes(java.util.List, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindOfficialArchivesTypes()
	{
		LinkedHashMap<Integer, OfficialArchivesType> officialArchivesTypes=new LinkedHashMap<Integer, OfficialArchivesType>();
		if (ServiceImpl.findOfficialArchivesTypes(officialArchivesTypes, pErrInfo)==false)
		{
			fail(pErrInfo.toString());
		}
		else 
		{
			for (OfficialArchivesType item : officialArchivesTypes.values())
			{
				System.out.println(item.getName());
			}
		}
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.OfficialArchivesTypeManageServiceImpl#findOfficialArchivesTypeByID(int, com.orifound.aiim.entity.OfficialArchivesType, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindOfficialArchivesTypeByID()
	{
		fail("Not yet implemented");
	}

}
