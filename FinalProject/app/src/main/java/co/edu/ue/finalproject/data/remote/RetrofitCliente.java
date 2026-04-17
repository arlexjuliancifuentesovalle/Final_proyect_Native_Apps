package co.edu.ue.finalproject.data.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCliente { //PUNTO DE ENTRADA CON EL BACKEND
        private static final String BASE_URL = "https://apiberberia-production.up.railway.app/";
        private static Retrofit retrofit;

    public static PagosApiService getApiService() {
        if (retrofit == null) {
            initRetrofit();
        }
        return retrofit.create(PagosApiService.class);
    }

    public static TurnosApiService getTurnosApiService() {
        if (retrofit == null) {
            initRetrofit();
        }
        return retrofit.create(TurnosApiService.class);
    }

    private static void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


}
