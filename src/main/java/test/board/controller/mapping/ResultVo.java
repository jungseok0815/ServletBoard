package test.board.controller.mapping;

public class ResultVo {
    private Object resultData = null;
    private String resultType = null;
    private String resultUrl = null;
    private String resultName = null;

    /**
     * redirect타입
     * @param resultUrl
     */
    public ResultVo(String resultUrl){
        this.resultType = "redirect";
        this.resultUrl = resultUrl;
    }

    /**
     * forward타입
     * @param resultData
     * @param resultUrl
     * @param resultName
     */
    public ResultVo(Object resultData, String resultUrl, String resultName) {
        this.resultType = "forward";
        this.resultData = resultData;
        this.resultUrl = resultUrl;
        this.resultName = resultName;
    }

    /**
     * json타입
     * @param resultData
     */
    public ResultVo(Object resultData) {
        this.resultType = "json";
        this.resultData = resultData;
    }

    public Object getResultData() {
        return resultData;
    }

    public String getResultType() {
        return resultType;
    }

    public String getResultUrl() {
        return resultUrl;
    }

    public String getResultName() {
        return resultName;
    }

    @Override
    public String toString() {
        return "ResultVo{" +
                "resultData=" + resultData +
                ", resultType='" + resultType + '\'' +
                ", resultUrl='" + resultUrl + '\'' +
                ", resultName='" + resultName + '\'' +
                '}';
    }
}
