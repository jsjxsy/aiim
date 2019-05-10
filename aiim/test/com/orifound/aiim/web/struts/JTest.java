package com.orifound.aiim.web.struts;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.orifound.aiim.bll.service.ISystemInitializeService;
import com.orifound.aiim.bll.service.IUserInfoManageService;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.SystemInitializer;
import com.orifound.aiim.entity.UserInfo;

public class JTest {
	
	static IUserInfoManageService userInfoManageService = null;
	static ISystemInitializeService systemInitializeService = null;
	static ApplicationContext ctx = null;
	static String [] XMLFilePath = {"/WebRoot/WEB-INF/SpringBeans-*.xml","/WebRoot/WEB-INF/applicationContext.xml"};
	
	@BeforeClass  
    public static void setUpBeforeClass() throws Exception {   
        try {   
            ctx = new FileSystemXmlApplicationContext(XMLFilePath);
            systemInitializeService = (ISystemInitializeService)ctx.getBean("systemInitializeService");
            userInfoManageService = (IUserInfoManageService) ctx.getBean("userInfoManageService");
            
            ErrInfo pErrInfo = new ErrInfo();
            SystemInitializer systemInitializer = SystemInitializer.getInstance();
            systemInitializeService.initialize(systemInitializer, pErrInfo);
            
        } catch (RuntimeException e) {    
            e.printStackTrace();   
        }   
    }
	
	@AfterClass
    public static void shutDownAfterClass() throws Exception {   
	    try {   
	        ctx = null;
	        userInfoManageService = null;
	    } catch (RuntimeException e) {    
	        e.printStackTrace();   
	    }   
	}
	
	@Test
	public void test(){
		try {
			ErrInfo pErrInfo = new ErrInfo();
			UserInfo userInfo = new UserInfo();
			userInfo.setUserName("aiim7");
			userInfo.setUserPWD("aiim7");
			if(userInfoManageService.login(userInfo, pErrInfo) == false){
				System.out.println(pErrInfo.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
