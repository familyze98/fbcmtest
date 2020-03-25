package hu.feherke.fbcmtest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MyFirebaseMessagingService";
    public static final String DEF_NOTIFICATION_CHANNEL_NAME="teszt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener( MainActivity.this,  new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String newToken = instanceIdResult.getToken();
                Log.d(TAG,"newToken: "+newToken);

            }
        });

        // W/FirebaseMessaging: Format /topics/topic-name is deprecated. Only 'topic-name' should be used in subscribeToTopic.
        FirebaseMessaging.getInstance().subscribeToTopic(DEF_NOTIFICATION_CHANNEL_NAME);



        if (getIntent().getExtras() != null) {
            Log.d(TAG, "Message data payload (MainActivity): ");
            Log.d(TAG, "************************************************************************");
            for (String key : getIntent().getExtras().keySet()) {
                Object value = getIntent().getExtras().get(key);
                Log.d(TAG, "Key: " + key + " Value: " + value);
            }
        }

    }

}
