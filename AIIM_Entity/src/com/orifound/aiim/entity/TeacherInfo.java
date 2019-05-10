package com.orifound.aiim.entity;

import java.util.*;
    
/**
 * 教职工基本信息实体类
 */
public class TeacherInfo
{
    /**
     * 构造函数
     */
    public TeacherInfo()
    {
        
    }
    
    /**
	* 带字段参数的构造函数
	* @param nBXH 内部序号
	* @param saveDate 归档日期
	* @param workFlowStatus 档案管理工作流状态
	* @param moveOutDate 寄出日期
	* @param barcode 档案条码
	* @param archivesTypeID 档案分类编号
	* @param archivesID 档号
	* @param title 题名
	* @param xm 姓名
	* @param gzh 工资号
	* @param xb 性别
	* @param csrq 生出日期
	* @param jg 籍贯
	* @param mz 民族
	* @param sfzhm 身份证号码
	* @param dp 党派
	* @param lxqdw 来校前单位
	* @param cjgzsj 参加工作时间
	* @param lxgzsj 来校工作时间
	* @param jdsj 建档时间
	* @param ssxy 所属学院
	* @param ssyx 所属院系
	* @param zcxl 职称系列
	* @param zc 职称
	* @param pzcsj 评聘职称时间
	* @param zwlx 职务类型
	* @param zw 职务
	* @param tzsj 提职时间
	* @param whcd 文化程度
	* @param xw 学位
	* @param bysj 毕业时间
	* @param byyx 毕业院校
	* @param dalx 档案类型
	* @param cllx 处理类型
	* @param clsj 处理时间
	* @param bz 备注
	* @param oldXH 原序号
	*/
    public TeacherInfo(int nBXH,Date saveDate,int workFlowStatus,Date moveOutDate,String barcode,int archivesTypeID,String archivesID,String title,String xm,String gzh,String xb,String csrq,String jg,String mz,String sfzhm,String dp,String lxqdw,String cjgzsj,String lxgzsj,String jdsj,String ssxy,String ssyx,String zcxl,String zc,String pzcsj,String zwlx,String zw,String tzsj,String whcd,String xw,String bysj,String byyx,String dalx,String cllx,String clsj,String bz,long oldXH)
	{
		// Table Name: TeacherInfo
		// Columns List,Can Used in SELECT SQL: NBXH,SaveDate,WorkFlowStatus,MoveOutDate,Barcode,ArchivesTypeID,ArchivesID,Title,xm,gzh,xb,csrq,jg,mz,sfzhm,dp,lxqdw,cjgzsj,lxgzsj,jdsj,ssxy,ssyx,zcxl,zc,pzcsj,zwlx,zw,tzsj,whcd,xw,bysj,byyx,dalx,cllx,clsj,bz,oldXH
		// Columns List,Can Used in INSERT SQL: :NBXH,:SaveDate,:WorkFlowStatus,:MoveOutDate,:Barcode,:ArchivesTypeID,:ArchivesID,:Title,:xm,:gzh,:xb,:csrq,:jg,:mz,:sfzhm,:dp,:lxqdw,:cjgzsj,:lxgzsj,:jdsj,:ssxy,:ssyx,:zcxl,:zc,:pzcsj,:zwlx,:zw,:tzsj,:whcd,:xw,:bysj,:byyx,:dalx,:cllx,:clsj,:bz,:oldXH
		// Columns List,Can Used in UPDATE SQL: NBXH=:NBXH,SaveDate=:SaveDate,WorkFlowStatus=:WorkFlowStatus,MoveOutDate=:MoveOutDate,Barcode=:Barcode,ArchivesTypeID=:ArchivesTypeID,ArchivesID=:ArchivesID,Title=:Title,xm=:xm,gzh=:gzh,xb=:xb,csrq=:csrq,jg=:jg,mz=:mz,sfzhm=:sfzhm,dp=:dp,lxqdw=:lxqdw,cjgzsj=:cjgzsj,lxgzsj=:lxgzsj,jdsj=:jdsj,ssxy=:ssxy,ssyx=:ssyx,zcxl=:zcxl,zc=:zc,pzcsj=:pzcsj,zwlx=:zwlx,zw=:zw,tzsj=:tzsj,whcd=:whcd,xw=:xw,bysj=:bysj,byyx=:byyx,dalx=:dalx,cllx=:cllx,clsj=:clsj,bz=:bz,oldXH=:oldXH

		this.nBXH = nBXH;
		this.saveDate = saveDate;
		this.workFlowStatus = workFlowStatus;
		this.moveOutDate = moveOutDate;
		this.barcode = barcode;
		this.archivesTypeID = archivesTypeID;
		this.archivesID = archivesID;
		this.title = title;
		this.xm = xm;
		this.gzh = gzh;
		this.xb = xb;
		this.csrq = csrq;
		this.jg = jg;
		this.mz = mz;
		this.sfzhm = sfzhm;
		this.dp = dp;
		this.lxqdw = lxqdw;
		this.cjgzsj = cjgzsj;
		this.lxgzsj = lxgzsj;
		this.jdsj = jdsj;
		this.ssxy = ssxy;
		this.ssyx = ssyx;
		this.zcxl = zcxl;
		this.zc = zc;
		this.pzcsj = pzcsj;
		this.zwlx = zwlx;
		this.zw = zw;
		this.tzsj = tzsj;
		this.whcd = whcd;
		this.xw = xw;
		this.bysj = bysj;
		this.byyx = byyx;
		this.dalx = dalx;
		this.cllx = cllx;
		this.clsj = clsj;
		this.bz = bz;
		this.oldXH = oldXH;
	}

