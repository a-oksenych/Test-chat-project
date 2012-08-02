package com.epam.chat.service;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.util.EntityUtils;

import java.net.URI;

public abstract class RestService<T> {

    private DefaultHttpClient restClient;

    public RestService() {
        ThreadSafeClientConnManager cm = new ThreadSafeClientConnManager();
        cm.setMaxTotal(200); // Increase max total connection to 200
        cm.setDefaultMaxPerRoute(200); // Increase default max connection per route to 20

        restClient = new DefaultHttpClient(cm);
    }

    public T get(String id) {
        HttpGet get = new HttpGet();
        workWithId(id, get);

        return null;
    }

    public T delete(String id) {
        HttpDelete delete = new HttpDelete();
        workWithId(id, delete);
        return null;
    }

    protected void workWithId(String id, HttpRequestBase get) {
        try {
            get.setURI(new URI(getHost() + getEntityName() + id));
            HttpResponse result = restClient.execute(get);
            System.out.println(EntityUtils.toString(result.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract String getHost();

    protected abstract String getEntityName();

    public T create(T entity) {
        HttpPost post = new HttpPost();
        T result = workWithEntity(post, entity);
        return result;
    }

    private T workWithEntity(HttpEntityEnclosingRequestBase request, T entity) {
        T result = null;
        try {
            request.setURI(new URI(getHost() + getEntityName() + "create"));
            String string = buildJson(entity);
            StringEntity stringEntity = new StringEntity(string);
            request.setEntity(stringEntity);
            HttpResponse resultText = restClient.execute(request);
            result = parseJson(resultText);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return result;
    }

    protected abstract T parseJson(HttpResponse resultText);

    protected abstract String buildJson(T entity);

    public T updateRoom(T entity) {
        HttpPost post = new HttpPost();
        T result = workWithEntity(post, entity);
        return result;
    }

}
