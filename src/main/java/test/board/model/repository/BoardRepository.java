package test.board.model.repository;

import test.board.model.vo.Board;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;


import static test.database.SQLExecutor.*;

public class BoardRepository {
    private static final Logger logger = Logger.getLogger(BoardRepository.class.getName());
    public static Properties sqlProp = new Properties();
        /**
     * 게시글 등록
     * @param conn
     * @param reqData
     * @return int result
     */
    public int insertBoard(Connection conn,Map<String,String> reqData) throws SQLException {
        return executeUpdate(conn, checkParams(sqlProp.getProperty("insertBoard"),reqData));
    }
    /**
     * 게시글 삭제
     * @param conn
     * @param reqData
     * @return int result
     */
    public int deleteBoard(Connection conn,Map<String,String> reqData) throws SQLException {
        return executeUpdate(conn, checkParams(sqlProp.getProperty("deleteBoard"),reqData));
    }
    /**
     * 게시글 수정
     * @param reqData
     * @param conn
     * @return int resilt
     */
    public int updateBoard(Connection conn,Map<String,String> reqData) throws SQLException {
        return executeUpdate(conn,checkParams(sqlProp.getProperty("updateBoard"),reqData));
    }
    /**
     * 전체 게시글 조회
     * @param conn
     * @return list<Board>
     */
    public ArrayList<Board> boardList(Connection conn) throws NoSuchFieldException, IllegalAccessException, InstantiationException, SQLException {
        return (ArrayList<Board>) selectList(conn,Board.class,sqlProp.getProperty("listBoard"));
    }
    /**
     * 게시글 조회
     * @param conn
     * @param reqData
     * @return Board
     */
    public Board boardSelect(Connection conn,Map<String,String> reqData) throws NoSuchFieldException, InstantiationException, IllegalAccessException, SQLException {
        return selectOne(conn,Board.class, checkParams(sqlProp.getProperty("selectBoard"),reqData));
    }
}
