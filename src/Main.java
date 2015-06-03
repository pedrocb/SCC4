import desmoj.core.dist.LinearCongruentialRandomGenerator;

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
        }
    }

    public static void main(String[] args) {
        double numeros[] = new double[60000];
        LinearCongruentialRandomGenerator generator = new LinearCongruentialRandomGenerator();
        for(int i=0;i<numeros.length;i++) {
            numeros[i] = generator.nextDouble();
        }

    }
}
