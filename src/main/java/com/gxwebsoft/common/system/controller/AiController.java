package com.gxwebsoft.common.system.controller;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONObject;
import com.gxwebsoft.common.core.utils.JSONUtil;
import com.gxwebsoft.common.core.web.ApiResult;
import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.common.core.websocket.WebSocketServer;
import com.gxwebsoft.common.system.entity.ChatMessage;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "AI")
@RestController
@RequestMapping("/api/chat")
public class AiController extends BaseController {
    @Resource
    private WebSocketServer webSocketServer;

    @PostMapping("/message")
    public ApiResult<?> message(@RequestBody ChatMessage message) throws IOException {
        Map<String, String> paramsJsonStr = new HashMap<>();
        paramsJsonStr.put("query", message.getQuery());
        paramsJsonStr.put("opsType", "0");

        Map<String, String> formData = new HashMap<>();
        formData.put("user", message.getUser());
        formData.put("responseMode", "streaming");
        formData.put("paramsJsonStr", JSONUtil.toJSONString(paramsJsonStr));
        formData.put("authCode", "a8cc4a0a-aea3-4ea5-811a-80316520a3d3");
        // 使用 Java 自带的 HttpURLConnection 发送流式请求
        try {
            URL url = new URL("https://ai-console.gxshucheng.com/ai-console-api/run/v1");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            connection.setDoOutput(true);
            connection.setConnectTimeout(600000);
            connection.setReadTimeout(600000);

            // 写入请求体
            try (OutputStream os = connection.getOutputStream();
                 PrintWriter writer = new PrintWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8))) {
                for (Map.Entry<String, String> entry : formData.entrySet()) {
                    writeFormField(writer, entry.getKey(), entry.getValue());
                }
                // 添加文件上传部分（可选）
                // writeFilePart(writer, "file", "test.txt", "text/plain", "This is the file content.");
                writer.append("--").append(boundary).append("--").append("\r\n");
                writer.flush();
            }

            StringBuilder responseStr = new StringBuilder();
            // 读取响应流
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println("Received chunk: " + line); // 打印接收到的每一部分数据
                    // 这里可以对每一部分数据进行处理，例如解析或发送给前端
                    if (!line.isEmpty()) {
                        String[] dataList = line.split("data: ");
                        if (dataList.length == 2) {
//                            System.out.println(dataList[1]);
                            Map data = JSONUtil.parseObject(dataList[1], Map.class);
                            if (data.get("event") != null && data.get("event").equals("message")) {
                                String answer = (String) data.get("answer");
                                String task_id = (String) data.get("task_id");
                                if (answer != null && !answer.isEmpty()) {
                                    HashMap<String, String> answerData = new HashMap<>();
                                    answerData.put("answer", answer);
                                    answerData.put("taskId", task_id);
                                    webSocketServer.sendMessage(message.getUser(), JSONUtil.toJSONString(answerData));
                                }
                                System.out.println("answer: " + answer);
                                responseStr.append(answer);
                            }else if (data.get("event") != null && data.get("event").equals("message_end")) {
                                String task_id = (String) data.get("task_id");
                                HashMap<String, String> answerData = new HashMap<>();
                                answerData.put("answer", "__END__");
                                answerData.put("taskId", task_id);

                                webSocketServer.sendMessage(message.getUser(), JSONUtil.toJSONString(answerData));
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                System.out.println(stackTraceElement);
            }
            webSocketServer.sendMessage(message.getUser(), "出错了,请晚点再来提问吧~");
            return fail("出错了,请晚点再来提问吧~");
        }

        // 返回成功响应
        return success("Stream processing completed");
    }

    private static final String boundary = "---" + System.currentTimeMillis() + "---";

    private static void writeFormField(PrintWriter writer, String fieldName, String value) {
        writer.append("--").append(boundary).append("\r\n");
        writer.append("Content-Disposition: form-data; name=\"").append(fieldName).append("\"\r\n");
        writer.append("\r\n");
        writer.append(value).append("\r\n");
    }

    @PostMapping("/messageStop")
    public ApiResult<?> stop(@RequestBody Map<String, Object> data) {
        if (data.get("taskId") == null) return success();
        String taskId = data.get("taskId").toString();
        Map<String, Integer> postData = new HashMap<>();
        postData.put("user", getLoginUserId());
        // 从环境变量或配置中获取AI服务token
        String token = System.getProperty("ai.service.token", "Bearer your-ai-token");
        if (data.get("type") != null) {
            token = System.getProperty("ai.service.token.alt", "Bearer your-ai-token-alt");
        }
        String res = HttpRequest.post("http://workflow.gxshucheng.com:8010/v1/chat-messages/" + taskId + "/stop")
                .header("Authorization", token)
                .header("Content-Type", "application/json")
                .body(JSONObject.toJSONString(postData))
                .execute().body();
        System.out.println("stop res:" + res);
        return success();
    }
}
