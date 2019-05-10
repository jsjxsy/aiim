package com.orifound.aiim.web.struts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ActionSupport;
import com.orifound.aiim.bll.service.ICurrentBarcodeManageService;
import com.orifound.aiim.bll.service.IStocktakingInfoManageService;
import com.orifound.aiim.bll.service.IStocktakingManageService;
import com.orifound.aiim.bll.service.IStoreroomManageService;
import com.orifound.aiim.bll.service.ITempratureHumidityInfoManageService;
import com.orifound.aiim.dal.dao.IStocktakingInfoDao;
import com.orifound.aiim.entity.CurrentBarcode;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EnumBarcodeType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.StockReportAddressNotMatch;
import com.orifound.aiim.entity.StockReportArchivesBoxNotMatch;
import com.orifound.aiim.entity.StockReportArchivesCount;
import com.orifound.aiim.entity.StockReportPaperNotExist;
import com.orifound.aiim.entity.StockReportSystemNotExist;
import com.orifound.aiim.entity.StocktakingAddressBoxDetail;
import com.orifound.aiim.entity.StocktakingArchivesDetail;
import com.orifound.aiim.entity.StocktakingInfo;
import com.orifound.aiim.entity.StoreroomArchivesInfo;
import com.orifound.aiim.entity.StoreroomStructure;
import com.orifound.aiim.entity.TempratureHumidityInfo;
/**
 * 
 * @author Administrator
 *
 */
