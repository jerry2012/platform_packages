/**
 * 
 */
package com.onyx.android.launcher.data.actor;

import android.app.Activity;
import android.content.Intent;

import com.onyx.android.launcher.ApplicationsActivity;
import com.onyx.android.launcher.R;
import com.onyx.android.sdk.data.OnyxItemURI;
import com.onyx.android.sdk.data.util.ActivityUtil;
import com.onyx.android.sdk.ui.OnyxGridView;
import com.onyx.android.sdk.ui.data.GridItemData;

/**
 * @author joy
 *
 */
public class ApplicationsActor extends AbstractItemActor
{

    public ApplicationsActor(OnyxItemURI parentURI)
    {
        super(new GridItemData(((OnyxItemURI)parentURI.clone()).append("Applications"), 
                R.string.Applications, 
                R.drawable.applications));
    }

    /* (non-Javadoc)
     * @see com.onyx.android.homework.data.actor.AbstractGridItemActor#process(com.onyx.android.sdk.ui.OnyxGridView, com.onyx.android.sdk.data.ItemURI)
     */
    @Override
    public boolean process(OnyxGridView gridView, OnyxItemURI uri, Activity hostActivity)
    {
        Intent intent = new Intent(hostActivity, ApplicationsActivity.class);
        return ActivityUtil.startActivitySafely(hostActivity, intent);
    }

}
