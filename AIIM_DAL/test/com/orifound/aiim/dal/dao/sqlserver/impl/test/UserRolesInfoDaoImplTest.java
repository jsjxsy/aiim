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

import com.orifound.aiim.dal.dao.sqlserver.impl.UserRolesInfoDaoImpl;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserRolesInfo;

/**
 * �û���ɫ��ϢDAOʵ����Ĳ�����
 *
 */
public class UserRolesInfoDaoImplTest
{

	//DAOʵ����
	private static UserRolesInfoDaoImpl DaoImpl = new UserRolesInfoDaoImpl();
	//������Ϣ����
	private static ErrInfo pErrInfo = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		//����Դע��
		DaoImpl.setDataSource(AIIMDataSourceTYB.getInstance().getDataSource());
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
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.UserRolesInfoDaoImpl#save(com.orifound.aiim.entity.UserRolesInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testSave()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.UserRolesInfoDaoImpl#delete(com.orifound.aiim.entity.UserRolesInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testDelete()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.UserRolesInfoDaoImpl#findByUserID(int, java.util.List, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindByUserID()
	{
		int pUserID=2;
		List<UserRolesInfo> userRolesInfos=new ArrayList<UserRolesInfo>();
		if (DaoImpl.findByUserID(pUserID, userRolesInfos, pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else
		{
			System.out.println("testFindByUserID ��ѯ���������: "+userRolesInfos.size());
		}
	}

}
