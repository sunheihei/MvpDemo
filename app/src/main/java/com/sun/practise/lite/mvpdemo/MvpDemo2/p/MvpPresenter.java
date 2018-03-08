package com.sun.practise.lite.mvpdemo.MvpDemo2.p;

import com.sun.practise.lite.mvpdemo.MvpDemo2.Contarct;
import com.sun.practise.lite.mvpdemo.MvpDemo2.m.DataModule;

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
 * Created by Sun on 2017/4/21.
 */

public class MvpPresenter implements Contarct.Presenter {

    private Contarct.View MvpView;

    public MvpPresenter(Contarct.View mvpView) {
        MvpView = mvpView;
        MvpView.setPresenter(this);
    }


    @Override
    public void start() {
        MvpView.showLoading();
        GetData();
    }


    //模拟数据处理
    List data = new ArrayList();

    private void GetData() {
        Observable.create(new ObservableOnSubscribe<List<String>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<String>> e) throws Exception {
                for (int i = 0; i < 50; i++) {
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
                        MvpView.hideLoading();
                        MvpView.setListItem(data);
                    }

                    @Override
                    public void onError(Throwable e) {
                        MvpView.failed();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void itemclick(int position) {
        MvpView.showMessage("点击了item" + position);
    }
}
