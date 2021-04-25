package com.example.rdproiectamdm;

import java.util.List;

public class CSVData
{
    String nume;
    String regiune;
    int cazuriTotale;
    float cazuriPerMilion;
    int cazuriUltimele7zile;
    float cazuriUltimele7zilePerMilion;
    int cazuriUltimele24ore;
    int mortiTotal;
    float mortiPerMilion;
    int mortiUltimele7zile;
    float mortiUltimele7zilePerMilion;
    int mortiUltimele24ore;

    List<CSVData> ListaTari;

    public CSVData(String[] params)
    {
        // Despachetare
        this.nume = params[0];
        this.regiune = params[1];
        this.cazuriTotale = Integer.parseInt(params[2]);
        this.cazuriPerMilion = Float.parseFloat(params[3]);
        this.cazuriUltimele7zile = Integer.parseInt(params[4]);
        this.cazuriUltimele7zilePerMilion = Float.parseFloat(params[5]);
        this.cazuriUltimele24ore = Integer.parseInt(params[6]);;
        this.mortiTotal = Integer.parseInt(params[7]);;
        this.mortiPerMilion = Float.parseFloat(params[8]);
        this.mortiUltimele7zile = Integer.parseInt(params[9]);
        this.mortiUltimele7zilePerMilion = Float.parseFloat(params[10]);;
        this.mortiUltimele24ore = Integer.parseInt(params[11]);
    }
}
