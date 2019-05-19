package santriprogrammer.com.bookinglapangan.eventbus;

public class TanggalEventBus {

    public static class EventBus {
        public String tanggal;
        public String jam;
        public int totalJam;

        public EventBus(String tanggal, String jam, int totalJam) {
            this.tanggal = tanggal;
            this.jam = jam;
            this.totalJam = totalJam;
        }

        public int getTotalJam() {
            return totalJam;
        }

        public void setTotalJam(int totalJam) {
            this.totalJam = totalJam;
        }

        public String getTanggal() {
            return tanggal;
        }

        public void setTanggal(String tanggal) {
            this.tanggal = tanggal;
        }

        public String getJam() {
            return jam;
        }

        public void setJam(String jam) {
            this.jam = jam;
        }
    }

}
