package dot.searchFlight;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SearchCriteria {
    @Builder.Default
    private String date = "05/29/2024 12:00 AM";
    @Builder.Default
   private String dest = "PRG";
    @Builder.Default
   private String direction = "outBound";
    @Builder.Default
   private String origin = "DXB";
    @Builder.Default
   private boolean isOriginMetro = true;
    @Builder.Default
   private boolean isDestMetro = false;
}
