package com.orifound.aiim.bll.service.impl.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.orifound.aiim.bll.service.impl.OfficialArchivesTypeSavedMappingManageServiceImpl;
import com.orifound.aiim.entity.ErrInfo;


public class officialArchivesTypeSavedMappingManageServiceImplTest {
	//ҵ���߼�ʵ����
	private static OfficialArchivesTypeSavedMappingManageServiceImpl ServiceImpl = new OfficialArchivesTypeSavedMappingManageServiceImpl();
	//������Ϣ����
	private static ErrInfo pErrInfo = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//DAOע��
		//ServiceImpl AIIMDataSource.getInstance().getDataSource()));
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
}
