package com.test.demo.nospring;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.ttl.TransmittableThreadLocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 金🗡
 * @date 2020/4/10 18:47
 * @description:
 */
public class TTL {
    private static ThreadLocal tl = new TransmittableThreadLocal<>();

    ExecutorService executors = Executors.newFixedThreadPool(2);

    public void testThreadLocal() {
        tl.set("金坛");
        Runnable runnable = () -> {

        };
    }


    public static void main(String[] args) {
        String message = "{\"loan_user_name\":\"黄晓春\",\"bank_card\":\"662***************443\","
                + "\"debx_overdue_rate\":\"0.05%\"," +
                "\"xxhb_overdue_rate\":\"0.05%\",\"year\":\"2020\",\"loan_period\":\"12\",\"user_name\":\"黄晓春\",\"loan_amount\":\"10000.00元\",\"loan_limit\":\"2020-12-31\",\"loan_purpose\":\"吃喝玩乐\",\"apply_name\":\"黄晓春\",\"user_id_no\":\"330*************18\",\"repayment_type\":\"等本等息\",\"annualized\":\"24%\",\"month\":\"04\"," +
                "\"repayment_amount\":\"10100.99元\",\"day\":\"03\"}\n";
        JSONObject jsonObject = JSON.parseObject(message);
        for (String s : jsonObject.keySet()) {
            jsonObject.put(s, "");

        }
        System.out.println(JSON.toJSONString(jsonObject));
        System.out.println("https://oss.lljinrong.com/protocol/xinrui/%E6%96%B0%E7%91%9E-%E5%80%9F%E6%AC%BE%E5%90%88%E5%90%8C.pdf".length());

    }

}
