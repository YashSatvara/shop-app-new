package com.example.shopapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Savelogin {

    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("uname")
    @Expose
    private String uname;
    @SerializedName("uphone")
    @Expose
    private String uphone;
    @SerializedName("uemail")
    @Expose
    private String uemail;
    @SerializedName("upassword")
    @Expose
    private String upassword;

    @SerializedName("ubirthdate")
    @Expose
    private String ubirthdate;

    @SerializedName("uaddress")
    @Expose
    private String uaddress;

    @SerializedName("uimageid")
    @Expose
    private String uimageid;

    @SerializedName("uimagename")
    @Expose
    private String uimagename;

    @SerializedName("uimage")
    @Expose
    private String uimage;

    @SerializedName("appratingrate")
    @Expose
    private String appratingrate;

    @SerializedName("ufeedback")
    @Expose
    private String ufeedback;



    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public String getUbirthdate() {
        return ubirthdate;
    }

    public void setUbirthdate(String ubirthdate) {
        this.ubirthdate = ubirthdate;
    }

    public String getUaddress() {
        return uaddress;
    }

    public void setUaddress(String uaddress) {
        this.uaddress = uaddress;
    }

    public String getUimageid() {
        return uimageid;
    }

    public void setUimageid(String uimageid) {
        this.uimageid = uimageid;
    }

    public String getUimagename() {
        return uimagename;
    }

    public void setUimagename(String uimagename) {
        this.uimagename = uimagename;
    }

    public String getUimage() {
        return uimage;
    }

    public void setUimage(String uimage) {
        this.uimage = uimage;
    }

    public String getAppratingrate() { return appratingrate; }

    public void setAppratingrate(String appratingrate) { this.appratingrate = appratingrate; }

    public String getUfeedback() {
        return ufeedback;
    }

    public void setUfeedback(String ufeedback) {
        this.ufeedback = ufeedback;
    }

}
