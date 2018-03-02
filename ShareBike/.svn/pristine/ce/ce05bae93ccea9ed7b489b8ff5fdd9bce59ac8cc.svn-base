package com.panda.sharebike.util;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Xml;
import android.widget.TextView;

import com.panda.sharebike.Config;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.xmlpull.v1.XmlPullParser;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class PayImplForNew {

    private static final String TAG = "MicroMsg.SDKSample.PayActivity";
    private String orderNo = null, payNo = null;
    private int orderPrice;
    PayReq req;
    TextView show;
    Map<String, String> resultunifiedorder;
    StringBuffer sb;
    private IWXAPI api;
    private Context context;

    public PayImplForNew(Context context, String orderNo, String payNo, int orderPrice) {
        this.context = context;
        this.orderNo = orderNo;
        this.payNo = payNo;
        this.orderPrice = orderPrice;
        initData(context);
    }

    private void initData(Context context) {
        //注册APPID
        api = WXAPIFactory.createWXAPI(context, Config.WX_APPID, false);
        api.registerApp(Config.WX_APPID);

        //show =(TextView)findViewById(R.id.editText_prepay_id);
        req = new PayReq();
        sb = new StringBuffer();
    }

    public void pay() {
        GetPrepayIdTask getPrepayId = new GetPrepayIdTask();
        getPrepayId.execute();
    }

    /**
     * 生成签名
     */

    private String genPackageSign(List<NameValuePair> params) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < params.size(); i++) {
            sb.append(params.get(i).getName());
            sb.append('=');
            sb.append(params.get(i).getValue());
            sb.append('&');
        }
        sb.append("key=");
        sb.append(Config.WX_API_KEY);
        String packageSign = MD5.getMessageDigest(sb.toString().getBytes()).toUpperCase();
        try {
            return new String(packageSign.getBytes(), "ISO8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
//		Log.e("orion",packageSign);
        return null;
    }

    private String genAppSign(List<NameValuePair> params) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < params.size(); i++) {
            sb.append(params.get(i).getName());
            sb.append('=');
            sb.append(params.get(i).getValue());
            sb.append('&');
        }
        sb.append("key=");
        sb.append(Config.WX_API_KEY);
//		Log.e("TAG_SB",sb.toString());
        this.sb.append("sign str\n" + sb.toString() + "\n\n");
        String appSign = MD5.getMessageDigest(sb.toString().getBytes()).toUpperCase();
