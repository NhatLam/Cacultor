package com.example.calcuclator;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

public class ResultViewModel extends AndroidViewModel {
    private MutableLiveData<Double> result = new MutableLiveData<>();
    private MutableLiveData<String> phuongthuc = new MutableLiveData<>();

    public ResultViewModel(@NonNull Application application) {
        super(application);
        result.setValue(0.0);
        phuongthuc.postValue("");

    }
    public void phuongthuc(String chuoi) {
        phuongthuc.setValue(chuoi);
    }

    public MutableLiveData<String> getPhuongthuc() {
        return phuongthuc;
    }

    public void giatriresult(Double cost) {
        result.setValue(cost);
    }

    public MutableLiveData<Double> getResult() {
        return result;
    }
}
