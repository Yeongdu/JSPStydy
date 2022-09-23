package khie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 요청과 관련된 API : javax.servlet.http.HttpServletRequest 인터페이스
 * 응답과 관련된 API : javax.servlet.http.HttpServletResponse 인터페이스
 * 
 * 1. 클라이언트가 서블릿에 요청을 하면 먼저 톰캣 서버가 해당 요청을 받음.
 * 2. 그런 다음 사용자의 요청이나 응답에 대한 HttpServletRequest 객체와
 *    HttpServletResponse 객체를 만들게 됨.
 * 3. 그리고 난 후 Servlet의 doGet() 메서드나 doPost() 메서드를 호출하면서
 *    이 객체들을 전달하게 됨.
 * 4. 톰캣이 사용자의 요청에 대한 정보를 모든 HttpServletRequest 객체의 속성으로
 *    담아 메서드로 전달을 함. 따라서 HttpServletRequest 에서 제공하는 메서드들은
 *    매개변수로 넘어온 객체들을 이용하여 사용자가 전송한 데이터를 받아오거나 응답을 할 수 
 *    있는 것임.
 *    
 * 서블릿에서 클라이언트의 요청을 얻는 방법
 * - HttpServletRequest 객체에서 <form> 태그로 전송된 데이터를 받아오는데
 *   사용이 되는 메서드.
 *   * getParameter(String name) : <form> 태그의 name 속성에 들어간 변수명을
 *                                  받아서 사용을 함. 반환형은 String 타입임.
 *   * getparameterValues(String name) : <form> 태그의 같은 name에
 *                                       대하여 여러 개의 값을 얻을 때 사용함.
 *                                       반환형은 String[] 배열 타입임.
 *                                       
 * 서블릿에서 요청 받은 내용을 처리하여 클라이언트에 보내는 방법
 * 1. HttpServletResponse 객체를 이용하여 응답을 함.
 * 2. doGet() 이나 doPost() 메서드 안에서 처리함.
 * 3. javax.servlet.http.HttpServletResponse 객체를 이용함.
 * 4. setContentType() 메서드를 이용하여 클라이언트에 전송할 
 *    데이터의 종류(MIME-TYPE)를 지정함.
 * 5. 클라이언트(웹 브라우저)와 서블릿의 통신은 자바 I/O의 스트림을 이용함.
 * 
 * 
 * 웹 브라우저에서 서블릿으로 데이터를 전송하는 방법 - 2가지
 * 1. get 방식
 *    - 서블릿에 데이터를 전송할 때는 데이터가 url 뒤에 name=value 형태로 전송이 됨.
 *    - 여러 개의 데이터를 전송할 때는 '&'로 구분하여 전송이 됨.
 *    - 보안이 취약함.
 *    - 전송할 수 있는 데이터는 최대 255자.
 *    - 기본 전송 방식으로 사용이 쉬움.
 *    - 웹 브라우저에 직접 입력해서 전송할 수도 있음.
 *    - 서블릿에서는 doGet() 메서드에서 전송된 데이터를 처리함.
 *    
 * 2. post 방식
 *    - 서블릿에 데이터를 전송할 때는 TCP/IP 프로토콜 데이터의 head 영역에
 *      숨겨진 채 전송이 됨.
 *    - 보안에 유리함.
 *    - 전송 데이터의 용량이 무제한임.
 *    - 처리 속도가 get 방식보다는 느림.
 *    - 서블릿에서는 doPost() 메서드에서 전송된 데이터를 처리함. 
 */



/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 폼 페이지에서 넘어온 정보를 받아주면 된다.
		//form태그에 method="get" 이라고 적힌 경우나 
		//method 라는 속성이 안 적혀진 경이 이 메서드에서 처리
		
		/*
		 * request 매개변수
		 * - 사용자(클라이언트) 의 요청에 대한 정보를 처리
		 * - 클라이언트로부터 Servlet으로 요청이 들어오게 되면
		 * 	요청 파라미터라는 것이 같이 오게 되는데
		 * 	이것에 대한 분석은 request.getParameter() 라는 매서드를 이용하여 파악한다.
		 * 
		 * response 매개변수
		 * - 사용자의 요청 정보에 대한 처리 결과를 클라이언트에 응답하여 처리한다.
		 * - 요청을 파악했다면 클라이언트로 내보낼 응답을 작성해야 한다.대부분의 웹 프로그래밍은 응답을 텍스트로 작성하며
		 * 이 텍스트는 대부분 HTML 페이지 모양을 하고 있게 된다. 여기서 응답은 텍스트를 기록해야 하는데,
		 * 이 떄 스크림이라는 개념을 이용해서 기록을 하게 된다. 말 그대로 데이터의 흐름이라고 생각하면 된다.
		 * Servlet에서는 클라이언트쪽으로 보내는 데이터의 흐름을 건드려야 할 필요가 있게된다.
		 * response인자를 활용하면 응답과 관련된 많은 작업을 할 수 있다. 응답 스트림에 텍스트를 기록하는 것도 가능하다.
		 * 이 작업을 하기 위해서는 reponse.getWritter()라는 매서드를 사용하면 된다.
		 * 해당 메서드를 이용하여 응답으로 내보낼 출력 스트림을 얻어낸 후에 출력 스트림에 텍스트를 기록하면 된다.
		 */
		
		String id = request.getParameter("userId");
		String pwd = request.getParameter("userPwd");
		
		System.out.println("입력한 아이디 >>> " + id);
		System.out.println("입력한 비밀번호 >>> " + pwd);
		
		//응답에 한글이 나오는 경우
		//클라이언트로 응답 시 한글을 작성하면 한글이 깨지는 현상이 발생
		response.setContentType("text/html; charset=UTF-8");
		
		//응답은 response 객체를 이용한다.
		PrintWriter out = response.getWriter();
		
		//클라이언트에 응답해주자
		out.println("<html>");
		out.println("<head></head>");
		out.println("<body>");
		out.println("<h2>");
		out.println("입력한 아이디 : " + id + "<br>");
		out.println("입력한 비밀번호 : " + pwd + "<br>");
		out.println("</h2>");
		out.println("</body>");
		out.println("</html>");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
