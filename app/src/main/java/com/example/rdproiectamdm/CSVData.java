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
        this.nume = (params[0].equals("")? "Unknown":params[0]);
        this.regiune = (params[1].equals("")? "Unknown":params[1]);
        this.cazuriTotale = (params[2].equals("")? 0 : Integer.parseInt(params[2]));
        this.cazuriPerMilion = (params[3].equals("")? 0.0f: Float.parseFloat(params[3]));
        this.cazuriUltimele7zile = (params[4].equals("")? 0 : Integer.parseInt(params[4]));
        this.cazuriUltimele7zilePerMilion = (params[5].equals("")? 0.0f: Float.parseFloat(params[5]));
        this.cazuriUltimele24ore = (params[6].equals("")? 0 : Integer.parseInt(params[6]));
        this.mortiTotal = (params[7].equals("")? 0 : Integer.parseInt(params[7]));
        this.mortiPerMilion = (params[8].equals("")? 0.0f: Float.parseFloat(params[8]));
        this.mortiUltimele7zile = (params[9].equals("")? 0 : Integer.parseInt(params[9]));
        this.mortiUltimele7zilePerMilion = (params[10].equals("")? 0.0f: Float.parseFloat(params[10]));
        this.mortiUltimele24ore = (params[11].equals("")? 0 : Integer.parseInt(params[11]));
    }

    public String getNumeTara()
    {
        return this.nume;
    }

    public int getCazuriTotale() { return this.cazuriTotale; }

    public String getRegiune() {return this.regiune; }

    public int getDecese() {return this.mortiTotal; }

    public int getCazuriUltimele24ore() {return this.cazuriUltimele24ore; }

    public String getContinent () { return this.regiune; }

    public float getCazuriPerMilion() {return this.cazuriPerMilion; }

    public float getCazuri7zilePerMilion () { return this.cazuriUltimele7zilePerMilion; }

}
