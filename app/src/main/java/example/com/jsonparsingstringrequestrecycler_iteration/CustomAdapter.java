package example.com.jsonparsingstringrequestrecycler_iteration;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by compaq on 4/1/2018.
 */

public class CustomAdapter extends RecyclerView.Adapter {
    private Activity mActivity;
    private List<String> mList;

    public CustomAdapter(Activity activity, List<String> list) {
        this.mActivity = activity;
        this.mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        LayoutInflater layoutInflater = (LayoutInflater) viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewGroup view = (ViewGroup) layoutInflater.inflate(R.layout.custom_layout, viewGroup, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        DataViewHolder mHolder = (DataViewHolder) holder;
        mHolder.mTitle.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private class DataViewHolder extends RecyclerView.ViewHolder {
        TextView mTitle;

        public DataViewHolder(View view) {
            super(view);
            mTitle = view.findViewById(R.id.tv_text);
        }
    }
}
