package wubbalubbadubzork.classes;

import com.google.gson.annotations.SerializedName;

public class Partida {
    @SerializedName("codigo")
    public String codigo;

    public void cargarPartida() {

    }

    public Partida(String codigo) {
        this.codigo = codigo;
    }
}
