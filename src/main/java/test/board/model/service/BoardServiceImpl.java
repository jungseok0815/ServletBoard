package test.board.model.service;

import test.board.controller.BoardController;
import test.board.model.repository.BoardRepository;
import test.board.controller.mapping.ResultVo;
import test.common.exception.CustomException;
import test.database.ConnectionPool;
import test.database.JDBCTemplate;

import javax.servlet.ServletContext;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Logger;


import static test.database.SQLExecutor.checkParams;


public class BoardServiceImpl implements BoardService {
    private static final Logger logger = Logger.getLogger(BoardController.class.getName());
    Map<String, Object> check = new HashMap<>();
    Connection conn = null;

    /**
     * 게시판 등록 페이지 이동
     * @return resultVo
     */
    @Override
    public ResultVo insertBoard() {
        return new ResultVo("/views/board/boardInsert.jsp");
    }

    /**
     * 게시글 등록
     * @param reqData
     * @return ResultVo
     */
    @Override
    public ResultVo insertBoard(Map<String,String> reqData) {
        try {
            conn = ConnectionPool.getConnetion();
            int result = new BoardRepository().insertBoard(conn,reqData);
            if (result > 0) {
                check.put("insert", "Ok");
            }
            else {
                check.put("insert", "fail");
            }
        } catch (SQLException e) {
            JDBCTemplate.rollback(conn);
            throw new RuntimeException(e);
        }finally {
            JDBCTemplate.commit(conn);
            ConnectionPool.releaseConnection(conn);
        }
        return new ResultVo(check);
    }

    /**
     * 게시글 삭제
     * @param reqData
     * @return resultVo
     */
    @Override
    public ResultVo deleteBoard(Map<String,String> reqData) {
        try {
            conn = ConnectionPool.getConnetion();

            int result = new BoardRepository().deleteBoard(conn,reqData);
            if (result > 0) {
                check.put("delete", "Ok");
            }
            else {
                check.put("delete", "fail");
            }
        } catch (SQLException e) {
            JDBCTemplate.rollback(conn);
            throw new RuntimeException(e);
        }finally {
            JDBCTemplate.commit(conn);
            ConnectionPool.releaseConnection(conn);
        }
        return new ResultVo(check);
    }

    /**
     * 게시글 수정
     * @param reqData
     * @return resultVo
     */
    @Override
    public ResultVo updateBoard(Map<String,String> reqData){
        try {
            conn = ConnectionPool.getConnetion();
            int result = new BoardRepository().updateBoard(conn,reqData);
            if (result > 0) {
                check.put("update", "Ok");
            }
            else {
                check.put("update", "fail");
            }
        } catch (SQLException e) {
            JDBCTemplate.rollback(conn);
            throw new RuntimeException(e);
        }finally {
            JDBCTemplate.commit(conn);
            ConnectionPool.releaseConnection(conn);
        }
        return new ResultVo(check);
    }

    /**
     * 게시글 전체 검색
     * @return resultVo
     */
    @Override
    public ResultVo listBoard() throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        try {
            conn = ConnectionPool.getConnetion();
            return new ResultVo(new BoardRepository().boardList(conn)
                    ,"/views/board/boardList.jsp","boardList");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionPool.releaseConnection(conn);
        }
    }

    /**
     * 게시글 검색
     * @param reqData
     * @return resultVo
     */
    @Override
    public ResultVo selectBoard(Map<String,String> reqData) throws NoSuchFieldException, InstantiationException, IllegalAccessException {
        try {
            conn = ConnectionPool.getConnetion();
            return new ResultVo(new BoardRepository().boardSelect(conn,reqData)
                    ,"/views/board/boardDetail.jsp", "board");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionPool.releaseConnection(conn);
        }
    }
}