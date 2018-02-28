package com.kb260.gxdk.api;

/**
 * @author  KB260
 * Created on  2017/12/28
 */
public class Url {

    public static final String BASE_URL = "http://112.124.110.38:8081/";//线上
    //public static final String BASE_URL = "http://192.168.11.132:8095/";//线下
    //public static final String BASE_URL = "http://192.168.31.176:8081/shareloan/";
    //public static final String BASE_URL = "http://192.168.31.176:8081/";
    //public static final String BASE_URL = "http://hs4uvr.natappfree.cc/";

    /**
     * 获取注册验证码
     */
    public static final String REGISTRATIONCODE = BASE_URL+"/api/appuser/registrationcode";
    /**
     * 获取忘记密码验证码
     */
    public static final String FORGETCODE = BASE_URL+"/api/appuser/forgetcode";
    /**
     * 注册
     */
    public static final String REGISTRATION = BASE_URL+"/api/appuser/registration";
    /**
     * 登录
     */
    public static final String APPLOGIN = BASE_URL+"/api/appuser/applogin";
    /**
     * 忘记密码
     */
    public static final String FORGETPASSWORD = BASE_URL+"/api/appuser/forgetpassword";
    /**
     * 修改密码
     */
    public static final String MODIFYPASSWORD = BASE_URL+"/api/appuser/modifypassword";
    /**
     * 修改个人资料
     */
    public static final String UPPERON = BASE_URL+"/api/appuser/upperon";

    /**
     * 资讯查询
     */
    public static final String SELTIPS = BASE_URL+"/api/appuser/seltips";
    /**
     * 小窍门查询
     */
    public static final String SELINFORATION = BASE_URL+"/api/appuser/selinforation";
    /**
     * 申请经理
     */
    public static final String MAKEAGENT = BASE_URL+"/api/appuser/makeagent";
    /**
     * 申请银行经理
     */
    public static final String MAKEBANK = BASE_URL+"/api/appuser/makebank";
    /**
     * 保存房贷
     */
    public static final String SAVEROOMLOAN = BASE_URL+"/api/credit/saveroomloan";

    /**
     * 查询支付押金
     */
    public static final String SELPAYSCORE = BASE_URL+"/api/credit/selpayscore";
    /**
     * 查询是否设置个支付密码
     */
    public static final String SELPAYWORD = BASE_URL+"/api/credit/selpayword";
    /**
     * 积分支付
     */
    public static final String SCOREPAY = BASE_URL+"/api/credit/scorepay";
    /**
     * 忘记支付密码
     */
    public static final String FORGETPAYPW = BASE_URL+"/api/appuser/forgetpaypw";

    /**
     * 修改支付密码
     */
    public static final String UPPAYPW = BASE_URL+"/api/appuser/uppaypw";
    /**
     * 设置支付密码
     */
    public static final String SETPAYPW = BASE_URL+"/api/appuser/setpaypw";
    /**
     * 获取车品牌
     */
    public static final String SELCARBRAND = BASE_URL+"/api/credit/selcarbrand";

    /**
     * 根据品牌获取所有车型接口
     */
    public static final String SELCARBRANDTYPE = BASE_URL+"/api/credit/selcarbrandtype";

    /**
     * 根据ID获取车型详情接口
     */
    public static final String SELCARBRANDTYPEDETAIL = BASE_URL+"/api/credit/selcarbrandtypedetail";

    /**
     * 查询是有车还是有房贷
     */
    public static final String SELSIROOMCAR = BASE_URL+"/api/credit/selsiroomcar";

    /**
     * 查询个人资料
     */
    public static final String SELPERON = BASE_URL+"/api/account/selperon";

    /**
     * 立即评估
     */
    public static final String SAVEEVALUATE = BASE_URL+"/api/gxdk/saveevaluate";

