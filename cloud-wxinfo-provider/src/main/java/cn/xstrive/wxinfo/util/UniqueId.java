package cn.xstrive.wxinfo.util;


import cn.hutool.core.util.IdUtil;

public class UniqueId {
    public static String getuniqueid(){
        return IdUtil.simpleUUID();
    }
}
