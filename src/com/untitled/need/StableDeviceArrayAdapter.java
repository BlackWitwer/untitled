package com.untitled.need;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.untitled.R;

import java.util.HashMap;
import java.util.List;

public class StableDeviceArrayAdapter extends ArrayAdapter<Device> {

	private HashMap<Device, Integer> mIdMap = new HashMap<Device, Integer>();
	private int resource;

	public StableDeviceArrayAdapter(Context context, int textViewResourceId,
	                                List<Device> objects, int aResource) {
		super(context, textViewResourceId, objects);
		for (int i = 0; i < objects.size(); ++i) {
			mIdMap.put(objects.get(i), i);
		}

		this.resource = aResource;
	}

	@Override
	public long getItemId(int position) {
		Device item = getItem(position);
		return mIdMap.get(item);
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LinearLayout deviceView;
		//Get the current alert object
		Device theDevice = getItem(position);

		//Inflate the view
		if(convertView==null)
		{
			deviceView = new LinearLayout(getContext());
			String inflater = Context.LAYOUT_INFLATER_SERVICE;
			LayoutInflater vi;
			vi = (LayoutInflater)getContext().getSystemService(inflater);
			vi.inflate(resource, deviceView, true);
		}
		else
		{
			deviceView = (LinearLayout) convertView;
		}
		//Get the text boxes from the listitem.xml file
		TextView nameText = (TextView)deviceView.findViewById(R.id.txtNameText);
		TextView addressText =(TextView)deviceView.findViewById(R.id.txtAdressText);

		//Assign the appropriate data from our alert object above
		nameText.setText(theDevice.getDeviceName());
		addressText.setText(theDevice.getDeviceAddress());

		return deviceView;
	}
}