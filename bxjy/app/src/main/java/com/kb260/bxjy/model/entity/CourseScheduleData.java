package com.kb260.bxjy.model.entity;

/**
 * @author KB260
 *         Created on  2018/2/28
 */

public class CourseScheduleData {

    /**
     * teacherName : 钢铁侠
     * courseDuration : 40
     * describe : 钢铁侠大战巨蟹
     * create_time : 1517560271000
     * courseId : 1
     * courseName : 视频-钢铁侠
     */

    private String teacherName;
    private int courseDuration;
    private String describe;
    private long create_time;
    private int courseId;
    private String courseName;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(int courseDuration) {
        this.courseDuration = courseDuration;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
