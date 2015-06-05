import desmoj.core.dist.LinearCongruentialRandomGenerator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static double[] quickSort(double[] array,int inicio, int fim)
    {
        if(inicio>=fim)
        {
            return array;
        }
        int meio = inicio + (fim-inicio)/2,i = inicio, j = fim;
        double pivo = array[meio];
        while(i <= j)
        {
            while(array[i]<pivo)
            {
                i++;
            }
            while(array[j]>pivo)
            {
                j--;
            }
            if(i<=j)
            {
                double tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
                i++;
                j--;
            }
        }
        if(inicio < j)
        {
            quickSort(array,inicio,j);
        }
        if(fim > i)
        {
            quickSort(array, i, fim);
        }
        return array;
    }

    public static void kstest(double numeros[])
    {
        numeros = quickSort(numeros,0,numeros.length-1);
        double array1[] = new double[numeros.length], array2[] = new double[numeros.length];
        double n = (double)numeros.length;
        for(int i=0;i<n;i++)
        {
            array1[i] =( (double)i)/n - numeros[i];
            array2[i] = numeros[i] - ((((double)i)-1)/n);
        }

        double dmais = array1[0];
        double dmenos = array2[0];
        for(int i=0;i<n;i++)
        {
            if(dmais<array1[i])
            {
                dmais = array1[i];
            }
            if(dmenos<array2[i])
            {
                dmenos = array2[i];
            }
        }
        double d = Math.max(dmais, dmenos);
        d = (Math.sqrt(n) + 0.12 + (0.11/Math.sqrt(n))) * d;
        if(d<1.358)
        {
            System.out.println("Teste aceite!");
        }
        else
        {
            System.out.println("Teste nao aceites!");
        }
    }

    public static void readvalues(double[] array,String file){
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line;
        try {
            for(int i=0;i<200;i++){
                line = br.readLine();
                array[i] = Double.parseDouble(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void substituteforranks(double[] array1,double[] array2){
        int aux1=0,aux2 = 0,rank = 1;
        double lastranked = -1;
        while(aux1<array1.length || aux2<array2.length){
            if(aux2>= array2.length || array1[aux1]<array2[aux2]){
                if(array1[aux1]>lastranked){
                    lastranked = array1[aux1];
                    array1[aux1] = rank;
                    rank++;
                }
                else {
                    array1[aux1] = rank;
                }
                aux1++;
            }
            else{
                if(array2[aux2]>lastranked){
                    lastranked = array2[aux2];
                    array2[aux2] = rank;
                    rank++;
                }
                else {
                    array2[aux2] = rank;
                }
                aux2++;
            }
        }
    }

    public static int sum(double[] array){
        int sum=0;
        for(int i=0;i<array.length;i++){
            sum+=array[i];
        }
        return sum;
    }

    public static void kwtest(double[] array1,double[] array2){
        array1 = quickSort(array1,0,array2.length-1);
        array2 = quickSort(array2,0,array2.length-1);
        substituteforranks(array1,array2);
        int sum1 = sum(array1);
        int sum2 = sum(array2);
        double n = array1.length + array2.length;
        double t = (12.0/(n*(n+1.0)))*((Math.pow(sum1,2.0)/array1.length)+(Math.pow(sum2,2.0)/array2.length)) - 3.0*(n+1.0);
        System.out.println(t);
        if(t<3.841){
            System.out.println("Teste passado");
        }
        else{
            System.out.println("Teste chumbado");
        }
    }

    public static int[] getruns(double[] array){
        int[] runs = new int[6];
        int tamanho=1;
        for(int i =0;i<6;i++){
            runs[i] = 0;
        }
        for(int i =1;i<array.length;i++){
            if(array[i]<=array[i-1]){
                if(tamanho>6){
                    tamanho = 6;
                }
                runs[tamanho-1]++;
                tamanho = 1;
            }
            else{
                tamanho++;
            }

        }
        if(tamanho>6){
            tamanho = 6;
        }
        runs[tamanho-1]++;
        return runs;
    }

    public static void runstest(double[] array){
        int runs[];
        double b[] = {1.0/6.0,5.0/24.0,11.0/120.0,19.0/720.0,29.0/5040.0,1.0/840.0};
        double[][] a =
                        {{4529.4,9044.9,13568.0,18091.0,22615.0,27892.0},
                        {9044.9,18097.0,27139.0,36187.0,45234.0,55789.0},
                        {13568.0,27139.0,40721.0,54281.0,67852.0,83685.0},
                        {18091.0,36187.0,54281.0,72414.0,90470.0,11580.0},
                        {22615.0,45234.0,67852.0,91470.0,113262.0,139476.0},
                        {27892.0,55789.0,83685.0,111580.0,139476.0,172860.0}};
        int n = array.length;
        runs = getruns(array);
        double sum =0;
        for(int i=0;i<6;i++){
            for(int j=0;j<6;j++){
                sum+=(a[i][j]*(runs[i] - n*b[i])*(runs[j]-n*b[j]));
            }
        }
        double r = sum/n;
        if(r<12.592){
            System.out.println("Passou no runstest");
        }
        else{
            System.out.println("Não passou no runstest");
        }
    }

    public static double geta0(double array[]){
        double sum =0;
        double sum2 = 0;
        int n = array.length;
        for(int i =0;i<n;i++){
            sum+= ((Math.pow(Math.log(array[i]),2)));
            sum2+= Math.log(array[i]);
        }
        double aux = Math.pow(((sum - (Math.pow(sum2,2)/n))*(6.0/Math.pow(Math.PI,2)))/(n-1),-1.0/2.0);
        return aux;
    }

    public static double geta(double array[]){
        double sum = 0;
        for(int i=0;i<array.length;i++){
            sum+= Math.log(array[i]);
        }
        return sum/array.length;
    }

    public static void findmle(double array[]){
        double a0 = geta0(array);
        double ak,ak1;
        double aux = a0;
        double a = geta(array);
        int n = array.length;
        do{
            ak = aux;
            double c=0;
            double b=0;
            double h=0;
            for(int i=0;i<n;i++){
                c+=(Math.pow(array[i],ak)*Math.log(array[i]));
                b+=Math.pow(array[i],ak);
                h+= (Math.pow(array[i],ak)*(Math.log(array[i])));
            }
            ak1 = ak + ((a + 1/ak - c/b)/(1/Math.pow(ak,2) + (b*h - Math.pow(c,2))/Math.pow(b,2)));
            aux = ak1;
        } while(Math.abs(ak-ak1) < Math.pow(10,-3));

        System.out.println("Alpha = " + ak1);
        double beta = 0;
        for(int i=0;i<n;i++){
            beta+=Math.pow(array[i],ak1);
        }
        beta = Math.pow((beta/n),1.0/ak1);
        System.out.println("Beta = " + beta);
    }


    public static void main(String[] args) {
        //Exercicio 1
        int n = 600000;
        int k = 42;
        double numeros[];
        LinearCongruentialRandomGenerator generator;
        System.out.println("Exercicio 1");
        for(int i=0;i<k;i++){
            numeros = new double[n];
            generator = new LinearCongruentialRandomGenerator(i);
            for (int a = 0;a < numeros.length; a++) {
                numeros[a] = generator.nextDouble();
            }
            runstest(numeros);
            //kstest(numeros);
        }


        //Exercicio 2
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        System.out.println("Exercicio 2");
        LinearCongruentialRandomGenerator generator2;
        for(int i=0;i<k;i++){
            numeros = new double[n];
            generator = new LinearCongruentialRandomGenerator(new Random().nextInt());
            generator2 = new LinearCongruentialRandomGenerator(new Random().nextInt());
            for (int a = 0;a < numeros.length; a++) {
                numeros[a] = Math.max(generator.nextDouble(),generator2.nextDouble());
            }
            kstest(numeros);
        }

        //Exercicio 3
        scanner.nextLine();
        System.out.println("Exercicio 3 a)");
        double[] machines1 = new double[200];
        readvalues(machines1, "Machines.txt");
        double[] machines2 = new double[200];
        readvalues(machines2, "Machines2.txt");
        kwtest(machines1, machines2);

        scanner.nextLine();
        machines1 = new double[200];
        readvalues(machines1, "Machines.txt");
        machines2 = new double[200];
        readvalues(machines2, "Machines2.txt");
        System.out.println("Exercicio 3 b)");
        System.out.println("Maquina 1:");
        findmle(machines1);
        System.out.println("Maquina 2:");
        findmle(machines2);
    }
}
