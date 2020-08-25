import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class ExportsCSVTest {
    ExportsCSV exportsCSV = new ExportsCSV();

    @Test
    public void testCountryInfo() {
        //For successfully pass the test use exportdata.csv file. Path: src/main/resources/exports/exportdata.csv
        CSVParser parser = new FileResource().getCSVParser();
        String result1 = exportsCSV.countryInfo(parser, "Malawi");
        Assert.assertEquals("Malawi : tobacco 53%, tea, sugar, cotton, coffee, peanuts, wood products, apparel (2010 est.) : $1,332,000,000", result1);

        CSVParser parser2 = new FileResource().getCSVParser();
        String result2 = exportsCSV.countryInfo(parser2, "Angola");
        Assert.assertEquals("Angola : crude oil, diamonds, refined petroleum products, coffee, sisal, fish and fish products, timber, cotton : $69,460,000,000", result2);

        CSVParser parser3 = new FileResource().getCSVParser();
        String result3 = exportsCSV.countryInfo(parser3, "Virgin Islands");
        Assert.assertEquals("Virgin Islands : rum : $4,234,000,000", result3);

        CSVParser parser4 = new FileResource().getCSVParser();
        String result4 = exportsCSV.countryInfo(parser4, "Ukraine");
        Assert.assertEquals("Ukraine : ferrous and nonferrous metals, fuel and petroleum products, chemicals, machinery and transport equipment, foodstuffs : $52,460,000,000", result4);
    }

    @Test
    public void testListExportersTwoProducts() {
        //For successfully pass the test use exportdata.csv file. Path: src/main/resources/exports/exportdata.csv
        CSVParser parser = new FileResource().getCSVParser();
        String result = exportsCSV.listExportersTwoProducts(parser, "cotton", "flowers");
        Assert.assertEquals("Zambia", result);
    }

    @Test
    public void testNumberOfExporters() {
        //For successfully pass the test use exportdata.csv file. Path: src/main/resources/exports/exportdata.csv
        CSVParser parser = new FileResource().getCSVParser();
        int result = exportsCSV.numberOfExporters(parser, "cocoa");
        Assert.assertEquals(17, result);
    }

    @Test
    public void testBigExporters() {
        //For successfully pass the test use exportdata.csv file. Path: src/main/resources/exports/exportdata.csv
        CSVParser parser = new FileResource().getCSVParser();
        ArrayList<String> result = exportsCSV.bigExporters(parser, "999,999,999,999");
        Assert.assertEquals(38, result.size());
    }
}