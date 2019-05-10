/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.*;

import com.orifound.aiim.bll.service.IRoleRightArchivesSecrecyManageService;
import com.orifound.aiim.bll.service.IRoleRightArchivesTypeManageService;
import com.orifound.aiim.bll.service.IRoleRightSystemFeatureManageService;
import com.orifound.aiim.bll.service.ISystemUserRightInitializeService;
import com.orifound.aiim.bll.service.IUserRightArchivesSecrecyManageService;
import com.orifound.aiim.bll.service.IUserRightArchivesTypeManageService;
import com.orifound.aiim.bll.service.IUserRightSystemFeatureManageService;
import com.orifound.aiim.bll.util.CommonUtil;
import com.orifound.aiim.entity.ArchivesSecrecy;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.SystemFeature;

/**
 * ϵͳ�û�Ȩ�޳�ʼ������ʵ����
 * 
 */
public class SystemUserRightInitializeServiceImpl implements ISystemUserRightInitializeService
{

	/**
	 * �û�ϵͳ������Ȩ����������
	 */
	private IUserRightSystemFeatureManageService userRightSystemFeatureManageService = null;

	/**
	 * ��������ֵ���û�ϵͳ������Ȩ����������
	 * 
	 * @param userRightSystemFeatureManageService
	 *            �û�ϵͳ������Ȩ����������
	 */
	public void setUserRightSystemFeatureManageService(IUserRightSystemFeatureManageService userRightSystemFeatureManageService)
	{
		this.userRightSystemFeatureManageService = userRightSystemFeatureManageService;
	}

	/**
	 * ��ȡ����ֵ���û�ϵͳ������Ȩ����������
	 * 
	 * @return �û�ϵͳ������Ȩ����������
	 */
	public IUserRightSystemFeatureManageService getUserRightSystemFeatureManageService()
	{
		return userRightSystemFeatureManageService;
	}

	/**
	 * ��ɫϵͳ������Ȩ����������
	 */
	private IRoleRightSystemFeatureManageService roleRightSystemFeatureManageService = null;

	/**
	 * ��������ֵ����ɫϵͳ������Ȩ����������
	 * 
	 * @param roleRightSystemFeatureManageService
	 *            ��ɫϵͳ������Ȩ����������
	 */
	public void setRoleRightSystemFeatureManageService(IRoleRightSystemFeatureManageService roleRightSystemFeatureManageService)
	{
		this.roleRightSystemFeatureManageService = roleRightSystemFeatureManageService;
	}

	/**
	 * ��ȡ����ֵ����ɫϵͳ������Ȩ����������
	 * 
	 * @return ��ɫϵͳ������Ȩ����������
	 */
	public IRoleRightSystemFeatureManageService getRoleRightSystemFeatureManageService()
	{
		return roleRightSystemFeatureManageService;
	}

	/**
	 * �û�������Դ��Ȩ����������
	 */
	private IUserRightArchivesTypeManageService userRightArchivesTypeManageService = null;

	/**
	 * ��������ֵ���û�������Դ��Ȩ����������
	 * 
	 * @param userRightArchivesTypeManageService
	 *            �û�������Դ��Ȩ����������
	 */
	public void setUserRightArchivesTypeManageService(IUserRightArchivesTypeManageService userRightArchivesTypeManageService)
	{
		this.userRightArchivesTypeManageService = userRightArchivesTypeManageService;
	}

	/**
	 * ��ȡ����ֵ���û�������Դ��Ȩ����������
	 * 
	 * @return �û�������Դ��Ȩ����������
	 */
	public IUserRightArchivesTypeManageService getUserRightArchivesTypeManageService()
	{
		return userRightArchivesTypeManageService;
	}

	/**
	 * ��ɫ������Դ��Ȩ����������
	 */
	private IRoleRightArchivesTypeManageService roleRightArchivesTypeManageService = null;

