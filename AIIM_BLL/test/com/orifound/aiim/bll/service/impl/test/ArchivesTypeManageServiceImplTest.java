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

import com.orifound.aiim.bll.service.impl.ArchivesTypeManageServiceImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesTypeDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.test.AIIMDataSource;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 档案类型管理服务测试类
 *
 */
public class ArchivesTypeManageServiceImplTest
{
	
	//业务逻辑实现类
	private static ArchivesTypeManageServiceImpl ServiceImpl=new ArchivesTypeManageServiceImpl();
	//错误信息对象
	private static ErrInfo pErrInfo=null;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		//DAO注入
		ServiceImpl.setArchivesTypeDao(new ArchivesTypeDaoImpl(AIIMDataSource.getInstance().getDataSource()));
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
		ServiceImpl=null;
		pErrInfo=null;
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		pErrInfo=new ErrInfo();
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{
		pErrInfo=null;
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.ArchivesTypeManageServiceImpl#saveArchivesType(com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testSaveArchivesType()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.ArchivesTypeManageServiceImpl#deleteArchivesType(com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testDeleteArchivesType()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.ArchivesTypeManageServiceImpl#updateArchivesType(com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testUpdateArchivesType()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.ArchivesTypeManageServiceImpl#findArchivesTypes(java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindArchivesTypes()
	{
		
		LinkedHashMap<Integer, ArchivesType> archivesTypes=new LinkedHashMap<Integer, ArchivesType>();
		if (ServiceImpl.findArchivesTypes(archivesTypes, pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else
		{
			for (ArchivesType item : archivesTypes.values())
			{
				System.out.println(item.getFullCode()+","+item.getFullName());
				
				if (item.getChildArchivesTypes()!=null)
				{
					for (ArchivesType item1 : item.getChildArchivesTypes().values())
					{
						System.out.println("----"+item1.getFullCode()+","+item1.getFullName());
						
						if (item1.getChildArchivesTypes()!=null)
						{
							for (ArchivesType item2 : item1.getChildArchivesTypes().values())
							{
								System.out.println("--------"+item2.getFullCode()+","+item2.getFullName());
								
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.ArchivesTypeManageServiceImpl#findArchivesTypeByID(java.lang.String, com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindArchivesTypeByID()
	{
		fail("Not yet implemented");
	}

}
