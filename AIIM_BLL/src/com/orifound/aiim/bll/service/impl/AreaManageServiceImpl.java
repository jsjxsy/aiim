package com.orifound.aiim.bll.service.impl;

import java.util.List;

import com.orifound.aiim.bll.service.IAreaManageService;
import com.orifound.aiim.dal.dao.IAreaDao;
import com.orifound.aiim.entity.Area;
import com.orifound.aiim.entity.ErrInfo;

public class AreaManageServiceImpl implements IAreaManageService {
	/**
	 * ���캯��
	 */
	public AreaManageServiceImpl() {

	}
	
	/**
	 * ������DAO�ӿ�
	 */
	private IAreaDao areaDao = null;
	
	public IAreaDao getAreaDao() {
		return areaDao;
	}
	
	public void setAreaDao(IAreaDao areaDao) {
		this.areaDao = areaDao;
	}


	/**
		 * ���ҵ�����������ݷ������Ƿ�ע��ɹ���DAO Depandency Injection��
		 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
		 * @return ����ɹ�����true�����򷵻�false
		 */
	private boolean checkInjection(ErrInfo pErrInfo) {
	
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		try
		{
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (areaDao==null)
			{
				pFlag = false;
				pErrInfo.getContent().append("Entity��DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
		}
		catch (Exception e)
		{
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				
				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException()!=null)
				{
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
	public boolean findAllArea(List<Area> areas, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkInjection(pErrInfo) == false) {
				pFlag = false;
			}

			//����DAO�ӿ�
			if (pFlag) {
				if (areaDao.findAllArea(areas, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ȡ����������Ϣʧ��: ");
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
	public boolean findByIDArea(int pID, Area pArea, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

}
