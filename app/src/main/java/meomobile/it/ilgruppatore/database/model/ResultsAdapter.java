package meomobile.it.ilgruppatore.database.model;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

import meomobile.it.ilgruppatore.R;

/**
 * Creato da fabio on 05/04/2016.
 */
public class ResultsAdapter extends ArrayAdapter<Result> {
    Activity context;

    public ResultsAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        Result result = getItem(position);
        // Controlla se una vista è stata usata, altrimenti inflata la nuova view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.rowgruppo, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.list_item_gruppo);
        TextView tvHome = (TextView) convertView.findViewById(R.id.tvVote);
        Switch s = (Switch) convertView.findViewById(R.id.switchVote);
        // Collego lo switch?
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Premuto: " + v + " " + v.getParent());
                getItem(position).setVote(1F);
            }
        });
        // Populate the data into the template view using the data object
        tvName.setText(result.getName());
        tvHome.setText(String.valueOf(result.getVote()));
        s.setChecked((result.getVote() > 0.5 ? true : false));
        // Return the completed view to render on screen
        return convertView;
    }
}
