package com.sls.api.cognito.challenges;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.sls.api.cognito.challenges.bean.VerifyAuthChallenge;

public class VerifyAuthChallengeEntry implements RequestHandler<VerifyAuthChallenge, VerifyAuthChallenge> {

  @Override
  public VerifyAuthChallenge handleRequest(VerifyAuthChallenge input, Context context) {
    String userClientCode = getUserAttribute(input, "custom:secretCode");
    String requiredSecretCode = input.getRequest().getChallengeAnswer();

    if (userClientCode.equals(requiredSecretCode)) {
      input.getResponse().setAnswerCorrect(true);
    } else {
      input.getResponse().setAnswerCorrect(false);
    }

    context.getLogger().log(input.toString());
    return input;
  }

  private String getUserAttribute(VerifyAuthChallenge input, String key) {
    return input.getRequest().getUserAttributes().getOrDefault(key, "");
  }
}
