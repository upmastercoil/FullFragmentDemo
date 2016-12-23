package tech.android.tcmp13.fullfragmentdemo.main.ui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;

import tech.android.tcmp13.fullfragmentdemo.R;
import tech.android.tcmp13.fullfragmentdemo.employees.model.Employee;
import tech.android.tcmp13.fullfragmentdemo.equipments.model.Equipment;
import tech.android.tcmp13.fullfragmentdemo.main.model.InputType;

/**
 * Created by tcmp13-t on 12/21/2016.
 */
public class AddDialog extends DialogFragment {

    private static final String INPUT_TYPE_KEY = "input_type_key";

    private AddListener addListener;

    /**
     * To pass arguments to a fragment, create a factory method with those args,
     * then pack them in a bundle and set as the arguments of the fragment
     *
     * @param inputType the input type of this dialog
     * @return the dialog with the appropriate values.
     */
    public static AddDialog newInstance(InputType inputType) {

        AddDialog dialog = new AddDialog();

        Bundle args = new Bundle();
        args.putInt(INPUT_TYPE_KEY, inputType.getRawValue());

        dialog.setArguments(args);
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //Fetch the input type from
        InputType inputType = InputType.fromRawValue(getArguments().getInt(INPUT_TYPE_KEY));

        //The parent is unknown at the moment, but the dialog fragment will know how to resolve the
        //parenthood issue by itself.
        @SuppressLint("InflateParams")
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add, null);

        //The alert dialog needs context to be drawn, however as it is a fragment, it needs the
        //activity to be drawn into, so pass getActivity() not getContext() ~ not an error....
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(contentView);
        if (inputType == InputType.EMPLOYEE)
            return buildAddEmployeeDialog(builder, contentView);
        else
            return buildAddEquipmentDialog(builder, contentView);
    }

    private Dialog buildAddEmployeeDialog(AlertDialog.Builder builder, View contentView) {

        builder.setTitle(R.string.add_employee_title);
        final TextInputEditText firstInputField = (TextInputEditText) contentView.findViewById(R.id.firstFieldInputEditText);
        final TextInputEditText secondInputField = (TextInputEditText) contentView.findViewById(R.id.secondFieldInputEditText);
        firstInputField.setHint(R.string.first_input_employee_name);
        secondInputField.setHint(R.string.second_input_employee_department);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if (addListener == null) {
                    dismiss();
                    return;
                }
                //There is a listener, parse the input and make an employee
                String name = firstInputField.getText().toString();
                String department = secondInputField.getText().toString();
                addListener.add(new Employee(name, department));
                dismiss();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                onCancel(dialogInterface);
            }
        });
        return builder.create();
    }

    private Dialog buildAddEquipmentDialog(AlertDialog.Builder builder, View contentView) {

        builder.setTitle(R.string.add_equipment_title);
        final TextInputEditText firstInputField = (TextInputEditText) contentView.findViewById(R.id.firstFieldInputEditText);
        final TextInputEditText secondInputField = (TextInputEditText) contentView.findViewById(R.id.secondFieldInputEditText);
        firstInputField.setHint(R.string.first_input_equipment_description);
        secondInputField.setHint(R.string.second_input_equipment_manufacturer);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if (addListener == null) {
                    dismiss();
                    return;
                }
                //There is a listener, parse the input and make an employee
                String description = firstInputField.getText().toString();
                String manufacturer = secondInputField.getText().toString();
                addListener.add(new Equipment(description, manufacturer));
                dismiss();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                onCancel(dialogInterface);
            }
        });
        return builder.create();
    }

    public void setAddListener(AddListener addListener) {
        this.addListener = addListener;
    }

    public interface AddListener {

        void add(Object o);
    }
}
