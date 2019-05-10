package com.orifound.aiim.dal.dao.sqlserver.impl.test;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.orifound.aiim.dal.dao.sqlserver.impl.EvaluateLevelDaoImpl;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.EvaluateLevel;

/**
 * 考核等级字典表(DD_EvaluateLevel)的DAO实现类 测试类
 * @author tyb
 *
 */
public class EvaluateLevelDaoImplTest {
	//DAO实现类
	private static EvaluateLevelDaoImpl DaoImpl = new EvaluateLevelDaoImpl();
	//错误信息对象
	private static ErrInfo pErrInfo = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//数据源注入
		DaoImpl.setDataSource(AIIMDataSourceTYB.getInstance().getDataSource());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		DaoImpl = null;
		pErrInfo = null;
	}

	@Before
	public void setUp() throws Exception {
		pErrInfo = new ErrInfo();
	}

	@After
	public void tearDown() throws Exception {
		pErrInfo = null;
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	/**
	 * 查询所有 测试
	 */
	@Test
	public void testFindAll() {
		List<EvaluateLevel> evaluateLevels = new ArrayList<EvaluateLevel>();
		if (DaoImpl.findAll(evaluateLevels , pErrInfo) == false) {
			fail(pErrInfo.toString());
		}
		System.out.println("evaluateLevels="+evaluateLevels.size());
	}

	@Test
	public void testFindByID() {
		fail("Not yet implemented");
	}

	@Test
	public void testSave() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

}
