/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.IArchivesFondsManageService;
import com.orifound.aiim.bll.service.IArchivesInfoTableManageService;
import com.orifound.aiim.bll.service.IArchivesSecrecyManageService;
import com.orifound.aiim.bll.service.IArchivesTypeDataItemManageService;
import com.orifound.aiim.bll.service.IArchivesTypeManageService;
import com.orifound.aiim.bll.service.ICatalogDataItemManageService;
import com.orifound.aiim.bll.service.ICheckRuleManageService;
import com.orifound.aiim.bll.service.IConfigManageService;
import com.orifound.aiim.bll.service.IDataSourceItemManageService;
import com.orifound.aiim.bll.service.IDataSourceManageService;
import com.orifound.aiim.bll.service.IDepartmentInfoManageService;
import com.orifound.aiim.bll.service.IDutyManageService;
import com.orifound.aiim.bll.service.IEvaluateItemManageService;
import com.orifound.aiim.bll.service.IEvaluateLevelManageService;
import com.orifound.aiim.bll.service.IOfficialArchivesDataItemSavedMappingManageService;
import com.orifound.aiim.bll.service.IOfficialArchivesInfoTableManageService;
import com.orifound.aiim.bll.service.IOfficialArchivesTypeManageService;
import com.orifound.aiim.bll.service.IOfficialArchivesTypeSavedMappingManageService;
import com.orifound.aiim.bll.service.IOfficialDocTypeManageService;
import com.orifound.aiim.bll.service.IRetentionPeriodManageService;
import com.orifound.aiim.bll.service.ISystemInitializeService;
import com.orifound.aiim.bll.service.IUserRoleManageService;
import com.orifound.aiim.bll.util.CommonUtil;
import com.orifound.aiim.bll.util.StringTool;
import com.orifound.aiim.entity.ArchivesFonds;
import com.orifound.aiim.entity.ArchivesInfoTable;
import com.orifound.aiim.entity.ArchivesSecrecy;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ArchivesTypeDataItem;
import com.orifound.aiim.entity.ArchivesTypeDataItemComparator;
import com.orifound.aiim.entity.CheckRule;
import com.orifound.aiim.entity.Config;
import com.orifound.aiim.entity.DataSource;
import com.orifound.aiim.entity.DataSourceItem;
import com.orifound.aiim.entity.DepartmentInfo;
import com.orifound.aiim.entity.Duty;
import com.orifound.aiim.entity.EnumArchivesInfoTableType;
import com.orifound.aiim.entity.EnumArchivesTypeDataItemComparatorType;
import com.orifound.aiim.entity.EnumDataSourceInherentType;
import com.orifound.aiim.entity.EnumOfficialArchivesInfoTableType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.EvaluateItem;
import com.orifound.aiim.entity.EvaluateLevel;
import com.orifound.aiim.entity.OfficialArchivesDataItemSavedMapping;
import com.orifound.aiim.entity.OfficialArchivesInfoTable;
import com.orifound.aiim.entity.OfficialArchivesType;
import com.orifound.aiim.entity.OfficialArchivesTypeSavedMapping;
import com.orifound.aiim.entity.OfficialDocType;
import com.orifound.aiim.entity.RetentionPeriod;
import com.orifound.aiim.entity.SystemInitializer;
import com.orifound.aiim.entity.UserRole;

/**
 * ϵͳ��ʼ������ʵ����
 * 
 */
public class SystemInitializeServiceImpl implements ISystemInitializeService
{

	/**
	 * ����ȫ�ڹ���������
	 */
	private IArchivesFondsManageService archivesFondsManageService = null;

	/**
	 * ��������ֵ������ȫ�ڹ���������
	 * 
	 * @param archivesFondsManageService
	 *            ����ȫ�ڹ���������
	 */
	public void setArchivesFondsManageService(IArchivesFondsManageService archivesFondsManageService)
	{
		this.archivesFondsManageService = archivesFondsManageService;
	}

	/**
	 * ��ȡ����ֵ������ȫ�ڹ���������
	 * 
	 * @return ����ȫ�ڹ���������
	 */
	public IArchivesFondsManageService getArchivesFondsManageService()
	{
		return archivesFondsManageService;
	}

	/**
	 * ���������Ϣ�����������
	 */
	private IArchivesInfoTableManageService archivesInfoTableManageService = null;

	/**
	 * ��������ֵ�����������Ϣ�����������
	 * 
	 * @param archivesInfoTableManageService
	 *            ���������Ϣ�����������
	 */
	public void setArchivesInfoTableManageService(IArchivesInfoTableManageService archivesInfoTableManageService)
	{
		this.archivesInfoTableManageService = archivesInfoTableManageService;
	}

	/**
	 * ��ȡ����ֵ�����������Ϣ�����������
	 * 
	 * @return ���������Ϣ�����������
	 */
	public IArchivesInfoTableManageService getArchivesInfoTableManageService()
	{
		return archivesInfoTableManageService;
	}

	/**
	 * �����������������
	 */
	private IArchivesTypeManageService archivesTypeManageService = null;

	/**
	 * ��������ֵ�������������������
	 * 
	 * @param archivesTypeManageService
	 *            �����������������
	 */
	public void setArchivesTypeManageService(IArchivesTypeManageService archivesTypeManageService)
	{
		this.archivesTypeManageService = archivesTypeManageService;
	}

	/**
	 * ��ȡ����ֵ�������������������
	 * 
	 * @return �����������������
	 */
	public IArchivesTypeManageService getArchivesTypeManageService()
	{
		return archivesTypeManageService;
	}

	/**
	 * �����������������������
	 */
	private IArchivesTypeDataItemManageService archivesTypeDataItemManageService = null;

	/**
	 * ��������ֵ�������������������������
	 * 
	 * @param archivesTypeDataItemManageService
	 *            �����������������������
	 */
	public void setArchivesTypeDataItemManageService(IArchivesTypeDataItemManageService archivesTypeDataItemManageService)
	{
		this.archivesTypeDataItemManageService = archivesTypeDataItemManageService;
	}

	/**
	 * ��ȡ����ֵ�������������������������
	 * 
	 * @return �����������������������
	 */
	public IArchivesTypeDataItemManageService getArchivesTypeDataItemManageService()
	{
		return archivesTypeDataItemManageService;
	}

	/**
	 * ����Դ����������
	 */
	private IDataSourceManageService dataSourceManageService = null;

	/**
	 * ��������ֵ������Դ����������
	 * 
	 * @param dataSourceManageService
	 *            ����Դ����������
	 */
	public void setDataSourceManageService(IDataSourceManageService dataSourceManageService)
	{
		this.dataSourceManageService = dataSourceManageService;
	}

	/**
	 * ��ȡ����ֵ������Դ����������
	 * 
	 * @return ����Դ����������
	 */
	public IDataSourceManageService getDataSourceManageService()
	{
		return dataSourceManageService;
	}

	/**
	 * ����Դ�����������������
	 */
	private IDataSourceItemManageService dataSourceItemManageService = null;

	/**
	 * ��������ֵ������Դ�����������������
	 * 
	 * @param dataSourceItemManageService
	 *            ����Դ�����������������
	 */
	public void setDataSourceItemManageService(IDataSourceItemManageService dataSourceItemManageService)
	{
		this.dataSourceItemManageService = dataSourceItemManageService;
	}

	/**
	 * ��ȡ����ֵ������Դ�����������������
	 * 
	 * @return ����Դ�����������������
	 */
	public IDataSourceItemManageService getDataSourceItemManageService()
	{
		return dataSourceItemManageService;
	}

	/**
	 * У��������������
	 */
	private ICheckRuleManageService checkRuleManageService = null;

	/**
	 * ��������ֵ��У��������������
	 * 
	 * @param checkRuleManageService
	 *            У��������������
	 */
	public void setCheckRuleManageService(ICheckRuleManageService checkRuleManageService)
	{
		this.checkRuleManageService = checkRuleManageService;
	}

