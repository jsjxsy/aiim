package com.orifound.aiim.bll.service.impl.test;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.orifound.aiim.bll.service.impl.EvaluateManageServiceImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.EvaluateDetailsDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.EvaluateRegisterDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.test.AIIMDataSourceTYB;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.EvaluateRegisterVO;

/**
 * 考核管理服务实现测试类
 * @author tyb
 *
 */
public class EvaluateManageServiceImplTest {
	
	//业务逻辑实现类
	private static EvaluateManageServiceImpl ServiceImpl = new EvaluateManageServiceImpl();
	//错误信息对象
	private static ErrInfo pErrInfo = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//DAO注入
		ServiceImpl.setEvaluateRegisterDao(new EvaluateRegisterDaoImpl(AIIMDataSourceTYB.getInstance().getDataSource()));
		ServiceImpl.setEvaluateDetailsDao(new EvaluateDetailsDaoImpl(AIIMDataSourceTYB.getInstance().getDataSource()));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		ServiceImpl = null;
		pErrInfo = null;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		pErrInfo = new ErrInfo();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		pErrInfo = null;
	}

	@Test
	public void testFindByMaxYear() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindByYear() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindEvaluateDetailsByRegID() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateDetail() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateEvaluate() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertAppendByYear() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertByYear() {
		fail("Not yet implemented");
	}

	/**
	 * 考核登记信息检索
	 */
	@Test
	public void testSearch() {
		List<EvaluateRegisterVO> evaluateRegisterVOs = new ArrayList<EvaluateRegisterVO>();
		DataPageInfo dataPageInfo = new DataPageInfo();
		dataPageInfo.setPageSize(10);
		String evaluateName = "";
		int dutyId = 1;
		String registerDate = "1";
		int minScore = 0;
		int maxScore = 0;
		if (ServiceImpl.search(evaluateName, dutyId, registerDate, minScore, maxScore, dataPageInfo, evaluateRegisterVOs, pErrInfo) == false) {
			fail(pErrInfo.toString());
		}
		
		for(EvaluateRegisterVO evaluateRegisterVO : evaluateRegisterVOs) {
			System.out.println(evaluateRegisterVO.getYears()+" "+evaluateRegisterVO.getRealName());
		}
	}
	
	@Test
	public void deleteBatchTest() {
		List<Integer> evaluateIds = new ArrayList<Integer>();
		evaluateIds.add(51);
		if (ServiceImpl.deleteBatch(evaluateIds , pErrInfo) == false) {
			fail(pErrInfo.toString());
		}
		System.out.println("批量删除成功！");
	}

}
