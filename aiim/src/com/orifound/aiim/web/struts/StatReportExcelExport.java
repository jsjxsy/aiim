package com.orifound.aiim.web.struts;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.opensymphony.xwork2.ActionSupport;
import com.orifound.aiim.bll.service.IStatReportManageService;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.ReportResultArchivesCollection;
import com.orifound.aiim.entity.ReportResultArchivesDestroy;
import com.orifound.aiim.entity.ReportResultArchivesPublic;
import com.orifound.aiim.entity.ReportResultArchivesSaved;
import com.orifound.aiim.entity.ReportResultArchivesTypeUse;
import com.orifound.aiim.entity.ReportResultArchivesUsePerson;
import com.orifound.aiim.entity.ReportResultArchivesUsePurpose;
import com.orifound.aiim.entity.ReportResultCertificateCharge;
import com.orifound.aiim.entity.ReportResultDepartmentSaved;
import com.orifound.aiim.entity.ReportResultOfficialArchivesInput;
import com.orifound.aiim.entity.ReportResultPersionalArchivesMoveOut;
import com.orifound.aiim.entity.ReportResultStoreroomUse;
import com.orifound.aiim.entity.ReportResultSystemVisit;
import com.orifound.aiim.entity.ReportResultTempratureHumidityForMonth;
import com.orifound.aiim.entity.ReportResultTempratureHumidityForYear;
import com.orifound.aiim.entity.ReportResultWorkProcedure;

