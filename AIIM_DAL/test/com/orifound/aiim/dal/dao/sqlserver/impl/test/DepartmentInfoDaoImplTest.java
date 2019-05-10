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

import com.orifound.aiim.dal.dao.sqlserver.impl.DepartmentInfoDaoImpl;
import com.orifound.aiim.entity.DepartmentInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ������ϢDAO������
 *
 */
public class DepartmentInfoDaoImplTest
{

	//DAOʵ����
	private static DepartmentInfoDaoImpl DaoImpl = new DepartmentInfoDaoImpl();
	//������Ϣ����
	private static ErrInfo pErrInfo = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		//����Դע��
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
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.DepartmentInfoDaoImpl#save(com.orifound.aiim.entity.DepartmentInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testSave()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.DepartmentInfoDaoImpl#delete(com.orifound.aiim.entity.DepartmentInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testDelete()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.DepartmentInfoDaoImpl#update(com.orifound.aiim.entity.DepartmentInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testUpdate()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.DepartmentInfoDaoImpl#findAll(java.util.List, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindAll()
	{
		List<DepartmentInfo> departmentInfos=new ArrayList<DepartmentInfo>();
		if (DaoImpl.findAll(departmentInfos, pErrInfo)==false)
		{
			fail(pErrInfo.toString());
		}
		else
		{
			System.out.println(departmentInfos.size());
		}
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.DepartmentInfoDaoImpl#findByID(int, com.orifound.aiim.entity.DepartmentInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindByID()
	{
		fail("Not yet implemented");
	}

}
