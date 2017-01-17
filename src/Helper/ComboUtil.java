package Helper;

import javafx.scene.control.ComboBox;

/**
 * Created by feder on 07/01/2017.
 */
public class ComboUtil {

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
