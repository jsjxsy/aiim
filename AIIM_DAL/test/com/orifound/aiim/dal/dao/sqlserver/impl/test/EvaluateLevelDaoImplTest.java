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
 * ���˵ȼ��ֵ��(DD_EvaluateLevel)��DAOʵ���� ������
 * @author tyb
 *
 */
public class EvaluateLevelDaoImplTest {
	//DAOʵ����
	private static EvaluateLevelDaoImpl DaoImpl = new EvaluateLevelDaoImpl();
	//������Ϣ����
	private static ErrInfo pErrInfo = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//����Դע��
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
	 * ��ѯ���� ����
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
