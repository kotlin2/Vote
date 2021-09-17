package Pack;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class voteController {
	String sql ="";	
	User user;
	
	@RequestMapping("/login")
	public String vote(Model model, HttpServletRequest request, HttpServletResponse response , HttpSession httpSession) {
		//로그인 페이지 버튼 클릭후 매핑 관련
		
		DBConn dBconn = new DBConn();
		Connection conn = dBconn.connect();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null; 
		
		String uid = request.getParameter("uname");
		String pw = request.getParameter("pw");
		
		String sql = "select * from user where uid='"+uid+"' and pw='"+pw+"'";
		
		try {
			preparedStatement = conn.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			
			resultSet.next();
			
			String gbn = resultSet.getString("CID");
			System.out.println(gbn);
			
			if(gbn.equals("0"))
			{  //투표가능

				//1. 객체생성과 동시에 값 설정 = set메소드 절약
				user = new User(resultSet.getString("UID"), resultSet.getString("UNAME"), resultSet.getString("PW"), resultSet.getString("CID"));
				
				//2. 세션에 UID 추가 보관
				//httpSession.setAttribute("uid", resultSet.getString("UID"));
				//세션 안써도 빈 응용 가능
				
				//3. Connection Close
				conn.close();
				preparedStatement.close();
				resultSet.close();
				
				return "VoteView";
			}
			else
			{   //투표불가능
				response.sendRedirect("/VotePjt/index.jsp");
			}
		} 
		
		catch (Exception e) {
			try {response.sendRedirect("/VotePjt/index.jsp");} 
			catch (IOException e1) {}
		}
		
		return null;
	}
	
	
	 @RequestMapping("/vote")
	 public String vote(HttpSession httpSession, HttpServletRequest request ,HttpServletResponse response) { 
	        
		//String uid = (String)httpSession.getAttribute("uid");
		String uid = user.getUID(); //세션 대신 사용가능(Bean 이용)
	    String voteResult  = request.getParameter("radio");
	       
	    sql = "update user set CID = ? where UID = ?"; 
	        
	    try{
	    	DBConn dBconn = new DBConn();
			Connection conn = dBconn.connect();
			PreparedStatement preparedStatement = null;
			
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, voteResult);
			preparedStatement.setString(2, uid);
			System.out.println(voteResult + " " + uid);
			
			preparedStatement.executeUpdate();
			
			//Connection Close
			conn.close();
			preparedStatement.close();
			
			System.out.println("입력완료");
			return null;
		} 
	       
	    catch (SQLException e) {e.printStackTrace();}
            
	    return null;
	  }
	 
	 @RequestMapping("/result")
	 public String result(Model model) {
		 System.out.println("test");
		 sql = "select CID, count(*) as cnt from user where cid != '0' group by CID order by CID";
		 
		 try{
		    	DBConn dBconn = new DBConn();
				Connection conn = dBconn.connect();
				PreparedStatement preparedStatement = null;
				
				
				preparedStatement = conn.prepareStatement(sql);
				ResultSet resultSet = preparedStatement.executeQuery();
				
				while(resultSet.next())
					model.addAttribute(resultSet.getString(1), resultSet.getString(2));
				
				//Connection Close
				conn.close();
				preparedStatement.close();
				
				return "ResultView";
			} 
		       
		 catch (SQLException e) {e.printStackTrace();}
		 
		 return null;
	 }
}
