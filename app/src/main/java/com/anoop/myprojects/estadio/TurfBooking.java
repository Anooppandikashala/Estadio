package com.anoop.myprojects.estadio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TurfBooking extends AppCompatActivity {

    EditText date,time_from,time_to;

    Button book_now;

    int mYear, mMonth, mDay;

    String day="",dayName="",year="",month="",monthNumber="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turf_booking);


        date = findViewById(R.id.date);

        book_now= findViewById(R.id.booknow);

        time_from = findViewById(R.id.time_start);

        time_from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(TurfBooking.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        time_from.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, false);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        time_to = findViewById(R.id.time_to);
        time_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(TurfBooking.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        time_to.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, false);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(TurfBooking.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year1,
                                                  int monthOfYear, int dayOfMonth) {

                                date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year1);
                                SimpleDateFormat inFormat = new SimpleDateFormat("dd-MM-yyyy");

                                Date date_ = null;
                                try {
                                    date_ = inFormat.parse(date.getText().toString());
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                SimpleDateFormat outFormat = new SimpleDateFormat("EEEE");
                                String goal = outFormat.format(date_);
                                //System.out.println(goal);

                                day = String.valueOf(dayOfMonth);
                                month = getMonthName(monthOfYear);
                                monthNumber = String.valueOf(monthOfYear+1);
                                dayName = goal;
                                year = String.valueOf(year1);

                                System.out.println(day+" "+month+" "+year+" "+dayName);

                            }
                        }, mYear, mMonth, mDay);

                datePickerDialog.getDatePicker().setMinDate(new Date().getTime());
                datePickerDialog.show();

            }
        });



        book_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TurfBooking.this);
                builder.setMessage("You want to Save ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // FIRE ZE MISSILES!
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });
                builder.setTitle("Are You Sure");
                builder.create().show();
            }
        });

    }

    String getMonthName(int month)
    {
        String m = "";
        switch (month)
        {
            case 0:
            {
                m= "Jan";
                break;
            }
            case 1:
            {
                m=  "Feb";
                break;
            }
            case 2:
            {
                m= "Mar";
                break;
            }
            case 3:
            {
                m=  "Apr";
                break;
            }
            case 4:
            {
                m=  "May";
                break;
            }
            case 5:
            {
                m=  "Jun";
                break;
            }
            case 6:
            {
                m=  "Jul";
                break;
            }
            case 7:
            {
                m=  "Aug";
                break;
            }
            case 8:
            {
                m=  "Sep";
                break;
            }
            case 9:
            {
                m=  "Oct";
                break;
            }
            case 10:
            {
                m=  "Nov";
                break;
            }
            case 11:
            {
                m=  "Dec";
                break;
            }

        }
        return m;

    }

    public class AlertDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("You want to Save ?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // FIRE ZE MISSILES!
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                        }
                    });
            builder.setTitle("Are You Sure");
            // Create the AlertDialog object and return it
            return builder.create();
        }
    }


}
