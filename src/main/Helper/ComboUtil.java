package main.Helper;

import javafx.scene.control.ComboBox;

/**
 * Classe contenente utility per ComboBox
 * @author Federico Amici
 */
public class ComboUtil {

    /**
     * Ritorna la riga scelta nella comboBox
     * @param combo
     * @return La stringa corrispondente alla casella scelta, altrimenti null
     */
    public static String parseCombo(ComboBox combo) {
        if(combo.getItems().size() != 0) {
            if (combo.getSelectionModel().getSelectedItem() != null) {
                if (combo.getSelectionModel().getSelectedItem().equals("flussorighehp")) {
                    return "flussorighehp";
                } else if (combo.getSelectionModel().getSelectedItem().equals("flussocontinuo")) {
                    return "flussocontinuo";
                } else if (combo.getSelectionModel().getSelectedItem().equals("flussorighesp")) {
                    return "flussorighesp";
                }
            }
        }
        return null;
    }

}
