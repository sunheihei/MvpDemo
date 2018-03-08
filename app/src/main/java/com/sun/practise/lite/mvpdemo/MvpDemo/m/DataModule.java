package com.sun.practise.lite.mvpdemo.MvpDemo.m;

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
        Observable.create(new ObservableOnSubscribe<List<String>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<String>> e) throws Exception {
                for (int i = 0; i <100; i++) {
                    data.add(i + "items");
                }
                e.onNext(data);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<String> strings) {
                        mCallback.LoadSuccess(strings);
                    }

                    @Override
                    public void onError(Throwable e) {
                         mCallback.LoadFailed();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
