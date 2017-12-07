package com.tt.ttbry.mybbs.config;

import java.util.List;

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

    public static class StoriesBean {
        /**
         * images : ["https://pic4.zhimg.com/v2-55447e90bd6daecf56352030ea93efdb.jpg"]
         * type : 0
         * id : 9659520
         * ga_prefix : 120714
         * title : 知道哪些法律小常识，可以在职场中保护自己？
         */

        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private List<String> images;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean {
        /**
         * image : https://pic2.zhimg.com/v2-9ddf40f985f7892c6f5add67e1a4dea5.jpg
         * type : 0
         * id : 9660035
         * ga_prefix : 120710
         * title : 你平时习惯蹲坑 or 马桶？来看看谁更不卫生吧
         */

        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
