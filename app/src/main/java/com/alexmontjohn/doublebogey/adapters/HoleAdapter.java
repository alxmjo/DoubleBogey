package com.alexmontjohn.doublebogey.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.alexmontjohn.doublebogey.R;
import com.alexmontjohn.doublebogey.score.ScoreCard;

public class HoleAdapter extends BaseAdapter implements ListAdapter {

    private ScoreCard mScoreCard;
    private Context context;

    public HoleAdapter(ScoreCard mScoreCard, Context context) {
        this.mScoreCard = mScoreCard;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mScoreCard.holes.size();
    }

    @Override
    public Object getItem(int pos) {
        return mScoreCard.holes.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.hole_list_item, null);
        }

        // Set hole name
        TextView listHoleName = (TextView) view.findViewById(R.id.hole_name);
        listHoleName.setText(mScoreCard.holes.get(position));

        // Set score
        final TextView listStrokes = (TextView) view.findViewById(R.id.stroke_count);
        listStrokes.setText(String.valueOf(mScoreCard.scores.get(position)));

        // Handle buttons and add onClickListeners
        final Button subtractBtn = (Button) view.findViewById(R.id.subtract_btn);
        final Button addBtn = (Button) view.findViewById(R.id.add_btn);

        // Start subtract button off invisible
        if (mScoreCard.scores.get(position) == 0) {
            subtractBtn.setVisibility(View.INVISIBLE);
        } else {
            subtractBtn.setVisibility(View.VISIBLE);
        }

        subtractBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int s = mScoreCard.scores.get(position);
                s--;
                mScoreCard.scores.set(position, s);
                listStrokes.setText(String.valueOf(mScoreCard.scores.get(position)));
                if (mScoreCard.scores.get(position) < 1) {
                    subtractBtn.setVisibility(View.INVISIBLE);
                }
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int s = mScoreCard.scores.get(position);
                s++;
                mScoreCard.scores.set(position, s);
                listStrokes.setText(String.valueOf(mScoreCard.scores.get(position)));
                subtractBtn.setVisibility(View.VISIBLE);
            }
        });

        return view;
    }
}
