package com.example.climbachiya.glikeanimation;

import android.app.Fragment;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by C.limbachiya on 4/5/2016.
 */
public class FragmentVector extends Fragment {

    private Animation btnAnim;
    RelativeLayout layout;
    ImageView imgReward,imgRewardClone;
    boolean isLiked = false;
    MediaPlayer mp = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.simple_vector_drawable, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        layout = (RelativeLayout) view.findViewById(R.id.root);
        imgReward = (ImageView) view.findViewById(R.id.imgReward);
        imgRewardClone =(ImageView) view.findViewById(R.id.imgRewardClone);

        loadAndSetAnimation();

        imgReward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation();
            }
        });
    }

    //Start actual animation here
    private void startAnimation() {
        imgRewardClone.startAnimation(btnAnim);
        PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(ContextCompat.getColor(getActivity(), R.color.colorAccent),
                PorterDuff.Mode.SRC_ATOP);

        imgRewardClone.setColorFilter(porterDuffColorFilter);
    }

    /**
     * Load animation and set am=nimation
     */
    private void loadAndSetAnimation() {


        btnAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.btn_anim);
        btnAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mp = MediaPlayer.create(getActivity(), R.raw.fb_click);
                mp.start();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mp.release();
                int color;
                if(!isLiked){
                    color = R.color.colorPrimary;
                }else{
                    color = R.color.colorBlack;
                }

                isLiked = !isLiked;
                PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(ContextCompat.getColor(getActivity(), color),
                        PorterDuff.Mode.SRC_ATOP);

                imgReward.setColorFilter(porterDuffColorFilter);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
