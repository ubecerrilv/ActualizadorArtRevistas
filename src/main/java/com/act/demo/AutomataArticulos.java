package com.act.demo;

import java.util.ArrayList;
import java.util.HashSet;

public class AutomataArticulos {
    /*
     * ATRIBUTOS
     * */
    private String estado="Q0";
    private final char[] A = {'A','a','B','b','C','c','D','d','E','e','F','f','G','g','H','h','I','i','J','j','K','k','L','l','M','m','N','n',
            'Ñ','ñ','O','o','P','p','Q','q','R','r','S','s','T','t','U','u','V','v','W','w','X','x','Y','y','Z','z','_','&','-'};
    private final char[] N = {'0','1','2','3','4','5','6','7','8','9'};
    //private final char[] A2 = {'A','a','B','b','C','c','D','d','E','e','F','f','G','g','H','h','I','i','J','j','K','k','L','l','M','m','N','n',
    //'Ñ','ñ','O','o','P','p','Q','q','R','r','S','s','T','t','U','u','V','v','W','w','X','x','Y','y','Z','z'};

    private String cor = "";
    private ArrayList<Character> numeros = new ArrayList<Character>();
    private HashSet<String> articulos = new HashSet<String>();

    public HashSet<String> articulos (String html, String inicio){
        //RELLENAR EL ARRAYLIST
        for(char c : N) {
            numeros.add(c);
        }

        //ARREGLO PARA PODER VERIFICAR EL INICIO DE LA CADENA
        char urlInicio [] = inicio.toCharArray();

        //ARREGLO DE TODO EL CONTENIDO
        char htmlArray [] = html.toCharArray();

        for (int i=0; i<htmlArray.length;i++){
            //Verificar si ya está completo
            //Todo change state
            if((htmlArray[i]==' ' || htmlArray[i]=='>' || htmlArray[i]=='"'|| htmlArray[i]==',') && (this.estado.compareToIgnoreCase("Q15")==0)) {
                articulos.add(cor);
                cor = "";
            }

            switch (this.estado) {
                case "Q0":
                    if (htmlArray[i] == urlInicio[0]) {

                        for (int j = 0; j < urlInicio.length; j++) {

                            cor += htmlArray[i + j];
                            //System.out.print(htmlArray[i+j]);

                            if (urlInicio[j] != htmlArray[i + j]) {
                                cor = "";
                                break;
                            } else if (j == urlInicio.length - 1) {
                                i += j;
                                this.estado = "Q1";
                            }
                        }
                        //System.out.print("Estado: "+estado+"\n");
                    } else {
                        this.estado = "Q0";
                        cor = "";
                    }
                    break;

                //Verificar resto del url
                case "Q1":
                    if(htmlArray[i]=='/'){
                        this.estado="Q2";
                        cor+=htmlArray[i];
                    }else {
                        this.estado="Q0";
                        cor="";
                    }
                    break;

                case "Q2":
                    if(htmlArray[i]=='a'){
                        this.estado="Q3";
                        cor+=htmlArray[i];
                    }else {
                        this.estado="Q0";
                        cor="";
                    }
                    break;

                case "Q3":
                    if(htmlArray[i]=='r'){
                        this.estado="Q4";
                        cor+=htmlArray[i];
                    }else {
                        this.estado="Q0";
                        cor="";
                    }
                    break;

                case "Q4":
                    if(htmlArray[i]=='t'){
                        this.estado="Q5";
                        cor+=htmlArray[i];
                    }else {
                        this.estado="Q0";
                        cor="";
                    }
                    break;

                case "Q5":
                    if(htmlArray[i]=='i'){
                        this.estado="Q6";
                        cor+=htmlArray[i];
                    }else {
                        this.estado="Q0";
                        cor="";
                    }
                    break;

                case "Q6":
                    if(htmlArray[i]=='c'){
                        this.estado="Q7";
                        cor+=htmlArray[i];
                    }else {
                        this.estado="Q0";
                        cor="";
                    }
                    break;

                case "Q7":
                    if(htmlArray[i]=='l'){
                        this.estado="Q8";
                        cor+=htmlArray[i];
                    }else {
                        this.estado="Q0";
                        cor="";
                    }
                    break;

                case "Q8":
                    if(htmlArray[i]=='e'){
                        this.estado="Q9";
                        cor+=htmlArray[i];
                    }else {
                        this.estado="Q0";
                        cor="";
                    }
                    break;

                case "Q9":
                    if(htmlArray[i]=='/'){
                        this.estado="Q10";
                        cor+=htmlArray[i];
                    }else {
                        this.estado="Q0";
                        cor="";
                    }
                    break;

                case "Q10":
                    if(htmlArray[i]=='v'){
                        this.estado="Q11";
                        cor+=htmlArray[i];
                    }else {
                        this.estado="Q0";
                        cor="";
                    }
                    break;

                case "Q11":
                    if(htmlArray[i]=='i'){
                        this.estado="Q12";
                        cor+=htmlArray[i];
                    }else {
                        this.estado="Q0";
                        cor="";
                    }
                    break;

                case "Q12":
                    if(htmlArray[i]=='e'){
                        this.estado="Q13";
                        cor+=htmlArray[i];
                    }else {
                        this.estado="Q0";
                        cor="";
                    }
                    break;

                case "Q13":
                    if(htmlArray[i]=='w'){
                        this.estado="Q14";
                        cor+=htmlArray[i];
                    }else {
                        this.estado="Q0";
                        cor="";
                    }
                    break;

                case "Q14":
                    if(htmlArray[i]=='/'){
                        this.estado="Q15";
                        cor+=htmlArray[i];
                    }else {
                        this.estado="Q0";
                        cor="";
                    }
                    break;

                case "Q15":
                    //If it's a number
                    if(numeros.contains(htmlArray[i])){
                        this.estado="Q15";
                        cor+=htmlArray[i];
                    }else {
                        this.estado="Q0";
                        cor="";
                    }
                    break;
            }//End switch

        }//End for

        return articulos;
    }
}
