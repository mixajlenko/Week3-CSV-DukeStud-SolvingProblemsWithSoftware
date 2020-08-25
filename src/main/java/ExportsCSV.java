import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.util.ArrayList;

public class ExportsCSV {

    /**
     * A method named countryInfo that has two parameters, parser is a CSVParser and country is a String. This method
     * returns a string of information about the country or returns “NOT FOUND” if there is no information about the
     * country. The format of the string returned is the country, followed by “: “, followed by a list of the
     * countries’ exports, followed by “: “, followed by the countries export value. For example, using the file
     * exports_small.csv and the country Germany, the program returns the string:
     * "Germany: motor vehicles, machinery, chemicals: $1,547,000,000,000"
     */
    public String countryInfo(CSVParser parser, String country) {
        for (CSVRecord record : parser) {
            if (record.get("Country").equals(country)) {
                String res = record.get("Country");
                String res2 = record.get("Exports");
                String res3 = record.get("Value (dollars)");
                return res + " : " + res2 + " : " + res3;
            }
        }
        return "NOT FOUND!";
    }

    /**
     * A method named listExportersTwoProducts that has three parameters, parser is a CSVParser, exportItem1 is a
     * String and exportItem2 is a String. This method prints the names of all the countries that have both exportItem1
     * and exportItem2 as export items. For example, using the file exports_small.csv, this method called with the
     * items “gold” and “diamonds” would print the countries:
     * Namibia
     * South Africa
     */
    public String listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        String country = null;
        for (CSVRecord record : parser) {
            String exporter = record.get("Exports");
            if (exporter.contains(exportItem1) && exporter.contains(exportItem2)) {
                country = record.get("Country");
                System.out.println(country);
            }
        }
        return country;
    }

    /**
     * a method named numberOfExporters, which has two parameters, parser is a CSVParser, and exportItem is a String.
     * This method returns the number of countries that export exportItem. For example, using the file
     * exports_small.csv, this method called with the item “gold” would return 3.
     */
    public int numberOfExporters(CSVParser parser, String exportItem) {
        int countOfExporters = 0;
        for (CSVRecord record : parser) {
            String exporter = record.get("Exports");
            if (exporter.contains(exportItem)) {
                countOfExporters++;
            }
        }
        return countOfExporters;
    }

    /**
     * a void method named bigExporters that has two parameters, parser is a CSVParser, and amount is a String in the
     * format of a dollar sign, followed by an integer number with a comma separator every three digits from the right.
     * An example of such a string might be “$400,000,000”. This method prints the names of countries and their Value
     * amount for all countries whose Value (dollars) string is longer than the amount string. You do not need to parse
     * either string value as an integer, just compare the lengths of the strings. For example, if bigExporters is
     * called with the file exports_small.csv and amount with the string $999,999,999, then this method would print
     * eight countries and their export values shown here:
     * Germany $1,547,000,000,000
     * Macedonia $3,421,000,000
     * Malawi $1,332,000,000
     * Malaysia $231,300,000,000
     * Namibia $4,597,000,000
     * Peru $36,430,000,000
     * South Africa $97,900,000,000
     * United States $1,610,000,000,000
     */
    public ArrayList<String> bigExporters(CSVParser parser, String amount) {
        ArrayList<String> foundedCountries = new ArrayList<>();
        String amount2 = "$" + amount;
        for (CSVRecord record : parser) {
            String exporter = record.get("Value (dollars)");
            if (exporter.length() >= amount2.length()) {
                String res1 = record.get("Country");
                String res2 = record.get("Value (dollars)");
                String res3 = res1 + " : " + res2;
                foundedCountries.add(res3);
                System.out.println(res3);
            }
        }
        return foundedCountries;
    }
}
