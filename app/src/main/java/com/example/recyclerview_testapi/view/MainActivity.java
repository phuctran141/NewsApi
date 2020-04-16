package com.example.recyclerview_testapi.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.recyclerview_testapi.R;
import com.example.recyclerview_testapi.Util.AppConstance;
import com.example.recyclerview_testapi.model.busisness.BookData;
import com.example.recyclerview_testapi.model.busisness.BookAdapter;
import com.example.recyclerview_testapi.model.busisness.ListBook;
import com.example.recyclerview_testapi.model.busisness.Onlistener;
import com.example.recyclerview_testapi.model.request.ApiRequestForm;
import com.example.recyclerview_testapi.model.response.Article;
import com.example.recyclerview_testapi.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    MainViewModel mainViewModel;
    List<BookData> mListBookData;
    BookAdapter mBookAdapter;
    RecyclerView mRecyclerview;
    ProgressBar mProgressBar;
    LinearLayout LayoutLoading, LayoutRecyclerview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("BBB","Hello main");
        init();
        mapview();
        observer();
        onListener();


    }
    private void mapview(){

        mRecyclerview = findViewById(R.id.recyclerview);
        mProgressBar = findViewById(R.id.ProgressBar);
        LayoutRecyclerview=findViewById(R.id.LayoutRecyclerview);
        LayoutLoading=findViewById(R.id.Loading);
        mListBookData = new ArrayList<BookData>();
        mBookAdapter = new BookAdapter(mListBookData);
        mRecyclerview.setAdapter(mBookAdapter);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setItemViewCacheSize(20);
        getLifecycle().addObserver(mainViewModel);

    }
    private void init(){
        mainViewModel = new MainViewModel();
    }
    private void observer(){
        mainViewModel.CallBookAtLocation();
        mainViewModel.isError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d("BBB error",s);
            }
        });
        mainViewModel.isLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {

                if(aBoolean){
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            ShowProgressBar();
//                            Log.d("BBB show bar","Show");
//                        }
//                    },300);
                    ShowProgressBar();

                }
                hideProgressBar();
                Log.d("BBB hide bar","hide");

            }
        });
        mainViewModel.CallDataSucess().observe(this, new Observer<ListBook>() {

            @Override
            public void onChanged(ListBook listBook) {
                mListBookData.clear();
                Log.d("BBB","call thành công");
                for (int i = 0; i<listBook.getArticles().size(); i++){

                    Article article = listBook.getArticles().get(i);
                    String Name = article.getSource().getName();
                    String Author =article.getAuthor();
                    String Title = article.getTitle();
                    String Discription = article.getDescription();
                    String Url = article.getUrl();
                    String Content = article.getContent();
                    String Imgage = article.getUrlToImage();
                    String Time = article.getPublishedAt();

                    mListBookData.add(new BookData(Name,Imgage,Author,Url,Discription,Content,Title,Time));
                }

                mBookAdapter.notifyDataSetChanged();
            }
        });

    }
    private void onListener() {
        mBookAdapter.SetItemOnClickListener(new Onlistener() {
            @Override
            public void onClick(int position) {
//                Intent intent = new Intent(MainActivity.this,DetailsActivity.class);
//                Bundle bundle =new Bundle();
//                BookData BookData;
//                BookData = mListBookData.get(position);
//                bundle.putString("Name",BookData.getName());
//                bundle.putString("Image",BookData.getImge());
//                bundle.putString("Author",BookData.getAuthor());
//                bundle.putString("Content",BookData.getContent());
//                bundle.putString("Description",BookData.getDescription());
//                bundle.putString("Title",BookData.getTitle());
//                bundle.putString("Url",BookData.getUrl());
//                intent.putExtras(bundle);
//                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint("Search lasted News...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("BBB query", query);
                if(query.length()>2){
                mainViewModel.CallDataSearch(query, AppConstance.SORTBY,AppConstance.APP_ID);
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("BBB newText", newText);


                return false;
            }
        });

        searchMenuItem.getIcon().setVisible(false,false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_search:
                Toast.makeText(this, "bạn click search", Toast.LENGTH_SHORT).show();

                break;
            case R.id.action_settings:
                Toast.makeText(this, "bạn click settings", Toast.LENGTH_SHORT).show();
                break;
                
        }
        return super.onOptionsItemSelected(item);
    }

    void ShowProgressBar(){
        LayoutLoading.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.VISIBLE);
        LayoutRecyclerview.setVisibility(View.GONE);
    }
    void hideProgressBar(){
        LayoutLoading.setVisibility(View.GONE);
        LayoutRecyclerview.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
    }
}
