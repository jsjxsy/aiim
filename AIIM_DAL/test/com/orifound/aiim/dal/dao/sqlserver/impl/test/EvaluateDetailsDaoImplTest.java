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
 * ������ϸDAO������
 * @author tyb
 *
 */
public class EvaluateDetailsDaoImplTest {
	
	//DAOʵ����
	private static EvaluateDetailsDaoImpl DaoImpl = new EvaluateDetailsDaoImpl();
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

	@Test
	public void testFindAll() {
		fail("Not yet implemented");
	}

	/**
	 * ����Ψһ��ʶ���ҿ�����ϸ��Ϣ
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
	 * �����ض���ȵĿ�����ϸ ����
	 */
	@Test
	public void insertByYearTest() {
		String year = "2011";
		if (DaoImpl.insertByYear(year, pErrInfo) == false) {
			fail(pErrInfo.toString());
		}
		System.out.println("����ɹ���");
	}
	
	/** 
	 * ���ݿ��˼�¼��Ų�ѯ���˵Ǽ���ϸ��Ϣ��ʾ�� ����
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
