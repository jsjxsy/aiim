package com.orifound.aiim.dal.dao.sqlserver.impl.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.orifound.aiim.dal.dao.sqlserver.impl.OfficialArchivesDataItemSavedMappingDaoImpl;
import com.orifound.aiim.entity.ErrInfo;


public class OfficialArchivesDataItemSavedMappingDaoImplTest {
	//DAO实现类
	private static OfficialArchivesDataItemSavedMappingDaoImpl DaoImpl = new OfficialArchivesDataItemSavedMappingDaoImpl();
	//错误信息对象
	private static ErrInfo pErrInfo = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//数据源注入
		DaoImpl.setDataSource(AIIMDataSource.getInstance().getDataSource());
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
}
