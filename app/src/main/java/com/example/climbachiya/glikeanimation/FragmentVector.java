package com.example.climbachiya.glikeanimation;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Fragment;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
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

    private Animation btnAnim, scaleAnim;
    RelativeLayout layout;
    ImageView imgReward,imgRewardClone, imgHeart;
    boolean isLiked = false;
    boolean isHearted = false;
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
        imgHeart =(ImageView) view.findViewById(R.id.image_heart);

        changeBackground();

        loadAndSetAnimation();

        imgReward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation();
            }
        });
        imgHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimationHeart();
            }
        });
    }

    private void startAnimationHeart() {

        imgHeart.startAnimation(scaleAnim);

    }

    private void changeBackground() {

        int colorFrom = Color.WHITE;
        int colorTo = ContextCompat.getColor(getActivity(), R.color.colorsky);
        int duration = 1000;
        ObjectAnimator.ofObject(layout, "backgroundColor", new ArgbEvaluator(), colorFrom, colorTo)
                .setDuration(duration)
                .start();

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

        scaleAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.scale_heart);
        scaleAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

                /*AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(getActivity(),
                        R.animator.property_animator);
                set.setTarget(imgHeart);
                set.start();*/

                //animateImageView();
                mp = MediaPlayer.create(getActivity(), R.raw.fb_click);
                mp.start();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mp.release();
                int color;
                if(!isHearted){
                    color = R.color.colorAccent;
                }else{
                    color = R.color.colorBlack;
                }

                isHearted = !isHearted;
                PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(ContextCompat.getColor(getActivity(), color),
                        PorterDuff.Mode.SRC_ATOP);

                imgHeart.setColorFilter(porterDuffColorFilter);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        btnAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.like_animation);
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

    private void animateImageView() {

        final int color = ContextCompat.getColor(getActivity(), R.color.colorAccent);

        final ValueAnimator colorAnim = ObjectAnimator.ofFloat(0f, 1f);
        colorAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float mul = (Float) animation.getAnimatedValue();
                int alphaOrange = adjustAlpha(color, mul);
                imgHeart.setColorFilter(alphaOrange, PorterDuff.Mode.SRC_ATOP);
                if (mul == 0.0) {
                    imgHeart.setColorFilter(null);
                }
            }
        });

        colorAnim.setDuration(1500);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.setRepeatCount(-1);
        colorAnim.start();
    }

    public int adjustAlpha(int color, float factor) {
        int alpha = Math.round(Color.alpha(color) * factor);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }
}
