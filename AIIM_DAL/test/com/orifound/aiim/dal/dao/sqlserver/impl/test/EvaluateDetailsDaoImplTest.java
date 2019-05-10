package com.orifound.aiim.dal.dao.sqlserver.impl.test;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.orifound.aiim.dal.dao.sqlserver.impl.EvaluateDetailsDaoImpl;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.EvaluateDetails;

/**
 * 考核明细DAO测试类
 * @author tyb
 *
 */
public class EvaluateDetailsDaoImplTest {
	
	//DAO实现类
	private static EvaluateDetailsDaoImpl DaoImpl = new EvaluateDetailsDaoImpl();
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

	@Test
	public void testFindAll() {
		fail("Not yet implemented");
	}

	/**
	 * 根据唯一标识查找考核明细信息
	 */
	@Test
	public void testFindByEvaluateRegID() {
		List<EvaluateDetails> evaluateDetailss = new ArrayList<EvaluateDetails>();
		//DoSomething
		if (DaoImpl.findByEvaluateRegID(21, evaluateDetailss, pErrInfo) == false) {
			fail(pErrInfo.toString());
		}
		System.out.println(evaluateDetailss.size());
	}

	@Test
	public void testSave() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		EvaluateDetails evaluateDetails = new EvaluateDetails();
		if (DaoImpl.update(evaluateDetails , pErrInfo) == false) {
			fail(pErrInfo.toString());
		}
	}

	/**
	 * 插入特定年度的考核明细 测试
	 */
	@Test
	public void insertByYearTest() {
		String year = "2011";
		if (DaoImpl.insertByYear(year, pErrInfo) == false) {
			fail(pErrInfo.toString());
		}
		System.out.println("插入成功！");
	}
	
	/** 
	 * 根据考核记录编号查询考核登记明细信息显示类 测试
	 */
	@Test
	public void findByEvaluateRegIDTest() {
		int evaluateRegID = 21;
		List<EvaluateDetails> evaluates = new ArrayList<EvaluateDetails>();
		if (DaoImpl.findByEvaluateRegID(evaluateRegID, evaluates  , pErrInfo) == false) {
			fail(pErrInfo.toString());
		}
		for(EvaluateDetails details : evaluates) {
			System.out.println(details.getEvaluateRegID());
			System.out.println(details.getTag());
		}
	}
}
