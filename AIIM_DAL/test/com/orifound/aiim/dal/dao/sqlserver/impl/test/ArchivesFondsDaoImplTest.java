/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesFondsDaoImpl;
import com.orifound.aiim.entity.ArchivesFonds;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 档案全宗DAO实现的测试类
 *
 */
public class ArchivesFondsDaoImplTest
{
	//DAO实现类
	private static ArchivesFondsDaoImpl DaoImpl=new ArchivesFondsDaoImpl();
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
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesFondsDaoImpl#save(com.orifound.aiim.entity.ArchivesFonds, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testSave()
	{
		ArchivesFonds item=new ArchivesFonds("G02", "黄金学院档案",false, null);
		if (DaoImpl.save(item, pErrInfo)==false)
			fail(pErrInfo.toString());
		
		ArchivesFonds item1=new ArchivesFonds("G03", "黄金学院档案3",false, null);
		if (DaoImpl.save(item1, pErrInfo)==false)
			fail(pErrInfo.toString());
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesFondsDaoImpl#delete(com.orifound.aiim.entity.ArchivesFonds, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testDelete()
	{
		ArchivesFonds item=new ArchivesFonds();
		item.setID(1);
		if (DaoImpl.delete(item, pErrInfo)==false)
			fail(pErrInfo.toString());
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesFondsDaoImpl#update(com.orifound.aiim.entity.ArchivesFonds, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testUpdate()
	{
		ArchivesFonds item=new ArchivesFonds(1,"G03", "黄金学院档案3",true, null);
		if (DaoImpl.update(item, pErrInfo)==false)
			fail(pErrInfo.toString());
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesFondsDaoImpl#findAll(java.util.List, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindAll()
	{
		List<ArchivesFonds> items=new ArrayList<ArchivesFonds>();

		if (DaoImpl.findAll(items, pErrInfo)==false)
			fail(pErrInfo.toString());
		else {
			System.out.println("findAll result count: " +items.size());
		}
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesFondsDaoImpl#findByID(java.lang.String, com.orifound.aiim.entity.ArchivesFonds, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindByID()
	{
		int id=1;
		ArchivesFonds item=new ArchivesFonds();
		
		if (DaoImpl.findByID(id, item, pErrInfo)==false)
			fail(pErrInfo.toString());
		else {
			System.out.println("findbyID result : " +item.getName().toString());
		}
		
	}

}
