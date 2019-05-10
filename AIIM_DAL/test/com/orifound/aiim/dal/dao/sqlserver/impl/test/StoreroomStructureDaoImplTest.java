/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.orifound.aiim.dal.dao.sqlserver.impl.StoreroomStructureDaoImpl;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.StoreroomStructure;

/**
 * @author Administrator
 *
 */
public class StoreroomStructureDaoImplTest {
	
	//DAO实现类
	private static StoreroomStructureDaoImpl	 DaoImpl = new StoreroomStructureDaoImpl();
	//错误信息对象
	private static ErrInfo pErrInfo = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//数据源注入
		DaoImpl.setDataSource(AIIMDataSourceDXT.getInstance().getDataSource());
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

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.StoreroomStructureDaoImpl#StoreroomStructureDaoImpl()}.
	 */
	@Test
	public void testStoreroomStructureDaoImpl() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.StoreroomStructureDaoImpl#StoreroomStructureDaoImpl(javax.sql.DataSource)}.
	 */
	@Test
	public void testStoreroomStructureDaoImplDataSource() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.StoreroomStructureDaoImpl#addStoreroomStructure(com.orifound.aiim.entity.StoreroomStructure, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testAddStoreroomStructure() {
		System.out.println("添加库房结构信息begin-->");
		StoreroomStructure storeroomStructure = new StoreroomStructure();
		storeroomStructure.setBarcode("");
		storeroomStructure.setEndFlag(false);
		storeroomStructure.setFullName("库房四	");
		storeroomStructure.setName("库房四");
		storeroomStructure.setParentID(0);
		storeroomStructure.setRemark("测试备注2");
		storeroomStructure.setRoomFlag(true);
		//storeroomStructure.setTotalSize(23);
		//storeroomStructure.setUsedSize(2);
		if(DaoImpl.addStoreroomStructure(storeroomStructure, pErrInfo)==false){
			System.out.println("添加库房结构信息失败。");
			System.out.println("错误信息："+pErrInfo.toString());
		}else{
			System.out.println("添加库房结构信息成功。");
		}
		fail("Not yet implemented");
	}

//	/**
//	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.StoreroomStructureDaoImpl#addStoreroomStructures(int, int, int, int, com.orifound.aiim.entity.ErrInfo)}.
//	 */
//	@Test
//	public void testAddStoreroomStructures() {
//		fail("Not yet implemented");
//	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.StoreroomStructureDaoImpl#deleteStoreroomStructure(com.orifound.aiim.entity.StoreroomStructure, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testDeleteStoreroomStructure() {
	
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.StoreroomStructureDaoImpl#findStoreroomStructureByID(int, com.orifound.aiim.entity.StoreroomStructure, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindStoreroomStructureByID() {
		StoreroomStructure storeroomStructure = new StoreroomStructure();	
		storeroomStructure.setID(3);
	
		
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.StoreroomStructureDaoImpl#findStoreroomStructureChildrenByID(int, java.util.List, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindStoreroomStructureChildrenByID() {
		List<StoreroomStructure> children = new ArrayList<StoreroomStructure>();
		
		fail("Not yet implemented");
	}

//	/**
//	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.StoreroomStructureDaoImpl#findStoreroomStructures(java.util.List, com.orifound.aiim.entity.ErrInfo)}.
//	 */
//	@Test
//	public void testFindStoreroomStructures() {
//		fail("Not yet implemented");
//	}

//	/**
//	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.StoreroomStructureDaoImpl#findStorerooms(java.util.List, com.orifound.aiim.entity.ErrInfo)}.
//	 */
//	@Test
//	public void testFindStorerooms() {
//		fail("Not yet implemented");
//	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.StoreroomStructureDaoImpl#updateStoreroomStructure(com.orifound.aiim.entity.StoreroomStructure, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testUpdateStoreroomStructure() {
		StoreroomStructure storeroomStructure = new StoreroomStructure();
		storeroomStructure.setFullName("第三柜-第五层");
		storeroomStructure.setID(3);
		storeroomStructure.setName("第五层");
		storeroomStructure.setRemark("--------");
		storeroomStructure.setTotalSize(233);
		storeroomStructure.setRoomFlag(false);
		storeroomStructure.setEndFlag(true);
		if(DaoImpl.updateStoreroomStructure(storeroomStructure, pErrInfo)==false){
			System.out.println("失败");
			System.out.println(storeroomStructure.toString());
		}else{
			System.out.print( "成功：");
			System.out.println("fullName:"+storeroomStructure.getFullName());
		}
		fail("Not yet implemented");
	}

}
