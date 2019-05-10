/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.OfficialDocType;
import com.orifound.aiim.entity.OfficialTemplate;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 公文模板表的DAO接口定义
 *
 */
public interface IOfficialTemplateDao {

	/**
	 * Dao接口定义：添加模板
	 * @param officialTemplate 要添加的模板
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(OfficialTemplate officialTemplate, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的模板
	 * @param officialTemplate 要删除的模板
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(OfficialTemplate officialTemplate, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的模板
	 * @param officialTemplate 要更新的模板
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(OfficialTemplate officialTemplate, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的模板
	 * @param officialTemplates 返回查找成功的模板集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(OfficialDocType officialDocType,List<OfficialTemplate> officialTemplates, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找模板
	 * @param pID 指定的唯一标识
	 * @param officialTemplate 返回查找成功的模板
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, OfficialTemplate officialTemplate, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找模板
	 * @param pID 指定的唯一标识
	 * @param officialTemplate 返回查找成功的模板
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByName(OfficialDocType officialDocType,String title,List<OfficialTemplate> officialTemplates, ErrInfo pErrInfo);
}
