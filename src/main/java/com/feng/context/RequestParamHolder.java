package com.feng.context;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * HttpServletRequest的请求参数共享上下文
 *
 * @version 1.0
 * @since 14-十月-2015 16:33:51
 */
public abstract class RequestParamHolder {

    public static ThreadLocal<Map<String, Object>> paramContext = new ThreadLocal<Map<String, Object>>() {
        @Override
        protected Map<String, Object> initialValue() {
            return new HashMap<String, Object>();
        }
    };

    public static final String JKLX_UPLOAD_IMAGE = "uploadImage";

    public static void putRequestParam(HttpServletRequest request) {
        paramContext.remove();
        if (request == null) {
            return;
        }
        Enumeration<String> enu = request.getParameterNames();
        Map<String, Object> paramMap = paramContext.get();
        while (enu.hasMoreElements()) {
            String key = enu.nextElement();
            String value = request.getParameter(key) == null ? "" : request.getParameter(key);
            paramMap.put(key, value);
        }
    }

    public static void putRequestParam(Map<String, Object> requestParams) {
        paramContext.remove();
        Map<String, Object> paramMap = paramContext.get();
        paramMap.putAll(requestParams);
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(String key) {
        return (T) paramContext.get().get(key);
    }

    public static Set<String> keySet() {
        return paramContext.get().keySet();
    }

    /**
     * 不建议在业务逻辑通过该方法来传递参数
     */
    private static void put(String key, Object value) {
        paramContext.get().put(key, value);
    }

    /**
     * 客户号
     */
    public static String getKhh() {
        return get("khh");
    }



    /**
     * 业务功能号
     */
    public static String getYwgnh() {
        return get("ywgnh");
    }

    public static void setYwgnh(String ywgnh) {
        put("ywgnh", ywgnh);
    }

    /**
     * 业务流水号
     */
    public static String getLsh() {
        return get("ywlsh");
    }

    /**
     * 操作终端（1，PC，2，IOS，3，android）
     */
    public static String getCzzd() {
        return get("czzd");
    }

    /**
     * 设备id
     */
    public static String getSbid() {
        return get("sbid");
    }

    /**
     * 业务流程步骤
     */
    public static String getStep() {
        return get("step");
    }

    public static void setStep(String step) {
        put("step", step);
    }

    /**
     * 上一流程步骤
     */
    public static String getLastStep() {
        return get("lastStep");
    }

    public static void setLastStep(String step) {
        put("lastStep", step);
    }

    /**
     * 是否业务办理
     */
    public static String getIsYwbl() {
        return get("isYwbl");
    }

    /**
     * 业务流水页码
     */
    public static String getPageNum() {
        return get("pageNum");
    }

    /**
     * 影像上传:征信问卷编号，用于将上传的影像与征信题目关联。
     */
    public static String getZbbh() {
        return  get("zbbh");
    }

    /**
     * others接口的类型判断
     */
    public static  String getJklx() {
        return get("jklx");
    }

    /**
     * 影像上传的文件信息
     */
    public static  MultipartFile getFile() {
        return get("file");
    }

    /**
     * 影像类型
     */
    public static String getYxlx() {
        return get("yxlx");
    }

    /**
     * 顺序
     */
    public static String getSx() {
        return get("sx");
    }

    /**
     * 是否必传影像
     */
    public static String getIsBcYx() {
        return get("isBcYx");
    }

    /**
     * 影像类型名称
     */
    public static String getYxlxmc() {
        return get("yxlxmc");
    }

    /**
     * 表单提交数据
     */
    public static String getData() {
        String data = get("data");
        return data != null ? data.trim() : "";
    }

    /**
     * 业务功能号对应的英文名称
     */
    public static String getYwgnEnName() {
        return get("ywgnEnName");
    }

    public static void setYwgnEnName(String ywgnEnName) {
        put("ywgnEnName", ywgnEnName);
    }

    /**
     * 手机验证码
     */
    public static String getSjyzm() {
        return get("sjyzm");
    }

    /**
     * 证件编号
     */
    public static String getZjbh() {
        return get("zjbh");
    }

    /**
     * 最终步骤
     */
    public static Boolean isEndStep() {
        return get("endStep");
    }

    public static void setEndStep(Boolean endStep) {
        put("endStep", endStep);
    }

    public static void setView(String viewName) {
        put("viewName", viewName);
    }

    public static String getView() {
        return get("viewName");
    }

}
