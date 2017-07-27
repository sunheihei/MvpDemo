package com.sun.practise.lite.mvpdemo.p;
import com.sun.practise.lite.mvpdemo.m.DataModule;
import com.sun.practise.lite.mvpdemo.m.ModuleInterface;
import com.sun.practise.lite.mvpdemo.v.MvpView;
import java.util.List;

/**
 * Created by Sun on 2017/4/21.
 */

public class MvpPresenter  implements ModuleInterface{

    private MvpView mvpView;

    //获取Module实例，执行数据处理方法。
    DataModule dataModule = new DataModule(this);;
    public MvpPresenter(MvpView mvpView){
        this.mvpView = mvpView;

    }


    public void handledata() {
        mvpView.showLoading();
        dataModule.getdata();
    }

    public void onItemClick(int position){
        mvpView.showMessage("点击了item"+position);
    }

    //数据传过来之后，继续执行逻辑
    @Override
    public void LoadSuccess(List<String> data) {
        mvpView.hideLoading();
        mvpView.setListItem(data);
}

    @Override
    public void LoadFailed() {
     //处理失败后View的操作
        mvpView.failed();
    }
}
