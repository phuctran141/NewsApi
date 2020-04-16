package com.example.recyclerview_testapi.api;

import com.example.recyclerview_testapi.model.busisness.ListBook;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
//    http://newsapi.org/v2/everything?q=bitcoin&from=2020-03-10&sortBy=publishedAt&apiKey=API_KEY
    @GET("everything?q=jackma&sortBy=publishedAt&apiKey=17b3c5b4da0a4a5ba3f8144c279bfb17")
    Observable <ListBook> getDatafromapi();
    @GET("everything")
    Observable <ListBook> getDataSearch(@Query("q") String keyword,
                                         @Query("sortBy") String published,
                                         @Query("apiKey") String API_KEY);

}