public class StatReportExcelExport extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private IStatReportManageService statReportManageService;
	
	public IStatReportManageService getStatReportManageService() {
		return statReportManageService;
	}
	public void setStatReportManageService(IStatReportManageService statReportManageService) {
		this.statReportManageService = statReportManageService;
	}
	
	private InputStream excelStream;//excel输入流
	
	public InputStream getExcelStream() {
		return excelStream;
	}
	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}

	private String fileName;//excel导出文件名
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	private void workbookInputStream(HSSFWorkbook workbook,String fileName) throws Exception{ 
		this.fileName = fileName; //设置fileName 
		ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
		workbook.write(baos); 
		baos.flush(); 
		byte[] aa = baos.toByteArray(); 
		this.excelStream = new ByteArrayInputStream(aa, 0, aa.length); 
		baos.close(); 
		} 
		
	/**
	 * excel导出统计报表结果--案卷档案馆藏情况
	 * @return 
	 * @throws Exception
	 */
	public String exportReportResultArchivesCollectionExcel() throws Exception {
		// TODO Auto-generated method stub
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		List<ReportResultArchivesCollection> pReportResultArchivesCollections = new ArrayList<ReportResultArchivesCollection>(); 
		try {
			// 获取用户ID
			pErrPos = 1;
			if (pFlag) {
				if (statReportManageService.findAllReportResultArchivesCollection(pReportResultArchivesCollections, pErrInfo) == false) {
					result = "error";
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询报表统计结果表－案卷档案馆藏情况失败：");
				}
			}
			
			//生成excel导出
			HSSFWorkbook wb = new HSSFWorkbook();   
		    HSSFSheet sheet = wb.createSheet("sheet1");   
	        int rowNum = 0; // 标题开始行   
	        int colNum = 0; // 标题开始列   
	        HSSFRow row = sheet.createRow(rowNum++); //创建第1行，也就是输出表头 
	        HSSFCell cell = row.createCell(colNum++); // HSSFCell excell的格子单元
			cell.setCellValue("全宗名称");
			cell = row.createCell(colNum++); 
			cell.setCellValue("一级类目名称");
			cell = row.createCell(colNum++); 
			cell.setCellValue("总卷数");
			cell = row.createCell(colNum++); 
			cell.setCellValue("总件数");
			for (ReportResultArchivesCollection reportResultArchivesCollection : pReportResultArchivesCollections) {
				 row = sheet.createRow(rowNum++); 
				 colNum=0; 
			     cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(reportResultArchivesCollection.getArchivesFondsName()));   
                    
                 cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(reportResultArchivesCollection.getArchivesTypeName())); 
                 
                 cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(String.valueOf(reportResultArchivesCollection.getColumeCount())));   
                 
                 cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(String.valueOf(reportResultArchivesCollection.getPieceCount())));   
			}
			this.workbookInputStream(wb,"ReportResultArchivesCollection");
			result = "success";
		} catch (Exception e) {
			result = "error";
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
		}
		return result;
	}
	

	/**
	 * excel导出报表统计结果表－档案分类利用情况
	 * @return 
	 * @throws Exception
	 */
	public String exportReportResultArchivesTypeUseExcel() throws Exception {
		// TODO Auto-generated method stub
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		List<ReportResultArchivesTypeUse>  pReportResultArchivesTypeUses = new ArrayList<ReportResultArchivesTypeUse>(); 
		try {
			// 获取用户ID
			pErrPos = 1;
			if (pFlag) {
				if (statReportManageService.findAllReportResultArchivesTypeUse(pReportResultArchivesTypeUses, pErrInfo) == false) {
					result = "error";
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询报表统计结果表－档案分类利用情况失败：");
				}
			}
			
			HSSFWorkbook wb = new HSSFWorkbook();   
		    HSSFSheet sheet = wb.createSheet("sheet1");   
	        int rowNum = 0; // 标题开始行   
	        int colNum = 0; // 标题开始列   
	        HSSFRow row = sheet.createRow(rowNum++); //创建第1行，也就是输出表头 
	        HSSFCell cell = row.createCell(colNum++); // HSSFCell excell的格子单元
			cell.setCellValue("一级类目名称");
			cell = row.createCell(colNum++); 
			cell.setCellValue("总卷次");
			cell = row.createCell(colNum++); 
			cell.setCellValue("总件次");
			for (ReportResultArchivesTypeUse reportResultArchivesTypeUse : pReportResultArchivesTypeUses) {
				 row = sheet.createRow(rowNum++); 
				 colNum=0; 
			     cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(reportResultArchivesTypeUse.getArchivesTypeName()));   
                    
                 cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(String.valueOf(reportResultArchivesTypeUse.getColumeCount()))); 
                 
                 cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(String.valueOf(reportResultArchivesTypeUse.getPieceCount()))); 
                 
			}
			this.workbookInputStream(wb,"ReportResultArchivesTypeUse");

			result = "success";

		} catch (Exception e) {
			result = "error";
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
		}
		return result;
	}
	
	/**
	 * excel导出报表统计结果表－档案利用人情况
	 * @return 
	 * @throws Exception
	 */
	public String exportReportResultArchivesUsePersonExcel() throws Exception {
		// TODO Auto-generated method stub
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		List<ReportResultArchivesUsePerson>  pReportResultArchivesUsePersons = new ArrayList<ReportResultArchivesUsePerson>(); 
		try {
			// 获取用户ID
			pErrPos = 1;
			if (pFlag) {
				if (statReportManageService.findAllReportResultArchivesUsePerson(pReportResultArchivesUsePersons, pErrInfo) == false) {
					result = "error";
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询报表统计结果表－档案利用人情况失败：");
				}
			}
			
			HSSFWorkbook wb = new HSSFWorkbook();   
		    HSSFSheet sheet = wb.createSheet("sheet1");   
	        int rowNum = 0; // 标题开始行   
	        int colNum = 0; // 标题开始列   
	        HSSFRow row = sheet.createRow(rowNum++); //创建第1行，也就是输出表头 
	        HSSFCell cell = row.createCell(colNum++); // HSSFCell excell的格子单元
			cell.setCellValue("利用人区域信息");
			cell = row.createCell(colNum++); 
			cell.setCellValue("人次总数量");
			for (ReportResultArchivesUsePerson reportResultArchivesUsePerson : pReportResultArchivesUsePersons) {
				 row = sheet.createRow(rowNum++); 
				 colNum=0; 
			     cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(reportResultArchivesUsePerson.getUseArea()));   
                    
                 cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(String.valueOf(reportResultArchivesUsePerson.getPersonTimeCount()))); 
                 
                 
			}
			this.workbookInputStream(wb,"ReportResultArchivesUsePerson");
			
			
			result = "success";

		} catch (Exception e) {
			result = "error";
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
		}
		return result;
	}
	
	/**
	 * excel导出报表统计结果表－档案利用目的情况
	 * @return 
	 * @throws Exception
	 */
	public String exportReportResultArchivesUsePurposeExcel() throws Exception {
		// TODO Auto-generated method stub
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		List<ReportResultArchivesUsePurpose>  pReportResultArchivesUsePurposes = new ArrayList<ReportResultArchivesUsePurpose>(); 
		try {
			// 获取用户ID
			pErrPos = 1;
			if (pFlag) {
				if (statReportManageService.findAllReportResultArchivesUsePurpose(pReportResultArchivesUsePurposes, pErrInfo) == false) {
					result = "error";
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询报表统计结果表－档案利用目的情况失败：");
				}
			}
			
			HSSFWorkbook wb = new HSSFWorkbook();   
		    HSSFSheet sheet = wb.createSheet("sheet1");   
	        int rowNum = 0; // 标题开始行   
	        int colNum = 0; // 标题开始列   
	        HSSFRow row = sheet.createRow(rowNum++); //创建第1行，也就是输出表头 
	        HSSFCell cell = row.createCell(colNum++); // HSSFCell excell的格子单元
			cell.setCellValue("档案利用目的 ");
			cell = row.createCell(colNum++); 
			cell.setCellValue("总卷次");
			cell = row.createCell(colNum++); 
			cell.setCellValue("总件次");
			for (ReportResultArchivesUsePurpose reportResultArchivesUsePurpose : pReportResultArchivesUsePurposes) {
				 row = sheet.createRow(rowNum++); 
				 colNum=0; 
			     cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(reportResultArchivesUsePurpose.getPurposeName()));   
                    
                 cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(String.valueOf(reportResultArchivesUsePurpose.getColumeCount()))); 
                 
                 cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(String.valueOf(reportResultArchivesUsePurpose.getPieceCount()))); 
                 
			}
			this.workbookInputStream(wb,"ReportResultArchivesUsePurpose");
			
			result = "success";

		} catch (Exception e) {
			result = "error";
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
		}
		return result;
	}
	
	/**
	 * excel导出报表统计结果表－案卷档案归档情况
	 * @return 
	 * @throws Exception
	 */
	public String exportReportResultArchivesSavedExcel() throws Exception {
		// TODO Auto-generated method stub
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		List<ReportResultArchivesSaved>  pReportResultArchivesSaveds = new ArrayList<ReportResultArchivesSaved>(); 
		try {
			// 获取用户ID
			pErrPos = 1;
			if (pFlag) {
				if (statReportManageService.findAllReportResultArchivesSaved(pReportResultArchivesSaveds, pErrInfo) == false) {
					result = "error";
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询报表统计结果表－案卷档案归档情况失败：");
				}
			}
			
			HSSFWorkbook wb = new HSSFWorkbook();   
		    HSSFSheet sheet = wb.createSheet("sheet1");   
	        int rowNum = 0; // 标题开始行   
	        int colNum = 0; // 标题开始列   
	        HSSFRow row = sheet.createRow(rowNum++); //创建第1行，也就是输出表头 
	        HSSFCell cell = row.createCell(colNum++); // HSSFCell excell的格子单元
			cell.setCellValue("一级类目名称");
			cell = row.createCell(colNum++); 
			cell.setCellValue("总卷次");
			cell = row.createCell(colNum++); 
			cell.setCellValue("总件次");
			for (ReportResultArchivesSaved reportResultArchivesSaved : pReportResultArchivesSaveds) {
				 row = sheet.createRow(rowNum++); 
				 colNum=0; 
			     cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(reportResultArchivesSaved.getArchivesTypeName()));   
                    
                 cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(String.valueOf(reportResultArchivesSaved.getColumeCount()))); 
                 
                 cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(String.valueOf(reportResultArchivesSaved.getPieceCount()))); 
                 
			}
			this.workbookInputStream(wb,"ReportResultArchivesSaved");
			
			result = "success";

		} catch (Exception e) {
			result = "error";
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
		}
		return result;
	}
	
	
	/**
	 * excel导出报表统计结果表－部门归档情况
	 * @return 
	 * @throws Exception
	 */
	public String exportReportResultDepartmentSavedExcel() throws Exception {
		// TODO Auto-generated method stub
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		List<ReportResultDepartmentSaved>  pReportResultDepartmentSaveds = new ArrayList<ReportResultDepartmentSaved>(); 
		try {
			// 获取用户ID
			pErrPos = 1;
			if (pFlag) {
				if (statReportManageService.findAllReportResultDepartmentSaved(pReportResultDepartmentSaveds, pErrInfo) == false) {
					result = "error";
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询报表统计结果表－部门归档情况失败：");
				}
			}
			
			HSSFWorkbook wb = new HSSFWorkbook();   
		    HSSFSheet sheet = wb.createSheet("sheet1");   
	        int rowNum = 0; // 标题开始行   
	        int colNum = 0; // 标题开始列   
	        HSSFRow row = sheet.createRow(rowNum++); //创建第1行，也就是输出表头 
	        HSSFCell cell = row.createCell(colNum++); // HSSFCell excell的格子单元
	        cell.setCellValue("部门名称");
	        cell = row.createCell(colNum++); 
	        cell.setCellValue("一级类目名称");
			cell = row.createCell(colNum++); 
			cell.setCellValue("总卷次");
			cell = row.createCell(colNum++); 
			cell.setCellValue("总件次");
			for (ReportResultDepartmentSaved reportResultDepartmentSaved : pReportResultDepartmentSaveds) {
				 row = sheet.createRow(rowNum++); 
				 colNum=0; 
				 
				 cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(reportResultDepartmentSaved.getDepartmentName()));   
                 
			     cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(reportResultDepartmentSaved.getArchivesTypeName()));   
                    
                 cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(String.valueOf(reportResultDepartmentSaved.getColumeCount()))); 
                 
                 cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(String.valueOf(reportResultDepartmentSaved.getPieceCount()))); 
                 
			}
			this.workbookInputStream(wb,"ReportResultDepartmentSaved");
			
			
			result = "success";

		} catch (Exception e) {
			result = "error";
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
		}
		return result;
	}
	
	
	/**
	 * excel导出报表统计结果表－人事档案转出情况 
	 * @return 
	 * @throws Exception
	 */
	public String exportReportResultPersionalArchivesMoveOutExcel() throws Exception {
		// TODO Auto-generated method stub
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		List<ReportResultPersionalArchivesMoveOut>  pReportResultPersionalArchivesMoveOuts = new ArrayList<ReportResultPersionalArchivesMoveOut>(); 
		try {
			// 获取用户ID
			pErrPos = 1;
			if (pFlag) {
				if (statReportManageService.findAllReportResultPersionalArchivesMoveOut(pReportResultPersionalArchivesMoveOuts, pErrInfo) == false) {
					result = "error";
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询报表统计结果表－档案分类利用情况失败：");
				}
			}
			
			
			HSSFWorkbook wb = new HSSFWorkbook();   
		    HSSFSheet sheet = wb.createSheet("sheet1");   
	        int rowNum = 0; // 标题开始行   
	        int colNum = 0; // 标题开始列   
	        HSSFRow row = sheet.createRow(rowNum++); //创建第1行，也就是输出表头 
	        HSSFCell cell = row.createCell(colNum++); // HSSFCell excell的格子单元
			cell.setCellValue("人事档案具体子类名称");
			cell = row.createCell(colNum++); 
			cell.setCellValue("总卷次");
			for (ReportResultPersionalArchivesMoveOut reportResultPersionalArchivesMoveOut : pReportResultPersionalArchivesMoveOuts) {
				 row = sheet.createRow(rowNum++); 
				 colNum=0; 
			     cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(reportResultPersionalArchivesMoveOut.getArchivesTypeName()));   
                    
                 cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(String.valueOf(reportResultPersionalArchivesMoveOut.getColumeCount()))); 
			}
			this.workbookInputStream(wb,"ReportResultPersionalArchivesMoveOut");
			
			result = "success";

		} catch (Exception e) {
			result = "error";
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
		}
		return result;
	}
	
	
	/**
	 * excel导出报表统计结果表－档案销毁情况 
	 * @return 
	 * @throws Exception
	 */
	public String exportReportResultArchivesDestroyExcel() throws Exception {
		// TODO Auto-generated method stub
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		List<ReportResultArchivesDestroy>  pReportResultArchivesDestroys = new ArrayList<ReportResultArchivesDestroy>(); 
		try {
			// 获取用户ID
			pErrPos = 1;
			if (pFlag) {
				if (statReportManageService.findAllReportResultArchivesDestroy(pReportResultArchivesDestroys, pErrInfo) == false) {
					result = "error";
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询报表统计结果表－档案销毁情况失败：");
				}
			}
			
			HSSFWorkbook wb = new HSSFWorkbook();   
		    HSSFSheet sheet = wb.createSheet("sheet1");   
	        int rowNum = 0; // 标题开始行   
	        int colNum = 0; // 标题开始列   
	        HSSFRow row = sheet.createRow(rowNum++); //创建第1行，也就是输出表头 
	        HSSFCell cell = row.createCell(colNum++); // HSSFCell excell的格子单元
			cell.setCellValue("一级类目名称");
			cell = row.createCell(colNum++); 
			cell.setCellValue("年度总卷数");
			cell = row.createCell(colNum++); 
			cell.setCellValue("年度总件数");
			cell = row.createCell(colNum++); 
			cell.setCellValue("历史累计卷数");
			cell = row.createCell(colNum++); 
			cell.setCellValue("历史累计件数");
			for (ReportResultArchivesDestroy reportResultArchivesDestroy : pReportResultArchivesDestroys) {
				 row = sheet.createRow(rowNum++); 
				 colNum=0; 
			     cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(reportResultArchivesDestroy.getArchivesTypeName()));   
                    
                 cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(String.valueOf(reportResultArchivesDestroy.getColumeCount()))); 
                 
                 cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(String.valueOf(reportResultArchivesDestroy.getPieceCount()))); 
                 
                 cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(String.valueOf(reportResultArchivesDestroy.getHistoryColumeCount()))); 
                 
                 cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(String.valueOf(reportResultArchivesDestroy.getHistoryPieceCount()))); 
                 
			}
			this.workbookInputStream(wb,"ReportResultArchivesDestroy");
			
			result = "success";

		} catch (Exception e) {
			result = "error";
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
		}
		return result;
	}
	
	/**
	 * excel导出报表统计结果表－档案开放情况 
	 * @return 
	 * @throws Exception
	 */
	public String exportReportResultArchivesPublicExcel() throws Exception {
		// TODO Auto-generated method stub
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		List<ReportResultArchivesPublic>  pReportResultArchivesPublics = new ArrayList<ReportResultArchivesPublic>(); 
		try {
			// 获取用户ID
			pErrPos = 1;
			if (pFlag) {
				if (statReportManageService.findAllReportResultArchivesPublic(pReportResultArchivesPublics, pErrInfo) == false) {
					result = "error";
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询报表统计结果表－档案开放情况失败：");
				}
			}
			
			
			HSSFWorkbook wb = new HSSFWorkbook();   
		    HSSFSheet sheet = wb.createSheet("sheet1");   
	        int rowNum = 0; // 标题开始行   
	        int colNum = 0; // 标题开始列   
	        HSSFRow row = sheet.createRow(rowNum++); //创建第1行，也就是输出表头 
	        HSSFCell cell = row.createCell(colNum++); // HSSFCell excell的格子单元
	    	cell.setCellValue("一级类目名称");
			cell = row.createCell(colNum++); 
			cell.setCellValue("年度总卷数");
			cell = row.createCell(colNum++); 
			cell.setCellValue("年度总件数");
			cell = row.createCell(colNum++); 
			cell.setCellValue("历史累计卷数");
			cell = row.createCell(colNum++); 
			cell.setCellValue("历史累计件数");
			for (ReportResultArchivesPublic reportResultArchivesPublic : pReportResultArchivesPublics) {
				 row = sheet.createRow(rowNum++); 
				 colNum=0; 
			     cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(reportResultArchivesPublic.getArchivesTypeName()));   
                    
                 cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(String.valueOf(reportResultArchivesPublic.getColumeCount()))); 
                 
                 cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(String.valueOf(reportResultArchivesPublic.getPieceCount()))); 
                 
                 cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(String.valueOf(reportResultArchivesPublic.getHistoryColumeCount()))); 
                 
                 cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(String.valueOf(reportResultArchivesPublic.getHistoryPieceCount()))); 
                 
			}
			this.workbookInputStream(wb,"ReportResultArchivesPublic");
			
			result = "success";

		} catch (Exception e) {
			result = "error";
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
		}
		return result;
	}
	
	/**
	 * excel导出表统计结果表－库房设施利用情况 
	 * @return 
	 * @throws Exception
	 */
	public String exportReportResultStoreroomUseExcel() throws Exception {
		// TODO Auto-generated method stub
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		List<ReportResultStoreroomUse>  pReportResultStoreroomUses = new ArrayList<ReportResultStoreroomUse>(); 
		try {
			// 获取用户ID
			pErrPos = 1;
			if (pFlag) {
				if (statReportManageService.findAllReportResultStoreroomUse(pReportResultStoreroomUses, pErrInfo) == false) {
					result = "error";
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询报表统计结果表－库房设施利用情况失败：");
				}
			}
			
			HSSFWorkbook wb = new HSSFWorkbook();   
		    HSSFSheet sheet = wb.createSheet("sheet1");   
	        int rowNum = 0; // 标题开始行   
	        int colNum = 0; // 标题开始列   
	        HSSFRow row = sheet.createRow(rowNum++); //创建第1行，也就是输出表头 
	        HSSFCell cell = row.createCell(colNum++); // HSSFCell excell的格子单元
			cell.setCellValue("库房名称");
			cell = row.createCell(colNum++); 
			cell.setCellValue("总大小");
			cell = row.createCell(colNum++); 
			cell.setCellValue("已用空间");
			cell = row.createCell(colNum++); 
			cell.setCellValue("使用率");
			for (ReportResultStoreroomUse reportResultStoreroomUse : pReportResultStoreroomUses) {
				 row = sheet.createRow(rowNum++); 
				 colNum=0; 
			     cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(reportResultStoreroomUse.getStoreroomName()));   
                    
                 cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(String.valueOf(reportResultStoreroomUse.getTotalSize()))); 
                 
                 cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(String.valueOf(reportResultStoreroomUse.getUsedSize()))); 
                 
                 cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(String.valueOf(reportResultStoreroomUse.getUsePercent()))); 
                 
			}
			this.workbookInputStream(wb,"ReportResultStoreroomUse");
			
			result = "success";

		} catch (Exception e) {
			result = "error";
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
		}
		return result;
	}
	
	/**
	 * excel导出报表统计结果表－库房温湿度年度变化情况
	 * @return 
	 * @throws Exception
	 */
	public String exportReportResultTempratureHumidityForYearExcel() throws Exception {
		// TODO Auto-generated method stub
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		List<ReportResultTempratureHumidityForYear>  pReportResultTempratureHumidityForYears = new ArrayList<ReportResultTempratureHumidityForYear>(); 
		try {
			// 获取用户ID
			pErrPos = 1;
			if (pFlag) {
				if (statReportManageService.findAllReportResultTempratureHumidityForYear(pReportResultTempratureHumidityForYears, pErrInfo) == false) {
					result = "error";
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询报表统计结果表－库房温湿度年度变化情况失败：");
				}
			}
			
			HSSFWorkbook wb = new HSSFWorkbook();   
		    HSSFSheet sheet = wb.createSheet("sheet1");   
	        int rowNum = 0; // 标题开始行   
	        int colNum = 0; // 标题开始列   
	        HSSFRow row = sheet.createRow(rowNum++); //创建第1行，也就是输出表头 
	        HSSFCell cell = row.createCell(colNum++); // HSSFCell excell的格子单元
			cell.setCellValue("库房名称");
			cell = row.createCell(colNum++); 
			cell.setCellValue("月份");
			cell = row.createCell(colNum++); 
			cell.setCellValue("平均温度");
			cell = row.createCell(colNum++); 
			cell.setCellValue("平均温度");
			for (ReportResultTempratureHumidityForYear reportResultTempratureHumidityForYear : pReportResultTempratureHumidityForYears) {
				 row = sheet.createRow(rowNum++); 
				 colNum=0; 
			     cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(reportResultTempratureHumidityForYear.getStoreroomName()));   
                    
                 cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(String.valueOf(reportResultTempratureHumidityForYear.getAvgHumidity()))); 
                 
                 cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(String.valueOf(reportResultTempratureHumidityForYear.getAvgTemprature()))); 
                 
			}
			this.workbookInputStream(wb,"ReportResultTempratureHumidityForYear");
			result = "success";

		} catch (Exception e) {
			result = "error";
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
		}
		return result;
	}
	
	/**
	 * excel导出报表统计结果表－库房温湿度月度变化情况 
	 * @return 
	 * @throws Exception
	 */
	public String exportReportResultTempratureHumidityForMonthExcel() throws Exception {
		// TODO Auto-generated method stub
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		List<ReportResultTempratureHumidityForMonth>  pReportResultTempratureHumidityForMonths = new ArrayList<ReportResultTempratureHumidityForMonth>(); 
		try {
			// 获取用户ID
			pErrPos = 1;
			if (pFlag) {
				if (statReportManageService.findAllReportResultTempratureHumidityForMonth(pReportResultTempratureHumidityForMonths, pErrInfo) == false) {
					result = "error";
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询报表统计结果表－库房温湿度月度变化情况失败：");
				}
			}
			
			HSSFWorkbook wb = new HSSFWorkbook();   
		    HSSFSheet sheet = wb.createSheet("sheet1");   
	        int rowNum = 0; // 标题开始行   
	        int colNum = 0; // 标题开始列   
	        HSSFRow row = sheet.createRow(rowNum++); //创建第1行，也就是输出表头 
	        HSSFCell cell = row.createCell(colNum++); // HSSFCell excell的格子单元
	        cell.setCellValue("库房名称");
			cell = row.createCell(colNum++); 
			cell.setCellValue("日期");
			cell = row.createCell(colNum++); 
			cell.setCellValue("平均温度");
			cell = row.createCell(colNum++); 
			cell.setCellValue("平均温度");
			for (ReportResultTempratureHumidityForMonth reportResultTempratureHumidityForMonth : pReportResultTempratureHumidityForMonths) {
				 row = sheet.createRow(rowNum++); 
				 colNum=0; 
			     cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(reportResultTempratureHumidityForMonth.getStoreroomName()));   
                    
                 cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(String.valueOf(reportResultTempratureHumidityForMonth.getDate()))); 
                 
                 cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(String.valueOf(reportResultTempratureHumidityForMonth.getAvgHumidity()))); 
                 
                 cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(String.valueOf(reportResultTempratureHumidityForMonth.getAvgTemprature()))); 
                 
			}
			this.workbookInputStream(wb,"ReportResultTempratureHumidityForMonth");
			result = "success";

		} catch (Exception e) {
			result = "error";
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
		}
		return result;
	}
	
	/**
	 * excel导出表统计结果表－出证收费情况 
	 * @return 
	 * @throws Exception
	 */
	public String exportReportResultCertificateChargeExcel() throws Exception {
		// TODO Auto-generated method stub
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		List<ReportResultCertificateCharge>  pReportResultCertificateCharges = new ArrayList<ReportResultCertificateCharge>(); 
		try {
			// 获取用户ID
			pErrPos = 1;
			if (pFlag) {
				if (statReportManageService.findAllReportResultCertificateCharge(pReportResultCertificateCharges, pErrInfo) == false) {
					result = "error";
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询报表统计结果表－出证收费情况失败：");
				}
			}
			
			HSSFWorkbook wb = new HSSFWorkbook();   
		    HSSFSheet sheet = wb.createSheet("sheet1");   
	        int rowNum = 0; // 标题开始行   
	        int colNum = 0; // 标题开始列   
	        HSSFRow row = sheet.createRow(rowNum++); //创建第1行，也就是输出表头 
	        HSSFCell cell = row.createCell(colNum++); // HSSFCell excell的格子单元
			cell.setCellValue("月份");
			cell = row.createCell(colNum++); 
			cell.setCellValue("接待人次");
			cell = row.createCell(colNum++); 
			cell.setCellValue("证明总份数");
			cell = row.createCell(colNum++); 
			cell.setCellValue("收费总金额");
			for (ReportResultCertificateCharge reportResultCertificateCharge : pReportResultCertificateCharges) {
				 row = sheet.createRow(rowNum++); 
				 colNum=0; 
			     cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(reportResultCertificateCharge.getMonth()));   
                    
                 cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(String.valueOf(reportResultCertificateCharge.getPersonTimeCount()))); 
                 
                 cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(String.valueOf(reportResultCertificateCharge.getCopysSum()))); 
                 
                 cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(String.valueOf(reportResultCertificateCharge.getChargeSum()))); 
                 
			}
			this.workbookInputStream(wb,"ReportResultCertificateCharge");
			result = "success";

		} catch (Exception e) {
			result = "error";
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
		}
		return result;
	}
	
	
	/**
	 * excel导出报表统计结果表－公文登记情况 
	 * @return 
	 * @throws Exception
	 */
	public String exportReportResultOfficialArchivesInputExcel() throws Exception {
		// TODO Auto-generated method stub
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		List<ReportResultOfficialArchivesInput>  pReportResultOfficialArchivesInputs = new ArrayList<ReportResultOfficialArchivesInput>(); 
		try {
			// 获取用户ID
			pErrPos = 1;
			if (pFlag) {
				if (statReportManageService.findAllReportResultOfficialArchivesInput(pReportResultOfficialArchivesInputs, pErrInfo) == false) {
					result = "error";
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询报表统计结果表－公文登记情况失败：");
				}
			}
			
			HSSFWorkbook wb = new HSSFWorkbook();   
		    HSSFSheet sheet = wb.createSheet("sheet1");   
	        int rowNum = 0; // 标题开始行   
	        int colNum = 0; // 标题开始列   
	        HSSFRow row = sheet.createRow(rowNum++); //创建第1行，也就是输出表头 
	        HSSFCell cell = row.createCell(colNum++); // HSSFCell excell的格子单元
			cell.setCellValue("部门名称");
			cell = row.createCell(colNum++); 
			cell.setCellValue("公文档案分类名称 ");
			cell = row.createCell(colNum++); 
			cell.setCellValue("总件次");
			for (ReportResultOfficialArchivesInput reportResultOfficialArchivesInput : pReportResultOfficialArchivesInputs) {
				 row = sheet.createRow(rowNum++); 
				 colNum=0; 
			     cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(reportResultOfficialArchivesInput.getDepartmentName()));   
                    
                 cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(String.valueOf(reportResultOfficialArchivesInput.getArchivesTypeName()))); 
                 
                 cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(String.valueOf(reportResultOfficialArchivesInput.getPieceCount()))); 
                 
			}
			this.workbookInputStream(wb,"ReportResultOfficialArchivesInput");
			
			result = "success";

		} catch (Exception e) {
			result = "error";
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
		}
		return result;
	}
	
	/**
	 * excel导出报表统计结果表－系统访问情况 
	 * @return 
	 * @throws Exception
	 */
	public String exportReportResultSystemVisitExcel() throws Exception {
		// TODO Auto-generated method stub
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		List<ReportResultSystemVisit>  pReportResultSystemVisits = new ArrayList<ReportResultSystemVisit>(); 
		try {
			// 获取用户ID
			pErrPos = 1;
			if (pFlag) {
				if (statReportManageService.findAllReportResultSystemVisit(pReportResultSystemVisits, pErrInfo) == false) {
					result = "error";
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询报表统计结果表－系统访问情况失败：");
				}
			}
			
			HSSFWorkbook wb = new HSSFWorkbook();   
		    HSSFSheet sheet = wb.createSheet("sheet1");   
	        int rowNum = 0; // 标题开始行   
	        int colNum = 0; // 标题开始列   
	        HSSFRow row = sheet.createRow(rowNum++); //创建第1行，也就是输出表头 
	        HSSFCell cell = row.createCell(colNum++); // HSSFCell excell的格子单元
			cell.setCellValue("系统功能名称 ");
			cell = row.createCell(colNum++); 
			cell.setCellValue("总访问次数");
			for (ReportResultSystemVisit reportResultSystemVisit : pReportResultSystemVisits) {
				 row = sheet.createRow(rowNum++); 
				 colNum=0; 
			     cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(reportResultSystemVisit.getSystemFeatureName()));   
                    
                 cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(String.valueOf(reportResultSystemVisit.getVisitTimeCount()))); 
                 
                 
			}
			this.workbookInputStream(wb,"ReportResultSystemVisit");
			
			result = "success";

		} catch (Exception e) {
			result = "error";
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
		}
		return result;
	}
	
	/**
	 * excel导出报表统计结果表－工作情况 
	 * @return 
	 * @throws Exception
	 */
	public String exportReportResultWorkProcedureExcel() throws Exception {
		// TODO Auto-generated method stub
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		List<ReportResultWorkProcedure>  pReportResultWorkProcedures = new ArrayList<ReportResultWorkProcedure>(); 
		try {
			// 获取用户ID
			pErrPos = 1;
			if (pFlag) {
				if (statReportManageService.findAllReportResultWorkProcedure(pReportResultWorkProcedures, pErrInfo) == false) {
					result = "error";
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询报表统计结果表－工作情况失败：");
				}
			}
			
			
			HSSFWorkbook wb = new HSSFWorkbook();   
		    HSSFSheet sheet = wb.createSheet("sheet1");   
	        int rowNum = 0; // 标题开始行   
	        int colNum = 0; // 标题开始列   
	        HSSFRow row = sheet.createRow(rowNum++); //创建第1行，也就是输出表头 
	        HSSFCell cell = row.createCell(colNum++); // HSSFCell excell的格子单元
			cell.setCellValue("真实姓名 ");
			cell = row.createCell(colNum++); 
			cell.setCellValue("著录条目数");
			cell = row.createCell(colNum++); 
			cell.setCellValue("著录提交送审条目数");
			cell = row.createCell(colNum++); 
			cell.setCellValue("实物档案馆外移交数量");
			cell = row.createCell(colNum++); 
			cell.setCellValue("业务指导室接收审核数量");
			cell = row.createCell(colNum++); 
			cell.setCellValue("实物档案馆内移交数量");
			cell = row.createCell(colNum++); 
			cell.setCellValue("档案管理室接收审核数量 ");
			for (ReportResultWorkProcedure reportResultWorkProcedure : pReportResultWorkProcedures) {
				 row = sheet.createRow(rowNum++); 
				 colNum=0; 
			     cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(reportResultWorkProcedure.getRealName()));   
                    
                 cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(String.valueOf(reportResultWorkProcedure.getInputCount()))); 
                 
                 cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(String.valueOf(reportResultWorkProcedure.getInputSubmitCount()))); 
                 
                 cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(String.valueOf(reportResultWorkProcedure.getPaperTrans1Count()))); 
                 
                 cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(String.valueOf(reportResultWorkProcedure.getPaperCheck1Count()))); 
                 
                 cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(String.valueOf(reportResultWorkProcedure.getPaperTrans2Count()))); 
                 
                 cell = row.createCell( colNum++);   
                 //cell.setCellStyle(cellStyle);   
                 cell.setCellValue(new HSSFRichTextString(String.valueOf(reportResultWorkProcedure.getPaperCheck2Count()))); 
                 
			}
			this.workbookInputStream(wb,"ReportResultWorkProcedure");
			
			result = "success";

		} catch (Exception e) {
			result = "error";
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
		}
		return result;
	}
	

}