//		Log.e("orion",appSign);
        return appSign;
    }

    private String toXml(List<NameValuePair> params) {
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        for (int i = 0; i < params.size(); i++) {
            sb.append("<" + params.get(i).getName() + ">");


            sb.append(params.get(i).getValue());
            sb.append("</" + params.get(i).getName() + ">");
        }
        sb.append("</xml>");

//		Log.e("orion",sb.toString());
        return sb.toString();
    }

    private class GetPrepayIdTask extends AsyncTask<Void, Void, Map<String, String>> {

        private ProgressDialog dialog;


        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(context);
            //dialog = ProgressDialog.show(context, context.getString(R.string.app_tip), context.getString(R.string.getting_prepayid));
            dialog.setMessage("提示");
            dialog.setMessage("正在获取预支付订单...");
            dialog.show();
        }

        @Override
        protected void onPostExecute(Map<String, String> result) {
            if (dialog != null) {
                dialog.dismiss();
            }
            sb.append("prepay_id\n" + result.get("prepay_id") + "\n\n");
            //show.setText(sb.toString());

            resultunifiedorder = result;
            if (resultunifiedorder.get("prepay_id") != null) {
                //生成支付参数
                genPayReq();
            }
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Map<String, String> doInBackground(Void... params) {

            String url = String.format("https://api.mch.weixin.qq.com/pay/unifiedorder");
            String entity = genProductArgs();

//			Log.e("orion",entity);

            byte[] buf = Util.httpPost(url, entity);

            String content = new String(buf);
//			Log.e("orion", content);
            Map<String, String> xml = decodeXml(content);

            return xml;
        }
    }


    public Map<String, String> decodeXml(String content) {

        try {
            Map<String, String> xml = new HashMap<String, String>();
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(new StringReader(content));
            int event = parser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {

                String nodeName = parser.getName();
                switch (event) {
                    case XmlPullParser.START_DOCUMENT:

                        break;
                    case XmlPullParser.START_TAG:

                        if ("xml".equals(nodeName) == false) {
                            //实例化student对象
                            xml.put(nodeName, parser.nextText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                }
                event = parser.next();
            }

            return xml;
        } catch (Exception e) {
        }
        return null;

    }

    //生成随机字符串
    private String genNonceStr() {
        Random random = new Random();
        return MD5.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
    }

    //时间戳
    private long genTimeStamp() {
        return System.currentTimeMillis() / 1000;
    }

    //后台返回的支付订单编号
    private String genOutTradNo() {
//		Random random = new Random();
//       Log.e("TAGAA",MD5.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes()));
//		return MD5.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
        return payNo;
    }

    //获取本机IP
    private String genPhoneIp() {
        IpUtils ipUtils = new IpUtils(context);
        String hostIp = ipUtils.getLocalHostIp();
//		Log.e("TAG_IP",hostIp);
        return hostIp;
    }

    //
    @SuppressLint("LongLogTag")
    private String genProductArgs() {
        StringBuffer xml = new StringBuffer();

        try {
            String nonceStr = genNonceStr();

            xml.append("</xml>");
            List<NameValuePair> packageParams = new LinkedList<NameValuePair>();
            packageParams.add(new BasicNameValuePair("appid", Config.WX_APPID));
            packageParams.add(new BasicNameValuePair("attach", orderNo));
            packageParams.add(new BasicNameValuePair("body", "巧骑单车"));
            packageParams.add(new BasicNameValuePair("input_charset", "UTF-8"));
            packageParams.add(new BasicNameValuePair("mch_id", Config.WX_PARTNER));
            packageParams.add(new BasicNameValuePair("nonce_str", nonceStr));
            packageParams.add(new BasicNameValuePair("notify_url", Config.WX_PAY_URL));
            //附加数据传递商品订单号
            packageParams.add(new BasicNameValuePair("out_trade_no", genOutTradNo()));
            packageParams.add(new BasicNameValuePair("spbill_create_ip", genPhoneIp()));
            packageParams.add(new BasicNameValuePair("total_fee", String.valueOf(orderPrice)));
            packageParams.add(new BasicNameValuePair("trade_type", "APP"));
            String sign = genPackageSign(packageParams);
            packageParams.add(new BasicNameValuePair("sign", sign));


            String xmlstring = toXml(packageParams);
            // new String(xmlstring.toString().getBytes(),"ISO8859-1");
            //返回经过编码格式后的数据
            return new String(xmlstring.toString().getBytes(), "ISO8859-1");

        } catch (Exception e) {
//			Log.e(TAG, "genProductArgs fail, ex = " + e.getMessage());
            return null;
        }
    }

    //APP支付参数
    private void genPayReq() {
        //APP_ID应用id
        req.appId = Config.WX_APPID;
        //商户号
        req.partnerId = Config.WX_PARTNER;
        //微信返回的支付交易会话ID
        req.prepayId = resultunifiedorder.get("prepay_id");
        //暂填写固定值Sign=WXPay
        req.packageValue = "Sign=WXPay";
        //随机字符串，不长于32位
        req.nonceStr = genNonceStr();
        //时间戳
        req.timeStamp = String.valueOf(genTimeStamp());

        List<NameValuePair> signParams = new LinkedList<NameValuePair>();
        signParams.add(new BasicNameValuePair("appid", req.appId));
        signParams.add(new BasicNameValuePair("noncestr", req.nonceStr));
        signParams.add(new BasicNameValuePair("package", req.packageValue));
        signParams.add(new BasicNameValuePair("partnerid", req.partnerId));
        signParams.add(new BasicNameValuePair("prepayid", req.prepayId));
        signParams.add(new BasicNameValuePair("timestamp", req.timeStamp));

        req.sign = genAppSign(signParams);

        sb.append("sign\n" + req.sign + "\n\n");

        //show.setText(sb.toString());
//		Log.e("orion", signParams.toString());
        //调起来支付
        sendPayReq();
    }

    private void sendPayReq() {
        api.sendReq(req);
    }

}

