package com.dy45.reader.Activity.Longhu;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.widget.DatePicker;

import com.dy45.reader.Util.DateUtil;
import com.dy45.reader.Util.OnActionListener1;

import java.util.Calendar;
import java.util.Date;


public class DatePickFragment extends DialogFragment {

    private static final String KEY_DATE="Date";
    private static final String KEY_TITLE="Title";


    private OnActionListener1<Date> dateSetListener;

    public static DatePickFragment newInstance(Date date, String title ,OnActionListener1<Date> listener1) {
        DatePickFragment fragment = new DatePickFragment();
        Bundle args = new Bundle();
        args.putLong(KEY_DATE, date.getTime());
        args.putString(KEY_TITLE,title);
        fragment.setArguments(args);
        fragment.dateSetListener = listener1;
        return fragment;
    }

    public static void show(Fragment parent,Date date,String title,OnActionListener1<Date> listener1){
        DatePickFragment fragment = DatePickFragment.newInstance(date,title,listener1);
        fragment.setTargetFragment(parent,0);
        fragment.show(parent.getActivity().getSupportFragmentManager(),"day");
    }



    public DatePickFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        c.setTimeInMillis(getArguments().getLong(KEY_DATE));
        int year = c.get(Calendar.YEAR);
        final int month = c.get(Calendar.MONTH);
        final int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                if(dateSetListener != null) {
                    dateSetListener.onAction(DateUtil.parseDate(year,monthOfYear,dayOfMonth));
                }
            }
        }, year,month,day);
        return datePickerDialog;
    }
}
