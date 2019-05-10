package com.orifound.aiim.bll.service.impl.test;


import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.orifound.aiim.bll.service.impl.AttachedFileAccessControlServiceImpl;
import com.orifound.aiim.bll.service.impl.UseScopesAccessControlServiceImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.AppraisalUseScopesDetailDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.AttachedFileUseRequestPassInfoDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.test.AIIMDataSource;
import com.orifound.commons.acservice.IAttachedFileAccessControlService;
import com.orifound.commons.acservice.IUseScopesAccessControlService;
import com.orifound.commons.acservice.impl.AccessControlServiceImpl;

public class ACServiceTest
{
	//访问控制服务
	private static AccessControlServiceImpl mACService=new AccessControlServiceImpl();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
	}

	@Before
	public void setUp() throws Exception
	{
	}

	@After
	public void tearDown() throws Exception
	{
	}
	
	@Test
	public void testACServiceForAttachedFile()
	{
		Collection<Object> pArchivesTypeACL=new ArrayList<Object>();
		pArchivesTypeACL.add(1);
		pArchivesTypeACL.add(2);
		pArchivesTypeACL.add(3);
		mACService.loadArchivesTypeACL(pArchivesTypeACL);
		
		Collection<Object> pArchivesSecrecyACL=new ArrayList<Object>();
		pArchivesSecrecyACL.add(1);
		pArchivesSecrecyACL.add(2);
		pArchivesSecrecyACL.add(3);
		mACService.loadArchivesSecrecyACL(pArchivesSecrecyACL);
		
		StringBuilder temp=new StringBuilder();
		IUseScopesAccessControlService pUseScopesACService=new UseScopesAccessControlServiceImpl(new AppraisalUseScopesDetailDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		IAttachedFileAccessControlService pAttachedFileACService=new AttachedFileAccessControlServiceImpl(new AttachedFileUseRequestPassInfoDaoImpl(AIIMDataSource.getInstance().getDataSource())); 
		
		try
		{
			System.out.println(mACService.checkArchivesAttachedFileACL(1, 1, 1, 2,pUseScopesACService, pAttachedFileACService, temp));
		}
		catch (Exception e)
		{
			fail(e.toString());
		}
		
		System.out.println(temp);
	}

}
