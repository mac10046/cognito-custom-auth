# cognito-custom-auth
MVP of aws cognito custom auth code in core JAVA

POJO for request and response to be handled in DefineAuthChallenge, CreateAuthChallenge and VerifyAuthChallenge

Points to remember while coding in this events code
1. CreateAuthChallenge require's to add atleast one private variable in the response else the flow will break and fail in aws cognito
2. All Cognito Events including above 3 mentioned shall reply under 5 secs - else socket timeout exception will be thrown and the client will fail with exception

# let me know if you need any other help with aws cognito :)
