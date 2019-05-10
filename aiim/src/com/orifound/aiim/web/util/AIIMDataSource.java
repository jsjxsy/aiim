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
			logger.error("���ݿ������û�������ʧ��,���������Ƿ���ȷ��");
			System.out.println("���ݿ������û�������ʧ�ܣ�");
			e.printStackTrace();
		}
	}

	@Override
	public synchronized void setPassword(String password) {
		try {
			super.setPassword(this.decrypt(password));
		} catch (Exception e) {
			logger.error("���ݿ������������ʧ��,���������Ƿ���ȷ��");
			System.out.println("���ݿ������������ʧ�ܣ�");
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
			logger.error("���ݿ����Ӵ�����ʧ��,���������Ƿ���ȷ��");
			System.out.println("���ݿ����Ӵ�����ʧ�ܣ�");
			e.printStackTrace();
		}
	}

	/**
	 * ����
	 * @param content  ����������
	 * @param password ������Կ
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
		Cipher cipher = Cipher.getInstance("AES");// ����������
		cipher.init(Cipher.DECRYPT_MODE, key);// ��ʼ��
		byte[] result = cipher.doFinal(decryptFrom);
		
		return new String(result); // ����
	}

	/**
	 * ��16����ת��Ϊ������
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
