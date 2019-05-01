package com.cc.xiaochengxu.model;

/**
 * @author chenchen
 * @date 2019/5/1 16:27
 * @Content:
 */
public class WXSessionModel {
    private String session_key;
    private String openid;

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public WXSessionModel(String session_key, String openid) {
        this.session_key = session_key;
        this.openid = openid;
    }

    public WXSessionModel() {
    }
}
