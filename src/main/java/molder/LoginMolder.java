package molder;

import com.example.lx.ApiService;
import com.example.lx.LoginCallBack;

import bean.BannerBean;
import bean.ResultBean;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginMolder {
    public void getdataBean(final LoginCallBack<ResultBean> callBack, int page) {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_RESULT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = build.create(ApiService.class);
        Observable<ResultBean> result = apiService.getResult(page);
        result.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultBean resultBean) {
                        callBack.chenggong(resultBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    public void getBannerBean(final LoginCallBack<BannerBean> callBack) {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_BANNER)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = build.create(ApiService.class);
        apiService.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BannerBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BannerBean bannerBean) {
                        callBack.chenggong(bannerBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.shibai(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
