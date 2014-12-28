package com.olme.domain;

import java.util.Date;

/**
 * Created by Bingo on 2014/8/13.
 */
public class MyQuestion {
	
    private int userId;
    private int comId;
    private String commTheme;
    private String comContent;
    private Date comStartTime;
    private Long comCount;

    public MyQuestion() {
        super();
    }

    public MyQuestion(int userId, int comId, String commTheme,Long comCount, String comContent, Date comStartTime) {
        this.userId = userId;
        this.comId = comId;
        this.commTheme = commTheme;
        this.comContent = comContent;
        this.comStartTime = comStartTime;
        this.comCount = comCount;
    }

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

	public Long getComCount() {
		return comCount;
	}

	public void setComCount(Long comCount) {
		this.comCount = comCount;
	}

	@Override
	public String toString() {
		return "MyQuestion [userId=" + userId + ", comId=" + comId
				+ ", commTheme=" + commTheme + ", comContent=" + comContent
				+ ", comStartTime=" + comStartTime + ", comCount=" + comCount
				+ "]";
	}
}
