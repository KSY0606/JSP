package memberMVC.ex02;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
import jakarta.servlet.http.HttpSession;


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
		PrintWriter pw;
		HttpSession session;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String action = request.getPathInfo(); // 요청명을 가져옴
		System.out.println("요청 이름 : " + action);
		try {
			List<ArticleVO> articleList = new ArrayList<ArticleVO>();
			if(action == null || action.equals("/listArticles.do")) {
				String _section = request.getParameter("section");
				String _pageNum = request.getParameter("pageNum");
				int section = Integer.parseInt((_section == null)? "1":_section);
				int pageNum = Integer.parseInt((_pageNum == null)? "1":_pageNum);
				Map<String, Integer> pagingMap = new HashMap<String, Integer>();
				pagingMap.put("section", section);
				pagingMap.put("pageNum", pageNum);
				Map articleMap = boardService.listArticles(pagingMap);
				articleMap.put("section", section);
				articleMap.put("pageNum", pageNum);
				request.setAttribute("articleMap", articleMap);
				nextPage = "/boardinfo/listArticles.jsp";
			}else if(action.equals("/articleForm.do")) {
				nextPage = "/boardinfo/articleForm.jsp"; // 글쓰기창을 나타냄
			}else if(action.equals("/addArticle.do")) { // 새 글 쓰기 작업을 수행
				int articleNo = 0;
				// 파일 업로드 기능을 사용하기위해 upload로 요청을 전달
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
				// 이미지를 첨부한 경우에만 수행
				if(imageFileName != null && imageFileName.length() != 0) {
					// temp폴더에 임시로 업로드된 파일객체를 생성
					File srcFile = new File(ART_IMAGE_REPO + "\\" + "temp\\" + imageFileName);
					// 글번호로 폴더를 생성
					File destDir = new File(ART_IMAGE_REPO + "\\" + articleNo);
					destDir.mkdir();
					// temp폴더에 파일을 글번호 이름으로 하는 폴더로 이동
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
					srcFile.delete();
				}
				pw = response.getWriter();
				pw.print("<script>" + "alert('새글을 추가했습니다.');" + "location.href='" + request.getContextPath() + "/board/listArticles.do';" + "</script>");
				return;
			}else if(action.equals("/viewArticle.do")) {
				String articleNo = request.getParameter("articleNo");
				System.out.println(articleNo);
				articleVO = boardService.viewArticle(Integer.parseInt(articleNo));
				request.setAttribute("article", articleVO);
				nextPage = "/boardinfo/viewArticle.jsp";
			}else if(action.equals("/modArticle.do")) {
				Map<String, String> articleMap = upload(request,response);
				int articleNo = Integer.parseInt(articleMap.get("articleNo"));
				articleVO.setArticleNo(articleNo);
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String imageFileName = articleMap.get("imageFileName");
				articleVO.setParentNo(0);
				articleVO.setId("hong");
				articleVO.setTitle("title");
				articleVO.setContent("content");
				articleVO.setImageFileName("imageFileName");
				boardService.modArticle(articleVO);
				// 이미지를 첨부한 경우에만 수행
				if(imageFileName != null && imageFileName.length() != 0) {
					String originalFileName = articleMap.get("originalFileName");
					// temp폴더에 임시로 업로드된 파일객체를 생성
					File srcFile = new File(ART_IMAGE_REPO + "\\" + "temp\\" + imageFileName);
					// 글번호로 폴더를 생성
					File destDir = new File(ART_IMAGE_REPO + "\\" + articleNo);
					destDir.mkdir();
					// temp폴더에 파일을 글번호 이름으로 하는 폴더로 이동
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
					File oldFile = new File(ART_IMAGE_REPO + "\\" + articleNo + "\\" + originalFileName);
					oldFile.delete();
				}
				pw = response.getWriter();
				pw.print("<script>" + "alert('글을 수정했습니다.');" + "location.href='" + request.getContextPath() + "/board/viewArticle.do?articleNo=" + articleNo + "';" + "</script>");
				return;
			}else if(action.equals("/removeArticle.do")) {
				int articleNo = Integer.parseInt(request.getParameter("articleNo"));
				List<Integer> articleNoList = boardService.removeArticle(articleNo);
				for(int no: articleNoList) {
					File imgDir = new File(ART_IMAGE_REPO + "\\" + no);
					if(imgDir.exists()) {
						FileUtils.deleteDirectory(imgDir);
					}
				}
				pw = response.getWriter();
				pw.print("<script>" + "alert('글을 삭제했습니다.');" + "location.href='" + request.getContextPath() + "/board/listArticles.do';" + "</script>");
				return;
			}else if(action.equals("/replyForm.do")) {
				int parentNo = Integer.parseInt(request.getParameter("parentNo"));
				session = request.getSession();
				session.setAttribute("parentNo", parentNo);
				nextPage = "/boardinfo/replyForm.jsp";
			}else if(action.equals("/addReply.do")) {
				session = request.getSession();
				int parentNo = (Integer)session.getAttribute("parentNo");
				session.removeAttribute("parentNo");
				Map<String, String> articleMap = upload(request, response);
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String imageFileName = articleMap.get("imageFileName");
				articleVO.setParentNo(parentNo);
				articleVO.setId("hong");
				articleVO.setTitle(title);
				articleVO.setContent(content);
				articleVO.setImageFileName(imageFileName);
				int articleNo = boardService.addReply(articleVO);
				if(imageFileName != null && imageFileName.length() != 0) {
					File srcFile = new File(ART_IMAGE_REPO + "\\temp\\" + imageFileName);
					File destDir = new File(ART_IMAGE_REPO + "\\" + articleNo);
					destDir.mkdir();
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
				}
				pw = response.getWriter();
				pw.print("<script>" + "alert('답글을 추가했습니다.');" + "location.href='" + request.getContextPath() + "/board/viewArticle.do?articleNo=" + articleNo + "';" + "</script>");
				return;
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);
		}catch (Exception e) {
			System.out.println("요청 처리중 에러" + e.getMessage());
		}
	} // doHandle 메서드 끝
	
	// 이미지 파일 업로드 + 새 글 관련 정보 저장
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
					// 파일 업로드로 같이 전송된 새 글 관련 (제목,내용) 매개변수를 저장한 후 반환
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
						// 업로드한 이미지를 일단 temp에 저장
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
