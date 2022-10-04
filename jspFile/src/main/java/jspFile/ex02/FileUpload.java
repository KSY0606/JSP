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
		factory.setRepository(currentFolder); // 업로드 할 경로 설정
		factory.setSizeThreshold(1024*1024); // 최대 업로드 가능한 파일 크기 설정
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List items = upload.parseRequest(request);
			for(int i = 0; i < items.size(); i++) {
				FileItem fileItem = (FileItem)items.get(i);
				if(fileItem.isFormField()) {
					System.out.println(fileItem.getFieldName() + "=" + fileItem.getString(encoding));
				} else {
					System.out.println("매개변수명 : " + fileItem.getFieldName());
					System.out.println("파일명 : " + fileItem.getName());
					System.out.println("파일크기 : " + fileItem.getSize() + "bytes");
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
			System.out.println("파일 업로드 실패 : " + e.getMessage());
		}
	}
}
