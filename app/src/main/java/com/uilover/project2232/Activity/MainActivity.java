package com.uilover.project2232.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.uilover.project2232.Adapter.CategoryAdapter;
import com.uilover.project2232.Adapter.TopDoctorsAdapter;
import com.uilover.project2232.ViewModel.MainViewModel;
import com.uilover.project2232.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        viewModel=new MainViewModel();
        setContentView(binding.getRoot());

        initCategory();
        initTopDoctors();

    }

    private void initTopDoctors() {
        binding.progressBarDoctor.setVisibility(View.VISIBLE);

        viewModel.loadDoctors().observe(this,doctorsModels -> {
            binding.doctorView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        binding.doctorView.setAdapter(new TopDoctorsAdapter(doctorsModels));
        binding.progressBarDoctor.setVisibility(View.GONE);
        });

    }

    private void initCategory() {
        binding.progressBarCat.setVisibility(View.VISIBLE);

        viewModel.loadCategory().observe(this,categoryModels -> {
            binding.catView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
            binding.catView.setAdapter(new CategoryAdapter(categoryModels));
            binding.progressBarCat.setVisibility(View.GONE);
        });
    }
}