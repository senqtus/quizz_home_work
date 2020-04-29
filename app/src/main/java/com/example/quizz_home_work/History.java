package com.example.quizz_home_work;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.quizz_home_work.data.HistoryRow;
import com.example.quizz_home_work.data.HistoryStorage;
import com.example.quizz_home_work.data.Storage;
import com.example.quizz_home_work.data.StorageImplementation;

import java.util.ArrayList;
import java.util.List;

public class History extends Activity {
    ListView history;
    HistoryStorage historyStorage;
    ArrayList<HistoryRow> historyForDisplay;
    HistoryArrayAdapter historyArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        history = findViewById(R.id.history);
        updateListView();
    }

    private void updateListView(){
        historyArrayAdapter = new HistoryArrayAdapter(this, 0, new ArrayList<HistoryRow>());
        history.setAdapter(historyArrayAdapter);
        Storage storageQuestion = new StorageImplementation();
        Object storageAsObject = storageQuestion
                .getObject(this, StorageImplementation.HISTORY, HistoryStorage.class);
        if (storageAsObject != null) {
            historyStorage = (HistoryStorage) storageAsObject;
        } else {
            historyStorage = new HistoryStorage();
        }
        historyForDisplay=historyStorage.getHistory();
        historyArrayAdapter.addAll(historyForDisplay);
    }

    class HistoryArrayAdapter extends ArrayAdapter<HistoryRow> {

        private Context mContext;

        private HistoryArrayAdapter(@NonNull Context context,
                                    int resource,
                                    @NonNull List<HistoryRow> objects) {
            super(context, resource, objects);
            mContext = context;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            HistoryRow current = getItem(position);
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.view_lines, parent, false);
            TextView textView = view.findViewById(R.id.row_model);
            textView.setText("Score : "+current.getScore().toString() + " \nTime : " + current.gettime().toString());

            return view;
        }
    }

}
