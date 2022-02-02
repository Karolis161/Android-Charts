package com.example.egzovar1;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class FirstFragment extends Fragment {

    EditText editText;
    Button button1, button2, button3;

    int pressed = 0;
    int bals = 0;
    int prieb = 0;
    int sum = 0;
    String temp;

    Bundle bundle = new Bundle();
    SecondFragment secondFragment = new SecondFragment();

    NotificationManagerCompat notificationManagerCompat;
    Notification notification;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_first, container, false);

        editText = (EditText) view.findViewById(R.id.editText);
        button1 = (Button) view.findViewById(R.id.button1);
        button2 = (Button) view.findViewById(R.id.button2);
        button3 = (Button) view.findViewById(R.id.button3);

        functions();
        return view;
    }

    public void calculations() {
        temp = String.valueOf(editText.getText());

        for (int i = 0; i < temp.length(); i++) {
            char ch = temp.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                bals++;
            } else
                prieb++;
        }
        sum = bals + prieb;
    }

    public void functions() {

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pressed = 1;
                bundle.putInt("pressed", pressed);
                secondFragment.setArguments(bundle);

                calculations();
                bundle.putInt("bals", bals);
                bundle.putInt("prieb", prieb);
                bundle.putInt("sum", sum);

                secondFragment.setArguments(bundle);

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment2, secondFragment)
                        .commit();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pressed = 2;
                bundle.putInt("pressed", pressed);
                secondFragment.setArguments(bundle);

                calculations();
                bundle.putInt("bals", bals);
                bundle.putInt("prieb", prieb);
                bundle.putInt("sum", sum);

                secondFragment.setArguments(bundle);

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment2, secondFragment)
                        .commit();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculations();
                sendNotification();
            }
        });
    }

    public void sendNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("myCH", "My channel",
                    NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getActivity().getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(), "myCH")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Zinute")
                .setContentText("Eiluteje yra "
                        + bals + " balsiu, "
                        + prieb + " priebalsiu ir "
                        + sum + " bendrai");

        notification = builder.build();
        notificationManagerCompat = NotificationManagerCompat.from(getActivity());
        notificationManagerCompat.notify(1, notification);
    }
}