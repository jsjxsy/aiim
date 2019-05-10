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

import com.orifound.aiim.dal.dao.sqlserver.impl.UserChargeUserInfoDaoImpl;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserChargeUserInfo;

/**
 * �û�������ϢDAOʵ�ֵĲ�����
 *
 */
public class UserChargeUserInfoDaoImplTest
{
	//DAOʵ����
	private static UserChargeUserInfoDaoImpl DaoImpl = new UserChargeUserInfoDaoImpl();
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
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.UserChargeUserInfoDaoImpl#save(com.orifound.aiim.entity.UserChargeUserInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testSave()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.UserChargeUserInfoDaoImpl#delete(com.orifound.aiim.entity.UserChargeUserInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testDelete()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.UserChargeUserInfoDaoImpl#update(com.orifound.aiim.entity.UserChargeUserInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testUpdate()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.UserChargeUserInfoDaoImpl#findByUserID(int, java.util.List, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindByUserID()
	{
		int pID=3;
		List<UserChargeUserInfo> userChargeUserInfos=new ArrayList<UserChargeUserInfo>();
		if (DaoImpl.findByUserID(pID, userChargeUserInfos, pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else
		{
			System.out.println("testFindByUserID ��ѯ���������: "+userChargeUserInfos.size());
		}
	}

}
