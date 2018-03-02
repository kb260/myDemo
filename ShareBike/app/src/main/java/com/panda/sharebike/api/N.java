package com.panda.sharebike.api;

/**
 * author : 宁立森
 * e-mail : byning2012@163.com
 * time   : 2017/07/13
 * desc   :
 * version: 1.0
 */
public class N {
    public static String SHARE_TOKEN_KEY = "SHARE_TOKEN_KEY";
    public static String SHARE_TOKEN = "token";
    //测试网址
    public static String BASE_URL_TEST = "192.168.0.177:91/";
    public static String BASE_URL_FORMAL = "";

    /**
     * 注册登录
     */
    //游客
    public static final String KEY_GUESTLOGIN = "qbike/auth/member/guestlogin";
    //注册验证码
    public static final String KEY_REGCODE = "qbike/auth/member/regcode";
    //注册
    public static final String KEY_REG = "qbike/auth/member/reg";
    //登录
    public static final String KEY_LOGIN = "qbike/auth/member/login";
    //登录验证码
    public static final String KEY_LOGINCODE = "qbike/auth/member/logincode";
    //验证码登录
    public static final String KEY_LOGINBYCODE = "qbike/auth/member/loginbycode";
    //忘记密码，发送验证码
    public static final String KEY_RESETCODE = "qbike/auth/member/pwd/resetcode";
    //忘记密码，设置新密码
    public static final String KEY_RESET = "qbike/auth/member/pwd/reset";


    /**
     * 个人中心
     */
    //实名认证
    public static final String KEY_REALNAMEAUTH = "qbike/member/realnameauth";
    //修改密码，发验证码
    public static final String KEY_EDITCODE = "qbike/member/pwd/editcode";
    //修改密码，设置新密码
    public static final String KEY_EDIT = "qbike/member/pwd/edit";
    //更换绑定手机验证码
    public static final String KEY_BINDCELLPHONECODE = "qbike/member/bindcellphonecode";
    //更换绑定手机
    public static final String KEY_BINGNEWCELLPHONE = "qbike/member/bindnewcellphone";
    //退出登录
    public static final String KEY_LOGOUT = "qbike/member/logout";
    //编辑昵称
    public static final String KEY_NICKNAME = "qbike/member/nickname";
    //编辑头像
    public static final String KEY_AVATAR = "qbike/member/avatar";
    //会员信息
    public static final String KEY_MEMBERINFO = "qbike/member/memberinfo";
    //会员中心
    public static final String KEY_MEMBERCENTER = "qbike/member/membercenter";
    //消息列表
    public static final String KEY_MEMBERMSGS = "qbike/member/msgbox/membermsgs";
    //消息详情
    public static final String KEY_DETAIL = "qbike/member/msgbox/detail";
    //判断登录与否
    public static final String KEY_LOGIN_DATA = "qbike/member/data";

    /**
     * 我的钱包
     */
    //个人钱包页
    public static final String KEY_MEMBERWALLET = "qbike/member/wallet/memberwallet";
    //查看会员钱包流水
    public static final String KEY_MEMBERACCOUNNTLOGFORLL = "qbike/member/wallet/memberaccountlogforall";
    //充值列表
    public static final String KEY_PAYLIST = "qbike/member/wallet/paylist";
    //充值
    public static final String KEY_RECHARGE = "qbike/member/wallet/order/recharge";
    //充押金
    public static final String KEY_DEPOSIT = "qbike/member/wallet/order/deposit";
    //退押金-申请
    public static final String KEY_REFUNDDEPOSIT = "qbike/member/wallet/refunddeposit";


    /**
     * 骑行
     */
    //index
    public static final String KEY_INDEX = "qbike/member/ride/index";
    //预约
    public static final String KEY_CREATE = "qbike/member/ride/subscribe/create";
    //取消预约
    public static final String KEY_CANCEL = "qbike/member/ride/subscribe/cancel";
    //租车
    public static final String KEY_RENT = "qbike/member/ride/rent";
    //场地还车
    public static final String KEY_FINISH = "qbike/member/ride/finish";
    //我的行程
    public static final String KEY_RECORDS = "qbike/member/ride/records";
    //行程详情
    public static final String KEY_DETAIL_RIDE = "qbike/member/ride/record/detail";
    //故障报修
    public static final String KEY_MALFUNCTION = "qbike/member/ride/malfunction";
    //故障报修，上传图片
    public static final String KEY_REPOST_IMG = "/qbike/upload/img";
    public static final String GET_GUIDE = "/qbike/auth/member/getGuide";
    public static final String GET_VERSION = "/qbike/auth/member/getVersion";
    public static final String GET_LOCK = "qbike/member/ride/getLock";
}
