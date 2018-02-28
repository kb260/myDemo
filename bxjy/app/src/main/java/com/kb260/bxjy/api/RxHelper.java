package com.kb260.bxjy.api;

import com.kb260.bxjy.utils.StringUtils;
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
                    if (tBaseModel.data == null) {
                        tBaseModel.data = (T) tBaseModel.message;
                    }
                    switch (tBaseModel.status){
                        case BaseModel.SUCCEED_CODE:
                            return createData(tBaseModel.data);
                        case BaseModel.TOKE_ERROR_CODE:
                            //token异常
                            return Observable.error(new ServerException(tBaseModel.message));
                        case BaseModel.TOKE_TIMEOUT_CODE:
                            //token过期
                            return Observable.error(new ServerException(tBaseModel.message));
                        default:
                            return Observable.error(new ServerException(tBaseModel.message));
                    }


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
