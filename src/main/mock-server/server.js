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
    // res.header(
    //     'Access-Control-Allow-Headers',
    //     'Access-Control-Allow-headers, Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method'
    // );
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

    if(username === 'testuser' && password === 'testpass') {
        return res.status(200)
            .header('Bearer', '123')
            .json(mock.userData);
    } else {
        return res.status(403).json(mock.invalidUser);
    }
});

app.listen(port, function() {
    console.log('Express server listening on port: ' + port);
});