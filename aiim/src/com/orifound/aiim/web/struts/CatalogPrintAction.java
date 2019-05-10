package com.orifound.aiim.web.struts;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.ICatalogPrintManageService;
import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ArchivesTypeDataItem;
import com.orifound.aiim.entity.CatalogDataItem;
import com.orifound.aiim.entity.EnumCatalogType;
import com.orifound.aiim.entity.EnumCheckResult;
import com.orifound.aiim.entity.EnumSystemDataItem;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.OfficialArchivesInfo;
import com.orifound.aiim.entity.OfficialArchivesType;
import com.orifound.aiim.entity.PaperTransferBatchesDetail;
import com.orifound.aiim.entity.PrintPageSet;
import com.orifound.aiim.entity.SystemInitializer;

/**
 * 目录打印Action
 *
 */
public class CatalogPrintAction extends BaseAction {

	private static final long serialVersionUID = 4101833970440922557L;
	
	/**
	 * 目录打印管理服务类
	 */
	@Autowired
	private ICatalogPrintManageService catalogPrintManageService;
	
	/**
	 * 保存Action跳转URL地址
	 */
	private String resultURL;
	
	/**
	 * 档案类型id
	 */
	private int archivesTypeID;
	
	/**
	 * 打印查询 档案信息集合
	 */
	private List<ArchivesInfo> archivesInfos = new ArrayList<ArchivesInfo>();
	
	/**
	 * 打印目录类型id
	 */
	private int catalogTypeID;
	
	/**
	 * 目录打印模板类型
	 */
	private EnumCatalogType catalogType;
	
	/**
	 * 显示打印数据项集合
	 */
	private LinkedHashMap<String, CatalogDataItem> catalogDataItems = new LinkedHashMap<String, CatalogDataItem>();
	
	/**
	 * 打印页面设置参数实体
	 */
	private PrintPageSet printPageSet = new PrintPageSet();
	
	/**
	 * 公文类型id
	 */
	private int officialArchivesTypeID;
	
	/**
	 * 打印显示字段名称数组
	 */
	private String[] columnName;
	
	/**
	 * 页面显示数据项集合
	 */
	private List<ArchivesTypeDataItem> viewDataItems = new ArrayList<ArchivesTypeDataItem>();
	
	/**
	 * 移交批次号
	 */
	private String batNo;
	
	/**
	 * 盒条码
	 */
	private String boxBarcode;
	
	/**
	 * 是否归档
	 * y:归档,n:未归档
	 */
	private String isArchived;
	
	/**
	 * 档案 内部序号
	 */
	private int NBXH;
	
	/**
	 * 打印案卷目录 部门名称 
	 */
	private String depaermentName;

	/**
	 * 目录打印配置
	 * @return String
	 */
	public String printConfig() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForBll(pErrInfo ) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				//初始化目录打印模板类型
				catalogType = EnumCatalogType.getEnumElement(catalogTypeID);
				
				//公文目录打印
				if(catalogTypeID == EnumCatalogType.公文目录.getEnumValue()) {
					System.out.println("公文目录打印配置");
					if (officialArchivesTypeID <= 0) {
						pFlag = false;
						pErrInfo.getContent().append("公文目录打印->公文类型id非法为空。");
					}
					if (pFlag) {
						OfficialArchivesType officialArchivesType = SystemInitializer.getInstance().getOfficialArchivesTypes().get(officialArchivesTypeID);
						catalogDataItems = officialArchivesType.getCatalogPrintTemplates().get(catalogType);
					}
					
					//非公文目录打印
				} else if(archivesTypeID>=1) {		
					ArchivesType archivesType = new ArchivesType(archivesTypeID);
					//根据目录打印模板类型查询打印显示的数据项集合
					if (catalogPrintManageService.findDataItemByCatalogType(archivesType , catalogType , catalogDataItems, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "根据目录打印模板类型查询打印显示的数据项集合 失败：");
					}
				}
				
