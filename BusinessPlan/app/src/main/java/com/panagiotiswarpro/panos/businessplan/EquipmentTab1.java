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

public class EquipmentTab1 extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public EquipmentTab1() {
        // Required empty public constructor
    }

    public static EquipmentTab1 newInstance(String param1, String param2) {
        EquipmentTab1 fragment = new EquipmentTab1();
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



    //δήλωση TextView και διαφώρων μεταβλητών..

    EditText facilitiesNameEditText,facilitiesPriceEditText;
    Button facilitiesAddButton;
    private String nameOfItem;
    private int priceOfItem;
    private String priceTemp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_equipment_tab1,container,false);


        facilitiesAddButton = (Button) view.findViewById(R.id.facilitiesAddButton);
        facilitiesNameEditText = (EditText) view.findViewById(R.id.facilitiesNameEditText);
        facilitiesPriceEditText = (EditText) view.findViewById(R.id.facilitiesPriceEditText);


        facilitiesAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                priceTemp=facilitiesPriceEditText.getText().toString();

                if(!facilitiesNameEditText.getText().toString().isEmpty() && !priceTemp.isEmpty()){
                    nameOfItem = facilitiesNameEditText.getText().toString();
                    priceOfItem = Integer.parseInt(priceTemp);

                    //βάζω στην λίστα το όνομα και την τιμή..
                    EquipmentTab3.listaEquipment.add("\n" +nameOfItem + "\n" + priceOfItem + "\n");
                    //ύστερα προσθέτω τις τιμες στην μεταβλητη για να τα εμφανισω στο tab3
                    EquipmentTab3.priceOfMonth += priceOfItem;

                    //μετα κανω κενα τα πεδία..
                    facilitiesNameEditText.setText("");
                    facilitiesPriceEditText.setText("");
                }else{
                    //εμφάνιση μηνύματος αν δεν έχει συμπληρώσει τα κενά..
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
