/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;


import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesInfoQueryCondition;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EnumArchivesInfoType;
import com.orifound.aiim.entity.EnumWorkFlowStatus;
import com.orifound.aiim.entity.EnumWorkingUserIDType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.IntegerEx;

/**
 * ������Ϣ�������DAO�ӿڶ���
 * @param <EnumCheckResult>
 *
 */
public interface IArchivesInfoWorkingDao
{

	/**
	 * Dao�ӿڶ��壺��ӵ�����Ϣ
	 * @param archivesType �����������࣬�䵵�������Ϣ�����Ա�����ֵ
	 * @param archivesInfo Ҫ��ӵĵ�����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(ArchivesType archivesType,ArchivesInfo archivesInfo, ErrInfo pErrInfo);

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

	/**
	 * ����ָ��������״̬�����е�����Ϣ����ҳ��ȡ��
	 * @param enumArchivesInfoType ָ��������Ϣ���ͣ��ļ��򰸾�
	 * @param userID ��¼��Ա�ı������
	 * @param archivesType �����������࣬�䵵�������Ϣ�����Ա�����ֵ
	 * @param enumWorkFlowStatus ָ��������״̬
	 * @param archivesInfoQueryConditions ָ����ѯ����
	 * @param dataPageInfo ���ݷ�ҳ��ʾ��Ϣ
	 * @param archivesInfos ��ѯ�ɹ��󷵻صĵ�����Ϣ�����ڲ���Ž�������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean find(EnumArchivesInfoType enumArchivesInfoType, int[] userID,ArchivesType archivesType,EnumWorkFlowStatus enumWorkFlowStatus,List<ArchivesInfoQueryCondition> archivesInfoQueryConditions, DataPageInfo dataPageInfo,List<ArchivesInfo> archivesInfos,ErrInfo pErrInfo);
	
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
	 * Dao�ӿڶ��壺����ָ�������Ĺ�����״̬���û������Ϣ
	 * @param archivesType �����������࣬�䵵�������Ϣ�����Ա�����ֵ
	 * @param pNBXH ָ���ĵ����ڲ����
	 * @param enumWorkingUserIDType �û��������
	 * @param userID �û����
	 * @param enumWorkFlowStatus ������״̬
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateWorkFlowUserIDByNBXH(ArchivesType archivesType, int[] pNBXHs,EnumWorkingUserIDType enumWorkingUserIDType,int userID,EnumWorkFlowStatus enumWorkFlowStatus, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ����������о����ļ��Ĺ�����״̬���û������Ϣ
	 * @param archivesType �����������࣬�䵵�������Ϣ�����Ա�����ֵ
	 * @param parentNBXH ָ���İ����ڲ����
	 * @param enumWorkingUserIDType �û��������
	 * @param userID �û����
	 * @param enumWorkFlowStatus ������״̬
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateWorkFlowUserIDForChild(ArchivesType archivesType, int[] parentNBXHs,EnumWorkingUserIDType enumWorkingUserIDType, int userID,EnumWorkFlowStatus enumWorkFlowStatus, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������µ�����Ϣ�İ����־
	 * @param archivesType �����������࣬�䵵�������Ϣ�����Ա�����ֵ
	 * @param parentNBXHs ָ���İ����ڲ��������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean batchUpdateParentNBXH(ArchivesType archivesType, final List<ArchivesInfo> archivesInfos,final int userID,final EnumWorkFlowStatus enumWorkFlowStatus,ErrInfo pErrInfo);
	
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
	 * Dao�ӿڶ��壺ͳ�Ƶ�����¼���ͨ��δͨ���ĵ�������
	 * @param userIDs ��¼��Ա�ı������(��ֹ�д��������)
	 * @param ArchivesType ������������
	 * @param enumWorkFlowStatus ��������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @param count ����ͳ�ƽ��
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean statArchivesInfoCountByWorkFlowStatus(int [] userIDs, ArchivesType archivesType, EnumWorkFlowStatus enumWorkFlowStatus,String userType,IntegerEx count, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��� : ����ָ������͹�����״̬�µĵ�����Ϣ
	 * @param userIDs ��¼��Ա�ı������(��ֹ�д��������)
	 * @param archivesType ָ����������
	 * @param enumWorkFlowStatus ָ��������״̬
	 * @param archivesInfos ��ѯ�ɹ��󷵻صĵ�����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesInfosByEnumWorkFlowStatus(int[] userIDs,ArchivesType archivesType, EnumWorkFlowStatus enumWorkFlowStatus, DataPageInfo dataPageInfo,String userType,List<ArchivesInfo> archivesInfos,ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��� : ����ָ������͹�����״̬�µĵ�����Ϣ
	 * @param deptIDs ��¼��Ա������Ĳ��ŵı������
	 * @param archivesType ָ����������
	 * @param enumWorkFlowStatus ָ��������״̬
	 * @param archivesInfos ��ѯ�ɹ��󷵻صĵ�����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesInfosByEnumWorkFlowStatus(ArchivesType archivesType,int[] deptIDs, EnumWorkFlowStatus enumWorkFlowStatus, DataPageInfo dataPageInfo,List<ArchivesInfo> archivesInfos,ErrInfo pErrInfo);

	/**
	 * ���µ������ԭ��
	 * @param nbxh ��ص������ڲ����
	 * @param backReason ���ԭ��
	 * @param archivesType ����������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateArchivesBackReason(int nbxh, ArchivesType archivesType,String backReason ,ErrInfo pErrInfo);
	
	 /**
  	 * �޸�ָ���ĵ�����Ϣ�����õ�����Ϣ�޸���־<br>
  	 * ֵ��԰��������޸���־
  	 * @param archivesType �����������࣬�䵵�����������Ա��븳ֵ��
  	 * @param NBXH Ҫ�����޸���ǵĵ����ڲ����
  	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
  	 * @return ����ɹ�����true�����򷵻�false
  	 */
     boolean updateArchivesInfoFixedFlag(ArchivesType archivesType,int NBXH,ErrInfo pErrInfo);
     
     /**
      * ���µ�������źͰ�����ı�
      * @param contentID �����
      * @param contentIDText ������ı�
      * @param NBXH Ҫ���µĵ������ڲ����
      * @param archivesType ������������
      * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
  	  * @return ����ɹ�����true�����򷵻�false
      */
     boolean updateContentIDText(int contentID,String contentIDText,int NBXH,ArchivesType archivesType,ErrInfo pErrInfo);
     
    /**
     * �������ļ����ɵ���
     * @param parentNBXH ���ڰ����ڲ����
     * @param archivesType ������������
     * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
  	  * @return ����ɹ�����true�����򷵻�false
     */
     boolean updateSubArchivesID(int parentNBXH,ArchivesType archivesType,ErrInfo pErrInfo);
}
