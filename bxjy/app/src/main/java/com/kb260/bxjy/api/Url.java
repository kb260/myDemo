package com.kb260.bxjy.api;

/**
 * @author  KB260
 * Created on  2017/12/28
 */
public class Url {

    public static final String BASE_URL = "http://47.52.65.163:8080/learned/";//线上
    //public static final String BASE_URL = "http://192.168.11.132:8095/";//线下

    /**
     * 用户注册
     */
    public static final String REGISTER = BASE_URL+"/api/open/common/register";
    /**
     * 发送验证码
     */
    public static final String SEND_CODE = BASE_URL+"/api/open/common/sendCode";
    /**
     * 验证码登录
     */
    public static final String LOGIN_CHECK_CODE = BASE_URL+"/api/open/common/loginCheckCode";

    /**
     * 密码登录
     */
    public static final String LOGIN_CHECK_PASSWORD = BASE_URL+"/api/open/common/loginCheckPassword";
    /**
     * 忘记密码，设置新密码
     */
    public static final String SET_NEW_PASSWORD = BASE_URL+"/api/open/common/setNewPassword";
    /**
     * 检测验证码是否正确
     */
    public static final String CHECK_CODE = BASE_URL+"/api/open/common/checkCode";

    /**
     * 首页轮播图
     */
    public static final String BANNER = BASE_URL+"/api/app/recommend/banner";

    /**
     * 首页直播推荐
     */
    public static final String LIVE_STREAM_LIMIT = BASE_URL+"/api/app/recommend/liveStreamLimit";

    /**
     * 个人中心
     */
    public static final String PERSON_INFO = BASE_URL+"/api/app/user/personInfo";

    /**
     * 账户安全-修改密码
     */
    public static final String RESET_PASSWORD = BASE_URL+"/api/app/user/resetPassword";

    /**
     * 个人中心-修改头像
     */
    public static final String RESET_HEAD_IMG = BASE_URL+"/api/app/user/resetHeadImg";

    /**
     * 个人中心-修改昵称
     */
    public static final String RESET_NAME = BASE_URL+"/api/app/user/resetName";

    /**
     * 个人中心-修改性别
     */
    public static final String RESET_SEX = BASE_URL+"/api/app/user/resetSex";

    /**
     * 首页-直播推荐(更多)
     */
    public static final String LIVE_STREAM = BASE_URL+"/api/app/recommend/liveStream";

    /**
     * 首页-热播榜(推荐)
     */
    public static final String TOP_BROADCAST_LIMIT = BASE_URL+"/api/app/recommend/topBroadcastLimit";

    /**
     * 首页-热播榜(更多)
     */
    public static final String TOP_BROAD_CAST = BASE_URL+"/api/app/recommend/topBroadcast";

    /**
     * 首页-教师榜(推荐)
     */
    public static final String TOP_TEACHER_LIMIT = BASE_URL+"/api/app/recommend/topTeacherLimit";

    /**
     * 首页-教师榜(更多)
     */
    public static final String TOP_TEACHER = BASE_URL+"/api/app/recommend/topTeacher";

    /**
     * 公开课-即将开课（推荐）
     */
    public static final String UPCOMING_COURSE_LIMIT = BASE_URL+"/api/app/recommend/upcomingCourseLimit";

    /**
     * 公开课-即将开课（更多）
     */
    public static final String UPCOMING_COURSE_MORE = BASE_URL+"/api/app/recommend/upcomingCourseMore";

    /**
     * 公开课-正在开课（推荐）
     */
    public static final String UNDERWAY_COURSE_GROUP_LIMIT = BASE_URL+"/api/app/recommend/underwayCourseGroupLimit";

    /**
     * 公开课-正在开课（更多）
     */
    public static final String UNDERWAY_COURSE_GROUP_MORE = BASE_URL+"/api/app/recommend/underwayCourseGroupMore";

    /**
     * 公开课-已经开课（推荐）
     */
    public static final String ALREADY_COURSE_GROUP_LIMIT = BASE_URL+"/api/app/recommend/alreadyCourseGroupLimit";

    /**
     * 公开课-已经开课（更多）
     */
    public static final String ALREADY_COURSE_GROUP_MORE = BASE_URL+"/api/app/recommend/alreadyCourseGroupMore";

    /**
     * 课程分类-科目-获取科目信息（推荐）
     */
    public static final String SUBJECT_LIMIT = BASE_URL+"/api/app/classify/subjectLimit";

    /**
     * 课程分类-科目-获取课程组(更多)
     */
    public static final String SUBJECT = BASE_URL+"/api/app/classify/subject";

    /**
     * 课程分类-教师-获取课程组（推荐）
     */
    public static final String TEACHER_LIMIT = BASE_URL+"/api/app/classify/teacherLimit";

    /**
     * 课程分类-教师-获取课程组（更多）
     */
    public static final String TEACHER = BASE_URL+"/api/app/classify/teacher";

    /**
     * 点击率-获取课程组（推荐）
     */
    public static final String CLICK_RATE_LIMIT = BASE_URL+"/api/app/classify/clickRateLimit";

    /**
     * 是否付费-筛选
     */
    public static final String COURSE_IS_PAY = BASE_URL+"/api/app/classify/CourseIsPay";
    /**
     * 教师检索
     */
    public static final String TEACHER_SEARCH = BASE_URL+"/api/app/teacher/teacherSearch";
    /**
     * 教师介绍
     */
    public static final String TEACHER_DETAIL = BASE_URL+"/api/app/teacher/teacherDetail";
    /**
     * 教师介绍-学生评价
     */
    public static final String TEACHER_EVALUATES = BASE_URL+"/api/app/teacher/teacherEvaluates";

    /**
     * 教师介绍-在售课程
     */
    public static final String TEACHER_COURSE = BASE_URL+"/api/app/teacher/teacherCourse";

    /**
     * 课程组详情
     */
    public static final String GROUP_DETAIL = BASE_URL+"/api/app/course/groupDetail";

    /**
     * 课程详情-课程表
     */
    public static final String COURSE_SCHEDULE = BASE_URL+"/api/app/course/CourseSchedule";

    /**
     * 课程详情-老师介绍
     */
    public static final String TEACHER_INTRODUCE = BASE_URL+"/api/app/course/teacherIntroduce";

    /**
     * 检索(1-直播推荐;2-热播榜;3-即将开课;4-正在开课;5-已经开课)
     */
    public static final String VIDEO_SEARCH = BASE_URL+"/api/app/course/videoSearch";

}
