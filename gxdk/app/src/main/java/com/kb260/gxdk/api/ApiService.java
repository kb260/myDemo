package com.kb260.gxdk.api;

import com.kb260.gxdk.entity.AliPay;
import com.kb260.gxdk.entity.ApplicationLogNew;
import com.kb260.gxdk.entity.BannerData;
import com.kb260.gxdk.entity.ChargeScore;
import com.kb260.gxdk.entity.HouseDetail;
import com.kb260.gxdk.entity.LouDong;
import com.kb260.gxdk.entity.Loupan;
import com.kb260.gxdk.entity.MyCreditData;
import com.kb260.gxdk.entity.MyTeamOne;
import com.kb260.gxdk.entity.NewPlate;
import com.kb260.gxdk.entity.PlateDetailNew;
import com.kb260.gxdk.entity.PlateDetailThree;
import com.kb260.gxdk.entity.Score;
import com.kb260.gxdk.entity.WithdrawLog;
import com.kb260.gxdk.model.AboutApp;
import com.kb260.gxdk.model.AboutMe;
import com.kb260.gxdk.model.CarAccess;
import com.kb260.gxdk.model.Card;
import com.kb260.gxdk.model.HouseAccess;
import com.kb260.gxdk.model.Information;
import com.kb260.gxdk.model.IntegralDetail;
import com.kb260.gxdk.model.IntegralLog;
import com.kb260.gxdk.model.LoginSuccess;
import com.kb260.gxdk.model.Me;
import com.kb260.gxdk.model.MeHome;
import com.kb260.gxdk.model.MyApplication;
import com.kb260.gxdk.model.MyProduct;
import com.kb260.gxdk.model.Order;
import com.kb260.gxdk.model.Protocol;
import com.kb260.gxdk.model.Version;
import java.util.List;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * @author  KB260
 * Created on  2017/11/15
 */

public interface ApiService {
    /**
     * 获取注册验证码
     */
    @FormUrlEncoded
    @POST(Url.REGISTRATIONCODE)
    Observable<BaseModel<String>> registrationcode(
            //@Header("token") String token,
            @Field("username") String username
    );

    /**
     * 获取忘记密码验证码
     */
    @FormUrlEncoded
    @POST(Url.FORGETCODE)
    Observable<BaseModel<String>> forgetcode(
            //@Header("token") String token,
            @Field("username") String username
    );

    /**
     * 注册
     */
    @FormUrlEncoded
    @POST(Url.REGISTRATION)
    Observable<BaseModel<String>> registration(
            //@Header("token") String token,
            @Field("province") String province,
            @Field("city") String city,
            @Field("zone") String zone,
            @Field("username") String username,
            @Field("realname") String realname,
            @Field("password") String password,
            @Field("ewm") String ewm
    );

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST(Url.APPLOGIN)
    Observable<BaseModel<LoginSuccess>> applogin(
            //@Header("token") String token,
            @Field("username") String username,
            @Field("password") String password,
            @Field("regisonid") String regisonid,
            @Field("weixin") String weixin,
            @Field("qq") String qq
    );

    /**
     * 忘记密码
     */
    @FormUrlEncoded
    @POST(Url.FORGETPASSWORD)
    Observable<BaseModel<String>> forgetpassword(
            @Header("token") String token,
            @Field("username") String username,
            @Field("password") String password
    );

    /**
     * 修改密码
     */
    @FormUrlEncoded
    @POST(Url.MODIFYPASSWORD)
    Observable<BaseModel<String>> modifypassword(
            @Header("token") String token,
            @Field("username") String username,
            @Field("password") String password,
            @Field("newpassword") String newpassword
    );

    /**
     * 修改个人资料
     */
    @FormUrlEncoded
    @POST(Url.UPPERON)
    Observable<BaseModel<String>> upperon(
            @Header("token") String token,
            @Field("userid") int userid,
            @Field("nick") String nick,
            @Field("picture") String picture,
            @Field("telphone") String telphone
    );

    /**
     * 资讯查询
     */
    @FormUrlEncoded
    @POST(Url.SELTIPS)
    Observable<BaseModel<List<Information>>> seltips(
            @Header("token") String token,
            @Field("userid") int userid,
            @Field("page") int page
    );

