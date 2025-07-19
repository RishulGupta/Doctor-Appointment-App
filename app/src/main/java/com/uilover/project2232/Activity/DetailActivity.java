package com.uilover.project2232.Activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.uilover.project2232.Adapter.DateAdapter;
import com.uilover.project2232.Adapter.TimeAdapter;
import com.uilover.project2232.Domain.DoctorsModel;
import com.uilover.project2232.databinding.ActivityDetailBinding;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private ActivityDetailBinding binding;
    private DoctorsModel item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        item = (DoctorsModel) getIntent().getSerializableExtra("object");

        setVariable();
        InitDate();
        InitTime();

    }

    private void InitTime() {
        binding.timeView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.timeView.setAdapter(new TimeAdapter(generateTimeSlots()));
    }

    private void InitDate() {
        binding.dateView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.dateView.setAdapter(new DateAdapter(generateDates()));
    }

    public static List<String> generateTimeSlots() {
        List<String> timeSlots = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");

        for (int i = 0; i < 24; i += 2) {
            LocalTime time = LocalTime.of(i, 0);
            timeSlots.add(time.format(formatter));
        }
        return timeSlots;
    }

    public static List<String> generateDates() {
        List<String> dates = new ArrayList<>();
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE/dd/MMM");
        for (int i = 0; i < 7; i++) {
            dates.add(today.plusDays(i).format(formatter));
        }
        return dates;
    }

    private void setVariable() {

        binding.backBtn.setOnClickListener(v -> finish());

        Glide.with(DetailActivity.this)
                .load(item.getPicture())
                .into(binding.img);

        binding.addressTxt.setText(item.getAddress());
        binding.nameTxt.setText(item.getName());
        binding.specialTxt.setText(item.getSpecial());
        binding.patiensTxt.setText(item.getPatiens() + "");
        binding.experinceTxt.setText(item.getExpriense() + " Years");
    }
}