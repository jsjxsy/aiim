package com.orifound.aiim.bll.service.impl;

import java.util.List;



import com.orifound.aiim.bll.service.IOfficialTemplateManageService;
import com.orifound.aiim.dal.dao.IOfficialTemplateDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.OfficialDocType;
import com.orifound.aiim.entity.OfficialTemplate;

public class OfficialTemplateManageServiceImpl implements IOfficialTemplateManageService {
	
	
	/**
	 * 公文模板表的数据访问对象
	 */
	private IOfficialTemplateDao officialTemplateDao = null;

	/**
	 * 获取属性值：公文模板表的数据访问对象
	 * @return 公文模板表的数据访问对象
	 */
	public IOfficialTemplateDao getOfficialTemplateDao() {
		return officialTemplateDao;
	}

	/**
	 * 设置属性值：公文模板表的数据访问对象
	 * @param officialTemplateDao 公文模板表的数据访问对象
	 */
	public void setOfficialTemplateDao(IOfficialTemplateDao officialTemplateDao) {
		this.officialTemplateDao = officialTemplateDao;
	}
	/**
	 * 构造函数
	 */
	public OfficialTemplateManageServiceImpl() {

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public OfficialTemplateManageServiceImpl(IOfficialTemplateDao officialTemplateDao) {
		this.officialTemplateDao = officialTemplateDao;
	}
	/**
	 * 检查OfficialTemplate的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForOfficialTemplate(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (officialTemplateDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("OfficialTemplate的DAO非法为空，请检查是否有进行依赖注入或赋值。");
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
		}

		return pFlag;
	}

	@Override
	public boolean deleteOfficialTemplate(OfficialTemplate officialTemplate, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		

		try {
			//开始处理 1...
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForOfficialTemplate(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0,"OfficialTemplateDAO非法为空。");
			}
			
			if (pFlag) 
			{
				if (officialTemplate.getID() == 0)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"模板号非法为空。");
				}
			
			}
			
			if (pFlag) {
				
				if(officialTemplateDao.delete(officialTemplate, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().append("模板删除失败");
				}
		
			}

		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}

	@Override
	public boolean findOfficialTemplateByID(int pID, OfficialTemplate officialTemplate, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForOfficialTemplate(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0,"OfficialTemplateDAO非法为空:");
			}

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (pID == 0) {
					pFlag = false;
					pErrInfo.getContent().append("公文模板编号非法为空!");
				}
			}
			
			if (pFlag) {
				if (officialTemplateDao.findByID(pID, officialTemplate, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"BLL层查询所有公文模板非法为空!");
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}

	@Override
	public boolean findOfficialTemplates(OfficialDocType officialDocType,List<OfficialTemplate> officialTemplates, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			if (officialTemplateDao.findAll(officialDocType,officialTemplates, pErrInfo)==false) {
				pFlag=false;
				pErrInfo.getContent().insert(0,"查找模板失败!");
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}

	@Override
	public boolean saveOfficialTemplate(OfficialTemplate officialTemplate, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		try {
			//开始处理 1...
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForOfficialTemplate(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0,"OfficialTemplateDAO非法为空。");
			}
			
			if (officialTemplate.getTitle()== null) {
				pErrInfo.getContent().insert(0,"模板题名非法为空");
			}else{
				if (officialTemplate.getTitle().trim().length() == 0) {
					pErrInfo.getContent().insert(0,"模板题名非法为空");
				}
			}

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (officialTemplate.getDocType()== 0) {
					pErrInfo.getContent().insert(0,"模板题名非法为空");
				}
			}
			
			//开始处理2...
			if (pFlag) {
				pErrPos = 3;
				if (officialTemplate.getProvider()== null) {
					pErrInfo.getContent().insert(0,"模板题名非法为空");
				}else{
					if(officialTemplate.getProvider().trim().length()==0){
						pErrInfo.getContent().insert(0,"模板题名非法为空");
					}
				}
			}
			//开始处理2...
			if (pFlag) {
				pErrPos = 4;
				if (officialTemplate.getFileName()== null) {
					pErrInfo.getContent().insert(0,"模板文件名非法为空");
				}else{
					if (officialTemplate.getFileName().trim().length()== 0) {
						pErrInfo.getContent().insert(0,"模板文件名非法为空");
				}
			}
			//开始处理2...
			if (pFlag) {
				pErrPos = 5;
				if (officialTemplate.getCreateDate()== null) {
					pErrInfo.getContent().insert(0,"模板创建日期非法为空");
				}
			}
			if(pFlag){
			if(officialTemplateDao.save(officialTemplate, pErrInfo)==false){
				pErrInfo.getContent().insert(0,"模板保存失败");
				}
			}
			
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}

	@Override
	public boolean updateOfficialTemplate(OfficialTemplate officialTemplate, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForOfficialTemplate(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0,"OfficialTemplateDAO非法为空。");
			}

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (officialTemplate.getDocType()== 0) {
					pErrInfo.getContent().insert(0,"模板题名非法为空");
				}
			}
			
			//开始处理2...
			if (pFlag) {
				pErrPos = 3;
				if (officialTemplate.getProvider()== null) {
					pErrInfo.getContent().insert(0,"模板题名非法为空");
				}else{
					if(officialTemplate.getProvider().trim().length()==0){
						pErrInfo.getContent().insert(0,"模板题名非法为空");
					}
				}
			}
			//开始处理2...
			if (pFlag) {
				pErrPos = 4;
				if (officialTemplate.getFileName()== null) {
					pErrInfo.getContent().insert(0,"模板文件名非法为空");
				}else{
					if (officialTemplate.getFileName().trim().length()== 0) {
						pErrInfo.getContent().insert(0,"模板文件名非法为空");
				}
			}
			//开始处理2...
			if (pFlag) {
				pErrPos = 5;
				if (officialTemplate.getCreateDate()== null) {
					pErrInfo.getContent().insert(0,"模板创建日期非法为空");
				}
			}
			
			if(pFlag){
			if(officialTemplateDao.update(officialTemplate, pErrInfo)==false){
				pErrInfo.getContent().insert(0,"模板更新法为空");
				}
			}
		
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}

	@Override
	public boolean findOfficialTemplateByName(OfficialDocType officialDocType,String title, List<OfficialTemplate> officialTemplates, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub

		
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForOfficialTemplate(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0,"OfficialTemplateDAO非法为空。");
			}

				if(officialTemplateDao.findByName(officialDocType,title, officialTemplates, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "模板名称非法为空");
				}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return pFlag;
		
	}
	
	
}
