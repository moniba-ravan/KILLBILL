var express =require('express');
var killbill = express();
var jalaali = require('jalaali-js');
var mongoose =require('mongoose');
var nJwt = require('njwt');
var jdate =  require('jdate').JDate();
var bodyParser=require('body-parser');
var assert = require('assert');
killbill.use(bodyParser.urlencoded({extended:true}));
killbill.use(bodyParser.json());
var mongodb= require('mongodb');
var port = 7677;
var url ='mongodb://localhost:27017/KillBill';
var userModels = require('./models/user.js');
var clothesModels = require('./models/clothes.js');
var communicationsModel = require('./models/communications.js');
var entertainmentModels = require('./models/entertainment.js');
var furnitureModels = require('./models/furniture.js');
var groceriesModels = require('./models/groceries.js');
var stationeryModels = require('./models/stationery.js');
var tranportationModels = require('./models/tranportation.js');

killbill.use(function(req,res,next){
console.log('..There is some process..');
  next();
});
mongoose.connect(url);
var db = mongoose.connection;

//register
killbill.post('/register', function (req, res) {
  var register = require('./APIs/register');
  register(req, res, db,userModels,jalaali);
});

//log in
killbill.post('/login', function (req, res) {
  var userLogin = require('./APIs/login.js');
  userLogin(req, res,db, nJwt);
});


//insert clothes expenditure
killbill.post('/addClothes',function(req,res){
  var addClothes = require('./APIs/addClothes.js');
  addClothes(req,res, db,clothesModels,nJwt,jalaali);
});

//insert communications expenditure
killbill.post('/addCommunications',function(req,res){
  var addCommunications = require('./APIs/addCommunications.js');
  addCommunications(req,res, db,communicationsModel,nJwt,jalaali);
});

//insert entertainment expenditure
killbill.post('/addEntertainment',function(req,res){
  var addEntertainment = require('./APIs/addEntertainment.js');
  addEntertainment(req,res, db,entertainmentModels,nJwt,jalaali);
});

//insert furniture expenditure
killbill.post('/addFurniture',function(req,res){
  var addFurniture = require('./APIs/addFurniture.js');
  addFurniture(req,res, db,furnitureModels,nJwt,jalaali);
});

//insert groceries expenditure
killbill.post('/addgroceries',function(req,res){
  var addgroceries = require('./APIs/addgroceries.js');
  addgroceries(req,res, db,groceriesModels,nJwt,jalaali);
});

//insert stationery expenditure
killbill.post('/addStationery',function(req,res){
  var addStationery = require('./APIs/addStationery.js');
  addStationery(req,res, db,stationeryModels,nJwt,jalaali);
});

//insert tranportation expenditure
killbill.post('/addTranportation',function(req,res){
  var addTranportation = require('./APIs/addTranportation.js');
  addTranportation(req,res, db,tranportationModels,nJwt,jalaali);
});

//your db
killbill.get('/killbillDb',function(req,res){
  var killbillDb = require('./APIs/killbillDb.js');
  killbillDb(req,res,db,nJwt);
});
//delete dataBase
killbill.post('/deleteDB',function(req,res){
  var deletdb = require('./APIs/deleteDB.js');
  deletdb(req,res,db);
});

//delete user
killbill.post('/deleteUser',function(req,res){
  var deletUser = require('./APIs/deleteUser.js');
  deletUser(req,res,db);
});


  killbill.listen(port,function(){
    console.log('KillBill is working on port ' + port);
});