	/**
	 * 成员对象在集合中的关键字
	 */
	private Object keyInCol=null;

	/**
	 * 获取属性值：成员对象在集合中的关键字
	 * @return 成员对象在集合中的关键字
	 */
	public Object getKeyInCol()
	{
		return keyInCol;
	}

	/**
	 * 设置属性值：成员对象在集合中的关键字
	 * @param keyInCol 成员对象在集合中的关键字
	 */
	public void setKeyInCol(Object keyInCol)
	{
		this.keyInCol = keyInCol;
	}

	/**
	 * 该数据项的附加对象，可以用来保存一些附加信息
	 */
	private Object tag=null;

	/**
	 * 获取属性值：该数据项的附加对象，可以用来保存一些附加信息
	 * @return 该数据项的附加对象，可以用来保存一些附加信息
	 */
	public Object getTag()
	{
		return tag;
	}

	/**
	 * 设置属性值：该数据项的附加对象，可以用来保存一些附加信息
	 * @param tag 该数据项的附加对象，可以用来保存一些附加信息
	 */
	public void setTag(Object tag)
	{
		this.tag = tag;
	}

	/**
	 * 内部序号
	 */
	private int nBXH=0;

	/**
	 * 获取属性值：内部序号
	 * @return 内部序号
	 */
	public int getNBXH()
	{
		return nBXH;
	}

	/**
	 * 设置属性值：内部序号
	 * @param nBXH 内部序号
	 */
	public void setNBXH(int nBXH)
	{
		this.nBXH = nBXH;
	}

	/**
	 * 归档日期
	 */
	private Date saveDate;

	/**
	 * 获取属性值：归档日期
	 * @return 归档日期
	 */
	public Date getSaveDate()
	{
		return saveDate;
	}

	/**
	 * 设置属性值：归档日期
	 * @param saveDate 归档日期
	 */
	public void setSaveDate(Date saveDate)
	{
		this.saveDate = saveDate;
	}

	/**
	 * 档案管理工作流状态
	 */
	private int workFlowStatus=0;

	/**
	 * 获取属性值：档案管理工作流状态
	 * @return 档案管理工作流状态
	 */
	public int getWorkFlowStatus()
	{
		return workFlowStatus;
	}

	/**
	 * 设置属性值：档案管理工作流状态
	 * @param workFlowStatus 档案管理工作流状态
	 */
	public void setWorkFlowStatus(int workFlowStatus)
	{
		this.workFlowStatus = workFlowStatus;
	}

	/**
	 * 寄出日期
	 */
	private Date moveOutDate;

	/**
	 * 获取属性值：寄出日期
	 * @return 寄出日期
	 */
	public Date getMoveOutDate()
	{
		return moveOutDate;
	}