    /**
     * 小窍门查询
     */
    @FormUrlEncoded
    @POST(Url.SELINFORATION)
    Observable<BaseModel<List<Information>>> selinforation(
            @Header("token") String token,
            @Field("userid") int userid,
            @Field("page") int page
    );

    /**
     * 申请经理
     */
    @FormUrlEncoded
    @POST(Url.MAKEAGENT)
    Observable<BaseModel<String>> makeagent(
            @Header("token") String token,
            @Field("userid") int userid,
            @Field("company") String company,
            @Field("companyaddress") String companyaddress,
            @Field("realname") String realname,
            @Field("telphone") String telphone,
             @Field("province") String province,
            @Field("city") String city,
            @Field("fund") String fund,
            @Field("fundpw") String fundpw,
            @Field("socialpw") String socialpw,
            @Field("social") String social
    );

    /**
     * 申请银行经理
     */
    @FormUrlEncoded
    @POST(Url.MAKEBANK)
    Observable<BaseModel<String>> makebank(
            @Header("token") String token,
            @Field("userid") int userid,
            @Field("branchname") String branchname,
            @Field("bankaddress") String bankaddress,
            @Field("bank") String bank,
            @Field("realname") String realname,
            @Field("telphone") String telphone,
            @Field("province") String province,
            @Field("city") String city,
            @Field("fund") String fund,
            @Field("fundpw") String fundpw,
            @Field("socialpw") String socialpw,
            @Field("social") String social
    );

    /**
     * 保存房贷
     */
    @FormUrlEncoded
    @POST(Url.SAVEROOMLOAN)
    Observable<BaseModel<String>> saveroomloan(
            @Header("token") String token,
            @Field("userid") int userid,
            @Field("realname") String realname,
            @Field("age") String age,
            @Field("idcard") String idcard,
            @Field("telphone") String telphone,
            @Field("sign") String sign,
            @Field("address") String address,
            @Field("roomaddress") String roomaddress,
            @Field("province") String province,
            @Field("city") String city,
            @Field("zone") String zone,
            @Field("area") String area,
            @Field("nature") String nature,
            @Field("current") String current,
            @Field("count") String count,
            @Field("rate") String rate,
            @Field("maxrate") String maxrate,
            @Field("loantime") String loantime,
            @Field("iscompany") String iscompany,
            @Field("position") String position,
            @Field("ratio") String ratio,
            @Field("isplace") String isplace,
            @Field("isoverdue") String isoverdue,
            @Field("dueoption") String dueoption,
            @Field("duetime") String duetime,
            @Field("amount") String amount,
            @Field("instruction") String instruction,
            @Field("type") int type,
            @Field("spousename") String spousename,
            @Field("spousetel") String spousetel,
            @Field("cardphoto") String cardphoto,
            @Field("marriagephoto") String marriagephoto,
            @Field("residencephoto") String residencephoto,
            @Field("threephoto") String threephoto,
            @Field("license") String license,
            @Field("creditphoto") String creditphoto,
            @Field("association") String association,
            @Field("replenishment") String replenishment,
            @Field("carframe") String carframe,
            @Field("carbrand") String carbrand,
            @Field("carno") String carno,
            @Field("carage") String carage,
            @Field("distance") String distance,
            @Field("ispay") String ispay,
            @Field("mortgagebank") String mortgagebank,
            @Field("expenditure") String expenditure,
            @Field("yearpay") String yearpay,
            @Field("surpius") String surpius,
            @Field("monthpay") String monthpay,
            @Field("times") String times,
            @Field("occupation") String occupation,
            @Field("industry") String industry,
            @Field("paycity") String paycity,
            @Field("limittime") String limittime,
            @Field("payform") String payform,
            @Field("communityid") String communityid,
            @Field("communityname") String communityname,
            @Field("workplace") String workplace
           /* @Field("iscar") String iscar,
            @Field("isroom") String isroom,
            @Field("workplace") String workplace,
            @Field("currentsalary") String currentsalary,
            @Field("worktime") String worktime,
            @Field("isfund") String isfund,
            @Field("unilateralpay") String unilateralpay,
            @Field("isInsurancepolicy") String isInsurancepolicy,
            @Field("insuredcompany") String insuredcompany,
            @Field("monthpay") String monthpay,
            @Field("yearpay") String yearpay,
            @Field("status") String status,
            @Field("descore") String descore*/
    );

