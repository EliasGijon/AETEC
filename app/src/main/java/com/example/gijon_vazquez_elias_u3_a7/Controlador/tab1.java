package com.example.gijon_vazquez_elias_u3_a7.Controlador;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.gijon_vazquez_elias_u3_a7.Informacion;
import com.example.gijon_vazquez_elias_u3_a7.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link tab1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class tab1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RelativeLayout Panel;
    TextView etiqueta;

    public tab1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment tab1.
     */
    // TODO: Rename and change types and number of parameters
    public static tab1 newInstance(String param1, String param2) {
        tab1 fragment = new tab1();
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

    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_tab1, container, false);

        Panel=view.findViewById(R.id.panel);

        Resources res = getResources();
        String[]  a= res.getStringArray(R.array.opciones);
        TypedArray imgs = res.obtainTypedArray(R.array.img);

        ImageView img[] = new ImageView[a.length];
        TextView textViews[] = new TextView[a.length];

        int marginInDp42 = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 42, getResources()
                        .getDisplayMetrics());
        int marginInDp7 = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 7, getResources()
                        .getDisplayMetrics());
        int marginInDp50 = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 50, getResources()
                        .getDisplayMetrics());
        for(int i=1;i<a.length;i++){
            ImageView as= new ImageView(getActivity());
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT
            );
            RelativeLayout.LayoutParams txt = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT
            );

            TextView tv = new TextView(getActivity());
            tv.setTextAppearance(R.font.montserrat);
            tv.setTextSize(20);
            String actividad=a[i];
            tv.setText(actividad);
            tv.setGravity(Gravity.CENTER_HORIZONTAL);
            tv.setTextColor(res.getColor(R.color.TECNM_NEGRO));
            tv.setId(i);

            as.setAdjustViewBounds(true);
            as.setImageResource(imgs.getResourceId(i-1,0));
            as.setScaleType(ImageView.ScaleType.FIT_XY);

            as.setId(i);
            as.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent siguiente=new Intent( getActivity(), Informacion.class);
                    Bundle Variables=new Bundle();
                    Variables.putString("queryxd",actividad);
                    siguiente.putExtras(Variables);
                    startActivity(siguiente);
                }
            });

            params.setMargins(marginInDp42, marginInDp50, marginInDp42, marginInDp7);
            txt.setMargins(marginInDp42, marginInDp7, marginInDp50, marginInDp7);

            if(i==1){
                params.addRule(RelativeLayout.BELOW, R.id.Etiqueta);
                txt.addRule(RelativeLayout.BELOW, as.getId());
            }
            else{
                ImageView temp=img[i-1];
                TextView tmp=textViews[i-1];
                params.addRule(RelativeLayout.BELOW, tmp.getId());
                txt.addRule(RelativeLayout.BELOW, as.getId());
            }
            as.setLayoutParams(params);
            tv.setLayoutParams(txt);

            img[i]=as;
            textViews[i]=tv;

            Panel.addView(tv);
            Panel.addView(as);
        }
        return view;
    }
}