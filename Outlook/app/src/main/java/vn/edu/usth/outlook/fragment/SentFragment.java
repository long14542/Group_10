package vn.edu.usth.outlook.fragment;

import static vn.edu.usth.outlook.activities.MainActivity.emailList;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import vn.edu.usth.outlook.Email_Sender;
import vn.edu.usth.outlook.R;
import vn.edu.usth.outlook.activities.Detail_1;
import vn.edu.usth.outlook.listener.SelectListener;
import vn.edu.usth.outlook.adapter.SentAdapter;


public class SentFragment extends Fragment implements SelectListener {

    RecyclerView recyclerView;
    SentAdapter sentAdapter;
    String userid_sender;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sent, container, false);

        recyclerView = view.findViewById(R.id.recycler_main_sent);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        emailList = new ArrayList<>();

        sentAdapter= new SentAdapter(getContext(),emailList,this);
        recyclerView.setAdapter(sentAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);


        return view;
    }
    Email_Sender deletedMail = null;
    List<String> archivedMail = new ArrayList<>();
    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            switch (direction) {
                case ItemTouchHelper.LEFT:
                    // Handle left swipe (delete)
                    deletedMail = emailList.get(position); // Get the deleted email
                    emailList.remove(position); // Remove it from the list
                   sentAdapter.notifyItemRemoved(position); // Notify the adapter

                    // Show a Snackbar with an undo option
                    Snackbar.make(recyclerView, "Email deleted", Snackbar.LENGTH_LONG)
                            .setAction("Undo", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    // User clicked "Undo," so add the deleted email back to the list
                                    if (deletedMail != null) {
                                        emailList.add(position, deletedMail);
                                        sentAdapter.notifyItemInserted(position);
                                    }
                                }
                            }).show();
                    break;
                case ItemTouchHelper.RIGHT:
                    final Email_Sender email = emailList.get(position); // Corrected variable name
                    archivedMail.add(String.valueOf(email)); // Use the correct list name
                    emailList.remove(position);
                    sentAdapter.notifyItemRemoved(position);

                    Snackbar make = Snackbar.make(recyclerView, email + ", Archived.", Snackbar.LENGTH_LONG);
                    make.setAction("Undo", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            archivedMail.remove(archivedMail.lastIndexOf(email));
                            emailList.add(position, email);
                            sentAdapter.notifyItemInserted(position);
                        }
                    });

                    make.show();

                    break;
            }

        }
    };


    @Override
    public void onItemClicked(int position) {
        Intent intent = new Intent(getContext(), Detail_1.class);
        intent.putExtra("Name", emailList.get(position).getSender());
        intent.putExtra("Head Mail", emailList.get(position).getSubject());
        intent.putExtra("Me", emailList.get(position).getReceiver());
        intent.putExtra("Content", emailList.get(position).getContent());
        intent.putExtra("position", position);
        startActivity(intent);
    }

    @Override
    public void onLongItemClick(int position) {

    }
}