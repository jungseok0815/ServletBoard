package test.board.controller.mapping;

import com.google.gson.Gson;
import test.board.controller.BoardController;
import test.board.controller.Parsing.UrlParsing;
import test.common.exception.CustomException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Logger;

import static test.common.Reader.readClass;


public class UrlMapping {
    private static final Logger logger = Logger.getLogger(BoardController.class.getName());
    Gson gson = new Gson();

    /**
     * urlParsing을 통해서 얻은 mapping 정보를 토대로 매핑 및 method하고 맞는 타입을 찾아서 각 기능 수행
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    public void mappingUrl(HttpServletRequest req, HttpServletResponse res,UrlParsing url) throws CustomException, NoSuchMethodException, IOException, ServletException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        ResultVo resultVo = null;

        /**
         * 요청 파라미터가 있는지 없는지에 따라 service 실행
         */
       if (url.getReqData().isEmpty()) {
           logger.info("read class null reqData");
           resultVo = (ResultVo) readClass(url.getClassName(), url.getMethodName());
       }
       else {
           logger.info("read class reqData");
           resultVo = (ResultVo) readClass(url.getClassName(), url.getMethodName(), url.getReqData());
       }

        /**
         * service에서 지정한 type으로 응답 방법 결정
         */
        switch (resultVo.getResultType()){
           case "redirect" :
               logger.info("class path redirect");
               res.sendRedirect(resultVo.getResultUrl());
               break;
           case "forward" :
               logger.info("class path forward");
               req.setAttribute(resultVo.getResultName(), resultVo.getResultData());
               req.getRequestDispatcher(resultVo.getResultUrl()).forward(req,res);
               break;
           case "json" :
               logger.info("class path json");
               res.getWriter().write(gson.toJson((resultVo.getResultData())));
               break;
           default:
                throw new CustomException("not path type");
       }
    }

}
