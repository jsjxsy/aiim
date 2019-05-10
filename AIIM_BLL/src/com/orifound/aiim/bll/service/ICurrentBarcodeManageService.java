package com.orifound.aiim.bll.service;
import com.orifound.aiim.entity.CurrentBarcode;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 条形码信息管理服务的接口定义
 *
 */
public interface ICurrentBarcodeManageService {

	
	/**
	 * 修改指定的条形码信息
	 * @param currentBarcode 修改后的条形码信息信息，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateCurrentBarcode(CurrentBarcode currentBarcode, ErrInfo pErrInfo);

	
	/**
	 * 根据唯一标识查找条形码信息信息
	 * @param pID 指定的唯一标识
	 * @param currentBarcode 返回查找成功的条形码信息信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findCurrentBarcodeByBarcodeType(CurrentBarcode currentBarcode,
			ErrInfo pErrInfo);
	
	/**
	 * 打印指定类型和数量的条形码
	 * @param pID 指定的唯一标识
	 * @param currentBarcode 返回查找成功的条形码信息信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean printBarcode(int num,CurrentBarcode currentBarcode,
			ErrInfo pErrInfo);

}
