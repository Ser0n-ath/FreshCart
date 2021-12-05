package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class





SingleProductPage extends AppCompatActivity {
    int count = 0;
    FloatingActionButton add;
    FloatingActionButton minus;
    TextView incrementdecrement;
    Button addtocart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_product_page);
        String productimage = "";
        String productname = "";
        String productprice = "";
        String productdescription = "";
        String productid = "";


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            productimage = extras.getString("mImages");
            productname = extras.getString("mImageNames");
            productprice = "$" + extras.getString("mPrice");
            productdescription = extras.getString("mDesc");
            productid = extras.getString("mId");
        }

        TextView tv = (TextView) findViewById(R.id.ProductNameText);
        tv.setText(productname);
        TextView tv1 = (TextView) findViewById(R.id.productnameinfo);
        tv1.setText(productname);
        TextView tv2 = (TextView) findViewById(R.id.productdescriptioninfo);
        tv2.setText(productdescription);
        TextView tv3 = (TextView) findViewById(R.id.productpriceinfo);
        tv3.setText(productprice);
        incrementdecrement = (TextView) findViewById(R.id.itemcounter);
        incrementdecrement.setText(String.valueOf(count));
        ImageView iv = (ImageView) findViewById(R.id.productpictureiv);
        Glide.with(this)
                .load(productimage)
                .placeholder(R.drawable.cartlogo)
                .dontAnimate()
                .into(iv);


        add = (FloatingActionButton) findViewById((R.id.additembutton));
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = count + 1;
                incrementdecrement.setText(String.valueOf(count));
            }


        });
        minus = (FloatingActionButton) findViewById((R.id.minusitembutton));
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count != 0) {
                    count = count - 1;
                    incrementdecrement.setText(String.valueOf(count));
                }
            }
        });
        addtocart = (Button) findViewById(R.id.addtocartbutton);
        String finalproductid = productid;
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(SingleProductPage.this, "Items added to cart\nDon't forget to place order", Toast.LENGTH_SHORT);
                TextView vt = (TextView) toast.getView().findViewById(android.R.id.message);
                if( vt != null) vt.setGravity(Gravity.CENTER);
                toast.show();
                //Toast.makeText(SingleProductPage.this, "Items added to cart\nDon't Forget to place order", Toast.LENGTH_SHORT).show();
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = user.getUid();
                DatabaseReference ref = FirebaseDatabase.getInstance("https://b07project-39fda-default-rtdb.firebaseio.com/").getReference();
                ref.child("Users").child(uid).child("cart").child(String.valueOf(finalproductid)).setValue(count);
                Intent intent = new Intent(SingleProductPage.this, PInfo.class);
                startActivity(intent);

            }
        });
    }
}
