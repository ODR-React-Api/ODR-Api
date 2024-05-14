package com.web.app.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import com.web.app.domain.InsClaimReplies;
import com.web.app.domain.CaseFileRelations;
import com.web.app.domain.Files;
import com.web.app.mapper.InsClaimRepliesMapper;
import com.web.app.service.InsClaimRepliesService;
import org.springframework.stereotype.Service;

@Service
public class InsClaimRepliesServiceImpl implements InsClaimRepliesService {
    @Autowired
    private InsClaimRepliesMapper insClaimRepliesMapper;

    @Override
    public int InsClaimRepliesData(InsClaimReplies insClaimReplies) {
        return insClaimRepliesMapper.insClaimReplies(insClaimReplies);
    }
    @Override
    public int InsClaimRepliesDataFiles(Files files) {
        return insClaimRepliesMapper.insClaimRepliesDataFiles(files);
    }

    @Override
    public int InsClaimRepliesDataFilesRelations(CaseFileRelations caseFileRelations) {
        return insClaimRepliesMapper.insClaimRepliesDataFilesRelations(caseFileRelations);
    }
}
