package com.orifound.aiim.dal.dao;

import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.LogInfo;

public interface ISystemLogInfoDao {

	boolean add(LogInfo logInfo, ErrInfo pErrInfo);

}
