package com.panagiotiswarpro.panos.businessplan;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class Team extends AppCompatActivity
        implements TeamTab1.OnFragmentInteractionListener, TeamTab2.OnFragmentInteractionListener,
        TeamTab3.OnFragmentInteractionListener, TeamTab4.OnFragmentInteractionListener {

    @Override
    public void onFragmentInteraction(Uri uri){
        //you can leave it empty
    }

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_team, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save) {

            if(TeamTab1.doihkites.isEmpty() || TeamTab4.ergatesKaiKostos.isEmpty()){
                Toast.makeText(this,"Παρακαλώ συμπληρώστε τους Διοικητές και τουλάχιστον ένα από τα πεδία 'Προσωπικό ή Συνεργάτες'",Toast.LENGTH_LONG).show();
            }else{

                StringBuilder stringBuilderDioikhtes = new StringBuilder();
                StringBuilder stringBuilderErgates = new StringBuilder();

                SharedPreferences sharedPreferences = getSharedPreferences("TeamTabs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("TeamTabsDone",true);

                for(String i : TeamTab1.doihkites){
                    stringBuilderDioikhtes.append(i);
                    stringBuilderDioikhtes.append("\n");
                }

                for(String i : TeamTab4.ergatesKaiKostos){
                    stringBuilderErgates.append(i);
                    stringBuilderErgates.append("\n");
                }

                editor.putString("Dioikhtes",stringBuilderDioikhtes.toString());
                editor.putString("Ergates",stringBuilderErgates.toString());
                editor.putInt("Kostos",TeamTab4.kostos);
                editor.apply();

                finish();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
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
            View rootView = inflater.inflate(R.layout.fragment_team, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
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
                    fragment= new TeamTab1();
                    break;
                case 1:
                    fragment= new TeamTab2();
                    break;
                case 2:
                    fragment= new TeamTab3();
                    break;
                case 3:
                    fragment= new TeamTab4();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }
    }
}
