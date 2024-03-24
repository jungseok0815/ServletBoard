<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2024-03-14
  Time: 오후 4:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Title</title>
      <link href="/resource/css/board/boardInsert.css" rel="stylesheet" type="text/css">
  </head>
  <body>
  <jsp:include page="/views/common/common.jsp" />
  <div class="board-insert-body">
      <div align="center">게시글 등록</div>
        <br>
            <div class="form-floating">
                <input type="text" name="boardTitle" value="" class="form-control">
            </div>
            <div class="form-floating">
                <input type="text" name="boardContent" value="" class="form-control" style="height: 300px">
            </div>
            <div align="center">
                <button class="btn btn-outline-primary board-insert-btn" type="submit">등록</button>
            </div>
  </div>
</body>
</html>
