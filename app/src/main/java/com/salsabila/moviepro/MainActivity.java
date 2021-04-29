package com.salsabila.moviepro;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.listPo)
    RecyclerView lisMo;
    MAdapter MAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        MAdapter = new MAdapter();
        lisMo.setLayoutManager(new LinearLayoutManager(this));
        lisMo.setAdapter(MAdapter);
        lisMo.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        RestClient.getMService().getMo().enqueue(new Callback<MResponse>() {

            @Override
            public void onResponse(Call<MResponse> call, Response<MResponse> response) {
                MAdapter.listMo.addAll(response.body().getSearch());
                MAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }
}