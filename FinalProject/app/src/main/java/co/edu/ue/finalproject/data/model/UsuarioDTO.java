package co.edu.ue.finalproject.data.model;

public class UsuarioDTO {
    private int usuId;
    private String usuPrimerNombre;
    private String usuPrimerApellido;
    private String usuSegundoNombre;
    private String usuSegundoApellido;
    private long usuTelefonoMovil;
    private TipoUsuarioDTO tiposUsuario;

    public int getUsuId() { return usuId; }
    public String getFullName() {
        StringBuilder sb = new StringBuilder(usuPrimerNombre);
        if (usuSegundoNombre != null && !usuSegundoNombre.isEmpty()) sb.append(" ").append(usuSegundoNombre);
        sb.append(" ").append(usuPrimerApellido);
        if (usuSegundoApellido != null && !usuSegundoApellido.isEmpty()) sb.append(" ").append(usuSegundoApellido);
        return sb.toString();
    }
    public long getUsuTelefonoMovil() { return usuTelefonoMovil; }
    public TipoUsuarioDTO getTiposUsuario() { return tiposUsuario; }

    public static class TipoUsuarioDTO {
        private int tipuId;
        private String tipuTipoUsuario;
        public String getTipuTipoUsuario() { return tipuTipoUsuario; }
    }
}
