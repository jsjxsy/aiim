/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.ArchivesInfoQueryCondition;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EnumArchivesInfoType;
import com.orifound.aiim.entity.EnumOfficialArchivesInfoTableType;
import com.orifound.aiim.entity.EnumWorkFlowStatus;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.OfficialArchivesInfo;
import com.orifound.aiim.entity.OfficialArchivesInfoQueryCondition;
import com.orifound.aiim.entity.OfficialArchivesType;

/**
 * ���ĵ����ǼǱ� �������Ľӿڶ���
 *
 */
public interface IOfficialArchivesInfoManageService {

	/**
	 * ���һ���µĹ��ĵ����ǼǱ� 
	 * @param userID ��¼��Ա�ı��
	 * @param archivesType �����������࣬�䵵�����������Ա��븳ֵ
	 * @param enumArchivesInfoType ָ��������Ϣ���ͣ��ļ������������ļ���
	 * @param officialArchivesInfo ����ӵĹ��ĵ����ǼǱ� ��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveOfficialArchivesInfo(int userID, OfficialArchivesType officialArchivesType,EnumOfficialArchivesInfoTableType enumOfficialArchivesInfoType,OfficialArchivesInfo officialArchivesInfo, ErrInfo pErrInfo);

	/**
	 * ɾ��ָ���Ĺ��ĵ����ǼǱ� 
	 * @param officialArchivesType ָ�����������ĵ������࣬�䵵�����������Ա��븳ֵ
	 * @param enumOfficialArchivesInfoType ָ�������Ĺ鵵���ͣ��ļ���������������ļ���
	 * @param officialArchivesInfo Ҫɾ���Ĺ��ĵ����ǼǱ� ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteOfficialArchivesInfo(OfficialArchivesType officialArchivesType,EnumOfficialArchivesInfoTableType enumOfficialArchivesInfoTableType,OfficialArchivesInfo officialArchivesInfo, ErrInfo pErrInfo);
	
	/**
	 * ɾ��ָ���Ĺ��ĵ����ǼǱ� 
	 * @param officialArchivesType ָ�����������ĵ������࣬�䵵�����������Ա��븳ֵ
	 * @param enumOfficialArchivesInfoType ָ�������Ĺ鵵���ͣ��ļ���������������ļ���
	 * @param officialArchivesInfo Ҫɾ���Ĺ��ĵ����ǼǱ� ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteOfficialArchivesInfos(OfficialArchivesType officialArchivesType,EnumOfficialArchivesInfoTableType enumOfficialArchivesInfoTableType,List<OfficialArchivesInfo> officialArchivesInfos, ErrInfo pErrInfo);

	
	/**
	 * �޸�ָ���Ĺ��ĵ����ǼǱ� 
	 * @param officialArchivesInfo �޸ĺ�Ĺ��ĵ����ǼǱ� ��Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateOfficialArchivesInfo(OfficialArchivesType officialArchivesType,EnumOfficialArchivesInfoTableType enumOfficalArchivesInfoTableType,OfficialArchivesInfo officialArchivesInfo, ErrInfo pErrInfo);

	/**
	 * �������еĹ��ĵ����ǼǱ� ��Ϣ
	 * @param officialArchivesInfos ���ز��ҳɹ��Ĺ��ĵ����ǼǱ� ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findOfficialArchivesInfos(OfficialArchivesType officialArchivesType,EnumOfficialArchivesInfoTableType enumOfficialArchivesInfoTableType,List<OfficialArchivesInfoQueryCondition> officialArchivesInfoQueryConditions, DataPageInfo dataPageInfo,List<OfficialArchivesInfo> officialArchivesInfos,
			ErrInfo pErrInfo);

	/**
	 * ����Ψһ��ʶ���ҹ��ĵ����ǼǱ� ��Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param officialArchivesInfo ���ز��ҳɹ��Ĺ��ĵ����ǼǱ� ��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findOfficialArchivesInfoByID(int pID, OfficialArchivesInfo officialArchivesInfo,
			ErrInfo pErrInfo);
	/**
	 * ����Ψһ��ʶ���ҹ��ĵ����ǼǱ� ��Ϣ
	 * @param pNBXH ָ����Ψһ��ʶ
	 * @param offcialArchivesType  ָ���ļ������Ĺ��ĵ������࣬�乫�ĵ������������Ա��븳ֵ
	 * @param officialArchivesInfo ���ز��ҳɹ��Ĺ��ĵ����ǼǱ� ��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findOfficialArchivesInfoByNBXH(int pNBXH, OfficialArchivesType offcialArchivesType,OfficialArchivesInfo officialArchivesInfo, ErrInfo pErrInfo);
	
	/**
	 * 
	 * @param offcialArchivesType
	 * @param officialArchivesInfo
	 * @param pErrInfo
	 * @return
	 */
	boolean findAll(OfficialArchivesType officialArchivesType,List<OfficialArchivesInfo> officialArchivesInfos,ErrInfo pErrInfo);
	

}
