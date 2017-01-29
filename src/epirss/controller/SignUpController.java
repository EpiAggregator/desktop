package epirss.controller;

import epirss.utils.ApiRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.http.HttpResponse;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by dellam_a on 2/1/17.
 */
public class SignUpController implements Initializable {

    @FXML
    private Button back;
    @FXML
    private Button create;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;


    @FXML
    public void back(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/loginView.fxml"));
            Scene scene = new Scene(root);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void createAccount(ActionEvent event) {
        ApiRequest.getInstance().createAccount(email.getText(), password.getText(), (String response) -> {
                ApiRequest.getInstance().login(email.getText(), password.getText(), (String r) -> {
                        try {
                            Parent root = FXMLLoader.load(getClass().getResource("/views/rssView.fxml"));
                            Scene scene = new Scene(root);
                            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            appStage.setScene(scene);
                            appStage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                });
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        back.setOnAction(this::back);
        create.setOnAction(this::createAccount);
    }
}
