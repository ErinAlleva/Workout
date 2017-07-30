package comeaa.workout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class OpenWorkout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_workout);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
    }

    public void ChestClick(View view) {
        Intent chestIntent = new Intent(this, Chest.class);
        startActivity(chestIntent);
    }

    public void BackClick(View view) {
        Intent backIntent = new Intent(this, Back.class);
        startActivity(backIntent);
    }

    public void BicepsTricepsClick(View view){
        Intent bicepsTricepsIntent = new Intent (this, BicepsTriceps.class);
        startActivity(bicepsTricepsIntent);
    }

    public void FullBodyClick(View view){
        Intent fullBodyIntent = new Intent(this, FullBody.class);
        startActivity(fullBodyIntent);
    }

    public void LegsClick(View view){
        Intent legIntent = new Intent(this, Legs.class);
        startActivity(legIntent);
    }

    public void ShouldersClick(View view){
        Intent shouldersIntent = new Intent(this, Shoulders.class);
            startActivity(shouldersIntent);
    }

}
