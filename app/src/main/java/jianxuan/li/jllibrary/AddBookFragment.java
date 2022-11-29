package jianxuan.li.jllibrary;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import jianxuan.li.jllibrary.data.Book;
import jianxuan.li.jllibrary.data.StockModel;
import jianxuan.li.jllibrary.data.Publisher;
import jianxuan.li.jllibrary.data.PublisherModel;

public class AddBookFragment extends Fragment {

    View v;

    /*
            "isbn TEXT",
            "bookTitle TEXT",
            "publisher TEXT",
            "qtyStock INTEGER",
            "price REAL"
     */

    EditText etbookTitle, etisbn, etqtyStock, etprice;
    Spinner spnPublisher;
    Button btnAddBook, btnCancel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_add_book, container, false);

        etbookTitle = v.findViewById(R.id.etbookTitle);
        etisbn = v.findViewById(R.id.etIsbn);
        etqtyStock = v.findViewById(R.id.etQtyStock);
        etprice = v.findViewById(R.id.etPrice);

        spnPublisher = v.findViewById(R.id.spnPublisher);

        btnAddBook = v.findViewById(R.id.btnAddBook);
        btnCancel = v.findViewById(R.id.btnCancel);

        PublisherModel publisherModel = new PublisherModel(getContext());
        ArrayList<Publisher> publishers = publisherModel.getAllPublishers();

        String[] publisherNames = new String[publishers.size()];
        for (int i = 0; i < publishers.size(); i++) {
            publisherNames[i] = publishers.get(i).getPublisher();
        }

        // add publishers to spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, publisherNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnPublisher.setAdapter(adapter);

        // the click event for the save button
        btnAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the values from the UI
                String bookTitle = etbookTitle.getText().toString();
                String isbn = etisbn.getText().toString();
                String publisher = spnPublisher.getSelectedItem().toString();
                String qtyStockStr = etqtyStock.getText().toString();
                int qtyStock = 0;
                double price = 0;

                try
                {
                    qtyStock = Integer.parseInt(qtyStockStr);
                }
                catch (NumberFormatException e)
                {
                    Toast.makeText(getContext(), "Please enter a valid number for the quantity", Toast.LENGTH_SHORT).show();
                }

                try{
                    price = Double.parseDouble(etprice.getText().toString());
                }catch (NumberFormatException e){
                    Toast.makeText(getContext(), "Please enter a valid number for the price", Toast.LENGTH_SHORT).show();
                }

                // validate the values
                if (bookTitle.isEmpty() || isbn.isEmpty() || publisher.isEmpty() || qtyStock == 0 || price == 0) {
                    Toast.makeText(getContext(), "Please enter all the values", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (isbn.length() != 13) {
                    Toast.makeText(getContext(), "ISBN must be 13 digits", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (qtyStock < 0) {
                    Toast.makeText(getContext(), "Quantity must be greater than 0", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (price < 0) {
                    Toast.makeText(getContext(), "Price must be greater than 0", Toast.LENGTH_SHORT).show();
                    return;
                }

                // create a book object
                Book book = new Book();
                book.setBookTitle(bookTitle);
                book.setIsbn(isbn);
                book.setPublisher(publisher);
                book.setQtyStock(qtyStock);
                book.setPrice(price);

                // create a book model object
                StockModel stockModel = new StockModel(getContext());

                // call the addBook method
                boolean status = stockModel.addBook(book);

                if (status) {
                    // clear the UI
                    etbookTitle.setText("");
                    etisbn.setText("");
                    etqtyStock.setText("");
                    etprice.setText("");
                    Toast.makeText(getContext(), "Book added successfully", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(), "Error adding book", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // the click event for the cancel button
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // clear the UI
                etbookTitle.setText("");
                etisbn.setText("");
                etqtyStock.setText("");
                etprice.setText("");

                // remove current fragment
                getActivity().getSupportFragmentManager().beginTransaction().remove(AddBookFragment.this).commit();
            }
        });

        return v;
    }
}