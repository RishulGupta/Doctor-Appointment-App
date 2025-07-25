package com.uilover.project2232.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.uilover.project2232.Domain.CategoryModel;
import com.uilover.project2232.Domain.DoctorsModel;
import com.uilover.project2232.Repository.MainRepository;

import java.util.List;

public class MainViewModel extends ViewModel {

    private final MainRepository repository;

    public MainViewModel() {
        this.repository = new MainRepository();
    }
    public LiveData<List<CategoryModel>> loadCategory(){return repository.loadCategory();}

    public LiveData<List<DoctorsModel>> loadDoctors(){
        return  repository.loadDoctor();
    }
}
