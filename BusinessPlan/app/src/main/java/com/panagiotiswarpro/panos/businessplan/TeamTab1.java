package com.panagiotiswarpro.panos.businessplan;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class TeamTab1 extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TeamTab1() {
        // Required empty public constructor
    }

    public static TeamTab1 newInstance(String param1, String param2) {
        TeamTab1 fragment = new TeamTab1();
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

    EditText onomaEponumoDoihkisi,thesiEpixeirisi,urlDoihkisi;
    Button addDoihkisi;
    static ArrayList<String> doihkites = new ArrayList<>();
    ArrayAdapter<String> adapterOfDoihkites;
    ListView listDoihkites;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_team_tab1,container,false);

        onomaEponumoDoihkisi = (EditText) view.findViewById(R.id.OnomaEponumoDoihkisi);
        thesiEpixeirisi = (EditText) view.findViewById(R.id.ThesiEpixeirisi);
        urlDoihkisi = (EditText) view.findViewById(R.id.URLdioikisi);
        listDoihkites = (ListView) view.findViewById(R.id.ListDoihkites);
        addDoihkisi = (Button) view.findViewById(R.id.AddDoihkisi);

        adapterOfDoihkites = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,doihkites);
        listDoihkites.setAdapter(adapterOfDoihkites);

        listDoihkites.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        addDoihkisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!onomaEponumoDoihkisi.getText().toString().isEmpty() && !thesiEpixeirisi.getText().toString().isEmpty() && !urlDoihkisi.getText().toString().isEmpty()){
                    doihkites.add("\n" + onomaEponumoDoihkisi.getText().toString() + "\n" + thesiEpixeirisi.getText().toString() + "\n" + urlDoihkisi.getText().toString() + "\n");
                    adapterOfDoihkites.notifyDataSetChanged();

                    onomaEponumoDoihkisi.setText("");
                    thesiEpixeirisi.setText("");
                    urlDoihkisi.setText("");
                }else{
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
