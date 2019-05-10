/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.IAppraisalUseScopesDetailManageService;
import com.orifound.aiim.bll.util.CommonUtil;
import com.orifound.aiim.bll.util.StringTool;
import com.orifound.aiim.dal.dao.IAppraisalUseScopesDetailDao;
import com.orifound.aiim.entity.AppraisalUseScopesDetail;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * �������ֿ��Ƽ�����ϸ������ʵ������������
 *
 */
public class AppraisalUseScopesDetailManageServiceImpl implements IAppraisalUseScopesDetailManageService {

	/**
	 * ע�뻮�ؼ����ĵ�����ϸ��ϢDAO
	 */
	@Autowired
	private IAppraisalUseScopesDetailDao appraisalUseScopesDetailDao;
	
	/**
	 * ���캯��
	 */
	public AppraisalUseScopesDetailManageServiceImpl() {

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public AppraisalUseScopesDetailManageServiceImpl(IAppraisalUseScopesDetailDao appraisalUseScopesDetailDao) {
		this.appraisalUseScopesDetailDao = appraisalUseScopesDetailDao;
	}
	
	/**
	 * ��鵵�����ֿ��Ƽ�����ϸ������DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForAppraisalUseScopesDetail(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (appraisalUseScopesDetailDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("�������ֿ��Ƽ�����ϸ������DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
	
	
	
	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IAppraisalUseScopesDetailManageService#deleteAppraisalUseScopesDetail(com.orifound.aiim.entity.AppraisalUseScopesDetail, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteAppraisalUseScopesDetail(
			AppraisalUseScopesDetail appraisalUseScopesDetail, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IAppraisalUseScopesDetailManageService#findAppraisalUseScopesDetailByID(int, com.orifound.aiim.entity.AppraisalUseScopesDetail, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findAppraisalUseScopesDetailByID(int pID,
			AppraisalUseScopesDetail appraisalUseScopesDetail, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IAppraisalUseScopesDetailManageService#findAppraisalUseScopesDetails(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findAppraisalUseScopesDetails(
			List<AppraisalUseScopesDetail> appraisalUseScopesDetails,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IAppraisalUseScopesDetailManageService#saveAppraisalUseScopesDetail(com.orifound.aiim.entity.AppraisalUseScopesDetail, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveOrUpdateAppraisalUseScopesDetail(AppraisalUseScopesDetail appraisalUseScopesDetail, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForAppraisalUseScopesDetail(pErrInfo) == false) {
				pFlag = false;
			}
			
			//��鵵�����ֿ��Ƽ�����ϸ������ʵ���Ƿ�Ϊ��
			if (pFlag) {
				if (appraisalUseScopesDetail == null) {
					pFlag =false;
					pErrInfo.getContent().append("�������ֿ��Ƽ�����ϸ������ʵ��Ƿ�Ϊ�ա�");
				}
			}
			
			//������Լ������Ƿ�Ϊ��
			if (pFlag) {
				if (StringTool.checkNull(appraisalUseScopesDetail.getAppraisalPersion()) == false) {
					pFlag =false;
					pErrInfo.getContent().append("����:�����˷Ƿ�Ϊ�ա�");
				}
			}
			
			//������Լ��������Ƿ�Ϊ��
			if (pFlag) {
				if (appraisalUseScopesDetail.getAppraisalDate() == null) {
					pFlag =false;
					pErrInfo.getContent().append("����:�������ڷǷ�Ϊ�ա�");
				}
			}
			
			//������Ծ����˱�� �Ƿ�Ϊ��
			if (pFlag) {
				if (appraisalUseScopesDetail.getManagerUserID() <= 0) {
					pFlag =false;
					pErrInfo.getContent().append("����:�����˱�ŷǷ�Ϊ�ա�");
				}
			}
			
			
			//��ѯ�Ѿ����ڵĻ�����Ϣ
			AppraisalUseScopesDetail oldAppraisalUseScopesDetail = new AppraisalUseScopesDetail();
			if (pFlag) {
				pErrPos = 2;
				//���ݵ����ڲ���Ų��ҵ������ֿ��Ƽ�����ϸ������ʵ������Ϣ
				if (appraisalUseScopesDetailDao.findByByNBXH(appraisalUseScopesDetail.getArchivesTypeID() ,appraisalUseScopesDetail.getNBXH(), oldAppraisalUseScopesDetail, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ݵ����ڲ���Ų��ҵ������ֿ��Ƽ�����ϸ������ʵ������Ϣ ʧ�ܣ�");
				}
			}
			
			//�ж��Ƿ��Ѿ����ڻ�����Ϣ
			if (pFlag) {
				pErrPos = 3;
				if (oldAppraisalUseScopesDetail.getID() >= 1) {	//���ڻ�����Ϣ ���и��²���
					//����idֵ
					appraisalUseScopesDetail.setID(oldAppraisalUseScopesDetail.getID());
					
					//�ж��Ƿ񻮿ؼ��� ȡ�����н�ɫ
					if(appraisalUseScopesDetail.getRoleIds()==null || appraisalUseScopesDetail.getRoleIds().size()<=0) {
						
						//ɾ�� �������ֿ��Ƽ�����ϸ�����Ϣ
						if (appraisalUseScopesDetailDao.delete(appraisalUseScopesDetail, pErrInfo) == false) {
							pFlag = false;
							pErrInfo.getContent().insert(0, "���� �������ֿ��Ƽ�����ϸ�����Ϣ ʧ�ܣ�");
						}
						
					} else {
						//���� �������ֿ��Ƽ�����ϸ�����Ϣ
						if (appraisalUseScopesDetailDao.update(appraisalUseScopesDetail, pErrInfo) == false) {
							pFlag = false;
							pErrInfo.getContent().insert(0, "���� �������ֿ��Ƽ�����ϸ�����Ϣ ʧ�ܣ�");
						}
						
						pErrPos = 4;
						//�жϵ����Ƿ��Ѿ����ڻ��ؽ�ɫ
						if(oldAppraisalUseScopesDetail.getRoleIds()!=null || oldAppraisalUseScopesDetail.getRoleIds().size()>=1) {
							//ɾ�����ؽ�ɫid����
							List<Integer> deleteRoleIds = oldAppraisalUseScopesDetail.getRoleIds();
							//�������ؽ�ɫid����
							List<Integer> saveRoleIds = new ArrayList<Integer>();
							
							//ѭ�� ҳ�滮�ؼ����Ǽ����ɵĽ�ɫid����
							for(Integer newRoleId : appraisalUseScopesDetail.getRoleIds()) {
								if(oldAppraisalUseScopesDetail.getRoleIds().contains(newRoleId)) {
									//������ؽ�ɫ�Ѿ�����  ��ɾ���б��ȥ
									deleteRoleIds.remove(newRoleId);
								} else {
									//��������� ���в������
									saveRoleIds.add(newRoleId);
								}
							}
							
							pErrPos = 5;
							//�ж��Ƿ����ɾ���Ľ�ɫ
							if(pFlag && deleteRoleIds.size() >= 1) {
								if (appraisalUseScopesDetailDao.deleteRoles(oldAppraisalUseScopesDetail.getID(), deleteRoleIds, pErrInfo) == false) {
									pFlag = false;
									pErrInfo.getContent().insert(0, "ɾ�� �������ؼ�����ɫ ʧ�ܣ�");
								}
							}
							
							pErrPos = 6;
							//�ж��Ƿ���������Ľ�ɫ
							if (pFlag && saveRoleIds.size()>=1) {
								if (appraisalUseScopesDetailDao.saveRoles(oldAppraisalUseScopesDetail.getID(), saveRoleIds, pErrInfo) == false) {
									pFlag = false;
									pErrInfo.getContent().insert(0, "���� �������ؼ�����ɫ ʧ�ܣ�");
								}
							}
						} else {	
							pErrPos = 7;
							//���������ڻ��ؽ�ɫ	ֱ�Ӳ����ɫ
							if (appraisalUseScopesDetailDao.saveRoles(oldAppraisalUseScopesDetail.getID(), appraisalUseScopesDetail.getRoleIds(), pErrInfo) == false) {
								pFlag = false;
								pErrInfo.getContent().insert(0, "���� �������ؼ�����ɫ ʧ�ܣ�");
							}
						}
					}
					
				} else {	//�����ڻ�����Ϣ ������������
					
					//���浵�����ֿ��Ƽ�����ϸ�����Ϣ
					if (appraisalUseScopesDetailDao.save(appraisalUseScopesDetail, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "���浵�����ֿ��Ƽ�����ϸ�����Ϣ ʧ�ܣ�");
					}
					
					
					//���뻮�ؽ�ɫ��Ϣ
					if (pFlag) {
						if (appraisalUseScopesDetailDao.saveRoles(appraisalUseScopesDetail.getID(), appraisalUseScopesDetail.getRoleIds(), pErrInfo) == false) {
							pFlag = false;
							pErrInfo.getContent().insert(0, "���� �������ؼ�����ɫ ʧ�ܣ�");
						}
					}
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IAppraisalUseScopesDetailManageService#updateAppraisalUseScopesDetail(com.orifound.aiim.entity.AppraisalUseScopesDetail, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateAppraisalUseScopesDetail(
			AppraisalUseScopesDetail appraisalUseScopesDetail, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean findAppraisalUseScopesDetailByByNBXH(int archivesTypeID, int NBXH, AppraisalUseScopesDetail appraisalUseScopesDetail, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForAppraisalUseScopesDetail(pErrInfo) == false) {
				pFlag = false;
			}
			
			//����ڲ�����Ƿ�Ϊ��
			if (pFlag) {
				if (NBXH <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("�������->�ڲ���ŷǷ�Ϊ�ա�");
				}
			}

			// ����DAO
			if (pFlag) {
				pErrPos = 2;
				if (appraisalUseScopesDetailDao.findByByNBXH(archivesTypeID, NBXH, appraisalUseScopesDetail, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Service ���ݵ����ڲ���Ų��ҵ������ֿ��Ƽ�����ϸ������ʵ������Ϣ ʧ�ܣ�");
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
	public boolean findWithPage(Map<String, String> params,
			DataPageInfo dataPageInfo,
			List<AppraisalUseScopesDetail> appraisalUseScopesDetails, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForAppraisalUseScopesDetail(pErrInfo) == false) {
				pFlag = false;
			}
			
			//�жϷ�ҳ�����Ƿ�Ϊ��
			if (pFlag) {
				if(dataPageInfo == null || dataPageInfo.getPageSize()<=0) {
					pFlag = false;
					pErrInfo.getContent().append("��������ҳ��Ƿ�Ϊ�ա�");
				}
			}
			
			//�жϴ�ټ�����¼���ϲ���
			if (pFlag) {
				if (appraisalUseScopesDetails == null) {
					pFlag = false;
					pErrInfo.getContent().append("���������ؼ�����¼����Ϊ�ա�");
				}
			}
			
			//���ҳ���ѯ�����Ƿ�Ϊ��
			if (pFlag) {
				if (params == null) {
					pFlag = false;
					pErrInfo.getContent().append("������ҳ���ѯ����Ϊ�ա�");
				}
			}
			
			if (pFlag) {
				pErrPos = 2;
				//��ҳ��ѯ���ż����Ǽ���Ϣ
				
				//�ж��Ƿ��е�����������	��ѯ��һ�����������µ����е�������id
				String archivesTypeId = params.get("archivesTypeId");
				List<Integer> archivesTypeIds = null;
				if(StringTool.checkNull(archivesTypeId)) {
					archivesTypeIds = new ArrayList<Integer>();
					CommonUtil.getAllChildArchivesTypeId(archivesTypeIds, Integer.valueOf(""+archivesTypeId));
				}
				
				if (appraisalUseScopesDetailDao.findWithPage(archivesTypeIds, params, dataPageInfo, appraisalUseScopesDetails, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ҳ��ѯ���ż����Ǽ���Ϣ ʧ�ܣ�");
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
	public boolean findRoleNamesById(int pId, List<String> roleNames,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForAppraisalUseScopesDetail(pErrInfo) == false) {
				pFlag = false;
			}

			//�����������ؼ�����Ϣ ����id��ȡ��Ȩ�����н�ɫ����
			if (pFlag) {
				pErrPos = 2;
				if (appraisalUseScopesDetailDao.findRoleNamesById(pId, roleNames, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Service �����������ؼ�����Ϣ ����id��ȡ��Ȩ�����н�ɫ���� ʧ�ܣ�");
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
