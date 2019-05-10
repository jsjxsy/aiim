/**
 * 
 */
package com.orifound.aiim.bll.service.impl.test;

import static org.junit.Assert.*;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.orifound.aiim.bll.service.impl.ArchivesFondsManageServiceImpl;
import com.orifound.aiim.bll.service.impl.ArchivesInfoQueryServiceImpl;
import com.orifound.aiim.bll.service.impl.ArchivesInfoTableManageServiceImpl;
import com.orifound.aiim.bll.service.impl.ArchivesSecrecyManageServiceImpl;
import com.orifound.aiim.bll.service.impl.ArchivesTypeDataItemManageServiceImpl;
import com.orifound.aiim.bll.service.impl.ArchivesTypeManageServiceImpl;
import com.orifound.aiim.bll.service.impl.CheckRuleManageServiceImpl;
import com.orifound.aiim.bll.service.impl.DataSourceItemManageServiceImpl;
import com.orifound.aiim.bll.service.impl.DataSourceManageServiceImpl;
import com.orifound.aiim.bll.service.impl.DepartmentInfoManageServiceImpl;
import com.orifound.aiim.bll.service.impl.RetentionPeriodManageServiceImpl;
import com.orifound.aiim.bll.service.impl.SystemInitializeServiceImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesFondsDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesInfoSavedDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesInfoTableDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesSecrecyDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesTypeDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesTypeDataItemDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.CheckRuleDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.DataSourceDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.DataSourceItemDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.DepartmentInfoDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.RetentionPeriodDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.test.AIIMDataSource;
import com.orifound.aiim.dal.dao.sqlserver.impl.test.AIIMDataSourceDXT;
import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesInfoQueryCondition;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ArchivesTypeDataItem;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.SystemInitializer;
import com.orifound.aiim.entity.UserInfo;

/**
 * @author Administrator
 *
 */
public class ArchivesInfoQueryServiceImplTest {
	


	//业务逻辑实现类
	private static ArchivesInfoQueryServiceImpl ServiceImpl1 = new ArchivesInfoQueryServiceImpl();
	
	private static SystemInitializeServiceImpl ServiceImpl = new SystemInitializeServiceImpl();
	
	//错误信息对象
	private static ErrInfo pErrInfo = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {		
		//DAO注入		
		ServiceImpl1.setArchivesInfoSavedDao(new ArchivesInfoSavedDaoImpl( AIIMDataSourceDXT.getInstance().getDataSource()));
		
		
//业务逻辑对象注入
		
		//档案全宗
		ArchivesFondsManageServiceImpl archivesFondsManageServiceImpl=new ArchivesFondsManageServiceImpl();
		archivesFondsManageServiceImpl.setArchivesFondsDao(new ArchivesFondsDaoImpl(AIIMDataSourceDXT.getInstance().getDataSource()));
		ServiceImpl.setArchivesFondsManageService(archivesFondsManageServiceImpl);
		
		//档案相关信息表管理服务对象
		ArchivesInfoTableManageServiceImpl archivesInfoTableManageServiceImpl=new ArchivesInfoTableManageServiceImpl();
		archivesInfoTableManageServiceImpl.setArchivesInfoTableDao(new ArchivesInfoTableDaoImpl(AIIMDataSourceDXT.getInstance().getDataSource()));
		ServiceImpl.setArchivesInfoTableManageService(archivesInfoTableManageServiceImpl);
		
		//档案分类管理服务对象
		ArchivesTypeManageServiceImpl archivesTypeManageServiceImpl=new ArchivesTypeManageServiceImpl();
		archivesTypeManageServiceImpl.setArchivesTypeDao(new ArchivesTypeDaoImpl(AIIMDataSourceDXT.getInstance().getDataSource()));
		ServiceImpl.setArchivesTypeManageService(archivesTypeManageServiceImpl);
		
		//档案分类数据项管理服务对象
		ArchivesTypeDataItemManageServiceImpl archivesTypeDataItemManageServiceImpl=new ArchivesTypeDataItemManageServiceImpl();
		archivesTypeDataItemManageServiceImpl.setArchivesTypeDataItemDao(new ArchivesTypeDataItemDaoImpl(AIIMDataSourceDXT.getInstance().getDataSource()));
		ServiceImpl.setArchivesTypeDataItemManageService(archivesTypeDataItemManageServiceImpl);
		
		//数据源管理服务对象
		DataSourceManageServiceImpl dataSourceManageServiceImpl=new DataSourceManageServiceImpl();
		dataSourceManageServiceImpl.setDataSourceDao(new DataSourceDaoImpl(AIIMDataSourceDXT.getInstance().getDataSource()));
		ServiceImpl.setDataSourceManageService(dataSourceManageServiceImpl);
		
		//数据源的数据项管理服务对象
		DataSourceItemManageServiceImpl dataSourceItemManageServiceImpl=new DataSourceItemManageServiceImpl();
		dataSourceItemManageServiceImpl.setDataSourceItemDao(new DataSourceItemDaoImpl(AIIMDataSourceDXT.getInstance().getDataSource()));
		ServiceImpl.setDataSourceItemManageService(dataSourceItemManageServiceImpl);
		
		//校验规则管理服务对象
		CheckRuleManageServiceImpl checkRuleManageServiceImpl=new CheckRuleManageServiceImpl();
		checkRuleManageServiceImpl.setCheckRuleDao(new CheckRuleDaoImpl(AIIMDataSourceDXT.getInstance().getDataSource()));
		ServiceImpl.setCheckRuleManageService(checkRuleManageServiceImpl);
		
		//保管期限管理服务对象
		RetentionPeriodManageServiceImpl retentionPeriodManageServiceImpl=new RetentionPeriodManageServiceImpl(new RetentionPeriodDaoImpl(AIIMDataSourceDXT.getInstance().getDataSource()));
		ServiceImpl.setRetentionPeriodManageService(retentionPeriodManageServiceImpl);
		
		//档案密级管理服务对象
		ArchivesSecrecyManageServiceImpl archivesSecrecyManageServiceImpl=new ArchivesSecrecyManageServiceImpl(new ArchivesSecrecyDaoImpl(AIIMDataSourceDXT.getInstance().getDataSource()));
		ServiceImpl.setArchivesSecrecyManageService(archivesSecrecyManageServiceImpl);
		
		//部门信息管理服务对象
		DepartmentInfoManageServiceImpl departmentInfoManageServiceImpl=new DepartmentInfoManageServiceImpl(new DepartmentInfoDaoImpl(AIIMDataSourceDXT.getInstance().getDataSource()));
		ServiceImpl.setDepartmentInfoManageService(departmentInfoManageServiceImpl);
		
		
	
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
		SystemInitializer systemInitializer=SystemInitializer.getInstance();
		ServiceImpl.initialize(systemInitializer, pErrInfo);
		pErrInfo = new ErrInfo();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		pErrInfo = null;
	}
	
	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.ArchivesInfoQueryServiceImpl#findArchivesInfoByBarcode(java.lang.String, com.orifound.aiim.entity.ArchivesInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindArchivesInfoByBarcode() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.ArchivesInfoQueryServiceImpl#findArchivesInfoByNBXH(com.orifound.aiim.entity.ArchivesType, int, com.orifound.aiim.entity.ArchivesInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindArchivesInfoByNBXH() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.ArchivesInfoQueryServiceImpl#findQueryDataItems(java.util.List, java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindQueryDataItems() {
		fail("Not yet implemented");
	}

	@Test
	public void queryCrossClassified(){
		fail("Not yet implemented");
	}
	
	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.ArchivesInfoQueryServiceImpl#queryClassified(com.orifound.aiim.entity.ArchivesType, java.util.List, com.orifound.aiim.entity.DataPageInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testQueryClassified() {	
		ArchivesType archivesType = new ArchivesType();
		archivesType.setID(3);
		DataPageInfo dataPageInfo = new DataPageInfo();
		dataPageInfo.setPageSize(10);
		dataPageInfo.setCurrentPage(1);
		Map<String, ArchivesTypeDataItem> dataItems = null;	
		ArchivesTypeDataItem dataItem = null;
		List< ArchivesInfoQueryCondition> archivesInfoQueryConditions = null;
		ArchivesInfoQueryCondition archivesInfoQueryCondition = null;
		
		dataItems = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesType.getID()).getDataItemsForInputQuery();
		List<ArchivesInfo> archivesInfos =new ArrayList<ArchivesInfo>();
		archivesInfoQueryConditions = new ArrayList<ArchivesInfoQueryCondition>();
		UserInfo userinfo = new UserInfo();

		
		
	
		
		
