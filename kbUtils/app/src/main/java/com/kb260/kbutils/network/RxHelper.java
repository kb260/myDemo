package com.kb260.kbutils.network;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author KB260
 *         Created on  2018/1/10
 */

public class RxHelper {
    /**
     * 对结果进行预处理
     */
    public static <T> ObservableTransformer<BaseModel<T>, T> handleResult() {
        return upstream -> upstream.flatMap(tBaseModel -> {
                    if (tBaseModel.status!= null){
                        switch (tBaseModel.status){
                            case Code.SUCCESS:
                                return createData(tBaseModel.params);
                            default:
                                return Observable.error(new ServerException(tBaseModel.message));
                        }
                    }else {
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
