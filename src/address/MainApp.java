package address;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Person;
import view.PersonOverViewController;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	private ObservableList<Person> personData = FXCollections.observableArrayList();

	public MainApp() {
		personData.add(new Person("Hans", "Muster"));
		personData.add(new Person("Ruth", "Mueller"));
		personData.add(new Person("Heinz", "Kurz"));
		personData.add(new Person("Cornelia", "Meier"));
		personData.add(new Person("Werner", "Meyer"));
		personData.add(new Person("Lydia", "Kunz"));
		personData.add(new Person("Anna", "Best"));
		personData.add(new Person("Stefan", "Meier"));
		personData.add(new Person("Martin", "Mueller"));
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("AddressApp");

		initRootLayout();

		showPersonOverview();
	}

	/*
	 * Инициализирует корневой макет.
	 */
	public void initRootLayout() {
		try {
			// Загружаем корневой макет из fxml файла.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Отображаем сцену, содержащую корневой макет.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Показывает в корневом макете сведения об адресатах.
	 */
	public void showPersonOverview() {
		try {
			// Загружаем сведения об адресатах.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/view/PersonOverView.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();

			// Помещаем сведения об адресатах в центр корневого макета.
			rootLayout.setCenter(personOverview);

			// Даём контроллеру доступ к главному приложению.
			PersonOverViewController controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Возвращает главную сцену.
	 * 
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	/*
	 * Возвращает данные в виде наблюдаемого списка адресатов.
	 * 
	 * @return
	 */
	public ObservableList<Person> getPersonData() {
		return personData;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
