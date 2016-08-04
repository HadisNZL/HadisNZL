package com.ubetween.hadisnzl.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ubetween.hadisnzl.R;
import com.ubetween.hadisnzl.base.GuideBaseFragment;


/**
 * @author hadis on 16.7.22.
 */
public class ThreeFragment extends GuideBaseFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_three, container, false);
        return view;
    }
}
