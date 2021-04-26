package com.example.rdproiectamdm;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.textfield.TextInputEditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FirstFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_first, container, false);

        Vector<CSVData> objectList = new Vector<CSVData>();
        try
        {
            objectList = UploadData();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


        // Pie Charts

        initPieCharts(rootView);

        chart1.setData(generatePieDataContinent(objectList, "Europe", 10));
        chart2.setData(generatePieDataContinent(objectList, "Western Pacific", 5));
        chart3.setData(generatePieDataContinent(objectList, "Americas", 5));
        chart4.setData(generatePieDataContinent(objectList, "Africa", 5));
        chart5.setData(generatePieTops(objectList, "TOP3TOTAL"));
        chart6.setData(generatePieTops(objectList, "TOP3DEATH"));
        chart7.setData(generatePieTops(objectList, "TOP324H"));

        TextView CNT = rootView.findViewById(R.id.textView);
        final Random myRandom = new Random();
        AtomicInteger sum = new AtomicInteger(getTotalCasesSum(objectList));

        Thread counterThread=new Thread(()->{
            try{
                while(true)
                {
                    sum.addAndGet(myRandom.nextInt(500));
                    CNT.setText("Numar total de cazuri pe planeta: "  + sum.toString());
                    Thread.sleep(1000);
                }
            }
            catch (Exception e){

            }
        });
        counterThread.start();

        Button btnCautare = rootView.findViewById(R.id.buttonCautare);
        EditText txt = rootView.findViewById(R.id.editCountryName);


        Vector<CSVData> finalObjectList = objectList;
        btnCautare.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                DisplayInfos(rootView, finalObjectList, txt.getText().toString());
                chart8.setData(generatePieDataIndividual(finalObjectList, txt.getText().toString()));
                chart8.setUsePercentValues(true);
            }
        });

        return rootView;
    }

    public void DisplayInfos(View viewID, Vector<CSVData> function_input, String countryName)
    {
        TextView TWInfo = viewID.findViewById(R.id.textViewInfo);
        for(CSVData country : function_input)
        {
            if (country.getNumeTara().equals(countryName))
            {
                TWInfo.setText(
                        "Tara: " + country.getNumeTara() + "\n" +
                        "Continent: " + country.getContinent() +  "\n" +
                        "Cazuri totale: " + country.getCazuriTotale() +  "\n" +
                        "Cazuri in ultimele 24 de ore: " + country.getCazuriUltimele24ore() + "\n" +
                         "Cazuri pe milionul de locuitori: " + country.getCazuriPerMilion() + "\n" +
                         "Incidenta in ultimele 7 zile per milion: " + country.getCazuri7zilePerMilion() + "\n" +
                        "Decese totale: " + country.getDecese() + "\n"
                );
            }
        }
    }

    public int getTotalCasesSum(Vector<CSVData> function_input)
    {
        for(CSVData looper : function_input)
        {
            if(looper.getNumeTara().equals("Global"))
            {
                return looper.getCazuriTotale();
            }
        }
        return 0;
    }

    @SuppressWarnings("FieldCanBeLocal")
    private Typeface tf;
    private PieChart chart1;
    private PieChart chart2;
    private PieChart chart3;
    private PieChart chart4;
    private PieChart chart5;
    private PieChart chart6;
    private PieChart chart7;
    private PieChart chart8;

    public void initPieCharts(View viewID)
    {
        Vector<PieChart> charts = new Vector<PieChart>();
        chart1 = viewID.findViewById(R.id.chart1); charts.add(chart1);
        chart2 = viewID.findViewById(R.id.chart2); charts.add(chart2);
        chart3 = viewID.findViewById(R.id.chart3); charts.add(chart3);
        chart4 = viewID.findViewById(R.id.chart4); charts.add(chart4);
        chart5 = viewID.findViewById(R.id.chart5); charts.add(chart5);
        chart6 = viewID.findViewById(R.id.chart6); charts.add(chart6);
        chart7 = viewID.findViewById(R.id.chart7); charts.add(chart7);
        chart8 = viewID.findViewById(R.id.chart8); charts.add(chart8);

        for(PieChart current_chart : charts)
        {

            current_chart.getDescription().setEnabled(false);
            current_chart.setHoleRadius(15f);
            current_chart.setTransparentCircleRadius(15f);
            Legend l = current_chart.getLegend();
            l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
            l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
            l.setOrientation(Legend.LegendOrientation.VERTICAL);
            l.setDrawInside(false);
        }
    }

    protected  PieData generatePieDataIndividual(Vector<CSVData> function_input, String tara)
    {
        ArrayList<PieEntry> entries1 = new ArrayList<>();
        for(CSVData object : function_input)
        {
            if(object.getNumeTara().equals(tara))
            {
                entries1.add(new PieEntry(object.getDecese(), "Decese totale (%)"));
                entries1.add(new PieEntry(object.getCazuriTotale() - object.getDecese(), "Pacienti vindecati (%)"));
            }
        }

        PieDataSet ds1 = new PieDataSet(entries1, "");
        ds1.setColors(ColorTemplate.MATERIAL_COLORS);
        ds1.setSliceSpace(1f);
        ds1.setValueTextColor(Color.BLACK);
        ds1.setValueTextSize(12f);

        PieData d = new PieData(ds1);
        d.setValueTypeface(tf);

        return d;
    }

    protected PieData generatePieDataContinent(Vector<CSVData> function_input, String continent, int counter)
    {
        ArrayList<PieEntry> entries1 = new ArrayList<>();
        int local_counter = 0;
        for(CSVData object : function_input)
        {
            if(object.getRegiune().equals(continent) && local_counter < counter)
            {
                entries1.add(new PieEntry(object.getCazuriTotale(), object.getNumeTara()));
                local_counter++;
            }
        }

        PieDataSet ds1 = new PieDataSet(entries1, "Lista Tari");
        ds1.setColors(ColorTemplate.JOYFUL_COLORS);
        ds1.setSliceSpace(1f);
        ds1.setValueTextColor(Color.BLACK);
        ds1.setValueTextSize(12f);

        PieData d = new PieData(ds1);
        d.setValueTypeface(tf);

        return d;
    }

    protected PieData generatePieTops(Vector<CSVData> function_input, String Query)
    {
        ArrayList<PieEntry> entries1 = new ArrayList<>();

        int[] maxs = {0, 0, 0};
        String[] maxs_name = {"", "", ""};

        if(Query.equals("TOP3TOTAL"))
        {
            for(CSVData object : function_input)
            {
                if(!object.getNumeTara().equals("Global"))
                {
                    for (int loop = 0; 3 > loop; loop++)
                    {
                        if (object.getCazuriTotale() > maxs[loop])
                        {
                            maxs[loop] = object.getCazuriTotale();
                            maxs_name[loop] = object.getNumeTara();
                            break;
                        }
                    }
                }
            }
        }
        else if(Query.equals("TOP3DEATH"))
        {
            for(CSVData object : function_input)
            {
                if(!object.getNumeTara().equals("Global"))
                {
                    for (int loop = 0; 3 > loop; loop++)
                    {
                        if (object.getDecese() > maxs[loop])
                        {
                            maxs[loop] = object.getDecese();
                            maxs_name[loop] = object.getNumeTara();
                            break;
                        }
                    }
                }
            }
        }
        else if(Query.equals("TOP324H"))
        {
            for(CSVData object : function_input)
            {
                if(!object.getNumeTara().equals("Global"))
                {
                    for (int loop = 0; 3 > loop; loop++)
                    {
                        if (object.getCazuriUltimele24ore() > maxs[loop])
                        {
                            maxs[loop] = object.getCazuriUltimele24ore();
                            maxs_name[loop] = object.getNumeTara();
                            break;
                        }
                    }
                }
            }
        }

        for(int loop = 0; 3 > loop; loop++)
        {
            entries1.add(new PieEntry(maxs[loop], maxs_name[loop]));
        }

        PieDataSet ds1 = new PieDataSet(entries1, "Lista Tari");
        ds1.setColors(ColorTemplate.MATERIAL_COLORS);
        ds1.setSliceSpace(1f);
        ds1.setValueTextColor(Color.BLACK);
        ds1.setValueTextSize(12f);

        PieData d = new PieData(ds1);
        d.setValueTypeface(tf);

        return d;
    }

    public Vector<CSVData> UploadData() throws IOException
    {
        Vector<CSVData> ListaTari = new Vector<CSVData>();
        InputStream input = getResources().openRawResource(R.raw.input);
        BufferedReader reader = new BufferedReader( new InputStreamReader(input, Charset.forName("UTF-8")));
        String line = "";
        int lineCounter = 0;
        try
        {
            while((line = reader.readLine()) != null )
            {
                if(lineCounter == 0)
                {
                    lineCounter++;
                    continue;
                }
                else {
                    CSVData tara = new CSVData(line.split(","));
                    ListaTari.addElement(tara);
                }
                lineCounter++;
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return ListaTari;
    }


}