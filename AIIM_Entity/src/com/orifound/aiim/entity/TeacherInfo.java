package com.orifound.aiim.entity;

import java.util.*;
    
/**
 * ��ְ��������Ϣʵ����
 */
public class TeacherInfo
{
    /**
     * ���캯��
     */
    public TeacherInfo()
    {
        
    }
    
    /**
	* ���ֶβ����Ĺ��캯��
	* @param nBXH �ڲ����
	* @param saveDate �鵵����
	* @param workFlowStatus ������������״̬
	* @param moveOutDate �ĳ�����
	* @param barcode ��������
	* @param archivesTypeID ����������
	* @param archivesID ����
	* @param title ����
	* @param xm ����
	* @param gzh ���ʺ�
	* @param xb �Ա�
	* @param csrq ��������
	* @param jg ����
	* @param mz ����
	* @param sfzhm ���֤����
	* @param dp ����
	* @param lxqdw ��Уǰ��λ
	* @param cjgzsj �μӹ���ʱ��
	* @param lxgzsj ��У����ʱ��
	* @param jdsj ����ʱ��
	* @param ssxy ����ѧԺ
	* @param ssyx ����Ժϵ
	* @param zcxl ְ��ϵ��
	* @param zc ְ��
	* @param pzcsj ��Ƹְ��ʱ��
	* @param zwlx ְ������
	* @param zw ְ��
	* @param tzsj ��ְʱ��
	* @param whcd �Ļ��̶�
	* @param xw ѧλ
	* @param bysj ��ҵʱ��
	* @param byyx ��ҵԺУ
	* @param dalx ��������
	* @param cllx ��������
	* @param clsj ����ʱ��
	* @param bz ��ע
	* @param oldXH ԭ���
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
	 * ��Ա�����ڼ����еĹؼ���
	 */
	private Object keyInCol=null;

	/**
	 * ��ȡ����ֵ����Ա�����ڼ����еĹؼ���
	 * @return ��Ա�����ڼ����еĹؼ���
	 */
	public Object getKeyInCol()
	{
		return keyInCol;
	}

	/**
	 * ��������ֵ����Ա�����ڼ����еĹؼ���
	 * @param keyInCol ��Ա�����ڼ����еĹؼ���
	 */
	public void setKeyInCol(Object keyInCol)
	{
		this.keyInCol = keyInCol;
	}

	/**
	 * ��������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 */
	private Object tag=null;

	/**
	 * ��ȡ����ֵ����������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 * @return ��������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 */
	public Object getTag()
	{
		return tag;
	}

	/**
	 * ��������ֵ����������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 * @param tag ��������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 */
	public void setTag(Object tag)
	{
		this.tag = tag;
	}

	/**
	 * �ڲ����
	 */
	private int nBXH=0;

	/**
	 * ��ȡ����ֵ���ڲ����
	 * @return �ڲ����
	 */
	public int getNBXH()
	{
		return nBXH;
	}

	/**
	 * ��������ֵ���ڲ����
	 * @param nBXH �ڲ����
	 */
	public void setNBXH(int nBXH)
	{
		this.nBXH = nBXH;
	}

	/**
	 * �鵵����
	 */
	private Date saveDate;

	/**
	 * ��ȡ����ֵ���鵵����
	 * @return �鵵����
	 */
	public Date getSaveDate()
	{
		return saveDate;
	}

	/**
	 * ��������ֵ���鵵����
	 * @param saveDate �鵵����
	 */
	public void setSaveDate(Date saveDate)
	{
		this.saveDate = saveDate;
	}

	/**
	 * ������������״̬
	 */
	private int workFlowStatus=0;

	/**
	 * ��ȡ����ֵ��������������״̬
	 * @return ������������״̬
	 */
	public int getWorkFlowStatus()
	{
		return workFlowStatus;
	}

	/**
	 * ��������ֵ��������������״̬
	 * @param workFlowStatus ������������״̬
	 */
	public void setWorkFlowStatus(int workFlowStatus)
	{
		this.workFlowStatus = workFlowStatus;
	}

	/**
	 * �ĳ�����
	 */
	private Date moveOutDate;

	/**
	 * ��ȡ����ֵ���ĳ�����
	 * @return �ĳ�����
	 */
	public Date getMoveOutDate()
	{
		return moveOutDate;
	}

