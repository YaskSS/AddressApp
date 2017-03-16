package view;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;
import model.Person;
import util.DateUtil;
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

	// ������ �� ������� ����������.
	private MainApp mainApp = new MainApp();

	/*
	 * �����������. ����������� ���������� ������ ������ initialize().
	 */
	public PersonOverViewController() {
	}

	/*
	 * ������������� ������-�����������. ���� ����� ���������� �������������
	 * ����� ����, ��� fxml-���� ����� ��������.
	 */

	@FXML
	public void initialize() {
		// ������������� ������� ��������� � ����� ���������.
		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
		// lastNameColumn.setCellValueFactory(cellData ->
		// cellData.getValue().lastNameProperty());
		lastNameColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Person, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<Person, String> param) {
						// TODO Auto-generated method stub
						return param.getValue().lastNameProperty();
					}
				});
		// ������� �������������� ���������� �� ��������.
		showPersonDetails(null);

		// ������� ��������� ������, � ��� ��������� ����������
		// �������������� ���������� �� ��������.
		personTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showPersonDetails(newValue));
	}

	/*
	 * ���������� ������� �����������, ������� ��� �� ���� ������.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		// ���������� � ������� ������ �� ������������ ������
		personTable.setItems(mainApp.getPersonData());
	}

	/**
	 * ��������� ��� ��������� ����, ��������� ����������� �� ��������. ����
	 * ��������� ������� = null, �� ��� ��������� ���� ���������.
	 * 
	 * @param person
	 *            � ������� ���� Person ��� null
	 */
	private void showPersonDetails(Person person) {
		if (person != null) {
			// ��������� ����� ����������� �� ������� person.
			firstNameLabel.setText(person.getFirstName());
			lastNameLabel.setText(person.getLastName());
			streetLabel.setText(person.getStreet());
			postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
			cityLabel.setText(person.getCity());
			birthdayLabel.setText(DateUtil.format(person.getBirthday()));

		} else {
			// ���� Person = null, �� ������� ���� �����.
			firstNameLabel.setText("");
			lastNameLabel.setText("");
			streetLabel.setText("");
			postalCodeLabel.setText("");
			cityLabel.setText("");
			birthdayLabel.setText("");
		}
	}

	/*
	 * ����������, ����� ������������ ������� �� ������ ��������.
	 */
	@FXML
	private void handleDeletePerson() {
		int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			personTable.getItems().remove(selectedIndex);
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Person Selected");
			alert.setContentText("Please select a person in the table.");

			alert.showAndWait();
		}
	}
	
	/*
	 * ����������, ����� ������������ ������� �� ������ New...
	 * ��������� ���������� ���� � �������������� ����������� ������ ��������.
	 */
	@FXML
	private void handleNewPerson() {
	    Person tempPerson = new Person();
	    boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
	    if (okClicked) {
	        mainApp.getPersonData().add(tempPerson);
	    }
	}

	/*
	 * ����������, ����� ������������ ������� �� ������ Edit...
	 * ��������� ���������� ���� ��� ��������� ���������� ��������.
	 */
	@FXML
	private void handleEditPerson() {
	    Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
	    if (selectedPerson != null) {
	        boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
	        if (okClicked) {
	            showPersonDetails(selectedPerson);
	        }

	    } else {
	        // ������ �� �������.
	        Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(mainApp.getPrimaryStage());
	        alert.setTitle("No Selection");
	        alert.setHeaderText("No Person Selected");
	        alert.setContentText("Please select a person in the table.");

	        alert.showAndWait();
	    }
	}
}
