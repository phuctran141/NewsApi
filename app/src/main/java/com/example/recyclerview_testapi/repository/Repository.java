package com.example.recyclerview_testapi.repository;

import com.example.recyclerview_testapi.api.ApiService;
import com.example.recyclerview_testapi.api.NetWorkModule;
import com.example.recyclerview_testapi.model.busisness.ListBook;

import io.reactivex.Observable;

public class Repository {

    private ApiService apiService;
    private static Repository repository=null;
    private Repository(){
        apiService = NetWorkModule.getInstance();
    }
    public static Repository getInstance(){
        if(repository==null){
            repository = new Repository();
        }
        return repository;
    }
//    public Observable<ListBook> getDatafromApiService(ApiRequestForm apiRequestForm){
//        return apiService.getDatafromapi(apiRequestForm.getCURRENCY()
//                ,apiRequestForm.getDATE()
//                ,apiRequestForm.getPUBLISHED()
//                ,apiRequestForm.getAPI_KEY());
//    }
    public Observable<ListBook> getDatafromApiService(){
            return apiService.getDatafromapi();
    }
    public Observable<ListBook> getDatafromSearch(String KEY_WORD, String SORTBY, String API_KEY){

        return apiService.getDataSearch(KEY_WORD,SORTBY,API_KEY);
    }



}
