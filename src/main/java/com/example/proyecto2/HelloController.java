package com.example.proyecto2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.imageio.IIOParam;
import java.io.IOException;
import java.text.BreakIterator;
import java.util.*;

public class HelloController {
    @FXML
    public Label texto;
    @FXML
    public Label texto1;
    @FXML
    public Label intento;
    @FXML
    public TextField adivinar;

    @FXML
    public Label letra0, letra1, letra2, letra3, letra4;
    @FXML
    public Label letra5, letra6, letra7, letra8, letra9;
    @FXML
    public Label letra10, letra11, letra12, letra13, letra14;
    @FXML
    public Label letra15, letra16, letra17, letra18, letra19;
    @FXML
    public Label letra20, letra21, letra22, letra23, letra24;

    @FXML
    public Button A, B, C, D, E, F, G, H, I, J, K, L, M, N, Ñ, O, P, Q, R, S, T, U, V, W, X, Y, Z;

    public List<String> listaPalabras = Arrays.asList("plato", "canto", "nubes", "fluir", "trapo", "sello", "mismo", "ruego", "campo",
            "carta", "vigor", "dulce", "suelo", "raton", "flema", "tigre", "broma", "verde",
            "fuego", "corto", "pinta", "saber", "golpe", "perro", "salto", "fruta", "brisa",
            "nacer", "pardo", "limon");

    public String palabraActual;
    public Label[][] cajas;

    //Variables de juego
    public int intentos = 0;
    public int palabrasAdivinadas = 0;
    public int palabrasJugadas = 0;

    public Set<Character> letrasUtilizadas = new HashSet<>();

    @FXML
    public void initialize() {
        cajas = new Label[][]{
                {letra0, letra1, letra2, letra3, letra4},
                {letra5, letra6, letra7, letra8, letra9},
                {letra10, letra11, letra12, letra13, letra14},
                {letra15, letra16, letra17, letra18, letra19},
                {letra20, letra21, letra22, letra23, letra24}
        };
    }

    @FXML
    public void iniciar(ActionEvent event) throws IOException {
        Parent nuevaVista = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("juego.fxml")));

        Scene nuevaEscena = new Scene(nuevaVista);
        Stage escenario = (Stage) ((Node) event.getSource()).getScene().getWindow();
        escenario.setScene(nuevaEscena);
        escenario.show();
    }

    public void randomPalabra() {
        //La palabra a adivinar es una random de la lista de palabras
        Random aleatorio = new Random();
        palabraActual = listaPalabras.get(aleatorio.nextInt(listaPalabras.size()));
        intentos = 0;
        letrasUtilizadas.clear();

        for (Label[] fila : cajas) {
            for (Label linea : fila) {
                linea.setText("");
                linea.setStyle("-fx-background-color: #ffffff");
            }
        }
        //Que vuelvan las letras sin usar
        resetearTeclado();
        texto.setText("Empieza el juego");
        intento.setText("Intento: 0/5");
        palabrasJugadas++;
    }

    public void resetearTeclado() {
        Button[] letras = {A, B, C, D, E, F, G, H, I, J, K, L, M, N, Ñ, O, P, Q, R, S, T, U, V, W, X, Y, Z};
        for (Button boton : letras) {
            if (boton != null) {
                boton.setStyle("-fx-background-color: #d9d9d9; -fx-text-fill: black;");
            }
        }
    }

    public Button obtenerletra(char letra){
        char letraMayuscula = Character.toUpperCase(letra);
        if(letraMayuscula == 'A') return A;
        if(letraMayuscula == 'B') return B;
        if(letraMayuscula == 'C') return C;
        if(letraMayuscula == 'D') return D;
        if(letraMayuscula == 'E') return E;
        if(letraMayuscula == 'F') return F;
        if(letraMayuscula == 'G') return G;
        if(letraMayuscula == 'H') return H;
        if(letraMayuscula == 'I') return I;
        if(letraMayuscula == 'J') return J;
        if(letraMayuscula == 'K') return K;
        if(letraMayuscula == 'L') return L;
        if(letraMayuscula == 'M') return M;
        if(letraMayuscula == 'N') return N;
        if(letraMayuscula == 'Ñ') return Ñ;
        if(letraMayuscula == 'O') return O;
        if(letraMayuscula == 'P') return P;
        if(letraMayuscula == 'Q') return Q;
        if(letraMayuscula == 'R') return R;
        if(letraMayuscula == 'S') return S;
        if(letraMayuscula == 'T') return T;
        if(letraMayuscula == 'U') return U;
        if(letraMayuscula == 'V') return V;
        if(letraMayuscula == 'W') return W;
        if(letraMayuscula == 'X') return X;
        if(letraMayuscula == 'Y') return Y;
        if(letraMayuscula == 'Z') return Z;
        return null;
    }

    public void probar() {
        String resultado = adivinar.getText().toUpperCase();
        String palabraAdivinar = palabraActual.toUpperCase();
        if (resultado.length() != 5) {
            texto.setText("La palabra tiene que ser de 5 letras");
            adivinar.clear();
            return;
        }
        if (intentos >= 5) {
            texto.setText("No te quedan intentos");
            return;
        }
        for (int i = 0; i < 5; i++) {
            String letraMia = resultado.substring(i, i + 1);
            cajas[intentos][i].setText(letraMia);

            Button boton = obtenerletra(letraMia.charAt(0));

            if (boton != null) {
                String letraAdivinar = palabraAdivinar.substring(i, i + 1);
                if (letraMia.equals(letraAdivinar)) {
                    cajas[intentos][i].setStyle("-fx-background-color: #ff66c4");
                    boton.setStyle("-fx-background-color: #737373");
                } else if (palabraAdivinar.contains(letraMia)) {
                    cajas[intentos][i].setStyle("-fx-background-color: #ffde59");
                    boton.setStyle("-fx-background-color: #737373");
                } else {
                    cajas[intentos][i].setStyle("-fx-background-color:#d9d9d9");
                    boton.setStyle("-fx-background-color: #737373");
                }
            }
        }
        intentos++;
        intento.setText("Intento: " + intentos + "/5");

        if (resultado.equals(palabraAdivinar)) {
            texto.setText("Acertada");
            palabrasAdivinadas++;


            randomPalabra();
        } else if (intentos == 5) {
            texto.setText("Has perdido, la palabra era: " + palabraActual.toUpperCase());
            texto1.setText("Has adivinado: " +palabrasAdivinadas + " palabras");
        }
        adivinar.clear();
    }
}