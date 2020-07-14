package com.example.sccn_carbon_emission_da;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.EntryXComparator;
import com.github.mikephil.charting.utils.MPPointF;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONArray;

import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.widget.Toast.LENGTH_LONG;


public class Home extends Fragment {

    public Home() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.activity_home, container, false);

        jsonPlaceHolderApi jsonPlaceHolderApi;
        jsonPlaceHolderApi jsonPlaceHolderApi2;



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://creationdevs.in/sccn/fetchmain.php/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl("https://creationdevs.in/sccn/fetch.php/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        jsonPlaceHolderApi = retrofit.create(jsonPlaceHolderApi.class);
        jsonPlaceHolderApi2=retrofit2.create(jsonPlaceHolderApi.class);

        create_piechart(jsonPlaceHolderApi2,v);

        Call<JsonArray> call = jsonPlaceHolderApi.createPost();

        call.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), response.code(), LENGTH_LONG).show();
                    return;
                }


                JsonElement scope1=null,scope2=null,scope3=null;
                float scope1_total_emission=0,scope2_total_emission=0,scope3_total_emission=0;
                JsonArray scope1jA=null,scope2jA=null,scope3jA=null;

             //   JsonObject user;
                JsonArray data=response.body();

                int i=1;
                for(JsonElement value:data){
                    if(i==1){

                        scope1=value;

                        JsonObject scope1jO=scope1.getAsJsonObject();
                        scope1jA=scope1jO.get("Scope1").getAsJsonArray();


                        for(JsonElement val:scope1jA){

                            JsonObject temp=val.getAsJsonObject();
                            scope1_total_emission=scope1_total_emission+temp.get("emission").getAsFloat();

                        }




                    }
                    else if(i==2){

                        scope2=value;

                        JsonObject scope2jO=scope2.getAsJsonObject();
                        scope2jA=scope2jO.get("Scope2").getAsJsonArray();


                        for(JsonElement val:scope2jA){

                            JsonObject temp=val.getAsJsonObject();
                            scope2_total_emission=scope2_total_emission+temp.get("emission").getAsFloat();

                        }



                    }
                    else if(i==3){

                        scope3=value;

                        JsonObject scope3jO=scope3.getAsJsonObject();
                        scope3jA=scope3jO.get("Scope3").getAsJsonArray();


                        for(JsonElement val:scope3jA){

                            JsonObject temp=val.getAsJsonObject();
                            scope3_total_emission=scope3_total_emission+temp.get("emission").getAsFloat();

                        }


                    }
                    else{

                        Toast.makeText(getActivity(),"Out of bounds",LENGTH_LONG).show();

                    }

                    i++;
                 //   Toast.makeText(getActivity(),Float.toString(scope1_total_emission),LENGTH_LONG).show();
                }
              //  Toast.makeText(getActivity(),"scope1 : "+scope1.toString()+"scope2 : "+scope2.toString()+"scope3 : "+scope3.toString(),LENGTH_LONG).show();


                SharedPreferences pref = getActivity().getSharedPreferences("MyPref", 0);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("scope1_total_emission", Float.toString(scope1_total_emission));
                editor.putString("scope2_total_emission", Float.toString(scope2_total_emission));
                editor.putString("scope3_total_emission", Float.toString(scope3_total_emission));
                editor.apply();

                LineChart chart;
        // seekBarY.setOnSeekBarChangeListener(this);

                chart = v.findViewById(R.id.chart1);
                //  chart.setOnChartValueSelectedListener(this);

                chart.setDrawGridBackground(false);
                // chart.getDescription().setEnabled(false);
                chart.setDrawBorders(true);

                chart.getAxisLeft().setEnabled(true);
                //  chart.getXAxis().setEnabled(false);
                XAxis xAxis = chart.getXAxis();
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

                chart.getAxisRight().setEnabled(false);
                chart.getAxisRight().setDrawAxisLine(false);
                chart.getAxisRight().setDrawGridLines(false);
                chart.getXAxis().setDrawAxisLine(false);
                chart.getXAxis().setDrawGridLines(false);


                // enable touch gestures
                chart.setTouchEnabled(true);

                // enable scaling and dragging
                chart.setDragEnabled(true);
                chart.setScaleEnabled(true);

                // if disabled, scaling can be done on x- and y-axis separately
                chart.setPinchZoom(false);

                Legend l = chart.getLegend();
                l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
                l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
                l.setOrientation(Legend.LegendOrientation.VERTICAL);
                l.setDrawInside(false);

                chart.resetTracking();