	/**
	 * 设置属性值：寄出日期
	 * @param moveOutDate 寄出日期
	 */
	public void setMoveOutDate(Date moveOutDate)
	{
		this.moveOutDate = moveOutDate;
	}
	
	/**
	 * 档案条码
	 */
	private String barcode=null;

	/**
	 * 获取属性值：档案条码
	 * @return 档案条码
	 */
	public String getBarcode()
	{
		return barcode;
	}

	/**
	 * 设置属性值：档案条码
	 * @param barcode 档案条码
	 */
	public void setBarcode(String barcode)
	{
		this.barcode = barcode;
	}

	/**
	 * 档案分类编号
	 */
	private int archivesTypeID=0;

	/**
	 * 获取属性值：档案分类编号
	 * @return 档案分类编号
	 */
	public int getArchivesTypeID()
	{
		return archivesTypeID;
	}

	/**
	 * 设置属性值：档案分类编号
	 * @param archivesTypeID 档案分类编号
	 */
	public void setArchivesTypeID(int archivesTypeID)
	{
		this.archivesTypeID = archivesTypeID;
	}

	/**
	 * 档号
	 */
	private String archivesID=null;

	/**
	 * 获取属性值：档号
	 * @return 档号
	 */
	public String getArchivesID()
	{
		if(this.archivesID == null){
			try{
				return this.importDataitemsMappings.get("gzh").getValue();
			}catch(Exception e){
				return archivesID;
			}
		}
		return archivesID;
	}

	/**
	 * 设置属性值：档号
	 * @param archivesID 档号
	 */
	public void setArchivesID(String archivesID)
	{
		this.archivesID = archivesID;
	}

	/**
	 * 题名
	 */
	private String title=null;

	/**
	 * 获取属性值：题名
	 * @return 题名
	 */
	public String getTitle()
	{
		if(this.title == null){
			try{
				return this.importDataitemsMappings.get("xm").getValue();
			}catch(Exception e){
				return title;
			}
		}
		return title;
	}

	/**
	 * 设置属性值：题名
	 * @param title 题名
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * 姓名
	 */
	private String xm=null;

	/**
	 * 获取属性值：姓名
	 * @return 姓名
	 */
	public String getXm()
	{
		if(this.xm == null){
			try{
				return this.importDataitemsMappings.get("xm").getValue();
			}catch(Exception e){
				return xm;
			}
		}
		return xm;
	}

	/**
	 * 设置属性值：姓名
	 * @param xm 姓名
	 */
	public void setXm(String xm)
	{
		this.xm = xm;
	}

	/**
	 * 工资号
	 */
	private String gzh=null;

	/**
	 * 获取属性值：工资号
	 * @return 工资号
	 */
	public String getGzh()
	{
		if(this.gzh == null){
			try{
				return this.importDataitemsMappings.get("gzh").getValue();
			}catch(Exception e){
				return gzh;
			}
		}
		return gzh;
	}

	/**
	 * 设置属性值：工资号
	 * @param gzh 工资号
	 */
	public void setGzh(String gzh)
	{
		this.gzh = gzh;
	}

	/**
	 * 性别
	 */
	private String xb = null;

	/**
	 * 获取属性值：性别
	 * @return 性别
	 */
	public String getXb()
	{
		if(this.xb == null){
			try{
				return this.importDataitemsMappings.get("xb").getValue();
			}catch(Exception e){
				return xb;
			}
		}
		return xb;
	}

	/**
	 * 设置属性值：性别
	 * @param xb 性别
	 */
	public void setXb(String xb)
	{
		this.xb = xb;
	}

	/**
	 * 生出日期
	 */
	private String csrq=null;

	/**
	 * 获取属性值：生出日期
	 * @return 生出日期
	 */
	public String getCsrq()
	{
		if(this.csrq == null){
			try{
				return this.importDataitemsMappings.get("csrq").getValue();
			}catch(Exception e){
				return csrq;
			}
		}
		return csrq;
	}

	/**
	 * 设置属性值：生出日期
	 * @param csrq 生出日期
	 */
	public void setCsrq(String csrq)
	{
		this.csrq = csrq;
	}

	/**
	 * 籍贯
	 */
	private String jg=null;

	/**
	 * 获取属性值：籍贯
	 * @return 籍贯
	 */
	public String getJg()
	{
		if(this.jg == null){
			try{
				return this.importDataitemsMappings.get("jg").getValue();
			}catch(Exception e){
				return jg;
			}
		}
		return jg;
	}

