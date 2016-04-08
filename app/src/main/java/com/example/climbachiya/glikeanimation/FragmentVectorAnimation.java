package com.example.climbachiya.glikeanimation;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Fragment;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ViewAnimator;

/**
 * Created by C.limbachiya on 4/5/2016.
 */
public class FragmentVectorAnimation extends Fragment {

    ImageView imgTruck,imgView, imgRefreshCircle, imgRefreshCircleFlat;
    ImageView imgHorizontal,imgVertical;
    RelativeLayout llRoot;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.vector_drawable_animated, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        llRoot = (RelativeLayout) view.findViewById(R.id.root);
        imgTruck = (ImageView) view.findViewById(R.id.imgTruck);
        imgView = (ImageView) view.findViewById(R.id.imageView);
        imgRefreshCircle = (ImageView) view.findViewById(R.id.imageCircleRefresh);
        imgRefreshCircleFlat = (ImageView) view.findViewById(R.id.imageCircleRefreshFlat);
        imgHorizontal = (ImageView) view.findViewById(R.id.image_horizontal);
        imgVertical = (ImageView) view.findViewById(R.id.image_vertical);

        /*ArcTranslateAnimation animation = new ArcTranslateAnimation(
                0, 600, 0, 0);
        animation
                .setInterpolator(new LinearInterpolator());
        animation.setDuration(3000);
        animation.setRepeatMode(ValueAnimator.REVERSE);
        animation.setRepeatCount(-1);
        animation.setFillAfter(true);
        imgTruck.startAnimation(animation);*/

        Animation anim = new CircularAnimation(imgTruck, 100);
        anim.setDuration(3000);
        anim.setRepeatCount(Animation.INFINITE);
        imgTruck.startAnimation(anim);

        changeBackground();

        loadGreenVector();

        loadBlueVector();

        loadPinkVector();

        loadRectangleAnimation();

        animateImageView(imgTruck);
    }

    private void loadRectangleAnimation() {

        AnimatorSet setHorzAnim = (AnimatorSet) AnimatorInflater.loadAnimator(getActivity(),
                R.animator.horizontal_line_rotate);
        setHorzAnim.setTarget(imgHorizontal);
        setHorzAnim.start();

        AnimatorSet setVetlAnim = (AnimatorSet) AnimatorInflater.loadAnimator(getActivity(),
                R.animator.vertical_line_rotate);
        setVetlAnim.setTarget(imgVertical);
        setVetlAnim.start();
    }

    private void loadPinkVector() {

        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(getActivity(),
                R.animator.circle_expand);
        set.setTarget(imgView);
        set.start();
    }

    private void loadBlueVector() {

        AnimatorSet setAnim = (AnimatorSet) AnimatorInflater.loadAnimator(getActivity(),
                R.animator.circle_expand_rotate);
        setAnim.setTarget(imgRefreshCircle);
        setAnim.start();
    }

    private void loadGreenVector() {

        AnimatorSet setFlat = (AnimatorSet) AnimatorInflater.loadAnimator(getActivity(),
                R.animator.circle_expand_rotate_flat);
        setFlat.setTarget(imgRefreshCircleFlat);
        setFlat.start();
    }

    private void changeBackground() {

        int colorFrom = Color.WHITE;
        int colorTo = ContextCompat.getColor(getActivity(), R.color.colorlightgreen);
        int duration = 1000;
        ObjectAnimator.ofObject(llRoot, "backgroundColor", new ArgbEvaluator(), colorFrom, colorTo)
                .setDuration(duration)
                .start();

    }

    private void animateImageView(final ImageView imgView) {

        final int color = ContextCompat.getColor(getActivity(), R.color.colorAccent);

        final ValueAnimator colorAnim = ObjectAnimator.ofFloat(0f, 1f);
        colorAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float mul = (Float) animation.getAnimatedValue();
                int alphaOrange = adjustAlpha(color, mul);
                imgView.setColorFilter(alphaOrange, PorterDuff.Mode.SRC_ATOP);
                if (mul == 0.0) {
                    imgView.setColorFilter(null);
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
