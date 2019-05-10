/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.IArchivesInfoSavedManageService;
import com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService;
import com.orifound.aiim.bll.service.ICatalogPrintManageService;
import com.orifound.aiim.bll.service.IOfficialArchivesInfoManageService;
import com.orifound.aiim.bll.service.IPaperTransferManageService;
import com.orifound.aiim.bll.service.IStoreroomArchivesInfoManageService;
import com.orifound.aiim.bll.util.CommonUtil;
import com.orifound.aiim.bll.util.StringTool;
import com.orifound.aiim.dal.dao.ICatalogPrintDao;
import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.CatalogDataItem;
import com.orifound.aiim.entity.EnumCatalogType;
import com.orifound.aiim.entity.EnumCheckResult;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.OfficialArchivesInfo;
import com.orifound.aiim.entity.OfficialArchivesType;
import com.orifound.aiim.entity.PaperTransferBatchesDetail;
import com.orifound.aiim.entity.StoreroomArchivesInfo;

/**
 * 目录打印管理服务实现类
 *
 */
public class CatalogPrintManageServiceImpl implements ICatalogPrintManageService {
	
	/**
	 * 注入目录打印dao
	 */
	@Autowired
	private ICatalogPrintDao catalogPrintDao;
	
	/**
	 * 注入公文档案登记表 管理服务对象
	 */
	@Autowired
	private IOfficialArchivesInfoManageService officialArchivesInfoManageService;
	
	/**
	 * 注入档案移交管理服务对象
	 */
	@Autowired
	private IPaperTransferManageService paperTransferManageService;
	
	/**
	 * 注入库房档案信息的管理服务对象
	 */
	@Autowired
	private IStoreroomArchivesInfoManageService storeroomArchivesInfoManageService;
	
	/**
	 * 注入档案归档信息的管理服务对象
	 */
	@Autowired
	private IArchivesInfoSavedManageService archivesInfoSavedManageService;
	
	/**
	 * 注入档案归档工作表中的档案信息管理服务对象
	 */
	@Autowired
	private IArchivesInfoWorkingManageService archivesInfoWorkingManageService;
	
