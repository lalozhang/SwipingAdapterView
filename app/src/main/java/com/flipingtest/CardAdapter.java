package com.flipingtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.flipingtest.model.CardEntity;

import java.util.ArrayList;

/**
 * Created by lalo.zhang on 2015/12/20.
 */
public class CardAdapter extends BaseAdapter{
    private ArrayList<CardEntity> data;
    private Context context;
    private OnCardExitListener mListener;

    public CardAdapter(Context context,ArrayList<CardEntity> data){
        this.context=context;
        this.data=data;
    }

    public void setOnCardExitListener(OnCardExitListener listener){
        this.mListener=listener;
    }

    @Override
    public int getCount() {
        if(data!=null&&!data.isEmpty()){
            return data.size();
        }
        return 0;
    }

    @Override
    public CardEntity getItem(int position) {
        if (data!=null&&!data.isEmpty()&&data.size()>position){
            return data.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final CardEntity item = getItem(position);
        ViewHolder holder;
        if(convertView==null){
            holder=new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView= inflater.inflate(R.layout.fliping_card_item, parent, false);
            holder.tvTitle= (TextView) convertView.findViewById(R.id.tv_title);
            holder.tvName= (TextView) convertView.findViewById(R.id.tv_name);
            holder.tvPositionCompanyLoc= (TextView) convertView.findViewById(R.id.tv_position_company_loc);
            holder.tvDes= (TextView) convertView.findViewById(R.id.tv_des);
            holder.tvMessage= (TextView) convertView.findViewById(R.id.tv_message);
            holder.icon=(ImageView)convertView.findViewById(R.id.iv_pic);
            holder.ivIgnore= (ImageView) convertView.findViewById(R.id.iv_dislike);
            holder.ivPass= (ImageView) convertView.findViewById(R.id.iv_like);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }

        holder.tvTitle.setText(item.title);
        holder.tvName.setText(item.name);
        holder.tvPositionCompanyLoc.setText(item.position+"|"+item.company+"("+item.locCity+")");
        holder.tvDes.setText(item.des);
        holder.tvMessage.setText(item.message);
        holder.icon=(ImageView)convertView.findViewById(R.id.iv_pic);
        holder.ivIgnore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener!=null){
                    mListener.onLeftExit(item);
                }
            }
        });
        holder.ivPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener!=null){
                    mListener.onRightExit(item);
                }
            }
        });

        return convertView;
    }

    private static class ViewHolder{

        public TextView tvTitle;
        public TextView tvDes;
        public TextView tvName;
        public TextView tvPositionCompanyLoc;
        public TextView tvMessage;
        public ImageView icon;
        public ImageView ivIgnore;
        public ImageView ivPass;
    }

    public interface OnCardExitListener{
        void onLeftExit(CardEntity item);
        void onRightExit(CardEntity item);
    }
}
