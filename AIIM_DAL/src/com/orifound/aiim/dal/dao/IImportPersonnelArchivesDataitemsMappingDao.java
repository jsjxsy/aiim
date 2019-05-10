package com.orifound.aiim.dal.dao;

import java.util.Map;

import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.ImportDataitemsMapping;

public interface IImportPersonnelArchivesDataitemsMappingDao {

	/**
	 * 根据导入类型查找映射关系
	 * @param importDataitemsMappings 返回的映射关系
	 * @param importType 导入类型
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByImportType(Map<String,ImportDataitemsMapping> importDataitemsMappings, int importType, ErrInfo pErrInfo);

}
