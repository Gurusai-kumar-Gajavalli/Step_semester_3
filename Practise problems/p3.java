import java.util.Arrays;

public class p3 {
    public static class BusRoute implements Comparable<BusRoute> {
        private String routeCode;
        private String routeName;
        private int priority;

        public BusRoute(String routeCode, String routeName, int priority) {
            this.routeCode = routeCode;
            this.routeName = routeName;
            this.priority = priority;
        }

        public BusRoute(String routeCode, String routeName) {
            this(routeCode, routeName, 5); 
        }

        @Override
        public int compareTo(BusRoute other) {
            int priorityCompare = Integer.compare(other.priority, this.priority);
            if (priorityCompare != 0) return priorityCompare;

            int codeCompare = this.routeCode.compareToIgnoreCase(other.routeCode);
            if (codeCompare != 0) return codeCompare;

            return Integer.compare(this.routeName.length(), other.routeName.length());
        }
    }

    public static BusRoute[] rankRoutes(BusRoute[] routes) {
        BusRoute[] sorted = Arrays.copyOf(routes, routes.length);
        for (int i = 0; i < sorted.length - 1; i++) {
            for (int j = i + 1; j < sorted.length; j++) {
                if (sorted[i].compareTo(sorted[j]) > 0) {
                    BusRoute temp = sorted[i];
                    sorted[i] = sorted[j];
                    sorted[j] = temp;
                }
            }
        }
        return sorted;
    }

    public static void main(String[] args) {
        BusRoute[] routes = {
            new BusRoute("RT205L", "Airport Express", 3),
            new BusRoute("rt201j", "City Central", 4),
            new BusRoute("RT299T", "Night Service")
        };
        BusRoute[] ranked = rankRoutes(routes);
        for (BusRoute r : ranked) {
            System.out.print("\"" + r.routeCode + "\" ");
        }
    }
}