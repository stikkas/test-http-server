package my.home.pro.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	private static int count = 0;
	private static Map<Integer, ServerController> controllers = new HashMap<>();

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));
		Parent root = (Parent) loader.load();
		MainController ctrl = (MainController) loader.getController();
		ctrl.setApp(this);

		Scene scene = new Scene(root);

		stage.setTitle("Benchmark for http servers");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public TitledPane createPane() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));
		TitledPane pane = (TitledPane) loader.load();
		pane.setText("Server " + ++count);
		controllers.put(count, loader.getController());
		return pane;
	}

	public ServerController getController(Integer server) {
		return controllers.get(server);
	}
}
