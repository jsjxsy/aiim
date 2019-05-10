package com.orifound.aiim.web.util;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ArchivesTypeEx;
import com.orifound.aiim.entity.DepartmentInfo;
import com.orifound.aiim.entity.EnumWorkFlowStatus;
import com.orifound.aiim.entity.OfficialArchivesType;
import com.orifound.aiim.entity.OfficialDocType;
import com.orifound.aiim.entity.StatReport;
import com.orifound.aiim.entity.StoreroomStructure;
import com.orifound.aiim.entity.SystemFeature;
import com.orifound.aiim.entity.UserDefinedSearch;
import com.orifound.aiim.entity.UserInfo;
import com.orifound.aiim.entity.UserRole;
import com.orifound.aiim.entity.UserRolesArchivesType;
import com.orifound.aiim.entity.UserRolesInfo;
import com.orifound.aiim.entity.UserRolesSystemFeature;

public class CreateTreeUtil {

	//�õ�����������
	public static String getArchivesTypeTree(List<ArchivesType> archivesTypes){
		String tree = "";	
		for (ArchivesType archivesType : archivesTypes) {	
			tree += "var n"+archivesType.getID()+" = tree.add(root,'last','"+archivesType.getName()+"','"+archivesType.getID()+"');";
			Map<Integer, ArchivesType> childArchivesTypes = archivesType.getChildArchivesTypes();
			tree += getArchivesTypeTreeChild(childArchivesTypes);
		}
		return tree;
	}
	//���� ���������� �޼��ӽڵ�
	public static String getArchivesTypeTreeChild(Map<Integer, ArchivesType> childArchivesTypes){
		String tree = "";
		if(childArchivesTypes!= null){
			
			for (Iterator<Integer> iterator = childArchivesTypes.keySet().iterator(); iterator.hasNext();) {
				ArchivesType childArchivesType = (ArchivesType) childArchivesTypes.get(iterator.next());
				tree+= "var n"+childArchivesType.getID()+" = tree.add(n"+childArchivesType.getParentID()+",'last','"+childArchivesType.getName()+"','"+childArchivesType.getID()+"');";
				//
			     Map<Integer, ArchivesType> childArchivesTypes1 = childArchivesType.getChildArchivesTypes();
				 tree += getArchivesTypeTreeChild(childArchivesTypes1);			
			}
		}
		return tree;
	}
	
	//�õ����ķ�����
	public static String getOfficialArchivesTypeTree(LinkedHashMap<Integer, OfficialArchivesType> officialArchivesTypes){
		String tree = "";	
		for (OfficialArchivesType officialArchivesType : officialArchivesTypes.values()) {	
			tree += "var n"+officialArchivesType.getID()+" = tree.add(root,'last','"+officialArchivesType.getName()+"','"+officialArchivesType.getID()+"');";
		}
		return tree;
	}
	
	//�õ�����������
	public static String getDocCenterTree(LinkedHashMap<Integer, OfficialArchivesType> officialArchivesTypes,LinkedHashMap<Integer, OfficialDocType> officialDocTypes,List<DepartmentInfo> departmentInfos){
		String tree = "var n111 = tree.add(root,'last','����ģ��','111');";
		tree+="var n222 = tree.add(root,'last','���ӹ���','222');";
		int temp=officialDocTypes.size();
		for (OfficialDocType officialDocType : officialDocTypes.values()) {
			tree+="n111.addLink('officialTemplateAction_findAllOfficialTemplate.action?type="+officialDocType.getID()+"','"+officialDocType.getName()+"','right',null);";
		}
		
		for (DepartmentInfo departmentInfo : departmentInfos) {
			int index=temp+departmentInfo.getID();
			tree += "var n"+index+" = tree.add(n222,'last','"+departmentInfo.getName()+"','"+departmentInfo.getID()+"');";
			for (OfficialArchivesType officialArchivesType : officialArchivesTypes.values()) {
				tree+="n"+index+".addLink('officeDocAction_findOfficialArchivesInfosByCondition.action?officialArchivesTypeID="+officialArchivesType.getID()+"&FormationDepartmentID="+departmentInfo.getID()+"','"+officialArchivesType.getName()+"','right',null);";
			}
		}
        		
		return tree;
	}
	
