/**
 * 
 */
package com.orifound.aiim.bll.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.orifound.aiim.entity.ArchivesInfoQueryCondition;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.EnumColumnDataType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.ArchivesTypeEx;
import com.orifound.aiim.entity.OfficialArchivesInfoQueryCondition;
import com.orifound.aiim.entity.PaperTransferBatchesArchvTypeDetail;
import com.orifound.aiim.entity.SystemInitializer;

/**
 * ����������
 * 
 */
public class CommonUtil {


	/**
	 * ��ȡ����ֵ��ȫ�ֵ�ϵͳ��ʼ��������ʵ��
	 * @return ȫ�ֵ�ϵͳ��ʼ��������ʵ��
	 */
	public static SystemInitializer getSystemInitializer()
	{
		return SystemInitializer.getInstance();
	}
	
	/**
	 * ��ȡϵͳ����״���������������¡
	 * @param clonedArchivesTypes ���ؿ�¡����״��������
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public static boolean cloneSystemArchivesTypes(LinkedHashMap<Integer, ArchivesType> clonedArchivesTypes,ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		try
		{
			//���ϵͳ��ʼ��������ĵ������༯���Ƿ���ֵ
			pErrPos = 1;
			if (getSystemInitializer().getArchivesTypes() == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("ϵͳ��ʼ���������״�������༯�ϷǷ�Ϊ�գ����Ƚ���ϵͳ��ʼ��������");
			}
			else
			{
				if (getSystemInitializer().getArchivesTypes().size() == 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("ϵͳ��ʼ���������״�������༯�ϷǷ�Ϊ�գ���ȷ��ϵͳ���ݿ��д��ڵ��������ֵ���Ϣ��");
				}
			}
			
			if (pFlag)
			{
				pErrPos = 2;
				if (getSystemInitializer().getArchivesTypes()!=null)
				{
					for (ArchivesType item : getSystemInitializer().getArchivesTypes().values())
					{
						clonedArchivesTypes.put(item.getID(), item.clone());
					}
				}
			}
		}
		catch (Exception e)
		{
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null)
				{
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
	 * ��ϵͳ����״���������������¡Ϊ��״��չ�������༯��
	 * @param clonedArchivesTypeExs ���ؿ�¡����״��չ��������
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public static boolean cloneSystemArchivesTypesToEx(LinkedHashMap<Integer, ArchivesTypeEx> clonedArchivesTypeExs,ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		try
		{
			//���ϵͳ��ʼ��������ĵ������༯���Ƿ���ֵ
			pErrPos = 1;
			if (getSystemInitializer().getArchivesTypes() == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("ϵͳ��ʼ���������״�������༯�ϷǷ�Ϊ�գ����Ƚ���ϵͳ��ʼ��������");
			}
			else
			{
				if (getSystemInitializer().getArchivesTypes().size() == 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("ϵͳ��ʼ���������״�������༯�ϷǷ�Ϊ�գ���ȷ��ϵͳ���ݿ��д��ڵ��������ֵ���Ϣ��");
				}
			}
			
			if (pFlag)
			{
				pErrPos = 2;
				if (getSystemInitializer().getArchivesTypes()!=null)
				{
					for (ArchivesType item : getSystemInitializer().getArchivesTypes().values())
					{
						clonedArchivesTypeExs.put(item.getID(),new ArchivesTypeEx(item.clone()));
					}
				}
			}
		}
		catch (Exception e)
		{
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null)
				{
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
	 * ����״�ṹ�ĵ������༯��ת��Ϊƽ��ṹ
	 * @param treeArchivesTypes Ҫת������״�������༯��
	 * @param planeArchivesTypes ����ƽ��ṹ�ĵ������༯��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public static boolean convertTreeArchivesTypesToPlane(LinkedHashMap<Integer, ArchivesType> treeArchivesTypes, Map<Integer, ArchivesType> planeArchivesTypes, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			// �����״�ṹ�ĵ��������Ƿ�Ϊ��
			pErrPos = 1;
			if (treeArchivesTypes == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("��״�ṹ�ĵ���������Ϣ�Ƿ�Ϊ�ա�");
			}
			else
			{
				if (treeArchivesTypes.size() == 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("��״�ṹ�ĵ���������Ϣ�Ƿ�Ϊ�ա�");
				}
			}

			// ��ʼת��
			if (pFlag)
			{
				for (ArchivesType item : treeArchivesTypes.values())
				{
					// ����ǰ������뷵�ؽ������
					planeArchivesTypes.put(item.getID(), item);

					// �����ǰ�����»����¼����࣬��ݹ鴦��
					if (item.getChildArchivesTypes() != null)
					{
						if (item.getChildArchivesTypes().size() > 0)
						{
							if (convertTreeArchivesTypesToPlane(item.getChildArchivesTypes(), planeArchivesTypes, pErrInfo) == false)
							{
								pFlag = false;
								pErrInfo.getContent().insert(0, "��״�ṹ�ĵ�������ת��Ϊƽ��ṹʧ��: ");
							}
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			// �쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// ���پֲ�����
			throwable = null;
		}

		return pFlag;
	}
	
	
	/**
	 * ��ָ���ĵ����������л�ȡ��ĩ�ڵ�ĵ�������
	 * @param treeArchivesTypes Ҫת������״�������༯��
	 * @param planeArchivesTypes ����ƽ��ṹ�ĵ������༯��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */ 
	public static boolean getEndArchivesTypesByTree(LinkedHashMap<Integer, ArchivesType> treeArchivesTypes, Map<Integer, ArchivesType> endArchivesTypes, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			// �����״�ṹ�ĵ��������Ƿ�Ϊ��
			pErrPos = 1;
			if (treeArchivesTypes == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("��״�ṹ�ĵ���������Ϣ�Ƿ�Ϊ�ա�");
			}
			else
			{
				if (treeArchivesTypes.size() == 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("��״�ṹ�ĵ���������Ϣ�Ƿ�Ϊ�ա�");
				}
			}

			// ��ʼת��
			if (pFlag)
			{
				for (ArchivesType item : treeArchivesTypes.values())
				{
					if(item.getEndFlag()==true){
						// ����ǰ����ĩ�ڵ�ķ�����뷵�ؽ������
						endArchivesTypes.put(item.getID(), item);
					}					

					// �����ǰ�����»����¼����࣬��ݹ鴦��
					if (item.getChildArchivesTypes() != null)
					{
						if (item.getChildArchivesTypes().size() > 0)
						{
							if (getEndArchivesTypesByTree(item.getChildArchivesTypes(), endArchivesTypes, pErrInfo) == false)
							{
								pFlag = false;
								pErrInfo.getContent().insert(0, "��ָ���ĵ����������л�ȡ��ĩ�ڵ�ĵ�������ʧ��: ");
							}
						}
					}
				}
			}
			
			
		}catch (Exception e){
			// �쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}finally{
			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// ���پֲ�����
			throwable = null;
		}

