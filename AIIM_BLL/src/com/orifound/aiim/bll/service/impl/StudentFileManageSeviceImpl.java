package com.orifound.aiim.bll.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.IStudentFileManageService;
import com.orifound.aiim.dal.dao.IEMSTemplateDao;
import com.orifound.aiim.dal.dao.IImportPersonnelArchivesDataitemsMappingDao;
import com.orifound.aiim.dal.dao.IMoveOutRegisterDao;
import com.orifound.aiim.dal.dao.IStudentDocsInfoDao;
import com.orifound.aiim.dal.dao.IStudentInfoDao;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EMS;
import com.orifound.aiim.entity.EnumWorkFlowStatus;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.Excel;
import com.orifound.aiim.entity.ImportDataitemsMapping;
import com.orifound.aiim.entity.IntegerEx;
import com.orifound.aiim.entity.MoveOutInfo;
import com.orifound.aiim.entity.StudentInfo;

/**
 * 学生档案管理业务逻辑服务实现类
 * @author Administrator
 *
 */
public class StudentFileManageSeviceImpl implements IStudentFileManageService {

	@Autowired
	private IImportPersonnelArchivesDataitemsMappingDao importPersonnelArchivesDataitemsMappingDao;
	
	@Autowired
	private IStudentInfoDao studentInfoDao;
	
	@Autowired
	private IStudentDocsInfoDao studentDocsInfoDao;
	
	@Autowired
	private IMoveOutRegisterDao moveOutRegisterDao;
	
