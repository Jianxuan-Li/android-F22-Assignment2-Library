package jianxuan.li.jllibrary;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import jianxuan.li.jllibrary.data.Book;
import jianxuan.li.jllibrary.data.StockModel;

public class ListBookFragment extends Fragment {
    View v;
    List<Book> books;
    ListAdapter bookListAdapter;

    RecyclerView rcView;
    Button btnCancel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_list_book, container, false);

        rcView = v.findViewById(R.id.recyclerView);
        btnCancel = v.findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(ListBookFragment.this).commit();
            }
        });

        loadBooks();

        return v;
    }

    private void loadBooks() {
        books = new StockModel(getContext()).getBooks();
        bookListAdapter = new ListAdapter(books, getContext());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcView.setLayoutManager(layoutManager);

        rcView.setAdapter(bookListAdapter);
    }
}