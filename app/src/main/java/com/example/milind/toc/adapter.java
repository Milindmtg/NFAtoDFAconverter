package com.example.milind.toc;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class adapter extends ArrayAdapter {
        //to reference the Activity
        private final Activity context;
        private ArrayList<String> data;

        //to store the list of countries
        private final ArrayList<String> nameArray;
        private final ArrayList<String> info;
        //to store the list of countries
        public adapter(Activity context, ArrayList<String> nameArrayParam,ArrayList<String> info){

            super(context,R.layout.list , nameArrayParam);

            this.context=context;
            this.info=info;
            this.nameArray = nameArrayParam;

        }

    public ArrayList<String> getData() {
        return data;
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater=context.getLayoutInflater();
            View rowView=inflater.inflate(R.layout.list, null,true);

            //this code gets references to objects in the listview_row.xml file
            TextView name = (TextView) rowView.findViewById(R.id.view);
            TextView infor = rowView.findViewById(R.id.alpha);
            //data=new ArrayList<>();
            //this code sets the values of the objects to values from the arrays
            name.setText(nameArray.get(position));
            infor.setText(info.get(position));
            rowView=inflater.inflate(R.layout.list, null,true);
            name = (TextView) rowView.findViewById(R.id.view);
            infor=(TextView)rowView.findViewById(R.id.alpha);
            name.setText(nameArray.get(position));
            infor.setText(info.get(position));
            //data.add(info.getText().toString());
            return rowView;
        };

    }

