<%@ page import="test.board.model.vo.Board" %><%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2024-03-13
  Time: 오후 5:41
  To change this template use File | Settings | File Templates.
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <link href="/resource/css/board/detailBoard.css" rel="stylesheet" type="text/css">
</head>
<body>
    <jsp:include page="/views/common/common.jsp" />
    <c:set var="board" value="${requestScope.board}" />
    <c:set var="title" value="${board.boardTitle}" />
    <c:set var="content" value="${board.boardContent}" />
    <c:set var="boardNum" value="${board.boardNum}" />
    <c:set var="createDate" value="${board.createDate}" />

    <div class="detail-board-container">
        <div class="detail-board-title">
            <input type="text" class="form-control" value="${title}" id="exampleFormControlInput1">
        </div>
        <div class="detail-board-content">
            <textarea class="form-control" id="exampleFormControlTextarea1" rows="6">${content}</textarea>
        </div>
        <div align="right" class="create-date">
            <p>${createDate}</p>
        </div>
        <div align="center">
            <button class="btn btn-warning" onclick="updateBoard(${boardNum})">수정</button>
            <button class="btn btn-danger" onclick="deleteBoard(${boardNum})">삭제</button>
        </div>
    </div>
</body>
</html>
