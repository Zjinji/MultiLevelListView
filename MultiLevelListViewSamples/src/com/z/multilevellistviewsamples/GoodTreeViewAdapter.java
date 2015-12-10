package com.z.multilevellistviewsamples;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.z.multilevellistview.bean.MultiLevelBean;
import com.z.multilevellistview.settings.ArrowSettings;
import com.z.multilevellistview.settings.FolderManager;
import com.z.multilevellistview.settings.PaddingSettings;
import com.z.multilevellistview.widget.XListView;
import com.z.multilevellistview.widget.XListView.IXListViewListener;
public class GoodTreeViewAdapter extends BaseAdapter implements IXListViewListener{
	/** 元素数据源 */
	private ArrayList<MultiLevelBean> elementsData;
	/** 树中元素 */
	private ArrayList<MultiLevelBean> elements;
	/**ListView*/
	private XListView xListView;
	private Context context;
	private Animation toLeft;
	//记录是否已经加载过该分类
	private List<String> gcPidList;
	
	private FolderManager folderManager;
	
	public GoodTreeViewAdapter(Context context, ArrayList<MultiLevelBean> elements, ArrayList<MultiLevelBean> elementsData ,XListView xListView, List<String> gcPidList, FolderManager folderManager) {
		this.context = context;
		this.elements = elements;
		this.elementsData = elementsData;
		this.xListView = xListView;
		this.gcPidList = gcPidList;
		this.folderManager = folderManager;
		folderManager = new FolderManager();
		InitAnima();
	}
	
	private void InitAnima() {
		toLeft = AnimationUtils.loadAnimation(context, R.anim.anim_item_right_to_left);
	}
	
	public void initData(){
		xListView.setPullLoadEnable(false);
		xListView.setPullRefreshEnable(true);
		xListView.setXListViewListener(this);
		this.onRefresh();
	}
	
	public ArrayList<MultiLevelBean> getElements() {
		return elements;
	}
	
	public ArrayList<MultiLevelBean> getElementsData() {
		return elementsData;
	}
	
	@Override
	public int getCount() {
		return elements.size();
	}

	@Override
	public Object getItem(int position) {
		return elements.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.enquiry_item_layout, null);
			holder.elementText = (TextView) convertView.findViewById(R.id.goods_classify_textview);
			holder.arrowImg = (ImageView) convertView.findViewById(R.id.goods_classify_imageview);
			
			convertView.setTag(holder);
			convertView.setAnimation(toLeft);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		MultiLevelBean element = elements.get(position);
		holder.elementText.setText(element.getContent());
		
		PaddingSettings paddingSettings = new PaddingSettings(element, holder.elementText, holder.arrowImg);
		folderManager.setPadding(paddingSettings);
		ArrowSettings arrowSettings = new ArrowSettings(holder.arrowImg, element, R.drawable.classify_arrow_icon, R.drawable.classify_arrow_icon_down);
		folderManager.adapterFolderView(arrowSettings);
		
		return convertView;
	}
	
	class ViewHolder{
		TextView elementText;
		ImageView arrowImg;
	}
	
	@Override
	public void onRefresh() {
		elementsData.clear();
		elements.clear();
		Map<String, Object> map = null;
		Map<String, Object> mapStatus = null;
		List<Map<String, Object>> goodDataList = null;
		GoodElement nodeElement = null;
		
		try {
			map = JsonUtils.getMapObj(Constants.DATA5);
			goodDataList = JsonUtils.getListMap(map.get("goods_class_data").toString());
			
			for(Map<String, Object> xMap : goodDataList){
				//节点名称，节点level，节点id，父节点id，是否有子节点，是否展开
				int id = Integer.parseInt(xMap.get("id").toString());
				int gcPid = Integer.parseInt(xMap.get("gc_pid").toString());
				String gcName = xMap.get("gc_name").toString();
				int gcChild = Integer.parseInt(xMap.get("gc_child").toString());
				int gcCeng = Integer.parseInt(xMap.get("gc_ceng").toString());
				boolean hasChildNode = gcChild == 0 ? false : true;
				if(gcPid == 0){
					nodeElement = new GoodElement(id, gcName, GoodElement.NO_PARENT, gcCeng - 1, hasChildNode, false);
					elements.add(nodeElement);
				}else{
					nodeElement = new GoodElement(id, gcName, gcPid, gcCeng - 1, hasChildNode, false);
					elementsData.add(nodeElement);
				}
				//保存节点属性数据
				nodeElement.setId(id);
				nodeElement.setParend_id(gcPid);
				nodeElement.setGc_name(gcName);
				nodeElement.setGc_child(gcChild);
				nodeElement.setGc_ceng(gcCeng);
			}
		} catch (Exception e) {
			e.printStackTrace();
			xListView.stopRefresh();
		}
		
		Toast.makeText(context, "Already Refresh", Toast.LENGTH_SHORT).show();
		xListView.stopRefresh();
	}

	@Override
	public void onLoadMore() {
		
	}
	
}
