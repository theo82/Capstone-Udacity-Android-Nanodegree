package theo.tziomakas.news;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import theo.tziomakas.news.model.Comment;


public class DisplayComments extends AppCompatActivity {

    ArrayList<Comment> commentArrayList;
    Toolbar mToolbar;
    private DatabaseReference mDatabase;
    private Intent i;
    private String newsTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_comments);

        mToolbar = (Toolbar) findViewById(R.id.display_comments_app_bar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Display Comments");

        mDatabase = FirebaseDatabase.getInstance().getReference();

        commentArrayList = new ArrayList<>();

        i = getIntent();

        newsTitle = i.getStringExtra("newsTitle");

        mDatabase = FirebaseDatabase.getInstance().getReference();

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        mDatabase = FirebaseDatabase.getInstance().getReference();

        Query query = mDatabase.child("comments").child("newsTitle").equalTo(newsTitle);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    Toast.makeText(DisplayComments.this,"There are comments posted",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(DisplayComments.this,"There are no comments posted",Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