	//�õ���CheckBox����������
	public static String getArchivesTypeCheckBoxTree(List<ArchivesType> archivesTypes){
		String tree = "";	
		for (ArchivesType archivesType : archivesTypes) {	
			tree += "var n"+archivesType.getID()+" = tree.addChkNode(root,'"+archivesType.getName()+"','"+archivesType.getID()+"');";
			Map<Integer, ArchivesType> childArchivesTypes = archivesType.getChildArchivesTypes();
			tree += getArchivesTypeTreeCheckBoxChild(childArchivesTypes);
		}
		return tree;
	}
	//���� ��CheckBox���������� �޼��ӽڵ�
	public static String getArchivesTypeTreeCheckBoxChild(Map<Integer, ArchivesType> childArchivesTypes){
		String tree = "";
		if(childArchivesTypes!= null){
			
			for (Iterator<Integer> iterator = childArchivesTypes.keySet().iterator(); iterator.hasNext();) {
				ArchivesType childArchivesType = (ArchivesType) childArchivesTypes.get(iterator.next());
				tree+= "var n"+childArchivesType.getID()+" = tree.addChkNode(n"+childArchivesType.getParentID()+",'"+childArchivesType.getName()+"','"+childArchivesType.getID()+"');";
				//
			     Map<Integer, ArchivesType> childArchivesTypes1 = childArchivesType.getChildArchivesTypes();
				 tree += getArchivesTypeTreeCheckBoxChild(childArchivesTypes1);			
			}
		}		
		return tree;
	}

	/**
	 * �õ�������
	 * @param departmentInfos
	 * @return
	 */
	public static String getDeptTree(List<DepartmentInfo> departmentInfos){
		String tree = "var n0 = tree.add(root,'last','����','00');";
		for (DepartmentInfo departmentInfo : departmentInfos) {
			tree +="var n"+departmentInfo.getID() +" = tree.add(n0,'last','"+departmentInfo.getName()+"','"+departmentInfo.getID() +"');";
		}
		return tree;
	}
	
	/**
	 * ��ȡ���ɿⷿ�ṹһ���ڵ���javascript����
	 * @return
	 */
	public static String getStoreroomStructureTree(List<StoreroomStructure> storeroomStructures){
		String tree ="";
		tree = "var n0 = tree.add(root,'last','�ⷿ�ṹ','0');";
		for(StoreroomStructure storeroomStructure:storeroomStructures){
			tree =tree + "var n"+storeroomStructure.getID()+"=tree.add(n0,'last','"+storeroomStructure.getName()+"','"+storeroomStructure.getID()+"');";
			//var n11 = tree.add(n0,'last','�ⷿ�ṹ','0');
			if(storeroomStructure.getChildren() != null && storeroomStructure.getChildren().size()!=0){
				tree = tree + getStoreroomStructureChildTree(storeroomStructure.getChildren());//��ȡ�ӽڵ㹹���������javascript����
			}
		}	
		return tree;
	}
	//��ȡ���ɿⷿ�ṹ��javascript����
	public static String getStoreroomStructureChildTree(List<StoreroomStructure> storeroomStructures){
		String tree = "";
		for(StoreroomStructure storeroomStructure:storeroomStructures){
			tree =tree + "var n"+storeroomStructure.getID()+"=tree.add(n"+storeroomStructure.getParentID()+",'last','"+storeroomStructure.getName()+"','"+storeroomStructure.getID()+"');";
			if(storeroomStructure.getChildren() != null && storeroomStructure.getChildren().size()!=0){
				tree = tree + getStoreroomStructureChildTree(storeroomStructure.getChildren());//��ȡ�ӽڵ㹹���������javascript����
			}
		}
		return tree;
	}
	
	
	/**
	 * �õ������γɲ��ŵ����ƽ���
	 * @param departmentInfos
	 * @return
	 */
	public static String getArchivesTypeTreeWithProperty(List<ArchivesTypeEx> archivesTypes){
		String tree = "";
		for (ArchivesTypeEx archivesType : archivesTypes) {	
			tree += "var n"+archivesType.getID()+" = tree.add(root,'last','"+archivesType.getName()+"','"+archivesType.getID()+"');";
			Map<Integer, ArchivesTypeEx> childArchivesTypes = archivesType.getChildArchivesTypeExs();
			if(childArchivesTypes == null){
				tree += "var n"+archivesType.getID()+1+" = tree.add(n"+archivesType.getID()+",'last','�Ѻ�׼("+archivesType.getInputCheckPassArchivesInfoCount()+")','"+ EnumWorkFlowStatus.ҵ��ָ������¼���ͨ��.getEnumValue() +"');";
				tree += "var n"+archivesType.getID()+2+" = tree.add(n"+archivesType.getID()+",'last','δ��׼("+archivesType.getInputCheckBackArchivesInfoCount()+")','"+ EnumWorkFlowStatus.ҵ��ָ������¼��˴��.getEnumValue() +"');";
				tree += "var n"+archivesType.getID()+3+" = tree.add(n"+archivesType.getID()+",'last','���("+archivesType.getPaperCheckBackArchivesInfoCount()+")','"+ EnumWorkFlowStatus.ҵ��ָ���ҽ�����˴��.getEnumValue() +"');";
			}else{
				tree += getArchivesTypeExTreeChild(childArchivesTypes);
			}	
		}
		return tree;
	}
	
