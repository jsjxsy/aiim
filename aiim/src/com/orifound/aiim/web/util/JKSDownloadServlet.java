package com.orifound.aiim.web.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserInfo;

/**
 * 数字证书下载
 *
 */
public class JKSDownloadServlet extends HttpServlet {

	private static final long serialVersionUID = 2327670318248793983L;

	public JKSDownloadServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); 
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		OutputStream outputStream = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		ErrInfo pErrInfo = new ErrInfo();
		try {
			//获取用户信息
			UserInfo userInfo = WebCommonUtil.getUserInfo(request, pErrInfo);
			if (pFlag) {
				if (userInfo == null) {
					pFlag = false;
					pErrInfo.getContent().append("获取用户信息失败：");
				} else if (StringTool.checkNull(userInfo.getDepartmentName()) == false) {
					pFlag = false;
					pErrInfo.getContent().append("获取用户部门信息失败：");
				}
			}
			
			pFlag = true;
			
			//文件保存路径
			String filePath = null;
			String filename = null;
			if (pFlag) {
				filename = userInfo.getDepartmentName()+".jks";
				filename = "档案馆.jks";
				filePath = request.getSession().getServletContext().getRealPath("/jksFile")+"\\";
				System.out.println("filePath="+filePath);
				if (StringTool.checkNull(filePath) == false || new File(filePath).isDirectory()==false) {
					pFlag = false;
					pErrInfo.getContent().append("文件保存路径不存在。");
				}
			}
			
			//下载文件
			if (pFlag) {
//				filename = new String(filename.getBytes("iso8859-1"), "gb2312");
				response.setContentType("application/x-msdownload");
				response.setHeader("Content-disposition",
						"attachment; filename="+new String(filename.getBytes("gb2312"),"iso8859-1"));
				
				bis = new BufferedInputStream(new FileInputStream(filePath+filename));
				outputStream = response.getOutputStream();
				bos = new BufferedOutputStream(outputStream);
				byte[] buff = new byte[2048];
				int bytesRead;
				while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
					bos.write(buff, 0, bytesRead);
				}
				request.setAttribute("departmentName", userInfo.getDepartmentName());
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			e.printStackTrace();
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

			if (bis != null) {
				bis.close();
			}
			if (bos != null) {
				bos.close();	
			}
			if(outputStream!=null) {
				outputStream.close();
				outputStream = null;	
			}
			//销毁局部变量
			throwable = null;
		}
		System.out.println(pErrInfo.toString());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void init() throws ServletException {
	}
}