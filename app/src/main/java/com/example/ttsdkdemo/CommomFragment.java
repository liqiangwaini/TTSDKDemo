package com.example.ttsdkdemo;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

/**
 * Created by lqsir on 2016/4/14.
 */
public class CommomFragment extends DialogFragment {
    ListView listView;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_login_dialog, null);
        listView = (ListView) view.findViewById(R.id.item_fm_list);
        builder.setView(view).setPositiveButton("OK", null);
        return builder.create();
    }
}
