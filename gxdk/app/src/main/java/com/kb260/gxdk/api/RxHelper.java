package com.kb260.gxdk.api;

import com.kb260.gxdk.utils.StringUtils;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author KB260
 *         Created on  2017/12/28
 */

public class RxHelper {

    /**
     * 对结果进行预处理
     */
    public static <T> ObservableTransformer<BaseModel<T>, T> handleResult() {
        return upstream -> upstream.flatMap(tBaseModel -> {
                    if (StringUtils.isEmpty(tBaseModel.status)) {
                        return Observable.error(new ServerException("数据异常"));
                    }

                    if (tBaseModel.isNEED_BIND_QQ()) {
                        return Observable.error(new ServerException(tBaseModel.status));
                    }
                    if (tBaseModel.isNEED_UNBIND_QQ()) {
                        return Observable.error(new ServerException(tBaseModel.status));
                    }
                    if (tBaseModel.isNEED_BIND_WX()) {
                        return Observable.error(new ServerException(tBaseModel.status));
                    }
                    if (tBaseModel.isNEED_UNBIND_WX()) {
                        return Observable.error(new ServerException(tBaseModel.status));
                    }

                    if (tBaseModel.params == null) {
                        tBaseModel.params = (T) tBaseModel.message;
                    }

                    if (tBaseModel.offite()) {
                        return Observable.error(new Throwable(tBaseModel.status));
                    }

                    if (tBaseModel.isAlipay()) {
                        return createData(tBaseModel.params);
                    }

                    if (tBaseModel.isNoSetPayPassword()) {
                        return createData(tBaseModel.params);
                    }

                    if (tBaseModel.isSucceed()) {
                        return createData(tBaseModel.params);
                    }

                    if (tBaseModel.isNoMoney()) {
                        return Observable.error(new ServerException(tBaseModel.message));
                    }

                    if (tBaseModel.isPhoneNotRegister()) {
                        return createData(tBaseModel.params);
                    }
                    if (tBaseModel.isWaitRegister()) {
                        return createData((T) tBaseModel.status);
                    }
                    if (tBaseModel.isTokenVerificationFailed()) {
                        return createData((T) tBaseModel.status);
                    }
                    return Observable.error(new ServerException(tBaseModel.message));
                }

        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 创建成功的数据
     */
    private static <T> Observable<T> createData(final T data) {
        return Observable.create(subscriber -> {
            try {
                subscriber.onNext(data);
                subscriber.onComplete();
            } catch (Exception e) {
                subscriber.onError(e);
            }
        });
    }
}
