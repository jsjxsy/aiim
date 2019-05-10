package com.orifound.aiim.bll.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.ITeacherFileManagService;
import com.orifound.aiim.dal.dao.IImportPersonnelArchivesDataitemsMappingDao;
import com.orifound.aiim.dal.dao.ITeacherDocsInfoDao;
import com.orifound.aiim.dal.dao.ITeacherDocsTypeDao;
import com.orifound.aiim.dal.dao.ITeacherInfoDao;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EnumWorkFlowStatus;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.Excel;
import com.orifound.aiim.entity.ImportDataitemsMapping;
import com.orifound.aiim.entity.IntegerEx;
import com.orifound.aiim.entity.TeacherDocsInfo;
import com.orifound.aiim.entity.TeacherDocsType;
import com.orifound.aiim.entity.TeacherInfo;

/**
 * 教职工档案管理服务类
 * @author Administrator
 *
 */
public class TeacherFileManagServiceImpl implements ITeacherFileManagService {

	@Autowired
	private IImportPersonnelArchivesDataitemsMappingDao importPersonnelArchivesDataitemsMappingDao;
	
	@Autowired
	private ITeacherInfoDao teacherInfoDao;
	
	@Autowired
	private ITeacherDocsTypeDao teacherDocsTypeDao;
	
