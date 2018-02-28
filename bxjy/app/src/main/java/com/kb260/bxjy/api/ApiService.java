package com.kb260.bxjy.api;

import com.kb260.bxjy.model.entity.BannerData;
import com.kb260.bxjy.model.entity.ClassData;
import com.kb260.bxjy.model.entity.ClassDetailData;
import com.kb260.bxjy.model.entity.CodeData;
import com.kb260.bxjy.model.entity.CourseScheduleData;
import com.kb260.bxjy.model.entity.LiveRecommendedData;
import com.kb260.bxjy.model.entity.PersonInfoData;
import com.kb260.bxjy.model.entity.RateLimitData;
import com.kb260.bxjy.model.entity.SubjectLimitData;
import com.kb260.bxjy.model.entity.TeacherCourseData;
import com.kb260.bxjy.model.entity.TeacherDetailData;
import com.kb260.bxjy.model.entity.TeacherEvaluatesData;
import com.kb260.bxjy.model.entity.TeacherLimitData;
import com.kb260.bxjy.model.entity.UserData;
import java.util.List;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * @author KB260
 *         Created on  2017/11/15
 */

public interface ApiService {

    //注册
    @FormUrlEncoded
    @POST(Url.REGISTER)
    Observable<BaseModel<UserData>> register(
            @Field("phone") String phone,
            @Field("password") String password,
            @Field("password2") String password2,
            @Field("code") String code
    );

    //发送验证码
    @FormUrlEncoded
    @POST(Url.SEND_CODE)
    Observable<BaseModel<CodeData>> sendCode(
            @Field("phone") String phone,
            @Field("type") String type//1-用户注册；2-修改密码；3-验证码登录
    );

    //验证码登录
    @FormUrlEncoded
    @POST(Url.LOGIN_CHECK_CODE)
    Observable<BaseModel<UserData>> loginCheckCode(
            @Header("token") String token,
            @Field("phone") String phone,
            @Field("code") String code
    );

    //密码登录
    @FormUrlEncoded
    @POST(Url.LOGIN_CHECK_PASSWORD)
    Observable<BaseModel<UserData>> loginCheckPassword(
            @Header("token") String token,
            @Field("phone") String phone,
            @Field("password") String password
    );

    //忘记密码，设置新密码
    @FormUrlEncoded
    @POST(Url.SET_NEW_PASSWORD)
    Observable<BaseModel<String>> setNewPassword(
            @Field("phone") String phone,
            @Field("password") String password,
            @Field("password2") String password2
    );

    //检测验证码是否正确
    @FormUrlEncoded
    @POST(Url.CHECK_CODE)
    Observable<BaseModel<String>> checkCode(
            @Header("token") String token,
            @Field("phone") String phone,
            @Field("code") String code
    );

    //首页，轮播图
    @FormUrlEncoded
    @POST(Url.BANNER)
    Observable<BaseModel<List<BannerData>>> banner(
            @Header("token") String token,
            @Field("count") String count
    );

    //首页-直播推荐
    @POST(Url.LIVE_STREAM_LIMIT)
    Observable<BaseModel<List<LiveRecommendedData>>> liveStreamLimit(
            @Header("token") String token
    );

    //个人中心
    @POST(Url.PERSON_INFO)
    Observable<BaseModel<PersonInfoData>> personInfo(
            @Header("token") String token
    );

    //账户安全-修改密码
    @FormUrlEncoded
    @POST(Url.RESET_PASSWORD)
    Observable<BaseModel<String>> resetPassword(
            @Header("token") String token,
            @Field("oldPassword") String oldPassword,
            @Field("newPassword1") String newPassword1,
            @Field("newPassword2") String newPassword2
    );

    //账户安全-修改头像
    @FormUrlEncoded
    @POST(Url.RESET_HEAD_IMG)
    Observable<BaseModel<String>> resetHeadImg(
            @Header("token") String token,
            @Field("img") String img
    );

    //账户安全-修改昵称
    @FormUrlEncoded
    @POST(Url.RESET_NAME)
    Observable<BaseModel<String>> resetName(
            @Header("token") String token,
            @Field("name") String name
    );

    //账户安全-修改性别
    @FormUrlEncoded
    @POST(Url.RESET_SEX)
    Observable<BaseModel<String>> resetSex(
            @Header("token") String token,
            @Field("sex") String sex   //1:女；0-男；
    );

    //首页-直播推荐(更多)
    @FormUrlEncoded
    @POST(Url.LIVE_STREAM)
    Observable<BaseModel<List<ClassData>>> liveStream(
            @Header("token") String token,
            @Field("page") int page,
            @Field("rows") int rows
    );

    //首页-热播榜(推荐)
    @POST(Url.TOP_BROADCAST_LIMIT)
    Observable<BaseModel<List<ClassData>>> topBroadcastLimit(
            @Header("token") String token
    );

    //首页-热播榜(更多)
    @FormUrlEncoded
    @POST(Url.LIVE_STREAM)
    Observable<BaseModel<List<ClassData>>> topBroadcast(
            @Header("token") String token,
            @Field("page") int page,
            @Field("rows") int rows
    );

    //首页-教师榜(推荐)
    @POST(Url.TOP_BROADCAST_LIMIT)
    Observable<BaseModel<List<TeacherLimitData>>> topTeacherLimit(
            @Header("token") String token
    );

    //首页-教师榜(更多)
    @FormUrlEncoded
    @POST(Url.LIVE_STREAM)
    Observable<BaseModel<List<TeacherLimitData>>> topTeacher(
            @Header("token") String token,
            @Field("page") int page,
            @Field("rows") int rows
    );

    //公开课-即将开课（推荐）
    @POST(Url.UPCOMING_COURSE_LIMIT)
    Observable<BaseModel<List<ClassData>>> upcomingCourseLimit(
            @Header("token") String token
    );

