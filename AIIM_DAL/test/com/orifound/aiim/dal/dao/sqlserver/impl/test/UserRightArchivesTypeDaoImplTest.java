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

import com.orifound.aiim.dal.dao.sqlserver.impl.UserRightArchivesTypeDaoImpl;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * �û�������Դ��Ȩ��ϢDAOʵ�ֵĲ�����
 *
 */
public class UserRightArchivesTypeDaoImplTest
{

	//DAOʵ����
	private static UserRightArchivesTypeDaoImpl DaoImpl = new UserRightArchivesTypeDaoImpl();
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
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.UserRightArchivesTypeDaoImpl#save(com.orifound.aiim.entity.UserRightArchivesType, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testSave()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.UserRightArchivesTypeDaoImpl#delete(com.orifound.aiim.entity.UserRightArchivesType, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testDelete()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.UserRightArchivesTypeDaoImpl#update(com.orifound.aiim.entity.UserRightArchivesType, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testUpdate()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.UserRightArchivesTypeDaoImpl#findByUserID(int, java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindByUserID()
	{
		int pUserID=2;
		LinkedHashMap<Integer, ArchivesType> userRightArchivesTypes=new LinkedHashMap<Integer, ArchivesType>();
		if (DaoImpl.findByUserID(pUserID, userRightArchivesTypes, pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else
		{
			System.out.println("testFindByUserID ��ѯ���������: "+userRightArchivesTypes.size());
			for (ArchivesType item : userRightArchivesTypes.values())
			{
				System.out.println(item.getFullCode());
			}
		}
	}

}
