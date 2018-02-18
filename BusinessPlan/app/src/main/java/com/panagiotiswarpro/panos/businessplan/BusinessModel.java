package com.panagiotiswarpro.panos.businessplan;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class BusinessModel extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_model);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        //removeFocus from editTexts
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_business_model, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_save) {
            EditText ToposEgkastasisEditText = (EditText) findViewById(R.id.ToposEgkastasis);
            String ToposEgkastasis = ToposEgkastasisEditText.getText().toString();

            EditText DateEgkatastashEditText = (EditText) findViewById(R.id.DateEgkatastash);
            String DateEgkatastash = DateEgkatastashEditText.getText().toString();

            Spinner MorfiSpinner = (Spinner) findViewById(R.id.MorfiSpinner);
            int spinner = MorfiSpinner.getSelectedItemPosition();

            CheckBox Paragwgikh = (CheckBox) findViewById(R.id.ParagogikoCheckBox);
            boolean paragwgikh = Paragwgikh.isChecked();

            CheckBox Emporikh = (CheckBox) findViewById(R.id.EmporikoCheckBox);
            boolean emporikh = Emporikh.isChecked();

            CheckBox Paroxh = (CheckBox) findViewById(R.id.ParoxhCheckBox);
            boolean paroxh = Paroxh.isChecked();

            CheckBox Franchise = (CheckBox) findViewById(R.id.franchiseCheckBox);
            boolean franchise = Franchise.isChecked();

            CheckBox Distrubutor = (CheckBox) findViewById(R.id.distributorCheckBox);
            boolean distrubutor = Distrubutor.isChecked();

            CheckBox AlloTypos = (CheckBox) findViewById(R.id.AlloCheckBox);
            boolean alloTypos = AlloTypos.isChecked();

            EditText PerigrafiAlloEditText = (EditText) findViewById(R.id.PerigrafiAllo);
            String PerigrafiAllo = PerigrafiAlloEditText.getText().toString();

            CheckBox Idiotes = (CheckBox) findViewById(R.id.IdiotesCheckBox);
            boolean idiotes = Idiotes.isChecked();

            CheckBox Epixeirhseis = (CheckBox) findViewById(R.id.EpixeirisisCheckBox);
            boolean epixeirhseis = Epixeirhseis.isChecked();

            CheckBox AlloPelates = (CheckBox) findViewById(R.id.AlloiPelatesCheckBox);
            boolean alloPelates = AlloPelates.isChecked();

            EditText PerigrafiAlloPelatesEditText = (EditText) findViewById(R.id.PerigrafiAlloPelates);
            String PerigrafiAlloPelates = PerigrafiAlloPelatesEditText.getText().toString();

            EditText perigrafhEditText = (EditText) findViewById(R.id.perigrafh);
            String perigrafh = perigrafhEditText.getText().toString();

            if (ToposEgkastasis.matches("") || DateEgkatastash.matches("") || spinner==0 ||
                    (!paragwgikh & !emporikh & !paroxh & !franchise & !distrubutor & !alloTypos) ||
                    (!idiotes & !epixeirhseis & !alloPelates) || perigrafh.matches("") ) {
                Toast.makeText(this, "Συμπληρώστε όλα τα πεδία", Toast.LENGTH_SHORT).show();
            }
            else
            {
                SharedPreferences sharedPref = getSharedPreferences("businessModel", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean("businessModelDone", true);
                editor.putString("ToposEgkastasis", ToposEgkastasis);
                editor.putString("DateEgkatastash", DateEgkatastash);
                editor.putInt("morfh", spinner);
                editor.putBoolean("paragwgikh", paragwgikh);
                editor.putBoolean("emporikh", emporikh);
                editor.putBoolean("paroxh", paroxh);
                editor.putBoolean("franchise", franchise);
                editor.putBoolean("distrubutor", distrubutor);
                editor.putBoolean("alloTypos", alloTypos);
                editor.putString("PerigrafiAllo", PerigrafiAllo);
                editor.putBoolean("idiotes", idiotes);
                editor.putBoolean("epixeirhseis", epixeirhseis);
                editor.putBoolean("alloPelates", alloPelates);
                editor.putString("PerigrafiAlloPelates", PerigrafiAlloPelates);
                editor.putString("perigrafh", perigrafh);

                editor.apply();
                finish();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_business_model_main, container, false);

            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position){
                case 0:
                    fragment= new BusinessModelTab1();
                    break;
                case 1:
                    fragment= new BusinessModelTab2();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}