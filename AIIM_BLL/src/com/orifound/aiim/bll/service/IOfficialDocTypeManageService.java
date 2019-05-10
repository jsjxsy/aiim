/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.LinkedHashMap;

import com.orifound.aiim.entity.OfficialDocType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 公文文种信息管理服务的接口定义
 *
 */
public interface IOfficialDocTypeManageService
{

	/**
	 * 添加一个新的公文文种信息
	 * @param officialDocType 新添加的公文文种信息信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveOfficialDocType(OfficialDocType officialDocType, ErrInfo pErrInfo);

	/**
	 * 删除指定的公文文种信息
	 * @param officialDocType 要删除的公文文种信息，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteOfficialDocType(OfficialDocType officialDocType, ErrInfo pErrInfo);

	/**
	 * 修改指定的公文文种信息
	 * @param officialDocType 修改后的公文文种信息信息，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateOfficialDocType(OfficialDocType officialDocType, ErrInfo pErrInfo);

	/**
	 * 查找所有的公文文种信息信息
	 * @param officialDocTypes 返回查找成功的公文文种信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findOfficialDocTypes(LinkedHashMap<Integer,OfficialDocType> officialDocTypes, ErrInfo pErrInfo);

	/**
	 * 根据唯一标识查找公文文种信息信息
	 * @param pID 指定的唯一标识
	 * @param officialDocType 返回查找成功的公文文种信息信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findOfficialDocTypeByID(int pID, OfficialDocType officialDocType, ErrInfo pErrInfo);

}
