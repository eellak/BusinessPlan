package com.panagiotiswarpro.panos.businessplan;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class proionta_tab2 extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public proionta_tab2() {
        // Required empty public constructor
    }

    public static proionta_tab2 newInstance(String param1, String param2) {
        proionta_tab2 fragment = new proionta_tab2();
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

    TextView analusiEsoda,eksodaTextView;
    ListView esodaListView;
    static ArrayList<String> listaEsoda = new ArrayList<>();
    ArrayAdapter<String> adapterOfEsoda;
    static Integer sunolikaEsoda=0;

    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (visible && isResumed()) {
            adapterOfEsoda.notifyDataSetChanged();
            analusiEsoda.setText(Integer.toString(sunolikaEsoda));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_proionta_tab2, container, false);

        analusiEsoda = (TextView) view.findViewById(R.id.AnalusiEsoda);
        eksodaTextView = (TextView) view.findViewById(R.id.EksodaTextView);
        esodaListView = (ListView) view.findViewById(R.id.EsodaListView);

        adapterOfEsoda = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,listaEsoda);
        esodaListView.setAdapter(adapterOfEsoda);

        analusiEsoda.setText(Integer.toString(sunolikaEsoda));
        adapterOfEsoda.notifyDataSetChanged();

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
