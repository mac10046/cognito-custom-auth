package com.sls.api.cognito.challenges;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.sls.api.cognito.challenges.bean.CreateAuthChallenge;

public class CreateAuthChallengeEntry implements RequestHandler<CreateAuthChallenge, CreateAuthChallenge> {

  @Override
  public CreateAuthChallenge handleRequest(CreateAuthChallenge input, Context context) {

    Map<String, String> privateChallengeParameters = new HashMap<String, String>();
    // You got to set some private variable even in case you dont need it on Verify Auth Lambda
    privateChallengeParameters.put("secretCode",
        input.getRequest().getUserAttributes().getOrDefault("custom:secret", "somevalue"));
    input.getResponse().setPrivateChallengeParameters(privateChallengeParameters);
    context.getLogger().log(input.toString());
    return input;
  }

}
