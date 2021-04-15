package com.example.rdproiectamdm;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThirdFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThirdFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    CheckBox check1, check2, check3, check4, check5, check6, check7, check8, check9, check10, check11, check12;
    Button CheckButton;
    TextView Diag;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ThirdFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThirdFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ThirdFragment newInstance(String param1, String param2) {
        ThirdFragment fragment = new ThirdFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public String BoxesStatus()
    {
        if(check1.isChecked() | check2.isChecked() | check3.isChecked())
        {
            return "Aveti cele mai frecvente simptome, trebuie sa consultati un medic!";
        }
        else if(check4.isChecked() | check5.isChecked() | check6.isChecked() | check7.isChecked())
        {
            return "Trebuie sa vizitati un doctor de urgenta!";
        }
        else if(check8.isChecked() | check9.isChecked() | check10.isChecked() | check11.isChecked() | check12.isChecked())
        {
            return "Nu trebuie sa va ingrijorati, simptomele dumneavoastra nu sunt grave!";
        }
        else
            return "Selectati cel putin 1 simptom!";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_third, container, false);
        CheckButton = rootView.findViewById(R.id.buttonCheck);
        Diag = rootView.findViewById(R.id.textViewDiag);

        check1 = rootView.findViewById(R.id.checkBox1);
        check2 = rootView.findViewById(R.id.checkBox2);
        check3 = rootView.findViewById(R.id.checkBox3);
        check4 = rootView.findViewById(R.id.checkBox4);
        check5 = rootView.findViewById(R.id.checkBox5);
        check6 = rootView.findViewById(R.id.checkBox6);
        check7 = rootView.findViewById(R.id.checkBox7);
        check8 = rootView.findViewById(R.id.checkBox8);
        check9 = rootView.findViewById(R.id.checkBox9);
        check10 = rootView.findViewById(R.id.checkBox10);
        check11 = rootView.findViewById(R.id.checkBox11);
        check12 = rootView.findViewById(R.id.checkBox12);

        CheckButton.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                Diag.setText(BoxesStatus());
            }
        });

        return rootView;
    }
}