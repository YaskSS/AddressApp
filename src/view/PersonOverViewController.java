package view;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;
import model.Person;
import address.MainApp;

public class PersonOverViewController {
	
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;

    // Ссылка на главное приложение.
    private MainApp mainApp = new MainApp();

    /*
     * Конструктор.
     * Конструктор вызывается раньше метода initialize().
     */
    public PersonOverViewController() {
    }

    /*
     * Инициализация класса-контроллера. Этот метод вызывается автоматически
     * после того, как fxml-файл будет загружен.
     */
    
    @FXML
    public void initialize() {
        // Инициализация таблицы адресатов с двумя столбцами.
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
       // lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        lastNameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Person,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<Person, String> param) {
				// TODO Auto-generated method stub
				return param.getValue().lastNameProperty();
			}
		});
    }

    /*
     * Вызывается главным приложением, которое даёт на себя ссылку.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Добавление в таблицу данных из наблюдаемого списка
        personTable.setItems(mainApp.getPersonData());
    }

}
