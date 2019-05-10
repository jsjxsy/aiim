package com.orifound.aiim.bll.service.impl;

import java.util.List;

import com.orifound.aiim.bll.service.ITempratureHumidityInfoManageService;
import com.orifound.aiim.dal.dao.ITempratureHumidityInfoDao;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.TempratureHumidityInfo;
/**
 * 库房温度湿度信息管理服务
 * @author Administrator
 *
 */
public class TempratureHumidityInfoManageServiceImpl implements ITempratureHumidityInfoManageService {

	/**
	 * 构造函数
	 */
	public TempratureHumidityInfoManageServiceImpl() {

	}
	public TempratureHumidityInfoManageServiceImpl(ITempratureHumidityInfoDao tempratureHumidityInfoDao) {
		this.tempratureHumidityInfoDao = tempratureHumidityInfoDao;
	}
	
	/**
	 * 库房温湿度登记信息表的DAO接口
	 */
	private ITempratureHumidityInfoDao tempratureHumidityInfoDao = null;
	 
	public ITempratureHumidityInfoDao getTempratureHumidityInfoDao() {
		return tempratureHumidityInfoDao;
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
			if (tempratureHumidityInfoDao== null) {
				pFlag = false;
				pErrInfo.getContent().append("库房温湿度登记信息表的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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
	
	public void setTempratureHumidityInfoDao(ITempratureHumidityInfoDao tempratureHumidityInfoDao) {
		this.tempratureHumidityInfoDao = tempratureHumidityInfoDao;
	}

	@Override
	public boolean addTempratureHumidityInfo(TempratureHumidityInfo tempratureHumidityInfo, ErrInfo pErrInfo) {
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
				if (tempratureHumidityInfoDao.add(tempratureHumidityInfo, pErrInfo)== false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "添加库房温湿度登记信息失败: ");
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
	public boolean findTempratureHumidityInfosByCondition(String whereSQL, List<TempratureHumidityInfo> tempratureHumidityInfos, DataPageInfo dataPageInfo, ErrInfo pErrInfo) {
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
				if (tempratureHumidityInfoDao.findByCondition(whereSQL, tempratureHumidityInfos, dataPageInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "根据条件查询库房温湿度登记信息失败: ");
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
	public boolean deleteTempratureHumidityInfo(TempratureHumidityInfo tempratureHumidityInfo, ErrInfo pErrInfo) {
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
			
			if (pFlag) {
				pErrPos = 2;
				if(tempratureHumidityInfo.getID()==0){
					pErrInfo.getContent().append("库房环境信息的编号没有赋值！");
				}
			}

			//调用DAO接口
			if (pFlag) {
				if (tempratureHumidityInfoDao.delete(tempratureHumidityInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"删除指定的库房温湿度登记信息失败: ");
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
	public boolean findTempratureHumidityInfoByID(TempratureHumidityInfo tempratureHumidityInfo, ErrInfo pErrInfo) {
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
				if (tempratureHumidityInfoDao.findByID(tempratureHumidityInfo, pErrInfo)== false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "根据唯一标识查找库房温湿度登记信息失败: ");
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
	public boolean findTempratureHumidityInfos(List<TempratureHumidityInfo> tempratureHumidityInfos, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateTempratureHumidityInfo(TempratureHumidityInfo tempratureHumidityInfo, ErrInfo pErrInfo) {
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
				if (tempratureHumidityInfoDao.update(tempratureHumidityInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "更新指定的库房温湿度登记信息失败: ");
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
