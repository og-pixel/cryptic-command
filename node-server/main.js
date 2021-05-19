const https = require('https');
const express = require('express');
const app = express();
const port = 3000;

const options = {
  hostname: 'redmine.miloszjakubanis.xyz',
  port: 443,
  path: '/',
  method: 'GET'
};

async function one(options){
    return new Promise((resolve, reject) => {
      let responseBody = '';
      const req = https.request(options, (res) => {
        res.on('data', (d) => {
          responseBody += d;
        });

        res.on('error', (error) => {
          reject();
        });

        res.on('end', () => {
          resolve(responseBody);
        })
      });        
      req.on('finish', () => {
        console.log("end");
        req.end();
        resolve(responseBody);
      });
      req.end();
    });
}

async function main() {
  console.log("hello main");
  var a = one(options);
  console.log("after one");
}


app.listen(port, () => console.log("Running"));

app.get('/', (req, res) => {
  const url = req.query.page;
  main();
});

