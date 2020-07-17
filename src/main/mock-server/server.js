let express = require('express');
let app = express();

let bodyParser = require('body-parser');
let port = 8084;

let mock = {
    userData: require('./mock/userData'),
    invalidUser: require('./mock/userInvalid'),
    hosts: require('./mock/hosts')
}

app.use(function(req, res, next) {
    res.header('Access-Control-Allow-Origin', "*");
    res.header('Access-Control-Allow-Credentials', true);
    res.header('Access-Control-Allow-Methods', 'GET,HEAD,OPTIONS,POST,PUT');
//    res.header("Access-Control-Allow-Headers", "Authorization, Access-Control-Allow-Headers, Origin, Accept, X-Requested-With, " +
//                               "Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, x-token");
    res.header("Access-Control-Allow-Headers", "*");
    next();
});

app.use(bodyParser.json());
app.use(bodyParser.text());
app.use(bodyParser.urlencoded({extended: true}));

//Define what gets sent on what URL
app.post('/login', function(req, res, next) {
    console.log('Request: ' + JSON.stringify(req.body));
     let username = req.body.username;
     let password = req.body.password;

    if(username != null && password != null) {
        return res.status(200)
            .header('Authorization', 'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlzcyI6Imh0dHA6Ly9ib290Y29tbWFuZGVyLmhlcm9rdWFwcHMuY29tIiwiZXhwIjoxNTk1ODE0MzA5LCJVU0VSX1JPTEUiOlsiQURNSU4iXX0.6h-5m_sofwC2hhFIoZhM0yyFVTAwM7nSqm7Fi_5l-lKGv2skwg_GWSFuqEEsvKP7C4NT82OWYbchDR6DFd68mw')
            .header('x-token', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlzcyI6Imh0dHA6Ly9ib290Y29tbWFuZGVyLmhlcm9rdWFwcHMuY29tIiwiZXhwIjoxNTk1ODE0MzA5LCJVU0VSX1JPTEUiOlsiQURNSU4iXX0.6h-5m_sofwC2hhFIoZhM0yyFVTAwM7nSqm7Fi_5l-lKGv2skwg_GWSFuqEEsvKP7C4NT82OWYbchDR6DFd68mw')
            .json(mock.userData);
    } else {
        return res.status(403).json(mock.invalidUser);
    }
});

app.listen(port, function() {
    console.log('Express server listening on port: ' + port);
});