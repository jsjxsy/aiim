/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.*;

import com.orifound.aiim.entity.ArchivesSecrecy;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.SystemFeature;


/**
 * ϵͳ�û�Ȩ�޳�ʼ������ӿ�
 *
 */
public interface ISystemUserRightInitializeService 
{
	
	/**
	 * ��ȡָ���û��߱���ϵͳ���ܲ˵�Ȩ��<br>
	 * �������û�������ɫ���߱���ϵͳ���ܲ˵�Ȩ�ޣ����û�Ȩ�޺ͽ�ɫȨ�޵ĺϼ������صĲ˵����ܼ��Ͼ߱���״�ṹ
	 * @param pUserID �û����
	 * @param pRolesID �û�������ɫ�ı������
	 * @param systemFeatureMenus ���ضԸ��û�ֱ����Ȩ�Ķ���ϵͳ���ܲ˵����ϣ�һ���˵���ϣ����¼����ܲ˵����ͨ��ChildSystemFeatures���Է���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean findRightSystemFeatureMenusByUserID(int pUserID,int[] pRolesID, LinkedHashMap<String,SystemFeature> systemFeatureMenus,ErrInfo pErrInfo);
	
	/**
	 * ��ȡָ���û��߱���ϵͳ����Ȩ�ޣ�UCL:�û����ʿ����б�<br>
	 * �������û�������ɫ���߱���ϵͳ����Ȩ�ޣ����û�Ȩ�޺ͽ�ɫȨ�޵ĺϼ�
	 * @param pUserID �û����
	 * @param pRolesID �û�������ɫ�ı������
	 * @param systemFeatures ���ظ��û��߱���ϵͳ����Ȩ�޼��ϣ���UclKeyΪ���Ϲؼ��֣�ע��ù��ܼ�����ƽ���͵ģ�û�в�νṹ������С����ȫ�����ڸü�����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findRightSystemFeaturesByUserID(int pUserID,int[] pRolesID,Map<String,SystemFeature> systemFeatures,ErrInfo pErrInfo);
	
	/**
	 * ��ȡָ���û��߱��ĵ�����ԴȨ��<br>
	 * �������û�������ɫ���߱��ĵ�����ԴȨ�ޣ����û�Ȩ�޺ͽ�ɫȨ�޵ĺϼ�
	 * @param pUserID �û����
	 * @param pRolesID �û�������ɫ�ı������
	 * @param archivesTypes ���ظ��û��߱��ĵ�����ԴȨ�޼��ϣ��Ե�����������Ϊ���ϼ����߱���״�ṹ���ɷ���������������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findRightArchivesTypesByUserID(int pUserID,int[] pRolesID,LinkedHashMap<Integer,ArchivesType> archivesTypes,ErrInfo pErrInfo);
	
	
	/**
	 * ��ȡָ���û��߱��ĵ����ܼ�Ȩ��
	 * @param pUserID �û����
	 * @param pRolesID �û�������ɫ�ı������
	 * @param archivesSecrecys ���ضԸ��û�ֱ����Ȩ�ĵ����ܼ���Ϣ���ϣ����ܼ������Ϊ���Ϲؼ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findRightArchivesSecrecysByUserID(int pUserID,int[] pRolesID,Map<Integer,ArchivesSecrecy> archivesSecrecys,ErrInfo pErrInfo);
	
	
}