    /**
     * 查询支付押金
     */
    @FormUrlEncoded
    @POST(Url.SELPAYSCORE)
    Observable<BaseModel<String>> selpayscore(
            @Header("token") String token,
            @Field("userid") int userid,
            @Field("count") double count
    );

    /**
     * 查询是否设置个支付密码
     */
    @FormUrlEncoded
    @POST(Url.SELPAYWORD)
    Observable<BaseModel<String>> selpayword(
            @Header("token") String token,
            @Field("userid") int userid
    );

    /**
     * 修改支付密码
     */
    @FormUrlEncoded
    @POST(Url.UPPAYPW)
    Observable<BaseModel<String>> uppaypw(
            @Header("token") String token,
            @Field("userid") int userid,
            @Field("payword") String payword,
            @Field("newpayword") String newpayword,
            @Field("telphone") String telphone
    );

    /**
     * 积分支付
     */
    @FormUrlEncoded
    @POST(Url.SCOREPAY)
    Observable<BaseModel<String>> scorepay(
            @Header("token") String token,
            @Field("userid") int userid,
            @Field("id") int id,
            @Field("score") double score,
            @Field("password") String password
    );

    /**
     * 忘记支付密码
     */
    @FormUrlEncoded
    @POST(Url.FORGETPAYPW)
    Observable<BaseModel<String>> forgetpaypw(
            @Header("token") String token,
            @Field("userid") int userid,
            @Field("payword") String payword,
             @Field("telphone") String telphone
    );

    /**
     * 设置支付密码
     */
    @FormUrlEncoded
    @POST(Url.SETPAYPW)
    Observable<BaseModel<String>> setpaypw(
            @Header("token") String token,
            @Field("userid") int userid,
            @Field("payword") String payword
    );

    /**
     * 获取车品牌
     */
    @POST(Url.SELCARBRAND)
    Observable<BaseModel<NewPlate>> selcarbrand(
            //@Header("token") String token,
    );

    /**
     * 根据ID获取车型详情接口
     */
    @FormUrlEncoded
    @POST(Url.SELCARBRANDTYPE)
    Observable<BaseModel<PlateDetailNew>> selcarbrandtype(
            //@Header("token") String token,
            @Field("id") String id
    );

    /**
     * 根据ID获取车型详情接口
     */
    @FormUrlEncoded
    @POST(Url.SELCARBRANDTYPEDETAIL)
    Observable<BaseModel<String>> selcarbrandtypedetail(
            //@Header("token") String token,
            @Field("cardid") String cardid
    );

    /**
     * 查询是有车还是有房贷
     */
    @FormUrlEncoded
    @POST(Url.SELSIROOMCAR)
    Observable<BaseModel<String>> selsiroomcar(
            @Header("token") String token,
            @Field("userid") int userid
    );

    /**
     * 查询个人资料
     */
    @FormUrlEncoded
    @POST(Url.SELPERON)
    Observable<BaseModel<Me>> selperon(
            @Header("token") String token,
            @Field("userid") int userid
    );
    /**
     * 立即评估
     */
    @FormUrlEncoded
    @POST(Url.SAVEEVALUATE)
    Observable<BaseModel<HouseAccess>> saveevaluate(
            @Header("token") String token,
            @Field("userid") int userid,
            @Field("telphone") String telphone,
            @Field("realname") String realname,
            @Field("address") String address,
            @Field("idcard") String idcard,
            @Field("roomarea") String roomarea,
            @Field("sign") String sign,
            @Field("communityid") String communityid,
            @Field("communityname") String communityname,
            @Field("realarea") String realarea,
            @Field("reserve") String reserve
    );
    /**
     * 查询我的申请
     */
    @FormUrlEncoded
    @POST(Url.SELROOMLOAN)
    Observable<BaseModel<List<MyApplication>>> selroomloan(
            @Header("token") String token,
            @Field("userid") int userid,
          /*  @Field("status") String status,*/
            @Field("page") int page
    );
    /**
     * 我的申请详情查询（该接口需要展示初审或终审时调其它情况直接从上个页面获取数据）
     */
    @FormUrlEncoded
    @POST(Url.SELORDERDETAIL)
    Observable<BaseModel<MyApplication>> selorderdetail(
            @Header("token") String token,
            @Field("userid") int userid,
            @Field("id") int id
    );
    /**
     * 获取首页轮播图
     */
    @POST(Url.SELROASTING)
    Observable<BaseModel<List<BannerData>>> selroasting(
            //@Header("token") String token
    );
    /**
     * 获取协议
     */
    @POST(Url.SELPROTOCOL)
    Observable<BaseModel<Protocol>> selprotocol(
            @Header("token") String token
    );
    /**
     * 查询我的产品
     */
    @FormUrlEncoded
    @POST(Url.SELROOMPRODUCT)
    Observable<BaseModel<List<MyProduct>>> selroomproduct(
            @Header("token") String token,
            @Field("userid") int userid,
            @Field("page") int page
    );

