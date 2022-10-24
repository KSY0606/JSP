package memberMVC.ex02;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/download.do")
public class FileDownloadController extends HttpServlet {
	private static String ART_IMAGE_REPO= "C:\\board\\article_image";
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	public void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String articleNo = request.getParameter("articleNo");
		String imageFileName = request.getParameter("imageFileName");
		OutputStream out = response.getOutputStream();
		String path = ART_IMAGE_REPO + "\\" + articleNo + "\\" + imageFileName;
		File imageFile = new File(path);
		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Content-disposition", "attachment;fileName=" + imageFileName);
		FileInputStream fis = new FileInputStream(imageFile);
		byte[] buffer = new byte[1024*8]; // 버퍼를 이용해서 한번에 8k씩 전송
		while(true) {
			int count = fis.read(buffer);
			if(count == -1) break;
			out.write(buffer,0,count);
		}
		fis.close();
		out.close();
	}
}
