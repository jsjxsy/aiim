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
 * ��Ч������Ŀ�ֵ�� (DD_EvaluateItem)��DAOʵ���� ������
 * @author tyb
 *
 */
public class EvaluateItemDaoImplTest {
	
	//DAOʵ����
	private static EvaluateItemDaoImpl DaoImpl = new EvaluateItemDaoImpl();
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
	 * ��ѯ���п����� ����
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