	//�����޼��ӽڵ�
	public static String getArchivesTypeExTreeChild(Map<Integer, ArchivesTypeEx> childArchivesTypeExs){
		String tree = "";
		for (Iterator<Integer> iterator = childArchivesTypeExs.keySet().iterator(); iterator.hasNext();) {
			ArchivesTypeEx childArchivesTypeEx = (ArchivesTypeEx) childArchivesTypeExs.get(iterator.next());
			tree+= "var n"+childArchivesTypeEx.getID()+" = tree.add(n"+childArchivesTypeEx.getParentID()+",'last','"+childArchivesTypeEx.getName()+"','"+childArchivesTypeEx.getID()+"');";
		     Map<Integer, ArchivesTypeEx> childArchivesTypeExs1 = childArchivesTypeEx.getChildArchivesTypeExs();
		     if(childArchivesTypeExs1 == null){
		    	 tree += "var n"+childArchivesTypeEx.getID()+1+" = tree.add(n"+childArchivesTypeEx.getID()+",'last','�Ѻ�׼("+childArchivesTypeEx.getInputCheckPassArchivesInfoCount()+")','"+ EnumWorkFlowStatus.ҵ��ָ������¼���ͨ��.getEnumValue() +"');";
				 tree += "var n"+childArchivesTypeEx.getID()+2+" = tree.add(n"+childArchivesTypeEx.getID()+",'last','δ��׼("+childArchivesTypeEx.getInputCheckBackArchivesInfoCount()+")','"+ EnumWorkFlowStatus.ҵ��ָ������¼��˴��.getEnumValue() +"');";
				 tree += "var n"+childArchivesTypeEx.getID()+3+" = tree.add(n"+childArchivesTypeEx.getID()+",'last','���("+childArchivesTypeEx.getPaperCheckBackArchivesInfoCount()+")','"+ EnumWorkFlowStatus.ҵ��ָ���ҽ�����˴��.getEnumValue() +"');";
		     }else{
		    	 tree += getArchivesTypeExTreeChild(childArchivesTypeExs1);		
		     }	 	
		}
		return tree;
	}

