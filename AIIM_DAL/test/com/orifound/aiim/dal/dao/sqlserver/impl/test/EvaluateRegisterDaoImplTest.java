package com.orifound.aiim.dal.dao.sqlserver.impl.test;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.orifound.aiim.dal.dao.sqlserver.impl.EvaluateRegisterDaoImpl;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.EvaluateRegister;
import com.orifound.aiim.entity.EvaluateRegisterVO;

/**
 * 考核登记信息DAO测试类
 * @author tyb
 *
 */
public class EvaluateRegisterDaoImplTest {
	//DAO实现类
	private static EvaluateRegisterDaoImpl DaoImpl = new EvaluateRegisterDaoImpl();
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

	@Test
	public void testFindByID() {
		EvaluateRegister evaluateRegister = new EvaluateRegister();
		if(DaoImpl.findByID(2, evaluateRegister , pErrInfo) == false) {
			fail(pErrInfo.toShortString());
		}
		System.out.println("考核登记信息 查询成功！"+evaluateRegister.getYears());
	}

	@Test
	public void testSave() {
		EvaluateRegister evaluateRegister = new EvaluateRegister("2009", 1, 1);
		if(DaoImpl.save(evaluateRegister , pErrInfo) == false) {
			fail(pErrInfo.toShortString());
		}
		System.out.println("考核登记信息 保存成功！");
	}

	/**
	 * 考核登记信息 更新测试
	 */
	@Test
	public void testUpdate() {
		EvaluateRegister evaluateRegister = new EvaluateRegister(2, "2010", 1, 1, 99, 1, "", new Date(), 1, 1);
		if(DaoImpl.update(evaluateRegister , pErrInfo) == false) {
			fail(pErrInfo.toShortString());
		}
		System.out.println("考核登记信息 更新成功！");
	}
	
	/**
	 * 当前年度考核登记信息 插入测试
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
	 * 当前年度考核登记信息 查询测试
	 */
	@Test
	public void findByYearTest() {
		String year = "2010";
		List<EvaluateRegisterVO> evaluateRegisterVOs = new ArrayList<EvaluateRegisterVO>();
		if (DaoImpl.findByYear(year, evaluateRegisterVOs , pErrInfo) == false) {
			fail(pErrInfo.toString());
		}
		System.out.println("evaluateRegisterVOs size="+evaluateRegisterVOs.size());
	}
	
	@Test
	public void insertAppendByYearTest() {
		if (DaoImpl.insertAppendByYear("2010", pErrInfo) == false) {
			fail(pErrInfo.toString());
		}
		System.out.println("追加成功！");
	}
	
	@Test
	public void searchTest() {
		//DoSomething
		List<EvaluateRegisterVO> evaluateRegisterVOs = new ArrayList<EvaluateRegisterVO>();
		DataPageInfo dataPageInfo = new DataPageInfo();
		dataPageInfo.setPageSize(10);
		String evaluateName = "";
		int dutyId = 1;
		String registerDate = "1";
		int minScore = 0;
		int maxScore = 0;
		if (DaoImpl.search(evaluateName, dutyId, registerDate, minScore, maxScore, dataPageInfo , evaluateRegisterVOs , pErrInfo) == false) {
			fail(pErrInfo.toString());
		}
		
		for(EvaluateRegisterVO evaluateRegisterVO : evaluateRegisterVOs) {
			System.out.println(evaluateRegisterVO.getYears()+" "+evaluateRegisterVO.getRealName());
		}
	}
}


