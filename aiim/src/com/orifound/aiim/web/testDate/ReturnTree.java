package com.orifound.aiim.web.testDate;

public class ReturnTree {
	public static String getDeptTree(){
		String deptTree = "var n1 = tree.add(root,'last','����','1');" +
        "var n11 = tree.add(n1,'last','У��ί','11');" +
        "var n12 = tree.add(n1,'last','����','12');"+
        "var n13 = tree.add(n1,'last','������','13');" +     
        "var n14 = tree.add(n1,'last','����','14');";
		return deptTree;
	}
	
	public static String getArchivesTypeCommonTree(){
		String tree = "var n1 = tree.add(root,'last','�ڵ�1','1');" +
					        "var n11 = tree.add(n1,'last','�ڵ�11','11');" +
					        "var n111 = tree.add(n11,'last','�ڵ�111','111');" +
					        "var n112 = tree.add(n11,'last','�ڵ�112','112');" +
					        "var n113 = tree.add(n11,'last','�ڵ�113','113');" +
					        "var n114 = tree.add(n11,'last','�ڵ�114','114');" +
					   "var n12 = tree.add(n1,'last','�ڵ�12','12');" +
					        "var n121 = tree.add(n12,'last','�ڵ�121','121');" +
					        "var n122 = tree.add(n12,'last','�ڵ�122','122');" +
					        "var n123 = tree.add(n12,'last','�ڵ�123','123');" +
					        "var n124 = tree.add(n12,'last','�ڵ�124','124');" +
					   "var n13 = tree.add(n1,'last','�ڵ�13','13');" +
					        "var n131 = tree.add(n13,'last','�ڵ�131','131');" +
					        "var n132 = tree.add(n13,'last','�ڵ�132','132');" +
					        "var n133 = tree.add(n13,'last','�ڵ�133','133');" +
					        "var n134 = tree.add(n13,'last','�ڵ�134','134');" +
					   "var n14 = tree.add(n1,'last','�ڵ�14','14');" +
					        "var n141 = tree.add(n14,'last','�ڵ�141','141');" +
					        "var n142 = tree.add(n14,'last','�ڵ�142','142');" +
					        "var n143 = tree.add(n14,'last','�ڵ�143','143');" +
					        "var n144 = tree.add(n14,'last','�ڵ�144','144');" +
					"var n2 = tree.add(root,'last','�ڵ�2','2');" +
					   "var n21 = tree.add(n2,'last','�ڵ�21','21');" +
					        "var n211 = tree.add(n21,'last','�ڵ�211','211');" +
					        "var n212 = tree.add(n21,'last','�ڵ�212','212');" +
					        "var n213 = tree.add(n21,'last','�ڵ�213','213');" +
					        "var n214 = tree.add(n21,'last','�ڵ�214','214');" +
					   "var n22 = tree.add(n2,'last','�ڵ�22','22');" +
					        "var n221 = tree.add(n22,'last','�ڵ�221','221');" +
					        "var n222 = tree.add(n22,'last','�ڵ�222','222');" +
					        "var n223 = tree.add(n22,'last','�ڵ�223','223');" +
					        "var n224 = tree.add(n22,'last','�ڵ�224','224');" +
					   "var n23 = tree.add(n2,'last','�ڵ�23','23');" +
					        "var n231 = tree.add(n23,'last','�ڵ�231','231');" +
					        "var n232 = tree.add(n23,'last','�ڵ�232','232');" +
					        "var n233 = tree.add(n23,'last','�ڵ�233','233');" +
					        "var n234 = tree.add(n23,'last','�ڵ�234','234');" +
					   "var n24 = tree.add(n2,'last','�ڵ�24','24');" +
					        "var n241 = tree.add(n24,'last','�ڵ�241','241');" +
					        "var n242 = tree.add(n24,'last','�ڵ�242','242');" +
					        "var n243 = tree.add(n24,'last','�ڵ�243','243');" +
					        "var n244 = tree.add(n24,'last','�ڵ�244','244');";
		return tree;
	}
	
