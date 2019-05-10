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

import com.orifound.aiim.dal.dao.sqlserver.impl.RoleRightArchivesTypeDaoImpl;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ��ɫ������Դ��Ȩ��ϢDAOʵ�ֵĲ�����
 *
 */
public class RoleRightArchivesTypeDaoImplTest
{

	//DAOʵ����
	private static RoleRightArchivesTypeDaoImpl DaoImpl = new RoleRightArchivesTypeDaoImpl();
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
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.RoleRightArchivesTypeDaoImpl#save(com.orifound.aiim.entity.RoleRightArchivesType, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testSave()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.RoleRightArchivesTypeDaoImpl#delete(com.orifound.aiim.entity.RoleRightArchivesType, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testDelete()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.RoleRightArchivesTypeDaoImpl#update(com.orifound.aiim.entity.RoleRightArchivesType, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testUpdate()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.RoleRightArchivesTypeDaoImpl#findByRoleID(int, java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindByRoleID()
	{
		int[] pRoleID=new int[]{1};
		LinkedHashMap<Integer, ArchivesType> roleRightArchivesTypes=new LinkedHashMap<Integer, ArchivesType>();
		if (DaoImpl.findByRoleID(pRoleID, roleRightArchivesTypes, pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else
		{
			System.out.println("testFindByUserID ��ѯ���������: "+roleRightArchivesTypes.size());
			for (ArchivesType item : roleRightArchivesTypes.values())
			{
				System.out.println(item.getFullCode());
			}
		}
	}

}