	@Autowired
	private IEMSTemplateDao EMSTemplateDao;
	/**
	 * 返回映射对象
	 * @param excel
	 * @return
	 * @throws Exception
	 */
    private  List<StudentInfo> getStudentInfosFromExcel(Excel excel, int importType,ArchivesType archivesType,ErrInfo pErrInfo){
    	boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		List<StudentInfo> studentInfos = null;
		
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
				studentInfos = new ArrayList<StudentInfo>();
				StudentInfo studentInfo = null;
				Map<String,ImportDataitemsMapping> importDataitemsMappingsWithValue = null;
				ImportDataitemsMapping importDataitemsMappingWithValue = null;
				
		    	List<String> columnNames = excel.getColumnNames();
		    	for (int j = 0; j < excel.getRowCount(); j++) {
		    		
		    		importDataitemsMappingsWithValue = new HashMap<String,ImportDataitemsMapping>(); //有值的集合
		    		studentInfo = new StudentInfo();
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
		    		studentInfo.setImportDataitemsMappings(importDataitemsMappingsWithValue);
		    		studentInfo.setArchivesTypeID(archivesType.getID());
		    		studentInfos.add(studentInfo);
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
		return studentInfos;
	}
    
	@Override
	public boolean importStudentInfo(Excel excel, int importType,ArchivesType archivesType,IntegerEx sum, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		List<StudentInfo> studentInfos = null;
		try {
			//获得带值的映射集合
			pErrPos = 1;
			studentInfos = this.getStudentInfosFromExcel(excel, importType,archivesType,pErrInfo);

			if (studentInfos == null) {
				pFlag = false;
			}
			
			//调用DAO插入数据库
			if (pFlag) {
				pErrPos = 2;
				if (studentInfoDao.add(studentInfos,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"添加学生信息失败: ");
				}
				
				if (pFlag) {
					sum.setValue(studentInfos.size());
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
	public boolean find(String xyName, EnumWorkFlowStatus enumWorkFlowStatus, DataPageInfo dataPageInfo, List<StudentInfo> studentInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查参数
			pErrPos = 1;
			

			//调用dao
			if (pFlag) {
				pErrPos = 2;
				if (studentInfoDao.find( xyName, enumWorkFlowStatus, dataPageInfo, studentInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"查询学生档案信息失败: ");
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
	public boolean findDocByNBXH(int nbxh, StudentInfo studentInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//验证参数
			pErrPos = 1;
			
			//调用DAO获取值
			if (pFlag) {
				pErrPos = 2;
				if (studentInfoDao.findByNBXH(nbxh,studentInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"查询学生档案信息失败: ");
				}
			}
			
			//调用DAO获取值
			if (pFlag) {
				pErrPos = 3;
				if (studentDocsInfoDao.findDocByNBXH(nbxh, studentInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"查询学生档案卷内文件失败: ");
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
	public boolean addDocs(int nbxh, List<Integer> ids, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//验证参数
			pErrPos = 1;

			//调用dao
			if (pFlag) {
				pErrPos = 2;
				if (studentDocsInfoDao.updateExistsFlag(ids, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"更新失败: ");
				}
			}
			
			//更新档案著录完成状态
			if (pFlag) {
				pErrPos = 2;
				if (studentInfoDao.updateWorkFlowStatus(nbxh, EnumWorkFlowStatus.人事档案卷内文件著录完成 ,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"更新档案工作流状态失败: ");
				}
			}
			
			//将归档后的档案记录插入到库房档案信息表中
			if (pFlag) {
				pErrPos = 3;
				if (studentInfoDao.addStudentInfoToStoreroomArchivesInfo(nbxh, pErrInfo)==false) {
					pFlag  = false;
					pErrInfo.getContent().insert(0,"将学生信息添加到库房档案信息表中失败：");
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
	public boolean moveOutRegister(int userId,Excel excel, int importType,ArchivesType archivesType, IntegerEx sum, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		List<StudentInfo> studentInfos = null;
		try {
			//获得带值的映射集合
			pErrPos = 1;
			studentInfos = this.getStudentInfosFromExcel(excel, importType,archivesType,pErrInfo);

			if (studentInfos == null) {
				pFlag = false;
			}
			
			//调用DAO插入数据库
			if (pFlag) {
				pErrPos = 2;
				for (StudentInfo studentInfo : studentInfos) {
					//查找学生转出地址信息
					MoveOutInfo moveOutInfo = new MoveOutInfo();
					if (moveOutRegisterDao.findMoveOutInfoByCompanyName(studentInfo.getCompanyName(),archivesType,moveOutInfo,pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0,"查找转出地址信息失败: ");
					}
					
					if (pFlag) {
						//添加转出信息
						if (moveOutInfo.getId() == 0) {
							//构造转出信息对象
							moveOutInfo.setTotalArchives(1);
							moveOutInfo.setCompanyAddr(studentInfo.getCompanyAddr());
							moveOutInfo.setCompanyName(studentInfo.getCompanyName());
							moveOutInfo.setMailingAddr(studentInfo.getMailingAddr());
							moveOutInfo.setMailingCompany(studentInfo.getMailingCompany());
							moveOutInfo.setReceivePostcode(studentInfo.getReceivePostcode());
							moveOutInfo.setPhone(studentInfo.getPhone());
							moveOutInfo.setRegisterDate(new Date());
							moveOutInfo.setManagerUserID(userId);
							moveOutInfo.setArchiveTypeId(archivesType.getID());
							
							if (moveOutRegisterDao.addMoveOutInfo(moveOutInfo,pErrInfo) == false) {
								pFlag = false;
								pErrInfo.getContent().insert(0,"查找转出地址信息失败: ");
							}
						}
					}
					
					if (pFlag) {
						//更新学生转出地址信息
						studentInfo.setMoveOutRegID(moveOutInfo.getId());//设置转出编号值
						if (studentInfoDao.updateMoveOutInfo(studentInfo,pErrInfo) == false) {
							pFlag = false;
							pErrInfo.getContent().insert(0,"更新转出信息失败: ");
						}
					}
					
					//更新转出单内档案总数量
					if (pFlag) {
						if (moveOutRegisterDao.updateTotalArchives(moveOutInfo.getId(),pErrInfo) == false) {
							pFlag = false;
							pErrInfo.getContent().insert(0,"更新转出信息失败: ");
						}
					}
					
//					//更新学生信息表中的所属转出单编号
//					if (pFlag) {
//						if (studentInfoDao.updateMoveOutRegID(moveOutInfo.getId(),pErrInfo) == false) {
//							pFlag = false;
//							pErrInfo.getContent().insert(0,"更新转出信息失败: ");
//						}
//					}
				}
					
				if (pFlag) {
					sum.setValue(studentInfos.size());
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
	public boolean findCollege(List<String> collegeNames, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (studentInfoDao.findCollege(collegeNames, pErrInfo) == false) {
					
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
	public boolean findMoveOutInfo(int moveOutWay, boolean moveOutFlag,ArchivesType archivesType,DataPageInfo dataPageInfo,int minNum ,int maxNum, List<MoveOutInfo> moveOutInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查参数
			pErrPos = 1;

			//调用DAO
			if (pFlag) {
				pErrPos = 2;
				if (moveOutRegisterDao.findMoveOutInfoByMoveOutWay(moveOutWay, moveOutFlag,archivesType,dataPageInfo, minNum, maxNum,moveOutInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"查找转出单信息失败: ");
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
	public boolean findMoveOutInfoById(int id, MoveOutInfo moveOutInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		List<StudentInfo> studentInfos = null;
		try {
			//检查参数
			pErrPos = 1;

			//调用DAO
			if (pFlag) {
				pErrPos = 2;
				if (moveOutRegisterDao.findMoveOutInfoById(id, moveOutInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"查找转出单信息失败: ");
				}
			}
			
			//查询该转出单的所有的人员信息
			if (pFlag) {
				pErrPos = 3;
				studentInfos = new ArrayList<StudentInfo>();
				if (studentInfoDao.findStudentInfoByMoveOutId(id, studentInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"查找转出单信息失败: ");
				}
			}
			
			if (pFlag) {
				moveOutInfo.setStudentInfos(studentInfos);
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
	public boolean updateMoveOutWay(int id, int moveOutWay, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查参数
			pErrPos = 1;
			
			//调用DAO更新转出状态
			if (pFlag) {
				pErrPos = 2;
				if (moveOutRegisterDao.updateMoveOutWay(id,moveOutWay,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"更新转出单转出方式失败: ");
				}
			}
			
			//更新档案的工作流状态
			if (pFlag) {
				pErrPos = 3;
				if (studentInfoDao.updateWorkFlowStatusByMoveOutRegID(id,EnumWorkFlowStatus.人事档案转出登记完成,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"根据转出单更新档案工作流状态失败: ");
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
	public boolean updateSN(int id, String sN, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查参数
			pErrPos = 1;

			//调用DAO关联序列号
			if (pFlag) {
				pErrPos = 2;
				if (moveOutRegisterDao.updateSN(id, sN, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"更新转出单序列号失败: ");
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
	public boolean findStudentInfos(StudentInfo studentInfo,List<EnumWorkFlowStatus> enumWorkFlowStatus,List<StudentInfo> studentInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查参数
			pErrPos = 1;

			//查询学生档案信息
			if (pFlag) {
				pErrPos = 2;
				if (studentInfoDao.findStudentInfos(studentInfo, enumWorkFlowStatus, studentInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"查询学生信息失败: ");
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
	public boolean findStudentInfos(int nbxh, EnumWorkFlowStatus enumWorkFlowStatus, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查参数
			pErrPos = 1;
			
			//调用DAO设置档案工作流状态
			if (pFlag) {
				pErrPos = 2;
				if (studentInfoDao.updateWorkFlowStatus(nbxh, enumWorkFlowStatus, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "设置档案工作流状态失败: ");
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
	public boolean getEMSinfos(int[] ids, List<EMS> emsInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		List<Integer> moveOutRegIDs = null;
		try {
			//验证参数
			pErrPos = 1;
			moveOutRegIDs = new ArrayList<Integer>();
			for (int i = 0; i < ids.length; i++) {
				moveOutRegIDs.add(ids[i]);
			}
			
			//调用DAO获得快递单信息
			if (pFlag) {
				pErrPos = 2;
				if (EMSTemplateDao.findEMSinfos(ids,emsInfos,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询快递单信息失败: ");
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
