/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.IArchivesCertificateInfoManageService;
import com.orifound.aiim.dal.dao.IArchivesCertificateInfoDao;
import com.orifound.aiim.dal.util.StringTool;
import com.orifound.aiim.entity.ArchivesCertificateInfo;
import com.orifound.aiim.entity.ArchivesCertificateRegister;
import com.orifound.aiim.entity.CertificateType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 档案出证明细情况管理服务实现类
 *
 */
public class ArchivesCertificateInfoManageServiceImpl implements IArchivesCertificateInfoManageService {
	/**
	 * 注入档案出证信息DAO
	 */
	@Autowired
	private IArchivesCertificateInfoDao archivesCertificateInfoDao;
	
	/**
	 * 构造函数
	 */
	public ArchivesCertificateInfoManageServiceImpl() {

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public ArchivesCertificateInfoManageServiceImpl(IArchivesCertificateInfoDao archivesCertificateInfoDao) {
		this.archivesCertificateInfoDao = archivesCertificateInfoDao;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesCertificateInfoManageService#deleteArchivesCertificateInfo(com.orifound.aiim.entity.ArchivesCertificateInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteArchivesCertificateInfo(
			ArchivesCertificateInfo archivesCertificateInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesCertificateInfoManageService#findArchivesCertificateInfoByID(int, com.orifound.aiim.entity.ArchivesCertificateInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesCertificateInfoByID(int pID, ArchivesCertificateInfo archivesCertificateInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			if (pFlag) {
				if (pID <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("唯一标识非法为空！");
				}
			}

			//根据唯一标识查找档案出证信息
			if (pFlag) {
				pErrPos = 2;
				if (archivesCertificateInfoDao.findByID(pID, archivesCertificateInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "根据唯一标识查找档案出证信息 失败：");
				}

			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesCertificateInfoManageService#findArchivesCertificateInfos(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesCertificateInfos(
			List<ArchivesCertificateInfo> archivesCertificateInfos,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesCertificateInfoManageService#findCertificateType(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findCertificateTypes(List<CertificateType> certificateTypes,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjection(pErrInfo) == false) {
				pFlag = false;
			}

			//查询所有档案出证类型
			if (pFlag) {
				pErrPos = 2;
				if (archivesCertificateInfoDao.findCertificateTypes(certificateTypes, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询所有档案出证类型 失败：");
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesCertificateInfoManageService#saveArchivesCertificateInfo(com.orifound.aiim.entity.ArchivesCertificateInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveArchivesCertificateInfo(ArchivesCertificateRegister certificateRegister, List<ArchivesCertificateInfo> archivesCertificateInfos, ErrInfo pErrInfo) {
		System.out.println("saveArchivesCertificateInfo..........");
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检测档案登记信息
			if (pFlag) {
				if (certificateRegister == null) {
					pFlag = false;
					pErrInfo.getContent().append("档案登记信息非法为空。");
				}
			}
			
			//利用人编号 
			if (pFlag) {
				if (certificateRegister.getPersonID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("利用人编号非法为空。");
				}
			}
			
			//应缴金额
			if (pFlag) {
				if (certificateRegister.getShouldCharge() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("应缴金额非法为空。");
				}
			}
			//经办人编号
			if (pFlag) {
				if (certificateRegister.getManagerUserID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("经办人编号非法为空。");
				}
			}
			//检测档案出证明细
			if (pFlag) {
				if (archivesCertificateInfos==null || archivesCertificateInfos.size()<=0) {
					pFlag = false;
					pErrInfo.getContent().append("档案出证明细非法为空。");
				}
			}

			//出证收费登记
			if (pFlag) {
				pErrPos = 2;
				if (archivesCertificateInfoDao.save(certificateRegister, archivesCertificateInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "出证收费登记 失败：");
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesCertificateInfoManageService#updateArchivesCertificateInfo(com.orifound.aiim.entity.ArchivesCertificateInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateArchivesCertificateInfo(
			ArchivesCertificateInfo archivesCertificateInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检测档案出证信息
			if (pFlag) {
				if (archivesCertificateInfo == null) {
					pFlag = false;
					pErrInfo.getContent().append("更新档案出证信息非法为空。");
				}
			}

			//更新指定的档案出证信息
			if (pFlag) {
				pErrPos = 2;
				if (archivesCertificateInfoDao.update(archivesCertificateInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "更新指定的档案出证信息 失败：");
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}
	
	/**
	 * 检查Entity的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjection(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (archivesCertificateInfoDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("档案出证信息的DAO非法为空，请检查是否有进行依赖注入或赋值。");
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
		}

		return pFlag;
	}

	@Override
	public boolean findArchivesCertificateInfoByCertificateRegID(int pCertificateRegID, ArchivesCertificateInfo archivesCertificateInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			if (pCertificateRegID <= 0) {
				pFlag = false;
				pErrInfo.getContent().append("出证登记编号非法为空！");
			}
			//查询所有档案出证类型
			if (pFlag) {
				pErrPos = 2;
				if (archivesCertificateInfoDao.findByCertificateRegID(pCertificateRegID, archivesCertificateInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询所有档案出证类型 失败：");
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}

	

	@Override
	public boolean findArchivesCertificateRegistersByQuery(
			Map<String, String> queryString,
			List<ArchivesCertificateRegister> archivesCertificateRegisters,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检测档案出证登记集合
			if (pFlag) {
				if (archivesCertificateRegisters == null) {
					pFlag = false;
					pErrInfo.getContent().append("档案出证登记集合非法为空！");
				}
			}

			//根据条件查询档案出证登记信息
			if (pFlag) {
				pErrPos = 2;
				if (archivesCertificateInfoDao.findArchivesCertificateRegistersByQuery(queryString, archivesCertificateRegisters, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "根据条件查询档案出证登记信息 失败：");
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}

	@Override
	public boolean findArchivesCertificateInfosByRegisterId(
			int certificateRegID,
			List<ArchivesCertificateInfo> archivesCertificateInfos,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检测出证登记
			if (pFlag) {
				if (certificateRegID <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("出证登记id非法为空。");
				}
			}
			
			//检测返回出证信息集合
			if (pFlag) {
				if (archivesCertificateInfos == null) {
					pFlag = false;
					pErrInfo.getContent().append("出证信息集合非法为空。");
				}
			}

			if (pFlag) {
				pErrPos = 2;
				if (archivesCertificateInfoDao.findByRegisterId(certificateRegID, archivesCertificateInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "根据出证登记id查找档案出证信息 失败：");
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}

	@Override
	public boolean updateArchivesCertificateInfoXH(
			ArchivesCertificateInfo archivesCertificateInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检测唯一标识
			if (pFlag) {
				if (archivesCertificateInfo.getID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("唯一标识非法为空。");
				}
			}
			
			//检测学号
			if (pFlag) {
				if (StringTool.checkNull(archivesCertificateInfo.getXH()) == false) {
					pFlag = false;
					pErrInfo.getContent().append("学号非法为空。");
				}
			}

			//更新指定的档案出证信息学号
			if (pFlag) {
				pErrPos = 2;
				if (archivesCertificateInfoDao.updateXH(archivesCertificateInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "更新指定的档案出证信息学号 失败：");
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}

}
