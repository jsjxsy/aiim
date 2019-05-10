/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;
import java.util.Map;

import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesInfoQueryCondition;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.IntegerEx;
import com.orifound.aiim.entity.UserInfo;

/**
 * ������Ϣ�鵵���DAO�ӿڶ���
 *
 */
public interface IArchivesInfoSavedDao {
	 
	/**
	 * Dao�ӿڶ��壺��ӵ�����Ϣ
	 * @param archivesType �����������࣬�䵵�������Ϣ�����Ա�����ֵ
	 * @param archivesInfo Ҫ��ӵĵ�����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(ArchivesType archivesType,int NBXH, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ���ĵ�����Ϣ<br>
	 * ��ɾ��ָ���ڲ���ŵĵ�����Ϣ��¼�������漰��������ļ���Ϣ��¼
	 * @param archivesType �����������࣬�䵵�������Ϣ�����Ա�����ֵ
	 * @param archivesInfo Ҫɾ���ĵ�����Ϣ�����ڲ�������Ա��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(ArchivesType archivesType,ArchivesInfo archivesInfo, ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺ɾ��ָ���ĵ�����Ϣ<br>
	 * ����ɾ��ָ���ڲ���ŵĵ�����Ϣ��¼������������ļ���Ϣ��¼Ҳһ��ɾ�������еĻ���
	 * @param archivesType �����������࣬�䵵�������Ϣ�����Ա�����ֵ
	 * @param archivesInfo Ҫɾ���ĵ�����Ϣ�����ڲ�������Ա��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteParentAndChild(ArchivesType archivesType,ArchivesInfo archivesInfo, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ���ĵ�����Ϣ<br>
	 * �޸ĵ�����Ϣʱ���ܹ��޸ĵ�����Ԫ���������ֶ�ֵ�����ڵ���������״̬�Լ���¼��Ա����Ϣ�����޸�
	 * @param archivesType �����������࣬�䵵�������Ϣ�����Ա�����ֵ
	 * @param archivesInfo Ҫ���µĵ�����Ϣ�����ڲ�������Ա��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(ArchivesType archivesType,ArchivesInfo archivesInfo, ErrInfo pErrInfo);

	/**[�ѷѳ�����queryClassified��...,archivesInfoQueryConditions_SQL,...��ȡ����]
	 * �����ѯ������Ϣ����ҳ��ȡ��
	 * @param archivesTypes ָ���ĵײ㵵�����༯�ϣ�ע���������ĩ�ڵ�ʱ����ֻ��һ������
	 * @param archivesInfoQueryConditions ��ѯ����
	 * @param dataPageInfo ���ݷ�ҳ��ʾ��Ϣ
	 * @param archivesInfos ���ز�ѯ�ɹ���ĵ�����Ϣ�����ڲ���Ž�������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */		
//	public boolean queryClassified(UserInfo userInfo,  List<ArchivesType> archivesTypes,List<ArchivesInfoQueryCondition> archivesInfoQueryConditions,DataPageInfo dataPageInfo, List<ArchivesInfo> archivesInfos,ErrInfo pErrInfo);
	