	/**
	 * ��ȡ����ֵ��У��������������
	 * 
	 * @return У��������������
	 */
	public ICheckRuleManageService getCheckRuleManageService()
	{
		return checkRuleManageService;
	}

	/**
	 * �������޹���������
	 */
	private IRetentionPeriodManageService retentionPeriodManageService = null;

	/**
	 * ��������ֵ���������޹���������
	 * 
	 * @param retentionPeriodManageService
	 *            �������޹���������
	 */
	public void setRetentionPeriodManageService(IRetentionPeriodManageService retentionPeriodManageService)
	{
		this.retentionPeriodManageService = retentionPeriodManageService;
	}

	/**
	 * ��ȡ����ֵ���������޹���������
	 * 
	 * @return �������޹���������
	 */
	public IRetentionPeriodManageService getRetentionPeriodManageService()
	{
		return retentionPeriodManageService;
	}

	/**
	 * �����ܼ�����������
	 */
	private IArchivesSecrecyManageService archivesSecrecyManageService = null;

	/**
	 * ��������ֵ�������ܼ�����������
	 * 
	 * @param archivesSecrecyManageService
	 *            �����ܼ�����������
	 */
	public void setArchivesSecrecyManageService(IArchivesSecrecyManageService archivesSecrecyManageService)
	{
		this.archivesSecrecyManageService = archivesSecrecyManageService;
	}

	/**
	 * ��ȡ����ֵ�������ܼ�����������
	 * 
	 * @return �����ܼ�����������
	 */
	public IArchivesSecrecyManageService getArchivesSecrecyManageService()
	{
		return archivesSecrecyManageService;
	}

	/**
	 * ������Ϣ����������
	 */
	private IDepartmentInfoManageService departmentInfoManageService = null;

	/**
	 * ��������ֵ��������Ϣ����������
	 * 
	 * @param departmentInfoManageService
	 *            ������Ϣ����������
	 */
	public void setDepartmentInfoManageService(IDepartmentInfoManageService departmentInfoManageService)
	{
		this.departmentInfoManageService = departmentInfoManageService;
	}

	/**
	 * ��ȡ����ֵ��������Ϣ����������
	 * 
	 * @return ������Ϣ����������
	 */
	public IDepartmentInfoManageService getDepartmentInfoManageService()
	{
		return departmentInfoManageService;
	}

	/**
	 * ϵͳ������������
	 */
	@Autowired
	private IConfigManageService configManageService;
	
	/**
	 * ��ȡ����ֵ��ϵͳ������������
	 * 
	 * @return ϵͳ������������
	 */
	public IConfigManageService getConfigManageService()
	{
		return configManageService;
	}

	/**
	 * ��������ֵ��ϵͳ������������
	 * 
	 * @param configManageService
	 *            ϵͳ������������
	 */
	public void setConfigManageService(IConfigManageService configManageService)
	{
		this.configManageService = configManageService;
	}
	
	/**
	 * ����������Ϣ����������
	 */
	private IOfficialDocTypeManageService officialDocTypeManageService = null;

	/**
	 * ��������ֵ������������Ϣ����������
	 * @param officialDocTypeManageService ����������Ϣ����������
	 */
	public void setOfficialDocTypeManageService(IOfficialDocTypeManageService officialDocTypeManageService)
	{
		this.officialDocTypeManageService = officialDocTypeManageService;
	}

	/**
	 * ��ȡ����ֵ������������Ϣ����������
	 * @return ����������Ϣ����������
	 */
	public IOfficialDocTypeManageService getOfficialDocTypeManageService()
	{
		return officialDocTypeManageService;
	}
	
	/**
	 * ���ĵ���������Ϣ����������
	 */
	private IOfficialArchivesTypeManageService officialArchivesTypeManageService = null;

	/**
	 * ��������ֵ�����ĵ���������Ϣ����������
	 * @param officialArchivesTypeManageService ���ĵ���������Ϣ����������
	 */
	public void setOfficialArchivesTypeManageService(IOfficialArchivesTypeManageService officialArchivesTypeManageService)
	{
		this.officialArchivesTypeManageService = officialArchivesTypeManageService;
	}

	/**
	 * ��ȡ����ֵ�����ĵ���������Ϣ����������
	 * @return ���ĵ���������Ϣ����������
	 */
	public IOfficialArchivesTypeManageService getOfficialArchivesTypeManageService()
	{
		return officialArchivesTypeManageService;
	}

	/**
	 * ���ĵ�����Ϣ��ر����������
	 */
	private IOfficialArchivesInfoTableManageService officialArchivesInfoTableManageService = null;

	/**
	 * ��������ֵ�����ĵ�����Ϣ��ر����������
	 * @param officialArchivesInfoTableManageService ���ĵ�����Ϣ��ر����������
	 */
	public void setOfficialArchivesInfoTableManageService(IOfficialArchivesInfoTableManageService officialArchivesInfoTableManageService)
	{
		this.officialArchivesInfoTableManageService = officialArchivesInfoTableManageService;
	}

	/**
	 * ��ȡ����ֵ�����ĵ�����Ϣ��ر����������
	 * @return ���ĵ�����Ϣ��ر����������
	 */
	public IOfficialArchivesInfoTableManageService getOfficialArchivesInfoTableManageService()
	{
		return officialArchivesInfoTableManageService;
	}

	/**
	 * ������Ŀ�ֵ������������
	 */
	private IEvaluateItemManageService evaluateItemManageService = null;
	
	/**
	 * ��ȡ����ֵ��������Ŀ�ֵ���������
	 * @return ������Ŀ���������
	 */
	public IEvaluateItemManageService getEvaluateItemManageService() {
		return evaluateItemManageService;
	}
	/**
	 * ��������ֵ��������Ŀ�ֵ���������
	 * @param EvaluateItemManageService ������Ŀ���������
	 */
	public void setEvaluateItemManageService(
			IEvaluateItemManageService evaluateItemManageService) {
		this.evaluateItemManageService = evaluateItemManageService;
	}

	/**
	 * ���˵ȼ��ֵ�����������
	 */
	private IEvaluateLevelManageService evaluateLevelManageService = null;

	/**
	 * ��ȡ����ֵ�����˵ȼ��ֵ�� ����������
	 * @return ���˵ȼ��ֵ�� ����������
	 */
	public IEvaluateLevelManageService getEvaluateLevelManageService() {
		return evaluateLevelManageService;
	}
	
	/**
	 * ��������ֵ�����˵ȼ��ֵ�� ����������
	 * @param evaluateLeevlManageService ���˵ȼ��ֵ�� ����������
	 */
	public void setEvaluateLevelManageService(IEvaluateLevelManageService evaluateLevelManageService) {
		this.evaluateLevelManageService = evaluateLevelManageService;
	}
	
	/**
	 * ְ����Ϣ�����ֵ�������
	 */
	private IDutyManageService dutyManageService = null;

	/**
	 * ��������ֵ��ְ����Ϣ�����ֵ������
	 * @param dutyManageService ְ����Ϣ�����ֵ������
	 */
	public void setDutyManageService(IDutyManageService dutyManageService) {
		this.dutyManageService = dutyManageService;
	}

	/**
	 * ��ȡ����ֵ��ְ����Ϣ�����ֵ������
	 * @return ְ����Ϣ�����ֵ������
	 */
	public IDutyManageService getDutyManageService() {
		return dutyManageService;
	}

	/**
	 * ���ĵ�������Ĺ鵵ӳ���ϵ��������
	 */
	private IOfficialArchivesTypeSavedMappingManageService officialArchivesTypeSavedMappingManageService;

