package com.example.sccn_carbon_emission_da;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.formatter.StackedValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.widget.Toast.LENGTH_LONG;

public class Visualize extends Fragment {

    public static ArrayList<Entry> values0 = new ArrayList<>();
    public static ArrayList<Entry> values1 = new ArrayList<>();
    public static ArrayList<Entry> values2 = new ArrayList<>();
    public static ArrayList<Entry> values3 = new ArrayList<>();
    public static ArrayList<Entry> values4 = new ArrayList<>();
    public static ArrayList<BarEntry> values6= new ArrayList<>();
    public static ArrayList<BarEntry> values5 = new ArrayList<>();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    public Visualize() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View v= inflater.inflate(R.layout.activity_visualize, container, false);
        scope1chart(v);

    /*    BarChart chart;
        chart = v.findViewById(R.id.chart1);

        chart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        chart.setMaxVisibleValueCount(40);

        // scaling can now only be done on x- and y-axis separately
        chart.setPinchZoom(false);

        chart.setDrawGridBackground(false);
        chart.setDrawBarShadow(false);

        chart.setDrawValueAboveBar(true);
        chart.setHighlightFullBarEnabled(true);

        // change the position of the y-labels
        YAxis leftAxis = chart.getAxisLeft();
        //leftAxis.setValueFormatter(new MyValueFormatter("K"));
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        chart.getAxisRight().setEnabled(false);

        XAxis xLabels = chart.getXAxis();
        xLabels.setPosition(XAxis.XAxisPosition.TOP);

        // chart.setDrawXLabels(false);
        // chart.setDrawYLabels(false);

        // setting data
        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setFormSize(8f);
        l.setFormToTextSpace(4f);
        l.setXEntrySpace(6f);

        ArrayList<BarEntry> values = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            float mul = (2 + 1);
            float val1 = (float) (Math.random() * mul) + mul / 3;
            float val2 = (float) (Math.random() * mul) + mul / 3;
            float val3 = (float) (Math.random() * mul) + mul / 3;

            values.add(new BarEntry(
                    i,
                    new float[]{val1, val2, val3}
                    //getResources().getDrawable(R.drawable.star))
                    ));
        }

        BarDataSet set1;
        set1 = new BarDataSet(values, "Statistics Vienna 2014");
        set1.setDrawIcons(false);
        set1.setColors(getColors());
        set1.setStackLabels(new String[]{"Births", "Divorces", "Marriages"});

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        BarData data = new BarData(dataSets);
        data.setValueFormatter(new StackedValueFormatter(false, "", 1));
        data.setValueTextColor(Color.WHITE);
        data.setBarWidth(1f);

        chart.setData(data);



     */
      /*  ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(4f, 0));
        entries.add(new BarEntry(8f, 1));
        entries.add(new BarEntry(6f, 2));
        entries.add(new BarEntry(12f, 3));
        entries.add(new BarEntry(18f, 4));
        entries.add(new BarEntry(9f, 5));

        BarDataSet dataset = new BarDataSet(entries, "# of Calls");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");

       */

       /* BarChart chart = new BarChart(getActivity());
        BarData data = new BarData(labels, dataset);
        chart.setData(data);
        chart.setDescription("# of times Alice called Bob");

        return chart;

        */


