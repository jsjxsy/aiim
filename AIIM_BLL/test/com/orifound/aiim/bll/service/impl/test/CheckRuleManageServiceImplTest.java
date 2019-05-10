package com.orifound.aiim.bll.service.impl.test;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.orifound.aiim.bll.service.impl.CheckRuleManageServiceImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.CheckRuleDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.test.AIIMDataSource;
import com.orifound.aiim.entity.CheckRule;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 校验规则管理服务测试类
 *
 */
public class CheckRuleManageServiceImplTest
{
	//业务逻辑实现类
	private static CheckRuleManageServiceImpl ServiceImpl = new CheckRuleManageServiceImpl();
	//错误信息对象
	private static ErrInfo pErrInfo = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		//DAO注入
		ServiceImpl.setCheckRuleDao(new CheckRuleDaoImpl(AIIMDataSource.getInstance().getDataSource()));
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
	
	@Test
	public void testSaveCheckRule()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteCheckRule()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateCheckRule()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testFindCheckRules()
	{
		Map<Integer, CheckRule> checkRules=new HashMap<Integer, CheckRule>();
		if (ServiceImpl.findCheckRules(checkRules, pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else
		{
			System.out.println("testFindCheckRules 查询结果集数量："+checkRules.size());
		}
	}

	@Test
	public void testFindCheckRuleByID()
	{
		fail("Not yet implemented");
	}

}
