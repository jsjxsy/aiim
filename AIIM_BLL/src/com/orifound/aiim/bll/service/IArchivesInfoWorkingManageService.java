/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;
import java.util.Map;

import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesInfoQueryCondition;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ArchivesTypeEx;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EnumArchivesInfoType;
import com.orifound.aiim.entity.EnumWorkFlowStatus;
import com.orifound.aiim.entity.ErrInfo;

/**
 * �����鵵�������еĵ�����Ϣ�������Ľӿڶ���<br>
 * ע�⣺�ýӿ��ж���ķ������Ե����鵵���������ݣ�ArchivesInfoWorking_TypeCode�����в�ѯ��<br>
 * ������ʵ����鵵��Ϣ��ArchivesInfoSaved_TypeCode���е�����
 *
 */
public interface IArchivesInfoWorkingManageService
{

	/**
	 * ����ָ������͹�����״̬�����������ĵ�������ҳ��ȡ��
	 * @param userID ��¼��Ա�ı������
	 * @param archivesType ָ����������
	 * @param enumArchivesInfoType ָ��������Ϣ���ͣ��ļ��򰸾�
	 * @param enumWorkFlowStatus ָ��������״̬
	 * @param archivesInfoQueryConditions ָ����ѯ����
	 * @param dataPageInfo ���ݷ�ҳ��ʾ��Ϣ
	 * @param archivesInfos ��ѯ�ɹ��󷵻صĵ�����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesInfos(int[] userID,ArchivesType archivesType,EnumArchivesInfoType enumArchivesInfoType, EnumWorkFlowStatus enumWorkFlowStatus,List<ArchivesInfoQueryCondition> archivesInfoQueryConditions, DataPageInfo dataPageInfo,List<ArchivesInfo> archivesInfos,ErrInfo pErrInfo);

	/**
	 * �����ڲ���Ų��ҵ����鵵�������еĵ�����Ϣ
	 * @param pNBXH ָ�����ڲ����
	 * @param archivesType �����������࣬�䵵�����������Ա��븳ֵ
	 * @param archivesInfo ���ز��ҳɹ��ĵ����鵵�������еĵ�����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesInfoByNBXH(int pNBXH, ArchivesType archivesType,ArchivesInfo archivesInfo, ErrInfo pErrInfo);
	
	/**
	 * �����鵵�����еĲ������ָ���Ķ���ļ���Ϣ����ָ����������Ϊ������ļ�<br>
	 * ע�⣬�����Ŀ�갸�����Ѵ��ڵľ����ļ��Ĵ�������˵�������ô��Ҫ��������ļ�������Խ��и��¸�ֵ��
	 * @param archivesType ָ���ļ������ĵ������࣬�䵵�����������Ա��븳ֵ
	 * @param archivesInfos Ҫ���в��Ķ���ļ������ڲ�������Ա��븳ֵ
	 * @param archivesInfo Ҫ�����Ŀ�갸�����ɹ����ͬ�����¸ö����ChildArchivesInfos����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean appendArchivesInfos(ArchivesType archivesType,List<ArchivesInfo> archivesInfos,ArchivesInfo archivesInfo, ErrInfo pErrInfo);
	
	/**
	 * �����鵵�����еĲ������ָ���İ���𿪣�������ļ�ȫ����Ϊ�������ļ���Ϣ
	 * @param archivesType ָ�����������ĵ������࣬�䵵�����������Ա��븳ֵ
	 * @param archivesInfos Ҫ���в��İ����ϣ����Ա������ڲ�������Ա��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean brokeArchivesInfo(ArchivesType archivesType,List<ArchivesInfo> archivesInfos,int userID, ErrInfo pErrInfo);

	/**
	 * ɾ��ָ���ĵ���������Ϣ��������ԭ�ĵ����ļ���Ϣ<br>
	 * ����ĵ����������ļ����򰸾��鵵�ĵ���������Ϣ��Ҳ�����Ǿ����ļ���Ϣ<br>
	 * ����ǰ�����������ļ��ļ�Ҳ��һ��ɾ��
	 * @param archivesType ָ�����������ĵ������࣬�䵵�����������Ա��븳ֵ
	 * @param enumArchivesInfoType ָ�������Ĺ鵵���ͣ��ļ���������������ļ���
	 * @param archivesInfo Ҫɾ���ĵ��������ڲ�������Ա��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteArchivesInfo(ArchivesType archivesType,EnumArchivesInfoType enumArchivesInfoType,ArchivesInfo archivesInfo,ErrInfo pErrInfo);
	
	/**
	 * ɾ��ָ���Ķ��������Ϣ��������ԭ�ĵ����ļ���Ϣ<br>
	 * ����ĵ����������ļ����򰸾��鵵�ĵ���������Ϣ��Ҳ�����Ǿ����ļ���Ϣ<br>
	 * ����ǰ�����������ļ��ļ�Ҳ��һ��ɾ��
	 * @param archivesType ָ�����������ĵ������࣬�䵵�����������Ա��븳ֵ
	 * @param enumArchivesInfoType ָ�������Ĺ鵵���ͣ��ļ���������������ļ���
	 * @param archivesInfos Ҫɾ���ĵ��������ڲ�������Ա��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteArchivesInfos(ArchivesType archivesType,EnumArchivesInfoType enumArchivesInfoType,List<ArchivesInfo> archivesInfos,ErrInfo pErrInfo);
	
	/**
	 * ����ָ���û�����¼��˵ĵ�������ҳ��ȡ��
	 * @param deptIDs ��¼�����Ա������Ĳ��ű������
	 * @param archivesType ָ���������࣬�䵵�����������Ա��븳ֵ
	 * @param dataPageInfo ���ݷ�ҳ��ʾ��Ϣ
	 * @param archivesInfos ��ѯ�ɹ��󷵻صĵ�����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findInputCheckNeedArchivesInfos(int[] deptIDs,ArchivesType archivesType,DataPageInfo dataPageInfo,List<ArchivesInfo> archivesInfos,ErrInfo pErrInfo);

	/**
	 * �����ڲ���Ų���ָ������ľ����ļ���Ϣ
	 * @param pNBXH ָ��������ڲ����
	 * @param archivesType ָ�����������ĵ������࣬�䵵�����������Ա��븳ֵ
	 * @param archivesInfos ���ز��ҳɹ���ľ����ļ�����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findChildArchivesInfosByNBXH(int pNBXH,ArchivesType archivesType, List<ArchivesInfo> archivesInfos,ErrInfo pErrInfo);
	
	/**
	 * �鵵�����е��������ָ���Ķ���ļ���Ϣ�ϲ�Ϊһ�ݰ���
	 * @param userID �����Ա�ı��
	 * @param archivesType ָ���ļ������ĵ������࣬�䵵�����������Ա��븳ֵ
	 * @param archivesInfos Ҫ�������Ķ���ļ������ڲ�������Ա��븳ֵ
	 * @param archivesInfo ����İ��������Ϣ�����ɹ����ͬ�����¸ö����ChildArchivesInfos����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean combineArchivesInfos(int userID,ArchivesType archivesType,List<ArchivesInfo> archivesInfos,ArchivesInfo archivesInfo, ErrInfo pErrInfo);
	
	/**
	 * �鵵�����еĺϾ�����ָ���Ķ��������Ϣ�ϲ�Ϊһ�ݰ���
	 * @param archivesType ָ�����������ĵ������࣬�䵵�����������Ա��븳ֵ
	 * @param archivesInfos Ҫ���кϾ�Ķ���������ڲ�������Ա��븳ֵ
	 * @param archivesInfo �Ͼ��İ��������Ϣ���Ͼ�ɹ����ͬ�����¸ö����µ�ChildArchivesInfos����ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean mergeArchivesInfos(ArchivesType archivesType,List<ArchivesInfo> archivesInfos,ArchivesInfo archivesInfo, ErrInfo pErrInfo);

	/**
	 * ���һ���µĵ�����Ϣ<br>
	 * ����ĵ����������ļ����򰸾��鵵�ĵ���������Ϣ��Ҳ�����Ǿ����ļ���Ϣ
	 * @param userID ��¼��Ա�ı��
	 * @param archivesType �����������࣬�䵵�����������Ա��븳ֵ
	 * @param enumArchivesInfoType ָ��������Ϣ���ͣ��ļ������������ļ���
	 * @param archivesInfo Ҫ��ӵĵ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveArchivesInfo(int userID, ArchivesType archivesType,EnumArchivesInfoType enumArchivesInfoType,ArchivesInfo archivesInfo,ErrInfo pErrInfo);

	/**
	 * �޸�ָ���ĵ�����Ϣ<br>
	 * ����ĵ����������ļ����򰸾��鵵�ĵ���������Ϣ��Ҳ�����Ǿ����ļ���Ϣ<br>
	 * �޸ĵ�����Ϣʱ���ܹ��޸ĵ�����Ԫ���������ֶ�ֵ�����ڵ���������״̬�Լ���¼��Ա����Ϣ�����޸�
	 * @param userID ��¼��Ա�ı��
	 * @param archivesType �����������࣬�䵵�����������Ա��븳ֵ
	 * @param enumArchivesInfoType ָ��������Ϣ���ͣ��ļ������������ļ���
	 * @param archivesInfo Ҫ��ӵĵ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateArchivesInfo(ArchivesType archivesType,EnumArchivesInfoType enumArchivesInfoType,ArchivesInfo archivesInfo,ErrInfo pErrInfo);

	/**
	 * �����鵵�����еĲ�ִ�����ָ���ľ����ļ������������в�ֳ�ȥ�γɶ������ļ�
	 * @param archivesType ָ�����������ĵ������࣬�䵵�����������Ա��븳ֵ
	 * @param parentArchivesInfo �����������ڲ�������Ա��븳ֵ
	 * @param childArchivesInfos Ҫ���в�ֵľ����ļ����ϣ����Ա��NBXH���Ա��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean separateArchivesInfos(ArchivesType archivesType,ArchivesInfo parentArchivesInfo,List<ArchivesInfo> childArchivesInfos,ErrInfo pErrInfo);
	
	/**
	 * �����鵵�����еķ־�����ָ���ľ����ļ������������з����ȥ�γ��µİ���
	 * @param archivesType ָ�����������ĵ������࣬�䵵�����������Ա��븳ֵ
	 * @param parentArchivesInfo ԭ�������������ڲ�������Ա��븳ֵ
	 * @param childArchivesInfos Ҫ���з���ľ����ļ����ϣ����Ա��NBXH���Ա��븳ֵ
	 * @param desArchivesInfo ��������γɵ�Ŀ�갸��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean splitArchivesInfos(ArchivesType archivesType,ArchivesInfo parentArchivesInfo,List<ArchivesInfo> childArchivesInfos,ArchivesInfo desArchivesInfo,ErrInfo pErrInfo);
	
	/**
	 * ��¼��Ϣ�ύ���󣺽�ָ���Ķ�ݵ�����Ϣ�����ύ��ҵ��ָ���ҽ�����¼���
	 * @param userID ��¼��Ա�ı��
	 * @param archivesType ָ�����������ĵ������࣬�䵵�����������Ա��븳ֵ
	 * @param archivesInfos Ҫ�ύ����ĵ������ϣ����Ա������ڲ�������Ա��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean submitToInputCheck(int userID,ArchivesType archivesType,EnumArchivesInfoType enumArchivesInfoType,List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo);

	/**
	 * ͳ��ָ���û���ҵ��ָ����רְ������Ա����Ҫ������¼��˵ĸ��ֵ�������ĵ�������<br>
	 * ����û���ύ����ĵ������಻��ͳ�ƺͷ��ؽ��<br>
	 * ע�⣺���ؽ��������״�ṹ���������ù�����ת��Ϊҳ���������״�ṹ
	 * @param deptID �û�����Ĳ��ű������
	 * @param archivesTypeEx ����ָ���û���Ҫ������¼��˵ĵ������༯�ϣ��Ե�����������Ϊ���ϵļ���������ȴ�������¼��˵ĵ���������ͨ���ö����InputCheckNeedArchivesInfoCount����������ͳ�ƽ��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean statInputCheckNeedCount(int[] deptID,Map<Integer, ArchivesTypeEx> archivesTypeEx, ErrInfo pErrInfo);
	
	/**
	 * ��¼���ͨ��<br>
	 * ��ָ������������¼���ͨ���ı�־
	 * @param userID �����Ա�ı��
	 * @param archivesType ָ�����������ĵ������࣬�䵵�����������Ա��븳ֵ
	 * @param archivesInfo ָ���ĵ��������ڲ�������Ա��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean inputCheckPass(int userID,ArchivesType archivesType,ArchivesInfo archivesInfo,ErrInfo pErrInfo);
	
	/**
	 * ��¼��˴��<br>
	 * ��ָ������������¼��˴�صı�־
	 * @param userID �����Ա�ı��
	 * @param archivesType ָ�����������ĵ������࣬�䵵�����������Ա��븳ֵ
	 * @param archivesInfo ָ���ĵ��������ڲ�������Ա��븳ֵ
	 * @param backReason ���ԭ��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean inputCheckBack(int userID,ArchivesType archivesType,ArchivesInfo archivesInfo,String backReason,ErrInfo pErrInfo);
	
	/**
	 * ͳ����¼��˽��<br>
	 * ͳ�Ƴ���������������¼���ͨ����δͨ���ĵ����������������û���ύ����ĵ������಻��ͳ�ƺͷ��ؽ��<br>
	 * ע�⣺���ؽ��������״�ṹ���������ù�����ת��Ϊҳ���������״�ṹ
	 * @param userID ��¼�û����
	 * @param archivesTypeEx ����ͳ�ƽ���������ݵ���ײ㵵�����࣬�Ե�����������Ϊ���ϵļ�����ͨ���ö����InputCheckPassArchivesInfoCount��InputCheckBackArchivesInfoCount����������ͳ�ƽ��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean statInputCheckResult(int[] userID,Map<Integer, ArchivesTypeEx> archivesTypeEx,ErrInfo pErrInfo);
	
	/**
	 * ����ָ����¼�û��͵�����������¼���ͨ���ĵ�����Ϣ
	 * @param userID ��¼�û����
	 * @param archivesType �����������࣬�䵵�����������Ա��븳ֵ
	 * @param dataPageInfo ���ݷ�ҳ��ʾ��Ϣ
	 * @param archivesInfos ���ز��ҳɹ��ĵ�����Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findInputCheckPassArchivesInfos(int[] userID,ArchivesType archivesType,DataPageInfo dataPageInfo,List<ArchivesInfo> archivesInfos,ErrInfo pErrInfo);
	
	/**
	 * ����ָ����¼�û��͵�����������¼���δͨ���ĵ�����Ϣ
	 * @param userID ��¼�û����
	 * @param archivesType �����������࣬�䵵�����������Ա��븳ֵ
	 * @param archivesInfos ���ز��ҳɹ��ĵ�����Ϣ����
	 * @param dataPageInfo ���ݷ�ҳ��ʾ��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findInputCheckBackArchivesInfos(int[] userID,ArchivesType archivesType,DataPageInfo dataPageInfo,List<ArchivesInfo> archivesInfos,ErrInfo pErrInfo);
	
	/**
	 * �޸���¼��˴�صĵ�����Ϣ<br>
	 * ���µ�����Ϣ�����õ���������״̬Ϊ�ύ������ɡ������޸���־
	 * @param archivesType �����������࣬�䵵�����������Ա��븳ֵ
	 * @param archivesInfo Ҫ�޸��ĵ�����Ϣ�����ڲ�������Ա��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean fixArchivesInfoForInputCheckBack(ArchivesType archivesType,ArchivesInfo archivesInfo,ErrInfo pErrInfo);
	
	
	/**
	 * �޸�������˴�صĵ�����Ϣ<br>
	 * ���µ�����Ϣ�����õ���������״̬Ϊҵ��ָ������¼���ͨ���������޸���־
	 * @param archivesType �����������࣬�䵵�����������Ա��븳ֵ
	 * @param archivesInfo Ҫ�޸��ĵ�����Ϣ�����ڲ�������Ա��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean fixArchivesInfoForPaperCheckBack(ArchivesType archivesType,ArchivesInfo archivesInfo,ErrInfo pErrInfo);
	
	/**
	 * ���ù�����״̬��־�����浵���鵵�����еĹ���״̬����־�����鵵�����е�ָ�������Ѿ��������<br>
	 * ҵ���߼������µ�����Ϣ���еĵ���������״̬�͹�����Ա��Ϣ�������浵���鵵������Ϣ
	 * 
	 * @param enumArchivesInfoType ������Ϣ���࣬���ļ�������
	 * @param pArchivesTypeID ����������
	 * @param pNBXH �ڲ����
	 * @param userID �û����
	 * @param enumWorkFlowStatus ������״̬
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
      boolean setFlagForWorkFlow(EnumArchivesInfoType enumArchivesInfoType, int pArchivesTypeID,  int[] pNBXHs, int userID,EnumWorkFlowStatus enumWorkFlowStatus, ErrInfo pErrInfo);

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
      * ɾ��������Ϣ
      * @param archivesType ����������������
      * @param nBXH Ҫɾ���ĵ������ڲ����
      * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
  	  * @return ����ɹ�����true�����򷵻�false
      */
	boolean deleteParentAndChild(ArchivesType archivesType, int nBXH, ErrInfo pErrInfo);

	/**
	 * ���ļ����������뵽������
	 * @param userID �����û���ID��
	 * @param archivesType ����������������
	 * @param parentNBXH Ҫ�����ļ��İ����ڲ����
	 * @param nBXHS Ҫ������ļ��ڲ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
  	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean insertFileToArchives(int userID, ArchivesType archivesType, int parentNBXH, int[] nBXHS, ErrInfo pErrInfo);
}
