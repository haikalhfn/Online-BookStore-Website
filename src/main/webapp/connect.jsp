<%@ page import= "java.sql.*"%>
<% 
String username=request.getParameter("username");
String password=request.getParameter("password");
String email=request.getParameter("email");
String phonenum=request.getParameter("phonenum");

try{
	Class.forName("com.mysql.jdbc.Driver");
	Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
	PreparedStatement ps= conn.prepareStatement("insert into user(username,password,email,Nophone)value(?,?,?,?)");
	ps.setString(1,username);
	ps.setString(2,password);
	ps.setString(3,email);
	ps.setString(4,phonenum);
	int x = ps.executeUpdate();
	
	if(x>0){
		response.sendRedirect("login.jsp");
		
	
	}
	else
		out.println("Register fail");
	
}catch(Exception e){
   out.println(e);
   }
%>
