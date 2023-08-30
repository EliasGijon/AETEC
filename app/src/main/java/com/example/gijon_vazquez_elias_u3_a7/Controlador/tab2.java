package com.example.gijon_vazquez_elias_u3_a7.Controlador;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.gijon_vazquez_elias_u3_a7.MainActivity;
import com.example.gijon_vazquez_elias_u3_a7.R;
import com.example.gijon_vazquez_elias_u3_a7.envioCorreo;

import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link tab2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class tab2 extends Fragment {
    Button Enviar;
    Spinner opciones;
    EditText AP,AM,N,NOCOM,EMAIL;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public tab2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment tab2.
     */
    // TODO: Rename and change types and number of parameters
    public static tab2 newInstance(String param1, String param2) {
        tab2 fragment = new tab2();
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
        View view= inflater.inflate(R.layout.fragment_tab2, container, false);
        opciones= view.findViewById(R.id.sp01);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),R.array.opciones, android.R.layout.simple_spinner_dropdown_item);
        opciones.setAdapter(adapter);

        AP=view.findViewById(R.id.PersonLastNameP);
        AM=view.findViewById(R.id.PersonLastNameM);
        N=view.findViewById(R.id.PersonName);
        NOCOM=view.findViewById(R.id.TextNumber);
        EMAIL=view.findViewById(R.id.EmailAddress);

        EMAIL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String em=EMAIL.getText().toString();
                if (!validarEmail("miEmail@gmail.com")){
                    EMAIL.setError("Email no válido");
                }
            }
        });

        //Enviar Formulario
        Enviar=view.findViewById(R.id.enviar);
        Enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ap=AP.getText().toString();
                String am=AM.getText().toString();
                String n=N.getText().toString();
                String em=EMAIL.getText().toString();
                String nocom=NOCOM.getText().toString();
                if(opciones.getSelectedItem().equals("Seleccione una Opción")){
                    Toast toast = Toast.makeText(getActivity(), "Falta Seleccionar una Actividad", Toast.LENGTH_SHORT);
                    toast.show();
                }
                if(ap.equals("")||am.equals("")||n.equals("")||em.equals("")){
                    if(ap.equals("")){
                        AP.setError("Ingresa tu Apellido Paterno");
                    }
                    if(am.equals("")){
                        AM.setError("Ingresa tu Apellido Materno");
                    }
                    if(n.equals("")){
                        N.setError("Ingresa tus Nombres");
                    }
                    if(em.equals("")){
                        EMAIL.setError("Email no valido");
                    }
                    mensajes();
                }
                else{
                    //EMAIL.setText(em);
                    String SMS="Apellido Paterno: "+ ap +
                            "\nApellido Materno: "+ am +
                            "\nNombre: "+ n +
                            "\nActividad: "+ opciones.getSelectedItem() +
                            "\nNumero de Control: "+ nocom +
                            "\nEmail: "+ em;

                    AP.setText(null);
                    AM.setText(null);
                    N.setText(null);
                    EMAIL.setText(null);
                    NOCOM.setText(null);
                    opciones.setSelection(0);

                    Toast toast = Toast.makeText(getActivity(), "Correo Enviado Correctamente", Toast.LENGTH_SHORT);
                    toast.show();

                    envioCorreo.EnviarFormulario(SMS);
                    //((MainActivity) getActivity()).EnviarFormulario(SMS);
                }
            }
        });
        return view;
    }
    public void mensajes(){
        Context context = getActivity();
        CharSequence text = "Falta Rellenar Campos";
        /*switch(Arg){
            case "Opciones":
                text="Falta Seleccionar una Actividad";
                break;
            case "AP":
                text="Falta Rellenar tu Apellido Paterno";
                break;
            case "AM":
                text="Falta Rellenar tu Apellido Materno";
                break;
            case "N":
                text="Falta Rellenar tus Nombres";
                break;
            case "EM":
                text="Falta Rellenar tu Correo Electronico";
                break;
        }*/
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
}