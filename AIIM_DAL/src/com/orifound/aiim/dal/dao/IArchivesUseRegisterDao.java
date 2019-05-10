package com.orifound.aiim.dal.dao;
	import java.util.List;
	import com.orifound.aiim.entity.ArchivesUseRegister;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;

	/**
	 * 档案利用登记表的DAO接口定义
	 *
	 */
	public interface IArchivesUseRegisterDao {

		/**
		 * Dao接口定义：添加档案利用登记信息
		 * @param  archivesUseRegister要添加的档案利用登记信息
		 * @param pErrInfo 返回处理失败的错误信息
		 * @return 处理成功返回true，否则返回false
		 */
		boolean add(ArchivesUseRegister archivesUseRegister, ErrInfo pErrInfo);

		/**
		 * Dao接口定义：删除指定的档案利用登记信息
		 * @param  archivesUseRegister要删除的档案利用登记信息 ，其ID必须赋值
		 * @param pErrInfo 返回处理失败的错误信息
		 * @return 处理成功返回true，否则返回false
		 */
		boolean delete(ArchivesUseRegister archivesUseRegister, ErrInfo pErrInfo);

		/**
		 * Dao接口定义：更新指定的档案利用登记信息
		 * @param  archivesUseRegister要更新的档案利用登记信息
		 * @param pErrInfo 返回处理失败的错误信息
		 * @return 处理成功返回true，否则返回false
		 */
		boolean update(ArchivesUseRegister archivesUseRegister, ErrInfo pErrInfo);

		/**
		 * Dao接口定义：根据条件查询档案利用登记信息
		 * @param querySQL 用于查询SQL条件，以AND开头
		 * @param dataPageInfo 数据分页显示信息
		 * @param archivesUseRegisters 返回查找成功的档案利用登记信息集合
		 * @param pErrInfo 返回处理失败的错误信息
		 * @return 处理成功返回true，否则返回false
		 */
		boolean findByConditions(String querySQL,DataPageInfo dataPageInfo,List<ArchivesUseRegister> archivesUseRegisters, ErrInfo pErrInfo);

		/**
		 * Dao接口定义：根据唯一标识查找档案利用登记信息		
		 * @param  archivesUseRegister 返回查找成功的档案利用登记信息，其ID必须赋值
		 * @param pErrInfo 返回处理失败的错误信息
		 * @return 处理成功返回true，否则返回false
		 */
		boolean findByID( ArchivesUseRegister archivesUseRegister, ErrInfo pErrInfo);
		
		/**
		 * Dao接口定义：查询所有过期利用登记信息<br>其中包含用户信息
		 * @param archivesUseRegisters 
		 * @param pErrInfo
		 * @return
		 */
		boolean findAllExpiredRegister(DataPageInfo dataPageInfo, List<ArchivesUseRegister> archivesUseRegisters, ErrInfo pErrInfo);

		/**
		 * Dao接口定义：查询所有将要到期利用登记信息<br>其中包含用户信息
		 * @param daysNum 到期预警要提前的天数
		 * @param dataPageInfo
		 * @param archivesUseRegisters
		 * @param pErrInfo
		 * @return
		 */
		boolean findAllExpiringRegister(int daysNum,DataPageInfo dataPageInfo, List<ArchivesUseRegister> archivesUseRegisters, ErrInfo pErrInfo);

	}
