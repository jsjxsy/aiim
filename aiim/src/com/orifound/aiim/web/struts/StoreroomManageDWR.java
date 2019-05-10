package com.orifound.aiim.web.struts;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.ActionSupport;
import com.orifound.aiim.bll.service.IStoreroomManageService;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.StoreroomStructure;

public class StoreroomManageDWR extends ActionSupport {

	private static final long serialVersionUID = 6556L;
	
	IStoreroomManageService storeroomManageService ;
	
	public IStoreroomManageService getStoreroomManageService() {
		return storeroomManageService;
	}

	public void setStoreroomManageService(
			IStoreroomManageService storeroomManageService) {
		this.storeroomManageService = storeroomManageService;
	}
	
	
	/**
	 * ��ѯָ���ڵ��µ��ӽڵ㼯��
	 * @param storeroomStructureID
	 * @return �ӽڵ���󼯺�
	 */
	public List<StoreroomStructure> findStoreroomStructureChildrenByID(int storeroomStructureID,HttpSession session,ServletContext application){		
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();		
		Throwable throwable = new Throwable();
		List<StoreroomStructure> children = new ArrayList<StoreroomStructure>();
		try {
			pErrPos = 1;
			if(storeroomStructureID == 0){//����Ǹ��ڵ㣬�򷵻ؿ�
				pFlag = false;
			}
			
			//����ҵ���߼�����ѯ�ⷿ�ṹ��Ϣ		
			if (pFlag) {
				StoreroomStructure storeroomStructure =null;
				if(true/*storeroomManageService.findStoreroomStructureChildrenByID(storeroomStructureID, children, pErrInfo)*/){
					for(int i= 0;i<5;i++){
						storeroomStructure = new StoreroomStructure();
						storeroomStructure.setID((int)(Math.random()*1000));
						storeroomStructure.setBarcode(10000+(int)(Math.random()*1000)+i+"");
						storeroomStructure.setEndFlag(true);
						storeroomStructure.setTotalSize(30);
						storeroomStructure.setName("��"+i+"��");
						storeroomStructure.setTotalSize(30);
						children.add(storeroomStructure);
					}				
				}
			}
			
		} catch (Exception e) {
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
		}		
		return children;
	}
	
	
	/**
	 * ����ָ����ŵĿⷿ�ṹ��Ϣ
	 * @param storeroomStructureID
	 * @return
	 */
	public StoreroomStructure findStoreroomStructureByID(int storeroomStructureID,HttpSession session,ServletContext application) throws Exception{					
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();		
		Throwable throwable = new Throwable();
		StoreroomStructure storeroomStructure = new StoreroomStructure();
		try {
			pErrPos = 1;			
			
			//����ҵ���߼�����ѯ�ⷿ�ṹ��Ϣ		
			if (pFlag) {
				if(true/*storeroomManageService.findStoreroomStructureByID(storeroomStructureID, storeroomStructure, pErrInfo)*/){
					storeroomStructure.setID(storeroomStructureID);
					storeroomStructure.setBarcode("13421");
					storeroomStructure.setEndFlag(storeroomStructureID%2==0);
					storeroomStructure.setName("��һ��");
					storeroomStructure.setTotalSize(30);
System.out.println("ID:" + storeroomStructureID + "��ѯ�ɹ���");
					//10-17Ϊ��
				}
			}
		} catch (Exception e) {
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
		}		
		return storeroomStructure;
	}
	
	
	/**
	 * ��ӿⷿ�ṹ��Ϣ
	 * @param storeroomStructure
	 * @return
	 */
	public int updateStoreroomStructure(StoreroomStructure storeroomStructure,HttpSession session,ServletContext application) {//���ڵ��ID���ڴ˶�����
		int structureID = 0;		
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();		
		Throwable throwable = new Throwable();
		try {
			pErrPos = 1;
			//�жϿⷿ�ṹ��Ϣ����Ϊ��
			if (storeroomStructure==null) {
				pFlag=false;
				pErrInfo.getContent().append("storeroomStructure����δ��ʼ��");
			}		
			pErrPos = 2;
			//����ҵ���߼�����ӿⷿ�ṹ��Ϣ		
			if (pFlag) {
				if(true/*storeroomManageService.updateStoreroomStructure(storeroomStructure, pErrInfo)*/){
System.out.println("���¿ⷿ�ṹ��Ϣ�ɹ��� parentID ��"+ storeroomStructure.getParentID() +" name:"+ storeroomStructure.getName()+" type:"+" EndFlag:"+ storeroomStructure.getEndFlag()+" RoomFlag:"+storeroomStructure.getRoomFlag() );
					storeroomStructure.setID((int)(Math.random()*1000));//ģ������
					structureID = storeroomStructure.getID();
				}
			}
		} catch (Exception e) {
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
		}
		return structureID;
	}
	
	
	
	/**
	 * ��ӿⷿ�ṹ��Ϣ
	 * @param storeroomStructure
	 * @return
	 */
	public int saveStoreroomStructure(StoreroomStructure storeroomStructure,HttpSession session,ServletContext application) {//���ڵ��ID���ڴ˶�����
		int structureID = 0;		
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();		
		Throwable throwable = new Throwable();
		try {
			pErrPos = 1;
			//�жϿⷿ�ṹ��Ϣ����Ϊ��
			if (storeroomStructure==null) {
				pFlag=false;
				pErrInfo.getContent().append("archivesInfo����δ��ʼ��");
			}
		
			pErrPos = 2;
			//����ҵ���߼�����ӿⷿ�ṹ��Ϣ		
			if (pFlag) {
				if(true/*storeroomManageService.saveStoreroomStructure(storeroomStructure, pErrInfo)*/){
System.out.println("��ӿⷿ�ṹ��Ϣ�ɹ��� parentID ��"+ storeroomStructure.getParentID() +" name:"+ storeroomStructure.getName()+" type:"+" EndFlag:"+ storeroomStructure.getEndFlag()+" RoomFlag:"+storeroomStructure.getRoomFlag() );
					storeroomStructure.setID((int)(Math.random()*1000));//ģ������
					structureID = storeroomStructure.getID();
				}
			}
		} catch (Exception e) {
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
		}
		return structureID;
	}
	
	/**
	 * ɾ���ⷿ�ṹ��Ϣ
	 * @param storeroomStructure
	 * @return
	 */
	public int deleteStoreroomStructure(StoreroomStructure storeroomStructure,HttpSession session,ServletContext application) {//���ڵ��ID���ڴ˶�����
		int structureID = 0;		
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();	
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//�жϿⷿ�ṹ��Ϣ����Ϊ��
			if (storeroomStructure==null) {
				pFlag=false;
				pErrInfo.getContent().append("storeroomStructure����δ��ʼ��");
			}
		
			pErrPos = 2;
			//����ҵ���߼�����ӿⷿ�ṹ��Ϣ		
			if (pFlag) {
				if(true/*storeroomManageService.deleteStoreroomStructure(storeroomStructure, pErrInfo)*/){
System.out.println("ɾ���ⷿ�ṹ��Ϣ�ɹ��� parentID ��"+ storeroomStructure.getParentID() +" name:"+ storeroomStructure.getName()+" type:"+" EndFlag:"+ storeroomStructure.getEndFlag()+" RoomFlag:"+storeroomStructure.getRoomFlag() );
					storeroomStructure.setID((int)(Math.random()*1000));//ģ������
					structureID = storeroomStructure.getID();
				}
			}
		} catch (Exception e) {
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
		}
		return structureID;
	}
	

}