        return v;
    }

    private int[] getColors() {
        // have as many colors as stack-values per entry
        int[] colors = new int[3];
        System.arraycopy(ColorTemplate.MATERIAL_COLORS, 0, colors, 0, 3);
        return colors;
    }

    public void scope1chart(View v){



        jsonPlaceHolderApi jsonPlaceHolderApi;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://creationdevs.in/sccn/fetch.php/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        jsonPlaceHolderApi = retrofit.create(jsonPlaceHolderApi.class);

        Call<JsonArray> call = jsonPlaceHolderApi.createPost();

        call.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), response.code(), LENGTH_LONG).show();
                    return;
                }

             //   Toast.makeText(getActivity(), response.body().toString(), LENGTH_LONG).show();

                JsonArray data = response.body();
                Dictionary dataset2 = new Hashtable();
                //  JsonElement twowheeler=null,fourwheeler=null,bus=null,eleemissions=null,offsetemissions=null,dieselemissions=null,lpgemissions=null;
                JsonArray twowheelerjA=null,fourwheelerjA=null,busjA=null,eleemissionsjA=null,offsetemissionsjA=null,dieselemissionsjA=null,lpgemissionsjA=null;
                float two_wheeler_total_emission=0,four_wheeler_total_emission=0,bus_total_emission=0,elemissions_total_emission=0,offesetemission_total_emission=0,diesel_emission_total_emission=0,lpgemmissions_total_emissions=0;


                int i=0;


                BarChart chart;
                chart = v.findViewById(R.id.chart1);
                chart.getDescription().setEnabled(false);

                // scaling can now only be done on x- and y-axis separately
                chart.setPinchZoom(false);
                chart.setDrawBarShadow(false);
                chart.setDrawGridBackground(false);

                // create a custom MarkerView (extend MarkerView) and specify the layout
                // to use for it


                Legend l = chart.getLegend();
                l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
                l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
                l.setOrientation(Legend.LegendOrientation.VERTICAL);
                l.setDrawInside(true);
                l.setYOffset(0f);
                l.setXOffset(10f);
                l.setYEntrySpace(0f);
                l.setTextSize(8f);

                XAxis xAxis = chart.getXAxis();
                xAxis.setGranularity(1f);
                xAxis.setCenterAxisLabels(true);
                xAxis.setValueFormatter(new ValueFormatter() {
                    @Override
                    public String getFormattedValue(float value) {
                        return String.valueOf((int) value);
                    }
                });

                YAxis leftAxis = chart.getAxisLeft();
                leftAxis.setValueFormatter(new LargeValueFormatter());
                leftAxis.setDrawGridLines(false);
                leftAxis.setSpaceTop(35f);
                leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

                chart.getAxisRight().setEnabled(false);

                float groupSpace = 0.08f;
                float barSpace = 0.03f; // x4 DataSet
                float barWidth = 0.2f; // x4 DataSet
                // (0.2 + 0.03) * 4 + 0.08 = 1.00 -> interval per "group"





                int k=0;
                for(JsonElement value:data) {

                    JsonObject temp = value.getAsJsonObject();

                    switch(i) {

                        case 0:
                            twowheelerjA=temp.get("twowheeler").getAsJsonArray();
                            Toast.makeText(getActivity(),twowheelerjA.toString(),LENGTH_LONG).show();
                            for(JsonElement val:twowheelerjA){

                                JsonObject temp2=val.getAsJsonObject();
                                two_wheeler_total_emission=two_wheeler_total_emission+temp2.get("emission").getAsFloat();
                                values0.add(new BarEntry(k, temp2.get("emission").getAsFloat()));
                                k++;

                            }
                            break;

                        case 1:
                            fourwheelerjA=temp.get("fourwheeler").getAsJsonArray();
                            Toast.makeText(getActivity(),fourwheelerjA.toString(),LENGTH_LONG).show();
                            k=0;
                            for(JsonElement val:fourwheelerjA){

                                JsonObject temp2=val.getAsJsonObject();
                                four_wheeler_total_emission=four_wheeler_total_emission+temp2.get("emission").getAsFloat();
                                values1.add(new BarEntry(k, temp2.get("emission").getAsFloat()));
                                k++;

                            }
                            break;
                        case 2:
                            busjA=temp.get("bus").getAsJsonArray();
                            Toast.makeText(getActivity(),busjA.toString(),LENGTH_LONG).show();
                            k=0;
                            for(JsonElement val:busjA){

                                JsonObject temp2=val.getAsJsonObject();
                                bus_total_emission=bus_total_emission+temp2.get("emission").getAsFloat();
                                values2.add(new BarEntry(k, temp2.get("emission").getAsFloat()));
                                k++;

                            }
                            break;
                        case 3:
                            eleemissionsjA=temp.get("eleemissions").getAsJsonArray();
                            Toast.makeText(getActivity(),eleemissionsjA.toString(),LENGTH_LONG).show();
                            k=0;
                            for(JsonElement val:eleemissionsjA){

                                JsonObject temp2=val.getAsJsonObject();
                                elemissions_total_emission=elemissions_total_emission+temp2.get("emission").getAsFloat();
                                values3.add(new Entry(k, temp2.get("emission").getAsFloat()));
                                k++;

                            }
                            break;

                        case 4:
                            offsetemissionsjA=temp.get("offsetemissions").getAsJsonArray();
                            Toast.makeText(getActivity(),offsetemissionsjA.toString(),LENGTH_LONG).show();

                            k=0;
                            for(JsonElement val:offsetemissionsjA){

                                JsonObject temp2=val.getAsJsonObject();
                                offesetemission_total_emission=offesetemission_total_emission+temp2.get("emission").getAsFloat();
                                values4.add(new BarEntry(k, temp2.get("emission").getAsFloat()));
                                k++;

                            }
                            break;
                        case 5 :
                            dieselemissionsjA = temp.get("dieselemissions").getAsJsonArray();
                            Toast.makeText(getActivity(), dieselemissionsjA.toString(), LENGTH_LONG).show();

                            k = 0;
                            for (JsonElement val : dieselemissionsjA) {

                                 JsonObject temp2 = val.getAsJsonObject();
                                diesel_emission_total_emission = diesel_emission_total_emission + temp2.get("emission").getAsFloat();
                                values5.add(new BarEntry(k, temp2.get("emission").getAsFloat()));
                                k++;

                             }
                            break;

                        case 6 :

                            lpgemissionsjA=temp.get("lpgemissions").getAsJsonArray();
                            Toast.makeText(getActivity(),lpgemissionsjA.toString(),LENGTH_LONG).show();

                            k=0;
                            for(JsonElement val:lpgemissionsjA){

                                JsonObject temp2=val.getAsJsonObject();
                                lpgemmissions_total_emissions=lpgemmissions_total_emissions+temp2.get("emission").getAsFloat();
                                values6.add(new BarEntry(k,temp2.get("emission").getAsFloat()));
                                k++;

                            }
                            break;

                    }
                    i++;

                }
                BarDataSet set1, set2, set3, set4;
                set1 = new BarDataSet(values5, "Diesel EMissions");
                set1.setColor(Color.rgb(104, 241, 175));
                set2 = new BarDataSet(values6, "LPG Emissions");
                set2.setColor(Color.rgb(164, 228, 251));


                ArrayList<String> xLabels = new ArrayList<>();
                xLabels.add("January");
                xLabels.add("February");
                xLabels.add("March");
                xLabels.add("April");


                XAxis xxAxis=chart.getXAxis();
                xxAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                chart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xLabels));


                BarData data2 = new BarData(set1, set2);
                data2.setValueFormatter(new LargeValueFormatter());

                chart.setData(data2);

                chart.getBarData().setBarWidth(barWidth);

                // restrict the x-axis range
                chart.getXAxis().setAxisMinimum(0);

                // barData.getGroupWith(...) is a helper that calculates the width each group needs based on the provided parameters
                chart.getXAxis().setAxisMaximum(0 + chart.getBarData().getGroupWidth(groupSpace, barSpace) * 4);
                chart.groupBars(0, groupSpace, barSpace);
                chart.invalidate();
                scope2chart(v);
                scope3chart(v);


            }



            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Toast.makeText(getActivity(),"failure"+ t.getMessage(), LENGTH_LONG).show();
            }
        });

    }

    public void scope2chart(View v) {


        LineChart chart;
        // seekBarY.setOnSeekBarChangeListener(this);

        chart = v.findViewById(R.id.chart2);
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


        LineDataSet d = new LineDataSet(values3, "Electricity");
        LineDataSet d2 = new LineDataSet(values4, "Offeset");

        //Toast.makeText(getActivity(),"d"+values3.toString(),LENGTH_LONG).show();


            d.setDrawFilled(true);
            // Drawable drawable = ContextCompat.getDrawable(getActivity(), R.color.blue_800);
            //d.setFillDrawable(drawable);
            d.setLineWidth(5.5f);
            d.setCircleRadius(8f);


            int color = colors[2];
            d.setColor(color);
            d.setCircleColor(color);
            d.setFillColor(color);
            dataSets.add(d);

            d2.setDrawFilled(true);
        // Drawable drawable = ContextCompat.getDrawable(getActivity(), R.color.blue_800);
        //d.setFillDrawable(drawable);
            d2.setLineWidth(5.5f);
            d2.setCircleRadius(8f);


            color = colors[1];
            d2.setColor(color);
            d2.setCircleColor(color);
            d2.setFillColor(color);
            dataSets.add(d2);

        ArrayList<String> xLabels = new ArrayList<>();
        xLabels.add("January");
        xLabels.add("February");
        xLabels.add("March");


        XAxis xxAxis=chart.getXAxis();
        xxAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xLabels));

        LineData dat = new LineData(dataSets);
        chart.setData(dat);


        }
    public void scope3chart(View v) {


        LineChart chart;
        // seekBarY.setOnSeekBarChangeListener(this);

        chart = v.findViewById(R.id.chart3);
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


        LineDataSet d = new LineDataSet(values0, "Two Wheeler");
        LineDataSet d2 = new LineDataSet(values1, "Four Wheeler");
        LineDataSet d3 = new LineDataSet(values2, "Bus Emssions");

        //Toast.makeText(getActivity(),"d"+values3.toString(),LENGTH_LONG).show();

        LineDataSet temp=null;
        for(int i=0;i<=2;i++){
            if(i==0)
                temp=d;
            if(i==1)
                temp=d2;
            if(i==2)
                temp=d3;

            //temp.setDrawFilled(true);
            // Drawable drawable = ContextCompat.getDrawable(getActivity(), R.color.blue_800);
            //d.setFillDrawable(drawable);
            temp.setLineWidth(5.5f);
            temp.setCircleRadius(8f);


            int color = colors[i];
            temp.setColor(color);
            dataSets.add(temp);



        }


        ArrayList<String> xLabels = new ArrayList<>();
        xLabels.add("January");
        xLabels.add("February");
        xLabels.add("March");


        XAxis xxAxis=chart.getXAxis();
        xxAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xLabels));

        LineData dat = new LineData(dataSets);
        chart.setData(dat);


    }
    private final int[] colors = new int[] {
            ColorTemplate.VORDIPLOM_COLORS[0],
            ColorTemplate.VORDIPLOM_COLORS[1],
            ColorTemplate.VORDIPLOM_COLORS[2]
    };
}
