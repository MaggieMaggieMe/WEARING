package fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.myapplication.R;

public class HairFragment extends Fragment {
    //发型控件
    private RadioGroup group_hair;
    private RadioButton chooseHair_1,chooseHair_2;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_hair, container,false);
        return v;
    }

}
