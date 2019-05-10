/**
 * 
 */
package com.orifound.aiim.dal.dao;


import com.orifound.aiim.entity.CurrentBarcode;
import com.orifound.aiim.entity.ErrInfo;

/**
 * CurrentBarcode表的DAO接口定义
 *
 */
public interface ICurrentBarcodeDao {


	/**
	 * Dao接口定义：更新指定的条形码信息
	 * @param pEntity 要更新的条形码信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(CurrentBarcode currentBarcode, ErrInfo pErrInfo);

	
	/**
	 * Dao接口定义：插入指定类型的条形码信息
	 * @param pEntity 要更新的条形码信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(CurrentBarcode currentBarcode, ErrInfo pErrInfo);
	
	
	/**
	 * Dao接口定义：根据条码类型查找条形码信息
	 * @param barCodeType 指定的唯一标识
	 * @param currentBarcode 返回查找成功的条形码信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByBarcodeType(CurrentBarcode currentBarcode, ErrInfo pErrInfo);

}
