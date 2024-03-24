package test.common;

public class Parsing {
    /**
     * 받은 url을 / 기준으로 문자열 배열로 반환
     * @param pathInfo
     * @return String[]
     */
    static public String[] slashParsing(String pathInfo) {
        String[] parts = null;
        if (pathInfo != null) {
            parts = pathInfo.split("/");
        }
        return parts;
    }

    /**
     * underscore를 CamelCase로 변환하는 매소드
     * @param underscoreString
     * @return String
     */
    static public String underscoreToCamelCase(String underscoreString){
        StringBuilder camelCaseString = new StringBuilder();
        boolean nextUpperCase = false;

        for (int i = 0; i < underscoreString.length(); i++) {
            char currentChar = underscoreString.charAt(i);
            if (currentChar == '_') {
                nextUpperCase = true; // 다음 문자를 대문자로 변환
            } else {
                if (nextUpperCase) {
                    camelCaseString.append(Character.toUpperCase(currentChar));
                    nextUpperCase = false; // 플래그 초기화
                } else {
                    camelCaseString.append(Character.toLowerCase(currentChar));
                }
            }
        }
        return camelCaseString.toString();
    }
}
