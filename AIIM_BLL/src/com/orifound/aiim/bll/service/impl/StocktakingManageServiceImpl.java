package com.orifound.aiim.bll.service.impl;

import java.util.List;

import com.orifound.aiim.bll.service.IStocktakingManageService;
import com.orifound.aiim.dal.dao.IStockReportAddressNotMatchDao;
import com.orifound.aiim.dal.dao.IStockReportArchivesBoxNotMatchDao;
import com.orifound.aiim.dal.dao.IStockReportArchivesCountDao;
import com.orifound.aiim.dal.dao.IStockReportPaperNotExistDao;
import com.orifound.aiim.dal.dao.IStockReportSystemNotExistDao;
import com.orifound.aiim.dal.dao.IStocktakingDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.StockReportAddressNotMatch;
import com.orifound.aiim.entity.StockReportArchivesBoxNotMatch;
import com.orifound.aiim.entity.StockReportArchivesCount;
import com.orifound.aiim.entity.StockReportPaperNotExist;
import com.orifound.aiim.entity.StockReportSystemNotExist;
import com.orifound.aiim.entity.StocktakingAddressBoxDetail;
import com.orifound.aiim.entity.StocktakingArchivesDetail;
/**
 * 库房盘点业务逻辑实现类
 * @author Administrator
 *
 */
public class StocktakingManageServiceImpl implements IStocktakingManageService {
	
