package com.yarolegovich.slidingrootnav.sample.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.yarolegovich.slidingrootnav.sample.ModelClass;
import com.yarolegovich.slidingrootnav.sample.ProductAdapter;
import com.yarolegovich.slidingrootnav.sample.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ProductList extends Fragment {
RecyclerView rv;
ArrayList<ModelClass>arrayList;
    RequestQueue requestQueue;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_product_list, container, false);
        rv=view.findViewById(R.id.product);
        requestQueue = Volley.newRequestQueue(getContext());
        arrayList = new ArrayList<>();


        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, "http://lokalstreet.com/api/get_category", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //   Toast.makeText(getApplicationContext(), "" + response, Toast.LENGTH_SHORT).show();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String product= jsonObject.getString("name");
                        String id= jsonObject.getString("id");

                   ModelClass modelClass=new ModelClass();

                        modelClass.setTextview(product);


                        arrayList.add(modelClass);
                       ProductAdapter Ca=new ProductAdapter(arrayList,getContext());
                        rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                       rv.setAdapter(Ca);


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue.add(stringRequest1);

        return view;
    }
}