    /**
     * 保存房贷车贷产品（type=1 房贷 2车贷）
     */
    @FormUrlEncoded
    @POST(Url.SAVEROOMPRODUCT)
    Observable<BaseModel<String>> saveroomproduct(
            @Header("token") String token,
            @Field("userid") int userid,
            @Field("age") int age,
            @Field("maxage") int maxage,
            @Field("creditday") String creditday,
            @Field("roomage") int roomage,
            @Field("bank") String bank,
            @Field("branchname") String branchname,
            @Field("loantype") String loantype,
            @Field("type") String type,
            @Field("usertype") String usertype,
            @Field("ismanager") String ismanager,
            @Field("isproperty") String isproperty,
            @Field("area") int area,
            @Field("rate") String rate,
            @Field("maxrate") String maxrate,
            @Field("count") String count,
            @Field("paytype") String paytype,
            @Field("paytime") String paytime,
            @Field("address") String address,
            @Field("province") String province,
            @Field("city") String city,
            @Field("zone") String zone,
            @Field("cardphoto") String cardphoto,
            @Field("marriagephoto") String marriagephoto,
            @Field("residencephoto") String residencephoto,
            @Field("threephoto") String threephoto,
            @Field("license") String license,
            @Field("creditphoto") String creditphoto,
            @Field("association") String association,
            @Field("maxcarage") int maxcarage,
            @Field("carage") int carage,
            @Field("reserve") String reserve,
            @Field("cardistance") int cardistance,
            @Field("ratio") String ratio
    );

    /**
     * 抢单首页接口
     */
    @FormUrlEncoded
    @POST(Url.GRABSELROOMLOAN)
    Observable<BaseModel<List<Order>>> grabselroomloan(
            @Header("token") String token,
            @Field("userid") int userid,
            @Field("page") int page,
            @Field("status") String status
    );

    /**
     * 抢单
     */
    @FormUrlEncoded
    @POST(Url.GRABORDER)
    Observable<BaseModel<String>> graborder(
            @Header("token") String token,
            @Field("userid") int userid,
            @Field("id") int id,
            @Field("times") String times
    );

    /**
     * 我的团队
     */
    @FormUrlEncoded
    @POST(Url.SELMYTEAM)
    Observable<BaseModel<List<MyTeamOne.DataBean>>> selmyteam(
            @Header("token") String token,
            @Field("userid") int userid
    );

    /**
     * 你的申请已推荐
     */
    @FormUrlEncoded
    @POST(Url.SELMYTEAMONE)
    Observable<BaseModel<MyTeamOne>> selmyteamone(
            @Header("token") String token,
            @Field("userid") int userid
    );

    /**
     * 你的已申请二级已推荐
     */
    @FormUrlEncoded
    @POST(Url.SELMYTEAMTWO)
    Observable<BaseModel<MyTeamOne>> selmyteamtwo(
            @Header("token") String token,
            @Field("userid") int userid
    );

    /**
     * 你的未申请推荐
     */
    @FormUrlEncoded
    @POST(Url.SELMYTEAMTHREE)
    Observable<BaseModel<MyTeamOne>> selmyteamthree(
            @Header("token") String token,
            @Field("userid") int userid
    );

    /**
     * 点击我的
     */
    @FormUrlEncoded
    @POST(Url.SELMYACCOUNT)
    Observable<BaseModel<MeHome>> selmyaccount(
            @Header("token") String token,
            @Field("userid") int userid
    );

    /**
     * 点击增加
     */
    @FormUrlEncoded
    @POST(Url.CLICKMYSCOREADD)
    Observable<BaseModel<IntegralDetail>> clickmyscoreadd(
            @Header("token") String token,
            @Field("userid") int userid,
            @Field("page") int page
    );

