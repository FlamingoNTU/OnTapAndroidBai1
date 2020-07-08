package vn.edu.ntu.ngocchau.ontapbai1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class HienthiFragment extends Fragment {

    TextView txthienthi;
    Button btnthoat;

    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hienthi, container, false);
        addview(view);
        getdata();
        addEvent();
        return view;
    }

    private void addview(View view) {
        txthienthi = view.findViewById(R.id.txthienthi);
        btnthoat = view.findViewById(R.id.btnthoat);

        navController = NavHostFragment.findNavController(HienthiFragment.this);
    }

    private void getdata() {
        Bundle data = getArguments();
        String str = "Chúc mừng khách hàng: " + data.getString("ten") + "\n Sinh ngày: "
                + data.getString("ngaysinh") + "\n Đã đăng ký thành công dịch vụ: \n"
                + data.getString("dichvu") + "\n Hình thức thanh toán: "
                + data.getString("phuongthuc") + "\n Chúng tôi sẽ liên lạc với bạn qua sdt: \n"
                + data.getString("sdt");
        txthienthi.setText(str);
    }

    private void addEvent() {
        btnthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_hienthiFragment_to_dangkyFragment);
            }
        });
    }
}