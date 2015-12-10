package com.z.multilevellistviewsamples;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.HeaderViewListAdapter;
import android.widget.Toast;

import com.z.multilevellistview.bean.MultiLevelBean;
import com.z.multilevellistview.settings.FolderManager;
import com.z.multilevellistview.settings.FolderSettings;
import com.z.multilevellistview.widget.XListView;


public class MainActivity extends Activity{
	private XListView xListView;
	//商品分类
	private GoodTreeViewAdapter treeViewAdapter;
	private ArrayList<MultiLevelBean> elements = new ArrayList<MultiLevelBean>();
	private ArrayList<MultiLevelBean> elementsData = new ArrayList<MultiLevelBean>();
	private List<String> gcPidList = new ArrayList<String>();
	private FolderManager folderManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xListView = (XListView) findViewById(R.id.xlistview);
        
        treeViewAdapter = new GoodTreeViewAdapter(this, elements, elementsData, xListView, gcPidList, folderManager = new FolderManager());
        xListView.setAdapter(treeViewAdapter);
        treeViewAdapter.initData();
        
        TreeViewItemClickListener treeViewItemClickListener = new TreeViewItemClickListener();
        xListView.setOnItemClickListener(treeViewItemClickListener);
        
    }

    
    private class TreeViewItemClickListener implements OnItemClickListener {
		private View frontView = null;
		private int frontPosition = -1;
		
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
			GoodTreeViewAdapter adapter = null;
			int tempPosition = (int) parent.getItemIdAtPosition(position);
			if(xListView.getAdapter() instanceof HeaderViewListAdapter) {
				 HeaderViewListAdapter listAdapter=(HeaderViewListAdapter)xListView.getAdapter();
				 adapter = (GoodTreeViewAdapter) listAdapter.getWrappedAdapter();
			}else{
				 adapter = ((GoodTreeViewAdapter) parent.getAdapter());
			}
			
			GoodElement element = (GoodElement) adapter.getItem(tempPosition);
			Toast.makeText(MainActivity.this, element.getContent(), Toast.LENGTH_SHORT).show();
			
			view.setBackgroundColor(Color.parseColor("#eaeaea"));
			if (frontPosition != -1 && frontPosition != tempPosition) {
				frontView.setBackgroundColor(Color.parseColor("#ffffff"));
			}
			frontView = view;
			frontPosition = tempPosition;
			if(element != null){
				if (!element.isHasChildView()) {
					return;
				}else{
					FolderSettings folderSettings = new FolderSettings(element, elements, elementsData, tempPosition, adapter);
					folderManager.foldOrUnFoldFunction(folderSettings);
				}
			}else{
				return;
			}
		}

	}
}
