package com.cute.community.entity;

import java.io.Serializable;

public class User implements Serializable {
    private String stuId;

    private String password;

    private String userName;

    private Integer groupId;

    private Integer role;

    private String userDesc;

    private String userSgnature;

    private Integer headPictureId;

    private Integer pictureId;

    private static final long serialVersionUID = 1L;

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId == null ? null : stuId.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc == null ? null : userDesc.trim();
    }

    public String getUserSgnature() {
        return userSgnature;
    }

    public void setUserSgnature(String userSgnature) {
        this.userSgnature = userSgnature == null ? null : userSgnature.trim();
    }

    public Integer getHeadPictureId() {
        return headPictureId;
    }

    public void setHeadPictureId(Integer headPictureId) {
        this.headPictureId = headPictureId;
    }

    public Integer getPictureId() {
        return pictureId;
    }

    public void setPictureId(Integer pictureId) {
        this.pictureId = pictureId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", stuId=").append(stuId);
        sb.append(", password=").append(password);
        sb.append(", userName=").append(userName);
        sb.append(", groupId=").append(groupId);
        sb.append(", role=").append(role);
        sb.append(", userDesc=").append(userDesc);
        sb.append(", userSgnature=").append(userSgnature);
        sb.append(", headPictureId=").append(headPictureId);
        sb.append(", pictureId=").append(pictureId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}