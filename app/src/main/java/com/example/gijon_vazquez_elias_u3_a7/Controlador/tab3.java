package com.example.gijon_vazquez_elias_u3_a7.Controlador;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.gijon_vazquez_elias_u3_a7.Conexion;
import com.example.gijon_vazquez_elias_u3_a7.R;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link tab3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class tab3 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RelativeLayout Panel;
    public tab3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment tab3.
     */
    // TODO: Rename and change types and number of parameters
    public static tab3 newInstance(String param1, String param2) {
        tab3 fragment = new tab3();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_tab3, container, false);

        Button myButton = new Button(getActivity());
        myButton.setText("Click Me");

        // Set click listener for the button
        myButton.setOnClickListener(new View.OnClickListener() {
            // Button click logic here
            @Override
            public void onClick(View v) {
                try {

                    Connection connection = Conexion.getConnection();

                    // Consulta SQL
                    String sql = "select * from productos_proveedor";

                    // Preparar la declaración SQL con parámetros
                    PreparedStatement statement = connection.prepareStatement(sql);

                    // Ejecutar la consulta
                    ResultSet resultSet = statement.executeQuery();

                    while (resultSet.next()) {
                        TextView tv = new TextView(getActivity());
                        tv.setText(resultSet.getString(0));
                        Panel.addView(tv);
                        System.out.println(resultSet.getString(0));
                    }
                }catch (Exception e){
                    System.out.printf("error consulta");
                }
            }
        });

        Panel = view.findViewById(R.id.sc);
        Panel.addView(myButton);
        return view;
    }
}