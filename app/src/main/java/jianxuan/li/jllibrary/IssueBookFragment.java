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
import jianxuan.li.jllibrary.data.StockModel;

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

            // check fields are not empty
            if (bookId.isEmpty() || qtyToBeIssued.isEmpty() || customerName.isEmpty() || customerEmail.isEmpty() || dateOfIssue.isEmpty()){
                Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            int bookIdInt;
            int qtyToBeIssuedInt;

            // check the book id and qty are integers
            try {
                bookIdInt = Integer.parseInt(bookId);
                qtyToBeIssuedInt = Integer.parseInt(qtyToBeIssued);
            } catch (NumberFormatException e) {
                Toast.makeText(getContext(), "Book ID must be an integer", Toast.LENGTH_SHORT).show();
                return;
            }

            // check book id
            StockModel stockModel = new StockModel(getContext());
            Book book = stockModel.getBook(bookIdInt);
            if (book == null){
                edtBookId.setError("Book ID not found");
                return;
            }

            // check qty to be issued
            if (Integer.parseInt(qtyToBeIssued) > book.getQtyStock() || Integer.parseInt(qtyToBeIssued) <= 0){
                edtQtyToBeIssued.setError("Qty to be issued is more than qty in stock");
                // show the available qty in stock by Toast
                Toast.makeText(getContext(), "Available qty in stock: " + String.valueOf(book.getQtyStock()), Toast.LENGTH_SHORT).show();
                return;
            }

            // check email by regex
            if (!customerEmail.matches("^[A-Za-z0-9+_.-]+@(.+)$")){
                edtCustomerEmail.setError("Invalid email");
                return;
            }

            // create an issue object
            Issue issue = new Issue();
            issue.setBookId(bookIdInt);
            issue.setCustomerName(customerName);
            issue.setCustomerEmail(customerEmail);
            issue.setQtyIssued(qtyToBeIssuedInt);
            issue.setDateOfIssue(dateOfIssue);

            // add issue to database
            IssueModel issueModel = new IssueModel(getContext());
            boolean result = issueModel.addIssue(issue);
            if (!result){
                Toast.makeText(getContext(), "Issue failed", Toast.LENGTH_LONG).show();
                return;
            }

            // update qty in the stock by its id
            stockModel.changeQtyInStockByBookId(bookIdInt, book.getQtyStock() - qtyToBeIssuedInt);
            // clear fields
            edtBookId.setText("");
            edtCustomerName.setText("");
            edtCustomerEmail.setText("");
            edtQtyToBeIssued.setText("");
            edtDateOfIssue.setText("");
            Toast.makeText(getContext(), "Issue added successfully, " + String.valueOf(book.getQtyStock() - qtyToBeIssuedInt) + "remaining.", Toast.LENGTH_LONG).show();
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

            datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

            datePickerDialog.show();
        });

        return v;
    }
}