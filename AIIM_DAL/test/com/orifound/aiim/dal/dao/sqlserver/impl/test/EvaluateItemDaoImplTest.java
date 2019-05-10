package com.orifound.aiim.dal.dao.sqlserver.impl.test;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.orifound.aiim.dal.dao.sqlserver.impl.EvaluateItemDaoImpl;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.EvaluateItem;

/**
 * 绩效考评项目字典表 (DD_EvaluateItem)的DAO实现类 测试类
 * @author tyb
 *
 */
public class EvaluateItemDaoImplTest {
	
	//DAO实现类
	private static EvaluateItemDaoImpl DaoImpl = new EvaluateItemDaoImpl();
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
	 * 查询所有考核项 测试
	 */
	@Test
	public void testFindAll() {
		List<EvaluateItem> EvaluateItems = new ArrayList<EvaluateItem>();
		if (DaoImpl.findAll(EvaluateItems , pErrInfo) == false) {
			fail(pErrInfo.toString());
		}
		System.out.println(EvaluateItems.size());
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
