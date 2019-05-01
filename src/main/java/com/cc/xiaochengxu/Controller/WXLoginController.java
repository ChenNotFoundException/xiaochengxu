package com.cc.xiaochengxu.Controller;

import com.cc.xiaochengxu.model.WXSessionModel;
import com.cc.xiaochengxu.utils.HttpClientUtil;
import com.cc.xiaochengxu.utils.JSONResult;
import com.cc.xiaochengxu.utils.JsonUtils;
import com.cc.xiaochengxu.utils.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenchen
 * @date 2019/5/1 15:52
 * @Content:
 */
@RestController
public class WXLoginController {
    @Autowired
    private RedisOperator redis;
    @PostMapping("/wxLogin")
    public JSONResult wxLogin(String code) {
        System.out.println("wxLogin-code：" + code);
//        https://api.weixin.qq.com/sns/jscode2session?
// appid=APPID&
// secret=SECRET&
// js_code=JSCODE&
// grant_type=authorization_code
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        Map <String, String> param = new HashMap <>();
        param.put("appid", "wx415dd699636e1f09");
        param.put("secret", "YOUR_secert"); //自己对应的secret
        param.put("js_code", code);
        param.put("grant_type", "authorization_code");
        String doGet = HttpClientUtil.doGet(url, param);
        WXSessionModel model = JsonUtils.jsonToPojo(doGet, WXSessionModel.class);

        //存入session到redis
        redis.set("user-redis-session:" + model.getOpenid(), model.getSession_key(), 1000 * 60 * 30);

        System.out.println(doGet);
        return JSONResult.ok();
    }
}
