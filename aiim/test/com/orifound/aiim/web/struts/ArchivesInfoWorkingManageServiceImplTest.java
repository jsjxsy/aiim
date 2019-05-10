package com.orifound.aiim.web.struts;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService;
import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.SystemInitializer;

public class ArchivesInfoWorkingManageServiceImplTest {
	
	static IArchivesInfoWorkingManageService archivesInfoWorkingManageService = null;
	static ApplicationContext ctx = null;
	static String [] XMLFilePath = {"/WebRoot/WEB-INF/SpringBeans-*.xml","/WebRoot/WEB-INF/applicationContext.xml"};
	static ArchivesType archivesType = null;
	static DataPageInfo dataPageInfo = null;
	static ErrInfo pErrInfo = null;
	
	@BeforeClass  
    public static void setUpBeforeClass() throws Exception {   
        try {   
        	
            ctx = new FileSystemXmlApplicationContext(XMLFilePath);
            archivesInfoWorkingManageService = (IArchivesInfoWorkingManageService) ctx.getBean("archivesInfoWorkingManageService");
            archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(2);
            dataPageInfo = new DataPageInfo();
            pErrInfo = new ErrInfo();
            
        } catch (RuntimeException e) {    
            e.printStackTrace();   
        }   
    }
	
	@AfterClass
    public static void shutDownAfterClass() throws Exception {   
	    try {   
	    	
	        ctx = null;
	        archivesInfoWorkingManageService = null;
	        archivesType = null;
	        dataPageInfo = null;
	        pErrInfo = null;
	        
	    } catch (RuntimeException e) {    
	        e.printStackTrace();   
	    }   
	}
	
	@Test
	public void test(){ 
		List<ArchivesInfo> archivesInfos = new ArrayList<ArchivesInfo>();
		dataPageInfo.setPageSize(15);
		if(archivesInfoWorkingManageService.findInputCheckNeedArchivesInfos(new int [] {1}, archivesType, dataPageInfo, archivesInfos, pErrInfo) == false){
			System.out.println(pErrInfo.toString());
		}else{
			System.out.println(archivesInfos.size());
		}
	}
}