    /**
     * 点击我的积分
     */
    @FormUrlEncoded
    @POST(Url.CLICKMYSCORE)
    Observable<BaseModel<Score>> clickmyscore(
            @Header("token") String token,
            @Field("userid") int userid,
            @Field("page") int page
    );
    /**
     * 点击支出
     */
    @FormUrlEncoded
    @POST(Url.CLICKMYSCORESUB)
    Observable<BaseModel<IntegralDetail>> clickmyscoresub(
            @Header("token") String token,
            @Field("userid") int userid,
            @Field("page") int page
    );

    /**
     * 历史纪录
     */
    @FormUrlEncoded
    @POST(Url.SELHISTORY)
    Observable<BaseModel<List<IntegralLog>>> selhistory(
            @Header("token") String token,
            @Field("userid") int userid,
            @Field("page") int page
    );

    /**
     * 绑定银行卡
     */
    @FormUrlEncoded
    @POST(Url.SAVEBANK)
    Observable<BaseModel<String>> savebank(
            @Header("token") String token,
            @Field("userid") int userid,
            @Field("realname") String realname,
            @Field("bankcardno") String bankcardno,
            @Field("bankname") String bankname,
            @Field("banknamebranch") String banknamebranch,
            @Field("telphone") String telphone,
            @Field("reserve") String reserve
    );

    /**
     * 查询我的银行卡
     */
    @FormUrlEncoded
    @POST(Url.SELBANK)
    Observable<BaseModel<List<Card>>> selbank(
            @Header("token") String token,
            @Field("userid") int userid
    );

    /**
     * 解绑银行卡
     */
    @FormUrlEncoded
    @POST(Url.DELBANK)
    Observable<BaseModel<String>> delbank(
            @Header("token") String token,
            @Field("userid") int userid,
            @Field("id") int id
    );

    /**
     * 获取可提现积分 type充值1 2奖励
     */
    @FormUrlEncoded
    @POST(Url.GETSCOREABLE)
    Observable<BaseModel<String>> getscoreable(
            @Header("token") String token,
            @Field("userid") int userid,
            @Field("type") int type
    );

    /**
     * 提现记录 0申请中
     */
    @FormUrlEncoded
    @POST(Url.SELDEPOSITHISTORY)
    Observable<BaseModel<List<WithdrawLog>>> seldeposithistory(
            @Header("token") String token,
            @Field("userid") int userid,
            @Field("page") int page,
            @Field("status") int status
    );

    /**
     * 保存提现记录
     */
    @FormUrlEncoded
    @POST(Url.SAVEDEPOSIT)
    Observable<BaseModel<String>> savedeposit(
            @Header("token") String token,
            @Field("userid") int userid,
            @Field("bankcardno") String bankcardno,
            @Field("bankname") String bankname,
            @Field("scoretype") String scoretype,
            @Field("score") String score
    );

    /**
     * 保存意见反馈
     */
    @FormUrlEncoded
    @POST(Url.SAVEFEEDBACK)
    Observable<BaseModel<String>> savefeedback(
            @Header("token") String token,
            @Field("userid") int userid,
            @Field("content") String content,
            @Field("picture") String picture
    );

    /**
     * 关于共享贷款
     */
    @FormUrlEncoded
    @POST(Url.SELABOUTGXDK)
    Observable<BaseModel<AboutApp>> selaboutgxdk(
            @Header("token") String token,
            @Field("userid") int userid
    );

    /**
     * 关于我们
     */
    @FormUrlEncoded
    @POST(Url.SELABOUTUS)
    Observable<BaseModel<AboutMe>> selaboutus(
            @Header("token") String token,
            @Field("userid") int userid
    );

    /**
     * 查询版本
     */
    @FormUrlEncoded
    @POST(Url.SELVERSION)
    Observable<BaseModel<Version>> selversion(
            @Header("token") String token,
            @Field("userid") int userid,
            @Field("type") String type
    );
    /**
     * 我的消息
     */
    @FormUrlEncoded
    @POST(Url.SELMYMESSAGE)
    Observable<BaseModel<List<Information>>> selmymessage(
            @Header("token") String token,
            @Field("userid") int userid,
            @Field("page") int page
    );

