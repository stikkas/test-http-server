package my.home.pro.test;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Благодатских С.
 */
public class MainController implements Initializable {

	private MainApp app;

	@FXML
	private TextArea resultPane;

	@FXML
	private VBox paneBox;

	@FXML
	private void addButtonClicked() {
		try {
			paneBox.getChildren().add(app.createPane());
		} catch (IOException ex) {
			resultPane.appendText(ex.getMessage());
		}
	}

	@FXML
	private void launchButtonClicked() {
		paneBox.getChildren().forEach(p -> {
			TitledPane pane = (TitledPane) p;
			Integer server = Integer.valueOf(pane.getText().split(" ")[1]);
			ServerController controller = app.getController(server);
			new Worker(controller.getCount(), controller.getUrl(), resultPane).start();
		});
	}

	public TextArea getResultPane() {
		return resultPane;
	}

	public VBox getPaneBox() {
		return paneBox;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}

	public void setApp(MainApp app) {
		this.app = app;
		addButtonClicked();
	}
}
