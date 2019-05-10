/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.IPerformanceManageService;
import com.orifound.aiim.dal.dao.IArchivesInfoWorkProcedureDao;
import com.orifound.aiim.dal.dao.IPaperTransferBatchesDao;
import com.orifound.aiim.dal.dao.IUserInfoDao;
import com.orifound.aiim.dal.util.StringTool;
import com.orifound.aiim.dal.util.TimeTool;
import com.orifound.aiim.entity.EnumSystemRole;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserInfo;

/**
 * ��Ч�������Ľӿڶ���
 * @author tyb
 *
 */
public class PerformanceManageServiceImpl implements IPerformanceManageService {
	
	/**
	 * �����鵵���̱�DAO
	 */
	@Autowired
	private IArchivesInfoWorkProcedureDao archivesInfoWorkProcedureDao;
	
	/**
	 * �ƽ�������Ϣ���DAO
	 */
	@Autowired
	private IPaperTransferBatchesDao paperTransferBatchesDao;
	
	/**
	 * ע���û���Ϣ���DAO
	 */
	@Autowired
	private IUserInfoDao userInfoDao;
	
	/**
	 * ���DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForDAO(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (archivesInfoWorkProcedureDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("��DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
			
			if (pFlag) {
				if (paperTransferBatchesDao == null) {
					pFlag = false;
					pErrInfo.getContent().append("�ƽ�������Ϣ���DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
				}
			}
			
			if (pFlag) {
				if (userInfoDao == null) {
					pFlag = false;
					pErrInfo.getContent().append("�û���Ϣ���DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
				}		
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
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

	/**
	 * ��Ч����->��������ѯ
	 * @param counts  counts[0]���ƽ�����counts[1]�����
	 * @param beginTime ��⿪ʼʱ��
	 * @param endTime ������ʱ��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return boolean ����ɹ�����true�����򷵻�false
	 */
	public boolean findInStorageCondition(Map<String, Integer> inStorageCondition, String beginTime, String endTime, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForDAO(pErrInfo) == false) {
				pFlag = false;
			}
			
