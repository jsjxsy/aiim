package com.orifound.aiim.dal.dao;

import java.util.Map;

import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.ImportDataitemsMapping;

public interface IImportPersonnelArchivesDataitemsMappingDao {

	/**
	 * ���ݵ������Ͳ���ӳ���ϵ
	 * @param importDataitemsMappings ���ص�ӳ���ϵ
	 * @param importType ��������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByImportType(Map<String,ImportDataitemsMapping> importDataitemsMappings, int importType, ErrInfo pErrInfo);

}
