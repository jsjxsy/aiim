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
 * Excel������
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
			System.out.println("�Ҳ����ļ�");
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
	 * ���������õ�ֵ
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
					case HSSFCell.CELL_TYPE_NUMERIC: // ��ֵ��
						if (HSSFDateUtil.isCellDateFormatted(hssfCell)) {// �����date������ ����ȡ��cell��dateֵ
							returnValue = dateFormat.format(HSSFDateUtil.getJavaDate(hssfCell.getNumericCellValue()));
						} else {// ������
							returnValue = decimalFormat.format(hssfCell.getNumericCellValue());
						}
						break;
						
					/* ���б�ʾ��Ԫ�������Ϊstring���� */
					case HSSFCell.CELL_TYPE_STRING: // �ַ�����
						
						returnValue = hssfCell.getRichStringCellValue().toString();
						break;
						
					case HSSFCell.CELL_TYPE_FORMULA:// ��ʽ��
						// ����ʽ����ֵ
						returnValue = String.valueOf(hssfCell.getNumericCellValue());
						if (returnValue.equals("NaN")) {// �����ȡ������ֵΪ�Ƿ�ֵ,��ת��Ϊ��ȡ�ַ���
							returnValue = hssfCell.getRichStringCellValue().toString();
						}
						// cell.getCellFormula();����ʽ
						break;
						
					case HSSFCell.CELL_TYPE_BOOLEAN:// ����
						returnValue = "" + hssfCell.getBooleanCellValue();
						break;
						
					/* ���б�ʾ�õ�Ԫ��ֵΪ�� */
					case HSSFCell.CELL_TYPE_BLANK: // ��ֵ
						returnValue = "";
						break;
						
					case HSSFCell.CELL_TYPE_ERROR: // ����
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
	 * �õ����еļ�¼��
	 * 
	 * @return
	 */
	public int getRowCount() {
		return hssfWorkbook.getSheetAt(0).getLastRowNum();
	}

	/**
	 * �õ�Excel��������ֶ���
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