	/**
	 * ҵ��ָ���ҹ鵵������
	 * @param archivesTypes
	 * @return
	 */
	public static String getArchivesTypeTreeWithPropertyCheckBack(List<ArchivesTypeEx> archivesTypes){
		String tree = "";
		for (ArchivesTypeEx archivesType : archivesTypes) {	
			tree += "var n"+archivesType.getID()+" = tree.add(root,'last','"+archivesType.getName()+"','"+archivesType.getID()+"');";
			Map<Integer, ArchivesTypeEx> childArchivesTypes = archivesType.getChildArchivesTypeExs();
			if(childArchivesTypes == null){
				tree += "var n"+archivesType.getID()+3+" = tree.add(n"+archivesType.getID()+",'last','���("+archivesType.getPaperCheckBackArchivesInfoCount()+")','"+ EnumWorkFlowStatus.ҵ��ָ���ҽ�����˴��.getEnumValue() +"');";
			}else{
				tree += getArchivesTypeExTreeChildCheckBack(childArchivesTypes);
			}	
		}
		
		
		return tree;
	}
	
	//�����޼��ӽڵ�
	public static String getArchivesTypeExTreeChildCheckBack(Map<Integer, ArchivesTypeEx> childArchivesTypeExs){
		String tree = "";
		for (Iterator<Integer> iterator = childArchivesTypeExs.keySet().iterator(); iterator.hasNext();) {
			ArchivesTypeEx childArchivesTypeEx = (ArchivesTypeEx) childArchivesTypeExs.get(iterator.next());
			tree+= "var n"+childArchivesTypeEx.getID()+" = tree.add(n"+childArchivesTypeEx.getParentID()+",'last','"+childArchivesTypeEx.getName()+"','"+childArchivesTypeEx.getID()+"');";
		     Map<Integer, ArchivesTypeEx> childArchivesTypeExs1 = childArchivesTypeEx.getChildArchivesTypeExs();
		     if(childArchivesTypeExs1 == null){
				 tree += "var n"+childArchivesTypeEx.getID()+3+" = tree.add(n"+childArchivesTypeEx.getID()+",'last','���("+childArchivesTypeEx.getPaperCheckBackArchivesInfoCount()+")','"+ EnumWorkFlowStatus.ҵ��ָ���ҽ�����˴��.getEnumValue() +"');";
		     }else{
		    	 tree += getArchivesTypeExTreeChildCheckBack(childArchivesTypeExs1);		
		     }	 	
		}
		return tree;
	}
	
	/**
	 * �õ��û���
	 * @param userInfos
	 * @return
	 */
	public static String getUserTree(List<UserInfo> userInfos){
		String tree="var n0 = tree.add(root,'last','����','00');";
		for (UserInfo userInfo : userInfos) {
			tree +="var n"+userInfo.getUserID() +" = tree.add(n0,'last','"+userInfo.getRealName()+"','"+userInfo.getUserID() +"');";
		}
		return tree;
	}
	
	/**
	 * �õ��Զ���������ѯ��
	 * @param departmentInfos
	 * @return
	 */
	public static String getArchivesUserDefinedSearchTree(List<UserDefinedSearch> userDefinedSearchs){
		String tree = "";
		tree = "var n0 = tree.add(root,'last','�Զ���������ѯ','0');";
		//tree += "var n"+ "�û��Զ���������ѯ" +" = tree.add(root,'last','"+userDefinedSearch.getName()+"','"+userDefinedSearch.getID()+"');";
		for (UserDefinedSearch userDefinedSearch : userDefinedSearchs) {	
			tree += "var n"+userDefinedSearch.getID()+" = tree.add(n0,'last','"+userDefinedSearch.getName()+"','"+userDefinedSearch.getID()+"');";
		}
		return tree;
	}
	

	
	/**
	 * ������Ч������ϵͳ���β˵�
	 * @return String
	 */
	public static String getPerformanceManageTree() {
		String tree = "var n1 = tree.add(root,'last','�������̼��','1');" +
				   "var n11 = tree.add(n1,'last','��¼������','11');" +
				   "var n12 = tree.add(n1,'last','��¼������','12');" +
				   "var n13 = tree.add(n1,'last','��⹤����','13');";
		return tree;
	}
	