	/**
	 * 构造函数
	 */
	public StocktakingManageServiceImpl() {

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public StocktakingManageServiceImpl(IStocktakingDao stocktakingDao) {
		this.stocktakingDao = stocktakingDao;
	}
	
	/**
	 * 库房盘点Dao
	 */
	private IStocktakingDao stocktakingDao = null;
	

	public IStocktakingDao getStocktakingDao() {
		return stocktakingDao;
	}

	public void setStocktakingDao(IStocktakingDao stocktakingDao) {
		this.stocktakingDao = stocktakingDao;
	}
	/********************** Dao注入  *****************************/
	
	/**
	 * 库房盘点- 库房盘点档案数量信息表的DAO接口定义
	 */
	private IStockReportArchivesCountDao stockReportArchivesCountDao = null;
	
	public IStockReportArchivesCountDao getStockReportArchivesCountDao() {
		return stockReportArchivesCountDao;
	}

	public void setStockReportArchivesCountDao(IStockReportArchivesCountDao stockReportArchivesCountDao) {
		this.stockReportArchivesCountDao = stockReportArchivesCountDao;
	}

	/**
	 * 盘点报告-系统中不在架档案信息表的DAO接口定义
	 */
	private IStockReportSystemNotExistDao stockReportSystemNotExistDao = null;
	
	public IStockReportSystemNotExistDao getStockReportSystemNotExistDao() {
		return stockReportSystemNotExistDao;
	}

	public void setStockReportSystemNotExistDao(IStockReportSystemNotExistDao stockReportSystemNotExistDao) {
		this.stockReportSystemNotExistDao = stockReportSystemNotExistDao;
	}

	/**
	 * 库房盘点-实物档案不在架信息表的DAO接口定义
	 */
	private IStockReportPaperNotExistDao stockReportPaperNotExistDao = null;
		
	public IStockReportPaperNotExistDao getStockReportPaperNotExistDao() {
		return stockReportPaperNotExistDao;
	}

	public void setStockReportPaperNotExistDao(IStockReportPaperNotExistDao stockReportPaperNotExistDao) {
		this.stockReportPaperNotExistDao = stockReportPaperNotExistDao;
	}
	
	/**
	 * 库房盘点-上架位置不匹配信息表的DAO接口定义
	 */
	private IStockReportAddressNotMatchDao stockReportAddressNotMatchDao = null;
	
	public IStockReportAddressNotMatchDao getStockReportAddressNotMatchDao() {
		return stockReportAddressNotMatchDao;
	}

	public void setStockReportAddressNotMatchDao(IStockReportAddressNotMatchDao stockReportAddressNotMatchDao) {
		this.stockReportAddressNotMatchDao = stockReportAddressNotMatchDao;
	}

	/**
	 * 库房盘点-档案装盒不匹配信息表的DAO接口定义
	 */
	private IStockReportArchivesBoxNotMatchDao stockReportArchivesBoxNotMatchDao = null;
	
	public IStockReportArchivesBoxNotMatchDao getStockReportArchivesBoxNotMatchDao() {
		return stockReportArchivesBoxNotMatchDao;
	}

	public void setStockReportArchivesBoxNotMatchDao(IStockReportArchivesBoxNotMatchDao stockReportArchivesBoxNotMatchDao) {
		this.stockReportArchivesBoxNotMatchDao = stockReportArchivesBoxNotMatchDao;
	}
	
	
	/********************** Dao注入end  *************************/
	
	

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
			if (stocktakingDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("库房盘点的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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
	

	@Override
	public boolean addStocktakingAddressBoxDetail(StocktakingAddressBoxDetail stocktakingAddressBoxDetail, ErrInfo pErrInfo) {
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
				if (stocktakingDao.checkAddressBoxDetailExist(stocktakingAddressBoxDetail, pErrInfo)==false) {//表中不存在执行新增操作
					if (stocktakingDao.addAddressBoxDetail(stocktakingAddressBoxDetail, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "添加库房盘点的设备位置与档案盒详细失败: ");
					}
				}else{//表中存在则执行更新操作
					if (stocktakingDao.updateAddressBoxDetail(stocktakingAddressBoxDetail, pErrInfo)==false) {
						pFlag = false;
						pErrInfo.getContent().insert(0,"更新库房盘点的设备位置与档案盒详细失败：");
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

	@Override
	public boolean addStocktakingArchivesDetail(StocktakingArchivesDetail stocktakingArchivesDetail, ErrInfo pErrInfo) {
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
				if (stocktakingDao.checkArchivesDetailExist(stocktakingArchivesDetail, pErrInfo)==false) {//不存在则执行新增操作
					if (stocktakingDao.addArchivesDetail(stocktakingArchivesDetail, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "添加库房盘点的档案盒与档案卷详细失败: ");
					}
				}else{//存在则执行更新操作
					if (stocktakingDao.updateArchivesDetail(stocktakingArchivesDetail, pErrInfo)==false) {
						pFlag = false;
						pErrInfo.getContent().insert(0,"更新库房盘点的档案盒与档案卷详细失败：");
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

	@Override
	public boolean executeStocktaking(int stocktakingID, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		/**
		 * 单击盘点时先在数据库中将当前批次的盘点结果集全部删除
		 * 1、执行数量盘点
		 * 2、系统中不在架档案情况盘点
		 * 3、实物档案不在架盘点
		 * 4、上架位置不匹配的档案盘点
		 * 5、档案装盒不匹配的情况盘点
		 */
		
		try {
			//开始处理 1...
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkInjection(pErrInfo) == false) {
				pFlag = false;
			}
						
			//1:数量盘点
			if (pFlag) {
				pErrPos = 2;
				if (stockReportArchivesCountDao.executeStocktakingByStocktakingID(stocktakingID, pErrInfo)==false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"执行库房档案数量信息盘点失败：");
				}else{
					System.out.println("执行库房档案数量信息盘点成功！");
				}
			}
			
			//2:系统中不在架档案情况盘点
			if (pFlag) {
				pErrPos = 3;
				if(stockReportSystemNotExistDao.executeStocktakingByStocktakingID(stocktakingID, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"执行系统中不在架档案情况盘点失败：");
				}else{
					System.out.println("系统中不在架档案情况盘点成功！");
				}
			}
			
			//3:实物档案不在架盘点
			if (pFlag) {
				pErrPos = 4;
				if (pFlag) {
					if(stockReportPaperNotExistDao.executeStocktakingByStocktakingID(stocktakingID, pErrInfo)==false){
						pFlag = false;
						pErrInfo.getContent().insert(0,"执行实物档案不在架档案情况盘点失败：");
					}else{
						System.out.println("执行实物档案不在架档案情况盘点成功！");
					}
				}
			}
			
			//4:上架位置不匹配的档案盘点
			if (pFlag) {
				pErrPos = 5;				
				if(stockReportAddressNotMatchDao.executeStocktakingByStocktakingID(stocktakingID, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"执行上架位置不匹配信息情况盘点失败：");
				}else{
					System.out.println("执行上架位置不匹配信息情况盘点成功！");
				}
			}
			
			//5:档案装盒不匹配盘点
			if (pFlag) {
				pErrPos = 6;
				if (stockReportArchivesBoxNotMatchDao.executeStocktakingByStocktakingID(stocktakingID, pErrInfo)==false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"执行档案装盒不匹配信息情况盘点失败：");
				}else{
					System.out.println("执行档案装盒不匹配信息情况盘点成功！");
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

	
////////////////////////   findAllStockReport   /////////////////////////////////
	@Override
	public boolean findStockReportAddressNotMatch(int stocktakingID, List<StockReportAddressNotMatch> stockReportAddressNotMatchs, ErrInfo pErrInfo) {
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
				if (stockReportAddressNotMatchDao.findAll(stocktakingID, stockReportAddressNotMatchs, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查找所有的上架位置不匹配信息失败: ");
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
	public boolean findStockReportArchivesBoxNotMatch(int stocktakingID, List<StockReportArchivesBoxNotMatch> stockReportArchivesBoxNotMatchs, ErrInfo pErrInfo) {
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
				if (stockReportArchivesBoxNotMatchDao.findAll(stocktakingID,stockReportArchivesBoxNotMatchs, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查找所有的档案装盒不匹配信息失败: ");
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
	public boolean findStockReportArchivesCount(int stocktakingID, List<StockReportArchivesCount> stockReportArchivesCounts, ErrInfo pErrInfo) {
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
				if (stockReportArchivesCountDao.findAll(stocktakingID,stockReportArchivesCounts, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查找所有的库房盘点档案数量信息失败: ");
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
	public boolean findStockReportPaperNotExist(int stocktakingID, List<StockReportPaperNotExist> stockReportPaperNotExists, ErrInfo pErrInfo) {
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
				if (stockReportPaperNotExistDao.findAll(stocktakingID,stockReportPaperNotExists, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查找所有的实物档案不在架信息失败: ");
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
	public boolean findStockReportSystemNotExist(int stocktakingID, List<StockReportSystemNotExist> stockReportSystemNotExists, ErrInfo pErrInfo) {
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
				if (stockReportSystemNotExistDao.findAll(stocktakingID, stockReportSystemNotExists, pErrInfo)== false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查找所有的系统中不在架档案信息失败: ");
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
