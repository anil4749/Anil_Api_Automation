package dot.searchFlight;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class SearchFlightDto {
    @Builder.Default
    private String cabinClass = "Economy";
    @Builder.Default
    private String promoCode = "TESTTEST";
    @Builder.Default
    private boolean isDestMetro = false;
    @Builder.Default
    private boolean isOriginMetro = true;
   private PaxInfo paxInfo;
   private List<SearchCriteria> searchCriteria;
    @Builder.Default
   private String variant = "1";
    @Builder.Default
    private String curr = "INR";

    public static SearchFlightDto searchFlight(String source, String destination, String date, int numberOfAdult, int numberOfChield) {
        List<SearchCriteria> search = new ArrayList<>();
        search.add(SearchCriteria.builder().origin(source).dest(destination).date(date).isDestMetro(false).isOriginMetro(true).build());
        return SearchFlightDto.builder().paxInfo(
                PaxInfo.builder().adultCount(numberOfAdult).childCount(numberOfChield).build()).searchCriteria(
                search
        ).build();
    }
}


