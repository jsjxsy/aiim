/**
 * 
 */
package com.orifound.aiim.bll.service.impl;
import java.util.Date;
import java.util.List;

import com.orifound.aiim.bll.service.IStoreAddressInfoManageService;
import com.orifound.aiim.bll.service.IStoreroomManageService;
import com.orifound.aiim.dal.dao.IStoreAddressInfoDao;
import com.orifound.aiim.dal.dao.IStoreroomArchivesInfoDao;
import com.orifound.aiim.dal.dao.IStoreroomStructureDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.StoreAddressInfo;
import com.orifound.aiim.entity.StoreroomArchivesInfo;
import com.orifound.aiim.entity.StoreroomStructure;

/**
 * 库房管理服务实现类
 *
 */
public class StoreroomManageServiceImpl implements IStoreroomManageService {
	
	/**
	 * 构造函数
	 */
	public StoreroomManageServiceImpl() {
	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public StoreroomManageServiceImpl(IStoreroomStructureDao storeroomStructureDao) {
		this.storeroomStructureDao = storeroomStructureDao;
	}
	
	/**
	 * 档案上架位置信息表的DAO接口
	 */
	IStoreAddressInfoDao storeAddressInfoDao = null;

	public IStoreAddressInfoDao getStoreAddressInfoDao() {
		return storeAddressInfoDao;
	}

	public void setStoreAddressInfoDao(IStoreAddressInfoDao storeAddressInfoDao) {
		this.storeAddressInfoDao = storeAddressInfoDao;
	}

	/**
	 * 库房档案信息表 的DAO接口<br/>
	 * 框架注入
	 */
	IStoreroomArchivesInfoDao storeroomArchivesInfoDao = null;
	
	public IStoreroomArchivesInfoDao getStoreroomArchivesInfoDao() {
		return storeroomArchivesInfoDao;
	}

	public void setStoreroomArchivesInfoDao(IStoreroomArchivesInfoDao storeroomArchivesInfoDao) {
		this.storeroomArchivesInfoDao = storeroomArchivesInfoDao;
	}

	/**
	 * 库房结构信息表的DAO接口
	 * 框架注入
	 */
	IStoreroomStructureDao storeroomStructureDao = null;
	
	public IStoreroomStructureDao getStoreroomStructureDao() {
		return storeroomStructureDao;
	}

	public void setStoreroomStructureDao(IStoreroomStructureDao storeroomStructureDao) {
		this.storeroomStructureDao = storeroomStructureDao;
	}

	
	
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
			if (storeroomStructureDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("库房结构信息表的DAO非法为空，请检查是否有进行依赖注入或赋值。");
			}
			if (storeroomArchivesInfoDao==null) {
				pFlag = false;
				pErrInfo.getContent().append("库房档案信息表 的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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
	



	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IStoreroomManageService#findStoreroomArchivesInfos(com.orifound.aiim.entity.StoreroomArchivesInfoQueryCondition, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findStoreroomArchivesInfosByCondition(
			String whereSQL,
			List<StoreroomArchivesInfo> storeroomArchivesInfos, ErrInfo pErrInfo) {
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
				if (storeroomArchivesInfoDao.findByCondition(whereSQL, storeroomArchivesInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "根据查询条件查询库房档案信息失败: ");
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
System.out.println("error:" + pErrInfo.toString());
		}

		return pFlag;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IStoreroomManageService#findStoreroomStructureByID(int, com.orifound.aiim.entity.StoreroomStructure, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findStoreroomStructureByID(int storeroomStructureID,
			StoreroomStructure storeroomStructure, ErrInfo pErrInfo) {
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
				if (storeroomStructureDao.findByID(storeroomStructure, pErrInfo)== false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "根据库房结构编号查找库房结构详细信息失败: ");
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IStoreroomManageService#findStoreroomStructureChildrenByID(int, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findStoreroomStructureChildrenByID(int storeroomStructureID,
			List<StoreroomStructure> children, ErrInfo pErrInfo) {
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
				if (storeroomStructureDao.findChildrenByID(storeroomStructureID, children, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "根据库房结构编号查找其下级库房结构失败: ");
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


	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IStoreroomManageService#findStorerooms(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findStorerooms(List<StoreroomStructure> storeRooms,ErrInfo pErrInfo) {
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
				if (storeroomStructureDao.findStorerooms(storeRooms, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查找所有的库房信息失败: ");
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IStoreroomManageService#registerStoreAddress(java.lang.String, java.lang.String, com.orifound.aiim.entity.StoreAddressInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean registerStoreAddress(String archivesBoxBarcode,String storeAddressBarcode,ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		StoreAddressInfo storeAddressInfo = new StoreAddressInfo();
	
		/**
		 * 1、根据设备条形码查询库房结构信息
		 * 2、检查盒条形码是否已经存在于上架位置信息表中
		 * 3、根据上盒条形码存在情况决定执行插入还是更新操作
		 */
		try {
			//开始处理 1...
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			StoreroomStructure storeroomStructure = new StoreroomStructure();
			storeroomStructure.setBarcode(storeAddressBarcode);
			//根据条形码获取库房结构信息,并构造出上架位置信息
			if (pFlag) {
				pErrPos = 2;
				if (storeroomStructureDao.findByBarcode(storeroomStructure, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "根据库房设备条形码查找库房结构详细信息失败: ");
				}else{
					if(storeroomStructure.getID()==0){//没有找到设备条形码
						pFlag = false;
						pErrInfo.getContent().append("根据设备条形码没有找到对应的设备信息！设备条形码："+storeAddressBarcode);
					}else{//找到设备条形码，构造上架位置信息
						storeAddressInfo.setArchivesBoxBarcode(archivesBoxBarcode);
						storeAddressInfo.setPutTime(new Date());
						storeAddressInfo.setStoreAddressFullName(storeroomStructure.getFullName());
						storeAddressInfo.setStoreAddressID(storeroomStructure.getID());
					}
				}
			}
			
			//检查盒条形码是否存在,并根据存在与否执行相应的操作
			if(pFlag){
				pErrPos = 3;
				if(storeAddressInfoDao.checkBoxBarcodeExist(archivesBoxBarcode, pErrInfo)==true){//如果存在,执行更新操作 
					//更新上架位置信息
					if(storeAddressInfoDao.update(storeAddressInfo, pErrInfo)==false){
						pFlag = false;
						pErrInfo.getContent().append("更新指定的档案上架位置信息失败！盒条形码为："+archivesBoxBarcode);
					}
				}else{//不存在，执行插入操作
					//插入上架位置信息
					if(storeAddressInfoDao.add(storeAddressInfo, pErrInfo)==false){
						pFlag = false;
						pErrInfo.getContent().append("添加档案上架位置信息失败！盒条形码为："+archivesBoxBarcode);
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IStoreroomManageService#updateStoreroomStructure(com.orifound.aiim.entity.StoreroomStructure, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateStoreroomStructure(
			StoreroomStructure storeroomStructure, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateStoreroomStructureUsedSizeByBarcode(String storeAddressBarcode, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		StoreroomStructure storeroomStructure = null;

		try {
			//开始处理 1...
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkInjection(pErrInfo) == false) {
				pFlag = false;
			}
			/**
			 * 0、根据设备条形码获取设备信息
			 * 1、更新最底层设备已用空间
			 * 2、循环更新其上层设备已用空间
			 */
			
			//获取库房设备信息
			if (pFlag) {
				pErrPos = 2;
				storeroomStructure = new StoreroomStructure();
				storeroomStructure.setBarcode(storeAddressBarcode);
				if (storeroomStructureDao.findByBarcode(storeroomStructure, pErrInfo)==false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"根据库房设备条形码查找库房结构详细信息");
				}
			}

			//调用DAO接口
			if (pFlag) {
				pErrPos = 3;
				if (storeroomStructureDao.updateUsedSizeByBarcode(storeAddressBarcode, pErrInfo)== false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "通过条形码更新最底层设备的已用空间失败: ");
				}
			}
			
			
			//更新父节点已用空间
			if (pFlag) {
				pErrPos = 4;
				do {
					//更新当前设备的父节点已用空间
					if (storeroomStructureDao.updateUsedSizeByID(storeroomStructure.getParentID(), pErrInfo)==false) {
						pFlag = false;
						pErrInfo.getContent().insert(0,"通过设备编号("+storeroomStructure.getParentID()+")更新已用空间失败：");
					}
					
					//根据设备编号获取父节点的设备信息
					storeroomStructure.setID(storeroomStructure.getParentID());
					if (storeroomStructureDao.findByID(storeroomStructure, pErrInfo)==false) {
						pFlag = false;
						pErrInfo.getContent().insert(0,"根据设备编号("+storeroomStructure.getID()+")找库房结构详细信息");
					}
				} while (storeroomStructure.getParentID()!=0);//如果不是顶层库房设备，则继续更新其父节点的已用空间
				
			
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