	/**
	 * ��ȡ���ĵ�������Ĺ鵵ӳ���ϵ��������
	 * @return
	 */
	public IOfficialArchivesTypeSavedMappingManageService getOfficialArchivesTypeSavedMappingManageService() {
		return officialArchivesTypeSavedMappingManageService;
	}
	
	/**
	 * ���ù��ĵ�������Ĺ鵵ӳ���ϵ��������
	 * @param archivesTypeSavedMappingManageService
	 */
	public void setOfficialArchivesTypeSavedMappingManageService(IOfficialArchivesTypeSavedMappingManageService officialArchivesTypeSavedMappingManageService) {
		this.officialArchivesTypeSavedMappingManageService = officialArchivesTypeSavedMappingManageService;
	}
	
	/**
	 * ���ĵ���������鵵ӳ�����������
	 */
	private IOfficialArchivesDataItemSavedMappingManageService officialArchivesDataItemSavedMappingManageService;

	/**
	 * ��ȡ���ĵ���������鵵ӳ�����������
	 * @return
	 */
	public IOfficialArchivesDataItemSavedMappingManageService getOfficialArchivesDataItemSavedMappingManageService() {
		return officialArchivesDataItemSavedMappingManageService;
	}
	
	/**
	 * ���ù��ĵ���������鵵ӳ�����������
	 * @param officialArchivesDataItemSavedMappingManageService
	 */
	public void setOfficialArchivesDataItemSavedMappingManageService(IOfficialArchivesDataItemSavedMappingManageService officialArchivesDataItemSavedMappingManageService) {
		this.officialArchivesDataItemSavedMappingManageService = officialArchivesDataItemSavedMappingManageService;
	}

	/**
	 * �û���ɫ��Ϣ�ֵ�������
	 */
	private IUserRoleManageService userRoleManageService = null;

	/**
	 * ��������ֵ���û���ɫ��Ϣ�ֵ�������
	 * @param userRoleManageService �û���ɫ��Ϣ�ֵ�������
	 */
	public void setUserRoleManageService(IUserRoleManageService userRoleManageService) {
		this.userRoleManageService = userRoleManageService;
	}

	/**
	 * ��ȡ����ֵ���û���ɫ��Ϣ�ֵ�������
	 * @return �û���ɫ��Ϣ�ֵ�������
	 */
	public IUserRoleManageService getUserRoleManageService() {
		return userRoleManageService;
	}
	
	/**
	 * Ŀ¼��ӡģ������������������
	 */
	private ICatalogDataItemManageService catalogDataItemManageService = null;

	/**
	 * ��������ֵ��Ŀ¼��ӡģ������������������
	 * @param catalogDataItemManageService Ŀ¼��ӡģ������������������
	 */
	public void setCatalogDataItemManageService(ICatalogDataItemManageService catalogDataItemManageService)
	{
		this.catalogDataItemManageService = catalogDataItemManageService;
	}

	/**
	 * ��ȡ����ֵ��Ŀ¼��ӡģ������������������
	 * @return Ŀ¼��ӡģ������������������
	 */
	public ICatalogDataItemManageService getCatalogDataItemManageService()
	{
		return catalogDataItemManageService;
	}

	
	
