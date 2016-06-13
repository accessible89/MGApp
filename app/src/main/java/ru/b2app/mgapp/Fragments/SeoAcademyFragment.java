package ru.b2app.mgapp.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.b2app.mgapp.R;

/**
 * Created by acces on 13.06.2016.
 */
public class SeoAcademyFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.seo_fragment, container, false);
        return v;
    }
}
