package br.edu.ufopa.cadfishmaster.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import java.util.List;

import br.edu.ufopa.cadfishmaster.R;

public class ScreenViewAdapter extends PagerAdapter {

    Context context;
    List<ScreenItem> mlistScreen;

    public ScreenViewAdapter(Context context, List<ScreenItem> mlistScreen) {
        this.context = context;
        this.mlistScreen = mlistScreen;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull View container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE );
        View layoutScreen = inflater.inflate(R.layout.activity_menu, null);

        ImageView imageView = layoutScreen.findViewById(R.id.slider_img);
        TextView title = layoutScreen.findViewById(R.id.slider_title);
        TextView description = layoutScreen.findViewById(R.id.slider_description);

        title.setText(mlistScreen.get(position).getTitle());
        description.setText(mlistScreen.get(position).getDescription());
        imageView.setImageResource(mlistScreen.get(position).getScreenImg());

        return layoutScreen;
    }

    @Override
    public int getCount() {
        return mlistScreen.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
