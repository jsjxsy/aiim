/**
 * 
 */
package com.orifound.aiim.entity;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * ϵͳ��ʼ����������
 * 
 */
public class SystemInitializer
{

	/**
	 * ���캯��
	 */
	private SystemInitializer()
	{

	}

	// ����ģʽ
	private static SystemInitializer instance = new SystemInitializer();

	/**
	 * ��ȡϵͳ��ʼ������ʵ��������ģʽ��	
	 * @return ϵͳ��ʼ������ʵ��������ģʽ��
	 */
	public static SystemInitializer getInstance()
	{
		return instance;
	}

	/**
	 * ԭ�ĵ����ļ�����ʱ�洢Ŀ¼����
	 */
	private List<Config> attachedFileTempSavePaths = null;

	/**
	 * ��������ֵ��ԭ�ĵ����ļ�����ʱ�洢Ŀ¼����
	 * 
	 * @param attachedFileTempSavePaths
	 *            ԭ�ĵ����ļ�����ʱ�洢Ŀ¼����
	 */
	public void setAttachedFileTempSavePaths(List<Config> attachedFileTempSavePaths)
	{
		this.attachedFileTempSavePaths = attachedFileTempSavePaths;
	}

	/**
	 * ��ȡ����ֵ��ԭ�ĵ����ļ�����ʱ�洢Ŀ¼����
	 * 
	 * @return ԭ�ĵ����ļ�����ʱ�洢Ŀ¼����
	 */
	public List<Config> getAttachedFileTempSavePaths()
	{
		return attachedFileTempSavePaths;
	}
	
	/**
	 * ԭ�ĵ����ļ������ô洢Ŀ¼����
	 */
	private List<Config> attachedFileSavePaths = null;

	/**
	 * ��������ֵ��ԭ�ĵ����ļ������ô洢Ŀ¼����
	 * 
	 * @param attachedFileSavePaths
	 *            ԭ�ĵ����ļ������ô洢Ŀ¼����
	 */
	public void setAttachedFileSavePaths(List<Config> attachedFileSavePaths)
	{
		this.attachedFileSavePaths = attachedFileSavePaths;
	}

	/**
	 * ��ȡ����ֵ��ԭ�ĵ����ļ������ô洢Ŀ¼����
	 * 
	 * @return ԭ�ĵ����ļ������ô洢Ŀ¼����
	 */
	public List<Config> getAttachedFileSavePaths()
	{
		return attachedFileSavePaths;
	}

	/**
	 * ԭ�ĵ����ļ�PDF����Ĵ洢Ŀ¼
	 */
	private List<Config> attachedFileReSavePath = null;

	/**
	 * ��������ֵ��ԭ�ĵ����ļ�PDF����Ĵ洢Ŀ¼����
	 * 
	 * @param attachedFileReSavePath
	 *            ԭ�ĵ����ļ�PDF����Ĵ洢Ŀ¼����
	 */
	public void setAttachedFileReSavePath(List<Config> attachedFileReSavePath)
	{
		this.attachedFileReSavePath = attachedFileReSavePath;
	}

	/**
	 * ��ȡ����ֵ��ԭ�ĵ����ļ�PDF����Ĵ洢Ŀ¼����
	 * 
	 * @return ԭ�ĵ����ļ�PDF����Ĵ洢Ŀ¼����
	 */
	public List<Config> getAttachedFileReSavePath()
	{
		return attachedFileReSavePath;
	}

	/**
	 * ϵͳ��LogoͼƬ�ļ�������
	 */
	private String systemLogoFileName = "";

	/**
	 * ��������ֵ��ϵͳ��LogoͼƬ�ļ�������
	 * 
	 * @param systemLogoFileName
	 *            ϵͳ��LogoͼƬ�ļ�������
	 */
	public void setSystemLogoFileName(String systemLogoFileName)
	{
		this.systemLogoFileName = systemLogoFileName;
	}

	/**
	 * ��ȡ����ֵ��ϵͳ��LogoͼƬ�ļ�������
	 * 
	 * @return ϵͳ��LogoͼƬ�ļ�������
	 */
	public String getSystemLogoFileName()
	{
		return systemLogoFileName;
	}

	/**
	 * ÿҳ��ʾ�ļ�¼����
	 */
	private int pageViewRecordsCount = 10;

	/**
	 * ��������ֵ��ÿҳ��ʾ�ļ�¼����
	 * 
	 * @param pageViewRecordsCount
	 *            ÿҳ��ʾ�ļ�¼����
	 */
	public void setPageViewRecordsCount(int pageViewRecordsCount)
	{
		this.pageViewRecordsCount = pageViewRecordsCount;
	}