public class StoreroomManageAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 123343L;
	
	/**
	 * 库房管理业务逻辑类
	 */
	@Autowired
	private IStoreroomManageService storeroomManageService ;
	
	/**
	 * 条形码打印业务逻辑管理接口
	 */
	@Autowired
	private ICurrentBarcodeManageService currentBarcodeManageService;
	
	/**
	 * 库房温度湿度信息管理服务接口
	 */
	@Autowired
	private ITempratureHumidityInfoManageService tempratureHumidityInfoManageService;
	
	/**
	 * 库房盘点工作信息管理服务接口
	 */
	@Autowired
	private IStocktakingInfoManageService stocktakingInfoManageService;
	
	/**
	 * 库房盘点管理服务接口
	 */
	@Autowired
	private IStocktakingManageService stocktakingManageService;
	/**
	 * 实际上传文件
	 */
	private File upload;

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}
	
	/**
	 * 数据页信息对象类
	 */
	private DataPageInfo dataPageInfo = new DataPageInfo();
	
	public DataPageInfo getDataPageInfo() {
		return dataPageInfo;
	}

	public void setDataPageInfo(DataPageInfo dataPageInfo) {
		this.dataPageInfo = dataPageInfo;
	}

	/**
	 * 库房温湿度登记信息
	 */
	private TempratureHumidityInfo tempratureHumidityInfo;
	
	public TempratureHumidityInfo getTempratureHumidityInfo() {
		return tempratureHumidityInfo;
	}

	public void setTempratureHumidityInfo(TempratureHumidityInfo tempratureHumidityInfo) {
		this.tempratureHumidityInfo = tempratureHumidityInfo;
	}

	/**
	 * 查询所有的库房信息
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	public String findStorerooms() throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		String forWard = "";
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		String pageResult = "";
		List<StoreroomStructure> storeRooms = new ArrayList<StoreroomStructure>();

		try {
			//查找所有的库房信息
			if (pFlag) {
				pErrPos = 1;
				if(storeroomManageService.findStorerooms(storeRooms, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"查询库房信息失败！");
				}
			}
			
			//将取到的结果添加到request，返回到页面
			if(true){//此处必须执行
				pErrPos = 2;
				request.setAttribute("pageResult", pageResult);
				request.setAttribute("storeRooms", storeRooms);
				forWard ="toStoreroomStructure";
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
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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
				forWard = "error";
				request.setAttribute("pErrInfo", pErrInfo);
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return forWard;
	}
	
	/**
	 * 登记库房环境信息
	 * @return
	 * @throws Exception
	 */
	public String registerTempratureHumidityInfo() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		String forWard = "";
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		List<StoreroomStructure> storeRooms = new ArrayList<StoreroomStructure>();

		try {
			//开始处理 1...
			pErrPos = 1;
			if (tempratureHumidityInfo==null) {				
				pFlag = false;
				pErrInfo.getContent().append("库房环境信息非法为空！");
			}
			
			//查找所有的库房信息
			if (pFlag) {
				pErrPos = 1;
				if(storeroomManageService.findStorerooms(storeRooms, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"查询库房信息失败！");
				}
			}

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if(tempratureHumidityInfoManageService.addTempratureHumidityInfo(tempratureHumidityInfo, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"添加一个新的库房温度湿度信息失败：");
				}
			}
			
			if (pFlag) {
				pErrPos = 3;
				request.setAttribute("successFlg",true);
				request.setAttribute("storeRooms",storeRooms);
				forWard = "toAddKFHJ";
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
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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
				forWard = "error";
				request.setAttribute("pErrInfo", pErrInfo);
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return forWard;	
	}
	
	/**
	 * 通过库房环境信息编号获取库房信息
	 * @return
	 * @throws Exception
	 */
	public String getTempratureHumidityInfoByID() throws Exception{
		System.out.println("getTempratureHumidityInfoByID");
		boolean pFlag = true;
		int pErrPos = 0;
		String forWard = "";
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		List<StoreroomStructure> storeRooms = new ArrayList<StoreroomStructure>();

		try {
			//开始处理 1...
			pErrPos = 1;
			if (tempratureHumidityInfo==null) {				
				tempratureHumidityInfo = new TempratureHumidityInfo();
			}
			System.out.println("tempratureHumidityInfo.getID():"+tempratureHumidityInfo.getID());
			
			if(request.getParameter("tempratureId")!=null && !"".equals(request.getParameter("tempratureId"))){
				tempratureHumidityInfo.setID(Integer.parseInt(request.getParameter("tempratureId")));
			}
			
			//查找所有的库房信息
			if (pFlag) {
				pErrPos = 1;
				if(storeroomManageService.findStorerooms(storeRooms, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"查询库房信息失败！");
				}
			}

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if(tempratureHumidityInfoManageService.findTempratureHumidityInfoByID(tempratureHumidityInfo, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"根据唯一标识查找库房温度湿度信息信息失败：");
				}
			}
			
			//将数据返回到页面
			if (pFlag) {
				pErrPos = 3;
				//request.setAttribute("successFlg",true);
				request.setAttribute("storeRooms",storeRooms);
				forWard = "toAlterKFHJ";
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
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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
				forWard = "error";
				request.setAttribute("pErrInfo", pErrInfo);
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}
		return forWard;	
	}
	
	
	/**
	 * 更新库房环境信息
	 * @return
	 * @throws Exception
	 */
	public String updateTempratureHumidityInfo() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		String forWard = "";
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		List<StoreroomStructure> storeRooms = new ArrayList<StoreroomStructure>();

		try {
			//开始处理 1...
			pErrPos = 1;
			if (tempratureHumidityInfo==null) {				
				pFlag = false;
				pErrInfo.getContent().append("库房环境信息非法为空！");
			}
			
			//查找所有的库房信息
			if (pFlag) {
				pErrPos = 1;
				if(storeroomManageService.findStorerooms(storeRooms, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"查询库房信息失败！");
				}
			}

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if(tempratureHumidityInfoManageService.updateTempratureHumidityInfo(tempratureHumidityInfo, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"更新库房温度湿度信息失败：");
				}
			}
			
			if (pFlag) {
				pErrPos = 3;
				request.setAttribute("successFlg",true);
				request.setAttribute("storeRooms",storeRooms);
				forWard = "toAlterKFHJ";
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
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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
				forWard = "error";
				request.setAttribute("pErrInfo", pErrInfo);
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return forWard;	
	}
	
	/**
	 * 批量删除库房盘点信息
	 * @return
	 * @throws Exception
	 */
	public String deleteStocktakings() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		String forWard = "";
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		String whereSQL = "";
		List<StocktakingInfo> stocktakingInfos = new ArrayList<StocktakingInfo>();
		String[] stocktakings = {};	
		try {
			//开始处理 1...
			pErrPos = 1;
			

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if(request.getParameterValues("stocktakings")!=null ){
					stocktakings = request.getParameterValues("stocktakings");
				}
			}
			
			//调用业务逻辑，删除库房盘点工作信息
			if(pFlag){
				pErrPos = 3;
				StocktakingInfo stocktakingInfo =null;
				if (stocktakings.length>0) {
					for (String str_stocktakings : stocktakings) {
						stocktakingInfo = new StocktakingInfo();
						stocktakingInfo.setID(Integer.parseInt(str_stocktakings));
						System.out.println(stocktakingInfo.getID());
						if(stocktakingInfoManageService.deleteStocktakingInfo(stocktakingInfo, pErrInfo)==false){
							pFlag = false;
							pErrInfo.getContent().append("删除指定的库房盘点工作信息失败！");
						}else{
							System.out.println("编号为"+stocktakingInfo.getID()+"的库房盘点工作信息成功！");	
						}
					}
				}
			}
			
			//查询所有库房盘点信息(分页)
			if (pFlag) {
				pErrPos = 2;
				dataPageInfo.setPageSize(15);
				if (stocktakingInfoManageService.findStocktakingInfos(stocktakingInfos, dataPageInfo, pErrInfo)==false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"A查找所有的库房盘点工作信息信息失败:");
				}
			}
			
			//将结果返回到页面
			if (pFlag) {
				pErrPos = 4;
				request.setAttribute("stocktakingInfos", stocktakingInfos);				
				forWard = "toStocktakingInfoManage";
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
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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
				forWard = "error";
				request.setAttribute("pErrInfo", pErrInfo);
				tempBuilder = null;
				System.out.println("errorInfo:"+pErrInfo.toString());
			}

			//销毁局部变量
			throwable = null;
		}

		return forWard;
	}
	
	/**
	 * 批量删除库房环境登记信息
	 * @return
	 * @throws Exception
	 */
	public String deleteTempratureHumidityInfos() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		String forWard = "";
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		String whereSQL = "";
		List<TempratureHumidityInfo> tempratureHumidityInfos  = new ArrayList<TempratureHumidityInfo>();
		String[] tempratures = {};	
		try {
			//开始处理 1...
			pErrPos = 1;
			

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if(request.getParameterValues("tempratures")!=null ){
					tempratures = request.getParameterValues("tempratures");
				}
			}
			
			//调用业务逻辑，删除库房环境信息
			if(pFlag){
				pErrPos = 3;
				TempratureHumidityInfo temprature =null;
				if (tempratures.length>0) {
					for (String str_temprature : tempratures) {
						temprature = new TempratureHumidityInfo();
						temprature.setID(Integer.parseInt(str_temprature));
						System.out.println(temprature.getID());
						if(tempratureHumidityInfoManageService.deleteTempratureHumidityInfo(temprature, pErrInfo)==false){
							pFlag = false;
							pErrInfo.getContent().append("删除指定的库房温度湿度信息失败！");
						}else{
							System.out.println("编号为"+temprature.getID()+"的环境信息成功！");	
						}
					}
				}
			}
			
			//调用业务逻辑，查询出所有的档案信息(分页形式)
			if (pFlag) {
				pErrPos = 3;
				dataPageInfo.setPageSize(15);
				if(tempratureHumidityInfoManageService.findTempratureHumidityInfosByCondition(whereSQL, tempratureHumidityInfos, dataPageInfo, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"根据条件查找库房温度湿度信息信息集合失败：");
				}
			}
			
			//将结果返回到页面
			if (pFlag) {
				pErrPos = 4;
				request.setAttribute("tempratureHumidityInfos", tempratureHumidityInfos);				
				forWard = "toEnvironment";
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
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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
				forWard = "error";
				request.setAttribute("pErrInfo", pErrInfo);
				tempBuilder = null;
				System.out.println("errorInfo:"+pErrInfo.toString());
			}

			//销毁局部变量
			throwable = null;
		}

		return forWard;
	}
	
	/**
	 * 上传库房盘点工作的文件
	 * @return
	 * @throws Exception
	 */
	public String uploadStocktakingData() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		String forWard = "";
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		String archivesBoxBarcode = "";//盒条形码
		String storeAddressBarcode = "";//设备条形码
		String archivesBarcode = "";//档案条形码
		List<String> barcodes = new ArrayList<String>();//存储条形码
		int boxNum = 0;
		int archivesNum = 0;
		boolean archivesBarcodeFlag = false;
		int stocktakingId = 0;
		
		try {
			//开始处理 1...
			if (pFlag) {
				pErrPos = 1;
				if (this.getUpload() == null) {
					pFlag = false;
					pErrInfo.getContent().append("请选择要上传的文件");
				}
			}		
			
			//获取盘点编号
			if (request.getParameter("stocktakingId")!=null && !"".equals(request.getParameter("stocktakingId"))) {
				stocktakingId = Integer.parseInt(request.getParameter("stocktakingId"));
			} else{
				pFlag = false;
				pErrInfo.getContent().append("获取盘点工作编号失败！");
			}
			
			//分行读取条形码值，循环执行业务上架逻辑方法
			if (pFlag) {
				pErrPos = 2;
				FileInputStream fis = new FileInputStream(upload);
				BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
				String line  ="";				
				
				//将数据转存到数组中
				while((line=reader.readLine())!=null){
					if(!"".equals(line)){
						barcodes.add(line);
					}
				}
			}
			
			//判断上传的文件类型
			if (pFlag) {
				pErrPos = 4;
				//判断上传文件的类型，盒位置/档案位置
				if (barcodes.get(0).length()==5) {//盒位置
					archivesBarcodeFlag = false;
				}else if (barcodes.get(0).length()==7) {//档案位置
					archivesBarcodeFlag = true;
				}
			
				//调用业务逻辑
				if (archivesBarcodeFlag) {//上传类型为档案位置
					StocktakingArchivesDetail stocktakingArchivesDetail = null;
					for(int i= 0;i<barcodes.size();i++){
						if (barcodes.get(i).length()==7) {
							archivesBoxBarcode = barcodes.get(i);
							System.out.println("设备形码：" + barcodes.get(i));
						}
						if (barcodes.get(i).length()==9) {							
							archivesBarcode = barcodes.get(i);
							System.out.println("盒条形码：" + barcodes.get(i));
							archivesNum++;
							
							//检查盒条形码和库房设备形码都不为空
							if(!"".equals(archivesBoxBarcode) && !"".equals(archivesBarcode) ){
								stocktakingArchivesDetail = new StocktakingArchivesDetail();
								stocktakingArchivesDetail.setArchivesBarcode(archivesBarcode);
								stocktakingArchivesDetail.setArchivesBoxBarcode(archivesBoxBarcode);
								stocktakingArchivesDetail.setStocktakingID(stocktakingId);
								//登记上架位置信息
								if(stocktakingManageService.addStocktakingArchivesDetail(stocktakingArchivesDetail, pErrInfo)==false){
									pFlag = false;
									pErrInfo.getContent().insert(0,"添加库房盘点的档案盒与档案卷详细失败：");
								}
							}
						}
					}
				}else{//上传类型为盒位置
					StocktakingAddressBoxDetail stocktakingAddressBoxDetail  = null;
					for(int i= 0;i<barcodes.size();i++){
						if (barcodes.get(i).length()==5) {//设备条形码
							storeAddressBarcode = barcodes.get(i);
							System.out.println("设备形码：" + barcodes.get(i));
						}else if (barcodes.get(i).length()==7) {//盒条形码
							archivesBoxBarcode = barcodes.get(i);
							System.out.println("盒条形码：" + barcodes.get(i));
							boxNum++;
							
							//检查盒条形码和库房设备形码都不为空
							if(!"".equals(archivesBoxBarcode) && !"".equals(storeAddressBarcode) ){
								stocktakingAddressBoxDetail = new StocktakingAddressBoxDetail();
								stocktakingAddressBoxDetail.setArchivesBoxBarcode(archivesBoxBarcode);
								stocktakingAddressBoxDetail.setStoreAddressBarcode(storeAddressBarcode);
								stocktakingAddressBoxDetail.setStocktakingID(stocktakingId);
								if(stocktakingManageService.addStocktakingAddressBoxDetail(stocktakingAddressBoxDetail, pErrInfo)==false){
									pFlag = false;
									pErrInfo.getContent().insert(0,"添加库房盘点的设备位置与档案盒详细失败：");
								}
							}
						}
					}
				}
			}
			
			//获取盘点工作信息
			StocktakingInfo stocktakingInfo = new StocktakingInfo();
			stocktakingInfo.setID(stocktakingId);
			if (pFlag) {
				pErrPos = 2;
				if(stocktakingInfoManageService.findStocktakingInfoByID(stocktakingInfo, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"根据唯一标识查找库房盘点工作信息信息");
				}
			}
			
			//将结果返回到页面
			if (pFlag) {
				pErrPos = 3;
				request.setAttribute("uploadFlag",true);
				request.setAttribute("stocktakingInfo",stocktakingInfo);
				request.setAttribute("stocktakedFlag",stocktakingInfo.getStocktakedFlag());
				request.setAttribute("stocktakingId",request.getParameter("stocktakingId"));
				request.setAttribute("boxNum",boxNum);
				request.setAttribute("fileSize",upload.length());
				forWard = "toStocktaking";
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
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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
				forWard = "error";
				request.setAttribute("pErrInfo", pErrInfo);
				tempBuilder = null;
				System.out.println(pErrInfo.toString());
			}
			//销毁局部变量
			throwable = null;
		}
		return forWard;
	}
	
	/**
	 * DWR:执行盘点操作
	 * @param stocktakingId 盘点工作编号
	 * @param session 会话
	 * @return
	 * @throws Exception
	 */
	public String executeStocktaking(int stocktakingId,HttpSession session) throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		String result = "";
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			System.out.println("stocktakingId:"+stocktakingId);

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (stocktakingManageService.executeStocktaking(stocktakingId, pErrInfo)==false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"执行库房盘点工作失败：");
				}else{
					System.out.println("执行库房盘点工作成功!");
					StocktakingInfo stocktakingInfo = new StocktakingInfo();
					stocktakingInfo.setID(stocktakingId);
					if(stocktakingInfoManageService.updateStocktakingInfo(stocktakingInfo, pErrInfo)==false){
						pFlag = false;
						pErrInfo.getContent().insert(0,"修改指定的库房盘点工作的盘点状态失败：");
					}
					
					result = "盘点成功!";
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
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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
			}

			//销毁局部变量
			throwable = null;
		}
		return result;
	}
	
	/**
	 * 处理打开库房盘点页面请求，
	 * @return
	 * @throws Exception
	 */
	public String stocktakingBefore() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		String forWard = "";
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		StocktakingInfo stocktakingInfo = new StocktakingInfo();
		
		try {
			pErrPos = 1;
			if (request.getParameter("stocktakingId")!=null && !"".equals(request.getParameter("stocktakingId")) ) {
				stocktakingInfo.setID(Integer.parseInt(request.getParameter("stocktakingId")));
			}else{
				pFlag = false;
				pErrInfo.getContent().append("获取库房盘点工作编号失败！");
			}
			
			if (pFlag) {
				pErrPos = 2;
				if(stocktakingInfoManageService.findStocktakingInfoByID(stocktakingInfo, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"根据唯一标识查找库房盘点工作信息信息");
				}
			}

			//开始处理2...
			if (pFlag) {
				pErrPos = 3;
				request.setAttribute("stocktakingInfo",stocktakingInfo);
				request.setAttribute("stocktakedFlag",stocktakingInfo.getStocktakedFlag());
				request.setAttribute("stocktakingId",request.getParameter("stocktakingId"));
				forWard = "toStocktaking";
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
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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
				forWard = "error";
				request.setAttribute("pErrInfo", pErrInfo);
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return forWard;
	}
	
	/**
	 * 处理打开添加库房盘点工作页面事件
	 * @return
	 * @throws Exception
	 */
	public String addStocktakingBefore() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		String forWard = "";
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		List<StoreroomStructure> storeRooms = new ArrayList<StoreroomStructure>();
		
		try {
			//开始处理 1...
			pErrPos = 1;
			

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (storeroomManageService.findStorerooms(storeRooms, pErrInfo)==false) {
					pFlag =false;
					pErrInfo.getContent().insert(0,"查找所有的库房信息");
				}
			}
			
			//将数据返回到页面
			if (pFlag) {
				pErrPos = 3;
				request.setAttribute("storeRooms", storeRooms);
				forWard = "toAddStocktaking";
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
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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
				forWard = "error";
				request.setAttribute("pErrInfo", pErrInfo);
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}
		return forWard;
	} 
	
	
	/**
	 * 添加库房盘点工作信息
	 * @return
	 * @throws Exception
	 */
	public String addStocktaking() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		String forWard = "";
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		StocktakingInfo stocktakingInfo = new StocktakingInfo();
		List<StoreroomStructure> storeRooms = new ArrayList<StoreroomStructure>();
		
		try {
			//开始处理 1...
			pErrPos = 1;
			if (request.getParameter("storeroomID")!=null && !"".equals(request.getParameter("storeroomID"))) {
				stocktakingInfo.setStoreroomID(Integer.parseInt(request.getParameter("storeroomID")));
				stocktakingInfo.setStocktakingDate(new Date());
				stocktakingInfo.setStocktakedFlag(false);
				System.out.println("success!");
			}

			//调用业务逻辑，添加新的库房盘点信息
			if (pFlag) {
				pErrPos = 2;
				if (stocktakingInfoManageService.addStocktakingInfo(stocktakingInfo, pErrInfo)==false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"添加一个新的库房盘点工作信息失败：");
				}
			}

			//获取所有库房信息
			if (pFlag) {
				pErrPos = 2;
				if (storeroomManageService.findStorerooms(storeRooms, pErrInfo)==false) {
					pFlag =false;
					pErrInfo.getContent().insert(0,"查找所有的库房信息");
				}
			}
			
			//将数据返回到页面
			if (pFlag) {
				pErrPos = 3;
				request.setAttribute("successFlg", true);
				request.setAttribute("storeRooms", storeRooms);
				request.setAttribute("storeroomID", request.getParameter("storeroomID"));
				forWard = "toAddStocktaking";
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
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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
				forWard = "error";
				request.setAttribute("pErrInfo", pErrInfo);
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}
		return forWard;
	} 
	
	
	
	/**
	 * 获取所有库房盘点工作信息
	 * @return
	 * @throws Exception
	 */
	public String findStocktakingInfos() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		String forWard = "";
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		List<StocktakingInfo> stocktakingInfos = new ArrayList<StocktakingInfo>();

		try {
			//开始处理 1...
			pErrPos = 1;
			

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				dataPageInfo.setPageSize(15);
				if (stocktakingInfoManageService.findStocktakingInfos(stocktakingInfos, dataPageInfo, pErrInfo)==false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"A查找所有的库房盘点工作信息信息失败:");
				}
			}
			
			//将数据返回到页面
			if (pFlag) {
				pErrPos = 3;
				request.setAttribute("stocktakingInfos", stocktakingInfos);
				forWard = "toStocktakingInfoManage";
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
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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
				forWard = "error";
				request.setAttribute("pErrInfo", pErrInfo);
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}
		return forWard;
	}
	
	
	
	/**
	 * 根据时间范围查询库房温度、湿度信息
	 * @return
	 * @throws Exception
	 */
	public String findTempratureHumidityInfosByDate() throws Exception{
		System.out.println("findTempratureHumidityInfosByDate --> successful!!!");
		boolean pFlag = true;
		int pErrPos = 0;
		String forWard = "";
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		List<TempratureHumidityInfo> tempratureHumidityInfos = new ArrayList<TempratureHumidityInfo>();
		String whereSQL = "";

		try {
			//开始处理 1...
			pErrPos = 1;
			if(request.getParameter("beginDate")!=null && !"".equals(request.getParameter("beginDate").trim())){
				whereSQL += (" AND MeasureDate >= '"+request.getParameter("beginDate").trim()+"'");
			}
			
			if(request.getParameter("endDate")!=null && !"".equals(request.getParameter("endDate").trim())){
				whereSQL += (" AND MeasureDate <= '"+request.getParameter("endDate").trim()+"'");
			}
			
			
			System.out.println("whereSQL:"+whereSQL);
			
			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				dataPageInfo.setPageSize(15);
				if(tempratureHumidityInfoManageService.findTempratureHumidityInfosByCondition(whereSQL, tempratureHumidityInfos, dataPageInfo, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"根据条件查找库房温度湿度信息信息集合失败：");
				}
			}
			
			//将结果返回到页面
			if (pFlag) {
				pErrPos = 3;
				request.setAttribute("tempratureHumidityInfos", tempratureHumidityInfos);
				request.setAttribute("beginDate", request.getParameter("beginDate"));
				request.setAttribute("endDate", request.getParameter("endDate"));
				forWard = "toEnvironment";
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
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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
				forWard = "error";
				request.setAttribute("pErrInfo", pErrInfo);
				tempBuilder = null;
				System.out.println(pErrInfo.toString());
			}

			//销毁局部变量
			throwable = null;
			
		}

		return forWard;
	}
	
	/**
	 * 打开环境登记页面<br/>获取该页面所需要的数据：库房名称
	 * @return
	 * @throws Exception
	 */
	public String getAddHJDefaultInputItem() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		String forWard = "";
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		List<StoreroomStructure> storeRooms = new ArrayList<StoreroomStructure>();

		try {
			//查找所有的库房信息
			if (pFlag) {
				pErrPos = 1;
				if(storeroomManageService.findStorerooms(storeRooms, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"查询库房信息失败！");
				}
			}

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				request.setAttribute("storeRooms", storeRooms);
				forWard = "toAddKFHJ";//跳转到新增库房环境登记页面

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
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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
				forWard = "error";
				request.setAttribute("pErrInfo", pErrInfo);
				tempBuilder = null;
				System.out.println(pErrInfo.toString());
			}

			//销毁局部变量
			throwable = null;
		}

		return forWard;
		
	}

	/**
	 * 根据库房设施编号查询其下级设备的集合
	 * @return
	 * @throws Exception
	 */
	public String showSubStructureByID() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		String forWard = "";
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		List<StoreroomStructure> storeroomStructures = new ArrayList<StoreroomStructure>();
		int storeroomStructureID = 0;

		try {
			//开始处理 1...
			pErrPos = 1;
			
			if(request.getParameter("storeroomStructureID")!= null && !"".equals(request.getParameter("storeroomStructureID").trim())){
				storeroomStructureID = Integer.parseInt(request.getParameter("storeroomStructureID").trim());
			}else{
				pFlag = false;
				pErrInfo.getContent().append("获取库房结构编号失败！");
			}

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if(storeroomManageService.findStoreroomStructureChildrenByID(storeroomStructureID, storeroomStructures, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"根据库房结构编号查找其下级库房结构失败：");
				}
			}
			
			if (pFlag) {
				pErrPos = 3;
				request.setAttribute("storeroomStructures", storeroomStructures);
				forWard = "toVeiwSubStructure";
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
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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
				forWard = "error";
				request.setAttribute("pErrInfo", pErrInfo);
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}
		return forWard;
	}

	
	/**
	 * 登记实物档案在库房中的上架位置<br>
	 * 设备条形码5位，盒条形码7位
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	public String registerStoreAddress() {
		boolean pFlag = true;
		int pErrPos = 0;
		String forWard = "";
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		String archivesBoxBarcode = "";//盒条形码
		String storeAddressBarcode = "";//设备条形码
		List<String> storeAddressBarcodes = new ArrayList<String>();
		int boxNum = 0;
		try {
			//开始处理 1...
			if (pFlag) {
				pErrPos = 1;
				if (this.getUpload() == null) {
					pFlag = false;
					pErrInfo.getContent().append("请选择要上传的文件");
				}
			}		
			
			//分行读取条形码值，循环执行业务上架逻辑方法
			if (pFlag) {
				pErrPos = 2;
				FileInputStream fis = new FileInputStream(upload);
				BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
				String line  ="";
				while((line=reader.readLine())!=null){
					if (line.length()==5) {
						storeAddressBarcode = line;
						storeAddressBarcodes.add(storeAddressBarcode);
						System.out.println("设备条形码："+line);
					}
					if (line.length()==7) {
						archivesBoxBarcode = line;
						System.out.println("盒条形码："+line);
						boxNum++;
						//检查盒条形码和库房设备形码都不为空
						if(!"".equals(archivesBoxBarcode) && !"".equals(storeAddressBarcode) ){
							//登记上架位置信息
							if(storeroomManageService.registerStoreAddress(archivesBoxBarcode, storeAddressBarcode, pErrInfo)==false){
								pFlag = false;
								pErrInfo.getContent().insert(0,"登记实物档案在库房中的上架位置失败：");
							}
						}
					}
				}
			}
			
			//更新库房设备容量
			if (pFlag) {
				pErrPos = 3;
				for (String addressBarcode : storeAddressBarcodes) {
					if(storeroomManageService.updateStoreroomStructureUsedSizeByBarcode(addressBarcode, pErrInfo)==false){
						pFlag = false;
						pErrInfo.getContent().insert(0,"更新库房设备的已用空间失败：");
					}
				}
			}
			
			
			//将结果返回到页面
			if (pFlag) {
				pErrPos = 3;
				request.setAttribute("uploadFlag",true);
				request.setAttribute("boxNum",boxNum);
				System.out.println(boxNum);
				request.setAttribute("fileSize",upload.length());
				forWard = "toLocationRegister";
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
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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
				forWard = "error";
				request.setAttribute("pErrInfo", pErrInfo);
				System.out.println(pErrInfo.toString());
				tempBuilder = null;
			}
			//销毁局部变量
			throwable = null;
		}
		return forWard;
	}	
	
	
	
	/**
	 * 位置与状态查询<br>
	 * @return 
	 */
	public String findLocAndStatus()  throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		String forWard = "";
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		List<StoreroomArchivesInfo> storeroomArchivesInfos = new ArrayList<StoreroomArchivesInfo>();
		String whereSQL = "";
		
		try {
			//获取参数
			pErrPos = 1;
			if(!"".equals(request.getParameter("archivesBarcode").trim())){
				whereSQL +=( " AND ArchivesBarcode ='" + request.getParameter("archivesBarcode").trim())+"'";
				System.out.println("archivesBarcode---");
			}
			if(!"".equals(request.getParameter("archivesID").trim())){
				whereSQL += (" AND ArchivesID LIKE '" +request.getParameter("archivesID").trim())+"%'";
				System.out.println("archivesID---");
			}
			if(!"".equals(request.getParameter("title").trim())){
				whereSQL += (" AND Title LIKE '%" +request.getParameter("title").trim())+"%'";
				System.out.println("title---");
			}
		
			System.out.println("whereSQL:"+whereSQL);
			
			//调用业务逻辑：查询符合条件的库房档案信息
			if (pFlag) {	
				pErrPos = 2;
				if(storeroomManageService.findStoreroomArchivesInfosByCondition(whereSQL, storeroomArchivesInfos, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().append("查询符合条件的库房档案信息失败！");
				}
			}
			
			//将数据返回到页面
			if (pFlag) {
				pErrPos = 3;
				request.setAttribute("recordSize",storeroomArchivesInfos.size());
				request.setAttribute("storeroomArchivesInfos",storeroomArchivesInfos);
				request.setAttribute("archivesBarcode", request.getParameter("archivesBarcode"));
				request.setAttribute("archivesID", request.getParameter("archivesID"));
				request.setAttribute("title", request.getParameter("title"));
				
				forWard = "toQueryLocAndStatus";
				System.out.println("storeroomManageService.findStoreroomArchivesInfosByCondition ---success!");
			
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
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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
				forWard = "error";
				request.setAttribute("pErrInfo", pErrInfo);
				tempBuilder = null;
System.out.println(pErrInfo.toString());
			}

			//销毁局部变量
			throwable = null;
		}

		return forWard;
	}
	
	
	
	/**
	 * 打印条形码
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	public String printBarcode(int barcodeType,int barcodeCount,HttpSession session,ServletContext application) throws Exception{
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;	
			if(barcodeType != 2 && barcodeType != 3){
				pFlag = false;
				pErrInfo.getContent().append("条形码类型选择错误。");
				result = "条形码类型选择错误";
			}
			
			//验证打印数量
			if(barcodeCount <= 0){
				pFlag = false;
				pErrInfo.getContent().append("打印数量必须大于零。");
				result = "打印数量必须大于零";
			}
			
			//生成条码并传回条码的起止范围
			if (pFlag) {
				CurrentBarcode currentBarcode = new CurrentBarcode();
				currentBarcode.setBarcodeType(EnumBarcodeType.getEnumElement(barcodeType));
				if(currentBarcodeManageService.printBarcode(barcodeCount, currentBarcode, pErrInfo)){
					int begin = currentBarcode.getCurrentBarcodeNo() - barcodeCount + 1;
					int end = currentBarcode.getCurrentBarcodeNo();
					result = EnumBarcodeType.getEnumElement(barcodeType)+":起止范围是：" + begin +"--" + end;
				}else{
					pFlag = false;
					pErrInfo.getContent().insert(0,"打印" + currentBarcode.getBarcodeType() + "失败。");
					result = pErrInfo.toShortString();
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
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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
		return result;
	}
	
	
	
//////////////8.24 10.49ADD//////////////////////////
	public String findStocktakingReports() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		String forWard = "";
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		StocktakingInfo stocktakingInfo = new StocktakingInfo();
		try {
			//开始处理 1...
			pErrPos = 1;
			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if(request.getParameter("stocktakingId")!=null && !"".equals(request.getParameter("stocktakingId"))){
					stocktakingInfo.setID(Integer.parseInt(request.getParameter("stocktakingId")));
				}else{
					pFlag = false;
					pErrInfo.getContent().append("获取盘点工作编号失败！");
				}
			}
		
			if(stocktakingInfoManageService.findStocktakingInfoByID(stocktakingInfo, pErrInfo)==false){
				pFlag = false;
				pErrInfo.getContent().insert(0,"根据唯一标识查找库房盘点工作信息信息失败：");
			}
			
			//将结果返回到页面
			if (pFlag) {
				pErrPos = 4;
				request.setAttribute("stocktakingInfo",stocktakingInfo);
				request.setAttribute("stocktakedFlag",stocktakingInfo.getStocktakedFlag());
System.out.println("stocktakedFlag:"+stocktakingInfo.getStocktakedFlag());
				request.setAttribute("stocktakingId",request.getParameter("stocktakingId"));
				forWard = "toViewStocktakingReports";
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
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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
				forWard = "error";
				request.setAttribute("pErrInfo", pErrInfo);
				tempBuilder = null;
				System.out.println("errorInfo:"+pErrInfo.toString());
			}

			//销毁局部变量
			throwable = null;
		}

		return forWard;
	}
	
	//查询所有库房档案数量情况
	public String findStockReportArchivesCount() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		String forWard = "";
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		int stocktakingId = 0;
		List<StockReportArchivesCount> stockReportArchivesCounts = new ArrayList<StockReportArchivesCount>();
		try {
			//开始处理 1...
			pErrPos = 1;
			
			if (pFlag) {
				pErrPos = 2;
				if(request.getParameter("stocktakingId")!=null && !"".equals(request.getParameter("stocktakingId"))){
					stocktakingId = Integer.parseInt(request.getParameter("stocktakingId"));
				}else{
					pFlag = false;
					pErrInfo.getContent().append("获取盘点工作编号失败！");
				}
			}
			
			if (pFlag) {
				pErrPos = 3;
				if (stocktakingManageService.findStockReportArchivesCount(stocktakingId, stockReportArchivesCounts, pErrInfo)==false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"查询所有库房档案数量情况");
				}
				
			}

			//开始处理2...
			if (pFlag) {
				pErrPos = 4;
				request.setAttribute("stockReportArchivesCounts", stockReportArchivesCounts);
				forWard = "toStockReportArchivesCount";
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
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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
				forWard = "error";
				request.setAttribute("pErrInfo", pErrInfo);
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return forWard;
	}


	//查询所有系统中不在架档案情况
	public String findStockReportSystemNotExist() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		String forWard = "";
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		int stocktakingId = 0;
		List<StockReportSystemNotExist> stockReportSystemNotExists = new ArrayList<StockReportSystemNotExist>();
		try {
			//开始处理 1...
			pErrPos = 1;
			
			if (pFlag) {
				pErrPos = 2;
				if(request.getParameter("stocktakingId")!=null && !"".equals(request.getParameter("stocktakingId"))){
					stocktakingId = Integer.parseInt(request.getParameter("stocktakingId"));
				}else{
					pFlag = false;
					pErrInfo.getContent().append("获取盘点工作编号失败！");
				}
			}

			//开始处理2...
			if (pFlag) {
				pErrPos = 3;
				if (stocktakingManageService.findStockReportSystemNotExist(stocktakingId, stockReportSystemNotExists, pErrInfo)==false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"查询所有系统中不在架档案情况失败：");
				}
			}
			
			if (pFlag) {
				pErrPos = 4;
				request.setAttribute("stockReportSystemNotExists", stockReportSystemNotExists);
				forWard = "toStockReportSystemNotExist";
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
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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
				forWard = "error";
				request.setAttribute("pErrInfo", pErrInfo);
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return forWard;
	}
	

	//查询所有实物档案不在架档案情况
	public String findStockReportPaperNotExist() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		String forWard = "";
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		int stocktakingId = 0;
		List<StockReportPaperNotExist> stockReportPaperNotExists = new ArrayList<StockReportPaperNotExist>();

		try {
			//开始处理 1...
			pErrPos = 1;
			
			if (pFlag) {
				pErrPos = 2;
				if(request.getParameter("stocktakingId")!=null && !"".equals(request.getParameter("stocktakingId"))){
					stocktakingId = Integer.parseInt(request.getParameter("stocktakingId"));
				}else{
					pFlag = false;
					pErrInfo.getContent().append("获取盘点工作编号失败！");
				}
			}

			//开始处理2...
			if (pFlag) {
				pErrPos = 3;
				if (stocktakingManageService.findStockReportPaperNotExist(stocktakingId, stockReportPaperNotExists, pErrInfo)==false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"查询所有实物档案不在架档案情况");
				}
			}
			
			if (pFlag) {
				pErrPos = 4;
				request.setAttribute("stockReportPaperNotExists",stockReportPaperNotExists);
				forWard = "toStockReportPaperNotExist";
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
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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
				forWard = "error";
				request.setAttribute("pErrInfo", pErrInfo);
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return forWard;
	}
	
	//查询所有位置不匹配情况
	public String findStockReportAddressNotMatch() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		String forWard = "";
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		int stocktakingId = 0;
		List<StockReportAddressNotMatch> stockReportAddressNotMatchs = new ArrayList<StockReportAddressNotMatch>();

		try {
			//开始处理 1...
			pErrPos = 1;
			
			if (pFlag) {
				pErrPos = 2;
				if(request.getParameter("stocktakingId")!=null && !"".equals(request.getParameter("stocktakingId"))){
					stocktakingId = Integer.parseInt(request.getParameter("stocktakingId"));
				}else{
					pFlag = false;
					pErrInfo.getContent().append("获取盘点工作编号失败！");
				}
			}

			//开始处理2...
			if (pFlag) {
				pErrPos = 3;
				if (stocktakingManageService.findStockReportAddressNotMatch(stocktakingId, stockReportAddressNotMatchs, pErrInfo)==false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"查询所有位置不匹配情况失败：");
				}
			}
			
			if (pFlag) {
				pErrPos = 4;
				request.setAttribute("stockReportAddressNotMatchs",stockReportAddressNotMatchs);
				forWard = "toStockReportAddressNotMatch";
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
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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
				forWard = "error";
				request.setAttribute("pErrInfo", pErrInfo);
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return forWard;
	}
	
	//查询所有装盒不匹配档案情况
	public String findStockReportArchivesBoxNotMatch() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		String forWard = "";
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		int stocktakingId = 0;
		List<StockReportArchivesBoxNotMatch> stockReportArchivesBoxNotMatchs = new ArrayList<StockReportArchivesBoxNotMatch>();
		
		try {
			//开始处理 1...
			pErrPos = 1;
			
			if (pFlag) {
				pErrPos = 2;
				if(request.getParameter("stocktakingId")!=null && !"".equals(request.getParameter("stocktakingId"))){
					stocktakingId = Integer.parseInt(request.getParameter("stocktakingId"));
				}else{
					pFlag = false;
					pErrInfo.getContent().append("获取盘点工作编号失败！");
				}
			}

			//开始处理2...
			if (pFlag) {
				pErrPos = 3;
				if (stocktakingManageService.findStockReportArchivesBoxNotMatch(stocktakingId, stockReportArchivesBoxNotMatchs, pErrInfo)==false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"查询所有装盒不匹配档案情况失败：");
				}
			}
			
			if (pFlag) {
				pErrPos = 4;
				request.setAttribute("stockReportArchivesBoxNotMatchs", stockReportArchivesBoxNotMatchs);
				forWard = "toStockReportArchivesBoxNotMatch";
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
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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
				forWard = "error";
				request.setAttribute("pErrInfo", pErrInfo);
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return forWard;
	}
	

}
