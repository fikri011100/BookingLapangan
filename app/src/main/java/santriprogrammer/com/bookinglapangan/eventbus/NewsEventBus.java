package santriprogrammer.com.bookinglapangan.eventbus;

public class NewsEventBus {

    public static class EventBus {
        public String trigg;

        public EventBus(String trigg) {
            this.trigg = trigg;
        }

        public String getTrigg() {
            return trigg;
        }

        public void setTrigg(String trigg) {
            this.trigg = trigg;
        }
    }

}
