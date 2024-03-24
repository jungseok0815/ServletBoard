package test.board.model.service;

import test.board.controller.mapping.ResultVo;

import java.util.Map;

public interface BoardService {
    ResultVo insertBoard(Map<String,String> reqDate);
    ResultVo insertBoard();
    ResultVo deleteBoard(Map<String,String> reqDate);
    ResultVo selectBoard(Map<String,String> reqDate) throws Exception;
    ResultVo updateBoard(Map<String,String> reqDate);
    ResultVo listBoard() throws Exception;
}
