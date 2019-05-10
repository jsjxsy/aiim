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
import com.orifound.aiim.bll.service.impl.ArchivesInfoWorkingManageServiceImpl;
import com.orifound.aiim.bll.service.impl.CurrentContentIDManageServiceImpl;
import com.orifound.aiim.bll.util.ArchivesInfoFactory;
import com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesInfoWorkProcedureDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesInfoWorkingDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.CurrentContentIDDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.test.AIIMDataSource;
import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesInfoQueryCondition;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ArchivesTypeDataItem;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EnumArchivesInfoType;
import com.orifound.aiim.entity.EnumOperatorType;
import com.orifound.aiim.entity.EnumSystemDataItem;
import com.orifound.aiim.entity.EnumWorkFlowStatus;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 档案工作表管理服务测试类
 *
 */
public class ArchivesInfoWorkingManageServiceImplTest
{

	//业务逻辑实现类
	private static ArchivesInfoWorkingManageServiceImpl ServiceImpl = new ArchivesInfoWorkingManageServiceImpl();
	//错误信息对象
	private static ErrInfo pErrInfo = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		//DAO注入
		ServiceImpl.setArchivesInfoWorkingDao(new ArchivesInfoWorkingDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		
		//业务逻辑注入
		ServiceImpl.setArchivesInfoWorkProcedureManageService(new ArchivesInfoWorkProcedureManageServiceImpl(new ArchivesInfoWorkProcedureDaoImpl(AIIMDataSource.getInstance().getDataSource())));
		ServiceImpl.setCurrentContentIDManageService(new CurrentContentIDManageServiceImpl(new CurrentContentIDDaoImpl(AIIMDataSource.getInstance().getDataSource())));
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
	 * Test method for {@link com.orifound.aiim.bll.service.impl.ArchivesInfoWorkingManageServiceImpl#saveArchivesInfo(com.orifound.aiim.entity.ArchivesInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testSaveArchivesInfo()
	{
		//系统初始化
		if (SystemInitializePrepare.initialize(pErrInfo)==false)
		{
			fail(pErrInfo.toString());
		}
		else
		{
			ArchivesInfo archivesInfo=null;
			try
			{
				archivesInfo=ArchivesInfoFactory.newArchivsInfo(3); //JX14
			}
			catch (Exception e)
			{
				fail(e.toString());
			}
			
			archivesInfo.setWorkFlowStatus(EnumWorkFlowStatus.著录完成);
			archivesInfo.setFondsID(1);//"G01"
			archivesInfo.setRetentionPeriodID(1);
			archivesInfo.setParentNBXH(20);
			archivesInfo.setSecrecyID(2);
			archivesInfo.setPageSum(5);
			archivesInfo.setFormationYear(2011);
			archivesInfo.setFormationDepartmentID(2);
			archivesInfo.setTitle("xx");
			archivesInfo.getRowFieldsValues().get("ftm").setValue("xx");
			archivesInfo.setResponsibility("北京东方基业科技发展有限公司");
			int userID=2;
			ArchivesType archivesType=new ArchivesType(3);
			EnumArchivesInfoType enumArchivesInfoType=EnumArchivesInfoType.卷内文件;
			if (ServiceImpl.saveArchivesInfo(userID, archivesType, enumArchivesInfoType, archivesInfo, pErrInfo)==false)
			{
				fail(pErrInfo.toString());
			}
			else 
			{
				System.out.println("save OK.   NBXH: "+archivesInfo.getNBXH());
			}
		}
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.ArchivesInfoWorkingManageServiceImpl#deleteArchivesInfo(com.orifound.aiim.entity.ArchivesInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testDeleteArchivesInfo()
	{
		
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.ArchivesInfoWorkingManageServiceImpl#updateArchivesInfo(com.orifound.aiim.entity.ArchivesInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testUpdateArchivesInfo()
	{
		//系统初始化
		if (SystemInitializePrepare.initialize(pErrInfo)==false)
		{
			fail(pErrInfo.toString());
		}
		else
		{
			ArchivesInfo archivesInfo=null;
			try
			{
				archivesInfo=ArchivesInfoFactory.newArchivsInfo(3); //JX14
			}
			catch (Exception e)
			{
				fail(e.toString());
			}
			
			archivesInfo.setNBXH(21);
			archivesInfo.setParentNBXH(20);
			archivesInfo.setWorkFlowStatus(EnumWorkFlowStatus.著录完成);
			archivesInfo.setArchivesFondsID("G01");
			archivesInfo.setRetentionPeriodID(3);
			archivesInfo.setSecrecyID(5);
			archivesInfo.setPageSum(5);
			archivesInfo.setFormationYear(1998);
			archivesInfo.setFormationDepartmentID(3);
			archivesInfo.setPageSum(1);
			ArchivesType archivesType=new ArchivesType(3);
			EnumArchivesInfoType enumArchivesInfoType=EnumArchivesInfoType.卷内文件;
			if (ServiceImpl.updateArchivesInfo(archivesType, enumArchivesInfoType, archivesInfo, pErrInfo)==false)
			{
				fail(pErrInfo.toString());
			}
			else 
			{
				System.out.println("testUpdateArchivesInfo OK.");
			}
		}
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.ArchivesInfoWorkingManageServiceImpl#findArchivesInfos(com.orifound.aiim.entity.EnumArchivesInfoType, int[], com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.EnumWorkFlowStatus, com.orifound.aiim.entity.DataPageInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindArchivesInfos()
	{
		//系统初始化
		if (SystemInitializePrepare.initialize(pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else
		{
			EnumArchivesInfoType enumArchivesInfoType=EnumArchivesInfoType.文件级档案;
			int[] userID=new int[]{1,2};
			ArchivesType archivesType=new ArchivesType(3);//JX14
			EnumWorkFlowStatus enumWorkFlowStatus=EnumWorkFlowStatus.著录完成;
			List<ArchivesInfoQueryCondition> archivesInfoQueryConditions=new ArrayList<ArchivesInfoQueryCondition>();

			
			DataPageInfo dataPageInfo=new DataPageInfo(10, 1);
			java.util.List<ArchivesInfo> archivesInfos=new ArrayList<ArchivesInfo>();
			if (ServiceImpl.findArchivesInfos(userID, archivesType, enumArchivesInfoType, enumWorkFlowStatus, archivesInfoQueryConditions, dataPageInfo, archivesInfos, pErrInfo)==false)
			{
				fail(pErrInfo.toString());
			}
			else 
			{
				
//				System.out.println("testFindArchivesInfosByWorkFlowStatus OK. archivesInfos.size: "+archivesInfos.size());
				System.out.println("row count: "+dataPageInfo.getRowCount());
//				System.out.println(archivesInfos.get(0).getFormationYear());
			}
			
			System.out.println("---------------------------------------------");
			
			
			for (ArchivesTypeDataItem item : SystemInitializePrepare.getSystemInitializer().getPlaneArchivesTypes().get(3).getDataItemsForInputQuery().values())
			{
//				if (item.getSystemDataItemType()==EnumSystemDataItem.档案形成年度)
//				{
//					archivesInfoQueryConditions.add(new ArchivesInfoQueryCondition(item, EnumOperatorType.等于, "2010", true));
//				}
				
				if (item.getSystemDataItemType()==EnumSystemDataItem.题名)
				{
					archivesInfoQueryConditions.add(new ArchivesInfoQueryCondition(item, EnumOperatorType.等于, "f", true));
				}
			}
			
			dataPageInfo=new DataPageInfo(10, 1);
			archivesInfos=new ArrayList<ArchivesInfo>();
			if (ServiceImpl.findArchivesInfos(userID, archivesType, enumArchivesInfoType, enumWorkFlowStatus, archivesInfoQueryConditions, dataPageInfo, archivesInfos, pErrInfo)==false)
			{
				fail(pErrInfo.toString());
			}
			else 
			{
				
//				System.out.println("testFindArchivesInfosByWorkFlowStatus OK. archivesInfos.size: "+archivesInfos.size());
				System.out.println("row count: "+dataPageInfo.getRowCount());
//				System.out.println(archivesInfos.get(0).getFormationYear());
			}
			
		}
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.ArchivesInfoWorkingManageServiceImpl#findArchivesInfoByNBXH(int, com.orifound.aiim.entity.ArchivesInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindArchivesInfoByNBXH()
	{
		//系统初始化
		if (SystemInitializePrepare.initialize(pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else
		{
			int pNBXH=8;
			ArchivesType archivesType=new ArchivesType(3);
			ArchivesInfo archivesInfo=new ArchivesInfo();
			if (ServiceImpl.findArchivesInfoByNBXH(pNBXH, archivesType, archivesInfo, pErrInfo)==false)
			{
				fail(pErrInfo.toString());
			}
			else 
			{
				System.out.println("testFindArchivesInfoByNBXH  OK. ");
				System.out.println(archivesInfo.getFormationDepartment());
			}
		}
	}
	
	@Test
	public void testFindChildArchivesInfosByNBXH()
	{
		//系统初始化
		if (SystemInitializePrepare.initialize(pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else
		{
			int pNBXH=20;
			ArchivesType archivesType=new ArchivesType(3);
			List<ArchivesInfo> archivesInfos=new ArrayList<ArchivesInfo>();
			if (ServiceImpl.findChildArchivesInfosByNBXH(pNBXH, archivesType, archivesInfos, pErrInfo)==false)
			{
				fail(pErrInfo.toString());
			}
			else 
			{
				System.out.println("testFindChildArchivesInfosByNBXH  OK. ");
				System.out.println(archivesInfos.size());
			}
		}
	}
	
	@Test
	public void tesSubmitToInputCheck()
	{
		//系统初始化
		if (SystemInitializePrepare.initialize(pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else
		{
			int pNBXH=0;
			ArchivesType archivesType=new ArchivesType(3);
			List<ArchivesInfo> archivesInfos=new ArrayList<ArchivesInfo>();
			try
			{
				pNBXH=19;
				archivesInfos.add(ArchivesInfoFactory.newArchivsInfo(3, pNBXH));
				
				pNBXH=20;
				archivesInfos.add(ArchivesInfoFactory.newArchivsInfo(3, pNBXH));
			}
			catch (Exception e) 
			{
				fail(e.toString());
			}
			
			
			int userID=3;
			EnumArchivesInfoType enumArchivesInfoType=EnumArchivesInfoType.案卷级档案;
			if (ServiceImpl.submitToInputCheck(userID, archivesType, enumArchivesInfoType, archivesInfos, pErrInfo)==false)
			{
				fail(pErrInfo.toString());
			}
			else 
			{
				System.out.println("tesSubmitToInputCheck  OK. ");
			}
		}
	}
	
	@Test
	public void tesDeleteArchivesInfo()
	{

		//系统初始化
		if (SystemInitializePrepare.initialize(pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else
		{
			int pNBXH=20;
			ArchivesInfo archivesInfo=null;
			try
			{
				archivesInfo=ArchivesInfoFactory.newArchivsInfo(3, pNBXH);
			}
			catch (Exception e)
			{
				fail(e.toString());
			}
			
			ArchivesType archivesType=new ArchivesType(3);
			
			EnumArchivesInfoType enumArchivesInfoType=EnumArchivesInfoType.案卷级档案;
			if (ServiceImpl.deleteArchivesInfo(archivesType, enumArchivesInfoType, archivesInfo, pErrInfo)==false)
			{
				fail(pErrInfo.toString());
			}
			else 
			{
				System.out.println("tesDeleteArchivesInfo  OK. ");
			}
		}
	
	}
	
	@Test
	public void tesDeleteArchivesInfos()
	{

		//系统初始化
		if (SystemInitializePrepare.initialize(pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else
		{
			List<ArchivesInfo> archivesInfos=new ArrayList<ArchivesInfo>();
			try
			{
				archivesInfos.add(ArchivesInfoFactory.newArchivsInfo(3, 8));
				archivesInfos.add(ArchivesInfoFactory.newArchivsInfo(3, 9));
				archivesInfos.add(ArchivesInfoFactory.newArchivsInfo(3, 10));
			}
			catch (Exception e)
			{
				fail(e.toString());
			}
			
			ArchivesType archivesType=new ArchivesType(3);
			
			EnumArchivesInfoType enumArchivesInfoType=EnumArchivesInfoType.文件级档案;
			if (ServiceImpl.deleteArchivesInfos(archivesType, enumArchivesInfoType, archivesInfos, pErrInfo)==false)
			{
				fail(pErrInfo.toString());
			}
			else 
			{
				System.out.println("tesDeleteArchivesInfos  OK. ");
			}
		}
	
	}

}
