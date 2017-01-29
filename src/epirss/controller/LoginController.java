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
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by dellam_a on 2/1/17.
 */
public class LoginController implements Initializable {

    @FXML
    private Button signIn;
    @FXML
    private Button signUp;

    @FXML
    private TextField email;
    @FXML
    private PasswordField password;

    @FXML
    private void login(ActionEvent event) {

        ApiRequest.getInstance().login(email.getText(), password.getText(), (String response) -> {
            try {
                JSONObject obj = new JSONObject(response);
                ApiRequest.Token = obj.getString("token");
            } catch (JSONException e) {
                e.printStackTrace();
            }
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
    }

    @FXML
    private void createAccount(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/signUpView.fxml"));
            Scene scene = new Scene(root);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        signUp.setOnAction(this::createAccount);
        signIn.setOnAction(this::login);
    }
}
