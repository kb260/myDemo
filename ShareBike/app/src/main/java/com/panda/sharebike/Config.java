package com.panda.sharebike;

import com.blankj.utilcode.util.SPUtils;
import com.panda.sharebike.api.N;
import com.panda.sharebike.util.LoginUtil;

/**
 * <pre>
 *     author : zhangyuan
 *     e-mail : zhangyuan_min@163.com
 *     time   : 2017/06/16
 *     desc   : 定义配置数据（常量）
 *     version: 1.0
 * </pre>
 */
public class Config {
    /**
     * "status":"loggedmember":登录状态
     * "status":"guest"退出状态/游客状态
     */
    public static final String STATUS_LOGIN = "loggedmember";
    public static final String STATUS_GUEST = "guest";
    //游客token主要
    public static final String TOKEN = SPUtils.getInstance(N.SHARE_TOKEN).getString(N.SHARE_TOKEN_KEY);

    public static final String CHAT_SERVER_URL = "";

    public static final String SCANUNLOCK_KEY = "activity_end";

    public static String USER_ID = "";

    //登录凭证token
    public static String SIGN_ID = "";
    //登录回调接口
    public static LoginUtil.ILoginCallBack LOGINCALLBACK;
    //appid 微信分配的公众账号ID
    public static String APP_WE_CHAT_ID = "";


    public static final String ALI_PAY_URL = "http://www.okbikes.cn:8080/qbike/thirdpay/alipay/notify";
    public static final String WX_PAY_URL = "http://www.okbikes.cn:8080/qbike/thirdpay/tenpay/notify";


    /**
     * 支付宝支付业务：入参app_id
     */
    public static final String APPID = "2018012602079339";
    /**
     * 微信支付业务:入参app_id
     */
    public static final String WX_APPID = "wxe63a1b0bed1ecf73";
    public static final String WX_APPSECRET = "4e3de99cc9545f0f62e15e00e02959d3";
    public static final String WX_PARTNER = "1486635262";//商户号
    public static final String WX_API_KEY = "sjbicyclecreatebyalter2017year88";//API密钥
    /**
     * 支付宝账户登录授权业务：入参pid值
     */
    public static final String PID = "";
    /**
     * 支付宝账户登录授权业务：入参target_id值
     */
    public static final String TARGET_ID = "";

