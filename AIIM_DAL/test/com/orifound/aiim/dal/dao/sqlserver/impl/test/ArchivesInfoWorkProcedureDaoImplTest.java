package com.orifound.aiim.dal.dao.sqlserver.impl.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesInfoWorkProcedureDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.UserInfoDaoImpl;
import com.orifound.aiim.entity.ErrInfo;

public class ArchivesInfoWorkProcedureDaoImplTest {
	//DAOʵ����
	private static ArchivesInfoWorkProcedureDaoImpl DaoImpl = new ArchivesInfoWorkProcedureDaoImpl();
	
	private static UserInfoDaoImpl userInfoDaoImpl = new UserInfoDaoImpl();
	
	//������Ϣ����
	private static ErrInfo pErrInfo = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//����Դע��
		DaoImpl.setDataSource(AIIMDataSourceTYB.getInstance().getDataSource());
		//����Դע��
		userInfoDaoImpl.setDataSource(AIIMDataSourceTYB.getInstance().getDataSource());
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
