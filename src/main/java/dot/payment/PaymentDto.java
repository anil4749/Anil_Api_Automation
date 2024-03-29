package dot.payment;

import dot.searchFlight.SearchCriteria;
import dot.searchFlight.SearchFlightDto;
import dot.selectFlight.Preferences;
import lombok.Builder;
import lombok.Data;
import org.json.simple.JSONArray;

import java.util.List;

@Builder
@Data
public class PaymentDto {
    private List<PassengerDetails> passengerList;
    private Preferences preferences;
    @Builder.Default
    private String currency = "";
    @Builder.Default
    private String itineraryAction = "3";
    @Builder.Default
    private String locale = "en";
    @Builder.Default
    private SearchFlightDto searchRequest = null;
    @Builder.Default
    private JSONArray selectedFlights = null;
    private String confirmUrl;


}


