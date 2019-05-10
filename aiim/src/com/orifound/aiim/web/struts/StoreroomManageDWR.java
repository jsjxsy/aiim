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
	 * 查询指定节点下的子节点集合
	 * @param storeroomStructureID
	 * @return 子节点对象集合
	 */
	public List<StoreroomStructure> findStoreroomStructureChildrenByID(int storeroomStructureID,HttpSession session,ServletContext application){		
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();		
		Throwable throwable = new Throwable();
		List<StoreroomStructure> children = new ArrayList<StoreroomStructure>();
		try {
			pErrPos = 1;
			if(storeroomStructureID == 0){//如果是根节点，则返回空
				pFlag = false;
			}
			
			//调用业务逻辑，查询库房结构信息		
			if (pFlag) {
				StoreroomStructure storeroomStructure =null;
				if(true/*storeroomManageService.findStoreroomStructureChildrenByID(storeroomStructureID, children, pErrInfo)*/){
					for(int i= 0;i<5;i++){
						storeroomStructure = new StoreroomStructure();
						storeroomStructure.setID((int)(Math.random()*1000));
						storeroomStructure.setBarcode(10000+(int)(Math.random()*1000)+i+"");
						storeroomStructure.setEndFlag(true);
						storeroomStructure.setTotalSize(30);
						storeroomStructure.setName("第"+i+"层");
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
	 * 查找指定编号的库房结构信息
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
			
			//调用业务逻辑，查询库房结构信息		
			if (pFlag) {
				if(true/*storeroomManageService.findStoreroomStructureByID(storeroomStructureID, storeroomStructure, pErrInfo)*/){
					storeroomStructure.setID(storeroomStructureID);
					storeroomStructure.setBarcode("13421");
					storeroomStructure.setEndFlag(storeroomStructureID%2==0);
					storeroomStructure.setName("第一层");
					storeroomStructure.setTotalSize(30);
System.out.println("ID:" + storeroomStructureID + "查询成功！");
					//10-17为层
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
	 * 添加库房结构信息
	 * @param storeroomStructure
	 * @return
	 */
	public int updateStoreroomStructure(StoreroomStructure storeroomStructure,HttpSession session,ServletContext application) {//父节点的ID存在此对象中
		int structureID = 0;		
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();		
		Throwable throwable = new Throwable();
		try {
			pErrPos = 1;
			//判断库房结构信息对象不为空
			if (storeroomStructure==null) {
				pFlag=false;
				pErrInfo.getContent().append("storeroomStructure对象未初始化");
			}		
			pErrPos = 2;
			//调用业务逻辑，添加库房结构信息		
			if (pFlag) {
				if(true/*storeroomManageService.updateStoreroomStructure(storeroomStructure, pErrInfo)*/){
System.out.println("更新库房结构信息成功！ parentID ："+ storeroomStructure.getParentID() +" name:"+ storeroomStructure.getName()+" type:"+" EndFlag:"+ storeroomStructure.getEndFlag()+" RoomFlag:"+storeroomStructure.getRoomFlag() );
					storeroomStructure.setID((int)(Math.random()*1000));//模拟数据
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
	 * 添加库房结构信息
	 * @param storeroomStructure
	 * @return
	 */
	public int saveStoreroomStructure(StoreroomStructure storeroomStructure,HttpSession session,ServletContext application) {//父节点的ID存在此对象中
		int structureID = 0;		
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();		
		Throwable throwable = new Throwable();
		try {
			pErrPos = 1;
			//判断库房结构信息对象不为空
			if (storeroomStructure==null) {
				pFlag=false;
				pErrInfo.getContent().append("archivesInfo对象未初始化");
			}
		
			pErrPos = 2;
			//调用业务逻辑，添加库房结构信息		
			if (pFlag) {
				if(true/*storeroomManageService.saveStoreroomStructure(storeroomStructure, pErrInfo)*/){
System.out.println("添加库房结构信息成功！ parentID ："+ storeroomStructure.getParentID() +" name:"+ storeroomStructure.getName()+" type:"+" EndFlag:"+ storeroomStructure.getEndFlag()+" RoomFlag:"+storeroomStructure.getRoomFlag() );
					storeroomStructure.setID((int)(Math.random()*1000));//模拟数据
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
	 * 删除库房结构信息
	 * @param storeroomStructure
	 * @return
	 */
	public int deleteStoreroomStructure(StoreroomStructure storeroomStructure,HttpSession session,ServletContext application) {//父节点的ID存在此对象中
		int structureID = 0;		
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();	
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//判断库房结构信息对象不为空
			if (storeroomStructure==null) {
				pFlag=false;
				pErrInfo.getContent().append("storeroomStructure对象未初始化");
			}
		
			pErrPos = 2;
			//调用业务逻辑，添加库房结构信息		
			if (pFlag) {
				if(true/*storeroomManageService.deleteStoreroomStructure(storeroomStructure, pErrInfo)*/){
System.out.println("删除库房结构信息成功！ parentID ："+ storeroomStructure.getParentID() +" name:"+ storeroomStructure.getName()+" type:"+" EndFlag:"+ storeroomStructure.getEndFlag()+" RoomFlag:"+storeroomStructure.getRoomFlag() );
					storeroomStructure.setID((int)(Math.random()*1000));//模拟数据
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
