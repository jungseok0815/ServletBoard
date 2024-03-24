package test.board.controller.Parsing;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import test.board.controller.BoardController;
import test.board.model.vo.Board;
import test.common.exception.CustomException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.function.Supplier;
import java.util.logging.Logger;


import static test.common.Parsing.slashParsing;



public class UrlParsing {

    public static JsonObject jsonObject;
    private static final Logger logger = Logger.getLogger(BoardController.class.getName());
    private String className = null;
    private String methodName =null;
    Map<String, String> reqData=new HashMap<>();;
    public String getClassName() {
        return className;
    }
    public String getMethodName() {
        return methodName;
    }
    public Map<String, String> getReqData() {
        return reqData;
    }



    /**
     * utlInfo.json파일 정보를 가지고 className, method, reaData 생성
     * @param req
     * @param res
     * @throws IOException
     * @throws ServletException
     */
    public UrlParsing(HttpServletRequest req, HttpServletResponse res) throws CustomException {
        String[] path = slashParsing(req.getRequestURI());
        String model = jsonObject.get(path[2]).getAsString();
        if (path == null || path.length < 1)  throw new CustomException("path null error");
        if (model == null) throw new CustomException("listBoObject null error");
        /**
         * service를 실행시키기 위해서 형식에 변환
         */
        className = "test."+model.toLowerCase()+".model.service."+model+"ServiceImpl";
        methodName = path[3].substring(0, path[3].lastIndexOf(".do")) + model;

        /**
         * req안에 있는 paramName, paraData를 reqDate Map에 넣어주는 역할
         */
        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            reqData.put(paramName, req.getParameter(paramName));
        }
    }
}
