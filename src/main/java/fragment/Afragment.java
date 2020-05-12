package fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.lx.R;
import com.example.lx.RecyclerAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import bean.BannerBean;
import bean.ResultBean;
import persenter.LoginPersnter;
import view.LoginView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Afragment extends Fragment implements LoginView {


    private RecyclerView mRecycler;
    private RecyclerAdapter adapter;
    private int page=1;
    private SmartRefreshLayout mSmar;
    private LoginPersnter persnter;

    public Afragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_afragment, null);
        persnter = new LoginPersnter(this,page);
        initView(inflate);
        persnter.data(page);
        persnter.getBanner();
        return inflate;
    }

    private void initView(View inflate) {
        mRecycler = inflate.findViewById(R.id.recycler);
        mSmar = inflate.findViewById(R.id.smar);

        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new RecyclerAdapter(getActivity());
        mRecycler.setAdapter(adapter);



    }

    @Override
    public void showToast(String str) {

    }

    @Override
    public void setData(final List<ResultBean.DataBean.DatasBean> data) {
        adapter.setResult(data);


        mSmar.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                persnter.data(page);
                mSmar.finishRefresh();
            }
        });

        mSmar.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                data.clear();
                persnter.data(page);
                mSmar.finishRefresh();
            }
        });
    }

    @Override
    public void setBanner(List<BannerBean.DataBean> dataBeans) {
        adapter.setBanner(dataBeans);
    }
}
