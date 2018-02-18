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

public class EquipmentTab3 extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public EquipmentTab3() {
        // Required empty public constructor
    }

    public static EquipmentTab3 newInstance(String param1, String param2) {
        EquipmentTab3 fragment = new EquipmentTab3();
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

    TextView monthlyCharges,monthlyChargesPrice;
    static Integer priceOfMonth=0;
    ListView montlyEquipment;
    static ArrayList<String> listaEquipment = new ArrayList<>();
    ArrayAdapter<String> adapterOfList;


    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (visible && isResumed()) {
            adapterOfList.notifyDataSetChanged();
            monthlyChargesPrice.setText(Integer.toString(priceOfMonth));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_equipment_tab3,container,false);

        monthlyCharges = (TextView) view.findViewById(R.id.monthlyCharges);
        monthlyChargesPrice = (TextView) view.findViewById(R.id.monthlyChargesPrice);
        montlyEquipment = (ListView) view.findViewById(R.id.MonthlyEquipment);

        adapterOfList = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,listaEquipment);
        montlyEquipment.setAdapter(adapterOfList);

        monthlyChargesPrice.setText(Integer.toString(priceOfMonth));
        adapterOfList.notifyDataSetChanged();

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
