package tech.android.tcmp13.fullfragmentdemo.main.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import tech.android.tcmp13.fullfragmentdemo.R;
import tech.android.tcmp13.fullfragmentdemo.employees.model.Employee;
import tech.android.tcmp13.fullfragmentdemo.equipments.model.Equipment;
import tech.android.tcmp13.fullfragmentdemo.main.control.MainViewPagerAdapter;
import tech.android.tcmp13.fullfragmentdemo.main.model.InputType;

public class MainActivity extends AppCompatActivity implements AddDialog.AddListener{

    private TabLayout tabLayout;
    private MainViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupMainViewPager();
    }

    private void setupMainViewPager() {

        //Find the pager and set with adapter
        ViewPager mainViewPager = (ViewPager) findViewById(R.id.mainViewPager);
        //Since ViewPager is from the support library, even though we state minSDK 17, we have
        //to work with the support library for full compatibility with the ViewPager and its adapter
        adapter = new MainViewPagerAdapter(this, getSupportFragmentManager());
        mainViewPager.setAdapter(adapter);
        initAndAttachTabLayout(mainViewPager);
    }

    @SuppressWarnings("ConstantConditions")
    private void initAndAttachTabLayout(ViewPager mainViewPager) {

        //Find and set the tab layout
        tabLayout = (TabLayout) findViewById(R.id.mainTabLayout);
        //From here on the tab layout and the view pager are in sync
        tabLayout.setupWithViewPager(mainViewPager);
        tabLayout.getTabAt(0).setIcon(android.R.drawable.ic_menu_add);
        tabLayout.getTabAt(1).setIcon(android.R.drawable.ic_menu_preferences);
        tabLayout.getTabAt(2).setIcon(android.R.drawable.ic_menu_agenda);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.addActionItem) {

            InputType activeInputType = getCurrentActiveInputType();
            if (activeInputType == null)
                return false;
            AddDialog dialog = AddDialog.newInstance(activeInputType);
            dialog.setAddListener(this);
            dialog.show(getSupportFragmentManager(), "addDialog");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private InputType getCurrentActiveInputType() {

        return InputType.fromRawValue(tabLayout.getSelectedTabPosition());
    }

    /**
     * The callback for the dialog to break the circle of dependency.
     * @param o the object to add
     */
    @Override
    public void add(Object o) {

        InputType activeInputType = getCurrentActiveInputType();
        adapter.add(o, activeInputType);
    }
}
