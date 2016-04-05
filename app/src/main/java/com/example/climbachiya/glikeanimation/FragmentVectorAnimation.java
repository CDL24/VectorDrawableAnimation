package com.example.climbachiya.glikeanimation;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by C.limbachiya on 4/5/2016.
 */
public class FragmentVectorAnimation extends Fragment {

    ImageView imgTruck;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.vector_drawable_animated, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imgTruck = (ImageView) view.findViewById(R.id.imgTruck);
    }


}
