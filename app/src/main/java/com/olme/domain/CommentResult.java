package com.olme.domain;


/**
 * Created by Bingo on 2014/8/13.
 */
public class CommentResult {
	private Integer questionId;
    private Integer commentId;
    private String comContent;
    private Integer comTime;
    private String userName;
    private String questionTheme;
    private Integer comReadFlag;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getComContent() {
        return comContent;
    }

    public void setComContent(String comContent) {
        this.comContent = comContent;
    }

    public Integer getComTime() {
        return comTime;
    }

    public void setComTime(Integer comTime) {
        this.comTime = comTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getQuestionTheme() {
        return questionTheme;
    }

    public void setQuestionTheme(String questionTheme) {
        this.questionTheme = questionTheme;
    }

    public Integer getComReadFlag() {
        return comReadFlag;
    }

    public void setComReadFlag(Integer comReadFlag) {
        this.comReadFlag = comReadFlag;
    }
}
