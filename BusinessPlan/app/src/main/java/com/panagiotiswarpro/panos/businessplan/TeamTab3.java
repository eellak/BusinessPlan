package com.panagiotiswarpro.panos.businessplan;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TeamTab3 extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TeamTab3() {
        // Required empty public constructor
    }

    public static TeamTab3 newInstance(String param1, String param2) {
        TeamTab3 fragment = new TeamTab3();
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

    EditText sunergatisOnoma, eidikotitaSunergatis, logosSunergati, kostosSunergati;
    Button addSunergati;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_team_tab3, container, false);

        sunergatisOnoma = (EditText) view.findViewById(R.id.SunergatisOnoma);
        eidikotitaSunergatis = (EditText) view.findViewById(R.id.EidikotitaSunergatis);
        logosSunergati = (EditText) view.findViewById(R.id.LogosSunergati);
        kostosSunergati = (EditText) view.findViewById(R.id.KostosSunergati);
        addSunergati = (Button) view.findViewById(R.id.AddSunergati);

        addSunergati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!sunergatisOnoma.getText().toString().isEmpty() && !eidikotitaSunergatis.getText().toString().isEmpty() && !kostosSunergati.getText().toString().isEmpty() && !logosSunergati.getText().toString().isEmpty()){

                    TeamTab4.ergatesKaiKostos.add("\n" + sunergatisOnoma.getText().toString() + "\n" + eidikotitaSunergatis.getText().toString() + "\n" + logosSunergati.getText().toString() + "\n" + kostosSunergati.getText().toString() + "\n");
                    TeamTab4.kostos += (Integer.parseInt(kostosSunergati.getText().toString()));

                    sunergatisOnoma.setText("");
                    logosSunergati.setText("");
                    kostosSunergati.setText("");
                    eidikotitaSunergatis.setText("");

                }else {
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
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onStart() {
        super.onStart();
        try {
            mListener = (OnFragmentInteractionListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement OnFragmentInteractionListener");
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
