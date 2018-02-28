package com.kb260.bxjy.model.entity;

/**
 * @author KB260
 *         Created on  2018/2/27
 */

public class TeacherLimitData {

    /**
     * teacherId : 6
     * teacherName : 孙悟空
     * courseGroup : null
     */

    private int teacherId;
    private String teacherName;
    private Object courseGroup;

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Object getCourseGroup() {
        return courseGroup;
    }

    public void setCourseGroup(Object courseGroup) {
        this.courseGroup = courseGroup;
    }
}
