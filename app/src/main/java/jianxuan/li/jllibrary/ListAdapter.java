package jianxuan.li.jllibrary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import jianxuan.li.jllibrary.data.Book;

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<Book> books;
    private final Context context;

    public class ListViewHolder extends RecyclerView.ViewHolder {
        public TextView txtBookTitle, txtIsbn, txtPublisher, txtQtyStock, txtPrice;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            txtBookTitle = itemView.findViewById(R.id.txtBookTitle);
            txtIsbn = itemView.findViewById(R.id.txtIsbn);
            txtPublisher = itemView.findViewById(R.id.txtPublisher);
            txtQtyStock = itemView.findViewById(R.id.txtQtyStock);
            txtPrice = itemView.findViewById(R.id.txtPrice);
        }
    }

    public ListAdapter(List<Book> books, Context context) {
        this.books = books;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_list_record_layout, parent, false);
        return new ListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Book book = books.get(position);
        ((ListViewHolder) holder).txtBookTitle.setText("BookTitle: " + book.getBookTitle());
        ((ListViewHolder) holder).txtIsbn.setText("ISBN: " + book.getIsbn());
        ((ListViewHolder) holder).txtPublisher.setText("Publisher: " + book.getPublisher());
        ((ListViewHolder) holder).txtQtyStock.setText("Quantity in Stock: " + String.valueOf(book.getQtyStock()));
        ((ListViewHolder) holder).txtPrice.setText("Price: " + String.valueOf(book.getPrice()));
    }

    @Override
    public int getItemCount() {
        return this.books.size();
    }
}
