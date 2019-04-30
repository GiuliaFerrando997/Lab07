package it.polito.tdp.poweroutages;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.poweroutages.model.Model;
import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.PowerOutages;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
public class PowerOutagesController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Nerc> cbNERC;

    @FXML
    private TextField txtYears;

    @FXML
    private TextField txtHours;

    @FXML
    private Button btnWCA;

    @FXML
    private TextArea txtResult;

    @FXML
    void doSearchWCA(ActionEvent event) {
    	txtResult.clear();
    	Nerc zona = cbNERC.getValue();
    	if(zona==null)
    		txtResult.setText("Selezionare una regione");
    	else {
	    	try {
	    		int anni = Integer.parseInt(txtYears.getText());
	    		int ore = Integer.parseInt(txtHours.getText());
	    		List<PowerOutages> s = model.getResult(anni, ore, zona);
	    		txtResult.appendText("Numero di ore: "+model.getOre(s)+"\n"
	    				+ "Numero di persone: "+model.getPersone(s)+"\n");
	    		for(PowerOutages p : s) {
	    			txtResult.appendText(p.toString()+"\n");
	    		}
	    		
	    	} catch(NumberFormatException e) {
	    		txtResult.setText("Inserire un numero di anni e/o ore valido");
	    	} catch (RuntimeException e) {
	    		txtResult.setText("Errrrrore di connessione al database!");
	    	}
    	}

    }

    @FXML
    void initialize() {
        assert cbNERC != null : "fx:id=\"cbNERC\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert txtYears != null : "fx:id=\"txtYears\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert txtHours != null : "fx:id=\"txtHours\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert btnWCA != null : "fx:id=\"btnWCA\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        
        txtResult.setStyle("-fx-font-family: monospace");
    }

	public void setModel(Model model) {
		this.model=model;
		this.setComboItems();
		
	}

	private void setComboItems() {
		List<Nerc> nerc = model.getNercList();
		cbNERC.getItems().addAll(nerc);
		}
		
}