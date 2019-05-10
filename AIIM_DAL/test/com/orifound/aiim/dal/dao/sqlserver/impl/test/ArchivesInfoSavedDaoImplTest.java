package com.orifound.aiim.dal.dao.sqlserver.impl.test;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesInfoSavedDaoImpl;
import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 
 * @author tyb
 *
 */
public class ArchivesInfoSavedDaoImplTest {
	//DAO实现类
	private static ArchivesInfoSavedDaoImpl DaoImpl = new ArchivesInfoSavedDaoImpl();
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
	public void testQueryClassifiedForSaveDestroyAppraisal() {
		int formationDepartmentID = 0;
		DataPageInfo dataPageInfo = new DataPageInfo();
		dataPageInfo.setPageSize(10);
		List<ArchivesInfo> archivesInfos = new ArrayList<ArchivesInfo>();
		ArchivesType archivesType = null;
		//DoSomething
		if (DaoImpl.queryClassifiedForSaveDestroyAppraisal(archivesType, formationDepartmentID, dataPageInfo, archivesInfos, pErrInfo) == false) {
			fail(pErrInfo.toString());
		}
		
		System.out.println(archivesInfos.size());
	}

}
