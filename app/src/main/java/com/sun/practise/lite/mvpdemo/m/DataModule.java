package com.sun.practise.lite.mvpdemo.m;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Sun on 2017/7/25.
 */

public class DataModule {

    List data = new ArrayList();

     ModuleInterface mCallback;

    public DataModule(ModuleInterface moduleInterface){
        this.mCallback = moduleInterface;
    }

    //模拟数据处理
    public void getdata() {
        Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(@NonNull ObservableEmitter e) throws Exception {
                for (int i = 0; i <100; i++) {
                    data.add(i + "items");
                }
                e.onNext(data);
            }
        }).subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Object o) {
                        //处理完之后回调 ModuleInterface，通知P：数据处理完毕可以继续执行逻辑
                        mCallback.LoadSuccess(data);
                    }
                    @Override
                    public void onError(Throwable e) {
                        //数据获取失败
                       mCallback.LoadFailed();
                    }
                    @Override
                    public void onComplete() {

                    }
                });
    }

}
