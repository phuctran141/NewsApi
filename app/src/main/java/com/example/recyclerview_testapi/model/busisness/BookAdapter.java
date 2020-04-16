package com.example.recyclerview_testapi.model.busisness;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview_testapi.R;
import com.example.recyclerview_testapi.Util.Ultils;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHodel> {

    private List<BookData> mArrayBookData;
    private Onlistener mOnItemListener;

    public BookAdapter(List<BookData> listBookData) {
//        mArrayBookData =new ArrayList<>();
        mArrayBookData=listBookData;
    }

    public void SubmitArray(List<BookData> listBookData){
        mArrayBookData.clear();
        mArrayBookData.addAll(listBookData);
    }
    @NonNull
    @Override
    public ViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item,parent,false);
        ViewHodel viewHodel = new ViewHodel(view);
        return viewHodel;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodel holder, int position) {

        BookData bookData = mArrayBookData.get(position);
//        holder.mName.setText("Name: "+ bookData.getName());
        String Author = bookData.getAuthor();
//        if(Author!=null && Author.length()>15){
//            Author = Author.substring(0,14)+"...";
//        }else if(Author==null){
//            Author = "...";
//        }
        String Time = bookData.getPublisherAt();
        Log.d("BBB time:",Time);
        String Desc = bookData.getContent();
        holder.mDescription.setText(Desc);
        String Title = bookData.getTitle();
        holder.mTitle.setText(Title);
        holder.mAuthor.setText("Author: "+ Author);
        String Name = bookData.getName();
//        if(Url.length()>35){
//            Url=Url.substring(0,34)+"...";
//        }

        holder.mName.setText(Name);
        holder.mDate.setText(Ultils.DateFormat(bookData.getPublisherAt()));
        holder.mTime.setText(Ultils.DateToTimeFormat(bookData.getPublisherAt()));

        Picasso.get().load(bookData.getImge()).into(holder.mImgae, new Callback() {
            @Override
            public void onSuccess() {
                holder.mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {
                holder.mProgressBar.setVisibility(View.GONE);
                Log.d("BBB error:" , e.getMessage());

            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemListener.onClick(position);
                Log.d("BBB vi trí", "" + position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArrayBookData !=null ? mArrayBookData.size() : 0;
    }

    class ViewHodel extends RecyclerView.ViewHolder{

        private ImageView mImgae;
        ProgressBar mProgressBar;
        private TextView mName, mAuthor, mTitle, mUrl, mTime, mDescription,mDate;
        public ViewHodel(@NonNull View itemView) {
            super(itemView);
            mProgressBar =itemView.findViewById(R.id.progress_loading_photo);
            mImgae= itemView.findViewById(R.id.img);
            mTitle=itemView.findViewById(R.id.Title);
            mAuthor=itemView.findViewById(R.id.Author);
            mDescription= itemView.findViewById(R.id.Des);
            mName=itemView.findViewById(R.id.Source);
            mDate=itemView.findViewById(R.id.pushlishAt);
            mTime=itemView.findViewById(R.id.Time);



        }
    }
    public void SetItemOnClickListener (Onlistener onItemListener){
        this.mOnItemListener = onItemListener;
    }
}
