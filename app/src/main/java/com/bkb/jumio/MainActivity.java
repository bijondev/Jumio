package com.bkb.jumio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.bkb.jumio.customui.NetverifyCustomActivity;
import com.google.android.material.navigation.NavigationView;
import com.jumio.MobileSDK;
import com.jumio.core.enums.JumioDataCenter;
import com.jumio.nv.NetverifySDK;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Button btnNetVerfy;

    public static final String KEY_API_TOKEN = "KEY_API_TOKEN";
    public static final String KEY_API_SECRET = "KEY_API_SECRET";
    public static final String KEY_DATACENTER = "KEY_DATACENTER";

    private static String NETVERIFY_API_TOKEN = "9b2443d0-0785-4939-ad5c-04a055311277";
    private static String NETVERIFY_API_SECRET = "QJdSWXH9WuVsoBi4hjL48QVvuM4OyOcF";
    private static JumioDataCenter NETVERIFY_DATACENTER = JumioDataCenter.US;

    /*
     * PUT YOUR BAM API TOKEN AND SECRET HERE
     * Do not store your credentials hardcoded within the app. Make sure to store them server-side and load your credentials during runtime.
     */
    private static String BAM_API_TOKEN = "";
    private static String BAM_API_SECRET = "";
    private static JumioDataCenter BAM_DATACENTER = JumioDataCenter.US;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();


        /*btnNetVerfy=findViewById(R.id.btnNetVerfy);
        btnNetVerfy.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            NetverifyFragment nvFragment = new NetverifyFragment();
            bundle.putString(KEY_API_TOKEN, NETVERIFY_API_TOKEN);
            bundle.putString(KEY_API_SECRET, NETVERIFY_API_SECRET);
            bundle.putSerializable(KEY_DATACENTER, NETVERIFY_DATACENTER);
            nvFragment.setArguments(bundle);
            switchFragment(nvFragment);
        });*/

        /*Bundle bundle = new Bundle();

        NetverifyFragment nvFragment = new NetverifyFragment();
        bundle.putString(KEY_API_TOKEN, NETVERIFY_API_TOKEN);
        bundle.putString(KEY_API_SECRET, NETVERIFY_API_SECRET);
        bundle.putSerializable(KEY_DATACENTER, NETVERIFY_DATACENTER);
        nvFragment.setArguments(bundle);
        switchFragment(nvFragment);*/
        NavigationView navigationView = findViewById(R.id.nav_view);
        if (navigationView != null) {
            Menu menu = navigationView.getMenu();
            //menu.findItem(R.id.nav_sdk).setTitle(MobileSDK.getSDKVersion());
            onNavigationItemSelected(menu.findItem(R.id.nav_netverify));
            navigationView.setNavigationItemSelectedListener(this);
            navigationView.setItemIconTintList(null);
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == NetverifySDK.REQUEST_CODE_NFC_DETECTED) {
            Fragment fragment = getSupportFragmentManager().getFragments().get(0);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Bundle bundle = new Bundle();

        switch (menuItem.getItemId()) {
            case R.id.nav_netverify:
                NetverifyFragment nvFragment = new NetverifyFragment();
                bundle.putString(KEY_API_TOKEN, NETVERIFY_API_TOKEN);
                bundle.putString(KEY_API_SECRET, NETVERIFY_API_SECRET);
                bundle.putSerializable(KEY_DATACENTER, NETVERIFY_DATACENTER);
                nvFragment.setArguments(bundle);
                switchFragment(nvFragment);
                break;
            case R.id.nav_netverify_custom:
                Intent nvCustomActivity = new Intent(getApplicationContext(), NetverifyCustomActivity.class);
                bundle.putString(KEY_API_TOKEN, NETVERIFY_API_TOKEN);
                bundle.putString(KEY_API_SECRET, NETVERIFY_API_SECRET);
                bundle.putSerializable(KEY_DATACENTER, NETVERIFY_DATACENTER);
                nvCustomActivity.putExtras(bundle);
                startActivity(nvCustomActivity);
                break;

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public boolean checkPermissions(int requestCode) {
        if (!MobileSDK.hasAllRequiredPermissions(this)) {
            //Acquire missing permissions.
            String[] mp = MobileSDK.getMissingPermissions(this);

            ActivityCompat.requestPermissions(this, mp, requestCode);
            //The result is received in onRequestPermissionsResult.
            return false;
        } else {
            return true;
        }
    }


    private void switchFragment(Fragment fragment) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commitAllowingStateLoss();
    }



}
