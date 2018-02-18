package com.panagiotiswarpro.panos.businessplan;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TeamTab4 extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TeamTab4() {
        // Required empty public constructor
    }

    public static TeamTab4 newInstance(String param1, String param2) {
        TeamTab4 fragment = new TeamTab4();
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

    TextView eksodaTextView;
    TextView analusiTextView;
    ListView listViewOloiErgates;
    static ArrayList<String> ergatesKaiKostos = new ArrayList<>();
    ArrayAdapter<String> adapterOfErgates;
    static Integer kostos=0;

    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (visible && isResumed()) {
            adapterOfErgates.notifyDataSetChanged();
            analusiTextView.setText(Integer.toString(kostos));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_team_tab4, container, false);
        eksodaTextView = (TextView) view.findViewById(R.id.EksodaTextView);
        analusiTextView = (TextView) view.findViewById(R.id.EksodaAnalusi);
        listViewOloiErgates = (ListView) view.findViewById(R.id.ListViewOloiErgates);

        adapterOfErgates = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,ergatesKaiKostos);
        listViewOloiErgates.setAdapter(adapterOfErgates);
        adapterOfErgates.notifyDataSetChanged();
        analusiTextView.setText(Integer.toString(kostos));

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