	/**
	 * �����ѯ������Ϣ����ҳ��ȡ��
	 * @param archivesTypes ָ���ĵײ㵵�����༯�ϣ�<br>
	 * ע���������ĩ�ڵ�ʱ����ֻ��һ������,������м䵵������ڵ�ʱ�ж�������
	 * @param archivesInfoQueryConditions_SQL ��ѯ�������ַ���
	 * @param dataPageInfo ���ݷ�ҳ��ʾ��Ϣ
	 * @param archivesInfos ���ز�ѯ�ɹ���ĵ�����Ϣ�����ڲ���Ž�������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */		
	public boolean queryClassified(UserInfo userInfo,  List<ArchivesType> archivesTypes,String archivesInfoQueryConditions_SQL,DataPageInfo dataPageInfo, List<ArchivesInfo> archivesInfos,ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺�����ڲ���Ų��ҵ�����Ϣ
	 * @param pNBXH ָ�����ڲ����
	 * @param archivesType �����������࣬�䵵�������Ϣ�����Ա�����ֵ
	 * @param archivesInfo ���ز��ҳɹ��ĵ�����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByNBXH(int pNBXH, ArchivesType archivesType, ArchivesInfo archivesInfo, ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺�����ڲ���Ų��ҵ�����Ϣ
	 * @param pNBXH ָ�����ڲ����
	 * @param archivesType �����������࣬�䵵�������Ϣ�����Ա�����ֵ
	 * @param archivesInfos ���ز��ҳɹ��ľ����ļ���Ϣ���ϣ��Ծ����ļ������������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findChildrenByNBXH(int pNBXH, ArchivesType archivesType, List<ArchivesInfo> archivesInfos,ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺����ָ��������������ļ����ֵ
	 * @param archivesType �����������࣬�䵵�������Ϣ�����Ա�����ֵ
	 * @param parentNBXH ָ���İ����ڲ����
	 * @param maxSubContentID ���ز��ҳɹ����������ļ����ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findMaxSubContentID(ArchivesType archivesType, int parentNBXH,IntegerEx maxSubContentID, ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺����ָ������ľ����ļ���������ҳ��
	 * @param archivesType �����������࣬�䵵�������Ϣ�����Ա�����ֵ
	 * @param parentNBXH ָ���İ����ڲ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateSubContentCountAndPageSum(ArchivesType archivesType, int parentNBXH,ErrInfo pErrInfo);
		
	/**
	 * Dao�ӿڶ��壺�������µ�����Ϣ�İ����־
	 * @param archivesType �����������࣬�䵵�������Ϣ�����Ա�����ֵ
	 * @param parentNBXHs ָ���İ����ڲ��������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean batchUpdateParentFlag(ArchivesType archivesType, final List<ArchivesInfo> archivesInfos,ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺����ɾ��������Ϣ<br>
	 * ��ɾ��ָ���ڲ���ŵĵ�����Ϣ��¼�������漰��������ļ���Ϣ��¼
	 * @param archivesType �����������࣬�䵵�������Ϣ�����Ա�����ֵ
	 * @param parentNBXHs ָ���İ����ڲ��������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean batchDelArchives(ArchivesType archivesType, final List<ArchivesInfo> archivesInfos,ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺���������ļ��������İ�����ڲ����
	 * @param archivesType �����������࣬�䵵�������Ϣ�����Ա�����ֵ
	 * @param parentNBXHs ָ���İ����ڲ��������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean batchUpdateParentNBXH(int parentNBXH, ArchivesType archivesType, final List<ArchivesInfo> archivesInfos,ErrInfo pErrInfo);
	
	/**
	 * ���ݲ�ѯ����ͳ�Ƴ�ָ������ĵ�������
	 * @param userInfo �û���Ϣ
	 * @param archivesType ����������Ϣ
	 * @param querySQL  SQL��ѯ����
	 * @param countNum  ���ز�ѯ���
	 * @param pErrInfo  ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean queryCountByClassified(UserInfo userInfo,ArchivesType archivesType,StringBuilder querySQL,IntegerEx countNum,ErrInfo pErrInfo);

	/**
	 * ��������->��ټ�������ѯָ���������͵Ĺ��ڵ�����Ϣ����ҳ��ȡ��
	 * @param archivesType �����������࣬�䵵�������Ϣ�����Ա�����ֵ
	 * @param FormationDepartmentID �����γɲ��ű��
	 * @param dataPageInfo ���ݷ�ҳ��ʾ��Ϣ
	 * @param archivesInfos ���ز�ѯ�ɹ���ĵ�����Ϣ�����ڲ���Ž�������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean queryClassifiedForSaveDestroyAppraisal(ArchivesType archivesType,int FormationDepartmentID,DataPageInfo dataPageInfo, List<ArchivesInfo> archivesInfos,ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺�������µ�����Ϣ�ı�������
	 * @param archivesType �����������࣬�䵵�������Ϣ�����Ա�����ֵ
	 * @param saveArchives ��Ҫ��������ĵ���Map���� ����Integer���飺�����ڲ���š���������id 	ֵ����������id
	 * @param destoryArchives ��Ҫ���ٵĵ���List���� Integer���飺�����ڲ���š���������id
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateBatchRetentionPeriod(ArchivesType archivesType, Map<Integer[], Integer> saveArchives, List<Integer[]> destoryArchives, ErrInfo pErrInfo);
	
	/**
	 * ��������->���ż�������ѯָ���������͵ĵ�����Ϣ����ҳ��ȡ��
	 * @param userInfo �û���Ϣ ���ڲ�ѯ���û����������ĵ���
	 * @param archivesType �����������࣬�䵵�������Ϣ�����Ա�����ֵ
	 * @param archivesInfoQueryConditions ��ѯ����
	 * @param isPublic �Ƿ񿪷ţ�1���š�0δ����
	 * @param dataPageInfo ���ݷ�ҳ��ʾ��Ϣ
	 * @param archivesInfos ���ز�ѯ�ɹ���ĵ�����Ϣ�����ڲ���Ž�������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */	
	boolean queryClassifiedForPublicAppraisal(UserInfo userInfo, ArchivesType archivesType,List<ArchivesInfoQueryCondition> archivesInfoQueryConditions,String isPublic, DataPageInfo dataPageInfo, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo);

	/**
	 * ��������->���ż���	�������µ���������Ϣ
	 * @param userInfo 		���ڲ�ѯ���û����ż����ĵ���
	 * @param archivesType 	��������
	 * @param archivesNBXHs �����ĵ����ڲ���ż���
	 * @param isPublic 		�Ƿ񿪷�:0δ���š�1����
	 * @param pErrInfo 		���ش���ʧ�ܵĴ�����Ϣ
	 * @return 				����ɹ�����true�����򷵻�false
	 */
	boolean updateBatchForPublicAppraisal(UserInfo userInfo, ArchivesType archivesType, List<Integer> archivesNBXHs, String isPublic, ErrInfo pErrInfo);
	
	/**
	 * ��������->������������ѯָ���������͵ĵ�����Ϣ����ҳ��ȡ��
	 * @param userInfo �û���Ϣ ���ڲ�ѯ���û����������ĵ���
	 * @param archivesType �����������࣬�䵵�������Ϣ�����Ա�����ֵ
	 * @param archivesInfoQueryConditions ��ѯ����
	 * @param isOpen �Ƿ񹫿���1������0δ����
	 * @param dataPageInfo ���ݷ�ҳ��ʾ��Ϣ
	 * @param archivesInfos ���ز�ѯ�ɹ���ĵ�����Ϣ�����ڲ���Ž�������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */	
	boolean queryClassifiedForOpenAppraisal(UserInfo userInfo, ArchivesType archivesType,List<ArchivesInfoQueryCondition> archivesInfoQueryConditions,String isOpen, DataPageInfo dataPageInfo, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo);

	/**
	 * ��������->��������	�������µ����ܼ���Ϣ
	 * @param userInfo 		��������Ϣ
	 * @param archivesType 	��������
	 * @param archivesNBXHs �����ĵ����ڲ���ż���
	 * @param isPublic 		�Ƿ񿪷�:0δ������1����
	 * @param pErrInfo 		���ش���ʧ�ܵĴ�����Ϣ
	 * @return 				����ɹ�����true�����򷵻�false
	 */
	boolean updateBatchForOpenAppraisal(UserInfo userInfo, ArchivesType archivesType, List<Integer> archivesNBXHs, String isPublic, ErrInfo pErrInfo);
	
	/**
	 * ��������->���ؼ�������ѯָ���������͵ĵ�����Ϣ����ҳ��ȡ��
	 * @param userInfo ��������Ϣ
	 * @param archivesType �����������࣬�䵵�������Ϣ�����Ա�����ֵ
	 * @param archivesInfoQueryConditions ��ѯ����
	 * @param dataPageInfo ���ݷ�ҳ��ʾ��Ϣ
	 * @param archivesInfos ���ز�ѯ�ɹ���ĵ�����Ϣ�����ڲ���Ž�������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */	
	boolean queryClassifiedForControlAreaAppraisal(UserInfo userInfo, ArchivesType archivesType,List<ArchivesInfoQueryCondition> archivesInfoQueryConditions, DataPageInfo dataPageInfo, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo);


	/**
	 * ���������ѯ������Ϣ
	 * @param archivesType
	 * @param barcode
	 * @param archivesInfo
	 * @param pErrInfo
	 * @return
	 */
	boolean findByBarcode(ArchivesType archivesType, String barcode, ArchivesInfo archivesInfo, ErrInfo pErrInfo);

	/**
	 * ���µ�������
	 * @param archivesID
	 * @param archivesType
	 * @param barcode
	 * @param pErrInfo
	 * @return
	 */
	boolean updateArchivesBarcode(String archivesID, ArchivesType archivesType, String barcode, ErrInfo pErrInfo);

	/**
	 * ���ݵ��Ų�ѯ������Ϣ
	 * @param archivesFondsID
	 * @param archivesType
	 * @param archivesInfo
	 * @param pErrInfo
	 * @return
	 */
	boolean findByArchivesID(String archivesID, ArchivesType archivesType,ArchivesInfo archivesInfo, ErrInfo pErrInfo);

	/**
	 * �����ڲ����ɾ��������Ϣ
	 * @param archivesType �����������࣬�䵵�������Ϣ�����Ա�����ֵ
	 * @param nbxhs  Ҫɾ���ĵ�����Ϣ�ڲ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteByNBXH(ArchivesType archivesType, int[] nbxhs, ErrInfo pErrInfo);

	/**
	 * ��������Ϣ��saved���Ƶ�working��
	 * @param archivesType �����������࣬�䵵�������Ϣ�����Ա�����ֵ
	 * @param nbxhs Ҫ���Ƶĵ�����Ϣ�ڲ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean checkOutArchivesInfo(ArchivesType archivesType, int[] nbxhs, ErrInfo pErrInfo);
}
