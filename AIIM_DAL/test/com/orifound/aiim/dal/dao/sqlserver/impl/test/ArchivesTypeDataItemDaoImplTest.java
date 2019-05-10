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

import com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesTypeDataItemDaoImpl;
import com.orifound.aiim.entity.ArchivesTypeDataItem;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 档案分类数据项信息DAO实现的测试类
 *
 */
public class ArchivesTypeDataItemDaoImplTest
{

	//DAO实现类
	private static ArchivesTypeDataItemDaoImpl DaoImpl = new ArchivesTypeDataItemDaoImpl();
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
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesTypeDataItemDaoImpl#save(com.orifound.aiim.entity.ArchivesTypeDataItem, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testSave()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesTypeDataItemDaoImpl#delete(com.orifound.aiim.entity.ArchivesTypeDataItem, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testDelete()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesTypeDataItemDaoImpl#update(com.orifound.aiim.entity.ArchivesTypeDataItem, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testUpdate()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesTypeDataItemDaoImpl#findByArchivesTypeID(int, java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindByArchivesTypeID()
	{
		int pID=1;
		LinkedHashMap<String, ArchivesTypeDataItem> archivesTypeDataItems=new LinkedHashMap<String, ArchivesTypeDataItem>();
		if (DaoImpl.findByArchivesTypeID(false,pID, archivesTypeDataItems, pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else
		{
			if (archivesTypeDataItems.size()>0)
			{
				System.out.println("档案分类 "+ pID + " 定义的数据项数量："+archivesTypeDataItems.size());
				for (ArchivesTypeDataItem item : archivesTypeDataItems.values())
				{
					System.out.println(item.getColumnName()+","+item.getDisplayText()+","+item.getEditStyle().toString()+","+item.getInputHoldFlag());
				}
			}
		}
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesTypeDataItemDaoImpl#findByID(int, com.orifound.aiim.entity.ArchivesTypeDataItem, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindByID()
	{
		fail("Not yet implemented");
	}

}
