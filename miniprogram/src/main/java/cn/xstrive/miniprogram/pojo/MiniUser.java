package cn.xstrive.miniprogram.pojo;

import lombok.Data;

@Data
public class MiniUser {
    /**
     * open_id
     */
    private String openId;
    /**
     * skey
     */
    private String skey;   //放的是键值

    /**
     * session_key
     */
    private String sessionvalue;
    /**
     * 市
     */
    private String nickName;

    private int gender;

    private String tele;
    private String language;

    private String city;

    private String province;

    private String country;

    private String avatarUrl;
}
