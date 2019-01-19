package com.example.lolipop.swipetodelete;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerItemTouchHelper.RecycleItemTouchHelperListener{


    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private List<Item> cartList;
    private CartListAdapter mAdapter;
    private CoordinatorLayout coordinatorLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.my_cart));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = findViewById(R.id.recycler_view);
        coordinatorLayout = findViewById(R.id.coordinator_layout);
        cartList = new ArrayList<>();
        cartList.add(new Item(3  , "pavel" , "i am a boy" , 23 , "https://i.imgur.com/sEQldDB.jpg"));
        cartList.add(new Item(3  , "pavel" , "i am a boy" , 23 , "https://i.imgur.com/sEQldDB.jpg"));
        cartList.add(new Item(3  , "pavel" , "i am a boy" , 23 , "https://i.imgur.com/sEQldDB.jpg"));
        cartList.add(new Item(3  , "pavel" , "i am a boy" , 23 , "https://i.imgur.com/sEQldDB.jpg"));
        cartList.add(new Item(3  , "pavel" , "i am a boy" , 23 , "https://i.imgur.com/sEQldDB.jpg"));
        cartList.add(new Item(3  , "pavel" , "i am a boy" , 23 , "https://i.imgur.com/sEQldDB.jpg"));
        cartList.add(new Item(3  , "pavel" , "i am a boy" , 23 , "https://i.imgur.com/sEQldDB.jpg"));
        cartList.add(new Item(3  , "pavel" , "i am a boy" , 23 , "https://i.imgur.com/sEQldDB.jpg"));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this , DividerItemDecoration.VERTICAL));
        mAdapter = new CartListAdapter(getApplicationContext() , cartList);
        recyclerView.setAdapter(mAdapter);


        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0 , ItemTouchHelper.LEFT , this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof  CartListAdapter.MyViewHolder){
            String name = ""+cartList.get(viewHolder.getAdapterPosition());
            final Item deleteItem = cartList.get(viewHolder.getAdapterPosition());
            final int deleteIdex = viewHolder.getAdapterPosition();

            // remove the item from recycle view
            mAdapter.removeItem(viewHolder.getAdapterPosition());

            // showing snake bar with undo option

            Snackbar snackbar = Snackbar.make(coordinatorLayout , name+"removed from cart" , Snackbar.LENGTH_SHORT);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    // when undo is selected

                    mAdapter.restoreItem(deleteItem , deleteIdex);
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    }
}
