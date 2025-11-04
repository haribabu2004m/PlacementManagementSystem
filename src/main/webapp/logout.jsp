<%
    session.invalidate(); // clear session
    response.sendRedirect("login.jsp");
%>