	/**
	 * ��������ֵ���ĳ�����
	 * @param moveOutDate �ĳ�����
	 */
	public void setMoveOutDate(Date moveOutDate)
	{
		this.moveOutDate = moveOutDate;
	}
	
	/**
	 * ��������
	 */
	private String barcode=null;

	/**
	 * ��ȡ����ֵ����������
	 * @return ��������
	 */
	public String getBarcode()
	{
		return barcode;
	}

	/**
	 * ��������ֵ����������
	 * @param barcode ��������
	 */
	public void setBarcode(String barcode)
	{
		this.barcode = barcode;
	}

	/**
	 * ����������
	 */
	private int archivesTypeID=0;

	/**
	 * ��ȡ����ֵ������������
	 * @return ����������
	 */
	public int getArchivesTypeID()
	{
		return archivesTypeID;
	}

	/**
	 * ��������ֵ������������
	 * @param archivesTypeID ����������
	 */
	public void setArchivesTypeID(int archivesTypeID)
	{
		this.archivesTypeID = archivesTypeID;
	}

	/**
	 * ����
	 */
	private String archivesID=null;

	/**
	 * ��ȡ����ֵ������
	 * @return ����
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
	 * ��������ֵ������
	 * @param archivesID ����
	 */
	public void setArchivesID(String archivesID)
	{
		this.archivesID = archivesID;
	}

	/**
	 * ����
	 */
	private String title=null;

	/**
	 * ��ȡ����ֵ������
	 * @return ����
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
	 * ��������ֵ������
	 * @param title ����
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * ����
	 */
	private String xm=null;

	/**
	 * ��ȡ����ֵ������
	 * @return ����
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
	 * ��������ֵ������
	 * @param xm ����
	 */
	public void setXm(String xm)
	{
		this.xm = xm;
	}

	/**
	 * ���ʺ�
	 */
	private String gzh=null;

	/**
	 * ��ȡ����ֵ�����ʺ�
	 * @return ���ʺ�
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
	 * ��������ֵ�����ʺ�
	 * @param gzh ���ʺ�
	 */
	public void setGzh(String gzh)
	{
		this.gzh = gzh;
	}

	/**
	 * �Ա�
	 */
	private String xb = null;

	/**
	 * ��ȡ����ֵ���Ա�
	 * @return �Ա�
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
	 * ��������ֵ���Ա�
	 * @param xb �Ա�
	 */
	public void setXb(String xb)
	{
		this.xb = xb;
	}

	/**
	 * ��������
	 */
	private String csrq=null;

	/**
	 * ��ȡ����ֵ����������
	 * @return ��������
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
	 * ��������ֵ����������
	 * @param csrq ��������
	 */
	public void setCsrq(String csrq)
	{
		this.csrq = csrq;
	}

	/**
	 * ����
	 */
	private String jg=null;

	/**
	 * ��ȡ����ֵ������
	 * @return ����
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
	 * ��������ֵ������
	 * @param jg ����
	 */
	public void setJg(String jg)
	{
		this.jg = jg;
	}

	/**
	 * ����
	 */
	private String mz=null;

	/**
	 * ��ȡ����ֵ������
	 * @return ����
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
	 * ��������ֵ������
	 * @param mz ����
	 */
	public void setMz(String mz)
	{
		this.mz = mz;
	}

	/**
	 * ���֤����
	 */
	private String sfzhm=null;

	/**
	 * ��ȡ����ֵ�����֤����
	 * @return ���֤����
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
	 * ��������ֵ�����֤����
	 * @param sfzhm ���֤����
	 */
	public void setSfzhm(String sfzhm)
	{
		this.sfzhm = sfzhm;
	}

	/**
	 * ����
	 */
	private String dp=null;

	/**
	 * ��ȡ����ֵ������
	 * @return ����
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
	 * ��������ֵ������
	 * @param dp ����
	 */
	public void setDp(String dp)
	{
		this.dp = dp;
	}

	/**
	 * ��Уǰ��λ
	 */
	private String lxqdw=null;

	/**
	 * ��ȡ����ֵ����Уǰ��λ
	 * @return ��Уǰ��λ
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
	 * ��������ֵ����Уǰ��λ
	 * @param lxqdw ��Уǰ��λ
	 */
	public void setLxqdw(String lxqdw)
	{
		this.lxqdw = lxqdw;
	}

