/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.StoreroomArchivesInfo;
import com.orifound.aiim.entity.StoreroomStructure;

/**
 * 库房管理服务的接口定义
 *
 */
public interface IStoreroomManageService {
		
	
	/**
	 * 查找所有的库房信息
	 * @param storeRooms 返回查找成功的库房信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findStorerooms(List<StoreroomStructure> storeRooms,ErrInfo pErrInfo);
	
	/**
	 * 根据库房结构编号查找其下级库房结构<br>
	 * 注意，该方法仅仅查找下一级库房结构，下下级的不会进行查询
	 * @param storeroomStructureID 指定的库房结构编号
	 * @param children 返回查找成功的下级库房结构
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findStoreroomStructureChildrenByID(int storeroomStructureID,List<StoreroomStructure> children,ErrInfo pErrInfo);
	
	/**
	 * 根据库房结构编号查找库房结构详细信息<br>
	 * 注意，该方法仅仅查找指定编号的库房结构信息（例如名称、条码等），其下级库房结构不会进行查询
	 * @param storeroomStructureID 指定的库房结构编号
	 * @param storeroomStructure 返回查找成功的库房结构信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findStoreroomStructureByID(int storeroomStructureID,StoreroomStructure storeroomStructure,ErrInfo pErrInfo);
	
	
	/**
	 * 更新库房结构信息<br>
	 * @param storeroomStructure 要更新的库房结构信息，其编号属性必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateStoreroomStructure(StoreroomStructure storeroomStructure,ErrInfo pErrInfo);
	
	/**
	 * 更新库房设备的已用空间
	 * @param storeAddressBarcode 设备条形码
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateStoreroomStructureUsedSizeByBarcode(String storeAddressBarcode,ErrInfo pErrInfo); 

	
	/**
	 * 登记实物档案在库房中的上架位置
	 * @param archivesBoxBarcode 档案盒的条形码
	 * @param storeAddressBarcode 存放位置的条形码
	 * @param storeAddressInfo 返回登记成功后的档案上架位置信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean registerStoreAddress(String archivesBoxBarcode,String storeAddressBarcode,ErrInfo pErrInfo);
	
	/**
	 * 位置与状态查询<br>
	 * 查找满足指定条件的库房档案信息，包括其上架位置与库房状态信息
	 * @param storeroomArchivesInfoQueryCondition 查询条件
	 * @param storeroomArchivesInfos 返回查找成功的库房档案信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findStoreroomArchivesInfosByCondition(String whereSQL,List<StoreroomArchivesInfo> storeroomArchivesInfos,ErrInfo pErrInfo);

}



