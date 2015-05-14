package my.home.pro.test;

import java.io.IOException;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;

/**
 *
 * @author Благодатских С.
 */
public class Worker extends Thread {

	private final int count;
	private final String url;
	private final TextArea infoPane;

	public Worker(int count, String url, TextArea infoPane) {
		this.count = count;
		this.url = url;
		this.infoPane = infoPane;
	}

	@Override
	public void run() {
		int failed = 0;
		int successfull = 0;
		long totalTime = 0;
		for (int i = 0; i < count; ++i) {
			HttpClient client = new HttpClient();
			GetMethod method = new GetMethod(url);
			try {
				long start = System.currentTimeMillis();
				int statusCode = client.executeMethod(method);
				if (statusCode != HttpStatus.SC_OK) {
					++failed;
				} else {
					++successfull;
					totalTime += System.currentTimeMillis() - start;
				}
			} catch (HttpException e) {
				showInfo("Fatal protocol violation: " + e.getMessage());
			} catch (IOException e) {
				showInfo("Fatal transport error: " + e.getMessage());
			} finally {
				// Release the connection.
				method.releaseConnection();
			}
		}
		showInfo(String.format("count: %d\nfailed: %d\nsuccess: %d\ntotal time: %dms\n"
				+ "successful average time: %.2fms\n",
				count, failed, successfull, totalTime, totalTime / (double) successfull));
	}

	private void showInfo(String msg) {
		Platform.runLater(() -> infoPane.appendText(
				String.format("url: %s\n%s\n", url, msg)));
	}
}
