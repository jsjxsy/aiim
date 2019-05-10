package com.orifound.aiim.entity;

/**
 * ѧ�������ļ���Ϣ��ʵ����
 * @author Administrator
 *
 */
public class StudentDocsInfo {

	/**
	 * ѧ����Ϣ�����ļ����
	 */
	private int id;
	
	/**
	 * �ĵ����
	 */
	private int docId;
	
	/**
	 * ѧ�������ڲ����
	 */
	private int nbxh;
	
	/**
	 * �ĵ�����
	 */
	private String docName;
	
	/**
	 * �Ƿ����
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
