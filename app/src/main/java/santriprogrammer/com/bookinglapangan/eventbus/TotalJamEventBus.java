package santriprogrammer.com.bookinglapangan.eventbus;

public class TotalJamEventBus {

    public static class EventBus {
        public int totalJam;

        public EventBus(int totalJam) {
            this.totalJam = totalJam;
        }

        public int getTotalJam() {
            return totalJam;
        }

        public void setTotalJam(int totalJam) {
            this.totalJam = totalJam;
        }
    }

}
