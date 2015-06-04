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
            quickSort(array,i,fim);
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


    public static void main(String[] args) {
        //Exercicio 1
        int n = 60000;
        int k = 42;
        double numeros[];
        LinearCongruentialRandomGenerator generator;
        System.out.println("Exercicio 1");
        for(int i=0;i<k;i++){
            numeros = new double[n];
            generator = new LinearCongruentialRandomGenerator(new Random().nextInt());
            for (int a = 0;a < numeros.length; a++) {
                numeros[a] = generator.nextDouble();
            }
            kstest(numeros);
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
        System.out.println("Exercicio 3");
        double[] machines1 = new double[200];
        readvalues(machines1,"Machines.txt");
        double[] machines2 = new double[200];
        readvalues(machines2,"Machines2.txt");
        kwtest(machines1,machines2);
    }
}
