import java.util.Arrays;

public class p3 {
    public static class PatientVitals {
        private double[] readings;
        private int count;

        public PatientVitals(double[] initialReadings) {
            this.readings = new double[500];
            this.count = 0;
            
            if (initialReadings != null) {
                for (double reading : initialReadings) {
                    recordReading(reading); 
                }
            }
        }

        public void recordReading(double reading) {
            if (reading > 0 && reading <= 45) {
                if (count < readings.length) {
                    readings[count++] = reading;
                }
            }
        }

        public double getAverage() {
            if (count == 0) return 0.0;
            double sum = 0;
            for (int i = 0; i < count; i++) {
                sum += readings[i];
            }
            return sum / count;
        }

        public double[] getAllReadings() {
            // Return a defensive copy every time it is called
            return Arrays.copyOf(readings, count);
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Invalid seed value (-2) is filtered out silently
        PatientVitals v = new PatientVitals(new double[]{36.5, -2, 37.1});
        System.out.println("Initial Readings: " + Arrays.toString(v.getAllReadings()));

        // Test Case 2: Ensure defensive copying prevents external modification
        double[] copy = v.getAllReadings();
        copy[0] = 999;
        
        System.out.println("Modified copy's first element: " + copy[0]);
        System.out.println("Original object's first element: " + v.getAllReadings()[0]);
    }
}