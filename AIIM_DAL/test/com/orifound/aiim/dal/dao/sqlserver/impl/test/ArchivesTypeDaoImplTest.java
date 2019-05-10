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

import com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesTypeDaoImpl;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 档案分类信息DAO实现的测试类
 *
 */
public class ArchivesTypeDaoImplTest
{

	//DAO实现类
	private static ArchivesTypeDaoImpl DaoImpl = new ArchivesTypeDaoImpl();
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
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesTypeDaoImpl#save(com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testSave()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesTypeDaoImpl#delete(com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testDelete()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesTypeDaoImpl#update(com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testUpdate()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesTypeDaoImpl#findForLevel1(java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindForLevel1()
	{
		LinkedHashMap<Integer, ArchivesType> archivesTypes=new LinkedHashMap<Integer, ArchivesType>();
		if (DaoImpl.findForLevel1(archivesTypes, pErrInfo)==false)
		{
			fail(pErrInfo.toString());
		}
		else
		{
			System.out.println("testFindForLevel1 查询结果集数量: "+archivesTypes.size());
			if (archivesTypes.size()>0)
			{
				for (ArchivesType item : archivesTypes.values())
				{
					System.out.println("--"+item.getFullCode()+","+item.getFullName());
				}
			}
		}
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesTypeDaoImpl#findForChild(int, java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindForChild()
	{
		int pID=7;
		LinkedHashMap<Integer, ArchivesType> childArchivesTypes=new LinkedHashMap<Integer, ArchivesType>();
		if (DaoImpl.findForChild(pID, childArchivesTypes, pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else
		{
			for (ArchivesType item : childArchivesTypes.values())
			{
				System.out.println(item.getFullCode()+","+item.getFullName());
				
				if (item.getChildArchivesTypes()!=null)
				{
					for (ArchivesType item1 : item.getChildArchivesTypes().values())
					{
						System.out.println("----"+item1.getFullCode()+","+item1.getFullName()+","+item1.getArchivesIDExpressionPrefix()+","+item1.getArchivesIDExpression()+","+item1.getSubArchivesIDExpression()+","+item1.getPersonalArchivesFlag());
					}
				}
			}
		}
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesTypeDaoImpl#findByID(int, com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindByID()
	{
		fail("Not yet implemented");
	}

}
