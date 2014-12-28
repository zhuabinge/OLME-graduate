package com.olme.domain;

import java.util.List;

/**
 * Created by Bingo on 2014/8/13.
 */
public class CourseType {
    private int typeId;
    private String typeName;
    private String typePhoto;
    private String typeDescription;
    private String typeTeacherName;
    private Long typeTotalNum;   //课程总数

    public CourseType() {
        super();
    }

    public CourseType(int typeId, String typeName, String typePhoto, String typeDescription, String typeTeacherName, Long typeTotalNum) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.typePhoto = typePhoto;
        this.typeDescription = typeDescription;
        this.typeTeacherName =this.typeTeacherName;
        this.typeTotalNum = typeTotalNum;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypePhoto() {
        return typePhoto;
    }

    public void setTypePhoto(String typePhoto) {
        this.typePhoto = typePhoto;
    }

    public String getTypeDescription() {
        return typeDescription;
    }

    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }

    public String getTypeTeacherName() {
        return typeTeacherName;
    }

    public void setTypeTeacherName(String typeTeacherName) {
        this.typeTeacherName = typeTeacherName;
    }

    public Long getTypeTotalNum() {
        return typeTotalNum;
    }

    public void setTypeTotalNum(Long typeTotalNum) {
        this.typeTotalNum = typeTotalNum;
    }

    @Override
    public String toString() {
        return "CourseType{" +
                "typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                ", typePhoto='" + typePhoto + '\'' +
                ", typeDescription='" + typeDescription + '\'' +
                ", typeTeacherName='" + typeTeacherName + '\'' +
                ", typeTotalNum=" + typeTotalNum +
                '}';
    }
}
