package com.example.shrut.myapk;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    private Session session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        session=new Session(Main3Activity.this);



        if(!session.loggedin()){
            logout();
        }
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer=findViewById(R.id.drawer_layout);
        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();




        if(savedInstanceState==null) {
            getSupportActionBar().setTitle("Chat");
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                   new Chatclass()).commit();
            navigationView.setCheckedItem(R.id.chat_id);



        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater menuInflater=getMenuInflater();
//        menuInflater.inflate(R.menu.main_menu,menu);
//
//
//        return super.onCreateOptionsMenu(menu);
//    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.profile_id:
                getSupportActionBar().setTitle("Profile");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Profileclass()).commit();

                break;

            case R.id.chat_id:
                getSupportActionBar().setTitle("Chat");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                       new Chatclass()).commit();
                break;

            case R.id.friends_id:
                getSupportActionBar().setTitle("Friends");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Friendsclass()).commit();
                break;

            case R.id.camera_id:

                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);
               // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                 //       new Cameraclass()).commit();
                break;

            case R.id.logout_id:
                  logout();
                  break;


            case R.id.delete_id:
             //   delete();
               break;

            case R.id.share_id:
                Toast.makeText(this,"share",Toast.LENGTH_SHORT).show();

                break;


        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else{

            super.onBackPressed();

        }
    }


    public void logout(){
        session.setLoggedin(false);
        Intent intent = new Intent(Main3Activity.this, MainActivity.class);
        startActivity(intent);
        finish();

    }


   /* public void onPress(){
        btn=(Button)findViewById(R.id.btn_back);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.app.FragmentTransaction ft=getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container,new Chatclass());
                //Intent it=new Intent(Main3Activity.this,Chatclass.class);
                //startActivity(it);
            }
        });
    }*/


    }


