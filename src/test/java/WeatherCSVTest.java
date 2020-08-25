import edu.duke.FileResource;
import org.apache.commons.csv.CSVRecord;
import org.junit.Assert;
import org.junit.Test;

public class WeatherCSVTest {
    WeatherCSV weatherCSV = new WeatherCSV();

    @Test
    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource file = new FileResource("src/main/resources/nc_weather/2014/weather-2014-03-20.csv");

        double result = weatherCSV.averageTemperatureWithHighHumidityInFile(file.getCSVParser(), 80);
        if (Double.isNaN(result)) {
            System.out.println("No temperatures with that humidity");
        } else {
            System.out.println(" Average Temp when high Humidity is " + result);
        }
        Assert.assertEquals(41.78666666666667, result, 0.0);
    }

    @Test
    public void testAverageTempInFile() {
        FileResource file = new FileResource("src/main/resources/nc_weather/2014/weather-2014-01-20.csv");
        double result = weatherCSV.averageTempInFile(file.getCSVParser());
        Assert.assertEquals(44.93333333333334, result, 0.0);
    }

    @Test
    public void testLowestHumidityInFile() {
        FileResource file = new FileResource("src/main/resources/nc_weather/2014/weather-2014-01-20.csv");
        CSVRecord record = weatherCSV.lowestHumidityInFile(file.getCSVParser());
        String result = "Lowest Humidity was: " + record.get("Humidity") + " at " + record.get("DateUTC");
        Assert.assertEquals("Lowest Humidity was: 24 at 2014-01-20 19:51:00", result);
    }

    @Test
    public void testColdestHourInFile() {
        FileResource file = new FileResource("src/main/resources/nc_weather/2014/weather-2014-01-20.csv");
        CSVRecord record = weatherCSV.coldestHourInFile(file.getCSVParser());
        String result = record.get("TemperatureF");
        Assert.assertEquals("30.0", result);
    }

    /**
     * You should run this program and select the files for January 19, 2014 and January 20, 2014, you should get:
     * Lowest Humidity was 24 at 2014-01-20 19:51:00
     * <p>
     * P.S. You can find these files here: src/main/resources/nc_weather/2014/
     * And you should choose both files at the same time to pass this test.
     */
    @Test
    public void testLowestHumidityInManyFiles() {
        CSVRecord record = weatherCSV.lowestHumidityInManyFiles();
        String result = "Lowest Humidity was: " + record.get("Humidity") + " " + "at " + record.get("DateUTC");
        Assert.assertEquals("Lowest Humidity was: 24 at 2014-01-20 19:51:00", result);
    }

    /**
     * Runs and selects the files for January 1–3 in 2014
     * <p>
     * P.S. You can find these files here: src/main/resources/nc_weather/2014/
     * And you should choose all files at the same time to pass this test.
     */
    @Test
    public void testFileWithColdestTemperature() {
        CSVRecord record = weatherCSV.fileWithColdestTemperature();
        String s1 = "Coldest day was in file: ";
        String s2 = "The coldest temperature (F) is: " + record.get("TemperatureF");
        String s3 = "All the Temperatures on the coldest day were: ";
        String result = (s1 + weatherCSV.fileRes.getName() + "\n" + s2 + "\n" + s3 + weatherCSV.allTemp(weatherCSV.fileRes));
        Assert.assertEquals(("Coldest day was in file: weather-2014-01-03.csv\n" +
                "The coldest temperature (F) is: 21.9\n" +
                "All the Temperatures on the coldest day were: \n" +
                "2014-01-03 05:51:00 : 41.0\n" +
                "2014-01-03 06:51:00 : 39.0\n" +
                "2014-01-03 07:51:00 : 35.1\n" +
                "2014-01-03 08:51:00 : 30.9\n" +
                "2014-01-03 09:51:00 : 28.0\n" +
                "2014-01-03 10:51:00 : 25.0\n" +
                "2014-01-03 11:51:00 : 24.1\n" +
                "2014-01-03 12:51:00 : 23.0\n" +
                "2014-01-03 13:51:00 : 25.0\n" +
                "2014-01-03 14:51:00 : 26.1\n" +
                "2014-01-03 15:51:00 : 28.0\n" +
                "2014-01-03 16:51:00 : 30.0\n" +
                "2014-01-03 17:51:00 : 30.9\n" +
                "2014-01-03 18:51:00 : 33.1\n" +
                "2014-01-03 19:51:00 : 33.1\n" +
                "2014-01-03 20:51:00 : 33.1\n" +
                "2014-01-03 21:51:00 : 30.9\n" +
                "2014-01-03 22:51:00 : 28.9\n" +
                "2014-01-03 23:51:00 : 28.9\n" +
                "2014-01-04 00:51:00 : 26.1\n" +
                "2014-01-04 01:51:00 : 24.1\n" +
                "2014-01-04 02:51:00 : 24.1\n" +
                "2014-01-04 03:51:00 : 23.0\n" +
                "2014-01-04 04:51:00 : 21.9"), result);
    }
}