	/**
	 * �����ص�ҵ���߼���������ע�루BLL Depandency Injection��
	 * 
	 * @param pErrInfo
	 *            ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkBllInjection(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			// ����Ƿ��н��е���ȫ��ҵ���߼����������ע��
			pErrPos = 1;
			if (archivesFondsManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("����ȫ�ڵ�ManageService����ҵ���߼����󣩷Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}

			// ����Ƿ��н��е��������Ϣ���ҵ���߼����������ע��
			pErrPos = 2;
			if (archivesInfoTableManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("���������Ϣ���ManageService����ҵ���߼����󣩷Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}

			// ����Ƿ��н��е�������ҵ���߼����������ע��
			pErrPos = 3;
			if (archivesTypeManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("���������ManageService����ҵ���߼����󣩷Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}

			// ����Ƿ��н��е�������������ҵ���߼����������ע��
			pErrPos = 4;
			if (archivesTypeDataItemManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("���������������ManageService����ҵ���߼����󣩷Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}

			// ����Ƿ��н�������Դҵ���߼����������ע��
			pErrPos = 5;
			if (dataSourceManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("����Դ�ĵ�ManageService����ҵ���߼����󣩷Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}

			// ����Ƿ��н�������Դ��������ҵ���߼����������ע��
			pErrPos = 6;
			if (dataSourceItemManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("����Դ���������ManageService����ҵ���߼����󣩷Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}

			// ����Ƿ��н���У�����ҵ���߼����������ע��
			pErrPos = 7;
			if (checkRuleManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("У������ManageService����ҵ���߼����󣩷Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}

			// ����Ƿ��н��б�������ҵ���߼����������ע��
			pErrPos = 8;
			if (retentionPeriodManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("�������޵�ManageService����ҵ���߼����󣩷Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}

			// ����Ƿ��н��в�����Ϣҵ���߼����������ע��
			pErrPos = 9;
			if (departmentInfoManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("������Ϣ��ManageService����ҵ���߼����󣩷Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
			
			// ����Ƿ��н��в�����Ϣҵ���߼����������ע��
			pErrPos = 10;
			if (departmentInfoManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("������Ϣ��ManageService����ҵ���߼����󣩷Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
			
			// ����Ƿ��н���������Ϣҵ���߼����������ע��
			pErrPos = 11;
			if (configManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("������Ϣ��ManageService����ҵ���߼����󣩷Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
			
			// ����Ƿ��н��й���������Ϣҵ���߼����������ע��
			pErrPos = 12;
			if (officialDocTypeManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("����������Ϣ��ManageService����ҵ���߼����󣩷Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
			
			// ����Ƿ��н��й��ĵ���������Ϣҵ���߼����������ע��
			pErrPos = 13;
			if (officialArchivesTypeManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("���ĵ���������Ϣ��ManageService����ҵ���߼����󣩷Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
			
			// ����Ƿ��н��й��ĵ�����Ϣ��ر�ҵ���߼����������ע��
			pErrPos = 14;
			if (officialArchivesInfoTableManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("���ĵ�����Ϣ��ر��ManageService����ҵ���߼����󣩷Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
			
			// ����Ƿ��н��п�����Ŀ�ֵ�����������������ע��
			pErrPos = 15;
			if (evaluateItemManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("������Ŀ�ֵ���Ϣ��ر��ManageService����ҵ���߼����󣩷Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
			
			// ����Ƿ��н��п��˵ȼ��ֵ����������������ע��
			pErrPos = 16;
			if (evaluateLevelManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("���˵ȼ��ֵ���Ϣ��ر��ManageService����ҵ���߼����󣩷Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
			
			pErrPos = 17;
			//����Ƿ����ְ����Ϣ�����ֵ������������ע��
			if (dutyManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append("ְ����Ϣ�����ֵ���Ϣ��ر��ManageService����ҵ���߼����󣩷Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
			
			pErrPos = 18;
			//����Ƿ���й��ĵ������͹鵵ӳ�������������ע��
			if (officialArchivesTypeSavedMappingManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append("���ĵ������͹鵵ӳ����ManageService����ҵ���߼����󣩷Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
			
			pErrPos = 19;
			//����Ƿ���й��ĵ���������鵵ӳ����������������ע��
			if (officialArchivesDataItemSavedMappingManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append("���ĵ���������鵵ӳ����ManageService����ҵ���߼����󣩷Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
			
			pErrPos = 20;
			//����Ƿ�����û���ɫ��Ϣ�ֵ������������ע��
			if (userRoleManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append("�û���ɫ��Ϣ�ֵ���ManageService����ҵ���߼����󣩷Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
			
			pErrPos = 21;
			//����Ƿ�����û���ɫ��Ϣ�ֵ������������ע��
			if (catalogDataItemManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append("Ŀ¼��ӡģ����������ManageService����ҵ���߼����󣩷Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
			
			
		}
		catch (Exception e)
		{
			// �쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
		}

		return pFlag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.bll.service.ISystemInitializeService#initialize(com
	 * .orifound.aiim.entity.SystemInitializer,
	 * com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean initialize(SystemInitializer systemInitializer, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			// ����Ƿ��н������ҵ���߼����������ע��
			if (checkBllInjection(pErrInfo) == false)
			{
				pFlag = false;
			}

			// ��ʼ������ȫ����Ϣ
			if (pFlag)
			{
				pErrPos = 2;
				List<ArchivesFonds> archivesFondss = new ArrayList<ArchivesFonds>();
				if (archivesFondsManageService.findArchivesFondss(archivesFondss, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ʼ������ȫ����Ϣʧ��: ");
				}
				else
				{
					// �����ʼ���ĵ���ȫ����Ϣ
					systemInitializer.setArchivesFonds(archivesFondss);
				}
			}

			// ��ʼ��ʵ�嵵��������Ϣ�õ���״�ṹ�ĵ�������
			if (pFlag)
			{
				pErrPos = 3;
				LinkedHashMap<Integer, ArchivesType> archivesTypes = new LinkedHashMap<Integer, ArchivesType>();
				if (archivesTypeManageService.findArchivesTypes(archivesTypes, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ʼ������������Ϣʧ��: ");
				}
				else
				{
					// �����ʼ������״�ṹ�ĵ���������Ϣ
					systemInitializer.setArchivesTypes(archivesTypes);
				}
			}

			// ת����ȡƽ��ṹ��ʵ�嵵�����༯��
			if (pFlag)
			{
				pErrPos = 4;
				Map<Integer, ArchivesType> planeArchivesTypes = new HashMap<Integer, ArchivesType>();
				if (CommonUtil.convertTreeArchivesTypesToPlane(systemInitializer.getArchivesTypes(), planeArchivesTypes, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ʼ��ƽ��ṹ�ĵ���������Ϣʧ��: ");
				}
				else
				{
					// �����ʼ����ƽ��ṹ��ʵ�嵵��������Ϣ
					systemInitializer.setPlaneArchivesTypes(planeArchivesTypes);
				}
			}

			// ��ʼ��ÿ��ʵ�嵵���������ر���Ϣ�����磺�������鵵���������̱�ԭ����Ϣ��ȵȣ�
			if (pFlag)
			{
				pErrPos = 5;
				for (ArchivesType archivesType : systemInitializer.getArchivesTypes().values())
				{
					EnumMap<EnumArchivesInfoTableType, ArchivesInfoTable> archivesInfoTables = new EnumMap<EnumArchivesInfoTableType, ArchivesInfoTable>(
							EnumArchivesInfoTableType.class);
					if (archivesInfoTableManageService.findArchivesTypeTables(archivesType.getID(), archivesInfoTables, pErrInfo) == false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0, "��ʼ���������ࣨ" + archivesType.getFullCode() + "������ر���Ϣʧ��: ");
					}
					else
					{
						// ���浵���������ر���Ϣ
						archivesType.setArchivesInfoTables(archivesInfoTables);
					}
				}
			}

			// ��ʼ��ʵ�嵵�������е�ÿ��һ�������������������Ϣ
			if (pFlag)
			{
				pErrPos = 6;
				for (ArchivesType archivesType : systemInitializer.getArchivesTypes().values())
				{
					LinkedHashMap<String, ArchivesTypeDataItem> archivesTypeDataItems = new LinkedHashMap<String, ArchivesTypeDataItem>();
					if (archivesTypeDataItemManageService.findByArchivesTypeID(false,archivesType.getID(), archivesTypeDataItems, pErrInfo) == false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0, "��ʼ���������ࣨ" + archivesType.getFullCode() + "������������Ϣʧ��: ");
					}
					else
					{
						// ���浵���������������Ϣ
						archivesType.setDataItemsForAll(archivesTypeDataItems);

						// ����������������������ֱ�õ���¼��ϡ���¼��ѯ��ϡ����ò�ѯ��ϡ���ѯ����б���ʾ��ϣ��������Ͼ����ֶ�����Ϊ�ؼ���
						LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForInput=new LinkedHashMap<String, ArchivesTypeDataItem>();
						LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForInputQuery=new LinkedHashMap<String, ArchivesTypeDataItem>();
						LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForUseQuery=new LinkedHashMap<String, ArchivesTypeDataItem>();
						LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForListDisplay=new LinkedHashMap<String, ArchivesTypeDataItem>();
						if (splitArchivesTypeDataItems(archivesType.getDataItemsForAll(),dataItemsForInput,dataItemsForInputQuery,dataItemsForUseQuery,dataItemsForListDisplay, pErrInfo) == false)
						{
							pFlag = false;
							pErrInfo.getContent().insert(0, "��������������ࣨ" + archivesType.getFullCode() + "����������ʧ��: ");
						}
						else 
						{
							//���������
							archivesType.setDataItemsForInput(dataItemsForInput);
							archivesType.setDataItemsForInputQuery(dataItemsForInputQuery);
							archivesType.setDataItemsForUseQuery(dataItemsForUseQuery);
							archivesType.setDataItemsForListDisplay(dataItemsForListDisplay);
						}
					}

					// ����ʧ������������ѭ��
					if (pFlag == false)
					{
						break;
					}
				}
			}
			
			// ��ʼ��ʵ�嵵�������е�ÿ��һ�����������Ŀ¼��ӡģ����Ϣ����Ŀ¼��ӡģ�������������ϣ�
			if (pFlag)
			{
				pErrPos = 61;
				for (ArchivesType archivesType : systemInitializer.getArchivesTypes().values())
				{
					if (catalogDataItemManageService.findCatalogDataItems(archivesType, pErrInfo) == false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0, "��ʼ���������ࣨ" + archivesType.getFullCode() + "����Ŀ¼��ӡģ����������Ϣʧ��: ");
					}

					// ����ʧ������������ѭ��
					if (pFlag==false)
					{
						break;
					}
				}
			}
			
			// ��ʼ�����ĵ���������Ϣ
			if (pFlag)
			{
				pErrPos = 7;
				LinkedHashMap<Integer, OfficialArchivesType> officialArchivesTypes=new LinkedHashMap<Integer, OfficialArchivesType>();
				if (officialArchivesTypeManageService.findOfficialArchivesTypes(officialArchivesTypes, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ʼ�����ĵ���������Ϣʧ��: ");
				}
				else 
				{
					//���湫�ĵ���������Ϣ
					systemInitializer.setOfficialArchivesTypes(officialArchivesTypes);
				}
			}
			
			// ��ʼ��ÿ�����ĵ����������ر���Ϣ�����磺���ĵǼǱ�ԭ����Ϣ��
			if (pFlag)
			{
				pErrPos = 8;
				for (OfficialArchivesType officialArchivesType : systemInitializer.getOfficialArchivesTypes().values())
				{
					EnumMap<EnumOfficialArchivesInfoTableType, OfficialArchivesInfoTable> officialArchivesInfoTables=new EnumMap<EnumOfficialArchivesInfoTableType, OfficialArchivesInfoTable>(EnumOfficialArchivesInfoTableType.class);
			
					if (officialArchivesInfoTableManageService.findOfficialArchivesInfoTables(officialArchivesType.getID(), officialArchivesInfoTables, pErrInfo) == false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0, "��ʼ�����ĵ������ࣨ" + officialArchivesType.getName() + "������ر���Ϣʧ��: ");
					}
					else
					{
						// ���湫�ĵ��������Ӧ����ر���Ϣ
						officialArchivesType.setOfficialArchivesInfoTables(officialArchivesInfoTables);
					}
				}
			}
			
			// ��ʼ��ÿ�����ĵ����������������Ϣ
			if (pFlag)
			{
				pErrPos = 9;
				for (OfficialArchivesType officialArchivesType : systemInitializer.getOfficialArchivesTypes().values())
				{
					LinkedHashMap<String, ArchivesTypeDataItem> archivesTypeDataItems = new LinkedHashMap<String, ArchivesTypeDataItem>();
					if (archivesTypeDataItemManageService.findByArchivesTypeID(true,officialArchivesType.getID(), archivesTypeDataItems, pErrInfo) == false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0, "��ʼ�����ĵ������ࣨ" + officialArchivesType.getName() + "������������Ϣʧ��: ");
					}
					else
					{
						// ���湫�ĵ����������������Ϣ
						officialArchivesType.setDataItemsForAll(archivesTypeDataItems);

						// ����������ĵ��������������ֱ�õ���¼��ϡ���¼��ѯ��ϡ����ò�ѯ��ϡ���ѯ����б���ʾ��ϣ��������Ͼ����ֶ�����Ϊ�ؼ���
						LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForInput=new LinkedHashMap<String, ArchivesTypeDataItem>();
						LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForInputQuery=new LinkedHashMap<String, ArchivesTypeDataItem>();
						LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForUseQuery=new LinkedHashMap<String, ArchivesTypeDataItem>();
						LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForListDisplay=new LinkedHashMap<String, ArchivesTypeDataItem>();
						if (splitArchivesTypeDataItems(officialArchivesType.getDataItemsForAll(),dataItemsForInput,dataItemsForInputQuery,dataItemsForUseQuery,dataItemsForListDisplay, pErrInfo) == false)
						{
							pFlag = false;
							pErrInfo.getContent().insert(0, "����������ĵ������ࣨ" + officialArchivesType.getName() + "����������ʧ��: ");
						}
						else 
						{
							//���������
							officialArchivesType.setDataItemsForInput(dataItemsForInput);
							officialArchivesType.setDataItemsForInputQuery(dataItemsForInputQuery);
							officialArchivesType.setDataItemsForUseQuery(dataItemsForUseQuery);
							officialArchivesType.setDataItemsForListDisplay(dataItemsForListDisplay);
						}
					}

					// ����ʧ������������ѭ��
					if (pFlag == false)
					{
						break;
					}
				}
			}
			
			//��ʼ��ÿ�����ĵ�������鵵��ӳ���ϵ��
			if (pFlag) {
				pErrPos = 10;
				for (OfficialArchivesType officialArchivesType : systemInitializer.getOfficialArchivesTypes().values())
				{
					Map<Integer,OfficialArchivesTypeSavedMapping> officialArchivesTypeSavedMappings = new HashMap<Integer,OfficialArchivesTypeSavedMapping>();
					if (officialArchivesTypeSavedMappingManageService.findArchivesTypesByOfficialArchivesTypeID(officialArchivesType.getID(), officialArchivesTypeSavedMappings, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "��ʼ�����ĵ�������鵵��ӳ����Ϣʧ��: ");
					} else {
						// ���ĵ����鵵ӳ���ϵ��Ϣ
						officialArchivesType.setOfficialArchivesTypeSavedMappings(officialArchivesTypeSavedMappings);
					}
				}
			}
			
			//��ʼ��ÿһ�����ĵ�������鵵ӳ���ϵ��Ӧ��������鵵ӳ����Ϣ
			if (pFlag) {
				pErrPos = 11;
				for (OfficialArchivesType officialArchivesType : systemInitializer.getOfficialArchivesTypes().values())
				{
					if (officialArchivesType.getOfficialArchivesTypeSavedMappings()!=null) {
						
						for (OfficialArchivesTypeSavedMapping item : officialArchivesType.getOfficialArchivesTypeSavedMappings().values()) {
							Map<Integer, OfficialArchivesDataItemSavedMapping> officialArchivesDataItemSavedMappings=new HashMap<Integer, OfficialArchivesDataItemSavedMapping>();
							if (officialArchivesDataItemSavedMappingManageService.findByArchivesTypeSavedMappingID(item.getID(), officialArchivesDataItemSavedMappings, pErrInfo)==false) {
								pFlag = false;
								pErrInfo.getContent().insert(0, "��ȡָ�����ĵ�������鵵ӳ���ϵ��Ӧ��������鵵ӳ����Ϣʧ��: ");
							}
							else {
								//��������鵵ӳ���ϵ���������ĵ�������ӳ����Ϣ������
								item.setOfficialArchivesDataItemSavedMapping(officialArchivesDataItemSavedMappings);
							}
						}
					}
				}
			}
			
			// ��ʼ�����ĵ��������Ŀ¼��ӡģ����Ϣ����Ŀ¼��ӡģ�������������ϣ�
			if (pFlag)
			{
				pErrPos = 61;
				for (OfficialArchivesType officialArchivesType : systemInitializer.getOfficialArchivesTypes().values())
				{
					if (catalogDataItemManageService.findCatalogDataItemsForOfficial(officialArchivesType, pErrInfo) == false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0, "��ʼ�����ĵ������ࣨ" + officialArchivesType.getName() + "����Ŀ¼��ӡģ����������Ϣʧ��: ");
					}
					
					// ����ʧ������������ѭ��
					if (pFlag==false)
					{
						break;
					}
				}
			}

			// ��ʼ����������
			if (pFlag)
			{
				pErrPos = 12;
				LinkedHashMap<Integer, RetentionPeriod> retentionPeriods = new LinkedHashMap<Integer, RetentionPeriod>();
				if (retentionPeriodManageService.findRetentionPeriods(retentionPeriods, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ʼ������������Ϣʧ��: ");
				}
				else
				{
					// ���汣��������Ϣ
					systemInitializer.setRetentionPeriods(retentionPeriods);
					
					RetentionPeriod period = null;
					
					////��ʼ�����ñ�������
					for(Integer key : retentionPeriods.keySet()) {
						period = retentionPeriods.get(key);
						//�ж��Ƿ�������ñ�������
						if(period!=null && StringTool.checkNull(period.getName()) && period.getName().equals("����")) {
							systemInitializer.setForeverRetentionPeriod(period);
							break;
						} else {
							pFlag = false;
							pErrInfo.getContent().insert(0, "��ʼ�����ñ���������Ϣ ʧ��: ");
						}
					}
				}
			}

			// ��ʼ�������ܼ�
			if (pFlag)
			{
				pErrPos = 13;
				LinkedHashMap<Integer, ArchivesSecrecy> archivesSecrecys = new LinkedHashMap<Integer, ArchivesSecrecy>();
				if (archivesSecrecyManageService.findArchivesSecrecys(archivesSecrecys, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ʼ�������ܼ���Ϣʧ��: ");
				}
				else
				{
					// ���汣��������Ϣ
					systemInitializer.setArchivesSecrecys(archivesSecrecys);
				}
			}
			
			// ��ʼ����������
			if (pFlag)
			{
				pErrPos = 14;
				LinkedHashMap<Integer, OfficialDocType> officialDocTypes = new LinkedHashMap<Integer, OfficialDocType>();
				if (officialDocTypeManageService.findOfficialDocTypes(officialDocTypes, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ʼ�������ܼ���Ϣʧ��: ");
				}
				else
				{
					// ���湫��������Ϣ
					systemInitializer.setOfficialDocTypes(officialDocTypes);
				}
			}

			// ��ʼ��������Ϣ
			if (pFlag)
			{
				pErrPos = 15;
				List<DepartmentInfo> departmentInfos = new ArrayList<DepartmentInfo>();
				if (departmentInfoManageService.findDepartmentInfos(departmentInfos, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ʼ��������Ϣʧ��: ");
				}
				else
				{
					// ���沿����Ϣ
					systemInitializer.setDepartmentInfos(departmentInfos);
				}
			}

			// ��ʼ������Դ��Ϣ�����磺�ܼ����������ޡ��γɲ��š�ȫ�ڡ������������Լ������ǹ�������Դ��Ϣ��
			if (pFlag)
			{
				pErrPos = 16;
				Map<Integer, DataSource> dataSources = new HashMap<Integer, DataSource>();
				if (dataSourceManageService.findDataSources(dataSources, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ʼ������Դ��Ϣʧ��: ");
				}
				else
				{
					// ��������Դ��Ϣ
					systemInitializer.setDataSources(dataSources);
				}
			}

			// ��ʼ��ÿ������Դ����������Ϣ�����磺�ܼ�����Դ�������������ź��ڲ���ѡ���
			if (pFlag)
			{
				pErrPos = 17;
				if (initializeDataSourceItems(systemInitializer, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ʼ������Դ����Ϣʧ��: ");
				}
			}

			// ��ʼ��ԭ����ʱ�洢Ŀ¼��Ϣ
			if (pFlag)
			{
				pErrPos = 18;
				List<Config> attachedFileTempSavePaths = new ArrayList<Config>();
				if (configManageService.findConfigByConfigType("AttachedFileWorkingPath", attachedFileTempSavePaths, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ʼ��ԭ����ʱ�洢Ŀ¼��Ϣʧ��: ");
				}
				else
				{
					// ����ԭ����ʱ�洢Ŀ¼��Ϣ
					systemInitializer.setAttachedFileTempSavePaths(attachedFileTempSavePaths);
				}
			}
			
			// ��ʼ��У������ֵ���Ϣ
			if (pFlag)
			{
				pErrPos = 19;
				Map<Integer, CheckRule> checkRules = new HashMap<Integer, CheckRule>();
				if (checkRuleManageService.findCheckRules(checkRules, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ʼ��У�������Ϣʧ��: ");
				}
				else
				{
					// ����У������ֵ���Ϣ
					systemInitializer.setCheckRules(checkRules);
				}
			}
			
			//��ʼ��������Ŀ�ֵ���Ϣ
			if (pFlag) {
				pErrPos = 20;
				List<EvaluateItem> evaluateItems = new ArrayList<EvaluateItem>();
				if (evaluateItemManageService.findEvaluateItems(evaluateItems , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ʼ��������Ŀ�ֵ���Ϣʧ��: ");
				} else {
					// ���濼����Ŀ�ֵ���Ϣ
					systemInitializer.setEvaluateItems(evaluateItems);
				}
			}
			
			//��ʼ���������ֵȼ��ֵ���Ϣ
			if (pFlag) {
				pErrPos = 21;
				List<EvaluateLevel> evaluateLevels = new ArrayList<EvaluateLevel>();
				if (evaluateLevelManageService.findEvaluateLevels(evaluateLevels , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ʼ���������ֵȼ��ֵ���Ϣʧ��: ");
				} else {
					// ���濼�����ֵȼ��ֵ���Ϣ
					systemInitializer.setEvaluateLevels(evaluateLevels);
				}
			}
			
			//��ʼ��ְ����Ϣ�����ֵ���Ϣ
			if (pFlag) {
				pErrPos = 22;
				List<Duty> duties = new ArrayList<Duty>();
				if (dutyManageService.findDutys(duties, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ʼ��ְ����Ϣ�����ֵ���Ϣʧ��: ");
				} else {
					// ����ְ����Ϣ�����ֵ���Ϣ
					systemInitializer.setDuties(duties);
				}
			}

			//��ʼ�����������ܼ������ֵ���Ϣ
			if (pFlag) {
				pErrPos = 23;
				ArchivesSecrecy archivesSecrecy = new ArchivesSecrecy();
				if (archivesSecrecyManageService.findByOpenSecrecy(archivesSecrecy , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ʼ�����������ܼ������ֵ���Ϣ ʧ��: ");
				} else {
					// ���湫�������ܼ������ֵ���Ϣ
					systemInitializer.setOpenArchivesSecrecy(archivesSecrecy);
				}
			}
			
			//��ʼ���û���ɫ��Ϣ�ֵ伯��
			if (pFlag) {
				pErrPos = 24;
				List<UserRole> userRoles = new ArrayList<UserRole>();
				if (userRoleManageService.findUserRoles(userRoles , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ʼ���û���ɫ��Ϣ�ֵ伯�� ʧ��: ");
				} else {
					// �����û���ɫ��Ϣ�ֵ伯��
					systemInitializer.setUserRoles(userRoles);
				}
			}
			
			//��ʼ�������û���ɫ���
			if (pFlag) {
				pErrPos = 25;
				int anonymouseUserRoleID = 0;
				List<UserRole> userRoles = new ArrayList<UserRole>();
				if(userRoleManageService.findUserRolesBySystemRolesFlag(1, userRoles, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ʼ�������û���ɫ���ʧ��: ");
				}else{
					if (userRoles.size()!=1) {
						pFlag = false;
						pErrInfo.getContent().append("�û���ɫ��Ϣ�ֵ���������ɫ��Ϣ���ô���");
					}else{
						anonymouseUserRoleID = userRoles.get(0).getID();
						systemInitializer.setAnonymouseUserRoleID(anonymouseUserRoleID);
					}
				}
				
			}
		}
		catch (Exception e)
		{
			// �쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// ���پֲ�����

		}

		return pFlag;
	}
	
	/**
	 * ��ʼ��ÿ������Դ������Դ���Ա
	 * @param systemInitializer ϵͳ��ʼ��������
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean initializeDataSourceItems(SystemInitializer systemInitializer,ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//���ϵͳ��ʼ�����Ƿ�Ϊ��
			pErrPos = 1;
			if (systemInitializer==null)
			{
				pFlag = false;
				pErrInfo.getContent().append("ϵͳ��ʼ��������Ƿ�Ϊ�ա�");
			}
			

			//��ת��ϵͳ���е��ֵ���Ϣ������Դ�ֵ伯���У����磺�����ܼ����������ޡ������γɲ��š��������֡�����ȫ�ڣ�Ȼ���������ǹ�������Դ
			if (pFlag)
			{
				for (DataSource item : systemInitializer.getDataSources().values())
				{
					pErrPos = 2;
					LinkedHashMap<Integer, DataSourceItem> dataSourceItems = new LinkedHashMap<Integer, DataSourceItem>();

					// ��������Դ������𣺱�������
					if (item.getInherentType() == EnumDataSourceInherentType.��������)
					{
						LinkedHashMap<Integer, DataSourceItem> retentionPeriodDataSourceItems = new LinkedHashMap<Integer, DataSourceItem>();
						if (convertRetentionPeriods(systemInitializer.getRetentionPeriods(), item.getID(), retentionPeriodDataSourceItems, pErrInfo) == false)
						{
							pFlag = false;
							pErrInfo.getContent().insert(0, "ת������ı�����������Դ����������Ϣʧ��: ");
						}
						else
						{
							// ����ת����Ľ��������Դ������
							item.setDataSourceItems(retentionPeriodDataSourceItems);
						}
					}
					// ��������Դ������𣺵����ܼ�
					else if (item.getInherentType() == EnumDataSourceInherentType.�����ܼ�)
					{
						pErrPos = 3;
						LinkedHashMap<Integer, DataSourceItem> archivesSecrecyDataSourceItems = new LinkedHashMap<Integer, DataSourceItem>();
						if (convertArchivesSecrecys(systemInitializer.getArchivesSecrecys(), item.getID(), archivesSecrecyDataSourceItems, pErrInfo) == false)
						{
							pFlag = false;
							pErrInfo.getContent().insert(0, "ת������ĵ����ܼ�����Դ����������Ϣʧ��: ");
						}
						else
						{
							// ����ת����Ľ��������Դ������
							item.setDataSourceItems(archivesSecrecyDataSourceItems);
						}
					}
					// ��������Դ������𣺲�����Ϣ
					else if (item.getInherentType() == EnumDataSourceInherentType.�����γɲ���)
					{
						pErrPos = 4;
						LinkedHashMap<Integer, DataSourceItem> departmentInfoDataSourceItems = new LinkedHashMap<Integer, DataSourceItem>();
						if (convertDepartmentInfos(systemInitializer.getDepartmentInfos(), item.getID(), departmentInfoDataSourceItems, pErrInfo) == false)
						{
							pFlag = false;
							pErrInfo.getContent().insert(0, "ת������ĵ����ܼ�����Դ����������Ϣʧ��: ");
						}
						else
						{
							// ����ת����Ľ��������Դ������
							item.setDataSourceItems(departmentInfoDataSourceItems);
						}
					}
					// ��������Դ������𣺹�������
					else if (item.getInherentType() == EnumDataSourceInherentType.��������)
					{
						pErrPos = 5;
						LinkedHashMap<Integer, DataSourceItem> officialDocTypeDataSourceItems = new LinkedHashMap<Integer, DataSourceItem>();
						if (convertOfficalDocTypes(systemInitializer.getOfficialDocTypes(), item.getID(), officialDocTypeDataSourceItems, pErrInfo) == false)
						{
							pFlag = false;
							pErrInfo.getContent().insert(0, "ת������ĵ����ܼ�����Դ����������Ϣʧ��: ");
						}
						else
						{
							// ����ת����Ľ��������Դ������
							item.setDataSourceItems(officialDocTypeDataSourceItems);
						}
					}
					// ��������Դ������𣺵���ȫ��
					else if (item.getInherentType() == EnumDataSourceInherentType.����ȫ��)
					{
						pErrPos = 6;
						LinkedHashMap<Integer, DataSourceItem> archivesFondsDataSourceItems = new LinkedHashMap<Integer, DataSourceItem>();
						if (convertArchivesFonds(systemInitializer.getArchivesFondss(), item.getID(), archivesFondsDataSourceItems, pErrInfo) == false)
						{
							pFlag = false;
							pErrInfo.getContent().insert(0, "ת������ĵ���ȫ������Դ����������Ϣʧ��: ");
						}
						else
						{
							// ����ת����Ľ��������Դ������
							item.setDataSourceItems(archivesFondsDataSourceItems);
						}
					}
					// ���������ǹ�������Դ
					else
					{
						pErrPos = 6;
						if (dataSourceItemManageService.findDataSourceItemsByDataSourceID(item.getID(), dataSourceItems, pErrInfo) == false)
						{
							pFlag = false;
							pErrInfo.getContent().insert(0, "��ȡ����Դ����ţ�" + item.getID() + "," + item.getName() + "������������Ϣʧ��: ");
							break;
						}
						else
						{
							item.setDataSourceItems(dataSourceItems);
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}

		return pFlag;
	}

	/**
	 * ���������������������<br>
	 * �ֱ�õ���¼��ϡ���¼��ѯ��ϡ����ò�ѯ��ϡ���ѯ����б���ʾ���<br>
	 * �������Ͼ����ֶ�����Ϊ�ؼ���
	 * 
	 * @param dataItemsForAll ָ�����������¶��������������ϣ����ֶ�����Ϊ�ؼ���
	 * @param dataItemsForInput ���ص���������Ҫ��¼��������ϣ����ֶ�����Ϊ�ؼ���
	 * @param dataItemsForInputQuery ���ص����������Ϊ��¼��ѯ������������ϣ����ֶ�����Ϊ�ؼ���
	 * @param dataItemsForUseQuery ���ص����������Ϊ�������ò�ѯ������������ϣ����ֶ�����Ϊ�ؼ���
	 * @param dataItemsForListDisplay ���ص��������ѯ������б���ʾ��������ϣ����ֶ�����Ϊ�ؼ���
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean splitArchivesTypeDataItems(LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForAll,LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForInput,LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForInputQuery,LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForUseQuery, LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForListDisplay,ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			// ��鵵�������¶����������������Ƿ�Ϊ��
			pErrPos = 1;
			if (dataItemsForAll == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("���������¶��������������ϷǷ�Ϊ�ա�");
			}

			// �����������������Ϣ������н���
			if (pFlag)
			{
				//����������Ҫ��¼���������
				ArrayList<ArchivesTypeDataItem> pDataItemsForInput=new ArrayList<ArchivesTypeDataItem>();
				//�����������Ϊ��¼��ѯ�������������
				ArrayList<ArchivesTypeDataItem> pDataItemsForInputQuery=new ArrayList<ArchivesTypeDataItem>();
				//�����������Ϊ�������ò�ѯ�������������
				ArrayList<ArchivesTypeDataItem> pDataItemsForUseQuery=new ArrayList<ArchivesTypeDataItem>();
				//���������ѯ������б���ʾ���������
				ArrayList<ArchivesTypeDataItem> pDataItemsForListDisplay=new ArrayList<ArchivesTypeDataItem>();
				
				// �����������
				for (ArchivesTypeDataItem dataItem : dataItemsForAll.values())
				{
					// ��¼��־
					if (dataItem.getInputFlag() == true)
					{
						pDataItemsForInput.add(dataItem);
					}

					// ��¼��ѯ��־
					if (dataItem.getInputQueryFlag() == true)
					{
						pDataItemsForInputQuery.add(dataItem);
					}

					// ���ò�ѯ��־
					if (dataItem.getUseQueryFlag() == true)
					{
						pDataItemsForUseQuery.add(dataItem);
					}

					// ��ѯ����б���ʾ��־
					if (dataItem.getListDisplayFlag() == true)
					{
						pDataItemsForListDisplay.add(dataItem);
					}
				}

				// ��¼���������������������Ķ�Ӧ������
				if (pDataItemsForInput.size() > 0)
				{
					Collections.sort(pDataItemsForInput, new ArchivesTypeDataItemComparator(EnumArchivesTypeDataItemComparatorType.��¼����));

					for (ArchivesTypeDataItem item : pDataItemsForInput)
					{
						dataItemsForInput.put(item.getColumnName(), item);
					}
				}

				// ��¼��ѯ�������������������������Ķ�Ӧ������
				if (pDataItemsForInputQuery.size() > 0)
				{
					Collections.sort(pDataItemsForInputQuery, new ArchivesTypeDataItemComparator(EnumArchivesTypeDataItemComparatorType.��¼��ѯ����));

					for (ArchivesTypeDataItem item : pDataItemsForInputQuery)
					{
						dataItemsForInputQuery.put(item.getColumnName(), item);
					}
				}

				// ���ò�ѯ�������������������������Ķ�Ӧ������
				if (pDataItemsForUseQuery.size() > 0)
				{
					Collections.sort(pDataItemsForUseQuery, new ArchivesTypeDataItemComparator(EnumArchivesTypeDataItemComparatorType.���ò�ѯ����));

					for (ArchivesTypeDataItem item : pDataItemsForUseQuery)
					{
						dataItemsForUseQuery.put(item.getColumnName(), item);
					}
				}

				// ��ѯ������б���ʾ�������
				if (pDataItemsForListDisplay.size() > 0)
				{
					Collections.sort(pDataItemsForListDisplay, new ArchivesTypeDataItemComparator(EnumArchivesTypeDataItemComparatorType.�б���ʾ����));

					for (ArchivesTypeDataItem item : pDataItemsForListDisplay)
					{
						dataItemsForListDisplay.put(item.getColumnName(), item);
					}
				}
			}
		}
		catch (Exception e)
		{
			// �쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());

				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// ���پֲ�����
			throwable = null;
		}

		return pFlag;
	}

	/**
	 * �����������ֵ���Ϣת��Ϊ����Դ��������ϣ��������������ȫ������Դ�����еı�����������Դ��
	 * 
	 * @param retentionPeriods
	 *            ���������ֵ���Ϣ
	 * @param retentionPeriodDataSourceID
	 *            �������޵�����Դ���
	 * @param retentionPeriodDataSourceItems
	 *            ����ת����ı�����������Դ���������
	 * @param pErrInfo
	 *            ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean convertRetentionPeriods(LinkedHashMap<Integer, RetentionPeriod> retentionPeriods, int retentionPeriodDataSourceID,
			LinkedHashMap<Integer, DataSourceItem> retentionPeriodDataSourceItems, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			// ��鱣��������Ϣ�Ƿ�Ϊ��
			pErrPos = 1;
			if (retentionPeriods == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("����������Ϣ�Ƿ�Ϊ�ա�");
			}

			// �������ת������������ؽ����
			if (pFlag)
			{
				if (retentionPeriods.size() > 0)
				{
					pErrPos = 2;
					int orderID = 0;
					for (RetentionPeriod item : retentionPeriods.values())
					{
						orderID = orderID + 1;
						DataSourceItem dataSourceItem = new DataSourceItem(item.getID(), retentionPeriodDataSourceID, orderID, item.getName(), item.getRemark());
						retentionPeriodDataSourceItems.put(dataSourceItem.getID(), dataSourceItem);
					}
				}
			}
		}
		catch (Exception e)
		{
			// �쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// ���پֲ�����
			throwable = null;
		}

		return pFlag;
	}

	/**
	 * �������ܼ��ֵ���Ϣת��Ϊ����Դ��������ϣ��������������ȫ������Դ�����еĵ����ܼ�����Դ��
	 * 
	 * @param archivesSecrecys
	 *            �����ܼ��ֵ���Ϣ
	 * @param archivesSecrecyDataSourceID
	 *            �����ܼ�������Դ���
	 * @param archivesSecrecyDataSourceItems
	 *            ����ת����ĵ����ܼ�����Դ���������
	 * @param pErrInfo
	 *            ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean convertArchivesSecrecys(LinkedHashMap<Integer, ArchivesSecrecy> archivesSecrecys, int archivesSecrecyDataSourceID,
			LinkedHashMap<Integer, DataSourceItem> archivesSecrecyDataSourceItems, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			// ��鵵���ܼ���Ϣ�Ƿ�Ϊ��
			pErrPos = 1;
			if (archivesSecrecys == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("�����ܼ���Ϣ�Ƿ�Ϊ�ա�");
			}

			// �������ת������������ؽ����
			if (pFlag)
			{
				if (archivesSecrecys.size() > 0)
				{
					pErrPos = 2;
					int orderID = 0;
					for (ArchivesSecrecy item : archivesSecrecys.values())
					{
						orderID = orderID + 1;
						DataSourceItem dataSourceItem = new DataSourceItem(item.getID(), archivesSecrecyDataSourceID, orderID, item.getName(), item.getRemark());
						archivesSecrecyDataSourceItems.put(dataSourceItem.getID(), dataSourceItem);
					}
				}
			}
		}
		catch (Exception e)
		{
			// �쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// ���پֲ�����
			throwable = null;
		}

		return pFlag;
	}

	/**
	 * �������ֵ���Ϣת��Ϊ����Դ��������ϣ��������������ȫ������Դ�����еĲ�����Ϣ����Դ��
	 * 
	 * @param departmentInfos
	 *            �����ֵ���Ϣ
	 * @param departmentInfoDataSourceID
	 *            �����ܼ�������Դ���
	 * @param departmentInfoDataSourceItems
	 *            ����ת����ĵ����ܼ�����Դ���������
	 * @param pErrInfo
	 *            ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean convertDepartmentInfos(List<DepartmentInfo> departmentInfos, int departmentInfoDataSourceID,
			LinkedHashMap<Integer, DataSourceItem> departmentInfoDataSourceItems, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			// ��鲿���ֵ���Ϣ�Ƿ�Ϊ��
			pErrPos = 1;
			if (departmentInfos == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("�����ֵ���Ϣ�Ƿ�Ϊ�ա�");
			}

			// �������ת������������ؽ����
			if (pFlag)
			{
				if (departmentInfos.size() > 0)
				{
					pErrPos = 2;
					int orderID = 0;
					for (DepartmentInfo item : departmentInfos)
					{
						orderID = orderID + 1;
						DataSourceItem dataSourceItem = new DataSourceItem(item.getID(), departmentInfoDataSourceID, orderID, item.getName(), item.getRemark());
						departmentInfoDataSourceItems.put(dataSourceItem.getID(), dataSourceItem);
					}
				}
			}
		}
		catch (Exception e)
		{
			// �쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// ���پֲ�����
			throwable = null;
		}

		return pFlag;
	}

	/**
	 * �����������ֵ���Ϣת��Ϊ����Դ��������ϣ��������������ȫ������Դ�����еĹ�����������Դ��
	 * 
	 * @param officialDocTypes
	 *            ���������ֵ���Ϣ
	 * @param officalDocTypeDataSourceID
	 *            �������ֵ�����Դ���
	 * @param officialDocTypeDataSourceItems
	 *            ����ת����Ĺ�����������Դ���������
	 * @param pErrInfo
	 *            ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean convertOfficalDocTypes(LinkedHashMap<Integer, OfficialDocType> officialDocTypes, int officalDocTypeDataSourceID,
			LinkedHashMap<Integer, DataSourceItem> officialDocTypeDataSourceItems, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			// ��鹫�������ֵ���Ϣ�Ƿ�Ϊ��
			pErrPos = 1;
			if (officialDocTypes == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("���������ֵ���Ϣ�Ƿ�Ϊ�ա�");
			}

			// �������ת������������ؽ����
			if (pFlag)
			{
				if (officialDocTypes.size() > 0)
				{
					pErrPos = 2;
					int orderID = 0;
					for (OfficialDocType item : officialDocTypes.values())
					{
						orderID = orderID + 1;
						DataSourceItem dataSourceItem = new DataSourceItem(item.getID(), officalDocTypeDataSourceID, orderID, item.getName(), item.getRemark());
						officialDocTypeDataSourceItems.put(dataSourceItem.getID(), dataSourceItem);
					}
				}
			}
		}
		catch (Exception e)
		{
			// �쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// ���پֲ�����
			throwable = null;
		}

		return pFlag;
	}
	
	/**
	 * ������ȫ���ֵ���Ϣת��Ϊ����Դ��������ϣ��������������ȫ������Դ�����еĵ���ȫ������Դ��
	 * 
	 * @param archivesFonds
	 *            ����ȫ���ֵ���Ϣ
	 * @param archivesFondsDataSourceID
	 *            ����ȫ�ڵ�����Դ���
	 * @param archivesFondsDataSourceItems
	 *            ����ת����ĵ���ȫ������Դ���������
	 * @param pErrInfo
	 *            ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean convertArchivesFonds(List<ArchivesFonds> archivesFonds, int archivesFondsDataSourceID,
			LinkedHashMap<Integer, DataSourceItem> archivesFondsDataSourceItems, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			// ��鵵��ȫ���ֵ���Ϣ�Ƿ�Ϊ��
			pErrPos = 1;
			if (archivesFonds == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("����ȫ���ֵ���Ϣ�Ƿ�Ϊ�ա�");
			}

			// �������ת������������ؽ����
			if (pFlag)
			{
				if (archivesFonds.size() > 0)
				{
					pErrPos = 2;
					int orderID = 0;
					for (ArchivesFonds item : archivesFonds)
					{
						orderID = orderID + 1;
						DataSourceItem dataSourceItem = new DataSourceItem(item.getID(), archivesFondsDataSourceID, orderID, item.getCode(), item.getName());
						archivesFondsDataSourceItems.put(dataSourceItem.getID(), dataSourceItem);
					}
				}
			}
		}
		catch (Exception e)
		{
			// �쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// ���پֲ�����
			throwable = null;
		}

		return pFlag;
	}
	
}
