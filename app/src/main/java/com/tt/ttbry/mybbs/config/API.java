package com.tt.ttbry.mybbs.config;

/**
 * Created by TTBry on 2017/11/8.
 */

public class API {

    //西诺网首页地址
    public static final String CCNOVEL_TRANSLATE = "http://www.ccnovel.com/";

    /**
     * http://gank.io/api/data/数据类型/请求个数/第几页
     * 数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
     * 请求个数： 数字，大于0
     * 第几页：数字，大于0
     * 例：http://gank.io/api/data/福利/10/1
     */
    //干货集中营API地址
    private static final String GANHUO_BASE_API = "http://gank.io/api/data";
    public static final String GANHUO_FULI = GANHUO_BASE_API + "/福利";
}
