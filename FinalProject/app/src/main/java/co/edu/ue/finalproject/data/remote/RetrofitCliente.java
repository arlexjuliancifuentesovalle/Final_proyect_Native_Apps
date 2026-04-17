package co.edu.ue.finalproject.data.remote;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitCliente {
    private static final String BASE_URL = "https://apiberberia-production.up.railway.app/";
    private static Retrofit retrofit;

    private static void initRetrofit() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new AuthInterceptor())
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create()) // Para recibir el token como String
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static PagosApiService getApiService() {
        if (retrofit == null) initRetrofit();
        return retrofit.create(PagosApiService.class);
    }

    public static TurnosApiService getTurnosApiService() {
        if (retrofit == null) initRetrofit();
        return retrofit.create(TurnosApiService.class);
    }

    public static UserApiService getUserApiService() {
        if (retrofit == null) initRetrofit();
        return retrofit.create(UserApiService.class);
    }
}
