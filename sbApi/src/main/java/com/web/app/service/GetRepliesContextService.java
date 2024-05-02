package com.web.app.service;
import java.util.List;
import com.web.app.controller.GetRepliesContextController;
import com.web.app.domain.GetRepliesContext;
public interface GetRepliesContextService {
  List<GetRepliesContext> getRepliesContext(String CaseId,String PlatformId);
}


// package com.web.app.service;
// import java.util.List;
// import com.web.app.controller.GetRepliesController;
// import com.web.app.domain.GetReplies;
// public interface GetRepliesService {
//       List<GetReplies> getReplies(String CaseId,String PlatformId);
// }