	/**
	 * 设置属性值：籍贯
	 * @param jg 籍贯
	 */
	public void setJg(String jg)
	{
		this.jg = jg;
	}

	/**
	 * 民族
	 */
	private String mz=null;

	/**
	 * 获取属性值：民族
	 * @return 民族
	 */
	public String getMz()
	{
		if(this.mz == null){
			try{
				return this.importDataitemsMappings.get("mz").getValue();
			}catch(Exception e){
				return mz;
			}
		}
		return mz;
	}

	/**
	 * 设置属性值：民族
	 * @param mz 民族
	 */
	public void setMz(String mz)
	{
		this.mz = mz;
	}

	/**
	 * 身份证号码
	 */
	private String sfzhm=null;

	/**
	 * 获取属性值：身份证号码
	 * @return 身份证号码
	 */
	public String getSfzhm()
	{
		if(this.sfzhm == null){
			try{
				return this.importDataitemsMappings.get("sfzhm").getValue();
			}catch(Exception e){
				return sfzhm;
			}
		}
		return sfzhm;
	}

	/**
	 * 设置属性值：身份证号码
	 * @param sfzhm 身份证号码
	 */
	public void setSfzhm(String sfzhm)
	{
		this.sfzhm = sfzhm;
	}

	/**
	 * 党派
	 */
	private String dp=null;

	/**
	 * 获取属性值：党派
	 * @return 党派
	 */
	public String getDp()
	{
		if(this.dp == null){
			try{
				return this.importDataitemsMappings.get("dp").getValue();
			}catch(Exception e){
				return dp;
			}
		}
		return dp;
	}

	/**
	 * 设置属性值：党派
	 * @param dp 党派
	 */
	public void setDp(String dp)
	{
		this.dp = dp;
	}

	/**
	 * 来校前单位
	 */
	private String lxqdw=null;

	/**
	 * 获取属性值：来校前单位
	 * @return 来校前单位
	 */
	public String getLxqdw()
	{
		if(this.lxqdw == null){
			try{
				return this.importDataitemsMappings.get("lxqdw").getValue();
			}catch(Exception e){
				return lxqdw;
			}
		}
		return lxqdw;
	}

	/**
	 * 设置属性值：来校前单位
	 * @param lxqdw 来校前单位
	 */
	public void setLxqdw(String lxqdw)
	{
		this.lxqdw = lxqdw;
	}

	/**
	 * 参加工作时间
	 */
	private String cjgzsj=null;

	/**
	 * 获取属性值：参加工作时间
	 * @return 参加工作时间
	 */
	public String getCjgzsj()
	{
		if(this.cjgzsj == null){
			try{
				return this.importDataitemsMappings.get("cjgzsj").getValue();
			}catch(Exception e){
				return cjgzsj;
			}
		}
		return cjgzsj;
	}

	/**
	 * 设置属性值：参加工作时间
	 * @param cjgzsj 参加工作时间
	 */
	public void setCjgzsj(String cjgzsj)
	{
		this.cjgzsj = cjgzsj;
	}

	/**
	 * 来校工作时间
	 */
	private String lxgzsj=null;

	/**
	 * 获取属性值：来校工作时间
	 * @return 来校工作时间
	 */
	public String getLxgzsj()
	{
		if(this.lxgzsj == null){
			try{
				return this.importDataitemsMappings.get("lxgzsj").getValue();
			}catch(Exception e){
				return lxgzsj;
			}
		}
		return lxgzsj;
	}

	/**
	 * 设置属性值：来校工作时间
	 * @param lxgzsj 来校工作时间
	 */
	public void setLxgzsj(String lxgzsj)
	{
		this.lxgzsj = lxgzsj;
	}

	/**
	 * 建档时间
	 */
	private String jdsj=null;

	/**
	 * 获取属性值：建档时间
	 * @return 建档时间
	 */
	public String getJdsj()
	{
		if(this.jdsj == null){
			try{
				return this.importDataitemsMappings.get("jdsj").getValue();
			}catch(Exception e){
				return jdsj;
			}
		}
		return jdsj;
	}

