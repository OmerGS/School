package tri;

import pays.Pays;
import java.util.ArrayList;

public class TriParSelectionAlpha implements ITri {

    @Override
    public void trier(ArrayList<Pays> listePays) {
        int n = listePays.size();
        for (int i = 0; i < n - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < n; j++) {
                if (listePays.get(j).getNom().compareToIgnoreCase(listePays.get(min_idx).getNom()) < 0) {
                    min_idx = j;
                }
            }

            Pays temp = listePays.get(min_idx);
            listePays.set(min_idx, listePays.get(i));
            listePays.set(i, temp);
        }
    }
}
