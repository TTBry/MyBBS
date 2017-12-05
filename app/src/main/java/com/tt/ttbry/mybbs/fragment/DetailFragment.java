package com.tt.ttbry.mybbs.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.tt.ttbry.mybbs.R;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by TTBry on 2017/12/5.
 */

public class DetailFragment extends BaseFragment {
    private static final String IMAGE_URL = "imageUrl";
    PhotoView mPvShowPhoto;

    public static DetailFragment newInstance(String imageUrl) {
        DetailFragment fragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(IMAGE_URL, imageUrl);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_meizi_fragment_layout, container, false);
        mPvShowPhoto = view.findViewById(R.id.detail_pv_show_photo);
        Bundle bundle = getArguments();
        String imageUrl = bundle.getString(IMAGE_URL);
        Glide.with(this).load(imageUrl).into(mPvShowPhoto);
        mPvShowPhoto.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float v, float v1) {
                getActivity().finish();
            }

            @Override
            public void onOutsidePhotoTap() {

            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