	/**
	 * �μӹ���ʱ��
	 */
	private String cjgzsj=null;

	/**
	 * ��ȡ����ֵ���μӹ���ʱ��
	 * @return �μӹ���ʱ��
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
	 * ��������ֵ���μӹ���ʱ��
	 * @param cjgzsj �μӹ���ʱ��
	 */
	public void setCjgzsj(String cjgzsj)
	{
		this.cjgzsj = cjgzsj;
	}

	/**
	 * ��У����ʱ��
	 */
	private String lxgzsj=null;

	/**
	 * ��ȡ����ֵ����У����ʱ��
	 * @return ��У����ʱ��
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
	 * ��������ֵ����У����ʱ��
	 * @param lxgzsj ��У����ʱ��
	 */
	public void setLxgzsj(String lxgzsj)
	{
		this.lxgzsj = lxgzsj;
	}

	/**
	 * ����ʱ��
	 */
	private String jdsj=null;

	/**
	 * ��ȡ����ֵ������ʱ��
	 * @return ����ʱ��
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
	 * ��������ֵ������ʱ��
	 * @param jdsj ����ʱ��
	 */
	public void setJdsj(String jdsj)
	{
		this.jdsj = jdsj;
	}

	/**
	 * ����ѧԺ
	 */
	private String ssxy=null;

	/**
	 * ��ȡ����ֵ������ѧԺ
	 * @return ����ѧԺ
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
	 * ��������ֵ������ѧԺ
	 * @param ssxy ����ѧԺ
	 */
	public void setSsxy(String ssxy)
	{
		this.ssxy = ssxy;
	}

	/**
	 * ����Ժϵ
	 */
	private String ssyx=null;

	/**
	 * ��ȡ����ֵ������Ժϵ
	 * @return ����Ժϵ
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
	 * ��������ֵ������Ժϵ
	 * @param ssyx ����Ժϵ
	 */
	public void setSsyx(String ssyx)
	{
		this.ssyx = ssyx;
	}

	/**
	 * ְ��ϵ��
	 */
	private String zcxl=null;

	/**
	 * ��ȡ����ֵ��ְ��ϵ��
	 * @return ְ��ϵ��
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
	 * ��������ֵ��ְ��ϵ��
	 * @param zcxl ְ��ϵ��
	 */
	public void setZcxl(String zcxl)
	{
		this.zcxl = zcxl;
	}

	/**
	 * ְ��
	 */
	private String zc=null;

	/**
	 * ��ȡ����ֵ��ְ��
	 * @return ְ��
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
	 * ��������ֵ��ְ��
	 * @param zc ְ��
	 */
	public void setZc(String zc)
	{
		this.zc = zc;
	}

	/**
	 * ��Ƹְ��ʱ��
	 */
	private String pzcsj=null;

	/**
	 * ��ȡ����ֵ����Ƹְ��ʱ��
	 * @return ��Ƹְ��ʱ��
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
	 * ��������ֵ����Ƹְ��ʱ��
	 * @param pzcsj ��Ƹְ��ʱ��
	 */
	public void setPzcsj(String pzcsj)
	{
		this.pzcsj = pzcsj;
	}

	/**
	 * ְ������
	 */
	private String zwlx=null;

	/**
	 * ��ȡ����ֵ��ְ������
	 * @return ְ������
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
	 * ��������ֵ��ְ������
	 * @param zwlx ְ������
	 */
	public void setZwlx(String zwlx)
	{
		this.zwlx = zwlx;
	}

	/**
	 * ְ��
	 */
	private String zw=null;

	/**
	 * ��ȡ����ֵ��ְ��
	 * @return ְ��
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
	 * ��������ֵ��ְ��
	 * @param zw ְ��
	 */
	public void setZw(String zw)
	{
		this.zw = zw;
	}

	/**
	 * ��ְʱ��
	 */
	private String tzsj=null;

	/**
	 * ��ȡ����ֵ����ְʱ��
	 * @return ��ְʱ��
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
	 * ��������ֵ����ְʱ��
	 * @param tzsj ��ְʱ��
	 */
	public void setTzsj(String tzsj)
	{
		this.tzsj = tzsj;
	}

	/**
	 * �Ļ��̶�
	 */
	private String whcd=null;

