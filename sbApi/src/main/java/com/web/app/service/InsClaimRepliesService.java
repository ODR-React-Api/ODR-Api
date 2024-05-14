package com.web.app.service;

import com.web.app.domain.InsClaimReplies;
import com.web.app.domain.Files;
import com.web.app.domain.CaseFileRelations;

public interface InsClaimRepliesService {
      int InsClaimRepliesData(InsClaimReplies insClaimReplies);
      int InsClaimRepliesDataFiles(Files files);
      int InsClaimRepliesDataFilesRelations(CaseFileRelations caseFileRelations);
}