	/**
	 * ��������������ϵͳ->�����Ǽ���Ϣ	���β˵�
	 * @return String
	 */
	public static String getArchiveAppraisalTree() {
		String tree = "var n1 = tree.add(root,'last','�����Ǽ���Ϣ','1');" +
		   "var n11 = tree.add(n1,'last','��ټ���','11');" +
		   "var n12 = tree.add(n1,'last','���ż���','12');" +
		   "var n13 = tree.add(n1,'last','��������','13');" +
		   "var n13 = tree.add(n1,'last','���ؼ���','14');";
		return tree;
	}
	
	/**
	 * ���� ��������->��֤���� �˵���
	 * @return
	 */
	public static String getCertificateTree() {
		String tree = "var n1 = tree.add(root,'last','��֤����','1');" +
		   "var n11 = tree.add(n1,'last','�շѵǼ�','11');" +
		   "var n12 = tree.add(n1,'last','��֤����','12');" +
		   "var n13 = tree.add(n1,'last','�շѻ���','13');";
		return tree;
	}
	
	/**
	 * ��ɫ�û����β˵�
	 * @return String
	 */
	public static String getUserRolesInfoTree(List<UserRole> userRoles) {
		String tree = "";
		for (UserRole userRole : userRoles) {
			//  n21=tree1.add(n2,"first","9499.NET","net9499","star","url:_blank","http://www.9499.net") 
		    tree += "var n"+userRole.getID()+" = tree.add(root,'last','"+userRole.getName()+"','"+userRole.getID()+"'); " ;
		     if (!userRole.getName().trim().equals("�ÿ�") && !userRole.getName().trim().equals("��ְ��")) {
		    	 List<UserRolesInfo> userRolesInfos=userRole.getHasUsers();
				    for (UserRolesInfo userRolesInfo : userRolesInfos) {
						tree += "var n1"+userRolesInfo.getID()+" = tree.add(n"+userRolesInfo.getRolesID()+",'last','"+userRolesInfo.getRealName()+"','"+userRolesInfo.getUserID()+"');";
					}
			}
		   
		}
		return tree;
	}
	
