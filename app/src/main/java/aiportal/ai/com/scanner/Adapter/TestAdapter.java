package aiportal.ai.com.scanner.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import aiportal.ai.com.scanner.R;
import aiportal.ai.com.scanner.bean.ds1Entrty;

/**
 * Created by baggio on 2017/12/30.
 */
public class TestAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<ds1Entrty> ds1EntrtyList;
    private Context context;
    public TestAdapter(Context context, List<ds1Entrty> ds1EntrtyList) {
        this.ds1EntrtyList = ds1EntrtyList;
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (ds1EntrtyList != null) {
            return ds1EntrtyList.size() + 1;
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.item_test_list, null);
                holder = new ViewHolder();
                holder.mTvNumber = (TextView) convertView.findViewById(R.id.txt_number);
                holder.mTvValue = (TextView) convertView.findViewById(R.id.txt_value);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if(position != 0){
                ds1Entrty ds1Entrty = ds1EntrtyList.get(position-1);
                holder.mTvNumber.setText(ds1Entrty.getRowNo() + "");
                holder.mTvValue.setText(ds1Entrty.getDESCRIPTION() + "");
                if(position == ds1EntrtyList.size()){
                    holder.mTvNumber.setBackgroundResource(R.drawable.boder_bottom);
                    holder.mTvValue.setBackgroundResource(R.drawable.boder_bottom);
                }
            }



        return convertView;
    }

    static class ViewHolder {
        public TextView mTvNumber, mTvValue;
    }
}
