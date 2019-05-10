/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.OfficialDocType;
import com.orifound.aiim.entity.OfficialTemplate;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 公文模板管理服务的接口定义
 *
 */
public interface IOfficialTemplateManageService {

	/**
	 * 添加一个新的公文模板
	 * @param officialTemplate 新添加的公文模板信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveOfficialTemplate(OfficialTemplate officialTemplate, ErrInfo pErrInfo);

	/**
	 * 删除指定的公文模板
	 * @param officialTemplate 要删除的公文模板，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteOfficialTemplate(OfficialTemplate officialTemplate, ErrInfo pErrInfo);

	/**
	 * 修改指定的公文模板
	 * @param officialTemplate 修改后的公文模板信息，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateOfficialTemplate(OfficialTemplate officialTemplate, ErrInfo pErrInfo);

	/**
	 * 查找所有的公文模板信息
	 * @param officialTemplates 返回查找成功的公文模板集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findOfficialTemplates(OfficialDocType officialDocType,List<OfficialTemplate> officialTemplates, ErrInfo pErrInfo);

	/**
	 * 根据唯一标识查找公文模板信息
	 * @param pID 指定的唯一标识
	 * @param officialTemplate 返回查找成功的公文模板信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findOfficialTemplateByID(int pID, OfficialTemplate officialTemplate, ErrInfo pErrInfo);
	
	/**
	 * 根据唯一标识查找公文模板信息
	 * @param pID 指定的唯一标识
	 * @param officialTemplate 返回查找成功的公文模板信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findOfficialTemplateByName(OfficialDocType officialDocType,String title,List<OfficialTemplate> officialTemplates, ErrInfo pErrInfo);

}