		return pFlag;
	}
	
	
	
	/**
	 * �Ƴ�ָ����չ�����������в�������ָ��ƽ����չ�������༯���е���ĩ�ڵ�<br>
	 * �������еĴ�ƽ��ṹ�еĶ�ӦԪ�ؽ��п�¡����
	 * @param treeArchivesTypeExs ָ������״��չ�������༯��
	 * @param planeArchivesTypeExs ָ����ƽ��ṹ��չ�������༯��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private static boolean removeEndArchivesTypeExNotExitsPlane(LinkedHashMap<Integer, ArchivesTypeEx> treeArchivesTypeExs,Map<Integer, ArchivesTypeEx> planeArchivesTypeExs, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//�����չ��״���������Ƿ�Ϊ��
			if (treeArchivesTypeExs==null)
			{
				pFlag = false;
				pErrInfo.getContent().append("��״��չ�������༯�ϷǷ�Ϊ�ա�");
			}
			
			//���ƽ����չ�������༯���Ƿ�Ϊ��
			if (pFlag)
			{
				if (planeArchivesTypeExs==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("ƽ����չ�������༯�ϷǷ�Ϊ�ա�");
				}
			}
			
			//��ʼ����
			if (pFlag)
			{
				//ѭ�����������е�Ԫ�أ��ж��Ƿ�����ӷ���
				if (treeArchivesTypeExs.size()>0)
				{
					//��Ҫ�Ƴ��Ĺؼ��ּ���
					List<Integer> needRemoveKeys=new ArrayList<Integer>();
					
					for (ArchivesTypeEx item : treeArchivesTypeExs.values())
					{
						//��������ӷ��࣬��ݹ鴦��
						if (item.getChildArchivesTypeExs()!=null)
						{
							if (removeEndArchivesTypeExNotExitsPlane(item.getChildArchivesTypeExs(),planeArchivesTypeExs, pErrInfo)==false)
							{
								pFlag = false;
								pErrInfo.getContent().insert(0, "�Ƴ�ָ����״��չ�������༯���еĵײ���չ��������ڵ�ʧ��: ");
							}
							//�ӷ��ദ������˺��п����ӷ��඼�Ƴ��ˣ����û���ӷ����ˣ���ǰ�ڵ�Ҳ��Ҫ�Ƴ�
							//�Ƚ��ؼ��ֱ�������Ҫ�Ƴ��Ĺؼ��ּ����У������ٱ����Ƴ�֮
							else
							{
								if (item.getChildArchivesTypeExs().size()==0)
								{
									needRemoveKeys.add(item.getID());
								}
							}
						}
						//���û���ӷ������һ���ж�
						else 
						{
							//�������ײ㵵������ڵ㣬���ж��Ƿ������ƽ�浵�����༯����
							if (item.getEndFlag()==true)
							{
								//��������ڣ�����ؼ��ֱ�������Ҫ�Ƴ��Ĺؼ��ּ����У������ٱ����Ƴ�֮
								if (planeArchivesTypeExs.containsKey(item.getID())==false)
								{
									needRemoveKeys.add(item.getID());
								}
								//������ڣ�������¡���Ը��������չ����ֵ����״��չ�������༯����
								else 
								{
									item.cloneExtendProperty(planeArchivesTypeExs.get(item.getID()));
								}
							}
							//���������ײ���࣬��ֱ���Ƴ����Ƚ���ؼ��ֱ�������Ҫ�Ƴ��Ĺؼ��ּ����У������ٱ����Ƴ�֮
							else
							{
								needRemoveKeys.add(item.getID());
							}
						}
						
						//��������ѭ��
						if (pFlag==false)
						{
							break;
						}
					}
					
					//����Ҫ�Ƴ��Ĺؼ��ּ��ϣ��ӵ�ǰ��״�������༯��������Ƴ�֮
					if (needRemoveKeys.size()>0)
					{
						for (Integer key : needRemoveKeys)
						{
							if (treeArchivesTypeExs.containsKey(key))
							{
								treeArchivesTypeExs.remove(key);
							}
						}
					}
					
					needRemoveKeys=null;
				}
			}
		}
		catch (Exception e)
		{
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null)
				{
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
	 * �Ƴ�ָ�������������в�������ָ��ƽ�浵�����༯���е���ĩ�ڵ�
	 * @param treeArchivesTypes ָ������״�������༯��
	 * @param planeArchivesTypes ָ����ƽ��ṹ�������༯��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private static boolean removeEndArchivesTypeNotExitsPlane(LinkedHashMap<Integer, ArchivesType> treeArchivesTypes,Map<Integer, ArchivesType> planeArchivesTypes, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//�����״���������Ƿ�Ϊ��
			if (treeArchivesTypes==null)
			{
				pFlag = false;
				pErrInfo.getContent().append("��״�������༯�ϷǷ�Ϊ�ա�");
			}
			
			//���ƽ�浵�����༯���Ƿ�Ϊ��
			if (pFlag)
			{
				if (planeArchivesTypes==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("ƽ�浵�����༯�ϷǷ�Ϊ�ա�");
				}
			}
			
			//��ʼ����
			if (pFlag)
			{
				//ѭ�����������е�Ԫ�أ��ж��Ƿ�����ӷ���
				if (treeArchivesTypes.size()>0)
				{
					//��Ҫ�Ƴ��Ĺؼ��ּ���
					List<Integer> needRemoveKeys=new ArrayList<Integer>();
					
					for (ArchivesType item : treeArchivesTypes.values())
					{
						//��������ӷ��࣬��ݹ鴦��
						if (item.getChildArchivesTypes()!=null)
						{
							if (removeEndArchivesTypeNotExitsPlane(item.getChildArchivesTypes(),planeArchivesTypes, pErrInfo)==false)
							{
								pFlag = false;
								pErrInfo.getContent().insert(0, "�Ƴ�ָ����״�������༯���еĵײ㵵������ڵ�ʧ��: ");
							}
							else
							{
								//�ӷ��ദ������˺��п����ӷ��඼�Ƴ��ˣ����û���ӷ����ˣ���ǰ�ڵ�Ҳ��Ҫ�Ƴ�
								//�Ƚ��ؼ��ֱ�������Ҫ�Ƴ��Ĺؼ��ּ����У������ٱ����Ƴ�֮
								if (item.getChildArchivesTypes().size()==0)
								{
									needRemoveKeys.add(item.getID());
								}
								//������������࣬���һ���ж��Ƿ������ƽ�浵�����༯���У�����������¡�������ֵ������������ԴȨ����Ϣ��
								else 
								{
									if (planeArchivesTypes.containsKey(item.getID()))
									{
										item.cloneBaseRightArchivesResourceFrom(planeArchivesTypes.get(item.getID()));
									}
								}
							}
						}
						//���û���ӷ������һ���ж�
						else 
						{
							//�������ײ㵵������ڵ㣬���ж��Ƿ������ƽ�浵�����༯����
							if (item.getEndFlag()==true)
							{
								//��������ڣ�����ؼ��ֱ�������Ҫ�Ƴ��Ĺؼ��ּ����У������ٱ����Ƴ�֮
								if (planeArchivesTypes.containsKey(item.getID())==false)
								{
									needRemoveKeys.add(item.getID());
								}
								//����������¡�������ֵ������������ԴȨ����Ϣ��
								else 
								{
									item.cloneBaseRightArchivesResourceFrom(planeArchivesTypes.get(item.getID()));
								}
							}
							//���������ײ������û�����࣬��ֱ���Ƴ����Ƚ���ؼ��ֱ�������Ҫ�Ƴ��Ĺؼ��ּ����У������ٱ����Ƴ�֮
							else
							{
								needRemoveKeys.add(item.getID());
							}
						}
						
						//��������ѭ��
						if (pFlag==false)
						{
							break;
						}
					}
					
					//����Ҫ�Ƴ��Ĺؼ��ּ��ϣ��ӵ�ǰ��״�������༯��������Ƴ�֮
					if (needRemoveKeys.size()>0)
					{
						for (Integer key : needRemoveKeys)
						{
							if (treeArchivesTypes.containsKey(key))
							{
								treeArchivesTypes.remove(key);
							}
						}
					}
					
					needRemoveKeys=null;
				}
			}
		}
		catch (Exception e)
		{
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null)
				{
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
	 * �ο�ϵͳ������������ƽ���͵���ײ㵵������ڵ㼯��ת��Ϊ��״�ṹ�ĵ������༯��<br>
	 * ���������ϼ����������·����ι�ϵ
	 * @param planeArchivesTypes Ҫת����ƽ���͵������༯��
	 * @param treeArchivesTypes ����ת�������״�ṹ�ĵ������༯��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public static boolean convertPlaneArchivesTypesToTree(Map<Integer, ArchivesType> planeArchivesTypes, LinkedHashMap<Integer, ArchivesType> treeArchivesTypes, ErrInfo pErrInfo) 
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		try 
		{
			//���ƽ���͵ĵ������༯���Ƿ�Ϊ��
			pErrPos = 1;
			if (pFlag) 
			{
				if (planeArchivesTypes==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("ƽ���͵ĵ������༯�ϷǷ�Ϊ�ա�");
				}
			}
			
			//�Ƚ�ϵͳ�����������Ŀ�¡�汾���������صĵ�����������
			if (pFlag)
			{
				pErrPos = 2;
				if (cloneSystemArchivesTypes(treeArchivesTypes, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "��¡ϵͳ����������ʧ��: ");
				}
			}
			
			//Ȼ���Ҫ���صĵ������������Ƴ�����Щ��������ƽ�浵�����༯���е���ײ㵵������ڵ�
			if (pFlag)
			{
				if (removeEndArchivesTypeNotExitsPlane(treeArchivesTypes, planeArchivesTypes, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ָ����״�������༯�����Ƴ���������ָ��ƽ�浵�����༯���е���ײ㵵������ڵ�ʧ��: ");
				}
			}
		} 
		catch (Exception e) 
		{
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} 
		finally 
		{
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				
				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException()!=null)
				{
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
	 * �ο�ϵͳ������������ƽ������չ����ײ㵵������ڵ㼯��ת��Ϊ��״�ṹ����չ�������༯��<br>
	 * ���������ϼ���չ���������·����ι�ϵ
	 * @param planeArchivesTypeExs Ҫת����ƽ������չ�������༯��
	 * @param treeArchivesTypeExs ����ת�������״�ṹ����չ�������༯��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public static boolean convertPlaneArchivesTypeExsToTree(Map<Integer, ArchivesTypeEx> planeArchivesTypeExs, LinkedHashMap<Integer, ArchivesTypeEx> treeArchivesTypeExs, ErrInfo pErrInfo) 
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		try 
		{
			//���ƽ���͵���չ�������༯���Ƿ�Ϊ��
			pErrPos = 1;
			if (pFlag) 
			{
				if (planeArchivesTypeExs==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("ƽ���͵���չ�������༯�ϷǷ�Ϊ�ա�");
				}
			}
			
			//�ȸ���ϵͳ�����������Ŀ�¡��һ����չ�������������������ص���չ������������
			if (pFlag)
			{
				pErrPos = 2;
				if (cloneSystemArchivesTypesToEx(treeArchivesTypeExs, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "��¡ϵͳ��������������չ����������ʧ��: ");
				}
			}
			
			//Ȼ���Ҫ���صĵ������������Ƴ�����Щ��������ƽ�浵�����༯���е���ײ㵵������ڵ㣬���ڵĴ�ƽ��ṹԪ���п�¡��չ����ֵ��������������
			if (pFlag)
			{
				if (removeEndArchivesTypeExNotExitsPlane(treeArchivesTypeExs, planeArchivesTypeExs, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ָ����״��չ�������༯�����Ƴ���������ָ��ƽ����չ�������༯���е���ײ㵵������ڵ�ʧ��: ");
				}
			}
		} 
		catch (Exception e) 
		{
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} 
		finally 
		{
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				
				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException()!=null)
				{
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
	 * ��ƽ���͵�ֽ�ʵ����ƽ����ε���������ϸ�������ת��Ϊҳ���������״�ṹ�ĵ�������ڵ㼯��
	 * @param archivesTypes �������༯�ϣ�һ�����ࣩ
	 * @param archivesTypeExs ֽ�ʵ����ƽ����ε���������ϸ����ڵ㼯��
	 * @param treeArchivesTypeExs ����ת�������״�����ƽ��ĵ������ͽڵ㼯��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public static boolean getArchivesTypeTreeFromArchvTypeDetails(Map<Integer, ArchivesType> archivesTypes,Map<Integer, PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetails,Map<Integer, ArchivesTypeEx> treeArchivesTypeExs, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			

			//��ʼ����2...
			if (pFlag) {

			}
		} catch (Exception e) {
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				
				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException()!=null)
				{
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
	 * ���ָ����ŵĵ����������Ƿ�Ϸ�
	 * @param archivesTypeID ����������
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public static boolean checkArchivesType(int archivesTypeID,ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			if (archivesTypeID<=0)
			{
				pFlag = false;
				pErrInfo.getContent().append("���������ŷǷ�Ϊ0");
			}
			
			//���ϵͳ��ʼ��������ĵ������༯���Ƿ���ֵ
			if (pFlag)
			{
				pErrPos = 1;
				if (getSystemInitializer().getArchivesTypes() == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("ϵͳ��ʼ���������״�������༯�ϷǷ�Ϊ�գ����Ƚ���ϵͳ��ʼ��������");
				}
				else
				{
					if (getSystemInitializer().getArchivesTypes().size() == 0)
					{
						pFlag = false;
						pErrInfo.getContent().append("ϵͳ��ʼ���������״�������༯�ϷǷ�Ϊ�գ���ȷ��ϵͳ���ݿ��д��ڵ��������ֵ���Ϣ��");
					}
				}
			}
			
			//���������ĵ�������
			if (pFlag)
			{
				pErrPos = 2;
				if (getSystemInitializer().getPlaneArchivesTypes().containsKey(archivesTypeID)==false)
				{
					pFlag = false;
					pErrInfo.getContent().append("ϵͳ�в����ڱ��Ϊ "+archivesTypeID+" �ĵ�������");
				}
			}
		}
		catch (Exception e)
		{
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null)
				{
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
	 * ���ָ����ŵĵ����������Ƿ�Ϸ�
	 * @param archivesTypeID ����������
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public static boolean checkOfficialArchivesType(int officialArchivesTypeID,ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			if (officialArchivesTypeID<=0)
			{
				pFlag = false;
				pErrInfo.getContent().append("���������ŷǷ�Ϊ0");
			}
			
			//���ϵͳ��ʼ��������ĵ������༯���Ƿ���ֵ
			if (pFlag)
			{
				pErrPos = 1;
				if (getSystemInitializer().getArchivesTypes() == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("ϵͳ��ʼ���������״�������༯�ϷǷ�Ϊ�գ����Ƚ���ϵͳ��ʼ��������");
				}
				else
				{
					if (getSystemInitializer().getArchivesTypes().size() == 0)
					{
						pFlag = false;
						pErrInfo.getContent().append("ϵͳ��ʼ���������״�������༯�ϷǷ�Ϊ�գ���ȷ��ϵͳ���ݿ��д��ڵ��������ֵ���Ϣ��");
					}
				}
			}
			
			//���������ĵ�������
			if (pFlag)
			{
				pErrPos = 2;
				if (getSystemInitializer().getOfficialArchivesTypes().containsKey(officialArchivesTypeID)==false)
				{
					pFlag = false;
					pErrInfo.getContent().append("ϵͳ�в����ڱ��Ϊ "+officialArchivesTypeID+" �ĵ�������");
				}
			}
		}
		catch (Exception e)
		{
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null)
				{
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
	 * ��鵵����¼��ѯ������������ص�����ֵ�޸�����<br>
	 * ���罫�յķ�Χ��ѯ����ֵ��ȫ����Сֵ�����ֵ����������������ֵ��ʽ��Ϊ��׼������ʱ�����ַ���
	 * @param archivesInfoQueryConditions ������¼��ѯ��������
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public static boolean checkArchivesInfoInputQueryConditions(List<ArchivesInfoQueryCondition> archivesInfoQueryConditions, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			if (archivesInfoQueryConditions!=null)
			{
				if (archivesInfoQueryConditions.size()>0)
				{
					for (ArchivesInfoQueryCondition item : archivesInfoQueryConditions)
					{
						//����Ƿ������Ϊ��¼��ѯ����
						if (item.getDataItem().getInputQueryFlag()==false)
						{
							pFlag = false;
							pErrInfo.getContent().append("�����"+item.getDataItem().getDisplayText()+"�����ܹ���Ϊ��¼��ѯ������");
						}
						
						//����Ƿ�Χ��ѯ��������Сֵ�����ֵ
						if (pFlag)
						{
							if (item.getDataItem().getRangeQueryFlag())
							{
								if (item.getMinValue().length()==0 && item.getMaxValue().length()==0)
								{
									pFlag = false;
									pErrInfo.getContent().append("�����"+item.getDataItem().getDisplayText()+"���ķ�Χ��ѯ�����Ƿ�Ϊ�ա�");
								}
								else 
								{
									//����Сֵ��û�����ֵ�������ֵ��ֵΪ��Сֵ
									if (item.getMinValue().length()>0 && item.getMaxValue().length()==0)
									{
										item.setMaxValue(item.getMinValue());
									}
									//�����ֵ��û����Сֵ������Сֵ��ֵΪ���ֵ
									else if (item.getMinValue().length()==0 && item.getMaxValue().length()>0)
									{
										item.setMinValue(item.getMaxValue());
									}
									else 
									{
										//�����Сֵ�����ֵ������ô��Ҫ����һ�¡�
										if (item.getDataItem().getColumnDataType()==EnumColumnDataType.ʵ��) 
										{
											if (Float.valueOf(item.getMinValue())>Float.valueOf(item.getMaxValue()))
											{
												String tempValue=item.getMinValue();
												item.setMinValue(item.getMaxValue());
												item.setMaxValue(tempValue);
											}
										}
										else if (item.getDataItem().getColumnDataType()==EnumColumnDataType.���� 
												|| item.getDataItem().getColumnDataType()==EnumColumnDataType.����ֵ)
										{
											if (Integer.valueOf(item.getMinValue())>Integer.valueOf(item.getMaxValue()))
											{
												String tempValue=item.getMinValue();
												item.setMinValue(item.getMaxValue());
												item.setMaxValue(tempValue);
											}
										}
										else if (item.getDataItem().getColumnDataType()==EnumColumnDataType.����ʱ��)
										{
											//�Ȱ�����ֵ��ʽ��Ϊ��׼�ַ�����ʽ
											SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
											Date tempDateMin=simpleDateFormat.parse(item.getMinValue());
											item.setMinValue(simpleDateFormat.format(tempDateMin));
											Date tempDateMax=simpleDateFormat.parse(item.getMaxValue());
											item.setMaxValue(simpleDateFormat.format(tempDateMax));
											
											if (tempDateMin.getTime()>tempDateMax.getTime())
											{
												String tempValue=item.getMinValue();
												item.setMinValue(item.getMaxValue());
												item.setMaxValue(tempValue);
											}
										}
										else if (item.getDataItem().getColumnDataType()==EnumColumnDataType.�ַ��� 
												|| item.getDataItem().getColumnDataType()==EnumColumnDataType.�ı�)
										{
											if (item.getMinValue().compareTo(item.getMaxValue())>0)
											{
												String tempValue=item.getMinValue();
												item.setMinValue(item.getMaxValue());
												item.setMaxValue(tempValue);
											}
										}
									}
								}
							}
							//������Ƿ�Χ��ѯ����������ֵ
							else 
							{
								if (item.getValue().length()==0)
								{
									pFlag = false;
									pErrInfo.getContent().append("�����"+item.getDataItem().getDisplayText()+"���Ĳ�ѯ�����Ƿ�Ϊ�ա�");
								}
								else 
								{
									//������ֵ��ʽ��Ϊ��׼�ַ�����ʽ
									if (item.getDataItem().getColumnDataType()==EnumColumnDataType.����ʱ��)
									{
										SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
										Date tempDateMin=simpleDateFormat.parse(item.getMinValue());
										item.setValue(simpleDateFormat.format(tempDateMin));
									}
								}
							}
						}
						
						//������δͨ����������ѭ��
						if (pFlag==false)
						{
							break;
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null)
				{
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
	 * ��鵵����¼��ѯ������������ص�����ֵ�޸�����<br>
	 * ���罫�յķ�Χ��ѯ����ֵ��ȫ����Сֵ�����ֵ����������������ֵ��ʽ��Ϊ��׼������ʱ�����ַ���
	 * @param archivesInfoQueryConditions ������¼��ѯ��������
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public static boolean checkOfficialArchivesInfoInputQueryConditions(List<OfficialArchivesInfoQueryCondition> officialArchivesInfoQueryConditions, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			if (officialArchivesInfoQueryConditions!=null)
			{
				if (officialArchivesInfoQueryConditions.size()>0)
				{
					for (OfficialArchivesInfoQueryCondition item : officialArchivesInfoQueryConditions)
					{
						//����Ƿ������Ϊ��¼��ѯ����
//						if (item.getDataItem().getInputQueryFlag()==false)
//						{
//							pFlag = false;
//							pErrInfo.getContent().append("�����"+item.getDataItem().getDisplayText()+"�����ܹ���Ϊ��¼��ѯ������");
//						}
						
						//����Ƿ�Χ��ѯ��������Сֵ�����ֵ
						if (pFlag)
						{
							if (item.getDataItem().getRangeQueryFlag())
							{
								if (item.getMinValue().length()==0 && item.getMaxValue().length()==0)
								{
									pFlag = false;
									pErrInfo.getContent().append("�����"+item.getDataItem().getDisplayText()+"���ķ�Χ��ѯ�����Ƿ�Ϊ�ա�");
								}
								else 
								{
									//����Сֵ��û�����ֵ�������ֵ��ֵΪ��Сֵ
									if (item.getMinValue().length()>0 && item.getMaxValue().length()==0)
									{
										item.setMaxValue(item.getMinValue());
									}
									//�����ֵ��û����Сֵ������Сֵ��ֵΪ���ֵ
									else if (item.getMinValue().length()==0 && item.getMaxValue().length()>0)
									{
										item.setMinValue(item.getMaxValue());
									}
									else 
									{
										//�����Сֵ�����ֵ������ô��Ҫ����һ�¡�
										if (item.getDataItem().getColumnDataType()==EnumColumnDataType.ʵ��) 
										{
											if (Float.valueOf(item.getMinValue())>Float.valueOf(item.getMaxValue()))
											{
												String tempValue=item.getMinValue();
												item.setMinValue(item.getMaxValue());
												item.setMaxValue(tempValue);
											}
										}
										else if (item.getDataItem().getColumnDataType()==EnumColumnDataType.���� 
												|| item.getDataItem().getColumnDataType()==EnumColumnDataType.����ֵ)
										{
											if (Integer.valueOf(item.getMinValue())>Integer.valueOf(item.getMaxValue()))
											{
												String tempValue=item.getMinValue();
												item.setMinValue(item.getMaxValue());
												item.setMaxValue(tempValue);
											}
										}
										else if (item.getDataItem().getColumnDataType()==EnumColumnDataType.����ʱ��)
										{
											//�Ȱ�����ֵ��ʽ��Ϊ��׼�ַ�����ʽ
											SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
											Date tempDateMin=simpleDateFormat.parse(item.getMinValue());
											item.setMinValue(simpleDateFormat.format(tempDateMin));
											Date tempDateMax=simpleDateFormat.parse(item.getMaxValue());
											item.setMaxValue(simpleDateFormat.format(tempDateMax));
											
											if (tempDateMin.getTime()>tempDateMax.getTime())
											{
												String tempValue=item.getMinValue();
												item.setMinValue(item.getMaxValue());
												item.setMaxValue(tempValue);
											}
										}
										else if (item.getDataItem().getColumnDataType()==EnumColumnDataType.�ַ��� 
												|| item.getDataItem().getColumnDataType()==EnumColumnDataType.�ı�)
										{
											if (item.getMinValue().compareTo(item.getMaxValue())>0)
											{
												String tempValue=item.getMinValue();
												item.setMinValue(item.getMaxValue());
												item.setMaxValue(tempValue);
											}
										}
									}
								}
							}
							//������Ƿ�Χ��ѯ����������ֵ
							else 
							{
								if (item.getValue().length()==0)
								{
									pFlag = false;
									pErrInfo.getContent().append("�����"+item.getDataItem().getDisplayText()+"���Ĳ�ѯ�����Ƿ�Ϊ�ա�");
								}
								else 
								{
									//������ֵ��ʽ��Ϊ��׼�ַ�����ʽ
									if (item.getDataItem().getColumnDataType()==EnumColumnDataType.����ʱ��)
									{
										SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
										Date tempDateMin=simpleDateFormat.parse(item.getMinValue());
										item.setValue(simpleDateFormat.format(tempDateMin));
									}
								}
							}
						}
						
						//������δͨ����������ѭ��
						if (pFlag==false)
						{
							break;
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null)
				{
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
	 * ��鵵�����ò�ѯ������������ص�����ֵ�޸�����<br>
	 * ���罫�յķ�Χ��ѯ����ֵ��ȫ����Сֵ�����ֵ����������������ֵ��ʽ��Ϊ��׼������ʱ�����ַ���
	 * @param archivesInfoQueryConditions ������¼��ѯ��������
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public static boolean checkArchivesInfoUseQueryConditions(List<ArchivesInfoQueryCondition> archivesInfoQueryConditions, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			if (archivesInfoQueryConditions!=null)
			{
				if (archivesInfoQueryConditions.size()>0)
				{
					for (ArchivesInfoQueryCondition item : archivesInfoQueryConditions)
					{
						//����Ƿ������Ϊ���ò�ѯ����
						if (item.getDataItem().getUseQueryFlag()==false)
						{
							pFlag = false;
							pErrInfo.getContent().append("�����"+item.getDataItem().getDisplayText()+"�����ܹ���Ϊ���ò�ѯ������");
						}
						
						//����Ƿ�Χ��ѯ��������Сֵ�����ֵ
						if (pFlag)
						{
							if (item.getDataItem().getRangeQueryFlag())
							{
								if (item.getMinValue().length()==0 && item.getMaxValue().length()==0)
								{
									pFlag = false;
									pErrInfo.getContent().append("�����"+item.getDataItem().getDisplayText()+"���ķ�Χ��ѯ�����Ƿ�Ϊ�ա�");
								}
								else 
								{
									//����Сֵ��û�����ֵ�������ֵ��ֵΪ��Сֵ
									if (item.getMinValue().length()>0 && item.getMaxValue().length()==0)
									{
										item.setMaxValue(item.getMinValue());
									}
									//�����ֵ��û����Сֵ������Сֵ��ֵΪ���ֵ
									else if (item.getMinValue().length()==0 && item.getMaxValue().length()>0)
									{
										item.setMinValue(item.getMaxValue());
									}
									else 
									{
										//�����Сֵ�����ֵ������ô��Ҫ����һ�¡�
										if (item.getDataItem().getColumnDataType()==EnumColumnDataType.ʵ��) 
										{
											if (Float.valueOf(item.getMinValue())>Float.valueOf(item.getMaxValue()))
											{
												String tempValue=item.getMinValue();
												item.setMinValue(item.getMaxValue());
												item.setMaxValue(tempValue);
											}
										}
										else if (item.getDataItem().getColumnDataType()==EnumColumnDataType.���� 
												|| item.getDataItem().getColumnDataType()==EnumColumnDataType.����ֵ)
										{
											if (Integer.valueOf(item.getMinValue())>Integer.valueOf(item.getMaxValue()))
											{
												String tempValue=item.getMinValue();
												item.setMinValue(item.getMaxValue());
												item.setMaxValue(tempValue);
											}
										}
										else if (item.getDataItem().getColumnDataType()==EnumColumnDataType.����ʱ��)
										{
											//�Ȱ�����ֵ��ʽ��Ϊ��׼�ַ�����ʽ
											SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
											Date tempDateMin=simpleDateFormat.parse(item.getMinValue());
											item.setMinValue(simpleDateFormat.format(tempDateMin));
											Date tempDateMax=simpleDateFormat.parse(item.getMaxValue());
											item.setMaxValue(simpleDateFormat.format(tempDateMax));
											
											if (tempDateMin.getTime()>tempDateMax.getTime())
											{
												String tempValue=item.getMinValue();
												item.setMinValue(item.getMaxValue());
												item.setMaxValue(tempValue);
											}
										}
										else if (item.getDataItem().getColumnDataType()==EnumColumnDataType.�ַ��� 
												|| item.getDataItem().getColumnDataType()==EnumColumnDataType.�ı�)
										{
											if (item.getMinValue().compareTo(item.getMaxValue())>0)
											{
												String tempValue=item.getMinValue();
												item.setMinValue(item.getMaxValue());
												item.setMaxValue(tempValue);
											}
										}
									}
								}
							}
							//������Ƿ�Χ��ѯ����������ֵ
							else 
							{
								if (item.getValue().length()==0)
								{
									pFlag = false;
									pErrInfo.getContent().append("�����"+item.getDataItem().getDisplayText()+"���Ĳ�ѯ�����Ƿ�Ϊ�ա�");
								}
								else 
								{
									//������ֵ��ʽ��Ϊ��׼�ַ�����ʽ
									if (item.getDataItem().getColumnDataType()==EnumColumnDataType.����ʱ��)
									{
										SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
										Date tempDateMin=simpleDateFormat.parse(item.getMinValue());
										item.setValue(simpleDateFormat.format(tempDateMin));
									}
								}
							}
						}
						
						//������δͨ����������ѭ��
						if (pFlag==false)
						{
							break;
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null)
				{
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
	 * �ж�ָ���ַ��������Ƿ�Ϊ��������
	 * @param strData �ַ�������
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public static boolean isDate(String strData)
	{
		boolean pIsDate = true;

		try
		{
			//����ת��Ϊ����
			SimpleDateFormat dateFormater=new SimpleDateFormat("yyyy-MM-dd");
			dateFormater.parse(strData);
		}
		catch (Exception e)
		{
			//�����쳣��ʾ��������
			pIsDate = false;
		}

		return pIsDate;
	}
	
	/**
	 * �ж�ָ���ַ��������Ƿ�Ϊ����ֵ����
	 * @param strData �ַ�������
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public static boolean isBoolean(String strData)
	{
		boolean pIsBoolean = true;

		try
		{
			if (strData.equals("0")==false && strData.equals("1")==false)
			{
				pIsBoolean=false;
			}
		}
		catch (Exception e)
		{
			//�����쳣��ʾ���ǲ���ֵ
			pIsBoolean=false;
		}

		return pIsBoolean;
	}
	
	/**
	 * �ж�ָ���ַ��������Ƿ�Ϊ��������
	 * @param strData �ַ�������
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public static boolean isInteger(String strData)
	{
		boolean pIsInteger = true;

		try
		{
			Long.parseLong(strData);
		}
		catch (Exception e)
		{
			//�����쳣��ʾ��������
			pIsInteger=false;
		}

		return pIsInteger;
	}
	
	/**
	 * �ж�ָ���ַ��������Ƿ�Ϊʵ������
	 * @param strData �ַ�������
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public static boolean isDouble(String strData)
	{
		boolean pIsDouble = true;

		try
		{
			Double.parseDouble(strData);
		}
		catch (Exception e)
		{
			//�����쳣��ʾ����ʵ��
			pIsDouble=false;
		}

		return pIsDouble;
	}
	
	/**
	 * ����״�ṹ�ĵ������༯��ת��Ϊƽ��ṹ
	 * 
	 * @param planeArchivesTypes ����ƽ����
	 * @param treeArchivesTypes �ο���
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public static boolean getChildPlaneArchivesTypes(LinkedHashMap<Integer, ArchivesType> treeArchivesTypes, Map<Integer, ArchivesType> planeArchivesTypes, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			// �����״�ṹ�ĵ��������Ƿ�Ϊ��
			pErrPos = 1;
			if (treeArchivesTypes == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("��״�ṹ�ĵ���������Ϣ�Ƿ�Ϊ�ա�");
			}
			else
			{
				if (treeArchivesTypes.size() == 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("��״�ṹ�ĵ���������Ϣ�Ƿ�Ϊ�ա�");
				}
			}

			// ��ʼת��
			if (pFlag)
			{
				for (ArchivesType item : treeArchivesTypes.values())
				{
					// �����ǰ�����»����¼����࣬��ݹ鴦��
					if (item.getChildArchivesTypes() != null)
					{
						if (item.getChildArchivesTypes().size() > 0)
						{
							if (getChildPlaneArchivesTypes(item.getChildArchivesTypes(), planeArchivesTypes, pErrInfo) == false)
							{
								pFlag = false;
								pErrInfo.getContent().insert(0, "��״�ṹ�ĵ�������ת��Ϊƽ��ṹʧ��: ");
							}
						}
					}else{
						
						// ����ǰ������뷵�ؽ������
						planeArchivesTypes.put(item.getID(), item);
					}
				}
			}
		}
		catch (Exception e)
		{
			// �쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// ���پֲ�����
			throwable = null;
		}

		return pFlag;
	}

	/**
	 * ����״�ṹ�ĵ������༯��ת��Ϊƽ��ṹ
	 * 
	 * @param archivesTypeExs ����ƽ����
	 * @param archivesTypes �ο���
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public static boolean getChildPlaneArchivesTypeExs(LinkedHashMap<Integer, ArchivesType> archivesTypes, Map<Integer, ArchivesTypeEx> childPlaneArchivesTypeExs, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			// �����״�ṹ�ĵ��������Ƿ�Ϊ��
			pErrPos = 1;
			if (archivesTypes == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("��״�ṹ�ĵ���������Ϣ�Ƿ�Ϊ�ա�");
			}
			else
			{
				if (archivesTypes.size() == 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("��״�ṹ�ĵ���������Ϣ�Ƿ�Ϊ�ա�");
				}
			}

			// ��ʼת��
			if (pFlag)
			{
				ArchivesTypeEx archivesTypeEx = null;
				for (ArchivesType item : archivesTypes.values())
				{
					// �����ǰ�����»����¼����࣬��ݹ鴦��
					if (item.getChildArchivesTypes() != null)
					{
						if (item.getChildArchivesTypes().size() > 0)
						{
							if (getChildPlaneArchivesTypeExs(item.getChildArchivesTypes(), childPlaneArchivesTypeExs, pErrInfo) == false)
							{
								pFlag = false;
								pErrInfo.getContent().insert(0, "��״�ṹ�ĵ�������ת��Ϊƽ��ṹʧ��: ");
							}
						}
					}else{
						
						archivesTypeEx = new ArchivesTypeEx();
						archivesTypeEx.cloneFrom(item);
						
						// ����ǰ������뷵�ؽ������
						childPlaneArchivesTypeExs.put(archivesTypeEx.getID(), archivesTypeEx);
					}
				}
			}
		}
		catch (Exception e)
		{
			// �쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// ���پֲ�����
			throwable = null;
		}

		return pFlag;
		
	}
	
	/**
	 * ��ȡָ���������͵�����������id����
	 * @param archiveTypeId ָ����������id
	 * @return
	 */
	public static boolean getAllChildArchivesTypeId(List<Integer> archivesTypeIds, int archivesTypeId) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//��ȡ����ʵ��
			ArchivesType archivesType = getSystemInitializer().getPlaneArchivesTypes().get(archivesTypeId);
			//�ݹ��ȡ�ӵ�������id
			archivesTypeIds.addAll(getChild(archivesType));
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo .getContent().append(e.toString());
			pErrInfo.setException(e);
			e.printStackTrace();
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
	
	//�ݹ��ѯ֪�����������µ������ӷ���
	private static List<Integer> getChild(ArchivesType archivesType) {
		List<Integer> archivesTypeIds = null;
		if(archivesType!=null && archivesType.getChildArchivesTypes()!=null && archivesType.getChildArchivesTypes().keySet()!=null && archivesType.getChildArchivesTypes().keySet().size()>=1) {
			archivesTypeIds = new ArrayList<Integer>();
			for(Integer archivesTypeId : archivesType.getChildArchivesTypes().keySet()) {
				archivesTypeIds.add(archivesTypeId);
				List<Integer> integers = getChild(archivesType.getChildArchivesTypes().get(archivesTypeId));
				if(integers!=null && integers.size()>=1) {
					archivesTypeIds.addAll(integers);
				}
			}
		}
		return archivesTypeIds;
	}
	
	/**
	 * ��ȡ�������͵�һ���������� 
	 * @return
	 */
	public static ArchivesType getTopArchivesType(ArchivesType archivesType) {
		if(archivesType!=null && archivesType.getParentArchivesType()!=null) {
			while(archivesType.getParentArchivesType() != null) {
				archivesType = archivesType.getParentArchivesType();
			}
		}
		return archivesType;
	}
}