	/**
	 * ��ȡ����ֵ��ÿҳ��ʾ�ļ�¼����
	 * 
	 * @return ÿҳ��ʾ�ļ�¼����
	 */
	public int getPageViewRecordsCount()
	{
		return pageViewRecordsCount;
	}

	/**
	 * �������༯�ϣ���һ�����ࣩ����״�ṹ�ĵ������༯�ϣ����Է�����������������
	 */
	private LinkedHashMap<Integer, ArchivesType> archivesTypes =null;

	/**
	 * ��������ֵ���������༯�ϣ���һ�����ࣩ����״�ṹ�ĵ������༯�ϣ����Է�����������������
	 * 
	 * @param archivesTypes
	 *            �������༯�ϣ���һ�����ࣩ����״�ṹ�ĵ������༯�ϣ����Է�����������������
	 */
	public void setArchivesTypes(LinkedHashMap<Integer, ArchivesType> archivesTypes)
	{
		this.archivesTypes = archivesTypes;
	}

	/**
	 * ��ȡ����ֵ���������༯�ϣ���һ�����ࣩ����״�ṹ�ĵ������༯�ϣ����Է�����������������
	 * 
	 * @return �������༯�ϣ���һ�����ࣩ����״�ṹ�ĵ������༯�ϣ����Է�����������������
	 */
	public LinkedHashMap<Integer, ArchivesType> getArchivesTypes()
	{
		return archivesTypes;
	}
	
	/**
	 * ƽ��ṹ�ĵ������༯�ϣ����е������඼�޲�ι�ϵ��ֱ�Ӵ���ڼ�����
	 */
	private Map<Integer, ArchivesType> planeArchivesTypes = null;

	/**
	 * ��������ֵ��ƽ��ṹ�ĵ������༯�ϣ����е������඼�޲�ι�ϵ��ֱ�Ӵ���ڼ�����
	 * @param camelPropertyName ƽ��ṹ�ĵ������༯�ϣ����е������඼�޲�ι�ϵ��ֱ�Ӵ���ڼ�����
	 */
	public void setPlaneArchivesTypes(Map<Integer, ArchivesType> planeArchivesTypes)
	{
		this.planeArchivesTypes = planeArchivesTypes;
	}

	/**
	 * ��ȡ����ֵ��ƽ��ṹ�ĵ������༯�ϣ����е������඼�޲�ι�ϵ��ֱ�Ӵ���ڼ�����
	 * @return ƽ��ṹ�ĵ������༯�ϣ����е������඼�޲�ι�ϵ��ֱ�Ӵ���ڼ�����
	 */
	public Map<Integer, ArchivesType> getPlaneArchivesTypes()
	{
		return planeArchivesTypes;
	}

	/**
	 * ���ĵ������༯��
	 */
	private LinkedHashMap<Integer, OfficialArchivesType> officialArchivesTypes = null;

	/**
	 * ��������ֵ�����ĵ������༯��
	 * @param officialArchivesTypes ���ĵ������༯��
	 */
	public void setOfficialArchivesTypes(LinkedHashMap<Integer, OfficialArchivesType> officialArchivesTypes)
	{
		this.officialArchivesTypes = officialArchivesTypes;
	}

	/**
	 * ��ȡ����ֵ�����ĵ������༯��
	 * @return ���ĵ������༯��
	 */
	public LinkedHashMap<Integer, OfficialArchivesType> getOfficialArchivesTypes()
	{
		return officialArchivesTypes;
	}

	/**
	 * ���������ֵ伯��
	 */
	private LinkedHashMap<Integer, OfficialDocType> officialDocTypes = null;

	/**
	 * ��������ֵ�����������ֵ伯��
	 * @param officialDocTypes ���������ֵ伯��
	 */
	public void setOfficialDocTypes(LinkedHashMap<Integer, OfficialDocType> officialDocTypes)
	{
		this.officialDocTypes = officialDocTypes;
	}

	/**
	 * ��ȡ����ֵ�����������ֵ伯��
	 * @return ���������ֵ伯��
	 */
	public LinkedHashMap<Integer, OfficialDocType> getOfficialDocTypes()
	{
		return officialDocTypes;
	}

	/**
	 * ����ȫ�ڼ���
	 */
	private List<ArchivesFonds> archivesFondss = null;

	/**
	 * ��������ֵ������ȫ�ڼ���
	 * 
	 * @param archivesFondss
	 *            ����ȫ�ڼ���
	 */
	public void setArchivesFonds(List<ArchivesFonds> archivesFondss)
	{
		this.archivesFondss = archivesFondss;
	}

	/**
	 * ��ȡ����ֵ������ȫ�ڼ���
	 * 
	 * @return ����ȫ�ڼ���
	 */
	public List<ArchivesFonds> getArchivesFondss()
	{
		return archivesFondss;
	}
	
