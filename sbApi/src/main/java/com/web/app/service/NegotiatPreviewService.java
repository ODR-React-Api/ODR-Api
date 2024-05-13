package com.web.app.service;
import com.web.app.domain.NegotiatPreview.NegotiatPreview;

public interface NegotiatPreviewService {
    int NegotiatPreview(NegotiatPreview negotiatPreview);

    int InsNegotiationData(NegotiatPreview negotiatPreview);

    int UpdNegotiationsData(NegotiatPreview negotiatPreview);
}
