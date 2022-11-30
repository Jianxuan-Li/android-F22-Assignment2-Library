package jianxuan.li.jllibrary;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

public class IssueBookFragment extends Fragment {
    View v;

    EditText edtBookId, edtCustomerName, edtCustomerEmail, edtQtyToBeIssued, edtDateOfIssue;
    Button btnSubmit, btnCancel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_issue_book, container, false);

        edtBookId = v.findViewById(R.id.edtBookId);
        edtCustomerName = v.findViewById(R.id.edtCustomerName);
        edtCustomerEmail = v.findViewById(R.id.edtCustomerEmail);
        edtQtyToBeIssued = v.findViewById(R.id.edtQtyToBeIssued);
        edtDateOfIssue = v.findViewById(R.id.edtDateOfIssue);
        btnSubmit = v.findViewById(R.id.btnSubmit);
        btnCancel = v.findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(v -> getActivity().getSupportFragmentManager().beginTransaction().remove(IssueBookFragment.this).commit());

        btnSubmit.setOnClickListener(v -> {
            String bookId = edtBookId.getText().toString();
            String customerName = edtCustomerName.getText().toString();
            String customerEmail = edtCustomerEmail.getText().toString();
            String qtyToBeIssued = edtQtyToBeIssued.getText().toString();
            String dateOfIssue = edtDateOfIssue.getText().toString();


        });

        edtDateOfIssue.setInputType(InputType.TYPE_NULL);
        edtDateOfIssue.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), (view, year1, month1, dayOfMonth) -> {
                String date = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
                edtDateOfIssue.setText(date);
            }, year, month, day);

            datePickerDialog.show();
        });

        return v;
    }
}