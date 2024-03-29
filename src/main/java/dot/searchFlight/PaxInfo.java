package dot.searchFlight;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PaxInfo {
    @Builder.Default
    private int adultCount = 2;
    @Builder.Default
   private int childCount = 0;
    @Builder.Default
   private int infantCount = 0;

}