    /**
     * 初审点击确认提交
     */
    @FormUrlEncoded
    @POST(Url.GIVEFIRSTRESULT)
    Observable<BaseModel<String>> givefirstresult(
            @Header("token") String token,
            @Field("userid") int userid,
            @Field("id") int id,
            @Field("count") String count,
            @Field("rate") String rate,
            @Field("maxrate") String maxrate,
            @Field("loanlimit") String loanlimit,
            @Field("loantype") String loantype,
            @Field("lastresult") String lastresult,
            @Field("loantime") String loantime,
            @Field("firstpicture") String firstpicture,
            @Field("remark") String remark,
            @Field("picture") String picture
    );

    /**
     * 点击终审查询详情返回信息
     */
    @FormUrlEncoded
    @POST(Url.CLICKLAST)
    Observable<BaseModel<String>> clicklast(
            @Header("token") String token,
            @Field("userid") int userid,
            @Field("id") int id
    );

    /**
     * 给出终审
     */
    @FormUrlEncoded
    @POST(Url.GIVELASTRESULT)
    Observable<BaseModel<String>> givelastresult(
            @Header("token") String token,
            @Field("userid") int userid,
            @Field("id") int id,
            @Field("count") String count,
            @Field("maxrate") String maxrate,
            @Field("rate") String rate,
            @Field("loanlimit") String loanlimit,
            @Field("loantype") String loantype,
            @Field("loantime") String loantime,
            @Field("lendingtime") String lendingtime,
            @Field("lastpicture") String lastpicture,
            @Field("remark") String remark,
            @Field("picture") String picture
    );

    /**
     * 点击确认贷款查询详情
     */
    @FormUrlEncoded
    @POST(Url.CLICKOK)
    Observable<BaseModel<String>> clickok(
            @Header("token") String token,
            @Field("userid") int userid,
            @Field("id") int id
    );

    /**
     * 点击确认放贷
     */
    @FormUrlEncoded
    @POST(Url.SURE)
    Observable<BaseModel<String>> sure(
            @Header("token") String token,
            @Field("userid") int userid,
            @Field("id") int id
    );

    /**
     * 点击成功查询
     */
    @POST(Url.CLICKSUCCESS)
    Observable<BaseModel<String>> clicksuccess(
            @Header("token") String token
    );

    /**
     * 查询支付剩余的积分
     */
    @FormUrlEncoded
    @POST(Url.SELSCOREPAYLAST)
    Observable<BaseModel<String>> selscorepaylast(
            @Header("token") String token,
            @Field("userid") int userid,
            @Field("id") int id,
            @Field("score") double score
    );
    /**
     * 申请解锁
     */
    @FormUrlEncoded
    @POST(Url.APPLYOPEN)
    Observable<BaseModel<String>> applyopen(
            @Header("token") String token,
            @Field("userid") int userid,
            @Field("id") int id
    );

    /**
     * 保存信贷
     */
    @FormUrlEncoded
    @POST(Url.SAVECREDI)
    Observable<BaseModel<String>> savecredi(
            @Header("token") String token,
            @Field("userid") int userid,
            @Field("realname") String realname,
            @Field("age") String age,
            @Field("idcard") String idcard,
            @Field("telphone") String telphone,
            @Field("address") String address,
            @Field("spousename") String spousename,
            @Field("spousetel") String spousetel,
            @Field("count") String count,
            @Field("dueoption") String dueoption,
            @Field("duetime") String duetime,
            @Field("amount") String amount,
            @Field("sign") String sign,
            @Field("isoverdue") String isoverdue,
            @Field("instruction") String instruction,
            @Field("iscar") String iscar,
            @Field("isroom") String isroom,
            @Field("workplace") String workplace,
            @Field("industry") String industry,
            @Field("occupation") String occupation,
            @Field("currentsalary") String currentsalary,
            @Field("worktime") String worktime,
            @Field("payform") String payform,
            @Field("isfund") String isfund,
            @Field("unilateralpay") String unilateralpay,
            @Field("paycity") String paycity,
            @Field("limittime") String limittime,
            @Field("isinsurancepolicy") String isinsurancepolicy,
            @Field("insuredcompany") String insuredcompany,
            @Field("monthpay") String monthpay,
            @Field("yearpay") String yearpay,
            @Field("province") String province,
            @Field("city") String city,
            @Field("zone") String zone,
            @Field("times") int times,
            @Field("carframe") String carframe,
            @Field("carbrand") String carbrand,
            @Field("carno") String carno,
            @Field("carage") String carage,
            @Field("distance") String distance,
            @Field("area") String area,
            @Field("current") String current,
            @Field("nature") String nature,
            @Field("roomaddress") String roomaddress,
            @Field("addressdetail") String addressdetail,
            @Field("carstatus") String carstatus,
            @Field("useddate") String useddate,
            @Field("useddatemonth") String useddatemonth,
            @Field("price") String price,
            @Field("car") String car,
            @Field("mortgagebank") String mortgagebank,
            @Field("surpius") String surpius,
            @Field("expenditure") String expenditure,
            @Field("reverse") String reverse,
            @Field("iscredit") int iscredit
    );

