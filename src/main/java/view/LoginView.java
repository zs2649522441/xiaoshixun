package view;

import java.util.List;

import bean.BannerBean;
import bean.ResultBean;

public interface LoginView {
    void showToast(String str);
    void setData(List<ResultBean.DataBean.DatasBean>data);
    void setBanner(List<BannerBean.DataBean>dataBeans);

}
