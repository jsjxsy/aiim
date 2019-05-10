package com.orifound.aiim.bll.service.impl;

import java.util.List;



import com.orifound.aiim.bll.service.IOfficialTemplateManageService;
import com.orifound.aiim.dal.dao.IOfficialTemplateDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.OfficialDocType;
import com.orifound.aiim.entity.OfficialTemplate;

public class OfficialTemplateManageServiceImpl implements IOfficialTemplateManageService {
	
	
	/**
	 * ����ģ�������ݷ��ʶ���
	 */
	private IOfficialTemplateDao officialTemplateDao = null;

	/**
	 * ��ȡ����ֵ������ģ�������ݷ��ʶ���
	 * @return ����ģ�������ݷ��ʶ���
	 */
	public IOfficialTemplateDao getOfficialTemplateDao() {
		return officialTemplateDao;
	}

	/**
	 * ��������ֵ������ģ�������ݷ��ʶ���
	 * @param officialTemplateDao ����ģ�������ݷ��ʶ���
	 */
	public void setOfficialTemplateDao(IOfficialTemplateDao officialTemplateDao) {
		this.officialTemplateDao = officialTemplateDao;
	}
	/**
	 * ���캯��
	 */
	public OfficialTemplateManageServiceImpl() {

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public OfficialTemplateManageServiceImpl(IOfficialTemplateDao officialTemplateDao) {
		this.officialTemplateDao = officialTemplateDao;
	}
	/**
	 * ���OfficialTemplate��DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForOfficialTemplate(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (officialTemplateDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("OfficialTemplate��DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
			//��ʼ���� 1...
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForOfficialTemplate(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0,"OfficialTemplateDAO�Ƿ�Ϊ�ա�");
			}
			
			if (pFlag) 
			{
				if (officialTemplate.getID() == 0)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"ģ��ŷǷ�Ϊ�ա�");
				}
			
			}
			
			if (pFlag) {
				
				if(officialTemplateDao.delete(officialTemplate, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().append("ģ��ɾ��ʧ��");
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
	public boolean findOfficialTemplateByID(int pID, OfficialTemplate officialTemplate, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForOfficialTemplate(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0,"OfficialTemplateDAO�Ƿ�Ϊ��:");
			}

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (pID == 0) {
					pFlag = false;
					pErrInfo.getContent().append("����ģ���ŷǷ�Ϊ��!");
				}
			}
			
			if (pFlag) {
				if (officialTemplateDao.findByID(pID, officialTemplate, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"BLL���ѯ���й���ģ��Ƿ�Ϊ��!");
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
	public boolean findOfficialTemplates(OfficialDocType officialDocType,List<OfficialTemplate> officialTemplates, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			if (officialTemplateDao.findAll(officialDocType,officialTemplates, pErrInfo)==false) {
				pFlag=false;
				pErrInfo.getContent().insert(0,"����ģ��ʧ��!");
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
	public boolean saveOfficialTemplate(OfficialTemplate officialTemplate, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		try {
			//��ʼ���� 1...
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForOfficialTemplate(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0,"OfficialTemplateDAO�Ƿ�Ϊ�ա�");
			}
			
			if (officialTemplate.getTitle()== null) {
				pErrInfo.getContent().insert(0,"ģ�������Ƿ�Ϊ��");
			}else{
				if (officialTemplate.getTitle().trim().length() == 0) {
					pErrInfo.getContent().insert(0,"ģ�������Ƿ�Ϊ��");
				}
			}

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (officialTemplate.getDocType()== 0) {
					pErrInfo.getContent().insert(0,"ģ�������Ƿ�Ϊ��");
				}
			}
			
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 3;
				if (officialTemplate.getProvider()== null) {
					pErrInfo.getContent().insert(0,"ģ�������Ƿ�Ϊ��");
				}else{
					if(officialTemplate.getProvider().trim().length()==0){
						pErrInfo.getContent().insert(0,"ģ�������Ƿ�Ϊ��");
					}
				}
			}
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 4;
				if (officialTemplate.getFileName()== null) {
					pErrInfo.getContent().insert(0,"ģ���ļ����Ƿ�Ϊ��");
				}else{
					if (officialTemplate.getFileName().trim().length()== 0) {
						pErrInfo.getContent().insert(0,"ģ���ļ����Ƿ�Ϊ��");
				}
			}
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 5;
				if (officialTemplate.getCreateDate()== null) {
					pErrInfo.getContent().insert(0,"ģ�崴�����ڷǷ�Ϊ��");
				}
			}
			if(pFlag){
			if(officialTemplateDao.save(officialTemplate, pErrInfo)==false){
				pErrInfo.getContent().insert(0,"ģ�屣��ʧ��");
				}
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
	public boolean updateOfficialTemplate(OfficialTemplate officialTemplate, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForOfficialTemplate(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0,"OfficialTemplateDAO�Ƿ�Ϊ�ա�");
			}

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (officialTemplate.getDocType()== 0) {
					pErrInfo.getContent().insert(0,"ģ�������Ƿ�Ϊ��");
				}
			}
			
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 3;
				if (officialTemplate.getProvider()== null) {
					pErrInfo.getContent().insert(0,"ģ�������Ƿ�Ϊ��");
				}else{
					if(officialTemplate.getProvider().trim().length()==0){
						pErrInfo.getContent().insert(0,"ģ�������Ƿ�Ϊ��");
					}
				}
			}
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 4;
				if (officialTemplate.getFileName()== null) {
					pErrInfo.getContent().insert(0,"ģ���ļ����Ƿ�Ϊ��");
				}else{
					if (officialTemplate.getFileName().trim().length()== 0) {
						pErrInfo.getContent().insert(0,"ģ���ļ����Ƿ�Ϊ��");
				}
			}
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 5;
				if (officialTemplate.getCreateDate()== null) {
					pErrInfo.getContent().insert(0,"ģ�崴�����ڷǷ�Ϊ��");
				}
			}
			
			if(pFlag){
			if(officialTemplateDao.update(officialTemplate, pErrInfo)==false){
				pErrInfo.getContent().insert(0,"ģ����·�Ϊ��");
				}
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
	public boolean findOfficialTemplateByName(OfficialDocType officialDocType,String title, List<OfficialTemplate> officialTemplates, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub

		
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForOfficialTemplate(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0,"OfficialTemplateDAO�Ƿ�Ϊ�ա�");
			}

				if(officialTemplateDao.findByName(officialDocType,title, officialTemplates, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "ģ�����ƷǷ�Ϊ��");
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
