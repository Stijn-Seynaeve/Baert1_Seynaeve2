/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.controller;

import databag.Lid;
import datatype.Geslacht;
import datatype.Rijksregisternummer;
import exception.ApplicationException;
import exception.DBException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import transactie.FietsTrans;
import transactie.LidTrans;
import transactie.RitTrans;
import ui.VIVESbike;

/**
 * FXML Controller class
 *
 * @author Katrien.Deleu
 */
public class LedenBeheerController implements Initializable {

    // referentie naar VIVESbike (main)
    private VIVESbike parent;

    /**
     * Referentie naar parent (start) instellen
     *
     * @param parent referentie naar de runnable class die alle oproepen naar de
     * schermen bestuurt
     */
    public void setParent(VIVESbike p) {
        parent = p;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
