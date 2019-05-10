/**
 * 
 */
package com.orifound.aiim.bll.service.impl.test;

import com.orifound.aiim.bll.service.impl.ArchivesFondsManageServiceImpl;
import com.orifound.aiim.bll.service.impl.ArchivesInfoTableManageServiceImpl;
import com.orifound.aiim.bll.service.impl.ArchivesSecrecyManageServiceImpl;
import com.orifound.aiim.bll.service.impl.ArchivesTypeDataItemManageServiceImpl;
import com.orifound.aiim.bll.service.impl.ArchivesTypeManageServiceImpl;
import com.orifound.aiim.bll.service.impl.CatalogDataItemManageServiceImpl;
import com.orifound.aiim.bll.service.impl.CheckRuleManageServiceImpl;
import com.orifound.aiim.bll.service.impl.ConfigManageServiceImpl;
import com.orifound.aiim.bll.service.impl.DataSourceItemManageServiceImpl;
import com.orifound.aiim.bll.service.impl.DataSourceManageServiceImpl;
import com.orifound.aiim.bll.service.impl.DepartmentInfoManageServiceImpl;
import com.orifound.aiim.bll.service.impl.DutyManageServiceImpl;
import com.orifound.aiim.bll.service.impl.EvaluateItemManageServiceImpl;
import com.orifound.aiim.bll.service.impl.EvaluateLevelManageServiceImpl;
import com.orifound.aiim.bll.service.impl.OfficialArchivesDataItemSavedMappingManageServiceImpl;
import com.orifound.aiim.bll.service.impl.OfficialArchivesInfoTableManageServiceImpl;
import com.orifound.aiim.bll.service.impl.OfficialArchivesTypeManageServiceImpl;
import com.orifound.aiim.bll.service.impl.OfficialArchivesTypeSavedMappingManageServiceImpl;
import com.orifound.aiim.bll.service.impl.OfficialDocTypeManageServiceImpl;
import com.orifound.aiim.bll.service.impl.RetentionPeriodManageServiceImpl;
import com.orifound.aiim.bll.service.impl.SystemInitializeServiceImpl;
import com.orifound.aiim.bll.service.impl.UserRoleManageServiceImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesFondsDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesInfoTableDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesSecrecyDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesTypeDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesTypeDataItemDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.CatalogDataItemDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.CheckRuleDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.ConfigDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.DataSourceDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.DataSourceItemDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.DepartmentInfoDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.DutyDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.EvaluateItemDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.EvaluateLevelDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.OfficialArchivesDataItemSavedMappingDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.OfficialArchivesInfoTableDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.OfficialArchivesTypeDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.OfficialArchivesTypeSavedMappingDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.OfficialDocTypeDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.RetentionPeriodDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.UserRoleDaoimpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.test.AIIMDataSource;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.SystemInitializer;

/**
 * ϵͳ��ʼ��������
 *
 */
public class SystemInitializePrepare
{
	/**
	 * ��ȡϵͳ��ʼ����
	 * @return ϵͳ��ʼ����
	 */
	public static SystemInitializer getSystemInitializer()
	{
		return SystemInitializer.getInstance();
	}
	
