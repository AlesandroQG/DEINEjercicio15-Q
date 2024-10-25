package com.alesandro.ejercicio15q;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.util.Timer;
import java.util.TimerTask;
import java.io.IOException;

/**
 * Clase Controladora del Temporizador
 */
public class Temporizador extends AnchorPane {
    @FXML // fx:id="min1"
    private Label min1; // Value injected by FXMLLoader

    @FXML // fx:id="min2"
    private Label min2; // Value injected by FXMLLoader

    @FXML // fx:id="seg1"
    private Label seg1; // Value injected by FXMLLoader

    @FXML // fx:id="seg2"
    private Label seg2; // Value injected by FXMLLoader

    private BooleanProperty fin;
    private int segundos;
    private Timer timer;

    /**
     * Constructor de la clase
     *
     * @param segundos
     */
    public Temporizador(int segundos) {
        this.fin = new SimpleBooleanProperty(false);
        this.segundos = -1;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Temporizador.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Función que asigna los segundos al temporizador
     *
     * @param segundos del temporizador
     * @return true/false
     */
    public boolean setSegundos(int segundos) {
        int minutos = (int)(segundos/60);
        if (minutos > 0 && minutos < 100) {
            this.segundos = segundos;
            return true;
        }
        return false;
    }

    /**
     * Función que inicia el temporizador
     */
    public void iniciar() {
        if (this.segundos == -1) {
            System.err.println("Asigna los segundos antes de iniciar el temporizador");
        } else {
            final int[] restante = {this.segundos};
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if (restante[0] < 0) {
                        timer.cancel();
                        Platform.runLater(() -> fin.set(true)); // Actualizar la propiedad "fin"
                        return;
                    }
                    int mins = restante[0] / 60;
                    int mins1 = mins / 10;
                    int mins2 = mins % 10;
                    int segs = restante[0] % 60;
                    int segs1 = segs / 10;
                    int segs2 = segs % 10;
                    // Usar Platform.runLater para actualizar los Labels en el hilo de la interfaz gráfica
                    Platform.runLater(() -> {
                        min1.setText(String.valueOf(mins1));
                        min2.setText(String.valueOf(mins2));
                        seg1.setText(String.valueOf(segs1));
                        seg2.setText(String.valueOf(segs2));
                    });
                    restante[0] -= 1;
                }
            }, 0, 1000);
        }
    }

    /**
     * Función para detener el temporizador manualmente
     */
    public void detener() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
    }

    /**
     * Devuelve si el temporizador ha finalizado
     * @return BooleanProperty fin
     */
    public BooleanProperty finProperty() {
        return fin;
    }

}