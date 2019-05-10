/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import com.orifound.aiim.bll.service.ICurrentBarcodeManageService;
import com.orifound.aiim.dal.dao.ICurrentBarcodeDao;
import com.orifound.aiim.entity.CurrentBarcode;
import com.orifound.aiim.entity.EnumBarcodeType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ��������Ϣ�������ʵ����
 *
 */
public class CurrentBarcodeManageServiceImpl implements
		ICurrentBarcodeManageService {
	
	/**
	 * ���캯��
	 */
	public CurrentBarcodeManageServiceImpl() {

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public CurrentBarcodeManageServiceImpl(ICurrentBarcodeDao currentBarcodeDao) {
		this.currentBarcodeDao = currentBarcodeDao;
	}
	
	
	/**
	 * ��������Ϣ������ݷ��ʶ���
	 */
	private ICurrentBarcodeDao currentBarcodeDao = null;

	/**
	 * ��ȡ����ֵ����������Ϣ������ݷ��ʶ���
	 * @return ��������Ϣ������ݷ��ʶ���
	 */
	public ICurrentBarcodeDao getCurrentBarcodeDao() {
		return currentBarcodeDao;
	}

	/**
	 * ��������ֵ����������Ϣ������ݷ��ʶ���
	 * @param currentBarcodeDao ��������Ϣ������ݷ��ʶ���
	 */
	public void setCurrentBarcodeDao(ICurrentBarcodeDao currentBarcodeDao) {
		this.currentBarcodeDao = currentBarcodeDao;
	}
	
	
	/**
	 * �����������Ϣ��ҵ���߼���������ע�루BLL Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkBllInjectionForCurrentBarcode(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//����Ƿ��н���BLLҵ���߼����������ע��
			pErrPos = 1;
			if (currentBarcodeDao == null) {
				pFlag = false;
				pErrInfo.getContent().append(
						"��������Ϣ��DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
	
	
	

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.ICurrentBarcodeManageService#findCurrentBarcodeByBarcodeType(com.orifound.aiim.entity.CurrentBarcode, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findCurrentBarcodeByBarcodeType(
			CurrentBarcode currentBarcode, ErrInfo pErrInfo) {
		boolean pFlag=true;
		int pErrPos=0;
		Throwable throwable = new Throwable();		
		
		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkBllInjectionForCurrentBarcode(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//��������������Ƿ��ѳ�ʼ��
			if(currentBarcode == null){
				pFlag = false;
				pErrInfo.getContent().append("���������δ��ʼ����");
			}else{
				//��������������Ƿ��Ѹ�ֵ
				if(currentBarcode.getBarcodeType()==EnumBarcodeType.NONE){
					pFlag = false;
					pErrInfo.getContent().append("���������ͷǷ�Ϊ�ա�");
				}
			}
			
			//����DAO�ӿ�
			if (pFlag)
			{
				pErrPos = 2;
				if (currentBarcodeDao.findByBarcodeType(currentBarcode, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"�������������Ͳ���������Ϣʧ��: ");
				}
			}
			
		}
		catch (Exception e)
		{
			//�쳣����
			pFlag=false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag==false && pErrInfo.getContent().length()>0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder=new StringBuilder(stackTraceElements[0].getClassName());
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
				
				pErrInfo.getContent().insert(0,tempBuilder.toString());
				tempBuilder=null;
			}
			
			//���پֲ�����
			throwable=null;
		}
		
		return pFlag;	
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.ICurrentBarcodeManageService#updateCurrentBarcode(com.orifound.aiim.entity.CurrentBarcode, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateCurrentBarcode(CurrentBarcode currentBarcode,
			ErrInfo pErrInfo) {
		boolean pFlag=true;
		int pErrPos=0;
		Throwable throwable = new Throwable();		
		
		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkBllInjectionForCurrentBarcode(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//��������������Ƿ��ѳ�ʼ��
			if(currentBarcode == null){
				pFlag = false;
				pErrInfo.getContent().append("���������δ��ʼ����");
			}else{
				//��������������Ƿ��Ѹ�ֵ
				if(currentBarcode.getBarcodeType()==EnumBarcodeType.NONE){
					pFlag = false;
					pErrInfo.getContent().append("���������ͷǷ�Ϊ�ա�");
				}
				if(currentBarcode.getCurrentBarcodeNo()==0){
					pFlag = false;
					pErrInfo.getContent().append("������ֵ�Ƿ�Ϊ�ա�");
				}
			}
			//����DAO�ӿ�
			if (pFlag)
			{
				pErrPos = 2;
				//����ָ�������������ֵ
				if (currentBarcodeDao.update(currentBarcode, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"������������Ϣʧ��: ");
				}
//				else{
//					System.out.println("���³ɹ���" + "NO:"+currentBarcode.getCurrentBarcodeNo());
//				}
			}
		}
		catch (Exception e)
		{
			//�쳣����
			pFlag=false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag==false && pErrInfo.getContent().length()>0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder=new StringBuilder(stackTraceElements[0].getClassName());
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
				
				pErrInfo.getContent().insert(0,tempBuilder.toString());
				tempBuilder=null;
			}
			
			//���پֲ�����
			throwable=null;
		}
		return pFlag;	
	}

	
	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.ICurrentBarcodeManageService#updateCurrentBarcode(com.orifound.aiim.entity.CurrentBarcode, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean printBarcode(int num,CurrentBarcode currentBarcode,
			ErrInfo pErrInfo) {
		boolean pFlag=true;
		int pErrPos=0;
		Throwable throwable = new Throwable();		
		
		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkBllInjectionForCurrentBarcode(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//��������������Ƿ��ѳ�ʼ��
			if(currentBarcode == null){
				pFlag = false;
				pErrInfo.getContent().append("���������δ��ʼ����");
			}else{
				//��������������Ƿ��Ѹ�ֵ
				if(currentBarcode.getBarcodeType()==EnumBarcodeType.NONE){
					pFlag = false;
					pErrInfo.getContent().append("���������ͷǷ�Ϊ�ա�");
				}				
			}
			
			//����DAO�ӿ�
			if (pFlag)
			{
				pErrPos = 2;
				//�����������Ͳ�������ֵ
				if (findCurrentBarcodeByBarcodeType(currentBarcode, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"�������������Ͳ�����������Ϣʧ��: ");
				}else{//������������Ϣ�ɹ�
					//���ָ������������û�м�¼���ͽ�ִ�н�����뵽���ݿ�
					if(currentBarcode.getCurrentBarcodeNo()==0){
						currentBarcode.setCurrentBarcodeNo(num);
						if(currentBarcodeDao.save(currentBarcode, pErrInfo)==false){
							pFlag = false;
							pErrInfo.getContent().insert(0,"������������Ϣʧ��: ");
						}
					}else{//����и����������͵ļ�¼����ִ�н�����µ����ݿ�
						currentBarcode.setCurrentBarcodeNo(currentBarcode.getCurrentBarcodeNo()+num);
						if(updateCurrentBarcode(currentBarcode, pErrInfo)==false){
							pFlag = false;
							pErrInfo.getContent().insert(0,"������������Ϣʧ��: ");
						}
					}	
				}
				
				
			}
		}
		catch (Exception e)
		{
			//�쳣����
			pFlag=false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag==false && pErrInfo.getContent().length()>0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder=new StringBuilder(stackTraceElements[0].getClassName());
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
				
				pErrInfo.getContent().insert(0,tempBuilder.toString());
				tempBuilder=null;
			}
			
			//���پֲ�����
			throwable=null;
		}
		return pFlag;	
	}
	
	

}