	/**
	 * ��������ֵ����ɫ������Դ��Ȩ����������
	 * 
	 * @param roleRightArchivesTypeManageService
	 *            ��ɫ������Դ��Ȩ����������
	 */
	public void setRoleRightArchivesTypeManageService(IRoleRightArchivesTypeManageService roleRightArchivesTypeManageService)
	{
		this.roleRightArchivesTypeManageService = roleRightArchivesTypeManageService;
	}

	/**
	 * ��ȡ����ֵ����ɫ������Դ��Ȩ����������
	 * 
	 * @return ��ɫ������Դ��Ȩ����������
	 */
	public IRoleRightArchivesTypeManageService getRoleRightArchivesTypeManageService()
	{
		return roleRightArchivesTypeManageService;
	}

	/**
	 * �û������ܼ���Ȩ����������
	 */
	private IUserRightArchivesSecrecyManageService userRightArchivesSecrecyManageService = null;

	/**
	 * ��������ֵ���û������ܼ���Ȩ����������
	 * 
	 * @param userRightArchivesSecrecyManageService
	 *            �û������ܼ���Ȩ����������
	 */
	public void setUserRightArchivesSecrecyManageService(IUserRightArchivesSecrecyManageService userRightArchivesSecrecyManageService)
	{
		this.userRightArchivesSecrecyManageService = userRightArchivesSecrecyManageService;
	}

	/**
	 * ��ȡ����ֵ���û������ܼ���Ȩ����������
	 * 
	 * @return �û������ܼ���Ȩ����������
	 */
	public IUserRightArchivesSecrecyManageService getUserRightArchivesSecrecyManageService()
	{
		return userRightArchivesSecrecyManageService;
	}

	/**
	 * ��ɫ�����ܼ���Ȩ����������
	 */
	private IRoleRightArchivesSecrecyManageService roleRightArchivesSecrecyManageService = null;

	/**
	 * ��������ֵ����ɫ�����ܼ���Ȩ����������
	 * 
	 * @param roleRightArchivesSecrecyManageService
	 *            ��ɫ�����ܼ���Ȩ����������
	 */
	public void setRoleRightArchivesSecrecyManageService(IRoleRightArchivesSecrecyManageService roleRightArchivesSecrecyManageService)
	{
		this.roleRightArchivesSecrecyManageService = roleRightArchivesSecrecyManageService;
	}

	/**
	 * ��ȡ����ֵ����ɫ�����ܼ���Ȩ����������
	 * 
	 * @return ��ɫ�����ܼ���Ȩ����������
	 */
	public IRoleRightArchivesSecrecyManageService getRoleRightArchivesSecrecyManageService()
	{
		return roleRightArchivesSecrecyManageService;
	}

