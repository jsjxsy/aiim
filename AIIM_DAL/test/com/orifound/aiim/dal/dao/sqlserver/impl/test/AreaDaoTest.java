package com.orifound.aiim.dal.dao.sqlserver.impl.test;

import java.util.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.orifound.aiim.dal.dao.sqlserver.impl.AreaDaoImpl;
import com.orifound.aiim.entity.Area;
import com.orifound.aiim.entity.ErrInfo;


public class AreaDaoTest {

	//DAO实现类
	private static AreaDaoImpl DaoImpl = new AreaDaoImpl();
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
	
//	@Test
//	public void testFindAll() {
//		List<Area> pAreas = new ArrayList<Area>();
// 		
//		if(DaoImpl.findAll(pAreas, pErrInfo)){
//			System.out.println("查询结果集数量为: "+pAreas.size());
//		}else{
//			System.out.println(pErrInfo.toString());
//		}
//	}
	@Test
	public void testFindAll() {
		List<Area> pAreas = new ArrayList<Area>();
 		
//		if(DaoImpl.findAll(pAreas, pErrInfo)){
//			System.out.println("查询结果集数量为: "+pAreas.size());
//		}else{
//			System.out.println(pErrInfo.toString());
//		}
	}
//	@Test
//	public void testFindById() {
//		Area pArea = new Area();
// 		
//		if(DaoImpl.findByID(1,pArea, pErrInfo)){
//			System.out.println("查询结果Area的名字为: "+pArea.getName());
//		}else{
//			System.out.println(pErrInfo.toString());
//		}
//	}
	@Test
	public void testFindById() {
		Area pArea = new Area();
 		
//		if(DaoImpl.findByID(1,pArea, pErrInfo)){
//			System.out.println("查询结果Area的名字为: "+pArea.getName());
//		}else{
//			System.out.println(pErrInfo.toString());
//		}
	}
}
