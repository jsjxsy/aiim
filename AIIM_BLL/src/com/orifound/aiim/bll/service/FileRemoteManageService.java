package com.orifound.aiim.bll.service;

import com.orifound.aiim.entity.HandleResult;


/**
 * �ļ�Զ�̴������Ľӿڶ���
 * 
 */
public interface FileRemoteManageService {
	
	/**
	 * ǩ���ļ���ǩ�������ļ��ϴ�
	 * @param originalfilePath	ǩ���ļ�·��
	 * @param signedFileBytes	ǩ���ļ��ı�����
	 * @param signedDataFilePath	ǩ�������ļ�·��
	 * @param signedDataFileBytes	ǩ�������ļ��ı�����
	 * @return ���ش���������
	 */
	public HandleResult fileUpload(String originalfilePath, byte[] signedFileBytes, String signedDataFilePath, byte[] signedDataFileBytes);
	
	/**
	 * �ļ��ϴ�
	 * @param fileAbsolutePath	�ļ�����·��
	 * @param fileBytes			�ϴ��ļ��ı�����
	 * @return ���ش���������
	 */
	public HandleResult fileUpload(String fileAbsolutePath, byte[] fileBytes);
	
	/**
	 * ɾ���ļ�
	 * @param fileAbsolutePath	�ļ�����·��
	 * @return ���ش���������
	 */
	public HandleResult deleteFile(String fileAbsolutePath);
	
	/**
	 * �����ļ�
	 * @param originalFileAbsolutePath	ԭ�ļ��ľ���·��
	 * @param targetFileAbsolutePath	Ŀ���ļ��ľ���·��
	 * @return ���ش���������
	 */
	public HandleResult copyFile(String originalFileAbsolutePath, String targetFileAbsolutePath);
	
	/**
	 * �����ļ�
	 * @param fileAbsolutePath	�ļ�����·��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ֵ���ɹ�������(HandleResult.decryptFileAddress)���ܺ������·��(http://localhost:80/aiim/test.txt)��ʧ�ܣ�����null��
	 */
	public HandleResult decryptFile(String fileAbsolutePath);
	
	/**
	 * ��֤�����ļ�������ǩ����ʹ����Կ���ָ����keyAlias��֤�����ļ�������ǩ����
	 * @param originalfile		����ǩ����֤��ԭ�ļ�
	 * @param signedDataFile	ǩ���ļ�������:ǩ�����ݵ��ļ����ƣ�
	 * @param keyAlias			��Կ����ָ����keyAlias��	ֵnullʱ���Զ�������Կ���и���keyAlias����һƥ����֤��
	 * @return ����ֵ�� �ɹ�������(HandleResult.signer)ǩ�������ƣ�ʧ�ܣ�����null��
	 */
	public HandleResult verifyWithKeyStore(String originalfile, String signedDataFile, String keyAlias);
}
