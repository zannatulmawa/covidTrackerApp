package my.workbench.coronavirustracker.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVFormat;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service //In order to call the SpringFramework
public class CoronaVirusDataService {
    private static String virusDataUrl = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    @PostConstruct //In order to make a post request through spring
    @Scheduled(cron = "* * * * * *") //calling spring to execute the method every second
    public void fetchVirusData() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(virusDataUrl)).build(); //creating the request
        HttpResponse <String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString()); //sending the response
        System.out.println(httpResponse.body());


        //In order to parse the csv file, we take this code from an open source library
        StringReader csvBodyReader = new StringReader(httpResponse.body());
        //import the string reader, creating csv body reader object
        //to convert the httpResponse into a stringReader so that we can parse the csv file here
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        for (CSVRecord record : records) { //looping through the record
            String state = record.get("Province/State"); //pulling out one column from here
            System.out.println(state);

        }
    }
}

