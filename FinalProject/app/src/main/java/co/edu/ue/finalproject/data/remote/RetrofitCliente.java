package co.edu.ue.finalproject.data.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCliente { //PUNTO DE ENTRADA CON EL BACKEND
        private static final String BASE_URL = "https://apiberberia-production.up.railway.app/";
        private static Retrofit retrofit;

        public static PagosApiService getApiService(){
            if (retrofit == null){ //verifica si ya
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL) //establece la ur
                        .addConverterFactory(GsonConverterFactory.create()) //respuestas json a objetos PagosDTO
                        .build(); //la construlle
            }
            return retrofit.create(PagosApiService.class); //crea y retorna la implementacion
        }


}