    //公开课-即将开课（更多）
    @FormUrlEncoded
    @POST(Url.UPCOMING_COURSE_MORE)
    Observable<BaseModel<List<ClassData>>> upcomingCourseMore(
            @Header("token") String token,
            @Field("page") int page,
            @Field("rows") int rows
    );

    //公开课-正在开课（推荐）
    @POST(Url.UNDERWAY_COURSE_GROUP_LIMIT)
    Observable<BaseModel<List<ClassData>>> underwayCourseGroupLimit(
            @Header("token") String token
    );

    //公开课-正在开课（更多）
    @FormUrlEncoded
    @POST(Url.UNDERWAY_COURSE_GROUP_MORE)
    Observable<BaseModel<List<ClassData>>> underwayCourseGroupMore(
            @Header("token") String token,
            @Field("page") int page,
            @Field("rows") int rows
    );

    //公开课-已经开课（推荐）
    @POST(Url.ALREADY_COURSE_GROUP_LIMIT)
    Observable<BaseModel<List<ClassData>>> alreadyCourseGroupLimit(
            @Header("token") String token
    );

    //公开课-已经开课（更多）
    @FormUrlEncoded
    @POST(Url.ALREADY_COURSE_GROUP_MORE)
    Observable<BaseModel<List<ClassData>>> alreadyCourseGroupMore(
            @Header("token") String token,
            @Field("page") int page,
            @Field("rows") int rows
    );

    //课程分类-科目-获取科目信息（推荐）
    @FormUrlEncoded
    @POST(Url.SUBJECT_LIMIT)
    Observable<BaseModel<List<SubjectLimitData>>> subjectLimit(
            @Header("token") String token,
            @Field("page") int page,
            @Field("rows") int rows
    );

    //课程分类-科目-获取课程组(更多)
    @FormUrlEncoded
    @POST(Url.UPCOMING_COURSE_MORE)
    Observable<BaseModel<List<SubjectLimitData>>> subject(
            @Header("token") String token,
            @Field("page") int page,
            @Field("subjectId") int subjectId,
            @Field("rows") int rows
    );

    //课程分类-教师-获取课程组（推荐）
    @FormUrlEncoded
    @POST(Url.TEACHER_LIMIT)
    Observable<BaseModel<List<TeacherLimitData>>> teacherLimit(
            @Header("token") String token,
            @Field("page") int page,
            @Field("rows") int rows
    );

    //课程分类-教师-获取课程组（更多）
    @FormUrlEncoded
    @POST(Url.TEACHER)
    Observable<BaseModel<List<TeacherLimitData>>> teacher(
            @Header("token") String token,
            @Field("page") int page,
            @Field("teacherId") int teacherId,
            @Field("rows") int rows
    );

    //点击率-获取课程组（推荐）
    @FormUrlEncoded
    @POST(Url.CLICK_RATE_LIMIT)
    Observable<BaseModel<List<RateLimitData>>> clickRateLimit(
            @Header("token") String token,
            @Field("page") int page,
            @Field("type") int type,//	0-降序；1-升序
            @Field("rows") int rows
    );

    //是否付费-筛选
    @FormUrlEncoded
    @POST(Url.COURSE_IS_PAY)
    Observable<BaseModel<List<RateLimitData>>> courseIsPay(
            @Header("token") String token,
            @Field("page") int page,
            @Field("type") int type,//	0-免费；1-付费
            @Field("rows") int rows
    );

    //教师检索
    @FormUrlEncoded
    @POST(Url.TEACHER_SEARCH)
    Observable<BaseModel<List<TeacherLimitData>>> teacherSearch(
            @Header("token") String token,
            @Field("condition") String condition
    );

    //教师介绍
    @FormUrlEncoded
    @POST(Url.TEACHER_DETAIL)
    Observable<BaseModel<TeacherDetailData>> teacherDetail(
            @Header("token") String token,
            @Field("teacherId") int teacherId
    );

    //教师介绍-学生评价
    @FormUrlEncoded
    @POST(Url.TEACHER_EVALUATES)
    Observable<BaseModel<List<TeacherEvaluatesData>>> teacherEvaluates(
            @Header("token") String token,
            @Field("teacherId") int teacherId,
            @Field("page") int page,
            @Field("rows") int rows
    );

    //教师介绍-在售课程
    @FormUrlEncoded
    @POST(Url.TEACHER_COURSE)
    Observable<BaseModel<List<TeacherCourseData>>> teacherCourse(
            @Header("token") String token,
            @Field("teacherId") int teacherId,
            @Field("page") int page,
            @Field("rows") int rows
    );

    //课程组详情
    @FormUrlEncoded
    @POST(Url.GROUP_DETAIL)
    Observable<BaseModel<ClassDetailData>> groupDetail(
            @Header("token") String token,
            @Field("CourseGroupId") String courseGroupId
    );

    //课程详情-课程表
    @FormUrlEncoded
    @POST(Url.COURSE_SCHEDULE)
    Observable<BaseModel<List<CourseScheduleData>>> courseSchedule(
            @Header("token") String token,
            @Field("CourseGroupId") String courseGroupId,
            @Field("page") int page,
            @Field("rows") int rows
    );

    //课程详情-老师介绍
    @POST(Url.TEACHER_INTRODUCE)
    Observable<BaseModel<List<TeacherDetailData>>> teacherIntroduce(
            @Header("token") String token
    );

    //检索(1-直播推荐;2-热播榜;3-即将开课;4-正在开课;5-已经开课)
    @POST(Url.VIDEO_SEARCH)
    Observable<BaseModel<List<TeacherCourseData>>> videoSearch(
            @Header("token") String token
    );

}