	public static String getArchivesTypeCheckBoxTree(){
		String tree = "var n1 = tree.addChkNode(root,'��Ӱ','');" +
							"var n11 = tree.addChkNode(n1,'����Ƭ','11');" +
							    "var n111 = tree.addChkNode(n11,'����Ƭ','111');" +
							"var n12 = tree.addChkNode(n1,'�ֲ�Ƭ','12');" +
							"var n13 = tree.addChkNode(n1,'����Ƭ','13');" +
							"var n14 = tree.addChkNode(n1,'������','14');" +
							"var n15 = tree.addChkNode(n1,'��ͨƬ','15');" +
					"var n2 = tree.addChkNode(root,'����ֱ��','2');" +
					"var n3 = tree.addChkNode(root,'�����̨','3');" +
					"var n4 = tree.addChkNode(root,'����','4');" +
							"var n41 = tree.addChkNode(n4,'��̨����','41');" +
							"var n42 = tree.addChkNode(n4,'��½����','42');" +
							"var n43 = tree.addChkNode(n4,'�պ�����','43');" +
							"var n44 = tree.addChkNode(n4,'ŷ������','44');";;
		return tree;
	}
	//�õ��Զ���������ѯ��
	public static String getConditionTree(){
		String tree = "var n1 = tree.add(root,'last','�Զ����������','1');" +
						   "var n11 = tree.add(n1,'last','���ĵ�Ⱥ�ĵ���','11');" +
						        "var n111 = tree.add(n11,'last','��Ⱥ-�ۺ�','111');" +
						        "var n112 = tree.add(n11,'last','��Ⱥ-�ͼ�','112');" +
						        "var n113 = tree.add(n11,'last','�ڵ�113','113');" +
						        "var n114 = tree.add(n11,'last','�ڵ�114','114');" +
						   "var n12 = tree.add(n1,'last','���ĵ�Ⱥ�ĵ���','12');" +
						        "var n121 = tree.add(n12,'last','�ڵ�121','121');" +
						        "var n122 = tree.add(n12,'last','�ڵ�122','122');" +
						        "var n123 = tree.add(n12,'last','�ڵ�123','123');" +
						        "var n124 = tree.add(n12,'last','�ڵ�124','124');" +
						   "var n13 = tree.add(n1,'last','���ĵ�Ⱥ�ĵ���','13');" +
						        "var n131 = tree.add(n13,'last','�ڵ�131','131');" +
						        "var n132 = tree.add(n13,'last','�ڵ�132','132');" +
						        "var n133 = tree.add(n13,'last','�ڵ�133','133');" +
						        "var n134 = tree.add(n13,'last','�ڵ�134','134');" ;
		return tree;
	}
	
	/**
	 * �õ�ҵ��ָ���ҵ��û�tree
	 * @return
	 */
    public static String getYWZDSUserTree(){
    	String YWZDSUserTree = "var n1 = tree.add(root,'last','����','1');" +
							        "var n11 = tree.add(n1,'last','����','11');" +
							        "var n12 = tree.add(n1,'last','����','12');"+
							        "var n13 = tree.add(n1,'last','����','13');" +     
							        "var n14 = tree.add(n1,'last','����','14');";
		return YWZDSUserTree;
    }
    
    /**
	 * �õ�ҵ��ָ���ҵ��û�tree
	 * @return
	 */
    public static String getWDTypeTree(){
    	String tree = "var n1 = tree.add(root,'last','����','1');" +
							        "var n11 = tree.add(n1,'last','����','11');" +
							        "var n12 = tree.add(n1,'last','����','12');"+
							        "var n13 = tree.add(n1,'last','����','13');" +     
							        "var n14 = tree.add(n1,'last','����','14');";
		return tree;
    }
    
    public static String getUserInfoTree(){
    	String tree ="var n1 = tree.add(root,'last','�˻�����','1');" +
							        "var n11 = tree.add(n1,'last','�˻���Ϣ','11');" +
							        "var n12 = tree.add(n1,'last','��ɫ��Ϣ','12');"+
							        "var n13 = tree.add(n1,'last','������Ϣ','13');"+
							        "var n13 = tree.add(n1,'last','�޸�����','14');";    
							     
    	return tree;
    }
}
