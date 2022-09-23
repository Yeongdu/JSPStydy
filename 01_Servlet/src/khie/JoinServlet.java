package khie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JoinServlet
 */
@WebServlet("/join")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinServlet() {
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
		response.setContentType("text/html; charset=UTF-8");
		
		request.setCharacterEncoding("UTF-8");
		
		//1단계 : Ex05.html.jsp페이지에서 넘어온 정보들을 받아주자
		String mem_id = request.getParameter("id");
		String mem_pwd = request.getParameter("pwd");
		String mem_name = request.getParameter("name");
		String mem_phone = request.getParameter("phone");
		String mem_addr = request.getParameter("addr");
		
		//여러개의 정보가 넘어올 경우 request.getParameter(name) 못쓴다
		//반환타입 배열인 request.getParameterValues() 써야함.
		String[] mem_hobby = request.getParameterValues("hobby");
		
		//2단계 : 웹 브라우저에 요청한 결과를 화면에 보여주자
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head></head>");
        out.println("<table>");
        out.println("<head></head>");
        out.println("<body>");
        out.println("<table border='1' cellspacing='0' cellpadding='5' align='center'>");
        
        out.println("<tr>");
        out.println("<th>아이디</th>");
        out.println("<td>"+mem_id+"</td>");
        out.println("</tr>");
        
        out.println("<tr>");
        out.println("<th>비밀번호</th>");
        out.println("<td>"+mem_pwd+"</td>");
        out.println("</tr>");
        
        out.println("<tr>");
        out.println("<th>이름</th>");
        out.println("<td>"+mem_name+"</td>");
        out.println("</tr>");
        
        out.println("<tr>");
        out.println("<th>연락처</th>");
        out.println("<td>"+mem_phone+"</td>");
        out.println("</tr>");
        
        out.println("<tr>");
        out.println("<th>주소</th>");
        out.println("<td>"+mem_addr+"</td>");
        out.println("</tr>");
        
        out.println("<tr>");
        out.println("<th>취미</th>");
        out.println("<td>");
        for(int i=0; i<mem_hobby.length; i++) {
            out.println(mem_hobby[i]+"&nbsp;&nbsp;");
        }
        out.println("</td>");
        out.println("</tr>");
        
        out.println("</table>");
        out.println("</body>");
        out.println("</table>");
		out.println("</html>");
		
		
		
	}

}
