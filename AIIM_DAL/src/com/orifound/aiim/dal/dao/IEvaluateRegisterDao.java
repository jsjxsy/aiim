/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.EvaluateRegister;
import com.orifound.aiim.entity.EvaluateRegisterVO;

/**
 * ���˵ǼǱ� (EvaluateRegister)��DAO�ӿڶ���
 *
 */
public interface IEvaluateRegisterDao {

	/**
	 * Dao�ӿڶ��壺��ӿ��˵Ǽ���Ϣ
	 * @param EvaluateRegister Ҫ��ӵĿ��˵Ǽ���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(EvaluateRegister evaluateRegister, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ���Ŀ��˵Ǽ���Ϣ
	 * @param EvaluateRegister Ҫɾ���Ŀ��˵Ǽ���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(EvaluateRegister evaluateRegister, ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺����ɾ��ָ���Ŀ��˵Ǽ���Ϣ
	 * @param evaluateIds Ҫɾ���Ŀ��˵Ǽ�id����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteBatch(List<Integer> evaluateIds, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ���Ŀ��˵Ǽ���Ϣ
	 * @param EvaluateRegister Ҫ���µĿ��˵Ǽ���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(EvaluateRegister evaluateRegister, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������еĿ��˵Ǽ���Ϣ
	 * @param EvaluateRegisters ���ز��ҳɹ��Ŀ��˵Ǽ���Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<EvaluateRegister> evaluateRegisters, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ���ҿ��˵Ǽ���Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param EvaluateRegister ���ز��ҳɹ��Ŀ��˵Ǽ���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, EvaluateRegister evaluateRegister, ErrInfo pErrInfo);
	
	/**
	 * ��ѯ�Ѿ�ͨ�����˵������ȵĿ��˼�¼
	 * @paramn dataPageInfo ����ҳ��Ϣ������
	 * @param evaluateRegisterVOs ���ز��ҳɹ��Ŀ��˵Ǽ���Ϣ��ʾ�༯��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByMaxYear(DataPageInfo dataPageInfo, List<EvaluateRegisterVO> evaluateRegisterVOs, ErrInfo pErrInfo);
	
	/**
	 * ��ѯ�Ѿ�ͨ�����˵�������
	 * @param evaluateRegister ���ؿ��˼�¼��������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findMaxYear(EvaluateRegister evaluateRegister, ErrInfo pErrInfo);
	
	/**
	 * ����ҳ��ѯ�ض���ȵĿ��˼�¼��ʾ��
	 * @param year ��ѯ���
	 * @paramn dataPageInfo ����ҳ��Ϣ������
	 * @param evaluateRegisterVOs ���ز��ҳɹ��Ŀ��˵Ǽ���Ϣ��ʾ�༯��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByYear(String year, List<EvaluateRegisterVO> evaluateRegisterVOs, ErrInfo pErrInfo);

	/**
	 * ��ҳ��ѯ�ض���ȵĿ��˼�¼��ʾ��
	 * @param year ��ѯ���
	 * @paramn dataPageInfo ����ҳ��Ϣ������
	 * @param evaluateRegisterVOs ���ز��ҳɹ��Ŀ��˵Ǽ���Ϣ��ʾ�༯��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByYear(String year, DataPageInfo dataPageInfo, List<EvaluateRegisterVO> evaluateRegisterVOs, ErrInfo pErrInfo);

	/**
	 * �����ض���ȵĿ��˼�¼
	 * @paran year �����¼���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean insertByYear(String year, ErrInfo pErrInfo);
	
	/**
	 * ׷���ض���ȵĿ��˼�¼
	 * @paran year ׷�Ӽ�¼���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean insertAppendByYear(String year, ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺���ݿ��˼�¼��Ų�ѯ���˵Ǽ���ϸ��Ϣ��ʾ��
	 * @param evaluateRegID ���˼�¼���
	 * @param evaluateRegisterVO ���ز��ҳɹ��Ŀ�����Ϣ��ʾ��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByEvaluateRegID(int evaluateRegID, EvaluateRegisterVO evaluateRegisterVO, ErrInfo pErrInfo);
	
	/**
	 * ��ѯ�ض���ȵĿ��˼�¼��
	 * @param year ��ѯ���
	 * @param evaluateRegisterVO ���ز��ҳɹ��Ŀ��˵Ǽ���Ϣ��ʾ��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findCountByYear(String year, EvaluateRegisterVO evaluateRegisterVO, ErrInfo pErrInfo);
	
	/**
	 * �������˵Ǽ���Ϣ
	 * @param evaluateName 	����������
	 * @param dutyId 		ְ��id
	 * @param registerDate 	��������
	 * @param minScore		��С����
	 * @param maxScore		������
	 * @param dataPageInfo	����ҳ��Ϣ������
	 * @param evaluateRegisterVOs ���ز��ҳɹ��Ŀ�����Ϣ��ʾ��
	 * @param pErrInfo 		���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean search(String evaluateName, int dutyId, String registerDate, int minScore, int maxScore, DataPageInfo dataPageInfo, List<EvaluateRegisterVO> evaluateRegisterVOs, ErrInfo pErrInfo);
	
	/**
	 * ���ݿ�����ȼ������˵Ǽ���Ϣ
	 * @param year 			�������
	 * @param evaluateRegisterVOs ���ز��ҳɹ��Ŀ�����Ϣ��ʾ��
	 * @param pErrInfo 		���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean search(String year, List<EvaluateRegisterVO> evaluateRegisterVOs, ErrInfo pErrInfo);
	
	
	/**
	 * ��ȡ���ݿ��д��ڵĿ��˼�¼���
	 * @param evaluatedYears ���ؿ�����ȼ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findEvaluatedYears(List<String> evaluatedYears, ErrInfo pErrInfo);
	
	/**
	 * ��ѯ��ǰ����Ƿ���Ҫ׷���½�����Ա,������ǰ��Ȳ���Ҫ׷��
	 * @param currentYear 	��ǰ���
	 * @param count			count[0] ���治�ڿ��˱����Ա��
	 * @param pErrInfo 		���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findNeedAppend(String currentYear, Integer[] count, ErrInfo pErrInfo);
	
}