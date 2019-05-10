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

import com.orifound.aiim.bll.service.impl.ArchivesFondsManageServiceImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesFondsDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.test.AIIMDataSource;
import com.orifound.aiim.entity.ArchivesFonds;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 档案全宗管理服务业务逻辑测试类
 *
 */
public class ArchivesFondsManageServiceImplTest
{
	//业务逻辑实现类
	private static ArchivesFondsManageServiceImpl ServiceImpl=new ArchivesFondsManageServiceImpl();
	//错误信息对象
	private static ErrInfo pErrInfo=null;
	

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		//DAO注入
		ServiceImpl.setArchivesFondsDao(new ArchivesFondsDaoImpl(AIIMDataSource.getInstance().getDataSource()));
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
		pErrInfo=null;
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
	 * Test method for {@link com.orifound.aiim.bll.service.impl.ArchivesFondsManageServiceImpl#findArchivesFonds(java.util.List, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindArchivesFonds()
	{
		List<ArchivesFonds> archivesFondss=new ArrayList<ArchivesFonds>();
		if (ServiceImpl.findArchivesFondss(archivesFondss, pErrInfo)==false)
			fail(pErrInfo.toString());
		else {
			System.out.println("testFindArchivesFonds 查询结果集数量："+archivesFondss.size());
		}
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.ArchivesFondsManageServiceImpl#findArchivesFondsByID(java.lang.String, com.orifound.aiim.entity.ArchivesFonds, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindArchivesFondsByID()
	{
		ArchivesFonds archivesFonds=new ArchivesFonds();
		int pID=1;
		if (ServiceImpl.findArchivesFondsByID(pID, archivesFonds, pErrInfo)==false)
		{
			fail(pErrInfo.toString());
		}
		else {
			System.out.println("testFindArchivesFondsByID 'G01'的全宗名称为："+archivesFonds.getName());
		}
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.ArchivesFondsManageServiceImpl#saveArchivesFonds(com.orifound.aiim.entity.ArchivesFonds, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testSaveArchivesFonds()
	{
		ArchivesFonds archivesFonds=new ArchivesFonds("G04", "测试全宗4",true, "test archivesfonds");
		if (ServiceImpl.saveArchivesFonds(archivesFonds, pErrInfo)==false)
		{
			fail(pErrInfo.toString());
		}
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.ArchivesFondsManageServiceImpl#deleteArchivesFonds(com.orifound.aiim.entity.ArchivesFonds, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testDeleteArchivesFonds()
	{
		ArchivesFonds archivesFonds=new ArchivesFonds("G04", "测试全宗4",true, "test archivesfonds");
		if (ServiceImpl.deleteArchivesFonds(archivesFonds, pErrInfo)==false)
		{
			fail(pErrInfo.toString());
		}
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.ArchivesFondsManageServiceImpl#updateArchivesFonds(com.orifound.aiim.entity.ArchivesFonds, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testUpdateArchivesFonds()
	{
		ArchivesFonds archivesFonds=new ArchivesFonds("G04", "测试全宗5",true,null);
		if (ServiceImpl.saveArchivesFonds(archivesFonds, pErrInfo)==false)
		{
			fail(pErrInfo.toString());
		}
	}

}