	/**
	 * 构造函数
	 */
	public CatalogPrintManageServiceImpl() {

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public CatalogPrintManageServiceImpl(ICatalogPrintDao catalogPrintDao) {
		this.catalogPrintDao = catalogPrintDao;
	}
	
	/**
	 * 检查的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForCatalogPrint(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (catalogPrintDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("DAO非法为空，请检查是否有进行依赖注入或赋值。");
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
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
		}

		return pFlag;
	}
	
	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.ICatalogPrintManageService#findDataItemByCatalogType(com.orifound.aiim.entity.EnumCatalogType, java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findDataItemByCatalogType(ArchivesType archivesType, EnumCatalogType catalogType, LinkedHashMap<String, CatalogDataItem> catalogDataItems, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查档案类型是否有赋值
			if (pFlag)
			{
				pErrPos = 2;
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息未初始化。");
				}else {
					if (archivesType.getID()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案分类编号非法为0");
					}
				}
			}
			
			//获取档案分类信息
			if (pFlag)
			{
				pErrPos = 3;
				if (CommonUtil.getSystemInitializer().getPlaneArchivesTypes().containsKey(archivesType.getID())==false)
				{
					pFlag = false;
					pErrInfo.getContent().append("系统中不存在编号为 "+archivesType.getID()+" 的档案分类");
				}
				else
				{
					//更新档案分类的引用
					archivesType=CommonUtil.getSystemInitializer().getPlaneArchivesTypes().get(archivesType.getID());
					archivesType = CommonUtil.getTopArchivesType(archivesType);
				}
			}

			if (pFlag) {
				pErrPos = 2;
				catalogDataItems.putAll(archivesType.getCatalogPrintTemplates().get(catalogType));
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
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

		return pFlag;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.ICatalogPrintManageService#findArchivesinfoForTransferCatalog(com.orifound.aiim.entity.ArchivesType, int, boolean, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesinfoForTransferCatalog(String depaermentName, String paperTransferBatNo,ArchivesType archivesType, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForCatalogPrint(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检测部门名称是否为空
			if (pFlag) {
				if (StringTool.checkNull(depaermentName) == false) {
					pFlag = false;
					pErrInfo.getContent().append("部门名称非法为空。");
				}
			}
			
			//检测批次号是否为空
			if (pFlag) {
				if (StringTool.checkNull(paperTransferBatNo) == false) {
					pFlag = false;
					pErrInfo.getContent().append("批次号非法为空。");
				}
			}
			
			//检查档案类型是否有赋值
			if (pFlag)
			{
				pErrPos = 2;
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息未初始化。");
				}else {
					if (archivesType.getID()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案分类编号非法为0");
					}
				}
			}
			
			//获取档案分类信息
			if (pFlag)
			{
				pErrPos = 3;
				if (CommonUtil.getSystemInitializer().getPlaneArchivesTypes().containsKey(archivesType.getID())==false)
				{
					pFlag = false;
					pErrInfo.getContent().append("系统中不存在编号为 "+archivesType.getID()+" 的档案分类");
				}
				else
				{
					//更新档案分类的引用
					archivesType=CommonUtil.getSystemInitializer().getPlaneArchivesTypes().get(archivesType.getID());
				}
			}

			//移交目录打印 查询档案信息
			if (pFlag) {
				pErrPos = 2;
				//设置自档案类型id
				if (catalogPrintDao.findArchivesinfoForTransferCatalog(depaermentName ,paperTransferBatNo, archivesType, archivesInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "移交目录打印 查询档案信息 失败：");
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
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

		return pFlag;
	}

	@Override
	public boolean findArchivesinfoForBoxCatalog(String boxBarcode, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForCatalogPrint(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检测检查盒条码是否为空
			if (pFlag) {
				if (StringTool.checkNull(boxBarcode) == false) {
					pFlag = false;
					pErrInfo.getContent().append("盒条码非法为空。");
				}
			}
			
			//检测档案信息集合是否为空
			if (pFlag) {
				if (archivesInfos == null) {
					pFlag = false;
					pErrInfo.getContent().append("档案信息集合非法为空。");
				}
			}
			
			ArchivesType archivesType = null;
			if (pFlag) {
				//根据盒条码查找档案信息
				List<StoreroomArchivesInfo> storeroomArchivesInfos = new ArrayList<StoreroomArchivesInfo>();
				if (storeroomArchivesInfoManageService.findByBoxBarcode(boxBarcode, storeroomArchivesInfos , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "根据盒条码查找档案信息 失败：");
				}
				//如果存在库房档案信息
				if(storeroomArchivesInfos.size() >= 1) {
					archivesType = new ArchivesType(storeroomArchivesInfos.get(0).getArchivesTypeID());
				}
			}
						
			//获取档案分类信息
			if (pFlag && archivesType!=null)
			{
				pErrPos = 3;
				if (CommonUtil.getSystemInitializer().getPlaneArchivesTypes().containsKey(archivesType.getID())==false)
				{
					pFlag = false;
					pErrInfo.getContent().append("系统中不存在编号为 "+archivesType.getID()+" 的档案分类");
				}
				else
				{
					//更新档案分类的引用
					archivesType=CommonUtil.getSystemInitializer().getPlaneArchivesTypes().get(archivesType.getID());
				}
			}

			if (pFlag) {
				pErrPos = 2;
				//盒内目录 打印查询
				if (catalogPrintDao.findArchivesinfoForBoxCatalog(boxBarcode, 4, archivesType, archivesInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "盒内目录 打印查询 失败：");
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
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

		return pFlag;
	}

	@Override
	public boolean findArchivesinfoForEnelope(int pNBXH, ArchivesType archivesType, ArchivesInfo archivesInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForCatalogPrint(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检测内部序号是否为空
			if (pFlag) {
				if (pNBXH <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("内部序号非法为空。");
				}
			}
			
			//检查档案类型是否有赋值
			if (pFlag)
			{
				pErrPos = 2;
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息未初始化。");
				}else {
					if (archivesType.getID()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案分类编号非法为0");
					}
				}
			}
			
			//获取档案分类信息
			if (pFlag)
			{
				pErrPos = 3;
				if (CommonUtil.getSystemInitializer().getPlaneArchivesTypes().containsKey(archivesType.getID())==false)
				{
					pFlag = false;
					pErrInfo.getContent().append("系统中不存在编号为 "+archivesType.getID()+" 的档案分类");
				}
				else
				{
					//更新档案分类的引用
					archivesType=CommonUtil.getSystemInitializer().getPlaneArchivesTypes().get(archivesType.getID());
				}
			}

			//封皮打印查询 根据内部序号查找档案信息
			if (pFlag) {
				pErrPos = 2;
				if (catalogPrintDao.findArchivesinfoForEnelope(pNBXH, archivesType, archivesInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "封皮打印查询 根据内部序号查找档案信息 失败：");
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
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

		return pFlag;
	}

	@Override
	public boolean findArchivesinfoForFileCatalog(ArchivesType archivesType, ArchivesInfo archivesInfo, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForEntityClassName(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检测档案信息集合是否为空
			if (pFlag) {
				if (archivesInfos == null) {
					pFlag = false;
					pErrInfo.getContent().append("档案信息集合非法为空。");
				}
			}
			
			//检测档案信息是否为空
			if (pFlag) {
				if (archivesInfo == null) {
					pFlag = false;
					pErrInfo.getContent().append("档案信息非法为空。");
				}
			}
			
			//判断内部序号是否为空
			int NBXH = Integer.valueOf(archivesInfo.getKeyInCol().toString());
			if (pFlag) {
				if (NBXH <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("档案内部序号非法为空。");
				}
			}
			if (pFlag) {
				pErrPos = 4;
				//根据档案的档号是否为空 判断查询表名
				if (archivesInfo.getTag()!=null && "y".equals(archivesInfo.getTag().toString())) {	//根据内部序号查找档案归档信息
					
					if (archivesInfoSavedManageService.findChildrenByNBXH(NBXH, archivesType, archivesInfos, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "根据内部序号查找档案信息 失败：");
					}
					
				} else {	//根据内部序号查找档案归档工作表中的档案信息
					if (archivesInfoWorkingManageService.findChildArchivesInfosByNBXH(NBXH, archivesType, archivesInfos, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "根据内部序号查找档案归档工作表中的档案信息 失败：");
					}
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
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

		return pFlag;
	}

	@Override
	public boolean findArchivesinfoForFileRetrieval() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findArchivesinfoForTransferList(String paperTransferBatNo,ArchivesType archivesType,List<PaperTransferBatchesDetail> paperTransferBatchesDetails,List<EnumCheckResult> enumCheckResults, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForEntityClassName(pErrInfo) == false) {
				pFlag = false;
			}

			//检查档案类型是否有赋值
			if (pFlag)
			{
				pErrPos = 2;
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息未初始化。");
				}else {
					if (archivesType.getID()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案分类编号非法为0");
					}
				}
			}
			
			//获取档案分类信息
			if (pFlag)
			{
				pErrPos = 3;
				if (CommonUtil.getSystemInitializer().getPlaneArchivesTypes().containsKey(archivesType.getID())==false)
				{
					pFlag = false;
					pErrInfo.getContent().append("系统中不存在编号为 "+archivesType.getID()+" 的档案分类");
				}
				else
				{
					//更新档案分类的引用
					archivesType=CommonUtil.getSystemInitializer().getPlaneArchivesTypes().get(archivesType.getID());
				}
			}
			
			if (pFlag) {
				if(paperTransferManageService.findPaperTransferBatchesDetails(paperTransferBatNo, archivesType, paperTransferBatchesDetails,enumCheckResults, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().append("查询批次档案详细信息失败");
				}
			}
			
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
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

		return pFlag;
	}

	@Override
	public boolean findArchivesinfoForofficialArchivesInfoCatalog(OfficialArchivesType officialArchivesType, List<OfficialArchivesInfo> officialArchivesInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForEntityClassName(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检查公文类型是否为空
			if (pFlag) {
				if(officialArchivesType==null || officialArchivesType.getID() <=0 ) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "公文类型非法为空。");
				}
			}
			
			if (pFlag) {
				pErrPos = 2;
				//根据公文类型查询公文信息
				if (officialArchivesInfoManageService.findAll(officialArchivesType, officialArchivesInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "根据公文类型查询公文信息 失败：");
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
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

		return pFlag;
	}

	/**
	 * 检查Entity的业务逻辑对象依赖注入（BLL Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkBllInjectionForEntityClassName(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查是否有进行BLL业务逻辑对象的依赖注入
			pErrPos = 1;
			if (officialArchivesInfoManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append(
						"公文档案登记表的BLL业务逻辑对象非法为空，请检查是否有进行依赖注入或赋值。");
			}
			
			if (paperTransferManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append(
						"档案移交的BLL业务逻辑对象非法为空，请检查是否有进行依赖注入或赋值。");
			}
			
			if (storeroomArchivesInfoManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append(
						"库房档案信息的BLL业务逻辑对象非法为空，请检查是否有进行依赖注入或赋值。");
			}
			
			if (archivesInfoSavedManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append(
						"档案归档信息的BLL业务逻辑对象非法为空，请检查是否有进行依赖注入或赋值。");
			}
			
			if (archivesInfoWorkingManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append(
						"档案归档工作表的BLL业务逻辑对象非法为空，请检查是否有进行依赖注入或赋值。");
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
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
		}

		return pFlag;
	}

}
