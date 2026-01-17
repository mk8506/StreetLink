package io.sunhacks.project.Helpers;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletResponse;

@Component
public class RestHelper {
    @Autowired
    private HttpServletResponse response;

    /**
     * JSON
     * @param status 
     * @param message
     * @param data
     * @param error 
     * @return Map<String, Object>
     */
    public Map<String, Object> sendJson(int status, String message, Map<String, Object> data, Exception error) {
        response.setContentType("application/json; charset=UTF-8");
        response.setStatus(status);

        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.setHeader("Access-Control-Allow-Origin", "*");

        Map<String, Object> result = new LinkedHashMap<String, Object>();

        result.put("timestamp", LocalDateTime.now().toString());
        result.put("status", status);
        result.put("message", message);

        if (data != null) {
            result.putAll(data);
        }
        if (error != null) {
            result.put("error", error.getClass().getName());
            result.put("message", error.getMessage());

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(bos);
            error.printStackTrace(ps);

            String trace = bos.toString();
            result.put("trace", trace);
        }

        return result;
    }

    public Map<String, Object> sendJson(Map<String, Object> data) {
        return this.sendJson(200, "OK", data, null);
    }

    public Map<String, Object> sendJson() {
        return this.sendJson(200, "OK", null, null);
    }

    public Map<String, Object> sendError(int status, String message) {
        Exception error = new Exception(message);
        return this.sendJson(status, null, null, error);
    }

    public Map<String, Object> serverError(String message) {
        return this.sendError(500, message);
    }

    public Map<String, Object> serverError(Exception error) {
        return this.sendJson(500, null, null, error);
    }
}