	/**
	 * 设置属性值：建档时间
	 * @param jdsj 建档时间
	 */
	public void setJdsj(String jdsj)
	{
		this.jdsj = jdsj;
	}

	/**
	 * 所属学院
	 */
	private String ssxy=null;

	/**
	 * 获取属性值：所属学院
	 * @return 所属学院
	 */
	public String getSsxy()
	{
		if(this.ssxy == null){
			try{
				return this.importDataitemsMappings.get("ssxy").getValue();
			}catch(Exception e){
				return ssxy;
			}
		}
		return ssxy;
	}

	/**
	 * 设置属性值：所属学院
	 * @param ssxy 所属学院
	 */
	public void setSsxy(String ssxy)
	{
		this.ssxy = ssxy;
	}

	/**
	 * 所属院系
	 */
	private String ssyx=null;

	/**
	 * 获取属性值：所属院系
	 * @return 所属院系
	 */
	public String getSsyx()
	{
		if(this.ssyx == null){
			try{
				return this.importDataitemsMappings.get("ssyx").getValue();
			}catch(Exception e){
				return ssyx;
			}
		}
		return ssyx;
	}

	/**
	 * 设置属性值：所属院系
	 * @param ssyx 所属院系
	 */
	public void setSsyx(String ssyx)
	{
		this.ssyx = ssyx;
	}

	/**
	 * 职称系列
	 */
	private String zcxl=null;

	/**
	 * 获取属性值：职称系列
	 * @return 职称系列
	 */
	public String getZcxl()
	{
		if(this.zcxl == null){
			try{
				return this.importDataitemsMappings.get("zcxl").getValue();
			}catch(Exception e){
				return zcxl;
			}
		}
		return zcxl;
	}

	/**
	 * 设置属性值：职称系列
	 * @param zcxl 职称系列
	 */
	public void setZcxl(String zcxl)
	{
		this.zcxl = zcxl;
	}

	/**
	 * 职称
	 */
	private String zc=null;

	/**
	 * 获取属性值：职称
	 * @return 职称
	 */
	public String getZc()
	{
		if(this.zc == null){
			try{
				return this.importDataitemsMappings.get("zc").getValue();
			}catch(Exception e){
				return zc;
			}
		}
		return zc;
	}

	/**
	 * 设置属性值：职称
	 * @param zc 职称
	 */
	public void setZc(String zc)
	{
		this.zc = zc;
	}

	/**
	 * 评聘职称时间
	 */
	private String pzcsj=null;

	/**
	 * 获取属性值：评聘职称时间
	 * @return 评聘职称时间
	 */
	public String getPzcsj()
	{
		if(this.pzcsj == null){
			try{
				return this.importDataitemsMappings.get("pzcsj").getValue();
			}catch(Exception e){
				return pzcsj;
			}
		}
		return pzcsj;
	}

	/**
	 * 设置属性值：评聘职称时间
	 * @param pzcsj 评聘职称时间
	 */
	public void setPzcsj(String pzcsj)
	{
		this.pzcsj = pzcsj;
	}

	/**
	 * 职务类型
	 */
	private String zwlx=null;

	/**
	 * 获取属性值：职务类型
	 * @return 职务类型
	 */
	public String getZwlx()
	{
		if(this.zwlx == null){
			try{
				return this.importDataitemsMappings.get("zwlx").getValue();
			}catch(Exception e){
				return zwlx;
			}
		}
		return zwlx;
	}

	/**
	 * 设置属性值：职务类型
	 * @param zwlx 职务类型
	 */
	public void setZwlx(String zwlx)
	{
		this.zwlx = zwlx;
	}

	/**
	 * 职务
	 */
	private String zw=null;

	/**
	 * 获取属性值：职务
	 * @return 职务
	 */
	public String getZw()
	{
		if(this.zw == null){
			try{
				return this.importDataitemsMappings.get("zw").getValue();
			}catch(Exception e){
				return zw;
			}
		}
		return zw;
	}

	/**
	 * 设置属性值：职务
	 * @param zw 职务
	 */
	public void setZw(String zw)
	{
		this.zw = zw;
	}

	/**
	 * 提职时间
	 */
	private String tzsj=null;