    /**
     * 支付宝充值
     */
    @FormUrlEncoded
    @POST(Url.ALI)
    Observable<BaseModel<AliPay>> ali(
            @Header("token") String token,
            @Field("userid") int userid,
            @Field("money") double money,
            @Field("type") String type,
            @Field("id") String id
    );

    /**
     * 充值页面返回的数据
     */
    @FormUrlEncoded
    @POST(Url.SHENSCORE)
    Observable<BaseModel<ChargeScore>> shenscore(
            @Header("token") String token,
            @Field("userid") int userid
    );

    /**
     * 点击商城
     */
    @FormUrlEncoded
    @POST(Url.SELSHOP)
    Observable<BaseModel<String>> selshop(
            @Header("token") String token,
            @Field("userid") int userid
    );

    /**
     * 提现中获取手机验证码
     */
    @FormUrlEncoded
    @POST(Url.CODE)
    Observable<BaseModel<String>> code(
            @Field("username") String username
    );

    /**
     * 提现中获取手机验证码
     */
    @FormUrlEncoded
    @POST(Url.HELPREG)
    Observable<BaseModel<String>> helpreg(
            @Header("token") String token,
            @Field("username") String username,
            @Field("realname") String realname,
            @Field("branchname") String branchname,
            @Field("bank") String bank,
            @Field("bankaddress") String bankaddress,
            @Field("curentaddrress") String curentaddrress,
            @Field("password") String password,
            @Field("parentid") int parentid,
            @Field("status") String status,
            @Field("company") String company,
            @Field("companyaddress") String companyaddress,
            @Field("idcard") String idcard
    );

    /**
     * 申请记录
     */
    @FormUrlEncoded
    @POST(Url.RECORD)
    Observable<BaseModel<ApplicationLogNew>> record(
            @Header("token") String token,
            @Field("userid") int userid
    );

    /**
     * 取消订单接口
     */
    @FormUrlEncoded
    @POST(Url.CANCEL)
    Observable<BaseModel<String>> cancel(
            @Header("token") String token,
            @Field("userid") int userid,
            @Field("id") int id
    );

    /**
     * 取消申请
     */
    @FormUrlEncoded
    @POST(Url.CANLEAPPLY)
    Observable<BaseModel<String>> canleapply(
            @Header("token") String token,
            @Field("id") int id,
            @Field("userid") int userid
    );

    /**
     * 我的订单
     */
    @FormUrlEncoded
    @POST(Url.SELMYORDER)
    Observable<BaseModel<String>> selmyorder(
            @Header("token") String token,
            @Field("userid") int userid
    );

    /**
     * 我的地址
     */
    @FormUrlEncoded
    @POST(Url.SELMYADDRESS)
    Observable<BaseModel<String>> selmyaddress(
            @Header("token") String token,
            @Field("userid") int userid
    );

    /**
     * 用户确认
     */
    @FormUrlEncoded
    @POST(Url.USERSURE)
    Observable<BaseModel<String>> usersurn(
            @Header("token") String token,
            @Field("userid") int userid,
            @Field("id") int id
    );

    /**
     * 修改申请
     */
    @FormUrlEncoded
    @POST(Url.UPDATAAPPLY)
    Observable<BaseModel<String>> updataapply(
            @Header("token") String token,
            @Field("userid") int userid,
            @Field("id") int id,
            @Field("status") String status,
            @Field("realname") String realname,
            @Field("bank") String bank,
            @Field("branchname") String branchname,
            @Field("bankaddress") String bankaddress,
            @Field("company") String company,
            @Field("companyaddress") String companyaddress,
            @Field("telephone") String telephone,
            @Field("province") String province,
            @Field("city") String city,
            @Field("fund") String fund,
            @Field("fundpw") String fundpw,
            @Field("socialpw") String socialpw,
            @Field("social") String social
    );

