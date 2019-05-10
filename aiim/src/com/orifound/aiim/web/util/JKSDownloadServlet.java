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
 * ����֤������
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
			//��ȡ�û���Ϣ
			UserInfo userInfo = WebCommonUtil.getUserInfo(request, pErrInfo);
			if (pFlag) {
				if (userInfo == null) {
					pFlag = false;
					pErrInfo.getContent().append("��ȡ�û���Ϣʧ�ܣ�");
				} else if (StringTool.checkNull(userInfo.getDepartmentName()) == false) {
					pFlag = false;
					pErrInfo.getContent().append("��ȡ�û�������Ϣʧ�ܣ�");
				}
			}
			
			pFlag = true;
			
			//�ļ�����·��
			String filePath = null;
			String filename = null;
			if (pFlag) {
				filename = userInfo.getDepartmentName()+".jks";
				filename = "������.jks";
				filePath = request.getSession().getServletContext().getRealPath("/jksFile")+"\\";
				System.out.println("filePath="+filePath);
				if (StringTool.checkNull(filePath) == false || new File(filePath).isDirectory()==false) {
					pFlag = false;
					pErrInfo.getContent().append("�ļ�����·�������ڡ�");
				}
			}
			
			//�����ļ�
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
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			e.printStackTrace();
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
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
			//���پֲ�����
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