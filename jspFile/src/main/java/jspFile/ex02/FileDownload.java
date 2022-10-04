package jspFile.ex02;

import jakarta.servlet.http.HttpServlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/download.do")
public class FileDownload extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String fileFolder = "C:\\myJSP\\file_up";
		String fileName = (String)request.getParameter("fileName");
		System.out.println("fileName = " + fileName);
		OutputStream out = response.getOutputStream();
		String downFile = fileFolder + "\\" + fileName;
		File file = new File(downFile);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
		FileInputStream fInput = new FileInputStream(file);
		byte[] buffer = new byte[1024*8];
		while(true) {
			int count = fInput.read(buffer);
			if(count == -1) {
				break;
			}
			out.write(buffer,0,count);
		}
		fInput.close();
		out.close();
	}

}
