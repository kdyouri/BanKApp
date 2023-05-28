package ma.ensa.bankapp;

import android.app.Activity;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowCloseListener {

    MapView mapView;

    GoogleMap mMap;

    ArrayList<Agence> agences = new ArrayList<Agence>();
    Agence activeAgence;

    ImageButton btnSearch;
    Button btnAppeler;
    Button btnSms;
    Button btnEmail;
    EditText txtSearch;
    BottomNavigationView navActions;

    final String NUM_TEL_CENTRE_APPEL = "+212522445874";
    final String NUM_TEL_SMS = "+212522445874";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapView = findViewById(R.id.mapView);

        if (_checkGoogleApiService()) {
            mapView.getMapAsync(this);
            mapView.onCreate(savedInstanceState);
        } else {
            Toast.makeText(this, "Google Maps API indisponible !", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean _checkGoogleApiService() {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int result = googleApiAvailability.isGooglePlayServicesAvailable(this);
        if (result == ConnectionResult.SUCCESS) {
            return true;
        } else if (googleApiAvailability.isUserResolvableError(result)) {
            Dialog dialog = googleApiAvailability.getErrorDialog(this, result, 201, new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    Toast.makeText(MainActivity.this, "Annulé par utilisateur", Toast.LENGTH_SHORT).show();
                }
            });
            dialog.show();
        }
        return false;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(34.266958,-6.6209796), 13));
        mMap.setOnMarkerClickListener(this);
        mMap.setOnInfoWindowCloseListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();

        // Les coordonées sont réels (Banque Populaire Kénitra) :
        agences.add(new Agence(34.250322817615945, -6.5437958025535075, "Karim Bassou", "Rue 1, Bloc 14, Avenue RIAD - Kénitra", "0662548796", "ag013@bp.ma"));
        agences.add(new Agence(34.25430371432982, -6.551346307348957, "Rachid Maoui", "Rue 1, Bloc 14, Avenue RIAD - Kénitra", "0686247965", "ag014@bp.ma"));
        agences.add(new Agence(34.248913257379996, -6.55324270494237, "Abdou Ouaaziz", "Rue 1, Bloc 14, Avenue RIAD - Kénitra", "0696254678", "ag015@bp.ma"));
        agences.add(new Agence(34.27077313486693, -6.561113063676484, "Nihal Belaouni", "Rue 1, Bloc 14, Avenue RIAD - Kénitra", "0669625748", "ag016@bp.ma"));
        agences.add(new Agence(34.26737199530101, -6.56644163869842, "Mourad Moutaouakil", "Rue 1, Bloc 14, Avenue RIAD - Kénitra", "0687496256", "ag017@bp.ma"));
        agences.add(new Agence(34.266242382892756, -6.5734841603245435, "Mohamed Rifaai", "Rue 1, Bloc 14, Avenue RIAD - Kénitra", "0792546686", "ag018@bp.ma"));
        agences.add(new Agence(34.26596353033224, -6.580181816590645, "Anouar Nejar", "Rue 1, Bloc 14, Avenue RIAD - Kénitra", "0646879625", "ag019@bp.ma"));
        agences.add(new Agence(34.25730924993145, -6.581908191739507, "Maha Azzouzi", "Rue 1, Bloc 14, Avenue RIAD - Kénitra", "0666254879", "ag020@bp.ma"));
        agences.add(new Agence(34.263697036705416, -6.58567918529465, "Amal Belabbass", "Rue 1, Bloc 14, Avenue RIAD - Kénitra", "0679662548", "ag021@bp.ma"));
        agences.add(new Agence(34.263980706208564, -6.585507182743837, "Redouan Rami", "Rue 1, Bloc 14, Avenue RIAD - Kénitra", "0648759662", "ag022@bp.ma"));
        agences.add(new Agence(34.26370038886454, -6.590830510700352, "Kawtar Tazi", "Rue 1, Bloc 14, Avenue RIAD - Kénitra", "0664829657", "ag023@bp.ma"));
        agences.add(new Agence(34.25746068300744, -6.596502309244192, "Ayman Assadi", "Rue 1, Bloc 14, Avenue RIAD - Kénitra", "0668257964", "ag024@bp.ma"));
        agences.add(new Agence(34.26157697212063, -6.599074178959454, "Rahima Mebrouk", "Rue 1, Bloc 14, Avenue RIAD - Kénitra", "0648796625", "ag025@bp.ma"));
        agences.add(new Agence(34.272503607533, -6.601125388932584, "Ahmed Benabbou", "Rue 1, Bloc 14, Avenue RIAD - Kénitra", "0648796256", "ag026@bp.ma"));
        agences.add(new Agence(34.27194092163074, -6.61091358735116, "Aziz Benabbou", "Rue 1, Bloc 14, Avenue RIAD - Kénitra", "0664879625", "ag027@bp.ma"));
        agences.add(new Agence(34.2679695333898, -6.614178794161601, "Karim Radi", "Rue 1, Bloc 14, Avenue RIAD - Kénitra", "0648796256", "ag028@bp.ma"));
        agences.add(new Agence(34.251508743909476, -6.610069719418954, "Aicha Berrada", "Rue 1, Bloc 14, Avenue RIAD - Kénitra", "0795466286", "ag029@bp.ma"));
        agences.add(new Agence(34.26867911907437, -6.6145217154285, "Kanza Kennani", "Rue 1, Bloc 14, Avenue RIAD - Kénitra", "0665926487", "ag030@bp.ma"));
        agences.add(new Agence(34.26428265256294, -6.6193320362487675, "Marouan Kettani", "Rue 1, Bloc 14, Avenue RIAD - Kénitra", "0676259648", "ag031@bp.ma"));
        agences.add(new Agence(34.25761471916932, -6.621052836860347, "Mohamed Gharib", "Rue 1, Bloc 14, Avenue RIAD - Kénitra", "0665628794", "ag032@bp.ma"));
        agences.add(new Agence(34.26883024987862, -6.643194727951678, "Hicham Kettani", "Rue 1, Bloc 14, Avenue RIAD - Kénitra", "0668792546", "ag033@bp.ma"));
        agences.add(new Agence(34.25861572330982, -6.648691384463121, "Meriam Tazi", "Rue 1, Bloc 14, Avenue RIAD - Kénitra", "0669254876", "ag034@bp.ma"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();

        txtSearch = findViewById(R.id.txtSearch);
        btnSearch = findViewById(R.id.btnSearch);
        btnAppeler = findViewById(R.id.btnAppeler);
        btnSms = findViewById(R.id.btnSms);
        btnEmail = findViewById(R.id.btnEmail);
        navActions = findViewById(R.id.navActions);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Agence agence : agences) {
                    Marker marker = mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(agence.getLatitude(), agence.getLongitude()))
                            .title(agence.getAdresse())
                            .snippet("Resp. : " + agence.getResponsable() +
                                    " (Tél. : " + agence.getTelephone() + ")")
                    );
                    agence.setMarker(marker);
                }
            }
        });
        btnAppeler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + NUM_TEL_CENTRE_APPEL));
                startActivity(intent);
            }
        });
        btnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = NUM_TEL_SMS;
                String message = "bonjour";

                try{
                    SmsManager sms = SmsManager.getDefault();
                    sms.sendTextMessage(phoneNumber, null, message, null, null);
                    Toast.makeText(MainActivity.this, "SMS envoyé avec succès.", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, "SMS n'a pas pu être envoyé !", Toast.LENGTH_SHORT).show();
                }

