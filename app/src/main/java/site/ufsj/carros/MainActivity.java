package site.ufsj.carros;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

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

        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if(permissionCheck != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
        }

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

        Spinner sp = (Spinner) findViewById(R.id.sp);
        sp.setAdapter(new AlunoAdapter(this,alunos));
        //sp.setOnItemClickListener(mudaCor());


    }

    /*public AdapterView.OnItemClickListener mudaCor(){
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


    public void onFinish(){
        csvFile.write(alunos,this);
        finish();
    }*/
}