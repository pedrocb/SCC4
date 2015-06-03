import desmoj.core.dist.LinearCongruentialRandomGenerator;

import java.util.Random;

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

    public static void test(double numeros[])
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



    public static void main(String[] args) {
        int n = 60000;
        int k = 42;
        double numeros[];
        LinearCongruentialRandomGenerator generator;
        for(int i=0;i<k;i++){
            numeros = new double[n];
            generator = new LinearCongruentialRandomGenerator(new Random().nextInt());
            for (int a = 0;a < numeros.length; a++) {
                numeros[a] = generator.nextDouble();
            }
            test(numeros);
        }
    }
}