	private static String getArchivesTypeCheckBoxTreeChild(Map<Integer,ArchivesType> childArchivesTypes){
		String tree = "";
		if(childArchivesTypes!= null){
			
			for (Iterator<Integer> iterator = childArchivesTypes.keySet().iterator(); iterator.hasNext();) {
				ArchivesType childArchivesType = (ArchivesType) childArchivesTypes.get(iterator.next());
				tree += "n"+childArchivesType.getID()+".checkBox.checked = true ;";
			     Map<Integer, ArchivesType> childArchivesTypes1 = childArchivesType.getChildArchivesTypes();
				 tree += getArchivesTypeCheckBoxTreeChild(childArchivesTypes1);			
			}
		}
		return tree;
	}
	/**
	 * ��ȡ�û�����������Ȩ��
	 * @param srcArchivestypes ���еĵ�������Ȩ��
	 * @param distArchivestypes �û��Ѿ���ȡ�ĵ�������Ȩ��
	 * @return
	 */
	public static String getUserRightArchivesTypeCheckBoxTree(LinkedHashMap<Integer,ArchivesType> srcArchivestypes,LinkedHashMap<Integer,ArchivesType> distArchivestypes){
		String tree = "";	
		for (ArchivesType archivesType : srcArchivestypes.values()) {	
			tree += "var n"+archivesType.getID()+" = tree.addChkNode(root,'"+archivesType.getName()+"','"+archivesType.getID()+"');";
			Map<Integer, ArchivesType> childArchivesTypes = archivesType.getChildArchivesTypes();
			tree += getArchivesTypeTreeCheckBoxChild(childArchivesTypes);
		}
		//��ѡ�ĵ�������Ȩ��
		for (ArchivesType archivesType : distArchivestypes.values()) {
			tree += "n"+archivesType.getID()+".checkBox.checked = true ;";
			Map<Integer,ArchivesType> childArchivesTypes2 = archivesType.getChildArchivesTypes();
			tree += getArchivesTypeCheckBoxTreeChild(childArchivesTypes2);
		}
		return tree;
	}
	//���� ��CheckBox���������� �޼��ӽڵ�
	public static String getUserRightArchivesTypeTreeCheckBoxChild(Map<Integer, ArchivesType> childArchivesTypes){
		String tree = "";
		if(childArchivesTypes!= null){
			
			for (Iterator<Integer> iterator = childArchivesTypes.keySet().iterator(); iterator.hasNext();) {
				ArchivesType childArchivesType = (ArchivesType) childArchivesTypes.get(iterator.next());
				tree+= "var n"+childArchivesType.getID()+" = tree.addChkNode(n"+childArchivesType.getParentID()+",'"+childArchivesType.getName()+"','"+childArchivesType.getID()+"');";
				//
			     Map<Integer, ArchivesType> childArchivesTypes1 = childArchivesType.getChildArchivesTypes();
				 tree += getArchivesTypeTreeCheckBoxChild(childArchivesTypes1);			
			}
		}		
		return tree;
	}
	
	
	/**
	 * ��ȡ�û�Ȩ��ϵͳ������
	 * @param srcSystemFeatures ���е��û�Ȩ��ϵͳ����Ȩ��
	 * @param distSystemFeatures �û��Ѿ���ȡ���û�Ȩ��ϵͳ����Ȩ��
	 * @return
	 */
	public static String getUserRightSystemFeatureCheckBoxTree(LinkedHashMap<String, SystemFeature> srcSystemFeatures,LinkedHashMap<String, SystemFeature> distSystemFeatures){
		String tree = "";	
		for (SystemFeature systemFeature : srcSystemFeatures.values()) {	
			tree += "var n"+systemFeature.getID()+" = tree.addChkNode(root,'"+systemFeature.getTitle()+"','"+systemFeature.getID()+"');";
			Map<String, SystemFeature> childSystemFeatures = systemFeature.getChildSystemFeatures();
			tree += getSystemFeatureTreeCheckBoxChild(childSystemFeatures);
		}
		//��ѡ��ϵͳ����Ȩ��
		for (SystemFeature systemFeature : distSystemFeatures.values()) {
			tree += "n"+systemFeature.getID()+".checkBox.checked = true ;";
			Map<String, SystemFeature> childSystemFeatures1 = systemFeature.getChildSystemFeatures();
			tree += getChildSystemFeatures(childSystemFeatures1);
		}
		return tree;
	}
	//���� ��CheckBox���������� �޼��ӽڵ�
	public static String getSystemFeatureTreeCheckBoxChild(Map<String, SystemFeature> childSystemFeatures){
		String tree = "";
		if(childSystemFeatures!= null){
			
			for (Iterator<String> iterator = childSystemFeatures.keySet().iterator(); iterator.hasNext();) {
				SystemFeature childSystemFeature = (SystemFeature) childSystemFeatures.get(iterator.next());
				tree+= "var n"+childSystemFeature.getID()+" = tree.addChkNode(n"+childSystemFeature.getParentID()+",'"+childSystemFeature.getTitle()+"','"+childSystemFeature.getID()+"');";
				Map<String, SystemFeature> childSystemFeatures1 = childSystemFeature.getChildSystemFeatures();
				tree += getSystemFeatureTreeCheckBoxChild(childSystemFeatures1);			
			}
		}		
		return tree;
	}
	
