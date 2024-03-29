package dot.selectFlight;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Preferences {
    @Builder.Default
    private String isReadyToSignUpForOffers = "true";

}
