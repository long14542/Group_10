package vn.edu.usth.outlook.activities;



import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;
import android.content.Intent;
import vn.edu.usth.outlook.R;
import vn.edu.usth.outlook.databinding.ActivityComposeBinding;

public class ComposeActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    private ActivityComposeBinding binding;
    private String userid_sender;
    private String userid_receiver;
    String emailKeySender;
    String emailKeyReceiver;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Change status bar background color to the corresponding color
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.background_all));

        binding = ActivityComposeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String content = binding.txtContent.getText().toString();
                String subject = binding.txtSubject.getText().toString();
                String receiver = binding.txtReceiver.getText().toString();
                String sender = binding.txtSender.getText().toString();





                }

        });

        // Handle back button click
        binding.backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to navigate to the previous activity or fragment
                Intent intent = new Intent(ComposeActivity.this, MainActivity.class);
                startActivity(intent);

                // Optionally, finish the current activity to remove it from the stack
                finish();
            }
        });

        // Add click listeners for moreBtn and linkBtn as needed
        // For example:
        binding.moreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ComposeActivity.this, "You clicked on the moreBtn", Toast.LENGTH_SHORT).show();
            }
        });

        ImageButton popupButton3 = findViewById(R.id.moreBtn);
        popupButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moreBtnPopup(view);
            }
        });

        binding.linkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ComposeActivity.this, "You clicked on the linkBtn", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void moreBtnPopup(View view){
        PopupMenu popup = new PopupMenu(this, view);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.more_compose);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {

        return false;
    }
}