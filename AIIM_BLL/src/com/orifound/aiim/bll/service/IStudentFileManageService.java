package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EMS;
import com.orifound.aiim.entity.EnumWorkFlowStatus;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.Excel;
import com.orifound.aiim.entity.IntegerEx;
import com.orifound.aiim.entity.MoveOutInfo;
import com.orifound.aiim.entity.StudentInfo;

/**
 * 学生档案管理服务类
 * @author Administrator
 *
 */
public interface IStudentFileManageService {

	/**
	 * 导入学生档案信息
	 * @param excel 要导入的Excel文件对象
	 * @param importType 导入类型
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean importStudentInfo(Excel excel, int importType,ArchivesType archivesType,IntegerEx sum, ErrInfo pErrInfo);

	/**
	 * 查找所有的学生信息，分页显示
	 * @param xyName 学院名称
	 * @param dataPageInfo 分页对象
	 * @param studentInfos 返回的学生信息对象
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean find(String xyName,EnumWorkFlowStatus enumWorkFlowStatus, DataPageInfo dataPageInfo, List<StudentInfo> studentInfos, ErrInfo pErrInfo);

	/**
	 * 根据内部序号查找卷内文件
	 * @param nbxh 学生档案内部序号
	 * @param studentInfo 返回的学生信息档案和卷内文件信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findDocByNBXH(int nbxh, StudentInfo studentInfo, ErrInfo pErrInfo);

	/**
	 * 给学生档案信息添加卷内文件
	 * @param ids 卷内文件编号
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean addDocs(int nbxh ,List<Integer> ids, ErrInfo pErrInfo);

	/**
	 * 转出登记
	 * @param excel
	 * @param importType 导入类型
	 * @param sum
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean moveOutRegister(int userId,Excel excel, int importType, ArchivesType archivesType, IntegerEx sum, ErrInfo pErrInfo);

	/**
	 * 找出学生所在的学院
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findCollege(List<String> collegeNames, ErrInfo pErrInfo);

	/**
	 * 查询指定状态的指定转出方式的转出单信息
	 * @param moveOutWay 转出方式
	 * @param moveOutFlag 转出状态
	 * @param moveOutInfos 返回的转出单信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findMoveOutInfo(int moveOutWay, boolean moveOutFlag,ArchivesType archivesType,DataPageInfo dataPageInfo,int minNum ,int maxNum, List<MoveOutInfo> moveOutInfos, ErrInfo pErrInfo);

	/**
	 * 根据ID查找转出单信息
	 * @param id 转出单编号
	 * @param moveOutInfo 返回的转出单信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findMoveOutInfoById(int id, MoveOutInfo moveOutInfo, ErrInfo pErrInfo);

	/**
	 * 更新转出单转出方式
	 * @param id 转出单编号
	 * @param moveOutWay 转出方式
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateMoveOutWay(int id, int moveOutWay, ErrInfo pErrInfo);

	/**
	 * 更新转出单序列号
	 * @param id 转出单编号
	 * @param sN 转出单序列号
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateSN(int id, String sN, ErrInfo pErrInfo);

	/**
	 * 查询学生档案信息
	 * @param studentInfo 要查询的学生档案信息的条件
	 * @param enumWorkFlowStatus 指定要查询那些状态的学生档案信息
	 * @param studentInfos 返回的学生档案信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findStudentInfos(StudentInfo studentInfo,List<EnumWorkFlowStatus> enumWorkFlowStatus, List<StudentInfo> studentInfos, ErrInfo pErrInfo);

	/**
	 * 设置档案工作流状态
	 * @param nbxh
	 * @param enumElement
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findStudentInfos(int nbxh, EnumWorkFlowStatus enumWorkFlowStatus,ErrInfo pErrInfo);

	/**
	 * 更具转出但查询快递单信息
	 * @param ids 转出单编号数组
	 * @param emsInfos 返回的快递单信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean getEMSinfos(int[] ids, List<EMS> emsInfos, ErrInfo pErrInfo);
}
