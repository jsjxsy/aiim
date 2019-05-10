package com.orifound.aiim.web.util;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AIIMDataSource extends BasicDataSource {

	private final static String KEYSTR = "aiim";
	
	static Log logger = LogFactory.getLog(AIIMDataSource.class);
	
	@Override
	public synchronized void setUsername(String username) {
		// TODO Auto-generated method stub
		try {
			//super.setUsername(this.decrypt(username));
			super.setUsername(username);
		} catch (Exception e) {
			logger.error("数据库链接用户名解密失败,请检查密文是否正确！");
			System.out.println("数据库链接用户名解密失败！");
			e.printStackTrace();
		}
	}

	@Override
	public synchronized void setPassword(String password) {
		try {
			super.setPassword(this.decrypt(password));
		} catch (Exception e) {
			logger.error("数据库链接密码解密失败,请检查密文是否正确！");
			System.out.println("数据库链接密码解密失败！");
			e.printStackTrace();
		}
	}
	
	@Override
	public synchronized void setUrl(String url) {
		// TODO Auto-generated method stub
		try {
			//super.setUrl(this.decrypt(url));
			super.setUrl(url);
		} catch (Exception e) {
			logger.error("数据库链接串解密失败,请检查密文是否正确！");
			System.out.println("数据库链接串解密失败！");
			e.printStackTrace();
		}
	}

	/**
	 * 解密
	 * @param content  待解密内容
	 * @param password 解密密钥
	 * @return
	 * @throws Exception  
	 */
	public String decrypt(String content) throws Exception {
		byte[] decryptFrom = parseHexStr2Byte(content);

		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		kgen.init(128,new SecureRandom(KEYSTR.getBytes()));
		SecretKey secretKey = kgen.generateKey();
		byte[] enCodeFormat = secretKey.getEncoded();
		SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
		Cipher cipher = Cipher.getInstance("AES");// 创建密码器
		cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
		byte[] result = cipher.doFinal(decryptFrom);
		
		return new String(result); // 加密
	}

	/**
	 * 将16进制转换为二进制
	 * 
	 * @param hexStr
	 * @return
	 */
	public byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
					16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}
}