	//�����Ѿ�ѡ�����ϵͳ����
	private static String  getChildSystemFeatures(Map<String, SystemFeature> childSystemFeatures){
		String tree = "";
		if (childSystemFeatures != null) {
			for (SystemFeature systemFeature2 : childSystemFeatures.values()) {
				tree += "n"+systemFeature2.getID()+".checkBox.checked = true ;";
				Map<String, SystemFeature> childSystemFeatures1 = systemFeature2.getChildSystemFeatures();
				tree += getChildSystemFeatures(childSystemFeatures1);
			}
		}
		return tree;
	}
	
	
	/**
	 * ��ȡ�û���ɫ��ϵͳ������
	 * @param srcSystemFeatures ���е��û���ɫ��ϵͳ����Ȩ��
	 * @param pUserRolesSystemFeatures �û��Ѿ���ȡ���û���ɫ��ϵͳ����Ȩ��
	 * @return
	 */
	public static String getUserRolesSystemFeatureCheckBoxTree(LinkedHashMap<String, SystemFeature> srcSystemFeatures,List<UserRolesSystemFeature> pUserRolesSystemFeatures){
		String tree = "";	
		for (SystemFeature systemFeature : srcSystemFeatures.values()) {	
			tree += "var n"+systemFeature.getID()+" = tree.addChkNode(root,'"+systemFeature.getTitle()+"','"+systemFeature.getID()+"');";
			Map<String, SystemFeature> childSystemFeatures = systemFeature.getChildSystemFeatures();
			tree += getSystemFeatureTreeCheckBoxChild(childSystemFeatures);
		}
		
		for (UserRolesSystemFeature userRolesSystemFeature : pUserRolesSystemFeatures) {
			tree += "n"+userRolesSystemFeature.getFeatureID()+".checkBox.checked = true ;";
		}
		return tree;
	}
	
	/**
	 * ��ȡ�û���ɫ�ĵ�������Ȩ����
	 * @param srcArchivestypes ���еĵ�������Ȩ��
	 * @param pUserRolesArchivesTypes �û��Ѿ���ȡ���û���ɫ�ĵ�������Ȩ��Ȩ��
	 * @return
	 */
	public static String getUserRolesArchivesTypeCheckBoxTree(LinkedHashMap<Integer,ArchivesType> srcArchivestypes,List<UserRolesArchivesType> pUserRolesArchivesTypes){
		String tree = "";	
		for (ArchivesType archivesType : srcArchivestypes.values()) {	
			tree += "var n"+archivesType.getID()+" = tree.addChkNode(root,'"+archivesType.getName()+"','"+archivesType.getID()+"');";
			Map<Integer, ArchivesType> childArchivesTypes = archivesType.getChildArchivesTypes();
			tree += getArchivesTypeTreeCheckBoxChild(childArchivesTypes);
		}
		//��ѡ�ĵ�������Ȩ��
		for (UserRolesArchivesType userRolesArchivesType : pUserRolesArchivesTypes) {
			tree += "n"+userRolesArchivesType.getArchivesTypeID()+".checkBox.checked = true ;";
		}
		return tree;
	}
	
	/**
	 * �õ�ѧԺ��
	 * @param collegeNames
	 * @return
	 */
	public static String getCollegeTree(List<String> collegeNames){
		String tree = "var n1 = tree.add(root,'last','ѧԺ','1');";
		for (int i = 0;i < collegeNames.size(); i++) {
			tree += "var n1"+ i +" = tree.add(n1,'last','"+ collegeNames.get(i) +"','1"+ i +"');";
		}
		return tree;
	}

	/**
	 * ��ȡͳ�Ʊ�����
	 * @param collegeNames
	 * @return
	 */
	public static String getStatReportTree(List<StatReport> pStatReports){
		String tree = "";
		for (StatReport statReport : pStatReports) {
			if(statReport.getParentID() == 0){
				tree += "var n"+statReport.getID()+" = tree.add(root,'last','"+statReport.getReportName()+"','"+statReport.getID()+"');";
			}else{
				tree += "var n"+statReport.getID()+" = tree.add(n"+statReport.getParentID()+",'last','"+statReport.getReportName()+"','"+statReport.getID()+"');";
			}
		}
		return tree;
	}
	
	/**
	 * ��ȡ�ҵĹ����ռ���
	 * @param collegeNames
	 * @return
	 */
	public static String getSystemMessageTree(LinkedHashMap<String, SystemFeature>   systemFeatures){
		String tree = "var n1 = tree.add(root,'last','�ҵĹ����ռ�','1'); ";
		for (SystemFeature systemFeature : systemFeatures.values()) {
			tree += "var n"+systemFeature.getID()+" = tree.add(n1,'last','"+systemFeature.getName()+"','"+systemFeature.getID()+"');";
		}
		return tree;
	}
	
}
