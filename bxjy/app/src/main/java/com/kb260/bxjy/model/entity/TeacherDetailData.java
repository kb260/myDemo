package com.kb260.bxjy.model.entity;

/**
 * @author KB260
 *         Created on  2018/2/27
 */

public class TeacherDetailData {

    /**
     * teacherId : 1
     * name : 钢铁侠
     * describe : ********牛逼
     * primaryCoverage : ***武器制造
     * score : 9.7
     * headImg : null
     * teacherLevel : 博学资深讲师
     */

    private int teacherId;
    private String name;
    private String describe;
    private String primaryCoverage;
    private double score;
    private Object headImg;
    private String teacherLevel;

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getPrimaryCoverage() {
        return primaryCoverage;
    }

    public void setPrimaryCoverage(String primaryCoverage) {
        this.primaryCoverage = primaryCoverage;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Object getHeadImg() {
        return headImg;
    }

    public void setHeadImg(Object headImg) {
        this.headImg = headImg;
    }

    public String getTeacherLevel() {
        return teacherLevel;
    }

    public void setTeacherLevel(String teacherLevel) {
        this.teacherLevel = teacherLevel;
    }
}
