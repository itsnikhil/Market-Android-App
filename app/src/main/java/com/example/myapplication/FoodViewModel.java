package com.example.myapplication;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FoodViewModel extends AndroidViewModel {
    private MutableLiveData<List<ItemModal>> data;
    private Application application;
    public FoodViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }

    public LiveData<List<ItemModal>> getItems() {
        if (data == null) {
            data = new MutableLiveData<List<ItemModal>>();
            loadUsers();
        }
        return data;
    }

    private void loadUsers() {
        String json = null;
        InputStream is;
        try {
            is = this.application.getAssets().open("food.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);
            List<ItemModal> itemList = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                ItemModal item = new ItemModal();
                item.setId(obj.getInt("id"));
                item.setName(obj.getString("name"));
                item.setDate(obj.getString("date"));
                item.setCategory(obj.getString("category"));
                item.setQuantity(obj.getInt("quantity"));
                item.setInventory(obj.getInt("inventory"));
                item.setType(obj.getString("type"));
                item.setCompany(obj.getString("company"));
                item.setDescription(obj.getString("description"));
                item.setImage(obj.getString("image"));
                item.setVeg(obj.getBoolean("veg"));
                item.setPrice((float) obj.getDouble("price"));
                itemList.add(item);
            }
            data.setValue(itemList);;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            data.setValue(null);
        }
    }
}
