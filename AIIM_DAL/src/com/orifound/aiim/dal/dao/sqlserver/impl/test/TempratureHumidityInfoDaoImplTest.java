package com.orifound.aiim.dal.dao.sqlserver.impl.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import com.orifound.aiim.dal.dao.sqlserver.impl.TempratureHumidityInfoDaoImpl;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.TempratureHumidityInfo;

public class TempratureHumidityInfoDaoImplTest {
	
	private static TempratureHumidityInfoDaoImpl tempratureHumidityInfoDaoImpl = new TempratureHumidityInfoDaoImpl();
	ErrInfo pErrInfo = null;

	@Test
	public void testTempratureHumidityInfoDaoImpl() {
		
	}

	@Test
	public void testTempratureHumidityInfoDaoImplDataSource() {
		fail("Not yet implemented");
	}

	@Test
	public void testAdd() {
		pErrInfo = new ErrInfo();
		tempratureHumidityInfoDaoImpl.setDataSource(AIIMDataSourceDXT.getInstance().getDataSource());
		TempratureHumidityInfo tempratureHumidityInfo = new TempratureHumidityInfo();
		tempratureHumidityInfo.setHumidity(23.33);
		tempratureHumidityInfo.setMeasureDate(new Date());
		tempratureHumidityInfo.setMeasureTime("8:32");
		tempratureHumidityInfo.setStoreroomID("1");
		tempratureHumidityInfo.setTemperature(33.23);
		if(tempratureHumidityInfoDaoImpl.add(tempratureHumidityInfo, pErrInfo)==false){
			System.out.println(pErrInfo.toString());
		}else{
			System.out.println("success! add--");
		}
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindByID() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

}
