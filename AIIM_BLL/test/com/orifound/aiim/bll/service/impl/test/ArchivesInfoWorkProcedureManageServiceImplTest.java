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

import com.orifound.aiim.bll.service.impl.ArchivesInfoWorkProcedureManageServiceImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesInfoWorkProcedureDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.test.AIIMDataSource;
import com.orifound.aiim.entity.ArchivesInfoWorkProcedure;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.EnumArchivesInfoType;
import com.orifound.aiim.entity.EnumWorkFlowStatus;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 档案工作过程信息管理服务测试类
 *
 */
public class ArchivesInfoWorkProcedureManageServiceImplTest
{

	//业务逻辑实现类
	private static ArchivesInfoWorkProcedureManageServiceImpl ServiceImpl = new ArchivesInfoWorkProcedureManageServiceImpl();
	//错误信息对象
	private static ErrInfo pErrInfo = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		//DAO注入
		ServiceImpl.setArchivesInfoWorkProcedureDao(new ArchivesInfoWorkProcedureDaoImpl(AIIMDataSource.getInstance().getDataSource()));
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
	 * Test method for {@link com.orifound.aiim.bll.service.impl.ArchivesInfoWorkProcedureManageServiceImpl#saveArchivesInfoWorkProcedure(com.orifound.aiim.entity.ArchivesInfoWorkProcedure, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testSaveArchivesInfoWorkProcedure()
	{
		if (SystemInitializePrepare.initialize(pErrInfo)==false)
		{
			fail(pErrInfo.toString());
		}
		else 
		{
			ArchivesInfoWorkProcedure archivesInfoWorkProcedure=new ArchivesInfoWorkProcedure();
			archivesInfoWorkProcedure.setArchivesTypeID(3);
			archivesInfoWorkProcedure.setNBXH(21);
			archivesInfoWorkProcedure.setUserID(2);
			archivesInfoWorkProcedure.setWorkFlowStatus(EnumWorkFlowStatus.著录完成);
			if (ServiceImpl.saveArchivesInfoWorkProcedure(archivesInfoWorkProcedure, pErrInfo)==false)
			{
				fail(pErrInfo.toString());
			}
			else 
			{
				System.out.println("testSaveArchivesInfoWorkProcedure OK.");
			}
		}
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.ArchivesInfoWorkProcedureManageServiceImpl#findArchivesInfoWorkProcedureByNBXH(int, java.util.List, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindArchivesInfoWorkProcedureByNBXH()
	{
		if (SystemInitializePrepare.initialize(pErrInfo)==false)
		{
			fail(pErrInfo.toString());
		}
		else 
		{
			int pNBXH=21;
			ArchivesType archivesType=new ArchivesType(3);
			List<ArchivesInfoWorkProcedure> archivesInfoWorkProcedures=new ArrayList<ArchivesInfoWorkProcedure>();
			if (ServiceImpl.findArchivesInfoWorkProcedureByNBXH(archivesType, pNBXH, archivesInfoWorkProcedures, pErrInfo)==false)
			{
				fail(pErrInfo.toString());
			}
			else 
			{
				System.out.println("testFindArchivesInfoWorkProcedureByNBXH OK.");
				System.out.println(archivesInfoWorkProcedures.size());
			}
		}
	}
	
	@Test
	public void testDeleteArchivesInfoWorkProcedure()
	{
		if (SystemInitializePrepare.initialize(pErrInfo)==false)
		{
			fail(pErrInfo.toString());
		}
		else 
		{
			int pNBXH=20;
			EnumArchivesInfoType enumArchivesInfoType=EnumArchivesInfoType.案卷级档案;
			ArchivesInfoWorkProcedure archivesInfoWorkProcedure=new ArchivesInfoWorkProcedure();
			archivesInfoWorkProcedure.setArchivesTypeID(3);
			archivesInfoWorkProcedure.setNBXH(pNBXH);
			if (ServiceImpl.deleteArchivesInfoWorkProcedure(enumArchivesInfoType, archivesInfoWorkProcedure, pErrInfo)==false)
			{
				fail(pErrInfo.toString());
			}
			else 
			{
				System.out.println("testDeleteArchivesInfoWorkProcedure OK.");
			}
		}
	}

}
