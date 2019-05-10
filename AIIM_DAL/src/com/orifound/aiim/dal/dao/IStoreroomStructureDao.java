	package com.orifound.aiim.dal.dao;

	import java.util.List;



	import com.orifound.aiim.entity.StoreroomStructure;
	import com.orifound.aiim.entity.ErrInfo;

	/**
	 * 库房结构信息表的DAO接口定义
	 *
	 */
	public interface IStoreroomStructureDao {

		/**
		 * Dao接口定义：添加库房结构信息
		 * @param storeroomStructure 要添加的库房结构信息
		 * @param pErrInfo 返回处理失败的错误信息
		 * @return 处理成功返回true，否则返回false
		 */
		boolean addStoreroomStructure(StoreroomStructure storeroomStructure, ErrInfo pErrInfo);
		
		/**
		 * Dao接口定义：更新库房结构信息<br>
		 * @param storeroomStructure 要更新的库房结构信息，其编号属性必须赋值
		 * @param pErrInfo 返回处理失败的错误信息
		 * @return 处理成功返回true，否则返回false
		 */
		boolean updateStoreroomStructure(StoreroomStructure storeroomStructure,ErrInfo pErrInfo);
		
		/**
		 * 通过条形码更新最底层设备的已用空间
		 * @param storeAddressBarcode
		 * @param pErrInfo
		 * @return
		 */
		boolean updateUsedSizeByBarcode(String storeAddressBarcode,ErrInfo pErrInfo);
				
		/**
		 * 通过设备编号更新已用空间
		 * @param storeAddressID
		 * @param pErrInfo
		 * @return
		 */
		boolean updateUsedSizeByID(int storeAddressID,ErrInfo pErrInfo );
		
		/**
		 * Dao接口定义：查找所有的库房信息
		 * @param storeRooms 返回查找成功的库房信息
		 * @param pErrInfo 返回处理失败的错误信息
		 * @return 处理成功返回true，否则返回false
		 */
		boolean findStorerooms(List<StoreroomStructure> storeRooms,ErrInfo pErrInfo);

		/**
		 * Dao接口定义：根据库房设备条形码查找库房结构详细信息<br>
		 * 注意，该方法仅仅查找指定编号的库房结构信息（例如名称、条码等），其下级库房结构不会进行查询
		 * @param storeroomStructure 返回查找成功的库房结构信息,条形码必须赋值
		 * @param pErrInfo 返回处理失败的错误信息
		 * @return 处理成功返回true，否则返回false
		 */
		boolean findByBarcode(StoreroomStructure storeroomStructure,ErrInfo pErrInfo);
		
		
		
		/**
		 * Dao接口定义：根据库房结构编号查找库房结构详细信息<br>
		 * 注意，该方法仅仅查找指定编号的库房结构信息（例如名称、条码等），其下级库房结构不会进行查询
		 * @param storeroomStructure 返回查找成功的库房结构信息,ID必须赋值
		 * @param pErrInfo 返回处理失败的错误信息
		 * @return 处理成功返回true，否则返回false
		 */
		boolean findByID(StoreroomStructure storeroomStructure,ErrInfo pErrInfo);
		

		/**
		 * 根据库房结构编号查找其下级库房结构<br>
		 * 注意，该方法仅仅查找下一级库房结构，下下级的不会进行查询
		 * @param storeroomStructureID 指定的库房结构编号
		 * @param children 返回查找成功的下级库房结构
		 * @param pErrInfo 返回处理失败的错误信息
		 * @return 处理成功返回true，否则返回false
		 */
		boolean findChildrenByID(int storeroomStructureID,List<StoreroomStructure> children,ErrInfo pErrInfo);
		
		
		

	}

