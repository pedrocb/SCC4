import desmoj.core.dist.LinearCongruentialRandomGenerator;

public class Main {

    static class KS
    {

        double[] quickSort(double[] array,int inicio, int fim)
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

        void test(double numeros[], double alfa)
        {
            numeros = quickSort(numeros,0,numeros.length-1);
            double array1[] = new double[numeros.length], array2[] = new double[numeros.length];
            int n = numeros.length;
            for(int i=0;i<n;i++)
            {
                array1[i] = ((i+1)/n)-numeros[i];
                array2[i] = (numeros[i]-i)/n;
            }

            double maxArray1 = array1[0];
            for(int i=0;i<n;i++)
            {
                if(maxArray1<array1[i])
                {
                    maxArray1 = array1[i];
                }
            }

            double maxArray2 = array2[0];
            for(int i=0;i<n;i++)
            {
                if(maxArray2<array2[i])
                {
                    maxArray2 = array2[i];
                }
            }

            if(alfa>Math.max(maxArray1,maxArray2))
            {
                System.out.println("Numeros uniformes aceites!");
            }
            else
            {
                System.out.println("Numeros uniformes nao aceites!");
            }
        }
    }


    public static void main(String[] args) {
        double numeros[] = new double[60000];
        LinearCongruentialRandomGenerator generator = new LinearCongruentialRandomGenerator();
        for(int i=0;i<numeros.length;i++) {
            numeros[i] = generator.nextDouble();
        }
        KS ks = new KS();
        ks.test(numeros,0.05);
    }
}