	/**
	 * 获取属性值：提职时间
	 * @return 提职时间
	 */
	public String getTzsj()
	{
		if(this.tzsj == null){
			try{
				return this.importDataitemsMappings.get("tzsj").getValue();
			}catch(Exception e){
				return tzsj;
			}
		}
		return tzsj;
	}

	/**
	 * 设置属性值：提职时间
	 * @param tzsj 提职时间
	 */
	public void setTzsj(String tzsj)
	{
		this.tzsj = tzsj;
	}

	/**
	 * 文化程度
	 */
	private String whcd=null;

	/**
	 * 获取属性值：文化程度
	 * @return 文化程度
	 */
	public String getWhcd()
	{
		if(this.whcd == null){
			try{
				return this.importDataitemsMappings.get("whcd").getValue();
			}catch(Exception e){
				return whcd;
			}
		}
		return whcd;
	}

	/**
	 * 设置属性值：文化程度
	 * @param whcd 文化程度
	 */
	public void setWhcd(String whcd)
	{
		this.whcd = whcd;
	}

	/**
	 * 学位
	 */
	private String xw=null;

	/**
	 * 获取属性值：学位
	 * @return 学位
	 */
	public String getXw()
	{
		if(this.xw == null){
			try{
				return this.importDataitemsMappings.get("xw").getValue();
			}catch(Exception e){
				return xw;
			}
		}
		return xw;
	}

	/**
	 * 设置属性值：学位
	 * @param xw 学位
	 */
	public void setXw(String xw)
	{
		this.xw = xw;
	}

	/**
	 * 毕业时间
	 */
	private String bysj=null;

	/**
	 * 获取属性值：毕业时间
	 * @return 毕业时间
	 */
	public String getBysj()
	{
		if(this.bysj == null){
			try{
				return this.importDataitemsMappings.get("bysj").getValue();
			}catch(Exception e){
				return bysj;
			}
		}
		return bysj;
	}

	/**
	 * 设置属性值：毕业时间
	 * @param bysj 毕业时间
	 */
	public void setBysj(String bysj)
	{
		this.bysj = bysj;
	}

	/**
	 * 毕业院校
	 */
	private String byyx=null;

	/**
	 * 获取属性值：毕业院校
	 * @return 毕业院校
	 */
	public String getByyx()
	{
		if(this.byyx == null){
			try{
				return this.importDataitemsMappings.get("byyx").getValue();
			}catch(Exception e){
				return byyx;
			}
		}
		return byyx;
	}

	/**
	 * 设置属性值：毕业院校
	 * @param byyx 毕业院校
	 */
	public void setByyx(String byyx)
	{
		this.byyx = byyx;
	}

	/**
	 * 档案类型
	 */
	private String dalx=null;

	/**
	 * 获取属性值：档案类型
	 * @return 档案类型
	 */
	public String getDalx()
	{
		if(this.dalx == null){
			try{
				return this.importDataitemsMappings.get("dalx").getValue();
			}catch(Exception e){
				return dalx;
			}
		}
		return dalx;
	}

	/**
	 * 设置属性值：档案类型
	 * @param dalx 档案类型
	 */
	public void setDalx(String dalx)
	{
		this.dalx = dalx;
	}
	
	/**
	 * 处理类型
	 */
	private String cllx=null;

	/**
	 * 获取属性值：处理类型
	 * @return 处理类型
	 */
	public String getCllx()
	{
		if(this.cllx == null){
			try{
				return this.importDataitemsMappings.get("cllx").getValue();
			}catch(Exception e){
				return cllx;
			}
		}
		return cllx;
	}

	/**
	 * 设置属性值：处理类型
	 * @param cllx 处理类型
	 */
	public void setCllx(String cllx)
	{
		this.cllx = cllx;
	}

	/**
	 * 处理时间
	 */
	private String clsj=null;

	/**
	 * 获取属性值：处理时间
	 * @return 处理时间
	 */
	public String getClsj()
	{
		if(this.clsj == null){
			try{
				return this.importDataitemsMappings.get("clsj").getValue();
			}catch(Exception e){
				return clsj;
			}
		}
		return clsj;
	}

	/**
	 * 设置属性值：处理时间
	 * @param clsj 处理时间
	 */
	public void setClsj(String clsj)
	{
		this.clsj = clsj;
	}

	/**
	 * 备注
	 */
	private String bz=null;