    /**
     * 查询我的申请
     */
    public static final String SELROOMLOAN = BASE_URL+"/api/credit/selroomloan";
    /**
     * 我的申请详情查询（该接口需要展示初审或终审时调其它情况直接从上个页面获取数据）
     */
    public static final String SELORDERDETAIL = BASE_URL+"/api/credit/selorderdetail";
    /**
     * 获取首页轮播图
     */
    public static final String SELROASTING = BASE_URL+"/api/gxdk/selroasting";
    /**
     * 获取协议
     */
    public static final String SELPROTOCOL = BASE_URL+"/api/gxdk/selprotocol";
    /**
     * 查询我的产品
     */
    public static final String SELROOMPRODUCT = BASE_URL+"/api/grab/selroomproduct";
    /**
     * 保存房贷车贷产品（type=1 房贷 2车贷）
     */
    public static final String SAVEROOMPRODUCT = BASE_URL+"/api/grab/saveroomproduct";
    /**
     * 抢单首页接口
     */
    public static final String GRABSELROOMLOAN = BASE_URL+"/api/grab/selroomloan";
    /**
     * 抢单
     */
    public static final String GRABORDER = BASE_URL+"/api/grab/graborder";
    /**
     * 我的团队
     */
    public static final String SELMYTEAM = BASE_URL+"/api/appuser/selmyteam";
    /**
     * 你的申请已推荐
     */
    public static final String SELMYTEAMONE = BASE_URL+"/api/appuser/selmyteamone";
    /**
     * 你的已申请二级已推荐
     */
    public static final String SELMYTEAMTWO = BASE_URL+"/api/appuser/selmyteamtwo";
    /**
     * 你的未申请推荐
     */
    public static final String SELMYTEAMTHREE = BASE_URL+"/api/appuser/selmyteamthree";

    /**
     * 点击我的
     */
    public static final String SELMYACCOUNT = BASE_URL+"/api/account/selmyaccount";

    /**
     * 点击增加
     */
    public static final String CLICKMYSCOREADD = BASE_URL+"/api/account/clickmyscoreadd";

    /**
     * 点击我的积分
     */
    public static final String CLICKMYSCORE = BASE_URL+"/api/account/clickmyscore";

    /**
     * 点击支出
     */
    public static final String CLICKMYSCORESUB = BASE_URL+"/api/account/clickmyscoresub";
    /**
     * 历史记录
     */
    public static final String SELHISTORY = BASE_URL+"/api/account/selchargehistory";

    /**
     * 绑定银行卡
     */
    public static final String SAVEBANK = BASE_URL+"/api/bank/savebank";
    /**
     * 查询我的银行卡
     */
    public static final String SELBANK = BASE_URL+"/api/bank/selbank";
    /**
     * 解绑银行卡
     */
    public static final String DELBANK = BASE_URL+"/api/bank/delbank";

    /**
     * 获取可提现积分 type充值1 2奖励
     */
    public static final String GETSCOREABLE = BASE_URL+"/api/bank/getscoreable";
    /**
     * 提现记录 0申请中
     */
    public static final String SELDEPOSITHISTORY = BASE_URL+"/api/bank/seldeposithistory";
    /**
     * 保存提现记录
     */
    public static final String SAVEDEPOSIT = BASE_URL+"/api/bank/savedeposit";
    /**
     * 保存意见反馈
     */
    public static final String SAVEFEEDBACK = BASE_URL+"/api/gxdk/savefeedback";
    /**
     * 关于共享贷款
     */
    public static final String SELABOUTGXDK = BASE_URL+"/api/gxdk/selaboutgxdk";
    /**
     * 关于我们
     */
    public static final String SELABOUTUS = BASE_URL+"/api/gxdk/selaboutus";
    /**
     * 查询版本
     */
    public static final String SELVERSION = BASE_URL+"/api/gxdk/selversion";
    /**
     * 我的消息
     */
    public static final String SELMYMESSAGE = BASE_URL+"/api/gxdk/selmymessage";