	/**
	 * ���������ֵ伯�ϣ������ͱ����Ϊ�ؼ���
	 */
	private Map<String, DataType> dataTypes = null;

	/**
	 * ��������ֵ�����������ֵ伯�ϣ������ͱ����Ϊ�ؼ���
	 * @param dataTypes ���������ֵ䣬�����ͱ����Ϊ�ؼ���
	 */
	public void setDataTypes(Map<String, DataType> dataTypes)
	{
		this.dataTypes = dataTypes;
	}

	/**
	 * ��ȡ����ֵ�����������ֵ伯�ϣ������ͱ����Ϊ�ؼ���
	 * @return ���������ֵ䣬�����ͱ����Ϊ�ؼ���
	 */
	public Map<String, DataType> getDataTypes()
	{
		return dataTypes;
	}

	/**
	 * ����Դ�ֵ伯�ϣ�������Դ�����Ϊ�ؼ���
	 */
	private Map<Integer, DataSource> dataSources = null;

	/**
	 * ��������ֵ������Դ�ֵ伯�ϣ�������Դ�����Ϊ�ؼ���
	 * @param dataSources ����Դ�ֵ伯�ϣ�������Դ�����Ϊ�ؼ���
	 */
	public void setDataSources(Map<Integer, DataSource> dataSources)
	{
		this.dataSources = dataSources;
	}

	/**
	 * ��ȡ����ֵ������Դ�ֵ伯�ϣ�������Դ�����Ϊ�ؼ���
	 * @return ����Դ�ֵ伯�ϣ�������Դ�����Ϊ�ؼ���
	 */
	public Map<Integer, DataSource> getDataSources()
	{
		return dataSources;
	}

	/**
	 * �����ܼ��ֵ伯�ϣ����ܼ������Ϊ���Ϲؼ���
	 */
	private LinkedHashMap<Integer, ArchivesSecrecy> archivesSecrecys = null;

	/**
	 * ��������ֵ�������ܼ��ֵ伯�ϣ����ܼ������Ϊ���Ϲؼ���
	 * @param archivesSecrecys �����ܼ��ֵ伯�ϣ����ܼ������Ϊ���Ϲؼ���
	 */
	public void setArchivesSecrecys(LinkedHashMap<Integer, ArchivesSecrecy> archivesSecrecys)
	{
		this.archivesSecrecys = archivesSecrecys;
	}

	/**
	 * ��ȡ����ֵ�������ܼ��ֵ伯�ϣ����ܼ������Ϊ���Ϲؼ���
	 * @return �����ܼ��ֵ伯�ϣ����ܼ������Ϊ���Ϲؼ���
	 */
	public LinkedHashMap<Integer, ArchivesSecrecy> getArchivesSecrecys()
	{
		return archivesSecrecys;
	}
	
	/**
	 * ����������Ϣ����
	 */
	private List<DepartmentInfo> departmentInfos = null;

	/**
	 * ��������ֵ������������Ϣ����
	 * @param departmentInfos ����������Ϣ����
	 */
	public void setDepartmentInfos(List<DepartmentInfo> departmentInfos)
	{
		this.departmentInfos = departmentInfos;
	}

	/**
	 * ��ȡ����ֵ������������Ϣ����
	 * @return ����������Ϣ����
	 */
	public List<DepartmentInfo> getDepartmentInfos()
	{
		return departmentInfos;
	}

	/**
	 * У������ֵ伯�ϣ��Թ�������Ϊ�ؼ���
	 */
	private Map<Integer, CheckRule> checkRules = null;

	/**
	 * ��������ֵ��У������ֵ伯�ϣ��Թ�������Ϊ�ؼ���
	 * @param checkRules У������ֵ伯�ϣ��Թ�������Ϊ�ؼ���
	 */
	public void setCheckRules(Map<Integer, CheckRule> checkRules)
	{
		this.checkRules = checkRules;
	}

	/**
	 * ��ȡ����ֵ��У������ֵ伯�ϣ��Թ�������Ϊ�ؼ���
	 * @return У������ֵ伯�ϣ��Թ�������Ϊ�ؼ���
	 */
	public Map<Integer, CheckRule> getCheckRules()
	{
		return checkRules;
	}

	/**
	 * ���������ֵ伯��
	 */
	private LinkedHashMap<Integer, RetentionPeriod> retentionPeriods = null;

	/**
	 * ��������ֵ�����������ֵ伯��
	 * @param retentionPeriod ���������ֵ伯��
	 */
	public void setRetentionPeriods(LinkedHashMap<Integer, RetentionPeriod> retentionPeriods)
	{
		this.retentionPeriods = retentionPeriods;
	}

