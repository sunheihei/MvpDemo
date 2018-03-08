package com.sun.practise.lite.mvpdemo.MvpDemo2;

import java.util.List;

/**
 * Created by Sun on 2018/3/8.
 */

public interface Contarct {

    interface View extends BaseView<Presenter> {

        void showLoading();

        void hideLoading();

        void setListItem(List<String> data);

        void failed();

        void showMessage(String message);

    }


    interface Presenter extends BasePresenter {

        void itemclick(int position);

    }


}
