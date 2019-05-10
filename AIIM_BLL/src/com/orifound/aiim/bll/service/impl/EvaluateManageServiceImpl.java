/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.IEvaluateManageService;
import com.orifound.aiim.dal.dao.IEvaluateDetailsDao;
import com.orifound.aiim.dal.dao.IEvaluateRegisterDao;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.EvaluateDetails;
import com.orifound.aiim.entity.EvaluateRegister;
import com.orifound.aiim.entity.EvaluateRegisterVO;

/**
 * ���˹������ʵ����
 * @author tyb
 *
 */
public class EvaluateManageServiceImpl implements IEvaluateManageService {
	/**
	 * ���˵ǼǱ�����ݷ��ʶ���
	 */
	@Autowired
	private IEvaluateRegisterDao evaluateRegisterDao ;
	
	/**
	 * ���˵Ǽ���ϸ������ݷ��ʶ���
	 */
	@Autowired
	private IEvaluateDetailsDao evaluateDetailsDao;
	
	/**
	 * ��鿼�˵ǼǱ��DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForEvaluateRegister(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (evaluateRegisterDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("���˵ǼǱ��DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
			
			if (pFlag) {
				if (evaluateDetailsDao == null) {
					pFlag = false;
					pErrInfo.getContent().append("���˵Ǽ���ϸ���DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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


	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IEvaluateManageService#findByMaxYear(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByMaxYear(DataPageInfo dataPageInfo, List<EvaluateRegisterVO> evaluateRegisterVOs, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForEvaluateRegister(pErrInfo) == false) {
				pFlag = false;
			}
			
			//��� ���˵Ǽ���Ϣ��ʾ�༯�� �Ƿ�Ϊ��
			if (pFlag) {
				if (evaluateRegisterVOs == null) {
					pFlag = false;
					pErrInfo.getContent().append("�������->���˵Ǽ���Ϣ��ʾ�༯�ϷǷ�Ϊ�ա�");
				}
			}

			if (pFlag) {
				pErrPos = 2;
				//��ѯ�Ѿ�ͨ�����˵������ȵĿ��˼�¼
				if (evaluateRegisterDao.findByMaxYear(dataPageInfo, evaluateRegisterVOs, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ�Ѿ�ͨ�����˵������ȵĿ��˼�¼ ʧ�ܣ�");
				}
				
				
				if(evaluateRegisterVOs.size() <= 0) {	//û�п��˼�¼����ʾ��ǰ�����Ҫ���˵����п�����Ա
					String maxYear = new SimpleDateFormat("yyyy").format(new Date());
					
					//��ѯ��ǰ��ȿ��˼�¼
					if (findByYear(maxYear, dataPageInfo, evaluateRegisterVOs, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "��ѯ��ǰ��ȿ��˼�¼ ʧ�ܣ�");
					}
					
					if(evaluateRegisterVOs.size() <= 0) {	//�����ڵ�ǰ��ȵĿ��˼�¼
						//���뵱ǰ��ȵĿ��˼�¼
						if (pFlag) {
							if(evaluateRegisterDao.insertByYear(maxYear, pErrInfo) == false ) {
								pFlag = false;
								pErrInfo.getContent().insert(0, "���뵱ǰ��ȵĿ��˼�¼ ʧ�ܣ�");
							}
						}
						
						//���뵱ǰ��ȵĿ��˼�¼��ϸ
						if (pFlag) {
							if (evaluateDetailsDao.insertByYear(maxYear, pErrInfo) == false) {
								pFlag = false;
								pErrInfo.getContent().insert(0, "���뵱ǰ��ȵĿ��˼�¼��ϸ ʧ�ܣ�");
							}
						}
						
						//��ѯ��ǰ��ȿ��˼�¼
						if (pFlag) {
							if (findByYear(maxYear, dataPageInfo, evaluateRegisterVOs, pErrInfo) == false) {
								pFlag = false;
								pErrInfo.getContent().insert(0, "��ѯ��ǰ��ȿ��˼�¼ ʧ�ܣ�");
							}
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


	@Override
	public boolean findByYear(String year, DataPageInfo dataPageInfo, List<EvaluateRegisterVO> evaluateRegisterVOs, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForEvaluateRegister(pErrInfo) == false) {
				pFlag = false;
			}
			
			//��� ����Ƿ�Ϊ��
			if (pFlag) {
				if (year==null || "".equals(year.trim())) {
					pFlag = false;
					pErrInfo.getContent().append("�������->������ȷǷ�Ϊ�㡣");
				}
			}
			
			//��� ����Ƿ�Ϊ������
			if (pFlag) {
				if (year.matches("[0-9]*") == false) {
					pFlag = false;
					pErrInfo.getContent().append("�������->������Ȳ��Ǵ����ַǷ���");
				}
			}
			
			//��� ���˵Ǽ���Ϣ��ʾ�༯�� �Ƿ�Ϊ��
			if (pFlag) {
				if (evaluateRegisterVOs == null) {
					pFlag = false;
					pErrInfo.getContent().append("�������->���˵Ǽ���Ϣ��ʾ�༯�ϷǷ�Ϊ�㡣");
				}
			}

			if (pFlag) {
				pErrPos = 2;
				//����DAO ���ض���ȵĿ��˼�¼
				if (evaluateRegisterDao.findByYear(year, dataPageInfo, evaluateRegisterVOs, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ض���ȵĿ��˼�¼ ʧ�ܣ�");
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
	public boolean findEvaluateDetailsByRegID(int evaluateRegID, EvaluateRegisterVO evaluateRegisterVO, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForEvaluateRegister(pErrInfo) == false) {
				pFlag = false;
			}
			
			//��鿼�˼�¼���
			if (pFlag) {
				if (evaluateRegID <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("�������->���˼�¼��ŷǷ�Ϊ�㡣");
				}
			}
			
			//��� ������Ϣ��ʾ��
			if (pFlag) {
				if (evaluateRegisterVO == null) {
					pFlag = false;
					pErrInfo.getContent().append("�������->����Ϣ��ʾ��Ƿ�Ϊ�ա�");
				}
			}

			if (pFlag) {
				pErrPos = 2;
				//����DAO ��ѯ���˵Ǽ���ϸ��Ϣ
				if (evaluateRegisterDao.findByEvaluateRegID(evaluateRegID, evaluateRegisterVO, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ض���ȵĿ��˼�¼ ʧ�ܣ�");
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
	public boolean updateDetail(EvaluateDetails evaluateDetails,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForEvaluateRegister(pErrInfo) == false) {
				pFlag = false;
			}
			
			//��� ���˵ǼǱ��
			if (pFlag) {
				if (evaluateDetails.getEvaluateRegID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("�������->���˵ǼǱ�ŷǷ���");
				}
			}
			
			//��� ������ϸ���
			if (pFlag) {
				if (evaluateDetails.getEvaluateItemID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("�������->������ϸ��ŷǷ���");
				}
			}
			
			//�ж� ����
			if (pFlag) {
				if (evaluateDetails.getScore() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("�������->���ַ����Ƿ���");
				}
			}
			
			//����DAO ���¿�����ϸ
			if (pFlag) {
				pErrPos = 2;
				if (evaluateDetailsDao.update(evaluateDetails, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "����ָ���Ŀ�����ϸ��Ϣ ʧ�ܡ�");
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
	public boolean updateEvaluate(EvaluateRegister evaluateRegister, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForEvaluateRegister(pErrInfo) == false) {
				pFlag = false;
			}
			
			//����ܷ���
			if (pFlag) {
				if (evaluateRegister.getScore() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("�������->�ܷ����Ƿ���");
				}
			}
			
			//��� ������id
			if (pFlag) {
				if (evaluateRegister.getCheckerUserID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("�������->������id�Ƿ���");
				}
			}
			
			//��� ������ְ��id
			if (pFlag) {
				if (evaluateRegister.getCheckerDutyID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("�������->������ְ��id�Ƿ���");
				}
			}
			
			//��� ���˵ȼ�id
			if (pFlag) {
				if (evaluateRegister.getLevelID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("�������->���˵ȼ�id�Ƿ���");
				}
			}
			if (pFlag) {
				pErrPos = 2;
				//����DAO ���¿��˵Ǽ���Ϣ
				if (evaluateRegisterDao.update(evaluateRegister, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���¿��˵Ǽ���Ϣ ʧ�ܣ�");
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
	public boolean insertAppendByYear(String year, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForEvaluateRegister(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				//����DAO ׷���ض���ȵĿ��˼�¼
				if (evaluateRegisterDao.insertAppendByYear(year, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "׷���ض���ȵĿ��˼�¼ ʧ�ܣ�");
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
	public boolean insertByYear(String year, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForEvaluateRegister(pErrInfo) == false) {
				pFlag = false;
			}
			
			//��� ����Ƿ�Ϊ��
			if (pFlag) {
				if (year==null || "".equals(year.trim())) {
					pFlag = false;
					pErrInfo.getContent().append("�������->������ȷǷ�Ϊ�㡣");
				}
			}
			
			//��� ����Ƿ�Ϊ������
			if (pFlag) {
				if (year.matches("[0-9]*") == false) {
					pFlag = false;
					pErrInfo.getContent().append("�������->������Ȳ��Ǵ����ַǷ���");
				}
			}
			
			//��鿼������Ƿ�Ϊ��ǰ���
			if (pFlag) {
				int intYear = Integer.valueOf(year);
				if(intYear != Calendar.getInstance().get(Calendar.YEAR)) {
					pFlag = false;
					pErrInfo.getContent().append("�������->������Ȳ���������ȣ����ܲ��뿼�˼�¼��");
				}
			}
			
			if (pFlag) {
				pErrPos = 2;
				//�����ض���ȵĿ��˼�¼
				if (evaluateRegisterDao.insertByYear(year, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�����ض���ȵĿ��˼�¼ ʧ�ܣ�");
				}
			}
			
			if (pFlag) {
				//���뵱ǰ��ȵĿ�����ϸ��¼
				if (evaluateDetailsDao.insertByYear(year, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�����ض���ȵĿ�����ϸ��¼ ʧ�ܣ�");
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
	public boolean search(String evaluateName, int dutyId, String registerDate,
			int minScore, int maxScore, DataPageInfo dataPageInfo,
			List<EvaluateRegisterVO> evaluateRegisterVOs, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForEvaluateRegister(pErrInfo) == false) {
				pFlag = false;
			}
			
			//�������˵Ǽ���Ϣ
			if (pFlag) {
				pErrPos = 2;
				if (evaluateRegisterDao.search(evaluateName, dutyId, registerDate, minScore, maxScore, dataPageInfo, evaluateRegisterVOs, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�������˵Ǽ���Ϣ ʧ�ܣ�");
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


	public IEvaluateRegisterDao getEvaluateRegisterDao() {
		return evaluateRegisterDao;
	}


	public void setEvaluateRegisterDao(IEvaluateRegisterDao evaluateRegisterDao) {
		this.evaluateRegisterDao = evaluateRegisterDao;
	}


	public IEvaluateDetailsDao getEvaluateDetailsDao() {
		return evaluateDetailsDao;
	}


	public void setEvaluateDetailsDao(IEvaluateDetailsDao evaluateDetailsDao) {
		this.evaluateDetailsDao = evaluateDetailsDao;
	}


	@Override
	public boolean deleteBatch(List<Integer> evaluateIds, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForEvaluateRegister(pErrInfo) == false) {
				pFlag = false;
			}
			
			//��� ���˵Ǽ���Ϣid�����Ƿ�Ϊ��
			if (pFlag) {
				if (evaluateIds==null || evaluateIds.size()<=0) {
					pFlag = false;
					pErrInfo.getContent().append("�������->���˵Ǽ���Ϣid���ϷǷ�Ϊ�ա�");
				}
			}

			if (pFlag) {
				pErrPos = 2;
				//����ɾ��ָ���Ŀ��˵Ǽ���Ϣ
				if (pFlag) {
					if (evaluateRegisterDao.deleteBatch(evaluateIds, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "����ɾ��ָ���Ŀ��˵Ǽ���Ϣ ʧ�ܣ�");
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


	@Override
	public boolean findCountByYear(String year, EvaluateRegisterVO evaluateRegisterVO, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForEvaluateRegister(pErrInfo) == false) {
				pFlag = false;
			}
			
			//��� ����Ƿ�Ϊ��
			if (pFlag) {
				if (year==null || "".equals(year.trim())) {
					pFlag = false;
					pErrInfo.getContent().append("�������->������ȷǷ�Ϊ�㡣");
				}
			}
			
			//��� ����Ƿ�Ϊ������
			if (pFlag) {
				if (year.matches("[0-9]*") == false) {
					pFlag = false;
					pErrInfo.getContent().append("�������->������Ȳ��Ǵ����ַǷ���");
				}
			}

			if (pFlag) {
				pErrPos = 2;
				//��ѯ�ض���ȵĿ��˼�¼��
				if (evaluateRegisterDao.findCountByYear(year, evaluateRegisterVO, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Service��ѯ�ض���ȵĿ��˼�¼�� ʧ�ܣ�");
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
	public boolean findEvaluatedYears(List<String> evaluatedYears, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForEvaluateRegister(pErrInfo) == false) {
				pFlag = false;
			}
			
			if (pFlag) {
				if (evaluatedYears == null) {
					pFlag = false;
					pErrInfo.getContent().append("������ȼ��ϷǷ�Ϊ�ա�");
				}
			}

			if (pFlag) {
				pErrPos = 2;
				//��ȡ���ݿ��д��ڵĿ��˼�¼���
				if (evaluateRegisterDao.findEvaluatedYears(evaluatedYears, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Service ��ȡ���ݿ��д��ڵĿ��˼�¼��� ʧ�ܣ�");
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
	public boolean search(String year, List<EvaluateRegisterVO> evaluateRegisterVOs, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForEvaluateRegister(pErrInfo) == false) {
				pFlag = false;
			}
			
			//��� ����Ƿ�Ϊ��
			if (pFlag) {
				if (year==null || "".equals(year.trim())) {
					pFlag = false;
					pErrInfo.getContent().append("�������->������ȷǷ�Ϊ�㡣");
				}
			}
			
			//��� ����Ƿ�Ϊ������
			if (pFlag) {
				if (year.matches("[0-9]*") == false) {
					pFlag = false;
					pErrInfo.getContent().append("�������->������Ȳ��Ǵ����ַǷ���");
				}
			}
			
			//��� ���˵Ǽ���Ϣ��ʾ�༯�� �Ƿ�Ϊ��
			if (pFlag) {
				if (evaluateRegisterVOs == null) {
					pFlag = false;
					pErrInfo.getContent().append("�������->���˵Ǽ���Ϣ��ʾ�༯�ϷǷ�Ϊ�㡣");
				}
			}

			if (pFlag) {
				pErrPos = 2;
				if (evaluateRegisterDao.search(year, evaluateRegisterVOs, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�������˵Ǽ���Ϣ ");
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
	public boolean findNeedAppend(String currentYear, Integer[] count, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForEvaluateRegister(pErrInfo) == false) {
				pFlag = false;
			}
			
			//�����Ա���������Ƿ�Ϊ��
			if (pFlag) {
				if (count==null || count.length<=0) {
					pFlag = false;
					pErrInfo.getContent().append("��Ա��������Ƿ�Ϊ�ա�");
				}
			}

			//��ѯ��ǰ����Ƿ���Ҫ׷���½�����Ա
			if (pFlag) {
				pErrPos = 2;
				if (evaluateRegisterDao.findNeedAppend(currentYear, count, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ��ǰ����Ƿ���Ҫ׷���½�����Ա ʧ�ܣ�");
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
		
System.out.println(pErrInfo.toString());

		return pFlag;
	}


	@Override
	public boolean findMaxYear(EvaluateRegister evaluateRegister, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForEvaluateRegister(pErrInfo) == false) {
				pFlag = false;
			}
			
			//��⿼�˼�¼�Ƿ�Ϊ��
			if (pFlag) {
				if (evaluateRegister == null) {
					pFlag = false;
					pErrInfo.getContent().append("���˼�¼�Ƿ�Ϊ�ա�");
				}
			}

			//��ѯ�Ѿ�ͨ�����˵�������
			if (pFlag) {
				pErrPos = 2;
				if (evaluateRegisterDao.findMaxYear(evaluateRegister, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ�Ѿ�ͨ�����˵������� ʧ��");
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