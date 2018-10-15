package com.example.milind.toc;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class transtab extends AppCompatActivity {
    private ArrayList<String> alphalist;
    private ArrayList<String> statelist;
    private ArrayList<String> finast=new ArrayList<>();
    private ArrayList<String> strsta=new ArrayList<>();
    private int curst=0;
    private int cural=0;
    private String[][] DFA;
    private Button trac;
    private ArrayList<ArrayList<String>> DFAST;
    private ArrayList<String>[][] transitions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transtab);
        Bundle bun=new Bundle();
        findViewById(R.id.trans).setVisibility(View.GONE);
        bun=getIntent().getExtras();
        alphalist=bun.getStringArrayList("alpha");
        statelist=bun.getStringArrayList("states");
        transitions=new ArrayList[statelist.size()][alphalist.size()];
        trac=findViewById(R.id.trans);
        trac.setText("where does '" + statelist.get(curst) + "' go on '" + alphalist.get(cural));
        for (int i = 0; i < statelist.size(); i++) {
            for (int j = 0; j < alphalist.size(); j++) {
                    transitions[i][j]=new ArrayList<>();
            }
        }
        trac.setText("where does '"+statelist.get(curst)+"' go on '"+alphalist.get(cural));
        findViewById(R.id.startst).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displaySingleSelectionDialog();
            }
        });
        findViewById(R.id.finst).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectfinal();
            }
        });
        findViewById(R.id.trans).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayMultiSelectDialog();
            }
        });
    }
    private void selectfinal() {
        states = new String[statelist.size()];
        states = statelist.toArray(states);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Select States");
        dialogBuilder.setMultiChoiceItems(states, checkedItems,
                (dialogInterface, which, ischecked) -> {
                    for(int i=0;i<statelist.size();i++)
                        if (checkedItems[i]==true) {
                            finast.add(states[i]);
                        }
                        else
                        {
                            finast.remove(states[which]);
                        }
                }
        );
        dialogBuilder.setPositiveButton("Done", (dialog, which) -> Addfinal());
        dialogBuilder.create().show();
    }

    private void Addfinal() {
        Toast.makeText(getApplicationContext(),"Final states are "+finast.toString(),Toast.LENGTH_SHORT).show();
        findViewById(R.id.finst).setVisibility(View.GONE);
        findViewById(R.id.trans).setVisibility(View.VISIBLE);
    }

    private boolean[] checkedItems = new boolean[7];
    private String[] states;
    private ArrayList<String> selectedstates = new ArrayList<>();
    @SuppressLint("RestrictedApi")
    private void displayMultiSelectDialog() {
        states = new String[statelist.size()];
        states = statelist.toArray(states);
        selectedstates=new ArrayList<>();
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Select States");
        dialogBuilder.setMultiChoiceItems(states, checkedItems,
                (dialogInterface, which, ischecked) -> {
                    if (ischecked) {
                        selectedstates.add(states[which]);
                    }
                }
        );
        dialogBuilder.setPositiveButton("Done", (dialog, which) -> showSelectedColors());
        dialogBuilder.create().show();
    }
    private void showSelectedColors() {
            if(curst<statelist.size())
            {
                transitions[curst][cural]=selectedstates;
                Toast.makeText(getApplicationContext(),statelist.get(curst)+" on "+alphalist.get(cural)+" goes to "+selectedstates.toString(),Toast.LENGTH_SHORT).show();
                cural++;
                if(cural>=alphalist.size()) {
                    curst++;
                    cural=0;
                }
            }
            if(curst<statelist.size())
                trac.setText("where does '" + statelist.get(curst) + "' go on '" + alphalist.get(cural));
            else
            {
                findViewById(R.id.transin).setVisibility(View.GONE);
                Convert();
            }
    }

    private int checkedItem = -1;
    private void displaySingleSelectionDialog() {
        states = new String[statelist.size()];
        states = statelist.toArray(states);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Select a starting state ");
        dialogBuilder.setSingleChoiceItems(states, checkedItem,
                (dialogInterface, which) -> {
                    checkedItem = which;
                });
        dialogBuilder.setPositiveButton("Done", (dialog, which) -> showSelectedVersion());
        dialogBuilder.create().show();
    }

    private void showSelectedVersion() {
        strsta.add(states[checkedItem]);
        Toast.makeText(getApplicationContext(),"Start state is "+strsta,Toast.LENGTH_SHORT).show();
        findViewById(R.id.startst).setVisibility(View.GONE);
//        findViewById(R.id.transinp).setVisibility(View.VISIBLE);
    }
    public void Convert() {
        try {
            DFAST = new ArrayList<ArrayList<String>>();
            DFAST.add(strsta);
                for(int i=0;i<DFAST.size();i++)
                    for (int j = 0; j < DFAST.get(i).size(); j++) {
                        for (int k = 0; k < statelist.size(); k++)
                            if (statelist.get(k).equals(DFAST.get(i).get(j))) {
                                ArrayList<String> temp = new ArrayList<>();
                                for (int a = 0; a < alphalist.size(); a++) {
                                    temp.add(transitions[k][a].toString());
                                }
                                DFAST.add(temp);
                                if (i == DFAST.size()) {

                                }
                                Toast.makeText(getApplicationContext(), Integer.toString(DFAST.size()), Toast.LENGTH_SHORT).show();
                            }
                    }
            for (int d = 0; d < DFAST.size(); d++) {
                for (int j = 0; j < DFAST.get(d).size(); j++)
                    Toast.makeText(getApplicationContext(), DFAST.get(d).get(j), Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}
