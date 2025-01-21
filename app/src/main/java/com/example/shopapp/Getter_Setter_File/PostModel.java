package com.example.shopapp.Getter_Setter_File;

public class PostModel {
    private String name;
    private String timeAgo;
    private int profile;
    private String min;
    private int profileImageRes;
    private String title;
    private String subtitle;
    private int heartIconRes;
    private int eyeIconRes;
    private int msgIconRes;

    public PostModel(String name, String timeAgo, int profile, String min, int profileImageRes, String title,
                     String subtitle, int heartIconRes, int eyeIconRes, int msgIconRes) {
        this.name = name;
        this.timeAgo = timeAgo;
        this.profile = profile;
        this.min = min;
        this.profileImageRes = profileImageRes;
        this.title = title;
        this.subtitle = subtitle;
        this.heartIconRes = heartIconRes;
        this.eyeIconRes = eyeIconRes;
        this.msgIconRes = msgIconRes;
    }

    public String getName() { return name; }
    public String getTimeAgo() { return timeAgo; }
    public int getprofile() { return profile; }
    public String getMin() { return min; }
    public int getProfileImageRes() { return profileImageRes; }
    public String getTitle() { return title; }
    public String getSubtitle() { return subtitle; }
    public int getHeartIconRes() { return heartIconRes; }
    public int getEyeIconRes() { return eyeIconRes; }
    public int getMsgIconRes() { return msgIconRes; }
}
