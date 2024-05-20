package com.web.app.mapper;
import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.InsClaimReplies;
import com.web.app.domain.CaseFileRelations;
import com.web.app.domain.Files;
@Mapper
public interface InsClaimRepliesMapper {
  int insClaimReplies(InsClaimReplies insClaimReplies);

  int insClaimRepliesDataFiles(Files files);
  
  int insClaimRepliesDataFilesRelations(CaseFileRelations caseFileRelations);
}
