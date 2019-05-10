package com.orifound.aiim.bll.service;

import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.LogInfo;

public interface ISystemLogManageService {

	public boolean addLog(LogInfo logInfo ,ErrInfo pErrInfo);

}
