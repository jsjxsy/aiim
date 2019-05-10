package com.orifound.aiim.entity;

/**
 * 学生卷内文件信息表实体类
 * @author Administrator
 *
 */
public class StudentDocsInfo {

	/**
	 * 学生信息卷内文件编号
	 */
	private int id;
	
	/**
	 * 文档编号
	 */
	private int docId;
	
	/**
	 * 学生档案内部序号
	 */
	private int nbxh;
	
	/**
	 * 文档名称
	 */
	private String docName;
	
	/**
	 * 是否存在
	 */
	private boolean existsFlag;

	public StudentDocsInfo(){
		
	}
	
	public StudentDocsInfo(int id,int docId,int nbxh,String docName,boolean existsFlag){
		this.id = id;
		this.docId = docId;
		this.nbxh = nbxh;
		this.docName = docName;
		this.existsFlag = existsFlag;
	}
	
	public StudentDocsInfo(int id,int docId,String docName,boolean existsFlag){
		this.id = id;
		this.docId = docId;
		this.docName = docName;
		this.existsFlag = existsFlag;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDocId() {
		return docId;
	}

	public void setDocId(int docId) {
		this.docId = docId;
	}

	public int getNbxh() {
		return nbxh;
	}

	public void setNbxh(int nbxh) {
		this.nbxh = nbxh;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public boolean isExistsFlag() {
		return existsFlag;
	}

	public void setExistsFlag(boolean existsFlag) {
		this.existsFlag = existsFlag;
	}

	
}
