<%@ page import="java.util.ArrayList" %>
<%@ page import="test.board.model.vo.Board" %><%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2024-03-14
  Time: 오후 5:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link href="/resource/css/board/boardList.css" rel="stylesheet" type="text/css">
</head>
<body>
    <jsp:include page="/views/common/common.jsp" />
    <table align="center" class="board-list-table">
        <thead align="center">
            <tr>
                <th>글번호</th>
                <th>제목</th>
                <th>날짜</th>
            </tr>
        </thead>
        <tbody align="center">
            <c:forEach var="borad" items="${boardList}">
                <tr onclick="location.href='/page/bo/select.do?boardNum=${borad.boardNum}'">
                    <td>${borad.boardNum}</td>
                    <td>${borad.boardTitle}</td>
                    <td>${borad.createDate}</td>
                </tr>
            </c:forEach>
        </tbody>

    </table>

</body>
</html>
