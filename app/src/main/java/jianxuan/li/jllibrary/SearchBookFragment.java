package jianxuan.li.jllibrary;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import jianxuan.li.jllibrary.data.Book;
import jianxuan.li.jllibrary.data.StockModel;

public class SearchBookFragment extends Fragment {
    View v;

    TextView txtBookId, txtTitle, txtPublisher, txtISBN, txtPrice, txtQtyStock;
    Button btnCancel, btnSearch;
    EditText edtBookId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_search_book, container, false);

        txtBookId = v.findViewById(R.id.txtBookId);
        txtTitle = v.findViewById(R.id.txtBookTitle);
        txtPublisher = v.findViewById(R.id.txtPublisher);
        txtISBN = v.findViewById(R.id.txtISBN);
        txtPrice = v.findViewById(R.id.txtPrice);
        txtQtyStock = v.findViewById(R.id.txtQtyStock);

        btnCancel = v.findViewById(R.id.btnCancel);
        btnSearch = v.findViewById(R.id.btnSearch);

        edtBookId = v.findViewById(R.id.edtBookId);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(SearchBookFragment.this).commit();
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Searching...", Toast.LENGTH_SHORT).show();
                String bookId = edtBookId.getText().toString();
                int bookIdInt = Integer.parseInt(bookId);
                Book book = new StockModel(getContext()).getBook(bookIdInt);

                if (book == null) {
                    Toast.makeText(getContext(), "Book not found", Toast.LENGTH_SHORT).show();
                    return;
                }

                txtBookId.setText("Book Id: " + String.valueOf(book.getId()));
                txtTitle.setText("Book title: " + book.getBookTitle());
                txtPublisher.setText("Publisher: " + book.getPublisher());
                txtISBN.setText("ISBN: " + book.getIsbn());
                txtPrice.setText("Price: $" + String.valueOf(book.getPrice()));
                txtQtyStock.setText("Stack: " + String.valueOf(book.getQtyStock()));
            }
        });

        return v;
    }
}