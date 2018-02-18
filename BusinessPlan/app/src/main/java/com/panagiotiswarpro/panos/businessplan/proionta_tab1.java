package com.panagiotiswarpro.panos.businessplan;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class proionta_tab1 extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public proionta_tab1() {
        // Required empty public constructor
    }

    public static proionta_tab1 newInstance(String param1, String param2) {
        proionta_tab1 fragment = new proionta_tab1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    EditText proionOnoma, timiProion, kostosParagwgis, posoParagwgi;
    CheckBox katanalotesBox, epixeirhseisBox;
    Button addProion;
    String protasi;
    Integer eksodo=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_proionta_tab1, container, false);

        proionOnoma = (EditText) view.findViewById(R.id.ProionOnoma);
        timiProion = (EditText) view.findViewById(R.id.TimiProion);
        kostosParagwgis = (EditText) view.findViewById(R.id.KostosParagwgis);
        posoParagwgi = (EditText) view.findViewById(R.id.PosoParagwgi);
        katanalotesBox = (CheckBox) view.findViewById(R.id.KatanalotesBox);
        epixeirhseisBox = (CheckBox) view.findViewById(R.id.EpixeirhseisBox);
        addProion = (Button) view.findViewById(R.id.AddProion);

        addProion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!proionOnoma.getText().toString().isEmpty() && !timiProion.getText().toString().isEmpty() && !kostosParagwgis.getText().toString().isEmpty() && !posoParagwgi.getText().toString().isEmpty() && (katanalotesBox.isChecked() || epixeirhseisBox.isChecked())){
                    if (Integer.parseInt(kostosParagwgis.getText().toString())>Integer.parseInt(timiProion.getText().toString())) {
                        Toast.makeText(getActivity(),"Παρακαλώ το κόστος παραγωγής να είναι μικρότερο της τιμής του προϊόντος..",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        if (Integer.parseInt(posoParagwgi.getText().toString()) > 0) {
                            protasi = "\n" + proionOnoma.getText().toString() + "\nτιμή: " + timiProion.getText().toString() + "\nΚόστος: " + kostosParagwgis.getText().toString() + "\nΠοσότητα: " + posoParagwgi.getText().toString() + "\nΑναφέρεται σε:\n" ;
                            if (katanalotesBox.isChecked()){
                                protasi += "καταναλωτές\n";
                            }
                            if (epixeirhseisBox.isChecked()){
                                protasi +="επιχειρήσεις\n";
                            }
                            proionta_tab2.listaEsoda.add(protasi);

                            eksodo += Integer.parseInt(timiProion.getText().toString());
                            eksodo -=  Integer.parseInt(kostosParagwgis.getText().toString());
                            eksodo *= Integer.parseInt(posoParagwgi.getText().toString());

                            proionta_tab2.sunolikaEsoda += eksodo;
                            proionOnoma.setText("");
                            timiProion.setText("");
                            kostosParagwgis.setText("");
                            posoParagwgi.setText("");
                            katanalotesBox.setChecked(false);
                            epixeirhseisBox.setChecked(false);
                            eksodo=0;
                            protasi = "";
                        }
                        else
                        {
                            Toast.makeText(getActivity(),"Παρακαλώ το ποσό παραγωγής να είναι Θετικό..",Toast.LENGTH_LONG).show();
                        }
                    }
                }
                else
                {
                    Toast.makeText(getActivity(),"Παρακαλώ συμπληρώστε τα κενά..",Toast.LENGTH_LONG).show();
                }
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