	@Autowired
	private ITeacherDocsInfoDao teacherDocsInfoDao;
	/**
	 * 检查业务服务类和数据访问类是否注入成功（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkInjection(ErrInfo pErrInfo) {

		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (teacherInfoDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("教职工信息表的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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
	
	/**
	 * 返回映射对象
	 * @param excel
	 * @return
	 * @throws Exception
	 */
    private  List<TeacherInfo> getTeacherInfosFromExcel(Excel excel, int importType,ArchivesType archivesType,ErrInfo pErrInfo){
    	boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		List<TeacherInfo> teacherInfos = null;
		
		Map<String,ImportDataitemsMapping> importDataitemsMappings = new HashMap<String,ImportDataitemsMapping>();
		try {
			pErrPos = 1;
			//调用DAO查找对应的映射关系
			if (importPersonnelArchivesDataitemsMappingDao.findByImportType(importDataitemsMappings,importType,pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "查找字段映射关系失败: ");
			}

			if (pFlag) {
				if (importDataitemsMappings.size() ==0) {
					pFlag = false;
					pErrInfo.getContent().append("没有定义映射关系！");
				}
			}
			
			//读取excel
			if (pFlag) {
				pErrPos = 2;
				teacherInfos = new ArrayList<TeacherInfo>();
				TeacherInfo teacherInfo = null;
				Map<String,ImportDataitemsMapping> importDataitemsMappingsWithValue = null;
				ImportDataitemsMapping importDataitemsMappingWithValue = null;
				
		    	List<String> columnNames = excel.getColumnNames();
		    	for (int j = 0; j < excel.getRowCount(); j++) {
		    		
		    		importDataitemsMappingsWithValue = new HashMap<String,ImportDataitemsMapping>(); //有值的集合
		    		teacherInfo = new TeacherInfo();
		    		if(excel.getValue( j+1, 0) != ""){
		    			
		    			for (ImportDataitemsMapping importDataitemsMapping : importDataitemsMappings.values()) {
		    				//取得Excel中的数据
		    				if(columnNames.indexOf(importDataitemsMapping.getSrcExcelFieldName()) >= 0){
		    					importDataitemsMapping.setValue(excel.getValue(j+1, columnNames.indexOf(importDataitemsMapping.getSrcExcelFieldName())));
		    				}else{
		    					importDataitemsMapping.setValue("");
		    				}		
		    				
		    				//克隆对象加入到集合中
		    				importDataitemsMappingWithValue = new ImportDataitemsMapping();
		    				importDataitemsMappingWithValue.cloneFrom(importDataitemsMapping);
		    				importDataitemsMappingsWithValue.put(importDataitemsMapping.getDesTableFiedName(),importDataitemsMappingWithValue);
						}	
		    		}
		    		teacherInfo.setImportDataitemsMappings(importDataitemsMappingsWithValue);
		    		teacherInfo.setArchivesTypeID(archivesType.getID());
		    		teacherInfos.add(teacherInfo);
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
		return teacherInfos;
	}
	
	@Override
	public boolean importTeacherInfo(Excel excel, int importType, ArchivesType archivesType, IntegerEx sum, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		List<TeacherInfo> teacherInfos = null;
		try {
			//获得带值的映射集合
			pErrPos = 1;
			teacherInfos = this.getTeacherInfosFromExcel(excel, importType,archivesType,pErrInfo);

			if (teacherInfos == null) {
				pFlag = false;
			}
			
			//调用DAO插入数据库
			if (pFlag) {
				pErrPos = 2;
				if (teacherInfoDao.add(teacherInfos,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"添加学生信息失败: ");
				}
				
				if (pFlag) {
					sum.setValue(teacherInfos.size());
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
	public boolean findTeacherInfos(TeacherInfo teacherInfo,ArchivesType archivesType, DataPageInfo dataPageInfo,List<TeacherInfo> teacherInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//验证参数
			pErrPos = 1;
		
			//调用DAO查询数据库
			if (pFlag) {
				pErrPos = 2;
				if (teacherInfoDao.findTeacherInfos(teacherInfo, archivesType, dataPageInfo, teacherInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"查询档案信息失败: ");
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
	public boolean findTeacherInfosForArchiving(ArchivesType archivesType, DataPageInfo dataPageInfo, List<TeacherInfo> teacherInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkInjection(pErrInfo) == false) {
				pFlag = false;
			}

			//调用DAO接口
			if (pFlag) {
				if (teacherInfoDao.findTeacherInfosForArchiving(archivesType, dataPageInfo, teacherInfos, pErrInfo)== false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询所有待归档的教职工档案信息失败: ");
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
	public boolean findTeacherInfoByNBXH(TeacherInfo teacherInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//验证参数
			pErrPos = 1;
		
			//调用DAO查询数据库
			if (pFlag) {
				pErrPos = 2;
				if (teacherInfoDao.findTeacherInfoByNBXH(teacherInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"查询档案信息失败: ");
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
	public boolean findTeacherDocsType(List<TeacherDocsType> teacherDocsTypes, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//验证参数 
			pErrPos = 1;
			
			//调用DAO
			if (pFlag) {
				pErrPos = 2;
				if (teacherDocsTypeDao.findAll(teacherDocsTypes, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询教职工档案材料分类失败 : ");
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
	public boolean addDoc(int nbxh, TeacherDocsInfo teacherDocsInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//验证参数 
			pErrPos = 1;
			
			//调用DAO
			if (pFlag) {
				pErrPos = 2;
				teacherDocsInfo.setNBXH(nbxh);
				if (teacherDocsInfoDao.add(teacherDocsInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "添加教职工档案卷内材料失败 : ");
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
	public boolean findTeacherDocsInfoByNBXH(int nbxh, List<TeacherDocsInfo> teacherDocsInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//验证参数 
			pErrPos = 1;
			
			//调用DAO
			if (pFlag) {
				pErrPos = 2;
				if (teacherDocsInfoDao.findByNBXH(nbxh, teacherDocsInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查找教职工档案卷内材料失败 : ");
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
	public boolean delDoc(int[] docIds, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//验证参数 
			pErrPos = 1;
			
			//调用DAO
			if (pFlag) {
				pErrPos = 2;
				if (teacherDocsInfoDao.delDocs(docIds, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "删除教职工档案卷内材料失败 : ");
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
	public boolean addTeacherInfo(TeacherInfo teacherInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//
			pErrPos = 1;
			

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (teacherInfoDao.add(teacherInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "添加教职工档案卷内材料失败 : ");
					System.out.println(pErrInfo.toString());
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
	public boolean updateTeacherInfo(TeacherInfo teacherInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//
			pErrPos = 1;
			

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (teacherInfoDao.update(teacherInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "添加教职工档案卷内材料失败 : ");
					System.out.println(pErrInfo.toString());
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
	public boolean findTeacherDocById(TeacherDocsInfo teacherDocsInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			
			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (teacherDocsInfoDao.findByID(teacherDocsInfo.getID(), teacherDocsInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "添加教职工档案卷内材料失败 : ");
					System.out.println(pErrInfo.toString());
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
	public boolean updateTeacherDocInfo(TeacherDocsInfo teacherDocsInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			
			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (teacherDocsInfoDao.update(teacherDocsInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "更新教职工档案卷内材料失败 : ");
					System.out.println(pErrInfo.toString());
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
	public boolean setMoveOut(int nbxh, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			
			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (teacherInfoDao.updateTeacherInfoStatus(nbxh,EnumWorkFlowStatus.人事档案转出登记完成.getEnumValue(), pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "更新教职工档案转出状态失败 : ");
					System.out.println(pErrInfo.toString());
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
	public boolean addTeacherInfoToStoreroomArchivesInfo(int nbxh, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkInjection(pErrInfo) == false) {
				pFlag = false;
			}

			//调用DAO接口
			if (pFlag) {
				if (teacherInfoDao.addTeacherInfoToStoreroomArchivesInfo(nbxh, pErrInfo)== false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "cause失败: ");
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
	public boolean updateTeacherInfoStatus(int nbxh, int workFlowStatus, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkInjection(pErrInfo) == false) {
				pFlag = false;
			}

			//调用DAO接口
			if (pFlag) {
				if (teacherInfoDao.updateTeacherInfoStatus(nbxh, workFlowStatus, pErrInfo)== false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "cause失败: ");
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
	public boolean findTeacherDocsInfoByNBXHForPrint(int nbxh, List<TeacherDocsInfo> teacherDocsInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//验证参数 
			pErrPos = 1;
			
			//调用DAO
			if (pFlag) {
				pErrPos = 2;
				if (teacherDocsInfoDao.findByNBXHForPrint(nbxh, teacherDocsInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查找教职工档案卷内材料失败 : ");
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
	public boolean batAddTeacherDocsInfo(List<String> gzhList,TeacherDocsInfo teacherDocsInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查参数
			pErrPos = 1;

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if(teacherDocsInfoDao.add(gzhList,teacherDocsInfo,pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "添加卷内材料失败： ");
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


}
