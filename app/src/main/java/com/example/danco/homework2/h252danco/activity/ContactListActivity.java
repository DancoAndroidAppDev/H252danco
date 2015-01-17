package com.example.danco.homework2.h252danco.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.danco.homework2.h252danco.DummyContent;
import com.example.danco.homework2.h252danco.R;
import com.example.danco.homework2.h252danco.fragment.ContactListFragment;


public class ContactListActivity extends ActionBarActivity
        implements ContactListFragment.ItemFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        getSupportActionBar().setTitle(R.string.app_name);

        if (savedInstanceState == null) {
            ContactListFragment fragment = (ContactListFragment)
                    getSupportFragmentManager().findFragmentById(R.id.main_fragment);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onUpdateDynamicFragment(DummyContent.DummyItem item) {
        startActivity(ContactDetailActivity.buildIntent(this, item));
    }
}
