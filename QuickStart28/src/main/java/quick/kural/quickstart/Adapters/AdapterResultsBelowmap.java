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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
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
        MultiTransformation multi = new MultiTransformation(
        new RoundedCornersTransformation(90, 0, RoundedCornersTransformation.CornerType.TOP_LEFT));



        GlideApp.with(context_main)
        .load(mItem.getListingSliders().get(0).getImgPath())
        .apply(RequestOptions.bitmapTransform(multi))
        .into(holder.imageView);


        GlideApp.with(context_main)
        .load(mItem.getListingSliders().get(0).getImgPath())
        .apply(RequestOptions.circleCropTransform())
        .into(holder.imageView_pic);

        holder.tv_categorie.setText(mItem.getMCategory().getCategoryName());
        holder.tv_review_txt.setText(mItem.getDescription());//mItem.getListingReviews().get(0).getReview()
        holder.tv_address.setText(mItem.getDescription());


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
            ImageView imageView, imageView_pic;
            TextView tv_heading,tv_categorie,tv_review_txt,tv_address;
            RatingBar ratingBar;
            ViewHolder(View view) {
            super(view);
            mView = view;
            imageView = mView.findViewById(R.id.imageView_vp_main);
            tv_heading = mView.findViewById(R.id.textView_vp_heading);
            tv_categorie = mView.findViewById(R.id.text_heading_over_img);
            tv_review_txt = mView.findViewById(R.id.textView_review_txt);
            tv_address = mView.findViewById(R.id.textView_address);
            ratingBar = mView.findViewById(R.id.rating_bar);
            imageView_pic = mView.findViewById(R.id.imageView_user_pic);

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