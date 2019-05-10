package com.orifound.aiim.entity;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * Excel工具类
 * @author Administrator
 *
 */
public class Excel {

	private HSSFWorkbook hssfWorkbook;

	public HSSFWorkbook getHssfWorkbook() {
		return hssfWorkbook;
	}

	public void setHssfWorkbook(HSSFWorkbook hssfWorkbook) {
		this.hssfWorkbook = hssfWorkbook;
	}

	public Excel(File file) {
		FileInputStream fs = null;
		POIFSFileSystem ps = null;
		try {
			fs = new FileInputStream(file);
			ps = new POIFSFileSystem(fs);
			this.hssfWorkbook = new HSSFWorkbook(ps);
		} catch (FileNotFoundException e) {
			System.out.println("找不到文件");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public HSSFSheet getSheet(String sheetName) {
		return hssfWorkbook.getSheet(sheetName);
	}

	/**
	 * 根据列名得到值
	 * 
	 * @param columnName
	 * @return
	 */
	public String getValue(int rowIndex, int cellIndex) throws Exception {
		String returnValue = "";
		DecimalFormat decimalFormat = new DecimalFormat("#");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
		HSSFRow hssfRow = hssfSheet.getRow(rowIndex);
		HSSFCell hssfCell = null;
		if (hssfRow != null) {
			hssfCell = hssfRow.getCell(cellIndex);
			if (hssfCell != null) {
				switch (hssfCell.getCellType()) {
					case HSSFCell.CELL_TYPE_NUMERIC: // 数值型
						if (HSSFDateUtil.isCellDateFormatted(hssfCell)) {// 如果是date类型则 ，获取该cell的date值
							returnValue = dateFormat.format(HSSFDateUtil.getJavaDate(hssfCell.getNumericCellValue()));
						} else {// 纯数字
							returnValue = decimalFormat.format(hssfCell.getNumericCellValue());
						}
						break;
						
					/* 此行表示单元格的内容为string类型 */
					case HSSFCell.CELL_TYPE_STRING: // 字符串型
						
						returnValue = hssfCell.getRichStringCellValue().toString();
						break;
						
					case HSSFCell.CELL_TYPE_FORMULA:// 公式型
						// 读公式计算值
						returnValue = String.valueOf(hssfCell.getNumericCellValue());
						if (returnValue.equals("NaN")) {// 如果获取的数据值为非法值,则转换为获取字符串
							returnValue = hssfCell.getRichStringCellValue().toString();
						}
						// cell.getCellFormula();读公式
						break;
						
					case HSSFCell.CELL_TYPE_BOOLEAN:// 布尔
						returnValue = "" + hssfCell.getBooleanCellValue();
						break;
						
					/* 此行表示该单元格值为空 */
					case HSSFCell.CELL_TYPE_BLANK: // 空值
						returnValue = "";
						break;
						
					case HSSFCell.CELL_TYPE_ERROR: // 故障
						returnValue = "";
						break;
						
					default:
						returnValue = hssfCell.getRichStringCellValue().toString();
				}
			} else {
				returnValue = "";
			}
		}else{
			returnValue = "";
		}
		return returnValue;
	}

	/**
	 * 得到所有的记录数
	 * 
	 * @return
	 */
	public int getRowCount() {
		return hssfWorkbook.getSheetAt(0).getLastRowNum();
	}

	/**
	 * 得到Excel表里面的字段名
	 * 
	 * @return
	 */
	public List<String> getColumnNames() {
		List<String> columnNames = new ArrayList<String>();

		HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
		HSSFRow columnNameRow = hssfSheet.getRow(0);
		for (int i = 0; i < columnNameRow.getLastCellNum(); i++) {
			columnNames.add(columnNameRow.getCell(i).getStringCellValue());
		}
		return columnNames;
	}
	
	
}
