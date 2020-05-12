package persenter;

import com.example.lx.LoginCallBack;

import java.util.List;

import bean.BannerBean;
import bean.ResultBean;
import molder.LoginMolder;
import view.LoginView;

public class LoginPersnter {
    private LoginView loginView;
    private LoginMolder loginMolder;

    public LoginPersnter(LoginView loginView, int page) {
        this.loginView = loginView;
        this.loginMolder=new LoginMolder();
    }

    public void getBanner() {
                loginMolder.getBannerBean(new LoginCallBack<BannerBean>() {
                    @Override
                    public void chenggong(BannerBean bannerBean) {
                        loginView.setBanner(bannerBean.getData());
                    }

                    @Override
                    public void shibai(String str) {
                        loginView.showToast(str);
                    }
                });
    }

    public void data(int page) {
        loginMolder.getdataBean(new LoginCallBack<ResultBean>() {
            @Override
            public void chenggong( ResultBean resultBean) {
                loginView.setData(resultBean.getData().getDatas());
            }

            @Override
            public void shibai(String str) {
                loginView.showToast(str);
            }
        },page);
    }
}
