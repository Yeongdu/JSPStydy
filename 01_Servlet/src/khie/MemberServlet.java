package khie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet("/member")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("memId");
		String pwd = request.getParameter("memPwd");
		String name = request.getParameter("memName");
		String age = request.getParameter("memAge");
		String phone = request.getParameter("memPhone");
		
		System.out.println("아이디 : " + id);
		System.out.println("비번 : " + pwd);
		System.out.println("이름 : " + name);
		System.out.println("나이 : " + age);
		System.out.println("연락처 : " + phone);
		
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
				
		out.println("<html>");
		out.println("<head></head>");
		out.println("<body>");
		out.println("<div>");
		out.println("<h2>회원정보</h2>");
		out.println("<table border='1' cellspacing='0'>");
		
		out.println("<th>");
		out.println("</th>");
		out.println("<td>");
		out.println("아이디 : " + id + "<br>");
		out.println("</td>");
		
		out.println("<th>");
		out.println("</th>");
		out.println("<td>");
		out.println("비밀번호 : " + pwd + "<br>");
		out.println("</td>");
		
		out.println("<th>");
		out.println("</th>");
		out.println("<td>");
		out.println("이름 : " + name + "<br>");
		out.println("</td>");
		
		out.println("<th>");
		out.println("</th>");
		out.println("<td>");
		out.println("나이 : " + age + "<br>");
		out.println("</td>");
		
		out.println("<th>");
		out.println("</th>");
		out.println("<td>");
		out.println("연락처 : " + phone + "<br>");
		out.println("</td>");
		
		out.println("</h2>");
		out.println("</table>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
