const http = require('http');

const hostname = '127.0.0.1';
const port = 3000;
const express = require('express');
const app = express();
var { Readability } = require('@mozilla/readability');
var { JSDOM } = require('jsdom');
var path = require('path');
var fs = require('fs');
const rp = require('request-promise');

app.get('/a', function(req, res){
    res.sendFile(path.join(__dirname + '/article.html'));
});

app.get('/', function (req, res) {
    const url = req.query.page;

    var content = "";
    rp(url)
        .then(function(html, callback){
            //success!
            var doc = new JSDOM(html.toString(), {
                url: url
            });
            let reader = new Readability(doc.window.document);
            let article = reader.parse();

            // fs.appendFile(article.title +'.html', article.content.toString(), function (err) {
            fs.appendFile('article.html', article.content.toString(), function (err) {
                if(err) throw err;
            });
            res.sendFile(path.join(__dirname + '/article.html'));
            callback();
        })
        .catch(function(err){
            //handle error
        });
    
});

app.listen(port, () => console.log("Running"));
