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
    //随机获取
    public static final String GANHUO_FULI_RANDOM = "http://gank.io/api/random/data/福利/20";

    //段子API
    public static final String GET_DUANZI = "http://is.snssdk.com/neihan/stream/mix/v1/?mpic=1&webp=1&essence=1&content_type=-102&message_cursor=-1&am_longitude=110&am_latitude=120&am_city=%E5%8C%97%E4%BA%AC%E5%B8%82&am_loc_time=1489226058493&count=30&min_time=1489205901&screen_width=1450&do00le_col_mode=0&iid=3216590132&device_id=32613520945&ac=wifi&channel=360&aid=7&app_name=joke_essay&version_code=612&version_name=6.1.2&device_platform=android&ssmix=a&device_type=sansung&device_brand=xiaomi&os_api=28&os_version=6.10.1&uuid=326135942187625&openudid=3dg6s95rhg2a3dg5&manifest_version_code=612&resolution=1450*2800&dpi=620&update_version_code=6120";

    //TTTV API
    public static final String TTTV_BASE_API = "http://117.48.205.32:8080";
    public static final String TTTV_GET_CHANNEL = TTTV_BASE_API + "/TTTv/channel?method=get";

    //知乎日报API
    //获取知乎日报最新消息
    public static final String ZHIHU_GET_NEWS_LATEST = "https://news-at.zhihu.com/api/4/news/latest";
    //获取消息内容: 使用在 ZHIHU_GET_NEWS_LATEST 中获得的 id，拼接在 ZHIHU_GET_NEWS_CONTENT 后
    public static final String ZHIHU_GET_NEWS_CONTENT = "https://news-at.zhihu.com/api/4/news/";

    //Neets.cc网址
    public static final String NEETS_CC = "http://neets.cc";
}
