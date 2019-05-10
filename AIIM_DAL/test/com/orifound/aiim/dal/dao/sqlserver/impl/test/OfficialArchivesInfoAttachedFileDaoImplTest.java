package com.orifound.aiim.dal.dao.sqlserver.impl.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.orifound.aiim.dal.dao.sqlserver.impl.OfficialArchivesInfoAttachedFileDaoImpl;
import com.orifound.aiim.entity.ErrInfo;


public class OfficialArchivesInfoAttachedFileDaoImplTest {
	//DAOʵ����
	private static OfficialArchivesInfoAttachedFileDaoImpl DaoImpl = new OfficialArchivesInfoAttachedFileDaoImpl();
	//������Ϣ����
	private static ErrInfo pErrInfo = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//����Դע��
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
