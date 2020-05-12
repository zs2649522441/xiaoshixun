package com.example.lx;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import bean.BannerBean;
import bean.ResultBean;

public class RecyclerAdapter extends RecyclerView.Adapter {
    private int TYPE_BANNER = 0;
    private int TYPE_desc = 1;

    Context context;
    List<ResultBean.DataBean.DatasBean> result=new ArrayList<>();
    List<BannerBean.DataBean> banner=new ArrayList<>();

    public RecyclerAdapter(Context context) {
        this.context = context;

    }

    public void setBanner(List<BannerBean.DataBean> banner) {
        this.banner.addAll(banner);
        notifyDataSetChanged();
    }

    public void setResult(List<ResultBean.DataBean.DatasBean> result) {
        this.result.addAll(result);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.banneritem, parent, false);
            return new Holder(inflate);
        } else if (viewType == 1) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.descitem, parent, false);
            return new HolderTwo(inflate);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        if (type==TYPE_BANNER){
            ((Holder)holder).mBanner.setImages(banner).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    BannerBean.DataBean path1 = (BannerBean.DataBean) path;
                    Glide.with(context).load(path1.getImagePath()).into(imageView);
                }
            }).start();
        }else if (type==TYPE_desc){
            if (banner.size()>0){
                position=position-1;
            }
            ((HolderTwo)holder).mDesc.setText(result.get(position).getTitle());
        }
    }

    @Override
    public int getItemCount() {
       if (banner.size()>0){
           return result.size()+1;
       }else {
           return result.size();
       }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0&&banner.size()>0) {
            return TYPE_BANNER;
        } else {
            return TYPE_desc;
        }

    }

    class Holder extends RecyclerView.ViewHolder {
        private Banner mBanner;
        public Holder(@NonNull View itemView) {
            super(itemView);
            mBanner = itemView.findViewById(R.id.banner);
        }
    }

    class HolderTwo extends RecyclerView.ViewHolder {
        private TextView mDesc;

        public HolderTwo(@NonNull View itemView) {
            super(itemView);
            mDesc = itemView.findViewById(R.id.desc);
        }
    }
}
