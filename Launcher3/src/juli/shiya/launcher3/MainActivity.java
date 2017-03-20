package juli.shiya.launcher3;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {
    private List<ResolveInfo> mApps;
    
    List<ResolveInfo>list;
    GridView mGrid;
    GridAdapter gridAdapter;
    private int mScreenWidth;  
    private int mScreenHeight;  
    private OnItemClickListener listener = new OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
            ResolveInfo info = mApps.get(position);
            
            //该应用的包名
            String pkg = info.activityInfo.packageName;
            //应用的主activity类
            String cls = info.activityInfo.name;
            
            ComponentName componet = new ComponentName(pkg, cls);
            
            Intent i = new Intent();
            i.setComponent(componet);
            startActivity(i);
        }

    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        Display display = getWindowManager().getDefaultDisplay();  
        mScreenHeight= display.getHeight();  
        mScreenWidth = display.getWidth();  
        loadApps();
        setContentView(R.layout.activity_main);
        mGrid = (GridView) findViewById(R.id.apps_list);
        
        gridAdapter = new  GridAdapter(this,mApps,mScreenHeight,mScreenWidth);
        mGrid.setAdapter(gridAdapter);

        mGrid.setOnItemClickListener(listener);
    }
    private void loadApps() {
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        
        list = new ArrayList<ResolveInfo>();
        mApps = getPackageManager().queryIntentActivities(mainIntent, 0);
        
       
        for (int i = 0; i < mApps.size(); i++) {
			
        	ResolveInfo info = mApps.get(i);
        	
        	if (info.activityInfo.packageName.equals("juli.shiya.launcher3")) {
        		
        		
            	mApps.remove(i);
            	
            	
    		}
    
		}
    }
}