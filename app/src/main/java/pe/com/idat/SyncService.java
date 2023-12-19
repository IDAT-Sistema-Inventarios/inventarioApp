package pe.com.idat;
import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import pe.com.idat.bd.BD;
/*
public class SyncService extends IntentService {

    private static final String TAG = "SyncService";
    private FirebaseAuth mAuth;

    public SyncService() {
        super("SyncService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        mAuth = FirebaseAuth.getInstance();

        syncLocalDataWithFirebase();
    }

    private void syncLocalDataWithFirebase() {
        NetworkUtils networkUtils = new NetworkUtils(getApplicationContext());

        if (networkUtils.isNetworkAvailable()) {
            List<Usuario> localUsers = BD.appDatabase.daoUsuario().getAllUsers();

            for (Usuario localUser : localUsers) {
                sendUserToFirestore(localUser);
            }

            BD.appDatabase.daoUsuario().deleteAllUsers();
        }
    }

    private void sendUserToFirestore(Usuario localUser) {
        mAuth.signInWithEmailAndPassword(localUser.getCorreo(), localUser.getPassword())
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser firebaseUser = mAuth.getCurrentUser();
                        // Implementa la lógica para enviar localUser a Firestore
                        // Ejemplo:
                        //FirebaseFirestore db = FirebaseFirestore.getInstance();
                       // db.collection("usuarios").document(firebaseUser.getUid()).set(localUser);
                    } else {
                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                    }
                });
    }
}
*/