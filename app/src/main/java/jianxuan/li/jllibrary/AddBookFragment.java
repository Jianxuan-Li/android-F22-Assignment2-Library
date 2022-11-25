package jianxuan.li.jllibrary;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class AddBookFragment extends Fragment {

    View v;

    /*
            "isbn TEXT",
            "bookTitle TEXT",
            "publisher TEXT",
            "qtyStock INTEGER",
            "price REAL"
     */

    EditText etbookTitle, etpublisher, etisbn, etqtyStock, etprice;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_add_book, container, false);

        etbookTitle = v.findViewById(R.id.etbookTitle);
        etpublisher = v.findViewById(R.id.etPublisher);
        etisbn = v.findViewById(R.id.etIsbn);
        etqtyStock = v.findViewById(R.id.etQtyStock);
        etprice = v.findViewById(R.id.etPrice);

        return v;
    }
}