    /**
     * 信贷查询
     */
    @FormUrlEncoded
    @POST(Url.SELECTCREDITSS)
    Observable<BaseModel<List<MyCreditData>>> selectcreditss(
            @Header("token") String token,
            @Field("userid") int userid,
            @Field("page") int page
    );

    /**
     * 虚拟电话
     */
    @FormUrlEncoded
    @POST(Url.AXBBANDING)
    Observable<BaseModel<String>> axbbanding(
            @Header("token") String token,
            @Field("userid") int userid,
            @Field("telphone") String telphone
    );

    /**
     * 车型列表
     */
    @FormUrlEncoded
    @POST(Url.SELCARLIST)
    Observable<BaseModel<PlateDetailThree>> selcarlist(
            @Field("id") String id
    );

    /**
     * 车辆评估
     */
    @FormUrlEncoded
    @POST(Url.CARPINGGU)
    Observable<BaseModel<CarAccess>> carpinggu(
            @Header("token") String token,
            @Field("userid") int userid,
            @Field("province") String province,
            @Field("city") String city,
            @Field("price") double price,
            @Field("mileage") double mileage,
            @Field("useddatemonth") String useddateMonth,
            @Field("useddate") String useddate,
            @Field("car") String car,
            @Field("carstatus") String carstatus,
            @Field("purpose") String purpose,
            @Field("reserve") String reserve
    );

    /**
     * 车型列表
     */
    @FormUrlEncoded
    @POST(Url.SHARE)
    Observable<BaseModel<String>> share(
            @Field("userid") int userid
    );

    /**
     * 车型列表
     */
    @FormUrlEncoded
    @POST(Url.ZHUCE)
    Observable<BaseModel<String>> zhuce(
            @Field("userid") int userid
    );
    /**
     * 车型列表
     */
    @FormUrlEncoded
    @POST(Url.TELPHONE)
    Observable<BaseModel<String>> telphone(
            @Field("userid") int userid,
            @Field("telphone") String telphone
    );

    /**
     * 车型列表
     */
    @FormUrlEncoded
    @POST(Url.GRAB)
    Observable<BaseModel<String>> grab(
            @Header("token") String token,
            @Field("userid") int userid
    );

    /**
     * 车型列表
     */
    @FormUrlEncoded
    @POST(Url.CASH)
    Observable<BaseModel<String>> cash(
            @Header("token") String token,
            @Field("userid") int userid,
            @Field("money") double money,
            @Field("type") String type,
            @Field("id") String id
    );

    /**
     * 删除产品
     */
    @FormUrlEncoded
    @POST(Url.DELROOMPRODUCT)
    Observable<BaseModel<String>> delroomproduct(
            @Header("token") String token,
            @Field("userid") int userid,
            @Field("id") int id
    );

    /**
     * 删除房贷车贷
     */
    @FormUrlEncoded
    @POST(Url.DELROOMLOAN)
    Observable<BaseModel<String>> delroomloan(
            @Header("token") String token,
            @Field("userid") int userid,
            @Field("id") int id
    );

    /**
     * 提交故障订单
     */
    @FormUrlEncoded
    @POST(Url.SAVEFAULT)
    Observable<BaseModel<String>> savefault(
            @Header("token") String token,
            @Field("userid") int userid,
            @Field("id") int id,
            @Field("content") String content,
            @Field("picture") String picture
    );

    /**
     * 获取楼盘
     */
    @FormUrlEncoded
    @POST(Url.LOUPAN)
    Observable<BaseModel<List<Loupan>>> loupan(
            @Field("province") String province,
            @Field("city") String city,
            @Field("area") String area,
            @Field("keyword") String keyword
    );

    /**
     * 获取楼栋
     */
    @FormUrlEncoded
    @POST(Url.BUILD)
    Observable<BaseModel<List<LouDong>>> build(
            @Field("communityid") String communityid,
            @Field("city") String city
    );

    /**
     * 获取楼层
     */
    @FormUrlEncoded
    @POST(Url.CENG)
    Observable<BaseModel<List<String>>> ceng(
            @Field("city") String city,
            @Field("buildingid") String buildingid
    );

    /**
     * 获取房子信息
     */
    @FormUrlEncoded
    @POST(Url.AREA)
    Observable<BaseModel<List<HouseDetail>>> area(
            @Field("buildingid") String buildingid,
            @Field("floorNo") String floorNo,
            @Field("city") String city
    );
}
