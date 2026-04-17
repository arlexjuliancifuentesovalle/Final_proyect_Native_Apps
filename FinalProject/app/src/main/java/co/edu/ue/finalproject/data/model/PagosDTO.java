    package co.edu.ue.finalproject.data.model;

    public class PagosDTO { //la clase de acceso a los datos, DTO transferencia de datos

        private int tippId;
        private String  tippDescripcion;
        private String tippTipoPago;

        public int getTippId() {
            return tippId;
        }

        public void setTippId(int tippId) {
            this.tippId = tippId;
        }

        public String getTippDescripcion() {
            return tippDescripcion;
        }

        public void setTippDescripcion(String tippDescripcion) {
            this.tippDescripcion = tippDescripcion;
        }

        public String getTippTipoPago() {
            return tippTipoPago;
        }

        public void setTippTipoPago(String tippTipoPago) {
            this.tippTipoPago = tippTipoPago;
        }
    }
