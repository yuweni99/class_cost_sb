package com.zys.class_cost_sb.config;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @Author hong
 * @Date 2019/12/26
 * 支付参数配置
 */
public class AlipayConfig {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016101600703982";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDLkSz3lIRtemxuaBAeG9SG3FlVxoLh+iz3Qmkb/5Y/EKuBBIE+qxLtVX2VQy3d41W4LViglwh66WnelPFrUU6qQWpUgqwFfMMgUAflEdCvGF9/I33UhF+it8poftfC9kRRdPBJD39Wr33XbKyB/9zvRWviGQ0V9Xdj1+MGjAh/mvMdAU8vT1W6JioJLNKLBXxR9LsdPfaYCuydGo+ePa/8IBn7PZJEpMADt7AijOg2/1x13Icjg/NFx8SnPs2UuV2aFWe05OikbOw08pCrOh0XGUhRfNsn5Bfo5OtadajYCvUT8zPOqFWNMpGJb0t2ClGyi/UXepvrwY99PRjAZ5fjAgMBAAECggEAYMP83R+DJ8tnGcBr0PQnvYenC6gWdTcIAu1Vq5J+KDJTZDvw+Ao4MAtQbBCIEA7No4QiuguxY0PrKHwimoIG49W+preK7/zTgFuqtRAX4/OxCW2klc3q7B1zoOKSy8FmzlXJFuHABUPcjPfCtPUcX7zfQJXp64NIonRwHZ+ZNpZm8mkfAuiwXryG7cD76yHKoyDASMMgkvhYh1I8Iqt60Dg2MHO0sR/C1VlRGQu6I49QsNgbos4akKwvHZjVFKc5Nz4fXcIwT9GI5a2ROwqYn1PU51mqbNeIK4t89lqxOAWc5f1yHNRIZj4H/PLtUUVMXIGnQvRJF3b4p42gpVbAaQKBgQDz6GV1whjt/2XYHufDOjs9cA5Zby6X4IP9xx/lPYnylbuCntsi9b53NH4FmNu9Dd0I1g7zQ6Gz3FxyIw+i1G4fbiYkMklnOpg/Z//eqr2aBGJIwMrm8MMPLYesJSiw1T+9mvhOuEc32UGPReRuNsK4dWDGKVG5dpM0J9kdE3uC7QKBgQDVqMmPUiWB+QQiM7O+ftXI7+yMrYwTfB17V252gFvuiwr1pKI54zjJ3VEqIMwJz8okfL+xvvM9DAeBiemK7t5Fhb/LXOz09a+PEWEuMkDGA3e2oAhUfMwjkdPar0raKLgP9TWh/T0fGHZxXVzPp1++oVTuHz8hByPSKaFIuR0cDwKBgFOCw5JxNaXvZ0h7NHFUs8nSXydh+PsJZUfz8MjFrEDB4aTUjUVIDMG12FxEPSBa6c5HtHC4PFpf3cs6k/T1dQsP5MFJ8nYEyvISwrnTjf14+MneMNqnnrHIrgZT5r29FgjuUYxqbgACQ/8xOAhAeaU8kzjmFtRAdCBMcHsTG5v1AoGBAMfspI5jg41XAbMvReXesRTQzDvmuMgSv1OGwGpKObvAh4TLhLE+GQlq+mO9RK95q+Dwij88OkbJVsyzy8ChO4M6X/le/qtIqfnpfVHI3CttWzucC69jB3HRJrfehuAUcgccoA7v852BOnNhtVLpfwHV3K7ktiGlmLMzGSP/c2mpAoGAdI71RczN9kbnMoINvUv7sl/1iCMZI/7s0jRDqEPra9SrD7eRpX+1IC1E+HcmvnD7S2Z6we1X3FGoZT8QrERq0yGMoYsWP3o/vz7WP3UdhAXsv6952Sc6/t8fm4FVHAYQSSDWhJhaF/AxLiN7XGAThLNQFmLuUkwZdZiq6aSGh5g=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAo9ZHPLa/C+TwuzpNrNZ0QBEtsdW3MB8LVtLOcsrkZDWcol887J7oZ0OFVSzHJCXgLoeQvxl3m5Kc5o7USpVI61aIGxOKWHCXeWMUiQSW7OHRF5ArsUdU8FbqS4ZpDrhy4C3y62wWEoxGHFLdtsOVU+bxdeNSk6KkV3E0/8rEiNjSH24Y42MEancsqpFmCj7IrZFPxNXWaBgdY5lsIyge0sgJDB0FP42Q2SRCUczdt/HND1JH6F8NroOLMx8rgN9O/Rpl9jZ6pGhCyXHNBqr7wP2i09JCMB4/KqhyVX7QVynnnLb0HicykltGWwln0LVEmMqKY3k7ykhxZkqGpjc/XwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://a6494283.ngrok.io/pay/aliPayAsyncBack";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://a6494283.ngrok.io/pay/aliPaySyncBack";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "d:\\log\\";

    public static String timeout_express = "10m";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
