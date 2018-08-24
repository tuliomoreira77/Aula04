package site.ufsj.carros;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.InputStream;
import java.util.HashMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class MainActivity extends Activity {

    Vector<Boolean> presenca = new Vector<Boolean>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InputStream inputStream = getResources().openRawResource(R.raw.alunos);
        CSVFile csvFile = new CSVFile(inputStream);
        List<String[]> alunosList = csvFile.read();

        ArrayList<HashMap<String, String>> alunos = new ArrayList<HashMap<String, String>>();

        for(int i = 0; i < alunosList.size(); i++){

            HashMap<String, String> aluno = new HashMap<String, String>();
            aluno.put("Aluno", alunosList.get(i)[1]);
            aluno.put("Matricula", alunosList.get(i)[0]);
            presenca.add(true);
            alunos.add(aluno);
        }

        String[] from = new String[]{"Aluno", "Matricula"};

        int layout = R.layout.carros;

        int[] to = new int[]{R.id.t1, R.id.t2};

        ListView lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(new SimpleAdapter(this, alunos, layout, from, to));
        lv.setOnItemClickListener(mudaCor());
    }

    public AdapterView.OnItemClickListener mudaCor(){
        return(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> av, View v, int position, long id) {
                Intent intent;

                switch(position){
                    default:
                        if(presenca.get(position)) {
                            v.setBackgroundColor(Color.GREEN);
                            presenca.set(position, false);
                        }
                        else {
                            v.setBackgroundColor(Color.RED);
                            presenca.set(position, true);
                        }
                        break;
                }
            }

        });
    }


    public void sair(View view){
        finish();
    }
}
}
