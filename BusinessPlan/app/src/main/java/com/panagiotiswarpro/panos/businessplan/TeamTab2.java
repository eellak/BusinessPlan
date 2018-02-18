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

public class TeamTab2 extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TeamTab2() {
        // Required empty public constructor
    }

    public static TeamTab2 newInstance(String param1, String param2) {
        TeamTab2 fragment = new TeamTab2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setUserVisibleHint(false);
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    EditText ergatisOnomatoeponumo, thesiErgati, misthosErgati, urlErgati;
    Button addErgati;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_team_tab2, container, false);

        thesiErgati = (EditText) view.findViewById(R.id.ThesiErgati);
        ergatisOnomatoeponumo = (EditText) view.findViewById(R.id.ErgatisOnomatoeponumo);
        misthosErgati = (EditText) view.findViewById(R.id.MisthosErgath);
        urlErgati = (EditText) view.findViewById(R.id.URLergati);

        addErgati = (Button) view.findViewById(R.id.AddErgati);

        addErgati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ergatisOnomatoeponumo.getText().toString().isEmpty() && !thesiErgati.getText().toString().isEmpty() && !urlErgati.getText().toString().isEmpty() && !misthosErgati.getText().toString().isEmpty()) {

                    TeamTab4.ergatesKaiKostos.add("\n" + ergatisOnomatoeponumo.getText().toString() + "\n" + thesiErgati.getText().toString() + "\n" + misthosErgati.getText().toString() + "\n" + urlErgati.getText().toString() + "\n");
                    TeamTab4.kostos += (Integer.parseInt(misthosErgati.getText().toString()));

                    ergatisOnomatoeponumo.setText("");
                    thesiErgati.setText("");
                    misthosErgati.setText("");
                    urlErgati.setText("");


                }else {
                    Toast.makeText(getActivity(),"Παρακαλώ συμπληρώστε τα κενά..",Toast.LENGTH_LONG).show();
                }
        }});
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
