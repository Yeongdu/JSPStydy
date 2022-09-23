package khie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StudentScore
 */
@WebServlet("/studentScore")
public class StudentScore extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentScore() {
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
		
		String name = request.getParameter("student_name");
		int han = Integer.parseInt(request.getParameter("student_han"));
		int eng = Integer.parseInt(request.getParameter("student_eng"));
		int math = Integer.parseInt(request.getParameter("student_math"));
		int java = Integer.parseInt(request.getParameter("student_java"));
		
		int scoreSum = (han+eng+math+java);
		int scoreAvg = (scoreSum / 4);
		
		String scoreHak="";
		String messege="";
		
		if(scoreAvg>=90) {
			scoreHak="A";
			messege="A학점!! 축하합니다!";
		}else if(scoreAvg>=80) {
			scoreHak="B";
			messege="B학점! 이정도면 열심히 했죠!";
		}else if(scoreAvg>=70) {
			scoreHak="C";
			messege="C,..? 재수강 각.?";
		}else if(scoreAvg>=60) {
			scoreHak="D";
			messege="D... 교수님과 원만한 합의를 바랍니다";
		}else if(scoreAvg<60) {
			scoreHak="F";
			messege="지금 장난합니까? 반성하세요‍ ಠ_ಠ";
		}
		
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head></head>");
		out.println("<body>");
		out.println("<div align='center'>");
		out.println("<h2>회원정보</h2>");
		out.println("<table border='1' cellspacing='0' cellpadding='5' align='center'>");
		
		out.println("<tr>");
        out.println("<th>이름</th>");
        out.println("<td>"+name+"</td>");
        out.println("</tr>");
		
        out.println("<tr>");
        out.println("<th>국어점수</th>");
        out.println("<td>"+han+"점</td>");
        out.println("</tr>");
        
        out.println("<tr>");
        out.println("<th>영어점수</th>");
        out.println("<td>"+eng+"점</td>");
        out.println("</tr>");
        
        out.println("<tr>");
        out.println("<th>수학점수</th>");
        out.println("<td>"+math+"점</td>");
        out.println("</tr>");
        
        
        out.println("<tr>");
        out.println("<th>자바점수</th>");
        out.println("<td>"+java+"점</td>");
        out.println("</tr>");
        
        out.println("<tr>");
        out.println("<th>총&nbsp;점</th>");
        out.println("<td>"+scoreSum+"점</td>");
        out.println("</tr>");
        
        out.println("<tr>");
        out.println("<th>평&nbsp;균</th>");
        out.println("<td>"+scoreAvg+"점</td>");
        out.println("</tr>");
        
        out.println("<tr>");
        out.println("<th>학&nbsp;점</th>");
        out.println("<td>"+scoreHak+"학점</td>");
        out.println("</tr>");
   
		out.println("</table>");
		out.println("<h2 style='background-color:GreenYellow'>"+messege+"</h2>");
		
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
		
	}

}
