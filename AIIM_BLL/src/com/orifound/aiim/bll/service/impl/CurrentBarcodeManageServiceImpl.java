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
 * 条形码信息管理服务实现类
 *
 */
public class CurrentBarcodeManageServiceImpl implements
		ICurrentBarcodeManageService {
	
	/**
	 * 构造函数
	 */
	public CurrentBarcodeManageServiceImpl() {

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public CurrentBarcodeManageServiceImpl(ICurrentBarcodeDao currentBarcodeDao) {
		this.currentBarcodeDao = currentBarcodeDao;
	}
	
	
	/**
	 * 条形码信息表的数据访问对象
	 */
	private ICurrentBarcodeDao currentBarcodeDao = null;

	/**
	 * 获取属性值：条形码信息表的数据访问对象
	 * @return 条形码信息表的数据访问对象
	 */
	public ICurrentBarcodeDao getCurrentBarcodeDao() {
		return currentBarcodeDao;
	}

	/**
	 * 设置属性值：条形码信息表的数据访问对象
	 * @param currentBarcodeDao 条形码信息表的数据访问对象
	 */
	public void setCurrentBarcodeDao(ICurrentBarcodeDao currentBarcodeDao) {
		this.currentBarcodeDao = currentBarcodeDao;
	}
	
	
	/**
	 * 检查条形码信息的业务逻辑对象依赖注入（BLL Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkBllInjectionForCurrentBarcode(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查是否有进行BLL业务逻辑对象的依赖注入
			pErrPos = 1;
			if (currentBarcodeDao == null) {
				pFlag = false;
				pErrInfo.getContent().append(
						"条形码信息的DAO非法为空，请检查是否有进行依赖注入或赋值。");
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
			//检查是否有进行DAO依赖注入
			if (checkBllInjectionForCurrentBarcode(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//检查条形码类型是否已初始化
			if(currentBarcode == null){
				pFlag = false;
				pErrInfo.getContent().append("条形码对象未初始化。");
			}else{
				//检查条形码类型是否已赋值
				if(currentBarcode.getBarcodeType()==EnumBarcodeType.NONE){
					pFlag = false;
					pErrInfo.getContent().append("条形码类型非法为空。");
				}
			}
			
			//调用DAO接口
			if (pFlag)
			{
				pErrPos = 2;
				if (currentBarcodeDao.findByBarcodeType(currentBarcode, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"根据条形码类型查找条码信息失败: ");
				}
			}
			
		}
		catch (Exception e)
		{
			//异常错误
			pFlag=false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag==false && pErrInfo.getContent().length()>0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder=new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				
				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException()!=null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}
				
				pErrInfo.getContent().insert(0,tempBuilder.toString());
				tempBuilder=null;
			}
			
			//销毁局部变量
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
			//检查是否有进行DAO依赖注入
			if (checkBllInjectionForCurrentBarcode(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//检查条形码类型是否已初始化
			if(currentBarcode == null){
				pFlag = false;
				pErrInfo.getContent().append("条形码对象未初始化。");
			}else{
				//检查条形码类型是否已赋值
				if(currentBarcode.getBarcodeType()==EnumBarcodeType.NONE){
					pFlag = false;
					pErrInfo.getContent().append("条形码类型非法为空。");
				}
				if(currentBarcode.getCurrentBarcodeNo()==0){
					pFlag = false;
					pErrInfo.getContent().append("条形码值非法为空。");
				}
			}
			//调用DAO接口
			if (pFlag)
			{
				pErrPos = 2;
				//更新指定类型条形码的值
				if (currentBarcodeDao.update(currentBarcode, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"更新条形码信息失败: ");
				}
//				else{
//					System.out.println("更新成功：" + "NO:"+currentBarcode.getCurrentBarcodeNo());
//				}
			}
		}
		catch (Exception e)
		{
			//异常错误
			pFlag=false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag==false && pErrInfo.getContent().length()>0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder=new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				
				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException()!=null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}
				
				pErrInfo.getContent().insert(0,tempBuilder.toString());
				tempBuilder=null;
			}
			
			//销毁局部变量
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
			//检查是否有进行DAO依赖注入
			if (checkBllInjectionForCurrentBarcode(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//检查条形码类型是否已初始化
			if(currentBarcode == null){
				pFlag = false;
				pErrInfo.getContent().append("条形码对象未初始化。");
			}else{
				//检查条形码类型是否已赋值
				if(currentBarcode.getBarcodeType()==EnumBarcodeType.NONE){
					pFlag = false;
					pErrInfo.getContent().append("条形码类型非法为空。");
				}				
			}
			
			//调用DAO接口
			if (pFlag)
			{
				pErrPos = 2;
				//根据条码类型查找条码值
				if (findCurrentBarcodeByBarcodeType(currentBarcode, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"根据条形码类型查找条形码信息失败: ");
				}else{//查找条形码信息成功
					//如果指定条形码类型没有记录，就将执行结果插入到数据库
					if(currentBarcode.getCurrentBarcodeNo()==0){
						currentBarcode.setCurrentBarcodeNo(num);
						if(currentBarcodeDao.save(currentBarcode, pErrInfo)==false){
							pFlag = false;
							pErrInfo.getContent().insert(0,"保存条形码信息失败: ");
						}
					}else{//如果有该条形码类型的记录，则将执行结果更新到数据库
						currentBarcode.setCurrentBarcodeNo(currentBarcode.getCurrentBarcodeNo()+num);
						if(updateCurrentBarcode(currentBarcode, pErrInfo)==false){
							pFlag = false;
							pErrInfo.getContent().insert(0,"更新条形码信息失败: ");
						}
					}	
				}
				
				
			}
		}
		catch (Exception e)
		{
			//异常错误
			pFlag=false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag==false && pErrInfo.getContent().length()>0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder=new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				
				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException()!=null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}
				
				pErrInfo.getContent().insert(0,tempBuilder.toString());
				tempBuilder=null;
			}
			
			//销毁局部变量
			throwable=null;
		}
		return pFlag;	
	}
	
	

}
