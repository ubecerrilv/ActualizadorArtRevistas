package com.act.demo;

import java.util.ArrayList;
import java.util.HashSet;

public class AutomataRevistas {
    /*
	 * ATRIBUTOS
	 * */
	private String estado="Q0";
	private final char[] A = {'A','a','B','b','C','c','D','d','E','e','F','f','G','g','H','h','I','i','J','j','K','k','L','l','M','m','N','n',
			'Ñ','ñ','O','o','P','p','Q','q','R','r','S','s','T','t','U','u','V','v','W','w','X','x','Y','y','Z','z','_','&','-',
			'0','1','2','3','4','5','6','7','8','9'};
	//private final char[] A2 = {'A','a','B','b','C','c','D','d','E','e','F','f','G','g','H','h','I','i','J','j','K','k','L','l','M','m','N','n',
			//'Ñ','ñ','O','o','P','p','Q','q','R','r','S','s','T','t','U','u','V','v','W','w','X','x','Y','y','Z','z'};
	private ArrayList<Character> alfabeto = new ArrayList<Character>();
	private String cor = "";
	private HashSet<String> revistas = new HashSet<String>();
	

	/*
	 * METODOS
	 * */
    public HashSet<String> revistas (String html, String inicio){
        //RELLENAR EL ARRAYLIST
		for(int j =0; j<A.length;j++) {
			alfabeto.add(A[j]);
		}

        //ARREGLO PARA PODER VERIFICAR EL INICIO DE LA CADENA
        char urlInicio [] = inicio.toCharArray();

        //ARREGLO DE TODO EL CONTENIDO
        char htmlArray [] = html.toCharArray();

        //RECORRER TODO EL ARREGLO PARA OBTENER LOS URLS
        for(int i = 0; i<htmlArray.length; i++){
            //VERIFICAR SI ES ESTADO FINAL Y FIN DE PAGINA, CASO POSITIVO AGREGAR AL ARRAY
			if((htmlArray[i]==' ' || htmlArray[i]=='>' || htmlArray[i]=='"'|| htmlArray[i]==',') && (this.estado.compareToIgnoreCase("Q9")==0)) {
				revistas.add(cor);
				cor="";
			}

            //AUTOMATA
            switch (this.estado) {
                case "Q0":
                    if(htmlArray[i]=='h'){
                        this.estado="Q1";
                        cor+=htmlArray[i];
                    }else {	
                        this.estado="Q0";
                        cor="";
                    }
                    break;

                case "Q1":
                    if(htmlArray[i]=='t'){
                        this.estado="Q2";
                        cor+=htmlArray[i];
                    }else {	
                        this.estado="Q0";
                        cor="";
                    }
                    break;

                case "Q2":
                    if(htmlArray[i]=='t'){
                        this.estado="Q3";
                        cor+=htmlArray[i];
                    }else {	
                        this.estado="Q0";
                        cor="";
                    }
                    break;

                case "Q3":
                    if(htmlArray[i]=='p'){
                        this.estado="Q4";
                        cor+=htmlArray[i];
                    }else {	
                        this.estado="Q0";
                        cor="";
                    }
                    break;

                case "Q4":
                    if(htmlArray[i]=='s'){
                        this.estado="Q5";
                        cor+=htmlArray[i];
                    }else if (htmlArray[i]==':'){
                        this.estado="Q6";
                        cor+=htmlArray[i];
                    }else {	
                        this.estado="Q0";
                        cor="";
                    }
                    break;
                
                case "Q5":
                    if (htmlArray[i]==':'){
                        this.estado="Q6";
                        cor+=htmlArray[i];
                    }else {	
                        this.estado="Q0";
                        cor="";
                    }
                    break;

                case "Q6":
                    if(htmlArray[i]=='/'){
                        this.estado="Q7";
                        cor+=htmlArray[i];
                    }else {	
                        this.estado="Q0";
                        cor="";
                    }
                    break;

                case "Q7":
                    if(htmlArray[i]=='/'){
                        this.estado="Q8";
                        cor+=htmlArray[i];
                    }else {	
                        this.estado="Q0";
                        cor="";
                    }
                    break;

                case "Q8":
                    if(htmlArray[i]==urlInicio[0]){

                        for (int j = 0; j<urlInicio.length;j++){

                            cor+=htmlArray[i+j];
                            //System.out.print(htmlArray[i+j]);

                            if(urlInicio[j]!=htmlArray[i+j]){
                                cor="";
                                break;
                            }else if(j == urlInicio.length-1){
                                i+=j;
                                this.estado="Q9";
                            }
                        }
                        //System.out.print("Estado: "+estado+"\n");
                    }else {	
                        this.estado="Q0";
                        cor="";
                    }
                    break;

                case "Q9":
                    if(alfabeto.contains(htmlArray[i])){
                        cor+=htmlArray[i];
                    }else {
                        this.estado="Q0";
                        cor="";
                    }
                    break;

                default:
                    continue;
            }
        }

        return revistas;
    }
}
