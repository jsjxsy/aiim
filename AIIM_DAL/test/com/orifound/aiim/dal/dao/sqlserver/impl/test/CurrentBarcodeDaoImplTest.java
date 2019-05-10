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

	//DAOʵ����
	private static CurrentBarcodeDaoImpl DaoImpl = new CurrentBarcodeDaoImpl();
	//������Ϣ����
	private static ErrInfo pErrInfo = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//����Դע��
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
		currentBarcode.setBarcodeType(EnumBarcodeType.�ⷿ�豸������);
		
		if(DaoImpl.findByBarcodeType(currentBarcode, pErrInfo)==false){
			System.out.println("error:  "+pErrInfo.toString());
		}else{
			if(currentBarcode.getCurrentBarcodeNo()==0){
				
			}
			System.out.println("��ѯ�ɹ�");
		}
		System.out.println("type:"+currentBarcode.getBarcodeType()+"barCodeNO: "+currentBarcode.getCurrentBarcodeNo());
		//System.out.println("sdfsdfsdfsd");
		//fail("Not yet implemented");
	}

	
	@Test
	public void testUpdate() {
		ErrInfo pErrInfo = new ErrInfo();
		CurrentBarcode currentBarcode = new CurrentBarcode();
		currentBarcode.setBarcodeType(EnumBarcodeType.����������);
		currentBarcode.setCurrentBarcodeNo(20);	

		if(DaoImpl.update(currentBarcode, pErrInfo)==false){
			System.out.println("error:  "+pErrInfo.toString());
		}else{
			System.out.println("���³ɹ���");
		}
		System.out.println("barCodeNO: "+currentBarcode.getCurrentBarcodeNo());
		//System.out.println("sdfsdfsdfsd");
		//fail("Not yet implemented");
	}
	
//	@Test
//	public void testSave() {
//		ErrInfo pErrInfo = new ErrInfo();
//		CurrentBarcode currentBarcode = new CurrentBarcode();
//		currentBarcode.setBarcodeType(EnumBarcodeType.�ⷿ�豸������);
//		currentBarcode.setCurrentBarcodeNo(20);	
//		if(DaoImpl.save(currentBarcode, pErrInfo)==false){
//			System.out.println("error:  "+pErrInfo.toString());
//		}else{
//			System.out.println("����ɹ���");
//		}
//		System.out.println("barCodeNO: "+currentBarcode.getCurrentBarcodeNo());
//		//System.out.println("sdfsdfsdfsd");
//		//fail("Not yet implemented");
//	}
}
