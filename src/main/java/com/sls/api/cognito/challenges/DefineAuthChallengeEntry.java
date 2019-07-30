package com.sls.api.cognito.challenges;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.sls.api.cognito.challenges.bean.DefineAuthChallenge;

public class DefineAuthChallengeEntry implements RequestHandler<DefineAuthChallenge, DefineAuthChallenge> {

  @Override
  public DefineAuthChallenge handleRequest(DefineAuthChallenge input, Context context) {

    // if 1st session is successful
    if (input.getRequest().getSession().length > 0 && input.getRequest().getSession()[0].isChallengeResult() == true) {
      input.getResponse().setIssueTokens(true);
      input.getResponse().setFailAuthentication(false);
    } else if (input.getRequest().getSession().length == 0) { // if session starts
      input.getResponse().setIssueTokens(false);
      input.getResponse().setFailAuthentication(false);
      input.getResponse().setChallengeName("CUSTOM_CHALLENGE");
    } else { // if session fails then fail authentication
      input.getResponse().setIssueTokens(false);
      input.getResponse().setFailAuthentication(true);
    }

    context.getLogger().log(input.toString());
    return input;
  }

}
