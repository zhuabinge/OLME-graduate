package com.olme.domain;

import java.util.Date;



/**
 * Created by wuzeyong on 2014/7/31 0031.
 * Function:
 */
public class LoginUser {
    private int userId;
    private String userEmail;
    private String userName;
    private String password;
    private String userPhoto;
    private String userAddress;
    private Date userBirthday;
    private String userPhone;
    private String userSex;

    public LoginUser() {
    }

    public LoginUser(int userId, String userEmail, String userName, String password, String userSex,String userPhoto, String userAddress, Date userBirthday, String userPhone) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.userName = userName;
        this.password = password;
        this.userPhoto = userPhoto;
        this.userAddress = userAddress;
        this.userBirthday = userBirthday;
        this.userPhone = userPhone;
        this.userSex = userSex;
    }

    public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

	@Override
	public String toString() {
		return "LoginUser [userId=" + userId + ", userEmail=" + userEmail
				+ ", userName=" + userName + ", password=" + password
				+ ", userPhoto=" + userPhoto
				+ ", userAddress=" + userAddress + ", userBirthday="
				+ userBirthday + ", userPhone=" + userPhone + ", userSex="
				+ userSex + "]";
	}
    
    
}
