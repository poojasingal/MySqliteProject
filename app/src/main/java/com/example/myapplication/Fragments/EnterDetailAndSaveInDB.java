package com.example.myapplication.Fragments;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.myapplication.Adapter.ContactAdapter;
import com.example.myapplication.Adapter.UserAdapter;
import com.example.myapplication.Database.SqLiteHelper;
import com.example.myapplication.Pojo.Users;
import com.example.myapplication.R;
import com.example.myapplication.ViewModels.EnterDetailViewModel;

import java.util.ArrayList;
import java.util.List;

public class EnterDetailAndSaveInDB extends Fragment implements View.OnClickListener {
    View view;
    EditText mName, mLoc, mDesg;
    Button mSave, mRetrive;
    private SqLiteHelper sqLiteHelper;
    EnterDetailViewModel enterDetailViewModel;
    private UserAdapter adapter;
    private RecyclerView mRecyclerView;
    List<Users> userList;
    private Cursor cursor;
    private String name, loc, desg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_enter_detail_and_save_in_d_b, container, false);

        sqLiteHelper = new SqLiteHelper(getActivity());
        enterDetailViewModel = new EnterDetailViewModel();

        mName = view.findViewById(R.id.et_name);
        mLoc = view.findViewById(R.id.et_loc);
        mDesg = view.findViewById(R.id.et_desg);
        mSave = view.findViewById(R.id.bt_save);
        mRecyclerView = view.findViewById(R.id.recyclerview);
        mRetrive = view.findViewById(R.id.bt_retrive);

        mSave.setOnClickListener(this);
        mRetrive.setOnClickListener(this);

     //   callAdapter();



        return view;
    }

    private void callAdapter() {

        userList = new ArrayList<>();

        try {
            sqLiteHelper.open(getActivity());
        } catch (Exception e) {
            e.printStackTrace();
        }

        cursor = sqLiteHelper.getAllUserDetails();

        while (cursor.moveToNext()){

            name = cursor.getString(cursor.getColumnIndex(SqLiteHelper.DbHelper.KEY_USER_NAME));
            loc = cursor.getString(cursor.getColumnIndex(SqLiteHelper.DbHelper.KEY_USER_LOC));
            desg = cursor.getString(cursor.getColumnIndex(SqLiteHelper.DbHelper.KEY_USER_DESG));

            userList.add(new Users(name, loc, desg));

        }

        adapter = new UserAdapter(getActivity(), userList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adapter);

    }

    @Override
    public void onClick(View view) {

        if (view.getId()==R.id.bt_save){

            enterDetailViewModel.onSaveClick(getActivity(), mName, mLoc, mDesg);

        }
        else if (view.getId()==R.id.bt_retrive){
            callAdapter();
        }
    }
}