	/**
	 * �����ص�ҵ���߼���������ע�루BLL Depandency Injection��
	 * 
	 * @param pErrInfo
	 *            ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkBllInjection(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			// ����Ƿ��н����û�ϵͳ������Ȩ�����BLLҵ���߼����������ע��
			pErrPos = 1;
			if (userRightSystemFeatureManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("�û�ϵͳ������Ȩ�����BLLҵ���߼�����Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}

			// ����Ƿ��н��н�ɫϵͳ������Ȩ�����BLLҵ���߼����������ע��
			if (roleRightSystemFeatureManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("��ɫϵͳ������Ȩ�����BLLҵ���߼�����Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}

			// ����Ƿ��н����û�������Դ��Ȩ�����BLLҵ���߼����������ע��
			if (userRightArchivesTypeManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("�û�������Դ��Ȩ�����BLLҵ���߼�����Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}

			// ����Ƿ��н��н�ɫ������Դ��Ȩ�����BLLҵ���߼����������ע��
			if (roleRightArchivesTypeManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("��ɫ������Դ��Ȩ�����BLLҵ���߼�����Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}

			// ����Ƿ��н����û������ܼ���Ȩ�����BLLҵ���߼����������ע��
			if (userRightArchivesSecrecyManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("�û������ܼ���Ȩ�����BLLҵ���߼�����Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}

			// ����Ƿ��н��н�ɫ�����ܼ���Ȩ�����BLLҵ���߼����������ע��
			if (roleRightArchivesSecrecyManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("��ɫ�����ܼ���Ȩ�����BLLҵ���߼�����Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
		}

		return pFlag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.orifound.aiim.bll.service.ISystemUserRightInitializeService#
	 * findRightSystemFeatureMenusByUserID(int, int[], java.util.LinkedHashMap,
	 * com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findRightSystemFeatureMenusByUserID(int pUserID, int[] pRolesID, LinkedHashMap<String, SystemFeature> systemFeatureMenus, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		LinkedHashMap<String, SystemFeature> userRightMenus = null;
		LinkedHashMap<String, SystemFeature> roleRightMenus = null;

		try
		{
			pErrPos = 1;
			// ����Ƿ��н������BLLҵ���߼����������ע��
			if (checkBllInjection(pErrInfo) == false)
			{
				pFlag = false;
			}

			// �����û�ϵͳ������Ȩ�������ӿڻ�ȡֱ�ӶԵ�ǰ�û���Ȩ��ϵͳ�˵�����
			if (pFlag)
			{
				pErrPos = 2;
				userRightMenus = new LinkedHashMap<String, SystemFeature>();
				if (userRightSystemFeatureManageService.findRightSystemFeatureMenusByUserID(pUserID, userRightMenus, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ȡ�û���ϵͳ���ܲ˵���Ȩ��Ϣʧ��: ");
				}
			}

			// ���ý�ɫϵͳ������Ȩ�������ӿڻ�ȡֱ�ӶԵ�ǰ�û�������ɫ��Ȩ��ϵͳ�˵�����
			if (pFlag)
			{
				if (pRolesID.length > 0)
				{
					pErrPos = 4;
					roleRightMenus = new LinkedHashMap<String, SystemFeature>();
					if (roleRightSystemFeatureManageService.findRightSystemFeatureMenusByRolesID(pRolesID, roleRightMenus, pErrInfo) == false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0, "��ȡ��ɫ��ϵͳ���ܲ˵���Ȩ��Ϣʧ��: ");
					}
				}
			}

			// �ϲ��û��ͽ�ɫ��ϵͳ�˵�������Ȩ����
			if (pFlag)
			{
				// ����н�ɫ��Ϣ����ϲ��û��ͽ�ɫ��Ȩ��ϵͳ���ܲ˵�����
				if (pRolesID.length > 0)
				{
					pErrPos = 5;
					if (combineSystemFeatureMenus(userRightMenus, roleRightMenus, systemFeatureMenus, pErrInfo) == false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0, "�ϲ��û����ɫ��ϵͳ���ܲ˵���Ȩ��Ϣʧ��: ");
					}
				}
				else
				{
					pErrPos = 6;
					// ���û�н�ɫ��Ϣ����ֱ�ӰѶ��û���Ȩ��ϵͳ���ܲ˵�������Ϊ�������
					for (SystemFeature item : userRightMenus.values())
					{
						systemFeatureMenus.put(item.getUclKey(), item);
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
			userRightMenus = null;
			roleRightMenus = null;
		}

		return pFlag;
	}

	/**
	 * �ϲ�����ϵͳ���ܲ˵�����
	 * 
	 * @param menus1
	 *            Ҫ�ϲ���ϵͳ�˵�����1
	 * @param menus2
	 *            Ҫ�ϲ���ϵͳ�˵�����2
	 * @param combinedMenus
	 *            �ϲ����ϵͳ�˵����ϣ���UclKey��Ϊ���ϼ������ص�����״�ṹ�ļ��ϣ��ɷ����¼����ܲ˵�����
	 * @param pErrInfo
	 *            ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean combineSystemFeatureMenus(LinkedHashMap<String, SystemFeature> menus1, LinkedHashMap<String, SystemFeature> menus2,
			LinkedHashMap<String, SystemFeature> combinedMenus, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{

			pErrPos = 1;
			if (menus1 == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("menus1�����Ƿ�Ϊ�ա�");
			}
			else
			{
				if (menus2 == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("menus2�����Ƿ�Ϊ�ա�");
				}
			}

			// �Ȱ�menu1��Ϊ���ؽ��
			if (pFlag)
			{
				if (menus1.size() > 0)
				{
					pErrPos = 2;
					for (SystemFeature item : menus1.values())
					{
						combinedMenus.put(item.getUclKey(), item);
					}
				}
			}

			// ��ʼ�ϲ�menu2
			if (pFlag)
			{
				if (menus2.size() > 0)
				{
					pErrPos = 3;
					for (SystemFeature item : menus2.values())
					{
						// ��ǰ���ؽ���в����ڵľͺϲ�������Ϊ�������
						if (combinedMenus.containsKey(item.getUclKey()) == false)
						{
							combinedMenus.put(item.getUclKey(), item);
						}
						// ���ڵĻ�������ϲ����¼��˵���
						else
						{
							String pKey = item.getUclKey();
							LinkedHashMap<String, SystemFeature> childMenus1 = menus1.get(pKey).getChildSystemFeatures();
							LinkedHashMap<String, SystemFeature> childMenus2 = menus2.get(pKey).getChildSystemFeatures();

							// ���childMenus1��Ϊnull����childMenus2Ϊ�գ�������ϲ��ˣ���ΪchildMenus1�Ѿ��ڷ��ؽ������

							// ���childMenus1Ϊnull��childMenus2ҲΪ�գ�������ϲ�

							// ���childMenus1Ϊnull����childMenus2��Ϊ�գ���childMenus2ֱ�Ӻϲ�
							if (childMenus1 == null && childMenus2 != null)
							{
								pErrPos = 4;
								if (childMenus2.size() > 0)
								{
									combinedMenus.get(pKey).setChildSystemFeatures(childMenus2);
								}
							}

							// ���childMenus1��Ϊnull��childMenus2Ҳ��Ϊ�գ�����Ҫ�ݹ�ϲ�
							if (childMenus1 != null && childMenus2 != null)
							{
								pErrPos = 5;
								LinkedHashMap<String, SystemFeature> childCombinedMenus = new LinkedHashMap<String, SystemFeature>();
								if (combineSystemFeatureMenus(childMenus1, childMenus2, childCombinedMenus, pErrInfo) == false)
								{
									pFlag = false;
									pErrInfo.getContent().insert(0, "�ϲ�ϵͳ���ܲ˵�����ʧ��: ");
								}
								else
								{
									// ���غϲ����
									combinedMenus.get(pKey).setChildSystemFeatures(childCombinedMenus);
								}

								childCombinedMenus = null;
							}

							childMenus1 = null;
							childMenus2 = null;
						}

						// �ϲ����������ѭ��
						if (pFlag == false)
						{
							break;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.orifound.aiim.bll.service.ISystemUserRightInitializeService#
	 * findRightSystemFeaturesByUserID(int, int[], java.util.Map,
	 * com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findRightSystemFeaturesByUserID(int pUserID, int[] pRolesID, Map<String, SystemFeature> systemFeatures, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		Map<String, SystemFeature> userRightSystemFeatures = null;
		Map<String, SystemFeature> roleRightSystemFeatures = null;

		try
		{
			pErrPos = 1;
			// ����Ƿ��н������BLLҵ���߼����������ע��
			if (checkBllInjection(pErrInfo) == false)
			{
				pFlag = false;
			}

			// �����û�ϵͳ������Ȩ�������ӿڻ�ȡֱ�ӶԵ�ǰ�û���Ȩ��ϵͳ���ܼ���
			if (pFlag)
			{
				pErrPos = 2;
				userRightSystemFeatures = new HashMap<String, SystemFeature>();
				if (userRightSystemFeatureManageService.findRightSystemFeaturesByUserID(pUserID, userRightSystemFeatures, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ȡ�û���ϵͳ������Ȩ��Ϣʧ��: ");
				}
			}

			// ���ý�ɫϵͳ������Ȩ�������ӿڻ�ȡֱ�ӶԵ�ǰ�û�������ɫ��Ȩ��ϵͳ�˵�����
			if (pFlag)
			{
				if (pRolesID.length > 0)
				{
					pErrPos = 4;
					roleRightSystemFeatures = new HashMap<String, SystemFeature>();
					if (roleRightSystemFeatureManageService.findRightSystemFeaturesByRolesID(pRolesID, roleRightSystemFeatures, pErrInfo) == false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0, "��ȡ��ɫ��ϵͳ������Ȩ��Ϣʧ��: ");
					}
				}
			}

			// �ϲ��û��ͽ�ɫ��ϵͳ������Ȩ���ϣ�UCL--�û����ʿ����б�
			if (pFlag)
			{
				// �ȰѶ��û���Ȩ��ϵͳ���ܼ�����Ϊ�������
				pErrPos = 5;
				for (SystemFeature item : userRightSystemFeatures.values())
				{
					systemFeatures.put(item.getUclKey(), item);
				}

				// ����н�ɫ��Ϣ����ϲ��û��ͽ�ɫ��ϵͳ������Ȩ���ϣ�UCL--�û����ʿ����б�
				if (pRolesID.length > 0)
				{
					pErrPos = 6;
					for (SystemFeature item : roleRightSystemFeatures.values())
					{
						if (systemFeatures.containsKey(item.getUclKey()) == false)
						{
							systemFeatures.put(item.getUclKey(), item);
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
			userRightSystemFeatures = null;
			roleRightSystemFeatures = null;
		}

		return pFlag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.orifound.aiim.bll.service.ISystemUserRightInitializeService#
	 * findRightArchivesTypesByUserID(int, int[], java.util.LinkedHashMap,
	 * com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findRightArchivesTypesByUserID(int pUserID, int[] pRolesID, LinkedHashMap<Integer, ArchivesType> archivesTypes, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		LinkedHashMap<Integer, ArchivesType> userRightArchivesTypes = null;
		LinkedHashMap<Integer, ArchivesType> roleRightArchivesTypes = null;
		LinkedHashMap<Integer, ArchivesType> combinedArchivesTypes = null; // ����ϲ���ĵ�����Դ��Ȩ���ϣ���ƽ��ṹ�ļ���

		try
		{
			pErrPos = 1;
			// ����Ƿ��н������BLLҵ���߼����������ע��
			if (checkBllInjection(pErrInfo) == false)
			{
				pFlag = false;
			}

			// ���ϵͳ��ʼ��������ĵ������༯���Ƿ���ֵ
			if (pFlag)
			{
				if (CommonUtil.getSystemInitializer().getArchivesTypes() == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("ϵͳ��ʼ���������״�������༯�ϷǷ�Ϊ�գ����Ƚ���ϵͳ��ʼ��������");
				}
				else
				{
					if (CommonUtil.getSystemInitializer().getArchivesTypes().size() == 0)
					{
						pFlag = false;
						pErrInfo.getContent().append("ϵͳ��ʼ���������״�������༯�ϷǷ�Ϊ�գ���ȷ��ϵͳ���ݿ��д��ڵ��������ֵ���Ϣ��");
					}
				}
			}

			// �����û�������Դ��Ȩ�������ӿڻ�ȡֱ�ӶԵ�ǰ�û���Ȩ�ĵ�����Դ����
			if (pFlag)
			{
				pErrPos = 2;
				userRightArchivesTypes = new LinkedHashMap<Integer, ArchivesType>();
				if (userRightArchivesTypeManageService.findRightArchivesTypeByUserID(pUserID, userRightArchivesTypes, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ȡ�û��ĵ�����Դ��Ȩ��Ϣʧ��: ");
				}
			}

			// ���ý�ɫ������Դ��Ȩ�������ӿڻ�ȡֱ�ӶԵ�ǰ�û�������ɫ��Ȩ�ĵ�����Դ����
			if (pFlag)
			{
				if (pRolesID.length > 0)
				{
					pErrPos = 4;
					roleRightArchivesTypes = new LinkedHashMap<Integer, ArchivesType>();
					if (roleRightArchivesTypeManageService.findRightArchivesTypeByRolesID(pRolesID, roleRightArchivesTypes, pErrInfo) == false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0, "��ȡ��ɫ�ĵ�����Դ��Ȩ��Ϣʧ��: ");
					}
				}
			}

			// �ϲ��û��ͽ�ɫ�ĵ�����Դ��Ȩ���ϣ�ƽ��ṹ�ļ��ϣ�
			if (pFlag)
			{
				// �ȰѶ��û���Ȩ�ĵ�����Դ������Ϊ�ϲ����
				combinedArchivesTypes = new LinkedHashMap<Integer, ArchivesType>();
				pErrPos = 5;
				for (ArchivesType item : userRightArchivesTypes.values())
				{
					combinedArchivesTypes.put(item.getID(), item);
				}

				// ����н�ɫ��Ϣ����ϲ��û��ͽ�ɫ�ĵ�����Դ��Ȩ����
				if (pRolesID.length > 0)
				{
					//����ɫ����Ȩ��Ϣ�ϲ����������
					pErrPos = 6;
					for (ArchivesType item : roleRightArchivesTypes.values())
					{
						if (combinedArchivesTypes.containsKey(item.getID()) == false)
						{
							combinedArchivesTypes.put(item.getID(), item);
						}
						//�����������Ѿ����ڸý�ɫ����Ȩ��Ϣ����ϲ��䵵����Ϣ��ԭ�ĵ����ļ���ʵ�ﵵ������Ȩ��Ϣ
						else 
						{
							//�û���Ȩ�ͽ�ɫ��Ȩ��ֻҪ��һ���߱����ͱ�ʾӵ�з���Ȩ��
							boolean pRightFlag_ArchivesInfo=combinedArchivesTypes.get(item.getID()).getRightFlag_ArchivesInfo() || item.getRightFlag_ArchivesInfo();
							boolean pRightFlag_AttachedFile=combinedArchivesTypes.get(item.getID()).getRightFlag_AttachedFile() || item.getRightFlag_AttachedFile();
							boolean pRightFlag_PaperArchives=combinedArchivesTypes.get(item.getID()).getRightFlag_PaperArchives() || item.getRightFlag_PaperArchives();
							combinedArchivesTypes.get(item.getID()).setRightFlag_ArchivesInfo(pRightFlag_ArchivesInfo);
							combinedArchivesTypes.get(item.getID()).setRightFlag_AttachedFile(pRightFlag_AttachedFile);
							combinedArchivesTypes.get(item.getID()).setRightFlag_PaperArchives(pRightFlag_PaperArchives);
						}
					}
				}
			}

			// ��ƽ��ṹ�ĵ�����Դ��Ȩ����ת��Ϊ��״�ṹ�ĵ�����Դ��Ȩ����
			if (pFlag)
			{
				if (CommonUtil.convertPlaneArchivesTypesToTree(combinedArchivesTypes, archivesTypes, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ƽ���͵������༯��ת��Ϊ��״�ṹʧ��: ");
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
			userRightArchivesTypes = null;
			roleRightArchivesTypes = null;
			combinedArchivesTypes = null;
		}

		return pFlag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.orifound.aiim.bll.service.ISystemUserRightInitializeService#
	 * findRightArchivesSecrecysByUserID(int, int[], java.util.Map,
	 * com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findRightArchivesSecrecysByUserID(int pUserID, int[] pRolesID, Map<Integer, ArchivesSecrecy> archivesSecrecys, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		Map<Integer, ArchivesSecrecy> userRightArchivesSecrecys = null;
		Map<Integer, ArchivesSecrecy> roleRightArchivesSecrecys = null;

		try
		{
			pErrPos = 1;
			// ����Ƿ��н������BLLҵ���߼����������ע��
			if (checkBllInjection(pErrInfo) == false)
			{
				pFlag = false;
			}

			// �����û������ܼ���Ȩ�������ӿڻ�ȡֱ�ӶԵ�ǰ�û���Ȩ�ĵ����ܼ�����
			if (pFlag)
			{
				pErrPos = 2;
				userRightArchivesSecrecys = new HashMap<Integer, ArchivesSecrecy>();
				if (userRightArchivesSecrecyManageService.findRightArchivesSecrecysByUserID(pUserID, userRightArchivesSecrecys, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ȡ�û��ĵ����ܼ���Ȩ��Ϣʧ��: ");
				}
			}

			// ���ý�ɫ�����ܼ���Ȩ�������ӿڻ�ȡֱ�ӶԵ�ǰ�û�������ɫ��Ȩ�ĵ����ܼ�����
			if (pFlag)
			{
				if (pRolesID.length > 0)
				{
					pErrPos = 4;
					roleRightArchivesSecrecys = new HashMap<Integer, ArchivesSecrecy>();
					if (roleRightArchivesSecrecyManageService.findRightArchivesSecrecysByRolesID(pRolesID, roleRightArchivesSecrecys, pErrInfo) == false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0, "��ȡ��ɫ�ĵ����ܼ���Ȩ��Ϣʧ��: ");
					}
				}
			}

			// �ϲ��û��ͽ�ɫ�ĵ����ܼ���Ȩ����
			if (pFlag)
			{
				// �ȰѶ��û���Ȩ�ĵ����ܼ�������Ϊ�������
				pErrPos = 5;
				for (ArchivesSecrecy item : userRightArchivesSecrecys.values())
				{
					archivesSecrecys.put(item.getID(), item);
				}

				// ����н�ɫ��Ϣ����ϲ��û��ͽ�ɫ�ĵ����ܼ���Ȩ����
				if (pRolesID.length > 0)
				{
					//����ɫ����Ȩ��Ϣ�ϲ����������
					pErrPos = 6;
					for (ArchivesSecrecy item : roleRightArchivesSecrecys.values())
					{
						if (archivesSecrecys.containsKey(item.getID()) == false)
						{
							archivesSecrecys.put(item.getID(), item);
						}
						//�����������Ѿ����ڸý�ɫ����Ȩ��Ϣ����ϲ��䵵����Ϣ��ԭ�ĵ����ļ���ʵ�ﵵ������Ȩ��Ϣ
						else 
						{
							//�û���Ȩ�ͽ�ɫ��Ȩ��ֻҪ��һ���߱����ͱ�ʾӵ�з���Ȩ��
							boolean pRightFlag_ArchivesInfo=archivesSecrecys.get(item.getID()).getRightFlag_ArchivesInfo() || item.getRightFlag_ArchivesInfo();
							boolean pRightFlag_AttachedFile=archivesSecrecys.get(item.getID()).getRightFlag_AttachedFile() || item.getRightFlag_AttachedFile();
							boolean pRightFlag_PaperArchives=archivesSecrecys.get(item.getID()).getRightFlag_PaperArchives() || item.getRightFlag_PaperArchives();
							archivesSecrecys.get(item.getID()).setRightFlag_ArchivesInfo(pRightFlag_ArchivesInfo);
							archivesSecrecys.get(item.getID()).setRightFlag_AttachedFile(pRightFlag_AttachedFile);
							archivesSecrecys.get(item.getID()).setRightFlag_PaperArchives(pRightFlag_PaperArchives);
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
			userRightArchivesSecrecys = null;
			roleRightArchivesSecrecys = null;
		}

		return pFlag;
	}

}
