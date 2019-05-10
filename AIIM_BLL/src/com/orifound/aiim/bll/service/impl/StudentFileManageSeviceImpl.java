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
 * ѧ����������ҵ���߼�����ʵ����
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
	 * ����ӳ�����
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
			//����DAO���Ҷ�Ӧ��ӳ���ϵ
			if (importPersonnelArchivesDataitemsMappingDao.findByImportType(importDataitemsMappings,importType,pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "�����ֶ�ӳ���ϵʧ��: ");
			}

			if (pFlag) {
				if (importDataitemsMappings.size() ==0) {
					pFlag = false;
					pErrInfo.getContent().append("û�ж���ӳ���ϵ��");
				}
			}
			
			//��ȡexcel
			if (pFlag) {
				pErrPos = 2;
				studentInfos = new ArrayList<StudentInfo>();
				StudentInfo studentInfo = null;
				Map<String,ImportDataitemsMapping> importDataitemsMappingsWithValue = null;
				ImportDataitemsMapping importDataitemsMappingWithValue = null;
				
		    	List<String> columnNames = excel.getColumnNames();
		    	for (int j = 0; j < excel.getRowCount(); j++) {
		    		
		    		importDataitemsMappingsWithValue = new HashMap<String,ImportDataitemsMapping>(); //��ֵ�ļ���
		    		studentInfo = new StudentInfo();
		    		if(excel.getValue( j+1, 0) != ""){
		    			
		    			for (ImportDataitemsMapping importDataitemsMapping : importDataitemsMappings.values()) {
		    				//ȡ��Excel�е�����
		    				if(columnNames.indexOf(importDataitemsMapping.getSrcExcelFieldName()) >= 0){
		    					importDataitemsMapping.setValue(excel.getValue(j+1, columnNames.indexOf(importDataitemsMapping.getSrcExcelFieldName())));
		    				}else{
		    					importDataitemsMapping.setValue("");
		    				}		
		    				
		    				//��¡������뵽������
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
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
			//���پֲ�����
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
			//��ô�ֵ��ӳ�伯��
			pErrPos = 1;
			studentInfos = this.getStudentInfosFromExcel(excel, importType,archivesType,pErrInfo);

			if (studentInfos == null) {
				pFlag = false;
			}
			
			//����DAO�������ݿ�
			if (pFlag) {
				pErrPos = 2;
				if (studentInfoDao.add(studentInfos,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"���ѧ����Ϣʧ��: ");
				}
				
				if (pFlag) {
					sum.setValue(studentInfos.size());
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
			//���پֲ�����
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
			//������
			pErrPos = 1;
			

			//����dao
			if (pFlag) {
				pErrPos = 2;
				if (studentInfoDao.find( xyName, enumWorkFlowStatus, dataPageInfo, studentInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"��ѯѧ��������Ϣʧ��: ");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
			//���پֲ�����
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
			//��֤����
			pErrPos = 1;
			
			//����DAO��ȡֵ
			if (pFlag) {
				pErrPos = 2;
				if (studentInfoDao.findByNBXH(nbxh,studentInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"��ѯѧ��������Ϣʧ��: ");
				}
			}
			
			//����DAO��ȡֵ
			if (pFlag) {
				pErrPos = 3;
				if (studentDocsInfoDao.findDocByNBXH(nbxh, studentInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"��ѯѧ�����������ļ�ʧ��: ");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
			//���پֲ�����
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
			//��֤����
			pErrPos = 1;

			//����dao
			if (pFlag) {
				pErrPos = 2;
				if (studentDocsInfoDao.updateExistsFlag(ids, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"����ʧ��: ");
				}
			}
			
			//���µ�����¼���״̬
			if (pFlag) {
				pErrPos = 2;
				if (studentInfoDao.updateWorkFlowStatus(nbxh, EnumWorkFlowStatus.���µ��������ļ���¼��� ,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"���µ���������״̬ʧ��: ");
				}
			}
			
			//���鵵��ĵ�����¼���뵽�ⷿ������Ϣ����
			if (pFlag) {
				pErrPos = 3;
				if (studentInfoDao.addStudentInfoToStoreroomArchivesInfo(nbxh, pErrInfo)==false) {
					pFlag  = false;
					pErrInfo.getContent().insert(0,"��ѧ����Ϣ��ӵ��ⷿ������Ϣ����ʧ�ܣ�");
				}
			}
			
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}
     			pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
			//���پֲ�����
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
			//��ô�ֵ��ӳ�伯��
			pErrPos = 1;
			studentInfos = this.getStudentInfosFromExcel(excel, importType,archivesType,pErrInfo);

			if (studentInfos == null) {
				pFlag = false;
			}
			
			//����DAO�������ݿ�
			if (pFlag) {
				pErrPos = 2;
				for (StudentInfo studentInfo : studentInfos) {
					//����ѧ��ת����ַ��Ϣ
					MoveOutInfo moveOutInfo = new MoveOutInfo();
					if (moveOutRegisterDao.findMoveOutInfoByCompanyName(studentInfo.getCompanyName(),archivesType,moveOutInfo,pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0,"����ת����ַ��Ϣʧ��: ");
					}
					
					if (pFlag) {
						//���ת����Ϣ
						if (moveOutInfo.getId() == 0) {
							//����ת����Ϣ����
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
								pErrInfo.getContent().insert(0,"����ת����ַ��Ϣʧ��: ");
							}
						}
					}
					
					if (pFlag) {
						//����ѧ��ת����ַ��Ϣ
						studentInfo.setMoveOutRegID(moveOutInfo.getId());//����ת�����ֵ
						if (studentInfoDao.updateMoveOutInfo(studentInfo,pErrInfo) == false) {
							pFlag = false;
							pErrInfo.getContent().insert(0,"����ת����Ϣʧ��: ");
						}
					}
					
					//����ת�����ڵ���������
					if (pFlag) {
						if (moveOutRegisterDao.updateTotalArchives(moveOutInfo.getId(),pErrInfo) == false) {
							pFlag = false;
							pErrInfo.getContent().insert(0,"����ת����Ϣʧ��: ");
						}
					}
					
//					//����ѧ����Ϣ���е�����ת�������
//					if (pFlag) {
//						if (studentInfoDao.updateMoveOutRegID(moveOutInfo.getId(),pErrInfo) == false) {
//							pFlag = false;
//							pErrInfo.getContent().insert(0,"����ת����Ϣʧ��: ");
//						}
//					}
				}
					
				if (pFlag) {
					sum.setValue(studentInfos.size());
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
			//���پֲ�����
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
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (studentInfoDao.findCollege(collegeNames, pErrInfo) == false) {
					
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
		//���پֲ�����
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
			//������
			pErrPos = 1;

			//����DAO
			if (pFlag) {
				pErrPos = 2;
				if (moveOutRegisterDao.findMoveOutInfoByMoveOutWay(moveOutWay, moveOutFlag,archivesType,dataPageInfo, minNum, maxNum,moveOutInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"����ת������Ϣʧ��: ");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
			//���پֲ�����
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
			//������
			pErrPos = 1;

			//����DAO
			if (pFlag) {
				pErrPos = 2;
				if (moveOutRegisterDao.findMoveOutInfoById(id, moveOutInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"����ת������Ϣʧ��: ");
				}
			}
			
			//��ѯ��ת���������е���Ա��Ϣ
			if (pFlag) {
				pErrPos = 3;
				studentInfos = new ArrayList<StudentInfo>();
				if (studentInfoDao.findStudentInfoByMoveOutId(id, studentInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"����ת������Ϣʧ��: ");
				}
			}
			
			if (pFlag) {
				moveOutInfo.setStudentInfos(studentInfos);
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
			//���پֲ�����
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
			//������
			pErrPos = 1;
			
			//����DAO����ת��״̬
			if (pFlag) {
				pErrPos = 2;
				if (moveOutRegisterDao.updateMoveOutWay(id,moveOutWay,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"����ת����ת����ʽʧ��: ");
				}
			}
			
			//���µ����Ĺ�����״̬
			if (pFlag) {
				pErrPos = 3;
				if (studentInfoDao.updateWorkFlowStatusByMoveOutRegID(id,EnumWorkFlowStatus.���µ���ת���Ǽ����,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"����ת�������µ���������״̬ʧ��: ");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
			//���پֲ�����
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
			//������
			pErrPos = 1;

			//����DAO�������к�
			if (pFlag) {
				pErrPos = 2;
				if (moveOutRegisterDao.updateSN(id, sN, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"����ת�������к�ʧ��: ");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
			//���پֲ�����
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
			//������
			pErrPos = 1;

			//��ѯѧ��������Ϣ
			if (pFlag) {
				pErrPos = 2;
				if (studentInfoDao.findStudentInfos(studentInfo, enumWorkFlowStatus, studentInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"��ѯѧ����Ϣʧ��: ");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
			//���پֲ�����
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
			//������
			pErrPos = 1;
			
			//����DAO���õ���������״̬
			if (pFlag) {
				pErrPos = 2;
				if (studentInfoDao.updateWorkFlowStatus(nbxh, enumWorkFlowStatus, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���õ���������״̬ʧ��: ");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
			//���پֲ�����
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
			//��֤����
			pErrPos = 1;
			moveOutRegIDs = new ArrayList<Integer>();
			for (int i = 0; i < ids.length; i++) {
				moveOutRegIDs.add(ids[i]);
			}
			
			//����DAO��ÿ�ݵ���Ϣ
			if (pFlag) {
				pErrPos = 2;
				if (EMSTemplateDao.findEMSinfos(ids,emsInfos,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ��ݵ���Ϣʧ��: ");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
			//���پֲ�����
			throwable = null;
		}
		return pFlag;
	}	
}