    /** 商户私钥，pkcs8格式 */
    /** 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个 */
    /** 如果商户两个都设置了，优先使用 RSA2_PRIVATE */
    /** RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议使用 RSA2_PRIVATE */
    /** 获取 RSA2_PRIVATE，建议使用支付宝提供的公私钥生成工具生成， */
    /**
     * 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1
     */
    public static final String RSA2_PRIVATE = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCgqTiq4+7Zh5Dcj+NMF+OWanP0677dMrntpNKXUe1IQy9EtyXbNLhNikjCHvhRzb0I4CMZWe5b1COvdVC6K5tVrEgKpzJYWlA6nPIxIOZl5dHyYE9TiXCp14UaFVC+J5jQkYKaUElHjHMiX2Ez/1q+yxqyAEyHnzlvMMMsOZ9p/IMz24ej4TGlRiiQPVRIUdrQKSAL8oJTUt2VS6nwQxb0TRAzrdLlr6e4vPvMiU/us5Ij1g33xaDt2kdeIjRnFT+omcXgM4r0eGTBvLm9NIEpFEgeygb01mVY2wxSZcfJZE8Gx4BIHR9Jk9pD/SLNRJRkwBB9aaGNoYiozQqJjLI3AgMBAAECggEATPGehSGSs7WcaDW1Oy05v0EOdd8t0sxHhYvpm7UN+gzLJ5K7W6d9GQ3AWM/yR2NMRGLsDGkooewQhyidV/wocbiH7Co1D2F5iQi/aU4DagvOJ9F+TFjHj1/7jAlnWRqnskPuE6+d2NloDq81Y2zJbhThnRxNiqp0HE9zOcwDEAOuoDBYuO71yAxm7/jNeZByQ3IKtQoCJrFZqldQsvt8k6GmYz30yeFhtQM1PL9Y4ME9qlXL1+tPs4yc3YpGZPjLquFQPzxQdd/AGPwOnPJrzf8QluOj1NUNt0a0SEII/PKhk6o6qOGiFm+gSVLzGGZg1rJyZU4ffsvylwFtDEQc+QKBgQDlyx6wtag7lhpRNoZmfzvNQUlLHKmGEi82eXb9ResUzS241Fm1kaZhCPHBxq1atlTExDGshKA2fUEF0R1/7GL1Sob8eDMTZlxnky9iExsmFwBTFmFiYoIV8DZ6r1uQn1H9KmXV7UZrjMUqxAMLKrjsjjduzoWtVMTDfo1/abLb5QKBgQCy+8MnOfrzmqp90PoufGjyPmTqe5uAIps6gLDmnwmnrLYw3AbNIRdIpBcgaaNOxEop3FbSTUia2z9DzPyU+D5zSCZJi2U1DkRDxrrj4vS4TR0pKOa/sgaC7+1RajiOqBBsWZTX3aNRadgqVVWLe74PKznPEx5H6frQixp9PKIL6wKBgHTuoGZhegMgRbIP4eJkWkT/NNGw+x7LQBUjSAZnvtEt+1czT5cST6zPBV6KwAoKACbEg1Z7gp9Xq2/PUoiW/GA/lWMBs1Pq4ZifEByDBmyihbrCB7ot0G5jdwWFGD4GpI0ENx/eBtiRoH87kSTsj39oexK6qtFh8mJckppEagx1AoGAFjS8Mqqz4vNmgbOixo+y6ZMqLBln4o7omWqMCCDlLsNz2z4BLgQUAnQxVlkk5Mnfta/bqb3i6yuWfs/46naeMOlbrrY3uUxq1cnbOBjZaeBPZOLpNhFdU3N3CynVmIVrfDXlDJ83+wrZwe7SbTrOCs2/H1zs3dk+wA7yG2WhaT8CgYBtmHSsE6RKj4wdA78TkqfKebp9oh8uAMxj8lFlpBAziP+IWXlTDLrFjb0He/+gwmyl0BLjpWn7NfeiOFgj6U7JsfPR3Kbc3Hh2GQ4fobx2RDA8QOikOed/UhTOxsqxJXsQ23M6U47caSOQKWcSoHBzIu7mhi/6f+M3d07w9RyT0w==";
    public static final String RSA_PRIVATE = "";

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
}
/**
 * ************************************************************************
 * **                              _oo0oo_                               **
 * **                             o8888888o                              **
 * **                             88" . "88                              **
 * **                             (| -_- |)                              **
 * **                             0\  =  /0                              **
 * **                           ___/'---'\___                            **
 * **                        .' \\\|     |// '.                          **
 * **                       / \\\|||  :  |||// \\                        **
 * **                      / _ ||||| -:- |||||- \\                       **
 * **                      | |  \\\\  -  /// |   |                       **
 * **                      | \_|  ''\---/''  |_/ |                       **
 * **                      \  .-\__  '-'  __/-.  /                       **
 * **                    ___'. .'  /--.--\  '. .'___                     **
 * **                 ."" '<  '.___\_<|>_/___.' >'  "".                  **
 * **                | | : '-  \'.;'\ _ /';.'/ - ' : | |                 **
 * **                \  \ '_.   \_ __\ /__ _/   .-' /  /                 **
 * **            ====='-.____'.___ \_____/___.-'____.-'=====             **
 * **                              '=---='                               **
 * ************************************************************************
 * **                        佛祖保佑      镇类之宝                         **
 * ************************************************************************
 */