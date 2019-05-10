package com.orifound.aiim.dal.dao.sqlserver.impl.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.orifound.aiim.dal.dao.sqlserver.impl.CurrentBarcodeDaoImpl;
import com.orifound.aiim.entity.CurrentBarcode;
import com.orifound.aiim.entity.EnumBarcodeType;
import com.orifound.aiim.entity.ErrInfo;

public class CurrentBarcodeDaoImplTest {

	//DAO实现类
	private static CurrentBarcodeDaoImpl DaoImpl = new CurrentBarcodeDaoImpl();
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
	
	
	
	@Test
	public void testFindByBarcodeType() {
		ErrInfo pErrInfo = new ErrInfo();
		CurrentBarcode currentBarcode = new CurrentBarcode();
		currentBarcode.setBarcodeType(EnumBarcodeType.库房设备条形码);
		
		if(DaoImpl.findByBarcodeType(currentBarcode, pErrInfo)==false){
			System.out.println("error:  "+pErrInfo.toString());
		}else{
			if(currentBarcode.getCurrentBarcodeNo()==0){
				
			}
			System.out.println("查询成功");
		}
		System.out.println("type:"+currentBarcode.getBarcodeType()+"barCodeNO: "+currentBarcode.getCurrentBarcodeNo());
		//System.out.println("sdfsdfsdfsd");
		//fail("Not yet implemented");
	}

	
	@Test
	public void testUpdate() {
		ErrInfo pErrInfo = new ErrInfo();
		CurrentBarcode currentBarcode = new CurrentBarcode();
		currentBarcode.setBarcodeType(EnumBarcodeType.档案条形码);
		currentBarcode.setCurrentBarcodeNo(20);	

		if(DaoImpl.update(currentBarcode, pErrInfo)==false){
			System.out.println("error:  "+pErrInfo.toString());
		}else{
			System.out.println("更新成功！");
		}
		System.out.println("barCodeNO: "+currentBarcode.getCurrentBarcodeNo());
		//System.out.println("sdfsdfsdfsd");
		//fail("Not yet implemented");
	}
	
//	@Test
//	public void testSave() {
//		ErrInfo pErrInfo = new ErrInfo();
//		CurrentBarcode currentBarcode = new CurrentBarcode();
//		currentBarcode.setBarcodeType(EnumBarcodeType.库房设备条形码);
//		currentBarcode.setCurrentBarcodeNo(20);	
//		if(DaoImpl.save(currentBarcode, pErrInfo)==false){
//			System.out.println("error:  "+pErrInfo.toString());
//		}else{
//			System.out.println("保存成功！");
//		}
//		System.out.println("barCodeNO: "+currentBarcode.getCurrentBarcodeNo());
//		//System.out.println("sdfsdfsdfsd");
//		//fail("Not yet implemented");
//	}
}
