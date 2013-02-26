public class TestEnum {

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(HotelType.ECONOMY);
        System.out.println(HotelType.ECONOMY == HotelType.valueOf("ECONOMY"));
    }

    public enum HotelType{
        ECONOMY("economy","经济"),
        HIGH_GRADE("high_grade","高档"),
        LUXURY("luxury","奢华"),
        COMFORT("comfort","舒适");
        String label;
        String key;
        private HotelType(String key,String lb) {
            this.label = lb;
            this.key = key;
        }
        public String getLabel() {
            return label;
        }
        public String getKey() {
            HotelType.valueOf("ec");
            return key;
        }
    }
}
