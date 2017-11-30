package com.footballscore.footballscore.net;

import android.content.Context;
import android.text.TextUtils;

import com.footballscore.footballscore.R;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@Singleton
public final class OauthInterceptor implements Interceptor {

    private static final String X_AUTH_TOKEN = "X-Auth-Token";

    private Context mContext;


    @Inject
    public OauthInterceptor(Context context) {
        mContext = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        final String token = mContext.getString(R.string.token);
        if (!TextUtils.isEmpty(token)) {
            builder.header(X_AUTH_TOKEN, token);
        }

        return chain.proceed(builder.build());
    }
}