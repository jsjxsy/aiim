package com.orifound.aiim.web.testDate;

public class ReturnTree {
	public static String getDeptTree(){
		String deptTree = "var n1 = tree.add(root,'last','所有','1');" +
        "var n11 = tree.add(n1,'last','校党委','11');" +
        "var n12 = tree.add(n1,'last','财务部','12');"+
        "var n13 = tree.add(n1,'last','安保部','13');" +     
        "var n14 = tree.add(n1,'last','教务处','14');";
		return deptTree;
	}
	
	public static String getArchivesTypeCommonTree(){
		String tree = "var n1 = tree.add(root,'last','节点1','1');" +
					        "var n11 = tree.add(n1,'last','节点11','11');" +
					        "var n111 = tree.add(n11,'last','节点111','111');" +
					        "var n112 = tree.add(n11,'last','节点112','112');" +
					        "var n113 = tree.add(n11,'last','节点113','113');" +
					        "var n114 = tree.add(n11,'last','节点114','114');" +
					   "var n12 = tree.add(n1,'last','节点12','12');" +
					        "var n121 = tree.add(n12,'last','节点121','121');" +
					        "var n122 = tree.add(n12,'last','节点122','122');" +
					        "var n123 = tree.add(n12,'last','节点123','123');" +
					        "var n124 = tree.add(n12,'last','节点124','124');" +
					   "var n13 = tree.add(n1,'last','节点13','13');" +
					        "var n131 = tree.add(n13,'last','节点131','131');" +
					        "var n132 = tree.add(n13,'last','节点132','132');" +
					        "var n133 = tree.add(n13,'last','节点133','133');" +
					        "var n134 = tree.add(n13,'last','节点134','134');" +
					   "var n14 = tree.add(n1,'last','节点14','14');" +
					        "var n141 = tree.add(n14,'last','节点141','141');" +
					        "var n142 = tree.add(n14,'last','节点142','142');" +
					        "var n143 = tree.add(n14,'last','节点143','143');" +
					        "var n144 = tree.add(n14,'last','节点144','144');" +
					"var n2 = tree.add(root,'last','节点2','2');" +
					   "var n21 = tree.add(n2,'last','节点21','21');" +
					        "var n211 = tree.add(n21,'last','节点211','211');" +
					        "var n212 = tree.add(n21,'last','节点212','212');" +
					        "var n213 = tree.add(n21,'last','节点213','213');" +
					        "var n214 = tree.add(n21,'last','节点214','214');" +
					   "var n22 = tree.add(n2,'last','节点22','22');" +
					        "var n221 = tree.add(n22,'last','节点221','221');" +
					        "var n222 = tree.add(n22,'last','节点222','222');" +
					        "var n223 = tree.add(n22,'last','节点223','223');" +
					        "var n224 = tree.add(n22,'last','节点224','224');" +
					   "var n23 = tree.add(n2,'last','节点23','23');" +
					        "var n231 = tree.add(n23,'last','节点231','231');" +
					        "var n232 = tree.add(n23,'last','节点232','232');" +
					        "var n233 = tree.add(n23,'last','节点233','233');" +
					        "var n234 = tree.add(n23,'last','节点234','234');" +
					   "var n24 = tree.add(n2,'last','节点24','24');" +
					        "var n241 = tree.add(n24,'last','节点241','241');" +
					        "var n242 = tree.add(n24,'last','节点242','242');" +
					        "var n243 = tree.add(n24,'last','节点243','243');" +
					        "var n244 = tree.add(n24,'last','节点244','244');";
		return tree;
	}
	
	public static String getArchivesTypeCheckBoxTree(){
		String tree = "var n1 = tree.addChkNode(root,'电影','');" +
							"var n11 = tree.addChkNode(n1,'剧情片','11');" +
							    "var n111 = tree.addChkNode(n11,'剧情片','111');" +
							"var n12 = tree.addChkNode(n1,'恐怖片','12');" +
							"var n13 = tree.addChkNode(n1,'动作片','13');" +
							"var n14 = tree.addChkNode(n1,'连续剧','14');" +
							"var n15 = tree.addChkNode(n1,'卡通片','15');" +
					"var n2 = tree.addChkNode(root,'电视直播','2');" +
					"var n3 = tree.addChkNode(root,'网络电台','3');" +
					"var n4 = tree.addChkNode(root,'歌曲','4');" +
							"var n41 = tree.addChkNode(n4,'港台流行','41');" +
							"var n42 = tree.addChkNode(n4,'大陆经典','42');" +
							"var n43 = tree.addChkNode(n4,'日韩歌曲','43');" +
							"var n44 = tree.addChkNode(n4,'欧美音乐','44');";;
		return tree;
	}
	//得到自定义条件查询树
	public static String getConditionTree(){
		String tree = "var n1 = tree.add(root,'last','自定义检索条件','1');" +
						   "var n11 = tree.add(n1,'last','李四党群的档案','11');" +
						        "var n111 = tree.add(n11,'last','党群-综合','111');" +
						        "var n112 = tree.add(n11,'last','党群-纪检','112');" +
						        "var n113 = tree.add(n11,'last','节点113','113');" +
						        "var n114 = tree.add(n11,'last','节点114','114');" +
						   "var n12 = tree.add(n1,'last','李四党群的档案','12');" +
						        "var n121 = tree.add(n12,'last','节点121','121');" +
						        "var n122 = tree.add(n12,'last','节点122','122');" +
						        "var n123 = tree.add(n12,'last','节点123','123');" +
						        "var n124 = tree.add(n12,'last','节点124','124');" +
						   "var n13 = tree.add(n1,'last','李四党群的档案','13');" +
						        "var n131 = tree.add(n13,'last','节点131','131');" +
						        "var n132 = tree.add(n13,'last','节点132','132');" +
						        "var n133 = tree.add(n13,'last','节点133','133');" +
						        "var n134 = tree.add(n13,'last','节点134','134');" ;
		return tree;
	}
	
	/**
	 * 得到业务指导室的用户tree
	 * @return
	 */
    public static String getYWZDSUserTree(){
    	String YWZDSUserTree = "var n1 = tree.add(root,'last','所有','1');" +
							        "var n11 = tree.add(n1,'last','张三','11');" +
							        "var n12 = tree.add(n1,'last','李四','12');"+
							        "var n13 = tree.add(n1,'last','王五','13');" +     
							        "var n14 = tree.add(n1,'last','赵六','14');";
		return YWZDSUserTree;
    }
    
    /**
	 * 得到业务指导室的用户tree
	 * @return
	 */
    public static String getWDTypeTree(){
    	String tree = "var n1 = tree.add(root,'last','所有','1');" +
							        "var n11 = tree.add(n1,'last','张三','11');" +
							        "var n12 = tree.add(n1,'last','李四','12');"+
							        "var n13 = tree.add(n1,'last','王五','13');" +     
							        "var n14 = tree.add(n1,'last','赵六','14');";
		return tree;
    }
    
    public static String getUserInfoTree(){
    	String tree ="var n1 = tree.add(root,'last','账户管理','1');" +
							        "var n11 = tree.add(n1,'last','账户信息','11');" +
							        "var n12 = tree.add(n1,'last','角色信息','12');"+
							        "var n13 = tree.add(n1,'last','代理信息','13');"+
							        "var n13 = tree.add(n1,'last','修改密码','14');";    
							     
    	return tree;
    }
}
