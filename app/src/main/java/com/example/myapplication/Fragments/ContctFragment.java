package com.example.myapplication.Fragments;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.Adapter.ContactAdapter;
import com.example.myapplication.Database.SqLiteHelper;
import com.example.myapplication.Pojo.Contact;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class ContctFragment extends Fragment implements View.OnClickListener {

    View view;
    SqLiteHelper sqLiteHelper;
    Button button;
    RecyclerView mRecyclerView;
    Cursor cursor;
    private String _name, _phoneno;
    int _id;
    List<Contact> getcontactlist;
    private ContactAdapter contactAdapter;

    public static ContctFragment newInstance() {
        return new ContctFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.contct_fragment, container, false);

        sqLiteHelper = new SqLiteHelper(getActivity());

        button = view.findViewById(R.id.contactno);
        mRecyclerView = view.findViewById(R.id.recyclerview);


        button.setOnClickListener(this);

        int count = 0;
        try {
            count = sqLiteHelper.DeleteContact();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        ArrayList<Contact> contact_list = new ArrayList<Contact>();
        contact_list.add(new Contact("pooja", "1"));
        contact_list.add(new Contact("pooja1", "12"));
        contact_list.add(new Contact("pooja2", "123"));
        contact_list.add(new Contact("pooja3", "1234"));
        contact_list.add(new Contact("pooja4", "12345"));
        contact_list.add(new Contact("pooja5", "123456"));
        contact_list.add(new Contact("pooja6", "1234567"));
        contact_list.add(new Contact("pooja7", "12345678"));


        for (int i=0; i<contact_list.size(); i++){
            String name = contact_list.get(i).getName();
            String phoneno = contact_list.get(i).getPhoneno();

            long id = sqLiteHelper.insertContact(name, phoneno);
            Log.e("tag_id", String.valueOf(id));
        }





        return view;
    }


    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.contactno){

            getContactData();


            if (getcontactlist.size()>0){
                contactAdapter = new ContactAdapter(getActivity(), getcontactlist);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setAdapter(contactAdapter);


            }




        }
    }

    public List<Contact> getContactData() {
        getcontactlist = new ArrayList<>();

        try {
            sqLiteHelper.open(getActivity());
        } catch (Exception e) {
            e.printStackTrace();
        }

        cursor =sqLiteHelper.getAllContactData();

        while (cursor.moveToNext()){

            _id = 1;
            _name = cursor.getString(cursor.getColumnIndex(SqLiteHelper.DbHelper.KEY_NAME));
            _phoneno = cursor.getString(cursor.getColumnIndex(SqLiteHelper.DbHelper.KEY_PH_NO));
            getcontactlist.add(new Contact(_id,_name, _phoneno));
        }

        return getcontactlist;
    }
}