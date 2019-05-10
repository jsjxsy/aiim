/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.*;

import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesInfoQueryCondition;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ArchivesTypeCountInfo;
import com.orifound.aiim.entity.ArchivesTypeDataItem;
import com.orifound.aiim.entity.ArchivesTypeEx;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserInfo;

/**
 * �����鵵��Ϣ�Ĺ������ӿ�
 *
 */
public interface IArchivesInfoQueryService {

	/**
	 * �����ѯ<br>
	 * ������ָ��һ�ֵײ㵵����������ɼ򵥲�ѯ�����ļ򵥲�ѯ�͸߼���ѯ
	 * @param archivesTypes ָ���ĵײ㵵�����༯�ϣ�ע���������ĩ�ڵ�ʱ����ֻ��һ������
	 * @param archivesInfoQueryConditions ��ѯ����
	 * @param dataPageInfo ���ݷ�ҳ��ʾ��Ϣ
	 * @param archivesInfos ���ز��ҳɹ���ĵ�����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	//boolean queryClassified(UserInfo userInfo, List<ArchivesType> archivesTypes,List<ArchivesInfoQueryCondition> archivesInfoQueryConditions,DataPageInfo dataPageInfo,List<ArchivesInfo> archivesInfos,ErrInfo pErrInfo);
	
	/**
	 * �����ѯ<br>
	 * ������ָ��һ�ֵײ㵵����������ɼ򵥲�ѯ�����ļ򵥲�ѯ�͸߼���ѯ
	 * @param archivesTypes ָ���ĵײ㵵�����༯�ϣ�ע���������ĩ�ڵ�ʱ����ֻ��һ������
	 * @param archivesInfoQueryConditions ��ѯ����
	 * @param dataPageInfo ���ݷ�ҳ��ʾ��Ϣ
	 * @param archivesInfos ���ز��ҳɹ���ĵ�����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean queryClassified(UserInfo userInfo, List<ArchivesType> archivesTypes,String archivesInfoQueryConditions_SQL,DataPageInfo dataPageInfo,List<ArchivesInfo> archivesInfos,ErrInfo pErrInfo);

	
	
	
	
	/**
	 * ���Ҷ���������๲ͬӵ�еĿɱ���ѯ��������<br>
	 * �������ۺϲ�ѯ�еĸ߼���ѯ����
	 * @param archivesTypes ����ײ㵵������
	 * @param archivesTypeDataItems ���ز��ҳɹ���������ϣ����ֶ�����Ϊ���ϵļ�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findQueryDataItems(List<ArchivesType> archivesTypes,LinkedHashMap<String, ArchivesTypeDataItem> archivesTypeDataItems,ErrInfo pErrInfo);
	
	/**
	 * �����ڲ���Ų���ָ�����൵����Ϣ<br>
	 * ע�⣺�÷����ǶԵ����鵵��Ϣ�����ݽ��в�ѯ�����������޹�
	 * @param archivesType ָ����������
	 * @param NBXH ָ��Ҫ���ҵĵ����ڲ����
	 * @param archivesInfo ��ѯ�ɹ��󷵻صĵ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesInfoByNBXH(ArchivesType archivesType,int NBXH,ArchivesInfo archivesInfo,ErrInfo pErrInfo);
	
	/**
	 * ���ݵ���������ҵ�����Ϣ<br>
	 * ע�⣺�÷����ǶԵ����鵵��Ϣ�����ݽ��в�ѯ�����������޹�
	 * @param archivesBarcode ָ�����������룬�����ǰ������������ļ�����
	 * @param archivesInfo ���ز��ҳɹ��ĵ�����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesInfoByBarcode(String archivesBarcode,ArchivesInfo archivesInfo,ErrInfo pErrInfo);
	
	/**
	 * �������ѯ <br>
	 * ���ݲ�ѯ������ѯ�������������������Ϣ
	 * @param archivesTypes ָ��Ҫ�������ѯ�Ķ���ײ㵵������
	 * @param archivesInfoQueryConditions ��ѯ����
	 * @param archivesTypeCountInfos ���ز�ѯ�ɹ���ĵ������������ͳ����Ϣ
	 * @param querySQL  �������ڲ�ѯ��SQL���
	 * @param pErrInfo
	 * @return
	 */
	boolean queryCrossClassified(UserInfo userInfo,List<ArchivesType> archivesTypes,List<ArchivesInfoQueryCondition> archivesInfoQueryConditions,List<ArchivesTypeCountInfo> archivesTypeCountInfos,StringBuilder querySQL,ErrInfo pErrInfo);
	
	
	/**
	 * �������ѯ�еĹؼ��ּ���<br>ȷ
	 * ���ݹؼ��ֲ�ѯ�������������еĶ���ײ㵵�������������Ϣ
	 * @param userInfo �û���Ϣ
	 * @param archivesTypes ����������Ϣ����
	 * @param keyWord �ؼ���
	 * @param archivesTypeCountInfos ���ز�ѯ�ɹ���ĵ������������ͳ����Ϣ
	 * @param pErrInfo
	 * @return
	 */
	public boolean queryCrossClassifiedByKeyWord(UserInfo userInfo, List<ArchivesType> archivesTypes,String keyWord ,List<ArchivesTypeCountInfo> archivesTypeCountInfos,ErrInfo pErrInfo);
	
	/**
	 * �����ڲ���Ų���ָ���ѹ鵵���а���ľ����ļ���Ϣ
	 * @param pNBXH ָ��������ڲ����
	 * @param archivesType ָ�����������ĵ������࣬�䵵�����������Ա��븳ֵ
	 * @param archivesInfos ���ز��ҳɹ���ľ����ļ�����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findChildArchivesInfosByNBXH(int pNBXH,ArchivesType archivesType, List<ArchivesInfo> archivesInfos,ErrInfo pErrInfo);
}
