package quick.kural.quickstart.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import quick.kural.quickstart.R;
import quick.kural.quickstart.Retrofit.Objects.SearchListing.Datum;
import quick.kural.quickstart.Utils.GlideApp;

public class AdapterResultsBelowmap extends
    RecyclerView.Adapter<AdapterResultsBelowmap.ViewHolder> {
    RecylerGridInterface rvInterface_main;
    List<Datum> mValues;
    Context context_main;


        public AdapterResultsBelowmap(Context context,
                                      RecylerGridInterface rvInterface,
                                      List<Datum> items){
        mValues = items;
        rvInterface_main = rvInterface;
        context_main=context;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.adapter_horizontal, parent, false);
        return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            Datum mItem = mValues.get(position);

            holder.tv_heading.setText(mItem.getListingTitle());

        GlideApp.with(context_main)
        .load(mItem.getListingId())
        .placeholder(R.drawable.logo)
        .apply(RequestOptions.circleCropTransform())
        .into(holder.imageView);

        int rating_val = 3; //mItem.getListingReviews()
        holder.ratingBar.setNumStars(rating_val);

        holder.mView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        if (null != rvInterface_main) {
        // Notify the active callbacks interface (the activity, if it
            // is attached to one) that an item has been selected.
        rvInterface_main.recyclerItemClicked(position);
        }
        }
        });



        }


    class ViewHolder extends RecyclerView.ViewHolder {

            View mView;
            ImageView imageView;
            TextView tv_heading;
            RatingBar ratingBar;
            ViewHolder(View view) {
            super(view);
            mView = view;
            imageView = mView.findViewById(R.id.imageView_vp_main);
            tv_heading = mView.findViewById(R.id.textView_vp_heading);
            ratingBar = mView.findViewById(R.id.rating_bar);

    }



    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }


    public interface RecylerGridInterface{
         void recyclerItemClicked(int positionOfClick);
    }


}