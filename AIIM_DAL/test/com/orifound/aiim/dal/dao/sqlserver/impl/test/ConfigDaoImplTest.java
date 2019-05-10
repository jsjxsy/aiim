package com.orifound.aiim.dal.dao.sqlserver.impl.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.orifound.aiim.dal.dao.sqlserver.impl.ConfigDaoImpl;
import com.orifound.aiim.entity.Config;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 
 * 系统配置信息DAO实现的测试类
 *
 */
public class ConfigDaoImplTest
{

	private static ConfigDaoImpl configDaoImpl=new ConfigDaoImpl();
	
	private static ErrInfo pErrInfo=null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		configDaoImpl.setDataSource(AIIMDataSource.getInstance().getDataSource());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
		configDaoImpl=null;
		pErrInfo=null;
	}

	@Before
	public void setUp() throws Exception
	{
		pErrInfo=new ErrInfo();
	}

	@After
	public void tearDown() throws Exception
	{
		pErrInfo=null;
	}

	@Test
	public void testDelete()
	{
		Config config=new Config();
		config.setID(5);
		if (configDaoImpl.delete(config, pErrInfo)==false)
		{
			fail(pErrInfo.toString());
		}
	}

	@Test
	public void testFindByConfigType()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testSave()
	{
		//Config config=new Config("testType","xiam1");
//		if (configDaoImpl.save(config, pErrInfo)==false)
//			fail(pErrInfo.toString());
//		else {
//			if (config.getID()==0)
//				fail("id=0");
//		}
	}

	@Test
	public void testUpdate()
	{
		//Config config=new Config("testType","xiamiao123");
//		config.setID(7);
//		if (configDaoImpl.update(config, pErrInfo)==false)
//			fail(pErrInfo.toString());
	}

	@Test
	public void testFindByID()
	{
		Config config=new Config();
		if (configDaoImpl.findByID(1, config, pErrInfo)==false)
			fail(pErrInfo.toString());
	}

}
