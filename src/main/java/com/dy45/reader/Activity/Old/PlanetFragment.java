package com.dy45.reader.Activity.Old;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dy45.reader.File.FileUtil;
import com.dy45.reader.Util.ToastUtil;
import com.dy45.articlereader.R;

import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * Created by dy45 on 4/22/2015.
 */
/**
 * Fragment that appears in the "content_frame", shows a planet
 */
public class PlanetFragment extends Fragment {
    public static final String ARG_PLANET_NUMBER = "planet_number";

    public PlanetFragment() {
        // Empty constructor required for fragment subclasses
    }

    public static Fragment newInstance(int position) {
        Fragment fragment = new PlanetFragment();
        Bundle args = new Bundle();
        args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_planet, container, false);
//        int i = getArguments().getInt(ARG_PLANET_NUMBER);
//        TextView iv = ((TextView) rootView.findViewById(R.id.image));
//        iv.setText(String.valueOf(i));

        Button fileText = (Button) rootView.findViewById(R.id.FileTest);
        fileText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                write(String.format("%1s at %2s","Hello File at ", String.valueOf(System.currentTimeMillis())));
                FileUtil.write("LoginIn","Hello world",true);
            }
        });

        return rootView;
    }

    private void write(String fileContent){
        try {
            FileOutputStream fileOutputStream=getActivity().openFileOutput("LoginIn", Context.MODE_APPEND);
            PrintStream printStream = new PrintStream(fileOutputStream);
            printStream.println(fileContent);
            printStream.close();
        }
        catch (Exception ex){
            ToastUtil.show(getActivity(),"Write failed.");
        }
    }

}