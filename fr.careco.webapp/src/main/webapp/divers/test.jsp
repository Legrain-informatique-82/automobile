<%@ page import="java.sql.*"%>
<html>
<head>
<title>Test Web Service</title>
</head>

<body>
	<h1>Table test Web Service</h1>

	<%  
	/*
       		WSCareco ws = new WSCareco();
            Connection connection = null;
            Class.forName(ws.JDBC_DRIVER);
            connection = DriverManager.getConnection(ws.getJDBC_URL());

            Statement statement = connection.createStatement();

            ResultSet resultset = statement.executeQuery("select * from test_ws");
            */

        %>
	<table border="1">
		<tr>
			<th>col1</th>
			<th>col2</th>
		</tr>
		<%  /*  %>
		<%  while(resultset.next()){  %>
		<tr>
			<td><%= resultset.getString(1) %></td>
			<td><%= resultset.getString(2) %></td>
		</tr>
		<% 
            } */
        %>
	</table>

</body>
</html>