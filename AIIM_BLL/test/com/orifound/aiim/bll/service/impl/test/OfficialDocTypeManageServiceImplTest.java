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

import com.orifound.aiim.bll.service.impl.OfficialDocTypeManageServiceImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.OfficialDocTypeDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.test.AIIMDataSource;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.OfficialDocType;

/**
 * @author EAGLE
 *
 */
public class OfficialDocTypeManageServiceImplTest
{
	//业务逻辑实现类
	private static OfficialDocTypeManageServiceImpl ServiceImpl = new OfficialDocTypeManageServiceImpl();
	//错误信息对象
	private static ErrInfo pErrInfo = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		//DAO注入
		ServiceImpl.setOfficialDocTypeDao(new OfficialDocTypeDaoImpl(AIIMDataSource.getInstance().getDataSource()));
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
	 * Test method for {@link com.orifound.aiim.bll.service.impl.OfficialDocTypeManageServiceImpl#saveOfficialDocType(com.orifound.aiim.entity.OfficialDocType, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testSaveOfficialDocType()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.OfficialDocTypeManageServiceImpl#deleteOfficialDocType(com.orifound.aiim.entity.OfficialDocType, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testDeleteOfficialDocType()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.OfficialDocTypeManageServiceImpl#updateOfficialDocType(com.orifound.aiim.entity.OfficialDocType, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testUpdateOfficialDocType()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.OfficialDocTypeManageServiceImpl#findOfficialDocTypes(java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindOfficialDocTypes()
	{
		LinkedHashMap<Integer, OfficialDocType> officialDocTypes=new LinkedHashMap<Integer, OfficialDocType>();
		if (ServiceImpl.findOfficialDocTypes(officialDocTypes, pErrInfo)==false)
		{
			fail(pErrInfo.toString());
		}
		else 
		{
			for (OfficialDocType item : officialDocTypes.values())
			{
				System.out.println(item.getName());
			}
		}
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.OfficialDocTypeManageServiceImpl#findOfficialDocTypeByID(int, com.orifound.aiim.entity.OfficialDocType, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindOfficialDocTypeByID()
	{
		fail("Not yet implemented");
	}

}
