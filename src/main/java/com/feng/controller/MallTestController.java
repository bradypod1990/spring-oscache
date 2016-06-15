package com.feng.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

@Controller
public class MallTestController {

	@RequestMapping("/testService")
	@ResponseBody
	public Object  tesService(@RequestParam String url, @RequestParam String param, HttpServletRequest request) {
		//"{\"func\":\"getOrderResult\",\"req_data\":{\"key\":\"005010375989_A0D095_1454135733511\"}}"
		String str=this.sendPost(url, param);
		String jsoncallback = request.getParameter("jsoncallback");
//		JSONObject json = JSONObject.parseObject(str);
		Map<String, String> map = new HashMap<String, String>();
		map.put("code", "我们");
		map.put("note", "b");
        return  jsoncallback + "(" + JSON.toJSONString(map) + ")";
	}
	
	@RequestMapping(value="/testService2",produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public Object tesService2(@RequestParam String url, @RequestParam String param, HttpServletRequest request) {
//		String str=this.sendPost(url, param);
		String str=this.sendPost(url, param);
		System.out.println(str);
        return str;
	}
	
	@RequestMapping(value="/testService3",produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public Object tesService3(HttpServletRequest request) {
//		String str=this.sendPost(url, param);
		
        return "我爱中国";
	}
	
	
	 /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("content-type", "text/html");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }

}
