package vn.edu.ntu.ngocchau.ontapbai1;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.util.Calendar;

public class DangkyFragment extends Fragment {
    EditText edtten, edtngaysinh, edtdiachi, edtsdt;
    ImageView imglich;
    RadioButton rbtienmat, rbnganhang, rbvidientu;
    Spinner spinner;
    Button btndky;

    NavController navController;
    String ten, ngaysinh, sdt, diachi, dichvu, phuongthuc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dangky, container, false);
        addview(view);
        getdata();
        addevent();
        return view;
    }

    private void addview(View view) {
        edtten = view.findViewById(R.id.edtten);
        edtngaysinh = view.findViewById(R.id.edtngaysinh);
        edtsdt = view.findViewById(R.id.edtsdt);
        edtdiachi = view.findViewById(R.id.edtdiachi);
        imglich = view.findViewById(R.id.imglich);
        rbtienmat = view.findViewById(R.id.rbtienmat);
        rbnganhang = view.findViewById(R.id.rbnganhang);
        rbvidientu = view.findViewById(R.id.rbvidientu);
        spinner = view.findViewById(R.id.spinner);
        btndky = view.findViewById(R.id.btndky);

        navController = NavHostFragment.findNavController(DangkyFragment.this);
    }

    private void getdata() {
        imglich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();

                DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        StringBuilder builder = new StringBuilder();
                        builder.append(year)
                                .append("-")
                                .append(++month)
                                .append("-")
                                .append(dayOfMonth);
                        edtngaysinh.setText(builder.toString());
                    }
                };
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),listener
                        ,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
        String[] dichvu = new String[]{"Truyền hình số","Truyền hình cáp","FPT"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(DangkyFragment.this.getActivity(),
                R.layout.support_simple_spinner_dropdown_item,dichvu);
        spinner.setAdapter(arrayAdapter);

    }

    private void addevent() {
        btndky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ten = edtten.getText().toString();
                ngaysinh = edtngaysinh.getText().toString();
                sdt = edtsdt.getText().toString();
                diachi = edtdiachi.getText().toString();

                if(rbtienmat.isChecked())
                {
                    phuongthuc = "Tiền mặt";
                }

                if(rbnganhang.isChecked())
                {
                    phuongthuc = "Ngân hàng";
                }
                if(rbvidientu.isChecked())
                {
                    phuongthuc = "Ví điện tử";
                }

                dichvu = spinner.getSelectedItem().toString();

                Bundle data = new Bundle();
                data.putString("ten", ten);
                data.putString("ngaysinh", ngaysinh);
                data.putString("sdt", sdt);
                data.putString("diachi", diachi);
                data.putString("dichvu", dichvu);
                data.putString("phuongthuc", phuongthuc);
                navController.navigate(R.id.action_dangkyFragment_to_hienthiFragment,data);
            }
        });
    }
}
