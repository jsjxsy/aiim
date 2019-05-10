package com.orifound.aiim.dal.dao.sqlserver.impl.test;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.orifound.aiim.dal.dao.sqlserver.impl.TaskInfoDaoImpl;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.TaskInfo;

/**
 * 任务信息DAO测试类
 * @author tyb
 *
 */
public class TaskInfoDaoImplTest {
	
	//DAO实现类
	private static TaskInfoDaoImpl DaoImpl = new TaskInfoDaoImpl();
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
	public void testFindWithPage() {
//		任务发布人fromUserName、接收人receiveName、发布日期开始beginTime、结束endTime
		Map<String, String> params = new LinkedHashMap<String, String>();
		params.put("fromUserName", "无敌");
		params.put("receiveName", "无敌");
		params.put("beginTime", "2010-01-01");
		params.put("endTime", "2010-12-12");
		
		
		DataPageInfo dataPageInfo = new DataPageInfo(10, 1);
//		DataPageInfo dataPageInfo = null;
		List<TaskInfo> taskInfos = new ArrayList<TaskInfo>();
		//DoSomething
		if (DaoImpl.findWithPage(params, dataPageInfo, taskInfos, pErrInfo) == false) {
			fail(pErrInfo.toString());
		}
		
		System.out.println(taskInfos.size());
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
		TaskInfo taskInfo = new TaskInfo();
		//DoSomething
		if (DaoImpl.findByID(8, taskInfo , pErrInfo) == false) {
			fail(pErrInfo.toString());
		}
		System.out.println(taskInfo.getReceiveIds());
		System.out.println(taskInfo.getReceiveNames());
	}

	/**
	 * 保存任务信息测试
	 */
	@Test
	public void testSave() {
		TaskInfo taskInfo = new TaskInfo("1212", "1212", 1, 1, true, new Date(), new Date());
		List<Integer> taskPersonIds = new ArrayList<Integer>();
		taskPersonIds.add(2);
		taskInfo.setTaskPersonIds(taskPersonIds);
		
		if (DaoImpl.save(taskInfo , pErrInfo) == false) {
			fail(pErrInfo.toString());
		}
		System.out.println("插入任务信息成功！");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}
	
	/**
	 * 
	 */
	@Test
	public void findDetailByIDTest() {
		TaskInfo taskInfo = new TaskInfo();
		if (DaoImpl.findDetailByID(10, taskInfo, pErrInfo) == false) {
			fail(pErrInfo.toString());
		}
		System.out.println("getTaskPersons="+taskInfo.getTaskPersons().size());
	}

}
