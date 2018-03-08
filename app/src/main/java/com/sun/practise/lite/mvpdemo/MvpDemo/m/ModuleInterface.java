package com.sun.practise.lite.mvpdemo.MvpDemo.m;

import java.util.List;

/**
 * Created by Sun on 2017/7/27.
 */

public interface ModuleInterface {
    //获取数据状态回调的接口
    void LoadSuccess(List<String> data);

    void LoadFailed();
}
