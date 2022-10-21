package memberMVC.ex02;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.http.fileupload.FileItem;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	private static String ART_IMAGE_REPO= "C:\\board\\article_image";
	BoardService boardService;
	ArticleVO articleVO;
	public void init(ServletConfig config) throws ServletException {
		boardService = new BoardService();
		articleVO = new ArticleVO();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = "";
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String action = request.getPathInfo(); // 요청명을 가져옴
		System.out.println("요청 이름 : " + action);
		try {
			List<ArticleVO> articleList = new ArrayList<ArticleVO>();
			if(action == null) {
				articleList = boardService.listArticles();
				request.setAttribute("articleList", articleList);
				nextPage = "/boardinfo/listArticles.jsp";
			}else if(action.equals("/listArticles.do")) {
				articleList = boardService.listArticles();
				request.setAttribute("articleList", articleList);
				nextPage = "/boardinfo/listArticles.jsp";
			}else if(action.equals("/articleForm.do")) {
				nextPage = "/boardinfo/articleForm.jsp";
			}else if(action.equals("/addArticle.do")) {
				int articleNo = 0;
				Map<String, String> articleMap=upload(request, response);
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String imageFileName = articleMap.get("imageFileName");
				articleVO.setParentNo(0);
				articleVO.setId("hong");
				articleVO.setTitle(title);
				articleVO.setContent(content);
				articleVO.setImageFileName(imageFileName);
				articleNo = boardService.addArticle(articleVO);
				if(imageFileName != null && imageFileName.length() != 0) {
					File srcFile = new File(ART_IMAGE_REPO + "\\" + "temp\\" + imageFileName);
					File destDir = new File(ART_IMAGE_REPO + "\\" + articleNo);
					destDir.mkdir();
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
				}
				nextPage="/board/listArticles.do";
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);
		}catch (Exception e) {
			System.out.println("요청 처리중 에러" + e.getMessage());
		}
	} // doHandle 메서드 끝
	
	// 이미지 파일 업로드
	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> articleMap = new HashMap<String, String>();
		String encoding="utf-8";
		File currentDirPath = new File(ART_IMAGE_REPO);
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(currentDirPath);
		factory.setSizeThreshold(1024*1024);
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List items = upload.parseRequest(request);
			for(int i = 0; i < items.size(); i++) {
				FileItem fileItem = (FileItem)items.get(i);
				if(fileItem.isFormField()) {
					System.out.println(fileItem.getFieldName() + "=" + fileItem.getString(encoding));
					articleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
				}else {
					System.out.println("파라미터이름 : " + fileItem.getFieldName());
					System.out.println("파일터이름 : " + fileItem.getName());
					System.out.println("파일크기 : " + fileItem.getSize() + "bytes");
					if(fileItem.getSize() > 0) {
						int idx = fileItem.getName().lastIndexOf("\\");
						if(idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");
						}
						String fileName = fileItem.getName().substring(idx+1);
						articleMap.put(fileItem.getFieldName(), fileName);
						File uploadFile = new File(currentDirPath + "\\temp\\" + fileName);
						fileItem.write(uploadFile);
					}
				}
			}
		}catch (Exception e) {
			System.out.println("파일 업로드 중 에러");
		}
		return articleMap;
	}
}
