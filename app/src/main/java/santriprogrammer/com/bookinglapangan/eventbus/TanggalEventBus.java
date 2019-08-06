package santriprogrammer.com.bookinglapangan.eventbus;

public class TanggalEventBus {

    public static class EventBus {
        public String tanggal;
        public String jam;
        public String jamjam;
        public int totalJam;

        public EventBus(String tanggal, String jam, int totalJam, String jamjam) {
            this.tanggal = tanggal;
            this.jam = jam;
            this.jamjam = jamjam;
            this.totalJam = totalJam;
        }

        public String getJamjam() {
            return jamjam;
        }

        public void setJamjam(String jamjam) {
            this.jamjam = jamjam;
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
