package com.sun.practise.lite.mvpdemo.v;

import java.util.List;

/**
 * Created by Sun on 2017/4/21.
 */

public interface MvpView {
       void showLoading();
       void hideLoading();
       void setListItem(List<String> data);
       void failed();
       void showMessage(String message);
}
