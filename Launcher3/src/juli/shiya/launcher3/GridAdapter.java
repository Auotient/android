package juli.shiya.launcher3;

import java.util.List;

import android.content.Context;
import android.content.pm.ResolveInfo;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter{

	Context context;
	List<ResolveInfo>maList;
	private LayoutInflater mInflater;  
	LinearLayout.LayoutParams params;  
	private int mScreenWidth;  
    private int mScreenHeight;  
	
	public GridAdapter(Context  context, List<ResolveInfo> mApps, int mScreenHeight, int mScreenWidth) {
		mInflater = LayoutInflater.from(context);  
		this.context = context;
		this.maList = mApps;
		this.mScreenWidth=mScreenWidth;
		this.mScreenHeight=mScreenHeight;
		
		params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);  
		params.gravity = Gravity.CENTER;  
	}

	@Override
	public int getCount() {
		return maList.size();
	}

	@Override
	public Object getItem(int position) {
		return maList.get(position);
	}

	@Override
	public long getItemId(int position) {
		
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ItemViewTag viewTag;  
	  
		if (convertView == null)  
		{  
			convertView = mInflater.inflate(R.layout.grid_item, null);  
			viewTag = new ItemViewTag((ImageView) convertView.findViewById(R.id.grid_icon), (TextView) convertView.findViewById(R.id.grid_name));  
			convertView.setTag(viewTag);  
		} else  
		{  
			viewTag = (ItemViewTag) convertView.getTag();  
		}  
		
		params = (LayoutParams) viewTag.mIcon.getLayoutParams();  
		params.height = (mScreenHeight/8)-20;
		params.width = (mScreenWidth-20)/2-20;
		ResolveInfo info = maList.get(position);
	
			
			viewTag.mName.setVisibility(View.VISIBLE);
		    viewTag.mIcon.setVisibility(View.VISIBLE);
			viewTag.mName.setText(info.loadLabel(context.getPackageManager()).toString());  
		    viewTag.mIcon.setImageDrawable(info.activityInfo.loadIcon(context.getPackageManager()));
			viewTag.mIcon.setLayoutParams(params);
		return convertView;  
	}
	class ItemViewTag  
	{  
		protected ImageView mIcon;  
		protected TextView mName;  


		public ItemViewTag(ImageView icon, TextView name)  
		{  
			this.mName = name;  
			this.mIcon = icon;  
		} 

	}
}
