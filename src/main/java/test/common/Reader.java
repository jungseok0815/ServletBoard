package test.common;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import test.common.exception.CustomException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Properties;

public class Reader {
    /**
     * xml파일을 읽는 매소드
     * @param inputStream
     * @return prop
     * @throws ParserConfigurationException
     * @throws IOException
     */
    static public Properties readXmlFile(InputStream inputStream) throws ParserConfigurationException, IOException {
        Properties prop = new Properties();
        try {
            prop.loadFromXML(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            inputStream.close();
        }
        return prop;
    }

    /**
     * Properties파일을 읽어는 매소드
     * @param inputStream
     * @return prop
     * @throws IOException
     *
     */
    static public Properties readPropertiesFile(InputStream inputStream) throws IOException {
        Properties prop = new Properties();
        try {
            prop.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            inputStream.close();
        }
        return prop;
    }

    /**
     * json파일을 읽는 매소드
     * @param inputStream
     * @return JsonObject
     * @throws IOException
     *
     */
    static public JsonObject readJsonFile(InputStream inputStream) throws IOException {
        JsonObject jsonObject = new JsonObject();
        try (java.io.Reader reader = new InputStreamReader(inputStream)) {
            jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
        }catch  (Exception e) {
            e.printStackTrace();
        }finally {
            inputStream.close();
        }
        return jsonObject;
    }

    /**
     * 클래스안에 있는 매소드를 실행(인자값이 있는 경우)
     * @param className
     * @param methodName
     * @param getData
     * @return
     */
    static public Object readClass(String className, String methodName, Map<String, String> getData) {
        try {
            Class<?> clazz = Class.forName(className);
            Object obj = clazz.getDeclaredConstructor().newInstance();
            Method method = clazz.getDeclaredMethod(methodName, Map.class);
            return method.invoke(obj, getData);
        } catch (Exception e) {
            throw new RuntimeException("fail readClass params");
        }
    }

    /**
     * 클래스안에 있는 매소드를 실행(인자값이 없는 경우)
     * @param className
     * @param methodName
     * @return
     */
    static public Object readClass(String className, String methodName) throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> clazz = Class.forName(className);
        Object obj = clazz.getDeclaredConstructor().newInstance();
        Method method = clazz.getDeclaredMethod(methodName);
        return method.invoke(obj);
    }

}