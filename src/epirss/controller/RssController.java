package epirss.controller;

import epirss.utils.ApiRequest;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javax.swing.text.html.ListView;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by dellam_a on 1/12/17.
 */
public class RssController implements Initializable {
    @FXML
    public ListView rssList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        ApiRequest.getInstance().getFeed();
    }
}