//                Intent intent = new Intent(Intent.ACTION_SENDTO);
//                intent.setDataAndType(Uri.parse("smsto:" + phoneNumber), "vnd.android-dir/mms-sms");
//                intent.putExtra("sms_body", message);
//                startActivity(intent);

//                SmsManager sms = SmsManager.getDefault();
//                PendingIntent intent = PendingIntent.getBroadcast(MainActivity.this, 0, new Intent("SMS_SENT"), 0);
//                sms.sendTextMessage(phoneNumber, null, message, intent, null);
            }
        });
        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = activeAgence.getEmail();
                String subject = "Réclamation";
                String body = "Bonjour Mm/Mr " + activeAgence.getResponsable() +
                        ". Je vous envoi ce texto pour vous exprimer mon souci du retard de mon dossier de crédit, " +
                        "déposé auprès de votre agence il y a environs 90 jours.";
                String chooserTitle = "Envoyer Email";

//                Uri uri = Uri.parse("mailto:" + email)
//                        .buildUpon()
//                        .appendQueryParameter("subject", subject)
//                        .appendQueryParameter("body", body)
//                        .build();
//                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
//                startActivity(Intent.createChooser(intent, chooserTitle));

//                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + email));
//                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
//                intent.putExtra(Intent.EXTRA_TEXT, body);
//                startActivity(Intent.createChooser(intent, chooserTitle));

                ShareCompat.IntentBuilder.from(MainActivity.this)
                        .setType("message/rfc822")
                        .addEmailTo(email)
                        .setSubject(subject)
                        .setText(body)
                        .setChooserTitle(chooserTitle)
                        .startChooser();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        for (Agence agence: agences) {
            if (agence.getMarker().equals(marker)) {
                activeAgence = agence;
                break;
            }
        }
        navActions.setVisibility(View.VISIBLE);
        return false;
    }

    @Override
    public void onInfoWindowClose(@NonNull Marker marker) {
        navActions.setVisibility(View.INVISIBLE);
    }
}