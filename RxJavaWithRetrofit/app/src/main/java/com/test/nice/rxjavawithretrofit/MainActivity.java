package com.test.nice.rxjavawithretrofit;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String re = null;
        initRequest();
    }

    private void initRequest() {
        Api.getDefault().getSearch("2","5204092642")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(new HttpResultFun<List<SearchBean.DataBean>>(this))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscribeer<List<SearchBean.DataBean>>(this) {
                    @Override
                    protected void _onNext(List<SearchBean.DataBean> been) {
                        Toast.makeText(MainActivity.this,been.size()+"",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    protected void _onError(String message) {
                        Toast.makeText(MainActivity.this,message+"",Toast.LENGTH_SHORT).show();
                    }
                });

    }

//
     class HttpResultFun<T>implements Func1<HttpResult<T>, T> {
        private Context mContext;

    public HttpResultFun(Context context) {
        mContext = context;
    }

    @Override
         public T call(HttpResult<T> result) {
             Toast.makeText(mContext,result.toString(),Toast.LENGTH_SHORT).show();
             if(!result.isSuccess()){
                 Toast.makeText(mContext,result.getMessage(),Toast.LENGTH_SHORT).show();
                 return null;
             }
             return  result.getData();
         }
     }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
