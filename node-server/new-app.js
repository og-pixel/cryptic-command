const express = require('express');
const app = express();
const port = 3000;
const Readability = require("@mozilla/readability");
const JSDOM = require('jsdom');

app.get('/', (req, res) => {
//    res.send('<h1>Hello Moto!</h1>');

    var article = new Readability(document).parse();
    res.send(article.content());
});

app.listen(port, () => {
    console.log(`Example app listening at http://localhost:${port}`);
});
