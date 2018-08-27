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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class MainActivity extends Activity {

    ArrayList<Aluno> alunos;
    CSVFile csvFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File file = new File("aulaAndroid.csv");
        if(!file.exists())
        {
            InputStream inputStream = getResources().openRawResource(R.raw.alunos);
            csvFile = new CSVFile();
            List<String[]> alunosList = csvFile.read(inputStream);

            alunos = new ArrayList<Aluno>();

            for(int i = 0; i < alunosList.size(); i++){

                Aluno aluno = new Aluno();
                aluno.setNome(alunosList.get(i)[1]);
                aluno.setMatricula(alunosList.get(i)[0]);
                aluno.setPresenca(alunosList.get(i)[2]);
                alunos.add(aluno);
            }
            csvFile.write(alunos,this);
        }

        else
        {
            try {
                InputStream inputStream = this.openFileInput("aulaAndroid.csv");
                List<String[]> alunosList = csvFile.read(inputStream);

                alunos = new ArrayList<Aluno>();

                for(int i = 0; i < alunosList.size(); i++){

                    Aluno aluno = new Aluno();
                    aluno.setNome(alunosList.get(i)[1]);
                    aluno.setMatricula(alunosList.get(i)[0]);
                    aluno.setPresenca(alunosList.get(i)[2]);
                    alunos.add(aluno);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        ListView lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(new AlunoAdapter(this,alunos));
        lv.setOnItemClickListener(mudaCor());


    }

    public AdapterView.OnItemClickListener mudaCor(){
        return(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> av, View v, int position, long id) {
                Intent intent;

                switch(position){
                    default:
                        if(alunos.get(position).getPresenca().contains("f")) {
                            v.setBackgroundColor(Color.GREEN);
                            alunos.get(position).setPresenca("p");
                        }
                        else {
                            v.setBackgroundColor(Color.RED);
                            alunos.get(position).setPresenca("f");
                        }
                        break;
                }
            }

        });
    }


    public void onFinish(View view){
        csvFile.write(alunos,this);
        finish();
    }
}