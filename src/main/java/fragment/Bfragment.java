package fragment;


import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.fragment.app.Fragment;

import com.example.lx.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Bfragment extends Fragment {


    private VideoView mVideo;

    public Bfragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_bfragment, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        mVideo = inflate.findViewById(R.id.video);

        String Video="https://yunxue-bucket.oss-cn-shanghai.aliyuncs.com/classfile/0/从技术走向管理/001.从技术到管理_第1节_从技术到管理的内外部因素.mp4";
        Uri uri = Uri.parse( Video);


        //设置视频路径
        mVideo.setVideoURI(uri);
        //开始播放视频
        mVideo.start();
        //视频进度控制器
        mVideo.setMediaController(new MediaController(getActivity()));

        /**
         * 循环播放
         */
        mVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
                mp.setLooping(true);

            }
        });

    }

}