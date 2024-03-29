package dot.selectFlight;

import dot.searchFlight.SearchFlightDto;
import lombok.Builder;
import lombok.Data;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class SelectFlightDto {
    @Builder.Default
    private List<String> passengerList = new ArrayList<>();
    @Builder.Default
    private String currency = "";
    @Builder.Default
    private Preferences preferences = null;
    @Builder.Default
  private   SearchFlightDto searchRequest = null;
    @Builder.Default
    private JSONArray selectedFlights = null;

}


