package `in`.fundsindia.interviewsample.di.module
import `in`.fundsindia.interviewsample.data.remote.ApiService
import `in`.fundsindia.interviewsample.data.repositoryImpl.MovieRemoteRepositoryImpl
import `in`.fundsindia.interviewsample.domain.repository.MovieRepository
import android.util.Log
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.*

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofit(
            gsonConverterFactory: GsonConverterFactory,
            okHttpClient: OkHttpClient
    ): Retrofit {

        return Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        var tryCount = 0
        val client = OkHttpClient.Builder()
                //  .cache(cache)
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.HEADERS
        interceptor.level = HttpLoggingInterceptor.Level.BODY
//        client.addInterceptor {
//            val original = it.request()
//                val builder = original.newBuilder()
//                    .header("sp_customer_type", "driver")
//                     .method(original.method, original.body)
//            if(!TextUtils.isEmpty(PreferenceUtils.getToken())) {
//                builder.header("Authorization",PreferenceUtils.getToken())
//            }
//
//          val  newRequset = builder.build()
////            var sslSocketFactory=getUnsafeOkHttpClient()
////            if (sslSocketFactory!=null)
////                client.sslSocketFactory(sslSocketFactory)
////            client.hostnameVerifier(object : HostnameVerifier{
////               override fun verify(hostname: String?, session: SSLSession?): Boolean {
////                   Log.d("Network module",""+hostname)
////                    return true
////                }
////            })
////
//            // try the request
//            // try the request
//            var response: Response = it.proceed(newRequset)
//
//            Log.d("CloudRepository", "Request is not successful "+response.isSuccessful +" trycount: "+tryCount +" response cide: "+ response.code)
//
//            while (!response.isSuccessful && response.code.equals(504) && tryCount < 3) {
//                tryCount++
//
//                Log.d("CloudRepository", "Request is not successful inside "+response.isSuccessful +" trycount: "+tryCount)
//
//                // retry the request
//                response = it.proceed(newRequset)
//
//            }
//
//            return@addInterceptor response
//
//
////            return@addInterceptor response
////            val request = builder.build()
////                return@addInterceptor it.proceed(request)
//        }


        client.addNetworkInterceptor(interceptor)
        return client.build()
    }


    private fun getUnsafeOkHttpClient(): SSLSocketFactory? {
        return try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts = arrayOf<TrustManager>(
                object : X509TrustManager  {
                    @Throws(CertificateException::class)
                    override fun checkClientTrusted(
                        chain: Array<X509Certificate?>?,
                        authType: String?
                    ) {
                    }

                    @Throws(CertificateException::class)
                    override fun checkServerTrusted(
                        chain: Array<X509Certificate?>?,
                        authType: String?
                    ) {
                    }

                    override fun getAcceptedIssuers(): Array<X509Certificate?>? {
                        return arrayOf()
                    }
                }
            )

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())
            // Create an ssl socket factory with our all-trusting manager
            return sslContext.socketFactory
        } catch (e: Exception) {
            Log.d("NetworkModule","exception: "+e.localizedMessage)
            throw RuntimeException(e)

        }
    }



    fun getTrustAllHostsSSLSocketFactory(): SSLSocketFactory? {
        try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {

                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    Log.d("Network module","getAcceptedIssuers")

                    return arrayOf()
                }

                @Throws(CertificateException::class)
                override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
                    Log.d("Network module", "checkClientTrusted$authType")
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
                    Log.d("Network module", "checkServerTrusted$authType")
                }
            })

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())
            // Create an ssl socket factory with our all-trusting manager

            return sslContext.socketFactory
        } catch (e: KeyManagementException) {
            e.printStackTrace()
            return null
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
            return null
        }

    }


    @Provides
    @Singleton
    fun providesGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun providesGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }



    @Singleton
    @Provides
    fun provideService( retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideCloudRepository(apIs: ApiService): MovieRepository {
        return MovieRemoteRepositoryImpl(apIs)
    }


}