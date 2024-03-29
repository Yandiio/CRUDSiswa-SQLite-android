package id.sch.bdg.smkn4.pwpb.datasiswa.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

import id.sch.bdg.smkn4.pwpb.datasiswa.Model.Student;
import id.sch.bdg.smkn4.pwpb.datasiswa.R;

public class  RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.UserViewHolder> {
    Context context;
    OnUserClickListener listener;
    List<Student> listPersonInfo;

    public RecyclerviewAdapter(Context context, List<Student> listPersonInfo, OnUserClickListener listener) {
        this.context = context;
        this.listPersonInfo = listPersonInfo;
        this.listener = listener;
    }

    public interface OnUserClickListener{
        void onUserClick(Student currentPerson, String action);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_item_layout,parent,false);
        UserViewHolder userViewHolder = new UserViewHolder(view);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, final int position) {
        final Student currentPerson = listPersonInfo.get(position);
        holder.txtName.setText(currentPerson.getName());
        holder.txtName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choice(currentPerson);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPersonInfo.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView txtName;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.tvName);
        }
    }

    public void choice(final Student person) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("Pilihan");
        CharSequence[] items = {"Lihat Data", "Update Data", "Hapus Data"};
        alertDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0 :
                        viewData(person);
                        break;
                    case 1 :
                        updateData(person);
                        break;
                    case 2 :
                        deleteData(person);
                        break;
                }
            }
        });
        alertDialog.show();
    }

    private void updateData(Student currentPerson) {
        listener.onUserClick(currentPerson,"Edit");
    }

    private void viewData(Student currentPerson) {
        listener.onUserClick(currentPerson,"View");
    }

    private void deleteData(Student currentPerson) {
        listener.onUserClick(currentPerson,"Delete");
    }
}
