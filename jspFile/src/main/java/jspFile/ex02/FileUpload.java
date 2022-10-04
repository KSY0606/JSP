package jspFile.ex02;

import jakarta.servlet.http.HttpServlet;

import java.io.File;
import java.io.IOException;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.FileItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/upload.do")
public class FileUpload extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String encoding = "utf-8";
		File currentFolder = new File("C:\\myJSP\\file_up");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(currentFolder); // ���ε� �� ��� ����
		factory.setSizeThreshold(1024*1024); // �ִ� ���ε� ������ ���� ũ�� ����
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List items = upload.parseRequest(request);
			for(int i = 0; i < items.size(); i++) {
				FileItem fileItem = (FileItem)items.get(i);
				if(fileItem.isFormField()) {
					System.out.println(fileItem.getFieldName() + "=" + fileItem.getString(encoding));
				} else {
					System.out.println("�Ű������� : " + fileItem.getFieldName());
					System.out.println("���ϸ� : " + fileItem.getName());
					System.out.println("����ũ�� : " + fileItem.getSize() + "bytes");
					if(fileItem.getSize() > 0) {
						int idx = fileItem.getName().lastIndexOf("\\");
						if(idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");
						}
						String fileName = fileItem.getName().substring(idx+1);
						File uploadFile = new File(currentFolder+"\\"+fileName);
						fileItem.write(uploadFile);
					}
				}
			}
		}catch(Exception e) {
			System.out.println("���� ���ε� ���� : " + e.getMessage());
		}
	}
}
