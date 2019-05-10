package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.ICurrentTransferBatNoDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.IntegerEx;
import com.orifound.aiim.entity.PaperTransferBatch;

/**
 * 当前批次信息表DAO实现类
 * @author Administrator
 *
 */
public class CurrentTransferBatNoDaoImpl extends JdbcDaoSupport implements ICurrentTransferBatNoDao {

	@Override
	public boolean findCurrentNo(IntegerEx currentNo, ErrInfo pErrInfo) {
	    String SQL_SELECT_FindCurrentNo = "SELECT CurrentNo FROM CurrentTransferBatNo WHERE BatNoCreateDate=?";
	    List<Integer> currentNos =  getJdbcTemplate().queryForList(SQL_SELECT_FindCurrentNo, Integer.class, new java.sql.Date(System.currentTimeMillis()));
	    if (currentNos.size() >0) {
	    	currentNo.setValue(currentNos.get(0));
	    	return true;
		}else{
			getJdbcTemplate().update("INSERT INTO CurrentTransferBatNo values('"+new java.sql.Date(System.currentTimeMillis())+"',0,0,'')");
			currentNo.setValue(0);
		}
		return true;
	}

	@Override
	public boolean findCurrentTransferBatNo(int userID, PaperTransferBatch paperTransferBatch, ErrInfo pErrInfo) {
		String SQL_SELECT_FindCurrentTransferBatNo = "SELECT CurrentBatNo FROM CurrentTransferBatNo WHERE BatNoCreateUserID=? AND BatNoCreateDate=?";
		paperTransferBatch.setBatNo(getJdbcTemplate().queryForObject(SQL_SELECT_FindCurrentTransferBatNo, java.lang.String.class, new Object[]{userID,new java.sql.Date(System.currentTimeMillis())}));
		return true;
	}

	@Override
	public boolean update(int userID, IntegerEx currentNo, ErrInfo pErrInfo) {
		String SQL_UPDATE_UpdateCurrentNo = "UPDATE CurrentTransferBatNo SET CurrentNo=?,BatNoCreateUserID=?,CurrentBatNo=? WHERE BatNoCreateDate=?";
		getJdbcTemplate().update(SQL_UPDATE_UpdateCurrentNo, currentNo.getValue()+1,userID,getTransferBatNoWithCurrentNoAndCurrentDate(currentNo.getValue()),new java.sql.Date(System.currentTimeMillis()));
		return true;
	}

	/**
	 * 工具方法，根据当前时间和当前序号生成批次号
	 * @param currentNo
	 * @return
	 */
	private String getTransferBatNoWithCurrentNoAndCurrentDate(int currentNo){
		currentNo = currentNo + 1;
		String endStr = new Integer(currentNo).toString();
		if(endStr.length() == 1){
			endStr = "00"+endStr;
		}else if(endStr.length() == 2){
			endStr = "0"+(endStr);
		}
		return new SimpleDateFormat("yyyyMMdd").format(new Date(System.currentTimeMillis()))+endStr;
	}
}
