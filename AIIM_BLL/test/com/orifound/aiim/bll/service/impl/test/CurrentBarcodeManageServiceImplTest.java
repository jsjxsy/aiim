package com.orifound.aiim.bll.service.impl.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.orifound.aiim.bll.service.impl.CurrentBarcodeManageServiceImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.CurrentBarcodeDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.test.AIIMDataSource;
import com.orifound.aiim.dal.dao.sqlserver.impl.test.AIIMDataSourceDXT;
import com.orifound.aiim.entity.CurrentBarcode;
import com.orifound.aiim.entity.EnumBarcodeType;
import com.orifound.aiim.entity.ErrInfo;

public class CurrentBarcodeManageServiceImplTest {
	
	//ҵ���߼�ʵ����
	private static CurrentBarcodeManageServiceImpl ServiceImpl = new CurrentBarcodeManageServiceImpl();
	//������Ϣ����
	private static ErrInfo pErrInfo = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//DAOע��
		ServiceImpl.setCurrentBarcodeDao(new CurrentBarcodeDaoImpl(AIIMDataSourceDXT.getInstance().getDataSource()));
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

	@Test
	public void testGetCurrentBarcodeDao() {
	//	System.out.println("sdfsdfdsf");
		fail("Not yet implemented");
	}

	@Test
	public void testSetCurrentBarcodeDao() {
		fail("Not yet implemented");
	}

//	@Test
//	public void testFindCurrentBarcodeByBarcodeType() {
//		CurrentBarcode currentBarcode = new CurrentBarcode();
//		currentBarcode.setBarcodeType(EnumBarcodeType.������������);
//		if(ServiceImpl.findCurrentBarcodeByBarcodeType(currentBarcode, pErrInfo)==false){
//			System.out.println("findCurrentBarcodeByBarcodeTypeʧ�ܡ�");
//			System.out.println(pErrInfo.toString());
//		}else{
//			//System.out.println("type:"+ currentBarcode.getBarcodeType()+"NO:"+currentBarcode.getCurrentBarcodeNo());
//		}
//	
//	}

	@Test
	public void testSaveCurrentBarcode() {
		fail("Not yet implemented");
	}

//	@Test
//	public void testUpdateCurrentBarcode() {
//		CurrentBarcode currentBarcode = new CurrentBarcode();
//		currentBarcode.setBarcodeType(EnumBarcodeType.������������);
//		currentBarcode.setCurrentBarcodeNo(2);
//		if(ServiceImpl.updateCurrentBarcode(currentBarcode, pErrInfo)==false){
//			System.out.println(pErrInfo.toString());
//		}else{
//			System.out.println("type:"+currentBarcode.getBarcodeType()+"  NO:"+currentBarcode.getCurrentBarcodeNo());
//		}
//		fail("Not yet implemented");
//	}

	@Test
	public void testPrintBarcode() {
		CurrentBarcode currentBarcode = new CurrentBarcode();
		currentBarcode.setBarcodeType(EnumBarcodeType.������������);
		
		if(ServiceImpl.printBarcode(10, currentBarcode, pErrInfo)==false){
			System.out.println(pErrInfo.toString());
		}else{
			System.out.println("�����ӡ�ɹ���"+"type:"+currentBarcode.getBarcodeType()+"  NO:"+currentBarcode.getCurrentBarcodeNo());
			System.out.println((currentBarcode.getCurrentBarcodeNo()+1-10)+"--->"+currentBarcode.getCurrentBarcodeNo());
		}
		fail("Not yet implemented");
	}

}
