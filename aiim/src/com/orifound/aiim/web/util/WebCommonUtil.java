package com.orifound.aiim.web.util;

/**
 * web工具类
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
	 * 根据档案分类和系统配置得到原文上传存储路径的工具方法<br/>
	 * 适合于文件上传使用
	 * @param configs 系统配置项集合
	 * @param archivesType 指定原文的分类
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
			
			//判断当前日期是否在范围之内
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
	 * 根据档案分类和系统配置得到原文上传存储路径的工具方法<br/>
	 * 适合于文件上传使用
	 * @param configs 系统配置项集合
	 * @param archivesType 指定原文的分类
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
			
			//判断当前日期是否在范围之内
			if (today.compareTo(minDate) > 0 && today.compareTo(maxDate) < 0) {
				savePath = config.getConfigValue()+officialArchivesType.getAttachedFileSavePath()+"\\"+format.format(today)+"\\";
			}
		}
		
		return savePath;
	}
	
	/**
	 * 根据档案分类和系统配置得到要下载原文存储路径的工具方法   存储路径不包含文件名<br/>
	 * 适合于文件下载使用
	 * @param attachedTime 附加原文时间
	 * @param configs 系统配置项集合
	 * @param archivesType 指定原文的分类
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
			
			//判断当前日期是否在范围之内
			if (attachedTime.compareTo(minDate) > 0 && attachedTime.compareTo(maxDate) < 0) {
				savePath = config.getConfigValue()+archivesType.getAttachedFileSavePath()+"\\";
			}
		}
		return savePath;
	}
	
	/**
	 * 得到文件在服务器上的绝对路径
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
			
			//判断当前日期是否在范围之内
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
	 * 根据档案分类和系统配置得到要下载原文存储路径的工具方法   存储路径不包含文件名<br/>
	 * 适合于文件下载使用
	 * @param attachedTime 附加原文时间
	 * @param configs 系统配置项集合
	 * @param archivesType 指定原文的分类
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
			
			//判断当前日期是否在范围之内
			if (attachedTime.compareTo(minDate) > 0 && attachedTime.compareTo(maxDate) < 0) {
				savePath = config.getConfigValue()+officialArchivesType.getAttachedFileSavePath()+"\\";
			}
		}
		return savePath;
	}
	
	/**
	 * 得到文件在服务器上的绝对路径
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
			
			//判断当前日期是否在范围之内
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
	 * 获取当前用户信息
	 * @param request 
	 * @param perInfo 返回错误信息
	 */
	public static UserInfo getUserInfo(HttpServletRequest request, ErrInfo pErrInfo){
		UserInfo userInfo = (UserInfo)request.getSession().getAttribute("userInfo");
		if(userInfo == null){
			pErrInfo.getContent().append("获取用户信息失败,请重新登录！");
		}
		return userInfo;
	}
	
	/**
	 * 获取受限的保管期限数据字典集合
	 * @param retentionPeriods	返回保管期限数据字典集合
	 * @param totalYear			受限条件:总年度
	 * @return 返回是否操作成功
	 */
	public List<RetentionPeriod> getLimitedRetentionPeriods(int totalYear) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		List<RetentionPeriod> retentionPeriods = new ArrayList<RetentionPeriod>();
		
		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//检测保管期限数据字典参数是否为空
			if(totalYear <= 0) {
				pFlag = false;
				pErrInfo.getContent().append("受限条件:总年度非法为空。");
			}
			
			Collection<RetentionPeriod> collection = SystemInitializer.getInstance().getRetentionPeriods().values();
			if(collection==null || collection.size()<=0) {
				pFlag = false;
				pErrInfo.getContent().append("系统初始化器获取保管期限数据字典集合非法为空。");
			}

			if (pFlag) {
				pErrPos = 2;
				List<RetentionPeriod> allRetentionPeriods = new ArrayList<RetentionPeriod>();
				allRetentionPeriods.addAll(collection);
				
				//判断 保管期限数据字典表中 大于受限条件:总年度的进行插入
				for(RetentionPeriod entity : allRetentionPeriods) {
					if(entity.getTotalYears() > totalYear) {
						retentionPeriods.add(entity);
					}
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo .getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
			//销毁局部变量
			throwable = null;
		}
		return retentionPeriods;
	}
	
	/**
	 * 得到学生档案分类集合
	 * @param archivesTypes
	 * @return
	 */
	public static List<ArchivesType> getStudentTypes(List<ArchivesType> pArchivesTypes){
		List<ArchivesType> archivesTypes = new ArrayList<ArchivesType>();
		for (int i=0;i<pArchivesTypes.size();i++) {
			if (pArchivesTypes.get(i).getPersonalArchivesFlag() == EnumPersonalArchivesType.学生人事档案分类) {
				archivesTypes.add(pArchivesTypes.get(i));
			}
//			if (archivesTypes.get(i).getPersonalArchivesFlag() == EnumPersonalArchivesType.博士生档案) {
//				archivesTypes.add(archivesTypes.get(i));
//			}else if(archivesTypes.get(i).getPersonalArchivesFlag() == EnumPersonalArchivesType.硕士生档案){
//				archivesTypes.add(archivesTypes.get(i));
//			}else if(archivesTypes.get(i).getPersonalArchivesFlag() == EnumPersonalArchivesType.本科生档案){
//				archivesTypes.add(archivesTypes.get(i));
//			}
		}
		return archivesTypes;
	}
	
	/**
	 * 得到教职工档案分类集合
	 * @param archivesTypes
	 * @return
	 */
	public static List<ArchivesType> getTeacherTypes(List<ArchivesType> pArchivesTypes){
		List<ArchivesType> archivesTypes = new ArrayList<ArchivesType>();
		for (int i=0;i<pArchivesTypes.size();i++) {
			if (pArchivesTypes.get(i).getPersonalArchivesFlag() == EnumPersonalArchivesType.教职工人事档案分类) {
				archivesTypes.add(pArchivesTypes.get(i));
			}
		}
		return archivesTypes;
	}
	
	/**
	 * 根据导入类型得到档案分类
	 * @param archivesTypes
	 * @return
	 */
	public static ArchivesType getPersonTypeByImportType(EnumImportType enumImportType){
		Map<Integer, ArchivesType> archivesTypes = SystemInitializer.getInstance().getPlaneArchivesTypes();

		if (enumImportType == EnumImportType.博士生档案基本信息 || enumImportType == EnumImportType.博士生毕业去向信息) {
			for (ArchivesType archivesType : archivesTypes.values()) {
				if(archivesType.getPersonalArchivesFlag() == EnumPersonalArchivesType.博士生档案){
					return archivesType;
				}
			}
		}else if (enumImportType == EnumImportType.硕士生档案基本信息 || enumImportType == EnumImportType.硕士生毕业去向信息) {
			for (ArchivesType archivesType : archivesTypes.values()) {
				if(archivesType.getPersonalArchivesFlag() == EnumPersonalArchivesType.硕士生档案){
					return archivesType;
				}
			}
		}else if (enumImportType == EnumImportType.本科生档案基本信息 || enumImportType == EnumImportType.本科生毕业去向信息) {
			for (ArchivesType archivesType : archivesTypes.values()) {
				if(archivesType.getPersonalArchivesFlag() == EnumPersonalArchivesType.本科生档案){
					return archivesType;
				}
			}
		}else if (enumImportType == EnumImportType.博士后档案基本信息) {
			for (ArchivesType archivesType : archivesTypes.values()) {
				if(archivesType.getPersonalArchivesFlag() == EnumPersonalArchivesType.博士后档案){
					return archivesType;
				}
			}
		}
		else if (enumImportType == EnumImportType.教职工档案基本信息) {
			for (ArchivesType archivesType : archivesTypes.values()) {
				if(archivesType.getPersonalArchivesFlag() == EnumPersonalArchivesType.教职工档案){
					return archivesType;
				}
			}
		}
		return null;
	}
	
	/**
	 * 获得客户端IP地址
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
