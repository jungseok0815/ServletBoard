<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2024-03-13
  Time: 오전 10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="/resource/css/board/board.css" rel="stylesheet" type="text/css">
</head>
<body>
    <jsp:include page="/views/common/common.jsp" />
    <div class="board-body">
        <div align="center" class="board-insert">
            <a href="page/bo/insert.do">
                <button type="button" class="btn btn-primary">
                    게시글 등록
                </button>
            </a>
            <a href="page/bo/list.do">
                <button type="button" class="btn btn-primary">
                    게시글 목록
                </button>
            </a>
        </div>
    </div>
</body>
</html>
