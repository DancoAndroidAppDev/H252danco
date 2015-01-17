package com.example.danco.homework2.h252danco.fragment;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.danco.homework2.h252danco.DummyContent;
import com.example.danco.homework2.h252danco.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link com.example.danco.homework2.h252danco.fragment.ContactDetailFragment.DynamicFragmentListener} interface
 * to handle interaction events.
 * Use the {@link ContactDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactDetailFragment extends Fragment
        implements View.OnClickListener, DatePickerFragment.DatePickerFragmentListener {
    public static final String DATE_PICKER_TAG = "date_picker";

    public static final String CONTACT = "contact";

    private String contactId;
    private String contactName;
    private String contactStreetAddress;
    private String city, state, zip;
    private int birthYear;
    private int birthMonth;
    private int birthDayOfMonth;

    private DummyContent.DummyItem item;

    private final NumberFormat dayMonthFormat = new DecimalFormat("00");


    public ContactDetailFragment() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param item item for which to display details.
     * @return A new instance of fragment DynamicFragment.
     */
    public static ContactDetailFragment newInstance(DummyContent.DummyItem item) {
        ContactDetailFragment fragment = new ContactDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(CONTACT, item);
        fragment.setArguments(args);
        fragment.item = item;
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            if (getArguments() != null) {
                item = getArguments().getParcelable(CONTACT);
            }
        } else {
            item = savedInstanceState.getParcelable(CONTACT);
        }

        contactName = item.name;
        contactStreetAddress = item.streetAddress;
        city= item.city;
        state = item.state;
        zip = item.zip;
        birthYear = item.birthYear;
        birthMonth = item.birthMonth;
        birthDayOfMonth = item.birthDayOfMonth;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_detail, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewHolder holder = new ViewHolder(view);
        view.setTag(holder);

        holder.contactName.setText(contactName);
        holder.contactName.setTypeface(Typeface.DEFAULT_BOLD);
        holder.contactName.setOnClickListener(this);
        holder.streetAddress.setText(
                String.format("%s\n%s, %s %s", contactStreetAddress, city, state, zip));
        holder.dob.setText(String.format("%s/%s/%s",
                dayMonthFormat.format(birthYear),
                dayMonthFormat.format(birthMonth),
                dayMonthFormat.format(birthDayOfMonth)));
        holder.dob.setOnClickListener(this);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable(CONTACT, item);
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.contactDob:
                DatePickerFragment fragment = DatePickerFragment.newInstance(2015, 1, 1);
                fragment.show(getChildFragmentManager(), DATE_PICKER_TAG);
                break;
        }
    }


    @Override
    public void onOkSelected(int year, int month, int dayOfMonth) {
        this.birthYear = year;
        this.birthMonth = month;
        this.birthDayOfMonth = dayOfMonth;
        item.birthYear = year;
        item.birthMonth = month;
        item.birthDayOfMonth = dayOfMonth;

        ViewHolder holder = getViewHolder();
        if (holder != null) {
            holder.dob.setText(String.format("%s/%s/%s",
                    dayMonthFormat.format(birthYear),
                    dayMonthFormat.format(birthMonth),
                    dayMonthFormat.format(birthDayOfMonth)));
        }
    }


    private ViewHolder getViewHolder() {
        View view = getView();
        // getView will return null after onDestroyView
        return view != null ? (ViewHolder) view.getTag() : null;
    }


    @Override
    public void onCancelSelected() {

    }


    static class ViewHolder {
        final TextView contactName;
        final EditText streetAddress;
        final TextView dob;


        ViewHolder(View view) {
            contactName = (TextView) view.findViewById(R.id.contact_name);
            streetAddress = (EditText) view.findViewById(R.id.contact_street_address);
            dob = (TextView) view.findViewById(R.id.contactDob);
        }
    }
}
