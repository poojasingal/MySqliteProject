package com.example.myapplication.ViewModels;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.Database.SqLiteHelper;

public class EnterDetailViewModel extends ViewModel {
    SqLiteHelper sqLiteHelper;

    public void onSaveClick(Context context, EditText mName, EditText mLoc, EditText mDesg) {
        sqLiteHelper = new SqLiteHelper(context);

        if(performSaveVAlidation(context, mName, mLoc, mDesg)){
            long id = sqLiteHelper.insertUsers(mName.getText().toString(), mLoc.getText().toString(), mDesg.getText().toString());

            Log.e("tag_id", String.valueOf(id));

        }

    }

    private boolean performSaveVAlidation(Context context, EditText mName, EditText mLoc, EditText mDesg) {

        String name = mName.getText().toString();
        String loc = mLoc.getText().toString();
        String desg = mDesg.getText().toString();


        boolean isValidated = true;

        if (name.isEmpty()){
            Toast.makeText(context, "Please enter name", Toast.LENGTH_SHORT).show();
            isValidated = false;
        }
        else if (loc.isEmpty()){
            Toast.makeText(context, "Please enter Location", Toast.LENGTH_SHORT).show();
            isValidated = false;
        }
        else if (desg.isEmpty()){
            Toast.makeText(context, "Please enter Designation", Toast.LENGTH_SHORT).show();
            isValidated = false;
        }

        return isValidated;

    }
}
