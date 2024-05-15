package com.web.app.service;

import java.util.List;
import com.web.app.domain.NegotiatPreview.MasterTemplates;

public interface NegotiatPreviewService {
    List<MasterTemplates> getNegotiationsTemplate(MasterTemplates masterTemplates);
}
