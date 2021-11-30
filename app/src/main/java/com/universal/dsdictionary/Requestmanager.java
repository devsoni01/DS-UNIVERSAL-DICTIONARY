package com.universal.dsdictionary;

import android.content.Context;
import android.widget.Toast;

import com.universal.dsdictionary.Models.Apiresponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class Requestmanager {

    Context context;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.dictionaryapi.dev/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public Requestmanager(Context context) {
        this.context = context;
    }

    public void getWordMeaning(OnFetchDataListener listener, String word){
        CallDictionary callDictionary = retrofit.create(CallDictionary.class);
        Call<List<Apiresponse>> call = callDictionary.callMeanings(word);

        try{
            call.enqueue(new Callback<List<Apiresponse>>() {
                @Override
                public void onResponse(Call<List<Apiresponse>> call, Response<List<Apiresponse>> response) {
                    if (!response.isSuccessful()){
                        Toast.makeText(context, "Error !!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    listener.onFetchData(response.body().get(0), response.message());
                }

                @Override
                public void onFailure(Call<List<Apiresponse>> call, Throwable t) {

                    listener.onError("Request Failed !!");
                }
            });
        } catch (Exception e){
            e.printStackTrace();
            Toast.makeText(context, "An Error Occured !!!", Toast.LENGTH_SHORT).show();
        }
    }

    public interface CallDictionary {
        @GET("entries/en/{word}")
        Call<List<Apiresponse>> callMeanings(
                @Path("word") String word
        );
    }
}