			List<Integer> userIds = null;
			if (pFlag) {
				//��ѯ���о��е��������ҽ�ɫ����Ա��Ϣ����
				List<UserInfo> userInfos = new ArrayList<UserInfo>();
				if (findArchivesinfoManagers(userInfos , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ���о��е��������ҽ�ɫ����Ա��Ϣ���� ʧ�ܣ�");
				}
				
				if (userInfos.size() >= 1) {
					userIds = new ArrayList<Integer>();
					for(UserInfo info : userInfos) {
						userIds.add(info.getUserID());
					}
				}
			}

			if (pFlag) {
				pErrPos = 2;
				//���ҵ���������������
				if (archivesInfoWorkProcedureDao.findInStorageCondition(inStorageCondition, userIds, beginTime, endTime, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"���ҵ���������������ʧ��");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
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
	 * ��⵵���γɲ��š�ҵ��ָ���ҵ���Ա�ض�ʱ�������¼����������<br>
	 * @param recordCondition map���ϱ���:��Ա��ʵ����-��¼���� ��ֵ��
	 * @param userIds ��Աid����
	 * @param beginTime ��⿪ʼʱ��	
	 * @param endTime	������ʱ��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	 public boolean findRecordCondition(Map<String, Integer> recordCondition, List<Integer> userIds, String beginTime, String endTime, ErrInfo pErrInfo) {
			boolean pFlag = true;
			int pErrPos = 0;
			Throwable throwable = new Throwable();

			try {
				pErrPos = 1;
				//����Ƿ��н���DAO����ע��
				if (checkDaoInjectionForDAO(pErrInfo) == false) {
					pFlag = false;
				}
				
				//�����¼���map�����Ƿ�Ϊ��
				if (pFlag) {
					if(recordCondition == null) {
						pFlag = false;
						pErrInfo.getContent().append("�������:��¼���map���ϷǷ�Ϊ�ա�");
					}
				}
				
				//���ҵ��ָ������Աid�����Ƿ�Ϊ��
				if (pFlag) {
					if(userIds==null || userIds.size()<=0) {
						pFlag = false;
						pErrInfo.getContent().append("�������:��Աid���ϷǷ�Ϊ�ա�");
					}
				}

				//��鿪ʼʱ�䡢����ʱ�䲻Ϊ��ʱ����ʼʱ���Ƿ�С�ڽ���ʱ��
				if (pFlag) {
					if(StringTool.checkNull(beginTime) && StringTool.checkNull(endTime)) {
						Date beginDate = TimeTool.getDateFromString(beginTime);
						Date endDate = TimeTool.getDateFromString(endTime);
						if(beginDate.getTime() >= endDate.getTime()) {
							pErrInfo.getContent().append("�������:��ʼʱ����ڽ���ʱ��Ƿ���");
						}
					}
				}
				
				if (pFlag) {
					pErrPos = 2;
					if(archivesInfoWorkProcedureDao.recordCondition(recordCondition, userIds, beginTime, endTime, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "��⵵���γɲ��š�ҵ��ָ���ҵ���Ա�ض�ʱ�������¼��� DAOִ��ʧ�ܡ�");
					}
				}
			} catch (Exception e) {
				//�쳣����
				pFlag = false;
				pErrInfo.getContent().append(e.toString());
				pErrInfo.setException(e);
			} finally {
				//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
				if (pFlag == false && pErrInfo.getContent().length() > 0) {
					StackTraceElement[] stackTraceElements = throwable
							.getStackTrace();
					StringBuilder tempBuilder = new StringBuilder(
							stackTraceElements[0].getClassName());
					tempBuilder.append(".");
					tempBuilder.append(stackTraceElements[0].getMethodName());
					tempBuilder.append("-->");

					//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
					if (pErrInfo.getException() != null) {
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
	 * ���ҵ��ָ������Ա�ض�ʱ�������ˡ�����˵���������<br>
	 * @param recordAudit ��װ��Map<String, Integer[]>������Ա��˵�������,String������Ա��ʵ����,Integer[0]��������ˡ�Integer[1]�����
	 * @param userIds ��Աid����
	 * @param beginTime ��⿪ʼʱ��
	 * @param endTime	������ʱ��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	 public boolean findRecordAuditCondition(Map<String, Integer[]> recordAudit, List<Integer> userIds, String beginTime, String endTime, ErrInfo pErrInfo) {
		 boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForDAO(pErrInfo) == false) {
				pFlag = false;
			}
			
			if (pFlag) {
				pErrPos = 2;
				if (archivesInfoWorkProcedureDao.recordAuditCondition(recordAudit, userIds, beginTime, endTime, pErrInfo) == false ) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ҵ��ָ������Ա�ض�ʱ�������ˡ�����˵���������->DAOִ��ʧ�ܡ�");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
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
	 * ��ѯ���о���ҵ��ָ���ҽ�ɫ����Ա��Ϣ����
	 * @param userInfos ���ҳɹ��󷵻ص��û���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean findBusinessGuids(List<UserInfo> userInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			if (checkDaoInjectionForDAO(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				//DAO����
				if (userInfoDao.findBusinessGuids(userInfos, EnumSystemRole.ҵ��ָ��רԱ��ɫ, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ���о���ҵ��ָ���ҽ�ɫ����Ա��Ϣ���� DAOִ��ʧ�ܡ�");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
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
	 * ��ѯҵ��ָ����ָ�������е�����ְ��Ա
	 * @param businessGuidIds ҵ��ָ������Աid����
	 * @param userIds ������ְ��ԱId����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean findPartTimePersons(List<Integer> businessGuidIds, List<Integer> userIds, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForDAO(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				//DAO����
				if (userInfoDao.findPartTimePersons(businessGuidIds, userIds, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯҵ��ָ����ָ�������е�����ְ��Ա DAOִ��ʧ�ܡ�");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
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

	@Override
	public boolean findArchivesinfoManagers(List<UserInfo> userInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForDAO(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				//��ѯ���о��е��������ҽ�ɫ����Ա��Ϣ����
				if (userInfoDao.findArchivesinfoManagers(userInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Service ��ѯ���о��е��������ҽ�ɫ����Ա��Ϣ���� ʧ�ܣ�");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
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

	@Override
	public boolean receiverCondition(List<Integer> counts, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForDAO(pErrInfo) == false) {
				pFlag = false;
			}

			//��ѯ��Ч����->��������ѯ ���������ҽ������
			if (pFlag) {
				pErrPos = 2;
				if (paperTransferBatchesDao.receiverCondition(counts, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ��Ч����->��������ѯ ���������ҽ������ ʧ�ܣ�");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
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
}