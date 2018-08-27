package site.ufsj.carros;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class CSVFile {

    public CSVFile(){

    }

    public List read(InputStream inputStream){
        List resultList = new ArrayList();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;
            while ((csvLine = reader.readLine()) != null) {
                String[] row = csvLine.split(",");
                resultList.add(row);
            }
        }
        catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file: "+ex);
        }
        finally {
            try {
                inputStream.close();
            }
            catch (IOException e) {
                throw new RuntimeException("Error while closing input stream: "+e);
            }
        }
        return resultList;
    }

    public void write(ArrayList<Aluno> alunos, Context context)
    {
        try {
            OutputStreamWriter outputStream;
            outputStream = new OutputStreamWriter(context.openFileOutput("aulaAndroid.csv", Context.MODE_PRIVATE));
            for(int i=0; i< alunos.size(); i++)
            {
                outputStream.write(alunos.get(i).getMatricula());
                outputStream.write(alunos.get(i).getNome());
                outputStream.write(alunos.get(i).getPresenca());
                outputStream.write("\n");
            }
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

