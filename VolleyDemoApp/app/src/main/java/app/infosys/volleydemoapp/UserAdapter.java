package app.infosys.volleydemoapp;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import app.infosys.volleydemoapp.Models.UserModel;

/**
 * Created by GrimReaper on 3/7/2017.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private LayoutInflater mlayoutInflater;
    private List<UserModel> listData;
    Context context;//for the EmptyNUllPointerException
//    List<TeacherDataMdl> data; = Collections.emptyList();  //for the EmptyNUllPointerException


    //    my custom Constructor to get Filled
    public UserAdapter(Context context, List<UserModel> listData) {
        this.mlayoutInflater = LayoutInflater.from(context);
        this.listData = listData;
        this.context = context;
    }

    //    the interface for the clickLisnter
    private ExpenseItemClickCallback itemClickCallback;

    //the interface
    //an interface for making this recyclerView ClickAble
    public interface ExpenseItemClickCallback {
        void onItemClick(int p);

        void onSecondaryIconClick(int p);
    }

    public void setItemClickCallback(final ExpenseItemClickCallback itemClickCallback) {
        this.itemClickCallback = itemClickCallback;
    }


    @Override
    public UserAdapter.UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mlayoutInflater.inflate(R.layout.recyc_item_layout, parent, false);
        //by doing this it will use that findViewByID word only once and we will get optimized RecyclerView
        return new UserAdapter.UserViewHolder((view));
    }

    @Override
    public void onBindViewHolder(UserAdapter.UserViewHolder holder, int position) {
        //now all incoming data from the server needed to be
        //tightly bound here on the recycler View will be here
        UserModel item = listData.get(position);
        holder.nameTV.setText(item.getName());

        holder.PhoneTV.setText(item.getPhone());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    //the custom class for the View holder in the android :-) for my own view to get bind
    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView nameTV, PhoneTV;


        public UserViewHolder(View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.nameTV);
            PhoneTV = itemView.findViewById(R.id.PhoneTV);


        }


    }
}
