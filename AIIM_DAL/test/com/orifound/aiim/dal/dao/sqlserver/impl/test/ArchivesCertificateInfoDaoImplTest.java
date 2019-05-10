package com.orifound.aiim.dal.dao.sqlserver.impl.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesCertificateInfoDaoImpl;
import com.orifound.aiim.entity.ArchivesCertificateRegister;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 
 * @author tyb
 * 
 */
public class ArchivesCertificateInfoDaoImplTest {

	// DAOʵ����
	private static ArchivesCertificateInfoDaoImpl DaoImpl = new ArchivesCertificateInfoDaoImpl();
	// ������Ϣ����
	private static ErrInfo pErrInfo = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// ����Դע��
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
	public void test1() { 
		//���˱��personID������������personName����֤����certificateType
		Map<String, String> queryString = new LinkedHashMap<String, String>();
		queryString.put("personID", "1");
		queryString.put("personName", "1");
		queryString.put("certificateType", "3");
		
		List<ArchivesCertificateRegister> archivesCertificateRegisters = new ArrayList<ArchivesCertificateRegister>();
		if (DaoImpl.findArchivesCertificateRegistersByQuery(queryString , archivesCertificateRegisters , pErrInfo) == false) {
			fail(pErrInfo.toString());
		}
		System.out.println("succss");
		System.out.println(archivesCertificateRegisters.size());
	}
}
