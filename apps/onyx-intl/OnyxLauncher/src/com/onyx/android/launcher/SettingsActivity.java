/**
 * 
 */
package com.onyx.android.launcher;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;

import com.onyx.android.launcher.adapter.SettingsAdapter;
import com.onyx.android.launcher.data.GridItemManager;
import com.onyx.android.launcher.view.OnyxPagedGridViewHost;
import com.onyx.android.sdk.ui.OnyxGridView;
import com.onyx.android.sdk.ui.data.GridItemData;
import com.onyx.android.sdk.ui.data.GridViewPaginator.OnPageIndexChangedListener;
import com.onyx.android.sdk.ui.util.ScreenUpdateManager;
import com.onyx.android.sdk.ui.util.ScreenUpdateManager.UpdateMode;

/**
 * @author joy
 *
 */
public class SettingsActivity extends OnyxBaseActivity
{
    @SuppressWarnings("unused")
    private static final String TAG = "SettingsActivity";
    
    private OnyxGridView mGridView = null;
    private ImageButton mButtonHome = null;
    
    @Override
    public OnyxGridView getGridView()
    {
        return mGridView;
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        this.setContentView(R.layout.activity_settings);
        
        mGridView = ((OnyxPagedGridViewHost)findViewById(R.id.gridview_settings)).getGridView(); ;
        mButtonHome = (ImageButton)this.findViewById(R.id.button_home);
        
        mGridView.setOnItemClickListener(new OnItemClickListener()
        {
            
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id)
            {
                GridItemData item = (GridItemData)view.getTag();
                GridItemManager.processURI(mGridView, item.getURI(), SettingsActivity.this);
            }
        });
        
        mButtonHome.setOnClickListener(new OnClickListener()
        {
            
            @Override
            public void onClick(View v)
            {
                LauncherActivity.goLauncherHome(SettingsActivity.this);
                SettingsActivity.this.finish();
            }
        });
        
        SettingsAdapter adapter = new SettingsAdapter(this, mGridView);
        adapter.getPaginator().registerOnPageIndexChangedListener(new OnPageIndexChangedListener()
        {

        	@Override
        	public void onPageIndexChanged()
        	{
        		ScreenUpdateManager.invalidate(mGridView, UpdateMode.GC);
        	}
        });

        ArrayList<GridItemData> settings = GridItemManager.getSettings();
        adapter.fillItems(GridItemManager.getSettingsURI(), settings);
        mGridView.setAdapter(adapter);

        this.registerLongPressListener();
        this.initGridViewItemNavigation();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu)
    {
        SettingsActivity.this.disabledMenuMultiple(menu);
        return super.onPrepareOptionsMenu(menu);
    }
    
    protected void initGridViewItemNavigation()
    {
    	mGridView.setCrossVertical(true);
    	mGridView.setCrossHorizon(false);
    }
}