//                Toast.makeText(getActivity(),chart.getData().toString(),LENGTH_LONG).show();
                int progress = 8;


                ArrayList<ILineDataSet> dataSets = new ArrayList<>();


                for(int z=0;z<3;z++) {

                    ArrayList<Entry> values = new ArrayList<>();
                    JsonArray scope;
                    int j = 100;

                    if(z==0)
                        scope=scope1jA;
                    else if(z==1)
                        scope=scope2jA;
                    else
                        scope=scope3jA;


                    for (JsonElement value : scope) {

                        JsonObject temp = value.getAsJsonObject();
                        values.add(new Entry(j++, temp.get("emission").getAsFloat()));

                    }

                    LineDataSet d = new LineDataSet(values, "Scope " + (z + 1));
                    //d.setDrawFilled(true);
                   // Drawable drawable = ContextCompat.getDrawable(getActivity(), R.color.blue_800);
                    // d.setFillDrawable(drawable);
                    d.setLineWidth(5.5f);
                    d.setCircleRadius(8f);


                    int color = colors[z];
                    d.setColor(color);
                    d.setCircleColor(color);
                  //  d.setFillColor(color);
                    dataSets.add(d);
                }
                // make the first DataSet dashed
              /*  ((LineDataSet) dataSets.get(0)).enableDashedLine(10, 10, 0);
                ((LineDataSet) dataSets.get(0)).setColors(ColorTemplate.VORDIPLOM_COLORS);
                ((LineDataSet) dataSets.get(0)).setCircleColors(ColorTemplate.VORDIPLOM_COLORS);

               */

                LineData dat = new LineData(dataSets);
                chart.setData(dat);
                chart.invalidate();

            }
            private final int[] colors = new int[] {
                    ColorTemplate.VORDIPLOM_COLORS[0],
                    ColorTemplate.VORDIPLOM_COLORS[1],
                    ColorTemplate.VORDIPLOM_COLORS[2]
            };




            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Toast.makeText(getActivity(),"failure"+ t.getMessage(), LENGTH_LONG).show();
            }
        });




        // Inflate the layout for this fragment
        return v;
    }

    public void create_piechart(jsonPlaceHolderApi jsonPlaceHolderApi,View v){


        Call<JsonArray> call = jsonPlaceHolderApi.createPost();

        call.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), response.code(), LENGTH_LONG).show();
                    return;
                }

                Toast.makeText(getActivity(), response.body().toString(), LENGTH_LONG).show();

                JsonArray data = response.body();
                Dictionary dataset2 = new Hashtable();
              //  JsonElement twowheeler=null,fourwheeler=null,bus=null,eleemissions=null,offsetemissions=null,dieselemissions=null,lpgemissions=null;
                JsonArray twowheelerjA=null,fourwheelerjA=null,busjA=null,eleemissionsjA=null,offsetemissionsjA=null,dieselemissionsjA=null,lpgemissionsjA=null;
                float two_wheeler_total_emission=0,four_wheeler_total_emission=0,bus_total_emission=0,elemissions_total_emission=0,offesetemission_total_emission=0,diesel_emission_total_emission=0,lpgemmissions_total_emissions=0;


                int i=0;
                for(JsonElement value:data) {

                    JsonObject temp = value.getAsJsonObject();
                        // twowheelerjA=temp.get("lpgemissions").getAsJsonArray();

                   // scope1jA = scope1jO.get("Scope1").getAsJsonArray();


                    switch (i){

                        case 0:
                                twowheelerjA=temp.get("twowheeler").getAsJsonArray();
                                Toast.makeText(getActivity(),twowheelerjA.toString(),LENGTH_LONG).show();
                                for(JsonElement val:twowheelerjA){

                                JsonObject temp2=val.getAsJsonObject();
                                two_wheeler_total_emission=two_wheeler_total_emission+temp2.get("emission").getAsFloat();

                                }
                                break;

                        case 1:
                                fourwheelerjA=temp.get("fourwheeler").getAsJsonArray();
                                Toast.makeText(getActivity(),fourwheelerjA.toString(),LENGTH_LONG).show();
                                for(JsonElement val:fourwheelerjA){

                                JsonObject temp2=val.getAsJsonObject();
                                four_wheeler_total_emission=four_wheeler_total_emission+temp2.get("emission").getAsFloat();

                                }
                                break;
                        case 2:
                                busjA=temp.get("bus").getAsJsonArray();
                                Toast.makeText(getActivity(),busjA.toString(),LENGTH_LONG).show();
                                for(JsonElement val:busjA){

                                JsonObject temp2=val.getAsJsonObject();
                                bus_total_emission=bus_total_emission+temp2.get("emission").getAsFloat();

                                }
                                break;
                        case 3:
                                eleemissionsjA=temp.get("eleemissions").getAsJsonArray();
                                Toast.makeText(getActivity(),eleemissionsjA.toString(),LENGTH_LONG).show();
                                for(JsonElement val:eleemissionsjA){

                                JsonObject temp2=val.getAsJsonObject();
                                elemissions_total_emission=elemissions_total_emission+temp2.get("emission").getAsFloat();

                                }
                                break;

                        case 4:
                                offsetemissionsjA=temp.get("offsetemissions").getAsJsonArray();
                                Toast.makeText(getActivity(),offsetemissionsjA.toString(),LENGTH_LONG).show();
                                for(JsonElement val:offsetemissionsjA){

                                JsonObject temp2=val.getAsJsonObject();
                                offesetemission_total_emission=offesetemission_total_emission+temp2.get("emission").getAsFloat();

                                }
                                break;
                        case 5:
                                dieselemissionsjA=temp.get("dieselemissions").getAsJsonArray();
                                Toast.makeText(getActivity(),dieselemissionsjA.toString(),LENGTH_LONG).show();
                                for(JsonElement val:dieselemissionsjA){

                                JsonObject temp2=val.getAsJsonObject();
                                diesel_emission_total_emission=diesel_emission_total_emission+temp2.get("emission").getAsFloat();

                                }
                                break;

                        case 6:
                                lpgemissionsjA=temp.get("lpgemissions").getAsJsonArray();
                                Toast.makeText(getActivity(),lpgemissionsjA.toString(),LENGTH_LONG).show();
                                for(JsonElement val:lpgemissionsjA){

                                JsonObject temp2=val.getAsJsonObject();
                                lpgemmissions_total_emissions=lpgemmissions_total_emissions+temp2.get("emission").getAsFloat();

                                }
                                break;

                    }

                 i++;

                }

                SharedPreferences pref = getActivity().getSharedPreferences("MyPref", 0);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("two_wheeler_emission", Float.toString(two_wheeler_total_emission));
                editor.putString("four_wheeler_emission", Float.toString(four_wheeler_total_emission));
                editor.putString("bus_emission", Float.toString(bus_total_emission));
                editor.putString("lpg_emission", Float.toString(lpgemmissions_total_emissions));
                editor.putString("diesel_emission", Float.toString(diesel_emission_total_emission));
                editor.putString("offset_emission", Float.toString(offesetemission_total_emission));
                editor.putString("electricity_emission", Float.toString(elemissions_total_emission));
                editor.apply();

                PieChart chart,chart2;
                chart=v.findViewById(R.id.chart2);
                chart2=v.findViewById(R.id.chart3);
                chart.setUsePercentValues(true);
                chart.getDescription().setEnabled(false);
                chart.setExtraOffsets(5, 10, 5, 5);

                chart.setDragDecelerationFrictionCoef(0.95f);

              //  chart.setCenterTextTypeface(tfLight);
                //chart.setCenterText(generateCenterSpannableText());

                chart.setDrawHoleEnabled(true);
                chart.setHoleColor(Color.WHITE);

                chart.setTransparentCircleColor(Color.WHITE);
                chart.setTransparentCircleAlpha(110);

                chart.setHoleRadius(58f);
                chart.setTransparentCircleRadius(61f);

                chart.setDrawCenterText(true);

                chart.setRotationAngle(0);
                // enable rotation of the chart by touch
                chart.setRotationEnabled(true);
                chart.setHighlightPerTapEnabled(true);


                // chart.setUnit(" €");
                // chart.setDrawUnitsInChart(true);

                // add a selection listener

                chart.animateY(1400, Easing.EaseInOutQuad);
                // chart.spin(2000, 0, 360);

                Legend l = chart.getLegend();
                l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
                l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
                l.setOrientation(Legend.LegendOrientation.VERTICAL);
                l.setDrawInside(false);
                l.setXEntrySpace(7f);
                l.setYEntrySpace(0f);
                l.setYOffset(0f);

                // entry label styling
                chart.setEntryLabelColor(Color.WHITE);
               // chart.setEntryLabelTypeface(tfRegular);
                chart.setEntryLabelTextSize(12f);

                chart2.setUsePercentValues(true);
                chart2.getDescription().setEnabled(false);
                chart2.setExtraOffsets(5, 10, 5, 5);

                chart2.setDragDecelerationFrictionCoef(0.95f);

                //  chart.setCenterTextTypeface(tfLight);
                //chart.setCenterText(generateCenterSpannableText());

                chart2.setDrawHoleEnabled(true);
                chart2.setHoleColor(Color.WHITE);

                chart2.setTransparentCircleColor(Color.WHITE);
                chart2.setTransparentCircleAlpha(110);

                chart2.setHoleRadius(58f);
                chart2.setTransparentCircleRadius(61f);

                chart2.setDrawCenterText(true);

                chart2.setRotationAngle(0);
                // enable rotation of the chart by touch
                chart2.setRotationEnabled(true);
                chart2.setHighlightPerTapEnabled(true);


                // chart.setUnit(" €");
                // chart.setDrawUnitsInChart(true);

                // add a selection listener

                chart2.animateY(1400, Easing.EaseInOutQuad);
                // chart.spin(2000, 0, 360);

                Legend l2 = chart2.getLegend();
                l2.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
                l2.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
                l2.setOrientation(Legend.LegendOrientation.VERTICAL);
                l2.setDrawInside(false);
                l2.setXEntrySpace(7f);
                l2.setYEntrySpace(0f);
                l2.setYOffset(0f);

                // entry label styling
                chart2.setEntryLabelColor(Color.WHITE);
                // chart.setEntryLabelTypeface(tfRegular);
                chart2.setEntryLabelTextSize(12f);


                ArrayList<PieEntry> entries = new ArrayList<>();
                ArrayList<PieEntry> entries2 = new ArrayList<>();

                Toast.makeText(getActivity(),"Two"+two_wheeler_total_emission+"four"+four_wheeler_total_emission,LENGTH_LONG).show();
                entries.add(new PieEntry(two_wheeler_total_emission,"Two Wheeler"));
                entries.add(new PieEntry(four_wheeler_total_emission,"Four Wheeler"));
                entries.add(new PieEntry(bus_total_emission,"Bus"));
                entries.add(new PieEntry(diesel_emission_total_emission,"Diesel"));
                entries.add(new PieEntry(lpgemmissions_total_emissions,"lpg"));

                entries2.add(new PieEntry(elemissions_total_emission,"electricity"));
                entries2.add(new PieEntry(offesetemission_total_emission,"offeset"));


              //  entries.add(new PieEntry((float) (Math.random()),myNum2));
              //  entries.add(new PieEntry((float) (Math.random()),myNum3));


                // NOTE: The order of the entries when being added to the entries array determines their position around the center of
                // the chart.
       /*         for (int i = 0; i < count ; i++) {
                    entries.add(new PieEntry((float) ((Math.random() * range) + range / 5),
                            parties[i % parties.length],
                            getResources().getDrawable(R.drawable.star)));
                }

        */

                PieDataSet dataSet = new PieDataSet(entries, "Scope 1");
                dataSet.setDrawIcons(false);
                dataSet.setSliceSpace(3f);
                dataSet.setIconsOffset(new MPPointF(0, 40));
                dataSet.setSelectionShift(5f);

                PieDataSet dataSet2 = new PieDataSet(entries2, "Scope 2");
                dataSet2.setDrawIcons(false);
                dataSet2.setSliceSpace(3f);
                dataSet2.setIconsOffset(new MPPointF(0, 40));
                dataSet2.setSelectionShift(5f);

                // add a lot of colors

                ArrayList<Integer> colors = new ArrayList<>();

                for (int c : ColorTemplate.VORDIPLOM_COLORS)
                    colors.add(c);

                for (int c : ColorTemplate.JOYFUL_COLORS)
                    colors.add(c);

                for (int c : ColorTemplate.COLORFUL_COLORS)
                    colors.add(c);

                for (int c : ColorTemplate.LIBERTY_COLORS)
                    colors.add(c);

                for (int c : ColorTemplate.PASTEL_COLORS)
                    colors.add(c);

                colors.add(ColorTemplate.getHoloBlue());

                dataSet.setColors(colors);
                dataSet2.setColors(colors);
                //dataSet.setSelectionShift(0f);

                PieData data2 = new PieData(dataSet);
                data2.setValueFormatter(new PercentFormatter(chart));
                data2.setValueTextSize(11f);
                data2.setValueTextColor(Color.WHITE);
                chart.setData(data2);

                // undo all highlights
                chart.highlightValues(null);

                PieData data3 = new PieData(dataSet2);
                data3.setValueFormatter(new PercentFormatter(chart2));
                data3.setValueTextSize(11f);
                data3.setValueTextColor(Color.WHITE);
                chart2.setData(data3);

                // undo all highlights
                chart2.highlightValues(null);

                chart2.invalidate();




            }



            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Toast.makeText(getActivity(),"failure"+ t.getMessage(), LENGTH_LONG).show();
            }
        });






    }

    // TODO: Rename method, update argument and hook method into UI event


}
