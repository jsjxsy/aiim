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

import com.orifound.aiim.dal.dao.sqlserver.impl.UserInfoDaoImpl;
import com.orifound.aiim.entity.EnumSystemRole;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserInfo;

/**
 * �û���ϢDAOʵ�ֵĲ�����
 *
 */
public class UserInfoDaoImplTest
{

	//DAOʵ����
	private static UserInfoDaoImpl DaoImpl = new UserInfoDaoImpl();
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
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.UserInfoDaoImpl#delete(com.orifound.aiim.entity.UserInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testDelete()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.UserInfoDaoImpl#findAll(java.util.List, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindAll()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.UserInfoDaoImpl#findAnonymous(com.orifound.aiim.entity.UserInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindAnonymous()
	{
		UserInfo userInfo=new UserInfo();
		if (DaoImpl.findAnonymous(userInfo, pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else
		{
			System.out.println("testFindByUserName �û�����: "+userInfo.getUserName());
		}
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.UserInfoDaoImpl#findByDepartmentID(int, java.util.List, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindByDepartmentID()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.UserInfoDaoImpl#findByIDCard(int, java.lang.String, com.orifound.aiim.entity.UserInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindByIDCard()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.UserInfoDaoImpl#findByRealName(java.lang.String, java.util.List, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindByRealName()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.UserInfoDaoImpl#findByUserName(java.lang.String, com.orifound.aiim.entity.UserInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindByUserName()
	{
		String pUserName="aiim2";
		UserInfo userInfo=new UserInfo();
		if (DaoImpl.findByUserName(pUserName, userInfo, pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else
		{
			System.out.println("testFindByUserName �û�����: "+userInfo.getUserPWD());
		}
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.UserInfoDaoImpl#save(com.orifound.aiim.entity.UserInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testSave()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.UserInfoDaoImpl#update(com.orifound.aiim.entity.UserInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testUpdate()
	{
		fail("Not yet implemented");
	}
	
	/**
	 * ��ѯ���о���ҵ��ָ���ҽ�ɫ����Ա��Ϣ����
	 */
	@Test
	public void findbusinessGuidsTest() {
		List<UserInfo> userInfos = new ArrayList<UserInfo>();
		if (DaoImpl.findBusinessGuids(userInfos,EnumSystemRole.ҵ��ָ��רԱ��ɫ, pErrInfo) == false) {
			fail(pErrInfo.toShortString());
		}
		//ҵ��ָ������Ա����
		for(UserInfo userInfo : userInfos) {
			System.out.println(userInfo.getUserID()+":"+userInfo.getRealName());
		}
	
	}
	
	/**
	 * ��ѯҵ��ָ����ָ�������е�����ְ��Ա
	 */
	@Test
	public void findpartTimePersonsTest() {
		List<UserInfo> businessGuids = new ArrayList<UserInfo>();
		if (DaoImpl.findBusinessGuids(businessGuids,EnumSystemRole.ҵ��ָ��רԱ��ɫ, pErrInfo) == false) {
			fail(pErrInfo.toShortString());
		}
		
	}

}
