package com.orifound.aiim.web.util;

/**
 * web������
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.orifound.aiim.entity.ArchivesInfoAttachedFile;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.Config;
import com.orifound.aiim.entity.EnumImportType;
import com.orifound.aiim.entity.EnumPersonalArchivesType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.OfficialArchivesInfoAttachedFile;
import com.orifound.aiim.entity.OfficialArchivesType;
import com.orifound.aiim.entity.RetentionPeriod;
import com.orifound.aiim.entity.SystemInitializer;
import com.orifound.aiim.entity.UserInfo;

public class WebCommonUtil {
	
	/**
	 * ���ݵ��������ϵͳ���õõ�ԭ���ϴ��洢·���Ĺ��߷���<br/>
	 * �ʺ����ļ��ϴ�ʹ��
	 * @param configs ϵͳ�������
	 * @param archivesType ָ��ԭ�ĵķ���
	 * @return
	 * @throws ParseException
	 */
	public static String getAttachedFileSavePath(List<Config> configs, ArchivesType archivesType) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

		Date today = new Date();
		
		String savePath = "";
		
		for (Config config : configs) {
			Date minDate = format.parse(config.getMinValue());
			Date maxDate = format.parse(config.getMaxValue());
			
			//�жϵ�ǰ�����Ƿ��ڷ�Χ֮��
			if (today.compareTo(minDate) > 0 && today.compareTo(maxDate) < 0) {
				if (config.getConfigValue().endsWith("\\")) {
					savePath = config.getConfigValue()+archivesType.getAttachedFileSavePath()+"\\"+format.format(today)+"\\";
				}else{
					savePath = config.getConfigValue() + "\\" + archivesType.getAttachedFileSavePath() + "\\" + format.format(today) + "\\";
				}
			}
		}
		
		return savePath;
	}
	/**
	 * ���ݵ��������ϵͳ���õõ�ԭ���ϴ��洢·���Ĺ��߷���<br/>
	 * �ʺ����ļ��ϴ�ʹ��
	 * @param configs ϵͳ�������
	 * @param archivesType ָ��ԭ�ĵķ���
	 * @return
	 * @throws ParseException
	 */
	public static String getAttachedFileSavePath(List<Config> configs, OfficialArchivesType officialArchivesType) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		
		Date today = new Date();
		
		String savePath = "";
		
		for (Config config : configs) {
			Date minDate = format.parse(config.getMinValue());
			Date maxDate = format.parse(config.getMaxValue());
			
			//�жϵ�ǰ�����Ƿ��ڷ�Χ֮��
			if (today.compareTo(minDate) > 0 && today.compareTo(maxDate) < 0) {
				savePath = config.getConfigValue()+officialArchivesType.getAttachedFileSavePath()+"\\"+format.format(today)+"\\";
			}
		}
		
		return savePath;
	}
	
	/**
	 * ���ݵ��������ϵͳ���õõ�Ҫ����ԭ�Ĵ洢·���Ĺ��߷���   �洢·���������ļ���<br/>
	 * �ʺ����ļ�����ʹ��
	 * @param attachedTime ����ԭ��ʱ��
	 * @param configs ϵͳ�������
	 * @param archivesType ָ��ԭ�ĵķ���
	 * @return
	 * @throws ParseException
	 */
	public static String getAttachedFileBasePath(Date attachedTime,List<Config> configs, ArchivesType archivesType) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		
		//Date today = new Date();
		
		String savePath = "";
		
		for (Config config : configs) {
			Date minDate = format.parse(config.getMinValue());
			Date maxDate = format.parse(config.getMaxValue());
			
			//�жϵ�ǰ�����Ƿ��ڷ�Χ֮��
			if (attachedTime.compareTo(minDate) > 0 && attachedTime.compareTo(maxDate) < 0) {
				savePath = config.getConfigValue()+archivesType.getAttachedFileSavePath()+"\\";
			}
		}
		return savePath;
	}
	
	/**
	 * �õ��ļ��ڷ������ϵľ���·��
	 * @param archivesInfoAttachedFile
	 * @param configs
	 * @param archivesType
	 * @return
	 * @throws ParseException
	 */
	public static String getAttachedFilePathOnServer(ArchivesInfoAttachedFile archivesInfoAttachedFile,List<Config> configs, ArchivesType archivesType) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		
		String savePath = "";
		
		for (Config config : configs) {
			Date minDate = format.parse(config.getMinValue());
			Date maxDate = format.parse(config.getMaxValue());
			
			//�жϵ�ǰ�����Ƿ��ڷ�Χ֮��
			if (archivesInfoAttachedFile.getAttachedTime().compareTo(minDate) > 0 && archivesInfoAttachedFile.getAttachedTime().compareTo(maxDate) < 0) {
				if (config.getConfigValue().endsWith("\\")) {
					savePath = config.getConfigValue()+archivesType.getAttachedFileSavePath()+"\\"+format.format(archivesInfoAttachedFile.getAttachedTime())+"\\"+ archivesInfoAttachedFile.getOriFileName();
				}else{
					savePath = config.getConfigValue()+ "\\" +archivesType.getAttachedFileSavePath()+"\\"+format.format(archivesInfoAttachedFile.getAttachedTime())+"\\"+ archivesInfoAttachedFile.getOriFileName();
				}
				
			}
		}
		return savePath;
	}
	
	
	/**
	 * ���ݵ��������ϵͳ���õõ�Ҫ����ԭ�Ĵ洢·���Ĺ��߷���   �洢·���������ļ���<br/>
	 * �ʺ����ļ�����ʹ��
	 * @param attachedTime ����ԭ��ʱ��
	 * @param configs ϵͳ�������
	 * @param archivesType ָ��ԭ�ĵķ���
	 * @return
	 * @throws ParseException
	 */
	public static String getOfficialArchivesAttachedFileBasePath(Date attachedTime,List<Config> configs, OfficialArchivesType officialArchivesType) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		
		//Date today = new Date();
		
		String savePath = "";
		
		for (Config config : configs) {
			Date minDate = format.parse(config.getMinValue());
			Date maxDate = format.parse(config.getMaxValue());
			
			//�жϵ�ǰ�����Ƿ��ڷ�Χ֮��
			if (attachedTime.compareTo(minDate) > 0 && attachedTime.compareTo(maxDate) < 0) {
				savePath = config.getConfigValue()+officialArchivesType.getAttachedFileSavePath()+"\\";
			}
		}
		return savePath;
	}
	
	/**
	 * �õ��ļ��ڷ������ϵľ���·��
	 * @param archivesInfoAttachedFile
	 * @param configs
	 * @param archivesType
	 * @return
	 * @throws ParseException
	 */
	public static String getOfficialAttachedFilePathOnServer(OfficialArchivesInfoAttachedFile officialArchivesInfoAttachedFile,List<Config> configs, OfficialArchivesType officialArchivesType) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		
		String savePath = "";
		
		for (Config config : configs) {
			Date minDate = format.parse(config.getMinValue());
			Date maxDate = format.parse(config.getMaxValue());
			
			//�жϵ�ǰ�����Ƿ��ڷ�Χ֮��
			if (officialArchivesInfoAttachedFile.getAttachedTime().compareTo(minDate) > 0 && officialArchivesInfoAttachedFile.getAttachedTime().compareTo(maxDate) < 0) {
				if (config.getConfigValue().endsWith("\\")) {
					savePath = config.getConfigValue()+officialArchivesType.getAttachedFileSavePath()+"\\"+format.format(officialArchivesInfoAttachedFile.getAttachedTime())+"\\"+ officialArchivesInfoAttachedFile.getOriFileName();
				}else{
					savePath = config.getConfigValue()+ "\\" +officialArchivesType.getAttachedFileSavePath()+"\\"+format.format(officialArchivesInfoAttachedFile.getAttachedTime())+"\\"+ officialArchivesInfoAttachedFile.getOriFileName();
				}
				
			}
		}
		return savePath;
	}
	
	/**
	 * ��ȡ��ǰ�û���Ϣ
	 * @param request 
	 * @param perInfo ���ش�����Ϣ
	 */
	public static UserInfo getUserInfo(HttpServletRequest request, ErrInfo pErrInfo){
		UserInfo userInfo = (UserInfo)request.getSession().getAttribute("userInfo");
		if(userInfo == null){
			pErrInfo.getContent().append("��ȡ�û���Ϣʧ��,�����µ�¼��");
		}
		return userInfo;
	}
	
	/**
	 * ��ȡ���޵ı������������ֵ伯��
	 * @param retentionPeriods	���ر������������ֵ伯��
	 * @param totalYear			��������:�����
	 * @return �����Ƿ�����ɹ�
	 */
	public List<RetentionPeriod> getLimitedRetentionPeriods(int totalYear) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		List<RetentionPeriod> retentionPeriods = new ArrayList<RetentionPeriod>();
		
		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//��Ᵽ�����������ֵ�����Ƿ�Ϊ��
			if(totalYear <= 0) {
				pFlag = false;
				pErrInfo.getContent().append("��������:����ȷǷ�Ϊ�ա�");
			}
			
			Collection<RetentionPeriod> collection = SystemInitializer.getInstance().getRetentionPeriods().values();
			if(collection==null || collection.size()<=0) {
				pFlag = false;
				pErrInfo.getContent().append("ϵͳ��ʼ������ȡ�������������ֵ伯�ϷǷ�Ϊ�ա�");
			}

			if (pFlag) {
				pErrPos = 2;
				List<RetentionPeriod> allRetentionPeriods = new ArrayList<RetentionPeriod>();
				allRetentionPeriods.addAll(collection);
				
				//�ж� �������������ֵ���� ������������:����ȵĽ��в���
				for(RetentionPeriod entity : allRetentionPeriods) {
					if(entity.getTotalYears() > totalYear) {
						retentionPeriods.add(entity);
					}
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo .getContent().append(e.toString());
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
		return retentionPeriods;
	}
	
	/**
	 * �õ�ѧ���������༯��
	 * @param archivesTypes
	 * @return
	 */
	public static List<ArchivesType> getStudentTypes(List<ArchivesType> pArchivesTypes){
		List<ArchivesType> archivesTypes = new ArrayList<ArchivesType>();
		for (int i=0;i<pArchivesTypes.size();i++) {
			if (pArchivesTypes.get(i).getPersonalArchivesFlag() == EnumPersonalArchivesType.ѧ�����µ�������) {
				archivesTypes.add(pArchivesTypes.get(i));
			}
//			if (archivesTypes.get(i).getPersonalArchivesFlag() == EnumPersonalArchivesType.��ʿ������) {
//				archivesTypes.add(archivesTypes.get(i));
//			}else if(archivesTypes.get(i).getPersonalArchivesFlag() == EnumPersonalArchivesType.˶ʿ������){
//				archivesTypes.add(archivesTypes.get(i));
//			}else if(archivesTypes.get(i).getPersonalArchivesFlag() == EnumPersonalArchivesType.����������){
//				archivesTypes.add(archivesTypes.get(i));
//			}
		}
		return archivesTypes;
	}
	
	/**
	 * �õ���ְ���������༯��
	 * @param archivesTypes
	 * @return
	 */
	public static List<ArchivesType> getTeacherTypes(List<ArchivesType> pArchivesTypes){
		List<ArchivesType> archivesTypes = new ArrayList<ArchivesType>();
		for (int i=0;i<pArchivesTypes.size();i++) {
			if (pArchivesTypes.get(i).getPersonalArchivesFlag() == EnumPersonalArchivesType.��ְ�����µ�������) {
				archivesTypes.add(pArchivesTypes.get(i));
			}
		}
		return archivesTypes;
	}
	
	/**
	 * ���ݵ������͵õ���������
	 * @param archivesTypes
	 * @return
	 */
	public static ArchivesType getPersonTypeByImportType(EnumImportType enumImportType){
		Map<Integer, ArchivesType> archivesTypes = SystemInitializer.getInstance().getPlaneArchivesTypes();

		if (enumImportType == EnumImportType.��ʿ������������Ϣ || enumImportType == EnumImportType.��ʿ����ҵȥ����Ϣ) {
			for (ArchivesType archivesType : archivesTypes.values()) {
				if(archivesType.getPersonalArchivesFlag() == EnumPersonalArchivesType.��ʿ������){
					return archivesType;
				}
			}
		}else if (enumImportType == EnumImportType.˶ʿ������������Ϣ || enumImportType == EnumImportType.˶ʿ����ҵȥ����Ϣ) {
			for (ArchivesType archivesType : archivesTypes.values()) {
				if(archivesType.getPersonalArchivesFlag() == EnumPersonalArchivesType.˶ʿ������){
					return archivesType;
				}
			}
		}else if (enumImportType == EnumImportType.����������������Ϣ || enumImportType == EnumImportType.��������ҵȥ����Ϣ) {
			for (ArchivesType archivesType : archivesTypes.values()) {
				if(archivesType.getPersonalArchivesFlag() == EnumPersonalArchivesType.����������){
					return archivesType;
				}
			}
		}else if (enumImportType == EnumImportType.��ʿ�󵵰�������Ϣ) {
			for (ArchivesType archivesType : archivesTypes.values()) {
				if(archivesType.getPersonalArchivesFlag() == EnumPersonalArchivesType.��ʿ�󵵰�){
					return archivesType;
				}
			}
		}
		else if (enumImportType == EnumImportType.��ְ������������Ϣ) {
			for (ArchivesType archivesType : archivesTypes.values()) {
				if(archivesType.getPersonalArchivesFlag() == EnumPersonalArchivesType.��ְ������){
					return archivesType;
				}
			}
		}
		return null;
	}
	
	/**
	 * ��ÿͻ���IP��ַ
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
