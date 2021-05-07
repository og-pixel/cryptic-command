const http = require('http');
const https = require('https');

const hostname = '127.0.0.1';
const port = 3000;
const express = require('express');
const app = express();
var { Readability } = require('@mozilla/readability');
var { JSDOM } = require('jsdom');

var path = require('path');
var fs = require('fs');


app.get('/', function (req, res) {
    const url = req.query.page;

    var article = {}
    const document = https.request(url, res2 => {

        res2.on('data', (html) => {
            var doc = new JSDOM(html.toString(), {
               url: url
            });

            const reader = new Readability(doc.window.document);
            article = reader.parse()
            console.log("response")
            console.log(article.content)

            // console.log(reader);
            // console.log(article.content);
        })
    })

    document.on('response', (callback) => {
        console.log("i was on response")
        console.log(article)
        fs.writeFile('article.html', article.content, {encoding: 'utf8'}, (callback) => {
            res.sendFile(path.join(__dirname + '/article.html'));
        })
    });
    // document.end();
});

app.listen(port, () => console.log("Running"));
