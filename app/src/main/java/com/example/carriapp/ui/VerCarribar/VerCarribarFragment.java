package com.example.carriapp.ui.VerCarribar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.example.carriapp.databinding.ActivityVerCarribarBinding;

public class VerCarribarFragment extends Fragment {

    private ActivityVerCarribarBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = ActivityVerCarribarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

         return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}