//		//开始构造查询条件集合
//		while (enumeration.hasMoreElements()) {
//			parameterName = (String) enumeration.nextElement();// 取出条件的名字
//			if (!"archivesTypeId".equals(parameterName) && !"dataPageInfo.currentPage".equals(parameterName)) {
//				values = request.getParameterValues(parameterName);// 取出条件所对应的值
//				// 该值为dataItem的Id
//
//				dataItem = dataItems.get(parameterName);// 取得与表单项对应的dataItem
//				
//				if (dataItem != null && values.length > 1) {//两个值
//					
//					if (values[0] != "" && values[1] != "") {//都不为空
//						// 把值放入archivesInfoQueryCondition
//					    archivesInfoQueryCondition = new ArchivesInfoQueryCondition(dataItem);// 构造archivesInfoQueryCondition对象
//						archivesInfoQueryCondition.setMinValue(values[0]);
//						archivesInfoQueryCondition.setMaxValue(values[1]);
//						archivesInfoQueryConditions.put(parameterName,archivesInfoQueryCondition);// 构造archivesInfoQueryCondition的集合
//					} else if(values[0] == "" && values[1] == ""){//都为空不做处理
//						
//
//					}else{//有一为空
//						archivesInfoQueryCondition = new ArchivesInfoQueryCondition(dataItem);// 构造archivesInfoQueryCondition对象
//						if(values[0] == ""){
//							archivesInfoQueryCondition.setValue(values[1]);
//						}else{
//							archivesInfoQueryCondition.setValue(values[0]);
//						}
//						archivesInfoQueryConditions.put(parameterName,archivesInfoQueryCondition);// 构造archivesInfoQueryCondition的集合
//					}
//					
//				}else if(dataItem != null && values[0] != ""){//只有一个值
//					archivesInfoQueryCondition = new ArchivesInfoQueryCondition(dataItem);// 构造archivesInfoQueryCondition对象
//					archivesInfoQueryCondition.setValue(values[0]);
//					archivesInfoQueryConditions.put(parameterName,archivesInfoQueryCondition);// 构造archivesInfoQueryCondition的集合
//				}
//			}
//		}

		
		

		
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.ArchivesInfoQueryServiceImpl#queryCrossClassified(java.util.List, java.util.List, java.util.List, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testQueryCrossClassified() {
		fail("Not yet implemented");
	}

}
