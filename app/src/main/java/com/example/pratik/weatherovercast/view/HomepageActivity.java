package com.example.pratik.weatherovercast.view;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pratik.weatherovercast.R;
import com.example.pratik.weatherovercast.Util.Utility;
import com.example.pratik.weatherovercast.model.ResObj;
import com.example.pratik.weatherovercast.retrofit.ApiUtils;
import com.example.pratik.weatherovercast.retrofit.UserService;
import com.example.pratik.weatherovercast.room.MyAppDatabase;

import java.net.HttpURLConnection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomepageActivity extends AppCompatActivity {

    @BindView(R.id.imgSearch)
    ImageView imgSearch;

    @BindView(R.id.etSearch)
    EditText etSearch;

    @BindView(R.id.btSearch)
    Button btSearch;

    @BindView(R.id.imgStatus)
    ImageView imgStatus;

    @BindView(R.id.tvTemp)
    TextView tvTemp;

    @BindView(R.id.tvLocation)
    TextView tvLocation;

    @BindView(R.id.tvStatus)
    TextView tvStatus;

    @BindView(R.id.tvMax)
    TextView tvMax;

    @BindView(R.id.tvMin)
    TextView tvMin;

    @BindView(R.id.tvPressure)
    TextView tvPressure;

    @BindView(R.id.tvHumidity)
    TextView tvHumidity;

    @BindView(R.id.ll_back)
    LinearLayout ll_back;

    EditText cty_name;
    Button button;
    UserService userService;
    TextView textView;
    ResObj resObj;
    int count=0;
    public static MyAppDatabase myAppDatabase;
    private Observable<List<ResObj>> objObservable;
    List<ResObj> resObjList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        ButterKnife.bind(this);
       // textView=findViewById(R.id.textview);

        myAppDatabase= Room.databaseBuilder(getApplicationContext(),MyAppDatabase.class,"weatherdb").allowMainThreadQueries().build();
        userService= ApiUtils.getUserService();
       /* textView=findViewById(R.id.textview);
        cty_name=findViewById(R.id.et);
        button=findViewById(R.id.bt);

        userService= ApiUtils.getUserService();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cityname=cty_name.getText().toString();
                display(cityname);

                //switching
            }
        });*/

        if ((resObjList=myAppDatabase.myDao().getInfo()).size()>0)
        {
            objObservable=Observable.fromArray(resObjList);
            showData();
        }
        else
            display("bangalore");



    }

    @OnClick(R.id.imgSearch)
    public void onClickOfSearchIcon()
    {
        imgSearch.setVisibility(View.GONE);
        etSearch.setVisibility(View.VISIBLE);
        btSearch.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.btSearch)
    public void onClickOfSearchButton()
    {
       display(etSearch.getText().toString()) ;
    }
    private void display(final String cityname)
    {
        //switched
        Call<ResObj> call=userService.login(cityname);
        call.enqueue(new Callback<ResObj>() {
            @Override
            public void onResponse(Call<ResObj> call, Response<ResObj> response) {
                if (response.code()== HttpURLConnection.HTTP_OK)
                {
                     resObj=response.body();
                    myAppDatabase.myDao().addInfo(resObj);
                    resObjList=myAppDatabase.myDao().getInfo();
                    objObservable=Observable.fromArray(resObjList);
                    showData();
                    count++;

                    //textView.setText(resObj.toString());
                }
            }

            @Override
            public void onFailure(Call<ResObj> call, Throwable t) {

            }
        });
    }

    public void showData()
    {
        if (objObservable!=null)
        {
            objObservable.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<List<ResObj>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @SuppressLint("ResourceAsColor")
                        @Override
                        public void onNext(List<ResObj> resObjs) {
                         //   textView.setText(resObjs.get(resObjs.size() - 1).getName());
                            if (resObjs.get(resObjs.size()-1).getWeather().get(0).getIcon().charAt(2)=='n')
                            {
                                ll_back.setBackgroundColor(R.color.night);
                                tvTemp.setTextColor(R.color.colorAccent);
                                tvLocation.setTextColor(R.color.colorAccent);
                                tvStatus.setTextColor(R.color.colorAccent);
                                tvMax.setTextColor(R.color.colorAccent);
                                tvMin.setTextColor(R.color.colorAccent);
                                tvPressure.setTextColor(R.color.colorAccent);
                                tvHumidity.setTextColor(R.color.colorAccent);
                            }
                            switch (resObjs.get(resObjs.size()-1).getWeather().get(0).getDescription())
                            {
                                case "clear sky":imgStatus.setImageResource(R.drawable.o1d);
                                                 break;
                                case "few clouds":imgStatus.setImageResource(R.drawable.o2d);
                                    break;
                                case "scattered clouds":imgStatus.setImageResource(R.drawable.o3d);
                                    break;
                                case "broken clouds":imgStatus.setImageResource(R.drawable.o4d);
                                    break;
                                case "shower rain":imgStatus.setImageResource(R.drawable.o9d);
                                    break;
                                case "rain":imgStatus.setImageResource(R.drawable.i0d);
                                    break;
                                case "thunderstorm":imgStatus.setImageResource(R.drawable.i1d);
                                    break;
                                case "snow":imgStatus.setImageResource(R.drawable.i3d);
                                    break;
                                case "mist":imgStatus.setImageResource(R.drawable.f0d);
                                    break;
                               default:imgStatus.setImageResource(R.drawable.f0d);
                                         break;

                            }
                            tvTemp.setText(String.valueOf(Utility.convertKtoC(resObjs.get(resObjs.size()-1).getMain().getTemp())));
                            tvLocation.setText(resObjs.get(resObjs.size()-1).getName()+" , "+resObjs.get(resObjs.size()-1).getSys().getCountry());
                            tvStatus.setText(resObjs.get(resObjs.size()-1).getWeather().get(0).getDescription());
                            tvMax.setText("Max Temp  ~  "+ Utility.convertKtoC(resObjs.get(resObjs.size()-1).getMain().getTempMax()));
                            tvMin.setText("Min Temp  ~  "+Utility.convertKtoC(resObjs.get(resObjs.size()-1).getMain().getTempMin()));
                            tvPressure.setText("Pressure ~  "+resObjs.get(resObjs.size()-1).getMain().getPressure());
                            tvHumidity.setText("Humidity    ~  "+resObjs.get(resObjs.size()-1).getMain().getHumidity());

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });

        }

    }


}
