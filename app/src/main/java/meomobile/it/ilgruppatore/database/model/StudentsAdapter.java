package meomobile.it.ilgruppatore.database.model;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import meomobile.it.ilgruppatore.R;

/**
 * Creato da fabio on 05/04/2016.
 */
public class StudentsAdapter extends ArrayAdapter<Student> {
    Activity context;

    public StudentsAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        Student student = getItem(position);
        // Controlla se una vista è stata usata, altrimenti inflata la nuova view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.rowgruppo, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.list_item_gruppo);
        TextView tvHome = (TextView) convertView.findViewById(R.id.tvVote);
        // Populate the data into the template view using the data object
        tvName.setText(student.getName());
        tvHome.setText(String.valueOf(student.getVote()));
        // Return the completed view to render on screen
        return convertView;
    }
}