    /**
     * 初审点击确认提交
     */
    public static final String GIVEFIRSTRESULT = BASE_URL+"/api/grab/givefirstresult";
    /**
     * 点击终审查询详情返回信息
     */
    public static final String CLICKLAST = BASE_URL+"/api/grab/clicklast";
    /**
     * 给出终审
     */
    public static final String GIVELASTRESULT = BASE_URL+"/api/grab/givelastresult";
    /**
     * 点击确认贷款查询详情
     */
    public static final String CLICKOK = BASE_URL+"/api/grab/clickok";
    /**
     * 点击确认放贷
     */
    public static final String SURE = BASE_URL+"/api/grab/sure";
    /**
     * 点击成功查询
     */
    public static final String CLICKSUCCESS = BASE_URL+"/api/grab/clicksuccess";
    /**
     * 查询支付剩余的积分
     */
    public static final String SELSCOREPAYLAST = BASE_URL+"/api/credit/selscorepaylast";
    /**
     * 申请解锁
     */
    public static final String APPLYOPEN = BASE_URL+"/api/grab/applyopen";
    /**
     * 保存信贷
     */
    public static final String SAVECREDI = BASE_URL+"/api/credit/savecredi";
    /**
     * 支付宝充值
     */
    public static final String ALI = BASE_URL+"/api/pay/ali";
    /**
     * 充值页面返回的数据
     */
    public static final String SHENSCORE = BASE_URL+"/api/account/shenscore";
    /**
     * 点击商城
     */
    public static final String SELSHOP = BASE_URL+"/api/shop/selshop";
    /**
     * 提现中获取手机验证码
     */
    public static final String CODE = BASE_URL+"/api/appuser/code";

    /**
     * 帮好友注册
     */
    public static final String HELPREG = BASE_URL+"/api/appuser/helpreg";
    /**
     * 申请记录
     */
    public static final String RECORD = BASE_URL+"/api/appuser/record";
    /**
     * 取消订单接口
     */
    public static final String CANCEL = BASE_URL+"/api/credit/cancel";
    /**
     * 取消申请
     */
    public static final String CANLEAPPLY = BASE_URL+"/api/appuser/canleapply";
    /**
     * 我的订单
     */
    public static final String SELMYORDER = BASE_URL+"/api/shop/selmyorder";
    /**
     * 我的地址
     */
    public static final String SELMYADDRESS = BASE_URL+"/api/shop/selmyaddress";
    /**
     * 用户确认贷款
     */
    public static final String USERSURE = BASE_URL+"/api/grab/usersure";
    /**
     * 修改申请
     */
    public static final String UPDATAAPPLY = BASE_URL+"/api/appuser/updataapply";
    /**
     * 信贷查询
     */
    public static final String SELECTCREDITSS = BASE_URL+"/api/credit/selectcreditss";
    /**
     * 虚拟电话
     */
    public static final String AXBBANDING = BASE_URL+"/api/credit/axbbanding";
    /**
     * 虚拟电话
     */
    public static final String SELCARLIST = BASE_URL+"/api/credit/selcarlist";
    /**
     * 车辆评估
     */
    public static final String CARPINGGU = BASE_URL+"/api/credit/carpinggu";
    /**
     * 二维码
     */
    public static final String SHARE = BASE_URL+"/api/gxdk/share";
    /**
     * 分享注册
     */
    public static final String ZHUCE = BASE_URL+"/api/gxdk/zhuce";
    /**
     * 分享注册
     */
    public static final String TELPHONE = BASE_URL+"/api/credit/telphoness";
    /**
     * 分享注册
     */
    public static final String GRAB = BASE_URL+"/api/grab/getmin";
    /**
     * 银行卡支付
     */
    public static final String CASH = BASE_URL+"/api/cash";
    /**
     * 删除产品
     */
    public static final String DELROOMPRODUCT = BASE_URL+"/api/grab/delroomproduct";
    /**
     * 删除房贷车贷
     */
    public static final String DELROOMLOAN = BASE_URL+"/api/credit/delroomloan";

    /**
     * 提交故障订单
     */
    public static final String SAVEFAULT = BASE_URL+"/api/faults/savefault";

    /**
     * 获取楼盘
     */
    public static final String LOUPAN = BASE_URL+"/api/third/loupan";

    /**
     * 获取楼栋
     */
    public static final String BUILD = BASE_URL+"/api/third/build";

    /**
     * 获取楼层
     */
    public static final String CENG = BASE_URL+"/api/third/ceng";

    /**
     * 获取房子信息
     */
    public static final String AREA = BASE_URL+"/api/third/area";

}
