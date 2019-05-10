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

import com.orifound.aiim.bll.service.impl.DepartmentInfoManageServiceImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.DepartmentInfoDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.test.AIIMDataSource;
import com.orifound.aiim.entity.DepartmentInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 部门信息管理服务测试类
 *
 */
public class DepartmentInfoManageServiceImplTest
{

	//业务逻辑实现类
	private static DepartmentInfoManageServiceImpl ServiceImpl = new DepartmentInfoManageServiceImpl();
	//错误信息对象
	private static ErrInfo pErrInfo = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		//DAO注入
		ServiceImpl.setDepartmentInfoDao(new DepartmentInfoDaoImpl(AIIMDataSource.getInstance().getDataSource()));
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
	 * Test method for {@link com.orifound.aiim.bll.service.impl.DepartmentInfoManageServiceImpl#deleteDepartmentInfo(com.orifound.aiim.entity.DepartmentInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testDeleteDepartmentInfo()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.DepartmentInfoManageServiceImpl#findDepartmentInfos(java.util.List, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindDepartmentInfos()
	{
		List<DepartmentInfo> departmentInfos=new ArrayList<DepartmentInfo>();
		if (ServiceImpl.findDepartmentInfos(departmentInfos, pErrInfo)==false)
		{
			fail(pErrInfo.toString());
		}
		else 
		{
			System.out.println("testFindDepartmentInfos OK:");
			for (DepartmentInfo item : departmentInfos)
			{
				System.out.println(item.getName());
			}
		}
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.DepartmentInfoManageServiceImpl#findDepartmentInfoByID(int, com.orifound.aiim.entity.DepartmentInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindDepartmentInfoByID()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.DepartmentInfoManageServiceImpl#saveDepartmentInfo(com.orifound.aiim.entity.DepartmentInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testSaveDepartmentInfo()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.DepartmentInfoManageServiceImpl#updateDepartmentInfo(com.orifound.aiim.entity.DepartmentInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testUpdateDepartmentInfo()
	{
		fail("Not yet implemented");
	}

}
