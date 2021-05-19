const https = require('https');

const hostname = '127.0.0.1';
const port = 3000;
const express = require('express');
const app = express();
var { Readability } = require('@mozilla/readability');
var { JSDOM } = require('jsdom');

var path = require('path');
var fs = require('fs');

app.listen(port, () => console.log("Running"));

app.get('/', (req, res) => {
  const url = req.query.page;
  const options = {
      hostname: "stackoverflow.com/questions/17690803/node-js-getaddrinfo-enotfound",
      // hostname: url,
      path: "/",
      method: 'GET',
      port: 443,
  };
  var z = main(options, url); 
});

async function main(options, url) {
  console.log("enter");
  var a = await doRequest(options);
  console.log("after doRequest");
  console.log(a);
  // var b = await parseArticle(a, url);
  // console.log("after parseArticle");
  // console.log(b);
  // var c = await sendFile(b);
  // console.log("after sendFile");
  // console.log(c);

  // Promise.resolve(c);
}

/**
 * Do a request with options provided.
 *
 * @param {Object} options
 * @return {Promise} a promise of request
 */
async function doRequest(options) {

  return new Promise((resolve, reject) => {
      const req = https.get(options, (res) => {
        res.setEncoding('utf8');
        let responseBody = '';
        res.on('data', (chunk) => {
          responseBody += chunk;
        });

        res.on('end', () => {
          resolve(responseBody);
        });

        res.on('error', (err) => {
          reject(err);
        });

      });
      req.on('error', (err) => {
        reject(err);
      });

      req.on('end', () => {          
        resolve();
      });
  
      req.end();
  });

}

// async function sendFile(article){
//   return new Promise((resolve, reject) => {
    
//   })
// }

async function saveFile(article){
  return new Promise((resolve, reject) => {            
      fs.writeFile('article.html', article.content, {encoding: 'utf8'}, (callback) => {
        // resolve(res.sendFile(path.join(__dirname + '/article.html')));
        resolve(true);
      })
  });
}

async function parseArticle(responseBody, url) {
  var doc = new JSDOM(responseBody.toString(), {
      url: url
  });
  const reader = new Readability(doc.window.document);
  let article = reader.parse();

  await Promise.resolve(article);
}