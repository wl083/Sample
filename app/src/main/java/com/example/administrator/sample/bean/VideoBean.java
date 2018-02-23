package com.example.administrator.sample.bean;

/**
 * Created by shizi on 2016/11/8 0008.
 */

public class VideoBean {
    private String title;
    private String data;
    private long viewCount;

//    private String imgUrl;        //*图片地址
//    private String imgHead;       //*头像地址
    private String userName;      //*用户名称

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


    public long getViewCount() {
        return viewCount;
    }

    public void setViewCount(long viewCount) {
        this.viewCount = viewCount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "VideoBean{" +
                "title='" + title + '\'' +
                ", data='" + data + '\'' +
                ", viewCount=" + viewCount +
                ", userName='" + userName + '\'' +
                '}';
    }
}