	private static void setBLL(SystemInitializeServiceImpl ServiceImpl)
	{
		//ҵ���߼�����ע��
		
		//����ȫ��
		ArchivesFondsManageServiceImpl archivesFondsManageServiceImpl=new ArchivesFondsManageServiceImpl();
		archivesFondsManageServiceImpl.setArchivesFondsDao(new ArchivesFondsDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setArchivesFondsManageService(archivesFondsManageServiceImpl);
		
		//���������Ϣ�����������
		ArchivesInfoTableManageServiceImpl archivesInfoTableManageServiceImpl=new ArchivesInfoTableManageServiceImpl();
		archivesInfoTableManageServiceImpl.setArchivesInfoTableDao(new ArchivesInfoTableDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setArchivesInfoTableManageService(archivesInfoTableManageServiceImpl);
		
		//�����������������
		ArchivesTypeManageServiceImpl archivesTypeManageServiceImpl=new ArchivesTypeManageServiceImpl();
		archivesTypeManageServiceImpl.setArchivesTypeDao(new ArchivesTypeDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setArchivesTypeManageService(archivesTypeManageServiceImpl);
		
		//�����������������������
		ArchivesTypeDataItemManageServiceImpl archivesTypeDataItemManageServiceImpl=new ArchivesTypeDataItemManageServiceImpl();
		archivesTypeDataItemManageServiceImpl.setArchivesTypeDataItemDao(new ArchivesTypeDataItemDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setArchivesTypeDataItemManageService(archivesTypeDataItemManageServiceImpl);
		
		//����Դ����������
		DataSourceManageServiceImpl dataSourceManageServiceImpl=new DataSourceManageServiceImpl();
		dataSourceManageServiceImpl.setDataSourceDao(new DataSourceDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setDataSourceManageService(dataSourceManageServiceImpl);
		
		//����Դ�����������������
		DataSourceItemManageServiceImpl dataSourceItemManageServiceImpl=new DataSourceItemManageServiceImpl();
		dataSourceItemManageServiceImpl.setDataSourceItemDao(new DataSourceItemDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setDataSourceItemManageService(dataSourceItemManageServiceImpl);
		
		//У��������������
		CheckRuleManageServiceImpl checkRuleManageServiceImpl=new CheckRuleManageServiceImpl();
		checkRuleManageServiceImpl.setCheckRuleDao(new CheckRuleDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setCheckRuleManageService(checkRuleManageServiceImpl);
		
		//�������޹���������
		RetentionPeriodManageServiceImpl retentionPeriodManageServiceImpl=new RetentionPeriodManageServiceImpl(new RetentionPeriodDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setRetentionPeriodManageService(retentionPeriodManageServiceImpl);
		
		//�����ܼ�����������
		ArchivesSecrecyManageServiceImpl archivesSecrecyManageServiceImpl=new ArchivesSecrecyManageServiceImpl(new ArchivesSecrecyDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setArchivesSecrecyManageService(archivesSecrecyManageServiceImpl);
		
		//������Ϣ����������
		DepartmentInfoManageServiceImpl departmentInfoManageServiceImpl=new DepartmentInfoManageServiceImpl(new DepartmentInfoDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setDepartmentInfoManageService(departmentInfoManageServiceImpl);
		
		//������Ϣ����������
		ConfigManageServiceImpl configManageServiceImpl=new ConfigManageServiceImpl(new ConfigDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setConfigManageService(configManageServiceImpl);
		
		//����������Ϣ����������
		OfficialDocTypeManageServiceImpl officialDocTypeManageServiceImpl=new OfficialDocTypeManageServiceImpl(new OfficialDocTypeDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setOfficialDocTypeManageService(officialDocTypeManageServiceImpl);
		
		//���ĵ���������Ϣ����������
		OfficialArchivesTypeManageServiceImpl officialArchivesTypeManageServiceImpl=new OfficialArchivesTypeManageServiceImpl(new OfficialArchivesTypeDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setOfficialArchivesTypeManageService(officialArchivesTypeManageServiceImpl);
		
		//���ĵ������������Ϣ�����������
		OfficialArchivesInfoTableManageServiceImpl officialArchivesInfoTableManageServiceImpl=new OfficialArchivesInfoTableManageServiceImpl(new OfficialArchivesInfoTableDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setOfficialArchivesInfoTableManageService(officialArchivesInfoTableManageServiceImpl);
		
		//������Ŀ�ֵ�����������������ע��
		EvaluateItemManageServiceImpl evaluateItemManageServiceImpl=new EvaluateItemManageServiceImpl(new EvaluateItemDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setEvaluateItemManageService(evaluateItemManageServiceImpl);
		
		//���˵ȼ��ֵ����������������ע��
		EvaluateLevelManageServiceImpl evaluateLevelManageServiceImpl=new EvaluateLevelManageServiceImpl(new EvaluateLevelDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setEvaluateLevelManageService(evaluateLevelManageServiceImpl);
		
		//ְ����Ϣ�����ֵ������������ע��
		DutyManageServiceImpl dutyManageServiceImpl=new DutyManageServiceImpl(new DutyDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setDutyManageService(dutyManageServiceImpl);
		
		//���ĵ������ͱ���ӳ�������������ע��
		OfficialArchivesTypeSavedMappingManageServiceImpl officialArchivesTypeSavedMappingManageServiceImpl=new OfficialArchivesTypeSavedMappingManageServiceImpl(new OfficialArchivesTypeSavedMappingDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setOfficialArchivesTypeSavedMappingManageService(officialArchivesTypeSavedMappingManageServiceImpl);
		
		//���ĵ���������鵵ӳ����������������ע��
		OfficialArchivesDataItemSavedMappingManageServiceImpl officialArchivesDataItemSavedMappingManageServiceImpl=new OfficialArchivesDataItemSavedMappingManageServiceImpl(new OfficialArchivesDataItemSavedMappingDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setOfficialArchivesDataItemSavedMappingManageService(officialArchivesDataItemSavedMappingManageServiceImpl);
		
		//�û���ɫ��Ϣ�ֵ������������ע��
		UserRoleManageServiceImpl userRolesInfoManageServiceImpl=new UserRoleManageServiceImpl(new UserRoleDaoimpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setUserRoleManageService(userRolesInfoManageServiceImpl);
		
		//Ŀ¼��ӡģ�����������������������ע��
		CatalogDataItemManageServiceImpl catalogDataItemManageServiceImpl=new CatalogDataItemManageServiceImpl(new CatalogDataItemDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setCatalogDataItemManageService(catalogDataItemManageServiceImpl);
	}
	
	/**
	 * ϵͳ��ʼ��
	 * @param pErrInfo
	 * @return
	 */
	public static boolean initialize(ErrInfo pErrInfo)
	{
		boolean pFlag=true;
		
		SystemInitializeServiceImpl ServiceImpl = new SystemInitializeServiceImpl();
		
		setBLL(ServiceImpl);
		SystemInitializer systemInitializer=SystemInitializer.getInstance();
		if (ServiceImpl.initialize(systemInitializer, pErrInfo)==false)
		{
			pFlag= false;
		}
		
		return pFlag;
	}
}
