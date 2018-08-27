package site.ufsj.carros;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AlunoAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<Aluno> lista;

    public AlunoAdapter(Context context, ArrayList<Aluno> lista){
        this.context = context;
        this.lista = lista;
    }

    public int getCount() {
        return lista.size();
    }


    public Object getItem(int position) {
        return lista.get(position);
    }


    public long getItemId(int position) {
        return position;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        View layout;
        Aluno aluno = lista.get(position);

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = inflater.inflate(R.layout.carros, null);
        }
        else{
            layout = convertView;
        }


        TextView modelo = (TextView) layout.findViewById(R.id.t1);
        modelo.setText(aluno.getNome());

        TextView marca = (TextView) layout.findViewById(R.id.t2);
        marca.setText(aluno.getMatricula());

        if(aluno.getPresenca().contains("p")){
            layout.setBackgroundColor(Color.GREEN);
        }
        if(aluno.getPresenca().contains("f"))
        {
            layout.setBackgroundColor(Color.RED);
        }
        return layout;
    }
}
