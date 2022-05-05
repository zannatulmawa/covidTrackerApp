package my.workbench.coronavirustracker.services;

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
    public void fetchVirusData() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(virusDataUrl)).build(); //creating the request
        HttpResponse <String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString()); //sending the response
        System.out.println(httpResponse.body());


        StringReader csvBodyReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        for (CSVRecord record : records) {
            String state = record.get("Province/State");
            System.out.println(state);

        }
    }
}

