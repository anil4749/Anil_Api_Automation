package dot.payLater;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PayLaterDto {
    @Builder.Default
    private String systemId = "FZPSS";
    @Builder.Default
    private String entityId = "  ";
    @Builder.Default
    private String paymentId = "paymentId";
    @Builder.Default
    private String currency = "currency";
    @Builder.Default
    private String amount = "amount";
    @Builder.Default
    private String requestType = "TRAVEL";
    @Builder.Default
    private String sessionId = "sessionId";
    @Builder.Default
    private String threatMetrixPurl = "https://uatpayments.flydubai.com/fp/clear.png?org_id=1snn5n9w&amp;session_id=flydubai4d792948cfe1a4787990c47b1c5d37529&amp;m=1 ";
    @Builder.Default
    private String threatMetrixIMGurl = "https://uatpayments.flydubai.com/fp/clear.png?org_id=1snn5n9w&amp;session_id=flydubaid792948cfe1a4787990c47b1c5d37529&amp;m=2 ";
    @Builder.Default
    private String threatMetrixSCRIPTurl = "https://uatpayments.flydubai.com/fp/check.js?org_id=1snn5n9w&amp;session_id=flydubaid792948cfe1a4787990c47b1c5d37529 ";
    @Builder.Default
    private String paymentMethod = "PAYLATER";


}


