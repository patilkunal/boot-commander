function fn() {
    var env = karate.env;
    karate.log('karate.env system property was:', env);

    if(!env) {
        env = 'local'
    }

    var config = {
        env: env,
        baseURI: 'http://localhost:8080/boot-commander',
        bearerToken: ''
    }

    if(env === 'local') {
        var result = karate.callSingle('classpath:com/inovision/commander/karatetests/auth-single-use.feature', config);
        karate.log('This result is: ' + result.token);
        config.bearerToken = result.token;
    } else if (env === 'dev') {
    }

    return config;
}