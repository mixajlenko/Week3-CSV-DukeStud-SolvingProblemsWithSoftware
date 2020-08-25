import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;

public class WeatherCSV {
    File fileRes = null;

    /**
     * method averageTemperatureWithHighHumidityInFile that has two parameters, a CSVParser named parser and an integer
     * named value. This method returns a double that represents the average temperature of only those temperatures
     * when the humidity was greater than or equal to value. You should also write a void method named
     * testAverageTemperatureWithHighHumidityInFile() to test this method. When this method runs checking for humidity
     * greater than or equal to 80 and selects the file for January 20, 2014, the method should print out:
     * "No temperatures with that humidity"
     * If you run the method checking for humidity greater than or equal to 80 and select the file March 20, 2014,
     * a wetter day, the method should print out:
     * "Average Temp when high Humidity is 41.78666666666667"
     */
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double sumTemp = 0, countedHours = 0;
        for (CSVRecord data : parser) {
            if (Double.parseDouble(data.get("Humidity")) >= value) {
                sumTemp += Double.parseDouble(data.get("TemperatureF"));
                countedHours++;
            }
        }
        return sumTemp / countedHours;
    }

    /**
     * method averageTemperatureInFile that has one parameter, a CSVParser named parser. This method returns a double
     * that represents the average temperature in the file. You should also write a void method named
     * testAverageTemperatureInFile() to test this method. When this method runs and selects the file for January 20,
     * 2014, the method should print out:
     * Average temperature in file is 44.93333333333334
     */
    public double averageTempInFile(CSVParser parser) {
        double sum = 0.0;
        int count = 0;
        double result;

        for (CSVRecord rec : parser) {
            double res = Double.parseDouble(rec.get("TemperatureF"));
            count++;
            sum = sum + res;
        }
        result = sum / count;
        System.out.println(result);

        return result;
    }

    /**
     * method named lowestHumidityInFile that has one parameter, a CSVParser named parser. This method returns the
     * CSVRecord that has the lowest humidity. If there is a tie, then return the first such record that was found.
     * Note that sometimes there is not a number in the Humidity column but instead there is the string “N/A”. This
     * only happens very rarely. You should check to make sure the value you get is not “N/A” before converting it
     * to a number. Also note that the header for the time is either TimeEST or TimeEDT, depending on the time of year.
     * You will instead use the DateUTC field at the right end of the data file to get both the date and time of a
     * temperature reading. Then prints the lowest humidity AND the time the lowest humidity occurred. For example,
     * for the file weather-2014-01-20.csv, the output should be:
     * Lowest Humidity was 24 at 2014-01-20 19:51:00
     */
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord coldestHum = null;
        for (CSVRecord currentRow : parser) {
            if (coldestHum == null) {
                coldestHum = currentRow;
            } else {
                if (currentRow.get("Humidity").contains("N/A")) {
                    String currStr = currentRow.get("Humidity");
                } else {
                    double currentHum = Double.parseDouble(currentRow.get("Humidity"));
                    double colderTemp = Double.parseDouble(coldestHum.get("Humidity"));
                    if (currentHum < colderTemp) {
                        coldestHum = currentRow;
                    }
                }
            }
        }
        return coldestHum;
    }

    /**
     * Method named coldestHourInFile that has one parameter, a CSVParser named parser. This method returns the
     * CSVRecord with the coldest temperature in the file and thus all the information about the coldest temperature,
     * such as the hour of the coldest temperature. You should also write a void method named testColdestHourInFile()
     * to test this method and print out information about that coldest temperature, such as the time of its occurrence.
     * <p>
     * NOTE: Sometimes there was not a valid reading at a specific hour, so the temperature field says -9999. You
     * should ignore these bogus temperature values when calculating the lowest temperature.
     */
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldestTemp = null;
        for (CSVRecord currentRow : parser) {
            if (coldestTemp != null) {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double colderTemp = Double.parseDouble(coldestTemp.get("TemperatureF"));
                if (currentTemp < colderTemp && currentTemp != -9999) {
                    coldestTemp = currentRow;
                }
            } else {
                coldestTemp = currentRow;
            }
        }
        return coldestTemp;
    }

    /**
     * method lowestHumidityInManyFiles that has no parameters. This method returns a CSVRecord that has the lowest
     * humidity over all the files. If there is a tie, then return the first such record that was found. You should
     * also write a void method named testLowestHumidityInManyFiles() to test this method and to print the lowest
     * humidity AND the time the lowest humidity occurred. Be sure to test this method on two files so you can check
     * if it is working correctly. If you run this program and select the files for January 19, 2014 and January 20,
     * 2014, you should get:
     * Lowest Humidity was 24 at 2014-01-20 19:51:00
     */
    public CSVRecord lowestHumidityInManyFiles() {
        CSVRecord lowestHumidity = null;
        DirectoryResource dr = new DirectoryResource();
        FileResource fr;

        for (File file : dr.selectedFiles()) {
            fr = new FileResource(file);
            CSVRecord current = lowestHumidityInFile(fr.getCSVParser());

            fileRes = file;

            if (lowestHumidity == null) {
                lowestHumidity = current;
            } else {
                double currentHum = Double.parseDouble(current.get("Humidity"));
                double lowHum = Double.parseDouble(lowestHumidity.get("Humidity"));

                if (currentHum < lowHum) {
                    lowestHumidity = current;
                }
            }
        }
        return lowestHumidity;
    }

    /**
     * method fileWithColdestTemperature that has no parameters. This method should return a string that is the name
     * of the file from selected files that has the coldest temperature. You should also write a void method named
     * testFileWithColdestTemperature() to test this method. Note that after determining the filename, you could call
     * the method coldestHourInFile to determine the coldest temperature on that day. When fileWithColdestTemperature
     * runs and selects the files for January 1–3 in 2014, the method should print out:
     * Coldest day was in file weather-2014-01-03.csv
     * Coldest temperature on that day was 21.9
     * All the Temperatures on the coldest day were:
     * 2014-01-03 05:51:00: 41.0
     * 2014-01-03 06:51:00: 39.0
     * 2014-01-03 07:51:00: 35.1
     * 2014-01-03 08:51:00: 30.9
     * 2014-01-03 09:51:00: 28.0
     * 2014-01-03 10:51:00: 25.0
     * 2014-01-03 11:51:00: 24.1
     * 2014-01-03 12:51:00: 23.0
     * 2014-01-03 13:51:00: 25.0
     * 2014-01-03 14:51:00: 26.1
     * 2014-01-03 15:51:00: 28.0
     * 2014-01-03 16:51:00: 30.0
     * 2014-01-03 17:51:00: 30.9
     * 2014-01-03 18:51:00: 33.1
     * 2014-01-03 19:51:00: 33.1
     * 2014-01-03 20:51:00: 33.1
     * 2014-01-03 21:51:00: 30.9
     * 2014-01-03 22:51:00: 28.9
     * 2014-01-03 23:51:00: 28.9
     * 2014-01-04 00:51:00: 26.1
     * 2014-01-04 01:51:00: 24.1
     * 2014-01-04 02:51:00: 24.1
     * 2014-01-04 03:51:00: 23.0
     * 2014-01-04 04:51:00: 21.9
     */
    public CSVRecord fileWithColdestTemperature() {
        CSVRecord coldestTemp = null;
        DirectoryResource dr = new DirectoryResource();
        FileResource fr;

        for (File file : dr.selectedFiles()) {
            fr = new FileResource(file);
            CSVRecord current = coldestHourInFile(fr.getCSVParser());
            fileRes = file;
            if (coldestTemp == null) {
                coldestTemp = current;
            } else {
                double currentTemp = Double.parseDouble(current.get("TemperatureF"));
                double coldTemp = Double.parseDouble(coldestTemp.get("TemperatureF"));

                if (currentTemp < coldTemp && currentTemp != -9999) {
                    coldestTemp = current;
                }
            }
        }
        return coldestTemp;
    }

    /**
     * This method using with fileWithColdestTemperature() method.
     */
    public String allTemp(File file) {
        FileResource allTemp = new FileResource(file);
        ArrayList<String> names = new ArrayList<>();
        String result = "";
        StringBuilder newString = new StringBuilder(result);
        for (CSVRecord rec : allTemp.getCSVParser()) {
            String res1 = rec.get("DateUTC");
            String res2 = rec.get("TemperatureF");
            names.add(res1 + " : " + res2);
        }
        for (String s : names) {
            newString.append("\n").append(s);
        }
        return String.valueOf(newString);
    }
}
