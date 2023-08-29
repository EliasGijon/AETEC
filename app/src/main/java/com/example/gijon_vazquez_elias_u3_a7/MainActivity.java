package com.example.gijon_vazquez_elias_u3_a7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import com.example.gijon_vazquez_elias_u3_a7.Controlador.PagerController;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    TabItem tab1,tab2,tab3;
    PagerController pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TAPS
        tabLayout=findViewById(R.id.tablalayout);
        viewPager=findViewById(R.id.viewpager);
        tab1=findViewById(R.id.tab1);
        tab2=findViewById(R.id.tab2);
        tab3=findViewById(R.id.tab3);

        //Visor de frgamentos
        pagerAdapter =new PagerController(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);

        //Cambio de pestañas de los tabs
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition()==0){
                    pagerAdapter.notifyDataSetChanged();
                }
                if(tab.getPosition()==1){
                    pagerAdapter.notifyDataSetChanged();
                }
                if(tab.getPosition()==2){
                    pagerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }
    //Envio de formulario de la tab 2
    public void EnviarFormulario(String SMS){
        try {
            //Correos
            String stringSender="eliasnikolay115@gmail.com";
            String stringReceiverEmail="lazcanoblanco272@gmail.com";

            //Clave de acceso a uso del correo que envia de dos pasos con aplicaciones de terceros permitidas
            String StringPasswordSenderEmail="dyseionmtgdqeqbi";

            //Establecemos conexion al servidor
            String stringHost="smtp.gmail.com";
            Properties properties=System.getProperties();
            properties.put("mail.smtp.host",stringHost);
            properties.put("mail.smtp.port","465");
            properties.put("mail.smtp.ssl.enable","true");
            properties.put("mail.smtp.auth","true");

            javax.mail.Session session= Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(stringSender,StringPasswordSenderEmail);
                }
            });

            MimeMessage mimeMessage = new MimeMessage(session);

            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(stringReceiverEmail));

            //Asunto
            mimeMessage.setSubject("Actividades Complementarias");
            //Contenido
            mimeMessage.setText(SMS);

            Thread thread=new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Transport.send(mimeMessage);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }
            });

            //Envio
            thread.start();

        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}