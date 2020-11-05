
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import= "java.sql.*,java.io.*" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
 String u=request.getParameter("username");
String p=request.getParameter("password");
try{
	String s1="org.apache.derby.jdbc.EmbeddedDriver";
	Class.forName(s1);
	Connection con=DriverManager.getConnection("jdbc:derby:C:/Users/Hp/MyDB;create=true","akhila", "admin@12");
	Statement stmt=con.createStatement();
	ResultSet rs =stmt.executeQuery("select * from users");
	 
	String username,password;
	int flag=0;
	while(rs.next())
	{
		username=rs.getString(1);
		password=rs.getString(2);
		if(u.equals(username)&&p.equals(password))
		{
			out.println("welcome:"+u);
			flag=1;
			break;
		}
	}
	if(flag==0)
	{
		out.println("error");
	}
	con.close();
	
}catch(Exception e){
	out.println(e);
}
 
%>

</body>
</html>