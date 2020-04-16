package com.example.recyclerview_testapi.viewmodel;

import android.util.Log;

import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.recyclerview_testapi.model.busisness.ListBook;
import com.example.recyclerview_testapi.repository.Repository;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel implements LifecycleObserver {
    private Repository mRepository;
    private CompositeDisposable compositeDisposable;
    private MutableLiveData<ListBook> mDataLocation;
    private MutableLiveData<ListBook> mDataSearch;
    private MutableLiveData<String> isError;
    private MutableLiveData<Boolean> isLoading;

    public MainViewModel() {
        this.mRepository = Repository.getInstance();
        this.compositeDisposable = new CompositeDisposable();
        this.mDataLocation = new MutableLiveData<>();
        this.mDataSearch = new MutableLiveData<>();
        this.isError = new MutableLiveData<>();
        this.isLoading = new MutableLiveData<>();
    }
    public LiveData<Boolean> isLoading(){
        return isLoading;
    }
    public LiveData<String> isError(){
        return isError;
    }
    public void CallBookAtLocation(){
        isLoading.setValue(true);
        mRepository.getDatafromApiService().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ListBook>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(ListBook listBook) {
                        mDataLocation.setValue(listBook);

                    }

                    @Override
                    public void onError(Throwable e) {
                        isError.setValue(e.getMessage());
                        Log.d("BBB onError", e.getMessage().toString());

                    }

                    @Override
                    public void onComplete() {
                        isLoading.setValue(false);

                    }
                });
    }
    public void CallDataSearch(String KEY_WORD, String SORTBY, String API_KEY){
        isLoading.setValue(true);
        mRepository.getDatafromSearch(KEY_WORD, SORTBY, API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ListBook>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(ListBook listBook) {
                        mDataLocation.setValue(listBook);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("BBB onError", e.getMessage());
                        isError.setValue(e.getMessage());

                    }

                    @Override
                    public void onComplete() {
                        isLoading.setValue(false);

                    }
                });
    }
    public LiveData<ListBook> CallDataSucess(){
        return mDataLocation;
    }
}
