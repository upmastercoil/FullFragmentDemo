package tech.android.tcmp13.fullfragmentdemo.main.control;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import tech.android.tcmp13.fullfragmentdemo.R;
import tech.android.tcmp13.fullfragmentdemo.employees.model.Employee;
import tech.android.tcmp13.fullfragmentdemo.employees.ui.EmployeesFragment;
import tech.android.tcmp13.fullfragmentdemo.equipments.model.Equipment;
import tech.android.tcmp13.fullfragmentdemo.equipments.ui.EquipmentsFragment;
import tech.android.tcmp13.fullfragmentdemo.main.model.InputType;
import tech.android.tcmp13.fullfragmentdemo.todo.control.MarkAsTodoListener;
import tech.android.tcmp13.fullfragmentdemo.todo.ui.ToDoFragment;

import static tech.android.tcmp13.fullfragmentdemo.main.model.MainViewPagerAdapterConstants.*;

/**
 * Pager adapter can be FragmentPagerAdapter for a finite list of items (fragments)
 * or FragmentStatePagerAdapter for infinite one.
 * <p>
 * Created by tcmp13-t on 12/21/2016.
 */
public class MainViewPagerAdapter extends FragmentPagerAdapter implements MarkAsTodoListener {

    private Context context;
    private Fragment[] pages;

    public MainViewPagerAdapter(Context context, FragmentManager fm) {

        super(fm);
        this.context = context;
        pages = new Fragment[PAGES_COUNT];
        EmployeesFragment employeesFragment = new EmployeesFragment();
        employeesFragment.setMarkAsTodoListener(this);
        pages[EMPLOYEE_PAGE_INDEX] = employeesFragment;
        EquipmentsFragment equipmentsFragment = new EquipmentsFragment();
        equipmentsFragment.setMarkAsTodoListener(this);
        pages[EQUIPMENT_PAGE_INDEX] = equipmentsFragment;
        pages[TODO_PAGE_INDEX] = new ToDoFragment();
    }

    @Override
    public Fragment getItem(int position) {

        //DON'T PANIC - no recycling here
        //We init fragment with static factory method only when it's necessary to pass data to
        //the fragment. If non is needed, use the def constructor.
        return pages[position];
    }

    @Override
    public CharSequence getPageTitle(int position) {

        //Save the titles as String ResId and call the context.getString to fetch the actual string.
        int result = -1;
        switch (position) {
            case EMPLOYEE_PAGE_INDEX:
                result = R.string.employees;
                break;
            case EQUIPMENT_PAGE_INDEX:
                result = R.string.equipments;
                break;
            case TODO_PAGE_INDEX:
                result = R.string.todo;
                break;
        }
        return context.getString(result);
    }

    @Override
    public int getCount() {

        return PAGES_COUNT;
    }

    /**
     * Receive a request to add object to one of the pages, delegate to the appropriate page.
     *
     * @param o         the object to add
     * @param inputType the page to delegate the object to
     */
    public void add(Object o, InputType inputType) {

        if (inputType == InputType.EMPLOYEE)
            ((EmployeesFragment) pages[EMPLOYEE_PAGE_INDEX]).addEmployee((Employee) o);
        else
            ((EquipmentsFragment) pages[EQUIPMENT_PAGE_INDEX]).addEquipment((Equipment) o);
    }

    @Override
    public void markAsToDo(Object o) {

        ((ToDoFragment) pages[TODO_PAGE_INDEX]).add(o);
    }
}