				//检测打印数据项集合是否为空
				if (pFlag) {
					if (catalogDataItems == null) {
						pFlag = false;
						pErrInfo.getContent().append("目录打印->打印数据项集合非法为空。");
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
		resultURL = "/PRINT/print_config.jsp";
		return SUCCESS;
	}
	
	/**
	 * 打印目录选择
	 * @return
	 */
	public String switchCatalog() {
		System.out.println("----打印目录选择--------");
		System.out.println("catalogTypeID="+catalogTypeID);
		switch (catalogTypeID) {
		case 0:
			resultURL = "/PRINT/print_select.jsp";
			break;
			
			//案卷目录	打印查询
		case 1:
			archivesCatalog();
			resultURL = "/PRINT/archivesCatalog.jsp";
			break;
			
			//盒内目录	打印查询
		case 2:
			boxCatalog();
			resultURL = "/PRINT/boxCatalog.jsp";
			break;
			
			//卷内目录	打印查询
		case 3:
			fileCatalog();
			resultURL = "/PRINT/fileCatalog.jsp";
			break;
			
			//公文目录	打印查询	
		case 4:
			officialArchivesInfoCatalogPrint();
			resultURL = "/PRINT/officialArchivesInfoCatalog.jsp";
			break;
		
			//备考表		打印查询	
		case 5:
			appendixTablePrint();
			resultURL = "/PRINT/appendixTable.jsp";
			break;
			
			//封皮	打印查询	
		case 6:
			envelopePrint();
			resultURL = "/PRINT/envelope.jsp";
			break;
			
			//调卷单	打印查询	
		case 7:
			fileRetrieval();
			resultURL = "/PRINT/fileRetrieval.jsp";
			break;
		
		//移交清单	打印查询
		case 11:
			transferList();
			resultURL = "/PRINT/transferList.jsp";
			
		default:
			break;
		}
		return SUCCESS;
	}
	
	/**
	 * 盒内目录 打印查询
	 * 页面传递参数：boxBarcode 盒条码
	 */
	private void boxCatalog() {
		System.out.println("----盒内目录 打印查询--------");
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForBll(pErrInfo) == false) {
				pFlag = false;
			}
			
			if (pFlag) {
				pErrPos = 2;
				//构建打印页面显示数据项集合
				this.buildDataItem();
				
				pErrPos = 3;
				//盒内目录 打印查询
				if (catalogPrintManageService.findArchivesinfoForBoxCatalog(boxBarcode, archivesInfos , pErrInfo) == false) {
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
	}

	/**
	 * 移交清单 打印查询
	 * 页面传递参数: archivesTypeID	档案类型id
	 * 				batNo	批次号
	 * 				stateType 档案状态
	 */
	public void transferList() {
		System.out.println("----移交清单 打印查询--------");
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		HttpServletRequest request = getRequest();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForBll(pErrInfo ) == false) {
				pFlag = false;
			}

			ArchivesType archivesType = new ArchivesType(archivesTypeID);
			List<EnumCheckResult> enumCheckResults = new ArrayList<EnumCheckResult>();
			if("1".equals(request.getParameter("stateType"))){
				enumCheckResults.add(EnumCheckResult.尚未审核);
			}else if("2".equals(request.getParameter("stateType"))){
				enumCheckResults.add(EnumCheckResult.尚未审核);
				enumCheckResults.add(EnumCheckResult.业务指导室审核通过);
				enumCheckResults.add(EnumCheckResult.档案管理室审核通过);
			}else if("3".equals(request.getParameter("stateType"))){//业务指导室查看当前清单
				enumCheckResults.add(EnumCheckResult.尚未审核);
				enumCheckResults.add(EnumCheckResult.业务指导室审核通过);
			}else if("4".equals(request.getParameter("stateType"))){
				enumCheckResults.add(EnumCheckResult.业务指导室审核通过);
				enumCheckResults.add(EnumCheckResult.档案管理室审核通过);
			}else if("5".equals(request.getParameter("stateType"))){
				enumCheckResults.add(EnumCheckResult.业务指导室审核通过);
				enumCheckResults.add(EnumCheckResult.档案管理室审核通过);
			}else if("6".equals(request.getParameter("stateType"))){
				enumCheckResults.add(EnumCheckResult.档案管理室审核通过);
			}
			
			//接收返回档案信息列表
			List<PaperTransferBatchesDetail> paperTransferBatchesDetails = new ArrayList<PaperTransferBatchesDetail>();
			
			//调用业务逻辑
			if (pFlag) {
				if(catalogPrintManageService.findArchivesinfoForTransferList(batNo, archivesType, paperTransferBatchesDetails, enumCheckResults, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "移交清单 打印查询 失败：");
				}
     		}
			request.setAttribute("paperTransferBatchesDetails", paperTransferBatchesDetails);
			
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
	}
	
	/**
	 * 案卷目录	打印查询
	 * 页面传递参数:	archivesTypeID 档案类型id
	 * 				batNo	批次号
	 * depaermentName 部门名称:DXBM(档案形成部门)、YWZD(业务指导室)、DAGL(档案管理室)
	 */
	public void archivesCatalog() {
		System.out.println("----案卷目录	打印查询--------");
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForBll(pErrInfo ) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				
				//构建打印页面显示数据项集合
				this.buildDataItem();
				
				//案卷目录打印 查询档案信息
				ArchivesType archivesType = new ArchivesType(archivesTypeID);
				System.out.println();
				if (catalogPrintManageService.findArchivesinfoForTransferCatalog(depaermentName, batNo, archivesType, archivesInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "案卷目录打印 查询档案信息 失败：");
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
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
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
		System.out.println(pErrInfo.toString());
	}
	
	/**
	 * 卷内目录	打印查询
	 * 页面传递参数: isArchived	是否归档
	 * 				NBXH	内部序号
	 * 				archivesTypeID 档案类型id
	 */
	private void fileCatalog() {
		System.out.println("----卷内目录	打印查询--------");
		System.out.println("NBXH="+NBXH);
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForBll(pErrInfo ) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				//构建打印页面显示数据项集合
				this.buildDataItem();
				
				//案卷目录打印 查询档案信息
				ArchivesType archivesType = new ArchivesType(archivesTypeID);
				ArchivesInfo archivesInfo = new ArchivesInfo();
				archivesInfo.setKeyInCol(NBXH);
				if("y".equals(isArchived)) {
					//查询已归档信息 
					archivesInfo.setTag("y");
				}
				//卷内目录 打印查询
				if (catalogPrintManageService.findArchivesinfoForFileCatalog(archivesType, archivesInfo, archivesInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "卷内目录 打印查询 失败：");
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
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
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
	}
	
	/**
	 * 备考表 打印查询
	 */
	private void appendixTablePrint() {
		
	}
	
	/**
	 * 封皮打印查询
	 */
	private void envelopePrint() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForBll(pErrInfo ) == false) {
				pFlag = false;
			}
			
			ArchivesType archivesType = new ArchivesType(archivesTypeID);
			ArchivesInfo archivesInfo = new ArchivesInfo();
			if (pFlag) {
				pErrPos = 2;
				if (catalogPrintManageService.findArchivesinfoForEnelope(NBXH, archivesType, archivesInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action封皮打印查询 根据内部序号查找档案信息 失败：");
				}
				getRequest().setAttribute("archivesInfo", archivesInfo);
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
	}
	
	/**
	 * 调卷单 打印查询
	 */
	private void fileRetrieval() {
		System.out.println("----调卷单	打印查询--------");
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForBll(pErrInfo ) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				//案卷目录打印 查询档案信息
				ArchivesType archivesType = new ArchivesType(archivesTypeID);
				
				if (catalogPrintManageService.findArchivesinfoForTransferCatalog(depaermentName, batNo, archivesType, archivesInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "案卷目录打印 查询档案信息 失败：");
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			e.printStackTrace();
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
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
	}

	/**
	 * 构建打印页面显示数据项集合
	 * 并进行页面标题设置
	 */
	private void buildDataItem() {
		//设置显示数据项集合
		LinkedHashMap<String, ArchivesTypeDataItem> dataItems = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID).getDataItemsForAll();
		
		if(dataItems.containsKey(EnumSystemDataItem.保管期限文本.getEnumValue())) {
			dataItems.get(EnumSystemDataItem.保管期限文本.getEnumValue()).setDisplayText("保管期限");
		}
		
		if(dataItems.containsKey(EnumSystemDataItem.档案密级文本.getEnumValue())) {
			dataItems.get(EnumSystemDataItem.档案密级文本.getEnumValue()).setDisplayText("档案密级");
		}
		
		if(dataItems.containsKey(EnumSystemDataItem.档案形成部门名称.getEnumValue())) {
			dataItems.get(EnumSystemDataItem.档案形成部门名称.getEnumValue()).setDisplayText("档案形成部门");
		}
		
		if(dataItems.containsKey(EnumSystemDataItem.题名.getEnumValue())) {
			dataItems.get(EnumSystemDataItem.题名.getEnumValue()).setKeyInCol("class='archivesinfoTitle'");
		}
		
		for(String viewName : columnName) {
			if(dataItems.containsKey(viewName)) {
				viewDataItems.add(dataItems.get(viewName));
			}
		}
		getRequest().setAttribute("viewDataItems", viewDataItems);
	}
	
	/**
	 * 公文目录打印
	 * @return
	 */
	public void officialArchivesInfoCatalogPrint() {
		System.out.println("-------公文目录打印--------");
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForBll(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				
				//构建打印页面显示数据项集合
				this.buildDataItem();
				List<OfficialArchivesInfo> officialArchivesInfos = new ArrayList<OfficialArchivesInfo>();
				OfficialArchivesType officialArchivesType = SystemInitializer.getInstance().getOfficialArchivesTypes().get(officialArchivesTypeID);
				if (catalogPrintManageService.findArchivesinfoForofficialArchivesInfoCatalog(officialArchivesType, officialArchivesInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "公文目录 打印查询 失败：");
				} 
				getRequest().setAttribute("officialArchivesInfos", officialArchivesInfos);
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
	}

	public String getResultURL() {
		return resultURL;
	}

	public void setResultURL(String resultURL) {
		this.resultURL = resultURL;
	}
	
	/**
	 * 检查目录打印的业务逻辑对象依赖注入（BLL Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkBllInjectionForBll(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查是否有进行BLL业务逻辑对象的依赖注入
			pErrPos = 1;
			if (catalogPrintManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append(
						"目录打印的BLL业务逻辑对象非法为空，请检查是否有进行依赖注入或赋值。");
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

	public LinkedHashMap<String, CatalogDataItem> getCatalogDataItems() {
		return catalogDataItems;
	}

	public void setCatalogDataItems(
			LinkedHashMap<String, CatalogDataItem> catalogDataItems) {
		this.catalogDataItems = catalogDataItems;
	}

	public int getArchivesTypeID() {
		return archivesTypeID;
	}

	public void setArchivesTypeID(int archivesTypeID) {
		this.archivesTypeID = archivesTypeID;
	}

	public int getCatalogTypeID() {
		return catalogTypeID;
	}

	public void setCatalogTypeID(int catalogTypeID) {
		this.catalogTypeID = catalogTypeID;
	}

	public EnumCatalogType getCatalogType() {
		return catalogType;
	}

	public void setCatalogType(EnumCatalogType catalogType) {
		this.catalogType = catalogType;
	}

	public PrintPageSet getPrintPageSet() {
		return printPageSet;
	}

	public List<ArchivesInfo> getArchivesInfos() {
		return archivesInfos;
	}

	public void setArchivesInfos(List<ArchivesInfo> archivesInfos) {
		this.archivesInfos = archivesInfos;
	}

	public void setPrintPageSet(PrintPageSet printPageSet) {
		this.printPageSet = printPageSet;
	}

	public String getDepaermentName() {
		return depaermentName;
	}

	public void setDepaermentName(String depaermentName) {
		this.depaermentName = depaermentName;
	}

	public int getOfficialArchivesTypeID() {
		return officialArchivesTypeID;
	}

	public String getBatNo() {
		return batNo;
	}

	public void setBatNo(String batNo) {
		this.batNo = batNo;
	}

	public String getBoxBarcode() {
		return boxBarcode;
	}

	public void setBoxBarcode(String boxBarcode) {
		this.boxBarcode = boxBarcode;
	}

	public void setOfficialArchivesTypeID(int officialArchivesTypeID) {
		this.officialArchivesTypeID = officialArchivesTypeID;
	}

	public String[] getColumnName() {
		return columnName;
	}

	public void setColumnName(String[] columnName) {
		this.columnName = columnName;
	}

	public List<ArchivesTypeDataItem> getViewDataItems() {
		return viewDataItems;
	}

	public String getIsArchived() {
		return isArchived;
	}

	public void setIsArchived(String isArchived) {
		this.isArchived = isArchived;
	}

	public int getNBXH() {
		return NBXH;
	}

	public void setNBXH(int nBXH) {
		NBXH = nBXH;
	}

	public void setViewDataItems(List<ArchivesTypeDataItem> viewDataItems) {
		this.viewDataItems = viewDataItems;
	}
}