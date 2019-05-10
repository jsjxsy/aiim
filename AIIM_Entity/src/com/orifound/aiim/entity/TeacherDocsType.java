package com.orifound.aiim.entity;

public class TeacherDocsType {

	private int ID;
	
	private String name;
	
	private int displayOrderID;
	
	private String reMark;
	
	private String caseType;
	
	private int blankLines;

	public int getBlankLines() {
		return blankLines;
	}

	public void setBlankLines(int blankLines) {
		this.blankLines = blankLines;
	}

	public String getCaseType() {
		return caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDisplayOrderID() {
		return displayOrderID;
	}

	public void setDisplayOrderID(int displayOrderID) {
		this.displayOrderID = displayOrderID;
	}

	public String getReMark() {
		return reMark;
	}

	public void setReMark(String reMark) {
		this.reMark = reMark;
	}
	
	public TeacherDocsType(){
		
	}
	
	public TeacherDocsType(int ID,String name,int displayOrderID,String reMark,String caseType,int blankLines){
		this.ID = ID;
		this.name = name;
		this.displayOrderID = displayOrderID;
		this.reMark = reMark;
		this.caseType = caseType;
		this.blankLines = blankLines;
		
	}
	
}
