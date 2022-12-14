package khie;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RedirectServlet
 */
@WebServlet("/redirect")
public class RedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RedirectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userId = request.getParameter("id").trim();
		String userPwd = request.getParameter("pwd").trim();

		//원래는 DB의 회원관리 테이블에서 입력한 아이디와 비밀번호가 맞는지 확인하여
		//회원이면 메인페이지 이동
		
		String dbId = "hong";
		String dbPwd = "1234";
		
		if(userId.equals(dbId)) {
			if(userPwd.equals(dbPwd)) {
				//회원인 경우 메인 페이지로 이동
				//정보를 이동하는 페이지로 전달하는 방법
				request.setAttribute("name", "홍길동");
				request.setAttribute("addr", "서울시 중구 남대문로");
				
				response.sendRedirect("Ex11.jsp");
			
				
			}
		}
	}

}
