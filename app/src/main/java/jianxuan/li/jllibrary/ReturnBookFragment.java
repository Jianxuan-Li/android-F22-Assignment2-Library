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
import android.widget.Toast;

import java.util.Calendar;

import jianxuan.li.jllibrary.data.Book;
import jianxuan.li.jllibrary.data.Issue;
import jianxuan.li.jllibrary.data.IssueModel;
import jianxuan.li.jllibrary.data.Return;
import jianxuan.li.jllibrary.data.ReturnModel;
import jianxuan.li.jllibrary.data.StockModel;

public class ReturnBookFragment extends Fragment {

    View v;
    EditText edtIssueId, edtBookId, edtQtyReturned, edtDateOfReturn;
    Button btnSubmit, btnCancel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_return_book, container, false);

        // Initialize the weights
        edtIssueId = v.findViewById(R.id.edtIssueId);
        edtBookId = v.findViewById(R.id.edtBookId);
        edtQtyReturned = v.findViewById(R.id.edtQtyReturned);
        edtDateOfReturn = v.findViewById(R.id.edtDateOfReturn);
        btnSubmit = v.findViewById(R.id.btnSubmit);
        btnCancel = v.findViewById(R.id.btnCancel);

        // the date input
        edtDateOfReturn.setInputType(InputType.TYPE_NULL);
        edtDateOfReturn.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), (view, year1, month1, dayOfMonth) -> {
                String date = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
                edtDateOfReturn.setText(date);
            }, year, month, day);

            datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

            datePickerDialog.show();
        });

        // click listener for the cancel button
        btnCancel.setOnClickListener(v -> getActivity().getSupportFragmentManager().beginTransaction().remove(ReturnBookFragment.this).commit());

        // click listener for the submit button
        btnSubmit.setOnClickListener(v -> {
            String issueId = edtIssueId.getText().toString();
            String bookId = edtBookId.getText().toString();
            String qtyReturned = edtQtyReturned.getText().toString();
            String dateOfReturn = edtDateOfReturn.getText().toString();

            // check if the fields are empty
            if (issueId.isEmpty() || bookId.isEmpty() || qtyReturned.isEmpty() || dateOfReturn.isEmpty()) {
                edtIssueId.setError("Please enter the issue id");
                edtBookId.setError("Please enter the book id");
                edtQtyReturned.setError("Please enter the quantity returned");
                edtDateOfReturn.setError("Please enter the date of return");
                return;
            }

            // check the issue id and book id is integer
            int issueIdInt, bookIdInt, qtyReturnedInt;
            try {
                issueIdInt = Integer.parseInt(issueId);
                bookIdInt = Integer.parseInt(bookId);
                qtyReturnedInt = Integer.parseInt(qtyReturned);
            } catch (NumberFormatException e) {
                edtIssueId.setError("Please enter the issue id");
                edtBookId.setError("Please enter the book id");
                return;
            }

            // check the quantity returned is positive
            if (qtyReturnedInt <= 0) {
                edtQtyReturned.setError("Please enter the quantity returned");
                return;
            }

            // check existence of the issue id and book id
            IssueModel issueModel = new IssueModel(getContext());
            Issue issue = issueModel.getIssue(issueIdInt);
            if (issue == null) {
                edtIssueId.setError("This issue id does not exist");
                return;
            }

            StockModel stockModel = new StockModel(getContext());
            Book book = stockModel.getBook(bookIdInt);
            if (book == null) {
                edtBookId.setError("This book id does not exist");
                return;
            }

            // check the quantity returned is less than the quantity issued
            if (qtyReturnedInt > issue.getQtyIssued()) {
                edtQtyReturned.setError("The quantity returned cannot be greater than the quantity issued");
                return;
            }

            // return the book
            ReturnModel returnModel = new ReturnModel(getContext());
            Return ret = new Return();
            ret.setIssueId(issueIdInt);
            ret.setBookId(bookIdInt);
            ret.setQtyReturned(qtyReturnedInt);
            ret.setDateOfReturn(dateOfReturn);
            boolean result = returnModel.addReturn(ret);

            if (!result) {
                Toast.makeText(getContext(), "Failed to return the book", Toast.LENGTH_LONG).show();
                return;
            }

            // update the quantity issued
            issue.setQtyIssued(issue.getQtyIssued() - qtyReturnedInt);
            boolean updated = issueModel.updateIssue(issue);

            if (!updated) {
                Toast.makeText(getContext(), "Failed to update the quantity issued", Toast.LENGTH_LONG).show();
                return;
            }

            // update the quantity in stock
            boolean changedQty = stockModel.changeQtyInStockByBookId(bookIdInt, book.getQtyStock() + qtyReturnedInt);

            if (!changedQty) {
                Toast.makeText(getContext(), "Failed to update the quantity in stock", Toast.LENGTH_LONG).show();
                return;
            }

            // clear the fields
            edtIssueId.setText("");
            edtBookId.setText("");
            edtQtyReturned.setText("");
            edtDateOfReturn.setText("");

            Toast.makeText(getContext(), "Successfully returned the book", Toast.LENGTH_LONG).show();
        });

        return v;
    }
}