	/**
	 * ��ȡ����ֵ���Ļ��̶�
	 * @return �Ļ��̶�
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
	 * ��������ֵ���Ļ��̶�
	 * @param whcd �Ļ��̶�
	 */
	public void setWhcd(String whcd)
	{
		this.whcd = whcd;
	}

	/**
	 * ѧλ
	 */
	private String xw=null;

	/**
	 * ��ȡ����ֵ��ѧλ
	 * @return ѧλ
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
	 * ��������ֵ��ѧλ
	 * @param xw ѧλ
	 */
	public void setXw(String xw)
	{
		this.xw = xw;
	}

	/**
	 * ��ҵʱ��
	 */
	private String bysj=null;

	/**
	 * ��ȡ����ֵ����ҵʱ��
	 * @return ��ҵʱ��
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
	 * ��������ֵ����ҵʱ��
	 * @param bysj ��ҵʱ��
	 */
	public void setBysj(String bysj)
	{
		this.bysj = bysj;
	}

	/**
	 * ��ҵԺУ
	 */
	private String byyx=null;

	/**
	 * ��ȡ����ֵ����ҵԺУ
	 * @return ��ҵԺУ
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
	 * ��������ֵ����ҵԺУ
	 * @param byyx ��ҵԺУ
	 */
	public void setByyx(String byyx)
	{
		this.byyx = byyx;
	}

	/**
	 * ��������
	 */
	private String dalx=null;

	/**
	 * ��ȡ����ֵ����������
	 * @return ��������
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
	 * ��������ֵ����������
	 * @param dalx ��������
	 */
	public void setDalx(String dalx)
	{
		this.dalx = dalx;
	}
	
	/**
	 * ��������
	 */
	private String cllx=null;

	/**
	 * ��ȡ����ֵ����������
	 * @return ��������
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
	 * ��������ֵ����������
	 * @param cllx ��������
	 */
	public void setCllx(String cllx)
	{
		this.cllx = cllx;
	}

	/**
	 * ����ʱ��
	 */
	private String clsj=null;

	/**
	 * ��ȡ����ֵ������ʱ��
	 * @return ����ʱ��
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
	 * ��������ֵ������ʱ��
	 * @param clsj ����ʱ��
	 */
	public void setClsj(String clsj)
	{
		this.clsj = clsj;
	}

	/**
	 * ��ע
	 */
	private String bz=null;

	/**
	 * ��ȡ����ֵ����ע
	 * @return ��ע
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
	 * ��������ֵ����ע
	 * @param bz ��ע
	 */
	public void setBz(String bz)
	{
		this.bz = bz;
	}

	/**
	 * ԭ���
	 */
	private long oldXH;

	/**
	 * ��ȡ����ֵ��ԭ���
	 * @return ԭ���
	 */
	public long getOldXH()
	{
		return oldXH;
	}

	/**
	 * ��������ֵ��ԭ���
	 * @param oldXH ԭ���
	 */
	public void setOldXH(long oldXH)
	{
		this.oldXH = oldXH;
	}
	
	/**
	 * Excel�����ݿ��ӳ���ϵ���󼯺�
	 */
	private Map<String, ImportDataitemsMapping> importDataitemsMappings = new HashMap<String, ImportDataitemsMapping>();
	
	/**
	 * Excel�����ݿ��ӳ���ϵ���󼯺�
	 */
	public Map<String, ImportDataitemsMapping> getImportDataitemsMappings() {
		return importDataitemsMappings;
	}

	/**
	 * Excel�����ݿ��ӳ���ϵ���󼯺�
	 */
	public void setImportDataitemsMappings(Map<String, ImportDataitemsMapping> importDataitemsMappings) {
		this.importDataitemsMappings = importDataitemsMappings;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public TeacherInfo clone()
	{
		TeacherInfo item = new TeacherInfo(nBXH,saveDate,workFlowStatus,moveOutDate,barcode,archivesTypeID,archivesID,title,xm,gzh,xb,csrq,jg,mz,sfzhm,dp,lxqdw,cjgzsj,lxgzsj,jdsj,ssxy,ssyx,zcxl,zc,pzcsj,zwlx,zw,tzsj,whcd,xw,bysj,byyx,dalx,cllx,clsj,bz,oldXH);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param teacherInfo ָ���Ķ���Դ
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



