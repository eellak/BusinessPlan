package com.panagiotiswarpro.panos.businessplan;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;


public class BusinessModelTab1 extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public BusinessModelTab1() {
    }

    public static BusinessModelTab1 newInstance(String param1, String param2) {
        BusinessModelTab1 fragment = new BusinessModelTab1();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_business_model_tab1, container, false);

        //Load saved settings
        SharedPreferences sharedPref = this.getActivity().getSharedPreferences("businessModel", Context.MODE_PRIVATE);
        if(sharedPref.getBoolean("businessModelDone", false))
        {
            EditText ToposEgkastasisEditText = (EditText) view.findViewById(R.id.ToposEgkastasis);
            ToposEgkastasisEditText.setText(sharedPref.getString("ToposEgkastasis",""));

            EditText DateEgkatastashEditText = (EditText) view.findViewById(R.id.DateEgkatastash);
            DateEgkatastashEditText.setText(sharedPref.getString("DateEgkatastash",""));

            Spinner MorfiSpinner = (Spinner) view.findViewById(R.id.MorfiSpinner);
            MorfiSpinner.setSelection(sharedPref.getInt("morfh", 0));

            CheckBox Paragwgikh = (CheckBox) view.findViewById(R.id.ParagogikoCheckBox);
            Paragwgikh.setChecked(sharedPref.getBoolean("paragwgikh", false));

            CheckBox Emporikh = (CheckBox) view.findViewById(R.id.EmporikoCheckBox);
            Emporikh.setChecked(sharedPref.getBoolean("emporikh", false));

            CheckBox Paroxh = (CheckBox) view.findViewById(R.id.ParoxhCheckBox);
            Paroxh.setChecked(sharedPref.getBoolean("paroxh", false));

            CheckBox Franchise = (CheckBox) view.findViewById(R.id.franchiseCheckBox);
            Franchise.setChecked(sharedPref.getBoolean("franchise", false));

            CheckBox Distrubutor = (CheckBox) view.findViewById(R.id.distributorCheckBox);
            Distrubutor.setChecked(sharedPref.getBoolean("distrubutor", false));

            CheckBox AlloTypos = (CheckBox) view.findViewById(R.id.AlloCheckBox);
            AlloTypos.setChecked(sharedPref.getBoolean("alloTypos", false));

            EditText PerigrafiAlloEditText = (EditText) view.findViewById(R.id.PerigrafiAllo);
            PerigrafiAlloEditText.setText(sharedPref.getString("PerigrafiAllo",""));

            CheckBox Idiotes = (CheckBox) view.findViewById(R.id.IdiotesCheckBox);
            Idiotes.setChecked(sharedPref.getBoolean("idiotes", false));

            CheckBox Epixeirhseis = (CheckBox) view.findViewById(R.id.EpixeirisisCheckBox);
            Epixeirhseis.setChecked(sharedPref.getBoolean("epixeirhseis", false));

            CheckBox AlloPelates = (CheckBox) view.findViewById(R.id.AlloiPelatesCheckBox);
            AlloPelates.setChecked(sharedPref.getBoolean("alloPelates", false));

            EditText PerigrafiAlloPelatesEditText = (EditText) view.findViewById(R.id.PerigrafiAlloPelates);
            PerigrafiAlloPelatesEditText.setText(sharedPref.getString("PerigrafiAlloPelates",""));
        }

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
