package com.example.milind.toc;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class nfatodfa extends AppCompatActivity {

    private ArrayList<String> alphalist = new ArrayList<>();
    private ArrayList<String> templist =new ArrayList<>();
    private adapter states;
    private ListView listView;
    private ListView listView2;
    private EditText cost;
    private EditText coal;
    private ArrayList<String> statelist;
    private final Activity ac=this;
    private int alphact;
    private int statct;
    private int totst;
    private int total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfatodfa);
        try {
            findViewById(R.id.noalp).setVisibility(View.GONE);
            states = new adapter(this, templist, alphalist);
            listView = (ListView) findViewById(R.id.liststat);
            listView.setAdapter(states);
            listView.setVisibility(View.GONE);
            listView2 = findViewById(R.id.listalp);
            listView2.setVisibility(View.GONE);
            cost = findViewById(R.id.cost);
            coal = findViewById(R.id.coal);
            statelist = new ArrayList<>();
            totst = 9999999;
            statct = -1;
            alphact = -1;
            total = 9999999;
            findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (findViewById(R.id.noalp).getVisibility() != View.VISIBLE) {
                        if (cost.getText().toString() != "" && totst == 9999999) {
                            statct = Integer.parseInt(cost.getText().toString());
                            totst = 1;
                            cost.setText("");
                            cost.setHint("Enter state no. 1");
                        } else if (totst <= statct) {
                            if (totst != statct) {
                                cost.setHint("Enter state no " + Integer.toString(totst + 1));
                            } else {
                                cost.setVisibility(View.GONE);
                            }
                            statelist.add(cost.getText().toString());
                            templist.add("State no. " + Integer.toString(totst));
                            states = new adapter(ac, templist, statelist);
                            listView = (ListView) findViewById(R.id.liststat);
                            listView.setAdapter(states);
                            listView.setVisibility(View.VISIBLE);
                            totst++;
                            cost.setText("");
                        } else if (totst > statct && cost.getText().toString() != "" && total == 9999999) {
                            if (cost.getVisibility() != View.VISIBLE) {
                                cost.setVisibility(View.VISIBLE);
                                findViewById(R.id.noalp).setVisibility(View.VISIBLE);
                                findViewById(R.id.nostat).setVisibility(View.GONE);
                                templist.clear();
                                alphalist.clear();
                            } else {
                                alphact = Integer.parseInt(cost.getText().toString());
                                total = alphact;
                                cost.setText("");
                                statct--;
                            }
                        }
                    } else if (findViewById(R.id.noalp).getVisibility() == View.VISIBLE) {
                        if (coal.getText().toString() != "" && total == 9999999) {
                            alphact = Integer.parseInt(coal.getText().toString());
                            total = 1;
                            coal.setText("");
                            coal.setHint("Enter Alphabet no. 1");
                        } else if (total <= alphact) {
                            if (total != alphact) {
                                coal.setHint("Enter Alphabet no " + Integer.toString(total + 1));
                            } else {
                                coal.setVisibility(View.GONE);
                            }
                            alphalist.add(coal.getText().toString());
                            templist.add("Alphabet no. " + Integer.toString(total));
                            adapter alp = new adapter(ac, templist, alphalist);
                            listView = (ListView) findViewById(R.id.listalp);
                            listView.setAdapter(alp);
                            listView.setVisibility(View.VISIBLE);
                            total++;
                            coal.setText("");
                        }
                        if (total > alphact) {
                            Intent in = new Intent(getApplicationContext(), transtab.class);
                            Bundle bun = new Bundle();
                            bun.putStringArrayList("states", statelist);
                            bun.putStringArrayList("alpha", alphalist);
                            in.putExtras(bun);
                            startActivity(in);
                        }
                    }
                }
            });
        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage()+"\nInvalid input",Toast.LENGTH_SHORT).show();
        }
    }
}
