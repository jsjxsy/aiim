package com.orifound.aiim.bll.service;

import com.orifound.aiim.entity.HandleResult;


/**
 * 文件远程处理服务的接口定义
 * 
 */
public interface FileRemoteManageService {
	
	/**
	 * 签名文件、签名生成文件上传
	 * @param originalfilePath	签名文件路径
	 * @param signedFileBytes	签名文件的比特流
	 * @param signedDataFilePath	签名生成文件路径
	 * @param signedDataFileBytes	签名生成文件的比特流
	 * @return 返回处理结果对象
	 */
	public HandleResult fileUpload(String originalfilePath, byte[] signedFileBytes, String signedDataFilePath, byte[] signedDataFileBytes);
	
	/**
	 * 文件上传
	 * @param fileAbsolutePath	文件绝对路径
	 * @param fileBytes			上传文件的比特流
	 * @return 返回处理结果对象
	 */
	public HandleResult fileUpload(String fileAbsolutePath, byte[] fileBytes);
	
	/**
	 * 删除文件
	 * @param fileAbsolutePath	文件绝对路径
	 * @return 返回处理结果对象
	 */
	public HandleResult deleteFile(String fileAbsolutePath);
	
	/**
	 * 复制文件
	 * @param originalFileAbsolutePath	原文件的绝对路径
	 * @param targetFileAbsolutePath	目标文件的绝对路径
	 * @return 返回处理结果对象
	 */
	public HandleResult copyFile(String originalFileAbsolutePath, String targetFileAbsolutePath);
	
	/**
	 * 解密文件
	 * @param fileAbsolutePath	文件绝对路径
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 返回值：成功，返回(HandleResult.decryptFileAddress)解密后的下载路径(http://localhost:80/aiim/test.txt)；失败，返回null。
	 */
	public HandleResult decryptFile(String fileAbsolutePath);
	
	/**
	 * 验证电子文件的数字签名：使用密钥库和指定的keyAlias验证电子文件的数字签名。
	 * @param originalfile		进行签名验证的原文件
	 * @param signedDataFile	签名文件（仅含:签名数据的文件名称）
	 * @param keyAlias			密钥库中指定的keyAlias。	值null时：自动遍历密钥库中各个keyAlias，逐一匹配验证。
	 * @return 返回值： 成功，返回(HandleResult.signer)签名者名称；失败，返回null。
	 */
	public HandleResult verifyWithKeyStore(String originalfile, String signedDataFile, String keyAlias);
}