	/**
	 * 获取属性值：备注
	 * @return 备注
	 */
	public String getBz()
	{
		if(this.bz == null){
			try{
				return this.importDataitemsMappings.get("bz").getValue();
			}catch(Exception e){
				return bz;
			}
		}
		return bz;
	}

	/**
	 * 设置属性值：备注
	 * @param bz 备注
	 */
	public void setBz(String bz)
	{
		this.bz = bz;
	}

	/**
	 * 原序号
	 */
	private long oldXH;

	/**
	 * 获取属性值：原序号
	 * @return 原序号
	 */
	public long getOldXH()
	{
		return oldXH;
	}

	/**
	 * 设置属性值：原序号
	 * @param oldXH 原序号
	 */
	public void setOldXH(long oldXH)
	{
		this.oldXH = oldXH;
	}
	
	/**
	 * Excel和数据库的映射关系对象集合
	 */
	private Map<String, ImportDataitemsMapping> importDataitemsMappings = new HashMap<String, ImportDataitemsMapping>();
	
	/**
	 * Excel和数据库的映射关系对象集合
	 */
	public Map<String, ImportDataitemsMapping> getImportDataitemsMappings() {
		return importDataitemsMappings;
	}

	/**
	 * Excel和数据库的映射关系对象集合
	 */
	public void setImportDataitemsMappings(Map<String, ImportDataitemsMapping> importDataitemsMappings) {
		this.importDataitemsMappings = importDataitemsMappings;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public TeacherInfo clone()
	{
		TeacherInfo item = new TeacherInfo(nBXH,saveDate,workFlowStatus,moveOutDate,barcode,archivesTypeID,archivesID,title,xm,gzh,xb,csrq,jg,mz,sfzhm,dp,lxqdw,cjgzsj,lxgzsj,jdsj,ssxy,ssyx,zcxl,zc,pzcsj,zwlx,zw,tzsj,whcd,xw,bysj,byyx,dalx,cllx,clsj,bz,oldXH);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param teacherInfo 指定的对象源
	*/
	public void cloneFrom(TeacherInfo teacherInfo)
	{
		this.nBXH = teacherInfo.getNBXH();
		this.saveDate = teacherInfo.getSaveDate();
		this.workFlowStatus = teacherInfo.getWorkFlowStatus();
		this.moveOutDate = teacherInfo.getMoveOutDate();
		this.barcode = teacherInfo.getBarcode();
		this.archivesTypeID = teacherInfo.getArchivesTypeID();
		this.archivesID = teacherInfo.getArchivesID();
		this.title = teacherInfo.getTitle();
		this.xm = teacherInfo.getXm();
		this.gzh = teacherInfo.getGzh();
		this.xb = teacherInfo.getXb();
		this.csrq = teacherInfo.getCsrq();
		this.jg = teacherInfo.getJg();
		this.mz = teacherInfo.getMz();
		this.sfzhm = teacherInfo.getSfzhm();
		this.dp = teacherInfo.getDp();
		this.lxqdw = teacherInfo.getLxqdw();
		this.cjgzsj = teacherInfo.getCjgzsj();
		this.lxgzsj = teacherInfo.getLxgzsj();
		this.jdsj = teacherInfo.getJdsj();
		this.ssxy = teacherInfo.getSsxy();
		this.ssyx = teacherInfo.getSsyx();
		this.zcxl = teacherInfo.getZcxl();
		this.zc = teacherInfo.getZc();
		this.pzcsj = teacherInfo.getPzcsj();
		this.zwlx = teacherInfo.getZwlx();
		this.zw = teacherInfo.getZw();
		this.tzsj = teacherInfo.getTzsj();
		this.whcd = teacherInfo.getWhcd();
		this.xw = teacherInfo.getXw();
		this.bysj = teacherInfo.getBysj();
		this.byyx = teacherInfo.getByyx();
		this.dalx = teacherInfo.getDalx();
		this.cllx = teacherInfo.getCllx();
		this.clsj = teacherInfo.getClsj();
		this.bz = teacherInfo.getBz();
		this.oldXH = teacherInfo.getOldXH();
		this.keyInCol = teacherInfo.getKeyInCol();
		this.tag = teacherInfo.getTag();
	}
}



