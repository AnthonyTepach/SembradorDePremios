import java.util.ArrayList;
import java.util.List;

public class Permutacion {


    /*public static void main(String[] args) {
        String[] elementos = "10 de Mayo de 2000\t30 de Diciembre de 1900\t11 de Mayo de 1966".split("\t");
        int n = 3;
        int r = elementos.length;
        Perm2(elementos, "", n, r);
    }*/
    List<String> dataSeeding = new ArrayList<>();
    protected void Perm2(String[] elementos, String s, int n, int r,String pregunta) {
        if (n == 0) {
            dataSeeding.add(pregunta+"\t"+s);
        } else {
            for (int i = 0; i < r; i++) {
                if (!s.contains(elementos[i])) { // Controla que no haya repeticiones
                    Perm2(elementos, s + elementos[i] + "\t", n - 1, r,pregunta);
                }
            }
        }
    }



}

