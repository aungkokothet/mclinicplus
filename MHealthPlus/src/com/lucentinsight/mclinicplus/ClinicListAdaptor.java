package com.lucentinsight.mclinicplus;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.SimpleAdapter.ViewBinder;

public class ClinicListAdaptor extends SimpleAdapter {

	public ClinicListAdaptor(Context context,
			List<? extends Map<String, ?>> data, int resource, String[] from,
			int[] to) {
		super(context, data, resource, from, to);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setViewImage(ImageView v, String value) {
		// TODO Auto-generated method stub
		//super.setViewImage(v, value);
		v.setContentDescription(value);
	}
	
	
}
