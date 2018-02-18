package com.panagiotiswarpro.panos.businessplan;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EquipmentTab2 extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public EquipmentTab2() {
        // Required empty public constructor
    }

    public static EquipmentTab2 newInstance(String param1, String param2) {
        EquipmentTab2 fragment = new EquipmentTab2();
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

    EditText equipmentNameEditText,equipmentPriceEditText;
    Button equipmentAddButton;

    private String nameOfItemEquipment;
    private int priceOfItemEquipment;
    private String priceTempEquipment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_equipment_tab2, container, false);

        equipmentAddButton = (Button) view.findViewById(R.id.equipmentAddButton);
        equipmentNameEditText = (EditText) view.findViewById(R.id.equipmentNameEditText);
        equipmentPriceEditText = (EditText) view.findViewById(R.id.equipmentPriceEditText);


        equipmentAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                priceTempEquipment=equipmentPriceEditText.getText().toString();
                if(!equipmentNameEditText.getText().toString().isEmpty() && !priceTempEquipment.isEmpty()){
                    nameOfItemEquipment = equipmentNameEditText.getText().toString();
                    priceOfItemEquipment = Integer.parseInt(priceTempEquipment);

                    EquipmentTab3.listaEquipment.add("\n" +nameOfItemEquipment + "\n" + priceOfItemEquipment + "\n");
                    EquipmentTab3.priceOfMonth += priceOfItemEquipment;

                    equipmentNameEditText.setText("");
                    equipmentPriceEditText.setText("");

                }else{
                    Toast.makeText(getActivity(),"Παρακαλώ συμπληρώστε τα κενά..",Toast.LENGTH_LONG).show();
                }
            }
        });


        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
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
