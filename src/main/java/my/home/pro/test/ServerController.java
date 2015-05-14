package my.home.pro.test;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

public class ServerController implements Initializable {

	@FXML
	private TextField urlEdit;

	@FXML
	private Spinner countEdit;

	@FXML 
	private TitledPane pane;

	@FXML
	private void delButtonClicked() {
		VBox box = (VBox)pane.getParent();
		box.getChildren().remove(pane);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		countEdit.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100000));
	}

	public String getUrl() {
		return urlEdit.getText();
	}

	public int getCount() {
		int count = 0;
		try {
			count  = Integer.valueOf(countEdit.getEditor().getText());
		} catch (Exception e) {

		}
		return count;
	}
}
