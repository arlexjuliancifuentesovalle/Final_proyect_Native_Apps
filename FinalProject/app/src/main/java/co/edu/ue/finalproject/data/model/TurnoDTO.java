package co.edu.ue.finalproject.data.model;

import com.google.gson.annotations.SerializedName;

public class TurnoDTO {
    private int turId;
    private String turFecha;
    private String turHora;
    private UsuarioDTO usuario;

    public int getTurId() { return turId; }
    public String getTurFecha() { return turFecha; }
    public String getTurHora() { return turHora; }
    public UsuarioDTO getUsuario() { return usuario; }

    public static class UsuarioDTO {
        private int usuId;
        private String usuPrimerNombre;
        private String usuPrimerApellido;
        private String usuSegundoNombre;
        private String usuSegundoApellido;
        private long usuTelefonoMovil;
        private TipoUsuarioDTO tiposUsuario;

        public String getFullName() {
            StringBuilder sb = new StringBuilder(usuPrimerNombre);
            if (usuSegundoNombre != null) sb.append(" ").append(usuSegundoNombre);
            sb.append(" ").append(usuPrimerApellido);
            if (usuSegundoApellido != null) sb.append(" ").append(usuSegundoApellido);
            return sb.toString();
        }

        public TipoUsuarioDTO getTiposUsuario() { return tiposUsuario; }
    }

    public static class TipoUsuarioDTO {
        private int tipuId;
        private String tipuTipoUsuario;

        public String getTipuTipoUsuario() { return tipuTipoUsuario; }
    }

}
