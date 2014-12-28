package com.olme.domain;

import java.util.Date;

/**
 * Created by Bingo on 2014/8/13.
 */
public class AllQuestion {
    private int userId;
    private int comId;
    private String userName;
    private String userPhoto;
    private String commTheme;
    private String comContent;
    private Date comStartTime;
    private Long answerCount;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getComId() {
        return comId;
    }

    public void setComId(int comId) {
        this.comId = comId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public String getCommTheme() {
        return commTheme;
    }

    public void setCommTheme(String commTheme) {
        this.commTheme = commTheme;
    }

    public String getComContent() {
        return comContent;
    }

    public void setComContent(String comContent) {
        this.comContent = comContent;
    }

    public Date getComStartTime() {
        return comStartTime;
    }

    public void setComStartTime(Date comStartTime) {
        this.comStartTime = comStartTime;
    }

    public Long getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(Long answerCount) {
        this.answerCount = answerCount;
    }

	@Override
	public String toString() {
		return "AllQuestion [userId=" + userId + ", comId=" + comId
				+ ", userName=" + userName + ", userPhoto=" + userPhoto
				+ ", commTheme=" + commTheme + ", comContent=" + comContent
				+ ", comStartTime=" + comStartTime + ", answerCount="
				+ answerCount + "]";
	}
    
}
