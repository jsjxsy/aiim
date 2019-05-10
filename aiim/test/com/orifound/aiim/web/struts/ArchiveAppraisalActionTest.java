package com.orifound.aiim.web.struts;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.orifound.aiim.entity.ErrInfo;

/**
 * 鉴定管理Action测试类
 * @author tyb
 *
 */
public class ArchiveAppraisalActionTest {
	static ApplicationContext ctx = null;
	static String [] XMLFilePath = {"/WebRoot/WEB-INF/SpringBeans-*.xml","/WebRoot/WEB-INF/applicationContext.xml"};
	static ErrInfo pErrInfo = null;
	
	private static ArchiveAppraisalAction archiveAppraisalAction;
	
	@BeforeClass  
    public static void setUpBeforeClass() throws Exception {   
        try {   
            ctx = new FileSystemXmlApplicationContext(XMLFilePath);
            archiveAppraisalAction  = (ArchiveAppraisalAction) ctx.getBean("archiveAppraisalAction");
            pErrInfo = new ErrInfo();
            
        } catch (RuntimeException e) {    
            e.printStackTrace();   
        }   
    }
	
	@AfterClass
    public static void shutDownAfterClass() throws Exception {   
	    try {   
	    	archiveAppraisalAction = null;
	        ctx = null;
	        pErrInfo = null;
	        
	    } catch (RuntimeException e) {    
	        e.printStackTrace();   
	    }   
	}
	@Test
	public void testGetArchivesTypeTree() {
		try {
			System.out.println(archiveAppraisalAction);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
