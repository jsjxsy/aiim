/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;
import java.util.Map;

import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesInfoQueryCondition;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserInfo;


/**
 *  �����鵵��Ϣ�Ĺ������ӿڶ���
 */
public interface IArchivesInfoSavedManageService {
	/**
	 * �������������ѯ������Ϣ����ҳ��ȡ��
	 * @param archivesType �����������࣬�䵵�������Ϣ�����Ա�����ֵ
	 * @param archivesInfoQueryConditions ��ѯ����
	 * @param dataPageInfo ���ݷ�ҳ��ʾ��Ϣ
	 * @param archivesInfos ���ز�ѯ�ɹ���ĵ�����Ϣ�����ڲ���Ž�������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean queryClassifiedForAppraisal(ArchivesType archivesType,List<ArchivesInfoQueryCondition> archivesInfoQueryConditions,DataPageInfo dataPageInfo, List<ArchivesInfo> archivesInfos,ErrInfo pErrInfo);
	
	/**
	 * ��������->��ټ����Ǽǣ���ѯָ���������͵Ĺ��ڵ�����Ϣ����ҳ��ȡ��
	 * @param archivesType �����������࣬�䵵�������Ϣ�����Ա�����ֵ
	 * @param formationDepartmentID �����γɲ��ű��
	 * @param dataPageInfo ���ݷ�ҳ��ʾ��Ϣ
	 * @param archivesInfos ���ز�ѯ�ɹ���ĵ�����Ϣ�����ڲ���Ž�������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean queryClassifiedForSaveDestroyAppraisal(ArchivesType archivesType,int formationDepartmentID,DataPageInfo dataPageInfo, List<ArchivesInfo> archivesInfos,ErrInfo pErrInfo);
	
	/**
	 * ��������->��ټ����Ǽǣ���¼�������顢�������µ�����Ϣ�ı�������
	 * @param userInfo ��������Ϣ
	 * @param archivesType �����������࣬�䵵�������Ϣ�����Ա�����ֵ
	 * @param saveArchives ��Ҫ��������ĵ���Map���� ����Integer���飺�����ڲ���š���������id 	ֵ����������id
	 * @param destoryArchives ��Ҫ���ٵĵ���List���� Integer���飺�����ڲ���š���������id
	 * @param batchArchives ���������ټ�����ϸ�����
	 * @param opinion ��������������	��������AppraisalDate������ԭ��AppraisalReason��������AppraisalPersion
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateBatchRetentionPeriod(UserInfo userInfo, ArchivesType archivesType, Map<Integer[], Integer> saveArchives, List<Integer[]> destoryArchives, Map<Integer, Map<String, String>> batchArchives, Map<String, String> opinion, ErrInfo pErrInfo);

	/**
	 * �鵵������Ϣ ��������working���Ƶ�saved��
	 * @param nbxh Ҫ�鵵�ĵ����ڲ����
	 * @param archivesType �����������࣬�䵵�������Ϣ�����Ա�����ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
    boolean saveArchivesInfos(int nbxh, ArchivesType archivesType, ErrInfo pErrInfo);
    
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
	 * ��������->���ż���	��¼�������顢�������µ���������Ϣ
	 * @param userInfo 		��������Ϣ
	 * @param archivesType 	��������
	 * @param archivesNBXHs �����ĵ����ڲ���ż���
	 * @param isPublic 		�Ƿ񿪷�:0δ���š�1����
	 * @param opinion 		��������������	��������AppraisalDate������ԭ��AppraisalReason��������AppraisalPersion
	 * @param pErrInfo 		���ش���ʧ�ܵĴ�����Ϣ
	 * @return 				����ɹ�����true�����򷵻�false
	 */
	boolean updateBatchForPublicAppraisal(UserInfo userInfo, ArchivesType archivesType, List<Integer> archivesNBXHs, String isPublic, Map<String, String> opinion, ErrInfo pErrInfo);
	
	
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
	 * ��������->��������	��¼�������顢�������µ����ܼ���Ϣ
	 * @param userInfo 		��������Ϣ
	 * @param archivesType 	��������
	 * @param archivesNBXHs �����ĵ����ڲ���ż���
	 * @param isPublic 		�Ƿ񿪷�:0δ������1����
	 * @param opinion 		��������������	��������AppraisalDate������ԭ��AppraisalReason��������AppraisalPersion
	 * @param pErrInfo 		���ش���ʧ�ܵĴ�����Ϣ
	 * @return 				����ɹ�����true�����򷵻�false
	 */
	boolean updateBatchForOpenAppraisal(UserInfo userInfo, ArchivesType archivesType, List<Integer> archivesNBXHs, String isPublic, Map<String, String> opinion, ErrInfo pErrInfo);
	

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
     * ���µ�������
     * @param archivesID ����
	 * @param archivesType �����������
     * @param barcode  �����µĵ�������
     * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
     */
	public boolean updateArchivesBarcode(String archivesID, ArchivesType archivesType, String barcode, ErrInfo pErrInfo);

	/**
	 * ���������ѯ������Ϣ
	 * @param archivesType �����������
	 * @param barcode ��������
	 * @param archivesInfo ��ѯ���صĵ�����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean findByBarcode(ArchivesType archivesType, String barcode, ArchivesInfo archivesInfo, ErrInfo pErrInfo);

	/**
	 * ���ݵ����ĵ��Ų�ѯ������Ϣ
	 * @param archivesID ����
	 * @param archivesType �����������
	 * @param archivesInfo ��ѯ���صĵ�����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean findByArchivesID(String archivesID, ArchivesType archivesType, ArchivesInfo archivesInfo, ErrInfo pErrInfo);

	/**
	 * �����ڲ���Ų��ҵ�����Ϣ
	 * @param pNBXH ָ�����ڲ����
	 * @param archivesType �����������࣬�䵵�������Ϣ�����Ա�����ֵ
	 * @param archivesInfo ���ز��ҳɹ��ĵ�����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByNBXH(int pNBXH, ArchivesType archivesType, ArchivesInfo archivesInfo, ErrInfo pErrInfo);

	/**
	 * ɾ��������Ϣ
	 * @param archivesType Ҫɾ���ĵ�����Ϣ���ڵĵ�������
	 * @param nbxhs  Ҫɾ���ĵ�����Ϣ�ڲ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean deleteByNBXH(ArchivesType archivesType, int[] nbxhs, ErrInfo pErrInfo);

	/**
	 * ��ع鵵��Ϣ
	 * @param nBXHS Ҫ��صĵ������ڲ����
	 * @param archivesType ��������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean savedCallBack(int[] nBXHS, UserInfo userInfo,ArchivesType archivesType, ErrInfo pErrInfo);
	
	/**
	 * �����ڲ���Ų��ҵ�����Ϣ
	 * @param pNBXH ָ�����ڲ����
	 * @param archivesType �����������࣬�䵵�������Ϣ�����Ա�����ֵ
	 * @param archivesInfos ���ز��ҳɹ��ľ����ļ���Ϣ���ϣ��Ծ����ļ������������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findChildrenByNBXH(int pNBXH, ArchivesType archivesType, List<ArchivesInfo> archivesInfos,ErrInfo pErrInfo);
	
}