	/**
	 * ��ȡ����ֵ�����������ֵ伯��
	 * @return ���������ֵ伯��
	 */
	public LinkedHashMap<Integer, RetentionPeriod> getRetentionPeriods()
	{
		return retentionPeriods;
	}

	/**
	 * ��Ч������Ŀ�ֵ����
	 */
	private List<EvaluateItem> evaluateItems = null;

	/**
	 * ��ȡ����ֵ����Ч������Ŀ�ֵ����
	 * @return ��Ч������Ŀ�ֵ����
	 */
	public List<EvaluateItem> getEvaluateItems() {
		return evaluateItems;
	}

	/**
	 * ��������ֵ����Ч������Ŀ�ֵ����
	 * @param evaluateItems ��Ч������Ŀ�ֵ����
	 */
	public void setEvaluateItems(List<EvaluateItem> evaluateItems) {
		this.evaluateItems = evaluateItems;
	}

	/**
	 * ���˵ȼ��ֵ�� ����
	 */
	private List<EvaluateLevel> evaluateLevels = null;

	/**
	 * ��������ֵ�����˵ȼ��ֵ�� ����
	 * @param evaluateLevels ���˵ȼ��ֵ�� ����
	 */
	public void setEvaluateLevels(List<EvaluateLevel> evaluateLevels) {
		this.evaluateLevels = evaluateLevels;
	}

	/**
	 * ��ȡ����ֵ�����˵ȼ��ֵ�� ����
	 * @return ���˵ȼ��ֵ�� ����
	 */
	public List<EvaluateLevel> getEvaluateLevels() {
		return evaluateLevels;
	}

	/**
	 * ְ����Ϣ�����ֵ�� ����
	 */
	private List<Duty> duties = null;

	/**
	 * ��ȡ����ֵ��ְ����Ϣ�����ֵ�� ����
	 * @return ְ����Ϣ�����ֵ�� ����
	 */
	public List<Duty> getDuties() {
		return duties;
	}

	/**
	 * ��������ֵ��ְ����Ϣ�����ֵ�� ����
	 * @param duties ְ����Ϣ�����ֵ�� ����
	 */
	public void setDuties(List<Duty> duties) {
		this.duties = duties;
	}
	
	/**
	 * ���������ܼ������ֵ���Ϣ
	 */
	private ArchivesSecrecy openArchivesSecrecy = null;

	/**
	 * ��ȡ����ֵ�����������ܼ��ֵ���Ϣ
	 * @return ���������ܼ��ֵ���Ϣ
	 */
	public ArchivesSecrecy getOpenArchivesSecrecy() {
		return openArchivesSecrecy;
	}

	/**
	 * ��������ֵ�����������ܼ��ֵ���Ϣ
	 * @param archivesSecrecy ���������ܼ��ֵ���Ϣ
	 */
	public void setOpenArchivesSecrecy(ArchivesSecrecy openArchivesSecrecy) {
		this.openArchivesSecrecy = openArchivesSecrecy;
	}
	
	/**
	 * �û���ɫ�ֵ���ʵ�弯��
	 */
	private List<UserRole> userRoles = null;

	/**
	 * ��ȡ����ֵ���û���ɫ�ֵ���ʵ�弯��
	 * @return �û���ɫ�ֵ���ʵ�弯��
	 */
	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	/**
	 * ��������ֵ���û���ɫ�ֵ���ʵ�弯��
	 * @param userRoles �û���ɫ�ֵ���ʵ�弯��
	 */
	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
	
	/**
	 * ���ñ�������
	 */
	private RetentionPeriod foreverRetentionPeriod ;

	/**
	 * ��ȡ����ֵ�����ñ�������
	 * @return ���ñ�������
	 */
	public RetentionPeriod getForeverRetentionPeriod() {
		return foreverRetentionPeriod;
	}

	/**
	 * ��������ֵ�����ñ�������
	 * @param foreverRetentionPeriod ���ñ�������
	 */
	public void setForeverRetentionPeriod(RetentionPeriod foreverRetentionPeriod) {
		this.foreverRetentionPeriod = foreverRetentionPeriod;
	}
	
	/**
	 * �����û���ɫ���
	 */
	private int anonymouseUserRoleID = 0;

	/**
	 * ��ȡ�����û���ɫ���
	 */
	public int getAnonymouseUserRoleID() {
		return anonymouseUserRoleID;
	}

	/**
	 * ���������û���ɫ���
	 */
	public void setAnonymouseUserRoleID(int anonymouseUserRoleID) {
		this.anonymouseUserRoleID = anonymouseUserRoleID;
	}
	
	
	
}
